package org.apache.commons.cli;

public interface CommandLineParser {
    CommandLine a(Options options, String[] strArr) throws ParseException;

    CommandLine b(Options options, String[] strArr, boolean z) throws ParseException;
}
