package org.xutils.http.body;

import android.text.TextUtils;

public final class BodyItemWrapper {

    /* renamed from: a  reason: collision with root package name */
    private final Object f10775a;
    private final String b;
    private final String c;

    public BodyItemWrapper(Object obj, String str) {
        this(obj, str, (String) null);
    }

    public BodyItemWrapper(Object obj, String str, String str2) {
        this.f10775a = obj;
        if (TextUtils.isEmpty(str)) {
            this.c = "application/octet-stream";
        } else {
            this.c = str;
        }
        this.b = str2;
    }

    public Object a() {
        return this.f10775a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
