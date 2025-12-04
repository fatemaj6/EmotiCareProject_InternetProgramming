package com.example.emoticare.auth.repo;

import com.example.emoticare.auth.model.User;
import com.example.emoticare.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (full_name, email, password, created_at) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserById(Long userId) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY created_at DESC";
        
        try (Connection conn = DatabaseConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public boolean updateUser(User user) {
        String query = "UPDATE users SET full_name = ?, phone = ?, updated_at = NOW() WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getPhone());
            stmt.setLong(3, user.getUserId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setAdmin(rs.getBoolean("is_admin"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        return user;
    }
    
}
