# FAQ: How does "processForm" work for "/hello"?
# FAQ: 

## Question: Can you please clarify how /hello is getting appended to the jsp file action for "processForm"?

## Answer
---
You can use "processForm" because it is a relative path to the controller "/hello" request mapping. Here is how it works.

1. When you wish to view the form, the HTML link points to "hello/showForm". This calls the controller and it displays the form.

2. At this point the browser URL/path is: http://localhost:8080/spring-mvc-demo/hello

3. The HTML form uses "processForm" for the form action. Notice that it does not have a forward slash, as a result, this will be relative to the current browser URL. Since the current browser URL is 

http://localhost:8080/spring-mvc-demo/hello

Then the actual form URL submission will send it to

http://localhost:8080/spring-mvc-demo/hello/processForm

The part in bold with map to the controller with top-level request mapping "/hello" and then map to request mapping in that class "/processForm"

The key here is relative path of showing the form and then submitting to relative path.

---
