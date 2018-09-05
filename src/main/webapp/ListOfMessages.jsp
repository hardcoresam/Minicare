<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Messaging</h1>
<hr><br><br>

<table border="1" align="center">

<c:forEach items="${requestScope.listOfMessages}" var="message">

<tr>

<td>${message.content}</td>

</tr>

</c:forEach>

</table>

<br><br>

<form action="StoreMessage.do" method="POST">
    <input type = "hidden" name="conversationId" value="${conversationId}">
    Enter Your Message : <textarea rows="4" cols="50" name="message" placeholder="Enter Your Message Here"></textarea>
    <c:out value='${messageError}'/>
    <input type= "submit" value= "Send">
</form>

</body>
</html>