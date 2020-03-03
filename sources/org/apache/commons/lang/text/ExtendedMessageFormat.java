package org.apache.commons.lang.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

public class ExtendedMessageFormat extends MessageFormat {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3398a = 31;
    private static final String b = "";
    private static final String c = "''";
    private static final char d = ',';
    private static final char e = '}';
    private static final char f = '{';
    private static final char g = '\'';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map registry;
    private String toPattern;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, (Map) null);
    }

    public ExtendedMessageFormat(String str, Map map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map map) {
        super("");
        setLocale(locale);
        this.registry = map;
        applyPattern(str);
    }

    public String toPattern() {
        return this.toPattern;
    }

    public final void applyPattern(String str) {
        Format format;
        String str2;
        if (this.registry == null) {
            super.applyPattern(str);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StrBuilder strBuilder = new StrBuilder(str.length());
        int i = 0;
        ParsePosition parsePosition = new ParsePosition(0);
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char c2 = charArray[parsePosition.getIndex()];
            boolean z = true;
            if (c2 != '\'') {
                if (c2 == '{') {
                    i2++;
                    c(str, parsePosition);
                    int index = parsePosition.getIndex();
                    strBuilder.a('{').e(a(str, a(parsePosition)));
                    c(str, parsePosition);
                    if (charArray[parsePosition.getIndex()] == ',') {
                        str2 = b(str, a(parsePosition));
                        format = a(str2);
                        if (format == null) {
                            strBuilder.a(',').c(str2);
                        }
                    } else {
                        str2 = null;
                        format = null;
                    }
                    arrayList.add(format);
                    if (format == null) {
                        str2 = null;
                    }
                    arrayList2.add(str2);
                    Validate.a(arrayList.size() == i2);
                    if (arrayList2.size() != i2) {
                        z = false;
                    }
                    Validate.a(z);
                    if (charArray[parsePosition.getIndex()] != '}') {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unreadable format element at position ");
                        stringBuffer.append(index);
                        throw new IllegalArgumentException(stringBuffer.toString());
                    }
                }
                strBuilder.a(charArray[parsePosition.getIndex()]);
                a(parsePosition);
            } else {
                a(str, parsePosition, strBuilder, true);
            }
        }
        super.applyPattern(strBuilder.toString());
        this.toPattern = a(super.toPattern(), arrayList2);
        if (a((Collection) arrayList)) {
            Format[] formats = getFormats();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Format format2 = (Format) it.next();
                if (format2 != null) {
                    formats[i] = format2;
                }
                i++;
            }
            super.setFormats(formats);
        }
    }

    public void setFormat(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormatByArgumentIndex(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !super.equals(obj) || ObjectUtils.c((Object) getClass(), (Object) obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        return !ObjectUtils.c((Object) this.toPattern, (Object) extendedMessageFormat.toPattern) && !ObjectUtils.c((Object) this.registry, (Object) extendedMessageFormat.registry);
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + ObjectUtils.a(this.registry)) * 31) + ObjectUtils.a(this.toPattern);
    }

    private Format a(String str) {
        String str2;
        if (this.registry != null) {
            int indexOf = str.indexOf(44);
            if (indexOf > 0) {
                String trim = str.substring(0, indexOf).trim();
                str2 = str.substring(indexOf + 1).trim();
                str = trim;
            } else {
                str2 = null;
            }
            FormatFactory formatFactory = (FormatFactory) this.registry.get(str);
            if (formatFactory != null) {
                return formatFactory.a(str, str2, getLocale());
            }
        }
        return null;
    }

    private int a(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        c(str, parsePosition);
        StrBuilder strBuilder = new StrBuilder();
        boolean z = false;
        while (!z && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (Character.isWhitespace(charAt)) {
                c(str, parsePosition);
                charAt = str.charAt(parsePosition.getIndex());
                if (!(charAt == ',' || charAt == '}')) {
                    z = true;
                    a(parsePosition);
                }
            }
            if ((charAt == ',' || charAt == '}') && strBuilder.c() > 0) {
                try {
                    return Integer.parseInt(strBuilder.toString());
                } catch (NumberFormatException unused) {
                }
            }
            strBuilder.a(charAt);
            z = !Character.isDigit(charAt);
            a(parsePosition);
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid format argument index at position ");
            stringBuffer.append(index);
            stringBuffer.append(": ");
            stringBuffer.append(str.substring(index, parsePosition.getIndex()));
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Unterminated format element at position ");
        stringBuffer2.append(index);
        throw new IllegalArgumentException(stringBuffer2.toString());
    }

    private String b(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        c(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i = 1;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                a(str, parsePosition, false);
            } else if (charAt == '{') {
                i++;
            } else if (charAt == '}' && i - 1 == 0) {
                return str.substring(index2, parsePosition.getIndex());
            }
            a(parsePosition);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unterminated format element at position ");
        stringBuffer.append(index);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    private String a(String str, ArrayList arrayList) {
        if (!a((Collection) arrayList)) {
            return str;
        }
        StrBuilder strBuilder = new StrBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i = 0;
        int i2 = -1;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                a(str, parsePosition, strBuilder, false);
            } else if (charAt != '{') {
                if (charAt == '}') {
                    i--;
                }
                strBuilder.a(charAt);
                a(parsePosition);
            } else {
                i++;
                if (i == 1) {
                    i2++;
                    strBuilder.a('{').e(a(str, a(parsePosition)));
                    String str2 = (String) arrayList.get(i2);
                    if (str2 != null) {
                        strBuilder.a(',').c(str2);
                    }
                }
            }
        }
        return strBuilder.toString();
    }

    private void c(String str, ParsePosition parsePosition) {
        char[] charArray = str.toCharArray();
        do {
            int a2 = StrMatcher.d().a(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + a2);
            if (a2 <= 0 || parsePosition.getIndex() >= str.length()) {
            }
            int a22 = StrMatcher.d().a(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + a22);
            return;
        } while (parsePosition.getIndex() >= str.length());
    }

    private ParsePosition a(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private StrBuilder a(String str, ParsePosition parsePosition, StrBuilder strBuilder, boolean z) {
        int index = parsePosition.getIndex();
        char[] charArray = str.toCharArray();
        if (!z || charArray[index] != '\'') {
            int i = index;
            for (int index2 = parsePosition.getIndex(); index2 < str.length(); index2++) {
                if (z && str.substring(index2).startsWith(c)) {
                    strBuilder.a(charArray, i, parsePosition.getIndex() - i).a('\'');
                    parsePosition.setIndex(c.length() + index2);
                    i = parsePosition.getIndex();
                } else if (charArray[parsePosition.getIndex()] != '\'') {
                    a(parsePosition);
                } else {
                    a(parsePosition);
                    if (strBuilder == null) {
                        return null;
                    }
                    return strBuilder.a(charArray, i, parsePosition.getIndex() - i);
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unterminated quoted string at position ");
            stringBuffer.append(index);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        a(parsePosition);
        if (strBuilder == null) {
            return null;
        }
        return strBuilder.a('\'');
    }

    private void a(String str, ParsePosition parsePosition, boolean z) {
        a(str, parsePosition, (StrBuilder) null, z);
    }

    private boolean a(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return false;
        }
        for (Object obj : collection) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
