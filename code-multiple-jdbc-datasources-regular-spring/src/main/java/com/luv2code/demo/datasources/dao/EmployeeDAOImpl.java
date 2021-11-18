package com.luv2code.demo.datasources.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.demo.datasources.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// need to inject the session factory
	@Autowired
	@Qualifier("employeeSessionFactory")
	private SessionFactory sessionFactory;
			
	@Override
	@Transactional("employeeTransactionManager")
	public List<Employee> getEmployees() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Employee> theQuery = 
				currentSession.createQuery("from Employee order by lastName",
						Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
				
		// return the results		
		return employees;
	}


}







