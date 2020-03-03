package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;

public class ReviewSubmitAcitvity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ReviewSubmitAcitvity f5441a;

    @UiThread
    public ReviewSubmitAcitvity_ViewBinding(ReviewSubmitAcitvity reviewSubmitAcitvity) {
        this(reviewSubmitAcitvity, reviewSubmitAcitvity.getWindow().getDecorView());
    }

    @UiThread
    public ReviewSubmitAcitvity_ViewBinding(ReviewSubmitAcitvity reviewSubmitAcitvity, View view) {
        this.f5441a = reviewSubmitAcitvity;
        reviewSubmitAcitvity.ivOrder = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.iv_order, "field 'ivOrder'", SimpleDraweeView.class);
        reviewSubmitAcitvity.itemTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", CustomTextView.class);
        reviewSubmitAcitvity.itemRating = (RatingBar) Utils.findRequiredViewAsType(view, R.id.item_rating, "field 'itemRating'", RatingBar.class);
        reviewSubmitAcitvity.topReview = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.top_review, "field 'topReview'", LinearLayout.class);
        reviewSubmitAcitvity.etTitle = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.et_title, "field 'etTitle'", CustomEditTextView.class);
        reviewSubmitAcitvity.etReviewContent = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.et_review_content, "field 'etReviewContent'", CustomEditTextView.class);
        reviewSubmitAcitvity.btnSubmit = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_submit, "field 'btnSubmit'", CustomButtonView.class);
        reviewSubmitAcitvity.mGridView = (GridView) Utils.findRequiredViewAsType(view, R.id.grid_view, "field 'mGridView'", GridView.class);
        reviewSubmitAcitvity.mImageCountTipView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.image_count_tip, "field 'mImageCountTipView'", CustomTextView.class);
        reviewSubmitAcitvity.loading = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.loading, "field 'loading'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        ReviewSubmitAcitvity reviewSubmitAcitvity = this.f5441a;
        if (reviewSubmitAcitvity != null) {
            this.f5441a = null;
            reviewSubmitAcitvity.ivOrder = null;
            reviewSubmitAcitvity.itemTitle = null;
            reviewSubmitAcitvity.itemRating = null;
            reviewSubmitAcitvity.topReview = null;
            reviewSubmitAcitvity.etTitle = null;
            reviewSubmitAcitvity.etReviewContent = null;
            reviewSubmitAcitvity.btnSubmit = null;
            reviewSubmitAcitvity.mGridView = null;
            reviewSubmitAcitvity.mImageCountTipView = null;
            reviewSubmitAcitvity.loading = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
