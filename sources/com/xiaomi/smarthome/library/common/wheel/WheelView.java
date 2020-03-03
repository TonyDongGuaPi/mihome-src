package com.xiaomi.smarthome.library.common.wheel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    public static final String WHEEL_VIEW_ITEM_HEIGHT = "WheelViewItemHeight";
    public static final String WHEEL_VIEW_ITEM_SPACE = "WheelViewItemSpace";
    public static final String WHEEL_VIEW_TEXT_SIZE = "WheelViewTextSize";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18756a = DisplayUtils.a(20.0f);
    private static final int b = 0;
    private static final int c = 400;
    private static final int d = 1;
    private static final int e = SHApplication.getAppContext().getResources().getColor(R.color.wheel_selected_color);
    private static final int f = SHApplication.getAppContext().getResources().getColor(R.color.wheel_unselected_color);
    private static final int[] g = {-15658735, 11184810, 11184810};
    private static final int h = 32;
    private static final int i = 16;
    private static final int j = 8;
    private static final int k = 10;
    private static final int l = 5;
    private GradientDrawable A;
    /* access modifiers changed from: private */
    public boolean B;
    /* access modifiers changed from: private */
    public int C;
    private GestureDetector D;
    /* access modifiers changed from: private */
    public Scroller E;
    /* access modifiers changed from: private */
    public int F;
    private List<OnWheelChangedListener> G;
    private List<OnWheelScrollListener> H;
    private int I;
    private int J;
    private int K;
    private GestureDetector.SimpleOnGestureListener L;
    private final int M;
    private final int N;
    /* access modifiers changed from: private */
    public Handler O;
    boolean isCyclic;
    /* access modifiers changed from: private */
    public WheelAdapter m;
    /* access modifiers changed from: private */
    public int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private TextPaint s;
    private TextPaint t;
    private StaticLayout u;
    private StaticLayout v;
    private StaticLayout w;
    private String x;
    private Drawable y;
    private GradientDrawable z;

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 5;
        this.r = 0;
        this.isCyclic = false;
        this.G = new LinkedList();
        this.H = new LinkedList();
        this.J = f18756a;
        this.K = 0;
        this.L = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                if (!WheelView.this.B) {
                    return false;
                }
                WheelView.this.E.forceFinished(true);
                WheelView.this.c();
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                WheelView.this.e();
                WheelView.this.b((int) (-f2));
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int unused = WheelView.this.F = (WheelView.this.n * WheelView.this.getItemHeight()) + WheelView.this.C;
                int a2 = WheelView.this.isCyclic ? Integer.MAX_VALUE : WheelView.this.m.a() * WheelView.this.getItemHeight();
                WheelView.this.E.fling(0, WheelView.this.F, 0, ((int) (-f2)) / 2, 0, 0, WheelView.this.isCyclic ? -a2 : 0, a2);
                WheelView.this.setNextMessage(0);
                return true;
            }
        };
        this.M = 0;
        this.N = 1;
        this.O = new Handler() {
            public void handleMessage(Message message) {
                WheelView.this.E.computeScrollOffset();
                int currY = WheelView.this.E.getCurrY();
                int access$500 = WheelView.this.F - currY;
                int unused = WheelView.this.F = currY;
                if (access$500 != 0) {
                    WheelView.this.b(access$500);
                }
                if (Math.abs(currY - WheelView.this.E.getFinalY()) < 1) {
                    WheelView.this.E.getFinalY();
                    WheelView.this.E.forceFinished(true);
                }
                if (!WheelView.this.E.isFinished()) {
                    WheelView.this.O.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.d();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        this.I = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_TEXT_SIZE, 32);
        this.J = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_ITEM_HEIGHT, f18756a);
        this.K = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_ITEM_SPACE, 0);
        a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 5;
        this.r = 0;
        this.isCyclic = false;
        this.G = new LinkedList();
        this.H = new LinkedList();
        this.J = f18756a;
        this.K = 0;
        this.L = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                if (!WheelView.this.B) {
                    return false;
                }
                WheelView.this.E.forceFinished(true);
                WheelView.this.c();
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                WheelView.this.e();
                WheelView.this.b((int) (-f2));
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int unused = WheelView.this.F = (WheelView.this.n * WheelView.this.getItemHeight()) + WheelView.this.C;
                int a2 = WheelView.this.isCyclic ? Integer.MAX_VALUE : WheelView.this.m.a() * WheelView.this.getItemHeight();
                WheelView.this.E.fling(0, WheelView.this.F, 0, ((int) (-f2)) / 2, 0, 0, WheelView.this.isCyclic ? -a2 : 0, a2);
                WheelView.this.setNextMessage(0);
                return true;
            }
        };
        this.M = 0;
        this.N = 1;
        this.O = new Handler() {
            public void handleMessage(Message message) {
                WheelView.this.E.computeScrollOffset();
                int currY = WheelView.this.E.getCurrY();
                int access$500 = WheelView.this.F - currY;
                int unused = WheelView.this.F = currY;
                if (access$500 != 0) {
                    WheelView.this.b(access$500);
                }
                if (Math.abs(currY - WheelView.this.E.getFinalY()) < 1) {
                    WheelView.this.E.getFinalY();
                    WheelView.this.E.forceFinished(true);
                }
                if (!WheelView.this.E.isFinished()) {
                    WheelView.this.O.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.d();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        this.I = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_TEXT_SIZE, 32);
        this.J = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_ITEM_HEIGHT, f18756a);
        this.K = attributeSet.getAttributeIntValue((String) null, WHEEL_VIEW_ITEM_SPACE, 0);
        a(context);
    }

    public WheelView(Context context) {
        super(context);
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 5;
        this.r = 0;
        this.isCyclic = false;
        this.G = new LinkedList();
        this.H = new LinkedList();
        this.J = f18756a;
        this.K = 0;
        this.L = new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                if (!WheelView.this.B) {
                    return false;
                }
                WheelView.this.E.forceFinished(true);
                WheelView.this.c();
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                WheelView.this.e();
                WheelView.this.b((int) (-f2));
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int unused = WheelView.this.F = (WheelView.this.n * WheelView.this.getItemHeight()) + WheelView.this.C;
                int a2 = WheelView.this.isCyclic ? Integer.MAX_VALUE : WheelView.this.m.a() * WheelView.this.getItemHeight();
                WheelView.this.E.fling(0, WheelView.this.F, 0, ((int) (-f2)) / 2, 0, 0, WheelView.this.isCyclic ? -a2 : 0, a2);
                WheelView.this.setNextMessage(0);
                return true;
            }
        };
        this.M = 0;
        this.N = 1;
        this.O = new Handler() {
            public void handleMessage(Message message) {
                WheelView.this.E.computeScrollOffset();
                int currY = WheelView.this.E.getCurrY();
                int access$500 = WheelView.this.F - currY;
                int unused = WheelView.this.F = currY;
                if (access$500 != 0) {
                    WheelView.this.b(access$500);
                }
                if (Math.abs(currY - WheelView.this.E.getFinalY()) < 1) {
                    WheelView.this.E.getFinalY();
                    WheelView.this.E.forceFinished(true);
                }
                if (!WheelView.this.E.isFinished()) {
                    WheelView.this.O.sendEmptyMessage(message.what);
                } else if (message.what == 0) {
                    WheelView.this.d();
                } else {
                    WheelView.this.finishScrolling();
                }
            }
        };
        this.I = 32;
        a(context);
    }

    private void a(Context context) {
        this.J = a(context, this.J);
        this.K = a(context, this.K);
        this.I = a(context, this.I);
        this.D = new GestureDetector(context, this.L);
        this.D.setIsLongpressEnabled(false);
        this.E = new Scroller(context);
    }

    public WheelAdapter getAdapter() {
        return this.m;
    }

    public void setAdapter(WheelAdapter wheelAdapter) {
        this.m = wheelAdapter;
        a();
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.E.forceFinished(true);
        this.E = new Scroller(getContext(), interpolator);
    }

    public int getVisibleItems() {
        return this.q;
    }

    public void setVisibleItems(int i2) {
        this.q = i2;
        invalidate();
    }

    public String getLabel() {
        return this.x;
    }

    public void setLabel(String str) {
        if (this.x == null || !this.x.equals(str)) {
            this.x = str;
            this.v = null;
            invalidate();
        }
    }

    public void addChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.G.add(onWheelChangedListener);
    }

    public void removeChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.G.remove(onWheelChangedListener);
    }

    /* access modifiers changed from: protected */
    public void notifyChangingListeners(int i2, int i3) {
        for (OnWheelChangedListener a2 : this.G) {
            a2.a(this, i2, i3);
        }
    }

    public void addScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.H.add(onWheelScrollListener);
    }

    public void removeScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.H.remove(onWheelScrollListener);
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutStart() {
        for (OnWheelScrollListener a2 : this.H) {
            a2.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutEnd() {
        for (OnWheelScrollListener b2 : this.H) {
            b2.b(this);
        }
    }

    public int getCurrentItem() {
        return this.n;
    }

    public void setCurrentItem(int i2, boolean z2) {
        if (this.m != null && this.m.a() != 0) {
            if (i2 < 0 || i2 >= this.m.a()) {
                if (this.isCyclic) {
                    while (i2 < 0) {
                        i2 += this.m.a();
                    }
                    i2 %= this.m.a();
                } else {
                    return;
                }
            }
            if (i2 == this.n) {
                return;
            }
            if (z2) {
                scroll(i2 - this.n, 400);
                return;
            }
            a();
            int i3 = this.n;
            this.n = i2;
            notifyChangingListeners(i3, this.n);
            invalidate();
        }
    }

    public void setCurrentItem(int i2) {
        setCurrentItem(i2, false);
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z2) {
        this.isCyclic = z2;
        invalidate();
        a();
    }

    private void a() {
        this.u = null;
        this.w = null;
        this.C = 0;
    }

    private void b() {
        if (this.s == null) {
            this.s = new TextPaint(1);
            this.s.setTextSize((float) this.I);
        }
        if (this.t == null) {
            this.t = new TextPaint(5);
            this.t.setTextSize((float) this.I);
            this.t.setShadowLayer(0.1f, 0.0f, 0.1f, -4144960);
        }
        if (this.y == null) {
            this.y = getContext().getResources().getDrawable(R.drawable.group_time_select_bg);
        }
    }

    private int a(Layout layout) {
        if (layout == null) {
            return 0;
        }
        return Math.max(((getItemHeight() * this.q) - 32) - this.J, getSuggestedMinimumHeight());
    }

    private String a(int i2) {
        if (this.m == null || this.m.a() == 0) {
            return null;
        }
        int a2 = this.m.a();
        if ((i2 < 0 || i2 >= a2) && !this.isCyclic) {
            return null;
        }
        while (i2 < 0) {
            i2 += a2;
        }
        return this.m.a(i2 % a2);
    }

    private String a(boolean z2) {
        String a2;
        StringBuilder sb = new StringBuilder();
        int i2 = (this.q / 2) + 1;
        for (int i3 = this.n - i2; i3 <= this.n + i2; i3++) {
            if ((z2 || i3 != this.n) && (a2 = a(i3)) != null) {
                sb.append(a2);
            }
            if (i3 < this.n + i2) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private int getMaxTextLength() {
        WheelAdapter adapter = getAdapter();
        if (adapter == null) {
            return 0;
        }
        int b2 = adapter.b();
        if (b2 > 0) {
            return b2;
        }
        String str = null;
        for (int max = Math.max(this.n - (this.q / 2), 0); max < Math.min(this.n + this.q, adapter.a()); max++) {
            String a2 = adapter.a(max);
            if (a2 != null && (str == null || str.length() < a2.length())) {
                str = a2;
            }
        }
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int getItemHeight() {
        if (this.r != 0) {
            return this.r;
        }
        if (this.u == null || this.u.getLineCount() <= 2) {
            return getHeight() / this.q;
        }
        this.r = this.u.getLineTop(2) - this.u.getLineTop(1);
        return this.r;
    }

    private int a(int i2, int i3) {
        b();
        int maxTextLength = getMaxTextLength();
        if (maxTextLength > 0) {
            double ceil = Math.ceil((double) Layout.getDesiredWidth("0", this.s));
            double d2 = (double) maxTextLength;
            Double.isNaN(d2);
            this.o = (int) (d2 * ceil);
        } else {
            this.o = 0;
        }
        this.o += this.K;
        this.p = 0;
        if (this.x != null && this.x.length() > 0) {
            this.p = (int) Math.ceil((double) Layout.getDesiredWidth(this.x, this.t));
        }
        boolean z2 = true;
        if (i3 != 1073741824) {
            int i4 = this.o + this.p + 20;
            if (this.p > 0) {
                i4 += 8;
            }
            int max = Math.max(i4, getSuggestedMinimumWidth());
            if (i3 != Integer.MIN_VALUE || i2 >= max) {
                i2 = max;
                z2 = false;
            }
        }
        if (z2) {
            int i5 = (i2 - 8) - 20;
            if (i5 <= 0) {
                this.p = 0;
                this.o = 0;
            }
            if (this.p > 0) {
                double d3 = (double) this.o;
                double d4 = (double) i5;
                Double.isNaN(d3);
                Double.isNaN(d4);
                double d5 = d3 * d4;
                double d6 = (double) (this.o + this.p);
                Double.isNaN(d6);
                this.o = (int) (d5 / d6);
                this.p = i5 - this.o;
            } else {
                this.o = i5 + 8;
            }
        }
        if (this.o > 0) {
            b(this.o, this.p);
        }
        return i2;
    }

    private void b(int i2, int i3) {
        if (this.u == null || this.u.getWidth() > i2) {
            this.u = new StaticLayout(a(this.B), this.s, i2, i3 > 0 ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER, 1.0f, (float) this.J, false);
        } else {
            this.u.increaseWidthTo(i2);
        }
        String str = null;
        if (!this.B && (this.w == null || this.w.getWidth() > i2)) {
            if (getAdapter() != null) {
                str = getAdapter().a(this.n);
            }
            if (str == null) {
                str = "";
            }
            this.w = new StaticLayout(str, this.t, i2, i3 > 0 ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER, 1.0f, (float) this.J, false);
        } else if (this.B) {
            this.w = null;
        } else {
            this.w.increaseWidthTo(i2);
        }
        if (i3 <= 0) {
            return;
        }
        if (this.v == null || this.v.getWidth() > i3) {
            this.v = new StaticLayout(this.x, this.t, i3, Layout.Alignment.ALIGN_NORMAL, 1.0f, (float) this.J, false);
        } else {
            this.v.increaseWidthTo(i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int a2 = a(size, mode);
        if (mode2 != 1073741824) {
            int a3 = a((Layout) this.u);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(a3, size2) : a3;
        }
        setMeasuredDimension(a2, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.u == null) {
            if (this.o == 0) {
                a(getWidth(), 1073741824);
            } else {
                b(this.o, this.p);
            }
        }
        if (this.o > 0) {
            canvas.save();
            canvas.translate(10.0f, -16.0f);
            c(canvas);
            b(canvas);
            canvas.restore();
        }
        d(canvas);
    }

    private void a(Canvas canvas) {
        this.z.setBounds(0, 0, getWidth(), getHeight() / this.q);
        this.z.draw(canvas);
        this.A.setBounds(0, getHeight() - (getHeight() / this.q), getWidth(), getHeight());
        this.A.draw(canvas);
    }

    private void b(Canvas canvas) {
        this.t.setColor(e);
        this.t.drawableState = getDrawableState();
        Rect rect = new Rect();
        this.u.getLineBounds(this.q / 2, rect);
        if (this.v != null) {
            canvas.save();
            canvas.translate((float) (this.u.getWidth() + 8), (float) rect.top);
            this.v.draw(canvas);
            canvas.restore();
        }
        if (this.w != null) {
            canvas.save();
            canvas.translate(0.0f, (float) (rect.top + this.C));
            this.w.draw(canvas);
            canvas.restore();
        }
    }

    private void c(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, (float) ((-this.u.getLineTop(1)) + this.C));
        this.s.setColor(f);
        this.s.drawableState = getDrawableState();
        this.u.draw(canvas);
        canvas.restore();
    }

    private void d(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight = getItemHeight() / 2;
        this.y.setBounds(0, height - itemHeight, getWidth(), height + itemHeight);
        this.y.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getAdapter() != null && !this.D.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            d();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        this.C += i2;
        int itemHeight = this.C / getItemHeight();
        int i3 = this.n - itemHeight;
        if (this.isCyclic && this.m.a() > 0) {
            while (i3 < 0) {
                i3 += this.m.a();
            }
            i3 %= this.m.a();
        } else if (!this.B) {
            i3 = Math.min(Math.max(i3, 0), this.m.a() - 1);
        } else if (i3 < 0) {
            itemHeight = this.n;
            i3 = 0;
        } else if (i3 >= this.m.a()) {
            itemHeight = (this.n - this.m.a()) + 1;
            i3 = this.m.a() - 1;
        }
        int i4 = this.C;
        if (i3 != this.n) {
            setCurrentItem(i3, false);
        } else {
            invalidate();
        }
        this.C = i4 - (itemHeight * getItemHeight());
        if (this.C > getHeight()) {
            this.C = (this.C % getHeight()) + getHeight();
        }
    }

    /* access modifiers changed from: private */
    public void setNextMessage(int i2) {
        c();
        this.O.sendEmptyMessage(i2);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.O.removeMessages(0);
        this.O.removeMessages(1);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.m != null) {
            boolean z2 = false;
            this.F = 0;
            int i2 = this.C;
            int itemHeight = getItemHeight();
            if (i2 <= 0 ? this.n > 0 : this.n < this.m.a()) {
                z2 = true;
            }
            if ((this.isCyclic || z2) && Math.abs((float) i2) > ((float) itemHeight) / 2.0f) {
                i2 = i2 < 0 ? i2 + itemHeight + 1 : i2 - (itemHeight + 1);
            }
            int i3 = i2;
            if (Math.abs(i3) > 1) {
                this.E.startScroll(0, 0, 0, i3, 400);
                setNextMessage(1);
                return;
            }
            finishScrolling();
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.B) {
            this.B = true;
            notifyScrollingListenersAboutStart();
        }
    }

    /* access modifiers changed from: package-private */
    public void finishScrolling() {
        if (this.B) {
            notifyScrollingListenersAboutEnd();
            this.B = false;
        }
        a();
        invalidate();
    }

    public void scroll(int i2, int i3) {
        this.E.forceFinished(true);
        this.F = this.C;
        this.E.startScroll(0, this.F, 0, (i2 * getItemHeight()) - this.F, i3);
        setNextMessage(0);
        e();
    }

    private int a(Context context, int i2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) (((float) (i2 / 2)) * displayMetrics.density);
    }
}
