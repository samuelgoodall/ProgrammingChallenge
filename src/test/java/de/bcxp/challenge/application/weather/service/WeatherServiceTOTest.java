package de.bcxp.challenge.application.weather.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherServiceTOTest {

    @Test
    void createWeatherServiceTO_ValidConstructorParams_ReturnCorrectTO() {

        //Arrange
        WeatherTO expected = new WeatherTO(1, 2, 1);

        //Act
        WeatherTO result = new WeatherTO(1, 2, 1);

        //Assert
        assertEquals(expected.day(), result.day());
        assertEquals(expected.max(), result.max());
        assertEquals(expected.min(), result.min());
    }

    @Test
    void createWeatherServiceTO_MinBiggerMax_ThrowIllegalArgumentException() {

        //Arrange

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WeatherTO(1, 1, 2));
        assertEquals("Minimum Temperature cannot be larger than maximum temperature! For day:1", exception.getMessage());
    }
}
