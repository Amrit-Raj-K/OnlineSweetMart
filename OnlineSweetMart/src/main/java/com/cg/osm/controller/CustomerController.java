package com.cg.osm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Customer;
import com.cg.osm.exception.CustomerNotFoundException;
import com.cg.osm.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service; //CustomerService is a dependency of CustomerController
	

	// localhost:8091/customer/save
	@PostMapping("/save")
	public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customers)
			throws CustomerNotFoundException {
         logger.info("Adding a customer : " + customers);
		List<Customer> customer = service.addCustomer(customers);

		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}
	
	
    //localhost:8091/customer/update/{customerId}
	@PutMapping("/update/{customerId}")
	
	public ResponseEntity<List<Customer>> updateCustomer(@RequestBody Customer customer,
			@PathVariable("customerId") int customerId) throws CustomerNotFoundException {
        
		try {
			service.findCustomer(customerId);
		if (customerId == customer.getCustomerId() ) {
			
			logger.info("Upadating a customer");
			List<Customer> customers = service.updateCustomer(customer, customerId);


			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		}
		throw new CustomerNotFoundException("Customer ID mismatch");
	}catch (Exception e) {
		throw new CustomerNotFoundException(e.getMessage());
	}
	
	}

    //localhost:8091/customer/get

	@GetMapping("/get")
	public ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> customer = service.fetchAllCustomers();
        logger.info("Fetching all customer records");
		if (customer.isEmpty()) {

			return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}

}




