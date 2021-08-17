package com.cg.osm.testing;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.cg.osm.entity.SweetOrder;
import com.cg.osm.exception.SweetOrderNotFoundException;
import com.cg.osm.repository.SweetOrderRepository;
import com.cg.osm.service.SweetOrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SweetOrderTesting {

	@Mock
	private SweetOrderRepository sweetOrderRepository;

	@Autowired
	@InjectMocks
	private SweetOrderServiceImpl sweetOrderService;
	private SweetOrder order1;
	private SweetOrder order2;
	List<SweetOrder> orderList;

	@BeforeEach
	public void setUp() {
		orderList = new ArrayList<>();
		order1 = new SweetOrder(101, "12-AUG-2021", 301);
		order2 = new SweetOrder(2, "13-AuG-2021", 200);
		orderList.add(order1);
		orderList.add(order2);
	}

	@AfterEach
	public void tearDown() {
		order1 = order2 = null;
		orderList = null;
	}

	// Test Case for Saving a Order
	@Test
	void givenSweetOrderToAddShouldReturnAddedSweetOrder() throws SweetOrderNotFoundException {
		when(sweetOrderRepository.saveAndFlush(any())).thenReturn(order1);
		sweetOrderService.addSweetOrder(order1);
		verify(sweetOrderRepository, times(1)).saveAndFlush(any());
	}

	// Test Code for Retrieval of all Orders
	@Test
	public void GivenGetAllOrdersShouldReturnListOfAllOrders() {
		sweetOrderRepository.saveAndFlush(order1);
		// stubbing mock to return specific data
		when(sweetOrderRepository.findAll()).thenReturn(orderList);
		List<SweetOrder> orderList1 = sweetOrderService.showAllSweetOrders();
		assertEquals(orderList1, orderList);
		verify(sweetOrderRepository, times(1)).saveAndFlush(order1);
		verify(sweetOrderRepository, times(1)).findAll();
	}

	@Test
	void testDeleteSweetOrderByIdShouldThrowSweetOrderNotFoundException() {
		assertThrows(SweetOrderNotFoundException.class, () -> {
			sweetOrderService.cancelSweetOrder(2);
		});
	}

	@Test
	void testFindSweetOrderByIdShouldThrowSweetOrderNotFoundException() {
		assertThrows(SweetOrderNotFoundException.class, () -> {
			sweetOrderService.findOrder(2);
		});
	}

}
