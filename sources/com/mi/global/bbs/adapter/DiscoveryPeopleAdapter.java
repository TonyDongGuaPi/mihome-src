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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.R2;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.FollowingUserDataModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CircleImageView;
import java.util.List;

public class DiscoveryPeopleAdapter extends BaseLoadMoreAdapter {
    static final int USER_FROM_FOLLOWER = 3;
    static final int USER_FROM_MODERATOR = 2;
    static final int USER_FROM_RECOMMEND = 1;
    private List<FollowingUserDataModel> items;
    /* access modifiers changed from: private */
    public Context mContext;
    OnFollowListener mOnFollowListener;

    public int getNormalViewType(int i) {
        return 0;
    }

    public class DiscoveryPeopleItemHolder_ViewBinding implements Unbinder {
        private DiscoveryPeopleItemHolder target;

        @UiThread
        public DiscoveryPeopleItemHolder_ViewBinding(DiscoveryPeopleItemHolder discoveryPeopleItemHolder, View view) {
            this.target = discoveryPeopleItemHolder;
            discoveryPeopleItemHolder.tvDiscoveryTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.discover_item_title, "field 'tvDiscoveryTitle'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.follower_item_icon, "field 'mFollowerItemIcon'", CircleImageView.class);
            discoveryPeopleItemHolder.mFollowerItemName = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_name, "field 'mFollowerItemName'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemModerator = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_moderator, "field 'mFollowerItemModerator'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemFollowerBt = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_follower_bt, "field 'mFollowerItemFollowerBt'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemFollowerCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_follower_count, "field 'mFollowerItemFollowerCount'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_thread_count, "field 'mFollowerItemThreadCount'", TextView.class);
            discoveryPeopleItemHolder.mFollowerItemReplyCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_reply_count, "field 'mFollowerItemReplyCount'", TextView.class);
            discoveryPeopleItemHolder.relDiscoveryDetail = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.discover_item_detail, "field 'relDiscoveryDetail'", RelativeLayout.class);
            discoveryPeopleItemHolder.llMessagePic = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.rl_message_picture, "field 'llMessagePic'", LinearLayout.class);
            discoveryPeopleItemHolder.discoveryPeopleThread = (TextView) Utils.findRequiredViewAsType(view, R.id.discover_item_thread, "field 'discoveryPeopleThread'", TextView.class);
            discoveryPeopleItemHolder.discoveryThreadPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.discover_item_image, "field 'discoveryThreadPic'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            DiscoveryPeopleItemHolder discoveryPeopleItemHolder = this.target;
            if (discoveryPeopleItemHolder != null) {
                this.target = null;
                discoveryPeopleItemHolder.tvDiscoveryTitle = null;
                discoveryPeopleItemHolder.mFollowerItemIcon = null;
                discoveryPeopleItemHolder.mFollowerItemName = null;
                discoveryPeopleItemHolder.mFollowerItemModerator = null;
                discoveryPeopleItemHolder.mFollowerItemFollowerBt = null;
                discoveryPeopleItemHolder.mFollowerItemFollowerCount = null;
                discoveryPeopleItemHolder.mFollowerItemThreadCount = null;
                discoveryPeopleItemHolder.mFollowerItemReplyCount = null;
                discoveryPeopleItemHolder.relDiscoveryDetail = null;
                discoveryPeopleItemHolder.llMessagePic = null;
                discoveryPeopleItemHolder.discoveryPeopleThread = null;
                discoveryPeopleItemHolder.discoveryThreadPic = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public DiscoveryPeopleAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, List<FollowingUserDataModel> list) {
        super(activity, dataLoading);
        this.mContext = activity;
        this.items = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return createFavorItemHolder(viewGroup);
    }

    private DiscoveryPeopleItemHolder createFavorItemHolder(ViewGroup viewGroup) {
        return new DiscoveryPeopleItemHolder(this.layoutInflater.inflate(R.layout.bbs_discover_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (getItemViewType(i) == 0) {
            bindThreadDataHolder((DiscoveryPeopleItemHolder) viewHolder, i);
        }
    }

    private void bindThreadDataHolder(DiscoveryPeopleItemHolder discoveryPeopleItemHolder, final int i) {
        String str;
        final FollowingUserDataModel followingUserDataModel = this.items.get(i);
        if (followingUserDataModel != null) {
            if (followingUserDataModel.Userfrom == 1) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_recommend);
            } else if (followingUserDataModel.Userfrom == 2) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(this.mContext.getString(R.string.discovery_moderator, new Object[]{followingUserDataModel.Forum}));
            } else if (followingUserDataModel.Userfrom == 3) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_favorite_user);
            } else {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_recommend);
            }
            discoveryPeopleItemHolder.mFollowerItemName.setText(followingUserDataModel.Name);
            ImageLoader.showImage(discoveryPeopleItemHolder.mFollowerItemIcon, followingUserDataModel.Icon);
            discoveryPeopleItemHolder.mFollowerItemModerator.setText(followingUserDataModel.Group);
            TextHelper.setQuantityString(this.mContext, discoveryPeopleItemHolder.mFollowerItemFollowerCount, (int) com.xiaomi.smarthome.R.mipmap.img_navigation, followingUserDataModel.Follower);
            TextHelper.setQuantityString(this.mContext, discoveryPeopleItemHolder.mFollowerItemReplyCount, (int) R2.plurals._replies, followingUserDataModel.Reply);
            TextHelper.setQuantityString(this.mContext, discoveryPeopleItemHolder.mFollowerItemThreadCount, (int) R2.plurals._threads, followingUserDataModel.Threads);
            discoveryPeopleItemHolder.relDiscoveryDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(DiscoveryPeopleAdapter.this.mContext, followingUserDataModel.Uid);
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DISCOVER, Constants.ClickEvent.CLICK_USER, "click_user_" + followingUserDataModel.Uid);
                }
            });
            discoveryPeopleItemHolder.mFollowerItemIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(DiscoveryPeopleAdapter.this.mContext, followingUserDataModel.Uid);
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DISCOVER, Constants.ClickEvent.CLICK_USER, "click_user_" + followingUserDataModel.Uid);
                }
            });
            TextView textView = discoveryPeopleItemHolder.mFollowerItemFollowerBt;
            if (followingUserDataModel.follow == 1) {
                str = this.mContext.getString(R.string.following);
            } else {
                str = "+ " + this.mContext.getString(R.string.follow);
            }
            textView.setText(str);
            discoveryPeopleItemHolder.mFollowerItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (DiscoveryPeopleAdapter.this.mOnFollowListener != null) {
                        DiscoveryPeopleAdapter.this.mOnFollowListener.onFollow(i, followingUserDataModel.Uid, followingUserDataModel.follow == 0);
                        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DISCOVER, Constants.ClickEvent.CLICK_FOLLOW, "click_follow_" + followingUserDataModel.Uid);
                    }
                }
            });
            if (!TextUtils.isEmpty(followingUserDataModel.Subject)) {
                discoveryPeopleItemHolder.llMessagePic.setVisibility(0);
                discoveryPeopleItemHolder.discoveryPeopleThread.setText(followingUserDataModel.Subject);
                if (!TextUtils.isEmpty(followingUserDataModel.Imgurl)) {
                    discoveryPeopleItemHolder.discoveryThreadPic.setVisibility(0);
                    ImageLoader.showImage(discoveryPeopleItemHolder.discoveryThreadPic, followingUserDataModel.Imgurl);
                } else {
                    discoveryPeopleItemHolder.discoveryThreadPic.setVisibility(8);
                }
                final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{followingUserDataModel.Tid}));
                discoveryPeopleItemHolder.llMessagePic.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DISCOVER, "click_thread", "click_thread_" + followingUserDataModel.Tid);
                        WebActivity.jump(DiscoveryPeopleAdapter.this.mContext, appUrl);
                    }
                });
                return;
            }
            discoveryPeopleItemHolder.llMessagePic.setVisibility(8);
        }
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public List<FollowingUserDataModel> getUsers() {
        return this.items;
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<FollowingUserDataModel> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    static class DiscoveryPeopleItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493202)
        TextView discoveryPeopleThread;
        @BindView(2131493201)
        ImageView discoveryThreadPic;
        @BindView(2131493898)
        LinearLayout llMessagePic;
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
        @BindView(2131493200)
        RelativeLayout relDiscoveryDetail;
        @BindView(2131493203)
        TextView tvDiscoveryTitle;

        public DiscoveryPeopleItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
