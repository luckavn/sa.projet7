package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.domain.BidList;
import com.axa.softwareacademy.p7.repositories.BidListRepository;
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
 * This controller class (Bidlist related actions) is aimed to expose data related methods to front-end
 */
@SuppressWarnings("PlaceholderCountMatchesArgumentCount")
@Controller
public class BidListController {
    private static final Logger logger = LogManager.getLogger(BidListController.class);

    @Autowired
    BidListRepository bidListRepository;

    /**
     * This endpoint allows to show bidlist' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the bidlist' list
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("Display of bidlist's list successful");
        return "bidList/list";
    }

    /**
     * This endpoint allows to display bidlist' adding form
     * @param bid is the bidlist object to be added
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("Display of bidlist's adding form");
        logger.info(bid.toString());
        return "bidList/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param bidList is the bidlist object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            LocalDateTime
                    localDateTime =
                    LocalDateTime.now();
            bidList.setBidListDate(localDateTime);
            bidList.setCreationDate(localDateTime);
            bidListRepository.save(bidList);
            model.addAttribute("bidLists", bidListRepository.findAll());
            logger.info("bidlist adding validation successful");
            logger.info(bidList.toString());
            return "redirect:/bidList/list";
        }
        logger.info("Error on bidlist adding validation, go back to adding form");
        logger.info(bidList.toString());
        return "bidList/add";
    }

    /**
     * This endpoint allows to display bidlist' updating form
     * @param id is the bidlist' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        if(bidListRepository.findByBidListId(id) != null) {
            BidList bidList = bidListRepository.findByBidListId(id);
            model.addAttribute("bidList", bidList);
            logger.info("Bidlist's updating form display successful");
            logger.info(bidList.toString());
            return "bidList/update";
        } else {
            throw new Exception("BidlistId not found in database");
        }
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the bidlist' id that is concerned by update
     * @param bidList is the bidlist object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Bidlist updating failed, go back to updating form display, has errors");
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("Bidlist saved successfuly, model updated");
        logger.info(bidList.toString());
        return "redirect:/bidList/list";
    }

    /**
     * This endpoint deletes a saved bidlist object
     * @param id is the bidlist' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("Bidlist deleted successfuly, model updated");
        logger.info(bidList.toString());
        return "redirect:/bidList/list";
    }
}


