package com.learning.springbootmongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootmongodb.model.Address;
import com.learning.springbootmongodb.model.Response;
import com.learning.springbootmongodb.model.User;
import com.learning.springbootmongodb.repository.UserRepository;

@RestController
@RequestMapping(path="/users")
public class UserController {     //It's a REST CONTROLLER which will help us to build the END POINTS -APIs-
	
	//Inside this Controller let's build our APIs
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path="/add")
	public ResponseEntity<Response> addUser(@RequestParam String name, @RequestParam String phone, @RequestParam String email, 
			@RequestParam String adrsLine, @RequestParam String city, @RequestParam String state, @RequestParam Integer zipCode){
		
		
		try {
			Address address = new Address(adrsLine, city, state, zipCode);
			User user = new User(null, name, phone, email, address);
			
			userRepository.save(user);
			
			Response response = new Response(101, "User " + name + "saved succesfully", null);
			return new ResponseEntity<Response>(response, HttpStatus.CREATED);
		}
		
		catch(Exception e) {
			
			Response response = new Response(301, "oops! Something went really wrong" + e.getMessage(), null);
			return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
			
		}
		
		
	}
		@GetMapping
		public List<User> getAllUsers(){
			
			return userRepository.findAll();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Response> getUserById(@PathVariable String id) {

			try {
				User user = userRepository.findById(id).orElse(null);

				List<User> users = new ArrayList<User>();
				users.add(user);

				if (user != null) {
					Response response = new Response(200, "User details fetched successfully!!", users);
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				} else {
					Response response = new Response(404, "User details not fetched successfully !!", null);
					return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				Response response = new Response(404, "User details not fetched successfully !!", null);
				return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);

			}
		}
		@PutMapping("/{id}")
		public ResponseEntity<Response> updateUser(@PathVariable String id, @RequestBody User user) {

			User usr = userRepository.findById(id).orElse(null);

			if (usr != null) {
				usr.setName(user.getName());
				usr.setPhone(user.getPhone());
				usr.setEmail(user.getEmail());
				if (user.getAddress() != null) {
					usr.getAddress().setAdrsLine(user.getAddress().getAdrsLine());
					usr.getAddress().setCity(user.getAddress().getCity());
					usr.getAddress().setState(user.getAddress().getState());
					usr.getAddress().setZipCode(user.getAddress().getZipCode());
				}

				userRepository.save(usr);

				Response response = new Response(202, "User details updated successfully!!", null);
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			} else {
				Response response = new Response(404, "User details not found!!", null);
				return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
			}

		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Response> deleteUser(@PathVariable String id) {

			User usr = userRepository.findById(id).orElse(null);

			if (usr != null) {
				userRepository.deleteById(id);
				Response response = new Response(202, "User details deleted successfully!!", null);
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);

			} else {
				Response response = new Response(404, "User details not found!!", null);
				return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);

			}
		}
}
		

