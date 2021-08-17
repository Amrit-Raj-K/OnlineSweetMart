package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Customer;
import com.cg.osm.exception.CustomerNotFoundException;
import com.cg.osm.exception.InvalidUserDataException;





public interface CustomerService {

	
	public List<Customer> addCustomer(Customer customer) throws CustomerNotFoundException;
	
	
	public List<Customer> updateCustomer(Customer customer,int customerId);
	
	public Customer cancelCustomer(int customerId) throws CustomerNotFoundException;
	
	
	public List<Customer> fetchAllCustomers();
	
	
	
	public Customer checkLoggin(String username,String password) throws InvalidUserDataException;
	
	
	
	public Customer findCustomer(int customerId) throws CustomerNotFoundException;
	
}
