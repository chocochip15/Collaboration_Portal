package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
/*Create User*/
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
			e.printStackTrace();
		}	
		
		

		userdetailsDAO.saveOrUpdate(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	 
	/*Deleting User By ID*/
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<UserDetails> deleteUser(@PathVariable("id") String id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	 
	        UserDetails user = userdetailsDAO.getByID(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
	        }
	 
	        userdetailsDAO.deleteByID(id);
	        return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
	    }
/*Retrieving User By ID*/
	 @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<UserDetails> getUser(@PathVariable("id") String id) {
	        System.out.println("Fetching User with id " + id);
	        UserDetails user = userdetailsDAO.getByID(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	    }
}
