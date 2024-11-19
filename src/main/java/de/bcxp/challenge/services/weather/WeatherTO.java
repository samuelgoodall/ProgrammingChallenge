package de.bcxp.challenge.services.weather;

/**
 * Dataclass for representing needed Data used in the Weather service
 * @param day
 * @param max
 * @param min
 */
public record WeatherTO(int day, int max, int min) {}
