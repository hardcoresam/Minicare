<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1 align="center">Edit Your Profile Here</h1>
<hr><br><br>

<form action="${pageContext.request.contextPath}/member/alterProfile.do" method="POST">

<table border="0" align="center">
<tr>
<td>Enter First Name:*</td>
<td><input type="text" name="firstName" value="${requestScope.member.firstName}"></td>
<td><font color="red"><c:out value='${errors["firstName"]}'/></font></td>
</tr>

<tr>
<td>Enter Last Name:</td>
<td><input type="text" name="lastName" value="${requestScope.member.lastName}"></td>
<td><font color="red"><c:out value='${errors["lastName"]}'/></font></td>
</tr>

<tr>
<td>Enter Phone Number:*</td>
<td><input type="text" name="phoneNumber" value="${requestScope.member.phoneNumber}"></td>
<td><font color="red"><c:out value='${errors["phoneNumber"]}'/></font></td>
</tr>

<tr>
<td>Enter Address:*</td>
<td><textarea name="address" rows="4" cols="40"><c:out value="${requestScope.member.address}"/></textarea></td>
<td><font color="red"><c:out value='${errors["address"]}'/></font></td>
</tr>

<c:choose>
    <c:when test='${requestScope.member.type == "SEEKER"}'>
        <tr>
        <td>Enter No of Children:</td>
        <td><input type="text" name="noOfChildren" value="${requestScope.member.noOfChildren}"></td>
        <td><font color="red"><c:out value='${errors["noOfChildren"]}'/></font></td>
        </tr>

        <tr>
        <td>Enter Spouse Name:</td>
        <td><input type="text" name="spouseName" value="${requestScope.member.spouseName}"></td>
        <td><font color="red"><c:out value='${errors["spouseName"]}'/></font></td>
        </tr>
    </c:when>

    <c:when test='${requestScope.member.type == "SITTER"}'>
        <tr>
        <td>Enter Experience:*</td>
        <td><input type="text" name="experience" placeholder="In Months" value="${requestScope.member.experience}"></td>
        <td><font color="red"><c:out value='${errors["experience"]}'/></font></td>
        </tr>

        <tr>
        <td>Enter Expected Pay:*</td>
        <td><input type="text" name="expectedPay" value="${requestScope.member.expectedPay}"></td>
        <td><font color="red"><c:out value='${errors["expectedPay"]}'/></font></td>
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