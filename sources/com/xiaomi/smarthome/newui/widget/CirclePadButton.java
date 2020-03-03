package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;

public class CirclePadButton extends View {
    private static Bitmap c;
    private static Bitmap d;
    private static Bitmap e;
    private static Bitmap f;
    private static Bitmap g;
    private static Bitmap h;

    /* renamed from: a  reason: collision with root package name */
    private MODE f20825a;
    private ClickListener b;
    private Paint i;
    private Paint j;
    private float k;
    private long l;
    private boolean m;

    public interface ClickListener {
        void onClick(MODE mode);
    }

    public enum MODE {
        Normal,
        Switch,
        Plus,
        Minus
    }

    public void setListener(ClickListener clickListener) {
        this.b = clickListener;
    }

    public CirclePadButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public CirclePadButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setTouchable(boolean z) {
        this.m = z;
    }

    public CirclePadButton(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f20825a = MODE.Normal;
        this.m = true;
        this.i = new Paint();
        this.j = new Paint();
        this.j.setColor(Color.parseColor("#e5e5e5"));
        this.j.setStyle(Paint.Style.FILL);
        d = BitmapFactory.decodeResource(getResources(), R.drawable.popup_bg_tvadd_pre);
        e = BitmapFactory.decodeResource(getResources(), R.drawable.popup_bg_tvreduce_nor);
        f = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvswitch_nor);
        g = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvadd_nor);
        h = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvreduce_nor);
        this.k = (float) DisplayUtils.a(getContext(), 200.0f);
        c = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_bg_nor);
        this.l = (long) DisplayUtils.a(getContext(), 36.0f);
    }

    public void setMode(MODE mode) {
        this.f20825a = mode;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (this.f20825a) {
            case Normal:
                canvas.drawBitmap(c, 0.0f, 0.0f, this.i);
                this.j.setColor(-1);
                canvas.drawCircle(this.k / 2.0f, this.k / 2.0f, (float) this.l, this.j);
                break;
            case Plus:
                canvas.drawBitmap(d, 0.0f, 0.0f, this.i);
                break;
            case Minus:
                canvas.drawBitmap(e, 0.0f, 0.0f, this.i);
                break;
            case Switch:
                canvas.drawBitmap(c, 0.0f, 0.0f, this.i);
                this.j.setColor(Color.parseColor("#e5e5e5"));
                canvas.drawCircle(this.k / 2.0f, this.k / 2.0f, (float) this.l, this.j);
                break;
        }
        canvas.drawBitmap(g, (this.k / 2.0f) - (((float) g.getWidth()) / 2.0f), (((this.k / 2.0f) - ((float) this.l)) / 2.0f) - (((float) g.getWidth()) / 2.0f), this.i);
        canvas.drawBitmap(h, (this.k / 2.0f) - (((float) h.getWidth()) / 2.0f), (this.k - (((this.k / 2.0f) - ((float) this.l)) / 2.0f)) - (((float) h.getWidth()) / 2.0f), this.i);
        canvas.drawBitmap(f, (this.k / 2.0f) - (((float) f.getWidth()) / 2.0f), (this.k / 2.0f) - ((float) (f.getHeight() / 2)), this.i);
    }

    public void updateUnableUI(boolean z) {
        if (z) {
            f = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvswitch_nor);
            g = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvadd_nor);
            h = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvreduce_nor);
        } else {
            g = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvadd_disable);
            h = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_minus_disable);
            f = BitmapFactory.decodeResource(getResources(), R.drawable.popup_icon_tvswitch_disable);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.m) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                a(motionEvent);
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(true);
                if (this.b != null) {
                    this.b.onClick(this.f20825a);
                }
                this.f20825a = MODE.Normal;
                invalidate();
                break;
            case 2:
                getParent().requestDisallowInterceptTouchEvent(true);
                a(motionEvent);
                break;
        }
        return true;
    }

    private void a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float f2 = x > this.k / 2.0f ? x - (this.k / 2.0f) : (this.k / 2.0f) - x;
        float f3 = y > this.k / 2.0f ? y - (this.k / 2.0f) : (this.k / 2.0f) - y;
        float f4 = (f2 * f2) + (f3 * f3);
        if (f4 <= ((float) (this.l * this.l))) {
            this.f20825a = MODE.Switch;
            invalidate();
        } else if (f4 > (this.k / 2.0f) * this.k * 2.0f || x < 0.0f || x > this.k) {
            this.f20825a = MODE.Normal;
            invalidate();
        } else if (((double) (f3 / f2)) < Math.tan(22.5d)) {
            this.f20825a = MODE.Normal;
            invalidate();
        } else if (y < this.k / 2.0f) {
            this.f20825a = MODE.Plus;
            invalidate();
        } else {
            this.f20825a = MODE.Minus;
            invalidate();
        }
    }
}
