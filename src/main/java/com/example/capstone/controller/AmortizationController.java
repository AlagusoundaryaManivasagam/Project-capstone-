package com.example.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.capstone.form.CreateAmortizationFormBean;
import com.example.capstone.form.CreateBudgetFormBean;

import jakarta.validation.Valid;

@Controller
public class AmortizationController {
	private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);
	@GetMapping("/amortization/ac")
	public ModelAndView display(){
		ModelAndView response = new ModelAndView();
        response.setViewName("amortization/ac");
        
		return response;
	}
	
	@PostMapping("/amortization/calculate")
	public ModelAndView calculate(@Valid CreateAmortizationFormBean form, BindingResult bindingResult){
		ModelAndView response = new ModelAndView();
        response.setViewName("amortization/ac");
		if ( bindingResult.hasErrors() ) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOG.debug(error.toString());
			}

			response.setViewName("amortization/ac");
			response.addObject("bindingResult", bindingResult);
			response.addObject("form", form);

		}
		return response;
	}
	
	}
	
	

