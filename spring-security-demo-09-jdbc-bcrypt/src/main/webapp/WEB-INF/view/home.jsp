<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<html>

<head>
<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page - demo user-roles</h2>
	<hr>

	<p>Welcome to the luv2code company home page!</p>

	<hr>

	<!-- Display user name and role -->

	<p>
		User:
		<security:authentication property="principal.username" />

		<br> <br> Role(s):
		<security:authentication property="principal.authorities" />
	</p>

	<!-- Add a link to point to /leaders  -> this is for managers -->

	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${ pageContext.request.contextPath }/leaders">LeaderShip
				Meeting</a> (Only for Manager people)
		</p>
	</security:authorize>


	<!-- Add a link to point to /systems  -> this is for admins -->

	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${ pageContext.request.contextPath }/systems">IT Systems
				Meeting </a> (Only for Admin people)

		</p>
	</security:authorize>

	<hr>

	<!-- - Add a logout button -->
	<form:form action="${pageContext.request.contextPath }/logout"
		method="POST">

		<input type="submit" value="Logout" />

	</form:form>
</body>

</html>