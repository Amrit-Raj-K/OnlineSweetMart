package com.cg.osm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.ProductCategory;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}