package com.srini.outlier.data;

import java.nio.file.Path;
import java.util.function.Supplier;

public class TestData {

    public enum PriceData implements Supplier<Path> {

        TEST_INPUT_1 {
            @Override
            public Path get() {
                return getPath("Outliers.csv");
            }
        };

        private static Path getPath(String fileName) {
            try {
                final var url = PriceData.class
                        .getResource("/input/" + fileName);
                return Path.of(url.toURI());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
