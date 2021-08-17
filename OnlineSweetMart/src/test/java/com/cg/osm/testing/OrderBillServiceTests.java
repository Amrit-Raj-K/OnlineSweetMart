package com.cg.osm.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.osm.entity.OrderBill;
import com.cg.osm.entity.SweetOrder;
import com.cg.osm.exception.OrderBillNotFoundException;
import com.cg.osm.repository.OrderBillRepository;
import com.cg.osm.service.OrderBillServiceImpl;


@ExtendWith(MockitoExtension.class)
class OrderBillServiceTests {

	@Mock
	private OrderBillRepository repo;
	
	
	@Autowired
	@InjectMocks
	private OrderBillServiceImpl service;
	
	private OrderBill orderbill1;
	private OrderBill orderbill2;
	private List<OrderBill> orderbilllist;
	
	@BeforeEach
	public void setUp() {
		LocalDate date = LocalDate.of(2021,8, 10);
		SweetOrder sweetorder1 = new SweetOrder(1,date);
		SweetOrder sweetorder2 = new SweetOrder(2,date);
		orderbilllist = new ArrayList<>();
		orderbill1 = new OrderBill(1,date,200,sweetorder1);
		orderbill2 = new OrderBill(2,date,300,sweetorder2);
		orderbilllist.add(orderbill1);
		orderbilllist.add(orderbill2);
	}
	
	@AfterEach
	public void tearDown() {
		orderbill1 = orderbill2 = null;
	}
	final Logger log = LoggerFactory.getLogger(OrderBillServiceTests.class);
	
	
	@Test
	public void testAddOrderBillShouldReturnListOfAllRecordsContainingTheCureentRecord() throws OrderBillNotFoundException {
		log.info(orderbill1.toString());
		List<OrderBill> result = service.addOrderBill(orderbill1);
		assertThat(result.contains(orderbill1));
	}

	@Test
	@DisplayName("Test to check showAllOrderBills")
	void testShowOrderBills() {
		Mockito.when(repo.findAll()).thenReturn(orderbilllist);
		List<OrderBill> result = service.showAllOrderBills();
		assertEquals(2, result.size());
		//assertThat(result.size()==0);
	}
//	
//	@Test
//	void testShowOrderBillsById() throws Exception {
//		OrderBill result = service.showAllOrderBill(orderbill2.getOrderBillId());
//		assertThat(result.getOrderBillId().equals(orderbill2.getOrderBillId()));		
//	}
	
//	@Test
//	void testCancelOrderBill() throws OrderBillNotFoundException {
//		List<OrderBill> result = service.cancelOrderBill(1);
//		assertThat(!result.contains(orderbill1));
//	}
	
//	@Test
//	void testupdateOrderBill() throws OrderBillNotFoundException {
//		Mockito.when(repo.saveAndFlush(orderbill2)).thenReturn(orderbill2);
//		List<OrderBill> result = service.updateOrderBill(orderbill2, orderbill2.getOrderBillId());
//		assertThat(result.contains(orderbill2));
//	}
}
