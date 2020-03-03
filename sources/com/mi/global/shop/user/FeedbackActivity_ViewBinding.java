package com.mi.global.shop.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomEditTextView;

public class FeedbackActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private FeedbackActivity f7038a;

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity) {
        this(feedbackActivity, feedbackActivity.getWindow().getDecorView());
    }

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity, View view) {
        this.f7038a = feedbackActivity;
        feedbackActivity.feedbackContent = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.feedback_content, "field 'feedbackContent'", CustomEditTextView.class);
        feedbackActivity.feedbackInfo = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.feedback_info, "field 'feedbackInfo'", CustomEditTextView.class);
        feedbackActivity.btnSubmit = (CommonButton) Utils.findRequiredViewAsType(view, R.id.btn_submit, "field 'btnSubmit'", CommonButton.class);
    }

    @CallSuper
    public void unbind() {
        FeedbackActivity feedbackActivity = this.f7038a;
        if (feedbackActivity != null) {
            this.f7038a = null;
            feedbackActivity.feedbackContent = null;
            feedbackActivity.feedbackInfo = null;
            feedbackActivity.btnSubmit = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
