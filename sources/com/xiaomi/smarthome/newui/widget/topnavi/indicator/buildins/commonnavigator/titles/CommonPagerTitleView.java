package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

public class CommonPagerTitleView extends FrameLayout implements IMeasurablePagerTitleView {

    /* renamed from: a  reason: collision with root package name */
    private OnPagerTitleChangeListener f20937a;
    private ContentPositionDataProvider b;

    public interface ContentPositionDataProvider {
        int a();

        int b();

        int c();

        int d();
    }

    public interface OnPagerTitleChangeListener {
        void a(int i, int i2);

        void a(int i, int i2, float f, boolean z);

        void b(int i, int i2);

        void b(int i, int i2, float f, boolean z);
    }

    public CommonPagerTitleView(Context context) {
        super(context);
    }

    public void onSelected(int i, int i2) {
        if (this.f20937a != null) {
            this.f20937a.a(i, i2);
        }
    }

    public void onDeselected(int i, int i2) {
        if (this.f20937a != null) {
            this.f20937a.b(i, i2);
        }
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        if (this.f20937a != null) {
            this.f20937a.a(i, i2, f, z);
        }
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        if (this.f20937a != null) {
            this.f20937a.b(i, i2, f, z);
        }
    }

    public int getContentLeft() {
        if (this.b != null) {
            return this.b.a();
        }
        return getLeft();
    }

    public int getContentTop() {
        if (this.b != null) {
            return this.b.b();
        }
        return getTop();
    }

    public int getContentRight() {
        if (this.b != null) {
            return this.b.c();
        }
        return getRight();
    }

    public int getContentBottom() {
        if (this.b != null) {
            return this.b.d();
        }
        return getBottom();
    }

    public void setContentView(View view) {
        setContentView(view, (FrameLayout.LayoutParams) null);
    }

    public void setContentView(View view, FrameLayout.LayoutParams layoutParams) {
        removeAllViews();
        if (view != null) {
            if (layoutParams == null) {
                layoutParams = new FrameLayout.LayoutParams(-1, -1);
            }
            addView(view, layoutParams);
        }
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null), (FrameLayout.LayoutParams) null);
    }

    public OnPagerTitleChangeListener getOnPagerTitleChangeListener() {
        return this.f20937a;
    }

    public void setOnPagerTitleChangeListener(OnPagerTitleChangeListener onPagerTitleChangeListener) {
        this.f20937a = onPagerTitleChangeListener;
    }

    public ContentPositionDataProvider getContentPositionDataProvider() {
        return this.b;
    }

    public void setContentPositionDataProvider(ContentPositionDataProvider contentPositionDataProvider) {
        this.b = contentPositionDataProvider;
    }
}
