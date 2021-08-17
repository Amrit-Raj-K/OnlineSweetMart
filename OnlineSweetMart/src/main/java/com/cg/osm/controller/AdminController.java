package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Customer;
import com.cg.osm.entity.ProductCategory;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.CustomerNotFoundException;
import com.cg.osm.exception.InvalidUserDataException;
import com.cg.osm.exception.ProductCategoryNotFoundException;
import com.cg.osm.exception.SweetItemNotFoundException;
import com.cg.osm.service.CustomerService;
import com.cg.osm.service.ProductCategoryService;
import com.cg.osm.service.SweetItemService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/type")
	public ResponseEntity<String> checkLoggin(@RequestBody Customer c) throws InvalidUserDataException {
		Customer cust = service.checkLoggin(c.getUsername(), c.getPassword());
		if (cust == null) {
			throw new InvalidUserDataException("Check the entered values for customername and password!");
		}
		String role;
		if (cust.getType().equals("1")) {

			role = "admin";
			return new ResponseEntity<String>("Welcome " + role, HttpStatus.OK);
		} else if (cust.getType().equals("2")) {

			role = "user";
			return new ResponseEntity<String>("Welcome " + role, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid user type!", HttpStatus.BAD_REQUEST);
		}
	}

	@Autowired
	private CustomerService custservice;


	@PostMapping("/addcustomer")
	public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customers)
			throws CustomerNotFoundException {
		List<Customer> customer = custservice.addCustomer(customers);
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}

	@Autowired
	private CustomerService service;

	@DeleteMapping("/deletecustomer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") int customerId)
			throws CustomerNotFoundException {
		try {
			Customer cust = service.findCustomer(customerId);
			cust.setCart(null);
			// service.findCustomer(cust);
			Customer s = service.cancelCustomer(customerId);
			return new ResponseEntity<Customer>(s, HttpStatus.OK);
		} catch (Exception e) {
			throw new CustomerNotFoundException(e.getMessage());
		}
	}

	@Autowired
	private ProductCategoryService pcService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/savepcategory")
	public ResponseEntity<ProductCategory> saveProductCategory(@RequestBody ProductCategory productcategory) {
		ProductCategory category = pcService.addProductCategory(productcategory);
		if (category == null) {
			return new ResponseEntity("category couldnt be saved", HttpStatus.OK);
		}
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deletecategory/{productcategoryId}")
	public ResponseEntity<ProductCategory> deleteProductCategory(
			@PathVariable("productcategoryId") Integer productcategoryId) throws ProductCategoryNotFoundException {
		ProductCategory category = pcService.cancelProductCategory(productcategoryId);
		if (category == null) {
			return new ResponseEntity("Sorry!,productcategory  not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
	}

	@Autowired
	public SweetItemService sweetService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/additem")
	public ResponseEntity<List<SweetItem>> insertProduct(@RequestBody SweetItem item)
			throws SweetItemNotFoundException {
		List<SweetItem> items = sweetService.addSweetItem(item);
		if (items == null) {
			return new ResponseEntity("Sorry! Items not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SweetItem>>(items, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deleteitem/{sweetItemId}")
	public ResponseEntity<List<SweetItem>> deleteItem(@PathVariable("sweetItemId") Integer sweetItemId)
			throws SweetItemNotFoundException {
		List<SweetItem> item = sweetService.cancelSweetItem(sweetItemId);
		if (item == null) {
			return new ResponseEntity("Sorry!,sweet Item" + sweetItemId + " is not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SweetItem>>(item, HttpStatus.OK);
	}
}
