package com.xiaomi.base.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

public class PlainUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10033a = 0;
    private static final int b = 0;
    private static final int c = 1080;

    public static void a(String str, Activity activity, View view, Drawable drawable, String str2) {
    }

    public static WH a(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        return new WH((int) (((float) (i * Device.f10020a)) / 1080.0f), (int) (((float) (i2 * Device.f10020a)) / 1080.0f));
    }

    public static WH a(String str) {
        return a(str, false);
    }

    public static WH a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (!TextUtils.isEmpty(parse.getQueryParameter("w")) && !TextUtils.isEmpty(parse.getQueryParameter("h"))) {
                return a(Integer.parseInt(parse.getQueryParameter("w")), Integer.parseInt(parse.getQueryParameter("h")));
            }
        }
        return null;
    }

    public static class WH {

        /* renamed from: a  reason: collision with root package name */
        public int f10034a;
        public int b;

        WH(int i, int i2) {
            this.b = i;
            this.f10034a = i2;
        }
    }
}
