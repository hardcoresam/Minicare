<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Apply To Job</h1>
<hr><br><br>

<form action = "ApplyJob.do" method = "POST">

<table border="1" align="center">
<tr>
<td>Enter Expected Pay*:</td>
<td><input type="text" name="expectedPay" value="${param.expectedPay}"></td>
<td><c:out value='${errors["expectedPay"]}'/></td>
</tr>

<tr>
<td><input type="submit" value="Apply"></td>
<td><input type="reset" value="Clear"></td>
</tr>

<input type = "hidden" name = "jobId" value = "${param.jobId}" >  //check this whether param is working or not?

</table>
</form>
</body>
</html>

