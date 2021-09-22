package com.cg.osm.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Customer;
import com.cg.osm.repository.CustomerRespository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRespository repo;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		Customer c = repo.findByName(s);

		return new User(c.getUsername(), c.getPassword(), new ArrayList<>());
	}
}