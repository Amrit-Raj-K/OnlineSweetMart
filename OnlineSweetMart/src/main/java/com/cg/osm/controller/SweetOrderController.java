package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.exception.SweetOrderNotFoundException;
import com.cg.osm.service.SweetOrderService;

@RestController
@RequestMapping("/api")
public class SweetOrderController {

	@Autowired
	private SweetOrderService service;

	/**
	 * localhost:8091/RestOrder/api/save
	 * 
	 * @param order
	 * @return saved order
	 */
	@PostMapping("/save")
	public ResponseEntity<SweetOrder> saveOrder(@RequestBody SweetOrder order) {
		SweetOrder savedorder = service.addSweetOrder(order);
		if (savedorder == null) {
			return new ResponseEntity("order couldnt be saved", HttpStatus.OK);
		}
		return new ResponseEntity<SweetOrder>(savedorder, HttpStatus.OK);

	}

	/**
	 * localhost:8091/RestOrder/api/update
	 * 
	 * @param order
	 * @return updated order
	 * @throws SweetOrderNotFoundException
	 */
	@PutMapping("/update")
	public ResponseEntity<SweetOrder> updateOrder(@RequestBody SweetOrder order) throws SweetOrderNotFoundException {
		try {
			SweetOrder updatedorder = service.findOrder(order.getSweetOrderId());
			ResponseEntity<SweetOrder> response = null;
			if (updatedorder == null)
				throw new SweetOrderNotFoundException("Order with " + order.getSweetOrderId() + " already Exist");
			else {
				service.updateSweetOrder(order);
				response = new ResponseEntity<SweetOrder>(updatedorder, HttpStatus.OK);
				return response;
			}
		} catch (Exception e) {
			throw new SweetOrderNotFoundException(e.getMessage());
		}
	}

	/**
	 * localhost:8098/api/delete
	 * 
	 * @param sweetOrderId
	 * @return deleted order
	 * @throws SweetOrderNotFoundException
	 */

	@DeleteMapping("/delete/{sweetOrderId}")
	public ResponseEntity<SweetOrder> deleteOrder(@PathVariable("sweetOrderId") Integer sweetOrderId)
			throws SweetOrderNotFoundException {
		try {
			SweetOrder sweetOrder = service.findOrder(sweetOrderId);
			sweetOrder.setCustomers(null);
			service.updateSweetOrder(sweetOrder);
			service.cancelSweetOrder(sweetOrderId);
			ResponseEntity<SweetOrder> response = new ResponseEntity<SweetOrder>(sweetOrder, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			throw new SweetOrderNotFoundException(e.getMessage());
		}
	}

	/**
	 * localhost:8091/RestOrder/api/get
	 * 
	 * @return List of all Sweetorders
	 */
	@GetMapping("/get")
	public ResponseEntity<List<SweetOrder>> getAllOrders() {
		List<SweetOrder> orders = service.showAllSweetOrders();
		if (orders.isEmpty()) {
			return new ResponseEntity("Sorry!,orders are  not available", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SweetOrder>>(orders, HttpStatus.OK);
	}

	/**
	 * localhost:8091/RestOrder/api/get
	 * 
	 * @param sweetOrderId
	 * @return sweetorder
	 * @throws SweetOrderNotFoundException
	 */
	@GetMapping("/get/{sweetOrderId}")
	public ResponseEntity<SweetOrder> findSweetOrder(@PathVariable("sweetOrderId") Integer sweetOrderId)
			throws SweetOrderNotFoundException {
		try {
			SweetOrder order = service.findOrder(sweetOrderId);
			if (order != null) {
				ResponseEntity<SweetOrder> response = new ResponseEntity<SweetOrder>(order, HttpStatus.OK);
				return response;
			} else {
				throw new SweetOrderNotFoundException("SweetOrder with this ID is not found");
			}
		} catch (Exception e) {
			throw new SweetOrderNotFoundException(e.getMessage());
		}

	}
}