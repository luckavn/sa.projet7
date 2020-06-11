package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.config.SecurityConfig;
import com.axa.softwareacademy.p7.domain.User;
import com.axa.softwareacademy.p7.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * This controller class (User related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    SecurityConfig securityConfig;

    /**
     * This endpoint allows to show user' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the user' list
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        logger.info("Users found successfully, model updated");
        return "user/list";
    }

    /**
     * This endpoint allows to display user' adding form
     * @param bid is the user object to be added
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        logger.info("Display of user's adding form");
        logger.info(bid.toString());
        return "user/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param user is the user object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) throws Exception {
        if (!result.hasErrors()) {
            if (securityConfig.isPasswordValid(user.getPassword())) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                model.addAttribute("users", userRepository.findAll());
                logger.info("User adding validation successful");
                logger.info(user.toString());
                return "redirect:/user/list";
            } else {
                throw new Exception("Password does not match com.axa.softwareacademy.p7.security policy");
            }
        }
        logger.info("Error on user adding validation, go back to adding form");
        logger.info(user.toString());
        return "user/add";
    }

    /**
     * This endpoint allows to display user' updating form
     * @param id is the user' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        logger.info("User's updating form display successful");
        logger.info(user.toString());
        return "user/update";
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the user' id that is concerned by update
     * @param user is the user object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("User updating failed, go back to updating form display, has errors");
            logger.info(user.toString());
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("User saved successfully, model updated");
        logger.info(user.toString());
        return "redirect:/user/list";
    }

    /**
     * This endpoint deletes a saved user object
     * @param id is the user' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("User deleted successfully, model updated");
        logger.info(user.toString());
        return "redirect:/user/list";
    }
}
