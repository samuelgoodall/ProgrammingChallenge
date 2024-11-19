package de.bcxp.challenge.dataprocessing;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface used for specifying how to read in data files like CSVs, JSON etc.
 */
public interface Reader {
    /**
     * Method for reading in data file
     * @param filepath Path the path to the data file that is to be read,
     *                 expects file to be in resources folder and expects
     *                 relative path from resources folder
     * @return String[][]
     */
    List<String[]> readFile(Path filepath);
}
