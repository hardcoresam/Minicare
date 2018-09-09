<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Welcome <c:out value="${sessionScope.member.firstName}"/></h1>
<hr><br><br>

<div align="center"><c:out value="${requestScope.success}"/></div>
<br><br>

<form action="${pageContext.request.contextPath}/sitter/listActiveJobs.do" method="POST">
<div align="center"><input type = "submit" value="List Active Jobs"></div>
<br><br>
</form>

<form action="${pageContext.request.contextPath}/sitter/listApplication.do" method="POST">
<div align="center"><input type = "submit" value="List Applications"></div>
<br><br>
</form>

<form action="${pageContext.request.contextPath}/sitter/listApplication.do" method="POST">
<div align="center"><input type = "submit" value="Delete Application"></div>
<br><br>
</form>

<form action="${pageContext.request.contextPath}/member/editProfile.do" method="POST">
<div align="center"><input type = "submit" value="Edit Profile"></div>
<br><br>
</form>

<form action="${pageContext.request.contextPath}/member/logOut.do" method="POST">
<div align="center"><input type = "submit" value="Log Out"></div>
<br><br>
</form>

<form action="${pageContext.request.contextPath}/member/closeAccount.do" method="POST" onsubmit="return confirm('Do you really want to close your Account?');">
<div align="center"><input type = "submit" name="    " value="Close Account"></div>
<br><br>
</form>

</body>
</html>