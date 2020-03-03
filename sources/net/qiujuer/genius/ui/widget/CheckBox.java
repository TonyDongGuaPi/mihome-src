package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.drawable.CircleCheckDrawable;

public class CheckBox extends android.widget.CheckBox {
    private CircleCheckDrawable mMarkDrawable;

    public CheckBox(Context context) {
        super(context);
        init((AttributeSet) null, R.attr.gCheckBoxStyle, R.style.Genius_Widget_CompoundButton_CheckBox);
    }

    public CheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, R.attr.gCheckBoxStyle, R.style.Genius_Widget_CompoundButton_CheckBox);
    }

    public CheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, R.style.Genius_Widget_CompoundButton_CheckBox);
    }

    @TargetApi(21)
    public CheckBox(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(CheckBox.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(CheckBox.class.getName());
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Typeface a2;
        Context context = getContext();
        Resources resources = getResources();
        float f = resources.getDisplayMetrics().density;
        int i3 = (int) (2.0f * f);
        if (attributeSet == null) {
            this.mMarkDrawable = new CircleCheckDrawable(resources.getColorStateList(R.color.g_default_check_box));
            setButtonDrawable(this.mMarkDrawable);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CheckBox, i, i2);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.CheckBox_gBorderSize, i3);
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.CheckBox_gIntervalSize, i3);
        int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.CheckBox_gMarkSize, -1);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.CheckBox_gMarkColor);
        String string = obtainStyledAttributes.getString(R.styleable.CheckBox_gFont);
        obtainStyledAttributes.recycle();
        if (colorStateList == null) {
            colorStateList = resources.getColorStateList(R.color.g_default_check_box);
        }
        boolean z = true;
        if (dimensionPixelOffset3 < 0) {
            dimensionPixelOffset3 = (int) (f * 22.0f);
            z = false;
        }
        this.mMarkDrawable = new CircleCheckDrawable(colorStateList);
        this.mMarkDrawable.c(dimensionPixelOffset);
        this.mMarkDrawable.d(dimensionPixelOffset2);
        this.mMarkDrawable.a(dimensionPixelOffset3, z);
        this.mMarkDrawable.a(isInEditMode());
        setButtonDrawable(this.mMarkDrawable);
        if (!isInEditMode() && string != null && string.length() > 0 && (a2 = Ui.a(getContext(), string)) != null) {
            setTypeface(a2);
        }
    }

    public void setBorderSize(int i) {
        this.mMarkDrawable.c(i);
    }

    public void setIntervalSize(int i) {
        this.mMarkDrawable.d(i);
    }

    public void setMarkSize(int i) {
        this.mMarkDrawable.a(i, true);
    }

    public int getBorderSize() {
        return this.mMarkDrawable.g();
    }

    public int getIntervalSize() {
        return this.mMarkDrawable.h();
    }

    public int getMarkSize() {
        return this.mMarkDrawable.i();
    }

    public void setMarkColor(int i) {
        this.mMarkDrawable.a(i);
    }

    public void setMarkColor(ColorStateList colorStateList) {
        this.mMarkDrawable.a(colorStateList);
    }

    public ColorStateList getMarkColor() {
        return this.mMarkDrawable.a();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshDrawableState();
    }
}
