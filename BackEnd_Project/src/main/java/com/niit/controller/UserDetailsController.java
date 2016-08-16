package com.niit.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.bean.UserDetails;

import com.niit.dao.UserDetailsDAO;

@RestController

public class UserDetailsController {
	@Autowired
	UserDetailsDAO userdetailsDAO;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody String str, UserDetails userDetails,
			UriComponentsBuilder ucBuilder) {
		/* System.out.println("Creating User " + user.getName()); */

		/*
		 * if (userService.isUserExist(user)) {
		 * System.out.println("A User with name " + user.getName() +
		 * " already exist"); return new
		 * ResponseEntity<Void>(HttpStatus.CONFLICT); }
		 */
		System.out.println(str);
		UserDetails user=null;
		try {
			user = (UserDetails)new ObjectMapper().readValue(str,UserDetails.class);	
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		

		userdetailsDAO.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}
