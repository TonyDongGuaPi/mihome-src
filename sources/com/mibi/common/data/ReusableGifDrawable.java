package com.mibi.common.data;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import miuipub.graphics.drawable.GifAnimationDrawable;

public class ReusableGifDrawable {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, GifAnimationDrawable> f7539a;

    public static GifAnimationDrawable a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f7539a == null) {
            f7539a = new HashMap<>();
        }
        if (f7539a.containsKey(str)) {
            return f7539a.get(str);
        }
        GifAnimationDrawable gifAnimationDrawable = new GifAnimationDrawable();
        if (!gifAnimationDrawable.a(context.getApplicationContext(), context.getAssets(), str)) {
            return null;
        }
        f7539a.put(str, gifAnimationDrawable);
        return gifAnimationDrawable;
    }
}
