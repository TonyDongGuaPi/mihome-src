package com.mics.widget.stickyball.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.mics.widget.stickyball.widget.StickyBallView;
import java.util.LinkedList;

public class SelectedStickyBall implements Animator.AnimatorListener, ISelectedView, StickyBallView.OnTranslationListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public StickyBallView f7847a;
    private DotIndicatorInfo b;
    private long c = 300;
    private float d;
    private AnimatorSet e;
    private ObjectAnimator f;
    private ObjectAnimator g;
    private ObjectAnimator h;
    private int i;
    private LinkedList<Integer> j;

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public SelectedStickyBall(Context context) {
        this.f7847a = new StickyBallView(context);
        this.f7847a.setOnTranslationListener(this);
        this.j = new LinkedList<>();
    }

    public void a(DotIndicatorInfo dotIndicatorInfo) {
        if (this.e != null && this.e.isRunning()) {
            this.e.cancel();
            this.e = null;
        }
        this.b = dotIndicatorInfo;
        PointF pointF = dotIndicatorInfo.i()[dotIndicatorInfo.j()];
        this.f7847a.setSourceXY(pointF.x - ((float) dotIndicatorInfo.e()), pointF.y - ((float) dotIndicatorInfo.f()));
        this.f7847a.setTargetXY(pointF.x - ((float) dotIndicatorInfo.e()), pointF.y - ((float) dotIndicatorInfo.f()));
        this.f7847a.setColor(dotIndicatorInfo.a());
        this.f7847a.setSourceRadius((float) dotIndicatorInfo.c());
        this.f7847a.setTargetRadius((float) dotIndicatorInfo.c());
        this.i = dotIndicatorInfo.j();
    }

    public void a(int i2) {
        if (this.i != i2) {
            this.j.offer(Integer.valueOf(i2));
            a();
        }
    }

    private void a() {
        if (this.e != null && this.e.isRunning()) {
            this.e.cancel();
        }
        Integer poll = this.j.poll();
        if (poll != null) {
            final boolean z = this.i < poll.intValue();
            final float b2 = b(poll.intValue());
            this.f = ObjectAnimator.ofFloat(this.f7847a, z ? "targetTranslationX" : "sourceTranslationX", new float[]{0.0f, b2});
            this.f.setInterpolator(new DecelerateInterpolator());
            this.f.setDuration(this.c);
            this.f.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                    if (z) {
                        SelectedStickyBall.this.f7847a.setTargetTranslationX(b2);
                        SelectedStickyBall.this.f7847a.updateTargetCache();
                        return;
                    }
                    SelectedStickyBall.this.f7847a.setSourceTranslationX(b2);
                    SelectedStickyBall.this.f7847a.updateSourceCache();
                }
            });
            final float b3 = b(poll.intValue());
            this.g = ObjectAnimator.ofFloat(this.f7847a, z ? "sourceTranslationX" : "targetTranslationX", new float[]{0.0f, b3});
            this.g.setInterpolator(Math.abs(b(poll.intValue())) > ((float) (this.b.b() * 2)) ? new OvershootInterpolator(0.8f) : new OvershootInterpolator(1.1f));
            this.g.setStartDelay((long) (((float) this.c) * 0.8f));
            this.g.setDuration(this.c);
            this.g.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                    if (z) {
                        SelectedStickyBall.this.f7847a.setSourceTranslationX(b3);
                        SelectedStickyBall.this.f7847a.updateSourceCache();
                        return;
                    }
                    SelectedStickyBall.this.f7847a.setTargetTranslationX(b3);
                    SelectedStickyBall.this.f7847a.updateTargetCache();
                }
            });
            this.h = ObjectAnimator.ofFloat(this.f7847a, z ? "sourceRadius" : "targetRadius", new float[]{(float) this.b.c(), 0.0f});
            this.h.setInterpolator(new DecelerateInterpolator());
            this.h.setDuration(this.c + ((long) (((float) this.c) * 0.8f)));
            this.e = new AnimatorSet();
            this.e.play(this.f).with(this.g).with(this.h);
            this.e.start();
            this.e.addListener(this);
            this.i = poll.intValue();
        }
    }

    private float b(int i2) {
        float f2 = this.b.i()[i2].x - this.b.i()[this.i].x;
        this.d = f2;
        return f2;
    }

    public View a(View view) {
        return this.f7847a;
    }

    public void onAnimationEnd(Animator animator) {
        this.f7847a.updateSourceCache();
        this.f7847a.updateTargetCache();
        this.f7847a.setSourceRadius((float) this.b.c());
        this.f7847a.setTargetRadius((float) this.b.c());
        this.e = null;
    }

    public void onAnimationCancel(Animator animator) {
        this.f7847a.setSourceRadius((float) this.b.c());
        this.f7847a.setTargetRadius((float) this.b.c());
        this.e = null;
    }

    public void a(float f2, float f3) {
        if (Math.abs(f2) >= Math.abs(this.d) || Math.abs(f3) >= Math.abs(this.d)) {
            this.f7847a.setSourceRadius((float) this.b.c());
        }
    }

    public void b(float f2, float f3) {
        if (Math.abs(f2) >= Math.abs(this.d) || Math.abs(f3) >= Math.abs(this.d)) {
            this.f7847a.setTargetRadius((float) this.b.c());
        }
    }
}
