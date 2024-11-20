package de.bcxp.challenge.services.countries;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.services.Service;
import de.bcxp.challenge.util.dataprocessing.Mapper;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CountryService extends Service<String[], CountryTO> {

    public CountryService(Mapper<String[], CountryTO> mapper, Reader reader) {
        super(mapper, reader);
    }

    protected CountryPopDensityTO getPopulationDensityOfInputs(List<String[]> countryItems) {

        //verify input integrity
        if (countryItems == null) {
            throw new IllegalArgumentException("The list of country items may not be null!");
        }

        if (countryItems.isEmpty()) {
            throw new IllegalArgumentException("The list of country items may not be empty!");
        }

        double maxPopDensity = 0.0;
        String countryMaxPopDensity = null;

        for (String[] countryItem : countryItems) {
            try {
                CountryTO countryTO = this.mapper.mapFromTo(countryItem);
                double populationDensity = countryTO.population() / countryTO.area();
                if (populationDensity > maxPopDensity) {
                    maxPopDensity = populationDensity;
                    countryMaxPopDensity = countryTO.country();
                }
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            }
        }

        if (countryMaxPopDensity != null) {
            return new CountryPopDensityTO(countryMaxPopDensity, maxPopDensity);
        } else {
            throw new IllegalArgumentException("The list of country items does not contain enough valid data!");
        }
    }

    public CountryPopDensityTO getPopulationDensityOfInputsFromFile(Path inputFile) throws IOException, CsvException {
        return this.getPopulationDensityOfInputs(this.reader.readFile(inputFile));
    }
}
