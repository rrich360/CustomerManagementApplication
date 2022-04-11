package com.rogerr.custom.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.rogerr.custom.model.Login;

@Controller
@RequestMapping("/")
public class LoginController {
	 
	@GetMapping("/")
	public String displayLogin(Model model) {
		
		Login user=new Login();
		model.addAttribute("Login",user);
		
		return"user_login";
	}
	
	
	@PostMapping("/")
	//@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid Model model, @ModelAttribute("Login") Login user, Errors errors) {
		if (user != null && user.getUsername() != null & user.getPassword() != null) {
			if (user.getUsername().equals("rrichar1") && user.getPassword().equals("hearts246")) {
				model.addAttribute("msg", user.getUsername());
				return "success";
			} else {
				model.addAttribute("error", "Invalid Details");
				return "user_login";
			}
		} else if(errors.hasErrors()) {
			return "user_login";
		} else {
			model.addAttribute("error", "Please enter Details");
			return "user_login";
		}
	}
	
  }

