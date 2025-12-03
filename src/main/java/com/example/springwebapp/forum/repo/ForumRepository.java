package com.example.springwebapp.forum.repo;

import com.example.springwebapp.forum.model.ForumPost;
import com.example.springwebapp.forum.model.ForumComment;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ForumRepository {
    private final Map<String, ForumPost> postDb = new ConcurrentHashMap<>();
    private final Map<String, ForumComment> commentDb = new ConcurrentHashMap<>();

    public ForumPost savePost(ForumPost post) {
        postDb.put(post.getId(), post);
        return post;
    }

    public Optional<ForumPost> findPostById(String id) {
        return Optional.ofNullable(postDb.get(id));
    }

    public List<ForumPost> findAllPosts() {
        return postDb.values().stream()
                .sorted((a, b) -> {
                    if (a.isPinned() != b.isPinned()) {
                        return a.isPinned() ? -1 : 1;
                    }
                    return b.getCreatedAt().compareTo(a.getCreatedAt());
                })
                .collect(Collectors.toList());
    }

    public List<ForumPost> findPostsByCategory(String category) {
        return postDb.values().stream()
                .filter(p -> p.getCategory().equals(category))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public void deletePost(String id) {
        postDb.remove(id);
        commentDb.values().removeIf(c -> c.getPostId().equals(id));
    }

    public ForumComment saveComment(ForumComment comment) {
        commentDb.put(comment.getId(), comment);
        return comment;
    }

    public List<ForumComment> findCommentsByPostId(String postId) {
        return commentDb.values().stream()
                .filter(c -> c.getPostId().equals(postId))
                .sorted(Comparator.comparing(ForumComment::getCreatedAt))
                .collect(Collectors.toList());
    }

    public void deleteComment(String id) {
        commentDb.remove(id);
    }
}
