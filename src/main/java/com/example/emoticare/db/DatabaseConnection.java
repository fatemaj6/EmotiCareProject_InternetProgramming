package com.example.emoticare.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/emoticare_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    
    // ⚠️ UPDATE THIS dengan password MySQL Anda!
    // Jika password kosong: private static final String DB_PASSWORD = "";
    // Jika password "admin123": private static final String DB_PASSWORD = "admin123";
    private static final String DB_PASSWORD = "admin123";
    
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    static {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("✅ MySQL Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✅ Database connection successful!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
