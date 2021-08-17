package com.cg.osm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.ProductCategory;
import com.cg.osm.exception.ProductCategoryNotFoundException;
import com.cg.osm.service.ProductCategoryService;

@RestController
@RequestMapping("/cat")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService service;
	//localhost:8091/RestOrder/cat/save
	@PostMapping("/save")
	public ResponseEntity<ProductCategory> saveOrder(@RequestBody ProductCategory productcategory) {
		ProductCategory category = service.addProductCategory(productcategory);
		if (category == null) {
			return new ResponseEntity("Product Category couldnt be saved", HttpStatus.OK);
		}
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);

	}
	//localhost:8091/RestOrder/cat/update
	@PutMapping("/update")
	public ResponseEntity<ProductCategory> updateOrder(@RequestBody ProductCategory productcategory) throws ProductCategoryNotFoundException {
		ProductCategory category = service.updateProductCategory(productcategory);
		if (category == null) {
			return new ResponseEntity("Sorry,productcategory could not be updated", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
	}
	//localhost:8091/RestOrder/cat/delete
	@DeleteMapping("/delete/{productcategoryId}")
	public ResponseEntity<ProductCategory> deleteOrder(@PathVariable("productcategoryId") Integer productcategoryId) throws ProductCategoryNotFoundException {
		ProductCategory category = service.cancelProductCategory(productcategoryId);
		if (category == null) {
			return new ResponseEntity("Sorry!,productcategory is not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
	}
    //localhost:8091/RestOrder/cat/get
	@GetMapping("/get")
	public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
		List<ProductCategory> productcategory = service.showAllProductCategory();
		if (productcategory.isEmpty()) {
			return new ResponseEntity("Sorry!,productcategory is  not available", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<ProductCategory>>(productcategory, HttpStatus.OK);
	}

}