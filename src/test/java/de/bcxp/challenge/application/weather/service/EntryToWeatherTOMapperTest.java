package de.bcxp.challenge.application.weather.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryToWeatherTOMapperTest {

    private final EntryToWeatherTOMapper underTest = new EntryToWeatherTOMapper();

    @Test
    void mapFromTo_CorrectInputItem_CorrectWeatherTO() {

        //Arrange
        String[] correctInput = new String[]{"1", "90", "85"};
        WeatherTO expected = new WeatherTO(1, 90, 85);

        //Act
        WeatherTO result = underTest.mapFromTo(correctInput);

        //Assert
        assertEquals(expected.day(), result.day());
        assertEquals(expected.min(), result.min());
        assertEquals(expected.max(), result.max());
    }

    @Test
    void mapFromTo_InputItemWithNonNumbers_ThrowIllegalArgumentException() {

        //Arrange
        String[] incorrectInput = new String[]{"1", "AB", "DE"};

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> underTest.mapFromTo(incorrectInput));
    }

}
