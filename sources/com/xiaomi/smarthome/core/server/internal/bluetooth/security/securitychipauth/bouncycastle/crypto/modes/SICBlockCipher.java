package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.DataLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.SkippingStreamCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.StreamBlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.ParametersWithIV;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Pack;

public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {

    /* renamed from: a  reason: collision with root package name */
    private final BlockCipher f14456a;
    private final int b = this.f14456a.b();
    private byte[] c = new byte[this.b];
    private byte[] d = new byte[this.b];
    private byte[] e = new byte[this.b];
    private int f = 0;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.f14456a = blockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.c = Arrays.b(parametersWithIV.a());
            if (this.b >= this.c.length) {
                int i = 8;
                if (8 > this.b / 2) {
                    i = this.b / 2;
                }
                if (this.b - this.c.length <= i) {
                    if (parametersWithIV.b() != null) {
                        this.f14456a.a(true, parametersWithIV.b());
                    }
                    c();
                    return;
                }
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.b - i) + " bytes.");
            }
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.b + " bytes.");
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    public String a() {
        return this.f14456a.a() + "/SIC";
    }

    public int b() {
        return this.f14456a.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        a(bArr, i, this.b, bArr2, i2);
        return this.b;
    }

    /* access modifiers changed from: protected */
    public byte b(byte b2) throws DataLengthException, IllegalStateException {
        if (this.f == 0) {
            this.f14456a.a(this.d, 0, this.e, 0);
            byte[] bArr = this.e;
            int i = this.f;
            this.f = i + 1;
            return (byte) (b2 ^ bArr[i]);
        }
        byte[] bArr2 = this.e;
        int i2 = this.f;
        this.f = i2 + 1;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        if (this.f == this.d.length) {
            this.f = 0;
            a(0);
            f();
        }
        return b3;
    }

    private void f() {
        if (this.c.length < this.b) {
            int i = 0;
            while (i != this.c.length) {
                if (this.d[i] == this.c[i]) {
                    i++;
                } else {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
            }
        }
    }

    private void a(int i) {
        byte b2;
        int length = this.d.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.d;
                b2 = (byte) (bArr[length] + 1);
                bArr[length] = b2;
            } else {
                return;
            }
        } while (b2 == 0);
    }

    private void b(int i) {
        byte b2 = this.d[this.d.length - 1];
        byte[] bArr = this.d;
        int length = this.d.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b2 != 0 && this.d[this.d.length - 1] < b2) {
            a(1);
        }
    }

    private void c(int i) {
        byte b2;
        int length = this.d.length - i;
        do {
            length--;
            if (length >= 0) {
                byte[] bArr = this.d;
                b2 = (byte) (bArr[length] - 1);
                bArr[length] = b2;
            } else {
                return;
            }
        } while (b2 == -1);
    }

    private void c(long j) {
        long j2;
        long j3;
        int i = 5;
        if (j >= 0) {
            long j4 = (((long) this.f) + j) / ((long) this.b);
            if (j4 > 255) {
                j3 = j4;
                while (i >= 1) {
                    long j5 = 1 << (i * 8);
                    while (j3 >= j5) {
                        a(i);
                        j3 -= j5;
                    }
                    i--;
                }
            } else {
                j3 = j4;
            }
            b((int) j3);
            this.f = (int) ((j + ((long) this.f)) - (((long) this.b) * j4));
            return;
        }
        long j6 = ((-j) - ((long) this.f)) / ((long) this.b);
        if (j6 > 255) {
            j2 = j6;
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j2 > j7) {
                    c(i);
                    j2 -= j7;
                }
                i--;
            }
        } else {
            j2 = j6;
        }
        for (long j8 = 0; j8 != j2; j8++) {
            c(0);
        }
        int i2 = (int) (((long) this.f) + j + (((long) this.b) * j6));
        if (i2 >= 0) {
            this.f = 0;
            return;
        }
        c(0);
        this.f = this.b + i2;
    }

    public void c() {
        Arrays.a(this.d, (byte) 0);
        System.arraycopy(this.c, 0, this.d, 0, this.c.length);
        this.f14456a.c();
        this.f = 0;
    }

    public long a(long j) {
        c(j);
        f();
        this.f14456a.a(this.d, 0, this.e, 0);
        return j;
    }

    public long b(long j) {
        c();
        return a(j);
    }

    public long d() {
        int i;
        byte[] bArr = new byte[this.d.length];
        System.arraycopy(this.d, 0, bArr, 0, bArr.length);
        for (int length = bArr.length - 1; length >= 1; length--) {
            if (length < this.c.length) {
                i = (bArr[length] & 255) - (this.c[length] & 255);
            } else {
                i = bArr[length] & 255;
            }
            if (i < 0) {
                int i2 = length - 1;
                bArr[i2] = (byte) (bArr[i2] - 1);
                i += 256;
            }
            bArr[length] = (byte) i;
        }
        return (Pack.c(bArr, bArr.length - 8) * ((long) this.b)) + ((long) this.f);
    }
}
