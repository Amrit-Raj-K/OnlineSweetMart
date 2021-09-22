package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

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
import com.cg.osm.jwt.AuthenticationRequest;
import com.cg.osm.jwt.AuthenticationResponse;
import com.cg.osm.jwt.JwtUtil;
import com.cg.osm.jwt.MyUserDetailsService;
import com.cg.osm.service.CustomerService;
import com.cg.osm.service.ProductCategoryService;
import com.cg.osm.service.SweetItemService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@PostMapping(path = "type")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		Customer cust=null;
		try{ cust = service.checkLoggin(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if (cust == null) {
			throw new InvalidUserDataException("Check the entered values for customername and password!");
		}
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Incorrect username or password", e);
		}}catch(Exception e) {
			throw new SweetItemNotFoundException(e.getMessage());
		}
		
		

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		String role = null;
		if (cust.getType().equals("1")) {

			role = "admin";
			
		} else if (cust.getType().equals("2")) {

			role = "user";
		} 
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		String userid=String.valueOf(cust.getCustomerId());
		String cartId =String.valueOf(cust.getCart().getCartId());
		String username=cust.getUsername();
		return ResponseEntity.ok(new AuthenticationResponse(jwt,role,userid,username,cartId));
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
