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
public class RatingControllerTest {
    @Autowired
    MockMvc mockmvc;

    @Test
    public void getRatingList() throws Exception {
        this.mockmvc.perform(get("/rating/list"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addRating() throws Exception {
        this.mockmvc.perform(get("/rating/add"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyRating() throws Exception {
        this.mockmvc.perform(post("/rating/update/"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/app/error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteRating() throws Exception {
        this.mockmvc.perform(get("/rating/delete/"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }
}
