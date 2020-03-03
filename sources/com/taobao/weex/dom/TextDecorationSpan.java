package com.taobao.weex.dom;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import com.taobao.weex.ui.component.WXTextDecoration;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TextDecorationSpan extends CharacterStyle implements UpdateAppearance {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final WXTextDecoration mTextDecoration;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6465602348088587333L, "com/taobao/weex/dom/TextDecorationSpan", 9);
        $jacocoData = a2;
        return a2;
    }

    public TextDecorationSpan(@NonNull WXTextDecoration wXTextDecoration) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTextDecoration = wXTextDecoration;
        $jacocoInit[0] = true;
    }

    public void updateDrawState(TextPaint textPaint) {
        boolean[] $jacocoInit = $jacocoInit();
        switch (this.mTextDecoration) {
            case LINETHROUGH:
                textPaint.setUnderlineText(false);
                $jacocoInit[2] = true;
                textPaint.setStrikeThruText(true);
                $jacocoInit[3] = true;
                break;
            case UNDERLINE:
                textPaint.setUnderlineText(true);
                $jacocoInit[4] = true;
                textPaint.setStrikeThruText(false);
                $jacocoInit[5] = true;
                break;
            case NONE:
                textPaint.setUnderlineText(false);
                $jacocoInit[6] = true;
                textPaint.setStrikeThruText(false);
                $jacocoInit[7] = true;
                break;
            default:
                $jacocoInit[1] = true;
                break;
        }
        $jacocoInit[8] = true;
    }
}
