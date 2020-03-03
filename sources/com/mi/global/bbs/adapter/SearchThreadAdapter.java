package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.SearchThreadModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import java.util.List;

public class SearchThreadAdapter extends RecyclerView.Adapter implements InfiniteScrollListener.DataLoading.DataLoadingCallbacks {
    private static final int QA_SEARCH_ANSWERED = 7;
    private static final int TYPE_LOADING_MORE = -1;
    private static final int TYPE_NORMAL = 0;
    private InfiniteScrollListener.DataLoading dataLoading;
    private boolean fromQa = false;
    private List<SearchThreadModel.DataBean.ResponseBean.DocsBean> items;
    private final LayoutInflater layoutInflater;
    private boolean showLoadingMore = false;
    /* access modifiers changed from: private */
    public SortListener sortListener;
    /* access modifiers changed from: private */
    public String sortStr;
    private int totalNum = 0;

    public interface SortListener {
        void onSort(int i);
    }

    public class ThreadItemHolder_ViewBinding implements Unbinder {
        private ThreadItemHolder target;

        @UiThread
        public ThreadItemHolder_ViewBinding(ThreadItemHolder threadItemHolder, View view) {
            this.target = threadItemHolder;
            threadItemHolder.threadItemTopTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_top_total, "field 'threadItemTopTotal'", TextView.class);
            threadItemHolder.threadItemTopOrder = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_top_order, "field 'threadItemTopOrder'", TextView.class);
            threadItemHolder.threadItemTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.thread_item_top, "field 'threadItemTop'", LinearLayout.class);
            threadItemHolder.threadItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_title, "field 'threadItemTitle'", TextView.class);
            threadItemHolder.threadItemDes = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_des, "field 'threadItemDes'", TextView.class);
            threadItemHolder.threadItemAu = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_au, "field 'threadItemAu'", TextView.class);
            threadItemHolder.threadItemTime = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_time, "field 'threadItemTime'", TextView.class);
            threadItemHolder.threadItemView = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_view, "field 'threadItemView'", TextView.class);
            threadItemHolder.threadItemComment = (TextView) Utils.findRequiredViewAsType(view, R.id.thread_item_comment, "field 'threadItemComment'", TextView.class);
            threadItemHolder.threadItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.thread_item, "field 'threadItem'", RelativeLayout.class);
            threadItemHolder.mTopDivider = Utils.findRequiredView(view, R.id.top_divider, "field 'mTopDivider'");
            threadItemHolder.qaSearchAnswered = (ImageView) Utils.findRequiredViewAsType(view, R.id.qa_search_answered, "field 'qaSearchAnswered'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ThreadItemHolder threadItemHolder = this.target;
            if (threadItemHolder != null) {
                this.target = null;
                threadItemHolder.threadItemTopTotal = null;
                threadItemHolder.threadItemTopOrder = null;
                threadItemHolder.threadItemTop = null;
                threadItemHolder.threadItemTitle = null;
                threadItemHolder.threadItemDes = null;
                threadItemHolder.threadItemAu = null;
                threadItemHolder.threadItemTime = null;
                threadItemHolder.threadItemView = null;
                threadItemHolder.threadItemComment = null;
                threadItemHolder.threadItem = null;
                threadItemHolder.mTopDivider = null;
                threadItemHolder.qaSearchAnswered = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void setSortStr(String str) {
        this.sortStr = str;
    }

    public SearchThreadAdapter(Context context, InfiniteScrollListener.DataLoading dataLoading2, List<SearchThreadModel.DataBean.ResponseBean.DocsBean> list, int i) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = list;
        this.dataLoading = dataLoading2;
        dataLoading2.registerCallback(this);
        this.totalNum = i;
    }

    public SearchThreadAdapter(Context context, InfiniteScrollListener.DataLoading dataLoading2, List<SearchThreadModel.DataBean.ResponseBean.DocsBean> list, int i, boolean z) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = list;
        this.dataLoading = dataLoading2;
        dataLoading2.registerCallback(this);
        this.totalNum = i;
        this.fromQa = z;
    }

    public void setTotalNum(int i) {
        this.totalNum = i;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case -1:
                return new LoadingMoreHolder(this.layoutInflater.inflate(R.layout.bbs_infinite_loading, viewGroup, false));
            case 0:
                return createThreadItemHolder(viewGroup);
            default:
                return null;
        }
    }

    private ThreadItemHolder createThreadItemHolder(ViewGroup viewGroup) {
        return new ThreadItemHolder(this.layoutInflater.inflate(R.layout.bbs_search_thread_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case -1:
                bindLoadingViewHolder((LoadingMoreHolder) viewHolder, i);
                return;
            case 0:
                bindThreadDataHolder((ThreadItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindThreadDataHolder(ThreadItemHolder threadItemHolder, int i) {
        SearchThreadModel.DataBean.ResponseBean.DocsBean docsBean = this.items.get(i);
        threadItemHolder.threadItemTop.setVisibility(i == 0 ? 0 : 8);
        threadItemHolder.mTopDivider.setVisibility(i == 0 ? 0 : 8);
        TextHelper.setQuantityString(this.layoutInflater.getContext(), threadItemHolder.threadItemTopTotal, R.plurals._result_found, String.valueOf(this.totalNum));
        threadItemHolder.threadItemTitle.setText(Html.fromHtml(docsBean.subject));
        threadItemHolder.threadItemDes.setText(Html.fromHtml(docsBean.message));
        threadItemHolder.threadItemAu.setText(docsBean.author);
        threadItemHolder.threadItemTime.setText(getFormatTime(docsBean.createTime));
        threadItemHolder.threadItemView.setText(String.valueOf(docsBean.views));
        threadItemHolder.threadItemComment.setText(String.valueOf(docsBean.replies));
        final String str = ConnectionHelper.getAppIndexUrl() + String.format(UrlAction.THREAD_URL, new Object[]{docsBean.id});
        threadItemHolder.threadItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(view.getContext(), str);
            }
        });
        if (!this.fromQa || docsBean.icon != 7) {
            threadItemHolder.qaSearchAnswered.setVisibility(8);
        } else {
            threadItemHolder.qaSearchAnswered.setVisibility(0);
        }
        if (i != 0) {
            return;
        }
        if (this.fromQa) {
            threadItemHolder.threadItemTopOrder.setVisibility(8);
            return;
        }
        threadItemHolder.threadItemTopOrder.setVisibility(0);
        threadItemHolder.threadItemTopOrder.setText(this.sortStr);
        threadItemHolder.threadItemTopOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogFactory.showSortDialog(view.getContext(), SearchThreadAdapter.this.sortStr, new DialogFactory.OnStringItemClickListener() {
                    public void onItemClick(int i, String str) {
                        if (SearchThreadAdapter.this.sortListener != null) {
                            SearchThreadAdapter.this.sortListener.onSort(i);
                        }
                    }
                });
            }
        });
    }

    private CharSequence getFormatTime(String str) {
        return str.trim().substring(0, 10);
    }

    private void bindLoadingViewHolder(LoadingMoreHolder loadingMoreHolder, int i) {
        loadingMoreHolder.progress.setVisibility((i <= 0 || !this.dataLoading.isDataLoading()) ? 4 : 0);
    }

    public int getItemViewType(int i) {
        return (i >= getDataItemCount() || getDataItemCount() <= 0) ? -1 : 0;
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<SearchThreadModel.DataBean.ResponseBean.DocsBean> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return getDataItemCount() + (this.showLoadingMore ? 1 : 0);
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    private int getLoadingMoreItemPosition() {
        if (this.showLoadingMore) {
            return getItemCount() - 1;
        }
        return -1;
    }

    public void dataStartedLoading() {
        if (!this.showLoadingMore) {
            this.showLoadingMore = true;
            notifyItemInserted(getLoadingMoreItemPosition());
        }
    }

    public void dataFinishedLoading() {
        if (this.showLoadingMore) {
            int loadingMoreItemPosition = getLoadingMoreItemPosition();
            this.showLoadingMore = false;
            notifyItemRemoved(loadingMoreItemPosition);
        }
    }

    public void setSortListener(SortListener sortListener2) {
        this.sortListener = sortListener2;
    }

    static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progress;

        LoadingMoreHolder(View view) {
            super(view);
            this.progress = (ProgressBar) view;
        }
    }

    static class ThreadItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131494106)
        View mTopDivider;
        @BindView(2131493869)
        ImageView qaSearchAnswered;
        @BindView(2131494080)
        RelativeLayout threadItem;
        @BindView(2131494081)
        TextView threadItemAu;
        @BindView(2131494082)
        TextView threadItemComment;
        @BindView(2131494083)
        TextView threadItemDes;
        @BindView(2131494084)
        TextView threadItemTime;
        @BindView(2131494085)
        TextView threadItemTitle;
        @BindView(2131494086)
        LinearLayout threadItemTop;
        @BindView(2131494087)
        TextView threadItemTopOrder;
        @BindView(2131494088)
        TextView threadItemTopTotal;
        @BindView(2131494089)
        TextView threadItemView;

        public ThreadItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
