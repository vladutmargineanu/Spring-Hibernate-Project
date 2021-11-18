Multiple Data Sources in Spring

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