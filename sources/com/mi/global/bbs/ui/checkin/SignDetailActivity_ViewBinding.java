package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.MsgView;

public class SignDetailActivity_ViewBinding implements Unbinder {
    private SignDetailActivity target;
    private View view2131492904;
    private View view2131492906;

    @UiThread
    public SignDetailActivity_ViewBinding(SignDetailActivity signDetailActivity) {
        this(signDetailActivity, signDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public SignDetailActivity_ViewBinding(final SignDetailActivity signDetailActivity, View view) {
        this.target = signDetailActivity;
        signDetailActivity.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        signDetailActivity.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_comments_pick_pic_bt, "field 'mActivityCommentsPickPicBt' and method 'onClick'");
        signDetailActivity.mActivityCommentsPickPicBt = (ImageView) Utils.castView(findRequiredView, R.id.activity_comments_pick_pic_bt, "field 'mActivityCommentsPickPicBt'", ImageView.class);
        this.view2131492904 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signDetailActivity.onClick(view);
            }
        });
        signDetailActivity.mActivityCommentsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_comments_edit_text, "field 'mActivityCommentsEditText'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.activity_comments_send_bt, "field 'mActivityCommentsSendBt' and method 'onClick'");
        signDetailActivity.mActivityCommentsSendBt = (TextView) Utils.castView(findRequiredView2, R.id.activity_comments_send_bt, "field 'mActivityCommentsSendBt'", TextView.class);
        this.view2131492906 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signDetailActivity.onClick(view);
            }
        });
        signDetailActivity.mReplyHorizontalLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.reply_horizontal_layout, "field 'mReplyHorizontalLayout'", LinearLayout.class);
        signDetailActivity.mActivityCommentsSelectList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_comments_select_list, "field 'mActivityCommentsSelectList'", RecyclerView.class);
        signDetailActivity.mCommentLayoutContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.comment_layout_container, "field 'mCommentLayoutContainer'", RelativeLayout.class);
        signDetailActivity.mReplyBt = (AppCompatEditText) Utils.findRequiredViewAsType(view, R.id.replyBt, "field 'mReplyBt'", AppCompatEditText.class);
        signDetailActivity.mLikeBt = (MsgView) Utils.findRequiredViewAsType(view, R.id.likeBt, "field 'mLikeBt'", MsgView.class);
        signDetailActivity.mBottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bottom_layout, "field 'mBottomLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        SignDetailActivity signDetailActivity = this.target;
        if (signDetailActivity != null) {
            this.target = null;
            signDetailActivity.mCommonRecycleView = null;
            signDetailActivity.mCommonRefreshView = null;
            signDetailActivity.mActivityCommentsPickPicBt = null;
            signDetailActivity.mActivityCommentsEditText = null;
            signDetailActivity.mActivityCommentsSendBt = null;
            signDetailActivity.mReplyHorizontalLayout = null;
            signDetailActivity.mActivityCommentsSelectList = null;
            signDetailActivity.mCommentLayoutContainer = null;
            signDetailActivity.mReplyBt = null;
            signDetailActivity.mLikeBt = null;
            signDetailActivity.mBottomLayout = null;
            this.view2131492904.setOnClickListener((View.OnClickListener) null);
            this.view2131492904 = null;
            this.view2131492906.setOnClickListener((View.OnClickListener) null);
            this.view2131492906 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
