<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Profile</title></head>
<body>
  <h2>My Profile</h2>
  <jsp:include page="_messages.jsp"/>

  <c:if test="${not empty user}">
    <p>Email: ${user.email}</p>
    <p>Role: ${user.role}</p>

    <form method="post" action="${pageContext.request.contextPath}/auth/profile">
      <label>First Name</label><br/>
      <input type="text" name="firstName" value="${user.firstName}"/><br/><br/>

      <label>Last Name</label><br/>
      <input type="text" name="lastName" value="${user.lastName}"/><br/><br/>

      <label>Phone</label><br/>
      <input type="text" name="phone" value="${user.phone}"/><br/><br/>

      <label>Date of Birth (yyyy-MM-dd)</label><br/>
      <input type="text" name="dateOfBirth" value="${user.dateOfBirth}"/><br/><br/>

      <button type="submit">Save Changes</button>
    </form>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/auth/logout" style="margin-top:20px;">
    <button type="submit">Logout</button>
  </form>
</body>
</html>
