package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.Editor.FontTextView;

public class QARequestFragment_ViewBinding implements Unbinder {
    private QARequestFragment target;

    @UiThread
    public QARequestFragment_ViewBinding(QARequestFragment qARequestFragment, View view) {
        this.target = qARequestFragment;
        qARequestFragment.requestMore = (FontTextView) Utils.findRequiredViewAsType(view, R.id.qa_request_more, "field 'requestMore'", FontTextView.class);
        qARequestFragment.requestEmptyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.request_empty_layout, "field 'requestEmptyLayout'", LinearLayout.class);
        qARequestFragment.questionEmptyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.question_empty_layout, "field 'questionEmptyLayout'", LinearLayout.class);
        qARequestFragment.itemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.item_icon, "field 'itemIcon'", CircleImageView.class);
        qARequestFragment.itemName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_name, "field 'itemName'", TextView.class);
        qARequestFragment.itemContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_answer_content, "field 'itemContent'", TextView.class);
        qARequestFragment.itemTime = (TextView) Utils.findRequiredViewAsType(view, R.id.time, "field 'itemTime'", TextView.class);
        qARequestFragment.answer = (TextView) Utils.findRequiredViewAsType(view, R.id.answer, "field 'answer'", TextView.class);
        qARequestFragment.ignore = (TextView) Utils.findRequiredViewAsType(view, R.id.ignore, "field 'ignore'", TextView.class);
        qARequestFragment.answerImgLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.answer_img_layout, "field 'answerImgLayout'", LinearLayout.class);
        qARequestFragment.qaRequestItemLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.qa_request_more_item_layout, "field 'qaRequestItemLayout'", LinearLayout.class);
        qARequestFragment.qaRequestItemLayoutDivider = Utils.findRequiredView(view, R.id.qa_request_more_item_divider, "field 'qaRequestItemLayoutDivider'");
        qARequestFragment.qaRequestViewMoreDivider = Utils.findRequiredView(view, R.id.qa_request_view_more_divider, "field 'qaRequestViewMoreDivider'");
        qARequestFragment.listLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.list_layout, "field 'listLayout'", LinearLayout.class);
        qARequestFragment.noLoginLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.no_login_layout, "field 'noLoginLayout'", RelativeLayout.class);
        qARequestFragment.noLoginBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.follow_login_bt, "field 'noLoginBtn'", TextView.class);
        qARequestFragment.mRefreshView = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.common_refresh_view, "field 'mRefreshView'", SwipeRefreshLayout.class);
        qARequestFragment.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.common_recycle_view, "field 'mRecycleView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        QARequestFragment qARequestFragment = this.target;
        if (qARequestFragment != null) {
            this.target = null;
            qARequestFragment.requestMore = null;
            qARequestFragment.requestEmptyLayout = null;
            qARequestFragment.questionEmptyLayout = null;
            qARequestFragment.itemIcon = null;
            qARequestFragment.itemName = null;
            qARequestFragment.itemContent = null;
            qARequestFragment.itemTime = null;
            qARequestFragment.answer = null;
            qARequestFragment.ignore = null;
            qARequestFragment.answerImgLayout = null;
            qARequestFragment.qaRequestItemLayout = null;
            qARequestFragment.qaRequestItemLayoutDivider = null;
            qARequestFragment.qaRequestViewMoreDivider = null;
            qARequestFragment.listLayout = null;
            qARequestFragment.noLoginLayout = null;
            qARequestFragment.noLoginBtn = null;
            qARequestFragment.mRefreshView = null;
            qARequestFragment.mRecycleView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
