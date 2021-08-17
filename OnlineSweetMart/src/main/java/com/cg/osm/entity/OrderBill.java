package com.cg.osm.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "orderbill")
public class OrderBill { // orderBill entity class

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderbill_seq")
	@SequenceGenerator(name = "orderbill_seq", sequenceName="orderbill_seq", allocationSize=1)
	@Column(name = "orderbillid")
	private Integer orderBillId;
	@JsonFormat(pattern = "dd-MMM-yyyy")
	@Column(name = "createddate")
	private LocalDate createdDate;
	@Column(name = "totalcost")
	private float totalCost;
	@OneToOne(targetEntity = SweetOrder.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "sweetorderid")
	private SweetOrder listSweetOrder;
	
	

	public OrderBill(Integer orderBillId, LocalDate createdDate, float totalCost, SweetOrder listSweetOrder) {
		super();
		this.orderBillId = orderBillId;
		this.createdDate = createdDate;
		this.totalCost = totalCost;
		this.listSweetOrder = listSweetOrder;
	}


	public OrderBill() {
		super();
	}
	

	public Integer getOrderBillId() {
		return orderBillId;
	}

	public void setOrderBillId(Integer orderBillId) {
		this.orderBillId = orderBillId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public SweetOrder getListSweetOrder() {
		return listSweetOrder;
	}

	public void setListSweetOrder(SweetOrder listSweetOrder) {
		this.listSweetOrder = listSweetOrder;
	}


	@Override
	public String toString() {
		return "OrderBill [orderBillId=" + orderBillId + ", createdDate=" + createdDate + ", totalCost=" + totalCost
				+ ", listSweetOrder=" + listSweetOrder + "]";
	}

	

}