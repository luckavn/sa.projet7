package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.domain.RuleName;
import com.axa.softwareacademy.p7.repositories.RuleNameRepository;
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
 * This controller class (Rulename related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class RuleNameController {
    private static final Logger logger = LogManager.getLogger(RuleNameController.class);

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     * This endpoint allows to show rulename' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the rulename' list
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("Display of rulename list successful");
        return "ruleName/list";
    }

    /**
     * This endpoint allows to display rulename' adding form
     * @param bid is the rulename object to be added
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        logger.info("Display of rulename's adding form");
        logger.info(bid.toString());
        return "ruleName/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param ruleName is the rulename object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            model.addAttribute("ruleNames", ruleNameRepository.findAll());
            logger.info("Rulename adding validation successful");
            logger.info(ruleName.toString());
            return "redirect:/ruleName/list";
        }
        logger.info("Error on rulename adding validation, go back to adding form");
        logger.info(ruleName.toString());
        return "ruleName/add";
    }

    /**
     * This endpoint allows to display rulename' updating form
     * @param id is the rulename' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        logger.info("Rulename's updating form display successful");
        logger.info(ruleName.toString());
        return "ruleName/update";
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the rulename' id that is concerned by update
     * @param ruleName is the rulename object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Rulename updating failed, go back to updating form display, has errors");
            logger.info(ruleName.toString());
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("Rulename saved successfully, model updated");
        logger.info(ruleName.toString());
        return "redirect:/ruleName/list";
    }

    /**
     * This endpoint deletes a saved rulename object
     * @param id is the rulename' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rulename Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("Rulename deleted successfully, model updated");
        logger.info(ruleName.toString());
        return "redirect:/ruleName/list";
    }
}
