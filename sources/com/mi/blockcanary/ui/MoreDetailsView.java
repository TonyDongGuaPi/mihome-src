package com.mi.blockcanary.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public final class MoreDetailsView extends View {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f6773a = new Paint(1);
    private boolean b = true;

    public MoreDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6773a.setStrokeWidth(BlockCanaryUi.a(2.0f, getResources()));
        this.f6773a.setColor(-8083771);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int i = width / 2;
        float f = (float) (height / 2);
        canvas.drawLine(0.0f, f, (float) width, f, this.f6773a);
        if (this.b) {
            float f2 = (float) i;
            canvas.drawLine(f2, 0.0f, f2, (float) height, this.f6773a);
        }
    }

    public void setFolding(boolean z) {
        if (z != this.b) {
            this.b = z;
            invalidate();
        }
    }
}
