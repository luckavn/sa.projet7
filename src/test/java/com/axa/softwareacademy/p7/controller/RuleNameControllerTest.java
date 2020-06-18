package com.axa.softwareacademy.p7.controller;

import com.axa.softwareacademy.p7.domain.User;
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
public class RuleNameControllerTest {
    User user = new User(1, "Lucas Vannier", "password","lucka", "ADMIN");

    @Autowired
    MockMvc mockmvc;

    @Test
    public void getRuleNameList() throws Exception {
        this.mockmvc.perform(get("/ruleName/list")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addRuleName() throws Exception {
        this.mockmvc.perform(get("/ruleName/add")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyRuleName() throws Exception {
        this.mockmvc.perform(post("/ruleName/update/1")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteRuleName() throws Exception {
        this.mockmvc.perform(get("/ruleName/delete/1")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andDo(MockMvcResultHandlers.print());
    }
}
