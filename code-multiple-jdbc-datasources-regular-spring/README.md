# Multiple Data Sources in Spring

This project shows how to configure multiple datasources in Spring. The project makes use of all Java configuration with Spring. The project is based on Maven.

In this project, we connect to two different databases: web_customer_tracker and employee_directory

1. SQL Scripts
The SQL scripts are located in the directory:
sql-scripts
- customer.sql: creates the database schema "web_customer_tracker", also adds sample data
- employee.sql: creates the database schema "employee_directory", also adds sample data

You will need to run these scripts accordingly.

2. Data source configuration
The project includes two configuration files to the data source configuration. The files are in the directory:
src/main/resources
- customer-persistence-mysql.properties
- employee-persistence-mysql.properties

3. Spring All Java Configuration
Directory: src/main/java/com/luv2code/demo/datasources/config
View the file: DemoAppConfig.java
This file defines two datasources using the @Bean annotation. One datasource for customerDataSource and another for employeeDataSource. The datasources also need their respective session factories and transaction managers

4. Java DAO code
The project includes DAOs for Customer and Employee. Make note of the @Autowired for the respective session factory. Also make note of the use of @Transacrtional with the name the of appropriate bean.

5. Controller code
The controlle makes use of the customer and employee DAOs. The data is placed in the model.

6. View page
File: display-results.jsp

This JSP page displays the results. It has an HTML table for Employee data and another HTML table for Customer data.
---

# 1. Multiple Data Sources in Spring

See the source code attached to this lecture: bonus-code-multiple-jdbc-datasources-regular-spring.zip



This project shows how to configure multiple datasources in Spring. The project makes use of all Java configuration with Spring. The project is based on Maven.

In this project, we connect to two different databases: web_customer_tracker and employee_directory

1. SQL Scripts

The SQL scripts are located in the directory:

sql-scripts

- customer.sql: creates the database schema "web_customer_tracker", also adds sample data

- employee.sql: creates the database schema "employee_directory", also adds sample data

You will need to run these scripts accordingly.



2. Data source configuration

The project includes two configuration files to the data source configuration. The files are in the directory:

src/main/resources

- customer-persistence-mysql.properties

- employee-persistence-mysql.properties



3. Spring All Java Configuration

Directory: src/main/java/com/luv2code/demo/datasources/config

View the file: DemoAppConfig.java

This file defines two datasources using the @Bean annotation. One datasource for customerDataSource and another for employeeDataSource. The datasources also need their respective session factories and transaction managers



4. Java DAO code

The project includes DAOs for Customer and Employee. Make note of the @Autowired for the respective session factory. Also make note of the use of @Transactional with the name the of appropriate bean.



5. Controller code

The controller makes use of the customer and employee DAOs. The data is placed in the model.



6. View page

File: display-results.jsp

This JSP page displays the results. It has an HTML table for Employee data and another HTML table for Customer data.



---

# 2. Multiple Data Sources in Spring Boot

Creating a custom data source using @Configuration and DataSourceBuilder



Create configuration class. Specify the prefix of your properties
```java
@Configuration
public class DemoConfiguration {
 
  @Bean
  @ConfigurationProperties("app.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
}
```

Add these to application.properties. Note the prefix of properties. Also note: "jdbc-url" … not just “url"
```xml
#
# JDBC properties
#
app.datasource.jdbc-url=jdbc:mysql://localhost:3306/employee_directory?useSSL=false&serverTimezone=UTC
app.datasource.username=springstudent
app.datasource.password=springstudent
```

Now if you want more datasources … just use more @Beans
```java
@Bean
@ConfigurationProperties(“alpha.datasource")
public DataSource alphaDataSource() {
  return DataSourceBuilder.create().build();
}


@Bean
@ConfigurationProperties(“bravo.datasource")
public DataSource betaDataSource() {
  return DataSourceBuilder.create().build();
}
```

In application.properties
```xml
alpha.datasource.jdbc-url=jdbc:mysql://localhost:3306/employee_directory?useSSL=false&serverTimezone=UTC
alpha.datasource.username=springstudent
alpha.datasource.password=springstudent
 
beta.datasource.jdbc-url=jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC
beta.datasource.username=foo
beta.datasource.password=bar
```
