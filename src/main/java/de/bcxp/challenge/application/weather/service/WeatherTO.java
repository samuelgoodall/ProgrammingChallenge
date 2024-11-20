package de.bcxp.challenge.application.weather.service;

/**
 * Dataclass for representing needed Data used in the Weather service
 *
 * @param day
 * @param max
 * @param min
 */
public record WeatherTO(int day, int max, int min) {
    public WeatherTO {
        if (max < min)
            throw new IllegalArgumentException("Minimum Temperature cannot be larger than maximum temperature! For day:" + day);
    }
}
