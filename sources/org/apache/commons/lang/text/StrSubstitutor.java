package org.apache.commons.lang.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class StrSubstitutor {

    /* renamed from: a  reason: collision with root package name */
    public static final char f3409a = '$';
    public static final StrMatcher b = StrMatcher.b("${");
    public static final StrMatcher c = StrMatcher.b("}");
    private char d;
    private StrMatcher e;
    private StrMatcher f;
    private StrLookup g;
    private boolean h;

    public static String a(Object obj, Map map) {
        return new StrSubstitutor(map).b(obj);
    }

    public static String a(Object obj, Map map, String str, String str2) {
        return new StrSubstitutor(map, str, str2).b(obj);
    }

    public static String a(Object obj, Properties properties) {
        if (properties == null) {
            return obj.toString();
        }
        HashMap hashMap = new HashMap();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            hashMap.put(str, properties.getProperty(str));
        }
        return a(obj, (Map) hashMap);
    }

    public static String a(Object obj) {
        return new StrSubstitutor(StrLookup.b()).b(obj);
    }

    public StrSubstitutor() {
        this((StrLookup) null, b, c, '$');
    }

    public StrSubstitutor(Map map) {
        this(StrLookup.a(map), b, c, '$');
    }

    public StrSubstitutor(Map map, String str, String str2) {
        this(StrLookup.a(map), str, str2, '$');
    }

    public StrSubstitutor(Map map, String str, String str2, char c2) {
        this(StrLookup.a(map), str, str2, c2);
    }

    public StrSubstitutor(StrLookup strLookup) {
        this(strLookup, b, c, '$');
    }

    public StrSubstitutor(StrLookup strLookup, String str, String str2, char c2) {
        a(strLookup);
        b(str);
        c(str2);
        a(c2);
    }

    public StrSubstitutor(StrLookup strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c2) {
        a(strLookup);
        a(strMatcher);
        b(strMatcher2);
        a(c2);
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilder = new StrBuilder(str);
        if (!c(strBuilder, 0, str.length())) {
            return str;
        }
        return strBuilder.toString();
    }

    public String a(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(i2).a(str, i, i2);
        if (!c(a2, 0, i2)) {
            return str.substring(i, i2 + i);
        }
        return a2.toString();
    }

    public String a(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        StrBuilder b2 = new StrBuilder(cArr.length).b(cArr);
        c(b2, 0, cArr.length);
        return b2.toString();
    }

    public String a(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(i2).a(cArr, i, i2);
        c(a2, 0, i2);
        return a2.toString();
    }

    public String a(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(stringBuffer.length()).a(stringBuffer);
        c(a2, 0, a2.c());
        return a2.toString();
    }

    public String a(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(i2).a(stringBuffer, i, i2);
        c(a2, 0, i2);
        return a2.toString();
    }

    public String a(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(strBuilder.c()).a(strBuilder);
        c(a2, 0, a2.c());
        return a2.toString();
    }

    public String a(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder(i2).a(strBuilder, i, i2);
        c(a2, 0, i2);
        return a2.toString();
    }

    public String b(Object obj) {
        if (obj == null) {
            return null;
        }
        StrBuilder a2 = new StrBuilder().a(obj);
        c(a2, 0, a2.c());
        return a2.toString();
    }

    public boolean b(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return false;
        }
        return b(stringBuffer, 0, stringBuffer.length());
    }

    public boolean b(StringBuffer stringBuffer, int i, int i2) {
        if (stringBuffer == null) {
            return false;
        }
        StrBuilder a2 = new StrBuilder(i2).a(stringBuffer, i, i2);
        if (!c(a2, 0, i2)) {
            return false;
        }
        stringBuffer.replace(i, i2 + i, a2.toString());
        return true;
    }

    public boolean b(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return false;
        }
        return c(strBuilder, 0, strBuilder.c());
    }

    public boolean b(StrBuilder strBuilder, int i, int i2) {
        if (strBuilder == null) {
            return false;
        }
        return c(strBuilder, i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean c(StrBuilder strBuilder, int i, int i2) {
        return a(strBuilder, i, i2, (List) null) > 0;
    }

    private int a(StrBuilder strBuilder, int i, int i2, List list) {
        StrMatcher strMatcher;
        StrMatcher strMatcher2;
        int a2;
        StrBuilder strBuilder2 = strBuilder;
        int i3 = i;
        int i4 = i2;
        StrMatcher b2 = b();
        StrMatcher c2 = c();
        char a3 = a();
        boolean z = list == null;
        char[] cArr = strBuilder2.b;
        int i5 = i3 + i4;
        List list2 = list;
        int i6 = i3;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i5) {
            int a4 = b2.a(cArr, i6, i3, i5);
            if (a4 != 0) {
                if (i6 > i3) {
                    int i9 = i6 - 1;
                    if (cArr[i9] == a3) {
                        strBuilder2.d(i9);
                        i8--;
                        i5--;
                        strMatcher2 = b2;
                        strMatcher = c2;
                        cArr = strBuilder2.b;
                        i7 = 1;
                    }
                }
                int i10 = i6 + a4;
                int i11 = i10;
                int i12 = 0;
                while (true) {
                    if (i11 >= i5) {
                        strMatcher2 = b2;
                        strMatcher = c2;
                        i6 = i11;
                        break;
                    } else if (!e() || (a2 = b2.a(cArr, i11, i3, i5)) == 0) {
                        int a5 = c2.a(cArr, i11, i3, i5);
                        if (a5 == 0) {
                            i11++;
                        } else if (i12 == 0) {
                            strMatcher2 = b2;
                            strMatcher = c2;
                            String str = new String(cArr, i10, (i11 - i6) - a4);
                            if (e()) {
                                StrBuilder strBuilder3 = new StrBuilder(str);
                                c(strBuilder3, 0, strBuilder3.c());
                                str = strBuilder3.toString();
                            }
                            int i13 = i11 + a5;
                            if (list2 == null) {
                                list2 = new ArrayList();
                                list2.add(new String(cArr, i3, i4));
                            }
                            a(str, list2);
                            list2.add(str);
                            String a6 = a(str, strBuilder2, i6, i13);
                            if (a6 != null) {
                                int length = a6.length();
                                strBuilder2.a(i6, i13, a6);
                                int a7 = a(strBuilder2, i6, length, list2) + (length - (i13 - i6));
                                i13 += a7;
                                i5 += a7;
                                i8 += a7;
                                cArr = strBuilder2.b;
                                i7 = 1;
                            }
                            list2.remove(list2.size() - 1);
                            i6 = i13;
                        } else {
                            i12--;
                            i11 += a5;
                            b2 = b2;
                            c2 = c2;
                        }
                    } else {
                        i12++;
                        i11 += a2;
                    }
                }
            } else {
                i6++;
                strMatcher2 = b2;
                strMatcher = c2;
            }
            b2 = strMatcher2;
            c2 = strMatcher;
        }
        return z ? i7 : i8;
    }

    private void a(String str, List list) {
        if (list.contains(str)) {
            StrBuilder strBuilder = new StrBuilder(256);
            strBuilder.c("Infinite loop in property interpolation of ");
            strBuilder.a(list.remove(0));
            strBuilder.c(": ");
            strBuilder.a((Collection) list, "->");
            throw new IllegalStateException(strBuilder.toString());
        }
    }

    /* access modifiers changed from: protected */
    public String a(String str, StrBuilder strBuilder, int i, int i2) {
        StrLookup d2 = d();
        if (d2 == null) {
            return null;
        }
        return d2.a(str);
    }

    public char a() {
        return this.d;
    }

    public void a(char c2) {
        this.d = c2;
    }

    public StrMatcher b() {
        return this.e;
    }

    public StrSubstitutor a(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.e = strMatcher;
            return this;
        }
        throw new IllegalArgumentException("Variable prefix matcher must not be null!");
    }

    public StrSubstitutor b(char c2) {
        return a(StrMatcher.a(c2));
    }

    public StrSubstitutor b(String str) {
        if (str != null) {
            return a(StrMatcher.b(str));
        }
        throw new IllegalArgumentException("Variable prefix must not be null!");
    }

    public StrMatcher c() {
        return this.f;
    }

    public StrSubstitutor b(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.f = strMatcher;
            return this;
        }
        throw new IllegalArgumentException("Variable suffix matcher must not be null!");
    }

    public StrSubstitutor c(char c2) {
        return b(StrMatcher.a(c2));
    }

    public StrSubstitutor c(String str) {
        if (str != null) {
            return b(StrMatcher.b(str));
        }
        throw new IllegalArgumentException("Variable suffix must not be null!");
    }

    public StrLookup d() {
        return this.g;
    }

    public void a(StrLookup strLookup) {
        this.g = strLookup;
    }

    public boolean e() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }
}
