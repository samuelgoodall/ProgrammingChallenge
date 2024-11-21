package de.bcxp.challenge.application.weather.controller;

import de.bcxp.challenge.application.ControllerFactory;
import de.bcxp.challenge.application.weather.service.EntryToWeatherTOMapper;
import de.bcxp.challenge.application.weather.service.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Reader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReaderConfiguration;

/**
 * The Factory for obtaining the WeatherController
 *
 * @see WeatherController
 */
public class WeatherControllerFactory implements ControllerFactory<WeatherController> {

    /**
     * creates a WeatherController
     *
     * @return WeatherController
     * @see WeatherController
     */
    @Override
    public WeatherController createController() {
        Reader reader = new CSVReader(new CSVReaderConfiguration(',', false, 1));
        EntryToWeatherTOMapper entryToWeatherTOMapper = new EntryToWeatherTOMapper();
        WeatherService weatherService = new WeatherService(entryToWeatherTOMapper);
        return new WeatherController(reader, weatherService);
    }
}
