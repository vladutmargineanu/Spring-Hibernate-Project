# FAQ: How did you get the jdbcURL?
## Answer:

```java
String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false"; 
```

The following is the database connection URL syntax for MySQL Connector/J:
```
jdbc:mysql://[host]
    [:port]/[database]
    [?propertyName1][=propertyValue1]
    [&propertyName2][=propertyValue2]...
 ```
host:port is the host name and port number of the computer hosting your database. If not specified, the default values of host and port are 127.0.0.1 and 3306, respectively.

database is the name of the database to connect to. If not specified, a connection is made with no default database.

propertyName=propertyValue represents an optional, ampersand-separated list of properties. These attributes enable you to instruct MySQL Connector/J to perform various tasks.

Documentation for JDBC url is here:
https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html

----

In our example:

host:port is localhost:3306, because localhost refers to our local computer, 3306 is the default mysql port

database is hb_student_tracker because that's the name of the database schema that was created by our sql scripts in the previous video. A database schema is the name for a collection of tables.

useSSL=false tells MySQL not to use SSL for this connection

---
