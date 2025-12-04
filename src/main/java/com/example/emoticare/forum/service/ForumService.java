package com.example.emoticare.forum.service;
import com.example.emoticare.forum.model.ForumPost;
import com.example.emoticare.forum.repo.ForumRepository;
import java.util.List;

public class ForumService {
    private ForumRepository forumRepository;

    public ForumService() { this.forumRepository = new ForumRepository(); }

    public boolean createPost(Long userId, String title, String content, String category) {
        ForumPost post = new ForumPost(userId, title, content, category);
        return forumRepository.createPost(post);
    }

    public List<ForumPost> getAllPosts() { return forumRepository.getAllPosts(); }
    public List<ForumPost> getPostsByCategory(String category) { return forumRepository.getPostsByCategory(category); }
}