package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto;

public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {

    /* renamed from: a  reason: collision with root package name */
    private final BlockCipher f14450a;

    /* access modifiers changed from: protected */
    public abstract byte b(byte b);

    protected StreamBlockCipher(BlockCipher blockCipher) {
        this.f14450a = blockCipher;
    }

    public BlockCipher e() {
        return this.f14450a;
    }

    public final byte a(byte b) {
        return b(b);
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (i3 + i2 <= bArr2.length) {
            int i4 = i + i2;
            if (i4 <= bArr.length) {
                while (i < i4) {
                    bArr2[i3] = b(bArr[i]);
                    i3++;
                    i++;
                }
                return i2;
            }
            throw new DataLengthException("input buffer too small");
        }
        throw new DataLengthException("output buffer too short");
    }
}
