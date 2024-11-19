package de.bcxp.challenge.services.weather;
import de.bcxp.challenge.util.dataprocessing.Mapper;

/**
 * Mapper to map from a raw entry in form of a String[] to a WeatherTO
 */
public class EntryToWeatherTOMapper implements Mapper<String[], WeatherTO> {

    /**
     * Maps from String[] to WeatherTO
     * @param input String[]
     * @return WeatherTO
     */
    @Override
    public WeatherTO mapFromTo(String[] input) {
        return new WeatherTO(Integer.parseInt(input[0]),
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]));
    }
}
