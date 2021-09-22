# FAQ: How to configure the Spring Dispatcher Servlet using all Java Code (no xml)
# Question:

## How to configure the Spring Dispatcher Servlet using all Java Code (no xml)?

## Answer:

### For Spring MVC, we cover all Java config (no xml) later in the course, complete with videos/explanation and everything.

However, if you just need the code, here are the steps

1. Delete the files: web.xml file and spring-mvc-demo-servlet.xml files

2. Create a new Java package: com.luv2code.springdemo.config

3. Add the following Java files in your package

File: DemoAppConfig.java
```java
package com.luv2code.springdemo.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springdemo")
public class DemoAppConfig {
 
	// define a bean for ViewResolver
 
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
}
```

File: MySpringMvcDispatcherServletInitializer.java
```java
package com.luv2code.springdemo.config;
 
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DemoAppConfig.class };
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
 
}
```
4. Test your app

Your app should work as desired.

---
