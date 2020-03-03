package org.apache.commons.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TypeHandler {
    public static Object a(String str, Object obj) throws ParseException {
        return a(str, (Class) obj);
    }

    public static Object a(String str, Class cls) throws ParseException {
        if (PatternOptionBuilder.f3196a == cls) {
            return str;
        }
        if (PatternOptionBuilder.b == cls) {
            return a(str);
        }
        if (PatternOptionBuilder.c == cls) {
            return b(str);
        }
        if (PatternOptionBuilder.d == cls) {
            return d(str);
        }
        if (PatternOptionBuilder.e == cls) {
            return c(str);
        }
        if (PatternOptionBuilder.g == cls) {
            return f(str);
        }
        if (PatternOptionBuilder.f == cls) {
            return f(str);
        }
        if (PatternOptionBuilder.h == cls) {
            return g(str);
        }
        if (PatternOptionBuilder.i == cls) {
            return e(str);
        }
        return null;
    }

    public static Object a(String str) throws ParseException {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(e.getClass().getName());
                stringBuffer.append("; Unable to create an instance of: ");
                stringBuffer.append(str);
                throw new ParseException(stringBuffer.toString());
            }
        } catch (ClassNotFoundException unused) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to find the class: ");
            stringBuffer2.append(str);
            throw new ParseException(stringBuffer2.toString());
        }
    }

    public static Number b(String str) throws ParseException {
        try {
            if (str.indexOf(46) != -1) {
                return Double.valueOf(str);
            }
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public static Class c(String str) throws ParseException {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to find the class: ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString());
        }
    }

    public static Date d(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static URL e(String str) throws ParseException {
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to parse the URL: ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString());
        }
    }

    public static File f(String str) throws ParseException {
        return new File(str);
    }

    public static File[] g(String str) throws ParseException {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
