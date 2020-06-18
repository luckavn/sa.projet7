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
public class UserControllerTest {
    User user = new User(1, "Lucas Vannier", "password","lucka", "ADMIN");

    @Autowired
    MockMvc mockmvc;

    @Test
    public void getUserList() throws Exception {
        this.mockmvc.perform(get("/user/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addUser() throws Exception {
        this.mockmvc.perform(get("/user/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyUser() throws Exception {
        this.mockmvc.perform(post("/user/update/1")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockmvc.perform(get("/user/delete/2")
                .param("password", user.getPassword()).param("fullname", user.getFullname())
                .param("role", user.getRole()).param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andDo(MockMvcResultHandlers.print());
    }
}
