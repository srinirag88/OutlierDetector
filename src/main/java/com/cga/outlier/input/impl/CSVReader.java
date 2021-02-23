package com.cga.outlier.input.impl;

import com.cga.outlier.domian.PriceData;
import com.cga.outlier.input.InputReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static com.cga.outlier.util.GenericUtils.parseDate;
import static java.lang.System.Logger.Level.INFO;
import static java.util.stream.Collectors.toUnmodifiableList;

public class CSVReader implements InputReader<Path> {

    private static final Pattern CSV_COMMA = Pattern.compile(",");

    private static final System.Logger LOGGER = System.getLogger(CSVReader.class.getName());

    /**
     * {@inheritDoc}
     *
     * @implNote An implementation to read data from a CSV file.
     */
    @Override
    public List<PriceData> readData(final Path dataSource) throws IOException {
        LOGGER.log(INFO, "Reading data from " + dataSource.getFileName());
        return Files.lines(dataSource)
                .skip(1) // Skip the header
                .map(this::parseDataRecords)
                .collect(toUnmodifiableList());
    }

    /**
     * Parse the CVS record line and get the {@link PriceData}
     */
    private PriceData parseDataRecords(String dataLine) {
        final var fields = CSV_COMMA.split(dataLine);
        return new PriceData(parseDate(fields[0].trim()), new BigDecimal(fields[1].trim()));
    }

}
