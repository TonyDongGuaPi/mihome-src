package com.mi.global.bbs.view.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.libra.Color;
import com.mi.global.bbs.R;
import java.util.List;

public class TabHostView extends LinearLayout {
    private static final int[] SYS_ATTRS = {16842901, 16842904, 16843562, 16843121, 16843565};
    private int containerViewId;
    private int currentPosition;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private int dividerColor;
    private boolean dividerEnabled;
    private int dividerPadding;
    private Paint dividerPaint;
    private int dividerWidth;
    private int drawablePadding;
    private List<Fragment> fragments;
    private int[] itemDrawableChoose;
    private int[] itemDrawableNormal;
    private int itemPadding;
    private int itemPaddingBottom;
    private int itemPaddingLeft;
    private int itemPaddingRight;
    private int itemPaddingTop;
    private String[] itemStr;
    private final FragmentManager mFragmentManager;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    private Fragment preFragment;
    private int tabCount;
    private ColorStateList tabTextColor;
    private int tabTextSize;
    private int topLineColor;
    private int topLineHeight;

    public interface OnItemClickListener {
        boolean onItemClick(View view, int i);
    }

    public interface TabDataProvider {
        int getTabIconDrawable(int i);

        String getTabText(int i);
    }

    public TabHostView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TabHostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tabCount = 0;
        this.dividerWidth = 1;
        this.tabTextSize = 11;
        this.dividerColor = Color.c;
        this.topLineColor = Color.c;
        this.topLineHeight = 1;
        this.dividerPadding = 5;
        this.drawablePadding = 0;
        this.dividerEnabled = false;
        this.itemPadding = 0;
        this.itemPaddingTop = 0;
        this.itemPaddingLeft = 0;
        this.itemPaddingRight = 0;
        this.itemPaddingBottom = 0;
        this.currentPosition = 0;
        this.fragments = null;
        this.preFragment = null;
        this.itemDrawableNormal = null;
        this.itemDrawableChoose = null;
        this.itemStr = null;
        this.mFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        setWillNotDraw(false);
        setOrientation(0);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.dividerWidth = (int) TypedValue.applyDimension(1, (float) this.dividerWidth, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, (float) this.dividerPadding, displayMetrics);
        this.topLineHeight = (int) TypedValue.applyDimension(1, (float) this.topLineHeight, displayMetrics);
        this.itemPadding = (int) TypedValue.applyDimension(1, (float) this.itemPadding, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, SYS_ATTRS);
        this.tabTextSize = obtainStyledAttributes.getDimensionPixelSize(0, this.tabTextSize);
        this.tabTextColor = obtainStyledAttributes.getColorStateList(1);
        this.dividerPadding = obtainStyledAttributes.getDimensionPixelSize(2, this.dividerPadding);
        this.drawablePadding = obtainStyledAttributes.getDimensionPixelSize(3, this.drawablePadding);
        this.itemPadding = obtainStyledAttributes.getDimensionPixelSize(4, this.itemPadding);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.TabHostView);
        this.dividerWidth = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_dividerWidth, this.dividerWidth);
        this.dividerEnabled = obtainStyledAttributes2.getBoolean(R.styleable.TabHostView_dividerEnabled, this.dividerEnabled);
        this.dividerColor = obtainStyledAttributes2.getColor(R.styleable.TabHostView_dividerColor, this.dividerColor);
        this.topLineColor = obtainStyledAttributes2.getColor(R.styleable.TabHostView_topLineColor, this.topLineColor);
        this.topLineHeight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_topLineHeight, this.topLineHeight);
        this.itemPaddingLeft = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_itemPaddingLeft, this.itemPaddingLeft);
        this.itemPaddingTop = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_itemPaddingTop, this.itemPaddingTop);
        this.itemPaddingRight = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_itemPaddingRight, this.itemPaddingRight);
        this.itemPaddingBottom = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.TabHostView_itemPaddingBottom, this.itemPaddingBottom);
        obtainStyledAttributes2.recycle();
        this.dividerPaint = new Paint();
        this.dividerPaint.setAntiAlias(true);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        setOnItemClickListener(new DefaultOnItemClickListener());
    }

    public TabHostView(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    private static String makeFragmentName(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.tabCount != 0) {
            this.dividerPaint.setColor(this.topLineColor);
            this.dividerPaint.setStrokeWidth((float) this.topLineHeight);
            canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) this.topLineHeight, this.dividerPaint);
            if (this.dividerEnabled) {
                this.dividerPaint.setColor(this.dividerColor);
                this.dividerPaint.setStrokeWidth((float) this.dividerWidth);
                for (int i = 0; i < this.tabCount - 1; i++) {
                    View childAt = getChildAt(i);
                    canvas.drawLine((float) childAt.getRight(), (float) this.dividerPadding, (float) childAt.getRight(), (float) (getHeight() - this.dividerPadding), this.dividerPaint);
                }
            }
        }
    }

    public boolean hasFragment() {
        return this.fragments != null && this.fragments.size() > 0;
    }

    public void addFragments(@IdRes int i, List<Fragment> list, TabDataProvider tabDataProvider) {
        this.containerViewId = i;
        this.fragments = list;
        if (i != -1 && list != null && tabDataProvider != null) {
            this.tabCount = list.size();
            showFragment();
            notifyDataChange(tabDataProvider);
        }
    }

    public TabHostView addFragments(@IdRes int i, List<Fragment> list) {
        this.containerViewId = i;
        this.fragments = list;
        return this;
    }

    public TabHostView setItemRes(int[] iArr, int[] iArr2, String[] strArr) {
        this.itemDrawableNormal = iArr;
        this.itemDrawableChoose = iArr2;
        this.itemStr = strArr;
        return this;
    }

    public void createItems() {
        if (this.fragments != null && this.fragments.size() != 0) {
            this.tabCount = this.fragments.size();
            showFragment();
            notifyDataChange(this.itemDrawableNormal, this.itemDrawableChoose, this.itemStr);
        }
    }

    private StateListDrawable makeDrawable(int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (i == 0 || i < 0 || i2 == 0 || i2 < 0) {
            return stateListDrawable;
        }
        stateListDrawable.addState(new int[]{16842912}, getResources().getDrawable(i2));
        stateListDrawable.addState(new int[0], getResources().getDrawable(i));
        return stateListDrawable;
    }

    private void showFragment() {
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        int currentPosition2 = getCurrentPosition();
        String makeFragmentName = makeFragmentName(this.containerViewId, (long) currentPosition2);
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag(makeFragmentName);
        if (findFragmentByTag == null) {
            findFragmentByTag = this.fragments.get(currentPosition2);
        }
        if (!findFragmentByTag.isAdded()) {
            if (this.preFragment != null) {
                beginTransaction = beginTransaction.hide(this.preFragment);
            }
            beginTransaction.add(this.containerViewId, findFragmentByTag, makeFragmentName);
        } else {
            if (this.preFragment != null) {
                beginTransaction = beginTransaction.hide(this.preFragment);
            }
            if (findFragmentByTag.isDetached()) {
                beginTransaction.attach(findFragmentByTag).show(findFragmentByTag);
            } else {
                beginTransaction.show(findFragmentByTag);
            }
        }
        beginTransaction.commitAllowingStateLoss();
        if (this.preFragment != findFragmentByTag) {
            this.preFragment = findFragmentByTag;
        }
    }

    private void notifyDataChange(TabDataProvider tabDataProvider) {
        removeAllViews();
        for (int i = 0; i < this.tabCount; i++) {
            addTab(i, Integer.valueOf(tabDataProvider.getTabIconDrawable(i)), tabDataProvider.getTabText(i));
        }
        ((TabItemView) getChildAt(this.currentPosition)).setChecked(true);
        invalidate();
    }

    private void notifyDataChange(int[] iArr, int[] iArr2, String[] strArr) {
        removeAllViews();
        int i = 0;
        while (i < this.tabCount) {
            addTab(i, makeDrawable(iArr.length > i ? iArr[i] : 0, iArr2.length > i ? iArr2[i] : 0), strArr.length > i ? strArr[i] : "");
            i++;
        }
        ((TabItemView) getChildAt(this.currentPosition)).setChecked(true);
        invalidate();
    }

    private void addTab(final int i, Object obj, String str) {
        final TabItemView tabItemView = new TabItemView(getContext());
        tabItemView.setFocusable(true);
        tabItemView.setCompoundDrawablePadding(this.drawablePadding);
        tabItemView.setTextSize((float) this.tabTextSize);
        tabItemView.setCirclePointColor(android.graphics.Color.parseColor("#ff5353"));
        tabItemView.setTextColor(this.tabTextColor != null ? this.tabTextColor : ColorStateList.valueOf(-16777216));
        Drawable drawable = null;
        if (obj instanceof Integer) {
            drawable = getResources().getDrawable(((Integer) obj).intValue());
        } else if (obj instanceof Drawable) {
            drawable = (Drawable) obj;
        }
        if (drawable != null) {
            tabItemView.setDrawable(drawable);
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        tabItemView.setText(str);
        tabItemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TabHostView.this.onItemClickListener.onItemClick(tabItemView, i)) {
                    TabHostView.this.setCurrentPosition(i);
                }
            }
        });
        setTabPadding(tabItemView);
        addView(tabItemView, this.defaultTabLayoutParams);
    }

    private void setTabPadding(TabItemView tabItemView) {
        tabItemView.setPadding(this.itemPaddingLeft == 0 ? this.itemPadding : this.itemPaddingLeft, this.itemPaddingTop == 0 ? this.itemPadding : this.itemPaddingTop, this.itemPaddingRight == 0 ? this.itemPadding : this.itemPaddingRight, this.itemPaddingBottom == 0 ? this.itemPadding : this.itemPaddingBottom);
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(int i) {
        if (this.currentPosition != i) {
            this.currentPosition = i;
            showFragment();
            updateChildState(i);
        }
    }

    public void showCirclePoint(int i) {
        if (i >= 0 && i <= this.tabCount - 1) {
            ((TabItemView) getChildAt(i)).drawCirclePoint();
        }
    }

    public void clearCirclePoint(int i) {
        if (i >= 0 && i <= this.tabCount - 1) {
            ((TabItemView) getChildAt(i)).clearCirclePoint();
        }
    }

    public void showMsgCount(int i, int i2) {
        showMsgCount(i, i2, 0);
    }

    public void clearMsgCount(int i) {
        showMsgCount(i, 0, 0);
    }

    public void showMsgCount(int i, int i2, int i3) {
        if (i >= 0 && i <= this.tabCount - 1) {
            TabItemView tabItemView = (TabItemView) getChildAt(i);
            if (i3 != 0) {
                tabItemView.setMsgBackgroundColor(i3);
            }
            tabItemView.drawMsg(i2);
        }
    }

    private void updateChildState(int i) {
        int size = this.fragments.size();
        for (int i2 = 0; i2 < size; i2++) {
            TabItemView tabItemView = (TabItemView) getChildAt(i2);
            if (i2 == i) {
                tabItemView.setChecked(true);
            } else {
                tabItemView.setChecked(false);
            }
        }
    }

    public void setDividerWidth(int i) {
        this.dividerWidth = i;
        if (this.dividerEnabled) {
            invalidate();
        }
    }

    public void setTabTextSize(int i) {
        this.tabTextSize = i;
        setTabTextSize();
    }

    private void setTabTextSize() {
        if (this.tabCount != 0) {
            for (int i = 0; i < this.tabCount; i++) {
                ((TabItemView) getChildAt(i)).setTextSize((float) this.tabTextSize);
            }
        }
    }

    public void setCirclePointRadius(float f) {
        setAllTabCirclePointRadius(f);
    }

    private void setAllTabCirclePointRadius(float f) {
        if (this.tabCount != 0) {
            for (int i = 0; i < this.tabCount; i++) {
                ((TabItemView) getChildAt(i)).setCirclePointRadius(f);
            }
        }
    }

    public void setCirclePointColor(int i) {
        setAllTabCirclePointColor(i);
    }

    public boolean hasCirclePoint(int i) {
        if (i < 0 || i > this.tabCount - 1) {
            return false;
        }
        return ((TabItemView) getChildAt(i)).hasCirclePoint();
    }

    private void setAllTabCirclePointColor(int i) {
        if (this.tabCount != 0) {
            for (int i2 = 0; i2 < this.tabCount; i2++) {
                ((TabItemView) getChildAt(i2)).setCirclePointColor(i);
            }
        }
    }

    public void setMsgTextColor(int i) {
        setAllTabMsgTextColor(i);
    }

    private void setAllTabMsgTextColor(int i) {
        if (this.tabCount != 0) {
            for (int i2 = 0; i2 < this.tabCount; i2++) {
                ((TabItemView) getChildAt(i2)).setMsgTextColor(i);
            }
        }
    }

    public void setMsgTextSize(float f) {
        setAllTabMsgTextSize(f);
    }

    private void setAllTabMsgTextSize(float f) {
        if (this.tabCount != 0) {
            for (int i = 0; i < this.tabCount; i++) {
                ((TabItemView) getChildAt(i)).setMsgTextSize(f);
            }
        }
    }

    public void setTabTextColor(int i) {
        this.tabTextColor = getResources().getColorStateList(i);
        setTabTextColor();
    }

    public void setTabTextColor(String str, String str2) {
        int i;
        int i2;
        try {
            i2 = android.graphics.Color.parseColor(str);
            try {
                i = android.graphics.Color.parseColor(str2);
            } catch (IllegalArgumentException e) {
                e = e;
                e.printStackTrace();
                i = 0;
                this.tabTextColor = new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{i, i2});
                setTabTextColor();
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            i2 = 0;
            e.printStackTrace();
            i = 0;
            this.tabTextColor = new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{i, i2});
            setTabTextColor();
        }
        this.tabTextColor = new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{i, i2});
        setTabTextColor();
    }

    private void setTabTextColor() {
        if (this.tabCount != 0) {
            for (int i = 0; i < this.tabCount; i++) {
                ((TabItemView) getChildAt(i)).setTextColor(this.tabTextColor);
            }
        }
    }

    public void setDividerColor(int i) {
        this.dividerColor = getResources().getColor(i);
        invalidate();
    }

    public void setTopLineColor(int i) {
        this.topLineColor = getResources().getColor(i);
        invalidate();
    }

    public void setTopLineHeight(int i) {
        this.topLineHeight = i;
        invalidate();
    }

    public void setDividerPadding(int i) {
        this.dividerPadding = i;
        if (this.dividerEnabled) {
            invalidate();
        }
    }

    public void setDividerEnabled(boolean z) {
        this.dividerEnabled = z;
        invalidate();
    }

    public void setTabPadding(int i, int i2, int i3, int i4) {
        this.itemPaddingLeft = i;
        this.itemPaddingTop = i2;
        this.itemPaddingRight = i3;
        this.itemPaddingBottom = i4;
        setTabPadding();
    }

    private void setTabPadding() {
        if (this.tabCount != 0) {
            for (int i = 0; i < this.tabCount; i++) {
                setTabPadding((TabItemView) getChildAt(i));
            }
        }
    }

    public void refreshTabIcon(int i, Drawable drawable) {
        if (i >= 0 && i < this.tabCount) {
            ((TabItemView) getChildAt(i)).setDrawable(drawable);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentPosition(savedState.currentPosition);
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

    private class DefaultOnItemClickListener implements OnItemClickListener {
        public boolean onItemClick(View view, int i) {
            return false;
        }

        private DefaultOnItemClickListener() {
        }
    }

    private StateListDrawable makeDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public void updateDrawable(StateListDrawable stateListDrawable, int i) {
        if (i >= 0 && i <= this.tabCount - 1) {
            ((TabItemView) getChildAt(i)).setDrawable(stateListDrawable);
        }
    }

    public void updateDrawable(Drawable drawable, Drawable drawable2, int i) {
        updateDrawable(makeDrawable(drawable, drawable2), i);
    }
}
