package de.bcxp.challenge.application.weather.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.Controller;
import de.bcxp.challenge.application.weather.service.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class WeatherController extends Controller<Integer> {

    private final WeatherService weatherService;

    public WeatherController(Reader reader, WeatherService weatherService) {
        super(reader);
        this.weatherService = weatherService;
    }


    @Override
    public Integer getResultFromFile(Path inputFile) throws IOException, CsvException {
        List<String[]> inputs = this.reader.readFile(inputFile);
        return this.weatherService.getDayOfSmallestTemperatureSpread(inputs);
    }
}
