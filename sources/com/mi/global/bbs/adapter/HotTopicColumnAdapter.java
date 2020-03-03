package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.util.Coder;
import java.util.List;

public class HotTopicColumnAdapter extends RecyclerView.Adapter {
    public static final String TAG = "HotTopicColumnAdapter";
    protected LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public Context mContext;
    private List<ColumnHomeModel.DataBean.ColumnRecommend> mListData;

    public class ColumnViewHolder_ViewBinding implements Unbinder {
        private ColumnViewHolder target;

        @UiThread
        public ColumnViewHolder_ViewBinding(ColumnViewHolder columnViewHolder, View view) {
            this.target = columnViewHolder;
            columnViewHolder.itemCard = (CardView) Utils.findRequiredViewAsType(view, R.id.topic_recycle_item_card, "field 'itemCard'", CardView.class);
            columnViewHolder.articleCount = (TextView) Utils.findRequiredViewAsType(view, R.id.topic_item_article_count, "field 'articleCount'", TextView.class);
            columnViewHolder.followerCount = (TextView) Utils.findRequiredViewAsType(view, R.id.topic_item_follower_count, "field 'followerCount'", TextView.class);
            columnViewHolder.ivArticleAlbum = (ImageView) Utils.findRequiredViewAsType(view, R.id.topic_column_top_pic, "field 'ivArticleAlbum'", ImageView.class);
            columnViewHolder.columnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.column_topic_item_title, "field 'columnTitle'", TextView.class);
            columnViewHolder.columnDes = (TextView) Utils.findRequiredViewAsType(view, R.id.column_topic_item_des, "field 'columnDes'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ColumnViewHolder columnViewHolder = this.target;
            if (columnViewHolder != null) {
                this.target = null;
                columnViewHolder.itemCard = null;
                columnViewHolder.articleCount = null;
                columnViewHolder.followerCount = null;
                columnViewHolder.ivArticleAlbum = null;
                columnViewHolder.columnTitle = null;
                columnViewHolder.columnDes = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HotTopicColumnAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(List<ColumnHomeModel.DataBean.ColumnRecommend> list) {
        this.mListData = list;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ColumnViewHolder(this.layoutInflater.inflate(R.layout.bbs_column_topic_recycle_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        bindViewHolder((ColumnViewHolder) viewHolder, i);
    }

    public int getItemCount() {
        return this.mListData.size();
    }

    private void bindViewHolder(ColumnViewHolder columnViewHolder, int i) {
        final ColumnHomeModel.DataBean.ColumnRecommend columnRecommend = this.mListData.get(i);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) columnViewHolder.itemCard.getLayoutParams();
        if (i == this.mListData.size() - 1) {
            layoutParams.setMargins(Coder.a(16.0f), 0, Coder.a(16.0f), 0);
        } else {
            layoutParams.setMargins(Coder.a(16.0f), 0, 0, 0);
        }
        columnViewHolder.itemCard.setLayoutParams(layoutParams);
        TextHelper.setQuantityString(this.mContext, columnViewHolder.articleCount, R.plurals._articles, String.valueOf(columnRecommend.count));
        TextHelper.setQuantityString(this.mContext, columnViewHolder.followerCount, R.plurals._followers, String.valueOf(columnRecommend.subscribeCount));
        ImageLoader.showImage(columnViewHolder.ivArticleAlbum, columnRecommend.background);
        columnViewHolder.columnTitle.setText(columnRecommend.name);
        columnViewHolder.columnDes.setText(columnRecommend.info);
        columnViewHolder.itemCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = HotTopicColumnAdapter.this.mContext;
                ColumnDetailActivity.jumpWithId(access$000, columnRecommend.columnID + "");
            }
        });
    }

    class ColumnViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131494109)
        TextView articleCount;
        @BindView(2131493123)
        TextView columnDes;
        @BindView(2131493124)
        TextView columnTitle;
        @BindView(2131494110)
        TextView followerCount;
        @BindView(2131494111)
        CardView itemCard;
        @BindView(2131494108)
        ImageView ivArticleAlbum;

        ColumnViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, this.itemView);
        }
    }
}
