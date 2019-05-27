<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<style>
.redtext {
        color: red;
}
</style>
</head>
<body>
<p></p>
<h1 class="redtext"> Name is Required</h1>
<s:a href="index.jsp">Back to login Page</s:a>
</body>
</html>