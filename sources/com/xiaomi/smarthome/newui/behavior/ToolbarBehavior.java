package com.xiaomi.smarthome.newui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: a  reason: collision with root package name */
    private View f20453a = null;
    private int b = 0;
    private View c = null;
    private int d = 0;

    public ToolbarBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        View view3 = this.c;
        if (this.c != view2) {
            return false;
        }
        if (this.f20453a == null) {
            this.f20453a = coordinatorLayout.findViewById(R.id.title_bar);
        }
        if (this.b == 0) {
            this.b = this.f20453a.getHeight();
        }
        if (this.d == 0) {
            this.d = view2.getTop();
        }
        float top = ((float) (this.d - view2.getTop())) / ((float) (this.d - this.b));
        if (top >= 1.0f) {
            this.f20453a.setAlpha(1.0f);
            top = 1.0f;
        } else {
            this.f20453a.setAlpha(0.0f);
        }
        this.f20453a.setAlpha(top);
        return true;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return view2 == this.c;
    }
}
