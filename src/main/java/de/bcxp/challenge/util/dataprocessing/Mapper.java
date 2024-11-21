package de.bcxp.challenge.util.dataprocessing;

public interface Mapper<F, T> {
    T mapFromTo(F input) throws IllegalArgumentException;
}
