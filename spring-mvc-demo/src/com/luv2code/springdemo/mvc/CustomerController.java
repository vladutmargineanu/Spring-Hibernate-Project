package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// add an InitBinder - to convert trim input strings
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		// remove leading and trailing whitespace
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		// resolve issue for our validation
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {

		theModel.addAttribute("customer", new Customer());

		return "customer-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

		System.out.println("Last name: |" + theCustomer.getLastName() + "|");

		if (theBindingResult.hasErrors()) {
			return "customer-form";
		} else {
			return "customer-confirmation";
		}
	}
}
