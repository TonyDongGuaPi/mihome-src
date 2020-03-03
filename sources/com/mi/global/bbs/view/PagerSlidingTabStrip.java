package com.mi.global.bbs.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.tab.CircleDrawable;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] ATTRS = {16842901, 16842904};
    private static final int MAX_OFFSCREEN_PAGES = 4;
    /* access modifiers changed from: private */
    public int currentPosition;
    /* access modifiers changed from: private */
    public float currentPositionOffset;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    public ViewPager.OnPageChangeListener delegatePageListener;
    private int dividerColor;
    private int dividerPadding;
    private Paint dividerPaint;
    private int dividerWidth;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    private int fontStyle;
    private int indicatorColor;
    private int indicatorHeight;
    private RectF indicatorRectF;
    private int indicatorWidth;
    /* access modifiers changed from: private */
    public float indicatorWidthOffset;
    private int lastScrollX;
    private Locale locale;
    private ArgbEvaluator mArgbEvaluator;
    /* access modifiers changed from: private */
    public ViewPager pager;
    private int pointColor;
    private int pointPosition;
    private float pointRadius;
    private Paint rectPaint;
    private int scrollOffset;
    /* access modifiers changed from: private */
    public int selectedPosition;
    private int selectedTabTextColor;
    private boolean shouldExpand;
    private int tabBackgroundResId;
    private int tabCount;
    private int tabPadding;
    private int tabTextColor;
    private int tabTextSize;
    /* access modifiers changed from: private */
    public LinearLayout tabsContainer;
    private boolean textAllCaps;
    private int underlineColor;
    private int underlineHeight;

    public interface IconTabProvider {
        int getPageIconResId(int i);
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, (AttributeSet) null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currentPosition = 0;
        this.selectedPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.dividerColor = 436207616;
        this.shouldExpand = false;
        this.textAllCaps = true;
        this.scrollOffset = 52;
        this.indicatorHeight = 8;
        this.underlineHeight = 2;
        this.indicatorWidth = 16;
        this.dividerPadding = 12;
        this.tabPadding = 24;
        this.dividerWidth = 1;
        this.tabTextSize = 12;
        this.tabTextColor = -7500660;
        this.selectedTabTextColor = -10066330;
        this.lastScrollX = 0;
        this.tabBackgroundResId = Color.parseColor("#00000000");
        this.pointPosition = -1;
        this.pointColor = -65536;
        this.pointRadius = 8.0f;
        this.indicatorWidthOffset = 0.0f;
        this.fontStyle = 0;
        this.mArgbEvaluator = new ArgbEvaluator();
        setFillViewport(true);
        setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(context);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, (float) this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, (float) this.indicatorHeight, displayMetrics);
        this.underlineHeight = (int) TypedValue.applyDimension(1, (float) this.underlineHeight, displayMetrics);
        this.indicatorWidth = (int) TypedValue.applyDimension(1, (float) this.indicatorWidth, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, (float) this.dividerPadding, displayMetrics);
        this.tabPadding = (int) TypedValue.applyDimension(1, (float) this.tabPadding, displayMetrics);
        this.dividerWidth = (int) TypedValue.applyDimension(1, (float) this.dividerWidth, displayMetrics);
        this.tabTextSize = (int) TypedValue.applyDimension(2, (float) this.tabTextSize, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
        this.tabTextColor = obtainStyledAttributes.getColor(1, this.tabTextColor);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        this.indicatorColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.indicatorColor);
        this.underlineColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, this.underlineColor);
        this.dividerColor = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.indicatorHeight);
        this.underlineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.underlineHeight);
        this.indicatorWidth = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorWidth, this.indicatorWidth);
        this.dividerPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.dividerPadding);
        this.tabPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.tabPadding);
        this.tabBackgroundResId = obtainStyledAttributes2.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, this.tabBackgroundResId);
        this.shouldExpand = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.scrollOffset);
        this.textAllCaps = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, this.textAllCaps);
        obtainStyledAttributes2.recycle();
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.dividerPaint = new Paint();
        this.dividerPaint.setAntiAlias(true);
        this.dividerPaint.setStrokeWidth((float) this.dividerWidth);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
        this.expandedTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.locale == null) {
            this.locale = getResources().getConfiguration().locale;
        }
        this.indicatorRectF = new RectF();
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        if (viewPager.getAdapter() != null) {
            viewPager.addOnPageChangeListener(new PageListener());
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        int i = this.tabCount - 1;
        if (i > 4) {
            i = 4;
        }
        this.pager.setOffscreenPageLimit(i);
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            if (this.pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i2, ((IconTabProvider) this.pager.getAdapter()).getPageIconResId(i2));
            } else {
                addTextTab(i2, this.pager.getAdapter().getPageTitle(i2).toString());
            }
        }
        updateTabStyles();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int unused = PagerSlidingTabStrip.this.currentPosition = PagerSlidingTabStrip.this.pager.getCurrentItem();
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.currentPosition, 0);
            }
        });
    }

    private void addTextTab(int i, String str) {
        FontTextView fontTextView = new FontTextView(getContext());
        fontTextView.setText(str);
        fontTextView.setGravity(17);
        fontTextView.setSingleLine();
        addTab(i, fontTextView);
    }

    private void addIconTab(int i, int i2) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        addTab(i, imageButton);
    }

    private void addTab(final int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PagerSlidingTabStrip.this.pager.setCurrentItem(i);
            }
        });
        view.setPadding(this.tabPadding, 0, this.tabPadding, 0);
        this.tabsContainer.addView(view, i, this.shouldExpand ? this.expandedTabLayoutParams : this.defaultTabLayoutParams);
    }

    /* access modifiers changed from: private */
    public void updateTabStyles() {
        for (int i = 0; i < this.tabCount; i++) {
            View childAt = this.tabsContainer.getChildAt(i);
            childAt.setBackgroundResource(17170445);
            if (childAt instanceof FontTextView) {
                FontTextView fontTextView = (FontTextView) childAt;
                fontTextView.setTextSize(0, (float) this.tabTextSize);
                fontTextView.setTextStyle(this.fontStyle);
                fontTextView.setTextColor(this.tabTextColor);
                if (this.textAllCaps) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        fontTextView.setAllCaps(true);
                    } else {
                        fontTextView.setText(fontTextView.getText().toString().toUpperCase(this.locale));
                    }
                }
                if (i == this.selectedPosition) {
                    fontTextView.setTextColor(this.selectedTabTextColor);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void scrollToChild(int i, int i2) {
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

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.tabCount != 0) {
            int height = getHeight();
            this.rectPaint.setColor(this.underlineColor);
            float f = (float) height;
            Canvas canvas2 = canvas;
            canvas2.drawRect(0.0f, (float) (height - this.underlineHeight), (float) this.tabsContainer.getWidth(), f, this.rectPaint);
            this.rectPaint.setColor(this.indicatorColor);
            View childAt = this.tabsContainer.getChildAt(this.currentPosition);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            float f2 = right - left;
            if (this.currentPositionOffset > 0.0f && this.currentPosition < this.tabCount - 1) {
                View childAt2 = this.tabsContainer.getChildAt(this.currentPosition + 1);
                left = (this.currentPositionOffset * ((float) childAt2.getLeft())) + ((1.0f - this.currentPositionOffset) * left);
                right = (this.currentPositionOffset * ((float) childAt2.getRight())) + ((1.0f - this.currentPositionOffset) * right);
            }
            int i = (int) (((this.indicatorWidthOffset * 2.0f) + 1.0f) * ((float) this.indicatorWidth));
            if (i > 0) {
                left += (f2 - ((float) i)) / 2.0f;
            }
            if (i > 0) {
                right = ((float) i) + left;
            }
            this.indicatorRectF.set(left, (float) (height - this.indicatorHeight), right, f);
            canvas.drawRoundRect(this.indicatorRectF, (float) (this.indicatorHeight / 2), (float) (this.indicatorHeight / 2), this.rectPaint);
            this.dividerPaint.setColor(this.dividerColor);
            for (int i2 = 0; i2 < this.tabCount - 1; i2++) {
                View childAt3 = this.tabsContainer.getChildAt(i2);
                canvas.drawLine((float) childAt3.getRight(), (float) this.dividerPadding, (float) childAt3.getRight(), (float) (height - this.dividerPadding), this.dividerPaint);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.pointPosition != -1) {
            TextView textView = (TextView) this.tabsContainer.getChildAt(this.pointPosition);
            String charSequence = textView.getText().toString();
            Rect rect = new Rect();
            textView.getPaint().getTextBounds(charSequence, 0, charSequence.length() - 1, rect);
            float measureText = textView.getPaint().measureText(charSequence);
            int width = textView.getWidth();
            CircleDrawable circleDrawable = new CircleDrawable(this.pointColor, this.pointRadius);
            circleDrawable.setShow(true);
            canvas.save();
            canvas.translate(((((float) (getWidth() + width)) + measureText) / 2.0f) + this.pointRadius, (float) ((getHeight() - rect.height()) / 2));
            circleDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public void showPointAt(int i) {
        this.pointPosition = i;
        invalidate();
    }

    public void clearPoint() {
        this.pointPosition = -1;
        invalidate();
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

    public void setUnderlineColorResource(int i) {
        this.underlineColor = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public void setUnderlineColor(int i) {
        this.underlineColor = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.dividerColor = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void setUnderlineHeight(int i) {
        this.underlineHeight = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public void setDividerPadding(int i) {
        this.dividerPadding = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setScrollOffset(int i) {
        this.scrollOffset = i;
        invalidate();
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        notifyDataSetChanged();
    }

    public boolean isTextAllCaps() {
        return this.textAllCaps;
    }

    public void setAllCaps(boolean z) {
        this.textAllCaps = z;
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

    public void setSelectedTextColorResource(int i) {
        this.selectedTabTextColor = getResources().getColor(i);
        updateTabStyles();
    }

    public int getSelectedTextColor() {
        return this.selectedTabTextColor;
    }

    public void setSelectedTextColor(int i) {
        this.selectedTabTextColor = i;
        updateTabStyles();
    }

    public void setFontType(int i) {
        this.fontStyle = i;
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
        return this.tabPadding;
    }

    public void setTabPaddingLeftRight(int i) {
        this.tabPadding = i;
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

    public void setPointColor(int i) {
        this.pointColor = i;
    }

    public void setPointRadius(float f) {
        this.pointRadius = f;
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

    private class PageListener implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int unused = PagerSlidingTabStrip.this.currentPosition = i;
            float unused2 = PagerSlidingTabStrip.this.currentPositionOffset = f;
            PagerSlidingTabStrip.this.scrollToChild(i, (int) (((float) PagerSlidingTabStrip.this.tabsContainer.getChildAt(i).getWidth()) * f));
            float access$500 = (PagerSlidingTabStrip.this.currentPositionOffset * ((float) (PagerSlidingTabStrip.this.currentPosition + 1))) + ((1.0f - PagerSlidingTabStrip.this.currentPositionOffset) * ((float) PagerSlidingTabStrip.this.currentPosition));
            float abs = Math.abs(access$500 - ((float) PagerSlidingTabStrip.this.currentPosition));
            PagerSlidingTabStrip.this.updateTabTextColor(abs);
            float unused3 = PagerSlidingTabStrip.this.indicatorWidthOffset = abs < 0.5f ? abs * 2.0f : (1.0f - abs) * 2.0f;
            PagerSlidingTabStrip.this.invalidate();
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrolled(i, f, i2);
            }
            int round = Math.round(access$500);
            if (round != PagerSlidingTabStrip.this.selectedPosition) {
                int unused4 = PagerSlidingTabStrip.this.selectedPosition = round;
                PagerSlidingTabStrip.this.updateTabStyles();
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.pager.getCurrentItem(), 0);
            }
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            int unused = PagerSlidingTabStrip.this.selectedPosition = i;
            PagerSlidingTabStrip.this.updateTabStyles();
            if (PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageSelected(i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTabTextColor(float f) {
        ((FontTextView) getTabViewAt(this.currentPosition)).setTextColor(((Integer) this.mArgbEvaluator.evaluate(f, Integer.valueOf(this.selectedTabTextColor), Integer.valueOf(this.tabTextColor))).intValue());
        if (this.currentPosition < this.tabCount - 1) {
            ((FontTextView) getTabViewAt(this.currentPosition + 1)).setTextColor(((Integer) this.mArgbEvaluator.evaluate(f, Integer.valueOf(this.tabTextColor), Integer.valueOf(this.selectedTabTextColor))).intValue());
        }
    }

    public View getTabViewAt(int i) {
        if (this.tabsContainer.getChildCount() > 0) {
            return this.tabsContainer.getChildAt(i);
        }
        return null;
    }
}
