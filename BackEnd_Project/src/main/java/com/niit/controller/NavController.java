package com.niit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class NavController {
	@RequestMapping("/")
	public ModelAndView getIndexPage() {
		System.out.println("hi");
		ModelAndView modelandview= new ModelAndView("User_Registration");
		return modelandview;
    }


}
