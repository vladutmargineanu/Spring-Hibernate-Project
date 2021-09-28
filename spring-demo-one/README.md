# FAQ: What is a Spring Bean?
## FAQ: What is a Spring Bean?

### A "Spring Bean" is simply a Java object.

When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

Spring Beans are created from normal Java classes .... just like Java objects.

---

Here's a blurb from the Spring Reference Manual


Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-introduction

---

In the early days, there was a term called "Java Beans". Spring Beans have a similar concept but Spring Beans do not follow all of the rigorous requirements of Java Beans.

---

In summary, whenever you see "Spring Bean", just think Java object. :-)

---

# FAQ: Why do we specify the Coach interface in getBean()?

## For example:
```java
Coach theCoach = context.getBean("myCoach", Coach.class); 
```

---

### Answer

When we pass the interface to the method, behind the scenes Spring will cast the object for you.
```java
context.getBean("myCoach", Coach.class) 
```

However, there are some slight differences than normal casting.

From the Spring docs:

Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).

Source:  http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html#getBean-java.lang.String-java.lang.Class-
---


# FAQ: What is the purpose for the no arg constructor?
# FAQ: What is the purpose for the no arg constructor?


## Question:
I was wondering why you created a no arg constructor? I thought that they are implied by Java and only required when you also have an overloaded constructor. Or is this a Spring specific thing?

---

## Answer:

When you don’t define any constructor in your class, compiler defines default one for you, however when you declare any constructor (in your example you have already defined a parameterized constructor), compiler doesn’t do it for you.

Since you have defined a constructor in class code, compiler didn’t create default one. While creating object you are invoking default one, which doesn’t exist in class code. Then the code gives an compilation error.
---


# FAQ: Why do we use CricketCoach class instead of Coach Interface?
# Question:

For the CricketCoach example with Setter Injection, why do we use the CricketCoach class instead of the Coach interface?

## Answer:

The getTeam() method is only defined in the CricketCoach class. It is not part of the Coach interface.

As a result, you would need the following code:
```java
    CricketCoach theCricketCoach = context.getBean("myCricketCoach", CricketCoach.class); 
```

---

The Coach interface has two methods: getDailyWorkout and getDailyFortune

The CricketCoach class has four methods: getDailyWorkout, getDailyFortune, getTeam and setTeam

---

When you retrieve a bean from the Spring container using the Coach interface:
```java
    Coach theCricketCoach = context.getBean("myCricketCoach", Coach.class); 
```

You only have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune. Even though the actual implementation has additional methods, you only have visibility to methods that are defined at the Coach interface level.

---

When you retrieve a bean from the Spring container using the CricketCoach class:
```java
    CricketCoach theCricketCoach = context.getBean("myCricketCoach", CricketCoach.class); 
```
You have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune.

ALSO, you have access to the additional methods defined in the CricketCoach class: getTeam, setTeam.

---

### The bottom line is it depends on how you retrieve the object and assign it ... that determines the visibility you have to the methods.
---

