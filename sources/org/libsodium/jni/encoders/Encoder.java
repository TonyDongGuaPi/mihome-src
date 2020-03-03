package org.libsodium.jni.encoders;

import java.nio.charset.Charset;

public interface Encoder {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f3724a = Charset.forName("UTF-8");
    public static final Hex b = new Hex();
    public static final Raw c = new Raw();

    String a(byte[] bArr);

    byte[] a(String str);
}
