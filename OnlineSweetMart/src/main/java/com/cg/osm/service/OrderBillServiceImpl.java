package com.cg.osm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.exception.OrderBillNotFoundException;
import com.cg.osm.repository.OrderBillRepository;

@Service
public class OrderBillServiceImpl implements OrderBillService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderBillServiceImpl.class); // created a logger object

	@Autowired
	private OrderBillRepository orderBillRepo;

	@Override
	public List<OrderBill> addOrderBill(OrderBill orderBill) throws OrderBillNotFoundException{
		if(orderBillRepo.findById(orderBill.getOrderBillId()).isEmpty()) {
			orderBillRepo.saveAndFlush(orderBill);
		return orderBillRepo.findAll();
		}
		throw new OrderBillNotFoundException("Sorry! order with id "+orderBill.getOrderBillId()+" already exists");
	}

	@Override
	public List<OrderBill> updateOrderBill(OrderBill orderBill, Integer id) throws OrderBillNotFoundException {
		LOG.info("in updateOrderBill -" + orderBill.toString() + id);
		if (!orderBillRepo.findById(id).isEmpty())
			orderBillRepo.saveAndFlush(orderBill);
		else if (orderBillRepo.findById(id).isEmpty())
			return null; // null if
		LOG.info(" in updateOrderBill after updating " + orderBillRepo.count());
		return orderBillRepo.findAll();
	}

	@Override
	public List<OrderBill> cancelOrderBill(int orderBillId) throws OrderBillNotFoundException {
		orderBillRepo.findById(orderBillId).orElseThrow(() -> new OrderBillNotFoundException("Cannot delete an empty or null value")); 
		orderBillRepo.deleteById(orderBillId);
		return orderBillRepo.findAll();
	}

	@Override
	public List<OrderBill> showAllOrderBills() {
		return orderBillRepo.findAll();
	}

	@Override
	public OrderBill showAllOrderBill(int orderBillId) throws OrderBillNotFoundException{
		Optional<OrderBill> order = orderBillRepo.findById(orderBillId);
		
		if(order.isEmpty())
			throw new OrderBillNotFoundException("OrderbillId "+orderBillId+" is not present");
		return order.get();// Doubtful
	}

}
