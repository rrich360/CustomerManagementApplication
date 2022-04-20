package com.rogerr.custom.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.rogerr.custom.model.Subscriber;
import com.rogerr.custom.service.UserService;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    // -------------------Retrieve All Users--

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        List<Subscriber> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<Subscriber>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Subscriber>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.debug("Fetching User with id " + id);
        Subscriber user = userService.findById(id);
        if (user == null) {
            logger.debug("User with id " + id + " not found");
            return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Subscriber>(user, HttpStatus.OK);
    }

    // -------------------Create a User--

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Subscriber user, UriComponentsBuilder ucBuilder) {
        logger.debug("Creating User " + user.getUsername());

        if (userService.isUserExist(user)) {
            logger.debug("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Subscriber user) {
        logger.debug("Updating User " + id);

        Subscriber currentUser = userService.findById(id);

        if (currentUser == null) {
            logger.debug("User with id " + id + " not found");
            return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());

        userService.updateUser(currentUser);
        return new ResponseEntity<Subscriber>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.debug("Fetching & Deleting User with id " + id);

        Subscriber user = userService.findById(id);
        if (user == null) {
            logger.debug("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users--

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUsers() {
        logger.debug("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<Subscriber>(HttpStatus.NO_CONTENT);
    }

}
