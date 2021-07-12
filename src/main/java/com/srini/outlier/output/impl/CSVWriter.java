package com.srini.outlier.output.impl;

import com.cga.outlier.domian.PriceData;
import com.cga.outlier.output.OutputWriter;
import com.srini.outlier.domian.PriceData;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.lineSeparator;
import static java.nio.file.Files.writeString;

public class CSVWriter implements OutputWriter<Path> {

    private static final System.Logger LOGGER = System.getLogger(CSVWriter.class.getName());

    /**
     * {@inheritDoc}
     *
     * @implNote An implementation to write data into a CSV file.
     */
    @Override
    public void writeData(List<PriceData> priceData, Path destination) throws IOException {
        LOGGER.log(INFO, "Writing data to " + destination.getFileName());
        final var csvHeader = "Date,Price" + lineSeparator();
        writeString(destination, csvHeader, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        for (final var data : priceData) {
            writeString(destination, data.toString() + lineSeparator(), StandardOpenOption.APPEND);
        }
    }
}
