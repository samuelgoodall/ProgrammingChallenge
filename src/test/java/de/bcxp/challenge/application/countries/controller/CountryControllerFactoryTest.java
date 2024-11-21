package de.bcxp.challenge.application.countries.controller;

import org.junit.jupiter.api.Test;

public class CountryControllerFactoryTest {

    private final CountryControllerFactory underTest = new CountryControllerFactory();

    /**
     * Just tests if a controller is created
     */
    @Test
    void createController_MethodIsCalled_ReturnController() {

        //Act & Assert
        underTest.createController();
    }
}
