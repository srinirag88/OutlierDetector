package com.cga.outlier.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenericUtils {
    /**
     * Parses the date time with the format dd/MM/yyyy
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
