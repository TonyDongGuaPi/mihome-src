package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;

public class MiBrainWaveView extends RelativeLayout {
    public static final int MIN_RECEIVE_DELAY_TIME = 600;

    /* renamed from: a  reason: collision with root package name */
    private static final String f10757a = "MiBrainWaveView";
    private static final float f = 60.0f;
    private static final float g = 50.0f;
    private static final float h = 1.5f;
    private static final float i = 15.0f;
    private static final int j = 1;
    private static final int k = 2;
    private static final int p = 1000;
    private CircleView b;
    private CircleView c;
    private RelativeLayout d;
    private ImageView e;
    private long l = 0;
    private double m = 0.0d;
    public int mDuration = 1000;
    private float n = 0.0f;
    private Handler o = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 2) {
                MiBrainWaveView.this.a().start();
            }
        }
    };
    private float q;
    private float r = 0.0f;
    private float s;
    private Interpolator t = new LinearInterpolator();
    private final Animator.AnimatorListener u = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }
    };

    public MiBrainWaveView(Context context) {
        super(context);
    }

    public MiBrainWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MiBrainWaveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (CircleView) findViewById(R.id.mi_brain_circle_view);
        this.d = (RelativeLayout) findViewById(R.id.mi_brain_wave_view_listening_rl);
        this.e = (ImageView) findViewById(R.id.mi_brain_wave_view_normal_iv);
        this.c = (CircleView) findViewById(R.id.mi_brain_circle_view_ring);
    }

    public void receiveVoice(double d2, IOnReceiveVoiceCallBack iOnReceiveVoiceCallBack) {
        this.q = 1.0f;
        Log.d(f10757a, "mScaleStart" + this.q);
        if (d2 > 50.0d) {
            if (this.m > d2) {
                d2 = this.m;
            }
            this.m = d2;
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.l > 600) {
                this.l = currentTimeMillis;
                this.r = b(this.m);
                if (iOnReceiveVoiceCallBack != null) {
                    iOnReceiveVoiceCallBack.a(this.s);
                }
                this.m = 0.0d;
                if (this.r != -1.0f) {
                    this.o.sendEmptyMessage(2);
                }
            }
        }
    }

    private float a(double d2) {
        if (this.b == null || this.b.getRadius() <= 0.0f) {
            return -1.0f;
        }
        float f2 = ((float) d2) / f;
        float radius = this.b.getRadius();
        float f3 = 2.0f;
        if (1.0f + f2 <= 2.0f) {
            f3 = f2;
        }
        float f4 = radius * f3;
        Log.d(f10757a, "getScaleRadius" + f4 + "----volumeDis" + f2);
        return f4;
    }

    private float b(double d2) {
        if (this.b == null || this.b.getRadius() <= 0.0f) {
            return -1.0f;
        }
        this.s = (((float) (d2 / 60.0d)) * ((float) getMeasuredHeight())) / 2.0f;
        float radius = this.s / this.b.getRadius();
        return radius > h ? radius : h;
    }

    public void setRadius(float f2) {
        if (this.b != null) {
            this.b.setRadius(f2);
        }
    }

    public int dip2px(float f2) {
        float f3 = getResources().getDisplayMetrics().density;
        Log.d(f10757a, Constants.y + getResources().getDisplayMetrics().density);
        return (int) ((f2 * f3) + 0.5f);
    }

    /* access modifiers changed from: private */
    public AnimatorSet a() {
        Log.d(f10757a, "createAnimators mScaleStart" + this.q + "----mScaleEnd" + this.r);
        ArrayList arrayList = new ArrayList();
        arrayList.add(a(this.b, "scaleX", 0, 0, this.q, this.r, true));
        arrayList.add(a(this.b, "scaleY", 0, 0, this.q, this.r, true));
        arrayList.add(a(this.b, "alpha", 0, 0, 1.0f, 1.0f, true));
        AnimatorSet animatorSet = new AnimatorSet();
        this.t = new DecelerateInterpolator(2.0f);
        animatorSet.playTogether(arrayList);
        animatorSet.setInterpolator(this.t);
        animatorSet.setDuration((long) this.mDuration);
        animatorSet.addListener(this.u);
        return animatorSet;
    }

    public float getRadiusEnd() {
        Log.d(f10757a, "getRadiusEnd" + this.s);
        return this.s;
    }

    private ObjectAnimator a(View view, String str, int i2, long j2, float f2, float f3, boolean z) {
        ObjectAnimator objectAnimator;
        if (z) {
            objectAnimator = ObjectAnimator.ofFloat(view, str, new float[]{f2, f3, f2});
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view, str, new float[]{f2, f3});
        }
        objectAnimator.setRepeatCount(i2);
        objectAnimator.setRepeatMode(1);
        objectAnimator.setStartDelay(j2);
        objectAnimator.setDuration(1000);
        return objectAnimator;
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null && this.b != null) {
            this.b.setOnClickListener(onClickListener);
        }
    }
}
