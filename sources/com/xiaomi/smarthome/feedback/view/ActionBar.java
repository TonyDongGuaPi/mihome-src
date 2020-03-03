package com.xiaomi.smarthome.feedback.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.xiaomi.smarthome.R;

public class ActionBar {

    /* renamed from: a  reason: collision with root package name */
    private ViewGroup f15972a;
    private ViewGroup b;
    private View c;
    private View d;
    /* access modifiers changed from: private */
    public OnShowListener e;
    /* access modifiers changed from: private */
    public OnHideListener f;
    private Animation g;
    private Animation h;
    private Animation i;
    private Animation j;
    /* access modifiers changed from: private */
    public boolean k = true;

    public interface OnHideListener {
        void a();

        void b();
    }

    public interface OnShowListener {
        void a();

        void b();
    }

    public ActionBar(Context context, ViewGroup viewGroup, ViewGroup viewGroup2) {
        this.f15972a = viewGroup;
        this.b = viewGroup2;
        this.g = AnimationUtils.loadAnimation(context, R.anim.actionbar_top_show);
        this.h = AnimationUtils.loadAnimation(context, R.anim.actionbar_top_hide);
        this.i = AnimationUtils.loadAnimation(context, R.anim.actionbar_bottom_show);
        this.j = AnimationUtils.loadAnimation(context, R.anim.actionbar_bottom_hide);
        this.g.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.e.a();
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.e.b();
                }
            }
        });
        this.h.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.f.a();
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.f.b();
                }
            }
        });
        this.i.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (!ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.e.a();
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (!ActionBar.this.k && ActionBar.this.e != null) {
                    ActionBar.this.e.b();
                }
            }
        });
        this.j.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (!ActionBar.this.k && ActionBar.this.f != null) {
                    ActionBar.this.f.a();
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (!ActionBar.this.k && ActionBar.this.f != null) {
                    ActionBar.this.f.b();
                }
            }
        });
    }

    public void a(View view, View view2) {
        if (!(this.f15972a == null || view == null)) {
            this.f15972a.addView(view);
            this.c = view;
            this.c.setVisibility(8);
        }
        if (this.b != null && view2 != null) {
            this.d = view2;
            this.d.setVisibility(8);
            this.b.addView(view2);
        }
    }

    public void a() {
        if (!(this.f15972a == null || this.c == null)) {
            this.f15972a.removeView(this.c);
            this.c.setVisibility(8);
        }
        if (!(this.b == null || this.d == null)) {
            this.d.setVisibility(8);
            this.b.removeView(this.d);
        }
        this.e = null;
        this.f = null;
    }

    public void b() {
        if (this.c != null) {
            this.k = true;
            this.c.startAnimation(this.g);
            this.c.setVisibility(0);
        }
    }

    public void c() {
        if (this.c != null) {
            this.k = true;
            this.c.startAnimation(this.h);
        }
    }

    public void d() {
        if (this.d != null) {
            this.k = false;
            this.d.startAnimation(this.i);
            this.d.setVisibility(0);
        }
    }

    public void e() {
        if (this.d != null) {
            this.d.startAnimation(this.j);
            this.d.setVisibility(0);
        }
    }

    public void f() {
        if (this.c != null) {
            this.k = true;
            this.c.startAnimation(this.g);
            this.c.setVisibility(0);
        }
        if (this.d != null) {
            if (this.c == null) {
                this.k = false;
            }
            this.d.startAnimation(this.i);
            this.d.setVisibility(0);
        }
    }

    public void g() {
        c();
        e();
    }

    public void a(OnShowListener onShowListener) {
        this.e = onShowListener;
    }

    public void a(OnHideListener onHideListener) {
        this.f = onHideListener;
    }
}
