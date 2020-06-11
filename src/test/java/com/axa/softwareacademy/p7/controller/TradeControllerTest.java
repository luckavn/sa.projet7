package com.axa.softwareacademy.p7.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
@WithMockUser
public class TradeControllerTest {
    @Autowired
    MockMvc mockmvc;

    @Test
    public void getTradeList() throws Exception {
        this.mockmvc.perform(get("/trade/list"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addTrade() throws Exception {
        this.mockmvc.perform(get("/trade/add"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyTrade() throws Exception {
        this.mockmvc.perform(post("/trade/update/"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrade() throws Exception {
        this.mockmvc.perform(get("/trade/delete/"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }
}
