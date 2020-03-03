package com.xiaomi.jr.idcardverifier;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.jr.idcardverifier.utils.VerifyUtils;

public class PreviewMaskResultView extends View {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f10864a;
    private int b;
    private int c;
    private RectF d;
    private Bitmap e;

    public PreviewMaskResultView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PreviewMaskResultView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewMaskResultView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayerType(1, (Paint) null);
        this.f10864a = new Paint(1);
        this.f10864a.setAlpha(255);
        this.f10864a.setStyle(Paint.Style.FILL);
        this.f10864a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d != null) {
            canvas.drawColor(this.b);
            canvas.drawRoundRect(this.d, (float) this.c, (float) this.c, this.f10864a);
            if (this.e != null && !this.e.isRecycled()) {
                canvas.drawBitmap(this.e, (Rect) null, this.d, (Paint) null);
            }
        }
    }

    public void setMaskColor(int i) {
        this.b = i;
    }

    public void setMaskBorderCornerRadius(int i) {
        this.c = i;
    }

    public void setMaskRect(RectF rectF) {
        this.d = rectF;
    }

    public void setResultImage(Bitmap bitmap) {
        if (this.e != null) {
            this.e.recycle();
        }
        if (bitmap == null) {
            this.e = null;
        } else {
            this.e = VerifyUtils.a(bitmap, (int) (this.d.right - this.d.left), (int) (this.d.bottom - this.d.top), this.c);
        }
        invalidate();
    }
}
