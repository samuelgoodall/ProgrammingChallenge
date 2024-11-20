package de.bcxp.challenge.application;

import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Controller<T> {
    protected final Reader reader;

    protected Controller(Reader reader) {
        this.reader = reader;
    }

    public abstract T getResultFromFile(Path inputFile) throws IOException, CsvException;
}
