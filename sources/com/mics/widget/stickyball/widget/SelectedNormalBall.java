package com.mics.widget.stickyball.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.mics.widget.stickyball.widget.StickyBallView;
import java.util.LinkedList;

public class SelectedNormalBall implements Animator.AnimatorListener, ISelectedView, StickyBallView.OnTranslationListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public StickyBallView f7844a;
    private DotIndicatorInfo b;
    private long c = 300;
    private float d;
    private AnimatorSet e;
    private ObjectAnimator f;
    private ObjectAnimator g;
    private int h;
    private LinkedList<Integer> i;

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public SelectedNormalBall(Context context) {
        this.f7844a = new StickyBallView(context);
        this.f7844a.setOnTranslationListener(this);
        this.i = new LinkedList<>();
    }

    public void a(DotIndicatorInfo dotIndicatorInfo) {
        if (this.e != null && this.e.isRunning()) {
            this.e.cancel();
            this.e = null;
        }
        this.b = dotIndicatorInfo;
        PointF pointF = dotIndicatorInfo.i()[dotIndicatorInfo.j()];
        this.f7844a.setSourceXY(pointF.x - ((float) dotIndicatorInfo.e()), pointF.y - ((float) dotIndicatorInfo.f()));
        this.f7844a.setTargetXY(pointF.x - ((float) dotIndicatorInfo.e()), pointF.y - ((float) dotIndicatorInfo.f()));
        this.f7844a.setColor(dotIndicatorInfo.a());
        this.f7844a.setSourceRadius((float) dotIndicatorInfo.c());
        this.f7844a.setTargetRadius((float) dotIndicatorInfo.c());
        this.h = dotIndicatorInfo.j();
    }

    public void a(int i2) {
        if (this.h != i2) {
            this.i.offer(Integer.valueOf(i2));
            a();
        }
    }

    private void a() {
        if (this.e != null && this.e.isRunning()) {
            this.e.cancel();
        }
        Integer poll = this.i.poll();
        if (poll != null) {
            final boolean z = this.h < poll.intValue();
            final float b2 = b(poll.intValue());
            this.f = ObjectAnimator.ofFloat(this.f7844a, z ? "targetTranslationX" : "sourceTranslationX", new float[]{0.0f, b2});
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
                        SelectedNormalBall.this.f7844a.setTargetTranslationX(b2);
                        SelectedNormalBall.this.f7844a.updateTargetCache();
                        return;
                    }
                    SelectedNormalBall.this.f7844a.setSourceTranslationX(b2);
                    SelectedNormalBall.this.f7844a.updateSourceCache();
                }
            });
            final float b3 = b(poll.intValue());
            this.g = ObjectAnimator.ofFloat(this.f7844a, z ? "sourceTranslationX" : "targetTranslationX", new float[]{0.0f, b3});
            this.f.setInterpolator(new DecelerateInterpolator());
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
                        SelectedNormalBall.this.f7844a.setSourceTranslationX(b3);
                        SelectedNormalBall.this.f7844a.updateSourceCache();
                        return;
                    }
                    SelectedNormalBall.this.f7844a.setTargetTranslationX(b3);
                    SelectedNormalBall.this.f7844a.updateTargetCache();
                }
            });
            this.e = new AnimatorSet();
            this.e.play(this.f).with(this.g);
            this.e.start();
            this.e.addListener(this);
            this.h = poll.intValue();
        }
    }

    private float b(int i2) {
        float f2 = this.b.i()[i2].x - this.b.i()[this.h].x;
        this.d = f2;
        return f2;
    }

    public View a(View view) {
        return this.f7844a;
    }

    public void onAnimationEnd(Animator animator) {
        this.f7844a.updateSourceCache();
        this.f7844a.updateTargetCache();
        this.f7844a.setSourceRadius((float) this.b.c());
        this.f7844a.setTargetRadius((float) this.b.c());
        this.e = null;
    }

    public void onAnimationCancel(Animator animator) {
        this.f7844a.setSourceRadius((float) this.b.c());
        this.f7844a.setTargetRadius((float) this.b.c());
        this.e = null;
    }

    public void a(float f2, float f3) {
        if (Math.abs(f2) >= Math.abs(this.d) || Math.abs(f3) >= Math.abs(this.d)) {
            this.f7844a.setSourceRadius((float) this.b.c());
        }
    }

    public void b(float f2, float f3) {
        if (Math.abs(f2) >= Math.abs(this.d) || Math.abs(f3) >= Math.abs(this.d)) {
            this.f7844a.setTargetRadius((float) this.b.c());
        }
    }
}
