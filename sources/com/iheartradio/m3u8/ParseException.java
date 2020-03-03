package com.iheartradio.m3u8;

public class ParseException extends Exception {
    private static final long serialVersionUID = -2217152001086567983L;
    private String mInput;
    private final String mMessageSuffix;
    public final ParseExceptionType type;

    static ParseException create(ParseExceptionType parseExceptionType, String str) {
        return create(parseExceptionType, str, (String) null);
    }

    static ParseException create(ParseExceptionType parseExceptionType, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        if (str2 != null) {
            if (sb.length() > 0) {
                sb.append(" - ");
            }
            sb.append(str2);
        }
        if (sb.length() > 0) {
            return new ParseException(parseExceptionType, sb.toString());
        }
        return new ParseException(parseExceptionType);
    }

    ParseException(ParseExceptionType parseExceptionType) {
        this(parseExceptionType, (String) null);
    }

    ParseException(ParseExceptionType parseExceptionType, String str) {
        this.type = parseExceptionType;
        this.mMessageSuffix = str;
    }

    public String getInput() {
        return this.mInput;
    }

    /* access modifiers changed from: package-private */
    public void setInput(String str) {
        this.mInput = str;
    }

    public String getMessage() {
        if (this.mMessageSuffix == null) {
            return this.type.message;
        }
        return this.type.message + ": " + this.mMessageSuffix;
    }
}
