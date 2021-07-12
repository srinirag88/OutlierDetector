package com.srini.outlier.output.impl;

import com.cga.outlier.input.impl.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


import static com.cga.outlier.data.TestData.PriceData.TEST_INPUT_1;

class CSVWriterTest {

    private final CSVReader csvReader = new CSVReader();

    private final CSVWriter csvWriter = new CSVWriter();

    /**
     * Tests the csv write part and asserts all data is written.
     *
     * @throws IOException If any error occurs.
     */
    @Test
    void writeData() throws IOException {
        final var testData = TEST_INPUT_1.get();
        final var expected = csvReader.readData(testData);
        final var output = Files.createTempFile("priceData", "");
        csvWriter.writeData(expected, output);
        //Read back the data to test.
        final var actual = csvReader.readData(testData);
        Assertions.assertEquals(expected, actual);
    }
}