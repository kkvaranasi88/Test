<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="signUpForm">
		 <tr>
            <td width="45%" class="align-right"><s:textfield name="firstName" label="First Name" value="" /></td>
          </tr>
          <tr>
            <td class="align-right"><s:textarea name="lastName" label="Last Name" value=""  /></td>
          </tr>
            <tr>
            <td width="45%" class="align-right"><s:textfield name="name" label=" Name" value="" /></td>
          </tr>
          <tr>
            <td class="align-right"><s:textarea name="address" label="Address" value=""  /></td>
          </tr>
	</table>
</body>
</html>