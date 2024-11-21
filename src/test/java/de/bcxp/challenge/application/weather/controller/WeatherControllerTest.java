package de.bcxp.challenge.application.weather.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.weather.service.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Reader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {
    private final Reader reader = mock(Reader.class);
    private final WeatherService weatherService = mock(WeatherService.class);
    private final WeatherController underTest = new WeatherController(reader, weatherService);

    private final List<String[]> inputs = mock(StringArrList.class);
    private final Path inputFile = mock(Path.class);

    @Test
    void getResultFromFile_CorrectFile_ReturnResult() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenReturn(inputs);
        when(weatherService.getDayOfSmallestTemperatureSpread(inputs)).thenReturn(1);

        //Act
        Integer result = underTest.getResultFromFile(inputFile);

        //Assert
        assertEquals(1, result);
    }

    @Test
    void getResultFromFile_FileNotFound_ThrowIOException() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenThrow(new IOException());

        //Act&Assert
        assertThrows(IOException.class, () -> underTest.getResultFromFile(inputFile));
    }

    @Test
    void getResultFromFile_EmptyFile_ThrowIllegalArgumentException() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenReturn(inputs);
        when(weatherService.getDayOfSmallestTemperatureSpread(inputs)).thenThrow(new IllegalArgumentException());

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> underTest.getResultFromFile(inputFile));

    }


    /**
     * Helper class for avoiding warning during mocking
     */
    private static final class StringArrList extends ArrayList<String[]> {
    }
}
