package com.example.emoticare.forum.repo;
import com.example.emoticare.forum.model.ForumPost;
import com.example.emoticare.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumRepository {
    public boolean createPost(ForumPost post) {
        String query = "INSERT INTO forums (user_id, title, content, category, created_at) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            stmt.setString(4, post.getCategory());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<ForumPost> getAllPosts() {
        List<ForumPost> posts = new ArrayList<>();
        String query = "SELECT * FROM forums ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) { posts.add(mapResultSetToForumPost(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return posts;
    }

    public List<ForumPost> getPostsByCategory(String category) {
        List<ForumPost> posts = new ArrayList<>();
        String query = "SELECT * FROM forums WHERE category = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { posts.add(mapResultSetToForumPost(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return posts;
    }

    private ForumPost mapResultSetToForumPost(ResultSet rs) throws SQLException {
        ForumPost post = new ForumPost();
        post.setForumId(rs.getLong("forum_id"));
        post.setUserId(rs.getLong("user_id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setCategory(rs.getString("category"));
        post.setLikes(rs.getInt("likes"));
        post.setCreatedAt(rs.getTimestamp("created_at"));
        return post;
    }
}