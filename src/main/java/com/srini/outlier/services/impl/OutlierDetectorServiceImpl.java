package com.srini.outlier.services.impl;

import com.cga.outlier.algorithm.OutlierAlgorithm;
import com.cga.outlier.domian.PriceData;
import com.cga.outlier.input.InputReader;
import com.cga.outlier.output.OutputWriter;
import com.cga.outlier.services.OutlierDetectorService;
import com.srini.outlier.algorithm.OutlierAlgorithm;
import com.srini.outlier.domian.PriceData;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.ServiceLoader;

import static java.lang.System.Logger.Level.ERROR;
import static java.lang.System.Logger.Level.INFO;

public class OutlierDetectorServiceImpl implements OutlierDetectorService {

    private final InputReader<Path> inputReader;

    private final OutputWriter<Path> outputWriter;

    private final OutlierAlgorithm outlierAlgorithm;

    private static final System.Logger LOGGER = System.getLogger(OutlierDetectorServiceImpl.class.getName());

    public OutlierDetectorServiceImpl(InputReader<Path> inputReader,
                                      OutputWriter<Path> outputWriter,
                                      OutlierAlgorithm outlierAlgorithm) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.outlierAlgorithm = outlierAlgorithm;
    }

    public OutlierDetectorServiceImpl() {
        this.inputReader = ServiceLoader.load(InputReader.class)
                .findFirst().orElseThrow(() -> new RuntimeException("No providers found"));
        this.outputWriter = ServiceLoader.load(OutputWriter.class)
                .findFirst().orElseThrow(() -> new RuntimeException("No providers found"));
        this.outlierAlgorithm = ServiceLoader.load(OutlierAlgorithm.class)
                .findFirst().orElseThrow(() -> new RuntimeException("No providers found"));
    }

    /**
     * {@inheritDoc}
     *
     * @implNote An implementation for outlier processing.
     */
    @Override
    public void processOutliers(Path input, Path output) {
        LOGGER.log(INFO, "Starting outlier processing for the data from " + input.getFileName());
        final List<PriceData> priceData;
        try {
            priceData = inputReader.readData(input);
        } catch (IOException e) {
            final var error = "Error reading from input " + input.getFileName().toString();
            LOGGER.log(ERROR, error);
            throw new RuntimeException(error);
        }
        final var result = outlierAlgorithm.findOutliers(priceData);
        LOGGER.log(INFO, "Outlier data are " + result.outlier());
        try {
            outputWriter.writeData(result.cleanedData(), output);
        } catch (IOException e) {
            final var error = "Error writing data to " + output.getFileName().toString();
            LOGGER.log(ERROR, error);
            throw new RuntimeException(error);
        }
    }
}
