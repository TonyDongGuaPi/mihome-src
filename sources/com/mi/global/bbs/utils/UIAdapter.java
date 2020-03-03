package com.mi.global.bbs.utils;

import com.mi.util.ScreenInfo;
import java.util.HashMap;

public class UIAdapter {
    public static final int HALF_OF_SCREENWIDTH = 0;
    public static final int HOME_GALLERY_HEIGHT = 20;
    public static final int HOME_GALLERY_WIDTH = 19;
    private float currentDensityDpi;
    private float currentDesity;
    private float currentScreenWidth;
    private float currentScreenWidthDip;
    public HashMap<Integer, Float> mWidgetUIParamHashMap;
    private float orignalDensity;
    private int orignalDensityDpi;
    private float orignalScreenWidth;
    private float orignalScreenWidthDip;

    public static class SingletonHolder {
        /* access modifiers changed from: private */
        public static UIAdapter INSTANCE = new UIAdapter();
    }

    private UIAdapter() {
        this.orignalDensity = 3.0f;
        this.orignalDensityDpi = 480;
        this.orignalScreenWidth = 1080.0f;
        this.orignalScreenWidthDip = this.orignalScreenWidth / this.orignalDensity;
    }

    public static UIAdapter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initAdapter() {
        this.currentDesity = ScreenInfo.a().d();
        this.currentDensityDpi = (float) ScreenInfo.a().e();
        this.currentScreenWidth = (float) ScreenInfo.a().b();
        this.currentScreenWidthDip = this.currentScreenWidth / this.currentDesity;
        initUIParameters();
    }

    private void initUIParameters() {
        if (this.mWidgetUIParamHashMap == null) {
            this.mWidgetUIParamHashMap = new HashMap<>();
        }
        this.mWidgetUIParamHashMap.put(20, Float.valueOf(getWidgetRatioPixelFromPx(570)));
        this.mWidgetUIParamHashMap.put(19, Float.valueOf(getWidgetRatioPixelFromPx(1080)));
    }

    private int getHeightPixelFromDip(int i) {
        return (int) ((((float) i) * this.currentDesity) + 0.5f);
    }

    private float getWidgetRatioPixelFromDip(int i) {
        return ((((float) i) * this.orignalDensity) * this.currentScreenWidth) / this.orignalScreenWidth;
    }

    private float getWidgetRatioPixelFromPx(int i) {
        return (((float) i) / this.orignalScreenWidth) * this.currentScreenWidth;
    }

    public int getWidgetPxValue(int i) {
        return (int) (this.mWidgetUIParamHashMap.get(Integer.valueOf(i)).floatValue() + 0.5f);
    }
}
