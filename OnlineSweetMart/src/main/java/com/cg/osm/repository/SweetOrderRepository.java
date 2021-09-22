package com.cg.osm.repository;



import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.SweetOrder;

/**
 * 
 * @author User
 *
 */
@Repository
public interface SweetOrderRepository extends JpaRepository<SweetOrder, Integer> {

	@Query(value = "SELECT max(sweetOrderId) FROM SweetOrder")
	public BigDecimal max();
}
