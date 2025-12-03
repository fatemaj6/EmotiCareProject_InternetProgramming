<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login - EmotiCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"/>
</head>
<body>
<div class="container">
    <h2>Welcome to EmotiCare</h2>
    <p>Your emotional wellness companion</p>

    <c:if test="${not empty error}">
        <div style="color:red">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/auth/login">
        <label>Email Address</label>
        <input type="email" name="email" value="${email}" required/>

        <label>Password</label>
        <input type="password" name="password" required/>

        <label><input type="checkbox"/> Remember me</label>
        <a href="#">Forgot password?</a><br/><br/>

        <button type="submit">Sign In</button>
    </form>

    <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth/register">Create Account</a></p>
</div>
</body>
</html>

