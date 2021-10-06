package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer DAO
	
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional 
	public List<Customer> getCustomers() {
		
		// delegate calls to DAO
		return customerDAO.getCustomers();
	}

}
