package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class ColumnHomeActivity_ViewBinding implements Unbinder {
    private ColumnHomeActivity target;

    @UiThread
    public ColumnHomeActivity_ViewBinding(ColumnHomeActivity columnHomeActivity) {
        this(columnHomeActivity, columnHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public ColumnHomeActivity_ViewBinding(ColumnHomeActivity columnHomeActivity, View view) {
        this.target = columnHomeActivity;
        columnHomeActivity.columnHomeNameText = (TextView) Utils.findRequiredViewAsType(view, R.id.column_home_name_text, "field 'columnHomeNameText'", TextView.class);
        columnHomeActivity.columnHomeNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.column_home_nag, "field 'columnHomeNagTab'", PagerSlidingTabStrip.class);
        columnHomeActivity.columnHomePager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.column_home_pager, "field 'columnHomePager'", ViewPager.class);
        columnHomeActivity.columnHomeNavigationContainer = (AbsorbNavigationLayout) Utils.findRequiredViewAsType(view, R.id.column_home_navigation_container, "field 'columnHomeNavigationContainer'", AbsorbNavigationLayout.class);
    }

    @CallSuper
    public void unbind() {
        ColumnHomeActivity columnHomeActivity = this.target;
        if (columnHomeActivity != null) {
            this.target = null;
            columnHomeActivity.columnHomeNameText = null;
            columnHomeActivity.columnHomeNagTab = null;
            columnHomeActivity.columnHomePager = null;
            columnHomeActivity.columnHomeNavigationContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
