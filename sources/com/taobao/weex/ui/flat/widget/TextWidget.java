package com.taobao.weex.ui.flat.widget;

import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.Layout;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.view.border.BorderDrawable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class TextWidget extends BaseWidget {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private Layout mLayout;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5379974459698085873L, "com/taobao/weex/ui/flat/widget/TextWidget", 10);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void setBackgroundAndBorder(@NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setBackgroundAndBorder(borderDrawable);
        $jacocoInit[7] = true;
    }

    public /* synthetic */ void setContentBox(int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setContentBox(i, i2, i3, i4);
        $jacocoInit[8] = true;
    }

    public /* synthetic */ void setLayout(int i, int i2, int i3, int i4, int i5, int i6, Point point) {
        boolean[] $jacocoInit = $jacocoInit();
        super.setLayout(i, i2, i3, i4, i5, i6, point);
        $jacocoInit[9] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextWidget(@NonNull FlatGUIContext flatGUIContext) {
        super(flatGUIContext);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void onDraw(@NonNull Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mLayout == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            this.mLayout.draw(canvas);
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
    }

    public void updateTextDrawable(Layout layout) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mLayout = layout;
        $jacocoInit[5] = true;
        invalidate();
        $jacocoInit[6] = true;
    }
}
