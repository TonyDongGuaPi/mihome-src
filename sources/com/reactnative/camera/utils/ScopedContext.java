package com.reactnative.camera.utils;

import android.content.Context;
import java.io.File;

public class ScopedContext {

    /* renamed from: a  reason: collision with root package name */
    private File f8683a = null;

    public ScopedContext(Context context) {
        a(context);
    }

    public void a(Context context) {
        this.f8683a = new File(context.getCacheDir() + "/Camera/");
    }

    public File a() {
        return this.f8683a;
    }
}
