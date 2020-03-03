package com.miui.supportlite.graphics;

import android.graphics.Bitmap;
import android.util.Log;
import com.miui.supportlite.reflect.Method;
import com.miui.supportlite.reflect.NoSuchMethodException;

public class BitmapFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8203a = "BitmapFactory";

    public static Bitmap a(Bitmap bitmap, int i) {
        try {
            Class<?> cls = Class.forName("miui.graphics.BitmapFactory");
            return (Bitmap) Method.a(cls, "fastBlur", Bitmap.class, Bitmap.class, Integer.TYPE).j(cls, (Object) null, bitmap, Integer.valueOf(i));
        } catch (NoSuchMethodException | ClassNotFoundException unused) {
            Log.e(f8203a, "Call miui.graphics.fastBlur() failed.");
            return null;
        }
    }
}
