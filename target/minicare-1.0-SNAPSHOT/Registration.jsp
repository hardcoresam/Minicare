<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Registration Page</h1>
<hr><br><br>

//I dont think the values will be prefilled here. check the code once bcoz for the second time we will not be anything from the RegistrationServlet.
//So check this and make the values prefilled.

<form action="Registration.do" method="POST">

<table border="0" align="center">
<tr>
<td>Enter First Name:*</td>
<td><input type="text" name="firstName" value="${param.firstName}"></td>
<td><c:out value='${errors["firstName"]}'/></td>
</tr>

<tr>
<td>Enter Last Name:</td>
<td><input type="text" name="lastName" value="${param.lastName}"></td>
<td><c:out value='${errors["lastName"]}'/></td>
</tr>

<tr>
<td>Enter Email:*</td>
<td><input type="email" name="email" value="${param.email}"></td>
<td><c:out value='${errors["email"]}'/></td>
</tr>

<tr>
<td>Enter Phone Number:*</td>
<td><input type="text" name="phoneNumber" value="${param.phoneNumber}"></td>
<td><c:out value='${errors["phoneNumber"]}'/></td>
</tr>

<tr>
<td>Enter Address:*</td>
<td><textarea name="address" rows="4" cols="40"> <c:out value="${param.address}"/> </textarea></td>
<td><c:out value='${errors["address"]}'/></td>
</tr>

<c:choose>
    <c:when test='${param.type=="seeker"}'>
        <tr>
        <td>Enter No of Children:</td>
        <td><input type="text" name="noOfChildren" value="${param.noOfChildren}"></td>
        <td><c:out value='${errors["noOfChildren"]}'/></td>
        </tr>

        <tr>
        <td>Enter Spouse Name:</td>
        <td><input type="text" name="spouseName" value="${param.spouseName}"></td>
        <td><c:out value='${errors["spouseName"]}'/></td>
        </tr>
    </c:when>

    <c:when test='${param.type=="sitter"}'>
        <tr>
        <td>Enter Experience:*</td>
        <td><input type="text" name="experience" placeholder="In Months" value="${param.experience}"></td>
        <td><c:out value='${errors["experience"]}'/></td>
        </tr>

        <tr>
        <td>Enter Expected Pay:*</td>
        <td><input type="text" name="expectedPay" value="${param.expectedPay}"></td>
        <td><c:out value='${errors["expectedPay"]}'/></td>
        </tr>
    </c:when>
</c:choose>

<tr>
<td>Enter Password:*</td>
<td><input type="password" name="password" value="${param.password}"></td>
<td><c:out value='${errors["password"]}'/></td>
</tr>

<tr>
<td><input type="submit" value="Register"></td>
<td><input type="reset" value="Clear"></td>
</tr>

</table>

<input type ="hidden" name="type" value="${param.type}">

</form>
</body>
</html>
