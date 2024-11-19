package de.bcxp.challenge.services.weather;
import de.bcxp.challenge.util.dataprocessing.Mapper;

public class EntryToWeatherTOMapper implements Mapper<String[], WeatherTO> {
    @Override
    public WeatherTO mapFromTo(String[] input) {
        return new WeatherTO(Integer.parseInt(input[0]),
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]));
    }
}
