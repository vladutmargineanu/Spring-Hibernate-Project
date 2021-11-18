package com.luv2code.deploydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	@RequestMapping("/test")
	public String sayHello() {
		return "hello";
	}
	
}