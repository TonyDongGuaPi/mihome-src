package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class SearchFragment_ViewBinding implements Unbinder {
    private SearchFragment target;

    @UiThread
    public SearchFragment_ViewBinding(SearchFragment searchFragment, View view) {
        this.target = searchFragment;
        searchFragment.searchProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.search_progress, "field 'searchProgress'", ProgressBar.class);
        searchFragment.searchResultsList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.search_results_list, "field 'searchResultsList'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        SearchFragment searchFragment = this.target;
        if (searchFragment != null) {
            this.target = null;
            searchFragment.searchProgress = null;
            searchFragment.searchResultsList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
