package com.cg.osm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	@Column(name = "cartid")
	private int cartId;
	@Column(name = "grandtotal")
	private double grandTotal;
	@Column(name = "total")
	private double total;
	@Column(name = "sweetitemcount")
	private int sweetItemCount;
	@OneToOne(targetEntity = SweetItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "sweetitemid")
	private SweetItem sweetItems;

	
	public Cart() {
		super();
	}

	public Cart(int cartId, double grandTotal, double total, int sweetItemCount) {
		super();
		this.cartId = cartId;
		this.grandTotal = grandTotal;
		this.total = total;
		this.sweetItemCount = sweetItemCount;
	}

	public SweetItem getSweetItems() {
		return sweetItems;
	}

	public void setSweetItems(SweetItem sweetItems) {
		this.sweetItems = sweetItems;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getSweetItemCount() {
		return sweetItemCount;
	}

	public void setSweetItemCount(int sweetItemCount) {
		this.sweetItemCount = sweetItemCount;
	}

}