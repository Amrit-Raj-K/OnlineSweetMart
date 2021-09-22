package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Cart;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.CartNotFoundException;
import com.cg.osm.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/show")
	public ResponseEntity<List<Cart>> showAllCarts() {
		List<Cart> carts = cartService.showAllCarts();
		if (carts.isEmpty()) {
			return new ResponseEntity("Sorry! Carts are not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}

	@GetMapping("/show/{cartId}")
	public ResponseEntity<Cart> showCart(@PathVariable("cartId") Integer cartId) {
		Cart cart = cartService.showAllCarts(cartId);
		if (cart == null) {
			return new ResponseEntity("Sorry! carts not found!", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/update/{cartId}")
	public ResponseEntity<List<Cart>> updateCart(@RequestBody Cart cart, @PathVariable("cartId") Integer cartId)
			throws CartNotFoundException {
		if (cartId == cart.getCartId()) {
			List<Cart> carts = cartService.updateCart(cart, cartId);
			if (carts.isEmpty()) {

				throw new CartNotFoundException("Cart with Id " + cartId + "is not available");
			}
			return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
		}
		throw new CartNotFoundException("Cart id mismatch");
	}

	@PostMapping("/save")
	public ResponseEntity<List<Cart>> addCart(@RequestBody Cart cart) {
		List<Cart> carts = cartService.addCart(cart);
		if (carts.isEmpty()) {
			return new ResponseEntity("Sorry! Cart is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<List<Cart>> deleteCart(@PathVariable("cartId") Integer cartId) throws CartNotFoundException {
		Cart crt = cartService.showById(cartId);
		crt.setSweetItems(null);
		cartService.updateCart(crt, cartId);
		List<Cart> carts = cartService.cancelCart(cartId);

		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}

}
