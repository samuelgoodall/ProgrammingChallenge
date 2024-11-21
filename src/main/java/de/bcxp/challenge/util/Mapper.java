package de.bcxp.challenge.util;

/**
 * Interface for creating mappers
 *
 * @param <F> the datatype that is to be mapped
 * @param <T> the datatype that is to be mapped to
 */
public interface Mapper<F, T> {
    T mapFromTo(F input) throws IllegalArgumentException;
}
