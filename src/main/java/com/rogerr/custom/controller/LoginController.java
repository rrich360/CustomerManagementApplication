package com.rogerr.custom.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String processLogin(@Valid @ModelAttribute("Login") Login user, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			return "user_login";
		} else {
		
		// login processing successful
		return "header";
		}
	}
	
	
}
