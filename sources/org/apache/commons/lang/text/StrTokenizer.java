package org.apache.commons.lang.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class StrTokenizer implements Cloneable, ListIterator {

    /* renamed from: a  reason: collision with root package name */
    private static final StrTokenizer f3410a = new StrTokenizer();
    private static final StrTokenizer b = new StrTokenizer();
    private char[] c;
    private String[] d;
    private int e;
    private StrMatcher f;
    private StrMatcher g;
    private StrMatcher h;
    private StrMatcher i;
    private boolean j;
    private boolean k;

    static {
        f3410a.a(StrMatcher.a());
        f3410a.b(StrMatcher.g());
        f3410a.c(StrMatcher.i());
        f3410a.d(StrMatcher.e());
        f3410a.a(false);
        f3410a.b(false);
        b.a(StrMatcher.b());
        b.b(StrMatcher.g());
        b.c(StrMatcher.i());
        b.d(StrMatcher.e());
        b.a(false);
        b.b(false);
    }

    private static StrTokenizer q() {
        return (StrTokenizer) f3410a.clone();
    }

    public static StrTokenizer b() {
        return q();
    }

    public static StrTokenizer a(String str) {
        StrTokenizer q = q();
        q.c(str);
        return q;
    }

    public static StrTokenizer a(char[] cArr) {
        StrTokenizer q = q();
        q.c(cArr);
        return q;
    }

    private static StrTokenizer r() {
        return (StrTokenizer) b.clone();
    }

    public static StrTokenizer c() {
        return r();
    }

    public static StrTokenizer b(String str) {
        StrTokenizer r = r();
        r.c(str);
        return r;
    }

    public static StrTokenizer b(char[] cArr) {
        StrTokenizer r = r();
        r.c(cArr);
        return r;
    }

    public StrTokenizer() {
        this.f = StrMatcher.d();
        this.g = StrMatcher.i();
        this.h = StrMatcher.i();
        this.i = StrMatcher.i();
        this.j = false;
        this.k = true;
        this.c = null;
    }

    public StrTokenizer(String str) {
        this.f = StrMatcher.d();
        this.g = StrMatcher.i();
        this.h = StrMatcher.i();
        this.i = StrMatcher.i();
        this.j = false;
        this.k = true;
        if (str != null) {
            this.c = str.toCharArray();
        } else {
            this.c = null;
        }
    }

    public StrTokenizer(String str, char c2) {
        this(str);
        a(c2);
    }

    public StrTokenizer(String str, String str2) {
        this(str);
        d(str2);
    }

    public StrTokenizer(String str, StrMatcher strMatcher) {
        this(str);
        a(strMatcher);
    }

    public StrTokenizer(String str, char c2, char c3) {
        this(str, c2);
        b(c3);
    }

    public StrTokenizer(String str, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(str, strMatcher);
        b(strMatcher2);
    }

    public StrTokenizer(char[] cArr) {
        this.f = StrMatcher.d();
        this.g = StrMatcher.i();
        this.h = StrMatcher.i();
        this.i = StrMatcher.i();
        this.j = false;
        this.k = true;
        this.c = cArr;
    }

    public StrTokenizer(char[] cArr, char c2) {
        this(cArr);
        a(c2);
    }

    public StrTokenizer(char[] cArr, String str) {
        this(cArr);
        d(str);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher) {
        this(cArr);
        a(strMatcher);
    }

    public StrTokenizer(char[] cArr, char c2, char c3) {
        this(cArr, c2);
        b(c3);
    }

    public StrTokenizer(char[] cArr, StrMatcher strMatcher, StrMatcher strMatcher2) {
        this(cArr, strMatcher);
        b(strMatcher2);
    }

    public int d() {
        s();
        return this.d.length;
    }

    public String e() {
        if (!hasNext()) {
            return null;
        }
        String[] strArr = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        return strArr[i2];
    }

    public String f() {
        if (!hasPrevious()) {
            return null;
        }
        String[] strArr = this.d;
        int i2 = this.e - 1;
        this.e = i2;
        return strArr[i2];
    }

    public String[] g() {
        s();
        return (String[]) this.d.clone();
    }

    public List h() {
        s();
        ArrayList arrayList = new ArrayList(this.d.length);
        for (String add : this.d) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public StrTokenizer i() {
        this.e = 0;
        this.d = null;
        return this;
    }

    public StrTokenizer c(String str) {
        i();
        if (str != null) {
            this.c = str.toCharArray();
        } else {
            this.c = null;
        }
        return this;
    }

    public StrTokenizer c(char[] cArr) {
        i();
        this.c = cArr;
        return this;
    }

    public boolean hasNext() {
        s();
        return this.e < this.d.length;
    }

    public Object next() {
        if (hasNext()) {
            String[] strArr = this.d;
            int i2 = this.e;
            this.e = i2 + 1;
            return strArr[i2];
        }
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return this.e;
    }

    public boolean hasPrevious() {
        s();
        return this.e > 0;
    }

    public Object previous() {
        if (hasPrevious()) {
            String[] strArr = this.d;
            int i2 = this.e - 1;
            this.e = i2;
            return strArr[i2];
        }
        throw new NoSuchElementException();
    }

    public int previousIndex() {
        return this.e - 1;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public void set(Object obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void s() {
        if (this.d != null) {
            return;
        }
        if (this.c == null) {
            List a2 = a((char[]) null, 0, 0);
            this.d = (String[]) a2.toArray(new String[a2.size()]);
            return;
        }
        List a3 = a(this.c, 0, this.c.length);
        this.d = (String[]) a3.toArray(new String[a3.size()]);
    }

    /* access modifiers changed from: protected */
    public List a(char[] cArr, int i2, int i3) {
        if (cArr == null || i3 == 0) {
            return Collections.EMPTY_LIST;
        }
        StrBuilder strBuilder = new StrBuilder();
        ArrayList arrayList = new ArrayList();
        int i4 = i2;
        while (i4 >= 0 && i4 < i3) {
            i4 = a(cArr, i4, i3, strBuilder, (List) arrayList);
            if (i4 >= i3) {
                a(arrayList, "");
            }
        }
        return arrayList;
    }

    private void a(List list, String str) {
        if (str == null || str.length() == 0) {
            if (!o()) {
                if (n()) {
                    str = null;
                }
            } else {
                return;
            }
        }
        list.add(str);
    }

    private int a(char[] cArr, int i2, int i3, StrBuilder strBuilder, List list) {
        while (i2 < i3) {
            int max = Math.max(l().a(cArr, i2, i2, i3), m().a(cArr, i2, i2, i3));
            if (max == 0 || j().a(cArr, i2, i2, i3) > 0 || k().a(cArr, i2, i2, i3) > 0) {
                break;
            }
            i2 += max;
        }
        if (i2 >= i3) {
            a(list, "");
            return -1;
        }
        int a2 = j().a(cArr, i2, i2, i3);
        if (a2 > 0) {
            a(list, "");
            return i2 + a2;
        }
        int a3 = k().a(cArr, i2, i2, i3);
        if (a3 <= 0) {
            return a(cArr, i2, i3, strBuilder, list, 0, 0);
        }
        return a(cArr, i2 + a3, i3, strBuilder, list, i2, a3);
    }

    private int a(char[] cArr, int i2, int i3, StrBuilder strBuilder, List list, int i4, int i5) {
        int i6;
        char[] cArr2 = cArr;
        int i7 = i2;
        int i8 = i3;
        StrBuilder strBuilder2 = strBuilder;
        List list2 = list;
        int i9 = i5;
        strBuilder.h();
        boolean z = i9 > 0;
        int i10 = i7;
        int i11 = 0;
        while (i10 < i8) {
            if (z) {
                int i12 = i11;
                if (a(cArr, i10, i3, i4, i5)) {
                    int i13 = i10 + i9;
                    if (a(cArr, i13, i3, i4, i5)) {
                        strBuilder2.a(cArr2, i10, i9);
                        i10 += i9 * 2;
                        i11 = strBuilder.f();
                    } else {
                        i11 = i12;
                        i10 = i13;
                        z = false;
                    }
                } else {
                    i6 = i10 + 1;
                    strBuilder2.a(cArr2[i10]);
                    i11 = strBuilder.f();
                }
            } else {
                int i14 = i11;
                int a2 = j().a(cArr2, i10, i7, i8);
                if (a2 > 0) {
                    a(list2, strBuilder2.d(0, i14));
                    return i10 + a2;
                } else if (i9 <= 0 || !a(cArr, i10, i3, i4, i5)) {
                    int a3 = l().a(cArr2, i10, i7, i8);
                    if (a3 <= 0) {
                        a3 = m().a(cArr2, i10, i7, i8);
                        if (a3 > 0) {
                            strBuilder2.a(cArr2, i10, a3);
                        } else {
                            i6 = i10 + 1;
                            strBuilder2.a(cArr2[i10]);
                            i11 = strBuilder.f();
                        }
                    }
                    i10 += a3;
                    i11 = i14;
                } else {
                    i10 += i9;
                    i11 = i14;
                    z = true;
                }
            }
            i10 = i6;
        }
        a(list2, strBuilder2.d(0, i11));
        return -1;
    }

    private boolean a(char[] cArr, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = i2 + i6;
            if (i7 >= i3 || cArr[i7] != cArr[i4 + i6]) {
                return false;
            }
        }
        return true;
    }

    public StrMatcher j() {
        return this.f;
    }

    public StrTokenizer a(StrMatcher strMatcher) {
        if (strMatcher == null) {
            this.f = StrMatcher.i();
        } else {
            this.f = strMatcher;
        }
        return this;
    }

    public StrTokenizer a(char c2) {
        return a(StrMatcher.a(c2));
    }

    public StrTokenizer d(String str) {
        return a(StrMatcher.b(str));
    }

    public StrMatcher k() {
        return this.g;
    }

    public StrTokenizer b(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.g = strMatcher;
        }
        return this;
    }

    public StrTokenizer b(char c2) {
        return b(StrMatcher.a(c2));
    }

    public StrMatcher l() {
        return this.h;
    }

    public StrTokenizer c(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.h = strMatcher;
        }
        return this;
    }

    public StrTokenizer c(char c2) {
        return c(StrMatcher.a(c2));
    }

    public StrMatcher m() {
        return this.i;
    }

    public StrTokenizer d(StrMatcher strMatcher) {
        if (strMatcher != null) {
            this.i = strMatcher;
        }
        return this;
    }

    public boolean n() {
        return this.j;
    }

    public StrTokenizer a(boolean z) {
        this.j = z;
        return this;
    }

    public boolean o() {
        return this.k;
    }

    public StrTokenizer b(boolean z) {
        this.k = z;
        return this;
    }

    public String a() {
        if (this.c == null) {
            return null;
        }
        return new String(this.c);
    }

    public Object clone() {
        try {
            return p();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Object p() throws CloneNotSupportedException {
        StrTokenizer strTokenizer = (StrTokenizer) super.clone();
        if (strTokenizer.c != null) {
            strTokenizer.c = (char[]) strTokenizer.c.clone();
        }
        strTokenizer.i();
        return strTokenizer;
    }

    public String toString() {
        if (this.d == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("StrTokenizer");
        stringBuffer.append(h());
        return stringBuffer.toString();
    }
}
