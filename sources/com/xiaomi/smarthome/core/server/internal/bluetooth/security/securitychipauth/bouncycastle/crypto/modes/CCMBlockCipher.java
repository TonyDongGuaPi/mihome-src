package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.DataLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.InvalidCipherTextException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.OutputLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.macs.CBCBlockCipherMac;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.AEADParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.ParametersWithIV;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.ByteArrayOutputStream;

public class CCMBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    private BlockCipher f14454a;
    private int b;
    private boolean c;
    private byte[] d;
    private byte[] e;
    private int f;
    private CipherParameters g;
    private byte[] h;
    private ExposedByteArrayOutputStream i = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream j = new ExposedByteArrayOutputStream();

    public int a(int i2) {
        return 0;
    }

    public CCMBlockCipher(BlockCipher blockCipher) {
        this.f14454a = blockCipher;
        this.b = blockCipher.b();
        this.h = new byte[this.b];
        if (this.b != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    public BlockCipher b() {
        return this.f14454a;
    }

    public void a(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        this.c = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.d = aEADParameters.d();
            this.e = aEADParameters.c();
            this.f = aEADParameters.b() / 8;
            cipherParameters2 = aEADParameters.a();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.d = parametersWithIV.a();
            this.e = null;
            this.f = this.h.length / 2;
            cipherParameters2 = parametersWithIV.b();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to CCM: " + cipherParameters.getClass().getName());
        }
        if (cipherParameters2 != null) {
            this.g = cipherParameters2;
        }
        if (this.d == null || this.d.length < 7 || this.d.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        d();
    }

    public String a() {
        return this.f14454a.a() + "/CCM";
    }

    public void a(byte b2) {
        this.i.write(b2);
    }

    public void a(byte[] bArr, int i2, int i3) {
        this.i.write(bArr, i2, i3);
    }

    public int a(byte b2, byte[] bArr, int i2) throws DataLengthException, IllegalStateException {
        this.j.write(b2);
        return 0;
    }

    public int a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException, IllegalStateException {
        if (bArr.length >= i2 + i3) {
            this.j.write(bArr, i2, i3);
            return 0;
        }
        throw new DataLengthException("Input buffer too short");
    }

    public int a(byte[] bArr, int i2) throws IllegalStateException, InvalidCipherTextException {
        int b2 = b(this.j.a(), 0, this.j.size(), bArr, i2);
        d();
        return b2;
    }

    public void d() {
        this.f14454a.c();
        this.i.reset();
        this.j.reset();
    }

    public byte[] c() {
        byte[] bArr = new byte[this.f];
        System.arraycopy(this.h, 0, bArr, 0, bArr.length);
        return bArr;
    }

    public int b(int i2) {
        int size = i2 + this.j.size();
        if (this.c) {
            return size + this.f;
        }
        if (size < this.f) {
            return 0;
        }
        return size - this.f;
    }

    public byte[] b(byte[] bArr, int i2, int i3) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2;
        if (this.c) {
            bArr2 = new byte[(this.f + i3)];
        } else if (i3 >= this.f) {
            bArr2 = new byte[(i3 - this.f)];
        } else {
            throw new InvalidCipherTextException("data too short");
        }
        b(bArr, i2, i3, bArr2, 0);
        return bArr2;
    }

    public int b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int i5;
        int i6;
        if (this.g != null) {
            int length = 15 - this.d.length;
            if (length >= 4 || i3 < (1 << (length * 8))) {
                byte[] bArr3 = new byte[this.b];
                bArr3[0] = (byte) ((length - 1) & 7);
                System.arraycopy(this.d, 0, bArr3, 1, this.d.length);
                SICBlockCipher sICBlockCipher = new SICBlockCipher(this.f14454a);
                sICBlockCipher.a(this.c, new ParametersWithIV(this.g, bArr3));
                if (this.c) {
                    i5 = this.f + i3;
                    if (bArr2.length >= i5 + i4) {
                        a(bArr, i2, i3, this.h);
                        byte[] bArr4 = new byte[this.b];
                        sICBlockCipher.a(this.h, 0, bArr4, 0);
                        int i7 = i2;
                        int i8 = i4;
                        while (true) {
                            i6 = i2 + i3;
                            if (i7 >= i6 - this.b) {
                                break;
                            }
                            sICBlockCipher.a(bArr, i7, bArr2, i8);
                            i8 += this.b;
                            i7 += this.b;
                        }
                        byte[] bArr5 = new byte[this.b];
                        int i9 = i6 - i7;
                        System.arraycopy(bArr, i7, bArr5, 0, i9);
                        sICBlockCipher.a(bArr5, 0, bArr5, 0);
                        System.arraycopy(bArr5, 0, bArr2, i8, i9);
                        System.arraycopy(bArr4, 0, bArr2, i4 + i3, this.f);
                    } else {
                        throw new OutputLengthException("Output buffer too short.");
                    }
                } else if (i3 >= this.f) {
                    i5 = i3 - this.f;
                    if (bArr2.length >= i5 + i4) {
                        int i10 = i2 + i5;
                        System.arraycopy(bArr, i10, this.h, 0, this.f);
                        sICBlockCipher.a(this.h, 0, this.h, 0);
                        for (int i11 = this.f; i11 != this.h.length; i11++) {
                            this.h[i11] = 0;
                        }
                        int i12 = i2;
                        int i13 = i4;
                        while (i12 < i10 - this.b) {
                            sICBlockCipher.a(bArr, i12, bArr2, i13);
                            i13 += this.b;
                            i12 += this.b;
                        }
                        byte[] bArr6 = new byte[this.b];
                        int i14 = i5 - (i12 - i2);
                        System.arraycopy(bArr, i12, bArr6, 0, i14);
                        sICBlockCipher.a(bArr6, 0, bArr6, 0);
                        System.arraycopy(bArr6, 0, bArr2, i13, i14);
                        byte[] bArr7 = new byte[this.b];
                        a(bArr2, i4, i5, bArr7);
                        if (!Arrays.b(this.h, bArr7)) {
                            throw new InvalidCipherTextException("mac check in CCM failed");
                        }
                    } else {
                        throw new OutputLengthException("Output buffer too short.");
                    }
                } else {
                    throw new InvalidCipherTextException("data too short");
                }
                return i5;
            }
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        throw new IllegalStateException("CCM cipher unitialized.");
    }

    private int a(byte[] bArr, int i2, int i3, byte[] bArr2) {
        CBCBlockCipherMac cBCBlockCipherMac = new CBCBlockCipherMac(this.f14454a, this.f * 8);
        cBCBlockCipherMac.a(this.g);
        byte[] bArr3 = new byte[16];
        if (f()) {
            bArr3[0] = (byte) (bArr3[0] | 64);
        }
        int i4 = 2;
        bArr3[0] = (byte) (bArr3[0] | ((((cBCBlockCipherMac.b() - 2) / 2) & 7) << 3));
        bArr3[0] = (byte) (bArr3[0] | (((15 - this.d.length) - 1) & 7));
        System.arraycopy(this.d, 0, bArr3, 1, this.d.length);
        int i5 = i3;
        int i6 = 1;
        while (i5 > 0) {
            bArr3[bArr3.length - i6] = (byte) (i5 & 255);
            i5 >>>= 8;
            i6++;
        }
        cBCBlockCipherMac.a(bArr3, 0, bArr3.length);
        if (f()) {
            int e2 = e();
            if (e2 < 65280) {
                cBCBlockCipherMac.a((byte) (e2 >> 8));
                cBCBlockCipherMac.a((byte) e2);
            } else {
                cBCBlockCipherMac.a((byte) -1);
                cBCBlockCipherMac.a((byte) -2);
                cBCBlockCipherMac.a((byte) (e2 >> 24));
                cBCBlockCipherMac.a((byte) (e2 >> 16));
                cBCBlockCipherMac.a((byte) (e2 >> 8));
                cBCBlockCipherMac.a((byte) e2);
                i4 = 6;
            }
            if (this.e != null) {
                cBCBlockCipherMac.a(this.e, 0, this.e.length);
            }
            if (this.i.size() > 0) {
                cBCBlockCipherMac.a(this.i.a(), 0, this.i.size());
            }
            int i7 = (i4 + e2) % 16;
            if (i7 != 0) {
                while (i7 != 16) {
                    cBCBlockCipherMac.a((byte) 0);
                    i7++;
                }
            }
        }
        cBCBlockCipherMac.a(bArr, i2, i3);
        return cBCBlockCipherMac.a(bArr2, 0);
    }

    private int e() {
        return this.i.size() + (this.e == null ? 0 : this.e.length);
    }

    private boolean f() {
        return e() > 0;
    }

    private class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] a() {
            return this.buf;
        }
    }
}
