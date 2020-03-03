package com.xiaomi.smarthome.newui.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;

public class GridItemAnimView extends View {
    public static final int STATE_ANIM_OFF = 4;
    public static final int STATE_ANIM_ON = 3;

    /* renamed from: a  reason: collision with root package name */
    private int f20866a;
    private Paint b;
    private Paint c;
    private ObjectAnimator d = ObjectAnimator.ofFloat(this, NotificationCompat.CATEGORY_PROGRESS, new float[]{0.0f, 100.0f});
    private ObjectAnimator e = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
    private RectF f;
    private Xfermode g = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    private float h;
    private float i;
    private int j = 300;
    private int k = 200;
    private int l = 150;
    private float m;
    public int mAnimCloseExpendColor = -4867650;
    public int mAnimOpenExpendColor = -14634568;
    public Context mContext;
    public int mNormalColor = -1;
    public float mRadius = 0.0f;
    public int mTextColor = -1;
    private float n;
    private String o = getResources().getString(R.string.noti_device_on);
    private String p = getResources().getString(R.string.noti_device_off);
    private AnimatorSet q;
    private int r;

    public GridItemAnimView(Context context) {
        super(context);
        this.mContext = context;
        a((AttributeSet) null);
    }

    public GridItemAnimView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        a(attributeSet);
    }

    public GridItemAnimView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.GridItemAnimView);
            this.mRadius = obtainStyledAttributes.getDimension(2, 0.0f);
            this.mAnimOpenExpendColor = obtainStyledAttributes.getColor(0, this.mAnimOpenExpendColor);
            this.mNormalColor = obtainStyledAttributes.getColor(1, this.mNormalColor);
            obtainStyledAttributes.recycle();
        }
        this.d.setDuration((long) this.j);
        this.d.setInterpolator(new LinearOutSlowInInterpolator());
        this.e.setDuration((long) this.l);
        this.q = new AnimatorSet();
        this.q.play(this.e).after(this.d).after((long) this.k);
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.c.setColor(this.mTextColor);
        this.r = DisplayUtils.a(this.mContext, 16.0f);
        this.c.setTextSize((float) this.r);
        this.f = new RectF();
    }

    public void setAnimatorExpendDuration(long j2) {
        this.d.setDuration(j2);
    }

    public int getAnimOpenExpendColor() {
        return this.mAnimOpenExpendColor;
    }

    public void setAnimOpenExpendColor(int i2) {
        this.mAnimOpenExpendColor = i2;
    }

    public int getAnimCloseExpendColor() {
        return this.mAnimCloseExpendColor;
    }

    public void setAnimCloseExpendColor(int i2) {
        this.mAnimCloseExpendColor = i2;
    }

    public int getNormalColor() {
        return this.mNormalColor;
    }

    public void setNormalColor(int i2) {
        this.mNormalColor = i2;
    }

    public void startAnimation(int i2, int i3, int i4) {
        startAnimation(i2, i3, i4, (Animator.AnimatorListener) null);
    }

    public int getAllAnimDuration() {
        return this.l + this.j + this.j;
    }

    public void startAnimation(int i2, int i3, int i4, Animator.AnimatorListener animatorListener) {
        this.f20866a = i4;
        this.m = (float) i2;
        this.n = (float) i3;
        if (animatorListener != null) {
            this.q.removeAllListeners();
            this.q.addListener(animatorListener);
        }
        this.q.start();
    }

    public int getState() {
        return this.f20866a;
    }

    public void setState(int i2) {
        this.f20866a = i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f.left = 0.0f;
        this.f.top = 0.0f;
        this.f.right = (float) getMeasuredWidth();
        this.f.bottom = (float) getMeasuredHeight();
        switch (this.f20866a) {
            case 3:
                a(canvas, true);
                return;
            case 4:
                a(canvas, false);
                return;
            default:
                return;
        }
    }

    private void a(Canvas canvas, boolean z) {
        int i2;
        int saveLayer = canvas.saveLayer((RectF) null, (Paint) null, 31);
        this.i = this.h / 100.0f;
        this.b.setColor(this.mNormalColor);
        canvas.save();
        this.b.setAlpha((int) (this.i * 255.0f));
        canvas.drawRoundRect(this.f, this.mRadius, this.mRadius, this.b);
        this.b.setXfermode(this.g);
        this.b.setColor(z ? this.mAnimOpenExpendColor : this.mAnimCloseExpendColor);
        int i3 = (int) ((this.i * 255.0f) + 20.0f);
        if (i3 >= 255) {
            i3 = 255;
        }
        this.b.setAlpha(i3);
        canvas.drawCircle(this.m, this.n, ((float) Math.sqrt(Math.pow((double) this.f.right, 2.0d) + Math.pow((double) this.f.bottom, 2.0d))) * this.i, this.b);
        if (((double) this.i) < 0.5d) {
            i2 = 0;
        } else {
            double d2 = (double) this.i;
            Double.isNaN(d2);
            i2 = (int) ((d2 - 0.5d) * 255.0d * 2.5d);
        }
        if (i2 >= 255) {
            i2 = 255;
        }
        this.c.setAlpha(i2);
        if (((double) this.i) > 0.5d) {
            this.c.setTextSize(((float) this.r) * this.i);
        }
        Paint.FontMetrics fontMetrics = this.c.getFontMetrics();
        canvas.drawText(z ? this.o : this.p, (float) DisplayUtils.b(this.mContext, 14.0f), (float) ((int) (((this.f.bottom / 2.0f) - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 2.0f))), this.c);
        canvas.restore();
        this.b.setXfermode((Xfermode) null);
        canvas.restoreToCount(saveLayer);
    }

    public void setProgress(float f2) {
        this.h = f2;
        invalidate();
    }

    public float getProgress() {
        return this.h;
    }
}
