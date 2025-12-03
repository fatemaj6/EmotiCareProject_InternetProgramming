<%-- Common message partial --%>
<c:if test="${not empty error}">
  <div style="color:#b00020">${error}</div>
</c:if>
<c:if test="${not empty msg}">
  <div style="color:#006400">${msg}</div>
</c:if>
