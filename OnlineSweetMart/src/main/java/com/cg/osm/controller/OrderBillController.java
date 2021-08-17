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

import com.cg.osm.entity.OrderBill;
import com.cg.osm.exception.OrderBillNotFoundException;
import com.cg.osm.service.OrderBillService;

@RestController
@RequestMapping("/orderbill")
public class OrderBillController {

	@Autowired
	private OrderBillService orderBillService;

	@GetMapping("/get")
	public ResponseEntity<List<OrderBill>> showAllOrderBills() {
		List<OrderBill> orderBills = orderBillService.showAllOrderBills();
		if (orderBills.isEmpty()) {
			return new ResponseEntity("Sorry! OrderBills are not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<OrderBill>>(orderBills, HttpStatus.OK);
	}

	@GetMapping("/get/{orderBillId}")
	public ResponseEntity<OrderBill> showOrderBill(@PathVariable("orderBillId") Integer orderBillId) throws OrderBillNotFoundException {
		OrderBill orderbill = orderBillService.showAllOrderBill(orderBillId);
		if (orderbill == null) {
			return new ResponseEntity("Sorry! Products not found!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<OrderBill>(orderbill, HttpStatus.OK);
	}

	@PutMapping("/update/{orderBillId}")
	public ResponseEntity<List<OrderBill>> updateOrderBill(@RequestBody OrderBill orderBill,
			@PathVariable("orderBillId") Integer orderBillId) throws OrderBillNotFoundException {
		if (orderBillId == orderBill.getOrderBillId()) { // comparing id in url to orderBillId in response posted using
															// postman, so PutMapping does not work like PostMapping
			List<OrderBill> orderBills = orderBillService.updateOrderBill(orderBill, orderBillId);
//			if (orderBills.isEmpty() || orderBills == null)
//				throw new OrderBillNotFoundException("bill with id: " + orderBillId + " is not available");

			return new ResponseEntity<List<OrderBill>>(orderBills, HttpStatus.OK);
		}
		throw new OrderBillNotFoundException("orderBillId does not match with the id passed in the url");
	}

	@PostMapping("/post")
	public ResponseEntity<List<OrderBill>> addOrderBill(@RequestBody OrderBill orderBill)
			throws OrderBillNotFoundException {
		List<OrderBill> orderBills = orderBillService.addOrderBill(orderBill);
		if (orderBills.isEmpty()) {
			return new ResponseEntity("Sorry! OrderBill is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<OrderBill>>(orderBills, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{orderBillId}")
	public ResponseEntity<List<OrderBill>> deleteOrderBill(@PathVariable("orderBillId") Integer orderBillId)
			throws OrderBillNotFoundException {
		OrderBill order = orderBillService.showAllOrderBill(orderBillId);
		if(order != null)
			order.setListSweetOrder(null);
		List<OrderBill> orderBills = orderBillService.cancelOrderBill(orderBillId);
		if (orderBills.isEmpty() || orderBills == null) {
			throw new OrderBillNotFoundException("bill with id: " + orderBillId + " is not available");
		}
		return new ResponseEntity<List<OrderBill>>(orderBills, HttpStatus.OK);
	}
}
