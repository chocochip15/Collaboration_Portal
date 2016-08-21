package com.niit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class NavController {
	@RequestMapping("/")
	public ModelAndView getIndexPage() {
		ModelAndView modelandview= new ModelAndView("UserRegistration");
		return modelandview;
    }


}
