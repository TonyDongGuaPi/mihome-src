package com.imagepicker;

import android.support.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;

public class ResponseHelper {

    /* renamed from: a  reason: collision with root package name */
    private WritableMap f6064a = Arguments.createMap();

    public void a() {
        this.f6064a = Arguments.createMap();
    }

    @NonNull
    public WritableMap b() {
        return this.f6064a;
    }

    public void a(@NonNull String str, @NonNull String str2) {
        this.f6064a.putString(str, str2);
    }

    public void a(@NonNull String str, int i) {
        this.f6064a.putInt(str, i);
    }

    public void a(@NonNull String str, boolean z) {
        this.f6064a.putBoolean(str, z);
    }

    public void a(@NonNull String str, double d) {
        this.f6064a.putDouble(str, d);
    }

    public void a(@NonNull Callback callback, @NonNull String str) {
        a();
        this.f6064a.putString("customButton", str);
        b(callback);
    }

    public void a(@NonNull Callback callback) {
        a();
        this.f6064a.putBoolean("didCancel", true);
        b(callback);
    }

    public void b(@NonNull Callback callback, @NonNull String str) {
        a();
        this.f6064a.putString("error", str);
        b(callback);
    }

    public void b(@NonNull Callback callback) {
        callback.invoke(this.f6064a);
    }
}
