package com.xiaomi.youpin.common.util.crypto.rc4coder;

import com.xiaomi.youpin.common.util.crypto.Base64Coder;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.crypto.IllegalBlockSizeException;

public class RC4DropCoder {
    private static final int b = 1024;
    private static final byte[] c = new byte[1024];

    /* renamed from: a  reason: collision with root package name */
    RC4 f23301a;

    static {
        Arrays.fill(c, (byte) 0);
    }

    public RC4DropCoder(byte[] bArr) throws SecurityException {
        if (c(bArr)) {
            throw new SecurityException("rc4 key is null");
        } else if (bArr.length == 32) {
            this.f23301a = new RC4(bArr);
            a(c);
        } else {
            throw new IllegalArgumentException("rc4Key length is invalid");
        }
    }

    public RC4DropCoder(String str) throws SecurityException {
        this(Base64Coder.a(str));
    }

    private static boolean c(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public byte[] a(byte[] bArr) throws SecurityException {
        if (bArr != null) {
            try {
                this.f23301a.a(bArr);
                return bArr;
            } catch (IllegalBlockSizeException e) {
                throw new SecurityException(e);
            }
        } else {
            throw new IllegalBlockSizeException("no block data");
        }
    }

    public String a(String str) {
        try {
            return new String(a(Base64Coder.a(str)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException(e);
        }
    }

    public byte[] b(byte[] bArr) throws SecurityException {
        if (bArr != null) {
            try {
                this.f23301a.a(bArr);
                return bArr;
            } catch (IllegalBlockSizeException e) {
                throw new SecurityException(e);
            }
        } else {
            throw new IllegalBlockSizeException("no block data");
        }
    }

    public String b(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bArr = null;
        }
        return String.valueOf(Base64Coder.a(b(bArr)));
    }
}
