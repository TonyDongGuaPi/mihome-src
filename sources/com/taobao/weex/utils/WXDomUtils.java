package com.taobao.weex.utils;

import android.support.annotation.NonNull;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXDomUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7057835251124622058L, "com/taobao/weex/utils/WXDomUtils", 34);
        $jacocoData = a2;
        return a2;
    }

    public WXDomUtils() {
        $jacocoInit()[0] = true;
    }

    public static float getContentWidth(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        float layoutWidth = wXComponent.getLayoutWidth();
        $jacocoInit[1] = true;
        CSSShorthand padding = wXComponent.getPadding();
        $jacocoInit[2] = true;
        CSSShorthand border = wXComponent.getBorder();
        $jacocoInit[3] = true;
        float f = padding.get(CSSShorthand.EDGE.LEFT);
        if (CSSConstants.isUndefined(f)) {
            $jacocoInit[4] = true;
        } else {
            layoutWidth -= f;
            $jacocoInit[5] = true;
        }
        float f2 = padding.get(CSSShorthand.EDGE.RIGHT);
        if (CSSConstants.isUndefined(f2)) {
            $jacocoInit[6] = true;
        } else {
            layoutWidth -= f2;
            $jacocoInit[7] = true;
        }
        float f3 = border.get(CSSShorthand.EDGE.LEFT);
        if (CSSConstants.isUndefined(f3)) {
            $jacocoInit[8] = true;
        } else {
            layoutWidth -= f3;
            $jacocoInit[9] = true;
        }
        float f4 = border.get(CSSShorthand.EDGE.RIGHT);
        if (CSSConstants.isUndefined(f4)) {
            $jacocoInit[10] = true;
        } else {
            layoutWidth -= f4;
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
        return layoutWidth;
    }

    public static float getContentHeight(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        float layoutHeight = wXComponent.getLayoutHeight();
        $jacocoInit[13] = true;
        CSSShorthand padding = wXComponent.getPadding();
        $jacocoInit[14] = true;
        CSSShorthand border = wXComponent.getBorder();
        $jacocoInit[15] = true;
        float f = padding.get(CSSShorthand.EDGE.TOP);
        if (CSSConstants.isUndefined(f)) {
            $jacocoInit[16] = true;
        } else {
            layoutHeight -= f;
            $jacocoInit[17] = true;
        }
        float f2 = padding.get(CSSShorthand.EDGE.BOTTOM);
        if (CSSConstants.isUndefined(f2)) {
            $jacocoInit[18] = true;
        } else {
            layoutHeight -= f2;
            $jacocoInit[19] = true;
        }
        float f3 = border.get(CSSShorthand.EDGE.TOP);
        if (CSSConstants.isUndefined(f3)) {
            $jacocoInit[20] = true;
        } else {
            layoutHeight -= f3;
            $jacocoInit[21] = true;
        }
        float f4 = border.get(CSSShorthand.EDGE.BOTTOM);
        if (CSSConstants.isUndefined(f4)) {
            $jacocoInit[22] = true;
        } else {
            layoutHeight -= f4;
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
        return layoutHeight;
    }

    public static float getContentWidth(@NonNull CSSShorthand cSSShorthand, @NonNull CSSShorthand cSSShorthand2, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float f2 = cSSShorthand.get(CSSShorthand.EDGE.LEFT);
        if (CSSConstants.isUndefined(f2)) {
            $jacocoInit[25] = true;
        } else {
            f -= f2;
            $jacocoInit[26] = true;
        }
        float f3 = cSSShorthand.get(CSSShorthand.EDGE.RIGHT);
        if (CSSConstants.isUndefined(f3)) {
            $jacocoInit[27] = true;
        } else {
            f -= f3;
            $jacocoInit[28] = true;
        }
        float f4 = cSSShorthand2.get(CSSShorthand.EDGE.LEFT);
        if (CSSConstants.isUndefined(f4)) {
            $jacocoInit[29] = true;
        } else {
            f -= f4;
            $jacocoInit[30] = true;
        }
        float f5 = cSSShorthand2.get(CSSShorthand.EDGE.RIGHT);
        if (CSSConstants.isUndefined(f5)) {
            $jacocoInit[31] = true;
        } else {
            f -= f5;
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
        return f;
    }
}
