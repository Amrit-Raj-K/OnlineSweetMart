package com.cg.osm.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Cart;
import com.cg.osm.exception.CartNotFoundException;
import com.cg.osm.repository.CartRepository;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<Cart> addCart(Cart cart) {

		cartRepo.saveAndFlush(cart);
		return cartRepo.findAll();
	}

	@Override
	public List<Cart> updateCart(Cart cart, Integer id) throws CartNotFoundException {

		if (!cartRepo.findById(id).isEmpty())
			cartRepo.saveAndFlush(cart);
		return cartRepo.findAll();
	}

	@Override
	public List<Cart> cancelCart(int cartId) throws CartNotFoundException {

		if (cartRepo.findById(cartId).isEmpty())
			return Collections.emptyList();
		else
			cartRepo.deleteById(cartId);
		return cartRepo.findAll();
	}

	@Override
	public List<Cart> showAllCarts() {

		return cartRepo.findAll();
	}

	@Override
	public Cart showAllCarts(int cartId) {

		Optional<Cart> order = cartRepo.findById(cartId);
		return order.get();
	}

	@Override
	public Cart showById(int cartId) throws CartNotFoundException {

		Optional<Cart> carts = Optional.ofNullable(cartRepo.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cannot update a non existing value")));
		return carts.get();
	}

}
