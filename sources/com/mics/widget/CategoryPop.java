package com.mics.widget;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mics.R;
import com.mics.util.KeyboardUtils;

public class CategoryPop {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7789a = 1;
    public static final int b = -1;
    private Rect c = new Rect();
    private int d;
    private int e;
    private LayoutParamsWrapper f;
    private Interpolator g;
    private ObjectAnimator h;
    private Builder i;
    /* access modifiers changed from: private */
    public Activity j;
    private LayoutInflater k;
    /* access modifiers changed from: private */
    public PopupWindow.OnDismissListener l;
    private long m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public ViewGroup p;
    /* access modifiers changed from: private */
    public ViewGroup q;
    private RelativeLayout r;
    /* access modifiers changed from: private */
    public RelativeLayout s;
    /* access modifiers changed from: private */
    public View t;
    private int u;
    private ViewTreeObserver.OnGlobalLayoutListener v = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            CategoryPop.this.s.post(new Runnable() {
                public void run() {
                    CategoryPop.this.j.runOnUiThread(new Runnable() {
                        public void run() {
                            CategoryPop.this.h();
                        }
                    });
                }
            });
        }
    };

    public static abstract class Adapter {

        /* renamed from: a  reason: collision with root package name */
        private Object f7800a;

        public interface IView {
            void a();
        }

        public interface OnItemClickListener {
            void a(int i, View view);
        }

        /* access modifiers changed from: protected */
        public abstract int a();

        /* access modifiers changed from: protected */
        public abstract String a(int i);

        /* access modifiers changed from: protected */
        public abstract void a(int i, View view);

        /* access modifiers changed from: protected */
        public abstract String b(int i);

        /* access modifiers changed from: protected */
        public void a(Object obj) {
            this.f7800a = obj;
        }

        public void b() {
            if (this.f7800a != null && (this.f7800a instanceof IView)) {
                ((IView) this.f7800a).a();
            }
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private PopupWindow.OnDismissListener f7801a;
        private LayoutInflater b;
        private Activity c;
        private View d;
        private Adapter e;
        private String f;
        private long g = -1;
        private int h;
        private boolean i;
        private int j;

        public Builder(Activity activity) {
            a(activity);
        }

        public PopupWindow.OnDismissListener a() {
            return this.f7801a;
        }

        public Builder a(PopupWindow.OnDismissListener onDismissListener) {
            this.f7801a = onDismissListener;
            return this;
        }

        public LayoutInflater b() {
            return this.b == null ? LayoutInflater.from(this.c) : this.b;
        }

        public Builder a(LayoutInflater layoutInflater) {
            this.b = layoutInflater;
            return this;
        }

        public Activity c() {
            return this.c;
        }

        private Builder a(Activity activity) {
            this.c = activity;
            return this;
        }

        public View d() {
            if (this.d != null) {
                return this.d;
            }
            CategoryPopView categoryPopView = new CategoryPopView(this.c);
            categoryPopView.a(this.e);
            return categoryPopView;
        }

        public Builder a(int i2) {
            this.d = b().inflate(i2, (ViewGroup) null);
            return this;
        }

        public Adapter e() {
            return this.e;
        }

        public Builder a(Adapter adapter) {
            this.e = adapter;
            return this;
        }

        public String f() {
            return this.f == null ? "" : this.f;
        }

        public Builder a(String str) {
            this.f = str;
            return this;
        }

        public long g() {
            return this.g;
        }

        public Builder a(long j2) {
            this.g = j2;
            return this;
        }

        public int h() {
            return this.h;
        }

        public Builder b(int i2) {
            this.h = i2;
            return this;
        }

        public boolean i() {
            return this.i;
        }

        public Builder a(boolean z) {
            this.i = z;
            return this;
        }

        public Builder c(int i2) {
            this.j = i2;
            return this;
        }

        public int j() {
            return this.j;
        }

        public CategoryPop k() {
            return new CategoryPop(this);
        }
    }

    CategoryPop(Builder builder) {
        this.i = builder;
        d();
    }

    private void d() {
        this.j = this.i.c();
        this.l = this.i.a();
        this.k = this.i.b();
        if (this.k == null) {
            this.k = LayoutInflater.from(this.j);
        }
        this.m = this.i.g();
        this.m = this.m < 0 ? 200 : this.m;
        this.n = this.i.i();
        this.p = (ViewGroup) this.j.getWindow().getDecorView().findViewById(16908290);
        this.q = (ViewGroup) this.k.inflate(R.layout.mics_pop_service_category, this.p, false);
        this.r = (RelativeLayout) this.q.findViewById(R.id.rl_pop_container);
        this.t = this.q.findViewById(R.id.v_background);
        this.s = (RelativeLayout) this.q.findViewById(R.id.rl_pop_holder);
        this.p.setClipChildren(true);
        this.q.setClipChildren(true);
        this.r.setClipChildren(true);
        this.s.setClipChildren(true);
        int i2 = this.i.j() == 1 ? R.drawable.mics_icon_pop_bg : R.drawable.mics_icon_pop_bg_mirror;
        int applyDimension = (int) TypedValue.applyDimension(1, 16.0f, this.j.getResources().getDisplayMetrics());
        int i3 = this.i.j() == 1 ? 0 : applyDimension;
        if (this.i.j() != 1) {
            applyDimension = 0;
        }
        if (this.i.j() == -1) {
            this.s.setGravity(80);
            ((RelativeLayout.LayoutParams) this.s.getLayoutParams()).addRule(12);
        } else if (this.i.j() == 1) {
            this.s.setGravity(48);
            ((RelativeLayout.LayoutParams) this.s.getLayoutParams()).addRule(10);
        }
        this.s.setBackgroundResource(i2);
        this.s.setPadding(0, i3, 0, applyDimension);
        if (this.i.j() == -1) {
            e();
        }
        TextView textView = (TextView) this.q.findViewById(R.id.tv_pop_title);
        textView.setText(this.i.f());
        ((ImageView) this.q.findViewById(R.id.iv_pop_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategoryPop.this.c();
            }
        });
        View d2 = this.i.d();
        if (d2 != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(3, textView.getId());
            this.s.addView(d2, layoutParams);
        }
        this.s.setPadding(this.s.getPaddingLeft(), this.i.h() == 0 ? this.s.getPaddingTop() : this.i.h(), this.s.getPaddingRight(), this.s.getPaddingBottom());
        this.s.setVisibility(8);
        this.t.setVisibility(8);
        this.t.setSoundEffectsEnabled(false);
    }

    public View a() {
        return this.q;
    }

    private void e() {
        this.p.getViewTreeObserver().addOnGlobalLayoutListener(this.v);
    }

    public void b() {
        if (!f()) {
            a((View) this.q);
            int i2 = 1;
            if (this.i.j() == 1) {
                i2 = -1;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, (float) i2, 1, 0.0f);
            translateAnimation.setDuration(this.m);
            this.s.setVisibility(0);
            this.s.startAnimation(translateAnimation);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(this.m);
            this.t.startAnimation(alphaAnimation);
            this.t.setVisibility(0);
        }
    }

    public void c() {
        if (!this.o) {
            int i2 = 1;
            this.o = true;
            if (this.i.j() == 1) {
                i2 = -1;
            }
            final TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, (float) i2);
            translateAnimation.setDuration(this.m);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    CategoryPop.this.p.post(new Runnable() {
                        public void run() {
                            CategoryPop.this.p.removeView(CategoryPop.this.q);
                            boolean unused = CategoryPop.this.o = false;
                            if (CategoryPop.this.l != null) {
                                CategoryPop.this.l.onDismiss();
                            }
                        }
                    });
                }
            });
            final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(this.m);
            if (!i()) {
                KeyboardUtils.a((View) this.s);
                a(0);
                this.s.postDelayed(new Runnable() {
                    public void run() {
                        CategoryPop.this.j.runOnUiThread(new Runnable() {
                            public void run() {
                                CategoryPop.this.s.setVisibility(8);
                                CategoryPop.this.s.startAnimation(translateAnimation);
                                CategoryPop.this.t.setVisibility(8);
                                CategoryPop.this.t.startAnimation(alphaAnimation);
                            }
                        });
                    }
                }, 200);
                return;
            }
            this.s.setVisibility(8);
            this.s.startAnimation(translateAnimation);
            this.t.setVisibility(8);
            this.t.startAnimation(alphaAnimation);
        }
    }

    private boolean f() {
        return this.p.findViewById(R.id.v_background) != null;
    }

    private void a(View view) {
        this.p.addView(view);
        g();
        this.r.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1 || i != 4) {
                    return false;
                }
                if (CategoryPop.this.n) {
                    CategoryPop.this.c();
                }
                return true;
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CategoryPop.this.n) {
                    CategoryPop.this.c();
                }
            }
        });
    }

    private void g() {
        this.r.setFocusable(true);
        this.r.setFocusableInTouchMode(true);
        this.r.requestFocus();
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.u == 0) {
            this.u = this.s.getHeight();
        }
        this.p.getWindowVisibleDisplayFrame(this.c);
        int i2 = this.c.bottom;
        if (this.d == 0) {
            this.d = this.p.getHeight();
            return;
        }
        this.d = this.p.getHeight();
        int i3 = this.d - i2;
        if (i3 != this.e) {
            if (f()) {
                a(i3);
            }
            if (i3 == 0) {
                g();
            }
            this.e = i3;
        }
        if (i3 > 0 && i3 < this.u) {
            ((RelativeLayout.LayoutParams) this.s.getLayoutParams()).topMargin = i3 - this.u;
        }
    }

    private void a(int i2) {
        if (this.f == null) {
            this.f = new LayoutParamsWrapper(this.s);
            this.g = new DecelerateInterpolator();
        }
        int i3 = this.f.c.bottomMargin;
        if (i2 != i3) {
            this.h = ObjectAnimator.ofInt(this.f, "marginBottom", new int[]{i3, i2});
            this.h.setDuration(200);
            this.h.setInterpolator(this.g);
            this.h.start();
        }
    }

    private class LayoutParamsWrapper {
        private View b;
        /* access modifiers changed from: private */
        public ViewGroup.MarginLayoutParams c;

        LayoutParamsWrapper(View view) {
            this.b = view;
            this.c = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        }

        @SuppressLint({"AnimatorKeep"})
        public void a(int i) {
            this.c.bottomMargin = i;
            this.b.setLayoutParams(this.c);
        }
    }

    private boolean i() {
        return ((ViewGroup.MarginLayoutParams) this.s.getLayoutParams()).bottomMargin == 0;
    }
}
