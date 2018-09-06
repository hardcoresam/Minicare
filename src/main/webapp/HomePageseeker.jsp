<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Welcome <c:out value="${requestScope.firstName}"/></h1>
<hr><br><br>

<div align="center"><c:out value="${requestScope.success}"/></div>
<br><br>

<form action="PostJob.jsp" method="POST">
<div align="center"><input type = "submit" value="Post Job"></div>
<br><br>
</form>

<form action="ListJobs.do" method="POST">
<div align="center"><input type = "submit" value="List Jobs"></div>
<br><br>
</form>

<form action="EditProfile.do" method="POST">
<div align="center"><input type = "submit" value="Edit Profile"></div>
<br><br>
</form>

<form action="LogOut.do" method="POST">
<div align="center"><input type = "submit" value="Log Out"></div>
<br><br>
</form>

<form action="CloseAccount.do" method="POST" onsubmit="return confirm('Do you really want to close your Account?');">
<div align="center"><input type = "submit" value="Close Account"></div>
<br><br>
</form>

</body>
</html>