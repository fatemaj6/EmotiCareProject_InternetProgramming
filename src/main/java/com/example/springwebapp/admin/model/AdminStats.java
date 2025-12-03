package com.example.springwebapp.admin.model;

public class AdminStats {
    private int totalUsers;
    private int activeUsers;
    private int inactiveUsers;
    private int forumPosts; // placeholder for Phase 2

    public AdminStats(int totalUsers, int activeUsers, int inactiveUsers, int forumPosts) {
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.inactiveUsers = inactiveUsers;
        this.forumPosts = forumPosts;
    }

    public int getTotalUsers() { return totalUsers; }
    public int getActiveUsers() { return activeUsers; }
    public int getInactiveUsers() { return inactiveUsers; }
    public int getForumPosts() { return forumPosts; }
}
