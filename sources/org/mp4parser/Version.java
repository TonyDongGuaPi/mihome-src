package org.mp4parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.logging.Logger;

public class Version {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3734a;
    private static final Logger b = Logger.getLogger(Version.class.getName());

    static {
        String str;
        try {
            str = new LineNumberReader(new InputStreamReader(Version.class.getResourceAsStream("/version.txt"))).readLine();
        } catch (IOException e) {
            b.warning(e.getMessage());
            str = "unknown";
        }
        f3734a = str;
    }
}
