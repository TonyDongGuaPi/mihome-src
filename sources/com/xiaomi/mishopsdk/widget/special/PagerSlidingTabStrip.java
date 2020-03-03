package com.xiaomi.mishopsdk.widget.special;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
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
import com.xiaomi.mishopsdk.R;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] ATTRS = {16842901, 16842904};
    private final String TAG = "PagerSlidingTabStrip";
    private boolean checkedTabWidths = false;
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    /* access modifiers changed from: private */
    public float currentPositionOffset = 0.0f;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private int dividerColor = 436207616;
    private int dividerPadding = 14;
    private Paint dividerPaint;
    private int dividerWidth = 1;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    private int indicatorColor = -46592;
    private int indicatorHeight = 1;
    private int lastScrollX = 0;
    private Locale locale;
    private final PageListener pageListener = new PageListener();
    /* access modifiers changed from: private */
    public ViewPager pager;
    private Paint rectPaint;
    private int screenWidth;
    private int scrollOffset = 52;
    private boolean shouldExpand = false;
    private int tabBackgroundResId = 17170445;
    private int tabCount;
    private int tabPadding = 8;
    private ColorStateList tabTextColor = new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[0]}, new int[]{Color.rgb(255, 111, 51), Color.rgb(255, 111, 51), Color.rgb(153, 153, 153)});
    private int tabTextSize = 12;
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = 0;
    /* access modifiers changed from: private */
    public LinearLayout tabsContainer;
    private boolean textAllCaps = true;
    private int underlineColor = -2039584;
    private int underlineHeight = 1;

    public interface IconTabProvider {
        int getPageIconResId(int i);
    }

    public PagerSlidingTabStrip(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        setFillViewport(true);
        setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(context);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.screenWidth = displayMetrics.widthPixels;
        this.scrollOffset = (int) TypedValue.applyDimension(1, (float) this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) TypedValue.applyDimension(1, (float) this.indicatorHeight, displayMetrics);
        this.underlineHeight = (int) TypedValue.applyDimension(1, (float) this.underlineHeight, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, (float) this.dividerPadding, displayMetrics);
        this.tabPadding = (int) TypedValue.applyDimension(1, (float) this.tabPadding, displayMetrics);
        this.dividerWidth = (int) TypedValue.applyDimension(1, (float) this.dividerWidth, displayMetrics);
        this.tabTextSize = (int) TypedValue.applyDimension(2, (float) this.tabTextSize, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.mishopsdk_PagerSlidingTabStrip);
        this.indicatorColor = obtainStyledAttributes2.getColor(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_indicatorColor, this.indicatorColor);
        this.underlineColor = obtainStyledAttributes2.getColor(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_underlineColor, this.underlineColor);
        this.dividerColor = obtainStyledAttributes2.getColor(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_dividerColor, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_indicatorHeight, this.indicatorHeight);
        this.underlineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_underlineHeight, this.underlineHeight);
        this.dividerPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_mishopDividerPadding, this.dividerPadding);
        this.tabPadding = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_tabPaddingLeftRight, this.tabPadding);
        this.tabBackgroundResId = obtainStyledAttributes2.getResourceId(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_tabBackground, this.tabBackgroundResId);
        this.shouldExpand = obtainStyledAttributes2.getBoolean(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_shouldExpand, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_scrollOffset, this.scrollOffset);
        this.textAllCaps = obtainStyledAttributes2.getBoolean(R.styleable.mishopsdk_PagerSlidingTabStrip_mishopsdk_mishopTextAllCaps, this.textAllCaps);
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
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        if (viewPager.getAdapter() != null) {
            viewPager.setOnPageChangeListener(this.pageListener);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        int[] iArr = new int[this.tabCount];
        for (int i = 0; i < this.tabCount; i++) {
            if (this.pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) this.pager.getAdapter()).getPageIconResId(i));
            } else {
                iArr[i] = addTextTab(i, this.pager.getAdapter().getPageTitle(i).toString());
            }
        }
        resetTextAdWidth(iArr);
        updateTabStyles(0);
        this.checkedTabWidths = false;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int unused = PagerSlidingTabStrip.this.currentPosition = PagerSlidingTabStrip.this.pager.getCurrentItem();
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.currentPosition, 0);
            }
        });
    }

    private void resetTextAdWidth(int[] iArr) {
        int i;
        if (iArr != null && iArr.length != 0 && iArr.length == this.tabsContainer.getChildCount()) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < iArr.length; i5++) {
                i3 += iArr[i5];
                i4 = Math.max(i4, iArr[i5]);
            }
            if (i3 < this.screenWidth) {
                boolean z = i4 * iArr.length <= this.screenWidth;
                int length = (this.screenWidth - i3) / iArr.length;
                int length2 = this.screenWidth / iArr.length;
                if (!z) {
                    i = (this.screenWidth - i3) - (iArr.length * length);
                } else {
                    i = this.screenWidth - (iArr.length * length2);
                }
                for (int i6 = 0; i6 < iArr.length; i6++) {
                    if (!z) {
                        iArr[i6] = iArr[i6] + length;
                    } else {
                        iArr[i6] = length2;
                    }
                }
                if (i != 0) {
                    int length3 = iArr.length - 1;
                    iArr[length3] = iArr[length3] + i;
                }
                while (i2 < this.tabsContainer.getChildCount()) {
                    View childAt = this.tabsContainer.getChildAt(i2);
                    if (childAt instanceof TextView) {
                        ((TextView) childAt).setWidth(iArr[i2]);
                        i2++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private int addTextTab(final int i, String str) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setFocusable(true);
        textView.setTextSize(0, (float) this.tabTextSize);
        textView.setGravity(17);
        textView.setSingleLine();
        textView.setSelected(false);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PagerSlidingTabStrip.this.pager.setCurrentItem(i);
                PagerSlidingTabStrip.this.setTabSelcted(i);
            }
        });
        this.tabsContainer.addView(textView);
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(str, 0, str.length(), rect);
        return rect.width() + (this.tabPadding * 2);
    }

    /* access modifiers changed from: private */
    public void setTabSelcted(int i) {
        updateTabStyles(i);
    }

    private void addIconTab(final int i, int i2) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setFocusable(true);
        imageButton.setImageResource(i2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PagerSlidingTabStrip.this.pager.setCurrentItem(i);
            }
        });
        this.tabsContainer.addView(imageButton);
    }

    private void updateTabStyles(int i) {
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            View childAt = this.tabsContainer.getChildAt(i2);
            childAt.setLayoutParams(this.defaultTabLayoutParams);
            childAt.setBackgroundResource(this.tabBackgroundResId);
            if (this.shouldExpand) {
                childAt.setPadding(0, 0, 0, 0);
            } else {
                childAt.setPadding(this.tabPadding, 0, this.tabPadding, 0);
            }
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.tabTextSize);
                textView.setTypeface(this.tabTypeface, this.tabTypefaceStyle);
                textView.setTextColor(this.tabTextColor);
                if (i2 == i) {
                    textView.setSelected(true);
                } else {
                    textView.setSelected(false);
                }
                if (this.textAllCaps) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.locale));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.shouldExpand && View.MeasureSpec.getMode(i) != 0) {
            int measuredWidth = getMeasuredWidth();
            int i3 = 0;
            for (int i4 = 0; i4 < this.tabCount; i4++) {
                i3 += this.tabsContainer.getChildAt(i4).getMeasuredWidth();
            }
            if (!this.checkedTabWidths && i3 > 0 && measuredWidth > 0) {
                if (i3 <= measuredWidth) {
                    for (int i5 = 0; i5 < this.tabCount; i5++) {
                        this.tabsContainer.getChildAt(i5).setLayoutParams(this.expandedTabLayoutParams);
                    }
                }
                this.checkedTabWidths = true;
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
            canvas.drawRect(0.0f, (float) (height - this.underlineHeight), (float) this.tabsContainer.getWidth(), f, this.rectPaint);
            this.rectPaint.setColor(this.indicatorColor);
            View childAt = this.tabsContainer.getChildAt(this.currentPosition);
            float left = (((float) childAt.getLeft()) + ((float) childAt.getRight())) / 2.0f;
            float measureTabTextWidth = measureTabTextWidth(this.pager.getAdapter().getPageTitle(this.currentPosition).toString());
            if (this.currentPositionOffset > 0.0f && this.currentPosition < this.tabCount - 1) {
                View childAt2 = this.tabsContainer.getChildAt(this.currentPosition + 1);
                left += (((((float) childAt2.getLeft()) + ((float) childAt2.getRight())) / 2.0f) - left) * this.currentPositionOffset;
                measureTabTextWidth += (measureTabTextWidth(this.pager.getAdapter().getPageTitle(this.currentPosition + 1).toString()) - measureTabTextWidth) * this.currentPositionOffset;
            }
            float f2 = measureTabTextWidth / 2.0f;
            Canvas canvas2 = canvas;
            canvas2.drawRect(left - f2, (float) (height - this.indicatorHeight), left + f2, f, this.rectPaint);
            this.dividerPaint.setColor(this.dividerColor);
            for (int i = 0; i < this.tabCount - 1; i++) {
                View childAt3 = this.tabsContainer.getChildAt(i);
                canvas.drawLine((float) childAt3.getRight(), (float) this.dividerPadding, (float) childAt3.getRight(), (float) (height - this.dividerPadding), this.dividerPaint);
            }
        }
    }

    public class PageListener implements ViewPager.OnPageChangeListener {
        public PageListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int unused = PagerSlidingTabStrip.this.currentPosition = i;
            float unused2 = PagerSlidingTabStrip.this.currentPositionOffset = f;
            PagerSlidingTabStrip.this.scrollToChild(i, (int) (f * ((float) PagerSlidingTabStrip.this.tabsContainer.getChildAt(i).getWidth())));
            PagerSlidingTabStrip.this.invalidate();
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.pager.getCurrentItem(), 0);
            }
        }

        public void onPageSelected(int i) {
            PagerSlidingTabStrip.this.setTabSelcted(i);
        }
    }

    public void setIndicatorColor(int i) {
        this.indicatorColor = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.indicatorColor = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int i) {
        this.indicatorHeight = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public void setUnderlineColor(int i) {
        this.underlineColor = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.underlineColor = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.dividerColor = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setUnderlineHeight(int i) {
        this.underlineHeight = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void setDividerPadding(int i) {
        this.dividerPadding = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public void setScrollOffset(int i) {
        this.scrollOffset = i;
        invalidate();
    }

    private float measureTabTextWidth(String str) {
        Rect rect = new Rect();
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((float) this.tabTextSize);
        textPaint.setTypeface(this.tabTypeface);
        textPaint.getTextBounds(str, 0, str.length(), rect);
        return (float) rect.width();
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public boolean isTextAllCaps() {
        return this.textAllCaps;
    }

    public void setAllCaps(boolean z) {
        this.textAllCaps = z;
    }

    public void setTextSize(int i) {
        this.tabTextSize = i;
        updateTabStyles(0);
    }

    public int getTextSize() {
        return this.tabTextSize;
    }

    public void setTextColorResource(int i) {
        try {
            this.tabTextColor = ColorStateList.createFromXml(getResources(), getResources().getXml(i));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        updateTabStyles(0);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.tabTextColor = colorStateList;
        updateTabStyles(0);
    }

    public ColorStateList getTextColor() {
        return this.tabTextColor;
    }

    public void setTypeface(Typeface typeface, int i) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = i;
        updateTabStyles(0);
    }

    public void setTabBackground(int i) {
        this.tabBackgroundResId = i;
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int i) {
        this.tabPadding = i;
        updateTabStyles(0);
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
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
}
