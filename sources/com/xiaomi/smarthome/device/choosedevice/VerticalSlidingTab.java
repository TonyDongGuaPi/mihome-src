package com.xiaomi.smarthome.device.choosedevice;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.util.List;
import java.util.Locale;

public class VerticalSlidingTab extends ScrollView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout.LayoutParams f15363a;
    private RelativeLayout b;
    /* access modifiers changed from: private */
    public LinearLayout c;
    private View d;
    private int e;
    /* access modifiers changed from: private */
    public int f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private Typeface p;
    private int q;
    private int r;
    private int s;
    private Locale t;
    private Scroller u;
    private List<String> v;
    /* access modifiers changed from: private */
    public OnTabPositionChangeListener w;

    public interface OnTabPositionChangeListener {
        void a(int i, int i2);
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
    }

    public VerticalSlidingTab(Context context) {
        this(context, (AttributeSet) null);
    }

    public VerticalSlidingTab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerticalSlidingTab(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = 0;
        this.g = -10066330;
        this.h = false;
        this.i = 100;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 12;
        this.n = -16777216;
        this.o = -1;
        this.p = null;
        this.q = 0;
        this.r = 0;
        this.s = R.drawable.background_tab;
        setFillViewport(true);
        setWillNotDraw(false);
        this.b = new RelativeLayout(context);
        this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.b);
        this.c = new LinearLayout(context);
        this.c.setOrientation(1);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.b.addView(this.c);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.i = (int) TypedValue.applyDimension(1, (float) this.i, displayMetrics);
        this.j = (int) TypedValue.applyDimension(1, (float) this.j, displayMetrics);
        this.k = (int) TypedValue.applyDimension(1, (float) this.k, displayMetrics);
        this.l = (int) TypedValue.applyDimension(1, (float) this.l, displayMetrics);
        this.m = (int) TypedValue.applyDimension(2, (float) this.m, displayMetrics);
        this.i = (int) (((float) displayMetrics.heightPixels) * 0.5f);
        this.f15363a = new LinearLayout.LayoutParams(-1, -1);
        if (this.t == null) {
            this.t = getResources().getConfiguration().locale;
        }
        this.u = new Scroller(context);
    }

    public void setOnTabPositionChangeListener(OnTabPositionChangeListener onTabPositionChangeListener) {
        this.w = onTabPositionChangeListener;
    }

    public void notifyDataSetChanged() {
        this.c.removeAllViews();
        this.e = this.v.size();
        for (int i2 = 0; i2 < this.e; i2++) {
            a(i2, this.v.get(i2));
        }
        a();
    }

    private void a(int i2) {
        if (this.c.getChildCount() != 0) {
            this.d = new View(getContext());
            View childAt = this.c.getChildAt(0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j, childAt.getBottom() - childAt.getTop());
            layoutParams.setMargins(0, 0, 0, 0);
            this.d.setLayoutParams(layoutParams);
            this.d.setBackgroundColor(this.g);
            this.b.addView(this.d);
            b(i2);
        }
    }

    private void a(final int i2, String str) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.choose_device_left_list_item, this.c, false);
        ((TextView) inflate.findViewById(R.id.name)).setText(str);
        inflate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VerticalSlidingTab.this.w != null) {
                    VerticalSlidingTab.this.w.a(VerticalSlidingTab.this.f, i2);
                }
                VerticalSlidingTab.this.b(i2);
                VerticalSlidingTab.this.a(i2, VerticalSlidingTab.this.c.getChildAt(i2).getHeight());
                int unused = VerticalSlidingTab.this.f = i2;
                VerticalSlidingTab.this.a();
            }
        });
        this.c.addView(inflate, i2);
    }

    /* access modifiers changed from: private */
    public void a() {
        for (int i2 = 0; i2 < this.e; i2++) {
            View childAt = ((ViewGroup) this.c.getChildAt(i2)).getChildAt(0);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (i2 == this.f) {
                    textView.setTextColor(this.o);
                    textView.setBackgroundResource(R.drawable.radius_13_blue_shaper);
                } else {
                    textView.setTextColor(this.n);
                    textView.setBackgroundColor(0);
                }
            }
        }
    }

    public int getCurrentPosition() {
        return this.f;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r8, int r9) {
        /*
            r7 = this;
            int r9 = r7.e
            if (r9 != 0) goto L_0x0005
            return
        L_0x0005:
            r7.a()
            android.widget.LinearLayout r9 = r7.c
            android.view.View r8 = r9.getChildAt(r8)
            int r8 = r8.getTop()
            int r9 = r7.r
            r0 = 0
            if (r8 <= r9) goto L_0x0021
            int r9 = r7.i
            if (r8 <= r9) goto L_0x0021
            int r9 = r7.r
            int r9 = r8 - r9
        L_0x001f:
            r5 = r9
            goto L_0x002f
        L_0x0021:
            int r9 = r7.r
            if (r8 >= r9) goto L_0x002e
            int r9 = r7.i
            if (r8 >= r9) goto L_0x002e
            int r9 = r7.r
            int r9 = r8 - r9
            goto L_0x001f
        L_0x002e:
            r5 = 0
        L_0x002f:
            int r9 = r7.i
            int r9 = r8 - r9
            r7.scrollTo(r0, r9)
            r7.r = r8
            if (r5 == 0) goto L_0x004d
            android.widget.Scroller r1 = r7.u
            int r2 = r7.getScrollX()
            int r3 = r7.getScrollY()
            r4 = 0
            r6 = 300(0x12c, float:4.2E-43)
            r1.startScroll(r2, r3, r4, r5, r6)
            r7.invalidate()
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.choosedevice.VerticalSlidingTab.a(int, int):void");
    }

    public void computeScroll() {
        if (this.u.computeScrollOffset()) {
            scrollTo(this.u.getCurrX(), this.u.getCurrY());
            postInvalidate();
            return;
        }
        super.computeScroll();
    }

    public void setTabNames(List<String> list) {
        this.v = list;
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int i2) {
        if (i2 != this.f) {
            this.f = i2;
            if (this.c.getChildCount() != 0) {
                b(i2);
                a(i2, this.c.getChildAt(i2).getHeight());
            }
        }
    }

    public void setIndicatorColor(int i2) {
        this.g = i2;
        invalidate();
    }

    public void setIndicatorColorResource(int i2) {
        this.g = getResources().getColor(i2);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.g;
    }

    public void setIndicatorWidth(int i2) {
        this.j = i2;
        invalidate();
    }

    public int getIndicatorWidth() {
        return this.j;
    }

    public void setScrollOffset(int i2) {
        this.i = i2;
        invalidate();
    }

    public int getScrollOffset() {
        return this.i;
    }

    public void setShouldExpand(boolean z) {
        this.h = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.h;
    }

    public void setTextSize(int i2) {
        this.m = i2;
        a();
    }

    public int getTextSize() {
        return this.m;
    }

    public void setTextColor(int i2) {
        this.n = i2;
        a();
    }

    public void setTextColorResource(int i2) {
        this.n = getResources().getColor(i2);
        a();
    }

    public int getTextColor() {
        return this.n;
    }

    public void setTypeface(Typeface typeface, int i2) {
        this.p = typeface;
        this.q = i2;
        a();
    }

    public void setTabBackground(int i2) {
        this.s = i2;
    }

    public int getTabBackground() {
        return this.s;
    }

    public void setTabPaddingLeftRight(int i2) {
        this.k = i2;
        a();
    }

    public int getTabPaddingLeftRight() {
        return this.k;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f = savedState.f15365a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f15365a = this.f;
        return savedState;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f15365a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f15365a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f15365a);
        }
    }
}
