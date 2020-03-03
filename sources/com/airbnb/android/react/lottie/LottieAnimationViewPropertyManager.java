package com.airbnb.android.react.lottie;

import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.ref.WeakReference;

public class LottieAnimationViewPropertyManager {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<LottieAnimationView> f712a;
    private String b;
    private Float c;
    private Boolean d;
    private Float e;
    private boolean f;
    private String g;
    private Boolean h;
    private ImageView.ScaleType i;
    private String j;
    private Boolean k;

    public LottieAnimationViewPropertyManager(LottieAnimationView lottieAnimationView) {
        this.f712a = new WeakReference<>(lottieAnimationView);
    }

    public void a(String str) {
        this.g = str;
        this.f = true;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(Float f2) {
        this.c = f2;
    }

    public void a(float f2) {
        this.e = Float.valueOf(f2);
    }

    public void a(boolean z) {
        this.d = Boolean.valueOf(z);
    }

    public void b(boolean z) {
        this.h = Boolean.valueOf(z);
    }

    public void a(ImageView.ScaleType scaleType) {
        this.i = scaleType;
    }

    public void c(String str) {
        this.j = str;
    }

    public void c(boolean z) {
        this.k = Boolean.valueOf(z);
    }

    public void a() {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.f712a.get();
        if (lottieAnimationView != null) {
            if (this.b != null) {
                lottieAnimationView.setAnimationFromJson(this.b, Integer.toString(this.b.hashCode()));
                this.b = null;
            }
            if (this.f) {
                lottieAnimationView.setAnimation(this.g);
                this.f = false;
            }
            if (this.c != null) {
                lottieAnimationView.setProgress(this.c.floatValue());
                this.c = null;
            }
            if (this.d != null) {
                lottieAnimationView.loop(this.d.booleanValue());
                this.d = null;
            }
            if (this.e != null) {
                lottieAnimationView.setSpeed(this.e.floatValue());
                this.e = null;
            }
            if (this.h != null) {
                lottieAnimationView.useHardwareAcceleration(this.h.booleanValue());
                this.h = null;
            }
            if (this.i != null) {
                lottieAnimationView.setScaleType(this.i);
                this.i = null;
            }
            if (this.j != null) {
                lottieAnimationView.setImageAssetsFolder(this.j);
                this.j = null;
            }
            if (this.k != null) {
                lottieAnimationView.enableMergePathsForKitKatAndAbove(this.k.booleanValue());
                this.k = null;
            }
        }
    }
}
