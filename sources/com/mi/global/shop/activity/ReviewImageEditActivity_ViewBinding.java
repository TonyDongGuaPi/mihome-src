package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;

public class ReviewImageEditActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ReviewImageEditActivity f5427a;

    @UiThread
    public ReviewImageEditActivity_ViewBinding(ReviewImageEditActivity reviewImageEditActivity) {
        this(reviewImageEditActivity, reviewImageEditActivity.getWindow().getDecorView());
    }

    @UiThread
    public ReviewImageEditActivity_ViewBinding(ReviewImageEditActivity reviewImageEditActivity, View view) {
        this.f5427a = reviewImageEditActivity;
        reviewImageEditActivity.ivImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_image, "field 'ivImage'", ImageView.class);
        reviewImageEditActivity.ivEditCrop = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_edit_crop, "field 'ivEditCrop'", ImageView.class);
        reviewImageEditActivity.rlEditCrop = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_edit_crop, "field 'rlEditCrop'", RelativeLayout.class);
        reviewImageEditActivity.ivEditDelete = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_edit_delete, "field 'ivEditDelete'", ImageView.class);
        reviewImageEditActivity.rlEditDelete = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_edit_delete, "field 'rlEditDelete'", RelativeLayout.class);
        reviewImageEditActivity.btnDone = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_done, "field 'btnDone'", CustomButtonView.class);
        reviewImageEditActivity.footer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.footer, "field 'footer'", RelativeLayout.class);
    }

    @CallSuper
    public void unbind() {
        ReviewImageEditActivity reviewImageEditActivity = this.f5427a;
        if (reviewImageEditActivity != null) {
            this.f5427a = null;
            reviewImageEditActivity.ivImage = null;
            reviewImageEditActivity.ivEditCrop = null;
            reviewImageEditActivity.rlEditCrop = null;
            reviewImageEditActivity.ivEditDelete = null;
            reviewImageEditActivity.rlEditDelete = null;
            reviewImageEditActivity.btnDone = null;
            reviewImageEditActivity.footer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
