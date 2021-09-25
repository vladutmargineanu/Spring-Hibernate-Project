<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration Form</title>
</head>
<body>

	<form:form action="processForm" modelAttribute="student">
	
		First name: <form:input path="firstName" />

		<br>
		<br>
	
		Last name: <form:input path="lastName" />

		<br>
		<br>

		<form:select path="country">

			<form:option value="Romania" />
			<form:option value="France" />
			<form:option value="Germany" />
			<form:option value="Italy" />
			<form:option value="Spain" />

		</form:select>

		<br>
		<br>
		
		<form:select path="country">

			<form:options items="${student.countryOptions}" />

		</form:select>

		<br>
		<br>

		<input type="submit" value="Submit" />
	</form:form>

</body>
</html>