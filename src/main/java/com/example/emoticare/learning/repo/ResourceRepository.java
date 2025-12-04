package com.example.emoticare.learning.repo;
import com.example.emoticare.learning.model.Resource;
import com.example.emoticare.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceRepository {
    public List<Resource> getAllResources() {
        List<Resource> resources = new ArrayList<>();
        String query = "SELECT * FROM resources ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) { resources.add(mapResultSetToResource(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return resources;
    }

    public List<Resource> getResourcesByCategory(String category) {
        List<Resource> resources = new ArrayList<>();
        String query = "SELECT * FROM resources WHERE category = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { resources.add(mapResultSetToResource(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return resources;
    }

    public Resource getResourceById(Long resourceId) {
        String query = "SELECT * FROM resources WHERE resource_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, resourceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { return mapResultSetToResource(rs); }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Resource mapResultSetToResource(ResultSet rs) throws SQLException {
        Resource resource = new Resource();
        resource.setResourceId(rs.getLong("resource_id"));
        resource.setTitle(rs.getString("title"));
        resource.setDescription(rs.getString("description"));
        resource.setResourceType(rs.getString("resource_type"));
        resource.setCategory(rs.getString("category"));
        resource.setContent(rs.getString("content"));
        resource.setDuration(rs.getInt("duration"));
        resource.setProgress(rs.getInt("progress"));
        resource.setCompleted(rs.getBoolean("completed"));
        resource.setCreatedAt(rs.getTimestamp("created_at"));
        return resource;
    }
}