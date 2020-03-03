package com.xiaomi.ai.mibrain;

public class MibrainUtils {
    public static native String base64(byte[] bArr);

    public static native String sha1(byte[] bArr);

    static {
        Mibrainsdk.loadMibrainLibs();
    }
}
