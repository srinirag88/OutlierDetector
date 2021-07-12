package com.srini.outlier.input.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.cga.outlier.data.TestData.PriceData.TEST_INPUT_1;
import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    final CSVReader csvReader = new CSVReader();

    /**
     * Tests the csv read part and asserts all data is read.
     *
     * @throws IOException If any error occurs.
     */
    @Test
    void readData() throws IOException {
        final var testData = TEST_INPUT_1.get();
        final var data = csvReader.readData(testData);
        assertEquals(1002, data.size());
    }
}