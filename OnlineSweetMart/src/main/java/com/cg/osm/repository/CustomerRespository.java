package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Customer;

;




@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer> {

	@Query("SELECT c FROM Customer c WHERE c.username=:uname and c.password=:pname")
	public Customer checkLogin(@Param("uname") String uname, @Param("pname") String pname);

	@Query("SELECT c FROM Customer c WHERE c.username=:uname ")
	public Customer findByName(@Param("uname") String uname);
}
