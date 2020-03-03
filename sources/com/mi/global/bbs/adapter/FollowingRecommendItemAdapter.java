package com.mi.global.bbs.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mi.global.bbs.model.FollowingUserDataModel;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import java.util.List;

public class FollowingRecommendItemAdapter extends RecyclerView.Adapter {
    public static final String TAG = "FollowingRecommendItemAdapter";
    private static final int TYPE_EMPTY = 2;
    private static final int TYPE_NORMAL = 1;
    protected LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public Context mContext;
    private List<FollowingUserDataModel> mListData;
    /* access modifiers changed from: private */
    public OnFollowingFollowListener mOnFollowListener;

    public class ColumnViewHolder_ViewBinding implements Unbinder {
        private ColumnViewHolder target;

        @UiThread
        public ColumnViewHolder_ViewBinding(ColumnViewHolder columnViewHolder, View view) {
            this.target = columnViewHolder;
            columnViewHolder.itemRelayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.item_Recommend_layout, "field 'itemRelayout'", RelativeLayout.class);
            columnViewHolder.tvRecommendTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_name, "field 'tvRecommendTitle'", TextView.class);
            columnViewHolder.tvRecommendDes = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_des, "field 'tvRecommendDes'", TextView.class);
            columnViewHolder.tvThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_thread_count, "field 'tvThreadCount'", TextView.class);
            columnViewHolder.ivRecIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_head, "field 'ivRecIcon'", ImageView.class);
            columnViewHolder.tvFollow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recommend_follower_bt, "field 'tvFollow'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ColumnViewHolder columnViewHolder = this.target;
            if (columnViewHolder != null) {
                this.target = null;
                columnViewHolder.itemRelayout = null;
                columnViewHolder.tvRecommendTitle = null;
                columnViewHolder.tvRecommendDes = null;
                columnViewHolder.tvThreadCount = null;
                columnViewHolder.ivRecIcon = null;
                columnViewHolder.tvFollow = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public FollowingRecommendItemAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(List<FollowingUserDataModel> list) {
        this.mListData = list;
        notifyDataSetChanged();
    }

    public void addData(FollowingUserDataModel followingUserDataModel) {
        this.mListData.add(followingUserDataModel);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mListData.size();
    }

    public int getItemViewType(int i) {
        return (this.mListData == null || this.mListData.get(i) == null || !TextUtils.isEmpty(this.mListData.get(i).Uid) || !TextUtils.isEmpty(this.mListData.get(i).Name)) ? 1 : 2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            return new EmptyViewHolder(this.layoutInflater.inflate(R.layout.following_item_remmand_empty, viewGroup, false));
        }
        return new ColumnViewHolder(this.layoutInflater.inflate(R.layout.following_recommend_recycle_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 1) {
            bindViewHolder((ColumnViewHolder) viewHolder, i);
        }
    }

    public void setOnFollowListener(OnFollowingFollowListener onFollowingFollowListener) {
        this.mOnFollowListener = onFollowingFollowListener;
    }

    private void bindViewHolder(final ColumnViewHolder columnViewHolder, final int i) {
        final FollowingUserDataModel followingUserDataModel = this.mListData.get(i);
        columnViewHolder.tvThreadCount.setText(this.mContext.getString(R.string.following_tab_recommend_content, new Object[]{String.valueOf(followingUserDataModel.Threads), String.valueOf(followingUserDataModel.Follower)}));
        ImageLoader.showImage(columnViewHolder.ivRecIcon, followingUserDataModel.Icon);
        columnViewHolder.tvRecommendTitle.setText(followingUserDataModel.Name);
        columnViewHolder.tvRecommendDes.setText(followingUserDataModel.Group);
        columnViewHolder.tvRecommendDes.setVisibility(8);
        if (followingUserDataModel.follow == 0) {
            columnViewHolder.tvFollow.setText(R.string.add_follow);
        } else {
            columnViewHolder.tvFollow.setText(R.string.following);
        }
        columnViewHolder.itemRelayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(FollowingRecommendItemAdapter.this.mContext, followingUserDataModel.Uid);
            }
        });
        columnViewHolder.tvFollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    columnViewHolder.tvFollow.setText(R.string.following);
                    if (FollowingRecommendItemAdapter.this.mOnFollowListener != null) {
                        FollowingRecommendItemAdapter.this.mOnFollowListener.onFolliwingFollow(FollowingRecommendItemAdapter.this, i, followingUserDataModel.Uid, followingUserDataModel.follow == 0);
                        return;
                    }
                    return;
                }
                ((BaseActivity) FollowingRecommendItemAdapter.this.mContext).gotoAccount();
            }
        });
    }

    public void setFollow(int i, int i2) {
        if (this.mListData != null && this.mListData.get(i) != null) {
            this.mListData.get(i).follow = i2;
        }
    }

    class ColumnViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493477)
        RelativeLayout itemRelayout;
        @BindView(2131494156)
        ImageView ivRecIcon;
        @BindView(2131494155)
        TextView tvFollow;
        @BindView(2131494154)
        TextView tvRecommendDes;
        @BindView(2131494158)
        TextView tvRecommendTitle;
        @BindView(2131494159)
        TextView tvThreadCount;

        ColumnViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, this.itemView);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        EmptyViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, this.itemView);
        }
    }
}
