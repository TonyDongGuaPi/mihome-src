package org.pgsqlite;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

public class CallbackContext {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4114a = "CallbackContext";
    private Callback b;
    private Callback c;

    public CallbackContext(Callback callback, Callback callback2) {
        this.b = callback;
        this.c = callback2;
    }

    public void a(WritableMap writableMap) {
        this.b.invoke(writableMap);
    }

    public void a(String str) {
        this.b.invoke(str);
    }

    public void a(WritableArray writableArray) {
        this.b.invoke(writableArray);
    }

    public void a() {
        this.b.invoke("Success");
    }

    public void b(WritableMap writableMap) {
        this.c.invoke(writableMap);
    }

    public void b(String str) {
        this.c.invoke(str);
    }
}
