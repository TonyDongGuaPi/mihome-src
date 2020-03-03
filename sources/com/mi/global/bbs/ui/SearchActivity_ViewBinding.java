package com.mi.global.bbs.ui;

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

public class SearchActivity_ViewBinding implements Unbinder {
    private SearchActivity target;
    private View view2131493634;
    private View view2131493912;
    private View view2131493946;

    @UiThread
    public SearchActivity_ViewBinding(SearchActivity searchActivity) {
        this(searchActivity, searchActivity.getWindow().getDecorView());
    }

    @UiThread
    public SearchActivity_ViewBinding(final SearchActivity searchActivity, View view) {
        this.target = searchActivity;
        searchActivity.searchView = (ClearEditText) Utils.findRequiredViewAsType(view, R.id.search_view, "field 'searchView'", ClearEditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.searchback, "field 'searchBack' and method 'onClick'");
        searchActivity.searchBack = (ImageView) Utils.castView(findRequiredView, R.id.searchback, "field 'searchBack'", ImageView.class);
        this.view2131493946 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchActivity.onClick(view);
            }
        });
        searchActivity.container = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.container, "field 'container'", FrameLayout.class);
        searchActivity.progress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.empty, "field 'progress'", ProgressBar.class);
        searchActivity.searchHistoryListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.search_history_results, "field 'searchHistoryListView'", RecyclerView.class);
        searchActivity.searchResultsTab = (PagerSlidingTabStrip) Utils.findRequiredViewAsType(view, R.id.search_results_top, "field 'searchResultsTab'", PagerSlidingTabStrip.class);
        searchActivity.searchPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.search_pager, "field 'searchPager'", ViewPager.class);
        searchActivity.searchResultsContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.search_results, "field 'searchResultsContent'", LinearLayout.class);
        searchActivity.mResultsContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.results_container, "field 'mResultsContainer'", FrameLayout.class);
        searchActivity.mSearchToolbarAgent = Utils.findRequiredView(view, R.id.search_toolbar_agent, "field 'mSearchToolbarAgent'");
        searchActivity.mSearchToolbarContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.search_toolbar_container, "field 'mSearchToolbarContainer'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.menu_search, "method 'onClick'");
        this.view2131493634 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.scrim, "method 'onClick'");
        this.view2131493912 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SearchActivity searchActivity = this.target;
        if (searchActivity != null) {
            this.target = null;
            searchActivity.searchView = null;
            searchActivity.searchBack = null;
            searchActivity.container = null;
            searchActivity.progress = null;
            searchActivity.searchHistoryListView = null;
            searchActivity.searchResultsTab = null;
            searchActivity.searchPager = null;
            searchActivity.searchResultsContent = null;
            searchActivity.mResultsContainer = null;
            searchActivity.mSearchToolbarAgent = null;
            searchActivity.mSearchToolbarContainer = null;
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
