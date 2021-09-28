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

# FAQ: How to read Dates with Hibernate
# FAQ: Handling Dates with Hibernate

## How can I read date strings from the command-line and store them as dates in the database?

### Answer: You can make use of a combination of Java's date formatting class and Hibernate annotations.

Sample output:
```
Student [id=50, firstName=Paul, lastName=Doe, email=paul@luv2code.com, dateOfBirth=null]
Student [id=51, firstName=Daffy, lastName=Duck, email=daffy@luv2code.com, dateOfBirth=null]
Student [id=52, firstName=Paul, lastName=Doe, email=paul@luv.com, dateOfBirth=31/12/1998]
```

Development Process Overview

1. Alter database table for student
2. Add a date utils class for parsing and formatting dates
3. Add date field to Student class
4. Add toString method to Student class
5. Update CreateStudentDemo

----

Detailed steps

1. Alter database table for student

We need to alter the database table to add a new column for "date_of_birth".

Run the following SQL in your MySQL Workbench tool.
```sql
ALTER TABLE `hb_student_tracker`.`student` 
ADD COLUMN `date_of_birth` DATETIME NULL AFTER `last_name`;
```
--

2. Add a date utils class for parsing and formatting dates

We need to add a DateUtils class to handle parsing and formatting dates. The source code is here. The class should be placed in the package: com.luv2code.hibernate.demo.

The date formatter uses special symbols for formatting/parsing.
-  dd:  day in month (number)
-  MM:  month in year (number)
- yyyy: year

See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html

```java
package com.luv2code.hibernate.demo;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateUtils {
    
    // The date formatter
    // - dd:   day in month (number)
    // - MM:   month in year (number)
    // - yyyy: year
    //
    // See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
    //
    //
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    // read a date string and parse/convert to a date
    public static Date parseDate(String dateStr) throws ParseException {
        Date theDate = formatter.parse(dateStr);
        
        return theDate;        
    }
    
    // read a date and format/convert to a string
    public static String formatDate(Date theDate) {
        
        String result = null;
        
        if (theDate != null) {
            result = formatter.format(theDate);
        }
        
        return result;
    }
}
```
---

3. Add date field to Student class

We need to add a date field to the Student class. We map this field to the database column, "date_of_birth". Also, we make use of the @Temporal annotation. This is a Java annotation for storing dates.
```java
    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)    
    private Date dateOfBirth;
```    
    
Here's the full source code.

---
```java
package com.luv2code.hibernate.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luv2code.hibernate.demo.DateUtils;

@Entity
@Table(name="student")
public class Student {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)    
    private Date dateOfBirth;
    
    public Student() {
        
    }

    public Student( String firstName, String lastName, String email, Date theDateOfBirth) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = theDateOfBirth;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
    }
        
}
```
---

4. Add toString method to Student class

We will make an update to the toString method in our Student class.  It will use the formatter from our DateUtils.class. This code is already included in Student.java from the previous step. I'm just highlighting it here for clarity.
```java
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
```
Note the use of DateUtils above.

---

5. Update CreateStudentDemo

Now for the grand finale. In the main program, read the date as a String and parse/convert it to a date. Here's the snippet of code.
```java
            String theDateOfBirthStr = "31/12/1998";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            
            Student tempStudent = new Student("Pauly", "Doe", "paul@luv.com", theDateOfBirth);
```

Here's the full code:

```java
package com.luv2code.hibernate.demo;
 
import java.text.ParseException;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;
 
public class CreateStudentDemo {
 
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
            // create a student object
            System.out.println("creating a new student object ...");
 
            String theDateOfBirthStr = "31/12/1998";
 
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
 
            Student tempStudent = new Student("Pauly", "Doe", "paul@luv.com", theDateOfBirth);
 
            // start transaction
            session.beginTransaction();
 
            // save the student object
            System.out.println("Saving the student ...");
            session.save(tempStudent);
 
            // commit transaction
            session.getTransaction().commit();
 
            System.out.println("Success!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            factory.close();
        }
    }
    
}
```
---
