package android.support.design.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class TopMaskBehavior extends ViewOffsetBehavior<View> {
    public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
        return super.getLeftAndRightOffset();
    }

    public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
        return super.getTopAndBottomOffset();
    }

    public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        return super.onLayoutChild(coordinatorLayout, view, i);
    }

    public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i) {
        return super.setLeftAndRightOffset(i);
    }

    public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
        return super.setTopAndBottomOffset(i);
    }

    public TopMaskBehavior() {
    }

    public TopMaskBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean layoutDependsOn(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull View view2) {
        return view2 instanceof AppBarLayout;
    }

    public boolean onDependentViewChanged(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull View view2) {
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).getBehavior();
        if (!(behavior instanceof AppBarLayout.BaseBehavior)) {
            return false;
        }
        setTopAndBottomOffset(((AppBarLayout.BaseBehavior) behavior).getTopAndBottomOffset());
        return true;
    }
}
