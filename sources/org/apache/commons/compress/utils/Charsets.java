package org.apache.commons.compress.utils;

import java.nio.charset.Charset;

public class Charsets {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f3347a = Charset.forName("ISO-8859-1");
    public static final Charset b = Charset.forName("US-ASCII");
    public static final Charset c = Charset.forName("UTF-16");
    public static final Charset d = Charset.forName("UTF-16BE");
    public static final Charset e = Charset.forName("UTF-16LE");
    public static final Charset f = Charset.forName("UTF-8");

    public static Charset a(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset a(String str) {
        return str == null ? Charset.defaultCharset() : Charset.forName(str);
    }
}
