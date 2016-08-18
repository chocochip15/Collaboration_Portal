package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.bean.UserDetails;
import com.niit.dao.UserDetailsDAO;

@RestController

public class UserDetailsController {
	@Autowired
	UserDetailsDAO userdetailsDAO;
/*Create a User*/
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestParam("id") String id ,@RequestBody  UserDetails userDetails,
			UriComponentsBuilder ucBuilder) {
		 System.out.println("Creating User " + userDetails.getName());

	
		  if (userdetailsDAO.getByID(id)!=null) {
		  System.out.println("A User with ID " + userDetails.getId() +
		  " already exist"); return new
		  ResponseEntity<Void>(HttpStatus.CONFLICT); }
		 
		/*System.out.println(str);
		UserDetails user=null;
		try {
			user = (UserDetails)new ObjectMapper().readValue(str,UserDetails.class);	
		} catch (Exception e) {
			e.printStackTrace();
		}	*/
		
		

		userdetailsDAO.saveOrUpdate(userDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(userDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/*Delete All Users
	@RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public ResponseEntity<UserDetails> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userdetailsDAO.();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
}*/
	 
	/*Deleting User By ID*/
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
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
	/*Retrieve all users*/
	  @RequestMapping(value = "/users/", method = RequestMethod.GET)
	    public ResponseEntity<List<UserDetails>> listAllUsers() {
	        List<UserDetails> users = userdetailsDAO.list();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
	    }
/*Retrieving User By ID*/
	 @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<UserDetails> getUser(@PathVariable("id") String id) {
	        System.out.println("Fetching User with id " + id);
	        UserDetails user = userdetailsDAO.getByID(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	    }
	 /*Update existing records in the table*/
	 @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<UserDetails> updateUser(@PathVariable("id") String id, @RequestBody UserDetails userdetails) {
	        System.out.println("Updating User " + id);
	         
	        UserDetails currentUser = userdetailsDAO.getByID(id);
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
	        }
	 
	      
	         
	        userdetailsDAO.saveOrUpdate(currentUser);
	        return new ResponseEntity<UserDetails>(currentUser, HttpStatus.OK);
	    }
}
