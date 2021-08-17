package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
