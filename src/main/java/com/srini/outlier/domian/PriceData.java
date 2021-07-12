package com.srini.outlier.domian;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the price data from the input source.
 */
public record PriceData(LocalDate date, BigDecimal price) {
    public PriceData {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("date is later than now");
        }
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," + price.setScale(7, RoundingMode.HALF_DOWN);
    }
}
