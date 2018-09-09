<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">List Of Applications</h1>
<hr><br><br>

<div align="center"><c:out value="${requestScope.success}"/></div>
<br>

<table border="1" align="center">

<tr>
<th>Title</th>
<th>Expected Pay</th>
<th>Pay Per Hour</th>
<th>Action</th>
<th>Message</th>
</tr>

<c:forEach items="${requestScope.listOfApplications}" var="jobDto">

<tr>

<td>${jobDto.title}</td>
<td>${jobDto.expectedPay}</td>
<td>${jobDto.payPerHour}</td>

<form action="${pageContext.request.contextPath}/sitter/deleteApplication.do" method="POST" onsubmit="return confirm('Do you really want to delete this Application?');">
    <input type = "hidden" name = "applicationId" value = "${jobDto.applicationId}" >
    <input type = "hidden" name = "applicationId" value = "${jobDto.applicationId}" >
    <td>
        <input type= "submit" value= "Delete">
    </td>
</form>

<form action="${pageContext.request.contextPath}/member/sendMessage.do" method="POST">
    <input type = "hidden" name = "seekerId" value = "${jobDto.seekerId}" >
    <td>
        <input type= "submit" value= "Message Him">
    </td>
</form>

</tr>

</c:forEach>

</table>
</body>
</html>
