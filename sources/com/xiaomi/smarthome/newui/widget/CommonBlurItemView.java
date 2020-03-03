package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class CommonBlurItemView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20827a = 1;
    private BitmapGetter b;
    /* access modifiers changed from: private */
    public boolean c;
    private Paint d;
    private int[] e = new int[2];
    private int[] f = new int[2];
    private Rect g = new Rect();
    private Rect h = new Rect();
    private int i = -1;
    /* access modifiers changed from: private */
    public Handler j = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1 && CommonBlurItemView.this.c) {
                CommonBlurItemView.this.invalidate();
                CommonBlurItemView.this.j.sendEmptyMessageDelayed(1, 10);
            }
        }
    };

    public interface BitmapGetter {
        Bitmap a(int[] iArr);
    }

    public CommonBlurItemView(Context context) {
        super(context);
        a();
    }

    public CommonBlurItemView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.c = true;
        this.j.sendEmptyMessage(1);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c = false;
    }

    private void a() {
        this.d = new Paint(1);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        super.onDraw(canvas);
        getLocationInWindow(this.e);
        Bitmap a2 = this.b.a(this.f);
        if (a2 != null) {
            this.g.set(0, 0, this.f[0], this.f[1]);
            this.h.set(-this.e[0], -this.e[1], this.f[0] - this.e[0], this.f[1] - this.e[1]);
            canvas.drawBitmap(a2, this.g, this.h, this.d);
            if (this.i != -1) {
                drawable = getResources().getDrawable(this.i);
            } else {
                drawable = getResources().getDrawable(R.drawable.top_widget_normal_shape_corner);
            }
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public void setBitmapGetter(BitmapGetter bitmapGetter) {
        this.b = bitmapGetter;
    }

    public void setDrawableId(int i2) {
        this.i = i2;
    }

    public void invalidateNow() {
        invalidate();
    }
}
