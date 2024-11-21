package de.bcxp.challenge.application;

import de.bcxp.challenge.util.dataprocessing.Reader;

/**
 * The Controller class used for calling the services and providing services with input from outside
 */
public abstract class Controller {
    protected final Reader reader;

    protected Controller(Reader reader) {
        this.reader = reader;
    }
}
