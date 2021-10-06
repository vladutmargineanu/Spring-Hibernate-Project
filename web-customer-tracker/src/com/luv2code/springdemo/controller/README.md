# FAQ: How to Add Search support
# FAQ: How to add Search features to the App?

A number of students have asked how to add search features to the app? I'll walk through this process.

Basically, we will allow the user to search for a customer by name. We'll add a search box at to the screen and the user can enter a name. On the backend, we'll compare this name to the customer's first name or last name.

![image](https://user-images.githubusercontent.com/48476504/136286736-977c3e76-5417-4f4d-876c-9709a53e94a0.png)


### Overview of Development Process
1. Create the HTML form
2. Add mapping to the controller
3. Add methods in the service layer to delegate to DAO
4. Add method in the DAO to perfom search

### Detailed Steps

1. Create the HTML form

You need to add a search form to read the user input and submit it to your Spring controller mapping

a. Edit the file: list-customers.jsp

b. We'll need to use Spring FORM tags, so at the  top of the file, add the following taglib reference
```xml
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
```

c. Now add a search form right after the search button
```xml
    <!--  add a search box -->
    <form:form action="search" method="GET">
        Search customer: <input type="text" name="theSearchName" />

        <input type="submit" value="Search" class="add-button" />
    </form:form>
```
---

2. Add mapping to the controller

You need to add a mapping to handle the search form submission

a. Edit the file: CustomerController.java

b. Add the new mapping and method
```java
    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";        
    }
```
c. You may have syntax errors on the customerService, but we'll resolve that in the next section.

---

3. Add methods in the service layer to delegate to DAO

You need to add methods in the service layer to delegate calls to the DAO

a. Edit the file: CustomerService.java

b. Add the method declaration
```java
    public List<Customer> searchCustomers(String theSearchName);
```
c. Edit the file: CustomerServiceImpl.java

d. Add the method:
```java
    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }
```
e. You may have syntax errors on the customerDAO, but we'll resolve that in the next section.

---

4. Add method in the DAO to perfom search

Now, we'll add methods in the DAO layer to search for a customer by first name or last name

a. Edit the file: CustomerDAO.java

b. Add the method declaration
```java
    public List<Customer> searchCustomers(String theSearchName);
```
c. Edit the file: CustomerDAOImpl.java

d. Add the method:
```java
    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    }
```
In this method, we need to check "theSearchName", this is the user input. We need to make sure it is not empty. If it is not empty then we will use it in the search query.  If it is empty, then we'll just ignore it and simply return all of the customers.

For the condition when "theSearchName" is not empty, then we use it to compare against the first name or last name. We also make use of the "like" clause and the "%" wildcard characters. This will allow us to search for substrings. For example, if we have customers with last name of "Patel", "Patterson" ... then we can search for "Pat" and it will match on those names.  

Also, notice the query uses the lower case version of the values to make a case insensitive search. If you'd like to make a case sensitive search, then simply remove the lower references.

You can read more on the HQL "like" clause here:
http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#hql-like-predicate

---

5. Test the app

Once you've made all of the updates then you can test your application.

The app will now have the search form at the top. You can enter a name to search and they app will give you the desired results.
---

# FAQ: How to Add Sorting support
# FAQ: How to add Sorting features to the App?

One possible solution is to sort using the columns headers. The user can click on the "First Name" column header and it will sort the data accordingly. Similar thing for other column headers. You can embedded a sort key in the link. When the user clicks the link then you pass the sort key to the back end and the data is sorted accordingly.

Here's a screenshot of the app. Make note of the column headers.

![image](https://user-images.githubusercontent.com/48476504/136287323-75fdab25-bbb4-42fb-9e5a-eb03094b76c3.png)

Overview of Development Process

1. Create a Utility class for sort constants

2. In JSP page, add sort links for column headers

3. Update controller to read sort field

4. Update method in the service layer to delegate to DAO

5. Update method in the DAO to get customers sorted by given field


### DETAILED STEPS

1. Create a Utility class for sort constants

This utility class will hold constant values for the sort fields. The values can be anything, as long as you stay consistent in the app.

File: SortUtils.java
```java
package com.luv2code.springdemo.util;
 
public interface SortUtils {
	
	public static final int FIRST_NAME = 1;
	public static final int LAST_NAME = 2;
	public static final int EMAIL = 3;
 
}
```

2. In JSP page, add sort links for column headers

In this page, the user can click on the "First Name" column header and it will sort the data accordingly. The links will have an embedded a sort key.

The code below defines a link for the first name. Note the use of SortUtils.FIRST_NAME.

File: list-customers.jsp
```xml
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>

				<!-- construct a sort link for first name -->
				<c:url var="sortLinkFirstName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
				</c:url>
```
We can do a similar thing for last name and email.
```xml
				<!-- construct a sort link for last name -->
				<c:url var="sortLinkLastName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
				</c:url>					
 
				<!-- construct a sort link for email -->
				<c:url var="sortLinkEmail" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
				</c:url>					
```

Then for the column headings, we set up the <a href> using the the appropriate link.
```xml
				<tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					<th>Action</th>
				</tr>
```

This provides the clickable links on the page as shown below.

![image](https://user-images.githubusercontent.com/48476504/136287355-c8c44bab-8036-4201-a6a6-3afa7f9fa884.png)


3. Update controller to read sort field

In the CustomerController, we need to update the method to read the sort field. If not sort field is provided, then we just default to SortUtils.LAST_NAME.

File: CustomerController.java
```java
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from the service
		List<Customer> theCustomers = null;
		
		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);			
		}
		else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

```


4. Update method in the service layer to delegate to DAO

Now, we update the getCustomers(int theSortField) method to accept an int parameter. This is for the service interface and service implementation.

File: CustomService.java
```java
package com.luv2code.springdemo.service;
 
import java.util.List;
 
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;
 
public interface CustomerService {
 
	public List<Customer> getCustomers(int theSortField);
 
	public void saveCustomer(Customer theCustomer);
 
	public Customer getCustomer(int theId);
 
	public void deleteCustomer(int theId);
	
}
```

File: CustomerServiceImpl.java
```java
package com.luv2code.springdemo.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;
 
@Service
public class CustomerServiceImpl implements CustomerService {
 
	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}
}
```

5. Update method in the DAO to get customers sorted by given field

In DAO interface, update the method to accept integer

File: CustomerDAO.java
```java
package com.luv2code.springdemo.dao;
 
import java.util.List;
 
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;
 
public interface CustomerDAO {
 
	public List<Customer> getCustomers(int theSortField);
	...
}
```

In the CustomerDAOImpl.java, the getCustomers(...) method has theSortField parameter. It will determine the sort field name based on the parameter.

File: CustomerDAOImpl.java
```java
package com.luv2code.springdemo.dao;
 
import java.util.List;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;
 
@Repository
public class CustomerDAOImpl implements CustomerDAO {
 
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers(int theSortField) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// determine sort field
		String theFieldName = null;
		
		switch (theSortField) {
			case SortUtils.FIRST_NAME: 
				theFieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				theFieldName = "lastName";
		}
		
		// create a query  
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> theQuery = 
				currentSession.createQuery(queryString, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

}
```

As you can see, there is a switch statement for theSortField. Based on the value, then it will use field name of "firstName", "lastName" etc. If the values don't match, then we default to sorting by lastName.

Once you run the application, then you can click the column headers to sort the data accordingly. Enjoy!
  
  ![image](https://user-images.githubusercontent.com/48476504/136287379-776b56f2-61a4-4ab8-ab88-e39e669b2159.png)

---
