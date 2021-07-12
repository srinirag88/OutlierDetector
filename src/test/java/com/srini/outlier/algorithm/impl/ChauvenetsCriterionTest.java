package com.srini.outlier.algorithm.impl;

import com.srini.outlier.domian.PriceData;
import com.srini.outlier.input.impl.CSVReader;
import com.srini.outlier.domian.PriceData;
import com.srini.outlier.input.impl.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.srini.outlier.data.TestData.PriceData.TEST_INPUT_1;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChauvenetsCriterionTest {

    /**
     * Tests the outlier algorithm an checks if the outlier is found given the input.
     *
     * @throws IOException If any error occurs.
     */
    @Test
    void findOutliers() throws IOException {
        final var testData = TEST_INPUT_1.get();
        final var csvReader = new CSVReader();
        final var data = csvReader.readData(testData);
        final var outlierAlg = new ChauvenetsCriterion();
        final var result = outlierAlg.findOutliers(data);
        final var actual = result.outlier().stream().map(PriceData::toString).collect(joining(","));
        assertEquals("07/01/1960,75.4577362,08/01/1960,200.4577362", actual);
    }
}