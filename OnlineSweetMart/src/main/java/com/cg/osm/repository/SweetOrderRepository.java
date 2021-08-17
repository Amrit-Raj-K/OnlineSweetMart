package com.cg.osm.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.cg.osm.entity.SweetOrder;

/**
 * 
 * @author User
 *
 */
@Repository
public interface SweetOrderRepository extends JpaRepository<SweetOrder, Integer> {

}
