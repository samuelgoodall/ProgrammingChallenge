package de.bcxp.challenge.application.countries.service;

import de.bcxp.challenge.application.Service;
import de.bcxp.challenge.application.countries.controller.CountryPopDensityTO;
import de.bcxp.challenge.util.Mapper;

import java.util.List;

/**
 * The Country Service used for performing business logic for the Countries Data
 */
public class CountryService extends Service<String[], CountryTO> {

    /**
     * Constructor
     *
     * @param mapper the Mapper used for mapping from String[] to TOs
     */
    public CountryService(Mapper<String[], CountryTO> mapper) {
        super(mapper);
    }

    /**
     * @param countryItems String[] the different data items as String[]
     * @return CountryPopDensityTO the result
     * @see CountryPopDensityTO
     */
    public CountryPopDensityTO getPopulationDensityOfInputs(List<String[]> countryItems) {

        this.validateInput(countryItems);

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
}
