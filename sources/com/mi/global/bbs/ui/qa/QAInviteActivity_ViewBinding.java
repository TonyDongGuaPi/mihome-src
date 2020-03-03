package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.ClearEditText;

public class QAInviteActivity_ViewBinding implements Unbinder {
    private QAInviteActivity target;
    private View view2131493634;
    private View view2131493857;
    private View view2131493946;

    @UiThread
    public QAInviteActivity_ViewBinding(QAInviteActivity qAInviteActivity) {
        this(qAInviteActivity, qAInviteActivity.getWindow().getDecorView());
    }

    @UiThread
    public QAInviteActivity_ViewBinding(final QAInviteActivity qAInviteActivity, View view) {
        this.target = qAInviteActivity;
        qAInviteActivity.searchView = (ClearEditText) Utils.findRequiredViewAsType(view, R.id.search_view, "field 'searchView'", ClearEditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.searchback, "field 'searchBack' and method 'onClick'");
        qAInviteActivity.searchBack = (ImageView) Utils.castView(findRequiredView, R.id.searchback, "field 'searchBack'", ImageView.class);
        this.view2131493946 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qAInviteActivity.onClick(view);
            }
        });
        qAInviteActivity.frameRecycleView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.fragment_invite_content, "field 'frameRecycleView'", RelativeLayout.class);
        qAInviteActivity.doneLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.qa_invite_done_layout, "field 'doneLayout'", RelativeLayout.class);
        qAInviteActivity.noResult = (ViewStub) Utils.findRequiredViewAsType(view, R.id.stub_no_search_results, "field 'noResult'", ViewStub.class);
        qAInviteActivity.mProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.fragment_invite_progress, "field 'mProgress'", ProgressBar.class);
        qAInviteActivity.mRecycleView = (ObservableRecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", ObservableRecyclerView.class);
        qAInviteActivity.mSearchToolbarAgent = Utils.findRequiredView(view, R.id.search_toolbar_agent, "field 'mSearchToolbarAgent'");
        qAInviteActivity.countTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.qa_invite_count, "field 'countTextView'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.qa_invite_done, "method 'onClick'");
        this.view2131493857 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qAInviteActivity.onClick(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.menu_search, "method 'onClick'");
        this.view2131493634 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                qAInviteActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        QAInviteActivity qAInviteActivity = this.target;
        if (qAInviteActivity != null) {
            this.target = null;
            qAInviteActivity.searchView = null;
            qAInviteActivity.searchBack = null;
            qAInviteActivity.frameRecycleView = null;
            qAInviteActivity.doneLayout = null;
            qAInviteActivity.noResult = null;
            qAInviteActivity.mProgress = null;
            qAInviteActivity.mRecycleView = null;
            qAInviteActivity.mSearchToolbarAgent = null;
            qAInviteActivity.countTextView = null;
            this.view2131493946.setOnClickListener((View.OnClickListener) null);
            this.view2131493946 = null;
            this.view2131493857.setOnClickListener((View.OnClickListener) null);
            this.view2131493857 = null;
            this.view2131493634.setOnClickListener((View.OnClickListener) null);
            this.view2131493634 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
