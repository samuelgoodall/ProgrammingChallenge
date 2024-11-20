package de.bcxp.challenge.services.countries;

import de.bcxp.challenge.util.dataprocessing.Mapper;

/**
 * Mapper to map from a raw entry in form of a String[] to a CountryTO
 */
public class EntryToCountryTOMapper implements Mapper<String[], CountryTO> {
    /**
     * Maps from String[] to CountryTO
     *
     * @param input String[]
     * @return CountryTO
     */
    @Override
    public CountryTO mapFromTo(String[] input) throws IllegalArgumentException {
        String country = input[0];
        double population = Double.parseDouble(input[3].replaceAll("\\.", "").replaceAll(",", "."));
        double area = Double.parseDouble(input[4]);
        return new CountryTO(country, population, area);
    }
}
