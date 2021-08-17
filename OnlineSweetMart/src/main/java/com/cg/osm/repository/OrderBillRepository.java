package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.OrderBill;


@Repository
public interface OrderBillRepository extends JpaRepository<OrderBill,Integer>{ // orderBill repository 
	
}
