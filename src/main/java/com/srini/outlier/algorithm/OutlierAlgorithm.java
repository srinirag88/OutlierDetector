package com.srini.outlier.algorithm;

import com.cga.outlier.domian.PriceData;
import com.srini.outlier.domian.PriceData;

import java.util.List;

public interface OutlierAlgorithm {

    /**
     * Takes the input data and find out the outliers in the data
     **/
    OutlierResult findOutliers(final List<PriceData> inputData);
}
