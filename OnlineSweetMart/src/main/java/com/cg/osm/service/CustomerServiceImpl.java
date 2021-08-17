
package com.cg.osm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Customer;
import com.cg.osm.exception.CustomerNotFoundException;
import com.cg.osm.exception.InvalidUserDataException;
import com.cg.osm.repository.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRepository;

	@Override
	public List<Customer> addCustomer(Customer customer) throws CustomerNotFoundException {
		if (customerRepository.findById(customer.getCustomerId()).isEmpty()) {
			customerRepository.saveAndFlush(customer);
			return customerRepository.findAll();
		}

		throw new CustomerNotFoundException("Sorry,Customer already exists");
	}

	@Override
	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> updateCustomer(Customer customer, int customerId) {
		customerRepository.saveAndFlush(customer);
		return customerRepository.findAll();
	}

	@Override
	public Customer cancelCustomer(int customerId) throws CustomerNotFoundException {
		Customer cust = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Cannot delete an empty or null value"));
		customerRepository.deleteById(customerId);
		return cust;
	}

	@Override
	public Customer findCustomer(int customerId) throws CustomerNotFoundException {

		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Cannot find customer for id " + customerId));

	}

	@Override
	public Customer checkLoggin(String username, String password) throws InvalidUserDataException {
		Customer c = customerRepository.checkLogin(username, password);
		return c;
	}

}
