<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forum - EmotiCare</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <div class="forum-header">
            <h1>Community Forum</h1>
            <a href="<c:url value='/forum/new'/>" class="btn btn-primary">Create New Post</a>
        </div>

        <div class="forum-filters">
            <h3>Filter by Category:</h3>
            <a href="<c:url value='/forum'/>" class="filter-link ${empty selectedCategory ? 'active' : ''}">All</a>
            <a href="<c:url value='/forum?category=GENERAL'/>" class="filter-link ${selectedCategory == 'GENERAL' ? 'active' : ''}">General</a>
            <a href="<c:url value='/forum?category=SUPPORT'/>" class="filter-link ${selectedCategory == 'SUPPORT' ? 'active' : ''}">Support</a>
            <a href="<c:url value='/forum?category=TIPS'/>" class="filter-link ${selectedCategory == 'TIPS' ? 'active' : ''}">Tips</a>
            <a href="<c:url value='/forum?category=RESOURCES'/>" class="filter-link ${selectedCategory == 'RESOURCES' ? 'active' : ''}">Resources</a>
        </div>

        <c:if test="${empty posts}">
            <p>No posts found. Be the first to create one!</p>
        </c:if>

        <c:if test="${not empty posts}">
            <div class="forum-posts">
                <c:forEach var="post" items="${posts}">
                    <div class="post-item ${post.pinned ? 'pinned' : ''}">
                        <c:if test="${post.pinned}">
                            <span class="pin-badge">📌 Pinned</span>
                        </c:if>
                        <h2><a href="<c:url value='/forum/post/${post.id}'/>">${post.title}</a></h2>
                        <p class="post-meta">
                            by ${post.authorName} | ${post.category} | ${post.createdAt}
                        </p>
                        <p class="post-preview">${post.content.substring(0, Math.min(150, post.content.length()))}...</p>
                        <p class="post-stats">${post.comments.size()} comments</p>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <a href="<c:url value='/home'/>">Back to Home</a>
    </div>
</body>
</html>
