package com.srini.outlier.input;

import com.cga.outlier.domian.PriceData;
import com.srini.outlier.domian.PriceData;

import java.io.IOException;
import java.util.List;

public interface InputReader<T extends Object> {
    /**
     * Reads the data into the memory.
     *
     * @return List of {@link PriceData}
     */
    List<PriceData> readData(T dataSource) throws IOException;
}
