package com.xiaomi.smarthome.device.choosedevice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Locale;

public class HorizontalSlidingTab extends HorizontalScrollView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RelativeLayout.LayoutParams f15339a;
    private final PageListener b;
    /* access modifiers changed from: private */
    public RelativeLayout c;
    /* access modifiers changed from: private */
    public LinearLayout d;
    public ViewPager.OnPageChangeListener delegatePageListener;
    /* access modifiers changed from: private */
    public ViewPager e;
    private int f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public float h;
    private Paint i;
    private int j;
    private int k;
    private int l;
    private int m;
    /* access modifiers changed from: private */
    public int n;
    private int o;
    private int p;
    private int q;
    private Typeface r;
    private int s;
    /* access modifiers changed from: private */
    public TextView t;
    private int u;
    private Locale v;
    private int w;

    public HorizontalSlidingTab(Context context) {
        this(context, (AttributeSet) null);
    }

    public HorizontalSlidingTab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalSlidingTab(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = new PageListener();
        this.g = 0;
        this.h = 0.0f;
        this.j = 6710886;
        this.k = 52;
        this.l = 2;
        this.m = 12;
        this.n = 24;
        this.o = 15;
        this.p = -8421505;
        this.q = -16737793;
        this.r = null;
        this.s = 0;
        this.u = 0;
        this.w = 0;
        setFillViewport(true);
        setWillNotDraw(false);
        this.c = new RelativeLayout(context);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.c);
        this.d = new LinearLayout(context);
        this.d.setOrientation(0);
        this.d.setGravity(16);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        this.c.addView(this.d);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.k = (int) TypedValue.applyDimension(1, (float) this.k, displayMetrics);
        this.l = (int) TypedValue.applyDimension(1, (float) this.l, displayMetrics);
        this.m = (int) TypedValue.applyDimension(1, (float) this.m, displayMetrics);
        this.n = (int) TypedValue.applyDimension(1, (float) this.n, displayMetrics);
        this.o = (int) TypedValue.applyDimension(1, (float) this.o, displayMetrics);
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setStyle(Paint.Style.FILL);
        this.t = new TextView(context);
        this.t.setTextColor(-1);
        this.t.setTextSize(2, 10.0f);
        this.t.setGravity(17);
        this.t.setBackgroundResource(R.drawable.number_icon_nor_3x);
        this.f15339a = new RelativeLayout.LayoutParams(-2, -2);
        if (this.v == null) {
            this.v = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.e = viewPager;
        if (viewPager.getAdapter() != null) {
            viewPager.setOnPageChangeListener(this.b);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void notifyDataSetChanged() {
        this.d.removeAllViews();
        this.f = this.e.getAdapter().getCount();
        for (int i2 = 0; i2 < this.f; i2++) {
            a(i2, this.e.getAdapter().getPageTitle(i2).toString());
        }
        a();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    HorizontalSlidingTab.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    HorizontalSlidingTab.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                HorizontalSlidingTab.this.f15339a.setMargins((HorizontalSlidingTab.this.d.getChildAt(0).getRight() + DisplayUtils.a(2.0f)) - HorizontalSlidingTab.this.n, HorizontalSlidingTab.this.d.getChildAt(0).getBottom() - DisplayUtils.a(23.0f), 0, 0);
                HorizontalSlidingTab.this.c.addView(HorizontalSlidingTab.this.t, HorizontalSlidingTab.this.f15339a);
                HorizontalSlidingTab.this.t.setVisibility(4);
                int unused = HorizontalSlidingTab.this.g = HorizontalSlidingTab.this.e.getCurrentItem();
                HorizontalSlidingTab.this.a(HorizontalSlidingTab.this.g, 0);
            }
        });
    }

    private void a(final int i2, String str) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setFocusable(true);
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (i2 == 0) {
                    STAT.d.H();
                } else {
                    STAT.d.I();
                }
                HorizontalSlidingTab.this.e.setCurrentItem(i2);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getWidth() / 2, -2);
        layoutParams.gravity = 16;
        if (i2 == 0) {
            textView.setPadding(0, 0, this.n, 0);
        } else {
            textView.setPadding(this.n, 0, 0, 0);
        }
        textView.setGravity(i2 == 0 ? GravityCompat.END : GravityCompat.START);
        textView.setLayoutParams(layoutParams);
        this.d.addView(textView, i2);
    }

    /* access modifiers changed from: private */
    public void a() {
        for (int i2 = 0; i2 < this.f; i2++) {
            View childAt = this.d.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.o);
                textView.setTypeface(this.r, this.s);
                if (i2 == this.g) {
                    textView.setTextColor(this.q);
                } else {
                    textView.setTextColor(this.p);
                }
            }
            if (i2 == 0 && this.t.getParent() != null) {
                if (this.w == 0) {
                    this.t.setVisibility(4);
                } else {
                    this.t.setVisibility(0);
                    this.t.setText(String.valueOf(this.w));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        if (this.f != 0) {
            int left = this.d.getChildAt(i2).getLeft() + i3;
            if (i2 > 0 || i3 > 0) {
                left -= this.k;
            }
            if (left != this.u) {
                this.u = left;
                scrollTo(left, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.f != 0) {
            int height = getHeight();
            this.i.setColor(this.j);
            View childAt = this.d.getChildAt(this.g);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.h > 0.0f && this.g < this.f - 1) {
                View childAt2 = this.d.getChildAt(this.g + 1);
                left = (this.h * ((float) childAt2.getLeft())) + ((1.0f - this.h) * left);
                right = (this.h * ((float) childAt2.getRight())) + ((1.0f - this.h) * right);
            }
            Canvas canvas2 = canvas;
            canvas2.drawRect(left, (float) (height - this.l), right, (float) height, this.i);
        }
    }

    private class PageListener implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int unused = HorizontalSlidingTab.this.g = i;
            float unused2 = HorizontalSlidingTab.this.h = f;
            HorizontalSlidingTab.this.a(i, (int) (((float) HorizontalSlidingTab.this.d.getChildAt(i).getWidth()) * f));
            HorizontalSlidingTab.this.invalidate();
            if (HorizontalSlidingTab.this.delegatePageListener != null) {
                HorizontalSlidingTab.this.delegatePageListener.onPageScrolled(i, f, i2);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                HorizontalSlidingTab.this.a(HorizontalSlidingTab.this.e.getCurrentItem(), 0);
            }
            if (HorizontalSlidingTab.this.delegatePageListener != null) {
                HorizontalSlidingTab.this.delegatePageListener.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            int unused = HorizontalSlidingTab.this.g = i;
            HorizontalSlidingTab.this.a();
            if (HorizontalSlidingTab.this.delegatePageListener != null) {
                HorizontalSlidingTab.this.delegatePageListener.onPageSelected(i);
            }
        }
    }

    public int getPosition() {
        return this.g;
    }

    public void setDividerPadding(int i2) {
        this.m = i2;
        invalidate();
    }

    public int getDividerPadding() {
        return this.m;
    }

    public void setScanDeviceCount(int i2) {
        this.w = i2;
        a();
    }

    public void setTextSize(int i2) {
        this.o = i2;
        a();
    }

    public int getTextSize() {
        return this.o;
    }

    public void setTextColor(int i2) {
        this.p = i2;
        a();
    }

    public int getTextColor() {
        return this.p;
    }

    public void setTypeface(Typeface typeface, int i2) {
        this.r = typeface;
        this.s = i2;
        a();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.g = savedState.f15343a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f15343a = this.g;
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
        int f15343a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f15343a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f15343a);
        }
    }
}
