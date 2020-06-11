
package com.axa.softwareacademy.p7.services;

import com.axa.softwareacademy.p7.domain.User;
import com.axa.softwareacademy.p7.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MyAppUserDetailsServiceTest {
    private User u1;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private UserRepository userRepository;

    private MyAppUserDetailsService testedMyAppUserDetailsService;

    @BeforeEach
    private void setUp() {
        u1 = new User(0, "Lucka", "Ikomi_azafr91", "Lucas Vannier", "USER");
        testedMyAppUserDetailsService = context.getBean(MyAppUserDetailsService.class);
    }

    @Test
    public void userLoginTest() {
        //Arrange
        when(userRepository.findByUserName(anyString())).thenReturn(u1);

        //Act
        UserDetails ud = testedMyAppUserDetailsService.loadUserByUsername(u1.getPassword());
        String udString = ud.toString();

        //Assert
        assertNotNull(ud);
        assertEquals(ud.getPassword(), u1.getPassword());
        assertEquals(ud.getUsername(), u1.getUsername());
    }

}
