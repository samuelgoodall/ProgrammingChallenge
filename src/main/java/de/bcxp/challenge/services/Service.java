package de.bcxp.challenge.services;

import de.bcxp.challenge.util.dataprocessing.Mapper;
import de.bcxp.challenge.util.dataprocessing.Reader;

/**
 * Abstract class to define how Services should look like
 * @param <F> The datatype returned by the Reader
 * @param <T> The datatype to be used for the business logic
 */
public abstract class Service<F,T> {
    protected final Mapper<F,T> mapper;
    protected final Reader reader;

    protected Service(Mapper<F,T> mapper, Reader reader) {
        this.mapper = mapper;
        this.reader = reader;
    }


}
