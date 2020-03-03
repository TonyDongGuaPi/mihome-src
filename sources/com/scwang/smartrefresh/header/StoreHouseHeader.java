package com.scwang.smartrefresh.header;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.libra.Color;
import com.scwang.smartrefresh.header.storehouse.StoreHouseBarItem;
import com.scwang.smartrefresh.header.storehouse.StoreHousePath;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import java.util.ArrayList;
import java.util.List;

public class StoreHouseHeader extends InternalAbstract implements RefreshHeader {
    protected static final float mBarDarkAlpha = 0.4f;
    protected static final float mFromAlpha = 1.0f;
    protected static final float mInternalAnimationFactor = 0.7f;
    protected static final int mLoadingAniItemDuration = 400;
    protected static final float mToAlpha = 0.4f;
    protected AniController mAniController;
    protected int mBackgroundColor;
    protected int mDrawZoneHeight;
    protected int mDrawZoneWidth;
    protected int mDropHeight;
    protected boolean mEnableFadeAnimation;
    protected int mHorizontalRandomness;
    protected boolean mIsInLoading;
    public List<StoreHouseBarItem> mItemList;
    protected int mLineWidth;
    protected int mLoadingAniDuration;
    protected int mLoadingAniSegDuration;
    protected Matrix mMatrix;
    protected int mOffsetX;
    protected int mOffsetY;
    protected float mProgress;
    protected RefreshKernel mRefreshKernel;
    protected float mScale;
    protected int mTextColor;
    protected Transformation mTransformation;

