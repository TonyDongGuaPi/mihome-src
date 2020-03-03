package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.macs;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.Mac;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes.CBCBlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.paddings.BlockCipherPadding;

public class CBCBlockCipherMac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14452a;
    private byte[] b;
    private int c;
    private BlockCipher d;
    private BlockCipherPadding e;
    private int f;

    public CBCBlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, (blockCipher.b() * 8) / 2, (BlockCipherPadding) null);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, (blockCipher.b() * 8) / 2, blockCipherPadding);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, int i) {
        this(blockCipher, i, (BlockCipherPadding) null);
    }

    public CBCBlockCipherMac(BlockCipher blockCipher, int i, BlockCipherPadding blockCipherPadding) {
        if (i % 8 == 0) {
            this.d = new CBCBlockCipher(blockCipher);
            this.e = blockCipherPadding;
            this.f = i / 8;
            this.f14452a = new byte[blockCipher.b()];
            this.b = new byte[blockCipher.b()];
            this.c = 0;
            return;
        }
        throw new IllegalArgumentException("MAC size must be multiple of 8");
    }

    public String a() {
        return this.d.a();
    }

    public void a(CipherParameters cipherParameters) {
        c();
        this.d.a(true, cipherParameters);
    }

    public int b() {
        return this.f;
    }

    public void a(byte b2) {
        if (this.c == this.b.length) {
            this.d.a(this.b, 0, this.f14452a, 0);
            this.c = 0;
        }
        byte[] bArr = this.b;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b2;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int b2 = this.d.b();
            int i3 = b2 - this.c;
            if (i2 > i3) {
                System.arraycopy(bArr, i, this.b, this.c, i3);
                this.d.a(this.b, 0, this.f14452a, 0);
                this.c = 0;
                i2 -= i3;
                i += i3;
                while (i2 > b2) {
                    this.d.a(bArr, i, this.f14452a, 0);
                    i2 -= b2;
                    i += b2;
                }
            }
            System.arraycopy(bArr, i, this.b, this.c, i2);
            this.c += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    public int a(byte[] bArr, int i) {
        int b2 = this.d.b();
        if (this.e == null) {
            while (this.c < b2) {
                this.b[this.c] = 0;
                this.c++;
            }
        } else {
            if (this.c == b2) {
                this.d.a(this.b, 0, this.f14452a, 0);
                this.c = 0;
            }
            this.e.a(this.b, this.c);
        }
        this.d.a(this.b, 0, this.f14452a, 0);
        System.arraycopy(this.f14452a, 0, bArr, i, this.f);
        c();
        return this.f;
    }

    public void c() {
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = 0;
        }
        this.c = 0;
        this.d.c();
    }
}
