package org.apache.commons.lang.time;

import com.taobao.weex.common.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;

public class DurationFormatUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3414a = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
    static final Object b = Constants.Name.Y;
    static final Object c = "M";
    static final Object d = "d";
    static final Object e = "H";
    static final Object f = "m";
    static final Object g = "s";
    static final Object h = "S";

    public static String a(long j) {
        return a(j, "H:mm:ss.SSS");
    }

    public static String b(long j) {
        return a(j, f3414a, false);
    }

    public static String a(long j, String str) {
        return a(j, str, true);
    }

    public static String a(long j, String str, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        Token[] a2 = a(str);
        if (Token.a(a2, d)) {
            int i5 = (int) (j / 86400000);
            j -= ((long) i5) * 86400000;
            i = i5;
        } else {
            i = 0;
        }
        if (Token.a(a2, e)) {
            int i6 = (int) (j / 3600000);
            j -= ((long) i6) * 3600000;
            i2 = i6;
        } else {
            i2 = 0;
        }
        if (Token.a(a2, f)) {
            int i7 = (int) (j / 60000);
            j -= ((long) i7) * 60000;
            i3 = i7;
        } else {
            i3 = 0;
        }
        if (Token.a(a2, g)) {
            int i8 = (int) (j / 1000);
            j -= ((long) i8) * 1000;
            i4 = i8;
        } else {
            i4 = 0;
        }
        return a(a2, 0, 0, i, i2, i3, i4, Token.a(a2, h) ? (int) j : 0, z);
    }

    public static String a(long j, boolean z, boolean z2) {
        String a2 = a(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" ");
            stringBuffer.append(a2);
            a2 = stringBuffer.toString();
            String d2 = StringUtils.d(a2, " 0 days", "");
            if (d2.length() != a2.length()) {
                String d3 = StringUtils.d(d2, " 0 hours", "");
                if (d3.length() != d2.length()) {
                    a2 = StringUtils.d(d3, " 0 minutes", "");
                    if (a2.length() != a2.length()) {
                        a2 = StringUtils.d(a2, " 0 seconds", "");
                    }
                } else {
                    a2 = d2;
                }
            }
            if (a2.length() != 0) {
                a2 = a2.substring(1);
            }
        }
        if (z2) {
            String d4 = StringUtils.d(a2, " 0 seconds", "");
            if (d4.length() != a2.length()) {
                a2 = StringUtils.d(d4, " 0 minutes", "");
                if (a2.length() != d4.length()) {
                    String d5 = StringUtils.d(a2, " 0 hours", "");
                    if (d5.length() != a2.length()) {
                        a2 = StringUtils.d(d5, " 0 days", "");
                    }
                } else {
                    a2 = d4;
                }
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(" ");
        stringBuffer2.append(a2);
        return StringUtils.d(StringUtils.d(StringUtils.d(StringUtils.d(stringBuffer2.toString(), " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String a(long j, long j2) {
        return a(j, j2, f3414a, false, TimeZone.getDefault());
    }

    public static String a(long j, long j2, String str) {
        return a(j, j2, str, true, TimeZone.getDefault());
    }

    public static String a(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i;
        int i2;
        int i3;
        Token[] a2 = a(str);
        Calendar instance = Calendar.getInstance(timeZone);
        instance.setTime(new Date(j));
        Calendar instance2 = Calendar.getInstance(timeZone);
        instance2.setTime(new Date(j2));
        int i4 = instance2.get(14) - instance.get(14);
        int i5 = instance2.get(13) - instance.get(13);
        int i6 = instance2.get(12) - instance.get(12);
        int i7 = instance2.get(11) - instance.get(11);
        int i8 = instance2.get(5) - instance.get(5);
        int i9 = instance2.get(2) - instance.get(2);
        int i10 = instance2.get(1) - instance.get(1);
        while (i4 < 0) {
            i4 += 1000;
            i5--;
        }
        while (i5 < 0) {
            i5 += 60;
            i6--;
        }
        while (i6 < 0) {
            i6 += 60;
            i7--;
        }
        while (i7 < 0) {
            i7 += 24;
            i8--;
        }
        int i11 = 0;
        if (Token.a(a2, c)) {
            while (i8 < 0) {
                i8 += instance.getActualMaximum(5);
                i9--;
                instance.add(2, 1);
            }
            while (i9 < 0) {
                i9 += 12;
                i10--;
            }
            if (!Token.a(a2, b) && i10 != 0) {
                while (i10 != 0) {
                    i9 += i10 * 12;
                    i10 = 0;
                }
            }
            i = i9;
        } else {
            if (!Token.a(a2, b)) {
                int i12 = instance2.get(1);
                if (i9 < 0) {
                    i12--;
                }
                while (instance.get(1) != i12) {
                    int actualMaximum = i8 + (instance.getActualMaximum(6) - instance.get(6));
                    if ((instance instanceof GregorianCalendar) && instance.get(2) == 1 && instance.get(5) == 29) {
                        actualMaximum++;
                    }
                    instance.add(1, 1);
                    i8 = actualMaximum + instance.get(6);
                }
                i10 = 0;
            }
            while (instance.get(2) != instance2.get(2)) {
                i8 += instance.getActualMaximum(5);
                instance.add(2, 1);
            }
            i = 0;
            while (i8 < 0) {
                i8 += instance.getActualMaximum(5);
                i--;
                instance.add(2, 1);
            }
        }
        int i13 = i10;
        if (!Token.a(a2, d)) {
            i7 += i8 * 24;
            i2 = 0;
        } else {
            i2 = i8;
        }
        if (!Token.a(a2, e)) {
            i6 += i7 * 60;
            i7 = 0;
        }
        if (!Token.a(a2, f)) {
            i5 += i6 * 60;
            i6 = 0;
        }
        if (!Token.a(a2, g)) {
            i3 = i4 + (i5 * 1000);
        } else {
            i3 = i4;
            i11 = i5;
        }
        return a(a2, i13, i, i2, i7, i6, i11, i3, z);
    }

    static String a(Token[] tokenArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        StrBuilder strBuilder = new StrBuilder();
        int i8 = i7;
        boolean z2 = false;
        for (Token token : tokenArr) {
            Object c2 = token.c();
            int b2 = token.b();
            if (c2 instanceof StringBuffer) {
                strBuilder.c(c2.toString());
            } else {
                if (c2 == b) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i), b2, '0') : Integer.toString(i));
                } else if (c2 == c) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i2), b2, '0') : Integer.toString(i2));
                } else if (c2 == d) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i3), b2, '0') : Integer.toString(i3));
                } else if (c2 == e) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i4), b2, '0') : Integer.toString(i4));
                } else if (c2 == f) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i5), b2, '0') : Integer.toString(i5));
                } else if (c2 == g) {
                    strBuilder.c(z ? StringUtils.b(Integer.toString(i6), b2, '0') : Integer.toString(i6));
                    z2 = true;
                } else if (c2 == h) {
                    if (z2) {
                        i8 += 1000;
                        strBuilder.c((z ? StringUtils.b(Integer.toString(i8), b2, '0') : Integer.toString(i8)).substring(1));
                    } else {
                        strBuilder.c(z ? StringUtils.b(Integer.toString(i8), b2, '0') : Integer.toString(i8));
                    }
                }
                z2 = false;
            }
        }
        return strBuilder.toString();
    }

    static Token[] a(String str) {
        Object obj;
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList(charArray.length);
        StringBuffer stringBuffer = null;
        Token token = null;
        boolean z = false;
        for (char c2 : charArray) {
            if (!z || c2 == '\'') {
                if (c2 != '\'') {
                    if (c2 == 'H') {
                        obj = e;
                    } else if (c2 == 'M') {
                        obj = c;
                    } else if (c2 == 'S') {
                        obj = h;
                    } else if (c2 == 'd') {
                        obj = d;
                    } else if (c2 == 'm') {
                        obj = f;
                    } else if (c2 == 's') {
                        obj = g;
                    } else if (c2 != 'y') {
                        if (stringBuffer == null) {
                            stringBuffer = new StringBuffer();
                            arrayList.add(new Token(stringBuffer));
                        }
                        stringBuffer.append(c2);
                        obj = null;
                    } else {
                        obj = b;
                    }
                } else if (z) {
                    stringBuffer = null;
                    obj = null;
                    z = false;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    arrayList.add(new Token(stringBuffer2));
                    obj = null;
                    stringBuffer = stringBuffer2;
                    z = true;
                }
                if (obj != null) {
                    if (token == null || token.c() != obj) {
                        token = new Token(obj);
                        arrayList.add(token);
                    } else {
                        token.a();
                    }
                    stringBuffer = null;
                }
            } else {
                stringBuffer.append(c2);
            }
        }
        return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
    }

    static class Token {

        /* renamed from: a  reason: collision with root package name */
        private Object f3415a;
        private int b;

        static boolean a(Token[] tokenArr, Object obj) {
            for (Token c : tokenArr) {
                if (c.c() == obj) {
                    return true;
                }
            }
            return false;
        }

        Token(Object obj) {
            this.f3415a = obj;
            this.b = 1;
        }

        Token(Object obj, int i) {
            this.f3415a = obj;
            this.b = i;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.b++;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public Object c() {
            return this.f3415a;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.f3415a.getClass() != token.f3415a.getClass() || this.b != token.b) {
                return false;
            }
            if (this.f3415a instanceof StringBuffer) {
                return this.f3415a.toString().equals(token.f3415a.toString());
            }
            if (this.f3415a instanceof Number) {
                return this.f3415a.equals(token.f3415a);
            }
            if (this.f3415a == token.f3415a) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f3415a.hashCode();
        }

        public String toString() {
            return StringUtils.d(this.f3415a.toString(), this.b);
        }
    }
}
