package com.luv2code.springdemo.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luv2code.springdemo.model.Customer;

@Service
public class CustomerServiceRestClientImpl implements CustomerService {

	private RestTemplate restTemplate;

	private String crmRestUrl;
		
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public CustomerServiceRestClientImpl(RestTemplate theRestTemplate, 
										@Value("${crm.rest.url}") String theUrl) {
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
				
		logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
	}
	
	@Override
	public List<Customer> getCustomers() {
		
		logger.info("in getCustomers(): Calling REST API " + crmRestUrl);

		// make REST call
		ResponseEntity<List<Customer>> responseEntity = 
											restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Customer>>() {});

		// get the list of customers from response
		List<Customer> customers = responseEntity.getBody();

		logger.info("in getCustomers(): customers" + customers);
		
		return customers;
	}

	@Override
	public Customer getCustomer(int theId) {

		logger.info("in getCustomer(): Calling REST API " + crmRestUrl);

		// make REST call
		Customer theCustomer = 
				restTemplate.getForObject(crmRestUrl + "/" + theId, 
										  Customer.class);

		logger.info("in saveCustomer(): theCustomer=" + theCustomer);
		
		return theCustomer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		logger.info("in saveCustomer(): Calling REST API " + crmRestUrl);
		
		int employeeId = theCustomer.getId();

		// make REST call
		if (employeeId == 0) {
			// add employee
			restTemplate.postForEntity(crmRestUrl, theCustomer, String.class);			
		
		} else {
			// update employee
			restTemplate.put(crmRestUrl, theCustomer);
		}

		logger.info("in saveCustomer(): success");	
	}

	@Override
	public void deleteCustomer(int theId) {

		logger.info("in deleteCustomer(): Calling REST API " + crmRestUrl);

		// make REST call
		restTemplate.delete(crmRestUrl + "/" + theId);

		logger.info("in deleteCustomer(): deleted customer theId=" + theId);
	}

}
