# bcrypt Additional Resources


---

Why you should use bcrypt to hash passwords
```
www.luv2code.com/why-bcrypt
```

---

Detailed bcrypt algorithm analysis
```
www.luv2code.com/bcrypt-wiki-page
```
---

Password hashing - Best Practices
```
www.luv2code.com/password-hashing-best-practices
```

---
# Spring Security - Adding a Public Landing Page

I want my application to have a landing page that is accessible to everyone at first, the user can then login to the application.
 
 
## Answer

You can add a public view page and set up the security constraints to allow access to the view page. In this example, we have a view page that anyone can access. then they can click the link to access the secure pages.

![image](https://user-images.githubusercontent.com/48476504/139749508-d3d2c409-d02a-46ab-8308-d9e1c18819da.png)

This project has the following modifications.

1. Updated security configs to allow public access to landing page

2. Updated controller to send requests to landing page

3. New landing page



Details below

---

1. Updated security configs to allow public access to landing page

See the config below. It will "permit all" access to the landing page "/".

```java
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
		http.authorizeRequests()
			.antMatchers("/").permitAll(). // allow public access to landing page
			.antMatchers("/employees").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/")  // after logout redirect to landing page (root)
				.permitAll();
		
	}
```

2. Updated controller to send requests to landing page

In the controller file, added new "/" mapping to send to landing page. And changed the original home mapping to "/employees". see changes in bold.

File: DemoController.java

```java
@Controller
public class DemoController {
 
	@GetMapping("/")
	public String showLanding() {
		
		return "landing";
	}
...
}
```

3. New landing page

Created a new view page for landing information. Anyone can access this page

File: src/main/webapp/WEB-INF/view/landing.jsp

```html

<html>
 
<head>
	<title>luv2code Landing Page</title>
</head>
 
<body>
	<h2>luv2code Landing Page</h2>
	<hr>
	
	<p>
	Welcome to the landing page!  This page is open to the public ... no login required :-)
	</p>
	
	<hr>
	
	<p>
		<a href="${pageContext.request.contextPath}/employees">Access Secure Site (requires login)</a>
	</p>
		
</body>
 
</html>
```



Now when you run the application, we have a view page that anyone can access. Then they can click the link to access the secure pages.

