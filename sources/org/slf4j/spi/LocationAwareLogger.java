package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

public interface LocationAwareLogger extends Logger {
    public static final int b = 0;
    public static final int c = 10;
    public static final int d = 20;
    public static final int e = 30;
    public static final int f = 40;

    void a(Marker marker, String str, int i, String str2, Throwable th);
}
