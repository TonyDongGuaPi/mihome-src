package org.apache.commons.lang;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.commons.lang.text.StrBuilder;

public class StringEscapeUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char f3372a = ',';
    private static final char b = '\"';
    private static final String c = String.valueOf('\"');
    private static final char[] d = {',', '\"', CharUtils.b, 10};

    public static String a(String str) {
        return a(str, false, false);
    }

    public static void a(Writer writer, String str) throws IOException {
        a(writer, str, false, false);
    }

    public static String b(String str) {
        return a(str, true, true);
    }

    public static void b(Writer writer, String str) throws IOException {
        a(writer, str, true, true);
    }

    private static String a(String str, boolean z, boolean z2) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(str.length() * 2);
            a(stringWriter, str, z, z2);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    private static void a(Writer writer, String str, boolean z, boolean z2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt > 4095) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("\\u");
                    stringBuffer.append(a(charAt));
                    writer.write(stringBuffer.toString());
                } else if (charAt > 255) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("\\u0");
                    stringBuffer2.append(a(charAt));
                    writer.write(stringBuffer2.toString());
                } else if (charAt > 127) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("\\u00");
                    stringBuffer3.append(a(charAt));
                    writer.write(stringBuffer3.toString());
                } else if (charAt < ' ') {
                    switch (charAt) {
                        case 8:
                            writer.write(92);
                            writer.write(98);
                            break;
                        case 9:
                            writer.write(92);
                            writer.write(116);
                            break;
                        case 10:
                            writer.write(92);
                            writer.write(110);
                            break;
                        case 12:
                            writer.write(92);
                            writer.write(102);
                            break;
                        case 13:
                            writer.write(92);
                            writer.write(114);
                            break;
                        default:
                            if (charAt <= 15) {
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("\\u000");
                                stringBuffer4.append(a(charAt));
                                writer.write(stringBuffer4.toString());
                                break;
                            } else {
                                StringBuffer stringBuffer5 = new StringBuffer();
                                stringBuffer5.append("\\u00");
                                stringBuffer5.append(a(charAt));
                                writer.write(stringBuffer5.toString());
                                break;
                            }
                    }
                } else if (charAt == '\"') {
                    writer.write(92);
                    writer.write(34);
                } else if (charAt == '\'') {
                    if (z) {
                        writer.write(92);
                    }
                    writer.write(39);
                } else if (charAt == '/') {
                    if (z2) {
                        writer.write(92);
                    }
                    writer.write(47);
                } else if (charAt != '\\') {
                    writer.write(charAt);
                } else {
                    writer.write(92);
                    writer.write(92);
                }
            }
        }
    }

    private static String a(char c2) {
        return Integer.toHexString(c2).toUpperCase(Locale.ENGLISH);
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(str.length());
            c(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static void c(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int length = str.length();
            StrBuilder strBuilder = new StrBuilder(4);
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (z) {
                    strBuilder.a(charAt);
                    if (strBuilder.c() == 4) {
                        try {
                            writer.write((char) Integer.parseInt(strBuilder.toString(), 16));
                            strBuilder.a(0);
                            z = false;
                        } catch (NumberFormatException e) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Unable to parse unicode value: ");
                            stringBuffer.append(strBuilder);
                            throw new NestableRuntimeException(stringBuffer.toString(), e);
                        }
                    }
                } else if (!z2) {
                    if (charAt == '\\') {
                        z2 = true;
                    } else {
                        writer.write(charAt);
                    }
                } else if (charAt == '\"') {
                    writer.write(34);
                } else if (charAt == '\'') {
                    writer.write(39);
                } else if (charAt == '\\') {
                    writer.write(92);
                } else if (charAt == 'b') {
                    writer.write(8);
                } else if (charAt == 'f') {
                    writer.write(12);
                } else if (charAt == 'n') {
                    writer.write(10);
                } else if (charAt != 'r') {
                    switch (charAt) {
                        case 't':
                            writer.write(9);
                            break;
                        case 'u':
                            z = true;
                            break;
                        default:
                            writer.write(charAt);
                            break;
                    }
                } else {
                    writer.write(13);
                }
                z2 = false;
            }
            if (z2) {
                writer.write(92);
            }
        }
    }

    public static String d(String str) {
        return c(str);
    }

    public static void d(Writer writer, String str) throws IOException {
        c(writer, str);
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        try {
            double length = (double) str.length();
            Double.isNaN(length);
            StringWriter stringWriter = new StringWriter((int) (length * 1.5d));
            e(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static void e(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.e.a(writer, str);
        }
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        try {
            double length = (double) str.length();
            Double.isNaN(length);
            StringWriter stringWriter = new StringWriter((int) (length * 1.5d));
            f(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static void f(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.e.b(writer, str);
        }
    }

    public static void g(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.c.a(writer, str);
        }
    }

    public static String g(String str) {
        if (str == null) {
            return null;
        }
        return Entities.c.b(str);
    }

    public static void h(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.c.b(writer, str);
        }
    }

    public static String h(String str) {
        if (str == null) {
            return null;
        }
        return Entities.c.c(str);
    }

    public static String i(String str) {
        if (str == null) {
            return null;
        }
        return StringUtils.e(str, "'", "''");
    }

    public static String j(String str) {
        if (StringUtils.e(str, d)) {
            return str;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            i(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static void i(Writer writer, String str) throws IOException {
        if (!StringUtils.e(str, d)) {
            writer.write(34);
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '\"') {
                    writer.write(34);
                }
                writer.write(charAt);
            }
            writer.write(34);
        } else if (str != null) {
            writer.write(str);
        }
    }

    public static String k(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            j(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static void j(Writer writer, String str) throws IOException {
        if (str != null) {
            if (str.length() < 2) {
                writer.write(str);
            } else if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
                String substring = str.substring(1, str.length() - 1);
                if (StringUtils.b(substring, d)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(c);
                    stringBuffer.append(c);
                    str = StringUtils.e(substring, stringBuffer.toString(), c);
                }
                writer.write(str);
            } else {
                writer.write(str);
            }
        }
    }
}
