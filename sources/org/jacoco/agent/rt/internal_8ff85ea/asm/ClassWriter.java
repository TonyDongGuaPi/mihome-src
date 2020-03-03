package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.ContentTypeDetector;

public class ClassWriter extends ClassVisitor {
    static final byte[] A;
    static final int B = 7;
    static final int C = 9;
    static final int D = 10;
    static final int E = 11;
    static final int F = 8;
    static final int G = 3;
    static final int H = 4;
    static final int I = 5;
    static final int J = 6;
    static final int K = 12;
    static final int L = 1;
    static final int M = 16;
    static final int N = 15;
    static final int O = 18;
    static final int P = 20;
    static final int Q = 30;
    static final int R = 31;
    static final int S = 32;
    static final int T = 33;
    public static final int c = 1;
    public static final int d = 2;
    static final int e = 262144;
    static final int f = 64;
    static final int g = 0;
    static final int h = 1;
    static final int i = 2;
    static final int j = 3;
    static final int k = 4;
    static final int l = 5;
    static final int m = 6;
    static final int n = 7;
    static final int o = 8;
    static final int p = 9;
    static final int q = 10;
    static final int r = 11;
    static final int s = 12;
    static final int t = 13;
    static final int u = 14;
    static final int v = 15;
    static final int w = 16;
    static final int x = 17;
    static final int y = 18;
    static final int z = 256;
    ClassReader U;
    int V;
    int W;
    final ByteVector X;
    Item[] Y;
    int Z;
    private AnnotationWriter aA;
    private AnnotationWriter aB;
    private Attribute aC;
    private int aD;
    private ByteVector aE;
    private int aF;
    final Item aa;
    final Item ab;
    final Item ac;
    final Item ad;
    Item[] ae;
    String af;
    int ag;
    ByteVector ah;
    FieldWriter ai;
    FieldWriter aj;
    MethodWriter ak;
    MethodWriter al;
    boolean am;
    private short an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private int[] at;
    private int au;
    private ByteVector av;
    private int aw;
    private int ax;
    private AnnotationWriter ay;
    private AnnotationWriter az;

    public final void a() {
    }

