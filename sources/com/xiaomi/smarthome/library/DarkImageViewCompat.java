package com.xiaomi.smarthome.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class DarkImageViewCompat extends AppCompatImageView {
    public DarkImageViewCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public DarkImageViewCompat(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DarkImageViewCompat(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DarkImageViewCompat);
        boolean z = true;
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.DarkImageViewCompat_enable_force_dark, true);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.DarkImageViewCompat_apply_color_filter, true);
        int color = obtainStyledAttributes.getColor(R.styleable.DarkImageViewCompat_color_tint, -1);
        obtainStyledAttributes.recycle();
        DarkModeCompat.a((View) this, z2);
        if (!isInPlugin()) {
            z = DarkModeCompat.a(context);
        } else if (!DarkModeCompat.b(context) || !DarkModeCompat.a(context)) {
            z = false;
        }
        if (z3 && z) {
            setColorFilter(color);
        }
    }

    private boolean isInPlugin() {
        String simpleName = getContext().getClass().getSimpleName();
        return simpleName.contains("LoadingBaseActivity") || simpleName.contains("PluginRNActivity") || simpleName.contains("PluginHostActivity");
    }
}
