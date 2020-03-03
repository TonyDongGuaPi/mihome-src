package com.taobao.weex.dom;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.taobao.weex.utils.TypefaceUtil;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXCustomStyleSpan extends MetricAffectingSpan {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7071506855625009227L, "com/taobao/weex/dom/WXCustomStyleSpan", 10);
        $jacocoData = a2;
        return a2;
    }

    public WXCustomStyleSpan(int i, int i2, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mStyle = i;
        this.mWeight = i2;
        this.mFontFamily = str;
        $jacocoInit[0] = true;
    }

    public void updateDrawState(TextPaint textPaint) {
        boolean[] $jacocoInit = $jacocoInit();
        TypefaceUtil.applyFontStyle(textPaint, this.mStyle, this.mWeight, this.mFontFamily);
        $jacocoInit[1] = true;
    }

    public void updateMeasureState(TextPaint textPaint) {
        boolean[] $jacocoInit = $jacocoInit();
        TypefaceUtil.applyFontStyle(textPaint, this.mStyle, this.mWeight, this.mFontFamily);
        $jacocoInit[2] = true;
    }

    public int getStyle() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStyle == -1) {
            i = 0;
            $jacocoInit[3] = true;
        } else {
            i = this.mStyle;
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
        return i;
    }

    public int getWeight() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWeight == -1) {
            i = 0;
            $jacocoInit[6] = true;
        } else {
            i = this.mWeight;
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return i;
    }

    public String getFontFamily() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mFontFamily;
        $jacocoInit[9] = true;
        return str;
    }
}
