package com.cg.osm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.osm.entity.ProductCategory;
import com.cg.osm.repository.ProductCategoryRepository;
import com.cg.osm.service.ProductCategoryService;

@SpringBootTest
class ProductServiceImplTest {
	@Autowired
	private ProductCategoryService productcategoryService;

	@MockBean
	private ProductCategoryRepository productRepository;

	@BeforeEach
	void setUp() throws Exception {
		ProductCategory product = new ProductCategory();
		
		product.setName("laddoo");
		product.setDescription("laddoo is a sweet item ");
		

	}

	
	@Test
	@DisplayName("fetch all product functionality")
	void fetchAllTest() {
		when(productRepository.findAll()).thenReturn(Stream
				.of(new ProductCategory(),
						new ProductCategory()).collect(Collectors.toList()));
		assertEquals(2, productcategoryService.showAllProductCategory().size());
	}
	
	

}