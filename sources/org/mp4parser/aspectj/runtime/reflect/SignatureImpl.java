package org.mp4parser.aspectj.runtime.reflect;

import com.adobe.xmp.XMPConst;
import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.Signature;

abstract class SignatureImpl implements Signature {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f3777a = true;
    static final char k = '-';
    static String[] l = new String[0];
    static Class[] m = new Class[0];
    static final String n = ":";
    private String b;
    int e = -1;
    String f;
    String g;
    Class h;
    Cache i;
    ClassLoader j = null;

    private interface Cache {
        String a(int i);

        void a(int i, String str);
    }

    /* access modifiers changed from: protected */
    public abstract String a(StringMaker stringMaker);

    SignatureImpl(int i2, String str, Class cls) {
        this.e = i2;
        this.f = str;
        this.h = cls;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(org.mp4parser.aspectj.runtime.reflect.StringMaker r3) {
        /*
            r2 = this;
            boolean r0 = f3777a
            if (r0 == 0) goto L_0x001d
            org.mp4parser.aspectj.runtime.reflect.SignatureImpl$Cache r0 = r2.i
            if (r0 != 0) goto L_0x0014
            org.mp4parser.aspectj.runtime.reflect.SignatureImpl$CacheImpl r0 = new org.mp4parser.aspectj.runtime.reflect.SignatureImpl$CacheImpl     // Catch:{ Throwable -> 0x0010 }
            r0.<init>()     // Catch:{ Throwable -> 0x0010 }
            r2.i = r0     // Catch:{ Throwable -> 0x0010 }
            goto L_0x001d
        L_0x0010:
            r0 = 0
            f3777a = r0
            goto L_0x001d
        L_0x0014:
            org.mp4parser.aspectj.runtime.reflect.SignatureImpl$Cache r0 = r2.i
            int r1 = r3.i
            java.lang.String r0 = r0.a(r1)
            goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            if (r0 != 0) goto L_0x0024
            java.lang.String r0 = r2.a((org.mp4parser.aspectj.runtime.reflect.StringMaker) r3)
        L_0x0024:
            boolean r1 = f3777a
            if (r1 == 0) goto L_0x002f
            org.mp4parser.aspectj.runtime.reflect.SignatureImpl$Cache r1 = r2.i
            int r3 = r3.i
            r1.a(r3, r0)
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.runtime.reflect.SignatureImpl.b(org.mp4parser.aspectj.runtime.reflect.StringMaker):java.lang.String");
    }

    public final String toString() {
        return b(StringMaker.k);
    }

    public final String a() {
        return b(StringMaker.j);
    }

    public final String b() {
        return b(StringMaker.l);
    }

    public int d() {
        if (this.e == -1) {
            this.e = b(0);
        }
        return this.e;
    }

    public String c() {
        if (this.f == null) {
            this.f = a(1);
        }
        return this.f;
    }

    public Class e() {
        if (this.h == null) {
            this.h = c(2);
        }
        return this.h;
    }

    public String f() {
        if (this.g == null) {
            this.g = e().getName();
        }
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public String a(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (!cls.isArray()) {
            return cls.getName().replace('$', '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a((Class) cls.getComponentType()));
        stringBuffer.append(XMPConst.ai);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    /* access modifiers changed from: package-private */
    public String b(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (!cls.isArray()) {
            return a(cls.getName()).replace('$', '.');
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(b((Class) cls.getComponentType()));
        stringBuffer.append(XMPConst.ai);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(a(clsArr[i2]));
        }
    }

    /* access modifiers changed from: package-private */
    public void b(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(b(clsArr[i2]));
        }
    }

    /* access modifiers changed from: package-private */
    public void c(StringBuffer stringBuffer, Class[] clsArr) {
        a(stringBuffer, clsArr);
    }

    public void a(ClassLoader classLoader) {
        this.j = classLoader;
    }

    private ClassLoader g() {
        if (this.j == null) {
            this.j = getClass().getClassLoader();
        }
        return this.j;
    }

    public SignatureImpl(String str) {
        this.b = str;
    }

    /* access modifiers changed from: package-private */
    public String a(int i2) {
        int indexOf = this.b.indexOf(45);
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            i3 = indexOf + 1;
            indexOf = this.b.indexOf(45, i3);
            i2 = i4;
        }
        if (indexOf == -1) {
            indexOf = this.b.length();
        }
        return this.b.substring(i3, indexOf);
    }

    /* access modifiers changed from: package-private */
    public int b(int i2) {
        return Integer.parseInt(a(i2), 16);
    }

    /* access modifiers changed from: package-private */
    public Class c(int i2) {
        return Factory.a(a(i2), g());
    }

    /* access modifiers changed from: package-private */
    public String[] d(int i2) {
        StringTokenizer stringTokenizer = new StringTokenizer(a(i2), ":");
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i3 = 0; i3 < countTokens; i3++) {
            strArr[i3] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public Class[] e(int i2) {
        StringTokenizer stringTokenizer = new StringTokenizer(a(i2), ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i3 = 0; i3 < countTokens; i3++) {
            clsArr[i3] = Factory.a(stringTokenizer.nextToken(), g());
        }
        return clsArr;
    }

    static void a(boolean z) {
        f3777a = z;
    }

    static boolean l() {
        return f3777a;
    }

    private static final class CacheImpl implements Cache {

        /* renamed from: a  reason: collision with root package name */
        private SoftReference f3778a;

        public CacheImpl() {
            b();
        }

        public String a(int i) {
            String[] a2 = a();
            if (a2 == null) {
                return null;
            }
            return a2[i];
        }

        public void a(int i, String str) {
            String[] a2 = a();
            if (a2 == null) {
                a2 = b();
            }
            a2[i] = str;
        }

        private String[] a() {
            return (String[]) this.f3778a.get();
        }

        private String[] b() {
            String[] strArr = new String[3];
            this.f3778a = new SoftReference(strArr);
            return strArr;
        }
    }
}
