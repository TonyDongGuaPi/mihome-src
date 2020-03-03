package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class SmartHomePtrHeaderWhite extends FrameLayout implements PtrUIHandler {

    /* renamed from: a  reason: collision with root package name */
    private TextView f18933a;
    private ImageView b;
    private ProgressBar c;
    private View d;
    private String e;
    private Animation f;
    private Animation g;
    private boolean h = false;

    private void b() {
    }

    public SmartHomePtrHeaderWhite(Context context) {
        super(context);
        initViews((AttributeSet) null);
    }

    public SmartHomePtrHeaderWhite(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(attributeSet);
    }

    public SmartHomePtrHeaderWhite(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void initViews(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PtrHeader);
            obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
        }
        this.d = LayoutInflater.from(getContext()).inflate(R.layout.pull_header_ptr_white, (ViewGroup) null);
        this.f18933a = (TextView) this.d.findViewById(R.id.pull_header_txt);
        this.b = (ImageView) this.d.findViewById(R.id.pull_header_indc);
        this.c = (ProgressBar) this.d.findViewById(R.id.pull_header_prog);
        addView(this.d);
        a();
        switchHeaderView(false);
    }

    public void switchHeaderView(boolean z) {
        if (this.d != null) {
            if (z) {
                this.d.setBackgroundResource(R.color.lite_scene_bg_color);
                this.f18933a.setVisibility(8);
                this.b.setVisibility(8);
                this.c.setVisibility(8);
            } else {
                this.d.setBackgroundResource(R.color.transparent);
                this.c.setVisibility(4);
                this.b.setVisibility(0);
                this.f18933a.setVisibility(0);
            }
            this.h = z;
        }
    }

    public boolean isLiteMode() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent != null) {
            ((PtrFrameLayout) parent).addPtrUIHandler(this);
        }
    }

    public void setLastUpdateTimeKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeKey(obj.getClass().getName());
    }

    private void a() {
        b();
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        a();
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        if (!this.h) {
            this.c.setVisibility(4);
            this.b.setVisibility(0);
            this.f18933a.setVisibility(0);
            this.f18933a.setText(getResources().getString(R.string.pull_down_refresh));
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        b();
        if (!this.h) {
            this.c.setVisibility(0);
            this.f18933a.setVisibility(0);
            this.f18933a.setText(R.string.refreshing);
            this.b.setVisibility(8);
        }
        this.b.clearAnimation();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        b();
        if (!this.h) {
            this.c.setVisibility(4);
            this.b.setVisibility(0);
            this.f18933a.setVisibility(0);
            this.f18933a.setText(getResources().getString(R.string.refresh_complete));
        }
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, PtrIndicator ptrIndicator) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (currentPosY >= offsetToRefresh || lastPosY < offsetToRefresh) {
            if (currentPosY > offsetToRefresh && lastPosY <= offsetToRefresh && z && b2 == 2) {
                a(ptrFrameLayout);
                if (this.g == null) {
                    this.g = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_180);
                    this.g.setFillAfter(true);
                }
                this.b.startAnimation(this.g);
            }
        } else if (z && b2 == 2) {
            b(ptrFrameLayout);
            if (this.f == null) {
                this.f = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_back_180);
                this.f.setFillAfter(true);
            }
            this.b.startAnimation(this.f);
        }
    }

    private void a(PtrFrameLayout ptrFrameLayout) {
        if (!ptrFrameLayout.isPullToRefresh()) {
            if (!this.h) {
                this.f18933a.setVisibility(0);
            }
            this.f18933a.setText(R.string.release_to_refresh);
        }
    }

    private void b(PtrFrameLayout ptrFrameLayout) {
        if (!this.h) {
            this.f18933a.setVisibility(0);
        }
        this.f18933a.setText(getResources().getString(R.string.pull_down_refresh));
    }

    public void setProgressBarDrawable(Drawable drawable) {
        this.c.setIndeterminateDrawable(drawable);
    }

    public void setRefreshArrow(int i) {
        this.b.setBackgroundResource(i);
    }

    public void setHeaderTextColor(int i) {
        this.f18933a.setTextColor(i);
    }
}
