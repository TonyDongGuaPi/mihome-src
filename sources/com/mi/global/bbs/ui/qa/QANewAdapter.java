package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.BaseLoadMoreAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.qa.QANewItem;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import java.util.ArrayList;
import java.util.List;

public class QANewAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<QANewItem.DataBean.NewItem> qaHomeNewItems = new ArrayList();

    public int getNormalViewType(int i) {
        return 0;
    }

    public class QANewHolder_ViewBinding implements Unbinder {
        private QANewHolder target;

        @UiThread
        public QANewHolder_ViewBinding(QANewHolder qANewHolder, View view) {
            this.target = qANewHolder;
            qANewHolder.itemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", TextView.class);
            qANewHolder.itemTime = (TextView) Utils.findRequiredViewAsType(view, R.id.item_time, "field 'itemTime'", TextView.class);
            qANewHolder.itemTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.item_target, "field 'itemTarget'", TextView.class);
            qANewHolder.itemWatch = (TextView) Utils.findRequiredViewAsType(view, R.id.item_watch, "field 'itemWatch'", TextView.class);
            qANewHolder.itemComment = (TextView) Utils.findRequiredViewAsType(view, R.id.item_comment, "field 'itemComment'", TextView.class);
            qANewHolder.itemAnswer = (TextView) Utils.findRequiredViewAsType(view, R.id.item_Answer, "field 'itemAnswer'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            QANewHolder qANewHolder = this.target;
            if (qANewHolder != null) {
                this.target = null;
                qANewHolder.itemTitle = null;
                qANewHolder.itemTime = null;
                qANewHolder.itemTarget = null;
                qANewHolder.itemWatch = null;
                qANewHolder.itemComment = null;
                qANewHolder.itemAnswer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public QANewAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
    }

    public void addData(List<QANewItem.DataBean.NewItem> list) {
        if (list != null) {
            this.qaHomeNewItems.clear();
            this.qaHomeNewItems.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.qaHomeNewItems.clear();
    }

    public int getDataItemCount() {
        return this.qaHomeNewItems.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return new QANewHolder(this.layoutInflater.inflate(R.layout.bbs_qa_home_new_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindQANewHolder((QANewHolder) viewHolder, i);
        }
    }

    private void bindQANewHolder(QANewHolder qANewHolder, int i) {
        handleCommonPart(qANewHolder, this.qaHomeNewItems.get(i));
    }

    private void handleCommonPart(QANewHolder qANewHolder, QANewItem.DataBean.NewItem newItem) {
        qANewHolder.itemTitle.setText(newItem.subject);
        qANewHolder.itemTime.setText(newItem.dateline);
        qANewHolder.itemTarget.setText(newItem.fname);
        qANewHolder.itemWatch.setText(newItem.views);
        qANewHolder.itemComment.setText(newItem.replies);
        qANewHolder.itemAnswer.setText(this.context.getResources().getString(R.string.qa_home_answer));
        setCommonPartClickListener(qANewHolder, newItem);
    }

    private void setCommonPartClickListener(QANewHolder qANewHolder, final QANewItem.DataBean.NewItem newItem) {
        qANewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, "click_thread", "new");
                WebActivity.jump(QANewAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{newItem.tid})));
            }
        });
        qANewHolder.itemAnswer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_ANSWER, "new");
                WebActivity.jump(QANewAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{newItem.tid})), true);
            }
        });
    }

    public class QANewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493476)
        TextView itemAnswer;
        @BindView(2131493486)
        TextView itemComment;
        @BindView(2131493510)
        TextView itemTarget;
        @BindView(2131493511)
        TextView itemTime;
        @BindView(2131493512)
        TextView itemTitle;
        @BindView(2131493517)
        TextView itemWatch;

        public QANewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
