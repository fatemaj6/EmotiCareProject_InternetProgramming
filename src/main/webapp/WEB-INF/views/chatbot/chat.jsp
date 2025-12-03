<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Chatbot - EmotiCare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/emoticare.css"/>
</head>
<body>
<div class="container">
    <h2>EmotiCare Chatbot</h2>
    <div class="chat-box" style="border:1px solid #ccc; padding:10px; height:300px; overflow-y:scroll;">
        <c:forEach var="msg" items="${conversation}">
            <p><strong>${msg.sender}:</strong> ${msg.content}</p>
        </c:forEach>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/chatbot/send">
        <input type="text" name="message" placeholder="Type your message..." required/>
        <button type="submit">Send</button>
    </form>
</div>
</body>
</html>
