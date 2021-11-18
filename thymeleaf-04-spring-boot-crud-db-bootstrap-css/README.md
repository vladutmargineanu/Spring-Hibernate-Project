# Why we are we not using @PutMapping or @DeleteMapping and instead we are using @GetMapping?

---

### Response
When you are building MVC browser-based applications, you can only use @GetMapping and @PostMapping.

If you are building REST applications, you can use all of the HTTP methods: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 

---

# Why do we need a hidden field id in the form? We are finding the right object from the database and sending a reference of it to the view template as a model attribute.

### Response
The lifecycle of this Employee object is for the web request. It only has request scope. Once the web request is over then the Employee object is no longer available.

/showFormForUpdate: the Employee object is retrieved from the database, added to the model and used to populate the HTML form.

/save: when the form is submitted a NEW Employee object is created and it is populated with data from the HTML form. This is NOT the same Employee object that was originally retrieved from the database. Spring is starting with a brand new Employee object. It calls the setter methods on the new Employee object and passes in data from the HTML form field.

In this case, since it a new Employee object ... it doesn't have an ID. So we use the hidden form field to pass over the ID value from the form. The object is then passed to the controller for processing. This is a common technique for performing entity updates on real-time projects.

The important thing it to note is that the Employee object is not shared between web requests.



>> What happens if this is not added? When the employee-form is saved

>> and resubmitted, if the id is not present in the form ( as a hidden field),

>> is it lost upon sending?

If you did not pass over the ID, then yes it is lost. As a result, the DAO would not know that this is an "update" process. So instead of updating the employee, the DAO would perform an insert for a new employee row. This is not the desired behavior. That's why we need to pass over the id.
If you have time, I'd recommend that you experiment with this. Remove the hidden form field from the form, and attempt to update an employee. You will see that the app will incorrectly perform database inserts instead of database updates.

---
