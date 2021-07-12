module com.srini.outlier {
    requires commons.cli;
    requires commons.math3;
    requires java.sql;
    requires org.jooq;
    requires com.zaxxer.hikari;
    exports com.srini.outlier.main;
    uses com.srini.outlier.input.InputReader;
    uses com.srini.outlier.algorithm.OutlierAlgorithm;
    uses com.srini.outlier.output.OutputWriter;
    uses com.srini.outlier.services.OutlierDetectorService;
    provides com.srini.outlier.input.InputReader with com.srini.outlier.input.impl.CSVReader;
    provides com.srini.outlier.output.OutputWriter with com.srini.outlier.output.impl.CSVWriter;
    provides com.srini.outlier.algorithm.OutlierAlgorithm with com.srini.outlier.algorithm.impl.ChauvenetsCriterion;
    provides com.srini.outlier.services.OutlierDetectorService with com.srini.outlier.services.impl.OutlierDetectorServiceImpl;
}