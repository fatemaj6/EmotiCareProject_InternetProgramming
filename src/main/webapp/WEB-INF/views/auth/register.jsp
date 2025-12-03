<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Create Account</title></head>
<body>
  <h2>Create Account</h2>
  <jsp:include page="_messages.jsp"/>

  <form method="post" action="${pageContext.request.contextPath}/auth/register">
    <label>Email</label><br/>
    <input type="email" name="email" required/><br/><br/>

    <label>Password</label><br/>
    <input type="password" name="password" minlength="6" required/><br/><br/>

    <label>Role</label><br/>
    <select name="role">
      <option value="STUDENT">Student</option>
      <option value="ADMIN">Admin</option>
    </select><br/><br/>

    <button type="submit">Register</button>
  </form>

  <p>Already have an account?
    <a href="${pageContext.request.contextPath}/auth/login">Sign In</a>
  </p>
</body>
</html>
