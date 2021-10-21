### HEADS UP - @After Advice running after @AfterThrowing advice
## You may have noticed that in the latest versions of Spring, the @After Advice is running AFTER the @AfterThrowing advice. This output is different than what I showed in the original video.

There were recent changes in the Spring 5.2.7 (released on 9 June 2020).

Starting with Spring 5.2.7:

- if advice methods defined in the same @Aspect class that need to run at the same join point

- the @After advice method is invoked AFTER any @AfterReturning or @AfterThrowing advice methods in the same aspect class

---

So in our case, the @After and @AfterThrowing are in the same aspect class: MyDemoLoggingAspect.java, hence in latest Spring 5.2.7, the @After will run AFTER the @AfterThrowing.

See the details here in the Spring Documentation - Advice Ordering

![image](https://user-images.githubusercontent.com/48476504/138268811-efd5f3ca-42ee-4808-ac75-5e79214f4407.png)

Here's the original github issue related to this feature change.

Implement reliable invocation order for advice within an @Aspect

This github issue is a good read because it contains the rationale for the change. It is always useful to understand the reasoning behind a feature change. :-)
---
