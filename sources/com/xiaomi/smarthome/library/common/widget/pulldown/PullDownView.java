package com.xiaomi.smarthome.library.common.widget.pulldown;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;

public class PullDownView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19062a = AnimateLinearLayout.class.getSimpleName();
    private static final int g = 2;
    private int b = -60;
    private int c;
    private RefreshListener d;
    private RotateAnimation e;
    private RotateAnimation f;
    private int h = 0;
    private boolean i = false;
    private int j;
    private int k;
    private String l = "";
    private String m = "";
    private String n = "";
    private IPullDownElastic o;

    public interface RefreshListener {
        void a(PullDownView pullDownView);
    }

    public boolean canScroll(PullDownView pullDownView) {
        return false;
    }

    public void computeScroll() {
    }

    public PullDownView(Context context) {
        super(context);
        a();
    }

    public PullDownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.e = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.e.setInterpolator(new LinearInterpolator());
        this.e.setDuration(250);
        this.e.setFillAfter(true);
        this.f = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f.setInterpolator(new LinearInterpolator());
        this.f.setDuration(200);
        this.f.setFillAfter(true);
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.d = refreshListener;
    }

    public void setPullDownElastic(IPullDownElastic iPullDownElastic) {
        this.o = iPullDownElastic;
        this.c = this.o.b();
        this.b = -this.c;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.c);
        layoutParams.topMargin = this.b;
        addView(this.o.a(), 0, layoutParams);
    }

    public void setRefreshTips(String str, String str2, String str3) {
        this.m = str;
        this.l = str2;
        this.n = str3;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(f19062a, "onTouchEvent");
        b(motionEvent);
        a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        if (this.d != null && this.o != null) {
            switch (motionEvent.getAction()) {
                case 0:
                    Log.d(f19062a, "down");
                    return;
                case 1:
                case 3:
                    Log.d(f19062a, "up");
                    if (this.k != 2 && this.i) {
                        if (this.k == 3) {
                            setMargin(this.b);
                        }
                        if (this.k == 1) {
                            this.k = 3;
                            setMargin(this.b);
                            a(this.k, false);
                            Log.d(f19062a, "由下拉刷新状态，到done状态");
                        }
                        if (this.k == 0) {
                            this.k = 2;
                            setMargin(0);
                            a(this.k, false);
                            b();
                            Log.d(f19062a, "由松开刷新状态，到done状态");
                        }
                    }
                    this.i = false;
                    return;
                case 2:
                    Log.d(f19062a, WXGesture.MOVE);
                    int y = (int) motionEvent.getY();
                    if (this.k != 2 && this.i) {
                        if (this.k == 0) {
                            if ((y - this.j) / 2 < this.c && y - this.j > 0) {
                                this.k = 1;
                                a(this.k, true);
                                Log.d(f19062a, "由松开刷新状态转变到下拉刷新状态");
                            } else if (y - this.j <= 0) {
                                this.k = 3;
                                a(this.k, false);
                                Log.d(f19062a, "由松开刷新状态转变到done状态");
                            }
                        }
                        if (this.k == 3 && y - this.j > 0) {
                            this.k = 1;
                            a(this.k, false);
                        }
                        if (this.k == 1) {
                            if ((y - this.j) / 2 >= this.c) {
                                this.k = 0;
                                a(this.k, false);
                                Log.d(f19062a, "由done或者下拉刷新状态转变到松开刷新");
                            } else if (y - this.j <= 0) {
                                this.k = 3;
                                a(this.k, false);
                                Log.d(f19062a, "由DOne或者下拉刷新状态转变到done状态");
                            }
                        }
                        if (y - this.j > 0) {
                            setMargin(((y - this.j) / 2) + this.b);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void setMargin(int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.a().getLayoutParams();
        layoutParams.topMargin = i2;
        this.o.a().setLayoutParams(layoutParams);
        this.o.a().invalidate();
    }

    private void a(int i2, boolean z) {
        this.o.a(i2, z);
        switch (i2) {
            case 0:
                this.o.a(0);
                this.o.b(8);
                this.o.c(0);
                this.o.a(this.l);
                this.o.c();
                this.o.a((Animation) this.e);
                Log.d(f19062a, "当前状态，松开刷新");
                return;
            case 1:
                this.o.a(0);
                this.o.b(8);
                this.o.c(0);
                this.o.a(this.m);
                this.o.c();
                if (z) {
                    this.o.a((Animation) this.f);
                }
                Log.d(f19062a, "当前状态，下拉刷新");
                return;
            case 2:
                this.o.a(8);
                this.o.b(0);
                this.o.c(8);
                this.o.a(this.n);
                this.o.c();
                Log.d(f19062a, "当前状态,正在刷新...");
                return;
            case 3:
                this.o.b(8);
                this.o.c();
                Log.d(f19062a, "当前状态，done");
                return;
            default:
                return;
        }
    }

    private void b() {
        if (this.d != null) {
            this.d.a(this);
        }
    }

    public void finishRefresh(String str) {
        if (this.o == null) {
            String str2 = f19062a;
            Log.d(str2, "finishRefresh mElastic:" + this.o);
            return;
        }
        this.k = 3;
        this.o.b(str);
        a(this.k, false);
        Log.d(f19062a, "执行了=====finishRefresh");
        this.o.a(0);
        this.o.c(0);
        setMargin(this.b);
    }

    private boolean c() {
        if (getChildCount() > 1) {
            View childAt = getChildAt(1);
            if (childAt instanceof AbsListView) {
                AbsListView absListView = (AbsListView) childAt;
                if (Math.abs(absListView.getChildAt(0).getTop() - absListView.getListPaddingTop()) >= 3 || absListView.getFirstVisiblePosition() != 0) {
                    return false;
                }
                return true;
            } else if (childAt instanceof ScrollView) {
                if (childAt.getScrollY() == 0) {
                    return true;
                }
                return false;
            }
        }
        return canScroll(this);
    }

    private void b(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                Log.d(f19062a, "down");
                return;
            case 1:
                Log.d(f19062a, "up");
                return;
            case 2:
                Log.d(f19062a, WXGesture.MOVE);
                return;
            case 3:
                Log.d(f19062a, "cancel");
                return;
            default:
                return;
        }
    }
}
