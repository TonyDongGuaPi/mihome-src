package com.mi.global.bbs.ui;

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

public class ShortContentDetailActivity_ViewBinding implements Unbinder {
    private ShortContentDetailActivity target;
    private View view2131492904;
    private View view2131492906;

    @UiThread
    public ShortContentDetailActivity_ViewBinding(ShortContentDetailActivity shortContentDetailActivity) {
        this(shortContentDetailActivity, shortContentDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShortContentDetailActivity_ViewBinding(final ShortContentDetailActivity shortContentDetailActivity, View view) {
        this.target = shortContentDetailActivity;
        shortContentDetailActivity.mCommonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mCommonRecycleView'", ObservableRecyclerView.class);
        shortContentDetailActivity.mCommonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mCommonRefreshView'", SwipeRefreshLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_comments_pick_pic_bt, "field 'mActivityCommentsPickPicBt' and method 'onClick'");
        shortContentDetailActivity.mActivityCommentsPickPicBt = (ImageView) Utils.castView(findRequiredView, R.id.activity_comments_pick_pic_bt, "field 'mActivityCommentsPickPicBt'", ImageView.class);
        this.view2131492904 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shortContentDetailActivity.onClick(view);
            }
        });
        shortContentDetailActivity.mActivityCommentsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_comments_edit_text, "field 'mActivityCommentsEditText'", EditText.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.activity_comments_send_bt, "field 'mActivityCommentsSendBt' and method 'onClick'");
        shortContentDetailActivity.mActivityCommentsSendBt = (TextView) Utils.castView(findRequiredView2, R.id.activity_comments_send_bt, "field 'mActivityCommentsSendBt'", TextView.class);
        this.view2131492906 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                shortContentDetailActivity.onClick(view);
            }
        });
        shortContentDetailActivity.mReplyHorizontalLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.reply_horizontal_layout, "field 'mReplyHorizontalLayout'", LinearLayout.class);
        shortContentDetailActivity.mActivityCommentsSelectList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_comments_select_list, "field 'mActivityCommentsSelectList'", RecyclerView.class);
        shortContentDetailActivity.mCommentLayoutContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.comment_layout_container, "field 'mCommentLayoutContainer'", RelativeLayout.class);
        shortContentDetailActivity.mReplyBt = (AppCompatEditText) Utils.findRequiredViewAsType(view, R.id.replyBt, "field 'mReplyBt'", AppCompatEditText.class);
        shortContentDetailActivity.mLikeBt = (MsgView) Utils.findRequiredViewAsType(view, R.id.likeBt, "field 'mLikeBt'", MsgView.class);
        shortContentDetailActivity.mBottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bottom_layout, "field 'mBottomLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        ShortContentDetailActivity shortContentDetailActivity = this.target;
        if (shortContentDetailActivity != null) {
            this.target = null;
            shortContentDetailActivity.mCommonRecycleView = null;
            shortContentDetailActivity.mCommonRefreshView = null;
            shortContentDetailActivity.mActivityCommentsPickPicBt = null;
            shortContentDetailActivity.mActivityCommentsEditText = null;
            shortContentDetailActivity.mActivityCommentsSendBt = null;
            shortContentDetailActivity.mReplyHorizontalLayout = null;
            shortContentDetailActivity.mActivityCommentsSelectList = null;
            shortContentDetailActivity.mCommentLayoutContainer = null;
            shortContentDetailActivity.mReplyBt = null;
            shortContentDetailActivity.mLikeBt = null;
            shortContentDetailActivity.mBottomLayout = null;
            this.view2131492904.setOnClickListener((View.OnClickListener) null);
            this.view2131492904 = null;
            this.view2131492906.setOnClickListener((View.OnClickListener) null);
            this.view2131492906 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
