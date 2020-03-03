package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.MsgView;
import com.mi.global.bbs.view.swipe.MySwipeRefreshLayout;

public class WebActivity_ViewBinding implements Unbinder {
    private WebActivity target;
    private View view2131492904;
    private View view2131492906;
    private View view2131494258;
    private View view2131494259;

    @UiThread
    public WebActivity_ViewBinding(WebActivity webActivity) {
        this(webActivity, webActivity.getWindow().getDecorView());
    }

    @UiThread
    public WebActivity_ViewBinding(final WebActivity webActivity, View view) {
        this.target = webActivity;
        webActivity.webActivityProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.web_activity_progressBar, "field 'webActivityProgressBar'", ProgressBar.class);
        webActivity.webActivityWebView = (ObservableWebView) Utils.findRequiredViewAsType(view, R.id.web_activity_webView, "field 'webActivityWebView'", ObservableWebView.class);
        webActivity.webActivityRefreshView = (MySwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.web_activity_refresh_view, "field 'webActivityRefreshView'", MySwipeRefreshLayout.class);
        webActivity.webActivityBottomLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.web_activity_bottom_layout, "field 'webActivityBottomLayout'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.web_activity_likeBt, "field 'webActivityLikeBt' and method 'onClick'");
        webActivity.webActivityLikeBt = (MsgView) Utils.castView(findRequiredView, R.id.web_activity_likeBt, "field 'webActivityLikeBt'", MsgView.class);
        this.view2131494259 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webActivity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.web_activity_commentBt, "field 'webActivityCommentBt' and method 'onClick'");
        webActivity.webActivityCommentBt = (MsgView) Utils.castView(findRequiredView2, R.id.web_activity_commentBt, "field 'webActivityCommentBt'", MsgView.class);
        this.view2131494258 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webActivity.onClick(view);
            }
        });
        webActivity.mActivityCommentsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_comments_edit_text, "field 'mActivityCommentsEditText'", EditText.class);
        webActivity.mCommentLayoutContainer = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.comment_layout_container, "field 'mCommentLayoutContainer'", RelativeLayout.class);
        webActivity.mActivityCommentsSelectList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_comments_select_list, "field 'mActivityCommentsSelectList'", RecyclerView.class);
        webActivity.mWebActivityReplyBt = (AppCompatEditText) Utils.findRequiredViewAsType(view, R.id.web_activity_replyBt, "field 'mWebActivityReplyBt'", AppCompatEditText.class);
        webActivity.mWebActivityBottomSheet = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.web_activity_bottom_sheet, "field 'mWebActivityBottomSheet'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.activity_comments_pick_pic_bt, "method 'onClick'");
        this.view2131492904 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webActivity.onClick(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.activity_comments_send_bt, "method 'onClick'");
        this.view2131492906 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        WebActivity webActivity = this.target;
        if (webActivity != null) {
            this.target = null;
            webActivity.webActivityProgressBar = null;
            webActivity.webActivityWebView = null;
            webActivity.webActivityRefreshView = null;
            webActivity.webActivityBottomLayout = null;
            webActivity.webActivityLikeBt = null;
            webActivity.webActivityCommentBt = null;
            webActivity.mActivityCommentsEditText = null;
            webActivity.mCommentLayoutContainer = null;
            webActivity.mActivityCommentsSelectList = null;
            webActivity.mWebActivityReplyBt = null;
            webActivity.mWebActivityBottomSheet = null;
            this.view2131494259.setOnClickListener((View.OnClickListener) null);
            this.view2131494259 = null;
            this.view2131494258.setOnClickListener((View.OnClickListener) null);
            this.view2131494258 = null;
            this.view2131492904.setOnClickListener((View.OnClickListener) null);
            this.view2131492904 = null;
            this.view2131492906.setOnClickListener((View.OnClickListener) null);
            this.view2131492906 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
