package com.taobao.weex.ui.component.richtext.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.taobao.weex.utils.ATagUtil;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ASpan extends ClickableSpan {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String mInstanceId;
    private String mURL;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6236890782492206451L, "com/taobao/weex/ui/component/richtext/span/ASpan", 3);
        $jacocoData = a2;
        return a2;
    }

    public ASpan(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceId = str;
        this.mURL = str2;
        $jacocoInit[0] = true;
    }

    public void onClick(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        ATagUtil.onClick(view, this.mInstanceId, this.mURL);
        $jacocoInit[1] = true;
    }

    public void updateDrawState(TextPaint textPaint) {
        $jacocoInit()[2] = true;
    }
}
