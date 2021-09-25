# Spring MVC Form Validation
---
# Special Note about BindingResult Parameter Order
## When performing Spring MVC validation, the location of the BindingResult parameter is very important. In the method signature, the BindingResult parameter must appear immediately after the model attribute. 

### If you place it in any other location, Spring MVC validation will not work as desired. In fact, your validation rules will be ignored.
```java
        @RequestMapping("/processForm")
        public String processForm(
                @Valid @ModelAttribute("customer") Customer theCustomer,
                BindingResult theBindingResult) {
            ...            
        }
```
Here is the relevant section from the Spring Reference Manual

---

Defining @RequestMapping methods

@RequestMapping handler methods have a flexible signature and can choose from a range of supported controller method arguments and return values.
...

The Errors or BindingResult parameters have to follow the model object that is being
bound immediately ...

Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods

---
