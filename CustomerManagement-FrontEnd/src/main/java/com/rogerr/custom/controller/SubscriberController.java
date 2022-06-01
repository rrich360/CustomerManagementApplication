package com.rogerr.custom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/UserManagement")
public class SubscriberController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getSubscribersPage() {
	        return "UserManagement";
	    }

}