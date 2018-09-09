<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Welcome</h1>
<hr><br><br>
<h3 align="center">Login Here</h3>
<br>

<form action="${pageContext.request.contextPath}/visitor/login.do" method="POST">    SO it is working with action="login.do" and in url pattern /login.do
                                                   it is also working with action="visitor-login.do" and in url pattern /visitor-login.do
                                                   Now find out what happens if we write action="visitor/login.do" and in url
                                                   pattern /visitor/login.do
                                                   so find out about the slash.

<table border="0" align="center">

<tr>
<td><font color="red"><c:out value='${loginError1}'/></font></td>   //Check this. This is there in LoginFilter.
</tr>

<tr>
<td><font color="red"><c:out value='${loginError}'/></font></td>
</tr>

<tr>
<td>Enter Email:*</td>
<td><input type="text" name="email" value="${param.email}"></td>
<td><font color="red"><c:out value='${errors["email"]}'/></font></td>
</tr>

<tr>
<td>Enter Password:*</td>
<td><input type="password" name="password"></td>
<td><font color="red"><c:out value='${errors["password"]}'/></font></td>
</tr>

<tr>
<td><input type="submit" value="Login"></td>
<td><input type="reset" value="Clear"></td>
</tr>

</form>
</table>
<br><br>

<form action="${pageContext.request.contextPath}/jsp/visitor/Choice.jsp" method="POST">
<div align = "center">New User ?</div>
<div align = "center"><input type="submit" value="Register"></div>
</form>

</body>
</html>