package org.jacoco.agent.rt.internal_8ff85ea.asm;

import cn.com.fmsh.communication.core.MessageHead;
import com.libra.Color;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f3587a = true;
    static final boolean b = true;
    static final boolean c = true;
    static final boolean d = true;
    static final boolean e = true;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 4;
    public static final int i = 8;
    static final int j = 256;
    public final byte[] k;
    public final int l;
    private final int[] m;
    private final String[] n;
    private final int o;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i2, int i3) {
        this.k = bArr;
        if (d(i2 + 6) <= 52) {
            this.m = new int[c(i2 + 8)];
            int length = this.m.length;
            this.n = new String[length];
            int i4 = i2 + 10;
            int i5 = 1;
            int i6 = 0;
            while (i5 < length) {
                int i7 = i4 + 1;
                this.m[i5] = i7;
                byte b2 = bArr[i4];
                int i8 = 3;
                if (b2 == 1) {
                    i8 = 3 + c(i7);
                    if (i8 > i6) {
                        i6 = i8;
                    }
                } else if (b2 != 15) {
                    if (b2 != 18) {
                        switch (b2) {
                            case 3:
                            case 4:
                                break;
                            case 5:
                            case 6:
                                i8 = 9;
                                i5++;
                                continue;
                            default:
                                switch (b2) {
                                    case 9:
                                    case 10:
                                    case 11:
                                    case 12:
                                        break;
                                    default:
                                        continue;
                                }
                        }
                    }
                    i8 = 5;
                } else {
                    i8 = 4;
                }
                i4 += i8;
                i5++;
            }
            this.o = i6;
            this.l = i4;
            return;
        }
        throw new IllegalArgumentException();
    }

    public int a() {
        return c(this.l);
    }

    public String b() {
        return b(this.l + 2, new char[this.o]);
    }

    public String c() {
        return b(this.l + 4, new char[this.o]);
    }

    public String[] d() {
        int i2 = this.l + 6;
        int c2 = c(i2);
        String[] strArr = new String[c2];
        if (c2 > 0) {
            char[] cArr = new char[this.o];
            for (int i3 = 0; i3 < c2; i3++) {
                i2 += 2;
                strArr[i3] = b(i2, cArr);
            }
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public void a(ClassWriter classWriter) {
        char[] cArr = new char[this.o];
        int length = this.m.length;
        Item[] itemArr = new Item[length];
        int i2 = 1;
        while (i2 < length) {
            int i3 = this.m[i2];
            byte b2 = this.k[i3 - 1];
            Item item = new Item(i2);
            if (b2 == 1) {
                String str = this.n[i2];
                if (str == null) {
                    int i4 = this.m[i2];
                    String[] strArr = this.n;
                    str = a(i4 + 2, c(i4), cArr);
                    strArr[i2] = str;
                }
                item.a(b2, str, (String) null, (String) null);
            } else if (b2 == 15) {
                int i5 = this.m[c(i3 + 1)];
                int i6 = this.m[c(i5 + 2)];
                item.a(b(i3) + 20, b(i5, cArr), a(i6, cArr), a(i6 + 2, cArr));
            } else if (b2 != 18) {
                switch (b2) {
                    case 3:
                        item.a(e(i3));
                        break;
                    case 4:
                        item.a(Float.intBitsToFloat(e(i3)));
                        break;
                    case 5:
                        item.a(f(i3));
                        i2++;
                        break;
                    case 6:
                        item.a(Double.longBitsToDouble(f(i3)));
                        i2++;
                        break;
                    default:
                        switch (b2) {
                            case 9:
                            case 10:
                            case 11:
                                int i7 = this.m[c(i3 + 2)];
                                item.a(b2, b(i3, cArr), a(i7, cArr), a(i7 + 2, cArr));
                                break;
                            case 12:
                                item.a(b2, a(i3, cArr), a(i3 + 2, cArr), (String) null);
                                break;
                            default:
                                item.a(b2, a(i3, cArr), (String) null, (String) null);
                                break;
                        }
                }
            } else {
                if (classWriter.ah == null) {
                    a(classWriter, itemArr, cArr);
                }
                int i8 = this.m[c(i3 + 2)];
                item.a(a(i8, cArr), a(i8 + 2, cArr), c(i3));
            }
            int length2 = item.h % itemArr.length;
            item.i = itemArr[length2];
            itemArr[length2] = item;
            i2++;
        }
        int i9 = this.m[1] - 1;
        classWriter.X.a(this.k, i9, this.l - i9);
        classWriter.Y = itemArr;
        double d2 = (double) length;
        Double.isNaN(d2);
        classWriter.Z = (int) (d2 * 0.75d);
        classWriter.W = length;
    }

    private void a(ClassWriter classWriter, Item[] itemArr, char[] cArr) {
        int i2;
        boolean z;
        int g2 = g();
        int c2 = c(g2);
        while (true) {
            if (c2 <= 0) {
                z = false;
                break;
            } else if ("BootstrapMethods".equals(a(g2 + 2, cArr))) {
                z = true;
                break;
            } else {
                g2 += e(g2 + 4) + 6;
                c2--;
            }
        }
        if (z) {
            int c3 = c(g2 + 8);
            int i3 = g2 + 10;
            int i4 = i3;
            for (i2 = 0; i2 < c3; i2++) {
                int i5 = (i4 - g2) - 10;
                int hashCode = c(c(i4), cArr).hashCode();
                for (int c4 = c(i4 + 2); c4 > 0; c4--) {
                    hashCode ^= c(c(i4 + 4), cArr).hashCode();
                    i4 += 2;
                }
                i4 += 4;
                Item item = new Item(i2);
                item.a(i5, hashCode & Integer.MAX_VALUE);
                int length = item.h % itemArr.length;
                item.i = itemArr[length];
                itemArr[length] = item;
            }
            int e2 = e(g2 + 4);
            ByteVector byteVector = new ByteVector(e2 + 62);
            byteVector.a(this.k, i3, e2 - 2);
            classWriter.ag = c3;
            classWriter.ah = byteVector;
        }
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(a(inputStream, false));
    }

    public ClassReader(String str) throws IOException {
        this(a(ClassLoader.getSystemResourceAsStream(str.replace('.', IOUtils.f15883a) + ".class"), true));
    }

    private static byte[] a(InputStream inputStream, boolean z) throws IOException {
        if (inputStream != null) {
            try {
                byte[] bArr = new byte[inputStream.available()];
                int i2 = 0;
                while (true) {
                    int read = inputStream.read(bArr, i2, bArr.length - i2);
                    if (read == -1) {
                        if (i2 < bArr.length) {
                            byte[] bArr2 = new byte[i2];
                            System.arraycopy(bArr, 0, bArr2, 0, i2);
                            bArr = bArr2;
                        }
                        if (z) {
                            inputStream.close();
                        }
                        return bArr;
                    }
                    i2 += read;
                    if (i2 == bArr.length) {
                        int read2 = inputStream.read();
                        if (read2 < 0) {
                            return bArr;
                        }
                        byte[] bArr3 = new byte[(bArr.length + 1000)];
                        System.arraycopy(bArr, 0, bArr3, 0, i2);
                        int i3 = i2 + 1;
                        bArr3[i2] = (byte) read2;
                        i2 = i3;
                        bArr = bArr3;
                    }
                }
            } finally {
                if (z) {
                    inputStream.close();
                }
            }
        } else {
            throw new IOException("Class not found");
        }
    }

    public void a(ClassVisitor classVisitor, int i2) {
        a(classVisitor, new Attribute[0], i2);
    }

    public void a(ClassVisitor classVisitor, Attribute[] attributeArr, int i2) {
        String[] strArr;
        char[] cArr;
        Context context;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Attribute attribute;
        ClassVisitor classVisitor2 = classVisitor;
        int i3 = i2;
        int i4 = this.l;
        char[] cArr2 = new char[this.o];
        Context context2 = new Context();
        context2.f3589a = attributeArr;
        context2.b = i3;
        context2.c = cArr2;
        int c2 = c(i4);
        String b2 = b(i4 + 2, cArr2);
        String b3 = b(i4 + 4, cArr2);
        String[] strArr2 = new String[c(i4 + 6)];
        int i5 = i4 + 8;
        for (int i6 = 0; i6 < strArr2.length; i6++) {
            strArr2[i6] = b(i5, cArr2);
            i5 += 2;
        }
        int g2 = g();
        int i7 = g2;
        int i8 = c2;
        int c3 = c(g2);
        String[] strArr3 = strArr2;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        Attribute attribute2 = null;
        int i13 = 0;
        while (c3 > 0) {
            String a2 = a(i7 + 2, cArr2);
            if ("SourceFile".equals(a2)) {
                str10 = a(i7 + 8, cArr2);
            } else if ("InnerClasses".equals(a2)) {
                i13 = i7 + 8;
            } else if ("EnclosingMethod".equals(a2)) {
                str8 = b(i7 + 8, cArr2);
                int c4 = c(i7 + 10);
                if (c4 != 0) {
                    str7 = a(this.m[c4], cArr2);
                    str6 = a(this.m[c4] + 2, cArr2);
                }
            } else if ("Signature".equals(a2)) {
                str11 = a(i7 + 8, cArr2);
            } else if ("RuntimeVisibleAnnotations".equals(a2)) {
                i9 = i7 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(a2)) {
                i11 = i7 + 8;
            } else if ("Deprecated".equals(a2)) {
                i8 |= 131072;
            } else if ("Synthetic".equals(a2)) {
                i8 |= 266240;
            } else if ("SourceDebugExtension".equals(a2)) {
                int e2 = e(i7 + 4);
                str9 = a(i7 + 8, e2, new char[e2]);
            } else if ("RuntimeInvisibleAnnotations".equals(a2)) {
                i10 = i7 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(a2)) {
                i12 = i7 + 8;
            } else {
                if ("BootstrapMethods".equals(a2)) {
                    int[] iArr = new int[c(i7 + 8)];
                    int i14 = i7 + 10;
                    for (int i15 = 0; i15 < iArr.length; i15++) {
                        iArr[i15] = i14;
                        i14 += (c(i14 + 2) + 2) << 1;
                    }
                    context2.d = iArr;
                    str = str6;
                    str2 = str7;
                    str3 = str9;
                    cArr = cArr2;
                    context = context2;
                    attribute = attribute2;
                    strArr = strArr3;
                    str4 = str8;
                    str5 = str10;
                } else {
                    context = context2;
                    str2 = str7;
                    str4 = str8;
                    String str12 = a2;
                    String str13 = str9;
                    char[] cArr3 = cArr2;
                    cArr = cArr2;
                    str5 = str10;
                    str = str6;
                    strArr = strArr3;
                    str3 = str13;
                    Attribute a3 = a(attributeArr, str12, i7 + 8, e(i7 + 4), cArr3, -1, (Label[]) null);
                    if (a3 != null) {
                        a3.c = attribute2;
                        attribute2 = a3;
                        str10 = str5;
                        str8 = str4;
                        str9 = str3;
                        str7 = str2;
                        str6 = str;
                        i7 += e(i7 + 4) + 6;
                        c3--;
                        context2 = context;
                        cArr2 = cArr;
                        strArr3 = strArr;
                        Attribute[] attributeArr2 = attributeArr;
                    } else {
                        attribute = attribute2;
                    }
                }
                attribute2 = attribute;
                str10 = str5;
                str8 = str4;
                str9 = str3;
                str7 = str2;
                str6 = str;
                i7 += e(i7 + 4) + 6;
                c3--;
                context2 = context;
                cArr2 = cArr;
                strArr3 = strArr;
                Attribute[] attributeArr22 = attributeArr;
            }
            cArr = cArr2;
            context = context2;
            strArr = strArr3;
            i7 += e(i7 + 4) + 6;
            c3--;
            context2 = context;
            cArr2 = cArr;
            strArr3 = strArr;
            Attribute[] attributeArr222 = attributeArr;
        }
        String str14 = str6;
        String str15 = str7;
        String str16 = str9;
        char[] cArr4 = cArr2;
        Context context3 = context2;
        Attribute attribute3 = attribute2;
        String[] strArr4 = strArr3;
        String str17 = str8;
        String str18 = str10;
        classVisitor.a(e(this.m[1] - 7), i8, b2, str11, b3, strArr4);
        if ((i3 & 2) == 0 && !(str18 == null && str16 == null)) {
            classVisitor2.a(str18, str16);
        }
        if (str17 != null) {
            classVisitor2.a(str17, str15, str14);
        }
        int i16 = i9;
        if (i16 != 0) {
            int i17 = i16 + 2;
            for (int c5 = c(i16); c5 > 0; c5--) {
                char[] cArr5 = cArr4;
                i17 = a(i17 + 2, cArr5, true, classVisitor2.a(a(i17, cArr5), true));
            }
        }
        char[] cArr6 = cArr4;
        int i18 = i10;
        if (i18 != 0) {
            int i19 = i18 + 2;
            for (int c6 = c(i18); c6 > 0; c6--) {
                i19 = a(i19 + 2, cArr6, true, classVisitor2.a(a(i19, cArr6), false));
            }
        }
        int i20 = i11;
        if (i20 != 0) {
            int i21 = i20 + 2;
            for (int c7 = c(i20); c7 > 0; c7--) {
                Context context4 = context3;
                int a4 = a(context4, i21);
                i21 = a(a4 + 2, cArr6, true, classVisitor2.a(context4.i, context4.j, a(a4, cArr6), true));
            }
        }
        Context context5 = context3;
        int i22 = i12;
        if (i22 != 0) {
            int i23 = i22 + 2;
            for (int c8 = c(i22); c8 > 0; c8--) {
                int a5 = a(context5, i23);
                i23 = a(a5 + 2, cArr6, true, classVisitor2.a(context5.i, context5.j, a(a5, cArr6), false));
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.c;
            attribute3.c = null;
            classVisitor2.a(attribute3);
            attribute3 = attribute4;
        }
        int i24 = i13;
        if (i24 != 0) {
            int i25 = i24 + 2;
            for (int c9 = c(i24); c9 > 0; c9--) {
                classVisitor2.a(b(i25, cArr6), b(i25 + 2, cArr6), a(i25 + 4, cArr6), c(i25 + 6));
                i25 += 8;
            }
        }
        int length = this.l + 10 + (strArr4.length * 2);
        for (int c10 = c(length - 2); c10 > 0; c10--) {
            length = a(classVisitor2, context5, length);
        }
        int i26 = length + 2;
        for (int c11 = c(i26 - 2); c11 > 0; c11--) {
            i26 = b(classVisitor2, context5, i26);
        }
        classVisitor.a();
    }

    private int a(ClassVisitor classVisitor, Context context, int i2) {
        int i3;
        Context context2 = context;
        int i4 = i2;
        char[] cArr = context2.c;
        int c2 = c(i4);
        String a2 = a(i4 + 2, cArr);
        String a3 = a(i4 + 4, cArr);
        int i5 = i4 + 6;
        int i6 = i5;
        int i7 = c2;
        int c3 = c(i5);
        Attribute attribute = null;
        String str = null;
        Object obj = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (c3 > 0) {
            String a4 = a(i6 + 2, cArr);
            if ("ConstantValue".equals(a4)) {
                int c4 = c(i6 + 8);
                if (c4 == 0) {
                    obj = null;
                } else {
                    obj = c(c4, cArr);
                }
            } else if ("Signature".equals(a4)) {
                str = a(i6 + 8, cArr);
            } else {
                if ("Deprecated".equals(a4)) {
                    i3 = i7 | 131072;
                } else if ("Synthetic".equals(a4)) {
                    i3 = i7 | 266240;
                } else if ("RuntimeVisibleAnnotations".equals(a4)) {
                    i11 = i6 + 8;
                } else if ("RuntimeVisibleTypeAnnotations".equals(a4)) {
                    i9 = i6 + 8;
                } else if ("RuntimeInvisibleAnnotations".equals(a4)) {
                    i10 = i6 + 8;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(a4)) {
                    i8 = i6 + 8;
                } else {
                    Attribute attribute2 = attribute;
                    int i12 = i8;
                    int i13 = i9;
                    int i14 = i10;
                    int i15 = i11;
                    Attribute a5 = a(context2.f3589a, a4, i6 + 8, e(i6 + 4), cArr, -1, (Label[]) null);
                    if (a5 != null) {
                        a5.c = attribute2;
                        attribute = a5;
                    } else {
                        attribute = attribute2;
                    }
                    i11 = i15;
                    i8 = i12;
                    i9 = i13;
                    i10 = i14;
                }
                i7 = i3;
            }
            i6 += e(i6 + 4) + 6;
            c3--;
            context2 = context;
        }
        Attribute attribute3 = attribute;
        int i16 = i8;
        int i17 = i9;
        int i18 = i10;
        int i19 = i11;
        int i20 = i6 + 2;
        FieldVisitor a6 = classVisitor.a(i7, a2, a3, str, obj);
        if (a6 == null) {
            return i20;
        }
        if (i19 != 0) {
            int i21 = i19 + 2;
            for (int c5 = c(i19); c5 > 0; c5--) {
                i21 = a(i21 + 2, cArr, true, a6.a(a(i21, cArr), true));
            }
        }
        if (i18 != 0) {
            int i22 = i18;
            int i23 = i22 + 2;
            for (int c6 = c(i22); c6 > 0; c6--) {
                i23 = a(i23 + 2, cArr, true, a6.a(a(i23, cArr), false));
            }
        }
        int i24 = i17;
        if (i24 != 0) {
            int i25 = i24 + 2;
            for (int c7 = c(i24); c7 > 0; c7--) {
                Context context3 = context;
                int a7 = a(context3, i25);
                i25 = a(a7 + 2, cArr, true, a6.a(context3.i, context3.j, a(a7, cArr), true));
            }
        }
        Context context4 = context;
        int i26 = i16;
        if (i26 != 0) {
            int i27 = i26 + 2;
            for (int c8 = c(i26); c8 > 0; c8--) {
                int a8 = a(context4, i27);
                i27 = a(a8 + 2, cArr, true, a6.a(context4.i, context4.j, a(a8, cArr), false));
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.c;
            attribute3.c = null;
            a6.a(attribute3);
            attribute3 = attribute4;
        }
        a6.a();
        return i20;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01c7, code lost:
        if (r1.t == 0) goto L_0x01e6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor r34, org.jacoco.agent.rt.internal_8ff85ea.asm.Context r35, int r36) {
        /*
            r33 = this;
            r8 = r33
            r9 = r35
            r0 = r36
            char[] r10 = r9.c
            int r1 = r8.c(r0)
            r9.e = r1
            int r1 = r0 + 2
            java.lang.String r1 = r8.a((int) r1, (char[]) r10)
            r9.f = r1
            int r1 = r0 + 4
            java.lang.String r1 = r8.a((int) r1, (char[]) r10)
            r9.g = r1
            int r11 = r0 + 6
            int r0 = r8.c(r11)
            r14 = r0
            r15 = r11
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r13 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
        L_0x0037:
            if (r14 <= 0) goto L_0x018a
            int r12 = r15 + 2
            java.lang.String r12 = r8.a((int) r12, (char[]) r10)
            r21 = r0
            java.lang.String r0 = "Code"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0068
            int r0 = r9.b
            r12 = 1
            r0 = r0 & r12
            if (r0 != 0) goto L_0x0057
            int r0 = r15 + 8
            r19 = r0
        L_0x0053:
            r0 = r21
            goto L_0x017d
        L_0x0057:
            r27 = r1
            r28 = r2
            r12 = r3
            r29 = r4
            r30 = r5
            r31 = r6
            r32 = r7
            r26 = r21
            goto L_0x016e
        L_0x0068:
            java.lang.String r0 = "Exceptions"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0090
            int r0 = r15 + 8
            int r0 = r8.c(r0)
            java.lang.String[] r0 = new java.lang.String[r0]
            int r6 = r15 + 10
            r22 = r1
            r12 = r6
            r6 = 0
        L_0x007e:
            int r1 = r0.length
            if (r6 >= r1) goto L_0x008c
            java.lang.String r1 = r8.b(r12, r10)
            r0[r6] = r1
            int r12 = r12 + 2
            int r6 = r6 + 1
            goto L_0x007e
        L_0x008c:
            r6 = r0
            r17 = r12
            goto L_0x00a1
        L_0x0090:
            r22 = r1
            java.lang.String r0 = "Signature"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00a7
            int r0 = r15 + 8
            java.lang.String r0 = r8.a((int) r0, (char[]) r10)
            r7 = r0
        L_0x00a1:
            r0 = r21
        L_0x00a3:
            r1 = r22
            goto L_0x017d
        L_0x00a7:
            java.lang.String r0 = "Deprecated"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00c7
            int r0 = r9.e
            r1 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 | r1
            r9.e = r0
        L_0x00b6:
            r28 = r2
            r12 = r3
            r29 = r4
            r30 = r5
            r31 = r6
            r32 = r7
            r26 = r21
            r27 = r22
            goto L_0x016e
        L_0x00c7:
            java.lang.String r0 = "RuntimeVisibleAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00d3
            int r0 = r15 + 8
            r5 = r0
            goto L_0x00a1
        L_0x00d3:
            java.lang.String r0 = "RuntimeVisibleTypeAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00df
            int r0 = r15 + 8
            r2 = r0
            goto L_0x00a1
        L_0x00df:
            java.lang.String r0 = "AnnotationDefault"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00eb
            int r0 = r15 + 8
            r4 = r0
            goto L_0x00a1
        L_0x00eb:
            java.lang.String r0 = "Synthetic"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x00fc
            int r0 = r9.e
            r1 = 266240(0x41000, float:3.73082E-40)
            r0 = r0 | r1
            r9.e = r0
            goto L_0x00b6
        L_0x00fc:
            java.lang.String r0 = "RuntimeInvisibleAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0108
            int r0 = r15 + 8
            r3 = r0
            goto L_0x00a1
        L_0x0108:
            java.lang.String r0 = "RuntimeInvisibleTypeAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0115
            int r0 = r15 + 8
            r1 = r0
            goto L_0x0053
        L_0x0115:
            java.lang.String r0 = "RuntimeVisibleParameterAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0120
            int r0 = r15 + 8
            goto L_0x00a3
        L_0x0120:
            java.lang.String r0 = "RuntimeInvisibleParameterAnnotations"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x012e
            int r0 = r15 + 8
            r18 = r0
            goto L_0x00a1
        L_0x012e:
            java.lang.String r0 = "MethodParameters"
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x013c
            int r0 = r15 + 8
            r16 = r0
            goto L_0x00a1
        L_0x013c:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute[] r1 = r9.f3589a
            int r20 = r15 + 8
            int r0 = r15 + 4
            int r23 = r8.e(r0)
            r24 = -1
            r25 = 0
            r26 = r21
            r0 = r33
            r27 = r22
            r28 = r2
            r2 = r12
            r12 = r3
            r3 = r20
            r29 = r4
            r4 = r23
            r30 = r5
            r5 = r10
            r31 = r6
            r6 = r24
            r32 = r7
            r7 = r25
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute r0 = r0.a(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x016e
            r0.c = r13
            r13 = r0
        L_0x016e:
            r3 = r12
            r0 = r26
            r1 = r27
            r2 = r28
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
        L_0x017d:
            int r12 = r15 + 4
            int r12 = r8.e(r12)
            int r12 = r12 + 6
            int r15 = r15 + r12
            int r14 = r14 + -1
            goto L_0x0037
        L_0x018a:
            r26 = r0
            r27 = r1
            r28 = r2
            r12 = r3
            r29 = r4
            r30 = r5
            r31 = r6
            r32 = r7
            int r15 = r15 + 2
            int r1 = r9.e
            java.lang.String r2 = r9.f
            java.lang.String r3 = r9.g
            r0 = r34
            r4 = r32
            r5 = r31
            org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor r0 = r0.a((int) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String[]) r5)
            if (r0 != 0) goto L_0x01ae
            return r15
        L_0x01ae:
            boolean r1 = r0 instanceof org.jacoco.agent.rt.internal_8ff85ea.asm.MethodWriter
            if (r1 == 0) goto L_0x01f2
            r1 = r0
            org.jacoco.agent.rt.internal_8ff85ea.asm.MethodWriter r1 = (org.jacoco.agent.rt.internal_8ff85ea.asm.MethodWriter) r1
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r2 = r1.p
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader r2 = r2.U
            if (r2 != r8) goto L_0x01f2
            java.lang.String r2 = r1.q
            r7 = r32
            if (r7 != r2) goto L_0x01f2
            r6 = r31
            if (r6 != 0) goto L_0x01ca
            int r2 = r1.t
            if (r2 != 0) goto L_0x01e8
            goto L_0x01e6
        L_0x01ca:
            int r2 = r6.length
            int r3 = r1.t
            if (r2 != r3) goto L_0x01e8
            int r2 = r6.length
            r3 = 1
            int r2 = r2 - r3
        L_0x01d2:
            if (r2 < 0) goto L_0x01e6
            int r3 = r17 + -2
            int[] r4 = r1.u
            r4 = r4[r2]
            int r5 = r8.c(r3)
            if (r4 == r5) goto L_0x01e1
            goto L_0x01e8
        L_0x01e1:
            int r2 = r2 + -1
            r17 = r3
            goto L_0x01d2
        L_0x01e6:
            r2 = 1
            goto L_0x01e9
        L_0x01e8:
            r2 = 0
        L_0x01e9:
            if (r2 == 0) goto L_0x01f2
            r1.r = r11
            int r0 = r15 - r11
            r1.s = r0
            return r15
        L_0x01f2:
            if (r16 == 0) goto L_0x0213
            byte[] r1 = r8.k
            byte r1 = r1[r16]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 1
            int r16 = r16 + 1
            r2 = r16
        L_0x01ff:
            if (r1 <= 0) goto L_0x0213
            java.lang.String r3 = r8.a((int) r2, (char[]) r10)
            int r4 = r2 + 2
            int r4 = r8.c(r4)
            r0.a((java.lang.String) r3, (int) r4)
            int r1 = r1 + -1
            int r2 = r2 + 4
            goto L_0x01ff
        L_0x0213:
            r4 = r29
            if (r4 == 0) goto L_0x0224
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r1 = r0.a()
            r2 = 0
            r8.a((int) r4, (char[]) r10, (java.lang.String) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r1)
            if (r1 == 0) goto L_0x0224
            r1.a()
        L_0x0224:
            r5 = r30
            if (r5 == 0) goto L_0x0242
            int r1 = r8.c(r5)
            int r5 = r5 + 2
        L_0x022e:
            if (r1 <= 0) goto L_0x0242
            int r2 = r5 + 2
            java.lang.String r3 = r8.a((int) r5, (char[]) r10)
            r4 = 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r3 = r0.a((java.lang.String) r3, (boolean) r4)
            int r5 = r8.a((int) r2, (char[]) r10, (boolean) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r3)
            int r1 = r1 + -1
            goto L_0x022e
        L_0x0242:
            if (r12 == 0) goto L_0x025f
            int r1 = r8.c(r12)
            int r3 = r12 + 2
        L_0x024a:
            if (r1 <= 0) goto L_0x025f
            int r2 = r3 + 2
            java.lang.String r3 = r8.a((int) r3, (char[]) r10)
            r4 = 0
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r3 = r0.a((java.lang.String) r3, (boolean) r4)
            r4 = 1
            int r3 = r8.a((int) r2, (char[]) r10, (boolean) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r3)
            int r1 = r1 + -1
            goto L_0x024a
        L_0x025f:
            r2 = r28
            if (r2 == 0) goto L_0x0285
            int r1 = r8.c(r2)
            int r2 = r2 + 2
        L_0x0269:
            if (r1 <= 0) goto L_0x0285
            int r2 = r8.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r9, (int) r2)
            int r3 = r2 + 2
            int r4 = r9.i
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r5 = r9.j
            java.lang.String r2 = r8.a((int) r2, (char[]) r10)
            r6 = 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r2 = r0.a((int) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath) r5, (java.lang.String) r2, (boolean) r6)
            int r2 = r8.a((int) r3, (char[]) r10, (boolean) r6, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r2)
            int r1 = r1 + -1
            goto L_0x0269
        L_0x0285:
            r1 = r27
            if (r1 == 0) goto L_0x02ac
            int r2 = r8.c(r1)
            int r1 = r1 + 2
        L_0x028f:
            if (r2 <= 0) goto L_0x02ac
            int r1 = r8.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r9, (int) r1)
            int r3 = r1 + 2
            int r4 = r9.i
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r5 = r9.j
            java.lang.String r1 = r8.a((int) r1, (char[]) r10)
            r6 = 0
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r1 = r0.a((int) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath) r5, (java.lang.String) r1, (boolean) r6)
            r4 = 1
            int r1 = r8.a((int) r3, (char[]) r10, (boolean) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r1)
            int r2 = r2 + -1
            goto L_0x028f
        L_0x02ac:
            r4 = 1
            r1 = r26
            if (r1 == 0) goto L_0x02b4
            r8.b(r0, r9, r1, r4)
        L_0x02b4:
            r1 = r18
            if (r1 == 0) goto L_0x02bc
            r2 = 0
            r8.b(r0, r9, r1, r2)
        L_0x02bc:
            if (r13 == 0) goto L_0x02c8
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute r1 = r13.c
            r2 = 0
            r13.c = r2
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute) r13)
            r13 = r1
            goto L_0x02bc
        L_0x02c8:
            r13 = r19
            if (r13 == 0) goto L_0x02d2
            r0.b()
            r8.a((org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r9, (int) r13)
        L_0x02d2:
            r0.c()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader.b(org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor, org.jacoco.agent.rt.internal_8ff85ea.asm.Context, int):int");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0922: MOVE  (r0v26 int) = (r25v1 int)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    private void a(org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor r57, org.jacoco.agent.rt.internal_8ff85ea.asm.Context r58, int r59) {
        /*
            r56 = this;
            r7 = r56
            r15 = r57
            r14 = r58
            r0 = r59
            byte[] r8 = r7.k
            char[] r13 = r14.c
            int r12 = r7.c(r0)
            int r1 = r0 + 2
            int r11 = r7.c(r1)
            int r1 = r0 + 4
            int r9 = r7.e(r1)
            r10 = 8
            int r16 = r0 + 8
            int r6 = r16 + r9
            int r0 = r9 + 2
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r5 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Label[r0]
            r14.h = r5
            int r0 = r9 + 1
            r7.a((int) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            r0 = r16
        L_0x002f:
            r4 = 132(0x84, float:1.85E-43)
            r3 = 1
            if (r0 >= r6) goto L_0x00e2
            int r1 = r0 - r16
            byte r2 = r8[r0]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r17 = org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter.A
            byte r2 = r17[r2]
            switch(r2) {
                case 0: goto L_0x00de;
                case 1: goto L_0x00da;
                case 2: goto L_0x00d6;
                case 3: goto L_0x00da;
                case 4: goto L_0x00de;
                case 5: goto L_0x00d6;
                case 6: goto L_0x00d6;
                case 7: goto L_0x00d2;
                case 8: goto L_0x00d2;
                case 9: goto L_0x00c4;
                case 10: goto L_0x00b6;
                case 11: goto L_0x00da;
                case 12: goto L_0x00d6;
                case 13: goto L_0x00d6;
                case 14: goto L_0x0086;
                case 15: goto L_0x005f;
                case 16: goto L_0x0041;
                case 17: goto L_0x0051;
                case 18: goto L_0x0044;
                default: goto L_0x0041;
            }
        L_0x0041:
            int r0 = r0 + 4
            goto L_0x002f
        L_0x0044:
            int r2 = r0 + 1
            int r2 = r7.c(r2)
            int r1 = r1 + r2
            r7.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r0 = r0 + 3
            goto L_0x002f
        L_0x0051:
            int r1 = r0 + 1
            byte r1 = r8[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            if (r1 != r4) goto L_0x005c
            int r0 = r0 + 6
            goto L_0x002f
        L_0x005c:
            int r0 = r0 + 4
            goto L_0x002f
        L_0x005f:
            int r0 = r0 + 4
            r2 = r1 & 3
            int r0 = r0 - r2
            int r2 = r7.e(r0)
            int r2 = r2 + r1
            r7.a((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r2 = r0 + 4
            int r2 = r7.e(r2)
        L_0x0072:
            if (r2 <= 0) goto L_0x0083
            int r3 = r0 + 12
            int r3 = r7.e(r3)
            int r3 = r3 + r1
            r7.a((int) r3, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r0 = r0 + 8
            int r2 = r2 + -1
            goto L_0x0072
        L_0x0083:
            int r0 = r0 + 8
            goto L_0x002f
        L_0x0086:
            int r0 = r0 + 4
            r2 = r1 & 3
            int r0 = r0 - r2
            int r2 = r7.e(r0)
            int r2 = r2 + r1
            r7.a((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r2 = r0 + 8
            int r2 = r7.e(r2)
            int r4 = r0 + 4
            int r4 = r7.e(r4)
            int r2 = r2 - r4
            int r2 = r2 + r3
        L_0x00a1:
            if (r2 <= 0) goto L_0x00b2
            int r3 = r0 + 12
            int r3 = r7.e(r3)
            int r3 = r3 + r1
            r7.a((int) r3, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r0 = r0 + 4
            int r2 = r2 + -1
            goto L_0x00a1
        L_0x00b2:
            int r0 = r0 + 12
            goto L_0x002f
        L_0x00b6:
            int r2 = r0 + 1
            int r2 = r7.e(r2)
            int r1 = r1 + r2
            r7.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r0 = r0 + 5
            goto L_0x002f
        L_0x00c4:
            int r2 = r0 + 1
            short r2 = r7.d(r2)
            int r1 = r1 + r2
            r7.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r0 = r0 + 3
            goto L_0x002f
        L_0x00d2:
            int r0 = r0 + 5
            goto L_0x002f
        L_0x00d6:
            int r0 = r0 + 3
            goto L_0x002f
        L_0x00da:
            int r0 = r0 + 2
            goto L_0x002f
        L_0x00de:
            int r0 = r0 + 1
            goto L_0x002f
        L_0x00e2:
            int r1 = r7.c(r0)
        L_0x00e6:
            if (r1 <= 0) goto L_0x011f
            int r2 = r0 + 2
            int r2 = r7.c(r2)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r2 = r7.a((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r4 = r0 + 4
            int r4 = r7.c(r4)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r4 = r7.a((int) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r3 = r0 + 6
            int r3 = r7.c(r3)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r7.a((int) r3, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int[] r10 = r7.m
            int r0 = r0 + 8
            int r17 = r7.c(r0)
            r10 = r10[r17]
            java.lang.String r10 = r7.a((int) r10, (char[]) r13)
            r15.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r4, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r3, (java.lang.String) r10)
            int r1 = r1 + -1
            r3 = 1
            r4 = 132(0x84, float:1.85E-43)
            r10 = 8
            goto L_0x00e6
        L_0x011f:
            int r0 = r0 + 2
            int r1 = r14.b
            r2 = 8
            r1 = r1 & r2
            if (r1 == 0) goto L_0x012a
            r4 = 1
            goto L_0x012b
        L_0x012a:
            r4 = 0
        L_0x012b:
            int r1 = r7.c(r0)
            r21 = r0
            r17 = r1
            r31 = r4
            r0 = 0
            r1 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 1
            r28 = 0
            r29 = -1
            r30 = -1
        L_0x0149:
            r4 = 67
            if (r17 <= 0) goto L_0x0375
            int r2 = r21 + 2
            java.lang.String r2 = r7.a((int) r2, (char[]) r13)
            java.lang.String r3 = "LocalVariableTable"
            boolean r3 = r3.equals(r2)
            if (r3 == 0) goto L_0x01c2
            int r2 = r14.b
            r2 = r2 & 2
            if (r2 != 0) goto L_0x01bb
            int r2 = r21 + 8
            int r3 = r7.c(r2)
            r4 = r21
        L_0x0169:
            if (r3 <= 0) goto L_0x01ab
            int r10 = r4 + 10
            r33 = r0
            int r0 = r7.c(r10)
            r25 = r5[r0]
            if (r25 != 0) goto L_0x0188
            r34 = r1
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r7.a((int) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            r35 = r2
            int r2 = r1.n
            r19 = 1
            r2 = r2 | 1
            r1.n = r2
            goto L_0x018c
        L_0x0188:
            r34 = r1
            r35 = r2
        L_0x018c:
            int r4 = r4 + 12
            int r1 = r7.c(r4)
            int r0 = r0 + r1
            r1 = r5[r0]
            if (r1 != 0) goto L_0x01a1
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r0 = r7.a((int) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r1 = r0.n
            r2 = 1
            r1 = r1 | r2
            r0.n = r1
        L_0x01a1:
            int r3 = r3 + -1
            r4 = r10
            r0 = r33
            r1 = r34
            r2 = r35
            goto L_0x0169
        L_0x01ab:
            r33 = r0
            r34 = r1
            r35 = r2
            r39 = r5
            r40 = r13
            r38 = r31
            r25 = r35
            goto L_0x02df
        L_0x01bb:
            r33 = r0
            r34 = r1
        L_0x01bf:
            r3 = 1
            goto L_0x02d5
        L_0x01c2:
            r33 = r0
            r34 = r1
            java.lang.String r0 = "LocalVariableTypeTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x01d4
            int r0 = r21 + 8
            r26 = r0
            goto L_0x02d5
        L_0x01d4:
            java.lang.String r0 = "LineNumberTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0221
            int r0 = r14.b
            r0 = r0 & 2
            if (r0 != 0) goto L_0x01bf
            int r0 = r21 + 8
            int r0 = r7.c(r0)
            r1 = r21
        L_0x01ea:
            if (r0 <= 0) goto L_0x01bf
            int r2 = r1 + 10
            int r2 = r7.c(r2)
            r3 = r5[r2]
            if (r3 != 0) goto L_0x0200
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r7.a((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r5)
            int r4 = r3.n
            r10 = 1
            r4 = r4 | r10
            r3.n = r4
        L_0x0200:
            r2 = r5[r2]
        L_0x0202:
            int r3 = r2.o
            if (r3 <= 0) goto L_0x0214
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = r2.v
            if (r3 != 0) goto L_0x0211
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r3 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Label
            r3.<init>()
            r2.v = r3
        L_0x0211:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r2 = r2.v
            goto L_0x0202
        L_0x0214:
            int r3 = r1 + 12
            int r3 = r7.c(r3)
            r2.o = r3
            int r1 = r1 + 4
            int r0 = r0 + -1
            goto L_0x01ea
        L_0x0221:
            java.lang.String r0 = "RuntimeVisibleTypeAnnotations"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0253
            int r0 = r21 + 8
            r1 = 1
            int[] r0 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor) r15, (org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14, (int) r0, (boolean) r1)
            int r2 = r0.length
            if (r2 == 0) goto L_0x0245
            r2 = 0
            r3 = r0[r2]
            int r3 = r7.b(r3)
            if (r3 >= r4) goto L_0x023d
            goto L_0x0245
        L_0x023d:
            r3 = r0[r2]
            int r3 = r3 + r1
            int r2 = r7.c(r3)
            goto L_0x0246
        L_0x0245:
            r2 = -1
        L_0x0246:
            r1 = r0
            r29 = r2
            r39 = r5
            r40 = r13
            r38 = r31
            r0 = r33
            goto L_0x02df
        L_0x0253:
            java.lang.String r0 = "RuntimeInvisibleTypeAnnotations"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0282
            int r0 = r21 + 8
            r1 = 0
            int[] r0 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor) r15, (org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14, (int) r0, (boolean) r1)
            int r2 = r0.length
            if (r2 == 0) goto L_0x0277
            r2 = r0[r1]
            int r2 = r7.b(r2)
            if (r2 >= r4) goto L_0x026e
            goto L_0x0277
        L_0x026e:
            r2 = r0[r1]
            r3 = 1
            int r2 = r2 + r3
            int r2 = r7.c(r2)
            goto L_0x0279
        L_0x0277:
            r3 = 1
            r2 = -1
        L_0x0279:
            r30 = r2
            r39 = r5
            r40 = r13
            r38 = r31
            goto L_0x02dd
        L_0x0282:
            r3 = 1
            java.lang.String r0 = "StackMapTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x02a6
            int r0 = r14.b
            r0 = r0 & 4
            if (r0 != 0) goto L_0x02d5
            int r0 = r21 + 10
            int r1 = r21 + 4
            int r1 = r7.e(r1)
            int r2 = r21 + 8
            int r2 = r7.c(r2)
            r22 = r0
            r23 = r1
            r28 = r2
            goto L_0x02d5
        L_0x02a6:
            java.lang.String r0 = "StackMap"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x02e2
            int r0 = r14.b
            r0 = r0 & 4
            if (r0 != 0) goto L_0x02d5
            int r0 = r21 + 10
            int r1 = r21 + 4
            int r1 = r7.e(r1)
            int r2 = r21 + 8
            int r2 = r7.c(r2)
            r22 = r0
            r23 = r1
            r28 = r2
            r39 = r5
            r40 = r13
            r38 = r31
            r0 = r33
            r1 = r34
            r27 = 0
            goto L_0x02df
        L_0x02d5:
            r39 = r5
            r40 = r13
            r38 = r31
            r0 = r33
        L_0x02dd:
            r1 = r34
        L_0x02df:
            r13 = r6
            goto L_0x035e
        L_0x02e2:
            r4 = r24
            r10 = 0
        L_0x02e5:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute[] r0 = r14.f3589a
            int r0 = r0.length
            if (r10 >= r0) goto L_0x034c
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute[] r0 = r14.f3589a
            r0 = r0[r10]
            java.lang.String r0 = r0.f3585a
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x032a
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute[] r0 = r14.f3589a
            r0 = r0[r10]
            int r19 = r21 + 8
            int r1 = r21 + 4
            int r24 = r7.e(r1)
            int r35 = r16 + -8
            r1 = r33
            r37 = r1
            r36 = r34
            r1 = r56
            r32 = r2
            r15 = -1
            r2 = r19
            r3 = r24
            r15 = r4
            r38 = r31
            r4 = r13
            r39 = r5
            r5 = r35
            r40 = r13
            r13 = r6
            r6 = r39
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute r0 = r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader) r1, (int) r2, (int) r3, (char[]) r4, (int) r5, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r6)
            if (r0 == 0) goto L_0x0338
            r0.c = r15
            r4 = r0
            goto L_0x0339
        L_0x032a:
            r32 = r2
            r15 = r4
            r39 = r5
            r40 = r13
            r38 = r31
            r37 = r33
            r36 = r34
            r13 = r6
        L_0x0338:
            r4 = r15
        L_0x0339:
            int r10 = r10 + 1
            r6 = r13
            r2 = r32
            r34 = r36
            r33 = r37
            r31 = r38
            r5 = r39
            r13 = r40
            r3 = 1
            r15 = r57
            goto L_0x02e5
        L_0x034c:
            r15 = r4
            r39 = r5
            r40 = r13
            r38 = r31
            r37 = r33
            r36 = r34
            r13 = r6
            r24 = r15
            r1 = r36
            r0 = r37
        L_0x035e:
            int r2 = r21 + 4
            int r2 = r7.e(r2)
            int r2 = r2 + 6
            int r21 = r21 + r2
            int r17 = r17 + -1
            r6 = r13
            r31 = r38
            r5 = r39
            r13 = r40
            r15 = r57
            goto L_0x0149
        L_0x0375:
            r37 = r0
            r36 = r1
            r39 = r5
            r40 = r13
            r38 = r31
            r13 = r6
            if (r22 == 0) goto L_0x03d1
            r6 = -1
            r14.k = r6
            r0 = 0
            r14.o = r0
            r14.p = r0
            r14.q = r0
            r14.s = r0
            java.lang.Object[] r0 = new java.lang.Object[r11]
            r14.r = r0
            java.lang.Object[] r0 = new java.lang.Object[r12]
            r14.t = r0
            r10 = r38
            if (r10 == 0) goto L_0x039d
            r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14)
        L_0x039d:
            r0 = r22
        L_0x039f:
            int r1 = r22 + r23
            int r1 = r1 + -2
            if (r0 >= r1) goto L_0x03cc
            byte r1 = r8[r0]
            r2 = 8
            if (r1 != r2) goto L_0x03c5
            int r1 = r0 + 1
            int r1 = r7.c(r1)
            if (r1 < 0) goto L_0x03c5
            if (r1 >= r9) goto L_0x03c5
            int r2 = r16 + r1
            byte r2 = r8[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3 = 187(0xbb, float:2.62E-43)
            if (r2 != r3) goto L_0x03c5
            r15 = r39
            r7.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r15)
            goto L_0x03c7
        L_0x03c5:
            r15 = r39
        L_0x03c7:
            int r0 = r0 + 1
            r39 = r15
            goto L_0x039f
        L_0x03cc:
            r15 = r39
            r17 = r14
            goto L_0x03d8
        L_0x03d1:
            r10 = r38
            r15 = r39
            r6 = -1
            r17 = 0
        L_0x03d8:
            int r0 = r14.b
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x03ef
            r1 = -1
            r3 = 0
            r5 = 0
            r18 = 0
            r0 = r57
            r2 = r11
            r6 = 67
            r4 = r5
            r5 = r18
            r0.a((int) r1, (int) r2, (java.lang.Object[]) r3, (int) r4, (java.lang.Object[]) r5)
            goto L_0x03f1
        L_0x03ef:
            r6 = 67
        L_0x03f1:
            int r0 = r14.b
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 != 0) goto L_0x03fc
            r0 = -33
            r18 = -33
            goto L_0x03fe
        L_0x03fc:
            r18 = 0
        L_0x03fe:
            r5 = r16
            r3 = r17
            r17 = 0
            r19 = 0
        L_0x0406:
            if (r5 >= r13) goto L_0x0904
            int r4 = r5 - r16
            r0 = r15[r4]
            if (r0 == 0) goto L_0x0434
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r0.v
            r2 = 0
            r0.v = r2
            r41 = r11
            r6 = r57
            r11 = -1
            r6.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
            int r2 = r14.b
            r2 = r2 & 2
            if (r2 != 0) goto L_0x0439
            int r2 = r0.o
            if (r2 <= 0) goto L_0x0439
            int r2 = r0.o
            r6.b((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
        L_0x042a:
            if (r1 == 0) goto L_0x0439
            int r2 = r1.o
            r6.b((int) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r1.v
            goto L_0x042a
        L_0x0434:
            r41 = r11
            r6 = r57
            r11 = -1
        L_0x0439:
            r2 = r22
        L_0x043b:
            if (r3 == 0) goto L_0x04c7
            int r0 = r3.k
            if (r0 == r4) goto L_0x0445
            int r0 = r3.k
            if (r0 != r11) goto L_0x04c7
        L_0x0445:
            int r0 = r3.k
            if (r0 == r11) goto L_0x0494
            r1 = r27
            if (r1 == 0) goto L_0x0479
            if (r10 == 0) goto L_0x0450
            goto L_0x0479
        L_0x0450:
            int r0 = r3.o
            int r11 = r3.q
            r42 = r4
            java.lang.Object[] r4 = r3.r
            r43 = r5
            int r5 = r3.s
            r44 = r12
            java.lang.Object[] r12 = r3.t
            r21 = r0
            r0 = r57
            r45 = r13
            r13 = r1
            r1 = r21
            r46 = r9
            r9 = r2
            r2 = r11
            r11 = r3
            r3 = r4
            r14 = r42
            r4 = r5
            r21 = r43
            r5 = r12
            r0.a((int) r1, (int) r2, (java.lang.Object[]) r3, (int) r4, (java.lang.Object[]) r5)
            goto L_0x04a1
        L_0x0479:
            r11 = r3
            r14 = r4
            r21 = r5
            r46 = r9
            r44 = r12
            r45 = r13
            r13 = r1
            r9 = r2
            r1 = -1
            int r2 = r11.p
            java.lang.Object[] r3 = r11.r
            int r4 = r11.s
            java.lang.Object[] r5 = r11.t
            r0 = r57
            r0.a((int) r1, (int) r2, (java.lang.Object[]) r3, (int) r4, (java.lang.Object[]) r5)
            goto L_0x04a1
        L_0x0494:
            r11 = r3
            r14 = r4
            r21 = r5
            r46 = r9
            r44 = r12
            r45 = r13
            r13 = r27
            r9 = r2
        L_0x04a1:
            if (r28 <= 0) goto L_0x04b9
            int r2 = r7.a((int) r9, (boolean) r13, (boolean) r10, (org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r11)
            int r28 = r28 + -1
            r3 = r11
            r27 = r13
            r4 = r14
            r5 = r21
            r12 = r44
            r13 = r45
            r9 = r46
        L_0x04b5:
            r11 = -1
            r14 = r58
            goto L_0x043b
        L_0x04b9:
            r2 = r9
            r27 = r13
            r4 = r14
            r5 = r21
            r12 = r44
            r13 = r45
            r9 = r46
            r3 = 0
            goto L_0x04b5
        L_0x04c7:
            r11 = r3
            r14 = r4
            r21 = r5
            r46 = r9
            r44 = r12
            r45 = r13
            r13 = r27
            r9 = r2
            byte r0 = r8[r21]
            r12 = r0 & 255(0xff, float:3.57E-43)
            byte[] r0 = org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter.A
            byte r0 = r0[r12]
            switch(r0) {
                case 0: goto L_0x082c;
                case 1: goto L_0x0812;
                case 2: goto L_0x07f6;
                case 3: goto L_0x07da;
                case 4: goto L_0x07ac;
                case 5: goto L_0x0790;
                case 6: goto L_0x072b;
                case 7: goto L_0x072b;
                case 8: goto L_0x06bf;
                case 9: goto L_0x0696;
                case 10: goto L_0x067d;
                case 11: goto L_0x0666;
                case 12: goto L_0x064f;
                case 13: goto L_0x0628;
                case 14: goto L_0x05ed;
                case 15: goto L_0x05a8;
                case 16: goto L_0x04df;
                case 17: goto L_0x056a;
                case 18: goto L_0x0507;
                default: goto L_0x04df;
            }
        L_0x04df:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            int r5 = r21 + 1
            java.lang.String r0 = r7.b(r5, r11)
            int r5 = r21 + 3
            byte r1 = r8[r5]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r6.b((java.lang.String) r0, (int) r1)
            int r5 = r21 + 4
        L_0x04ff:
            r0 = r17
            r1 = r29
            r13 = r36
            goto L_0x0842
        L_0x0507:
            r0 = 218(0xda, float:3.05E-43)
            if (r12 >= r0) goto L_0x050e
            int r12 = r12 + -49
            goto L_0x0510
        L_0x050e:
            int r12 = r12 + -20
        L_0x0510:
            int r5 = r21 + 1
            int r0 = r7.c(r5)
            int r4 = r14 + r0
            r0 = r15[r4]
            r1 = 167(0xa7, float:2.34E-43)
            if (r12 == r1) goto L_0x055f
            r1 = 168(0xa8, float:2.35E-43)
            if (r12 != r1) goto L_0x0523
            goto L_0x055f
        L_0x0523:
            r1 = 166(0xa6, float:2.33E-43)
            if (r12 > r1) goto L_0x052e
            int r12 = r12 + 1
            r5 = 1
            r1 = r12 ^ 1
            int r1 = r1 - r5
            goto L_0x0531
        L_0x052e:
            r5 = 1
            r1 = r12 ^ 1
        L_0x0531:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r2 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Label
            r2.<init>()
            r6.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r2)
            r1 = 200(0xc8, float:2.8E-43)
            r6.a((int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
            r6.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r2)
            if (r9 == 0) goto L_0x055b
            if (r11 == 0) goto L_0x054b
            int r0 = r11.k
            int r4 = r14 + 3
            if (r0 == r4) goto L_0x055b
        L_0x054b:
            r1 = 256(0x100, float:3.59E-43)
            r2 = 0
            r3 = 0
            r4 = 0
            r12 = 0
            r0 = r57
            r47 = r13
            r13 = 1
            r5 = r12
            r0.a((int) r1, (int) r2, (java.lang.Object[]) r3, (int) r4, (java.lang.Object[]) r5)
            goto L_0x0567
        L_0x055b:
            r47 = r13
            r13 = 1
            goto L_0x0567
        L_0x055f:
            r47 = r13
            r13 = 1
            int r12 = r12 + 33
            r6.a((int) r12, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
        L_0x0567:
            int r5 = r21 + 3
            goto L_0x0589
        L_0x056a:
            r47 = r13
            r13 = 1
            int r5 = r21 + 1
            byte r0 = r8[r5]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r5 = 132(0x84, float:1.85E-43)
            if (r0 != r5) goto L_0x059c
            int r0 = r21 + 2
            int r0 = r7.c(r0)
            int r1 = r21 + 4
            short r1 = r7.d(r1)
            r6.c(r0, r1)
            int r0 = r21 + 6
        L_0x0588:
            r5 = r0
        L_0x0589:
            r49 = r9
            r50 = r11
            r9 = r14
            r0 = r17
            r1 = r29
            r13 = r36
            r11 = r40
            r14 = r58
            r20 = 8
            goto L_0x064b
        L_0x059c:
            int r1 = r21 + 2
            int r1 = r7.c(r1)
            r6.b((int) r0, (int) r1)
            int r0 = r21 + 4
            goto L_0x0588
        L_0x05a8:
            r47 = r13
            r5 = 132(0x84, float:1.85E-43)
            r13 = 1
            int r0 = r21 + 4
            r1 = r14 & 3
            int r0 = r0 - r1
            int r1 = r7.e(r0)
            int r4 = r14 + r1
            int r1 = r0 + 4
            int r1 = r7.e(r1)
            int[] r2 = new int[r1]
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r3 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Label[r1]
            r20 = 8
            int r0 = r0 + 8
            r12 = r0
            r0 = 0
        L_0x05c8:
            if (r0 >= r1) goto L_0x05e2
            int r21 = r7.e(r12)
            r2[r0] = r21
            int r5 = r12 + 4
            int r5 = r7.e(r5)
            int r5 = r5 + r14
            r5 = r15[r5]
            r3[r0] = r5
            int r12 = r12 + 8
            int r0 = r0 + 1
            r5 = 132(0x84, float:1.85E-43)
            goto L_0x05c8
        L_0x05e2:
            r0 = r15[r4]
            r6.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0, (int[]) r2, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r3)
            r49 = r9
            r50 = r11
            r5 = r12
            goto L_0x0640
        L_0x05ed:
            r47 = r13
            r13 = 1
            r20 = 8
            int r5 = r21 + 4
            r0 = r14 & 3
            int r5 = r5 - r0
            int r0 = r7.e(r5)
            int r4 = r14 + r0
            int r0 = r5 + 4
            int r0 = r7.e(r0)
            int r1 = r5 + 8
            int r1 = r7.e(r1)
            int r2 = r1 - r0
            int r2 = r2 + r13
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r2 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Label[r2]
            int r5 = r5 + 12
            r3 = 0
        L_0x0611:
            int r12 = r2.length
            if (r3 >= r12) goto L_0x0622
            int r12 = r7.e(r5)
            int r12 = r12 + r14
            r12 = r15[r12]
            r2[r3] = r12
            int r5 = r5 + 4
            int r3 = r3 + 1
            goto L_0x0611
        L_0x0622:
            r3 = r15[r4]
            r6.a((int) r0, (int) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r3, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label[]) r2)
            goto L_0x063c
        L_0x0628:
            r47 = r13
            r13 = 1
            r20 = 8
            int r5 = r21 + 1
            byte r0 = r8[r5]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r5 = r21 + 2
            byte r1 = r8[r5]
            r6.c(r0, r1)
            int r5 = r21 + 3
        L_0x063c:
            r49 = r9
            r50 = r11
        L_0x0640:
            r9 = r14
            r0 = r17
            r1 = r29
            r13 = r36
            r11 = r40
            r14 = r58
        L_0x064b:
            r22 = 132(0x84, float:1.85E-43)
            goto L_0x0842
        L_0x064f:
            r47 = r13
            r13 = 1
            r20 = 8
            int r5 = r21 + 1
            int r0 = r7.c(r5)
            r5 = r40
            java.lang.Object r0 = r7.c(r0, r5)
            r6.a((java.lang.Object) r0)
            int r0 = r21 + 3
            goto L_0x06ac
        L_0x0666:
            r47 = r13
            r5 = r40
            r13 = 1
            r20 = 8
            int r0 = r21 + 1
            byte r0 = r8[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.Object r0 = r7.c(r0, r5)
            r6.a((java.lang.Object) r0)
            int r0 = r21 + 2
            goto L_0x06ac
        L_0x067d:
            r47 = r13
            r5 = r40
            r13 = 1
            r20 = 8
            int r12 = r12 + r18
            int r0 = r21 + 1
            int r0 = r7.e(r0)
            int r4 = r14 + r0
            r0 = r15[r4]
            r6.a((int) r12, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
            int r0 = r21 + 5
            goto L_0x06ac
        L_0x0696:
            r47 = r13
            r5 = r40
            r13 = 1
            r20 = 8
            int r0 = r21 + 1
            short r0 = r7.d(r0)
            int r4 = r14 + r0
            r0 = r15[r4]
            r6.a((int) r12, (org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
            int r0 = r21 + 3
        L_0x06ac:
            r49 = r9
            r50 = r11
            r9 = r14
            r1 = r29
            r13 = r36
            r14 = r58
            r22 = 132(0x84, float:1.85E-43)
        L_0x06b9:
            r11 = r5
            r5 = r0
            r0 = r17
            goto L_0x0842
        L_0x06bf:
            r47 = r13
            r5 = r40
            r13 = 1
            r20 = 8
            int[] r0 = r7.m
            int r1 = r21 + 1
            int r1 = r7.c(r1)
            r0 = r0[r1]
            r4 = r14
            r14 = r58
            int[] r1 = r14.d
            int r2 = r7.c(r0)
            r1 = r1[r2]
            int r2 = r7.c(r1)
            java.lang.Object r2 = r7.c(r2, r5)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handle r2 = (org.jacoco.agent.rt.internal_8ff85ea.asm.Handle) r2
            int r3 = r1 + 2
            int r3 = r7.c(r3)
            java.lang.Object[] r12 = new java.lang.Object[r3]
            int r1 = r1 + 4
            r13 = r1
            r1 = 0
        L_0x06f1:
            if (r1 >= r3) goto L_0x0706
            r48 = r3
            int r3 = r7.c(r13)
            java.lang.Object r3 = r7.c(r3, r5)
            r12[r1] = r3
            int r13 = r13 + 2
            int r1 = r1 + 1
            r3 = r48
            goto L_0x06f1
        L_0x0706:
            int[] r1 = r7.m
            int r0 = r0 + 2
            int r0 = r7.c(r0)
            r0 = r1[r0]
            java.lang.String r1 = r7.a((int) r0, (char[]) r5)
            int r0 = r0 + 2
            java.lang.String r0 = r7.a((int) r0, (char[]) r5)
            r6.a((java.lang.String) r1, (java.lang.String) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.Handle) r2, (java.lang.Object[]) r12)
            int r0 = r21 + 5
            r49 = r9
            r50 = r11
            r1 = r29
            r13 = r36
            r22 = 132(0x84, float:1.85E-43)
            r9 = r4
            goto L_0x06b9
        L_0x072b:
            r47 = r13
            r4 = r14
            r5 = r40
            r14 = r58
            r20 = 8
            int[] r0 = r7.m
            int r1 = r21 + 1
            int r1 = r7.c(r1)
            r0 = r0[r1]
            int r1 = r0 + -1
            byte r1 = r8[r1]
            r2 = 11
            if (r1 != r2) goto L_0x0748
            r13 = 1
            goto L_0x0749
        L_0x0748:
            r13 = 0
        L_0x0749:
            java.lang.String r2 = r7.b(r0, r5)
            int[] r1 = r7.m
            int r0 = r0 + 2
            int r0 = r7.c(r0)
            r0 = r1[r0]
            java.lang.String r3 = r7.a((int) r0, (char[]) r5)
            int r0 = r0 + 2
            java.lang.String r1 = r7.a((int) r0, (char[]) r5)
            r0 = 182(0xb6, float:2.55E-43)
            if (r12 >= r0) goto L_0x0771
            r6.a((int) r12, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r1)
            r49 = r9
            r50 = r11
            r22 = 132(0x84, float:1.85E-43)
            r9 = r4
            r11 = r5
            goto L_0x0784
        L_0x0771:
            r0 = r57
            r22 = r1
            r1 = r12
            r49 = r9
            r9 = r4
            r4 = r22
            r50 = r11
            r22 = 132(0x84, float:1.85E-43)
            r11 = r5
            r5 = r13
            r0.a((int) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (boolean) r5)
        L_0x0784:
            r0 = 185(0xb9, float:2.59E-43)
            if (r12 != r0) goto L_0x078c
            int r5 = r21 + 5
            goto L_0x04ff
        L_0x078c:
            int r5 = r21 + 3
            goto L_0x04ff
        L_0x0790:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            int r5 = r21 + 1
            java.lang.String r0 = r7.b(r5, r11)
            r6.a((int) r12, (java.lang.String) r0)
            int r5 = r21 + 3
            goto L_0x04ff
        L_0x07ac:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            r0 = 54
            if (r12 <= r0) goto L_0x07cb
            int r12 = r12 + -59
            int r0 = r12 >> 2
            int r0 = r0 + 54
            r1 = r12 & 3
            r6.b((int) r0, (int) r1)
            goto L_0x07d6
        L_0x07cb:
            int r12 = r12 + -26
            int r0 = r12 >> 2
            int r0 = r0 + 21
            r1 = r12 & 3
            r6.b((int) r0, (int) r1)
        L_0x07d6:
            int r5 = r21 + 1
            goto L_0x04ff
        L_0x07da:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            int r5 = r21 + 1
            byte r0 = r8[r5]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r6.b((int) r12, (int) r0)
            int r5 = r21 + 2
            goto L_0x04ff
        L_0x07f6:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            int r5 = r21 + 1
            short r0 = r7.d(r5)
            r6.a((int) r12, (int) r0)
            int r5 = r21 + 3
            goto L_0x04ff
        L_0x0812:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            int r5 = r21 + 1
            byte r0 = r8[r5]
            r6.a((int) r12, (int) r0)
            int r5 = r21 + 2
            goto L_0x04ff
        L_0x082c:
            r49 = r9
            r50 = r11
            r47 = r13
            r9 = r14
            r11 = r40
            r14 = r58
            r20 = 8
            r22 = 132(0x84, float:1.85E-43)
            r6.e_(r12)
            int r5 = r21 + 1
            goto L_0x04ff
        L_0x0842:
            if (r13 == 0) goto L_0x087f
            int r2 = r13.length
            if (r0 >= r2) goto L_0x087f
            if (r1 > r9) goto L_0x087f
            if (r1 != r9) goto L_0x0863
            r1 = r13[r0]
            int r1 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14, (int) r1)
            int r2 = r1 + 2
            int r3 = r14.i
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r4 = r14.j
            java.lang.String r1 = r7.a((int) r1, (char[]) r11)
            r12 = 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r1 = r6.b((int) r3, (org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath) r4, (java.lang.String) r1, (boolean) r12)
            r7.a((int) r2, (char[]) r11, (boolean) r12, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r1)
        L_0x0863:
            int r0 = r0 + 1
            int r1 = r13.length
            if (r0 >= r1) goto L_0x087d
            r1 = r13[r0]
            int r1 = r7.b(r1)
            r2 = 67
            if (r1 >= r2) goto L_0x0873
            goto L_0x087d
        L_0x0873:
            r1 = r13[r0]
            r2 = 1
            int r1 = r1 + r2
            int r2 = r7.c(r1)
            r1 = r2
            goto L_0x0842
        L_0x087d:
            r1 = -1
            goto L_0x0842
        L_0x087f:
            r2 = r19
            r3 = r30
            r12 = r37
        L_0x0885:
            if (r12 == 0) goto L_0x08d9
            int r4 = r12.length
            if (r2 >= r4) goto L_0x08d9
            if (r3 > r9) goto L_0x08d9
            if (r3 != r9) goto L_0x08ae
            r3 = r12[r2]
            int r3 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14, (int) r3)
            int r4 = r3 + 2
            r51 = r0
            int r0 = r14.i
            r52 = r1
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r1 = r14.j
            java.lang.String r3 = r7.a((int) r3, (char[]) r11)
            r53 = r10
            r10 = 0
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r0 = r6.b((int) r0, (org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath) r1, (java.lang.String) r3, (boolean) r10)
            r1 = 1
            r7.a((int) r4, (char[]) r11, (boolean) r1, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r0)
            goto L_0x08b5
        L_0x08ae:
            r51 = r0
            r52 = r1
            r53 = r10
            r10 = 0
        L_0x08b5:
            int r2 = r2 + 1
            int r0 = r12.length
            if (r2 >= r0) goto L_0x08cf
            r0 = r12[r2]
            int r0 = r7.b(r0)
            r1 = 67
            if (r0 >= r1) goto L_0x08c5
            goto L_0x08d1
        L_0x08c5:
            r0 = r12[r2]
            r3 = 1
            int r0 = r0 + r3
            int r0 = r7.c(r0)
            r3 = r0
            goto L_0x08d2
        L_0x08cf:
            r1 = 67
        L_0x08d1:
            r3 = -1
        L_0x08d2:
            r0 = r51
            r1 = r52
            r10 = r53
            goto L_0x0885
        L_0x08d9:
            r51 = r0
            r52 = r1
            r53 = r10
            r1 = 67
            r10 = 0
            r19 = r2
            r30 = r3
            r40 = r11
            r37 = r12
            r36 = r13
            r11 = r41
            r12 = r44
            r13 = r45
            r9 = r46
            r27 = r47
            r22 = r49
            r3 = r50
            r17 = r51
            r29 = r52
            r10 = r53
            r6 = 67
            goto L_0x0406
        L_0x0904:
            r46 = r9
            r41 = r11
            r44 = r12
            r13 = r36
            r12 = r37
            r11 = r40
            r6 = r57
            r10 = 0
            r0 = r15[r46]
            if (r0 == 0) goto L_0x091c
            r0 = r15[r46]
            r6.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r0)
        L_0x091c:
            int r0 = r14.b
            r0 = r0 & 2
            if (r0 != 0) goto L_0x09b7
            r0 = r25
            if (r0 == 0) goto L_0x09b7
            r1 = r26
            if (r1 == 0) goto L_0x0955
            int r26 = r1 + 2
            int r1 = r7.c(r1)
            int r1 = r1 * 3
            int[] r3 = new int[r1]
            int r1 = r3.length
            r2 = r26
        L_0x0937:
            if (r1 <= 0) goto L_0x0953
            int r1 = r1 + -1
            int r4 = r2 + 6
            r3[r1] = r4
            r4 = -1
            int r1 = r1 + r4
            int r5 = r2 + 8
            int r5 = r7.c(r5)
            r3[r1] = r5
            int r1 = r1 + r4
            int r5 = r7.c(r2)
            r3[r1] = r5
            int r2 = r2 + 10
            goto L_0x0937
        L_0x0953:
            r8 = r3
            goto L_0x0956
        L_0x0955:
            r8 = 0
        L_0x0956:
            int r25 = r0 + 2
            int r0 = r7.c(r0)
            r9 = r0
            r5 = r25
        L_0x095f:
            if (r9 <= 0) goto L_0x09b7
            int r0 = r7.c(r5)
            int r1 = r5 + 2
            int r1 = r7.c(r1)
            int r2 = r5 + 8
            int r4 = r7.c(r2)
            if (r8 == 0) goto L_0x098e
            r2 = 0
        L_0x0974:
            int r3 = r8.length
            if (r2 >= r3) goto L_0x098e
            r3 = r8[r2]
            if (r3 != r0) goto L_0x098b
            int r3 = r2 + 1
            r3 = r8[r3]
            if (r3 != r4) goto L_0x098b
            int r2 = r2 + 2
            r2 = r8[r2]
            java.lang.String r2 = r7.a((int) r2, (char[]) r11)
            r3 = r2
            goto L_0x098f
        L_0x098b:
            int r2 = r2 + 3
            goto L_0x0974
        L_0x098e:
            r3 = 0
        L_0x098f:
            int r2 = r5 + 4
            java.lang.String r2 = r7.a((int) r2, (char[]) r11)
            int r10 = r5 + 6
            java.lang.String r10 = r7.a((int) r10, (char[]) r11)
            r16 = r15[r0]
            int r0 = r0 + r1
            r17 = r15[r0]
            r0 = r57
            r1 = r2
            r2 = r10
            r10 = r4
            r4 = r16
            r25 = r5
            r5 = r17
            r6 = r10
            r0.a(r1, r2, r3, r4, r5, r6)
            int r5 = r25 + 10
            int r9 = r9 + -1
            r6 = r57
            r10 = 0
            goto L_0x095f
        L_0x09b7:
            if (r13 == 0) goto L_0x0a1c
            r0 = 0
        L_0x09ba:
            int r1 = r13.length
            if (r0 >= r1) goto L_0x0a1c
            r1 = r13[r0]
            int r1 = r7.b(r1)
            r2 = 1
            int r1 = r1 >> r2
            r3 = 32
            if (r1 != r3) goto L_0x0a00
            r1 = r13[r0]
            int r1 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r14, (int) r1)
            int r3 = r1 + 2
            int r9 = r14.i
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r10 = r14.j
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r4 = r14.l
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r5 = r14.m
            int[] r6 = r14.n
            java.lang.String r1 = r7.a((int) r1, (char[]) r11)
            r15 = 1
            r8 = r57
            r16 = 0
            r17 = r11
            r2 = r41
            r11 = r4
            r54 = r12
            r4 = r44
            r12 = r5
            r5 = r17
            r17 = r13
            r13 = r6
            r6 = r14
            r14 = r1
            r1 = r57
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r8 = r8.a(r9, r10, r11, r12, r13, r14, r15)
            r15 = 1
            r7.a((int) r3, (char[]) r5, (boolean) r15, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r8)
            goto L_0x0a0f
        L_0x0a00:
            r1 = r57
            r5 = r11
            r54 = r12
            r17 = r13
            r6 = r14
            r2 = r41
            r4 = r44
            r15 = 1
            r16 = 0
        L_0x0a0f:
            int r0 = r0 + 1
            r41 = r2
            r44 = r4
            r11 = r5
            r14 = r6
            r13 = r17
            r12 = r54
            goto L_0x09ba
        L_0x0a1c:
            r1 = r57
            r5 = r11
            r6 = r14
            r2 = r41
            r4 = r44
            r15 = 1
            r16 = 0
            r0 = r12
            if (r0 == 0) goto L_0x0a6e
            r3 = 0
        L_0x0a2b:
            int r8 = r0.length
            if (r3 >= r8) goto L_0x0a6e
            r8 = r0[r3]
            int r8 = r7.b(r8)
            int r8 = r8 >> r15
            r9 = 32
            if (r8 != r9) goto L_0x0a63
            r8 = r0[r3]
            int r8 = r7.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Context) r6, (int) r8)
            int r14 = r8 + 2
            int r9 = r6.i
            org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath r10 = r6.j
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r11 = r6.l
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label[] r12 = r6.m
            int[] r13 = r6.n
            java.lang.String r16 = r7.a((int) r8, (char[]) r5)
            r17 = 0
            r8 = r57
            r55 = r0
            r0 = r14
            r14 = r16
            r6 = 1
            r15 = r17
            org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor r8 = r8.a(r9, r10, r11, r12, r13, r14, r15)
            r7.a((int) r0, (char[]) r5, (boolean) r6, (org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor) r8)
            goto L_0x0a66
        L_0x0a63:
            r55 = r0
            r6 = 1
        L_0x0a66:
            int r3 = r3 + 1
            r0 = r55
            r6 = r58
            r15 = 1
            goto L_0x0a2b
        L_0x0a6e:
            r0 = r24
        L_0x0a70:
            if (r0 == 0) goto L_0x0a7c
            org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute r3 = r0.c
            r5 = 0
            r0.c = r5
            r1.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute) r0)
            r0 = r3
            goto L_0x0a70
        L_0x0a7c:
            r1.d(r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader.a(org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor, org.jacoco.agent.rt.internal_8ff85ea.asm.Context, int):void");
    }

    private int[] a(MethodVisitor methodVisitor, Context context, int i2, boolean z) {
        int i3;
        char[] cArr = context.c;
        int[] iArr = new int[c(i2)];
        int i4 = i2 + 2;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            iArr[i5] = i4;
            int e2 = e(i4);
            int i6 = e2 >>> 24;
            switch (i6) {
                case 0:
                case 1:
                    i3 = i4 + 2;
                    break;
                default:
                    switch (i6) {
                        case 19:
                        case 20:
                        case 21:
                            i3 = i4 + 1;
                            break;
                        case 22:
                            break;
                        default:
                            switch (i6) {
                                case 64:
                                case 65:
                                    for (int c2 = c(i4 + 1); c2 > 0; c2--) {
                                        int c3 = c(i4 + 3);
                                        int c4 = c(i4 + 5);
                                        a(c3, context.h);
                                        a(c3 + c4, context.h);
                                        i4 += 6;
                                    }
                                    i3 = i4 + 3;
                                    break;
                                default:
                                    switch (i6) {
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                            i3 = i4 + 4;
                                            break;
                                        default:
                                            i3 = i4 + 3;
                                            break;
                                    }
                            }
                    }
                    i3 = i4 + 2;
                    break;
            }
            int b2 = b(i3);
            TypePath typePath = null;
            if (i6 == 66) {
                if (b2 != 0) {
                    typePath = new TypePath(this.k, i3);
                }
                int i7 = i3 + (b2 * 2) + 1;
                i4 = a(i7 + 2, cArr, true, methodVisitor.c(e2, typePath, a(i7, cArr), z));
            } else {
                i4 = a(i3 + 3 + (b2 * 2), cArr, true, (AnnotationVisitor) null);
            }
        }
        return iArr;
    }

    private int a(Context context, int i2) {
        int i3;
        int i4;
        TypePath typePath;
        int e2 = e(i2);
        int i5 = e2 >>> 24;
        switch (i5) {
            case 0:
            case 1:
                i4 = e2 & -65536;
                i3 = i2 + 2;
                break;
            default:
                int i6 = -16777216;
                switch (i5) {
                    case 19:
                    case 20:
                    case 21:
                        i4 = e2 & -16777216;
                        i3 = i2 + 1;
                        break;
                    case 22:
                        break;
                    default:
                        switch (i5) {
                            case 64:
                            case 65:
                                i4 = e2 & -16777216;
                                int c2 = c(i2 + 1);
                                context.l = new Label[c2];
                                context.m = new Label[c2];
                                context.n = new int[c2];
                                i3 = i2 + 3;
                                for (int i7 = 0; i7 < c2; i7++) {
                                    int c3 = c(i3);
                                    int c4 = c(i3 + 2);
                                    context.l[i7] = a(c3, context.h);
                                    context.m[i7] = a(c3 + c4, context.h);
                                    context.n[i7] = c(i3 + 4);
                                    i3 += 6;
                                }
                                break;
                            default:
                                switch (i5) {
                                    case 71:
                                    case 72:
                                    case 73:
                                    case 74:
                                    case 75:
                                        i4 = e2 & Color.h;
                                        i3 = i2 + 4;
                                        break;
                                    default:
                                        if (i5 < 67) {
                                            i6 = -256;
                                        }
                                        i4 = e2 & i6;
                                        i3 = i2 + 3;
                                        break;
                                }
                        }
                }
                i4 = e2 & -65536;
                i3 = i2 + 2;
                break;
        }
        int b2 = b(i3);
        context.i = i4;
        if (b2 == 0) {
            typePath = null;
        } else {
            typePath = new TypePath(this.k, i3);
        }
        context.j = typePath;
        return i3 + 1 + (b2 * 2);
    }

    private void b(MethodVisitor methodVisitor, Context context, int i2, boolean z) {
        int i3 = i2 + 1;
        byte b2 = this.k[i2] & 255;
        int length = Type.d(context.g).length - b2;
        int i4 = 0;
        while (i4 < length) {
            AnnotationVisitor a2 = methodVisitor.a(i4, "Ljava/lang/Synthetic;", false);
            if (a2 != null) {
                a2.a();
            }
            i4++;
        }
        char[] cArr = context.c;
        while (i4 < b2 + length) {
            i3 += 2;
            for (int c2 = c(i3); c2 > 0; c2--) {
                i3 = a(i3 + 2, cArr, true, methodVisitor.a(i4, a(i3, cArr), z));
            }
            i4++;
        }
    }

    private int a(int i2, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int c2 = c(i2);
        int i3 = i2 + 2;
        if (z) {
            while (c2 > 0) {
                i3 = a(i3 + 2, cArr, a(i3, cArr), annotationVisitor);
                c2--;
            }
        } else {
            while (c2 > 0) {
                i3 = a(i3, cArr, (String) null, annotationVisitor);
                c2--;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.a();
        }
        return i3;
    }

    private int a(int i2, char[] cArr, String str, AnnotationVisitor annotationVisitor) {
        int i3 = 0;
        if (annotationVisitor == null) {
            byte b2 = this.k[i2] & 255;
            if (b2 == 64) {
                return a(i2 + 3, cArr, true, (AnnotationVisitor) null);
            }
            if (b2 != 91) {
                return b2 != 101 ? i2 + 3 : i2 + 5;
            }
            return a(i2 + 1, cArr, false, (AnnotationVisitor) null);
        }
        int i4 = i2 + 1;
        switch (this.k[i2] & 255) {
            case 64:
                return a(i4 + 2, cArr, true, annotationVisitor.a(str, a(i4, cArr)));
            case 66:
                annotationVisitor.a(str, (Object) Byte.valueOf((byte) e(this.m[c(i4)])));
                return i4 + 2;
            case 67:
                annotationVisitor.a(str, (Object) Character.valueOf((char) e(this.m[c(i4)])));
                return i4 + 2;
            case 68:
            case 70:
            case 73:
            case 74:
                annotationVisitor.a(str, c(c(i4), cArr));
                return i4 + 2;
            case 83:
                annotationVisitor.a(str, (Object) Short.valueOf((short) e(this.m[c(i4)])));
                return i4 + 2;
            case 90:
                annotationVisitor.a(str, (Object) e(this.m[c(i4)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                return i4 + 2;
            case 91:
                int c2 = c(i4);
                int i5 = i4 + 2;
                if (c2 == 0) {
                    return a(i5 - 2, cArr, false, annotationVisitor.a(str));
                }
                int i6 = i5 + 1;
                byte b3 = this.k[i5] & 255;
                if (b3 == 70) {
                    float[] fArr = new float[c2];
                    while (i3 < c2) {
                        fArr[i3] = Float.intBitsToFloat(e(this.m[c(i6)]));
                        i6 += 3;
                        i3++;
                    }
                    annotationVisitor.a(str, (Object) fArr);
                    return i6 - 1;
                } else if (b3 == 83) {
                    short[] sArr = new short[c2];
                    while (i3 < c2) {
                        sArr[i3] = (short) e(this.m[c(i6)]);
                        i6 += 3;
                        i3++;
                    }
                    annotationVisitor.a(str, (Object) sArr);
                    return i6 - 1;
                } else if (b3 != 90) {
                    switch (b3) {
                        case 66:
                            byte[] bArr = new byte[c2];
                            while (i3 < c2) {
                                bArr[i3] = (byte) e(this.m[c(i6)]);
                                i6 += 3;
                                i3++;
                            }
                            annotationVisitor.a(str, (Object) bArr);
                            return i6 - 1;
                        case 67:
                            char[] cArr2 = new char[c2];
                            while (i3 < c2) {
                                cArr2[i3] = (char) e(this.m[c(i6)]);
                                i6 += 3;
                                i3++;
                            }
                            annotationVisitor.a(str, (Object) cArr2);
                            return i6 - 1;
                        case 68:
                            double[] dArr = new double[c2];
                            while (i3 < c2) {
                                dArr[i3] = Double.longBitsToDouble(f(this.m[c(i6)]));
                                i6 += 3;
                                i3++;
                            }
                            annotationVisitor.a(str, (Object) dArr);
                            return i6 - 1;
                        default:
                            switch (b3) {
                                case 73:
                                    int[] iArr = new int[c2];
                                    while (i3 < c2) {
                                        iArr[i3] = e(this.m[c(i6)]);
                                        i6 += 3;
                                        i3++;
                                    }
                                    annotationVisitor.a(str, (Object) iArr);
                                    return i6 - 1;
                                case 74:
                                    long[] jArr = new long[c2];
                                    while (i3 < c2) {
                                        jArr[i3] = f(this.m[c(i6)]);
                                        i6 += 3;
                                        i3++;
                                    }
                                    annotationVisitor.a(str, (Object) jArr);
                                    return i6 - 1;
                                default:
                                    return a(i6 - 3, cArr, false, annotationVisitor.a(str));
                            }
                    }
                } else {
                    boolean[] zArr = new boolean[c2];
                    for (int i7 = 0; i7 < c2; i7++) {
                        zArr[i7] = e(this.m[c(i6)]) != 0;
                        i6 += 3;
                    }
                    annotationVisitor.a(str, (Object) zArr);
                    return i6 - 1;
                }
            case 99:
                annotationVisitor.a(str, (Object) Type.a(a(i4, cArr)));
                return i4 + 2;
            case 101:
                annotationVisitor.a(str, a(i4, cArr), a(i4 + 2, cArr));
                return i4 + 4;
            case 115:
                annotationVisitor.a(str, (Object) a(i4, cArr));
                return i4 + 2;
            default:
                return i4;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        if (r0.charAt(r5) != 'L') goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        if (r0.charAt(r5) == ';') goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        r5 = r5 + 1;
        r1[r4] = r0.substring(r2, r5);
        r2 = r5;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x002a, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.jacoco.agent.rt.internal_8ff85ea.asm.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.g
            java.lang.Object[] r1 = r10.r
            int r2 = r10.e
            r2 = r2 & 8
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L_0x0029
            java.lang.String r2 = "<init>"
            java.lang.String r5 = r10.f
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x001c
            java.lang.Integer r2 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ah
            r1[r4] = r2
        L_0x001a:
            r4 = 1
            goto L_0x0029
        L_0x001c:
            int r2 = r9.l
            int r2 = r2 + 2
            char[] r5 = r10.c
            java.lang.String r2 = r9.b(r2, r5)
            r1[r4] = r2
            goto L_0x001a
        L_0x0029:
            r2 = 1
        L_0x002a:
            int r5 = r2 + 1
            char r6 = r0.charAt(r2)
            r7 = 59
            switch(r6) {
                case 66: goto L_0x008d;
                case 67: goto L_0x008d;
                case 68: goto L_0x0086;
                case 70: goto L_0x007f;
                case 73: goto L_0x008d;
                case 74: goto L_0x0078;
                case 76: goto L_0x0061;
                case 83: goto L_0x008d;
                case 90: goto L_0x008d;
                case 91: goto L_0x0037;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0096
        L_0x0037:
            char r6 = r0.charAt(r5)
            r8 = 91
            if (r6 != r8) goto L_0x0042
            int r5 = r5 + 1
            goto L_0x0037
        L_0x0042:
            char r6 = r0.charAt(r5)
            r8 = 76
            if (r6 != r8) goto L_0x0055
            int r5 = r5 + 1
        L_0x004c:
            char r6 = r0.charAt(r5)
            if (r6 == r7) goto L_0x0055
            int r5 = r5 + 1
            goto L_0x004c
        L_0x0055:
            int r6 = r4 + 1
            int r5 = r5 + r3
            java.lang.String r2 = r0.substring(r2, r5)
            r1[r4] = r2
            r2 = r5
            r4 = r6
            goto L_0x002a
        L_0x0061:
            r2 = r5
        L_0x0062:
            char r6 = r0.charAt(r2)
            if (r6 == r7) goto L_0x006b
            int r2 = r2 + 1
            goto L_0x0062
        L_0x006b:
            int r6 = r4 + 1
            int r7 = r2 + 1
            java.lang.String r2 = r0.substring(r5, r2)
            r1[r4] = r2
            r4 = r6
            r2 = r7
            goto L_0x002a
        L_0x0078:
            int r2 = r4 + 1
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r1[r4] = r6
            goto L_0x0093
        L_0x007f:
            int r2 = r4 + 1
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ad
            r1[r4] = r6
            goto L_0x0093
        L_0x0086:
            int r2 = r4 + 1
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            r1[r4] = r6
            goto L_0x0093
        L_0x008d:
            int r2 = r4 + 1
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r1[r4] = r6
        L_0x0093:
            r4 = r2
            r2 = r5
            goto L_0x002a
        L_0x0096:
            r10.p = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader.a(org.jacoco.agent.rt.internal_8ff85ea.asm.Context):void");
    }

    private int a(int i2, boolean z, boolean z2, Context context) {
        int i3;
        int i4;
        int i5;
        char[] cArr = context.c;
        Label[] labelArr = context.h;
        if (z) {
            int i6 = i2 + 1;
            i3 = this.k[i2] & 255;
            i4 = i6;
        } else {
            context.k = -1;
            i4 = i2;
            i3 = 255;
        }
        context.q = 0;
        if (i3 < 64) {
            context.o = 3;
            context.s = 0;
        } else if (i3 < 128) {
            i3 -= 64;
            i4 = a(context.t, 0, i4, cArr, labelArr);
            context.o = 4;
            context.s = 1;
        } else {
            i5 = c(i4);
            i4 += 2;
            if (i3 == 247) {
                i4 = a(context.t, 0, i4, cArr, labelArr);
                context.o = 4;
                context.s = 1;
            } else if (i3 >= 248 && i3 < 251) {
                context.o = 2;
                context.q = 251 - i3;
                context.p -= context.q;
                context.s = 0;
            } else if (i3 == 251) {
                context.o = 3;
                context.s = 0;
            } else if (i3 < 255) {
                int i7 = i3 - 251;
                int i8 = z2 ? context.p : 0;
                int i9 = i7;
                while (i9 > 0) {
                    i4 = a(context.r, i8, i4, cArr, labelArr);
                    i9--;
                    i8++;
                }
                context.o = 1;
                context.q = i7;
                context.p += context.q;
                context.s = 0;
            } else {
                context.o = 0;
                int c2 = c(i4);
                int i10 = i4 + 2;
                context.q = c2;
                context.p = c2;
                int i11 = 0;
                while (c2 > 0) {
                    i10 = a(context.r, i11, i10, cArr, labelArr);
                    c2--;
                    i11++;
                }
                int c3 = c(i10);
                int i12 = i10 + 2;
                context.s = c3;
                int i13 = 0;
                while (c3 > 0) {
                    i12 = a(context.t, i13, i4, cArr, labelArr);
                    c3--;
                    i13++;
                }
            }
            context.k += i5 + 1;
            a(context.k, labelArr);
            return i4;
        }
        i5 = i3;
        context.k += i5 + 1;
        a(context.k, labelArr);
        return i4;
    }

    private int a(Object[] objArr, int i2, int i3, char[] cArr, Label[] labelArr) {
        int i4 = i3 + 1;
        switch (this.k[i3] & 255) {
            case 0:
                objArr[i2] = Opcodes.ab;
                return i4;
            case 1:
                objArr[i2] = Opcodes.ac;
                return i4;
            case 2:
                objArr[i2] = Opcodes.ad;
                return i4;
            case 3:
                objArr[i2] = Opcodes.ae;
                return i4;
            case 4:
                objArr[i2] = Opcodes.af;
                return i4;
            case 5:
                objArr[i2] = Opcodes.ag;
                return i4;
            case 6:
                objArr[i2] = Opcodes.ah;
                return i4;
            case 7:
                objArr[i2] = b(i4, cArr);
                return i4 + 2;
            default:
                objArr[i2] = a(c(i4), labelArr);
                return i4 + 2;
        }
    }

    /* access modifiers changed from: protected */
    public Label a(int i2, Label[] labelArr) {
        if (labelArr[i2] == null) {
            labelArr[i2] = new Label();
        }
        return labelArr[i2];
    }

    private int g() {
        int c2 = this.l + 8 + (c(this.l + 6) * 2);
        for (int c3 = c(c2); c3 > 0; c3--) {
            for (int c4 = c(c2 + 8); c4 > 0; c4--) {
                c2 += e(c2 + 12) + 6;
            }
            c2 += 8;
        }
        int i2 = c2 + 2;
        for (int c5 = c(i2); c5 > 0; c5--) {
            for (int c6 = c(i2 + 8); c6 > 0; c6--) {
                i2 += e(i2 + 12) + 6;
            }
            i2 += 8;
        }
        return i2 + 2;
    }

    private Attribute a(Attribute[] attributeArr, String str, int i2, int i3, char[] cArr, int i4, Label[] labelArr) {
        Attribute[] attributeArr2 = attributeArr;
        String str2 = str;
        for (int i5 = 0; i5 < attributeArr2.length; i5++) {
            if (attributeArr2[i5].f3585a.equals(str)) {
                return attributeArr2[i5].a(this, i2, i3, cArr, i4, labelArr);
            }
        }
        return new Attribute(str).a(this, i2, i3, (char[]) null, -1, (Label[]) null);
    }

    public int e() {
        return this.m.length;
    }

    public int a(int i2) {
        return this.m[i2];
    }

    public int f() {
        return this.o;
    }

    public int b(int i2) {
        return this.k[i2] & 255;
    }

    public int c(int i2) {
        byte[] bArr = this.k;
        return (bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8);
    }

    public short d(int i2) {
        byte[] bArr = this.k;
        return (short) ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8));
    }

    public int e(int i2) {
        byte[] bArr = this.k;
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    public long f(int i2) {
        return (((long) e(i2)) << 32) | (((long) e(i2 + 4)) & MessageHead.SERIAL_MAK);
    }

    public String a(int i2, char[] cArr) {
        int c2 = c(i2);
        if (i2 == 0 || c2 == 0) {
            return null;
        }
        String str = this.n[c2];
        if (str != null) {
            return str;
        }
        int i3 = this.m[c2];
        String[] strArr = this.n;
        String a2 = a(i3 + 2, c(i3), cArr);
        strArr[c2] = a2;
        return a2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(int r9, int r10, char[] r11) {
        /*
            r8 = this;
            int r10 = r10 + r9
            byte[] r0 = r8.k
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0007:
            if (r9 >= r10) goto L_0x0048
            int r5 = r9 + 1
            byte r9 = r0[r9]
            r6 = 1
            switch(r2) {
                case 0: goto L_0x0028;
                case 1: goto L_0x001b;
                case 2: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0046
        L_0x0012:
            int r2 = r4 << 6
            r9 = r9 & 63
            r9 = r9 | r2
            char r9 = (char) r9
        L_0x0018:
            r4 = r9
            r2 = 1
            goto L_0x0046
        L_0x001b:
            int r2 = r3 + 1
            int r6 = r4 << 6
            r9 = r9 & 63
            r9 = r9 | r6
            char r9 = (char) r9
            r11[r3] = r9
            r3 = r2
            r2 = 0
            goto L_0x0046
        L_0x0028:
            r9 = r9 & 255(0xff, float:3.57E-43)
            r7 = 128(0x80, float:1.794E-43)
            if (r9 >= r7) goto L_0x0035
            int r6 = r3 + 1
            char r9 = (char) r9
            r11[r3] = r9
            r3 = r6
            goto L_0x0046
        L_0x0035:
            r2 = 224(0xe0, float:3.14E-43)
            if (r9 >= r2) goto L_0x0041
            r2 = 191(0xbf, float:2.68E-43)
            if (r9 <= r2) goto L_0x0041
            r9 = r9 & 31
            char r9 = (char) r9
            goto L_0x0018
        L_0x0041:
            r9 = r9 & 15
            char r9 = (char) r9
            r2 = 2
            r4 = r9
        L_0x0046:
            r9 = r5
            goto L_0x0007
        L_0x0048:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r11, r1, r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader.a(int, int, char[]):java.lang.String");
    }

    public String b(int i2, char[] cArr) {
        return a(this.m[c(i2)], cArr);
    }

    public Object c(int i2, char[] cArr) {
        int i3 = this.m[i2];
        byte b2 = this.k[i3 - 1];
        if (b2 == 16) {
            return Type.c(a(i3, cArr));
        }
        switch (b2) {
            case 3:
                return Integer.valueOf(e(i3));
            case 4:
                return Float.valueOf(Float.intBitsToFloat(e(i3)));
            case 5:
                return Long.valueOf(f(i3));
            case 6:
                return Double.valueOf(Double.longBitsToDouble(f(i3)));
            case 7:
                return Type.b(a(i3, cArr));
            case 8:
                return a(i3, cArr);
            default:
                int b3 = b(i3);
                int[] iArr = this.m;
                int i4 = iArr[c(i3 + 1)];
                boolean z = this.k[i4 + -1] == 11;
                String b4 = b(i4, cArr);
                int i5 = iArr[c(i4 + 2)];
                return new Handle(b3, b4, a(i5, cArr), a(i5 + 2, cArr), z);
        }
    }
}
