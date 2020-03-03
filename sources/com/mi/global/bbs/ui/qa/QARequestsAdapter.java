package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.BaseLoadMoreAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.qa.QARequestItem;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QARequestsAdapter extends BaseLoadMoreAdapter {
    public static final String FROM_QAREQUEST_FRAGMENT = "fragment_qa_request_fragment";
    public static final String FROM_QAREQUEST_MORE_FRAGMENT = "fragment_qa_request_more_fragment";
    public static final String IGNORE = "1";
    /* access modifiers changed from: private */
    public BaseActivity context;
    private String from;
    /* access modifiers changed from: private */
    public OnClickIgnoreListener onClickIgnoreListener;
    public List<QARequestItem.RequestItem> requestItemList = new ArrayList();

    public interface OnClickIgnoreListener {
        void onClickIgnore(int i);
    }

    public int getNormalViewType(int i) {
        return 0;
    }

    public class QAQuestionHolder_ViewBinding implements Unbinder {
        private QAQuestionHolder target;

        @UiThread
        public QAQuestionHolder_ViewBinding(QAQuestionHolder qAQuestionHolder, View view) {
            this.target = qAQuestionHolder;
            qAQuestionHolder.itemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_icon, "field 'itemIcon'", ImageView.class);
            qAQuestionHolder.itemName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_name, "field 'itemName'", TextView.class);
            qAQuestionHolder.itemContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_answer_content, "field 'itemContent'", TextView.class);
            qAQuestionHolder.itemTime = (TextView) Utils.findRequiredViewAsType(view, R.id.time, "field 'itemTime'", TextView.class);
            qAQuestionHolder.itemAnswer = (TextView) Utils.findRequiredViewAsType(view, R.id.answer, "field 'itemAnswer'", TextView.class);
            qAQuestionHolder.itemIgnore = (TextView) Utils.findRequiredViewAsType(view, R.id.ignore, "field 'itemIgnore'", TextView.class);
            qAQuestionHolder.answerImgLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.answer_img_layout, "field 'answerImgLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            QAQuestionHolder qAQuestionHolder = this.target;
            if (qAQuestionHolder != null) {
                this.target = null;
                qAQuestionHolder.itemIcon = null;
                qAQuestionHolder.itemName = null;
                qAQuestionHolder.itemContent = null;
                qAQuestionHolder.itemTime = null;
                qAQuestionHolder.itemAnswer = null;
                qAQuestionHolder.itemIgnore = null;
                qAQuestionHolder.answerImgLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public QARequestsAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        this.from = str;
    }

    public void addData(List<QARequestItem.RequestItem> list) {
        if (list != null) {
            this.requestItemList.clear();
            this.requestItemList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.requestItemList.clear();
    }

    public int getDataItemCount() {
        if (this.requestItemList != null) {
            return this.requestItemList.size();
        }
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return new QAQuestionHolder(this.layoutInflater.inflate(R.layout.bbs_qa_request_more_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindQAQuestionHolder((QAQuestionHolder) viewHolder, i);
        }
    }

    private void bindQAQuestionHolder(QAQuestionHolder qAQuestionHolder, int i) {
        handleCommonPart(qAQuestionHolder, this.requestItemList.get(i), i);
    }

    private void handleCommonPart(QAQuestionHolder qAQuestionHolder, QARequestItem.RequestItem requestItem, int i) {
        if (this.from == FROM_QAREQUEST_FRAGMENT) {
            qAQuestionHolder.itemIcon.setVisibility(8);
        } else {
            qAQuestionHolder.itemIcon.setVisibility(0);
            if (requestItem.icon != null) {
                ImageLoader.showImage(qAQuestionHolder.itemIcon, requestItem.icon, (RequestOptions) new RequestOptions().a(R.drawable.bbs_icon_default_head));
            } else {
                Glide.a((FragmentActivity) this.context).a(Integer.valueOf(R.drawable.bbs_icon_default_head)).a(qAQuestionHolder.itemIcon);
            }
        }
        qAQuestionHolder.itemName.setText(requestItem.fromname);
        qAQuestionHolder.itemContent.setText(requestItem.subject);
        qAQuestionHolder.itemTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Long.valueOf(Long.parseLong(requestItem.dateline) * 1000)));
        if (requestItem.ignore.equals("1")) {
            qAQuestionHolder.itemAnswer.setVisibility(8);
            qAQuestionHolder.itemIgnore.setVisibility(0);
        } else {
            qAQuestionHolder.itemAnswer.setVisibility(0);
            qAQuestionHolder.itemIgnore.setVisibility(8);
        }
        setCommonPartClickListener(i, qAQuestionHolder, requestItem);
    }

    private void setCommonPartClickListener(final int i, final QAQuestionHolder qAQuestionHolder, final QARequestItem.RequestItem requestItem) {
        qAQuestionHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, "click_thread", "request");
                WebActivity.jump(QARequestsAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{requestItem.tid})));
            }
        });
        qAQuestionHolder.answerImgLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (qAQuestionHolder.itemAnswer.getVisibility() == 0) {
                    qAQuestionHolder.itemIgnore.setVisibility(0);
                    qAQuestionHolder.itemAnswer.setVisibility(8);
                    return;
                }
                qAQuestionHolder.itemIgnore.setVisibility(8);
                qAQuestionHolder.itemAnswer.setVisibility(0);
            }
        });
        qAQuestionHolder.itemAnswer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, Constants.ClickEvent.QA_CLICK_ANSWER, "request");
                WebActivity.jump(QARequestsAdapter.this.context, ApiClient.getAppUrl(String.format(Locale.ENGLISH, UrlAction.THREAD_URL, new Object[]{requestItem.tid})), true);
            }
        });
        qAQuestionHolder.itemIgnore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = i;
                if (i >= QARequestsAdapter.this.requestItemList.size()) {
                    i = QARequestsAdapter.this.requestItemList.size() - 1;
                }
                QARequestsAdapter.this.requestItemList.remove(i);
                QARequestsAdapter.this.notifyItemRemoved(i);
                if (i != QARequestsAdapter.this.requestItemList.size() - 1) {
                    QARequestsAdapter.this.notifyItemRangeChanged(i, QARequestsAdapter.this.requestItemList.size() - i);
                }
                QARequestsAdapter.this.onClickIgnoreListener.onClickIgnore(Integer.parseInt(requestItem.id));
            }
        });
    }

    public class QAQuestionHolder extends RecyclerView.ViewHolder {
        @BindView(2131492978)
        LinearLayout answerImgLayout;
        @BindView(2131492976)
        TextView itemAnswer;
        @BindView(2131493484)
        TextView itemContent;
        @BindView(2131493492)
        ImageView itemIcon;
        @BindView(2131493444)
        TextView itemIgnore;
        @BindView(2131493501)
        TextView itemName;
        @BindView(2131494092)
        TextView itemTime;

        public QAQuestionHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setClickIgnoreListener(OnClickIgnoreListener onClickIgnoreListener2) {
        this.onClickIgnoreListener = onClickIgnoreListener2;
    }
}
