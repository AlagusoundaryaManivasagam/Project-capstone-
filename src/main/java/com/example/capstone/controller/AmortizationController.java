package com.example.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.capstone.form.CreateAmortizationFormBean;
import com.example.capstone.form.CreateBudgetFormBean;

import jakarta.validation.Valid;

@Controller
public class AmortizationController {

	@GetMapping("/amortization/ac")
	public ModelAndView display(){
		ModelAndView response = new ModelAndView();
        response.setViewName("amortization/ac");
        
		return response;
	}
	
	@PostMapping("/amortization/calculate")
	public ModelAndView calculate(@Valid CreateAmortizationFormBean form){
		ModelAndView response = new ModelAndView();
        response.setViewName("amortization/ac");
        
		return response;
	}
	
	}
	
	

