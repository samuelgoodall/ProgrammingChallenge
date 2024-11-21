package de.bcxp.challenge.application.countries.controller;

import de.bcxp.challenge.application.ControllerFactory;
import de.bcxp.challenge.application.countries.service.CountryService;
import de.bcxp.challenge.application.countries.service.EntryToCountryTOMapper;
import de.bcxp.challenge.util.dataprocessing.Reader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReader;
import de.bcxp.challenge.util.dataprocessing.csv.CSVReaderConfiguration;

/**
 * The Factory for obtaining the CountryController
 */
public class CountryControllerFactory implements ControllerFactory<CountryController> {
    /**
     * creates a new CountryController
     *
     * @return CountryController
     */
    @Override
    public CountryController createController() {
        Reader reader = new CSVReader(new CSVReaderConfiguration(';', false, 1));
        EntryToCountryTOMapper entryToCountryTOMapper = new EntryToCountryTOMapper();
        CountryService countryService = new CountryService(entryToCountryTOMapper);
        return new CountryController(reader, countryService);
    }
}
