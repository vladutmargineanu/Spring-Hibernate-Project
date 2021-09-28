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


