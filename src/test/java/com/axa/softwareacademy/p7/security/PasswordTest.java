package com.axa.softwareacademy.p7.security;

import com.axa.softwareacademy.p7.config.SecurityConfig;
import com.axa.softwareacademy.p7.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PasswordTest {
    //Les chiffres sont considérés comme des majuscules
    String wrongPasswordNoDigit = "Password_";
    String wrongPasswordNoUpperCase = "password_";
    String wrongPasswordNoLowerCase = "PASSWORD_";
    String wrongPasswordNoSpecialCase = "Password12";
    String wrongPasswordNoEightCase = "Pass_12";
    String correctPassword = "Password_12";
    User user = new User(1, "Lucas Vannier", "password","lucka", "USER");

    @Autowired
    private SecurityConfig securityConfig;

    public PasswordTest() throws ParseException {
    }

    @BeforeEach
    private void setUpForTest() throws Exception {
        securityConfig = new SecurityConfig();
    }

    @Test
    public void wrongPasswordNoDigit() {
        //Arrange
        user.setPassword(wrongPasswordNoDigit);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertFalse(isPasswordValid);
        System.out.println("Password :" + wrongPasswordNoDigit + "" + "is not correct because it doesn't contains digits");
    }

    @Test
    public void wrongPasswordNoUpperCase() {
        //Arrange
        user.setPassword(wrongPasswordNoUpperCase);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertFalse(isPasswordValid);
        System.out.println("Password :" + wrongPasswordNoUpperCase + "" + "is not correct because it doesn't contains uppercase");
    }

    @Test
    public void wrongPasswordNoLowerCase() {
        //Arrange
        user.setPassword(wrongPasswordNoLowerCase);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertFalse(isPasswordValid);
        System.out.println("Password :" + wrongPasswordNoLowerCase + "" + "is not correct because it doesn't contains lowercase");
    }

    @Test
    public void wrongPasswordNoSpecialCase() {
        //Arrange
        user.setPassword(wrongPasswordNoSpecialCase);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertFalse(isPasswordValid);
        System.out.println("Password :" + wrongPasswordNoSpecialCase + "" + "is not correct because it doesn't contains special case");
    }

    @Test
    public void wrongPasswordNoEightCase() {
        //Arrange
        user.setPassword(wrongPasswordNoEightCase);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertFalse(isPasswordValid);
        System.out.println("Password :" + wrongPasswordNoEightCase + "" + "is not correct because it doesn't contains at least 8 characters");
    }

    @Test
    public void correctPassword() {
        //Arrange
        user.setPassword(correctPassword);

        //Act
        boolean isPasswordValid = securityConfig.isPasswordValid(user.getPassword());

        //Assert
        assertTrue(isPasswordValid);
        System.out.println("Password :" + correctPassword + "" + "is correct");
    }
}
