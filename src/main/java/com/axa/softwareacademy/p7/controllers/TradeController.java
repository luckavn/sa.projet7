package com.axa.softwareacademy.p7.controllers;

import com.axa.softwareacademy.p7.domain.Trade;
import com.axa.softwareacademy.p7.repositories.TradeRepository;
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
 * This controller class (Trade related actions) is aimed to expose data related methods to front-end
 */
@Controller
public class TradeController {
    private static final Logger logger = LogManager.getLogger(TradeController.class);
    public LocalDateTime localDateTime;

    @Autowired
    TradeRepository tradeRepository;

    /**
     * This endpoint allows to show trade' list to users
     * @param model is the public interface model, attributes can be added, model can be accessed
     * @return the trade' list
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("Display of trade list successful");
        return "trade/list";
    }

    /**
     * This endpoint allows to display trade' adding form
     * @param bid is the trade object to be added
     */
    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {
        logger.info("Display of trade's adding form");
        logger.info(bid.toString());
        return "trade/add";
    }

    /**
     * This endpoint validates the info informed within adding template
     * @param trade is the trade object to be added
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
            if (!result.hasErrors()) {
                localDateTime = LocalDateTime.now();
                trade.setCreationDate(localDateTime);
                trade.setTradeDate(localDateTime);
                tradeRepository.save(trade);
                model.addAttribute("trades", tradeRepository.findAll());
                logger.info("Trade adding validation successful");
                logger.info(trade.toString());
                return "redirect:/trade/list";
            }
        logger.info("Error on trade adding validation, go back to adding form");
        logger.info(trade.toString());
        return "trade/add";
    }

    /**
     * This endpoint allows to display trade' updating form
     * @param id is the trade' id that is concerned by update
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        logger.info("Trade's updating form display successful");
        logger.info(trade.toString());
        return "trade/update";
    }

    /**
     * This endpoint validates the info informed within updating template
     * @param id is the trade' id that is concerned by update
     * @param trade is the trade object to be updated
     * @param result is the back-end technical result
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Trade updating failed, go back to updating form display, has errors");
            logger.info(trade.toString());
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("Trade saved successfully, model updated");
        logger.info(trade.toString());
        return "redirect:/trade/list";
    }

    /**
     * This endpoint deletes a saved trade object
     * @param id is the trade' id that is concerned by deleting
     * @param model is the public interface model, attributes can be added, model can be accessed
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("Trade deleted successfully, model updated");
        logger.info(trade.toString());
        return "redirect:/trade/list";
    }
}
