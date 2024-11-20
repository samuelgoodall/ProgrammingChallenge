package de.bcxp.challenge.application;

import de.bcxp.challenge.util.dataprocessing.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract class to define how Services should look like
 *
 * @param <F> The datatype returned by the Reader
 * @param <T> The datatype to be used for the business logic
 */
public abstract class Service<F, T> {
    protected final Logger logger = LogManager.getLogger();
    protected final Mapper<F, T> mapper;

    protected Service(Mapper<F, T> mapper) {
        this.mapper = mapper;
    }
}
