package com.taobao.weex.ui.component.richtext.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import android.view.View;
import com.taobao.weex.adapter.IDrawableLoader;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ImgSpan extends ReplacementSpan implements IDrawableLoader.StaticTarget {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int height;
    private Drawable mDrawable;
    private View mView;
    private int width;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(3371097234299983881L, "com/taobao/weex/ui/component/richtext/span/ImgSpan", 25);
        $jacocoData = a2;
        return a2;
    }

    public ImgSpan(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.width = i;
        this.height = i2;
        $jacocoInit[0] = true;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        boolean[] $jacocoInit = $jacocoInit();
        if (fontMetricsInt == null) {
            $jacocoInit[1] = true;
        } else {
            fontMetricsInt.ascent = -this.height;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
            $jacocoInit[2] = true;
        }
        int i3 = this.width;
        $jacocoInit[3] = true;
        return i3;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mDrawable == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            canvas.save();
            $jacocoInit[6] = true;
            $jacocoInit[7] = true;
            $jacocoInit[8] = true;
            canvas.translate(f, (float) ((i5 - this.mDrawable.getBounds().bottom) - paint.getFontMetricsInt().descent));
            $jacocoInit[9] = true;
            this.mDrawable.draw(canvas);
            $jacocoInit[10] = true;
            canvas.restore();
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }

    public void setDrawable(Drawable drawable, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mDrawable = drawable;
        if (!z) {
            $jacocoInit[13] = true;
        } else {
            $jacocoInit[14] = true;
            this.mDrawable.setBounds(0, 0, this.width, this.height);
            $jacocoInit[15] = true;
        }
        setCallback();
        $jacocoInit[16] = true;
        this.mDrawable.invalidateSelf();
        $jacocoInit[17] = true;
    }

    public void setView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mView = view;
        $jacocoInit[18] = true;
        setCallback();
        $jacocoInit[19] = true;
    }

    private void setCallback() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mDrawable == null) {
            $jacocoInit[20] = true;
        } else if (this.mView == null) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            this.mDrawable.setCallback(this.mView);
            $jacocoInit[23] = true;
        }
        $jacocoInit[24] = true;
    }
}
