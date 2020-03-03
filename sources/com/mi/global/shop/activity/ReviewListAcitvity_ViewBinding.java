package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;

public class ReviewListAcitvity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ReviewListAcitvity f5432a;

    @UiThread
    public ReviewListAcitvity_ViewBinding(ReviewListAcitvity reviewListAcitvity) {
        this(reviewListAcitvity, reviewListAcitvity.getWindow().getDecorView());
    }

    @UiThread
    public ReviewListAcitvity_ViewBinding(ReviewListAcitvity reviewListAcitvity, View view) {
        this.f5432a = reviewListAcitvity;
        reviewListAcitvity.reviewRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.review_recycleView, "field 'reviewRecycleView'", RecyclerView.class);
        reviewListAcitvity.loading = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.loading, "field 'loading'", LinearLayout.class);
        reviewListAcitvity.emptyError = (EmptyLoadingViewPlus) Utils.findRequiredViewAsType(view, R.id.empty_error, "field 'emptyError'", EmptyLoadingViewPlus.class);
        reviewListAcitvity.llTips = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_tips, "field 'llTips'", LinearLayout.class);
        reviewListAcitvity.llNoneReview = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_none_review, "field 'llNoneReview'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        ReviewListAcitvity reviewListAcitvity = this.f5432a;
        if (reviewListAcitvity != null) {
            this.f5432a = null;
            reviewListAcitvity.reviewRecycleView = null;
            reviewListAcitvity.loading = null;
            reviewListAcitvity.emptyError = null;
            reviewListAcitvity.llTips = null;
            reviewListAcitvity.llNoneReview = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
