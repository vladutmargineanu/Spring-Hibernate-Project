# FAQ: How to use CSS, JavaScript and Images in Spring MVC Web App
# Question

## How do I use CSS, JavaScript and Images in a Spring MVC Web App?

## Answer

Here are the steps on how to access static resources in a Spring MVC. For example, you can use this to access images, css, JavaScript files etc.

Any static resource is processed as a URL Mapping in Spring MVC. You can configure references to static resources in the spring-mvc-demo-servlet.xml.

In my example, I'm going to have the following directory structure:

![image](https://user-images.githubusercontent.com/48476504/134424773-80d15310-9b33-4fb6-b726-58761e8f117b.png)



I chose to put everything in the "resources" directory. But you can use any name for "resources", such as "assets", "foobar" etc. Also, you can give any name that you want for the subdirectories under "resources".

---

Step 1: Add the following entry to your Spring MVC configuration file: spring-mvc-demo-servlet.xml

You can place this entry anywhere in your Spring MVC config file.
```java
<mvc:resources mapping="/resources/**" location="/resources/"></mvc:resources> 
```

Step 2: Now in your view pages, you can access the static files using this syntax:
```java
<img src="${pageContext.request.contextPath}/resources/images/spring-logo.png"> 
```

You need to use the JSP expression ${pageContext.request.contextPath} to access the correct root directory for your web application.

Apply the same technique for reading CSS and JavaScript.

---

Here's a full example that reads CSS, JavaScript and images.

```html
<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/my-test.css">

<script
	src="${pageContext.request.contextPath}/resources/js/simple-test.js"></script>

</head>

<body>

	<h2>Spring MVC Demo - Home Page</h2>

	<a href="showForm">Plain Hello World</a>

	<br>
	<br>

	<img
		src="${pageContext.request.contextPath}/resources/images/spring-logo.png" />

	<br>
	<br>

	<input type="button" onclick="doSomeWork()" value="Click Me" />

</body>

</html>
```
---
---

# Bonus: Deploying To Tomcat using WAR files

## Bonus: Deploying your App to Tomcat as a Web Application Archive (WAR) file

### When you deploy your Java web apps, you can make use of a Web Application Archive (WAR) file.
---
The Web Application Archive (WAR) file is a compressed version of your web application. It uses the zip file format but the file has the .war extension.

If you are using Eclipse, then the best way to visualize it is think of your "WebContent" directory being compressed as a zip file with the .war extension.

This includes all of your web pages, images, css etc. It also includes the WEB-INF directory which includes your classes in WEB-INF/classes and supporting JAR files in WEB-INF/lib.

The WAR file format is part of the Java EE / Servlet specification. As a result, all Java EE servers support this format (ie jboss, weblogic, websphere, glassfish and tomcat).

Below, I provide steps on how to create a WAR file in Eclipse. I also show how to deploy the WAR file on Tomcat.

---

1. In Eclipse, stop Tomcat

2. Right-click your project and select Export > WAR File

3. In the Destination field, enter: <any-directory>/mycoolapp.war

4. Outside of Eclipse, start Tomcat
- If you are using MS Windows, then you should find it on the Start menu

5. Make sure Tomcat is up and running by visiting: http://localhost:8080

6. Deploy your new WAR file by copying it to <tomcat-install-directory>\webapps
- Give it about 10-15 seconds to make the deployment. You'll know the deployment is over because you'll see a new folder created in webapps ... with your WAR file name.

7. Visit your new app. If your war file was: mycoolapp.war then you can access it with:  http://localhost:8080/mycoolapp/
---
