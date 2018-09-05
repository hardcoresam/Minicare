<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Edit Your Profile Here</h1>
<hr><br><br>

//Check whether this is working or not???????????

<form action="AlterProfile.do" method="POST">

<table border="0" align="center">
<tr>
<td>Enter First Name:*</td>
<td><input type="text" name="firstName" value="${requestScope.member.firstName}"></td>
<td><c:out value='${errors["firstName"]}'/></td>
</tr>

<tr>
<td>Enter Last Name:</td>
<td><input type="text" name="lastName" value="${requestScope.member.lastName}"></td>
<td><c:out value='${errors["lastName"]}'/></td>
</tr>

<tr>
<td>Enter Phone Number:*</td>
<td><input type="text" name="phoneNumber" value="${requestScope.member.phoneNumber}"></td>
<td><c:out value='${errors["phoneNumber"]}'/></td>
</tr>

<tr>
<td>Enter Address:*</td>
<td><textarea name="address" rows="4" cols="40"> <c:out value="${requestScope.member.address}"/> </textarea></td>
<td><c:out value='${errors["address"]}'/></td>
</tr>

<c:choose>
    <c:when test='${requestScope.member.type == "SEEKER"}'>
        <tr>
        <td>Enter No of Children:</td>
        <td><input type="text" name="noOfChildren" value="${requestScope.member.noOfChildren}"></td>
        <td><c:out value='${errors["noOfChildren"]}'/></td>
        </tr>

        <tr>
        <td>Enter Spouse Name:</td>
        <td><input type="text" name="spouseName" value="${requestScope.member.spouseName}"></td>
        <td><c:out value='${errors["spouseName"]}'/></td>
        </tr>
    </c:when>

    <c:when test='${requestScope.member.type == "SITTER"}'>
        <tr>
        <td>Enter Experience:*</td>
        <td><input type="text" name="experience" placeholder="In Months" value="${requestScope.member.experience}"></td>
        <td><c:out value='${errors["experience"]}'/></td>
        </tr>

        <tr>
        <td>Enter Expected Pay:*</td>
        <td><input type="text" name="expectedPay" value="${requestScope.member.expectedPay}"></td>
        <td><c:out value='${errors["expectedPay"]}'/></td>
        </tr>
    </c:when>
</c:choose>

<tr>
<td><input type="submit" value="Edit User"></td>
<td><input type="reset" value="Clear"></td>
</tr>

</table>

<input type ="hidden" name="type" value="${requestScope.member.type}">

</form>
</body>
</html>
