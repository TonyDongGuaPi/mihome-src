package com.xiaomi.smarthome.newui.widget.topnavi.indicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class FragmentContainerHelper {

    /* renamed from: a  reason: collision with root package name */
    private List<MagicIndicator> f20920a = new ArrayList();
    /* access modifiers changed from: private */
    public ValueAnimator b;
    private int c;
    private int d = 150;
    private Interpolator e = new AccelerateDecelerateInterpolator();
    private Animator.AnimatorListener f = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            FragmentContainerHelper.this.d(0);
            ValueAnimator unused = FragmentContainerHelper.this.b = null;
        }
    };
    private ValueAnimator.AnimatorUpdateListener g = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) floatValue;
            float f = floatValue - ((float) i);
            if (floatValue < 0.0f) {
                i--;
                f += 1.0f;
            }
            FragmentContainerHelper.this.a(i, f, 0);
        }
    };

    public FragmentContainerHelper() {
    }

    public FragmentContainerHelper(MagicIndicator magicIndicator) {
        this.f20920a.add(magicIndicator);
    }

    public static PositionData a(List<PositionData> list, int i) {
        PositionData positionData;
        if (i >= 0 && i <= list.size() - 1) {
            return list.get(i);
        }
        PositionData positionData2 = new PositionData();
        if (i < 0) {
            positionData = list.get(0);
        } else {
            i = (i - list.size()) + 1;
            positionData = list.get(list.size() - 1);
        }
        positionData2.f20936a = positionData.f20936a + (positionData.a() * i);
        positionData2.b = positionData.b;
        positionData2.c = positionData.c + (positionData.a() * i);
        positionData2.d = positionData.d;
        positionData2.e = positionData.e + (positionData.a() * i);
        positionData2.f = positionData.f;
        positionData2.g = positionData.g + (i * positionData.a());
        positionData2.h = positionData.h;
        return positionData2;
    }

    public void a(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        if (this.c != i) {
            if (z) {
                if (this.b == null || !this.b.isRunning()) {
                    d(2);
                }
                c(i);
                float f2 = (float) this.c;
                if (this.b != null) {
                    f2 = ((Float) this.b.getAnimatedValue()).floatValue();
                    this.b.cancel();
                    this.b = null;
                }
                this.b = new ValueAnimator();
                this.b.setFloatValues(new float[]{f2, (float) i});
                this.b.addUpdateListener(this.g);
                this.b.addListener(this.f);
                this.b.setInterpolator(this.e);
                this.b.setDuration((long) this.d);
                this.b.start();
            } else {
                c(i);
                if (this.b != null && this.b.isRunning()) {
                    a(this.c, 0.0f, 0);
                }
                d(0);
                a(i, 0.0f, 0);
            }
            this.c = i;
        }
    }

    public void b(int i) {
        this.d = i;
    }

    public void a(Interpolator interpolator) {
        if (interpolator == null) {
            this.e = new AccelerateDecelerateInterpolator();
        } else {
            this.e = interpolator;
        }
    }

    public void a(MagicIndicator magicIndicator) {
        this.f20920a.add(magicIndicator);
    }

    private void c(int i) {
        for (MagicIndicator onPageSelected : this.f20920a) {
            onPageSelected.onPageSelected(i);
        }
    }

    /* access modifiers changed from: private */
    public void d(int i) {
        for (MagicIndicator onPageScrollStateChanged : this.f20920a) {
            onPageScrollStateChanged.onPageScrollStateChanged(i);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, float f2, int i2) {
        for (MagicIndicator onPageScrolled : this.f20920a) {
            onPageScrolled.onPageScrolled(i, f2, i2);
        }
    }
}
