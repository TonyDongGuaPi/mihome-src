package com.taobao.weex.ui.view.border;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import com.taobao.weex.dom.CSSShorthand;

enum BorderStyle {
    SOLID,
    DASHED,
    DOTTED;

    static {
        boolean[] $jacocoInit;
        $jacocoInit[22] = true;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Shader getLineShader(float f, int i, CSSShorthand.EDGE edge) {
        CSSShorthand.EDGE edge2 = edge;
        boolean[] $jacocoInit = $jacocoInit();
        switch (this) {
            case DOTTED:
                if (edge2 == CSSShorthand.EDGE.LEFT) {
                    $jacocoInit[5] = true;
                } else if (edge2 == CSSShorthand.EDGE.RIGHT) {
                    $jacocoInit[6] = true;
                } else {
                    if (edge2 == CSSShorthand.EDGE.TOP) {
                        $jacocoInit[8] = true;
                    } else if (edge2 != CSSShorthand.EDGE.BOTTOM) {
                        $jacocoInit[9] = true;
                        break;
                    } else {
                        $jacocoInit[10] = true;
                    }
                    LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, f * 2.0f, 0.0f, new int[]{i, 0}, new float[]{0.5f, 0.5f}, Shader.TileMode.REPEAT);
                    $jacocoInit[11] = true;
                    return linearGradient;
                }
                LinearGradient linearGradient2 = new LinearGradient(0.0f, 0.0f, 0.0f, f * 2.0f, new int[]{i, 0}, new float[]{0.5f, 0.5f}, Shader.TileMode.REPEAT);
                $jacocoInit[7] = true;
                return linearGradient2;
            case DASHED:
                $jacocoInit[4] = true;
                break;
            default:
                $jacocoInit[3] = true;
                break;
        }
        if (edge2 == CSSShorthand.EDGE.LEFT) {
            $jacocoInit[12] = true;
        } else if (edge2 == CSSShorthand.EDGE.RIGHT) {
            $jacocoInit[13] = true;
        } else {
            if (edge2 == CSSShorthand.EDGE.TOP) {
                $jacocoInit[15] = true;
            } else if (edge2 != CSSShorthand.EDGE.BOTTOM) {
                $jacocoInit[16] = true;
                $jacocoInit[19] = true;
                return null;
            } else {
                $jacocoInit[17] = true;
            }
            LinearGradient linearGradient3 = new LinearGradient(0.0f, 0.0f, f * 6.0f, 0.0f, new int[]{i, 0}, new float[]{0.5f, 0.5f}, Shader.TileMode.REPEAT);
            $jacocoInit[18] = true;
            return linearGradient3;
        }
        LinearGradient linearGradient4 = new LinearGradient(0.0f, 0.0f, 0.0f, f * 6.0f, new int[]{i, 0}, new float[]{0.5f, 0.5f}, Shader.TileMode.REPEAT);
        $jacocoInit[14] = true;
        return linearGradient4;
    }
}
