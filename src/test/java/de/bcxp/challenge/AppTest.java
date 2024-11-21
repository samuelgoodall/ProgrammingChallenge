package de.bcxp.challenge;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 */
class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void doWeatherChallenge_FileIsThere_ReturnCorrectResult() throws URISyntaxException, IOException, CsvException {

        //Arrange

        //Act
        App.doWeatherChallenge();

        //Assert
        assertEquals("Day with smallest temperature spread: 14", outputStreamCaptor.toString().trim());
    }

    @Test
    void doCountryChallenge_FileIsThere_ReturnCorrectResult() throws URISyntaxException, IOException, CsvException {

        //Arrange

        //Act
        App.doCountryChallenge();

        //Assert
        assertEquals("Country with highest population density of 1633.227848: Malta", outputStreamCaptor.toString().trim());
    }

}