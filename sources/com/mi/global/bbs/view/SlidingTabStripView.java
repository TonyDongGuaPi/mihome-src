package com.mi.global.bbs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.view.Editor.FontTextView;
import java.util.List;

public class SlidingTabStripView extends HorizontalScrollView {
    private static final int[] ATTRS = {16842901};
    /* access modifiers changed from: private */
    public int currentPosition;
    private float currentPositionOffset;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private int indicatorColor;
    private int indicatorHeight;
    private boolean isUpperCase;
    private int lastScrollX;
    OnSelectedListener onSelectedListener;
    private Paint rectPaint;
    private int scrollOffset;
    private int selectTextColor;
    private int tabBackgroundResId;
    private int tabCount;
    private int tabMarginLeft;
    private int tabMarginRight;
    private int tabPaddingLeft;
    private int tabPaddingTop;
    private int tabTextColor;
    private int tabTextSize;
    private Typeface tabTypeface;
    private int tabTypefaceStyle;
    private LinearLayout tabsContainer;
    private int underlineColor;
    private float underlineHeight;

    public interface OnSelectedListener {
        void onSelected(int i);
    }

    public SlidingTabStripView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingTabStripView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabStripView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes;
        this.currentPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.indicatorColor = -10066330;
        this.scrollOffset = 52;
        this.indicatorHeight = 2;
        this.tabPaddingTop = 14;
        this.tabMarginLeft = 19;
        this.tabMarginRight = 8;
        this.tabPaddingLeft = 4;
        this.tabTextSize = 12;
        this.tabTextColor = Color.parseColor("#666666");
        this.underlineColor = Color.parseColor("#dad9d7");
        this.tabTypeface = null;
        this.tabTypefaceStyle = 0;
        this.lastScrollX = 0;
        this.tabBackgroundResId = Color.parseColor("#00000000");
        this.selectTextColor = Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT);
        this.underlineHeight = 0.5f;
        this.isUpperCase = false;
        setFillViewport(true);
        setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(context);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        addView(this.tabsContainer);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, (float) this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, (float) this.indicatorHeight, displayMetrics);
        this.underlineHeight = TypedValue.applyDimension(1, this.underlineHeight, displayMetrics);
        this.tabPaddingTop = (int) TypedValue.applyDimension(1, (float) this.tabPaddingTop, displayMetrics);
        this.tabPaddingLeft = (int) TypedValue.applyDimension(1, (float) this.tabPaddingLeft, displayMetrics);
        this.tabMarginLeft = (int) TypedValue.applyDimension(1, (float) this.tabMarginLeft, displayMetrics);
        this.tabMarginRight = (int) TypedValue.applyDimension(1, (float) this.tabMarginRight, displayMetrics);
        this.tabTextSize = (int) TypedValue.applyDimension(2, (float) this.tabTextSize, displayMetrics);
        if (!(attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS)) == null)) {
            this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
            obtainStyledAttributes.recycle();
        }
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    }

    public void notifyDataSetChanged(List<String> list) {
        this.tabsContainer.removeAllViews();
        this.tabCount = 0;
        if (list != null && list.size() > 0) {
            this.tabCount = list.size();
        }
        for (int i = 0; i < this.tabCount; i++) {
            addTextTab(i, list.get(i));
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SlidingTabStripView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                SlidingTabStripView.this.setCurrentPosition(SlidingTabStripView.this.currentPosition);
            }
        });
    }

    private void addTextTab(int i, String str) {
        FontTextView fontTextView = new FontTextView(getContext());
        if (this.isUpperCase) {
            str = str.toUpperCase();
        }
        fontTextView.setText(str);
        fontTextView.setGravity(17);
        fontTextView.setSingleLine();
        addTab(i, fontTextView);
    }

    private void addTab(final int i, View view) {
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SlidingTabStripView.this.setCurrentPosition(i);
                SlidingTabStripView.this.dispatchOnSelected(i);
            }
        });
        view.setPadding(this.tabPaddingLeft, this.tabPaddingTop, this.tabPaddingLeft, this.tabPaddingTop);
        this.defaultTabLayoutParams.rightMargin = this.tabMarginRight;
        this.defaultTabLayoutParams.leftMargin = this.tabMarginLeft;
        this.tabsContainer.addView(view, i, this.defaultTabLayoutParams);
    }

    public void setCurrentPosition(int i) {
        if (i != this.currentPosition) {
            this.currentPosition = i;
            updateTabStyles();
            int i2 = 0;
            if (i == 0) {
                i2 = this.tabMarginLeft;
            }
            if (i == this.tabCount) {
                i2 = this.tabMarginRight;
            }
            scrollToChild(i, i2);
            invalidate();
        }
    }

    private void updateTabStyles() {
        for (int i = 0; i < this.tabCount; i++) {
            View childAt = this.tabsContainer.getChildAt(i);
            childAt.setBackgroundResource(17170445);
            if (childAt instanceof FontTextView) {
                FontTextView fontTextView = (FontTextView) childAt;
                fontTextView.setTextSize(0, (float) this.tabTextSize);
                fontTextView.setTypeface(this.tabTypeface, this.tabTypefaceStyle);
                fontTextView.setTextColor(this.tabTextColor);
                if (i == this.currentPosition) {
                    fontTextView.setTextColor(this.selectTextColor);
                }
            }
        }
    }

    private void scrollToChild(int i, int i2) {
        if (this.tabCount != 0) {
            int left = this.tabsContainer.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.scrollOffset;
            }
            if (left != this.lastScrollX) {
                this.lastScrollX = left;
                scrollTo(left, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void dispatchOnSelected(int i) {
        if (this.onSelectedListener != null) {
            this.onSelectedListener.onSelected(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        if (!isInEditMode() && this.tabCount != 0) {
            int height = getHeight();
            this.rectPaint.setColor(this.underlineColor);
            float f3 = (float) height;
            canvas.drawRect(0.0f, f3 - this.underlineHeight, (float) this.tabsContainer.getWidth(), f3, this.rectPaint);
            this.rectPaint.setColor(this.indicatorColor);
            View childAt = this.tabsContainer.getChildAt(this.currentPosition);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.currentPositionOffset <= 0.0f || this.currentPosition >= this.tabCount - 1) {
                f = right;
                f2 = left;
            } else {
                View childAt2 = this.tabsContainer.getChildAt(this.currentPosition + 1);
                float f4 = this.currentPositionOffset;
                f = (this.currentPositionOffset * ((float) childAt2.getRight())) + ((1.0f - this.currentPositionOffset) * right);
                f2 = (f4 * ((float) childAt2.getLeft())) + ((1.0f - this.currentPositionOffset) * left);
            }
            canvas.drawRect(f2, (float) (height - this.indicatorHeight), f, f3, this.rectPaint);
        }
    }

    public void setIndicatorColorResource(int i) {
        this.indicatorColor = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorColor(int i) {
        this.indicatorColor = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public void setIndicatorHeight(int i) {
        this.indicatorHeight = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setScrollOffset(int i) {
        this.scrollOffset = i;
        invalidate();
    }

    public int getTextSize() {
        return this.tabTextSize;
    }

    public void setTextSize(int i) {
        this.tabTextSize = i;
        updateTabStyles();
    }

    public void setTextColorResource(int i) {
        this.tabTextColor = getResources().getColor(i);
        updateTabStyles();
    }

    public int getTextColor() {
        return this.tabTextColor;
    }

    public void setTextColor(int i) {
        this.tabTextColor = i;
        updateTabStyles();
    }

    public void setTypeface(Typeface typeface, int i) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = i;
        updateTabStyles();
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public void setTabBackground(int i) {
        this.tabBackgroundResId = i;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return this.tabPaddingTop;
    }

    public void setTabPaddingLeftRight(int i) {
        this.tabPaddingTop = i;
        updateTabStyles();
    }

    public void setSelectTextColor(int i) {
        this.selectTextColor = i;
        updateTabStyles();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.currentPosition = savedState.currentPosition;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPosition = this.currentPosition;
        return savedState;
    }

    public void setUpperCase(boolean z) {
        this.isUpperCase = z;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPosition;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPosition = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPosition);
        }
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener2) {
        this.onSelectedListener = onSelectedListener2;
    }
}
