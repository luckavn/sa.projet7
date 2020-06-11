package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.domain.Rating;
import com.axa.softwareacademy.p7.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * This controller class (Rating related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class RatingController {
    private static final Logger logger = LogManager.getLogger(RatingController.class);

    @Autowired
    RatingRepository ratingRepository;

    /**
     * This endpoint allows to show rating' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the rating' list
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingRepository.findAll());
        logger.info("Display of rating list successful");
        return "rating/list";
    }

    /**
     * This endpoint allows to display rating' adding form
     * @param rating is the rating object to be added
     */
    @GetMapping("/rating/add") public String addRatingForm(Rating rating) {
        logger.info("Display of rating's adding form");
        logger.info(rating.toString());
        return "rating/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param rating is the Rating object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            model.addAttribute("ratings", ratingRepository.findAll());
            logger.info("rating adding validation successful");
            logger.info(rating.toString());
            return "redirect:/rating/list";
        }
        logger.info("Error on rating adding validation, go back to adding form");
        logger.info(rating.toString());
        return "rating/add";
    }

    /**
     * This endpoint allows to display rating' updating form
     * @param id is the rating' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        logger.info("Rating's updating form display successful");
        logger.info(rating.toString());
        return "rating/update";
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the rating' id that is concerned by update
     * @param rating is the rating object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Rating updating failed, go back to updating form display, has errors");
            logger.info(rating.toString());
            return "rating/update";
        }
        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        logger.info("Rating saved successfully, model updated");
        logger.info(rating.toString());
        return "redirect:/rating/list";
    }

    /**
     * This endpoint deletes a saved rating object
     * @param id is the rating' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        logger.info("Rating deleted successfully, model updated");
        logger.info(rating.toString());
        return "redirect:/rating/list";
    }
}
