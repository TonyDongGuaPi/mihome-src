package org.cybergarage.xml;

public class ParserException extends Exception {
    public ParserException(Exception exc) {
        super(exc);
    }

    public ParserException(String str) {
        super(str);
    }
}
