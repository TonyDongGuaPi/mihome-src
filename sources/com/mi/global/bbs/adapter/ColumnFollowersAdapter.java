package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.model.ColumnFollowers;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import java.util.ArrayList;
import java.util.List;

public class ColumnFollowersAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context mContext;
    OnFollowListener mOnFollowListener;
    List<ColumnFollowers.ColumnFollowersList.ColumnFollower> users = new ArrayList();

    public class FollowersHolder_ViewBinding implements Unbinder {
        private FollowersHolder target;

        @UiThread
        public FollowersHolder_ViewBinding(FollowersHolder followersHolder, View view) {
            this.target = followersHolder;
            followersHolder.mFollowerItemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.follower_item_icon, "field 'mFollowerItemIcon'", ImageView.class);
            followersHolder.mFollowerItemName = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_name, "field 'mFollowerItemName'", TextView.class);
            followersHolder.mFollowerItemModerator = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_moderator, "field 'mFollowerItemModerator'", TextView.class);
            followersHolder.mFollowerItemFollowerBt = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_follower_bt, "field 'mFollowerItemFollowerBt'", TextView.class);
            followersHolder.mFollowerItemFollowerCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_follower_count, "field 'mFollowerItemFollowerCount'", TextView.class);
            followersHolder.mFollowerItemThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_thread_count, "field 'mFollowerItemThreadCount'", TextView.class);
            followersHolder.mFollowerItemReplyCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_reply_count, "field 'mFollowerItemReplyCount'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FollowersHolder followersHolder = this.target;
            if (followersHolder != null) {
                this.target = null;
                followersHolder.mFollowerItemIcon = null;
                followersHolder.mFollowerItemName = null;
                followersHolder.mFollowerItemModerator = null;
                followersHolder.mFollowerItemFollowerBt = null;
                followersHolder.mFollowerItemFollowerCount = null;
                followersHolder.mFollowerItemThreadCount = null;
                followersHolder.mFollowerItemReplyCount = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void setUsers(List<ColumnFollowers.ColumnFollowersList.ColumnFollower> list) {
        if (list != null && list.size() > 0) {
            this.users.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.users.clear();
    }

    public List<ColumnFollowers.ColumnFollowersList.ColumnFollower> getUsers() {
        return this.users;
    }

    public ColumnFollowersAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading) {
        super(activity, dataLoading);
        this.mContext = activity;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new FollowersHolder(this.layoutInflater.inflate(R.layout.bbs_followers_item, viewGroup, false));
        }
        return super.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        String str;
        String str2;
        String str3;
        String str4;
        super.onBindViewHolder(viewHolder, i);
        if (viewHolder instanceof FollowersHolder) {
            FollowersHolder followersHolder = (FollowersHolder) viewHolder;
            final ColumnFollowers.ColumnFollowersList.ColumnFollower columnFollower = this.users.get(i);
            ImageLoader.showHeadIcon(followersHolder.mFollowerItemIcon, columnFollower.base.icon);
            followersHolder.mFollowerItemName.setText(columnFollower.base.name);
            followersHolder.mFollowerItemModerator.setText(columnFollower.base.groupname);
            ColumnFollowers.ColumnFollowersList.ColumnFollower.StatisticsBean statisticsBean = columnFollower.statistics;
            if (statisticsBean == null) {
                str = "0";
            } else {
                str = statisticsBean.followers + "";
            }
            if (statisticsBean == null) {
                str2 = "0";
            } else {
                str2 = statisticsBean.replies + "";
            }
            if (statisticsBean == null) {
                str3 = "0";
            } else {
                str3 = statisticsBean.threads + "";
            }
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemFollowerCount, R.plurals._followers, str);
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemReplyCount, R.plurals._replies, str2);
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemThreadCount, R.plurals._threads, str3);
            followersHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Context access$000 = ColumnFollowersAdapter.this.mContext;
                    UserCenterActivity.jump(access$000, columnFollower.base.uid + "");
                }
            });
            followersHolder.mFollowerItemIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Context access$000 = ColumnFollowersAdapter.this.mContext;
                    UserCenterActivity.jump(access$000, columnFollower.base.uid + "");
                }
            });
            String userId = LoginManager.getInstance().getUserId();
            TextView textView = followersHolder.mFollowerItemFollowerBt;
            if (columnFollower.following) {
                str4 = this.mContext.getString(R.string.following);
            } else {
                str4 = "+ " + this.mContext.getString(R.string.follow);
            }
            textView.setText(str4);
            if (!TextUtils.isEmpty(userId)) {
                if (userId.equals(columnFollower.base.uid + "")) {
                    followersHolder.mFollowerItemFollowerBt.setVisibility(8);
                    followersHolder.mFollowerItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (ColumnFollowersAdapter.this.mOnFollowListener != null) {
                                OnFollowListener onFollowListener = ColumnFollowersAdapter.this.mOnFollowListener;
                                int i = i;
                                onFollowListener.onFollow(i, columnFollower.base.uid + "", columnFollower.following);
                            }
                        }
                    });
                }
            }
            followersHolder.mFollowerItemFollowerBt.setVisibility(0);
            followersHolder.mFollowerItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ColumnFollowersAdapter.this.mOnFollowListener != null) {
                        OnFollowListener onFollowListener = ColumnFollowersAdapter.this.mOnFollowListener;
                        int i = i;
                        onFollowListener.onFollow(i, columnFollower.base.uid + "", columnFollower.following);
                    }
                }
            });
        }
    }

    public int getDataItemCount() {
        return this.users.size();
    }

    public class FollowersHolder extends RecyclerView.ViewHolder {
        @BindView(2131493270)
        TextView mFollowerItemFollowerBt;
        @BindView(2131493271)
        TextView mFollowerItemFollowerCount;
        @BindView(2131493272)
        ImageView mFollowerItemIcon;
        @BindView(2131493273)
        TextView mFollowerItemModerator;
        @BindView(2131493274)
        TextView mFollowerItemName;
        @BindView(2131493275)
        TextView mFollowerItemReplyCount;
        @BindView(2131493276)
        TextView mFollowerItemThreadCount;

        public FollowersHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }
}
