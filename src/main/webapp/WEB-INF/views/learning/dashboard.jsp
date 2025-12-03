<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Learning Hub - EmotiCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"/>
</head>
<body>
<div class="container">
    <h2>Learning Hub</h2>

    <div class="progress-bar">
        <div class="progress-fill">58% Complete</div>
    </div>
    <p>Modules Completed: 7 / 12</p>
    <p>Total Learning Time: 12 hours 30 mins</p>

    <h3>Resources</h3>
    <c:forEach var="r" items="${resources}">
        <div style="margin-bottom:15px;">
            <strong>${r.title}</strong> (${r.type}) - ${r.durationMinutes} min<br/>
            Progress: 100%<br/>
            <button>Review</button>
        </div>
    </c:forEach>

    <h3>Quizzes</h3>
    <ul>
        <li>Stress Management Quiz - Score: 85% <button>Retake</button></li>
        <li>Emotional Intelligence Assessment <button>Start</button></li>
        <li>Mindfulness Check <button>Start</button></li>
    </ul>

    <h3>Badges</h3>
    <ul>
        <li>✅ First Steps</li>
        <li>✅ Knowledge Seeker</li>
        <li>✅ Quiz Master</li>
        <li>✅ 7-Day Streak</li>
        <li>🔒 Mindful Learner</li>
        <li>🔒 Community Helper</li>
    </ul>

    <p><strong>Next Milestone:</strong> Complete 2 more modules to unlock "Mindful Learner" badge</p>
</div>
</body>
</html>
