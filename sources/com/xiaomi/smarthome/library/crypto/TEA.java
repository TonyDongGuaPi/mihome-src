package com.xiaomi.smarthome.library.crypto;

import com.payu.custombrowser.util.CBConstant;

public class TEA {

    /* renamed from: a  reason: collision with root package name */
    public static String f19090a = "Now rise, and show your strength. Be eloquent, and deep, and tender; see, with a clear eye, into Nature, and into life:  spread your white wings of quivering thought, and soar, a god-like spirit, over the whirling world beneath you, up through long lanes of flaming stars to the gates of eternity!";
    static final /* synthetic */ boolean b = (!TEA.class.desiredAssertionStatus());
    private static final int c = -1640531527;
    private static final int d = 32;
    private static final int e = -957401312;
    private int[] f = new int[4];

    public TEA(byte[] bArr) {
        if (bArr == null) {
            throw new RuntimeException("Invalid key: Key was null");
        } else if (bArr.length >= 16) {
            int i = 0;
            int i2 = 0;
            while (i < 4) {
                int[] iArr = this.f;
                int i3 = i2 + 1;
                int i4 = i3 + 1;
                byte b2 = (bArr[i2] & 255) | ((bArr[i3] & 255) << 8);
                int i5 = i4 + 1;
                byte b3 = b2 | ((bArr[i4] & 255) << 16);
                iArr[i] = b3 | ((bArr[i5] & 255) << 24);
                i++;
                i2 = i5 + 1;
            }
        } else {
            throw new RuntimeException("Invalid key: Length was less than 16 bytes");
        }
    }

    public static void a(String[] strArr) {
        TEA tea = new TEA("And is there honey still for tea?".getBytes());
        if (!new String(tea.b(tea.a(f19090a.getBytes()))).equals(f19090a)) {
            throw new RuntimeException(CBConstant.STR_SNOOZE_MODE_FAIL);
        }
    }

    public byte[] a(byte[] bArr) {
        int[] iArr = new int[((((bArr.length / 8) + (bArr.length % 8 == 0 ? 0 : 1)) * 2) + 1)];
        iArr[0] = bArr.length;
        a(bArr, iArr, 1);
        a(iArr);
        return a(iArr, 0, iArr.length * 4);
    }

    public byte[] b(byte[] bArr) {
        if (!b && bArr.length % 4 != 0) {
            throw new AssertionError();
        } else if (b || (bArr.length / 4) % 2 == 1) {
            int[] iArr = new int[(bArr.length / 4)];
            a(bArr, iArr, 0);
            b(iArr);
            return a(iArr, 1, iArr[0]);
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr) {
        if (b || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = i + 1;
                int i5 = iArr[i4];
                int i6 = i3;
                int i7 = 0;
                while (true) {
                    int i8 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    i7 -= 1640531527;
                    i6 += (((i5 << 4) + this.f[0]) ^ i5) + ((i5 >>> 5) ^ i7) + this.f[1];
                    i5 += (((i6 << 4) + this.f[2]) ^ i6) + ((i6 >>> 5) ^ i7) + this.f[3];
                    i2 = i8;
                }
                iArr[i] = i6;
                iArr[i4] = i5;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public void b(int[] iArr) {
        if (b || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = i + 1;
                int i5 = iArr[i4];
                int i6 = e;
                while (true) {
                    int i7 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    i5 -= ((((i3 << 4) + this.f[2]) ^ i3) + ((i3 >>> 5) ^ i6)) + this.f[3];
                    i3 -= ((((i5 << 4) + this.f[0]) ^ i5) + ((i5 >>> 5) ^ i6)) + this.f[1];
                    i6 += 1640531527;
                    i2 = i7;
                }
                iArr[i] = i3;
                iArr[i4] = i5;
            }
            return;
        }
        throw new AssertionError();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(byte[] r7, int[] r8, int r9) {
        /*
            r6 = this;
            boolean r0 = b
            if (r0 != 0) goto L_0x0012
            int r0 = r7.length
            int r0 = r0 / 4
            int r0 = r0 + r9
            int r1 = r8.length
            if (r0 > r1) goto L_0x000c
            goto L_0x0012
        L_0x000c:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            r7.<init>()
            throw r7
        L_0x0012:
            r0 = 0
            r8[r9] = r0
            r1 = 24
            r2 = r9
            r9 = 0
            r3 = 24
        L_0x001b:
            int r4 = r7.length
            if (r9 >= r4) goto L_0x0039
            r4 = r8[r2]
            byte r5 = r7[r9]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << r3
            r4 = r4 | r5
            r8[r2] = r4
            if (r3 != 0) goto L_0x0034
            int r2 = r2 + 1
            int r3 = r8.length
            if (r2 >= r3) goto L_0x0031
            r8[r2] = r0
        L_0x0031:
            r3 = 24
            goto L_0x0036
        L_0x0034:
            int r3 = r3 + -8
        L_0x0036:
            int r9 = r9 + 1
            goto L_0x001b
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.crypto.TEA.a(byte[], int[], int):void");
    }

    /* access modifiers changed from: package-private */
    public byte[] a(int[] iArr, int i, int i2) {
        if (b || i2 <= (iArr.length - i) * 4) {
            byte[] bArr = new byte[i2];
            int i3 = i;
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                bArr[i5] = (byte) ((iArr[i3] >> (24 - (i4 * 8))) & 255);
                i4++;
                if (i4 == 4) {
                    i3++;
                    i4 = 0;
                }
            }
            return bArr;
        }
        throw new AssertionError();
    }
}
