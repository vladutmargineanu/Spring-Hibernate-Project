# FAQ: How does "processForm" work for "/hello"?

## Question: Can you please clarify how /hello is getting appended to the jsp file action for "processForm"?

## Answer
---
You can use "processForm" because it is a relative path to the controller "/hello" request mapping. Here is how it works.

1. When you wish to view the form, the HTML link points to "hello/showForm". This calls the controller and it displays the form.

2. At this point the browser URL/path is: http://localhost:8080/spring-mvc-demo/hello

3. The HTML form uses "processForm" for the form action. Notice that it does not have a forward slash, as a result, this will be relative to the current browser URL. Since the current browser URL is 

http://localhost:8080/spring-mvc-demo/hello

Then the actual form URL submission will send it to

http://localhost:8080/spring-mvc-demo/hello/processForm

The part in bold with map to the controller with top-level request mapping "/hello" and then map to request mapping in that class "/processForm"

The key here is relative path of showing the form and then submitting to relative path.

---

# FAQ: Use properties file to load country options
# Question: 

## How to use properties file to load country options

### Answer:

### This solution will show you how to place the country options in a properties file. The values will no longer be hard coded in the Java code.

1. Create a properties file to hold the countries. It will be a name value pair.  Country code is name. Country name is the value.

New text file:  WEB-INF/countries.properties
```
BR=Brazil 
FR=France 
CO=Colombia 
IN=India
```
Note the location of the properties file is very important. It must be stored in WEB-INF/countries.properties

2. Update header section for Spring config file

We are going to use a new set of Spring tags for <util>. As a result, you need to update the header information in the Spring config file.

File: spring-mvc-dmo-servlet.xml

Remove the previous header and add this.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
        xmlns:context="http://www.springframework.org/schema/context" 
        xmlns:mvc="http://www.springframework.org/schema/mvc" 
        xmlns:util="http://www.springframework.org/schema/util" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="
            http://www.springframework.org/schema/beans     
            http://www.springframework.org/schema/beans/spring-beans.xsd     
            http://www.springframework.org/schema/context     
            http://www.springframework.org/schema/context/spring-context.xsd     
            http://www.springframework.org/schema/mvc         
            http://www.springframework.org/schema/mvc/spring-mvc.xsd 
            http://www.springframework.org/schema/util     
            http://www.springframework.org/schema/util/spring-util.xsd">
  ```
3. Load the country options properties file in the Spring config file. Bean id: countryOptions

File: spring-mvc-demo-servlet.xml

Add the following lines:
```xml
<util:properties  id="countryOptions" location="classpath:../countries.properties" /> 
```

4.1 In StudentController.java, add the following import statement:
  
```java
import java.util.Map;
```
4.2 Inject the properties values into your Spring Controller: StudentController.java
```java
@Value("#{countryOptions}") 
private Map<String, String> countryOptions;
```
5. Add the country options to the Spring MVC model. Attribute name: theCountryOptions

```java
@RequestMapping("/showForm") 
public String showForm(Model theModel) { 
 
    // create a student object Student 
    Student theStudent = new Student();
 
    // add student object to the model 
    theModel.addAttribute("student", theStudent); 
 
    // add the country options to the model 
    theModel.addAttribute("theCountryOptions", countryOptions); 
 
    return "student-form"; 
}
```
6. Update the JSP page, student-form.jsp, to use the new model attribute for the drop-down list: theCountryOptions
```xml
<form:select path="country"> 
 <form:options items="${theCountryOptions}" />
</form:select>
```
  
7. Remove all references to country option from your Student.java.  

---
