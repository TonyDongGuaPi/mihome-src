package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.mi.global.bbs.model.FollowersResult;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context mContext;
    OnFollowListener mOnFollowListener;
    List<FollowersResult.FollowersBean.Followers> users = new ArrayList();

    public class FollowersHolder_ViewBinding implements Unbinder {
        private FollowersHolder target;

        @UiThread
        public FollowersHolder_ViewBinding(FollowersHolder followersHolder, View view) {
            this.target = followersHolder;
            followersHolder.mFollowerItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.follower_item_icon, "field 'mFollowerItemIcon'", CircleImageView.class);
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

    public void setUsers(List<FollowersResult.FollowersBean.Followers> list) {
        if (list != null && list.size() > 0) {
            this.users.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.users.clear();
    }

    public List<FollowersResult.FollowersBean.Followers> getUsers() {
        return this.users;
    }

    public FollowersAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading) {
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
        super.onBindViewHolder(viewHolder, i);
        if (viewHolder instanceof FollowersHolder) {
            FollowersHolder followersHolder = (FollowersHolder) viewHolder;
            final FollowersResult.FollowersBean.Followers followers = this.users.get(i);
            ImageLoader.showHeadIcon(followersHolder.mFollowerItemIcon, followers.icon);
            followersHolder.mFollowerItemName.setText(followers.username);
            followersHolder.mFollowerItemModerator.setText(followers.authortitle);
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemFollowerCount, R.plurals._followers, followers.follower);
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemReplyCount, R.plurals._replies, followers.posts);
            TextHelper.setQuantityString(this.mContext, followersHolder.mFollowerItemThreadCount, R.plurals._threads, followers.threads);
            followersHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(FollowersAdapter.this.mContext, followers.uid);
                }
            });
            followersHolder.mFollowerItemIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(FollowersAdapter.this.mContext, followers.uid);
                }
            });
            TextView textView = followersHolder.mFollowerItemFollowerBt;
            if (followers.follow == 1) {
                str = this.mContext.getString(R.string.following);
            } else {
                str = "+ " + this.mContext.getString(R.string.follow);
            }
            textView.setText(str);
            followersHolder.mFollowerItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FollowersAdapter.this.mOnFollowListener != null) {
                        FollowersAdapter.this.mOnFollowListener.onFollow(i, followers.uid, followers.follow == 0);
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
        CircleImageView mFollowerItemIcon;
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
