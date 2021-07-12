package com.srini.outlier.command;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OutlierDetectorOptions {

    /**
     * Defines the supported command line args
     *
     * @return {@link Options}
     */
    public Options createOptions() {
        final var inputFile = Option.builder().argName("inputFile")
                .optionalArg(false)
                .longOpt("inputFile")
                .hasArg()
                .desc("The Input CSV file to be processed")
                .required()
                .build();

        final var outputFile = Option.builder().argName("outputFile")
                .optionalArg(false)
                .longOpt("outputFile")
                .hasArg()
                .desc("The Output CSV file for the cleaned data")
                .required()
                .build();
        final var options = new Options();
        options.addOption(inputFile).addOption(outputFile);
        return options;
    }
}
