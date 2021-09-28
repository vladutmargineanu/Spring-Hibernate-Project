# Special Note about Deprecated Method in Hibernate 5.2

If you are using Hibernate 5.2 or higher, then the Query list() method has been deprecated.

In your code you should make the following update:

Replace
```java
session.createQuery("from Student").list()
```
With
```java
session.createQuery("from Student").getResultList()
```
---


# FAQ: How To View Hibernate SQL Parameter Values
# FAQ: How To View Hibernate SQL Parameter Values

## Question: I see hibernate printing out the query parameters as ? in the console. Is it possible to printout the value that was actually queried on the
database. Asking as this would help in the debugging purpose.

### Answer: When using Hibernate, if you log the Hibernate SQL statements, you will see this:

Hibernate: insert into student (email, first_name, last_name, id) values (?, ?, ?, ?)

However, for debugging your application, you want to see the actual parameter values in the Hibernate logs. Basically, you want to get rid of the question marks in the Hibernate logs.

You can view the actual parameters by viewing the low-level trace of the Hibernate logs. This is not set up by default. However, we can add log4j to allow us to see these low-level logs.



Here is an overview of the process:
1. Add log4j to your project classpath 

2. Add log4j.properties to your “src” directory



Here are the detailed steps:
1. Add log4j to your project classpath

1a. Download log4j v1.2.17 from this link: – http://central.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar

1b. Copy this file to your project’s lib directory

![image](https://user-images.githubusercontent.com/48476504/135163632-9609a1c8-d611-42b0-a68a-9417a54a39b6.png)



1c. Right-click your Eclipse project and select Properties

1d. Select Build Path > Libraries > Add JARS…

1e. Select the log4j-1.2.17.jar file from the lib directory

![image](https://user-images.githubusercontent.com/48476504/135163674-612b68ae-f990-4096-92d5-5f7711eaacf6.png)



2. Add log4j.properties to your “src” directory

2a. Copy the text from below
```java
# Root logger option
log4j.rootLogger=DEBUG, stdout
 
# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
 
log4j.logger.org.hibernate=TRACE
```
2b. Save this file as "log4j.properties" in your “src” directory

![image](https://user-images.githubusercontent.com/48476504/135163759-8be8d68d-c8c8-4287-9999-f69f9a19a786.png)




Note: This file has an important setting:
```java
log4j.logger.org.hibernate=TRACE 
```
This allows you see a low-level trace of Hibernate and this allows you see the real SQL parameter values.

Now run your application. You will see a lot of low-level TRACE logs in the Eclipse Console window.

Right-click in the Eclipse Console window and select Find/Replace…

Search for: binding parameter

or search for: extracted value

(the search string changes depending on which version of Hibernate you are using)

You will see the logs with the real parameter values. Congrats!

![image](https://user-images.githubusercontent.com/48476504/135163793-e2590562-0593-407d-aa3c-744107612616.png)

---
