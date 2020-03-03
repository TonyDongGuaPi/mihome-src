package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import java.util.ArrayList;
import java.util.List;

public class ColumnListAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context context;
    OnFollowListener mOnFollowListener;
    private String page_name;
    public List<ColumnDetailModel.DataBean.ColumnInfo> threadlist = new ArrayList();

    public int getNormalViewType(int i) {
        return 0;
    }

    public class SubSuggestHolder_ViewBinding implements Unbinder {
        private SubSuggestHolder target;

        @UiThread
        public SubSuggestHolder_ViewBinding(SubSuggestHolder subSuggestHolder, View view) {
            this.target = subSuggestHolder;
            subSuggestHolder.relSubItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.column_sub_suggest_item_layout, "field 'relSubItem'", RelativeLayout.class);
            subSuggestHolder.column_sub_item_icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_icon, "field 'column_sub_item_icon'", ImageView.class);
            subSuggestHolder.column_sub_item_title = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_title, "field 'column_sub_item_title'", TextView.class);
            subSuggestHolder.column_sub_item_des = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_des, "field 'column_sub_item_des'", TextView.class);
            subSuggestHolder.column_sub_item_article_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_article_count, "field 'column_sub_item_article_count'", TextView.class);
            subSuggestHolder.column_sub_item_follower_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_follower_count, "field 'column_sub_item_follower_count'", TextView.class);
            subSuggestHolder.tvColumnFollow = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_follower_bt, "field 'tvColumnFollow'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SubSuggestHolder subSuggestHolder = this.target;
            if (subSuggestHolder != null) {
                this.target = null;
                subSuggestHolder.relSubItem = null;
                subSuggestHolder.column_sub_item_icon = null;
                subSuggestHolder.column_sub_item_title = null;
                subSuggestHolder.column_sub_item_des = null;
                subSuggestHolder.column_sub_item_article_count = null;
                subSuggestHolder.column_sub_item_follower_count = null;
                subSuggestHolder.tvColumnFollow = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public ColumnListAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(activity, dataLoading);
        this.context = activity;
        this.page_name = str;
    }

    public List<ColumnDetailModel.DataBean.ColumnInfo> getThreadlist() {
        return this.threadlist;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return new SubSuggestHolder(this.layoutInflater.inflate(R.layout.bbs_column_sub_suggest_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindSuggestHolder((SubSuggestHolder) viewHolder, i);
        }
    }

    private void bindSuggestHolder(SubSuggestHolder subSuggestHolder, final int i) {
        final ColumnDetailModel.DataBean.ColumnInfo columnInfo = this.threadlist.get(i);
        ImageLoader.showImage(subSuggestHolder.column_sub_item_icon, columnInfo.background);
        subSuggestHolder.column_sub_item_title.setText(columnInfo.name);
        subSuggestHolder.column_sub_item_des.setText(columnInfo.info);
        TextHelper.setQuantityString(this.context, subSuggestHolder.column_sub_item_article_count, R.plurals._articles, String.valueOf(columnInfo.count));
        TextHelper.setQuantityString(this.context, subSuggestHolder.column_sub_item_follower_count, R.plurals._followers, String.valueOf(columnInfo.subscribeCount));
        if (columnInfo.subscribeStatus) {
            subSuggestHolder.tvColumnFollow.setText(R.string.str_subscribed);
        } else {
            subSuggestHolder.tvColumnFollow.setText(R.string.str_subscribe);
        }
        subSuggestHolder.tvColumnFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!LoginManager.getInstance().hasLogin()) {
                    ((BaseActivity) ColumnListAdapter.this.context).gotoAccount();
                } else if (ColumnListAdapter.this.mOnFollowListener != null) {
                    OnFollowListener onFollowListener = ColumnListAdapter.this.mOnFollowListener;
                    int i = i;
                    onFollowListener.onFollow(i, columnInfo.columnID + "", columnInfo.subscribeStatus);
                }
            }
        });
        subSuggestHolder.relSubItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = ColumnListAdapter.this.context;
                ColumnDetailActivity.jumpWithId(access$000, columnInfo.columnID + "");
            }
        });
    }

    public int getDataItemCount() {
        if (this.threadlist == null) {
            return 0;
        }
        return this.threadlist.size();
    }

    public void setData(List<ColumnDetailModel.DataBean.ColumnInfo> list) {
        if (list != null) {
            this.threadlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.threadlist.clear();
    }

    public class SubSuggestHolder extends RecyclerView.ViewHolder {
        @BindView(2131493115)
        TextView column_sub_item_article_count;
        @BindView(2131493116)
        TextView column_sub_item_des;
        @BindView(2131493117)
        TextView column_sub_item_follower_count;
        @BindView(2131493118)
        ImageView column_sub_item_icon;
        @BindView(2131493121)
        TextView column_sub_item_title;
        @BindView(2131493122)
        RelativeLayout relSubItem;
        @BindView(2131493114)
        TextView tvColumnFollow;

        public SubSuggestHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
