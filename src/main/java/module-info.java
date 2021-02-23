module com.cga.outlier {
    requires commons.cli;
    requires commons.math3;
    exports com.cga.outlier.main;
    uses com.cga.outlier.input.InputReader;
    uses com.cga.outlier.algorithm.OutlierAlgorithm;
    uses com.cga.outlier.output.OutputWriter;
    uses com.cga.outlier.services.OutlierDetectorService;
    provides com.cga.outlier.input.InputReader with com.cga.outlier.input.impl.CSVReader;
    provides com.cga.outlier.output.OutputWriter with com.cga.outlier.output.impl.CSVWriter;
    provides com.cga.outlier.algorithm.OutlierAlgorithm with com.cga.outlier.algorithm.impl.ChauvenetsCriterion;
    provides com.cga.outlier.services.OutlierDetectorService with com.cga.outlier.services.impl.OutlierDetectorServiceImpl;
}