package com.cg.osm.entity;

import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author User
 *
 */
@Entity
@Table(name = "sweetorder")
public class SweetOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweeto_seq")
	@SequenceGenerator(name = "sweeto_seq", sequenceName="sweeto_seq", allocationSize=1)
	@Column(name = "sweetorderid")
	private Integer sweetOrderId;
	@JsonFormat(pattern = "dd-MMM-yyyy")
	@Column(name = "createddate")
	private LocalDate createdDate;
	@Column(name = "itemid")
	private Integer itemId;

	public SweetOrder(int i, String string, int j) {
		// TODO Auto-generated constructor stub
	}

	public SweetOrder() {
		super();
	}

	public SweetOrder(int i, LocalDate date) {
		// TODO Auto-generated constructor stub
		this.sweetOrderId=i;
		this.createdDate=date;
	}

	@PrePersist
	private void onCreate() {
		createdDate = LocalDate.now();
	}

	

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private Customer customers;

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	public Integer getSweetOrderId() {
		return sweetOrderId;
	}

	public void setSweetOrderId(Integer sweetOrderId) {
		this.sweetOrderId = sweetOrderId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

}