package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontEditText;

public class FeedbackActivity_ViewBinding implements Unbinder {
    private FeedbackActivity target;

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity) {
        this(feedbackActivity, feedbackActivity.getWindow().getDecorView());
    }

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity, View view) {
        this.target = feedbackActivity;
        feedbackActivity.feedbackActivityContent = (FontEditText) Utils.findRequiredViewAsType(view, R.id.feedback_activity_content, "field 'feedbackActivityContent'", FontEditText.class);
        feedbackActivity.feedbackActivityNumberPicTx = (TextView) Utils.findRequiredViewAsType(view, R.id.feedback_activity_number_pic_tx, "field 'feedbackActivityNumberPicTx'", TextView.class);
        feedbackActivity.feedbackActivitySelectPicView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.feedback_activity_select_pic_view, "field 'feedbackActivitySelectPicView'", RecyclerView.class);
        feedbackActivity.feedbackActivityEmailEt = (FontEditText) Utils.findRequiredViewAsType(view, R.id.feedback_activity_email_et, "field 'feedbackActivityEmailEt'", FontEditText.class);
    }

    @CallSuper
    public void unbind() {
        FeedbackActivity feedbackActivity = this.target;
        if (feedbackActivity != null) {
            this.target = null;
            feedbackActivity.feedbackActivityContent = null;
            feedbackActivity.feedbackActivityNumberPicTx = null;
            feedbackActivity.feedbackActivitySelectPicView = null;
            feedbackActivity.feedbackActivityEmailEt = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
