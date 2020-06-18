package com.axa.softwareacademy.p7.controller;

import com.axa.softwareacademy.p7.domain.User;
import com.axa.softwareacademy.p7.services.MyAppUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Sql(scripts = "classpath:data-test.sql")
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTest {
    User user = new User(1, "Lucas Vannier", "password","lucka", "ADMIN");

    @Autowired
    MockMvc mockmvc;

    @Autowired
    MyAppUserDetailsService myAppUserDetailsService;

    @BeforeEach
    private void setUpForTest() throws Exception {
        myAppUserDetailsService = new MyAppUserDetailsService();
    }

    @Test
    public void getTradeList() throws Exception {
        this.mockmvc.perform(get("/trade/list")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addTrade() throws Exception {
        this.mockmvc.perform(get("/trade/add")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyTrade() throws Exception {
        this.mockmvc.perform(post("/trade/update/1")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrade() throws Exception {
        this.mockmvc.perform(get("/trade/delete/1")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andDo(MockMvcResultHandlers.print());
    }
}
