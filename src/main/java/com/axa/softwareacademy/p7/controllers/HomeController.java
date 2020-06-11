package com.axa.softwareacademy.p7.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller class (Home related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class HomeController {
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	/**
	 * This endpoint display homepage for unlogged users
	 * @param model is the public interface model, attributes can be added, model can be accessed
	 */
	@RequestMapping("/")
	public String home(Model model) {
		logger.info("Display of homepage successful");
		return "home";
	}

	/**
	 * This endpoint get unlogged user to login form and redirect to bidlist' list
	 * @param model is the public interface model, attributes can be added, model can be accessed
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("Admin connected, display of default page (Bidlist' list");
		return "redirect:/bidList/list";
	}


}
