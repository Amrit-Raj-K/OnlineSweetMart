package com.cg.osm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.SweetItem;

@Repository
public interface SweetItemRepo extends JpaRepository<SweetItem,Integer> {// SweetItem Repository interface

}
