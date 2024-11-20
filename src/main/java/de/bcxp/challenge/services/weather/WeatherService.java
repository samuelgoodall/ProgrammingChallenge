package de.bcxp.challenge.services.weather;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.services.Service;
import de.bcxp.challenge.util.dataprocessing.Mapper;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Service that is used to compute the Day of smallest Temperature spread
 */
public class WeatherService extends Service<String[], WeatherTO> {

    /**
     * Constructor
     *
     * @param reader Reader the reader implementation used for the Weather service
     */
    public WeatherService(Mapper<String[], WeatherTO> mapper, Reader reader) {
        super(mapper, reader);
    }

    /**
     * Method for computing day of min temp spread
     *
     * @param weatherItems List<String[]> the data given as rows of strings
     * @return int the day of smallestTemperatureSpread
     */
    protected int getDayOfSmallestTemperatureSpread(List<String[]> weatherItems) throws IllegalArgumentException {

        //verify input integrity
        if (weatherItems == null) {
            throw new IllegalArgumentException("The list of weather items may not be null!");
        }

        if (weatherItems.isEmpty()) {
            throw new IllegalArgumentException("The list of weather items may not be empty!");
        }


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

    /**
     * Method for computing day of min temp spread from inputFile
     *
     * @param inputFile Path the path to the input File
     * @return int the day of smallest TemperatureSpread
     */
    public int getDayOfSmallestTemperatureSpread(Path inputFile) throws IOException, CsvException {
        return getDayOfSmallestTemperatureSpread(this.reader.readFile(inputFile));
    }
}
