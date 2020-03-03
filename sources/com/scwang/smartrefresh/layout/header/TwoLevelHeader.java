package com.scwang.smartrefresh.layout.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.OnTwoLevelListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

public class TwoLevelHeader extends InternalAbstract implements RefreshHeader {
    protected boolean mEnablePullToCloseTwoLevel;
    protected boolean mEnableTwoLevel;
    protected int mFloorDuration;
    protected float mFloorRage;
    protected int mHeaderHeight;
    protected float mMaxRage;
    protected float mPercent;
    protected RefreshHeader mRefreshHeader;
    protected RefreshKernel mRefreshKernel;
    protected float mRefreshRage;
    protected int mSpinner;
    protected OnTwoLevelListener mTwoLevelListener;

    @NonNull
    public View getView() {
        return this;
    }

    public TwoLevelHeader(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPercent = 0.0f;
        this.mMaxRage = 2.5f;
        this.mFloorRage = 1.9f;
        this.mRefreshRage = 1.0f;
        this.mEnableTwoLevel = true;
        this.mEnablePullToCloseTwoLevel = true;
        this.mFloorDuration = 1000;
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TwoLevelHeader);
        this.mMaxRage = obtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlMaxRage, this.mMaxRage);
        this.mFloorRage = obtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlFloorRage, this.mFloorRage);
        this.mRefreshRage = obtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlRefreshRage, this.mRefreshRage);
        this.mFloorDuration = obtainStyledAttributes.getInt(R.styleable.TwoLevelHeader_srlFloorDuration, this.mFloorDuration);
        this.mEnableTwoLevel = obtainStyledAttributes.getBoolean(R.styleable.TwoLevelHeader_srlEnableTwoLevel, this.mEnableTwoLevel);
        this.mEnablePullToCloseTwoLevel = obtainStyledAttributes.getBoolean(R.styleable.TwoLevelHeader_srlEnablePullToCloseTwoLevel, this.mEnablePullToCloseTwoLevel);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof RefreshHeader) {
                this.mWrapperView = childAt;
                this.mRefreshHeader = (RefreshHeader) childAt;
                bringChildToFront(childAt);
                break;
            }
            i++;
        }
        if (this.mRefreshHeader == null) {
            this.mRefreshHeader = new RefreshHeaderWrapper(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
        if (this.mRefreshHeader == null) {
            this.mRefreshHeader = new RefreshHeaderWrapper(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mRefreshHeader.getView() == this) {
            super.onMeasure(i, i2);
        } else if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            this.mRefreshHeader.getView().measure(i, i2);
            super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), this.mRefreshHeader.getView().getMeasuredHeight());
        } else {
            super.onMeasure(i, i2);
        }
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        if ((((float) (i2 + i)) * 1.0f) / ((float) i) == this.mMaxRage || this.mHeaderHeight != 0) {
            if (!isInEditMode() && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate && this.mRefreshKernel == null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRefreshHeader.getView().getLayoutParams();
                marginLayoutParams.topMargin -= i;
                this.mRefreshHeader.getView().setLayoutParams(marginLayoutParams);
            }
            this.mHeaderHeight = i;
            this.mRefreshKernel = refreshKernel;
            this.mRefreshKernel.b(this.mFloorDuration);
            this.mRefreshHeader.onInitialized(this.mRefreshKernel, i, i2);
            this.mRefreshKernel.a((RefreshInternal) this, !this.mEnablePullToCloseTwoLevel);
            return;
        }
        this.mHeaderHeight = i;
        refreshKernel.a().setHeaderMaxDragRate(this.mMaxRage);
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        this.mRefreshHeader.onStateChanged(refreshLayout, refreshState, refreshState2);
        switch (refreshState2) {
            case TwoLevelReleased:
                if (this.mRefreshHeader.getView() != this) {
                    this.mRefreshHeader.getView().animate().alpha(0.0f).setDuration((long) (this.mFloorDuration / 2));
                }
                this.mRefreshKernel.a(this.mTwoLevelListener == null || this.mTwoLevelListener.a(refreshLayout));
                return;
            case TwoLevelFinish:
                if (this.mRefreshHeader.getView() != this) {
                    this.mRefreshHeader.getView().animate().alpha(1.0f).setDuration((long) (this.mFloorDuration / 2));
                    return;
                }
                return;
            case PullDownToRefresh:
                if (this.mRefreshHeader.getView().getAlpha() == 0.0f && this.mRefreshHeader.getView() != this) {
                    this.mRefreshHeader.getView().setAlpha(1.0f);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        moveSpinner(i);
        this.mRefreshHeader.onMoving(z, f, i, i2, i3);
        if (z) {
            if (this.mPercent < this.mFloorRage && f >= this.mFloorRage && this.mEnableTwoLevel) {
                this.mRefreshKernel.a(RefreshState.ReleaseToTwoLevel);
            } else if (this.mPercent >= this.mFloorRage && f < this.mRefreshRage) {
                this.mRefreshKernel.a(RefreshState.PullDownToRefresh);
            } else if (this.mPercent >= this.mFloorRage && f < this.mFloorRage) {
                this.mRefreshKernel.a(RefreshState.ReleaseToRefresh);
            }
            this.mPercent = f;
        }
    }

    /* access modifiers changed from: protected */
    public void moveSpinner(int i) {
        if (this.mSpinner != i && this.mRefreshHeader.getView() != this) {
            this.mSpinner = i;
            switch (this.mRefreshHeader.getSpinnerStyle()) {
                case Translate:
                    this.mRefreshHeader.getView().setTranslationY((float) i);
                    return;
                case Scale:
                    View view = this.mRefreshHeader.getView();
                    view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getTop() + Math.max(0, i));
                    return;
                default:
                    return;
            }
        }
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader, int i, int i2) {
        if (refreshHeader != null) {
            if (this.mRefreshHeader != null) {
                removeView(this.mRefreshHeader.getView());
            }
            this.mRefreshHeader = refreshHeader;
            if (refreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                addView(this.mRefreshHeader.getView(), 0, new RelativeLayout.LayoutParams(i, i2));
            } else {
                addView(this.mRefreshHeader.getView(), i, i2);
            }
        }
        return this;
    }

    public TwoLevelHeader setMaxRage(float f) {
        if (this.mMaxRage != f) {
            this.mMaxRage = f;
            if (this.mRefreshKernel != null) {
                this.mHeaderHeight = 0;
                this.mRefreshKernel.a().setHeaderMaxDragRate(this.mMaxRage);
            }
        }
        return this;
    }

    public TwoLevelHeader setEnablePullToCloseTwoLevel(boolean z) {
        this.mEnablePullToCloseTwoLevel = z;
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.a((RefreshInternal) this, !z);
        }
        return this;
    }

    public TwoLevelHeader setFloorRage(float f) {
        this.mFloorRage = f;
        return this;
    }

    public TwoLevelHeader setRefreshRage(float f) {
        this.mRefreshRage = f;
        return this;
    }

    public TwoLevelHeader setEnableTwoLevel(boolean z) {
        this.mEnableTwoLevel = z;
        return this;
    }

    public TwoLevelHeader setFloorDuration(int i) {
        this.mFloorDuration = i;
        return this;
    }

    public TwoLevelHeader setOnTwoLevelListener(OnTwoLevelListener onTwoLevelListener) {
        this.mTwoLevelListener = onTwoLevelListener;
        return this;
    }

    public TwoLevelHeader finishTwoLevel() {
        this.mRefreshKernel.c();
        return this;
    }
}
