package de.bcxp.challenge.application.weather.service;

import de.bcxp.challenge.application.Service;
import de.bcxp.challenge.util.Mapper;

import java.util.List;

/**
 * Service that is used to compute the Day of smallest Temperature spread
 */
public class WeatherService extends Service<String[], WeatherTO> {

    /**
     * Constructor
     */
    public WeatherService(Mapper<String[], WeatherTO> mapper) {
        super(mapper);
    }

    /**
     * Method for computing day of min temp spread
     *
     * @param weatherItems List<String[]> the data given as rows of strings
     * @return int the day of smallestTemperatureSpread
     */
    public Integer getDayOfSmallestTemperatureSpread(List<String[]> weatherItems) throws IllegalArgumentException {

        //verify input integrity
        this.validateInput(weatherItems);

        int minSpread = Integer.MAX_VALUE;
        Integer minDay = null;
        int position = 0;
        for (String[] weatherItem : weatherItems) {
            try {
                WeatherTO weatherTO = this.mapper.mapFromTo(weatherItem);
                if (minSpread > weatherTO.max() - weatherTO.min()) {
                    minSpread = weatherTO.max() - weatherTO.min();
                    minDay = weatherTO.day();
                }
                position++;
            } catch (NumberFormatException e) {
                logger.error("Entry at position:{} has a non integer number in one of its values, day:{}, is ignored", position, weatherItem[0]);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            }
        }

        if (minDay == null) {
            throw new IllegalArgumentException("The list of weather items does not contain enough valid data!");
        }

        return minDay;
    }
}
