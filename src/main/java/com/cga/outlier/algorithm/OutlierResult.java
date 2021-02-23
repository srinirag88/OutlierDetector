package com.cga.outlier.algorithm;

import com.cga.outlier.domian.PriceData;

import java.util.List;

/**
 * Holder record for the result data.
 */
public record OutlierResult(List<PriceData> outlier, List<PriceData> cleanedData) {
}
