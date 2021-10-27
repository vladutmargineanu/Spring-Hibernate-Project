# FAQ: How To Add Local CSS file for Spring Security Login Form?
## Question:

### What would be required to link a CSS file for Spring Security login form? For example, I'd like to have a CSS file (from src/main/webapp/css/style.css) for the style of the error message when using Spring Security login form. I suspect that it block the request to the CSS if i am not authenticated.



### Answer:

You are correct, by default Spring Security will block all requests to the web app if the user is not authenticated. However, if you'd like to use a local CSS file on your login form then these are the basic steps:

1. Create a separate CSS file

2. Reference the CSS file in your login page

3. Configure Spring Security to allow unauthenticated requests (permit all) to the "/css" directory

4. Configure the all Java configuration to serve content from the "/css" directory



Full Source code available here: spring-security-custom-login-form-with-css.zip



Here are the details of each step.



1. Create a separate CSS file

You can use any type of CSS styling. Just to demonstrate the point, the heading for the login page will be blue. The error messages will be red.

File: src/main/webapp/css/demo.css
```css
.failed {
 color: red;
}
 
h3 {
 color: blue;
}
```

2. Reference the CSS file in your login page

In the head section of the login page, reference the CSS file. Here's the snippet
```xml
<head>
 <title>Custom Login Page</title>
 <link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
```

and here's the complete code for this file.

File: src/main/webapp/WEB-INF/view/plain-login.jsp
```xml
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 
<html>
 
<head>
    <title>Custom Login Page</title>
    
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    
</head>
 
<body>
 
<h3>My Custom Login Page</h3>
 
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser"
               method="POST">
    
        <!-- Check for login error -->
    
        <c:if test="${param.error != null}">
        
            <i class="failed">Sorry! You entered invalid username/password.</i>
            
        </c:if>
            
        <p>
            User name: <input type="text" name="username" />
        </p>
 
        <p>
            Password: <input type="password" name="password" />
        </p>
        
        <input type="submit" value="Login" />
        
    </form:form>
 
</body>
 
</html>
```

3. Configure Spring Security to allow unauthenticated requests (permit all)to the "/css" directory

Make note of this snippet. It permits all requests to CSS. No need for authentication. Here's the snippet
```java
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll();        
```

and here's the complete code for this file.

File: DemoSecurityConfig.java
```java
package com.luv2code.springsecurity.demo.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
 
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 
        // add our users for in memory authentication
        
        UserBuilder users = User.withDefaultPasswordEncoder();
        
        auth.inMemoryAuthentication()
            .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
            .withUser(users.username("mary").password("test123").roles("MANAGER"))
            .withUser(users.username("susan").password("test123").roles("ADMIN"));
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll();        
    }
        
}
```

4. Configure the all Java configuration to serve content from the "/css" directory

Update your DemoAppConfig to implement the WebMvcConfigurer interface. This allows you to override a method for serving up content from the "/css" directory. Here's the snippet
```java
 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
     registry.addResourceHandler("/css/**").addResourceLocations("/css/");
 }
```

and here's the complete code for this file.
```java
package com.luv2code.springsecurity.demo.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
public class DemoAppConfig implements WebMvcConfigurer {
 
    // define a bean for ViewResolver
 
    @Bean
    public ViewResolver viewResolver() {
 
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }
}
```

Once you apply these changes then you'll see use of the CSS on the login form. When login form is first loaded you'll see the blue heading based on CSS style. If there is a failed login, you'll see the red error message based on CSS style.

---
