package com.cg.osm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sweetitem")
public class SweetItem {//SweetItem entity class

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweeti_seq")
	@SequenceGenerator(name = "sweeti_seq", sequenceName="sweeti_seq", allocationSize=1)
	@Column(name = "sweetitemid")
	private int sweetItemId;       //Primary key of sweet item with ID
	@Size(min = 3, message = "SweetItem Name must have at least 3 characters")
	@Pattern(regexp ="[a-zA-Z]*+[\\s]+[a-zA-Z]*")
	@Column(name = "sweetitemname")
	private String sweetItemName; //Name of the Sweet Item
	@Column(name = "price")
	private double price;         // Price of the Sweet Item
	@Column(name = "image")
	private String image; 
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	@ManyToOne(targetEntity = ProductCategory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryid")
	private ProductCategory category; // Mapping of Category with Sweet Item
	@Transient                         // To prevent it from adding to Oracle Database
	boolean available = true;          //To confirm the availability of the Sweet Item in front end
	
	
public SweetItem() {                  // Empty constructor 
	
}

	public SweetItem(int sweetItemId, String sweetItemName, double price,boolean available) { //Constructor excluding the Category
		super();
		this.sweetItemId = sweetItemId;
		this.sweetItemName = sweetItemName;
		this.price = price;
		this.available = available;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public int getSweetItemId() {
		return sweetItemId;
	}

	public void setSweetItemId(int sweetItemId) {
		this.sweetItemId = sweetItemId;
	}

	public String getSweetItemName() {
		return sweetItemName;
	}

	public void setSweetItemName(String sweetItemName) {
		this.sweetItemName = sweetItemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
public String toString() {
	return "SweetItem [sweetItemId=" + sweetItemId + ", sweetItemName=" + sweetItemName + ", price=" + price
			+ ", available=" + available + "]";
}
}
