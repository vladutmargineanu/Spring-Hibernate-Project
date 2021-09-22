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
