<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style>
.right{ float: right; }
</style>
</head>
<body>
<h1>Minicare</h1>

<div class="right">
<form action="${pageContext.request.contextPath}/jsp/seeker/HomePageseeker.jsp" method="POST">
<div align="center"><input type = "submit" value="Home"></div>
</form>
</div>

<div class="right">
<form action="${pageContext.request.contextPath}/member/logOut.do" method="POST">
<div align="center"><input type = "submit" value="Log Out"></div>
</form>
</div>



</body>
</html>