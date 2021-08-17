package com.cg.osm.service;

import java.util.List;
import com.cg.osm.entity.ProductCategory;
import com.cg.osm.exception.ProductCategoryNotFoundException;


public interface ProductCategoryService  {

	public ProductCategory addProductCategory(ProductCategory productcategory);
	public ProductCategory updateProductCategory(ProductCategory productcategory) throws ProductCategoryNotFoundException;
	public ProductCategory cancelProductCategory(int productcategoryId) throws ProductCategoryNotFoundException;
	public List<ProductCategory> showAllProductCategory();
	
}