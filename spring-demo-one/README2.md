# Question

## Why do we specify the Coach interface in getBean()?

### For example:
```java
Coach theCoach = context.getBean("myCoach", Coach.class); 
```

---

Answer

When we pass the interface to the method, behind the scenes Spring will cast the object for you.
```java
context.getBean("myCoach", Coach.class) 
```

However, there are some slight differences than normal casting.

From the Spring docs:

Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).

Source:  http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html#getBean-java.lang.String-java.lang.Class-
