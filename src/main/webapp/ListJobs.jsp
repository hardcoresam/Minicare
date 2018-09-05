<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">List Of Jobs</h1>
<hr><br><br>

<table border="1" align="center">

<tr>
<th>Title</th>
<th>Status</th>
<th>Start Date</th>
<th>End Date</th>
<th>Edit Job</th>
<th>List Applications</th>
<th>Close Job</th>
</tr>

<c:forEach items="${requestScope.listOfJobs}" var="job">

<tr>

<td>${job.title}</td>
<td>${job.status}</td>
<td>${job.startDate}</td>
<td>${job.endDate}</td>

<form action="EditJob.do" method="POST">
    <input type = "hidden" name = "jobId" value = "${job.jobId}" >
    <td>
        <input type= "submit" value= "Edit">
    </td>
</form>

<form action="ListApplicationSeeker.do" method="POST">
<input type = "hidden" name = "jobId" value = "${job.jobId}">
<td><input type= "submit" value= "List Applications"></td>
</form>

<form action="DeleteJob.do" method="POST" onsubmit="return confirm('Do you really want to delete the Job?');">
<input type = "hidden" name = "jobId" value = "${job.jobId}">
<td><input type= "submit" value= "Delete"></td>
</form>

</tr>

</c:forEach>

</table>
</body>
</html>