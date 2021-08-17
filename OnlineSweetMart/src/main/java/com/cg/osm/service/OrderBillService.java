package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.exception.OrderBillNotFoundException;


public interface OrderBillService {
	public List<OrderBill> addOrderBill(OrderBill orderBill) throws OrderBillNotFoundException;
	//public List<OrderBill> updateOrderBill(OrderBill orderBill) throws OrderBillNotFoundException;
	public List<OrderBill> cancelOrderBill(int orderBillId) throws OrderBillNotFoundException;
	public List<OrderBill> showAllOrderBills();
	public OrderBill showAllOrderBill(int orderBilldId)throws OrderBillNotFoundException;
	List<OrderBill> updateOrderBill(OrderBill orderBill, Integer id) throws OrderBillNotFoundException;//updated
	
}
