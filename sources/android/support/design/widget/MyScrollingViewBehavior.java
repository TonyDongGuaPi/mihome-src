package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class MyScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
    private CoordinatorLayout.Behavior mListenerBehavior;

    public MyScrollingViewBehavior() {
    }

    public MyScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setListenerBehavior(CoordinatorLayout.Behavior behavior) {
        this.mListenerBehavior = behavior;
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        boolean onDependentViewChanged = super.onDependentViewChanged(coordinatorLayout, view, view2);
        CoordinatorLayout.Behavior behavior = this.mListenerBehavior;
        return onDependentViewChanged;
    }
}
