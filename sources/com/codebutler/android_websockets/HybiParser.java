package com.codebutler.android_websockets;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class HybiParser {
    private static final int A = 2;
    private static final int B = 8;
    private static final int C = 9;
    private static final int D = 10;
    private static final List<Integer> E = Arrays.asList(new Integer[]{0, 1, 2, 8, 9, 10});
    private static final List<Integer> F = Arrays.asList(new Integer[]{0, 1, 2});

    /* renamed from: a  reason: collision with root package name */
    private static final String f5147a = "HybiParser";
    private static final int o = 255;
    private static final int p = 128;
    private static final int q = 128;
    private static final int r = 64;
    private static final int s = 32;
    private static final int t = 16;
    private static final int u = 15;
    private static final int v = 127;
    private static final int w = 1;
    private static final int x = 2;
    private static final int y = 0;
    private static final int z = 1;
    private WebSocketClient b;
    private boolean c = true;
    private int d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private int j;
    private byte[] k = new byte[0];
    private byte[] l = new byte[0];
    private boolean m = false;
    private ByteArrayOutputStream n = new ByteArrayOutputStream();

    public HybiParser(WebSocketClient webSocketClient) {
        this.b = webSocketClient;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, int i2) {
        if (bArr2.length == 0) {
            return bArr;
        }
        for (int i3 = 0; i3 < bArr.length - i2; i3++) {
            int i4 = i2 + i3;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i3 % 4]);
        }
        return bArr;
    }

    public void a(HappyDataInputStream happyDataInputStream) throws IOException {
        while (happyDataInputStream.available() != -1) {
            switch (this.d) {
                case 0:
                    a(happyDataInputStream.readByte());
                    break;
                case 1:
                    b(happyDataInputStream.readByte());
                    break;
                case 2:
                    b(happyDataInputStream.a(this.h));
                    break;
                case 3:
                    this.k = happyDataInputStream.a(4);
                    this.d = 4;
                    break;
                case 4:
                    this.l = happyDataInputStream.a(this.i);
                    a();
                    this.d = 0;
                    break;
            }
        }
        this.b.a().a(0, "EOF");
    }

    private void a(byte b2) throws ProtocolError {
        boolean z2 = (b2 & 64) == 64;
        boolean z3 = (b2 & 32) == 32;
        boolean z4 = (b2 & 16) == 16;
        if (z2 || z3 || z4) {
            throw new ProtocolError("RSV not zero");
        }
        this.e = (b2 & 128) == 128;
        this.g = b2 & 15;
        this.k = new byte[0];
        this.l = new byte[0];
        if (!E.contains(Integer.valueOf(this.g))) {
            throw new ProtocolError("Bad opcode");
        } else if (F.contains(Integer.valueOf(this.g)) || this.e) {
            this.d = 1;
        } else {
            throw new ProtocolError("Expected non-final packet");
        }
    }

    private void b(byte b2) {
        this.f = (b2 & 128) == 128;
        this.i = b2 & Byte.MAX_VALUE;
        if (this.i < 0 || this.i > 125) {
            this.h = this.i == 126 ? 2 : 8;
            this.d = 2;
            return;
        }
        this.d = this.f ? 3 : 4;
    }

    private void b(byte[] bArr) throws ProtocolError {
        this.i = d(bArr);
        this.d = this.f ? 3 : 4;
    }

    public byte[] a(String str) {
        return a(str, 1, -1);
    }

    public byte[] a(byte[] bArr) {
        return a(bArr, 2, -1);
    }

    private byte[] a(byte[] bArr, int i2, int i3) {
        return a((Object) bArr, i2, i3);
    }

    private byte[] a(String str, int i2, int i3) {
        return a((Object) str, i2, i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0168  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] a(java.lang.Object r19, int r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            boolean r4 = r0.m
            if (r4 == 0) goto L_0x000e
            r1 = 0
            return r1
        L_0x000e:
            java.lang.String r4 = "HybiParser"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Creating frame for: "
            r5.append(r6)
            r5.append(r1)
            java.lang.String r6 = " op: "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r6 = " err: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r4, r5)
            boolean r4 = r1 instanceof java.lang.String
            if (r4 == 0) goto L_0x003f
            java.lang.String r1 = (java.lang.String) r1
            byte[] r1 = r0.c((java.lang.String) r1)
            goto L_0x0041
        L_0x003f:
            byte[] r1 = (byte[]) r1
        L_0x0041:
            r4 = 2
            r5 = 0
            if (r3 <= 0) goto L_0x0047
            r6 = 2
            goto L_0x0048
        L_0x0047:
            r6 = 0
        L_0x0048:
            int r7 = r1.length
            int r7 = r7 + r6
            r8 = 65535(0xffff, float:9.1834E-41)
            r9 = 125(0x7d, float:1.75E-43)
            if (r7 > r9) goto L_0x0053
            r11 = 2
            goto L_0x0059
        L_0x0053:
            if (r7 > r8) goto L_0x0057
            r11 = 4
            goto L_0x0059
        L_0x0057:
            r11 = 10
        L_0x0059:
            boolean r12 = r0.c
            if (r12 == 0) goto L_0x005f
            r12 = 4
            goto L_0x0060
        L_0x005f:
            r12 = 0
        L_0x0060:
            int r12 = r12 + r11
            boolean r13 = r0.c
            if (r13 == 0) goto L_0x0068
            r13 = 128(0x80, float:1.794E-43)
            goto L_0x0069
        L_0x0068:
            r13 = 0
        L_0x0069:
            int r14 = r7 + r12
            byte[] r14 = new byte[r14]
            byte r2 = (byte) r2
            r2 = r2 | -128(0xffffffffffffff80, float:NaN)
            byte r2 = (byte) r2
            r14[r5] = r2
            r2 = 3
            r15 = 1
            if (r7 > r9) goto L_0x0082
            r7 = r7 | r13
            byte r7 = (byte) r7
            r14[r15] = r7
        L_0x007b:
            r2 = r3
            r16 = r6
            r17 = r11
            goto L_0x0147
        L_0x0082:
            if (r7 > r8) goto L_0x009a
            r8 = r13 | 126(0x7e, float:1.77E-43)
            byte r8 = (byte) r8
            r14[r15] = r8
            int r8 = r7 / 256
            double r8 = (double) r8
            double r8 = java.lang.Math.floor(r8)
            int r8 = (int) r8
            byte r8 = (byte) r8
            r14[r4] = r8
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r14[r2] = r7
            goto L_0x007b
        L_0x009a:
            r8 = r13 | 127(0x7f, float:1.78E-43)
            byte r8 = (byte) r8
            r14[r15] = r8
            double r8 = (double) r7
            r16 = r6
            r5 = 4633078116657397760(0x404c000000000000, double:56.0)
            r17 = r11
            r10 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r5 = r8 / r5
            double r5 = java.lang.Math.floor(r5)
            int r5 = (int) r5
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r14[r4] = r5
            r5 = 4631952216750555136(0x4048000000000000, double:48.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r5 = r8 / r5
            double r5 = java.lang.Math.floor(r5)
            int r5 = (int) r5
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r14[r2] = r5
            r5 = 4630826316843712512(0x4044000000000000, double:40.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r5 = r8 / r5
            double r5 = java.lang.Math.floor(r5)
            int r5 = (int) r5
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5
            r6 = 4
            r14[r6] = r5
            r5 = 5
            r2 = 4629700416936869888(0x4040000000000000, double:32.0)
            double r2 = java.lang.Math.pow(r10, r2)
            java.lang.Double.isNaN(r8)
            double r2 = r8 / r2
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r14[r5] = r2
            r2 = 6
            r5 = 4627448617123184640(0x4038000000000000, double:24.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r5 = r8 / r5
            double r5 = java.lang.Math.floor(r5)
            int r3 = (int) r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r14[r2] = r3
            r2 = 7
            r5 = 4625196817309499392(0x4030000000000000, double:16.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r5 = r8 / r5
            double r5 = java.lang.Math.floor(r5)
            int r3 = (int) r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r14[r2] = r3
            r2 = 8
            r5 = 4620693217682128896(0x4020000000000000, double:8.0)
            double r5 = java.lang.Math.pow(r10, r5)
            java.lang.Double.isNaN(r8)
            double r8 = r8 / r5
            double r5 = java.lang.Math.floor(r8)
            int r3 = (int) r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r14[r2] = r3
            r2 = 9
            r3 = r7 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r14[r2] = r3
            r2 = r21
        L_0x0147:
            if (r2 <= 0) goto L_0x015d
            int r3 = r2 / 256
            double r5 = (double) r3
            double r5 = java.lang.Math.floor(r5)
            int r3 = (int) r5
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r14[r12] = r3
            int r3 = r12 + 1
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r14[r3] = r2
        L_0x015d:
            int r6 = r12 + r16
            int r2 = r1.length
            r3 = 0
            java.lang.System.arraycopy(r1, r3, r14, r6, r2)
            boolean r1 = r0.c
            if (r1 == 0) goto L_0x01b0
            r1 = 4
            byte[] r1 = new byte[r1]
            double r5 = java.lang.Math.random()
            r7 = 4643211215818981376(0x4070000000000000, double:256.0)
            double r5 = r5 * r7
            double r5 = java.lang.Math.floor(r5)
            int r2 = (int) r5
            byte r2 = (byte) r2
            r1[r3] = r2
            double r2 = java.lang.Math.random()
            double r2 = r2 * r7
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            byte r2 = (byte) r2
            r1[r15] = r2
            double r2 = java.lang.Math.random()
            double r2 = r2 * r7
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            byte r2 = (byte) r2
            r1[r4] = r2
            double r2 = java.lang.Math.random()
            double r2 = r2 * r7
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            byte r2 = (byte) r2
            r3 = 3
            r1[r3] = r2
            int r2 = r1.length
            r4 = r17
            r3 = 0
            java.lang.System.arraycopy(r1, r3, r14, r4, r2)
            a((byte[]) r14, (byte[]) r1, (int) r12)
        L_0x01b0:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.codebutler.android_websockets.HybiParser.a(java.lang.Object, int, int):byte[]");
    }

    public void b(String str) {
        this.b.a(a(str, 9, -1));
    }

    public void a(int i2, String str) {
        if (!this.m) {
            this.b.a(a(str, 8, i2));
            this.m = true;
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r1v12, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v10, types: [int, byte] */
    /* JADX WARNING: type inference failed for: r2v12, types: [byte] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() throws java.io.IOException {
        /*
            r6 = this;
            byte[] r0 = r6.l
            byte[] r1 = r6.k
            r2 = 0
            byte[] r0 = a((byte[]) r0, (byte[]) r1, (int) r2)
            int r1 = r6.g
            r3 = 1
            if (r1 != 0) goto L_0x0049
            int r1 = r6.j
            if (r1 == 0) goto L_0x0041
            java.io.ByteArrayOutputStream r1 = r6.n
            r1.write(r0)
            boolean r0 = r6.e
            if (r0 == 0) goto L_0x010f
            java.io.ByteArrayOutputStream r0 = r6.n
            byte[] r0 = r0.toByteArray()
            int r1 = r6.j
            if (r1 != r3) goto L_0x0033
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.a()
            java.lang.String r0 = r6.c((byte[]) r0)
            r1.a((java.lang.String) r0)
            goto L_0x003c
        L_0x0033:
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.a()
            r1.a((byte[]) r0)
        L_0x003c:
            r6.b()
            goto L_0x010f
        L_0x0041:
            com.codebutler.android_websockets.HybiParser$ProtocolError r0 = new com.codebutler.android_websockets.HybiParser$ProtocolError
            java.lang.String r1 = "Mode was not set."
            r0.<init>(r1)
            throw r0
        L_0x0049:
            if (r1 != r3) goto L_0x0067
            boolean r1 = r6.e
            if (r1 == 0) goto L_0x005e
            java.lang.String r0 = r6.c((byte[]) r0)
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.a()
            r1.a((java.lang.String) r0)
            goto L_0x010f
        L_0x005e:
            r6.j = r3
            java.io.ByteArrayOutputStream r1 = r6.n
            r1.write(r0)
            goto L_0x010f
        L_0x0067:
            r4 = 2
            if (r1 != r4) goto L_0x0082
            boolean r1 = r6.e
            if (r1 == 0) goto L_0x0079
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.a()
            r1.a((byte[]) r0)
            goto L_0x010f
        L_0x0079:
            r6.j = r4
            java.io.ByteArrayOutputStream r1 = r6.n
            r1.write(r0)
            goto L_0x010f
        L_0x0082:
            r5 = 8
            if (r1 != r5) goto L_0x00ce
            int r1 = r0.length
            if (r1 < r4) goto L_0x0099
            byte r1 = r0[r2]
            int r1 = r1 * 256
            byte r2 = r0[r3]
            if (r2 <= 0) goto L_0x0094
            byte r2 = r0[r3]
            goto L_0x0098
        L_0x0094:
            byte r2 = r0[r3]
            int r2 = r2 + 256
        L_0x0098:
            int r2 = r2 + r1
        L_0x0099:
            int r1 = r0.length
            if (r1 <= r4) goto L_0x00a5
            byte[] r0 = r6.a((byte[]) r0, (int) r4)
            java.lang.String r0 = r6.c((byte[]) r0)
            goto L_0x00a6
        L_0x00a5:
            r0 = 0
        L_0x00a6:
            java.lang.String r1 = "HybiParser"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Got close op! "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = " "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r1, r3)
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            com.codebutler.android_websockets.WebSocketClient$Listener r1 = r1.a()
            r1.a(r2, r0)
            goto L_0x010f
        L_0x00ce:
            r2 = 9
            r3 = 10
            if (r1 != r2) goto L_0x00f3
            int r1 = r0.length
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 > r2) goto L_0x00eb
            java.lang.String r1 = "HybiParser"
            java.lang.String r2 = "Sending pong!!"
            android.util.Log.d(r1, r2)
            com.codebutler.android_websockets.WebSocketClient r1 = r6.b
            r2 = -1
            byte[] r0 = r6.a((byte[]) r0, (int) r3, (int) r2)
            r1.b((byte[]) r0)
            goto L_0x010f
        L_0x00eb:
            com.codebutler.android_websockets.HybiParser$ProtocolError r0 = new com.codebutler.android_websockets.HybiParser$ProtocolError
            java.lang.String r1 = "Ping payload too large"
            r0.<init>(r1)
            throw r0
        L_0x00f3:
            if (r1 != r3) goto L_0x010f
            java.lang.String r0 = r6.c((byte[]) r0)
            java.lang.String r1 = "HybiParser"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Got pong! "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.d(r1, r0)
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.codebutler.android_websockets.HybiParser.a():void");
    }

    private void b() {
        this.j = 0;
        this.n.reset();
    }

    private String c(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    private byte[] c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    private int d(byte[] bArr) throws ProtocolError {
        long c2 = c(bArr, 0, bArr.length);
        if (c2 >= 0 && c2 <= 2147483647L) {
            return (int) c2;
        }
        throw new ProtocolError("Bad integer: " + c2);
    }

    private static byte[] b(byte[] bArr, int i2, int i3) {
        if (i2 <= i3) {
            int length = bArr.length;
            if (i2 < 0 || i2 > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = i3 - i2;
            int min = Math.min(i4, length - i2);
            byte[] bArr2 = new byte[i4];
            System.arraycopy(bArr, i2, bArr2, 0, min);
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    private byte[] a(byte[] bArr, int i2) {
        return b(bArr, i2, bArr.length);
    }

    public static class ProtocolError extends IOException {
        public ProtocolError(String str) {
            super(str);
        }
    }

    private static long c(byte[] bArr, int i2, int i3) {
        if (bArr.length >= i3) {
            long j2 = 0;
            for (int i4 = 0; i4 < i3; i4++) {
                j2 += (long) ((bArr[i4 + i2] & 255) << (((i3 - 1) - i4) * 8));
            }
            return j2;
        }
        throw new IllegalArgumentException("length must be less than or equal to b.length");
    }

    public static class HappyDataInputStream extends DataInputStream {
        public HappyDataInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public byte[] a(int i) throws IOException {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == i) {
                return bArr;
            }
            throw new IOException(String.format("Read wrong number of bytes. Got: %s, Expected: %s.", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
