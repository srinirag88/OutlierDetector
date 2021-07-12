package com.srini.outlier.services.impl;

import com.cga.outlier.algorithm.impl.ChauvenetsCriterion;
import com.cga.outlier.input.impl.CSVReader;
import com.cga.outlier.output.impl.CSVWriter;
import com.cga.outlier.services.OutlierDetectorService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

import static com.cga.outlier.data.TestData.PriceData.TEST_INPUT_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the default implementation of {@link OutlierDetectorService}
 */
class OutlierDetectorServiceImplTest {

    private final OutlierDetectorService outlierDetectorService;

    private final CSVReader csvReader;

    public OutlierDetectorServiceImplTest() {
        this.csvReader = new CSVReader();
        this.outlierDetectorService = new OutlierDetectorServiceImpl(csvReader, new CSVWriter(), new ChauvenetsCriterion());
    }

    /**
     * Tests the processOutliers and asserts if the cleaned data is saved
     * The test data is setup so that there are 2 outliers.
     *
     * @throws IOException If any error occurs.
     */
    @Test
    void processOutliers() throws IOException {
        final var input = TEST_INPUT_1.get();
        final var output = Files.createTempFile("priceData", "");
        outlierDetectorService.processOutliers(input, output);
        final var actual = csvReader.readData(output);
        assertEquals(1000, actual.size());
    }
}