package de.bcxp.challenge;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.countries.controller.CountryController;
import de.bcxp.challenge.application.countries.controller.CountryControllerFactory;
import de.bcxp.challenge.application.countries.controller.CountryPopDensityTO;
import de.bcxp.challenge.application.weather.controller.WeatherController;
import de.bcxp.challenge.application.weather.controller.WeatherControllerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    public static void doWeatherChallenge() throws URISyntaxException, IOException, CsvException {
        Path path = Paths.get(ClassLoader.getSystemResource("de/bcxp/challenge/csv/weather.csv").toURI());
        WeatherControllerFactory weatherControllerFactory = new WeatherControllerFactory();
        WeatherController weatherController = weatherControllerFactory.createController();
        int dayWithSmallestTempSpread = weatherController.getResultFromFile(path);
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
    }

    public static void doCountryChallenge() throws URISyntaxException, IOException, CsvException {
        Path path = Paths.get(ClassLoader.getSystemResource("de/bcxp/challenge/csv/countries.csv").toURI());
        CountryControllerFactory countryControllerFactory = new CountryControllerFactory();
        CountryController countryController = countryControllerFactory.createController();
        CountryPopDensityTO result = countryController.getResultFromFile(path);
        System.out.printf("Country with highest population density of %f: %s%n", result.populationDensity(), result.country());
    }

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws URISyntaxException, IOException, CsvException {
        doWeatherChallenge();
        doCountryChallenge();
    }
}
