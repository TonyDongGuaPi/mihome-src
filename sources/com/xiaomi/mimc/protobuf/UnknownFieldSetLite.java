package com.xiaomi.mimc.protobuf;

import java.io.IOException;
import java.util.Arrays;

public final class UnknownFieldSetLite {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11349a = 8;
    private static final UnknownFieldSetLite b = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    private int c;
    private int[] d;
    private Object[] e;
    private int f;
    private boolean g;

    public static UnknownFieldSetLite a() {
        return b;
    }

    static UnknownFieldSetLite b() {
        return new UnknownFieldSetLite();
    }

    static UnknownFieldSetLite a(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i = unknownFieldSetLite.c + unknownFieldSetLite2.c;
        int[] copyOf = Arrays.copyOf(unknownFieldSetLite.d, i);
        System.arraycopy(unknownFieldSetLite2.d, 0, copyOf, unknownFieldSetLite.c, unknownFieldSetLite2.c);
        Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.e, i);
        System.arraycopy(unknownFieldSetLite2.e, 0, copyOf2, unknownFieldSetLite.c, unknownFieldSetLite2.c);
        return new UnknownFieldSetLite(i, copyOf, copyOf2, true);
    }

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private UnknownFieldSetLite(int i, int[] iArr, Object[] objArr, boolean z) {
        this.f = -1;
        this.c = i;
        this.d = iArr;
        this.e = objArr;
        this.g = z;
    }

    public void c() {
        this.g = false;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        if (!this.g) {
            throw new UnsupportedOperationException();
        }
    }

    public void a(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.c; i++) {
            int i2 = this.d[i];
            int b2 = WireFormat.b(i2);
            int a2 = WireFormat.a(i2);
            if (a2 != 5) {
                switch (a2) {
                    case 0:
                        codedOutputStream.b(b2, ((Long) this.e[i]).longValue());
                        break;
                    case 1:
                        codedOutputStream.d(b2, ((Long) this.e[i]).longValue());
                        break;
                    case 2:
                        codedOutputStream.a(b2, (ByteString) this.e[i]);
                        break;
                    case 3:
                        codedOutputStream.a(b2, 3);
                        ((UnknownFieldSetLite) this.e[i]).a(codedOutputStream);
                        codedOutputStream.a(b2, 4);
                        break;
                    default:
                        throw InvalidProtocolBufferException.invalidWireType();
                }
            } else {
                codedOutputStream.e(b2, ((Integer) this.e[i]).intValue());
            }
        }
    }

    public int e() {
        int i;
        int i2 = this.f;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.c; i4++) {
            int i5 = this.d[i4];
            int b2 = WireFormat.b(i5);
            int a2 = WireFormat.a(i5);
            if (a2 != 5) {
                switch (a2) {
                    case 0:
                        i = CodedOutputStream.g(b2, ((Long) this.e[i4]).longValue());
                        break;
                    case 1:
                        i = CodedOutputStream.i(b2, ((Long) this.e[i4]).longValue());
                        break;
                    case 2:
                        i = CodedOutputStream.c(b2, (ByteString) this.e[i4]);
                        break;
                    case 3:
                        i = (CodedOutputStream.i(b2) * 2) + ((UnknownFieldSetLite) this.e[i4]).e();
                        break;
                    default:
                        throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
            } else {
                i = CodedOutputStream.k(b2, ((Integer) this.e[i4]).intValue());
            }
            i3 += i;
        }
        this.f = i3;
        return i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        return this.c == unknownFieldSetLite.c && Arrays.equals(this.d, unknownFieldSetLite.d) && Arrays.deepEquals(this.e, unknownFieldSetLite.e);
    }

    public int hashCode() {
        return ((((527 + this.c) * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.deepHashCode(this.e);
    }

    /* access modifiers changed from: package-private */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.c; i2++) {
            MessageLiteToString.a(sb, i, String.valueOf(WireFormat.b(this.d[i2])), this.e[i2]);
        }
    }

    private void a(int i, Object obj) {
        f();
        this.d[this.c] = i;
        this.e[this.c] = obj;
        this.c++;
    }

    private void f() {
        if (this.c == this.d.length) {
            int i = this.c + (this.c < 4 ? 8 : this.c >> 1);
            this.d = Arrays.copyOf(this.d, i);
            this.e = Arrays.copyOf(this.e, i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i, CodedInputStream codedInputStream) throws IOException {
        d();
        int b2 = WireFormat.b(i);
        switch (WireFormat.a(i)) {
            case 0:
                a(i, (Object) Long.valueOf(codedInputStream.g()));
                return true;
            case 1:
                a(i, (Object) Long.valueOf(codedInputStream.i()));
                return true;
            case 2:
                a(i, (Object) codedInputStream.n());
                return true;
            case 3:
                UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
                unknownFieldSetLite.a(codedInputStream);
                codedInputStream.a(WireFormat.a(b2, 4));
                a(i, (Object) unknownFieldSetLite);
                return true;
            case 4:
                return false;
            case 5:
                a(i, (Object) Integer.valueOf(codedInputStream.j()));
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    /* access modifiers changed from: package-private */
    public UnknownFieldSetLite a(int i, int i2) {
        d();
        if (i != 0) {
            a(WireFormat.a(i, 0), (Object) Long.valueOf((long) i2));
            return this;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    /* access modifiers changed from: package-private */
    public UnknownFieldSetLite a(int i, ByteString byteString) {
        d();
        if (i != 0) {
            a(WireFormat.a(i, 2), (Object) byteString);
            return this;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private com.xiaomi.mimc.protobuf.UnknownFieldSetLite a(com.xiaomi.mimc.protobuf.CodedInputStream r2) throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r2.a()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.a((int) r0, (com.xiaomi.mimc.protobuf.CodedInputStream) r2)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.UnknownFieldSetLite.a(com.xiaomi.mimc.protobuf.CodedInputStream):com.xiaomi.mimc.protobuf.UnknownFieldSetLite");
    }
}
