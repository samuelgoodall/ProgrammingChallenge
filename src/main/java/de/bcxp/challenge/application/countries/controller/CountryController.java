package de.bcxp.challenge.application.countries.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.Controller;
import de.bcxp.challenge.application.countries.service.CountryService;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * The Controller for obtaining the Country with the highest population density
 */
public class CountryController extends Controller {
    /**
     * The CountryService
     */
    protected final CountryService countryService;

    /**
     * Constructor
     *
     * @param reader         the Reader to be used to read the File
     * @param countryService the Service that is used to solve the task
     */
    public CountryController(Reader reader, CountryService countryService) {
        super(reader);
        this.countryService = countryService;
    }

    /**
     * Returns the country with the highest population density with the corresponding density
     *
     * @param inputFile the File that is to be read for obtaining the data
     * @return CountryPopDensityTO datatransferobject of countryname, popdensity
     * @throws IOException  when something goes wrong during reading of File
     * @throws CsvException when something goes wrong when parsing the csv
     */
    public CountryPopDensityTO getCountryWithHighestPopulationDensity(Path inputFile) throws IOException, CsvException {
        List<String[]> inputs = this.reader.readFile(inputFile);
        return this.countryService.getPopulationDensityOfInputs(inputs);
    }
}
