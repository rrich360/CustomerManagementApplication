package com.provider.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.provider.springboot.model.SubscriberView;
import com.provider.springboot.service.SubscriberService;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
	
	@Autowired
	private SubscriberService userService;

	
	//-------------------Create a User-------------------------------------------------------
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody SubscriberView user) {
        if (userService.isUserExist(user)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

	
	//-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        SubscriberView user = SubscriberView.convert(userService.findById(id));
        if (user == null) {
            return new ResponseEntity<>("No Subscriber With ID: " + id + " Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
  //-------------------Retrieve All Subscribers--------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
    	List<SubscriberView> usersList = 
				userService.findAllUsers()
				.stream().map(SubscriberView :: convert)
				.collect(Collectors.toList());
        if(usersList.isEmpty()){
            return new ResponseEntity<List<SubscriberView>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SubscriberView>>(usersList, HttpStatus.OK);
    }
 
	
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody SubscriberView user) {
    	userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
	
  //------------------- Delete a User --------------------------------------------------------
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
    	userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
