package de.bcxp.challenge;

import de.bcxp.challenge.services.weather.EntryToWeatherTOMapper;
import de.bcxp.challenge.services.weather.WeatherService;
import de.bcxp.challenge.util.dataprocessing.Reader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReaderConfiguration;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    public static void doWeatherChallenge() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("de/bcxp/challenge/csv/weather.csv").toURI());
        Reader reader = new CSVReader(new CSVReaderConfiguration(',', false, 1));
        EntryToWeatherTOMapper entryToWeatherTOMapper = new EntryToWeatherTOMapper();
        WeatherService weatherService = new WeatherService(entryToWeatherTOMapper, reader);
        int dayWithSmallestTempSpread = weatherService.getDayOfSmallestTemperatureSpread(path);
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
    }

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws URISyntaxException {
        doWeatherChallenge();
        String countryWithHighestPopulationDensity = "Some country"; // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
