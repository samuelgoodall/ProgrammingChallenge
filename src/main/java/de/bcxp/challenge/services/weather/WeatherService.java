package de.bcxp.challenge.services.weather;
import de.bcxp.challenge.services.Service;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.nio.file.Path;
import java.util.List;

/**
 * Service that is used to compute the Day of smallest Temperature spread
 */
public class WeatherService extends Service<String[],WeatherTO> {

    /**
     * Constructor
     * @param reader Reader the reader implementation used for the Weather service
     */
    public WeatherService(Reader reader) {
        super(new EntryToWeatherTOMapper(),reader);
    }

    /**
     * Method for computing day of min temp spread
     * @param weatherItems List<String[]> the data given as rows of strings
     * @return int the day of smallestTemperatureSpread
     */
    protected int getDayOfSmallestTemperatureSpread(List<String[]> weatherItems){
        int minSpread = Integer.MAX_VALUE;
        int minDay = 0;
        for(String[] weatherItem : weatherItems){
            WeatherTO weatherTO = this.mapper.mapFromTo(weatherItem);
            if(minSpread>weatherTO.max()-weatherTO.min()){
                minSpread = weatherTO.max()-weatherTO.min();
                minDay = weatherTO.day();
            }
        }
        return minDay;
    }

    /**
     * Method for computing day of min temp spread from inputFile
     * @param inputFile Path the path to the input File
     * @return int the day of smallest TemperatureSpread
     */
    public int getDayOfSmallestTemperatureSpread(Path inputFile){
        return getDayOfSmallestTemperatureSpread(this.reader.readFile(inputFile));
    }
}
