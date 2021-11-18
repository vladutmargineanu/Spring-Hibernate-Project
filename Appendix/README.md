# Resources Spring

A frequently asked question is "Where to go from here?" A lot of developers want to further their knowledge by learning advanced Spring topics and practicing projects.

I've compiled a list of resources that you can use to get more information on Spring advanced features. Enjoy!

Spring Boot and Angular

- https://github.com/dsyer/spring-boot-angular

Spring MVC and File Upload

- https://spring.io/guides/gs/uploading-files/

Spring RESTful web services

- https://spring.io/guides/gs/rest-service/

Spring Security for Web Apps

- https://spring.io/guides/gs/securing-web/

Spring and Facebook

- https://spring.io/guides/gs/accessing-facebook/

Spring and Twitter

- https://spring.io/guides/gs/accessing-twitter/

--- 

Build a Basic CRUD App with Angular and Spring Boot

https://developer.okta.com/blog/2017/12/04/basic-crud-angular-and-spring-boot

=====

FAQ: I would like to see examples of real-world projects that use Spring

Answer:

Here are some sample Spring projects you can look at.

They are of moderate size complexity

Project Sagan 

This is a real-world app that powers the Spring.io website. It is in production and used by thousands of users each day.

You can get information about the project and get source code here: - https://github.com/spring-io/sagan/wiki

---

Spring Petstore Example

This is an example project for the classic PetClinic / PetStore example. https://github.com/spring-projects/spring-petclinic

---

E-Commerce Product - Broadleaf

https://www.broadleafcommerce.com/

The Broadleaf product is based on Spring and Hibernate. You can get details on their framework and source code at the link below

https://www.broadleafcommerce.com/framework

---

OpenSource Projects Using Spring

Access real-world projects that make use of Spring code - http://www.programcreek.com/2012/08/open-source-projects-that-use-spring-framework/

---

Finally there are some other instructors here on Udemy that created courses on Spring ecommerce, angular etc. Be sure to check the reviews
and perform your own research on those courses. I am not involved in any of those other courses. I just wanted to pass information along :-)

=====

FAQ: How to Host my Java apps Online?

---

Here's a free guide that walks you through the steps:

The Ultimate Guide to Hosting a Java Web App with Amazon Web Services (AWS) http://coderscampus.com/ultimate-guide-hosting-java-web-app-amazon-web-services-aws/

=====

Student Question I want a solution for hiding customer id in URL. Maybe,change request from GET to POST?

---

Solution

In the files below, look for modified code. Simply search for the text "luv2code: UPDATES".

Here's the basic approach.

For the links, change the table to use forms. There is a form for each row of data. The form would be setup to POST the data. Each row would have a unique button with the ID embedded. Apply special CSS to make the Submit button look like a hypertext link.

The controller request mappings will now support @PostMapping. This is for /showFormForUpdate and /delete

---

Notes about the solution.

Using the POST method does not add security. It simply "hides" the request data. But any web user can still easily see the data. All they have to do is use Chrome Dev Tools or FireFox Firebug. So, using POST is only giving you "security by obscurity" which is weak if you have highly sensitive data.

If you have highly sensitive data then you should use SSL encryption on your server and make use of Spring Security to protect sensitve web URLs in your app.

---

Having the customer ID in the URL is not a problem. The GET approach is a standard practice that is used in the industry. However the solution was provided based on student's request.

In our example, the ID is for customers ... but this could easily be a product ID. The important thing is the customer ID is not sensitive data.

If you check the major ecommerce sites like Amazon or Best Buy, the product ID is heavily used in the URL.

The URLs below are live URLs on production ecommerce systems that use product ID in the URL.

Amazon
- https://www.amazon.com/dp/B01DFKC2SO

BestBuy
- http://www.bestbuy.com/site/amazon-echo-dot/5578864.p?skuId=5578864

---

Solution Source Code

- https://gist.github.com/darbyluv2code/df856411a3e0c926a4654660045acda4

======

FAQ: Which more secure? GET or POST?

Note, simply using the POST method does not add secure encryption. The data is still sent in the clear without any protection or encryption.

See this link:

Is either GET or POST more secure than the other?

https://stackoverflow.com/questions/198462/is-either-get-or-post-more-secure-than-the-other

--- 

You can use SSL for enterprise-grade network security and encryption.

Here's a tutorial on Tomcat SSL: https://www.mulesoft.com/tcat/tomcat-ssl
---
