package com.mi.global.shop.widget.pulltorefresh;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.util.SkinUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;

public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";

    /* renamed from: a  reason: collision with root package name */
    private ViewGroup f7272a;
    private boolean b;
    private final CustomTextView c;
    private final CustomTextView d;
    private ImageView e;
    private CharSequence f;
    private CharSequence g;
    private CharSequence h;
    private CharSequence i;
    private SimpleDraweeView j;
    private SimpleDraweeView k;
    private DraweeController l;
    private Animatable m;
    protected final ImageView mHeaderImage;
    protected final ProgressBar mHeaderProgress;
    private boolean n;

    /* access modifiers changed from: protected */
    public abstract int getDefaultDrawableResId();

    /* access modifiers changed from: protected */
    public abstract void onLoadingDrawableSet(Drawable drawable);

    /* access modifiers changed from: protected */
    public abstract void onPullImpl(float f2);

    /* access modifiers changed from: protected */
    public abstract void pullToRefreshImpl();

    /* access modifiers changed from: protected */
    public abstract void refreshingImpl();

    /* access modifiers changed from: protected */
    public abstract void releaseToRefreshImpl();

    /* access modifiers changed from: protected */
    public abstract void resetImpl();

    public LoadingLayout(Context context, TypedArray typedArray, boolean z) {
        super(context);
        this.n = z;
        if (z) {
            LayoutInflater.from(context).inflate(R.layout.shop_pull_to_refresh_header_vertical_rn, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.shop_pull_to_refresh_header_vertical, this);
        }
        this.f = context.getString(R.string.cube_ptr_pull_down);
        this.g = context.getString(R.string.cube_ptr_refreshing);
        this.h = context.getString(R.string.cube_ptr_release_to_refresh);
        this.i = context.getString(R.string.cube_ptr_refresh_complete);
        this.f7272a = (ViewGroup) findViewById(R.id.fl_inner);
        this.c = (CustomTextView) this.f7272a.findViewById(R.id.pull_to_refresh_text);
        this.mHeaderProgress = (ProgressBar) this.f7272a.findViewById(R.id.pull_to_refresh_progress);
        this.d = (CustomTextView) this.f7272a.findViewById(R.id.pull_to_refresh_sub_text);
        this.mHeaderImage = (ImageView) this.f7272a.findViewById(R.id.pull_to_refresh_image);
        if (!z) {
            this.e = (ImageView) this.f7272a.findViewById(R.id.pull_to_refresh_dancing_ellipsis);
        }
        ((FrameLayout.LayoutParams) this.f7272a.getLayoutParams()).gravity = 80;
        setLoadingDrawable(context.getResources().getDrawable(getDefaultDrawableResId()));
        reset();
    }

    public LoadingLayout(Context context, TypedArray typedArray, int i2) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.shop_pull_to_refresh_header_vertical_festivalstyle, this);
        this.j = (SimpleDraweeView) findViewById(R.id.gif_bg);
        this.k = (SimpleDraweeView) findViewById(R.id.pull_bg);
        this.l = ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(SkinUtil.c(SkinUtil.I)).setAutoPlayAnimations(true)).build();
        this.j.setController(this.l);
        this.j.setVisibility(8);
        this.m = this.l.getAnimatable();
        this.f7272a = (ViewGroup) findViewById(R.id.fl_inner);
        if (SkinUtil.c(SkinUtil.H) != null) {
            FrescoUtils.a(SkinUtil.c(SkinUtil.H), this.k);
        }
        this.c = (CustomTextView) this.f7272a.findViewById(R.id.pull_to_refresh_text);
        this.mHeaderProgress = (ProgressBar) this.f7272a.findViewById(R.id.pull_to_refresh_progress);
        this.d = (CustomTextView) this.f7272a.findViewById(R.id.pull_to_refresh_sub_text);
        this.mHeaderImage = (ImageView) this.f7272a.findViewById(R.id.pull_to_refresh_image);
        if (!this.n) {
            this.e = (ImageView) this.f7272a.findViewById(R.id.pull_to_refresh_dancing_ellipsis);
        }
        ((FrameLayout.LayoutParams) this.f7272a.getLayoutParams()).gravity = 80;
        setLoadingDrawable(context.getResources().getDrawable(getDefaultDrawableResId()));
        reset();
    }

    public final void setHeight(int i2) {
        getLayoutParams().height = i2;
        requestLayout();
    }

    public final void setWidth(int i2) {
        getLayoutParams().width = i2;
        requestLayout();
    }

    public final int getContentSize() {
        return this.f7272a.getHeight();
    }

    public final void hideAllViews() {
        if (this.c.getVisibility() == 0) {
            this.c.setVisibility(4);
        }
        if (this.mHeaderProgress.getVisibility() == 0) {
            this.mHeaderProgress.setVisibility(4);
        }
        if (this.mHeaderImage.getVisibility() == 0) {
            this.mHeaderImage.setVisibility(4);
        }
        if (this.d.getVisibility() == 0) {
            this.d.setVisibility(4);
        }
    }

    public final void onPull(float f2) {
        if (!this.b) {
            onPullImpl(f2);
        }
    }

    public final void pullToRefresh() {
        if (this.c != null) {
            this.c.setText(this.f);
        }
        this.f7272a.setVisibility(0);
        pullToRefreshImpl();
    }

    public final void refreshing() {
        if (this.c != null) {
            this.c.setText(this.g);
        }
        if (!this.n) {
            ((AnimationDrawable) this.e.getBackground()).start();
        }
        if (this.b) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).start();
        } else {
            refreshingImpl();
        }
        if (this.d != null) {
            this.d.setVisibility(8);
        }
    }

    public final void releaseToRefresh() {
        if (this.c != null) {
            this.c.setText(this.h);
        }
        releaseToRefreshImpl();
    }

    public final void reset() {
        if (this.c != null) {
            this.c.setText(this.i);
        }
        this.mHeaderImage.setVisibility(0);
        if (!this.n) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.e.getBackground();
            animationDrawable.stop();
            animationDrawable.selectDrawable(0);
        }
        if (this.b) {
            ((AnimationDrawable) this.mHeaderImage.getDrawable()).stop();
        } else {
            resetImpl();
        }
        if (this.d == null) {
            return;
        }
        if (TextUtils.isEmpty(this.d.getText())) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.mHeaderImage.setImageDrawable(drawable);
        this.b = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.f = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.g = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.h = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
        this.c.setTypeface(typeface);
    }

    public final void showInvisibleViews() {
        if (4 == this.c.getVisibility()) {
            this.c.setVisibility(0);
        }
        if (4 == this.mHeaderProgress.getVisibility()) {
            this.mHeaderProgress.setVisibility(0);
        }
        if (4 == this.mHeaderImage.getVisibility()) {
            this.mHeaderImage.setVisibility(0);
        }
        if (4 == this.d.getVisibility()) {
            this.d.setVisibility(0);
        }
    }

    private void setSubHeaderText(CharSequence charSequence) {
        if (this.d == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setText(charSequence);
        if (8 == this.d.getVisibility()) {
            this.d.setVisibility(0);
        }
    }

    private void setSubTextAppearance(int i2) {
        if (this.d != null) {
            this.d.setTextAppearance(getContext(), i2);
        }
    }

    private void setSubTextColor(ColorStateList colorStateList) {
        if (this.d != null) {
            this.d.setTextColor(colorStateList);
        }
    }

    private void setTextAppearance(int i2) {
        if (this.c != null) {
            this.c.setTextAppearance(getContext(), i2);
        }
        if (this.d != null) {
            this.d.setTextAppearance(getContext(), i2);
        }
    }

    private void setTextColor(ColorStateList colorStateList) {
        if (this.c != null) {
            this.c.setTextColor(colorStateList);
        }
        if (this.d != null) {
            this.d.setTextColor(colorStateList);
        }
    }

    public Animatable getAnimatable() {
        return this.m;
    }

    public void setAnimatable(Animatable animatable) {
        this.m = animatable;
    }

    public SimpleDraweeView getGif_bg() {
        return this.j;
    }

    public void setGif_bg(SimpleDraweeView simpleDraweeView) {
        this.j = simpleDraweeView;
    }

    public boolean isRn() {
        return this.n;
    }

    public void setRn(boolean z) {
        this.n = z;
    }
}
