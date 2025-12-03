<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Post - EmotiCare Forum</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <h1>Create New Forum Post</h1>

        <form action="<c:url value='/forum/create'/>" method="post">
            <div class="form-group">
                <label for="title">Post Title:</label>
                <input type="text" id="title" name="title" required maxlength="200" 
                       placeholder="Enter a descriptive title">
            </div>

            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" required>
                    <option value="GENERAL">General Discussion</option>
                    <option value="SUPPORT">Support & Advice</option>
                    <option value="TIPS">Tips & Strategies</option>
                    <option value="RESOURCES">Resources</option>
                </select>
            </div>

            <div class="form-group">
                <label for="content">Post Content:</label>
                <textarea id="content" name="content" required rows="10" 
                          placeholder="Share your thoughts, questions, or experiences..."></textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Create Post</button>
                <a href="<c:url value='/forum'/>" class="btn btn-secondary">Cancel</a>
            </div>
        </form>

        <div class="guidelines">
            <h3>Community Guidelines</h3>
            <ul>
                <li>Be respectful and supportive</li>
                <li>Protect your privacy - don't share personal information</li>
                <li>Keep content relevant to mental health and wellbeing</li>
                <li>Report inappropriate content to moderators</li>
            </ul>
        </div>
    </div>
</body>
</html>
