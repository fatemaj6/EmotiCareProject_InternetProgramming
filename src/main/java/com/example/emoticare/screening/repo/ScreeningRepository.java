package com.example.emoticare.screening.repo;
import com.example.emoticare.screening.model.Screening;
import com.example.emoticare.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScreeningRepository {
    public boolean saveScreening(Screening screening) {
        String query = "INSERT INTO screenings (user_id, assessment_type, score, level, responses, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, screening.getUserId());
            stmt.setString(2, screening.getAssessmentType());
            stmt.setInt(3, screening.getScore());
            stmt.setString(4, screening.getLevel());
            stmt.setString(5, screening.getResponses());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Screening> getUserScreenings(Long userId) {
        List<Screening> screenings = new ArrayList<>();
        String query = "SELECT * FROM screenings WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { screenings.add(mapResultSetToScreening(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return screenings;
    }

    private Screening mapResultSetToScreening(ResultSet rs) throws SQLException {
        Screening screening = new Screening();
        screening.setScreeningId(rs.getLong("screening_id"));
        screening.setUserId(rs.getLong("user_id"));
        screening.setAssessmentType(rs.getString("assessment_type"));
        screening.setScore(rs.getInt("score"));
        screening.setLevel(rs.getString("level"));
        screening.setResponses(rs.getString("responses"));
        screening.setCreatedAt(rs.getTimestamp("created_at"));
        return screening;
    }
}