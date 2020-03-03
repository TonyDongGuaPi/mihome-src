package com.mi.global.bbs.view.Editor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.mi.global.bbs.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FontTextView extends AppCompatTextView {
    private static final int DEFAULT_SPACE_EXTRA = 2;
    public static final int HEAVY = 8;
    public static final int MEDIUM = 4;
    public static final int NORMAL = 0;
    private static final int[] TEXT_BOUNDS_FLAGS = {0, 4, 8};

    @Retention(RetentionPolicy.SOURCE)
    public @interface FontStyle {
    }

    public FontTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FontTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public FontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FontTextView);
        int i2 = 0;
        if (obtainStyledAttributes != null) {
            int i3 = obtainStyledAttributes.getInt(R.styleable.FontTextView_textBounds, 0);
            i2 = i3 != 0 ? 0 | TEXT_BOUNDS_FLAGS[i3] : i2;
            obtainStyledAttributes.recycle();
        }
        applyCustomFont(context, i2);
        setLineSpacing((float) ((int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics())), 1.0f);
    }

    private void applyCustomFont(Context context, int i) {
        Typeface typeface;
        if (i == 0) {
            typeface = FontCache.getTypeface("Roboto-Regular.ttf", context);
        } else if (i == 4) {
            typeface = FontCache.getTypeface("Roboto-Medium.ttf", context);
        } else if (i != 8) {
            typeface = FontCache.getTypeface("Roboto-Regular.ttf", context);
        } else {
            typeface = FontCache.getTypeface("Roboto-Bold.ttf", context);
        }
        setTypeface(typeface);
    }

    public void setTextStyle(int i) {
        applyCustomFont(getContext(), i);
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.setPaddingRelative(i, i2, i3, i4);
        } else {
            setPadding(i, i2, i3, i4);
        }
    }
}