    static {
        byte[] bArr = new byte[TbsListener.ErrorCode.COPY_INSTALL_SUCCESS];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ("AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKSSSSSSSSSSSSSSSSSS".charAt(i2) - 'A');
        }
        A = bArr;
    }

    public ClassWriter(int i2) {
        super(327680);
        this.W = 1;
        this.X = new ByteVector();
        this.Y = new Item[256];
        double length = (double) this.Y.length;
        Double.isNaN(length);
        this.Z = (int) (length * 0.75d);
        this.aa = new Item();
        this.ab = new Item();
        this.ac = new Item();
        this.ad = new Item();
        this.aF = (i2 & 2) != 0 ? 0 : (i2 & 1) != 0 ? 2 : 3;
    }

    public ClassWriter(ClassReader classReader, int i2) {
        this(i2);
        classReader.a(this);
        this.U = classReader;
    }

    public final void a(int i2, int i3, String str, String str2, String str3, String[] strArr) {
        int i4;
        this.V = i2;
        this.ao = i3;
        this.ap = c(str);
        this.af = str;
        if (str2 != null) {
            this.aq = a(str2);
        }
        if (str3 == null) {
            i4 = 0;
        } else {
            i4 = c(str3);
        }
        this.ar = i4;
        if (strArr != null && strArr.length > 0) {
            this.as = strArr.length;
            this.at = new int[this.as];
            for (int i5 = 0; i5 < this.as; i5++) {
                this.at[i5] = c(strArr[i5]);
            }
        }
    }

    public final void a(String str, String str2) {
        if (str != null) {
            this.au = a(str);
        }
        if (str2 != null) {
            this.av = new ByteVector().a(str2, 0, Integer.MAX_VALUE);
        }
    }

    public final void a(String str, String str2, String str3) {
        this.aw = c(str);
        if (str2 != null && str3 != null) {
            this.ax = b(str2, str3);
        }
    }

    public final AnnotationVisitor a(String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        byteVector.b(a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (z2) {
            annotationWriter.c = this.ay;
            this.ay = annotationWriter;
        } else {
            annotationWriter.c = this.az;
            this.az = annotationWriter;
        }
        return annotationWriter;
    }

    public final AnnotationVisitor a(int i2, TypePath typePath, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i2, typePath, byteVector);
        byteVector.b(a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, byteVector.b - 2);
        if (z2) {
            annotationWriter.c = this.aA;
            this.aA = annotationWriter;
        } else {
            annotationWriter.c = this.aB;
            this.aB = annotationWriter;
        }
        return annotationWriter;
    }

    public final void a(Attribute attribute) {
        attribute.c = this.aC;
        this.aC = attribute;
    }

    public final void a(String str, String str2, String str3, int i2) {
        if (this.aE == null) {
            this.aE = new ByteVector();
        }
        Item b = b(str);
        if (b.c == 0) {
            this.aD++;
            this.aE.b(b.f3595a);
            int i3 = 0;
            this.aE.b(str2 == null ? 0 : c(str2));
            ByteVector byteVector = this.aE;
            if (str3 != null) {
                i3 = a(str3);
            }
            byteVector.b(i3);
            this.aE.b(i2);
            b.c = this.aD;
        }
    }

    public final FieldVisitor a(int i2, String str, String str2, String str3, Object obj) {
        return new FieldWriter(this, i2, str, str2, str3, obj);
    }

    public final MethodVisitor a(int i2, String str, String str2, String str3, String[] strArr) {
        return new MethodWriter(this, i2, str, str2, str3, strArr, this.aF);
    }

    public byte[] b() {
        int i2;
        int i3;
        ByteVector byteVector;
        if (this.W <= 65535) {
            int i4 = (this.as * 2) + 24;
            int i5 = 0;
            for (FieldWriter fieldWriter = this.ai; fieldWriter != null; fieldWriter = (FieldWriter) fieldWriter.b) {
                i5++;
                i4 += fieldWriter.b();
            }
            int i6 = 0;
            for (MethodWriter methodWriter = this.ak; methodWriter != null; methodWriter = (MethodWriter) methodWriter.Q_) {
                i6++;
                i4 += methodWriter.d();
            }
            if (this.ah != null) {
                a("BootstrapMethods");
                i2 = i4 + this.ah.b + 8;
                i3 = 1;
            } else {
                i2 = i4;
                i3 = 0;
            }
            if (this.aq != 0) {
                i3++;
                i2 += 8;
                a("Signature");
            }
            if (this.au != 0) {
                i3++;
                i2 += 8;
                a("SourceFile");
            }
            if (this.av != null) {
                i3++;
                i2 += this.av.b + 6;
                a("SourceDebugExtension");
            }
            if (this.aw != 0) {
                i3++;
                i2 += 10;
                a("EnclosingMethod");
            }
            if ((this.ao & 131072) != 0) {
                i3++;
                i2 += 6;
                a("Deprecated");
            }
            if ((this.ao & 4096) != 0 && ((this.V & 65535) < 49 || (this.ao & 262144) != 0)) {
                i3++;
                i2 += 6;
                a("Synthetic");
            }
            if (this.aE != null) {
                i3++;
                i2 += this.aE.b + 8;
                a("InnerClasses");
            }
            if (this.ay != null) {
                i3++;
                i2 += this.ay.b() + 8;
                a("RuntimeVisibleAnnotations");
            }
            if (this.az != null) {
                i3++;
                i2 += this.az.b() + 8;
                a("RuntimeInvisibleAnnotations");
            }
            if (this.aA != null) {
                i3++;
                i2 += this.aA.b() + 8;
                a("RuntimeVisibleTypeAnnotations");
            }
            if (this.aB != null) {
                i3++;
                i2 += this.aB.b() + 8;
                a("RuntimeInvisibleTypeAnnotations");
            }
            int i7 = i2;
            if (this.aC != null) {
                int d2 = i3 + this.aC.d();
                i7 += this.aC.b(this, (byte[]) null, 0, -1, -1);
                i3 = d2;
            }
            ByteVector byteVector2 = new ByteVector(i7 + this.X.b);
            byteVector2.c(ContentTypeDetector.b).c(this.V);
            byteVector2.b(this.W).a(this.X.f3586a, 0, this.X.b);
            byteVector2.b(((393216 | ((this.ao & 262144) / 64)) ^ -1) & this.ao).b(this.ap).b(this.ar);
            byteVector2.b(this.as);
            for (int i8 = 0; i8 < this.as; i8++) {
                byteVector2.b(this.at[i8]);
            }
            byteVector2.b(i5);
            for (FieldWriter fieldWriter2 = this.ai; fieldWriter2 != null; fieldWriter2 = (FieldWriter) fieldWriter2.b) {
                fieldWriter2.a(byteVector2);
            }
            byteVector2.b(i6);
            for (MethodWriter methodWriter2 = this.ak; methodWriter2 != null; methodWriter2 = (MethodWriter) methodWriter2.Q_) {
                methodWriter2.a(byteVector2);
            }
            byteVector2.b(i3);
            if (this.ah != null) {
                byteVector2.b(a("BootstrapMethods"));
                byteVector2.c(this.ah.b + 2).b(this.ag);
                byteVector2.a(this.ah.f3586a, 0, this.ah.b);
            }
            if (this.aq != 0) {
                byteVector2.b(a("Signature")).c(2).b(this.aq);
            }
            if (this.au != 0) {
                byteVector2.b(a("SourceFile")).c(2).b(this.au);
            }
            if (this.av != null) {
                int i9 = this.av.b;
                byteVector2.b(a("SourceDebugExtension")).c(i9);
                byteVector2.a(this.av.f3586a, 0, i9);
            }
            if (this.aw != 0) {
                byteVector2.b(a("EnclosingMethod")).c(4);
                byteVector2.b(this.aw).b(this.ax);
            }
            if ((this.ao & 131072) != 0) {
                byteVector2.b(a("Deprecated")).c(0);
            }
            if ((this.ao & 4096) != 0 && ((this.V & 65535) < 49 || (this.ao & 262144) != 0)) {
                byteVector2.b(a("Synthetic")).c(0);
            }
            if (this.aE != null) {
                byteVector2.b(a("InnerClasses"));
                byteVector2.c(this.aE.b + 2).b(this.aD);
                byteVector2.a(this.aE.f3586a, 0, this.aE.b);
            }
            if (this.ay != null) {
                byteVector2.b(a("RuntimeVisibleAnnotations"));
                this.ay.a(byteVector2);
            }
            if (this.az != null) {
                byteVector2.b(a("RuntimeInvisibleAnnotations"));
                this.az.a(byteVector2);
            }
            if (this.aA != null) {
                byteVector2.b(a("RuntimeVisibleTypeAnnotations"));
                this.aA.a(byteVector2);
            }
            if (this.aB != null) {
                byteVector2.b(a("RuntimeInvisibleTypeAnnotations"));
                this.aB.a(byteVector2);
            }
            if (this.aC != null) {
                byteVector = byteVector2;
                this.aC.a(this, (byte[]) null, 0, -1, -1, byteVector);
            } else {
                byteVector = byteVector2;
            }
            if (!this.am) {
                return byteVector.f3586a;
            }
            this.ay = null;
            this.az = null;
            this.aC = null;
            this.aD = 0;
            this.aE = null;
            this.ai = null;
            this.aj = null;
            this.ak = null;
            this.al = null;
            this.aF = 1;
            this.am = false;
            new ClassReader(byteVector.f3586a).a((ClassVisitor) this, 264);
            return b();
        }
        throw new RuntimeException("Class file too large!");
    }

    /* access modifiers changed from: package-private */
    public Item a(Object obj) {
        if (obj instanceof Integer) {
            return a(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return a(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return a((int) ((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return a(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return a(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return a(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return a(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return a(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return g((String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int a2 = type.a();
            if (a2 == 10) {
                return b(type.e());
            }
            if (a2 == 11) {
                return d(type.i());
            }
            return b(type.i());
        } else if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            return a(handle.f3593a, handle.b, handle.c, handle.d, handle.e);
        } else {
            throw new IllegalArgumentException("value " + obj);
        }
    }

    public int b(Object obj) {
        return a(obj).f3595a;
    }

    public int a(String str) {
        this.aa.a(1, str, (String) null, (String) null);
        Item b = b(this.aa);
        if (b == null) {
            this.X.a(1).a(str);
            int i2 = this.W;
            this.W = i2 + 1;
            b = new Item(i2, this.aa);
            c(b);
        }
        return b.f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item b(String str) {
        this.ab.a(7, str, (String) null, (String) null);
        Item b = b(this.ab);
        if (b != null) {
            return b;
        }
        this.X.b(7, a(str));
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.ab);
        c(item);
        return item;
    }

    public int c(String str) {
        return b(str).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item d(String str) {
        this.ab.a(16, str, (String) null, (String) null);
        Item b = b(this.ab);
        if (b != null) {
            return b;
        }
        this.X.b(16, a(str));
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.ab);
        c(item);
        return item;
    }

    public int e(String str) {
        return d(str).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item a(int i2, String str, String str2, String str3, boolean z2) {
        this.ad.a(i2 + 20, str, str2, str3);
        Item b = b(this.ad);
        if (b != null) {
            return b;
        }
        if (i2 <= 4) {
            b(15, i2, c(str, str2, str3));
        } else {
            b(15, i2, b(str, str2, str3, z2));
        }
        int i3 = this.W;
        this.W = i3 + 1;
        Item item = new Item(i3, this.ad);
        c(item);
        return item;
    }

    @Deprecated
    public int a(int i2, String str, String str2, String str3) {
        return b(i2, str, str2, str3, i2 == 9);
    }

    public int b(int i2, String str, String str2, String str3, boolean z2) {
        return a(i2, str, str2, str3, z2).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item a(String str, String str2, Handle handle, Object... objArr) {
        int i2;
        ByteVector byteVector = this.ah;
        if (byteVector == null) {
            byteVector = new ByteVector();
            this.ah = byteVector;
        }
        int i3 = byteVector.b;
        int hashCode = handle.hashCode();
        byteVector.b(b(handle.f3593a, handle.b, handle.c, handle.d, handle.e()));
        byteVector.b(r12);
        int i4 = hashCode;
        for (Object obj : objArr) {
            i4 ^= obj.hashCode();
            byteVector.b(b(obj));
        }
        byte[] bArr = byteVector.f3586a;
        int i5 = (r12 + 2) << 1;
        int i6 = Integer.MAX_VALUE & i4;
        Item item = this.Y[i6 % this.Y.length];
        loop1:
        while (item != null) {
            if (item.b == 33 && item.h == i6) {
                int i7 = item.c;
                int i8 = 0;
                while (i8 < i5) {
                    if (bArr[i3 + i8] != bArr[i7 + i8]) {
                        item = item.i;
                    } else {
                        i8++;
                    }
                }
                break loop1;
            }
            item = item.i;
        }
        if (item != null) {
            i2 = item.f3595a;
            byteVector.b = i3;
        } else {
            i2 = this.ag;
            this.ag = i2 + 1;
            Item item2 = new Item(i2);
            item2.a(i3, i6);
            c(item2);
        }
        this.ac.a(str, str2, i2);
        Item b = b(this.ac);
        if (b != null) {
            return b;
        }
        a(18, i2, b(str, str2));
        int i9 = this.W;
        this.W = i9 + 1;
        Item item3 = new Item(i9, this.ac);
        c(item3);
        return item3;
    }

    public int b(String str, String str2, Handle handle, Object... objArr) {
        return a(str, str2, handle, objArr).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item b(String str, String str2, String str3) {
        this.ac.a(9, str, str2, str3);
        Item b = b(this.ac);
        if (b != null) {
            return b;
        }
        a(9, c(str), b(str2, str3));
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.ac);
        c(item);
        return item;
    }

    public int c(String str, String str2, String str3) {
        return b(str, str2, str3).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item a(String str, String str2, String str3, boolean z2) {
        int i2 = z2 ? 11 : 10;
        this.ac.a(i2, str, str2, str3);
        Item b = b(this.ac);
        if (b != null) {
            return b;
        }
        a(i2, c(str), b(str2, str3));
        int i3 = this.W;
        this.W = i3 + 1;
        Item item = new Item(i3, this.ac);
        c(item);
        return item;
    }

    public int b(String str, String str2, String str3, boolean z2) {
        return a(str, str2, str3, z2).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item a(int i2) {
        this.aa.a(i2);
        Item b = b(this.aa);
        if (b != null) {
            return b;
        }
        this.X.a(3).c(i2);
        int i3 = this.W;
        this.W = i3 + 1;
        Item item = new Item(i3, this.aa);
        c(item);
        return item;
    }

    /* access modifiers changed from: package-private */
    public Item a(float f2) {
        this.aa.a(f2);
        Item b = b(this.aa);
        if (b != null) {
            return b;
        }
        this.X.a(4).c(this.aa.c);
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.aa);
        c(item);
        return item;
    }

    /* access modifiers changed from: package-private */
    public Item a(long j2) {
        this.aa.a(j2);
        Item b = b(this.aa);
        if (b != null) {
            return b;
        }
        this.X.a(5).a(j2);
        Item item = new Item(this.W, this.aa);
        this.W += 2;
        c(item);
        return item;
    }

    /* access modifiers changed from: package-private */
    public Item a(double d2) {
        this.aa.a(d2);
        Item b = b(this.aa);
        if (b != null) {
            return b;
        }
        this.X.a(6).a(this.aa.d);
        Item item = new Item(this.W, this.aa);
        this.W += 2;
        c(item);
        return item;
    }

    private Item g(String str) {
        this.ab.a(8, str, (String) null, (String) null);
        Item b = b(this.ab);
        if (b != null) {
            return b;
        }
        this.X.b(8, a(str));
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.ab);
        c(item);
        return item;
    }

    public int b(String str, String str2) {
        return c(str, str2).f3595a;
    }

    /* access modifiers changed from: package-private */
    public Item c(String str, String str2) {
        this.ab.a(12, str, str2, (String) null);
        Item b = b(this.ab);
        if (b != null) {
            return b;
        }
        a(12, a(str), a(str2));
        int i2 = this.W;
        this.W = i2 + 1;
        Item item = new Item(i2, this.ab);
        c(item);
        return item;
    }

    /* access modifiers changed from: package-private */
    public int f(String str) {
        this.aa.a(30, str, (String) null, (String) null);
        Item b = b(this.aa);
        if (b == null) {
            b = a(this.aa);
        }
        return b.f3595a;
    }

    /* access modifiers changed from: package-private */
    public int a(String str, int i2) {
        this.aa.b = 31;
        this.aa.c = i2;
        this.aa.e = str;
        this.aa.h = (str.hashCode() + 31 + i2) & Integer.MAX_VALUE;
        Item b = b(this.aa);
        if (b == null) {
            b = a(this.aa);
        }
        return b.f3595a;
    }

    private Item a(Item item) {
        this.an = (short) (this.an + 1);
        Item item2 = new Item(this.an, this.aa);
        c(item2);
        if (this.ae == null) {
            this.ae = new Item[16];
        }
        if (this.an == this.ae.length) {
            Item[] itemArr = new Item[(this.ae.length * 2)];
            System.arraycopy(this.ae, 0, itemArr, 0, this.ae.length);
            this.ae = itemArr;
        }
        this.ae[this.an] = item2;
        return item2;
    }

    /* access modifiers changed from: package-private */
    public int a(int i2, int i3) {
        this.ab.b = 32;
        this.ab.d = ((long) i2) | (((long) i3) << 32);
        this.ab.h = (i2 + 32 + i3) & Integer.MAX_VALUE;
        Item b = b(this.ab);
        if (b == null) {
            String str = this.ae[i2].e;
            String str2 = this.ae[i3].e;
            this.ab.c = f(d(str, str2));
            b = new Item(0, this.ab);
            c(b);
        }
        return b.c;
    }

    /* access modifiers changed from: protected */
    public String d(String str, String str2) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class cls = Class.forName(str.replace(IOUtils.f15883a, '.'), false, classLoader);
            Class<?> cls2 = Class.forName(str2.replace(IOUtils.f15883a, '.'), false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return str;
            }
            if (cls2.isAssignableFrom(cls)) {
                return str2;
            }
            if (cls.isInterface() || cls2.isInterface()) {
                return "java/lang/Object";
            }
            do {
                cls = cls.getSuperclass();
            } while (!cls.isAssignableFrom(cls2));
            return cls.getName().replace('.', IOUtils.f15883a);
        } catch (Exception e2) {
            throw new RuntimeException(e2.toString());
        }
    }

    private Item b(Item item) {
        Item item2 = this.Y[item.h % this.Y.length];
        while (item2 != null && (item2.b != item.b || !item.a(item2))) {
            item2 = item2.i;
        }
        return item2;
    }

    private void c(Item item) {
        if (this.W + this.an > this.Z) {
            int length = this.Y.length;
            int i2 = (length * 2) + 1;
            Item[] itemArr = new Item[i2];
            for (int i3 = length - 1; i3 >= 0; i3--) {
                Item item2 = this.Y[i3];
                while (item2 != null) {
                    int length2 = item2.h % itemArr.length;
                    Item item3 = item2.i;
                    item2.i = itemArr[length2];
                    itemArr[length2] = item2;
                    item2 = item3;
                }
            }
            this.Y = itemArr;
            double d2 = (double) i2;
            Double.isNaN(d2);
            this.Z = (int) (d2 * 0.75d);
        }
        int length3 = item.h % this.Y.length;
        item.i = this.Y[length3];
        this.Y[length3] = item;
    }

    private void a(int i2, int i3, int i4) {
        this.X.b(i2, i3).b(i4);
    }

    private void b(int i2, int i3, int i4) {
        this.X.a(i2, i3).b(i4);
    }
}
