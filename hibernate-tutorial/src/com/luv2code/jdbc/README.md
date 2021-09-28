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
