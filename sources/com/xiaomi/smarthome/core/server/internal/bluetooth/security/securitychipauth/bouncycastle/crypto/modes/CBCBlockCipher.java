package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.DataLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.ParametersWithIV;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;

public class CBCBlockCipher implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14453a;
    private byte[] b;
    private byte[] c;
    private int d;
    private BlockCipher e = null;
    private boolean f;

    public CBCBlockCipher(BlockCipher blockCipher) {
        this.e = blockCipher;
        this.d = blockCipher.b();
        this.f14453a = new byte[this.d];
        this.b = new byte[this.d];
        this.c = new byte[this.d];
    }

    public BlockCipher d() {
        return this.e;
    }

    public void a(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        boolean z2 = this.f;
        this.f = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] a2 = parametersWithIV.a();
            if (a2.length == this.d) {
                System.arraycopy(a2, 0, this.f14453a, 0, a2.length);
                c();
                if (parametersWithIV.b() != null) {
                    this.e.a(z, parametersWithIV.b());
                } else if (z2 != z) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
            } else {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
        } else {
            c();
            if (cipherParameters != null) {
                this.e.a(z, cipherParameters);
            } else if (z2 != z) {
                throw new IllegalArgumentException("cannot change encrypting state without providing key.");
            }
        }
    }

    public String a() {
        return this.e.a() + "/CBC";
    }

    public int b() {
        return this.e.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.f ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public void c() {
        System.arraycopy(this.f14453a, 0, this.b, 0, this.f14453a.length);
        Arrays.a(this.c, (byte) 0);
        this.e.c();
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.d + i <= bArr.length) {
            for (int i3 = 0; i3 < this.d; i3++) {
                byte[] bArr3 = this.b;
                bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i + i3]);
            }
            int a2 = this.e.a(this.b, 0, bArr2, i2);
            System.arraycopy(bArr2, i2, this.b, 0, this.b.length);
            return a2;
        }
        throw new DataLengthException("input buffer too short");
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.d + i <= bArr.length) {
            System.arraycopy(bArr, i, this.c, 0, this.d);
            int a2 = this.e.a(bArr, i, bArr2, i2);
            for (int i3 = 0; i3 < this.d; i3++) {
                int i4 = i2 + i3;
                bArr2[i4] = (byte) (bArr2[i4] ^ this.b[i3]);
            }
            byte[] bArr3 = this.b;
            this.b = this.c;
            this.c = bArr3;
            return a2;
        }
        throw new DataLengthException("input buffer too short");
    }
}
