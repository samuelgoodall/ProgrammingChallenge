package de.bcxp.challenge.application.countries.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryToCountryTOMapperTest {
    private final EntryToCountryTOMapper underTest = new EntryToCountryTOMapper();


    @Test
    void mapFromTo_CorrectInputItem_CorrectWeatherTO() {
        //Arrange
        String[] correctInput = new String[]{"Austria", "something", "something", "90", "9"};
        CountryTO expected = new CountryTO("Austria", 90, 9);

        //Act
        CountryTO result = underTest.mapFromTo(correctInput);

        //Assert
        assertEquals(expected.country(), result.country());
        assertEquals(expected.area(), result.area());
        assertEquals(expected.population(), result.population());
    }

    @Test
    void mapFromTo_InputItemWithNonNumbers_ThrowIllegalArgumentException() {
        //Arrange
        String[] incorrectInput = new String[]{"Austria", "something", "something", "A", "9"};


        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> underTest.mapFromTo(incorrectInput));
    }
}
