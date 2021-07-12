package com.srini.outlier.main;


import com.srini.outlier.command.OutlierDetectorOptions;
import com.srini.outlier.command.RelaxedDefaultParser;
import com.srini.outlier.services.OutlierDetectorService;
import com.srini.outlier.command.OutlierDetectorOptions;
import com.srini.outlier.command.RelaxedDefaultParser;
import com.srini.outlier.services.OutlierDetectorService;
import org.apache.commons.cli.ParseException;

import java.nio.file.Paths;
import java.util.ServiceLoader;

/**
 * Main for the Outlier Detector App.
 */
public class Main {

    final private static OutlierDetectorService outlierDetectorService = ServiceLoader.load(OutlierDetectorService.class)
            .findFirst().orElseThrow(() -> new RuntimeException("Unable to find providers"));

    public static void main(String[] args) {
        final var outlierDetectorOptions = new OutlierDetectorOptions();
        final var parser = new RelaxedDefaultParser();
        try {
            // parse the command line arguments
            final var options = outlierDetectorOptions.createOptions();
            final var line = parser.parse(options, args);
            if (line.hasOption("inputFile") && line.hasOption("outputFile")) {
                final var inputPath = Paths.get(line.getOptionValue("inputFile"));
                final var outputPat = Paths.get(line.getOptionValue("outputFile"));
                outlierDetectorService.processOutliers(inputPath, outputPat);
            }

        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }
}
