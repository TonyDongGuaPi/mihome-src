package com.xiaomi.smarthome.newui.widget.guide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.newui.widget.guide.drawable.BlurBackgroundDrawable;
import java.util.ArrayList;
import java.util.List;

public class GuideBubbleManager implements View.OnClickListener, Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    private Activity f20899a;
    /* access modifiers changed from: private */
    public ViewGroup b;
    /* access modifiers changed from: private */
    public FrameLayout c;
    /* access modifiers changed from: private */
    public ViewGroup.LayoutParams d;
    private Bitmap e;
    private BlurBackgroundDrawable f;
    private View g;
    private GuideNextStepCallback h;
    /* access modifiers changed from: private */
    public boolean i = true;
    /* access modifiers changed from: private */
    public List<GuideBubble> j;
    private SparseArray<List<GuideExtraView>> k;
    /* access modifiers changed from: private */
    public GuideBubble l;
    /* access modifiers changed from: private */
    public int m = 0;
    private boolean n = false;

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    public void a(boolean z) {
        this.i = z;
    }

    public int a() {
        return this.j.size();
    }

    public GuideBubbleManager(Activity activity) {
        this.f20899a = activity;
        e();
    }

    private void e() {
        this.b = (ViewGroup) this.f20899a.getWindow().getDecorView().findViewById(16908290);
        this.c = new FrameLayout(this.f20899a);
        if (this.b instanceof FrameLayout) {
            this.d = new FrameLayout.LayoutParams(-1, -1);
        } else if (this.b instanceof LinearLayout) {
            this.d = new LinearLayout.LayoutParams(-1, -1);
        } else if (this.b instanceof RelativeLayout) {
            this.d = new RelativeLayout.LayoutParams(-1, -1);
        } else {
            this.d = new ViewGroup.LayoutParams(-1, -1);
        }
        this.j = new ArrayList();
        this.g = new View(this.f20899a);
        this.c.addView(this.g, 0, this.d);
        this.b.addView(this.c, this.d);
    }

    public void a(String str, String str2, String str3, PointF pointF, int i2) {
        GuideBubbleImpl guideBubbleImpl = new GuideBubbleImpl(this.f20899a, str, str2, str3);
        guideBubbleImpl.a(this.c, pointF, i2);
        this.j.add(guideBubbleImpl);
    }

    public GuideBubble a(PointF pointF, int i2) {
        GuideBubbleRightCloseImpl guideBubbleRightCloseImpl = new GuideBubbleRightCloseImpl(this.f20899a);
        guideBubbleRightCloseImpl.a(this.c, pointF, i2);
        this.j.add(guideBubbleRightCloseImpl);
        return guideBubbleRightCloseImpl;
    }

    public GuideBubble a(PointF pointF, int i2, String str) {
        GBRightCloseV2 gBRightCloseV2 = new GBRightCloseV2(this.f20899a, str);
        gBRightCloseV2.a(this.c, pointF, i2);
        this.j.add(gBRightCloseV2);
        return gBRightCloseV2;
    }

    public void a(View view, PointF pointF, int i2) {
        if (view.getLayoutParams() != null) {
            if (this.k == null) {
                this.k = new SparseArray<>();
            }
            List list = this.k.get(i2);
            if (list == null) {
                list = new ArrayList();
                this.k.put(i2, list);
            }
            GuideExtraView guideExtraView = new GuideExtraView();
            guideExtraView.f20902a = view;
            guideExtraView.b = pointF;
            guideExtraView.c = i2;
            list.add(guideExtraView);
            return;
        }
        throw new RuntimeException("View does not set layoutParams.");
    }

    private static class GuideExtraView {

        /* renamed from: a  reason: collision with root package name */
        View f20902a;
        PointF b;
        int c;

        private GuideExtraView() {
        }
    }

    public void a(GuideNextStepCallback guideNextStepCallback) {
        this.h = guideNextStepCallback;
    }

    public boolean b() {
        return this.n;
    }

    public void c() {
        this.b.post(new Runnable() {
            public void run() {
                if (GuideBubbleManager.this.c.getParent() == null) {
                    GuideBubbleManager.this.b.addView(GuideBubbleManager.this.c, GuideBubbleManager.this.d);
                }
                if (GuideBubbleManager.this.i) {
                    GuideBubbleManager.this.c.setOnClickListener(GuideBubbleManager.this);
                }
                if (GuideBubbleManager.this.m < GuideBubbleManager.this.j.size()) {
                    GuideBubbleManager.this.i();
                    GuideBubbleManager.this.g();
                    GuideBubbleManager.this.h();
                }
                if (!GuideBubbleManager.this.i && GuideBubbleManager.this.l != null) {
                    GuideBubbleManager.this.l.a(GuideBubbleManager.this);
                }
            }
        });
        this.n = true;
    }

    public void onClick(View view) {
        if (view == this.c && j() && this.l != null) {
            this.l.b();
            this.l.a(this);
        }
    }

    private void f() {
        this.m++;
        if (this.m < this.j.size()) {
            g();
            h();
            return;
        }
        int size = this.j.size();
        if (size > 0) {
            if (!this.b.postDelayed(new Runnable() {
                public void run() {
                    GuideBubbleManager.this.d();
                }
            }, this.j.get(size - 1).e())) {
                d();
                return;
            }
            return;
        }
        d();
    }

    /* access modifiers changed from: private */
    public void g() {
        List<GuideExtraView> list;
        if (this.k != null) {
            if (this.m > 0 && (list = this.k.get(this.m - 1)) != null) {
                for (GuideExtraView guideExtraView : list) {
                    this.c.removeView(guideExtraView.f20902a);
                }
            }
            List<GuideExtraView> list2 = this.k.get(this.m);
            if (list2 != null) {
                for (GuideExtraView guideExtraView2 : list2) {
                    this.c.addView(guideExtraView2.f20902a, guideExtraView2.f20902a.getLayoutParams());
                    guideExtraView2.f20902a.setTranslationX(guideExtraView2.b.x);
                    guideExtraView2.f20902a.setTranslationY(guideExtraView2.b.y);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.h != null) {
            this.h.a(this.m);
        }
        GuideBubble guideBubble = this.j.get(this.m);
        if (this.f != null && guideBubble.k()) {
            this.f.a(guideBubble.h(), guideBubble.f());
        }
        guideBubble.a();
        this.l = guideBubble;
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.f == null) {
            this.b.setDrawingCacheEnabled(true);
            this.b.buildDrawingCache(true);
            this.e = this.b.getDrawingCache(true);
            this.f = new BlurBackgroundDrawable(this.e);
            this.g.setBackgroundDrawable(this.f);
        }
    }

    public void d() {
        this.b.removeView(this.c);
        this.b.destroyDrawingCache();
        this.c.setOnClickListener(this);
        this.j.clear();
        this.f = null;
        if (this.e != null && !this.e.isRecycled()) {
            this.e.recycle();
            this.e = null;
        }
        this.m = 0;
        if (this.h != null) {
            this.h.a();
        }
        this.n = false;
    }

    private boolean j() {
        return this.l != null && this.l.c() && this.l.d();
    }

    public void onAnimationEnd(Animation animation) {
        f();
    }
}
