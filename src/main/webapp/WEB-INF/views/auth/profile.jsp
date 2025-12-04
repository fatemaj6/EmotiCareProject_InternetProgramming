<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html><head><title>Profile</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"></head>
<body><div class="container"><h1>User Profile</h1><div class="card"><h2><c:out value="${sessionScope.user.fullName}"/></h2><p>Email: <c:out value="${sessionScope.user.email}"/></p></div></div></body></html>