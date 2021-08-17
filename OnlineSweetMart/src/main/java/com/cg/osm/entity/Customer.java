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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * The class Customer represents a generic person
 * 
 * @author Nazia
 *
 */

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
	@Column(name = "customerid")
	private int customerId;

	@Size(min = 3, message = "Customer Name must have at least 2 characters")
	@NotBlank(message = "Customer Name cannot be blank")
	@Column(name="username")
	private String username;

	@NotBlank(message = "Password cannot be blank")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Address cannot be blank")
	@Column(name = "address")
	private String address;
	

	@OneToOne(targetEntity = Cart.class, cascade = CascadeType.ALL)

	@JoinColumn(name = "cartid")
	private Cart cart;

	@Transient
	private String passwordConfirm;

	@Column(name = "type")
	private String type;

	/**
	 * Customer default constructor
	 */
	public Customer() {

	}

	/**
	 * Customer constructor with all fields as parameter
	 * @param customerId the customer's id
	 * @param customerName the customer's name
	 * @param password the customer's password
	 * @param address the customer's address
	 * @param type the customer's type
	 */

	public Customer(int customerId, String username, String password, String address, String type) {
		super();
		this.customerId = customerId;
		this.username=username;
		this.password = password;
		this.address = address;

		this.type = type;

	}

	/**
	 * get the customer's role
	 * @return the type
	 */

	public String getType() {
		return type;
	}
	
	/**
	 * type setter method
	 * @param type the customer's type 
	 */

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * get the customer's password
	 * @return the password
	 */
	

	public String getPassword() {
		return password;
	}
	
	/**
	 * password setter method
	 * @param password  the customer's password
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * get the customer's confirm password
	 * @return passwordConfirm
	 */

	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	/**
	 * passwordConfirm setter method
	 * @param passwordConfirm the customer's confirm password
	 */

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	/**
	 * get customerName
	 * @return name 
	 */

	
	
	/**
	 * customerName setter method
	 * @param customerName the customer's name
	 */

	
	
	/**
	 * get customer's address
	 * @return address
	 */

	public String getAddress() {
		return address;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * set customer's address
	 * @param address the customer's address
	 */
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * get customerId
	 * @return customerId
	 */

	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 *customerId setter method
	 * @param customerId the customer's id
	 */

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * get cart
	 * @return cart
	 */

	public Cart getCart() {
		return cart;
	}
	
	/**
	 * cart setter method
	 * 
	 * @param cart the customer's cart
	 */

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	/**
	 * toString method for displaying 
	 */

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", username=" + username + ", password=" + password
				+ ", address=" + address + ", cart=" + cart + ", passwordConfirm=" + passwordConfirm + ", type=" + type
				+ "]";
	}

}