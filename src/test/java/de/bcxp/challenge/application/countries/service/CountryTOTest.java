package de.bcxp.challenge.application.countries.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryTOTest {

    @Test
    void createCountryTO_ValidConstructorParams_ReturnCorrectTO() {

        //Arrange
        CountryTO expected = new CountryTO("Prussia", 2, 1);

        //Act
        CountryTO result = new CountryTO("Prussia", 2, 1);

        //Assert
        assertEquals(expected.country(), result.country());
        assertEquals(expected.population(), result.population());
        assertEquals(expected.area(), result.area());
    }

    @Test
    void createCountryTO_AreaNegative_ThrowIllegalArgumentException() {

        //Arrange

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CountryTO("Prussia", 2, -1));
        assertEquals("Area cannot be negative!", exception.getMessage());
    }

    @Test
    void createCountryTO_PopulationNegative_ThrowIllegalArgumentException() {

        //Arrange

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CountryTO("Prussia", -2, 1));
        assertEquals("Population cannot be negative!", exception.getMessage());
    }
}
