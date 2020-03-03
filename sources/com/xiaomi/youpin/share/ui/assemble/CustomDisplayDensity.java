package com.xiaomi.youpin.share.ui.assemble;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class CustomDisplayDensity {

    /* renamed from: a  reason: collision with root package name */
    private static float f23737a;
    private static int b;
    /* access modifiers changed from: private */
    public static float c;

    public static void a(@NonNull final Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = ((float) (displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels)) / 360.0f;
        if (displayMetrics.density != f) {
            if (f23737a == 0.0f) {
                f23737a = displayMetrics.density;
                b = displayMetrics.densityDpi;
                c = displayMetrics.scaledDensity;
                context.registerComponentCallbacks(new ComponentCallbacks() {
                    public void onLowMemory() {
                    }

                    public void onConfigurationChanged(Configuration configuration) {
                        if (configuration != null && configuration.fontScale > 0.0f && context != null && context.getResources() != null) {
                            float unused = CustomDisplayDensity.c = context.getResources().getDisplayMetrics().scaledDensity;
                        }
                    }
                });
            }
            float f2 = (c * f) / f23737a;
            displayMetrics.density = f;
            displayMetrics.scaledDensity = f2;
            displayMetrics.densityDpi = (int) (160.0f * f);
        }
    }

    public static void b(@NonNull Context context) {
        if (f23737a != 0.0f) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            displayMetrics.density = f23737a;
            displayMetrics.densityDpi = b;
            displayMetrics.scaledDensity = c;
        }
    }
}
