package de.bcxp.challenge.application.countries.controller;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.application.Controller;
import de.bcxp.challenge.application.countries.service.CountryService;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CountryController extends Controller<CountryPopDensityTO> {

    private final CountryService countryService;

    public CountryController(Reader reader, CountryService countryService) {
        super(reader);
        this.countryService = countryService;
    }

    public CountryPopDensityTO getResultFromFile(Path inputFile) throws IOException, CsvException {
        List<String[]> inputs = this.reader.readFile(inputFile);
        return this.countryService.getPopulationDensityOfInputs(inputs);
    }
}
