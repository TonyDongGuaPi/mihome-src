package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.MsgView;

public class PostDetailActivity_ViewBinding implements Unbinder {
    private PostDetailActivity target;
    private View view2131492904;
    private View view2131492906;
    private View view2131493559;
    private View view2131494258;
    private View view2131494259;

    @UiThread
    public PostDetailActivity_ViewBinding(PostDetailActivity postDetailActivity) {
        this(postDetailActivity, postDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public PostDetailActivity_ViewBinding(final PostDetailActivity postDetailActivity, View view) {
        this.target = postDetailActivity;
        postDetailActivity.mRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", ObservableRecyclerView.class);
        postDetailActivity.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        postDetailActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.activity_post_progress, "field 'mProgress'", ProgressBar.class);
        postDetailActivity.webActivityBottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.web_activity_bottom_layout, "field 'webActivityBottomLayout'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.web_activity_likeBt, "field 'webActivityLikeBt' and method 'onClick'");
        postDetailActivity.webActivityLikeBt = (MsgView) Utils.castView(findRequiredView, R.id.web_activity_likeBt, "field 'webActivityLikeBt'", MsgView.class);
        this.view2131494259 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                postDetailActivity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.web_activity_commentBt, "field 'webActivityCommentBt' and method 'onClick'");
        postDetailActivity.webActivityCommentBt = (MsgView) Utils.castView(findRequiredView2, R.id.web_activity_commentBt, "field 'webActivityCommentBt'", MsgView.class);
        this.view2131494258 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                postDetailActivity.onClick(view);
            }
        });
        postDetailActivity.mActivityCommentsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_comments_edit_text, "field 'mActivityCommentsEditText'", EditText.class);
        postDetailActivity.mCommentLayoutContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.comment_layout_container, "field 'mCommentLayoutContainer'", RelativeLayout.class);
        postDetailActivity.mActivityCommentsSelectList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_comments_select_list, "field 'mActivityCommentsSelectList'", RecyclerView.class);
        postDetailActivity.mWebActivityReplyBt = (AppCompatEditText) Utils.findRequiredViewAsType(view, R.id.web_activity_replyBt, "field 'mWebActivityReplyBt'", AppCompatEditText.class);
        postDetailActivity.mWebActivityBottomSheet = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.web_activity_bottom_sheet, "field 'mWebActivityBottomSheet'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.layout_post_failed, "field 'layoutPostFailed' and method 'onClick'");
        postDetailActivity.layoutPostFailed = (RelativeLayout) Utils.castView(findRequiredView3, R.id.layout_post_failed, "field 'layoutPostFailed'", RelativeLayout.class);
        this.view2131493559 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                postDetailActivity.onClick(view);
            }
        });
        postDetailActivity.layoutPostDeleted = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_post_deleted, "field 'layoutPostDeleted'", RelativeLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.activity_comments_pick_pic_bt, "method 'onClick'");
        this.view2131492904 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                postDetailActivity.onClick(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.activity_comments_send_bt, "method 'onClick'");
        this.view2131492906 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                postDetailActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        PostDetailActivity postDetailActivity = this.target;
        if (postDetailActivity != null) {
            this.target = null;
            postDetailActivity.mRecycleView = null;
            postDetailActivity.mRefreshView = null;
            postDetailActivity.mProgress = null;
            postDetailActivity.webActivityBottomLayout = null;
            postDetailActivity.webActivityLikeBt = null;
            postDetailActivity.webActivityCommentBt = null;
            postDetailActivity.mActivityCommentsEditText = null;
            postDetailActivity.mCommentLayoutContainer = null;
            postDetailActivity.mActivityCommentsSelectList = null;
            postDetailActivity.mWebActivityReplyBt = null;
            postDetailActivity.mWebActivityBottomSheet = null;
            postDetailActivity.layoutPostFailed = null;
            postDetailActivity.layoutPostDeleted = null;
            this.view2131494259.setOnClickListener((View.OnClickListener) null);
            this.view2131494259 = null;
            this.view2131494258.setOnClickListener((View.OnClickListener) null);
            this.view2131494258 = null;
            this.view2131493559.setOnClickListener((View.OnClickListener) null);
            this.view2131493559 = null;
            this.view2131492904.setOnClickListener((View.OnClickListener) null);
            this.view2131492904 = null;
            this.view2131492906.setOnClickListener((View.OnClickListener) null);
            this.view2131492906 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
