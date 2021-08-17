package com.cg.osm.testing;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.Customer;
import com.cg.osm.exception.CustomerNotFoundException;
import com.cg.osm.repository.CustomerRespository;

import com.cg.osm.service.CustomerServiceImpl;




@ExtendWith(MockitoExtension.class)
class CustomerClassApplicationTests { 
	
	
	/*
	 * Mockito is used to mock interfaces so that a dummy functionality 
	 * can be added to a mock interface which can be used in unit testing
	 */
   
	
	
	
	@Mock
	private CustomerRespository customerRepository;

	@Autowired
	@InjectMocks
	private CustomerServiceImpl service;
	private Customer customer1;
	private Customer customer2;
	private List<Customer> customerlist;

	@BeforeEach
	public void setUp() {
		
		customerlist = new ArrayList<>();
		
		//creating customer object to pass values
		customer1 = new Customer(1, "ajay", "20-5-89/A,md road", "ajay@123", "1");
		customer2 = new Customer(1, "priya", "20-4-89/A,rd road", "priya@123"," 1");
		
		//adding the data to customerlist
		customerlist.add(customer1);
		customerlist.add(customer2);
	}

	@AfterEach
	public void tearDown() {
		customer1 = customer2 = null;
		customer2 = null;
	}

	// Test Case for Saving a Customer
	@Test
	void addCustomerTest() throws CustomerNotFoundException {
		service.addCustomer(customer1);
		verify(customerRepository, times(1)).saveAndFlush(customer1);
	}

	
	// Test for fetching all customers
	
	@Test
	public void showAllCustomers() {
		customerRepository.saveAndFlush(customer1);
		// stubbing mock to return specific data
		when(customerRepository.findAll()).thenReturn(customerlist);
		List<Customer> customerList1 = service.fetchAllCustomers();
		assertEquals(customerList1, customerlist);
		verify(customerRepository, times(1)).saveAndFlush(customer1);
		verify(customerRepository, times(1)).findAll();
	}
	
	//Test for delete customer

	@Test
	void testDeleteCustomerShouldThrowSweetItemNotFoundException() {
		assertThrows(CustomerNotFoundException.class, () -> {
			service.cancelCustomer(2);
		});
	}
	
	//Test to find customer

	@Test
	void testFindCustomerShouldThrowSweetItemNotFoundException() {
		assertThrows(CustomerNotFoundException.class, () -> {
			service.findCustomer(1);
		});
	}

}
	 
