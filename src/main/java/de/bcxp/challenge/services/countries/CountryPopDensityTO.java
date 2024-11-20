package de.bcxp.challenge.services.countries;

/**
 * Data class for returning result of CountryService
 *
 * @param country
 * @param populationDensity
 */
public record CountryPopDensityTO(String country, double populationDensity) {
}

