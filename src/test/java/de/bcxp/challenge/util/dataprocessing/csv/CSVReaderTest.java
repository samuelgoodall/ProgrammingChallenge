package de.bcxp.challenge.util.dataprocessing.csv;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVReaderTest {

    private final Path weatherPath = Paths.get("src/test/resources/de/bcxp/challenge/csv/weathertest.csv");
    private final Path countriesPath = Paths.get("src/test/resources/de/bcxp/challenge/csv/countriestest.csv");
    private final Path noFile = Paths.get("src/test/resources/de/bcxp/challenge/csv");

    @Test
    void readFile_ValidFilePathWithComma_ReturnCorrectResult() throws IOException, CsvException {

        //Arrange
        CSVReaderConfiguration csvReaderConfiguration = new CSVReaderConfiguration(',', false, 1);
        CSVReader underTest = new CSVReader(csvReaderConfiguration);

        String[] expected = new String[]{"1", "88", "59"};
        //Act
        List<String[]> result = underTest.readFile(weatherPath);

        //Assert
        assertEquals(expected[0], result.getFirst()[0]);
        assertEquals(expected[1], result.getFirst()[1]);
        assertEquals(expected[2], result.getFirst()[2]);
    }

    @Test
    void readFile_ValidFilePathWithSemiColon_ReturnCorrectResult() throws IOException, CsvException {

        //Arrange
        CSVReaderConfiguration csvReaderConfiguration = new CSVReaderConfiguration(';', false, 1);
        CSVReader underTest = new CSVReader(csvReaderConfiguration);

        String[] expected = new String[]{"Austria", "Vienna", "1995"};
        //Act
        List<String[]> result = underTest.readFile(countriesPath);

        //Assert
        assertEquals(expected[0], result.getFirst()[0]);
        assertEquals(expected[1], result.getFirst()[1]);
        assertEquals(expected[2], result.getFirst()[2]);
    }

    @Test
    void readFile_FilePathThatDoesNotPointToFile_ReturnEmptyList() throws IOException, CsvException {

        //Arrange
        CSVReaderConfiguration csvReaderConfiguration = new CSVReaderConfiguration(';', false, 1);
        CSVReader underTest = new CSVReader(csvReaderConfiguration);

        //Act
        List<String[]> result = underTest.readFile(noFile);

        //Assert
        assertTrue(result.isEmpty());
    }

}
