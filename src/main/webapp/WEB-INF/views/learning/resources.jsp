<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Learning Resources</title></head>
<body>
<h2>Available Resources</h2>
<c:forEach var="r" items="${resources}">
    <div style="margin-bottom:15px;">
        <strong>${r.title}</strong> (${r.type}) - ${r.durationMinutes} min<br/>
        <c:if test="${r.type eq 'QUIZ'}">
            <a href="${pageContext.request.contextPath}/learning/quiz/${r.id}">Take Quiz</a>
        </c:if>
    </div>
</c:forEach>

<h3>Add Resource (Admin)</h3>
<form method="post" action="${pageContext.request.contextPath}/learning/add">
    Title: <input type="text" name="title"/><br/>
    Type: <select name="type">
        <option value="ARTICLE">Article</option>
        <option value="VIDEO">Video</option>
        <option value="QUIZ">Quiz</option>
    </select><br/>
    Content: <input type="text" name="content"/><br/>
    Duration: <input type="number" name="duration"/><br/>
    <button type="submit">Add</button>
</form>
</body>
</html>
