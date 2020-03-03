package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.ClearEditText;
import com.mi.global.bbs.view.PagerSlidingTabStrip;

public class QASearchActivity_ViewBinding implements Unbinder {
    private QASearchActivity target;
    private View view2131493634;
    private View view2131493912;
    private View view2131493946;

    @UiThread
    public QASearchActivity_ViewBinding(QASearchActivity qASearchActivity) {
        this(qASearchActivity, qASearchActivity.getWindow().getDecorView());
    }

    @UiThread
    public QASearchActivity_ViewBinding(final QASearchActivity qASearchActivity, View view) {
        this.target = qASearchActivity;
        qASearchActivity.searchView = (ClearEditText) Utils.findRequiredViewAsType(view, R.id.search_view, "field 'searchView'", ClearEditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.searchback, "field 'searchBack' and method 'onClick'");
        qASearchActivity.searchBack = (ImageView) Utils.castView(findRequiredView, R.id.searchback, "field 'searchBack'", ImageView.class);
        this.view2131493946 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qASearchActivity.onClick(view);
            }
        });
        qASearchActivity.container = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.container, "field 'container'", FrameLayout.class);
        qASearchActivity.progress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.empty, "field 'progress'", ProgressBar.class);
        qASearchActivity.searchHistoryListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.search_history_results, "field 'searchHistoryListView'", RecyclerView.class);
        qASearchActivity.searchResultsTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.search_results_top, "field 'searchResultsTab'", PagerSlidingTabStrip.class);
        qASearchActivity.searchResultsTabDivide = Utils.findRequiredView(view, R.id.search_results_top_divide, "field 'searchResultsTabDivide'");
        qASearchActivity.searchPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.search_pager, "field 'searchPager'", ViewPager.class);
        qASearchActivity.searchResultsContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.search_results, "field 'searchResultsContent'", LinearLayout.class);
        qASearchActivity.mResultsContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.results_container, "field 'mResultsContainer'", FrameLayout.class);
        qASearchActivity.mSearchToolbarAgent = Utils.findRequiredView(view, R.id.search_toolbar_agent, "field 'mSearchToolbarAgent'");
        qASearchActivity.mSearchToolbarContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.search_toolbar_container, "field 'mSearchToolbarContainer'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.menu_search, "method 'onClick'");
        this.view2131493634 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qASearchActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.scrim, "method 'onClick'");
        this.view2131493912 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qASearchActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        QASearchActivity qASearchActivity = this.target;
        if (qASearchActivity != null) {
            this.target = null;
            qASearchActivity.searchView = null;
            qASearchActivity.searchBack = null;
            qASearchActivity.container = null;
            qASearchActivity.progress = null;
            qASearchActivity.searchHistoryListView = null;
            qASearchActivity.searchResultsTab = null;
            qASearchActivity.searchResultsTabDivide = null;
            qASearchActivity.searchPager = null;
            qASearchActivity.searchResultsContent = null;
            qASearchActivity.mResultsContainer = null;
            qASearchActivity.mSearchToolbarAgent = null;
            qASearchActivity.mSearchToolbarContainer = null;
            this.view2131493946.setOnClickListener((View.OnClickListener) null);
            this.view2131493946 = null;
            this.view2131493634.setOnClickListener((View.OnClickListener) null);
            this.view2131493634 = null;
            this.view2131493912.setOnClickListener((View.OnClickListener) null);
            this.view2131493912 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
