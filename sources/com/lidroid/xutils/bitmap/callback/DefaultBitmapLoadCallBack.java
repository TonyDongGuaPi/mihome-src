package com.lidroid.xutils.bitmap.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import java.lang.reflect.Method;

public class DefaultBitmapLoadCallBack<T extends View> extends BitmapLoadCallBack<T> {
    public void a(T t, String str, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
        a(t, bitmap);
        Animation b = bitmapDisplayConfig.b();
        if (b != null) {
            a(t, b);
        }
    }

    public void a(T t, String str, Drawable drawable) {
        a(t, drawable);
    }

    private void a(T t, Animation animation) {
        try {
            Method declaredMethod = Animation.class.getDeclaredMethod("clone", new Class[0]);
            declaredMethod.setAccessible(true);
            t.startAnimation((Animation) declaredMethod.invoke(animation, new Object[0]));
        } catch (Throwable unused) {
            t.startAnimation(animation);
        }
    }
}
