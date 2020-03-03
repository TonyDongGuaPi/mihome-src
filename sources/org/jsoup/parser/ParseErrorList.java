package org.jsoup.parser;

import java.util.ArrayList;

public class ParseErrorList extends ArrayList<ParseError> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3679a = 16;
    private final int maxSize;

    ParseErrorList(int i, int i2) {
        super(i);
        this.maxSize = i2;
    }

    /* access modifiers changed from: package-private */
    public boolean canAddError() {
        return size() < this.maxSize;
    }

    /* access modifiers changed from: package-private */
    public int getMaxSize() {
        return this.maxSize;
    }

    public static ParseErrorList noTracking() {
        return new ParseErrorList(0, 0);
    }

    public static ParseErrorList tracking(int i) {
        return new ParseErrorList(16, i);
    }
}
