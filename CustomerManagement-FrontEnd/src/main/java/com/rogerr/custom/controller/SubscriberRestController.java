package com.rogerr.custom.controller;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rogerr.custom.manager.SubscriberManager;
import com.rogerr.custom.model.SubscriberView;


@RestController
public class SubscriberRestController {

    @Autowired
    private SubscriberManager subscriberManager;

    private static Logger logger = LoggerFactory.getLogger(SubscriberRestController.class);

    
    // -------------------Retrieve All Users--

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        
        return subscriberManager.findAllUsers();
    }

    
    // -------------------Retrieve Single User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        logger.debug("Fetching Subscriber with id " + id);
       
        return subscriberManager.findById(id);
    }

    
    // -------------------Create a User--

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody SubscriberView user) {
        logger.debug("Creating Subscriber " + user.getUsername());
        
        return subscriberManager.saveUser(user);
    }
    

    // ------------------- Update a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody SubscriberView user) {
        logger.debug("Updating Subscriber " + id);

        return subscriberManager.updateUser(user);
    }

    // ------------------- Delete a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        logger.debug("Fetching & deleting subscriber with id " + id);

         subscriberManager.deleteUserById(id);
         return new ResponseEntity<SubscriberView>(HttpStatus.NO_CONTENT);
    }

    
}
