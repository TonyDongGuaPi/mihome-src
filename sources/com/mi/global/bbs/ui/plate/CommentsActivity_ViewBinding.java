package com.mi.global.bbs.ui.plate;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;

public class CommentsActivity_ViewBinding implements Unbinder {
    private CommentsActivity target;
    private View view2131492904;
    private View view2131492906;

    @UiThread
    public CommentsActivity_ViewBinding(CommentsActivity commentsActivity) {
        this(commentsActivity, commentsActivity.getWindow().getDecorView());
    }

    @UiThread
    public CommentsActivity_ViewBinding(final CommentsActivity commentsActivity, View view) {
        this.target = commentsActivity;
        commentsActivity.commonRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'commonRecycleView'", ObservableRecyclerView.class);
        commentsActivity.commonRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'commonRefreshView'", SwipeRefreshLayout.class);
        commentsActivity.activityCommentsEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_comments_edit_text, "field 'activityCommentsEditText'", EditText.class);
        commentsActivity.activityCommentsSelectList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.activity_comments_select_list, "field 'activityCommentsSelectList'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_comments_pick_pic_bt, "method 'onClick'");
        this.view2131492904 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                commentsActivity.onClick(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.activity_comments_send_bt, "method 'onClick'");
        this.view2131492906 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                commentsActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        CommentsActivity commentsActivity = this.target;
        if (commentsActivity != null) {
            this.target = null;
            commentsActivity.commonRecycleView = null;
            commentsActivity.commonRefreshView = null;
            commentsActivity.activityCommentsEditText = null;
            commentsActivity.activityCommentsSelectList = null;
            this.view2131492904.setOnClickListener((View.OnClickListener) null);
            this.view2131492904 = null;
            this.view2131492906.setOnClickListener((View.OnClickListener) null);
            this.view2131492906 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
