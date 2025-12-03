<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"/>
</head>
<body>
<div class="container">
    <h2>Manage Users</h2>
    <table>
        <tr>
            <th>ID</th><th>Email</th><th>Name</th><th>Role</th><th>Actions</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.id}</td>
                <td>${u.email}</td>
                <td>${u.firstName} ${u.lastName}</td>
                <td>${u.role}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/users/${u.id}/suspend">
                        <button type="submit">Suspend</button>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/admin/users/${u.id}/delete">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/admin/report">Back to Report</a>
</div>
</body>
</html>
