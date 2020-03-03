package org.libsodium.jni.encoders;

public class Raw implements Encoder {
    public byte[] a(String str) {
        if (str != null) {
            return str.getBytes(f3724a);
        }
        return null;
    }

    public String a(byte[] bArr) {
        if (bArr != null) {
            return new String(bArr, f3724a);
        }
        return null;
    }
}
