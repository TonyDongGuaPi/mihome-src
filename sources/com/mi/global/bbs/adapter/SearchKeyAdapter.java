package com.mi.global.bbs.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.SearchHotModel;
import com.mi.global.bbs.ui.WebActivity;
import java.util.ArrayList;
import java.util.List;

public class SearchKeyAdapter extends RecyclerView.Adapter {
    private static final int HISTORY_CLEAR = 2;
    private static final int HISTORY_HEAD = 0;
    private static final int HISTORY_ITEM = 1;
    private static final int HOT_HEAD = 3;
    private static final int HOT_ITEM = 4;
    /* access modifiers changed from: private */
    public List<String> historyWords = new ArrayList();
    /* access modifiers changed from: private */
    public List<SearchHotModel.DataBean.WordsBean> hotWords = new ArrayList();
    private LayoutInflater inflater;
    /* access modifiers changed from: private */
    public Context mContext;
    OnSearchListener onSearchListener;

    public interface OnSearchListener {
        void onSearch(String str);
    }

    public SearchKeyAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void addHistoryList(List<String> list) {
        this.historyWords.addAll(list);
        notifyDataSetChanged();
    }

    public List<String> getHistoryList() {
        return this.historyWords;
    }

    public void addHotList(List<SearchHotModel.DataBean.WordsBean> list) {
        this.hotWords.addAll(list);
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new HistoryHeadViewHolder(this.inflater.inflate(R.layout.bbs_search_his_head, viewGroup, false));
            case 1:
                return new HistoryItemViewHolder(this.inflater.inflate(R.layout.bbs_search_his_item, viewGroup, false));
            case 2:
                return new HistoryClearViewHolder(this.inflater.inflate(R.layout.bbs_no_search_history, viewGroup, false));
            case 3:
                return new HotHeadViewHolder(this.inflater.inflate(R.layout.bbs_search_hot_head, viewGroup, false));
            case 4:
                return new HotItemViewHolder(this.inflater.inflate(R.layout.bbs_search_hot_item, viewGroup, false));
            default:
                return new HistoryHeadViewHolder(this.inflater.inflate(R.layout.bbs_search_his_head, viewGroup, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HistoryItemViewHolder) {
            final int i2 = i - 1;
            final String str = this.historyWords.get(i2);
            HistoryItemViewHolder historyItemViewHolder = (HistoryItemViewHolder) viewHolder;
            historyItemViewHolder.historyKey.setText(str);
            historyItemViewHolder.container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SearchKeyAdapter.this.onSearchListener != null) {
                        SearchKeyAdapter.this.onSearchListener.onSearch(str);
                    }
                }
            });
            historyItemViewHolder.clearItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (i2 < SearchKeyAdapter.this.historyWords.size()) {
                        SearchKeyAdapter.this.historyWords.remove(i2);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                SearchKeyAdapter.this.notifyDataSetChanged();
                            }
                        }, 200);
                    }
                }
            });
        } else if (viewHolder instanceof HotItemViewHolder) {
            int i3 = 3;
            if (this.historyWords.size() != 0) {
                i3 = 3 + this.historyWords.size();
            }
            final int i4 = i - i3;
            final String word = this.hotWords.get(i4).getWord();
            HotItemViewHolder hotItemViewHolder = (HotItemViewHolder) viewHolder;
            hotItemViewHolder.hotKey.setText(word);
            hotItemViewHolder.container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String url = ((SearchHotModel.DataBean.WordsBean) SearchKeyAdapter.this.hotWords.get(i4)).getUrl();
                    if (!TextUtils.isEmpty(url)) {
                        WebActivity.jump(SearchKeyAdapter.this.mContext, url);
                    } else if (SearchKeyAdapter.this.onSearchListener != null) {
                        SearchKeyAdapter.this.onSearchListener.onSearch(word);
                    }
                }
            });
        } else if (!(viewHolder instanceof HistoryClearViewHolder)) {
        } else {
            if (this.historyWords.isEmpty()) {
                HistoryClearViewHolder historyClearViewHolder = (HistoryClearViewHolder) viewHolder;
                historyClearViewHolder.clear.setOnClickListener((View.OnClickListener) null);
                historyClearViewHolder.clear.setText(R.string.no_search_history);
                return;
            }
            HistoryClearViewHolder historyClearViewHolder2 = (HistoryClearViewHolder) viewHolder;
            historyClearViewHolder2.clear.setText(R.string.clear_search_history);
            historyClearViewHolder2.clear.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SearchKeyAdapter.this.historyWords.clear();
                    SearchKeyAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public int getItemCount() {
        if (this.historyWords == null || this.historyWords.size() <= 0) {
            if (this.hotWords == null || this.hotWords.size() <= 0) {
                return 2;
            }
            return this.hotWords.size() + 3;
        } else if (this.hotWords == null || this.hotWords.size() <= 0) {
            return this.historyWords.size() + 2;
        } else {
            return this.historyWords.size() + this.hotWords.size() + 3;
        }
    }

    public int getItemViewType(int i) {
        boolean z = !this.historyWords.isEmpty();
        boolean z2 = !this.hotWords.isEmpty();
        if (i != 0 && i > 0) {
            if (z) {
                if (i == this.historyWords.size() + 1) {
                    return 2;
                }
                if (i < this.historyWords.size() + 1) {
                    return 1;
                }
                if (z2) {
                    return i == this.historyWords.size() + 2 ? 3 : 4;
                }
            } else if (i == 1) {
                return 2;
            } else {
                if (z2) {
                    return i == 2 ? 3 : 4;
                }
            }
        }
        return 0;
    }

    public void setOnSearchListener(OnSearchListener onSearchListener2) {
        this.onSearchListener = onSearchListener2;
    }

    static class HistoryHeadViewHolder extends RecyclerView.ViewHolder {
        public HistoryHeadViewHolder(View view) {
            super(view);
        }
    }

    static class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView clearItem;
        LinearLayout container;
        TextView historyKey;

        public HistoryItemViewHolder(View view) {
            super(view);
            this.historyKey = (TextView) view.findViewById(R.id.search_his_item_text);
            this.clearItem = (ImageView) view.findViewById(R.id.search_his_item_clear);
            this.container = (LinearLayout) view.findViewById(R.id.search_his_item_container);
        }
    }

    static class HistoryClearViewHolder extends RecyclerView.ViewHolder {
        TextView clear;

        public HistoryClearViewHolder(View view) {
            super(view);
            this.clear = (TextView) view.findViewById(R.id.clear_search_history_text);
        }
    }

    static class HotHeadViewHolder extends RecyclerView.ViewHolder {
        public HotHeadViewHolder(View view) {
            super(view);
        }
    }

    static class HotItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView hotKey;

        public HotItemViewHolder(View view) {
            super(view);
            this.hotKey = (TextView) view.findViewById(R.id.search_hot_item_text);
            this.container = (LinearLayout) view.findViewById(R.id.search_hot_item_container);
        }
    }
}
