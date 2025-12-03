<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${post.title} - EmotiCare Forum</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <div class="post-full">
            <c:if test="${post.pinned}">
                <span class="pin-badge">📌 Pinned</span>
            </c:if>

            <h1>${post.title}</h1>

            <div class="post-meta">
                <span>Category: ${post.category}</span> |
                <span>Author: ${post.authorName}</span> |
                <span>Posted: ${post.createdAt}</span>
            </div>

            <div class="post-content">
                <p>${post.content}</p>
            </div>

            <c:if test="${sessionScope.AUTH_USER_ROLE == 'ADMIN'}">
                <div class="admin-actions">
                    <form action="<c:url value='/forum/post/${post.id}/pin'/>" method="post" style="display:inline;">
                        <button type="submit" class="btn-admin">
                            ${post.pinned ? 'Unpin' : 'Pin'} Post
                        </button>
                    </form>
                    <form action="<c:url value='/forum/post/${post.id}/delete'/>" method="post" 
                          style="display:inline;" onsubmit="return confirm('Delete this post?');">
                        <button type="submit" class="btn-danger">Delete Post</button>
                    </form>
                </div>
            </c:if>
        </div>

        <div class="comments-section">
            <h2>Comments (${post.comments.size()})</h2>

            <c:if test="${not empty post.comments}">
                <div class="comments-list">
                    <c:forEach var="comment" items="${post.comments}">
                        <div class="comment-item">
                            <p class="comment-author">${comment.authorName}</p>
                            <p class="comment-date">${comment.createdAt}</p>
                            <p class="comment-content">${comment.content}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty post.comments}">
                <p>No comments yet. Be the first to comment!</p>
            </c:if>

            <div class="add-comment">
                <h3>Add a Comment</h3>
                <form action="<c:url value='/forum/post/${post.id}/comment'/>" method="post">
                    <textarea name="content" required rows="4" 
                              placeholder="Share your thoughts..."></textarea>
                    <button type="submit" class="btn btn-primary">Post Comment</button>
                </form>
            </div>
        </div>

        <a href="<c:url value='/forum'/>" class="btn btn-secondary">Back to Forum</a>
    </div>
</body>
</html>
