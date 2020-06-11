package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller class (Login related actions) is aimed to expose data related methods to front-end
 */
@Controller
@RequestMapping("app")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * This endpoint is called when login is necessary to perform next navigation action
     */
    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        logger.info("Display login page successful");
        return mav;
    }

    /**
     * This endpoint is called when logged user wants to display user list
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        logger.info("Get all users from database successful");
        return mav;
    }

    /**
     * This endpoint is called when a wrong user/password is informed at login
     * @return error
     */
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        logger.info("Login error for user, unauthorized");
        return mav;
    }
}
