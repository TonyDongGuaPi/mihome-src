package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class QAHomeActivity_ViewBinding implements Unbinder {
    private QAHomeActivity target;

    @UiThread
    public QAHomeActivity_ViewBinding(QAHomeActivity qAHomeActivity) {
        this(qAHomeActivity, qAHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public QAHomeActivity_ViewBinding(QAHomeActivity qAHomeActivity, View view) {
        this.target = qAHomeActivity;
        qAHomeActivity.qaHomeTopBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.qa_home_top_img, "field 'qaHomeTopBg'", ImageView.class);
        qAHomeActivity.qaHomeNameText = (TextView) Utils.findRequiredViewAsType(view, R.id.qa_home_name_text, "field 'qaHomeNameText'", TextView.class);
        qAHomeActivity.qaHomeDes = (TextView) Utils.findRequiredViewAsType(view, R.id.qa_home_des, "field 'qaHomeDes'", TextView.class);
        qAHomeActivity.qaHomeNagTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.qa_home_nag, "field 'qaHomeNagTab'", PagerSlidingTabStrip.class);
        qAHomeActivity.qaHomePager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.qa_home_pager, "field 'qaHomePager'", ViewPager.class);
        qAHomeActivity.qaHomeNavigationContainer = (AbsorbNavigationLayout) Utils.findRequiredViewAsType(view, R.id.qa_home_navigation_container, "field 'qaHomeNavigationContainer'", AbsorbNavigationLayout.class);
        qAHomeActivity.qaHomeTopLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.qa_home_top_layout, "field 'qaHomeTopLayout'", LinearLayout.class);
        qAHomeActivity.fabImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.fab, "field 'fabImageView'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        QAHomeActivity qAHomeActivity = this.target;
        if (qAHomeActivity != null) {
            this.target = null;
            qAHomeActivity.qaHomeTopBg = null;
            qAHomeActivity.qaHomeNameText = null;
            qAHomeActivity.qaHomeDes = null;
            qAHomeActivity.qaHomeNagTab = null;
            qAHomeActivity.qaHomePager = null;
            qAHomeActivity.qaHomeNavigationContainer = null;
            qAHomeActivity.qaHomeTopLayout = null;
            qAHomeActivity.fabImageView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
