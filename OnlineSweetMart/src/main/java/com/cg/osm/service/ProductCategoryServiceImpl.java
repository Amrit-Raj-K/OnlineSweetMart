package com.cg.osm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.ProductCategory;
import com.cg.osm.entity.SweetItem;
import com.cg.osm.exception.ProductCategoryNotFoundException;
import com.cg.osm.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository pc;

	@Override
	public ProductCategory addProductCategory(ProductCategory productcategory) {
		ProductCategory category = pc.saveAndFlush(productcategory);
		return category;
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategory productcategory) throws ProductCategoryNotFoundException {
		ProductCategory product = pc.saveAndFlush(productcategory);
		return product;
	}

	@Override
	public ProductCategory cancelProductCategory(int productcategoryId) throws ProductCategoryNotFoundException {
		Optional<ProductCategory> category = pc.findById(productcategoryId);
		pc.deleteById(productcategoryId);
		return category.get();
	}

	@Override
	public List<ProductCategory> showAllProductCategory() {
		return pc.findAll();
	}

	@Override
	public ProductCategory findCategory(Integer id) {
		// TODO Auto-generated method stub
		Optional<ProductCategory> product = pc.findById(id);
		return product.get();
	}

}
