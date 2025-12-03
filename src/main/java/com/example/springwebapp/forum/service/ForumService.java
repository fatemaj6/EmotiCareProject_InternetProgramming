package com.example.springwebapp.forum.service;

import com.example.springwebapp.forum.model.ForumPost;
import com.example.springwebapp.forum.model.ForumComment;
import com.example.springwebapp.forum.repo.ForumRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForumService {
    private final ForumRepository repository;

    public ForumService(ForumRepository repository) {
        this.repository = repository;
    }

    public ForumPost createPost(String userId, String authorName, String title, 
                                String content, String category) {
        String id = UUID.randomUUID().toString();
        ForumPost post = new ForumPost(id, userId, authorName, title, content, 
                                       category, LocalDateTime.now());
        return repository.savePost(post);
    }

    public List<ForumPost> getAllPosts() {
        return repository.findAllPosts();
    }

    public List<ForumPost> getPostsByCategory(String category) {
        return repository.findPostsByCategory(category);
    }

    public Optional<ForumPost> getPostById(String id) {
        Optional<ForumPost> postOpt = repository.findPostById(id);
        postOpt.ifPresent(post -> {
            List<ForumComment> comments = repository.findCommentsByPostId(id);
            post.setComments(comments);
        });
        return postOpt;
    }

    public ForumComment addComment(String postId, String userId, String authorName, String content) {
        String id = UUID.randomUUID().toString();
        ForumComment comment = new ForumComment(id, postId, userId, authorName, 
                                                content, LocalDateTime.now());
        return repository.saveComment(comment);
    }

    public void deletePost(String id) {
        repository.deletePost(id);
    }

    public void togglePinPost(String id) {
        repository.findPostById(id).ifPresent(post -> {
            post.setPinned(!post.isPinned());
            repository.savePost(post);
        });
    }
}
