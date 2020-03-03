package com.xiaomi.smarthome.lite.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;

public class LiteSceneContainerView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f19389a;
    private Bitmap b;
    private Rect c;
    private Rect d;

    public LiteSceneContainerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f19389a = 0;
        this.b = null;
        a();
    }

    public LiteSceneContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiteSceneContainerView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void a() {
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.lite_scene_title_bg);
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        this.c = new Rect();
        this.d = new Rect();
        this.d.top = 0;
        this.d.left = 0;
        this.d.right = width;
        this.d.bottom = height;
        this.c.top = 0;
        this.c.left = 0;
        setWillNotDraw(false);
    }

    public void setLineCount(int i) {
        if (this.f19389a != i) {
            if (i < 0) {
                this.f19389a = 0;
            }
            if (i > 2) {
                this.f19389a = 2;
            }
            this.f19389a = i;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.c.bottom = getMeasuredHeight();
        this.c.right = getMeasuredWidth();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.b != null && measuredWidth != 0 && measuredHeight != 0 && this.b.getHeight() != 0 && this.b.getWidth() != 0) {
            this.d.top = ((2 - this.f19389a) * this.b.getHeight()) / 3;
            if (this.f19389a == 0) {
                canvas.drawColor(0);
            } else {
                canvas.drawBitmap(this.b, this.d, this.c, (Paint) null);
            }
        }
    }
}
