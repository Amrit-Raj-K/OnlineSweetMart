package com.cg.osm.service;

import java.util.List;

import com.cg.osm.entity.Cart;
import com.cg.osm.exception.CartNotFoundException;

public interface CartService {
	public List<Cart> addCart(Cart cart);

	public List<Cart> cancelCart(int cartId) throws CartNotFoundException;

	public List<Cart> showAllCarts();

	public Cart showAllCarts(int cartdId);

	public Cart showById(int cartId) throws CartNotFoundException;

	List<Cart> updateCart(Cart cart, Integer id) throws CartNotFoundException;
}
