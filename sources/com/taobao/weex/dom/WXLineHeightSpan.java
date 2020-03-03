package com.taobao.weex.dom;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXLineHeightSpan implements LineHeightSpan {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int lineHeight;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3265355404340093870L, "com/taobao/weex/dom/WXLineHeightSpan", 5);
        $jacocoData = a2;
        return a2;
    }

    public WXLineHeightSpan(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.lineHeight = i;
        $jacocoInit[0] = true;
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            WXLogUtils.d("LineHeight", charSequence + " ; start " + i + "; end " + i2 + "; spanstartv " + i3 + "; v " + i4 + "; fm " + fontMetricsInt);
            $jacocoInit[3] = true;
        }
        int i5 = (this.lineHeight - (fontMetricsInt.descent - fontMetricsInt.ascent)) / 2;
        fontMetricsInt.top -= i5;
        fontMetricsInt.bottom += i5;
        fontMetricsInt.ascent -= i5;
        fontMetricsInt.descent += i5;
        $jacocoInit[4] = true;
    }
}
