package com.axa.softwareacademy.p7.domain;

import org.junit.jupiter.api.Test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.*;

public class DomainTest {

    @Test
    public void testPojoUser() {
        // Arrange
        final Class<?> classUnderTest = User.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }

    @Test
    public void testPojoCurvePoint() {
        // Arrange
        final Class<?> classUnderTest = CurvePoint.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }

    @Test
    public void testPojoRating() {
        // Arrange
        final Class<?> classUnderTest = Rating.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }

    @Test
    public void testPojoRuleName() {
        // Arrange
        final Class<?> classUnderTest = RuleName.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }

    @Test
    public void testPojoTrade() {
        // Arrange
        final Class<?> classUnderTest = Trade.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }

    @Test
    public void testPojoBidList() {
        // Arrange
        final Class<?> classUnderTest = BidList.class;

        // Assert
        assertPojoMethodsFor(classUnderTest)
                .testing(GETTER)
                .testing(SETTER)
                .testing(CONSTRUCTOR)
                .testing(TO_STRING)
                .areWellImplemented();
    }
}
