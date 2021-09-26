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
        
# FAQ: How to populate radiobuttons with items from Java class?
# FAQ: How to populate radiobuttons with items from Java class like we did with selectlist?

## You can follow a similar approach that we used for the drop-down list.

### Here are the steps

1. Set up the data in your Student class

Add a new field
```java
    private LinkedHashMap<String, String> favoriteLanguageOptions;
In your constructor, populate the data

        // populate favorite language options
        favoriteLanguageOptions = new LinkedHashMap<>();
        // parameter order: value, display label
        //
        favoriteLanguageOptions.put("Java", "Java");
        favoriteLanguageOptions.put("C#", "C#");
        favoriteLanguageOptions.put("PHP", "PHP");
        favoriteLanguageOptions.put("Ruby", "Ruby");    
  ```  

Add getter method
```java
    public LinkedHashMap<String, String> getFavoriteLanguageOptions() {
        return favoriteLanguageOptions;
    }
```
2. Reference the data in your form
```xml
        Favorite Language:
        
        <form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
```
--- 
    
# FAQ: How to make Integer field required and handle Strings: freePasses
# FAQ: How to Make Integer field required and handle Strings: freePasses?

## Question:

I am getting the following error when i submit the form with an empty value for customer "freePasses". I am  using @NotNull on the field "freePasses". It is not throwing "is required" after validation after submit.

How to fix this?

Also, how do I handle validation if the user enters String input for the integer field?

-----

### Answer:

The root cause is the freePasses field is using a primitive type: int. In order to check for null we must use the appropriate wrapper class: Integer.

To resolve this, In Customer.java, replace "int" with "Integer"
```java
@NotNull(message="is required")     
@Min(value=0, message="must be greater than or equal to zero")     
@Max(value=10, message="must be less than or equal to 10")     
private Integer freePasses;

Then update your getter/setter methods to use "Integer"

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }
```
Save everything and retest.



=====

Here is the full source code.
```java
package com.luv2code.springdemo.mvc;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
 
public class Customer {
 
    private String firstName;
    
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName;
 
    @NotNull(message="is required")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private Integer freePasses;
    
    @Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digits")
    private String postalCode;
        
 
    public String getPostalCode() {
        return postalCode;
    }
 
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public Integer getFreePasses() {
        return freePasses;
    }
 
    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }    
    
}
```
====

## Handle String Input for Integer Fields

If the user enters String input such as "abcde" for the Free Passes integer field, we'd like to give a descriptive error message.

        ![image](https://user-images.githubusercontent.com/48476504/134826530-27783ad1-bc40-4d67-b84b-5ac697639b56.png)


We basically need to override the default Spring MVC validation messages.

Follow these steps.

1. In your Eclipse project, expand the node: Java Resources

2. Right-click the src directory and create a new sub-directory: resources

3. Right-click the resources sub-directory and create a new file named: messages.properties

Your directory structure should look like this:

        ![image](https://user-images.githubusercontent.com/48476504/134826532-e26f99b2-2a57-44d9-b020-99e4c95c3080.png)


4. Add the following entry to the messages.properties file

typeMismatch.customer.freePasses=Invalid number

5. Save file

---

This file contains key/value pairs for the error message type

For a basic example:
```xml
  typeMismatch.customer.freePasses=Invalid number
```
### The format of the error key is:   code + "." + object name + "." + field

To find out the given error code, in your Spring controller, you can log the details of the binding result
```java
 System.out.println("Binding result: " + theBindingResult);   
```

For details, see the docs here 

- http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/validation/DefaultMessageCodesResolver.html

---

6. Edit your config file: WEB-INF/spring-mvc-demo-servlet.xml

Add the following:
```xml
<bean id="messageSource" 
      class="org.springframework.context.support.ResourceBundleMessageSource">
 
    <property name="basenames" value="resources/messages" />
 
</bean>
```
7. Save the file. Restart the Tomcat server

8. Run your app and add bad data for the "Free Passes" field. You will see the error message from our properties file.

        ![image](https://user-images.githubusercontent.com/48476504/134826537-374ac1cf-5471-48de-8c31-ab88b869b420.png)

--- 
       
# FAQ: Spring MVC Custom Validation - Possible to validate with multiple strings?
## Spring MVC Custom Validation - FAQ: Is it possible to integrate multiple validation string in one annotation?


### Question:

Is it possible to integrate multiple validation string in one annotation? For example, validate against both LUV and TOPS.

### Answer:

Yes, you can do this. In your validation, you will make use of an array of strings.

Here's an overview of the steps.

1. Update CourseCode.java to use an array of strings

2. Update CourseCodeConstraintValidator.java to validate against array of strings

3. Update Customer.java to validate using array of strings

---

Detailed Steps

1. Update CourseCode.java to use an array of strings

Change the value entry to an array of Strings:
```java
    // define default course code
    public String[] value() default {"LUV"};
```
Note the use of square brackets for the array of Strings. Also, the initialized value uses curley-braces for array data.

2. Update CourseCodeConstraintValidator.java to validate against array of strings

Change the field for coursePrefixes to an array
```java
private String[] coursePrefixes; 
```

Update the isValid(...) method to loop through the course prefixes. In the loop, check to see if the code matches any of the course prefixes.
```java
    @Override
    public boolean isValid(String theCode, 
                        ConstraintValidatorContext theConstraintValidatorContext) {
        boolean result = false;
        
        if (theCode != null) {
            
            //
            // loop thru course prefixes
            //
            // check to see if code matches any of the course prefixes
            //
            for (String tempPrefix : coursePrefixes) {
                result = theCode.startsWith(tempPrefix);
                
                // if we found a match then break out of the loop
                if (result) {
                    break;
                }
            }
        }
        else {
            result = true;
        }
        
        return result;
  }
```
3. Update Customer.java to validate using array of strings
```java
    @CourseCode(value={"TOPS", "LUV"}, message="must start with TOPS or LUV")
    private String courseCode;
```
Note the use of curley braces.
        
---
        
