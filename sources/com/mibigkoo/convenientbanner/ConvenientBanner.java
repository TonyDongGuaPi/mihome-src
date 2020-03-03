package com.mibigkoo.convenientbanner;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.mibigkoo.convenientbanner.adapter.CBPageAdapter;
import com.mibigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.mibigkoo.convenientbanner.listener.CBPageChangeListener;
import com.mibigkoo.convenientbanner.listener.OnItemClickListener;
import com.mibigkoo.convenientbanner.view.CBLoopViewPager;
import com.xiaomi.smarthome.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConvenientBanner<T> extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private List<T> f7612a;
    private int[] b;
    private ArrayList<ImageView> c = new ArrayList<>();
    private CBPageChangeListener d;
    private ViewPager.OnPageChangeListener e;
    private CBPageAdapter f;
    /* access modifiers changed from: private */
    public CBLoopViewPager g;
    private ViewPagerScroller h;
    private ViewGroup i;
    /* access modifiers changed from: private */
    public long j;
    /* access modifiers changed from: private */
    public boolean k;
    private boolean l = false;
    private boolean m = true;
    private boolean n = true;
    /* access modifiers changed from: private */
    public AdSwitchTask o;

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT,
        ALIGN_PARENT_RIGHT,
        CENTER_HORIZONTAL
    }

    public ConvenientBanner(Context context) {
        super(context);
        a(context);
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.n = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        a(context);
    }

    @TargetApi(11)
    public ConvenientBanner(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.n = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        a(context);
    }

    @TargetApi(21)
    public ConvenientBanner(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConvenientBanner);
        this.n = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.include_viewpager, this, true);
        this.g = (CBLoopViewPager) inflate.findViewById(R.id.cbLoopViewPager);
        this.i = (ViewGroup) inflate.findViewById(R.id.loPageTurningPoint);
        a();
        this.o = new AdSwitchTask(this);
    }

    static class AdSwitchTask implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<ConvenientBanner> f7613a;

        AdSwitchTask(ConvenientBanner convenientBanner) {
            this.f7613a = new WeakReference<>(convenientBanner);
        }

        public void run() {
            ConvenientBanner convenientBanner = (ConvenientBanner) this.f7613a.get();
            if (convenientBanner != null && convenientBanner.g != null && convenientBanner.k) {
                convenientBanner.g.setCurrentItem(convenientBanner.g.getCurrentItem() + 1);
                convenientBanner.postDelayed(convenientBanner.o, convenientBanner.j);
            }
        }
    }

    public ConvenientBanner setPages(CBViewHolderCreator cBViewHolderCreator, List<T> list) {
        this.f7612a = list;
        this.f = new CBPageAdapter(cBViewHolderCreator, this.f7612a);
        this.g.setAdapter(this.f, this.n);
        if (this.b != null) {
            setPageIndicator(this.b);
        }
        return this;
    }

    public void notifyDataSetChanged() {
        this.g.getAdapter().notifyDataSetChanged();
        if (this.b != null) {
            setPageIndicator(this.b);
        }
    }

    public ConvenientBanner setPointViewVisible(boolean z) {
        this.i.setVisibility(z ? 0 : 8);
        return this;
    }

    public void setPointViewTopMargin(int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.topMargin = i2;
        this.i.setLayoutParams(layoutParams);
    }

    public ConvenientBanner setPageIndicator(int[] iArr) {
        this.i.removeAllViews();
        this.c.clear();
        this.b = iArr;
        if (this.f7612a == null) {
            return this;
        }
        for (int i2 = 0; i2 < this.f7612a.size(); i2++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(5, 0, 5, 0);
            if (this.c.isEmpty()) {
                imageView.setImageResource(iArr[1]);
            } else {
                imageView.setImageResource(iArr[0]);
            }
            this.c.add(imageView);
            this.i.addView(imageView);
        }
        this.d = new CBPageChangeListener(this.c, iArr);
        this.g.setOnPageChangeListener(this.d);
        this.d.onPageSelected(this.g.getRealItem());
        if (this.e != null) {
            this.d.a(this.e);
        }
        return this;
    }

    public ConvenientBanner setPageIndicatorAlign(PageIndicatorAlign pageIndicatorAlign) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
        int i2 = 0;
        layoutParams.addRule(9, pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_LEFT ? -1 : 0);
        layoutParams.addRule(11, pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? -1 : 0);
        if (pageIndicatorAlign == PageIndicatorAlign.CENTER_HORIZONTAL) {
            i2 = -1;
        }
        layoutParams.addRule(14, i2);
        this.i.setLayoutParams(layoutParams);
        return this;
    }

    public boolean isTurning() {
        return this.k;
    }

    public ConvenientBanner startTurning(long j2) {
        if (this.k) {
            stopTurning();
        }
        this.l = true;
        this.j = j2;
        this.k = true;
        postDelayed(this.o, j2);
        return this;
    }

    public void stopTurning() {
        this.k = false;
        removeCallbacks(this.o);
    }

    public ConvenientBanner setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        this.g.setPageTransformer(true, pageTransformer);
        return this;
    }

    private void a() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.h = new ViewPagerScroller(this.g.getContext());
            declaredField.set(this.g, this.h);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
    }

    public boolean isManualPageable() {
        return this.g.isCanScroll();
    }

    public void setManualPageable(boolean z) {
        this.g.setCanScroll(z);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            if (this.l) {
                startTurning(this.j);
            }
        } else if (action == 0 && this.l) {
            stopTurning();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getCurrentItem() {
        if (this.g != null) {
            return this.g.getRealItem();
        }
        return -1;
    }

    public void setcurrentitem(int i2) {
        if (this.g != null) {
            this.g.setCurrentItem(i2);
        }
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return this.e;
    }

    public ConvenientBanner setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.e = onPageChangeListener;
        if (this.d != null) {
            this.d.a(onPageChangeListener);
        } else {
            this.g.setOnPageChangeListener(onPageChangeListener);
        }
        return this;
    }

    public boolean isCanLoop() {
        return this.g.isCanLoop();
    }

    public ConvenientBanner setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            this.g.setOnItemClickListener((OnItemClickListener) null);
            return this;
        }
        this.g.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public void setScrollDuration(int i2) {
        this.h.a(i2);
    }

    public int getScrollDuration() {
        return this.h.a();
    }

    public CBLoopViewPager getViewPager() {
        return this.g;
    }

    public void setCanLoop(boolean z) {
        this.n = z;
        this.g.setCanLoop(z);
    }
}
