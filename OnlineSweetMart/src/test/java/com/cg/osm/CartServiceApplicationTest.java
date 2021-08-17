package com.cg.osm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.cg.osm.entity.Cart;
import com.cg.osm.exception.CartNotFoundException;
import com.cg.osm.repository.CartRepository;
import com.cg.osm.service.CartServiceImp;

@ExtendWith(MockitoExtension.class)
public class CartServiceApplicationTest { // Testing for Cart service layer

	@Mock
	private CartRepository cartrepo;

	@Autowired
	@InjectMocks
	private CartServiceImp crt;
	private Cart cart1;
	private Cart cart2;
	private List<Cart> cartlist;

	@BeforeEach
	public void setUp() {
		cartlist = new ArrayList<>();
		cart1 = new Cart(1, 200, 100, 2);
		cart2 = new Cart(2, 300, 150, 2);
		cartlist.add(cart1);
	}

	@AfterEach
	public void tearDown() {
		cart1 = cart2 = null;
		cart2 = null;
	}

	// Test Case for Saving a Order
	@Test
	void givenCartToAddShouldReturnAddedCart() throws CartNotFoundException {
		crt.addCart(cart1);
		verify(cartrepo, times(1)).saveAndFlush(cart1);
	}

	@Test
	public void GivenGetAllCartsShouldReturnListOfAllCarts() {
		cartrepo.saveAndFlush(cart1);
		// stubbing mock to return specific data
		when(cartrepo.findAll()).thenReturn(cartlist);
		List<Cart> cartList1 = crt.showAllCarts();
		assertEquals(cartList1, cartlist);
		verify(cartrepo, times(1)).saveAndFlush(cart1);
		verify(cartrepo, times(1)).findAll();
	}

	@Test
	void testShowCrtByIdShouldThrowCartNotFoundException() {
		assertThrows(CartNotFoundException.class, () -> {
			crt.showById(1);
		});
	}

	@Test
	void contextLoads() {
	}

}
