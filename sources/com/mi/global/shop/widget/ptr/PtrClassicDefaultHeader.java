package com.mi.global.shop.widget.ptr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PtrClassicDefaultHeader extends FrameLayout implements PtrUIHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7243a = "cube_ptr_classic_last_update";
    private int b = 150;
    private RotateAnimation c;
    private RotateAnimation d;
    private CustomTextView e;
    private View f;
    private View g;
    private long h = -1;
    private CustomTextView i;
    /* access modifiers changed from: private */
    public String j;
    private String k;
    private boolean l;
    private LastUpdateTimeUpdater m = new LastUpdateTimeUpdater();

    public PtrClassicDefaultHeader(Context context) {
        super(context);
        initViews((AttributeSet) null);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(attributeSet);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initViews(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void initViews(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PtrClassicHeader, 0, 0);
        if (obtainStyledAttributes != null) {
            this.b = obtainStyledAttributes.getInt(R.styleable.PtrClassicHeader_ptr_rotate_ani_time, this.b);
        }
        a();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.shop_cube_ptr_classic_default_header, this);
        this.f = inflate.findViewById(R.id.ptr_classic_header_rotate_view);
        this.e = (CustomTextView) inflate.findViewById(R.id.ptr_classic_header_rotate_view_header_title);
        this.i = (CustomTextView) inflate.findViewById(R.id.ptr_classic_header_rotate_view_header_last_update);
        this.g = inflate.findViewById(R.id.ptr_classic_header_rotate_view_progressbar);
        b();
    }

    public void setRotateAniTime(int i2) {
        if (i2 != this.b && i2 != 0) {
            this.b = i2;
            a();
        }
    }

    public void setLastUpdateTimeKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public void setLastRefreshTime(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.k = str;
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeKey(obj.getClass().getName());
    }

    private void a() {
        this.c = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.setDuration((long) this.b);
        this.c.setFillAfter(true);
        this.d = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.setDuration((long) this.b);
        this.d.setFillAfter(true);
    }

    private void b() {
        c();
        this.g.setVisibility(4);
    }

    private void c() {
        this.f.clearAnimation();
        this.f.setVisibility(4);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        b();
        this.l = true;
        d();
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.l = true;
        d();
        this.m.a();
        this.g.setVisibility(4);
        this.f.setVisibility(0);
        this.e.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.l = false;
        c();
        this.g.setVisibility(0);
        this.e.setVisibility(0);
        this.e.setText(R.string.cube_ptr_refreshing);
        d();
        this.m.b();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        c();
        this.g.setVisibility(4);
        this.e.setVisibility(0);
        this.e.setText(getResources().getString(R.string.cube_ptr_refresh_complete));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(f7243a, 0);
        if (!TextUtils.isEmpty(this.j)) {
            this.h = new Date().getTime();
            sharedPreferences.edit().putLong(this.j, this.h).commit();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (TextUtils.isEmpty(this.j) || !this.l) {
            this.i.setVisibility(8);
            return;
        }
        String lastUpdateTime = getLastUpdateTime();
        if (TextUtils.isEmpty(lastUpdateTime)) {
            this.i.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.i.setText(lastUpdateTime);
    }

    private String getLastUpdateTime() {
        if (this.h == -1 && !TextUtils.isEmpty(this.j)) {
            this.h = getContext().getSharedPreferences(f7243a, 0).getLong(this.j, -1);
        }
        if (this.h == -1) {
            return null;
        }
        long time = new Date().getTime() - this.h;
        int i2 = (int) (time / 1000);
        if (time < 0 || i2 <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getContext().getString(R.string.cube_ptr_last_update));
        if (i2 < 60) {
            sb.append(i2 + getContext().getString(R.string.cube_ptr_seconds_ago));
        } else {
            int i3 = i2 / 60;
            if (i3 > 60) {
                int i4 = i3 / 60;
                if (i4 > 24) {
                    sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.h)));
                } else {
                    sb.append(i4 + getContext().getString(R.string.cube_ptr_hours_ago));
                }
            } else {
                sb.append(i3 + getContext().getString(R.string.cube_ptr_minutes_ago));
            }
        }
        return sb.toString();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, int i2, int i3, float f2, float f3) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        if (i3 >= offsetToRefresh || i2 < offsetToRefresh) {
            if (i3 > offsetToRefresh && i2 <= offsetToRefresh && z && b2 == 2) {
                a(ptrFrameLayout);
                if (this.f != null) {
                    this.f.clearAnimation();
                    this.f.startAnimation(this.c);
                }
            }
        } else if (z && b2 == 2) {
            b(ptrFrameLayout);
            if (this.f != null) {
                this.f.clearAnimation();
                this.f.startAnimation(this.d);
            }
        }
    }

    private void a(PtrFrameLayout ptrFrameLayout) {
        if (!ptrFrameLayout.isPullToRefresh()) {
            this.e.setVisibility(0);
            this.e.setText(R.string.cube_ptr_release_to_refresh);
        }
    }

    private void b(PtrFrameLayout ptrFrameLayout) {
        this.e.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }

    private class LastUpdateTimeUpdater implements Runnable {
        private boolean b;

        private LastUpdateTimeUpdater() {
            this.b = false;
        }

        /* access modifiers changed from: private */
        public void a() {
            if (!TextUtils.isEmpty(PtrClassicDefaultHeader.this.j)) {
                this.b = true;
                run();
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            this.b = false;
            PtrClassicDefaultHeader.this.removeCallbacks(this);
        }

        public void run() {
            PtrClassicDefaultHeader.this.d();
            if (this.b) {
                PtrClassicDefaultHeader.this.postDelayed(this, 1000);
            }
        }
    }
}
