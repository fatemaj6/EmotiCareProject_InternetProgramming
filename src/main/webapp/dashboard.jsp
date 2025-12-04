<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% if (session.getAttribute("user") == null) { response.sendRedirect(request.getContextPath() + "/"); return; } %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Emoticare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
</head>
<body class="dashboard-page">
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo">💙</div>
            <h2>Emoticare</h2>
        </div>
        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/dashboard.jsp" class="nav-item active">🏠 Dashboard</a>
            <a href="${pageContext.request.contextPath}/learning/hub" class="nav-item">📚 Learning</a>
            <a href="${pageContext.request.contextPath}/screening/assess" class="nav-item">📋 Screening</a>
            <a href="${pageContext.request.contextPath}/forum/list" class="nav-item">💬 Forum</a>
            <a href="${pageContext.request.contextPath}/chatbot/chat" class="nav-item">🤖 AI Chat</a>
            <a href="${pageContext.request.contextPath}/auth/profile" class="nav-item">👤 Profile</a>
            <c:if test="${sessionScope.isAdmin == true}">
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-item">⚙️ Admin</a>
            </c:if>
        </nav>
        <div class="sidebar-footer">
            <button onclick="logout()" class="nav-item">🚪 Logout</button>
        </div>
    </aside>

    <main class="main-content">
        <header class="header">
            <h1>Welcome, <c:out value="${sessionScope.user.fullName}"/></h1>
            <p>Your mental wellness journey starts here</p>
        </header>

        <div class="dashboard-container">
            <section class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon">📚</div>
                    <div class="stat-info"><p>Modules</p><p class="stat-value">6/6</p></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon">🏆</div>
                    <div class="stat-info"><p>Badges</p><p class="stat-value">4/6</p></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon">⏱️</div>
                    <div class="stat-info"><p>Time</p><p class="stat-value">12h</p></div>
                </div>
            </section>

            <section class="quick-actions">
                <h2>Quick Actions</h2>
                <div class="action-grid">
                    <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/learning/hub'">
                        <div class="action-icon">📖</div>
                        <h3>Learning Hub</h3>
                        <p>Access resources</p>
                    </div>
                    <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/screening/assess'">
                        <div class="action-icon">📋</div>
                        <h3>Self-Screening</h3>
                        <p>Check your status</p>
                    </div>
                    <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/forum/list'">
                        <div class="action-icon">💬</div>
                        <h3>Community</h3>
                        <p>Join discussions</p>
                    </div>
                    <div class="action-card" onclick="location.href='${pageContext.request.contextPath}/chatbot/chat'">
                        <div class="action-icon">🤖</div>
                        <h3>AI Chat</h3>
                        <p>Talk to AI</p>
                    </div>
                </div>
            </section>
        </div>
    </main>

    <script>
        function logout() {
            if (confirm('Logout?')) {
                window.location.href = '${pageContext.request.contextPath}/auth/logout';
            }
        }
    </script>
</body>
</html>