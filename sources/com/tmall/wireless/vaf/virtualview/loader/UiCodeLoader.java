package com.tmall.wireless.vaf.virtualview.loader;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.nio.charset.Charset;

public class UiCodeLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9401a = "UiCodeLoader_TMTEST";
    private ArrayMap<String, Integer> b = new ArrayMap<>();
    private ArrayMap<String, CodeReader> c = new ArrayMap<>();

    public void b() {
    }

    public void a() {
        this.b.clear();
        this.c.clear();
    }

    public CodeReader a(String str) {
        if (!this.c.containsKey(str) || !this.b.containsKey(str)) {
            Log.e(f9401a, "getCode type invalide type:" + str + this.c.containsKey(str) + " " + this.b.containsKey(str));
            return null;
        }
        CodeReader codeReader = this.c.get(str);
        codeReader.c(this.b.get(str).intValue());
        return codeReader;
    }

    public boolean a(CodeReader codeReader, int i, int i2) {
        int i3 = codeReader.i();
        Log.w(f9401a, "load view count: " + i3);
        short h = codeReader.h();
        String str = new String(codeReader.c(), codeReader.d(), h, Charset.forName("UTF-8"));
        CodeReader codeReader2 = this.c.get(str);
        if (codeReader2 == null || i2 > codeReader2.a()) {
            return a(codeReader, h, str);
        }
        Log.w(f9401a, "load view name " + str + " should not override from " + i2 + " to " + i2);
        return false;
    }

    public boolean b(CodeReader codeReader, int i, int i2) {
        int i3 = codeReader.i();
        Log.w(f9401a, "load view count: " + i3);
        short h = codeReader.h();
        return a(codeReader, h, new String(codeReader.c(), codeReader.d(), h, Charset.forName("UTF-8")));
    }

    private boolean a(CodeReader codeReader, short s, String str) {
        Log.w(f9401a, "load view name " + str);
        this.c.put(str, codeReader);
        codeReader.b(s);
        short h = codeReader.h();
        this.b.put(str, Integer.valueOf(codeReader.d()));
        if (codeReader.b(h)) {
            return true;
        }
        Log.e(f9401a, "seekBy error:" + h);
        return false;
    }
}
