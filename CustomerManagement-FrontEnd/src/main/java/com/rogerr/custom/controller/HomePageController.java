package com.rogerr.custom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
	
			@GetMapping("/header")
		public String showHomePage() {
			
			System.out.println("In Home Page");
			
			return "header";
		}
		
}
