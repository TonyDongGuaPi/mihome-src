package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Resolution;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ParseUtil {
    static boolean a(char c) {
        return c == ' ' || c == 9 || c == 13 || c == 10;
    }

    private static byte b(char c) {
        return c >= 'A' ? (byte) ((c & 15) + 9) : (byte) (c & 15);
    }

    ParseUtil() {
    }

    public static int a(String str, String str2) throws ParseException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw ParseException.create(ParseExceptionType.NOT_JAVA_INTEGER, str2, str);
        }
    }

    public static <T extends Enum<T>> T a(String str, Class<T> cls, String str2) throws ParseException {
        try {
            return Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            throw ParseException.create(ParseExceptionType.NOT_JAVA_ENUM, str2, str);
        }
    }

    public static String b(String str, String str2) throws ParseException {
        Matcher matcher = Constants.al.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new ParseException(ParseExceptionType.INVALID_DATE_TIME_FORMAT, str2);
    }

    public static float c(String str, String str2) throws ParseException {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            throw ParseException.create(ParseExceptionType.NOT_JAVA_FLOAT, str2, str);
        }
    }

    public static List<Byte> d(String str, String str2) throws ParseException {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Constants.af.matcher(str.toUpperCase(Locale.US));
        if (matcher.matches()) {
            for (char b : matcher.group(1).toCharArray()) {
                arrayList.add(Byte.valueOf(b(b)));
            }
            return arrayList;
        }
        throw ParseException.create(ParseExceptionType.INVALID_HEXADECIMAL_STRING, str2, str);
    }

    public static boolean a(Attribute attribute, String str) throws ParseException {
        if (attribute.b.equals(Constants.ad)) {
            return true;
        }
        if (attribute.b.equals(Constants.ae)) {
            return false;
        }
        throw ParseException.create(ParseExceptionType.NOT_YES_OR_NO, str, attribute.toString());
    }

    public static Resolution e(String str, String str2) throws ParseException {
        Matcher matcher = Constants.ag.matcher(str);
        if (matcher.matches()) {
            return new Resolution(a(matcher.group(1), str2), a(matcher.group(2), str2));
        }
        throw new ParseException(ParseExceptionType.INVALID_RESOLUTION_FORMAT, str2);
    }

    public static String f(String str, String str2) throws ParseException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (i != 0 || charAt == '\"') {
                if (i2 != 2) {
                    if (i == str.length() - 1) {
                        if (charAt != '\"' || z) {
                            throw new ParseException(ParseExceptionType.UNCLOSED_QUOTED_STRING, str2);
                        }
                    } else if (z) {
                        sb.append(charAt);
                        z = false;
                    } else if (charAt == '\\') {
                        z = true;
                    } else if (charAt == '\"') {
                        i2++;
                    } else {
                        sb.append(charAt);
                    }
                    i++;
                } else if (a(charAt)) {
                    throw new ParseException(ParseExceptionType.ILLEGAL_WHITESPACE, str2);
                } else {
                    throw new ParseException(ParseExceptionType.INVALID_QUOTED_STRING, str2);
                }
            } else if (a(charAt)) {
                throw new ParseException(ParseExceptionType.ILLEGAL_WHITESPACE, str2);
            } else {
                throw new ParseException(ParseExceptionType.INVALID_QUOTED_STRING, str2);
            }
        }
        return sb.toString();
    }

    public static String a(String str, Encoding encoding) throws ParseException {
        try {
            return URLDecoder.decode(str.replace("+", "%2B"), encoding.value);
        } catch (UnsupportedEncodingException unused) {
            throw new ParseException(ParseExceptionType.INTERNAL_ERROR);
        }
    }

    public static Matcher a(Pattern pattern, String str, String str2) throws ParseException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return matcher;
        }
        throw ParseException.create(ParseExceptionType.BAD_EXT_TAG_FORMAT, str2, str);
    }

    public static <T> void a(String str, T t, ParseState parseState, Map<String, ? extends AttributeParser<T>> map, String str2) throws ParseException {
        for (Attribute next : g(str, str2)) {
            if (map.containsKey(next.f5937a)) {
                ((AttributeParser) map.get(next.f5937a)).a(next, t, parseState);
            } else {
                throw ParseException.create(ParseExceptionType.INVALID_ATTRIBUTE_NAME, str2, str);
            }
        }
    }

    public static List<Attribute> g(String str, String str2) throws ParseException {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (String next : h(str, str2)) {
            int indexOf = next.indexOf("=");
            int indexOf2 = next.indexOf("\"");
            if (indexOf == -1 || (indexOf2 != -1 && indexOf2 < indexOf)) {
                throw ParseException.create(ParseExceptionType.MISSING_ATTRIBUTE_SEPARATOR, str2, arrayList.toString());
            }
            String trim = next.substring(0, indexOf).trim();
            String substring = next.substring(indexOf + 1);
            if (trim.isEmpty()) {
                throw ParseException.create(ParseExceptionType.MISSING_ATTRIBUTE_NAME, str2, arrayList.toString());
            } else if (substring.isEmpty()) {
                throw ParseException.create(ParseExceptionType.MISSING_ATTRIBUTE_VALUE, str2, arrayList.toString());
            } else if (hashSet.add(trim)) {
                arrayList.add(new Attribute(trim, substring));
            } else {
                throw ParseException.create(ParseExceptionType.MULTIPLE_ATTRIBUTE_NAME_INSTANCES, str2, arrayList.toString());
            }
        }
        return arrayList;
    }

    public static List<String> h(String str, String str2) throws ParseException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int indexOf = str.indexOf(":") + 1;
        boolean z = false;
        boolean z2 = false;
        for (int i = indexOf; i < str.length(); i++) {
            if (!z) {
                char charAt = str.charAt(i);
                if (charAt == ',') {
                    arrayList.add(Integer.valueOf(i));
                } else if (charAt == '\"') {
                    z = true;
                }
            } else if (z2) {
                z2 = false;
            } else {
                char charAt2 = str.charAt(i);
                if (charAt2 == '\\') {
                    z2 = true;
                } else if (charAt2 == '\"') {
                    z = false;
                }
            }
        }
        if (!z) {
            for (Integer num : arrayList) {
                arrayList2.add(str.substring(indexOf, num.intValue()));
                indexOf = num.intValue() + 1;
            }
            arrayList2.add(str.substring(indexOf));
            return arrayList2;
        }
        throw new ParseException(ParseExceptionType.UNCLOSED_QUOTED_STRING, str2);
    }
}
