package de.bcxp.challenge.dataprocessing;

/**
 * Configuration Dataclass for specifying the intended behaviour of the CSV parser
 * @param seperator char the seperator that is used in the input csv
 * @param ignoreQuotations boolean specifies if to ignore quotations or not
 * @param numberOfSkiplines int the number of lines to be skipped
 */
public record CSVReaderConfiguration(char seperator, boolean ignoreQuotations, int numberOfSkiplines) {
}
