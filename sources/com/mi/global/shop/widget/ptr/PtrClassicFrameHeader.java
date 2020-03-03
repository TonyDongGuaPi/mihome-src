package com.mi.global.shop.widget.ptr;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;
import java.util.Date;

public class PtrClassicFrameHeader extends FrameLayout implements PtrUIHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7245a = "cube_ptr_classic_last_update";
    private boolean b;
    private long c = -1;
    /* access modifiers changed from: private */
    public String d;
    private boolean e;
    private String f;
    private FrameLayout g;
    private CustomTextView h;
    private CustomTextView i;
    private ImageView j;
    private LastUpdateTimeUpdater k = new LastUpdateTimeUpdater();
    protected ImageView mHeaderImage;
    protected ProgressBar mHeaderProgress;

    public PtrClassicFrameHeader(Context context) {
        super(context);
        initViews(context);
    }

    /* access modifiers changed from: protected */
    public void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.shop_pull_to_refresh_header_vertical, this);
        this.g = (FrameLayout) findViewById(R.id.fl_inner);
        this.h = (CustomTextView) this.g.findViewById(R.id.pull_to_refresh_text);
        this.mHeaderProgress = (ProgressBar) this.g.findViewById(R.id.pull_to_refresh_progress);
        this.i = (CustomTextView) this.g.findViewById(R.id.pull_to_refresh_sub_text);
        this.mHeaderImage = (ImageView) this.g.findViewById(R.id.pull_to_refresh_image);
        this.j = (ImageView) this.g.findViewById(R.id.pull_to_refresh_dancing_ellipsis);
        setLoadingDrawable(context.getResources().getDrawable(R.drawable.shop_mi_rabbit));
        reset();
    }

    public final void reset() {
        this.mHeaderImage.setVisibility(0);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.j.getBackground();
        animationDrawable.stop();
        animationDrawable.selectDrawable(0);
        if (this.b) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).stop();
        }
        if (this.i == null) {
            return;
        }
        if (TextUtils.isEmpty(this.i.getText())) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
        }
    }

    public void setLoadingDrawable(Drawable drawable) {
        this.mHeaderImage.setImageDrawable(drawable);
        this.b = drawable instanceof AnimationDrawable;
    }

    public void setLastUpdateTimeKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d = str;
        }
    }

    public void setLastRefreshTime(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeKey(obj.getClass().getName());
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        reset();
        this.e = true;
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.e = true;
        this.k.a();
        this.mHeaderImage.setVisibility(0);
        this.h.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.h.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.h.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.e = false;
        ((AnimationDrawable) this.j.getBackground()).start();
        if (this.b) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).start();
        }
        this.h.setVisibility(0);
        this.h.setText(R.string.cube_ptr_refreshing);
        this.k.b();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        this.h.setVisibility(0);
        this.h.setText(getResources().getString(R.string.cube_ptr_refresh_complete));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(f7245a, 0);
        if (!TextUtils.isEmpty(this.d)) {
            this.c = new Date().getTime();
            sharedPreferences.edit().putLong(this.d, this.c).commit();
        }
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, int i2, int i3, float f2, float f3) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        if (i3 >= offsetToRefresh || i2 < offsetToRefresh) {
            if (i3 > offsetToRefresh && i2 <= offsetToRefresh && z && b2 == 2) {
                a(ptrFrameLayout);
            }
        } else if (z && b2 == 2) {
            b(ptrFrameLayout);
        }
    }

    private void a(PtrFrameLayout ptrFrameLayout) {
        if (!ptrFrameLayout.isPullToRefresh()) {
            this.h.setVisibility(0);
            this.h.setText(R.string.cube_ptr_release_to_refresh);
        }
    }

    private void b(PtrFrameLayout ptrFrameLayout) {
        this.h.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.h.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.h.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }

    private class LastUpdateTimeUpdater implements Runnable {
        private boolean b;

        private LastUpdateTimeUpdater() {
            this.b = false;
        }

        /* access modifiers changed from: private */
        public void a() {
            if (!TextUtils.isEmpty(PtrClassicFrameHeader.this.d)) {
                this.b = true;
                run();
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            this.b = false;
            PtrClassicFrameHeader.this.removeCallbacks(this);
        }

        public void run() {
            if (this.b) {
                PtrClassicFrameHeader.this.postDelayed(this, 1000);
            }
        }
    }
}
