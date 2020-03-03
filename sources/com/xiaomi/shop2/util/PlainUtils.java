package com.xiaomi.shop2.util;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;

@Deprecated
public class PlainUtils {
    private static final int SIZE_W = 1080;

    public static class WH {
        public int h;
        public int w;

        WH(int i, int i2) {
            this.w = i;
            this.h = i2;
        }
    }

    public static WH getPathWidth(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (!TextUtils.isEmpty(parse.getQueryParameter("w")) && !TextUtils.isEmpty(parse.getQueryParameter("h"))) {
                return getDipSize(Integer.parseInt(parse.getQueryParameter("w")), Integer.parseInt(parse.getQueryParameter("h")));
            }
        }
        return null;
    }

    public static WH getDipSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        return new WH((int) ((((float) i) * ((float) Device.DISPLAY_WIDTH)) / 1080.0f), (int) ((((float) i2) * ((float) Device.DISPLAY_WIDTH)) / 1080.0f));
    }

    public static WH getPathWidth(String str) {
        return getPathWidth(str, false);
    }

    @SuppressLint({"NewApi"})
    public static void setAlpha(View view, float f) {
        if (view != null) {
            if (Build.VERSION.SDK_INT < 11) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
                alphaAnimation.setDuration(0);
                alphaAnimation.setFillAfter(true);
                view.startAnimation(alphaAnimation);
                return;
            }
            view.setAlpha(f);
        }
    }
}
