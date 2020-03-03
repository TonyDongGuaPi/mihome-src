package android.support.design.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.MyIndicator;

public class TopIndicatorBehavior extends CoordinatorLayout.Behavior<MyIndicator> {
    private static final int INVALID_VALUE = 0;
    private static final String TAG = "TopIndicatorBehavior";
    private int defaultMarginForText = UIUtil.a(SHApplication.getAppContext(), 24.0d);
    private int defaultViewpagerIndicatorMargin = UIUtil.a(SHApplication.getAppContext(), 32.0d);
    private int deltaHeight = UIUtil.a(SHApplication.getAppContext(), 16.0d);
    private int deltaMargin = this.deltaHeight;
    private int mLastOffset = 0;
    private int mPrimaryIndicatorHeight = 0;

    public TopIndicatorBehavior() {
    }

    public TopIndicatorBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean layoutDependsOn(@NonNull CoordinatorLayout coordinatorLayout, @NonNull MyIndicator myIndicator, @NonNull View view) {
        return view instanceof AppBarLayout;
    }

    public boolean onDependentViewChanged(@NonNull CoordinatorLayout coordinatorLayout, @NonNull MyIndicator myIndicator, @NonNull View view) {
        try {
            onOffsetChanged((AppBarLayout) view, myIndicator, coordinatorLayout.findViewById(R.id.viewpager), ((AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) view.getLayoutParams()).getBehavior()).getTopAndBottomOffset());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, MyIndicator myIndicator, View view, int i) {
        if (this.mPrimaryIndicatorHeight == 0) {
            this.mPrimaryIndicatorHeight = myIndicator.getHeight();
        }
        if (i != 0 && this.mLastOffset != i) {
            this.mLastOffset = i;
            float abs = Math.abs(((float) i) / ((float) appBarLayout.getTotalScrollRange()));
            ((ViewGroup.MarginLayoutParams) myIndicator.getLayoutParams()).height = (int) (((float) this.mPrimaryIndicatorHeight) + (((float) this.deltaHeight) * abs));
            myIndicator.setPadding(myIndicator.getPaddingLeft(), myIndicator.getPaddingTop(), myIndicator.getPaddingRight(), (int) (((float) (this.defaultMarginForText / 2)) * abs));
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = (int) (((float) this.defaultViewpagerIndicatorMargin) + (((float) this.deltaMargin) * abs));
            view.forceLayout();
            myIndicator.requestLayout();
        }
    }
}
