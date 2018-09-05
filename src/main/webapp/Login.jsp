<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Welcome</h1>
<hr><br><br>
<h3 align="center">Login Here</h3>
<br>

<form action="Login.do" method="POST">

<table border="0" align="center">

<tr>
<td><c:out value='${loginError}'/></td>
</tr>

<tr>
<td>Enter Email:*</td>
<td><input type="text" name="email" value="${param.email}"></td>
<td><c:out value='${errors["email"]}'/></td>
</tr>

<tr>
<td>Enter Password:*</td>
<td><input type="password" name="password"></td>
<td><c:out value='${errors["password"]}'/></td>
</tr>

<tr>
<td><input type="submit" value="Login"></td>
<td><input type="reset" value="Clear"></td>
</tr>

</form>
</table>
<br><br>

<form action="Choice.jsp" method="POST">
<div align = "center">New User ?</div>
<div align = "center"><input type="submit" value="Register"></div>
</form>

</body>
</html>