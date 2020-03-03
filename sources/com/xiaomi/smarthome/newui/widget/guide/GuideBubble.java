package com.xiaomi.smarthome.newui.widget.guide;

import android.graphics.PointF;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.xiaomi.smarthome.newui.widget.guide.drawable.BubbleDrawable;

public abstract class GuideBubble {

    /* renamed from: a  reason: collision with root package name */
    protected Animation.AnimationListener f20898a;
    protected Animation.AnimationListener b;

    public abstract void a();

    public abstract void a(ViewGroup viewGroup, PointF pointF, int i);

    public abstract void b();

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public PointF f() {
        return null;
    }

    /* access modifiers changed from: protected */
    public BubbleDrawable h() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int i() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int j() {
        return 0;
    }

    public boolean k() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean m() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean n() {
        return false;
    }

    /* access modifiers changed from: protected */
    public long q() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void a(Animation.AnimationListener animationListener) {
        this.f20898a = animationListener;
    }

    /* access modifiers changed from: protected */
    public Animation.AnimationListener o() {
        return this.f20898a;
    }

    /* access modifiers changed from: protected */
    public void b(Animation.AnimationListener animationListener) {
        this.b = animationListener;
    }

    /* access modifiers changed from: protected */
    public Animation.AnimationListener p() {
        return this.b;
    }
}
