<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring REST Demo</title>
</head>
<body>

	<h3>Spring REST Demo</h3>
	<hr>

	<a href="${ pageContext.request.contextPath }/test/hello">Hello</a>
	<br>
	<br>
	<a href="${ pageContext.request.contextPath }/api/students">Get All
		Students</a>

</body>
</html>