package com.xiaomi.jr.base.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.jr.base.R;

public class HeaderLoadingLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10334a = 500;
    private static final int b = 400;
    private ImageView c;
    private ImageView d;
    private Animation e;
    private Animation f;
    private Animation g;
    private View h;
    private State i = State.NONE;
    private State j = State.NONE;
    private int k;

    public enum State {
        NONE,
        RESET,
        PULL_TO_REFRESH,
        RELEASE_TO_REFRESH,
        REFRESHING,
        LOADING
    }

    public HeaderLoadingLayout(Context context) {
        super(context);
        a(context);
    }

    public HeaderLoadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.h = b(context);
        if (this.h != null) {
            this.k = getResources().getDimensionPixelSize(R.dimen.pull_to_refresh_header_height);
            addView(this.h, new FrameLayout.LayoutParams(-1, this.k));
            this.e = a(0.0f, 180.0f);
            this.f = a(-180.0f, 0.0f);
            this.g = a();
            return;
        }
        throw new NullPointerException("Loading view can not be null.");
    }

    private Animation a(float f2, float f3) {
        RotateAnimation rotateAnimation = new RotateAnimation(f2, f3, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }

    private Animation a() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(400);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        return rotateAnimation;
    }

    public void setState(State state) {
        if (this.i != state) {
            this.j = this.i;
            this.i = state;
            a(state, this.j);
        }
    }

    public State getState() {
        return this.i;
    }

    private State getPreState() {
        return this.j;
    }

    public int getContentSize() {
        if (this.h != null) {
            return this.k;
        }
        return 0;
    }

    private View b(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, (ViewGroup) null);
        this.c = (ImageView) inflate.findViewById(R.id.arrow_imageview);
        this.d = (ImageView) inflate.findViewById(R.id.loading_imageview);
        return inflate;
    }

    private void a(State state, State state2) {
        switch (state) {
            case RESET:
                b();
                return;
            case RELEASE_TO_REFRESH:
                d();
                return;
            case PULL_TO_REFRESH:
                c();
                return;
            case REFRESHING:
                e();
                return;
            default:
                return;
        }
    }

    private void b() {
        this.c.clearAnimation();
        this.d.clearAnimation();
        a(true);
    }

    private void c() {
        this.c.clearAnimation();
        a(true);
        if (getPreState() == State.RELEASE_TO_REFRESH) {
            this.c.startAnimation(this.f);
        }
    }

    private void d() {
        this.c.clearAnimation();
        this.c.startAnimation(this.e);
    }

    private void e() {
        this.c.clearAnimation();
        this.d.clearAnimation();
        a(false);
        this.d.startAnimation(this.g);
    }

    private void a(boolean z) {
        int i2 = 8;
        this.c.setVisibility(z ? 0 : 8);
        ImageView imageView = this.d;
        if (!z) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
    }
}
