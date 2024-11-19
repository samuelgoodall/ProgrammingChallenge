package de.bcxp.challenge.util.dataprocessing.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.util.dataprocessing.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Class used for reading in CSV files
 */
public class CSVReader implements Reader {

    private final CSVReaderConfiguration readerConfiguration;

    /**
     * Constructor
     * @param readerConfiguration the Configuration that is used to specify the
     *                            intended behaviour of the csv parser,
     *                            @see CSVReaderConfiguration
     *                            for details
     */
    public CSVReader(CSVReaderConfiguration readerConfiguration) {
        this.readerConfiguration = readerConfiguration;
    }

    /**
     * Implements
     * @param filePath Path the path to the data file that is to be read,
     *                 expects file to be in resources folder and expects
     *                 relative path from resources folder
     * @return List<String[]> a list of all entries encoded as Strings
     */
    @Override
    public List<String[]> readFile(Path filePath) {
        try (java.io.Reader reader = Files.newBufferedReader(filePath)) {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(this.readerConfiguration.seperator())
                    .withIgnoreQuotations(this.readerConfiguration.ignoreQuotations())
                    .build();
            try (com.opencsv.CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(this.readerConfiguration.numberOfSkiplines())
                    .withCSVParser(parser)
                    .build()) {
                return csvReader.readAll();
            }
        } catch (IOException | CsvException e) {
            //TODO manage Exeption handling properly!
            throw new RuntimeException(e);
        }
    }
}
