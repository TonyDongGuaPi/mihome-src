package org.xutils.http.body;

import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class StringBody implements RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f10780a;
    private String b;
    private String c = "UTF-8";

    public StringBody(String str, String str2) throws UnsupportedEncodingException {
        if (!TextUtils.isEmpty(str2)) {
            this.c = str2;
        }
        this.f10780a = str.getBytes(this.c);
    }

    public long b() {
        return (long) this.f10780a.length;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        return "application/json;charset=" + this.c;
    }

    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(this.f10780a);
        outputStream.flush();
    }
}
