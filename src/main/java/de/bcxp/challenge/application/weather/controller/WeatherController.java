package de.bcxp.challenge.application.weather.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.Controller;
import de.bcxp.challenge.application.weather.service.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Controller for the WeatherDataset
 */
public class WeatherController extends Controller {
    /**
     * The Service used to obtain the result
     */
    private final WeatherService weatherService;

    /**
     * Constructor
     *
     * @param reader         Reader the reader used to obtain data from the File
     * @param weatherService WeatherService
     */
    public WeatherController(Reader reader, WeatherService weatherService) {
        super(reader);
        this.weatherService = weatherService;
    }

    /**
     * method for getting the Result from File
     *
     * @param inputFile Path the path to the inputFile
     * @return the Integer number of the day with the smallest temperature spread
     * @throws IOException  when Errors occur during reading and opening the file
     * @throws CsvException when Errors occur during parsing the file
     */
    public Integer getDayOfSmallestTemperatureSpread(Path inputFile) throws IOException, CsvException {
        List<String[]> inputs = this.reader.readFile(inputFile);
        return this.weatherService.getDayOfSmallestTemperatureSpread(inputs);
    }
}
