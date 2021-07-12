package com.srini.outlier.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;

public class RelaxedDefaultParser extends DefaultParser {
    /**
     * {@inheritDoc}
     *
     * @implNote An implementation to ignore unknown args.
     */
    @Override
    public CommandLine parse(Options options, String[] arguments) throws ParseException {
        final var knownArgs = new ArrayList<String>();
        for (int i = 0; i < arguments.length; i++) {
            if (options.hasOption(arguments[i])) {
                knownArgs.add(arguments[i]);
                if (i + 1 < arguments.length && options.getOption(arguments[i]).hasArg()) {
                    knownArgs.add(arguments[i + 1]);
                }
            }
        }
        return super.parse(options, knownArgs.toArray(String[]::new));
    }
}
