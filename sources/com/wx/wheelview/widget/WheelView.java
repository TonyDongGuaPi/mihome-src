package com.wx.wheelview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.adapter.BaseWheelAdapter;
import com.wx.wheelview.adapter.SimpleWheelAdapter;
import com.wx.wheelview.common.WheelViewException;
import com.wx.wheelview.graphics.DrawableFactory;
import com.wx.wheelview.util.WheelUtils;
import java.util.HashMap;
import java.util.List;

public class WheelView<T> extends ListView implements IWheelView<T> {
    /* access modifiers changed from: private */
    public int d = 0;
    /* access modifiers changed from: private */
    public int e = 3;
    private boolean f = false;
    /* access modifiers changed from: private */
    public List<T> g = null;
    private int h = -1;
    private String i;
    private int j;
    private int k;
    private int l;
    private int m = 0;
    private boolean n = false;
    private Paint o;
    private Skin p = Skin.None;
    private WheelViewStyle q;
    /* access modifiers changed from: private */
    public WheelView r;
    /* access modifiers changed from: private */
    public HashMap<String, List<T>> s;
    private BaseWheelAdapter<T> t;
    /* access modifiers changed from: private */
    public OnWheelItemSelectedListener<T> u;
    /* access modifiers changed from: private */
    public OnWheelItemClickListener<T> v;
    private Handler w = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 256) {
                if (WheelView.this.u != null) {
                    WheelView.this.u.onItemSelected(WheelView.this.getCurrentPosition(), WheelView.this.getSelectionItem());
                }
                if (WheelView.this.r == null) {
                    return;
                }
                if (!WheelView.this.s.isEmpty()) {
                    WheelView.this.r.resetDataFromTop((List) WheelView.this.s.get(WheelView.this.g.get(WheelView.this.getCurrentPosition())));
                    return;
                }
                throw new WheelViewException("JoinList is error.");
            }
        }
    };
    private AdapterView.OnItemClickListener x = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (WheelView.this.v != null) {
                WheelView.this.v.a(WheelView.this.getCurrentPosition(), WheelView.this.getSelectionItem());
            }
        }
    };
    private View.OnTouchListener y = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };
    private AbsListView.OnScrollListener z = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            View childAt;
            if (i == 0 && (childAt = WheelView.this.getChildAt(0)) != null) {
                float y = childAt.getY();
                if (y != 0.0f && WheelView.this.d != 0) {
                    if (Math.abs(y) < ((float) (WheelView.this.d / 2))) {
                        WheelView.this.smoothScrollBy(WheelView.this.a(y), 50);
                        return;
                    }
                    WheelView.this.smoothScrollBy(WheelView.this.a(((float) WheelView.this.d) + y), 50);
                }
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i2 != 0) {
                WheelView.this.a(false);
            }
        }
    };

    public interface OnWheelItemClickListener<T> {
        void a(int i, T t);
    }

    public interface OnWheelItemSelectedListener<T> {
        void onItemSelected(int i, T t);
    }

    public enum Skin {
        Common,
        Holo,
        None
    }

    public WheelView(Context context) {
        super(context);
        a();
    }

    public WheelView(Context context, WheelViewStyle wheelViewStyle) {
        super(context);
        setStyle(wheelViewStyle);
        a();
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public void setOnWheelItemSelectedListener(OnWheelItemSelectedListener<T> onWheelItemSelectedListener) {
        this.u = onWheelItemSelectedListener;
    }

    public void setOnWheelItemClickListener(OnWheelItemClickListener<T> onWheelItemClickListener) {
        this.v = onWheelItemClickListener;
    }

    private void a() {
        if (this.q == null) {
            this.q = new WheelViewStyle();
        }
        this.o = new Paint(1);
        setTag("com.wx.wheelview");
        setVerticalScrollBarEnabled(false);
        setScrollingCacheEnabled(false);
        setCacheColorHint(0);
        setFadingEdgeLength(0);
        setOverScrollMode(2);
        setDividerHeight(0);
        setOnItemClickListener(this.x);
        setOnScrollListener(this.z);
        setOnTouchListener(this.y);
        if (Build.VERSION.SDK_INT >= 21) {
            setNestedScrollingEnabled(true);
        }
        b();
    }

    private void b() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    WheelView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    WheelView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                if (WheelView.this.getChildCount() > 0 && WheelView.this.d == 0) {
                    int unused = WheelView.this.d = WheelView.this.getChildAt(0).getHeight();
                    if (WheelView.this.d != 0) {
                        WheelView.this.getLayoutParams().height = WheelView.this.d * WheelView.this.e;
                        WheelView.this.a(WheelView.this.getFirstVisiblePosition(), WheelView.this.getCurrentPosition() + (WheelView.this.e / 2), WheelView.this.e / 2);
                        WheelView.this.c();
                        return;
                    }
                    throw new WheelViewException("wheel item is error.");
                }
            }
        });
    }

    public WheelViewStyle getStyle() {
        return this.q;
    }

    public void setStyle(WheelViewStyle wheelViewStyle) {
        this.q = wheelViewStyle;
    }

    /* access modifiers changed from: private */
    public void c() {
        Drawable a2 = DrawableFactory.a(this.p, getWidth(), this.d * this.e, this.q, this.e, this.d);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(a2);
        } else {
            setBackgroundDrawable(a2);
        }
    }

    public Skin getSkin() {
        return this.p;
    }

    public void setSkin(Skin skin) {
        this.p = skin;
    }

    public void setWheelSize(int i2) {
        if ((i2 & 1) != 0) {
            this.e = i2;
            if (this.t != null) {
                this.t.setWheelSize(i2);
                return;
            }
            return;
        }
        throw new WheelViewException("wheel size must be an odd number.");
    }

    public void setLoop(boolean z2) {
        if (z2 != this.f) {
            this.f = z2;
            setSelection(0);
            if (this.t != null) {
                this.t.setLoop(z2);
            }
        }
    }

    public void setWheelClickable(boolean z2) {
        if (z2 != this.n) {
            this.n = z2;
            if (this.t != null) {
                this.t.setClickable(z2);
            }
        }
    }

    public void resetDataFromTop(final List<T> list) {
        if (!WheelUtils.a(list)) {
            postDelayed(new Runnable() {
                public void run() {
                    WheelView.this.setWheelData(list);
                    WheelView.super.setSelection(0);
                    WheelView.this.a(true);
                }
            }, 10);
            return;
        }
        throw new WheelViewException("join map data is error.");
    }

    public int getSelection() {
        return this.m;
    }

    public void setSelection(final int i2) {
        this.m = i2;
        setVisibility(4);
        postDelayed(new Runnable() {
            public void run() {
                WheelView.super.setSelection(WheelView.this.a(i2));
                WheelView.this.a(false);
                WheelView.this.setVisibility(0);
            }
        }, 500);
    }

    public void join(WheelView wheelView) {
        if (wheelView != null) {
            this.r = wheelView;
            return;
        }
        throw new WheelViewException("wheelview cannot be null.");
    }

    public void joinDatas(HashMap<String, List<T>> hashMap) {
        this.s = hashMap;
    }

    /* access modifiers changed from: private */
    public int a(int i2) {
        if (WheelUtils.a(this.g)) {
            return 0;
        }
        return this.f ? (i2 + ((1073741823 / this.g.size()) * this.g.size())) - (this.e / 2) : i2;
    }

    public int getCurrentPosition() {
        return this.h;
    }

    public T getSelectionItem() {
        int currentPosition = getCurrentPosition();
        if (currentPosition < 0) {
            currentPosition = 0;
        }
        if (this.g == null || this.g.size() <= currentPosition) {
            return null;
        }
        return this.g.get(currentPosition);
    }

    @Deprecated
    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter == null || !(listAdapter instanceof BaseWheelAdapter)) {
            throw new WheelViewException("please invoke setWheelAdapter method.");
        }
        setWheelAdapter((BaseWheelAdapter) listAdapter);
    }

    public void setWheelAdapter(BaseWheelAdapter<T> baseWheelAdapter) {
        super.setAdapter(baseWheelAdapter);
        this.t = baseWheelAdapter;
        this.t.setData(this.g).setWheelSize(this.e).setLoop(this.f).setClickable(this.n);
    }

    public void setWheelData(List<T> list) {
        if (!WheelUtils.a(list)) {
            this.g = list;
            if (this.t != null) {
                this.t.setData(list);
                return;
            }
            return;
        }
        throw new WheelViewException("wheel datas are error.");
    }

    public void setExtraText(String str, int i2, int i3, int i4) {
        this.i = str;
        this.j = i2;
        this.k = i3;
        this.l = i4;
    }

    public int getWheelCount() {
        if (!WheelUtils.a(this.g)) {
            return this.g.size();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public int a(float f2) {
        if (Math.abs(f2) <= 2.0f) {
            return (int) f2;
        }
        if (Math.abs(f2) < 12.0f) {
            return f2 > 0.0f ? 2 : -2;
        }
        return (int) (f2 / 6.0f);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (getChildAt(0) != null && this.d != 0) {
            int firstVisiblePosition = getFirstVisiblePosition();
            if (!this.f || firstVisiblePosition != 0) {
                int i2 = Math.abs(getChildAt(0).getY()) <= ((float) (this.d / 2)) ? firstVisiblePosition : firstVisiblePosition + 1;
                a(firstVisiblePosition, (this.e / 2) + i2, this.e / 2);
                if (this.f) {
                    i2 = (i2 + (this.e / 2)) % getWheelCount();
                }
                if (i2 != this.h || z2) {
                    this.h = i2;
                    this.t.setCurrentPosition(i2);
                    this.w.removeMessages(256);
                    this.w.sendEmptyMessageDelayed(256, 300);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3, int i4) {
        for (int i5 = i3 - i4; i5 <= i3 + i4; i5++) {
            View childAt = getChildAt(i5 - i2);
            if (childAt != null) {
                if ((this.t instanceof ArrayWheelAdapter) || (this.t instanceof SimpleWheelAdapter)) {
                    a(i5, i3, childAt, (TextView) childAt.findViewWithTag(101));
                } else {
                    TextView a2 = WheelUtils.a(childAt);
                    if (a2 != null) {
                        a(i5, i3, childAt, a2);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r11, int r12, android.view.View r13, android.widget.TextView r14) {
        /*
            r10 = this;
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1 = 1098907648(0x41800000, float:16.0)
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3 = -1
            if (r12 != r11) goto L_0x0052
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.d
            if (r11 == r3) goto L_0x0015
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r2 = r11.d
        L_0x0013:
            r7 = r2
            goto L_0x0022
        L_0x0015:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.c
            if (r11 == r3) goto L_0x0020
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r2 = r11.c
            goto L_0x0013
        L_0x0020:
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0022:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.e
            if (r11 == r3) goto L_0x002d
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.e
            float r1 = (float) r11
        L_0x002d:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.f
            if (r11 == r3) goto L_0x003a
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            int r11 = r11.f
            float r11 = (float) r11
            r8 = r11
            goto L_0x0049
        L_0x003a:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            float r11 = r11.h
            int r11 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r11 == 0) goto L_0x0048
            com.wx.wheelview.widget.WheelView$WheelViewStyle r11 = r10.q
            float r11 = r11.h
            float r1 = r1 * r11
        L_0x0048:
            r8 = r1
        L_0x0049:
            r9 = 1065353216(0x3f800000, float:1.0)
            r4 = r10
            r5 = r13
            r6 = r14
            r4.a(r5, r6, r7, r8, r9)
            goto L_0x0093
        L_0x0052:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r4 = r10.q
            int r4 = r4.c
            if (r4 == r3) goto L_0x005e
            com.wx.wheelview.widget.WheelView$WheelViewStyle r2 = r10.q
            int r2 = r2.c
            r7 = r2
            goto L_0x0060
        L_0x005e:
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0060:
            com.wx.wheelview.widget.WheelView$WheelViewStyle r2 = r10.q
            int r2 = r2.e
            if (r2 == r3) goto L_0x006d
            com.wx.wheelview.widget.WheelView$WheelViewStyle r1 = r10.q
            int r1 = r1.e
            float r1 = (float) r1
            r8 = r1
            goto L_0x006f
        L_0x006d:
            r8 = 1098907648(0x41800000, float:16.0)
        L_0x006f:
            int r11 = r11 - r12
            int r11 = java.lang.Math.abs(r11)
            com.wx.wheelview.widget.WheelView$WheelViewStyle r12 = r10.q
            float r12 = r12.g
            int r12 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r12 == 0) goto L_0x0082
            com.wx.wheelview.widget.WheelView$WheelViewStyle r12 = r10.q
            float r12 = r12.g
            double r0 = (double) r12
            goto L_0x0087
        L_0x0082:
            r0 = 4604480258916220928(0x3fe6666660000000, double:0.699999988079071)
        L_0x0087:
            double r11 = (double) r11
            double r11 = java.lang.Math.pow(r0, r11)
            float r9 = (float) r11
            r4 = r10
            r5 = r13
            r6 = r14
            r4.a(r5, r6, r7, r8, r9)
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.wheelview.widget.WheelView.a(int, int, android.view.View, android.widget.TextView):void");
    }

    private void a(View view, TextView textView, int i2, float f2, float f3) {
        textView.setTextColor(i2);
        textView.setTextSize(1, f2);
        view.setAlpha(f3);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!TextUtils.isEmpty(this.i)) {
            Rect rect = new Rect(0, this.d * (this.e / 2), getWidth(), this.d * ((this.e / 2) + 1));
            this.o.setTextSize((float) this.k);
            this.o.setColor(this.j);
            Paint.FontMetricsInt fontMetricsInt = this.o.getFontMetricsInt();
            this.o.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.i, (float) (rect.centerX() + this.l), (float) ((((rect.bottom + rect.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2), this.o);
        }
    }

    public static class WheelViewStyle {

        /* renamed from: a  reason: collision with root package name */
        public int f9887a = -1;
        public int b = -1;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public int f = -1;
        public float g = -1.0f;
        public float h = -1.0f;

        public WheelViewStyle() {
        }

        public WheelViewStyle(WheelViewStyle wheelViewStyle) {
            this.f9887a = wheelViewStyle.f9887a;
            this.b = wheelViewStyle.b;
            this.c = wheelViewStyle.c;
            this.d = wheelViewStyle.d;
            this.e = wheelViewStyle.e;
            this.f = wheelViewStyle.f;
            this.g = wheelViewStyle.g;
            this.h = wheelViewStyle.h;
        }
    }
}
