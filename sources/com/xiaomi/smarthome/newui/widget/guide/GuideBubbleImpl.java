package com.xiaomi.smarthome.newui.widget.guide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.guide.drawable.BubbleDrawable;

public class GuideBubbleImpl extends GuideBubble implements Animation.AnimationListener {
    private Activity c;
    private ViewGroup d;
    private View e;
    private BubbleDrawable f;
    private TextView g;
    private TextView h;
    private TextView i;
    private String j;
    private String k;
    private String l;
    private PointF m;
    private PointF n;
    private Animation o;
    private Animation p;
    private boolean q = true;
    private boolean r = true;
    private int s;

    /* access modifiers changed from: protected */
    public long e() {
        return 200;
    }

    public GuideBubbleImpl(Activity activity, String str, String str2, String str3) {
        this.c = activity;
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.o = new AlphaAnimation(0.0f, 1.0f);
        this.o.setAnimationListener(this);
        this.o.setDuration(400);
        this.p = new AlphaAnimation(1.0f, 0.0f);
        this.p.setAnimationListener(this);
        this.p.setDuration(200);
    }

    public void a(ViewGroup viewGroup, PointF pointF, int i2) {
        this.n = pointF;
        this.d = viewGroup;
        this.e = LayoutInflater.from(this.c).inflate(R.layout.guide_bubble, (ViewGroup) null);
        this.d.addView(this.e, new ViewGroup.LayoutParams(-2, -2));
        this.s = a(pointF, i2);
        a(this.e);
        a(i2);
        this.e.setVisibility(4);
    }

    private int a(PointF pointF, int i2) {
        Point a2 = DisplayUtils.a(this.c);
        float f2 = pointF.x > ((float) (a2.x / 2)) ? ((float) a2.x) - pointF.x : pointF.x;
        float a3 = (float) DisplayUtils.a((Context) this.c, 10.0f);
        if (i2 == 80 || i2 == 48) {
            return (int) ((f2 * 2.0f) - (a3 * 2.0f));
        }
        if (i2 == 85) {
            return (int) Math.min(((float) a2.x) - (2.0f * a3), (f2 - a3) * 4.0f);
        }
        return (int) ((((float) a2.x) - pointF.x) - a3);
    }

    private void a(View view) {
        this.g = (TextView) view.findViewById(R.id.tv_guide_bubble_title);
        this.h = (TextView) view.findViewById(R.id.tv_guide_bubble_title_sub);
        this.i = (TextView) view.findViewById(R.id.tv_guide_bubble_title_sub_2);
        if (TextUtils.isEmpty(this.j)) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(this.j);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.h.setVisibility(8);
        } else {
            this.h.setText(this.k);
        }
        if (TextUtils.isEmpty(this.l)) {
            this.i.setVisibility(8);
        } else {
            this.i.setText(this.l);
        }
        this.g.setMaxWidth(this.s);
        this.h.setMaxWidth(this.s);
        this.i.setMaxWidth(this.s);
    }

    private void a(int i2) {
        this.f = new BubbleDrawable(this.c);
        this.f.a(i2);
        a(this.e, i2, this.f.c());
        this.e.setBackgroundDrawable(this.f);
    }

    private void a(View view, int i2, int i3) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        if (i2 == 3) {
            view.setPadding(paddingLeft + i3, paddingTop, paddingRight, paddingBottom);
        } else if (i2 == 5) {
            view.setPadding(paddingLeft, paddingTop, paddingRight + i3, paddingBottom);
        } else if (i2 == 48) {
            view.setPadding(paddingLeft, paddingTop + i3, paddingRight, paddingBottom);
        } else if (i2 == 80 || i2 == 85) {
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + i3);
        }
    }

    public void a() {
        g();
        this.e.setVisibility(0);
        this.d.setVisibility(0);
        this.d.startAnimation(this.o);
        this.f.setAlpha(178);
    }

    public void b() {
        this.e.setVisibility(4);
        this.d.setVisibility(4);
        this.d.startAnimation(this.p);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.q;
    }

    /* access modifiers changed from: protected */
    public PointF f() {
        if (this.m == null) {
            this.m = new PointF();
        }
        PointF a2 = this.f.a();
        this.m.set(this.n.x - a2.x, this.n.y - a2.y);
        return this.m;
    }

    public void g() {
        PointF f2 = f();
        this.e.setTranslationX(f2.x);
        this.e.setTranslationY(f2.y);
    }

    public BubbleDrawable h() {
        this.f.setBounds(0, 0, i(), j());
        return this.f;
    }

    /* access modifiers changed from: protected */
    public int i() {
        return this.e.getWidth();
    }

    /* access modifiers changed from: protected */
    public int j() {
        return this.e.getHeight();
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.p) {
            this.r = false;
            if (o() != null) {
                o().onAnimationStart(animation);
            }
        } else if (animation == this.o) {
            this.q = false;
            if (p() != null) {
                p().onAnimationStart(animation);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.p) {
            this.r = true;
            if (o() != null) {
                o().onAnimationEnd(animation);
            }
            a((Animation.AnimationListener) null);
        } else if (animation == this.o) {
            this.q = true;
            if (p() != null) {
                p().onAnimationEnd(animation);
            }
            b((Animation.AnimationListener) null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
        if (o() != null) {
            o().onAnimationRepeat(animation);
        }
        if (p() != null) {
            p().onAnimationRepeat(animation);
        }
    }
}
