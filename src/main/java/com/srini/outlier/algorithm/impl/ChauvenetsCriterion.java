package com.srini.outlier.algorithm.impl;

import com.cga.outlier.algorithm.OutlierAlgorithm;
import com.cga.outlier.algorithm.OutlierResult;
import com.cga.outlier.domian.PriceData;
import com.srini.outlier.algorithm.OutlierAlgorithm;
import com.srini.outlier.algorithm.OutlierResult;
import com.srini.outlier.domian.PriceData;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.Logger.Level.INFO;

public class ChauvenetsCriterion implements OutlierAlgorithm {

    private static final System.Logger LOGGER = System.getLogger(ChauvenetsCriterion.class.getName());

    /**
     * {@inheritDoc}
     *
     * @implNote An implementation to Chauvenets Criterion to get the outliers in the data.
     */
    @Override
    public OutlierResult findOutliers(List<PriceData> inputData) {
        LOGGER.log(INFO, "Applying Chauvenets Criterion for the data");
        final var priceData = inputData.stream().mapToDouble(data -> data.price().doubleValue()).toArray();
        final var dStats = new DescriptiveStatistics(priceData);
        final var stDev = dStats.getStandardDeviation();
        final var mean = dStats.getMean();
        final var outliers = new ArrayList<PriceData>();
        final var cleanedData = new ArrayList<PriceData>();
        // Fixing the significanceLevel as constant for now. Can be made a variable in future implementation
        final var significanceLevel = .01;
        if (dStats.getStandardDeviation() > 0) {
            final var normalDistribution = new NormalDistribution(mean, stDev);
            inputData.forEach(data -> {
                final var pValue = normalDistribution.cumulativeProbability(data.price().doubleValue());
                if (pValue < significanceLevel | pValue > 1 - significanceLevel) {
                    outliers.add(data);
                } else {
                    cleanedData.add(data);
                }
            });
        }
        return new OutlierResult(outliers, cleanedData);
    }
}
