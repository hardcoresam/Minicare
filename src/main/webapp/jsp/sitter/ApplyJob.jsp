<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Apply To Job</h1>
<hr><br><br>

<form action = "${pageContext.request.contextPath}/sitter/applyJob.do" method = "POST">

<table border="1" align="center">
<tr>
<td>Enter Expected Pay*:</td>
<td><input type="text" name="expectedPay" value="${requestScope.expectedPay}"></td>
<font color="red"><c:out value='${errors}'/></font>
</tr>

<tr>
<td><input type="submit" value="Apply"></td>
<td><input type="reset" value="Clear"></td>
</tr>

<input type = "hidden" name = "jobId" value = "${param.jobId}" >

</table>
</form>
</body>
</html>

