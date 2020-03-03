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
import com.mi.global.bbs.dao.SubcribtionDao;
import com.mi.global.bbs.db.DbUtil;
import com.mi.global.bbs.db.SubscripionHelper;
import com.mi.global.bbs.entity.Subcribtion;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnSubData;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

public class SubcriptionAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context context;
    private SubscripionHelper mHelper;
    OnFollowListener mOnFollowListener;
    private String page_name;
    public List<ColumnSubData> threadlist = new ArrayList();

    private void bindEmptyHolder(SubEmptyHolder subEmptyHolder, int i) {
    }

    public class SubColumnHolder_ViewBinding implements Unbinder {
        private SubColumnHolder target;

        @UiThread
        public SubColumnHolder_ViewBinding(SubColumnHolder subColumnHolder, View view) {
            this.target = subColumnHolder;
            subColumnHolder.relSubItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.column_sub_item_layout, "field 'relSubItem'", RelativeLayout.class);
            subColumnHolder.column_sub_item_icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_icon, "field 'column_sub_item_icon'", ImageView.class);
            subColumnHolder.column_sub_item_title = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_title, "field 'column_sub_item_title'", TextView.class);
            subColumnHolder.column_sub_item_tip = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_tip, "field 'column_sub_item_tip'", ImageView.class);
            subColumnHolder.column_sub_item_des = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_des, "field 'column_sub_item_des'", TextView.class);
            subColumnHolder.column_sub_item_article_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_article_count, "field 'column_sub_item_article_count'", TextView.class);
            subColumnHolder.column_sub_item_follower_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_follower_count, "field 'column_sub_item_follower_count'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SubColumnHolder subColumnHolder = this.target;
            if (subColumnHolder != null) {
                this.target = null;
                subColumnHolder.relSubItem = null;
                subColumnHolder.column_sub_item_icon = null;
                subColumnHolder.column_sub_item_title = null;
                subColumnHolder.column_sub_item_tip = null;
                subColumnHolder.column_sub_item_des = null;
                subColumnHolder.column_sub_item_article_count = null;
                subColumnHolder.column_sub_item_follower_count = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
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

    public SubcriptionAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(activity, dataLoading);
        this.context = activity;
        this.page_name = str;
        this.mHelper = DbUtil.getDriverHelper();
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public List<ColumnSubData> getThreadlist() {
        return this.threadlist;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new SubEmptyHolder(this.layoutInflater.inflate(R.layout.bbs_column_subcription_empty, viewGroup, false));
            case 2:
                return new SubColumnHolder(this.layoutInflater.inflate(R.layout.bbs_column_subcription_item, viewGroup, false));
            case 3:
                return new SubSuggestHolder(this.layoutInflater.inflate(R.layout.bbs_column_sub_suggest_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 1:
                bindEmptyHolder((SubEmptyHolder) viewHolder, i);
                return;
            case 2:
                bindColumnHolder((SubColumnHolder) viewHolder, i);
                return;
            case 3:
                bindSuggestHolder((SubSuggestHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindColumnHolder(final SubColumnHolder subColumnHolder, int i) {
        final ColumnDetailModel.DataBean.ColumnInfo subColumn = this.threadlist.get(i).getSubColumn();
        ImageLoader.showImage(subColumnHolder.column_sub_item_icon, subColumn.background);
        subColumnHolder.column_sub_item_title.setText(subColumn.name);
        subColumnHolder.column_sub_item_des.setText(subColumn.info);
        if (checkIfUpdate(subColumn.columnID, subColumn.lastAppendTime)) {
            subColumnHolder.column_sub_item_tip.setVisibility(0);
        } else {
            subColumnHolder.column_sub_item_tip.setVisibility(8);
        }
        TextHelper.setQuantityString(this.context, subColumnHolder.column_sub_item_article_count, (int) com.xiaomi.smarthome.R.mipmap.img_completed_blue, String.valueOf(subColumn.count));
        TextHelper.setQuantityString(this.context, subColumnHolder.column_sub_item_follower_count, R.plurals._followers, String.valueOf(subColumn.subscribeCount));
        subColumnHolder.relSubItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SubcriptionAdapter.this.updateGreenData(subColumn.columnID, subColumn.lastAppendTime);
                subColumnHolder.column_sub_item_tip.setVisibility(8);
                Context access$100 = SubcriptionAdapter.this.context;
                ColumnDetailActivity.jumpWithId(access$100, subColumn.columnID + "");
            }
        });
    }

    private boolean checkIfUpdate(long j, long j2) {
        Query c = this.mHelper.queryBuilder().a(SubcribtionDao.Properties.ColumnID.a((Object) Long.valueOf(j)), new WhereCondition[0]).c();
        if (c.c() == null || c.c().size() <= 0 || ((Subcribtion) c.c().get(0)).getLastAppendTime().longValue() == j2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateGreenData(long j, long j2) {
        Query c = this.mHelper.queryBuilder().a(SubcribtionDao.Properties.ColumnID.a((Object) Long.valueOf(j)), new WhereCondition[0]).c();
        if (c.c() != null && c.c().size() > 0) {
            Subcribtion subcribtion = (Subcribtion) c.c().get(0);
            subcribtion.setLastAppendTime(Long.valueOf(j2));
            this.mHelper.update(subcribtion);
        }
    }

    private void bindSuggestHolder(SubSuggestHolder subSuggestHolder, final int i) {
        final ColumnDetailModel.DataBean.ColumnInfo subSuggest = this.threadlist.get(i).getSubSuggest();
        ImageLoader.showImage(subSuggestHolder.column_sub_item_icon, subSuggest.background);
        subSuggestHolder.column_sub_item_title.setText(subSuggest.name);
        subSuggestHolder.column_sub_item_des.setText(subSuggest.info);
        TextHelper.setQuantityString(this.context, subSuggestHolder.column_sub_item_article_count, R.plurals._articles, String.valueOf(subSuggest.count));
        TextHelper.setQuantityString(this.context, subSuggestHolder.column_sub_item_follower_count, R.plurals._followers, String.valueOf(subSuggest.subscribeCount));
        if (subSuggest.subscribeStatus) {
            subSuggestHolder.tvColumnFollow.setText(R.string.str_subscribed);
        } else {
            subSuggestHolder.tvColumnFollow.setText(R.string.str_subscribe);
        }
        subSuggestHolder.tvColumnFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!LoginManager.getInstance().hasLogin()) {
                    ((BaseActivity) SubcriptionAdapter.this.context).gotoAccount();
                } else if (SubcriptionAdapter.this.mOnFollowListener != null) {
                    OnFollowListener onFollowListener = SubcriptionAdapter.this.mOnFollowListener;
                    int i = i;
                    onFollowListener.onFollow(i, subSuggest.columnID + "", subSuggest.subscribeStatus);
                }
            }
        });
        subSuggestHolder.relSubItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$100 = SubcriptionAdapter.this.context;
                ColumnDetailActivity.jumpWithId(access$100, subSuggest.columnID + "");
            }
        });
    }

    public int getNormalViewType(int i) {
        return this.threadlist.get(i).getType();
    }

    public int getDataItemCount() {
        if (this.threadlist == null) {
            return 0;
        }
        return this.threadlist.size();
    }

    public void setData(List<ColumnSubData> list) {
        if (list != null) {
            this.threadlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.threadlist.clear();
    }

    public class SubEmptyHolder extends RecyclerView.ViewHolder {
        public SubEmptyHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
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

    public class SubColumnHolder extends RecyclerView.ViewHolder {
        @BindView(2131493115)
        TextView column_sub_item_article_count;
        @BindView(2131493116)
        TextView column_sub_item_des;
        @BindView(2131493117)
        TextView column_sub_item_follower_count;
        @BindView(2131493118)
        ImageView column_sub_item_icon;
        @BindView(2131493120)
        ImageView column_sub_item_tip;
        @BindView(2131493121)
        TextView column_sub_item_title;
        @BindView(2131493119)
        RelativeLayout relSubItem;

        public SubColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
