<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Screening History - EmotiCare</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <h1>Your Screening History</h1>

        <c:if test="${empty screenings}">
            <p>You haven't completed any screenings yet.</p>
            <a href="<c:url value='/screening/start'/>" class="btn">Take Your First Screening</a>
        </c:if>

        <c:if test="${not empty screenings}">
            <div class="screening-list">
                <c:forEach var="s" items="${screenings}">
                    <div class="screening-item">
                        <h3>${s.screeningType}</h3>
                        <p>Date: ${s.completedAt}</p>
                        <p>Score: ${s.totalScore} | Severity: ${s.severity}</p>
                        <a href="<c:url value='/screening/result/${s.id}'/>">View Details</a>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <a href="<c:url value='/home'/>">Back to Home</a>
    </div>
</body>
</html>
