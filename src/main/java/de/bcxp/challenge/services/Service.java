package de.bcxp.challenge.services;

import de.bcxp.challenge.util.dataprocessing.Mapper;
import de.bcxp.challenge.util.dataprocessing.Reader;

public abstract class Service<F,T> {
    protected final Mapper<F,T> mapper;
    protected final Reader reader;

    protected Service(Mapper<F,T> mapper, Reader reader) {
        this.mapper = mapper;
        this.reader = reader;
    }


}
