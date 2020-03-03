package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class ScreenInfo {
    private static final boolean DEBUG = true;
    private static final String TAG = "ScreenInfo";
    private float screenDensity;
    private int screenDensitydpi;
    private int screenHeight;
    private int screenHeightDip;
    private int screenWidth;
    private int screenWidthDip;

    private ScreenInfo() {
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static ScreenInfo INSTANCE = new ScreenInfo();

        private SingletonHolder() {
        }
    }

    public static ScreenInfo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialScreenInfo(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            this.screenWidth = i;
            this.screenHeight = i2;
        } else {
            this.screenWidth = i2;
            this.screenHeight = i;
        }
        this.screenDensity = displayMetrics.density;
        this.screenDensitydpi = displayMetrics.densityDpi;
        this.screenWidthDip = (int) ((((float) displayMetrics.widthPixels) / this.screenDensity) + 0.5f);
        this.screenHeightDip = (int) ((((float) displayMetrics.heightPixels) / this.screenDensity) + 0.5f);
        String str = TAG;
        Log.d(str, "屏幕高度px:" + this.screenHeight + ",屏幕宽度px:" + this.screenWidth + ",Density:" + this.screenDensity + ",dpi:" + this.screenDensitydpi + ",屏幕高度dip:" + this.screenHeightDip + ",屏幕宽度dip:" + this.screenWidthDip);
        Log.d(TAG, displayMetrics.toString());
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHigth() {
        return this.screenHeight;
    }

    public float getScreenDensity() {
        return this.screenDensity;
    }

    public int getScreenDensitydpi() {
        return this.screenDensitydpi;
    }
}
