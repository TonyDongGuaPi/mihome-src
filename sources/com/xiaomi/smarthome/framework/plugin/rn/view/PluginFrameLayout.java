package com.xiaomi.smarthome.framework.plugin.rn.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;

public class PluginFrameLayout extends FrameLayout implements ReactPointerEventsView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17510a = "PluginFrameLayout";

    public PluginFrameLayout(Context context) {
        super(context);
    }

    public void requestLayout() {
        int i;
        int i2;
        super.requestLayout();
        try {
            View view = (View) getParent();
            if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            } else {
                i = view.getWidth();
                i2 = view.getHeight();
            }
            measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
            layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        } catch (Exception unused) {
        }
    }

    public PointerEvents getPointerEvents() {
        if (getVisibility() == 0) {
            return PointerEvents.BOX_NONE;
        }
        return PointerEvents.NONE;
    }
}
