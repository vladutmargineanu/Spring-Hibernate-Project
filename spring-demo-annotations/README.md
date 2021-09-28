# Special Note about Destroy Lifecycle and Prototype Scope

---

Here is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the @PreDestroy method.  Gasp!  

I didn't know this either until I dug through the Spring reference manual researching a student's question.

Here is the answer from the Spring reference manual. Section 1.5.2

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype

---         

In contrast to the other scopes, Spring does not manage the complete lifecycle of a
prototype bean: the container instantiates, configures, and otherwise assembles a
prototype object, and hands it to the client, with no further record of that prototype
instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 

To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.

---

This also applies to XML configuration.


---

## QUESTION: How can I create code to call the destroy method on prototype scope beans
## ANSWER:

You can destroy prototype beans but custom coding is required. This examples shows how to destroy prototype scoped beans.

This examples shows how to destroy prototype scoped beans. A couple of changes are required

### 1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans.

### 2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destroy()" method. This method should be used instead of the @PostDestroy annotation.

### 3. In this app, AnnotationDemoApp.java is the main program. FootballCoach.java is the prototype scoped bean. FootballCoach implements the DisposableBean interface and provides the destroy() method. The custom bean processing is handled in the MyCustomBeanProcessor class.

---

# FAQ: How to inject properties file using Java annotations
# FAQ: How to inject properties file using Java annotations


## Answer:

This solution will show you how inject values from a properties file using annotatons. The values will no longer be hard coded in the Java code.

1. Create a properties file to hold your properties. It will be a name value pair.  

New text file:  src/sport.properties
```
foo.email=myeasycoach@luv2code.com
foo.team=Silly Java Coders
```
Note the location of the properties file is very important. It must be stored in src/sport.properties

2. Load the properties file in the XML config file.

File: applicationContext.xml

Add the following lines:
```
    <context:property-placeholder location="classpath:sport.properties"/>  
```
This should appear just after the <context:component-scan .../> line

3. Inject the properties values into your Swim Coach: SwimCoach.java
```java
@Value("${foo.email}")
private String email;
    
@Value("${foo.team}")
private String team;
```
---


