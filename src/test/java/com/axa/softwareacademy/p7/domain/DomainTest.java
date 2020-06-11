package com.axa.softwareacademy.p7.domain;

import org.junit.jupiter.api.Test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;

public class UserTest {

    @Test
    public void testPojoUser() {
        // Arrange
        final Class<?> classUnderTest = User.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .testing(GETTER) //
                .testing(CONSTRUCTOR) //
                .areWellImplemented();
    }

    @Test
    public void testPojoCurvePoint() {
        // Arrange
        final Class<?> classUnderTest = CurvePoint.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .areWellImplemented();
    }

    @Test
    public void testPojoRating() {
        // Arrange
        final Class<?> classUnderTest = Rating.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .areWellImplemented();
    }

    @Test
    public void testPojoRuleName() {
        // Arrange
        final Class<?> classUnderTest = RuleName.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .areWellImplemented();
    }

    @Test
    public void testPojoTrade() {
        // Arrange
        final Class<?> classUnderTest = Trade.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .areWellImplemented();
    }

    @Test
    public void testPojoBidList() {
        // Arrange
        final Class<?> classUnderTest = BidList.class;

        // Assert
        assertPojoMethodsFor(classUnderTest) //
                .areWellImplemented();
    }
}
