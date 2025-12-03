<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Screening Results - EmotiCare</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <h1>Your Screening Results</h1>

        <div class="result-card">
            <h2>${screening.screeningType} Screening</h2>
            <p><strong>Completed:</strong> ${screening.completedAt}</p>
            <p><strong>Total Score:</strong> ${screening.totalScore}</p>
            <p><strong>Severity Level:</strong> <span class="severity-${screening.severity}">${screening.severity}</span></p>

            <div class="recommendations">
                <h3>Recommendations</h3>
                <p>${screening.recommendations}</p>
            </div>

            <div class="actions">
                <a href="<c:url value='/screening/history'/>" class="btn">View History</a>
                <a href="<c:url value='/screening/start'/>" class="btn">Take Another Screening</a>
                <a href="<c:url value='/learning/resources'/>" class="btn">Browse Resources</a>
            </div>
        </div>

        <div class="disclaimer">
            <p><strong>Important:</strong> This screening is not a diagnostic tool. 
            If you are experiencing severe symptoms, please seek professional help immediately.</p>
        </div>
    </div>
</body>
</html>
