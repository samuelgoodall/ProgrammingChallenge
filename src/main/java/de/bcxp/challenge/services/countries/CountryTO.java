package de.bcxp.challenge.services.countries;

/**
 * The DataClass for gathering the basic needed params
 *
 * @param country
 * @param population
 * @param area
 */
public record CountryTO(String country, double population, double area) {
    public CountryTO {
        if (population < 0) {
            throw new IllegalArgumentException("Population cannot be negative!");
        }
        if (area < 0) {
            throw new IllegalArgumentException("Area cannot be negative!");
        }
    }
}
