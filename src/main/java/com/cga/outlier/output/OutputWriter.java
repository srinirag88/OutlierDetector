package com.cga.outlier.output;

import com.cga.outlier.domian.PriceData;

import java.io.IOException;
import java.util.List;

public interface OutputWriter<T> {
    /**
     * Write the data into destination from the memory
     */
    void writeData(List<PriceData> priceData, T destination) throws IOException;
}
