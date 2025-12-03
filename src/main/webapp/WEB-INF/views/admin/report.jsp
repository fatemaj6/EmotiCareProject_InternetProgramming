<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Admin Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"/>
</head>
<body>
<div class="container">
    <h2>Admin Report</h2>
    <ul>
        <c:forEach var="line" items="${report}">
            <li>${line}</li>
        </c:forEach>
    </ul>
    <p><a href="${pageContext.request.contextPath}/admin/users">Manage Users</a></p>
</div>
</body>
</html>
