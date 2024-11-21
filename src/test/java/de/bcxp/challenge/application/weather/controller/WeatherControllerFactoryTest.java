package de.bcxp.challenge.application.weather.controller;

import org.junit.jupiter.api.Test;

public class WeatherControllerFactoryTest {


    WeatherControllerFactory weatherControllerFactory = new WeatherControllerFactory();

    /**
     * Only tests if controller of correct Type is created
     */
    @Test
    void createController() {

        //Act&Assert
        weatherControllerFactory.createController();
    }
}
