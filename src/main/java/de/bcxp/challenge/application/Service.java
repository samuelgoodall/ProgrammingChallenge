package de.bcxp.challenge.application;

import de.bcxp.challenge.util.dataprocessing.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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

    protected void validateInput(List<F> items) {
        //verify input integrity
        if (items == null) {
            throw new IllegalArgumentException("The list of items may not be null!");
        }

        if (items.isEmpty()) {
            throw new IllegalArgumentException("The list of items may not be empty!");
        }
    }
}
