package com.cga.outlier.services;

import java.nio.file.Path;

public interface OutlierDetectorService {

    void processOutliers(Path input, Path output);
}
