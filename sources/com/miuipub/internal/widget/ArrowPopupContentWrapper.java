package com.miuipub.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import miuipub.v6.R;

public class ArrowPopupContentWrapper extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private Paint f8339a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;

    public ArrowPopupContentWrapper(Context context) {
        this(context, (AttributeSet) null);
    }

    public ArrowPopupContentWrapper(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArrowPopupContentWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8339a = new Paint();
        this.f8339a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f8339a.setAntiAlias(true);
        Resources resources = getResources();
        this.b = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_1);
        this.c = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_2);
        this.d = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_3);
        this.e = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_4);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), (Paint) null, 31);
        super.dispatchDraw(canvas);
        canvas.drawBitmap(this.b, (float) (getPaddingLeft() + 0), (float) (getPaddingTop() + 0), this.f8339a);
        canvas.drawBitmap(this.c, (float) ((getWidth() - this.c.getWidth()) - getPaddingRight()), (float) (getPaddingTop() + 0), this.f8339a);
        canvas.drawBitmap(this.d, (float) (getPaddingLeft() + 0), (float) ((getHeight() - this.d.getHeight()) - getPaddingBottom()), this.f8339a);
        canvas.drawBitmap(this.e, (float) ((getWidth() - this.e.getWidth()) - getPaddingRight()), (float) ((getHeight() - this.e.getHeight()) - getPaddingBottom()), this.f8339a);
        canvas.restore();
    }
}
