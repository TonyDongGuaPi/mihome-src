package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class CountDownBgFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f20831a = false;
    private Context b;
    private ImageView c;
    private FrameLayout d;
    private boolean e = false;
    private final float f = 0.0f;
    private final float g = 0.5f;
    private final int h = 2000;
    private float i = 0.0f;
    private boolean j = true;
    private long k = 0;

    public CountDownBgFrameLayout(Context context) {
        super(context);
        a(context);
    }

    public CountDownBgFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CountDownBgFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.main_background, this);
        this.c = (ImageView) findViewById(R.id.iv_background);
        this.c.setVisibility(8);
        this.d = (FrameLayout) findViewById(R.id.view_ribbon_parent);
        setWillNotDraw(false);
        setPower(true);
    }

    public void setPower(boolean z) {
        if (z) {
            setBackgroundResource(R.drawable.countdown_blue_back);
        } else {
            setBackgroundResource(R.drawable.countdown_black_back);
        }
        postInvalidate();
    }

    public void stopBreathingEffect() {
        this.e = false;
        this.k = 0;
        this.i = 0.0f;
        setWillNotDraw(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.e) {
            this.d.setAlpha(getBreathingFloat());
            postInvalidate();
        } else {
            this.d.setAlpha(0.0f);
        }
        super.onDraw(canvas);
    }

    private float getBreathingFloat() {
        if (this.k == 0) {
            this.k = SystemClock.elapsedRealtime();
            return 0.0f;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.j) {
            if (this.i < 0.5f) {
                this.i += ((float) (elapsedRealtime - this.k)) * 5.0E-4f;
                this.k = elapsedRealtime;
                return this.i;
            }
            this.j = false;
            this.i = 0.5f;
            this.k = elapsedRealtime;
            return this.i;
        } else if (this.i > 0.0f) {
            this.i -= ((float) (elapsedRealtime - this.k)) * 5.0E-4f;
            if (this.i < 0.0f) {
                this.i = 0.0f;
            }
            this.k = elapsedRealtime;
            return this.i;
        } else {
            this.j = true;
            this.i = 0.0f;
            this.k = elapsedRealtime;
            return this.i;
        }
    }
}
