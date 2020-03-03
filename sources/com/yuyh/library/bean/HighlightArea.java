package com.yuyh.library.bean;

import android.graphics.RectF;
import android.view.View;
import com.yuyh.library.support.HShape;

public class HighlightArea {

    /* renamed from: a  reason: collision with root package name */
    public View f2563a;
    @HShape
    public int b;

    public HighlightArea(View view, @HShape int i) {
        this.f2563a = view;
        this.b = i;
    }

    public RectF a() {
        RectF rectF = new RectF();
        if (this.f2563a != null) {
            int[] iArr = new int[2];
            this.f2563a.getLocationOnScreen(iArr);
            rectF.left = (float) iArr[0];
            rectF.top = (float) iArr[1];
            rectF.right = (float) (iArr[0] + this.f2563a.getWidth());
            rectF.bottom = (float) (iArr[1] + this.f2563a.getHeight());
        }
        return rectF;
    }
}
