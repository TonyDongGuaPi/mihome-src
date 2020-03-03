package com.mi.global.bbs.ui.qa;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.CircleImageView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QATrendingAdapter extends BaseLoadMoreAdapter {
    public static final int TYPE_IMG = 1;
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<QAHomeTrendWrapper> qaHomeTrendItems = new ArrayList();

    public class QATrendingImgHolder_ViewBinding implements Unbinder {
        private QATrendingImgHolder target;

        @UiThread
        public QATrendingImgHolder_ViewBinding(QATrendingImgHolder qATrendingImgHolder, View view) {
            this.target = qATrendingImgHolder;
            qATrendingImgHolder.itemImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_img, "field 'itemImg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            QATrendingImgHolder qATrendingImgHolder = this.target;
            if (qATrendingImgHolder != null) {
                this.target = null;
                qATrendingImgHolder.itemImg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class QATrendingHolder_ViewBinding implements Unbinder {
        private QATrendingHolder target;

        @UiThread
        public QATrendingHolder_ViewBinding(QATrendingHolder qATrendingHolder, View view) {
            this.target = qATrendingHolder;
            qATrendingHolder.itemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", TextView.class);
            qATrendingHolder.itemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.item_icon, "field 'itemIcon'", CircleImageView.class);
            qATrendingHolder.itemName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_name, "field 'itemName'", TextView.class);
            qATrendingHolder.itemContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_answer_content, "field 'itemContent'", TextView.class);
            qATrendingHolder.itemTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.item_target, "field 'itemTarget'", TextView.class);
            qATrendingHolder.itemWatch = (TextView) Utils.findRequiredViewAsType(view, R.id.item_watch, "field 'itemWatch'", TextView.class);
            qATrendingHolder.itemComment = (TextView) Utils.findRequiredViewAsType(view, R.id.item_comment, "field 'itemComment'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            QATrendingHolder qATrendingHolder = this.target;
            if (qATrendingHolder != null) {
                this.target = null;
                qATrendingHolder.itemTitle = null;
                qATrendingHolder.itemIcon = null;
                qATrendingHolder.itemName = null;
                qATrendingHolder.itemContent = null;
                qATrendingHolder.itemTarget = null;
                qATrendingHolder.itemWatch = null;
                qATrendingHolder.itemComment = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public QATrendingAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
    }

    public void addData(List<QAHomeTrendWrapper> list) {
        if (list != null) {
            this.qaHomeTrendItems.clear();
            this.qaHomeTrendItems.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.qaHomeTrendItems.clear();
    }

    public int getDataItemCount() {
        return this.qaHomeTrendItems.size();
    }

    public int getNormalViewType(int i) {
        return this.qaHomeTrendItems.get(i).type == 1 ? 1 : 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new QATrendingHolder(this.layoutInflater.inflate(R.layout.bbs_qa_home_trending_item, viewGroup, false));
            case 1:
                return new QATrendingImgHolder(this.layoutInflater.inflate(R.layout.bbs_qa_home_trend_img_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 0:
                bindQATrendingHolder((QATrendingHolder) viewHolder, i);
                return;
            case 1:
                bindQATrendImgHolder((QATrendingImgHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindQATrendingHolder(QATrendingHolder qATrendingHolder, int i) {
        handleCommonPart(qATrendingHolder, this.qaHomeTrendItems.get(i));
    }

    private void handleCommonPart(QATrendingHolder qATrendingHolder, QAHomeTrendWrapper qAHomeTrendWrapper) {
        ImageLoader.showHeadIcon(qATrendingHolder.itemIcon, qAHomeTrendWrapper.avatar);
        qATrendingHolder.itemTitle.setText(qAHomeTrendWrapper.subject);
        StringBuilder sb = new StringBuilder();
        sb.append(qAHomeTrendWrapper.author);
        sb.append("  ");
        sb.append(this.context.getResources().getString(R.string.qa_home_answered));
        sb.append("  ");
        try {
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(Long.parseLong(qAHomeTrendWrapper.dateline) * 1000)));
        } catch (Exception unused) {
        }
        qATrendingHolder.itemName.setText(sb.toString());
        qATrendingHolder.itemContent.setText(qAHomeTrendWrapper.message);
        qATrendingHolder.itemTarget.setText(qAHomeTrendWrapper.fname);
        qATrendingHolder.itemWatch.setText(qAHomeTrendWrapper.views);
        qATrendingHolder.itemComment.setText(qAHomeTrendWrapper.replies);
        setCommonPartClickListener(qATrendingHolder, qAHomeTrendWrapper);
    }

    private void bindQATrendImgHolder(QATrendingImgHolder qATrendingImgHolder, int i) {
        handleImgPart(qATrendingImgHolder, this.qaHomeTrendItems.get(i));
    }

    private void handleImgPart(QATrendingImgHolder qATrendingImgHolder, QAHomeTrendWrapper qAHomeTrendWrapper) {
        ImageLoader.showHeadIcon(qATrendingImgHolder.itemImg, qAHomeTrendWrapper.pic_url);
        setImgPartClickListener(qATrendingImgHolder, qAHomeTrendWrapper);
    }

    private void setCommonPartClickListener(QATrendingHolder qATrendingHolder, final QAHomeTrendWrapper qAHomeTrendWrapper) {
        qATrendingHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_QA, "click_thread", "trending");
                WebActivity.jump(QATrendingAdapter.this.context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{qAHomeTrendWrapper.tid})));
            }
        });
    }

    private void setImgPartClickListener(QATrendingImgHolder qATrendingImgHolder, final QAHomeTrendWrapper qAHomeTrendWrapper) {
        qATrendingImgHolder.itemImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(QATrendingAdapter.this.context, qAHomeTrendWrapper.link);
            }
        });
    }

    public class QATrendingHolder extends RecyclerView.ViewHolder {
        @BindView(2131493486)
        TextView itemComment;
        @BindView(2131493484)
        TextView itemContent;
        @BindView(2131493492)
        CircleImageView itemIcon;
        @BindView(2131493501)
        TextView itemName;
        @BindView(2131493510)
        TextView itemTarget;
        @BindView(2131493512)
        TextView itemTitle;
        @BindView(2131493517)
        TextView itemWatch;

        public QATrendingHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class QATrendingImgHolder extends RecyclerView.ViewHolder {
        @BindView(2131493497)
        ImageView itemImg;

        public QATrendingImgHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
