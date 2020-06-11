package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.domain.CurvePoint;
import com.axa.softwareacademy.p7.repositories.CurvePointRepository;
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
import java.time.LocalDateTime;

/**
 * This controller class (Curvepoint related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class CurveController {
    private LocalDateTime localDateTime;
    private static final Logger logger = LogManager.getLogger(CurveController.class);


    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * This endpoint allows to show curvepoint' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the curvepoint' list
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("Display of curvepoint's list successful");
        return "curvePoint/list";
    }

    /**
     * This endpoint allows to display curvepoint' adding form
     * @param bid is the curvepoint object to be added
     */
    @GetMapping("/curvePoint/add") public String addCurvePoint(CurvePoint bid) {
        logger.info("Display of curvepoint's adding form");
        logger.info(bid.toString());
        return "curvePoint/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param curvePoint is the Curvepoint object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            localDateTime = LocalDateTime.now();
            curvePoint.setAsOfDate(localDateTime);
            curvePoint.setCreationDate(localDateTime);
            curvePointRepository.save(curvePoint);
            model.addAttribute("curvePoints", curvePointRepository.findAll());
            logger.info("curvepoint adding validation successful");
            logger.info(curvePoint.toString());
            return "redirect:/curvePoint/list";
        }
        logger.info("Error on curvepoint adding validation, go back to adding form");
        logger.info(curvePoint.toString());
        return "curvePoint/add";
    }

    /**
     * This endpoint allows to display curvepoint' updating form
     * @param id is the curvepoint' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        logger.info("Bidlist's updating form display successful");
        logger.info(curvePoint.toString());
        return "curvePoint/update";
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the curvepoint' id that is concerned by update
     * @param curvePoint is the curvepoint object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Curvepoint updating failed, go back to updating form display, has errors");
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("Curvepoint saved successfully, model updated");
        logger.info(curvePoint.toString());
        return "redirect:/curvePoint/list";
    }

    /**
     * This endpoint deletes a saved curvepoint object
     * @param id is the curvepoint' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("Curvepoint deleted successfully, model updated");
        logger.info(curvePoint.toString());
        return "redirect:/curvePoint/list";
    }
}