    public StoreHouseHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mItemList = new ArrayList();
        this.mLineWidth = -1;
        this.mScale = 1.0f;
        this.mDropHeight = -1;
        this.mHorizontalRandomness = -1;
        this.mProgress = 0.0f;
        this.mDrawZoneWidth = 0;
        this.mDrawZoneHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mLoadingAniDuration = 1000;
        this.mLoadingAniSegDuration = 1000;
        this.mTextColor = -1;
        this.mBackgroundColor = 0;
        this.mIsInLoading = false;
        this.mEnableFadeAnimation = false;
        this.mMatrix = new Matrix();
        this.mAniController = new AniController();
        this.mTransformation = new Transformation();
        DensityUtil densityUtil = new DensityUtil();
        this.mLineWidth = densityUtil.b(1.0f);
        this.mDropHeight = densityUtil.b(40.0f);
        this.mHorizontalRandomness = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        this.mBackgroundColor = -13421773;
        setTextColor(Color.d);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StoreHouseHeader);
        this.mLineWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.StoreHouseHeader_shhLineWidth, this.mLineWidth);
        this.mDropHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.StoreHouseHeader_shhDropHeight, this.mDropHeight);
        this.mEnableFadeAnimation = obtainStyledAttributes.getBoolean(R.styleable.StoreHouseHeader_shhEnableFadeAnimation, this.mEnableFadeAnimation);
        if (obtainStyledAttributes.hasValue(R.styleable.StoreHouseHeader_shhText)) {
            initWithString(obtainStyledAttributes.getString(R.styleable.StoreHouseHeader_shhText));
        } else {
            initWithString("StoreHouse");
        }
        obtainStyledAttributes.recycle();
        setMinimumHeight(this.mDrawZoneHeight + DensityUtil.a(40.0f));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), View.resolveSize(super.getSuggestedMinimumHeight(), i2));
        this.mOffsetX = (getMeasuredWidth() - this.mDrawZoneWidth) / 2;
        this.mOffsetY = (getMeasuredHeight() - this.mDrawZoneHeight) / 2;
        this.mDropHeight = getMeasuredHeight() / 2;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        int size = this.mItemList.size();
        float f = isInEditMode() ? 1.0f : this.mProgress;
        for (int i = 0; i < size; i++) {
            canvas.save();
            StoreHouseBarItem storeHouseBarItem = this.mItemList.get(i);
            float f2 = ((float) this.mOffsetX) + storeHouseBarItem.f8759a.x;
            float f3 = ((float) this.mOffsetY) + storeHouseBarItem.f8759a.y;
            if (this.mIsInLoading) {
                storeHouseBarItem.getTransformation(getDrawingTime(), this.mTransformation);
                canvas.translate(f2, f3);
            } else {
                float f4 = 0.0f;
                if (f == 0.0f) {
                    storeHouseBarItem.c(this.mHorizontalRandomness);
                } else {
                    float f5 = (((float) i) * 0.3f) / ((float) size);
                    float f6 = 0.3f - f5;
                    if (f == 1.0f || f >= 1.0f - f6) {
                        canvas.translate(f2, f3);
                        storeHouseBarItem.a(0.4f);
                    } else {
                        if (f > f5) {
                            f4 = Math.min(1.0f, (f - f5) / 0.7f);
                        }
                        float f7 = 1.0f - f4;
                        float f8 = f2 + (storeHouseBarItem.b * f7);
                        float f9 = f3 + (((float) (-this.mDropHeight)) * f7);
                        this.mMatrix.reset();
                        this.mMatrix.postRotate(360.0f * f4);
                        this.mMatrix.postScale(f4, f4);
                        this.mMatrix.postTranslate(f8, f9);
                        storeHouseBarItem.a(f4 * 0.4f);
                        canvas.concat(this.mMatrix);
                    }
                }
            }
            storeHouseBarItem.a(canvas);
            canvas.restore();
        }
        if (this.mIsInLoading) {
            invalidate();
        }
        canvas.restoreToCount(save);
        super.dispatchDraw(canvas);
    }

    public StoreHouseHeader setLoadingAniDuration(int i) {
        this.mLoadingAniDuration = i;
        this.mLoadingAniSegDuration = i;
        return this;
    }

    public StoreHouseHeader setLineWidth(int i) {
        this.mLineWidth = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            this.mItemList.get(i2).a(i);
        }
        return this;
    }

    public StoreHouseHeader setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            this.mItemList.get(i2).b(i);
        }
        return this;
    }

    public StoreHouseHeader setDropHeight(int i) {
        this.mDropHeight = i;
        return this;
    }

    public StoreHouseHeader initWithString(String str) {
        initWithString(str, 25);
        return this;
    }

    public StoreHouseHeader initWithString(String str, int i) {
        initWithPointList(StoreHousePath.a(str, ((float) i) * 0.01f, 14));
        return this;
    }

    public StoreHouseHeader initWithStringArray(int i) {
        String[] stringArray = getResources().getStringArray(i);
        ArrayList arrayList = new ArrayList();
        for (String split : stringArray) {
            String[] split2 = split.split(",");
            float[] fArr = new float[4];
            for (int i2 = 0; i2 < 4; i2++) {
                fArr[i2] = Float.parseFloat(split2[i2]);
            }
            arrayList.add(fArr);
        }
        initWithPointList(arrayList);
        return this;
    }

    public StoreHouseHeader setScale(float f) {
        this.mScale = f;
        return this;
    }

    public StoreHouseHeader initWithPointList(List<float[]> list) {
        boolean z = this.mItemList.size() > 0;
        this.mItemList.clear();
        DensityUtil densityUtil = new DensityUtil();
        int i = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        while (i < list.size()) {
            float[] fArr = list.get(i);
            PointF pointF = new PointF(((float) densityUtil.b(fArr[0])) * this.mScale, ((float) densityUtil.b(fArr[1])) * this.mScale);
            PointF pointF2 = new PointF(((float) densityUtil.b(fArr[2])) * this.mScale, ((float) densityUtil.b(fArr[3])) * this.mScale);
            float max = Math.max(Math.max(f, pointF.x), pointF2.x);
            float max2 = Math.max(Math.max(f2, pointF.y), pointF2.y);
            StoreHouseBarItem storeHouseBarItem = new StoreHouseBarItem(i, pointF, pointF2, this.mTextColor, this.mLineWidth);
            storeHouseBarItem.c(this.mHorizontalRandomness);
            this.mItemList.add(storeHouseBarItem);
            i++;
            f = max;
            f2 = max2;
        }
        this.mDrawZoneWidth = (int) Math.ceil((double) f);
        this.mDrawZoneHeight = (int) Math.ceil((double) f2);
        if (z) {
            requestLayout();
        }
        return this;
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        this.mRefreshKernel.a((RefreshInternal) this, this.mBackgroundColor);
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        this.mProgress = f * 0.8f;
        invalidate();
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mIsInLoading = true;
        this.mAniController.a();
        invalidate();
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mIsInLoading = false;
        this.mAniController.b();
        if (!z || !this.mEnableFadeAnimation) {
            for (int i = 0; i < this.mItemList.size(); i++) {
                this.mItemList.get(i).c(this.mHorizontalRandomness);
            }
            return 0;
        }
        startAnimation(new Animation() {
            {
                super.setDuration(250);
                super.setInterpolator(new AccelerateInterpolator());
            }

            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                StoreHouseHeader storeHouseHeader = StoreHouseHeader.this;
                StoreHouseHeader.this.mProgress = 1.0f - f;
                storeHouseHeader.invalidate();
                if (f == 1.0f) {
                    for (int i = 0; i < StoreHouseHeader.this.mItemList.size(); i++) {
                        StoreHouseHeader.this.mItemList.get(i).c(StoreHouseHeader.this.mHorizontalRandomness);
                    }
                }
            }
        });
        return 250;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mBackgroundColor = iArr[0];
            if (this.mRefreshKernel != null) {
                this.mRefreshKernel.a((RefreshInternal) this, this.mBackgroundColor);
            }
            if (iArr.length > 1) {
                setTextColor(iArr[1]);
            }
        }
    }

    private class AniController implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f8738a;
        int b;
        int c;
        int d;
        boolean e;

        private AniController() {
            this.f8738a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = true;
        }

        /* access modifiers changed from: private */
        public void a() {
            this.e = true;
            this.f8738a = 0;
            this.d = StoreHouseHeader.this.mLoadingAniDuration / StoreHouseHeader.this.mItemList.size();
            this.b = StoreHouseHeader.this.mLoadingAniSegDuration / this.d;
            this.c = (StoreHouseHeader.this.mItemList.size() / this.b) + 1;
            run();
        }

        public void run() {
            int i = this.f8738a % this.b;
            for (int i2 = 0; i2 < this.c; i2++) {
                int i3 = (this.b * i2) + i;
                if (i3 <= this.f8738a) {
                    StoreHouseBarItem storeHouseBarItem = StoreHouseHeader.this.mItemList.get(i3 % StoreHouseHeader.this.mItemList.size());
                    storeHouseBarItem.setFillAfter(false);
                    storeHouseBarItem.setFillEnabled(true);
                    storeHouseBarItem.setFillBefore(false);
                    storeHouseBarItem.setDuration(400);
                    storeHouseBarItem.a(1.0f, 0.4f);
                }
            }
            this.f8738a++;
            if (this.e && StoreHouseHeader.this.mRefreshKernel != null) {
                StoreHouseHeader.this.mRefreshKernel.a().getLayout().postDelayed(this, (long) this.d);
            }
        }

        /* access modifiers changed from: private */
        public void b() {
            this.e = false;
            StoreHouseHeader.this.removeCallbacks(this);
        }
    }
}
