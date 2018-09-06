<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Edit Your Job Here</h1>
<hr><br><br>

<form action="AlterJob.do" method="POST">

<table border="0" align="center">
<tr>
<td>Enter Title:*</td>
<td><input type="text" name="title" value="${requestScope.form.title}"></td>
<td><c:out value='${errors["title"]}'/></td>
</tr>

<tr>
<td>Enter Pay Per Hour:*</td>
<td><input type="text" name="payPerHour" value="${requestScope.form.payPerHour}"></td>
<td><c:out value='${errors["payPerHour"]}'/></td>
</tr>

<tr>
<td>Enter Start Time:*</td>
<td><input type="time" name="startTime" value="${requestScope.form.startTime}"></td>
<td><c:out value='${errors["startTime"]}'/></td>
</tr>

<tr>
<td>Enter End Time:*</td>
<td><input type="time" name="endTime" value="${requestScope.form.endTime}"></td>
<td><c:out value='${errors["endTime"]}'/></td>
</tr>

<tr>
<td>Enter Start Date:*</td>
<td><input type="date" name="startDate" value="${requestScope.form.startDate}"></td>
<td><c:out value='${errors["startDate"]}'/></td>
</tr>

<tr>
<td>Enter End Date:*</td>
<td><input type="date" name="endDate" value="${requestScope.form.endDate}"></td>
<td><c:out value='${errors["endDate"]}'/></td>
</tr>

<tr>
<td><input type="submit" value="Edit Job"></td>
<td><input type="reset" value="Clear"></td>
</tr>

<input type ="hidden" name="jobId" value="${requestScope.form.jobId}">

</table>
</form>
</body>
</html>