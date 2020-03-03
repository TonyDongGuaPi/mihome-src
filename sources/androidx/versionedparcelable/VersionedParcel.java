package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArraySet;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class VersionedParcel {

    /* renamed from: a  reason: collision with root package name */
    private static final String f485a = "VersionedParcel";
    private static final int b = -1;
    private static final int c = -2;
    private static final int d = -3;
    private static final int e = -4;
    private static final int f = -5;
    private static final int g = -6;
    private static final int h = -7;
    private static final int i = -9;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private static final int m = 4;
    private static final int n = 5;

    /* access modifiers changed from: protected */
    public abstract void a(double d2);

    /* access modifiers changed from: protected */
    public abstract void a(float f2);

    /* access modifiers changed from: protected */
    public abstract void a(int i2);

    /* access modifiers changed from: protected */
    public abstract void a(long j2);

    /* access modifiers changed from: protected */
    public abstract void a(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void a(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract void a(IInterface iInterface);

    /* access modifiers changed from: protected */
    public abstract void a(Parcelable parcelable);

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public abstract void a(boolean z);

    public void a(boolean z, boolean z2) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i2, int i3);

    public boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public abstract boolean b(int i2);

    /* access modifiers changed from: protected */
    public abstract VersionedParcel c();

    /* access modifiers changed from: protected */
    public abstract void c(int i2);

    /* access modifiers changed from: protected */
    public abstract int d();

    /* access modifiers changed from: protected */
    public abstract long e();

    /* access modifiers changed from: protected */
    public abstract float f();

    /* access modifiers changed from: protected */
    public abstract double g();

    /* access modifiers changed from: protected */
    public abstract String h();

    /* access modifiers changed from: protected */
    public abstract IBinder i();

    /* access modifiers changed from: protected */
    public abstract byte[] j();

    /* access modifiers changed from: protected */
    public abstract <T extends Parcelable> T k();

    /* access modifiers changed from: protected */
    public abstract Bundle l();

    /* access modifiers changed from: protected */
    public abstract boolean m();

    public void a(IInterface iInterface, int i2) {
        c(i2);
        a(iInterface);
    }

    public void a(Bundle bundle, int i2) {
        c(i2);
        a(bundle);
    }

    public void a(boolean z, int i2) {
        c(i2);
        a(z);
    }

    public void a(byte[] bArr, int i2) {
        c(i2);
        a(bArr);
    }

    public void a(byte[] bArr, int i2, int i3, int i4) {
        c(i4);
        a(bArr, i2, i3);
    }

    public void a(int i2, int i3) {
        c(i3);
        a(i2);
    }

    public void a(long j2, int i2) {
        c(i2);
        a(j2);
    }

    public void a(float f2, int i2) {
        c(i2);
        a(f2);
    }

    public void a(double d2, int i2) {
        c(i2);
        a(d2);
    }

    public void a(String str, int i2) {
        c(i2);
        a(str);
    }

    public void a(IBinder iBinder, int i2) {
        c(i2);
        a(iBinder);
    }

    public void a(Parcelable parcelable, int i2) {
        c(i2);
        a(parcelable);
    }

    public boolean b(boolean z, int i2) {
        if (!b(i2)) {
            return z;
        }
        return m();
    }

    public int b(int i2, int i3) {
        if (!b(i3)) {
            return i2;
        }
        return d();
    }

    public long b(long j2, int i2) {
        if (!b(i2)) {
            return j2;
        }
        return e();
    }

    public float b(float f2, int i2) {
        if (!b(i2)) {
            return f2;
        }
        return f();
    }

    public double b(double d2, int i2) {
        if (!b(i2)) {
            return d2;
        }
        return g();
    }

    public String b(String str, int i2) {
        if (!b(i2)) {
            return str;
        }
        return h();
    }

    public IBinder b(IBinder iBinder, int i2) {
        if (!b(i2)) {
            return iBinder;
        }
        return i();
    }

    public byte[] b(byte[] bArr, int i2) {
        if (!b(i2)) {
            return bArr;
        }
        return j();
    }

    public <T extends Parcelable> T b(T t, int i2) {
        if (!b(i2)) {
            return t;
        }
        return k();
    }

    public Bundle b(Bundle bundle, int i2) {
        if (!b(i2)) {
            return bundle;
        }
        return l();
    }

    public void a(byte b2, int i2) {
        c(i2);
        a((int) b2);
    }

    @RequiresApi(api = 21)
    public void a(Size size, int i2) {
        c(i2);
        a(size != null);
        if (size != null) {
            a(size.getWidth());
            a(size.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void a(SizeF sizeF, int i2) {
        c(i2);
        a(sizeF != null);
        if (sizeF != null) {
            a(sizeF.getWidth());
            a(sizeF.getHeight());
        }
    }

    public void a(SparseBooleanArray sparseBooleanArray, int i2) {
        c(i2);
        if (sparseBooleanArray == null) {
            a(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        a(size);
        for (int i3 = 0; i3 < size; i3++) {
            a(sparseBooleanArray.keyAt(i3));
            a(sparseBooleanArray.valueAt(i3));
        }
    }

    public void a(boolean[] zArr, int i2) {
        c(i2);
        a(zArr);
    }

    /* access modifiers changed from: protected */
    public void a(boolean[] zArr) {
        if (zArr != null) {
            a(r0);
            for (boolean z : zArr) {
                a(z ? 1 : 0);
            }
            return;
        }
        a(-1);
    }

    public boolean[] b(boolean[] zArr, int i2) {
        if (!b(i2)) {
            return zArr;
        }
        return n();
    }

    /* access modifiers changed from: protected */
    public boolean[] n() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        boolean[] zArr = new boolean[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            zArr[i2] = d() != 0;
        }
        return zArr;
    }

    public void a(char[] cArr, int i2) {
        c(i2);
        if (cArr != null) {
            a(r4);
            for (char a2 : cArr) {
                a((int) a2);
            }
            return;
        }
        a(-1);
    }

    public char[] b(char[] cArr, int i2) {
        if (!b(i2)) {
            return cArr;
        }
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        char[] cArr2 = new char[d2];
        for (int i3 = 0; i3 < d2; i3++) {
            cArr2[i3] = (char) d();
        }
        return cArr2;
    }

    public void a(int[] iArr, int i2) {
        c(i2);
        a(iArr);
    }

    /* access modifiers changed from: protected */
    public void a(int[] iArr) {
        if (iArr != null) {
            a(r0);
            for (int a2 : iArr) {
                a(a2);
            }
            return;
        }
        a(-1);
    }

    public int[] b(int[] iArr, int i2) {
        if (!b(i2)) {
            return iArr;
        }
        return o();
    }

    /* access modifiers changed from: protected */
    public int[] o() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        int[] iArr = new int[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            iArr[i2] = d();
        }
        return iArr;
    }

    public void a(long[] jArr, int i2) {
        c(i2);
        a(jArr);
    }

    /* access modifiers changed from: protected */
    public void a(long[] jArr) {
        if (jArr != null) {
            a(r0);
            for (long a2 : jArr) {
                a(a2);
            }
            return;
        }
        a(-1);
    }

    public long[] b(long[] jArr, int i2) {
        if (!b(i2)) {
            return jArr;
        }
        return p();
    }

    /* access modifiers changed from: protected */
    public long[] p() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        long[] jArr = new long[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            jArr[i2] = e();
        }
        return jArr;
    }

    public void a(float[] fArr, int i2) {
        c(i2);
        a(fArr);
    }

    /* access modifiers changed from: protected */
    public void a(float[] fArr) {
        if (fArr != null) {
            a(r0);
            for (float a2 : fArr) {
                a(a2);
            }
            return;
        }
        a(-1);
    }

    public float[] b(float[] fArr, int i2) {
        if (!b(i2)) {
            return fArr;
        }
        return q();
    }

    /* access modifiers changed from: protected */
    public float[] q() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        float[] fArr = new float[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            fArr[i2] = f();
        }
        return fArr;
    }

    public void a(double[] dArr, int i2) {
        c(i2);
        a(dArr);
    }

    /* access modifiers changed from: protected */
    public void a(double[] dArr) {
        if (dArr != null) {
            a(r0);
            for (double a2 : dArr) {
                a(a2);
            }
            return;
        }
        a(-1);
    }

    public double[] b(double[] dArr, int i2) {
        if (!b(i2)) {
            return dArr;
        }
        return r();
    }

    /* access modifiers changed from: protected */
    public double[] r() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        double[] dArr = new double[d2];
        for (int i2 = 0; i2 < d2; i2++) {
            dArr[i2] = g();
        }
        return dArr;
    }

    public <T> void a(Set<T> set, int i2) {
        a(set, i2);
    }

    public <T> void a(List<T> list, int i2) {
        a(list, i2);
    }

    private <T> void a(Collection<T> collection, int i2) {
        c(i2);
        if (collection == null) {
            a(-1);
            return;
        }
        int size = collection.size();
        a(size);
        if (size > 0) {
            int a2 = a(collection.iterator().next());
            a(a2);
            switch (a2) {
                case 1:
                    for (T a3 : collection) {
                        a((VersionedParcelable) a3);
                    }
                    return;
                case 2:
                    for (T a4 : collection) {
                        a((Parcelable) a4);
                    }
                    return;
                case 3:
                    for (T a5 : collection) {
                        a((Serializable) a5);
                    }
                    return;
                case 4:
                    for (T a6 : collection) {
                        a((String) a6);
                    }
                    return;
                case 5:
                    for (T a7 : collection) {
                        a((IBinder) a7);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public <T> void a(T[] tArr, int i2) {
        c(i2);
        a(tArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        a((java.lang.String) r4[r1]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r1 >= r0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        a((java.io.Serializable) r4[r1]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        if (r1 >= r0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        a((android.os.Parcelable) r4[r1]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        if (r1 >= r0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        a((androidx.versionedparcelable.VersionedParcelable) r4[r1]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        if (r1 >= r0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        a((android.os.IBinder) r4[r1]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        if (r1 >= r0) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void a(T[] r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0007
            r4 = -1
            r3.a((int) r4)
            return
        L_0x0007:
            int r0 = r4.length
            r3.a((int) r0)
            if (r0 <= 0) goto L_0x0057
            r1 = 0
            r2 = r4[r1]
            int r2 = r3.a(r2)
            r3.a((int) r2)
            switch(r2) {
                case 1: goto L_0x004b;
                case 2: goto L_0x003f;
                case 3: goto L_0x0033;
                case 4: goto L_0x0027;
                case 5: goto L_0x001b;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x0057
        L_0x001b:
            if (r1 >= r0) goto L_0x0057
            r2 = r4[r1]
            android.os.IBinder r2 = (android.os.IBinder) r2
            r3.a((android.os.IBinder) r2)
            int r1 = r1 + 1
            goto L_0x001b
        L_0x0027:
            if (r1 >= r0) goto L_0x0057
            r2 = r4[r1]
            java.lang.String r2 = (java.lang.String) r2
            r3.a((java.lang.String) r2)
            int r1 = r1 + 1
            goto L_0x0027
        L_0x0033:
            if (r1 >= r0) goto L_0x0057
            r2 = r4[r1]
            java.io.Serializable r2 = (java.io.Serializable) r2
            r3.a((java.io.Serializable) r2)
            int r1 = r1 + 1
            goto L_0x0033
        L_0x003f:
            if (r1 >= r0) goto L_0x0057
            r2 = r4[r1]
            android.os.Parcelable r2 = (android.os.Parcelable) r2
            r3.a((android.os.Parcelable) r2)
            int r1 = r1 + 1
            goto L_0x003f
        L_0x004b:
            if (r1 >= r0) goto L_0x0057
            r2 = r4[r1]
            androidx.versionedparcelable.VersionedParcelable r2 = (androidx.versionedparcelable.VersionedParcelable) r2
            r3.a((androidx.versionedparcelable.VersionedParcelable) r2)
            int r1 = r1 + 1
            goto L_0x004b
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.a(java.lang.Object[]):void");
    }

    private <T> int a(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    public void a(VersionedParcelable versionedParcelable, int i2) {
        c(i2);
        a(versionedParcelable);
    }

    /* access modifiers changed from: protected */
    public void a(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            a((String) null);
            return;
        }
        b(versionedParcelable);
        VersionedParcel c2 = c();
        a(versionedParcelable, c2);
        c2.b();
    }

    private void b(VersionedParcelable versionedParcelable) {
        try {
            a(a((Class<? extends VersionedParcelable>) versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    public void a(Serializable serializable, int i2) {
        c(i2);
        a(serializable);
    }

    private void a(Serializable serializable) {
        if (serializable == null) {
            a((String) null);
            return;
        }
        String name = serializable.getClass().getName();
        a(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            a(byteArrayOutputStream.toByteArray());
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + Operators.BRACKET_END_STR, e2);
        }
    }

    public void a(Exception exc, int i2) {
        c(i2);
        if (exc == null) {
            s();
            return;
        }
        int i3 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            i3 = -9;
        } else if (exc instanceof SecurityException) {
            i3 = -1;
        } else if (exc instanceof BadParcelableException) {
            i3 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            i3 = -3;
        } else if (exc instanceof NullPointerException) {
            i3 = -4;
        } else if (exc instanceof IllegalStateException) {
            i3 = -5;
        } else if (exc instanceof NetworkOnMainThreadException) {
            i3 = -6;
        } else if (exc instanceof UnsupportedOperationException) {
            i3 = -7;
        }
        a(i3);
        if (i3 != 0) {
            a(exc.getMessage());
            if (i3 == -9) {
                a((Parcelable) exc);
            }
        } else if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        } else {
            throw new RuntimeException(exc);
        }
    }

    /* access modifiers changed from: protected */
    public void s() {
        a(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r2 = v();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Exception b(java.lang.Exception r1, int r2) {
        /*
            r0 = this;
            boolean r2 = r0.b((int) r2)
            if (r2 != 0) goto L_0x0007
            return r1
        L_0x0007:
            int r2 = r0.v()
            if (r2 == 0) goto L_0x0016
            java.lang.String r1 = r0.h()
            java.lang.Exception r1 = r0.a((int) r2, (java.lang.String) r1)
            return r1
        L_0x0016:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.b(java.lang.Exception, int):java.lang.Exception");
    }

    private int v() {
        return d();
    }

    private Exception a(int i2, String str) {
        return b(i2, str);
    }

    @NonNull
    protected static Throwable a(@NonNull Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    private Exception b(int i2, String str) {
        switch (i2) {
            case -9:
                return (Exception) k();
            case -7:
                return new UnsupportedOperationException(str);
            case -6:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
            default:
                return new RuntimeException("Unknown exception code: " + i2 + " msg " + str);
        }
    }

    public byte b(byte b2, int i2) {
        if (!b(i2)) {
            return b2;
        }
        return (byte) (d() & 255);
    }

    @RequiresApi(api = 21)
    public Size b(Size size, int i2) {
        if (!b(i2)) {
            return size;
        }
        if (m()) {
            return new Size(d(), d());
        }
        return null;
    }

    @RequiresApi(api = 21)
    public SizeF b(SizeF sizeF, int i2) {
        if (!b(i2)) {
            return sizeF;
        }
        if (m()) {
            return new SizeF(f(), f());
        }
        return null;
    }

    public SparseBooleanArray b(SparseBooleanArray sparseBooleanArray, int i2) {
        if (!b(i2)) {
            return sparseBooleanArray;
        }
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(d2);
        for (int i3 = 0; i3 < d2; i3++) {
            sparseBooleanArray2.put(d(), m());
        }
        return sparseBooleanArray2;
    }

    public <T> Set<T> b(Set<T> set, int i2) {
        if (!b(i2)) {
            return set;
        }
        return (Set) a(i2, new ArraySet());
    }

    public <T> List<T> b(List<T> list, int i2) {
        if (!b(i2)) {
            return list;
        }
        return (List) a(i2, new ArrayList());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T, S extends java.util.Collection<T>> S a(int r3, S r4) {
        /*
            r2 = this;
            int r3 = r2.d()
            r0 = 0
            if (r3 >= 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r3 == 0) goto L_0x0051
            int r1 = r2.d()
            if (r3 >= 0) goto L_0x0011
            return r0
        L_0x0011:
            switch(r1) {
                case 1: goto L_0x0045;
                case 2: goto L_0x0039;
                case 3: goto L_0x002d;
                case 4: goto L_0x0021;
                case 5: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0051
        L_0x0015:
            if (r3 <= 0) goto L_0x0051
            android.os.IBinder r0 = r2.i()
            r4.add(r0)
            int r3 = r3 + -1
            goto L_0x0015
        L_0x0021:
            if (r3 <= 0) goto L_0x0051
            java.lang.String r0 = r2.h()
            r4.add(r0)
            int r3 = r3 + -1
            goto L_0x0021
        L_0x002d:
            if (r3 <= 0) goto L_0x0051
            java.io.Serializable r0 = r2.u()
            r4.add(r0)
            int r3 = r3 + -1
            goto L_0x002d
        L_0x0039:
            if (r3 <= 0) goto L_0x0051
            android.os.Parcelable r0 = r2.k()
            r4.add(r0)
            int r3 = r3 + -1
            goto L_0x0039
        L_0x0045:
            if (r3 <= 0) goto L_0x0051
            androidx.versionedparcelable.VersionedParcelable r0 = r2.t()
            r4.add(r0)
            int r3 = r3 + -1
            goto L_0x0045
        L_0x0051:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.a(int, java.util.Collection):java.util.Collection");
    }

    public <T> T[] b(T[] tArr, int i2) {
        if (!b(i2)) {
            return tArr;
        }
        return b(tArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] b(T[] r5) {
        /*
            r4 = this;
            int r0 = r4.d()
            r1 = 0
            if (r0 >= 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r0)
            if (r0 == 0) goto L_0x0056
            int r3 = r4.d()
            if (r0 >= 0) goto L_0x0016
            return r1
        L_0x0016:
            switch(r3) {
                case 1: goto L_0x004a;
                case 2: goto L_0x003e;
                case 3: goto L_0x0032;
                case 4: goto L_0x0026;
                case 5: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x0056
        L_0x001a:
            if (r0 <= 0) goto L_0x0056
            android.os.IBinder r1 = r4.i()
            r2.add(r1)
            int r0 = r0 + -1
            goto L_0x001a
        L_0x0026:
            if (r0 <= 0) goto L_0x0056
            java.lang.String r1 = r4.h()
            r2.add(r1)
            int r0 = r0 + -1
            goto L_0x0026
        L_0x0032:
            if (r0 <= 0) goto L_0x0056
            java.io.Serializable r1 = r4.u()
            r2.add(r1)
            int r0 = r0 + -1
            goto L_0x0032
        L_0x003e:
            if (r0 <= 0) goto L_0x0056
            android.os.Parcelable r1 = r4.k()
            r2.add(r1)
            int r0 = r0 + -1
            goto L_0x003e
        L_0x004a:
            if (r0 <= 0) goto L_0x0056
            androidx.versionedparcelable.VersionedParcelable r1 = r4.t()
            r2.add(r1)
            int r0 = r0 + -1
            goto L_0x004a
        L_0x0056:
            java.lang.Object[] r5 = r2.toArray(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.b(java.lang.Object[]):java.lang.Object[]");
    }

    public <T extends VersionedParcelable> T b(T t, int i2) {
        if (!b(i2)) {
            return t;
        }
        return t();
    }

    /* access modifiers changed from: protected */
    public <T extends VersionedParcelable> T t() {
        String h2 = h();
        if (h2 == null) {
            return null;
        }
        return a(h2, c());
    }

    /* access modifiers changed from: protected */
    public Serializable u() {
        String h2 = h();
        if (h2 == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(j())) {
                /* access modifiers changed from: protected */
                public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    if (cls != null) {
                        return cls;
                    }
                    return super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e2) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + h2 + Operators.BRACKET_END_STR, e2);
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + h2 + Operators.BRACKET_END_STR, e3);
        }
    }

    protected static <T extends VersionedParcelable> T a(String str, VersionedParcel versionedParcel) {
        try {
            return (VersionedParcelable) Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[]{VersionedParcel.class}).invoke((Object) null, new Object[]{versionedParcel});
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e3.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
        }
    }

    protected static <T extends VersionedParcelable> void a(T t, VersionedParcel versionedParcel) {
        try {
            c(t).getDeclaredMethod(Constants.TitleMenu.MENU_WRITE, new Class[]{t.getClass(), VersionedParcel.class}).invoke((Object) null, new Object[]{t, versionedParcel});
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e3.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
        }
    }

    private static <T extends VersionedParcelable> Class c(T t) throws ClassNotFoundException {
        return a((Class<? extends VersionedParcelable>) t.getClass());
    }

    private static Class a(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        return Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
    }

    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }
}
