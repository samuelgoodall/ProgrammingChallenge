package de.bcxp.challenge.application.countries.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.countries.service.CountryService;
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

public class CountryControllerTest {
    private final Reader reader = mock(Reader.class);
    private final CountryService countryService = mock(CountryService.class);
    private final CountryController underTest = new CountryController(reader, countryService);

    private final List<String[]> inputs = mock(StringArrList.class);
    private final CountryPopDensityTO popDensityTO = new CountryPopDensityTO("Austria", 83.5);
    private final Path inputFile = mock(Path.class);

    @Test
    void getCountryWithHighestPopulationDensity_CorrectFile_ReturnResult() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenReturn(inputs);
        when(countryService.getPopulationDensityOfInputs(inputs)).thenReturn(popDensityTO);

        //Act
        CountryPopDensityTO result = underTest.getCountryWithHighestPopulationDensity(inputFile);

        //Assert
        assertEquals(result.country(), popDensityTO.country());
        assertEquals(result.populationDensity(), popDensityTO.populationDensity());
    }

    @Test
    void getCountryWithHighestPopulationDensity_FileNotFound_ThrowIOException() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenThrow(new IOException());

        //Act&Assert
        assertThrows(IOException.class, () -> underTest.getCountryWithHighestPopulationDensity(inputFile));
    }

    @Test
    void getCountryWithHighestPopulationDensity_EmptyFile_ThrowIllegalArgumentException() throws IOException, CsvException {

        //Arrange
        when(reader.readFile(inputFile)).thenReturn(inputs);
        when(countryService.getPopulationDensityOfInputs(inputs)).thenThrow(new IllegalArgumentException());

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> underTest.getCountryWithHighestPopulationDensity(inputFile));

    }


    /**
     * Helper class for avoiding warning during mocking
     */
    private static final class StringArrList extends ArrayList<String[]> {
    }

}
