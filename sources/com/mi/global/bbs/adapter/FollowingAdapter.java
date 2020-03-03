package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.inter.OnCommentClickListener;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.FollowData;
import com.mi.global.bbs.model.FollowingActivityModel;
import com.mi.global.bbs.model.FollowingFeedModel;
import com.mi.global.bbs.model.FollowingUserDataModel;
import com.mi.global.bbs.model.LinkModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.ui.DiscoverPeopleActivity;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.checkin.SignDetailActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.post.PostShortContentActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.utils.TimeUtils;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.LikeAndCommentView;
import com.mi.global.bbs.view.praise.OnPraiseListener;
import com.mi.util.Coder;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FollowingAdapter extends BaseLoadMoreAdapter {
    private static final int SHORT_CONTENT_THUMB_UP_TYPE = 1;
    private static final int SIGN_THUMB_UP_TYPE = 0;
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<FollowData> followDatas;
    /* access modifiers changed from: private */
    public OnFollowingFollowListener followingFollowListener;
    /* access modifiers changed from: private */
    public HashMap<String, LinkModel> mLinkMap = new HashMap<>();
    /* access modifiers changed from: private */
    public OnFollowListener mOnFollowListener;
    private OnShareListener onShareListener;
    private String pageName;

    public class BirthdayItemHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private BirthdayItemHolder target;

        @UiThread
        public BirthdayItemHolder_ViewBinding(BirthdayItemHolder birthdayItemHolder, View view) {
            super(birthdayItemHolder, view);
            this.target = birthdayItemHolder;
            birthdayItemHolder.itemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_big_pic, "field 'itemPic'", ImageView.class);
        }

        public void unbind() {
            BirthdayItemHolder birthdayItemHolder = this.target;
            if (birthdayItemHolder != null) {
                this.target = null;
                birthdayItemHolder.itemPic = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumBigPicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumBigPicHolder target;

        @UiThread
        public ForumBigPicHolder_ViewBinding(ForumBigPicHolder forumBigPicHolder, View view) {
            super(forumBigPicHolder, view);
            this.target = forumBigPicHolder;
            forumBigPicHolder.forumItemBigPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_big_pic, "field 'forumItemBigPic'", ImageView.class);
        }

        public void unbind() {
            ForumBigPicHolder forumBigPicHolder = this.target;
            if (forumBigPicHolder != null) {
                this.target = null;
                forumBigPicHolder.forumItemBigPic = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumMultiPicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumMultiPicHolder target;

        @UiThread
        public ForumMultiPicHolder_ViewBinding(ForumMultiPicHolder forumMultiPicHolder, View view) {
            super(forumMultiPicHolder, view);
            this.target = forumMultiPicHolder;
            forumMultiPicHolder.itemPic1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic_1, "field 'itemPic1'", ImageView.class);
            forumMultiPicHolder.itemPic2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic_2, "field 'itemPic2'", ImageView.class);
            forumMultiPicHolder.itemPic3 = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic_3, "field 'itemPic3'", ImageView.class);
        }

        public void unbind() {
            ForumMultiPicHolder forumMultiPicHolder = this.target;
            if (forumMultiPicHolder != null) {
                this.target = null;
                forumMultiPicHolder.itemPic1 = null;
                forumMultiPicHolder.itemPic2 = null;
                forumMultiPicHolder.itemPic3 = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumSignHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumSignHolder target;

        @UiThread
        public ForumSignHolder_ViewBinding(ForumSignHolder forumSignHolder, View view) {
            super(forumSignHolder, view);
            this.target = forumSignHolder;
            forumSignHolder.mSignedFeelIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.signed_feel_iv, "field 'mSignedFeelIv'", ImageView.class);
        }

        public void unbind() {
            ForumSignHolder forumSignHolder = this.target;
            if (forumSignHolder != null) {
                this.target = null;
                forumSignHolder.mSignedFeelIv = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumTwoPicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumTwoPicHolder target;

        @UiThread
        public ForumTwoPicHolder_ViewBinding(ForumTwoPicHolder forumTwoPicHolder, View view) {
            super(forumTwoPicHolder, view);
            this.target = forumTwoPicHolder;
            forumTwoPicHolder.itemPic1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic_1, "field 'itemPic1'", ImageView.class);
            forumTwoPicHolder.itemPic2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic_2, "field 'itemPic2'", ImageView.class);
        }

        public void unbind() {
            ForumTwoPicHolder forumTwoPicHolder = this.target;
            if (forumTwoPicHolder != null) {
                this.target = null;
                forumTwoPicHolder.itemPic1 = null;
                forumTwoPicHolder.itemPic2 = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class LinkItemHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private LinkItemHolder target;

        @UiThread
        public LinkItemHolder_ViewBinding(LinkItemHolder linkItemHolder, View view) {
            super(linkItemHolder, view);
            this.target = linkItemHolder;
            linkItemHolder.itemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic, "field 'itemPic'", ImageView.class);
        }

        public void unbind() {
            LinkItemHolder linkItemHolder = this.target;
            if (linkItemHolder != null) {
                this.target = null;
                linkItemHolder.itemPic = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FollowingFollowOtherHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private FollowingFollowOtherHolder target;

        @UiThread
        public FollowingFollowOtherHolder_ViewBinding(FollowingFollowOtherHolder followingFollowOtherHolder, View view) {
            super(followingFollowOtherHolder, view);
            this.target = followingFollowOtherHolder;
            followingFollowOtherHolder.followingItemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic, "field 'followingItemPic'", ImageView.class);
            followingFollowOtherHolder.mFollowerItemFollowerCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_follower_count, "field 'mFollowerItemFollowerCount'", TextView.class);
            followingFollowOtherHolder.mFollowerItemThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.follower_item_thread_count, "field 'mFollowerItemThreadCount'", TextView.class);
        }

        public void unbind() {
            FollowingFollowOtherHolder followingFollowOtherHolder = this.target;
            if (followingFollowOtherHolder != null) {
                this.target = null;
                followingFollowOtherHolder.followingItemPic = null;
                followingFollowOtherHolder.mFollowerItemFollowerCount = null;
                followingFollowOtherHolder.mFollowerItemThreadCount = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FollowingSubscribeHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private FollowingSubscribeHolder target;

        @UiThread
        public FollowingSubscribeHolder_ViewBinding(FollowingSubscribeHolder followingSubscribeHolder, View view) {
            super(followingSubscribeHolder, view);
            this.target = followingSubscribeHolder;
            followingSubscribeHolder.forumItemContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_content, "field 'forumItemContent'", TextView.class);
            followingSubscribeHolder.followingItemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic, "field 'followingItemPic'", ImageView.class);
        }

        public void unbind() {
            FollowingSubscribeHolder followingSubscribeHolder = this.target;
            if (followingSubscribeHolder != null) {
                this.target = null;
                followingSubscribeHolder.forumItemContent = null;
                followingSubscribeHolder.followingItemPic = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumOnePicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumOnePicHolder target;

        @UiThread
        public ForumOnePicHolder_ViewBinding(ForumOnePicHolder forumOnePicHolder, View view) {
            super(forumOnePicHolder, view);
            this.target = forumOnePicHolder;
            forumOnePicHolder.itemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_pic, "field 'itemPic'", ImageView.class);
            forumOnePicHolder.itemInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.item_info, "field 'itemInfo'", TextView.class);
        }

        public void unbind() {
            ForumOnePicHolder forumOnePicHolder = this.target;
            if (forumOnePicHolder != null) {
                this.target = null;
                forumOnePicHolder.itemPic = null;
                forumOnePicHolder.itemInfo = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ActivityItemHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ActivityItemHolder target;

        @UiThread
        public ActivityItemHolder_ViewBinding(ActivityItemHolder activityItemHolder, View view) {
            super(activityItemHolder, view);
            this.target = activityItemHolder;
            activityItemHolder.followingActivityDateLine = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_time, "field 'followingActivityDateLine'", TextView.class);
            activityItemHolder.followingActivityAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_location, "field 'followingActivityAddress'", TextView.class);
            activityItemHolder.followingActivityPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_img, "field 'followingActivityPic'", ImageView.class);
            activityItemHolder.activityLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.activity_layout, "field 'activityLayout'", LinearLayout.class);
        }

        public void unbind() {
            ActivityItemHolder activityItemHolder = this.target;
            if (activityItemHolder != null) {
                this.target = null;
                activityItemHolder.followingActivityDateLine = null;
                activityItemHolder.followingActivityAddress = null;
                activityItemHolder.followingActivityPic = null;
                activityItemHolder.activityLayout = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class DefaultHolder_ViewBinding implements Unbinder {
        private DefaultHolder target;

        @UiThread
        public DefaultHolder_ViewBinding(DefaultHolder defaultHolder, View view) {
            this.target = defaultHolder;
            defaultHolder.itemUserIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_user_icon, "field 'itemUserIcon'", ImageView.class);
            defaultHolder.itemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_user_name, "field 'itemUserName'", TextView.class);
            defaultHolder.itemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.item_post_time, "field 'itemPostTime'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            DefaultHolder defaultHolder = this.target;
            if (defaultHolder != null) {
                this.target = null;
                defaultHolder.itemUserIcon = null;
                defaultHolder.itemUserName = null;
                defaultHolder.itemPostTime = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FollowingCommendHolder_ViewBinding implements Unbinder {
        private FollowingCommendHolder target;

        @UiThread
        public FollowingCommendHolder_ViewBinding(FollowingCommendHolder followingCommendHolder, View view) {
            this.target = followingCommendHolder;
            followingCommendHolder.recommendRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.id_recyclerview_horizontal, "field 'recommendRecycleView'", RecyclerView.class);
            followingCommendHolder.recommend_more = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_following_recommend_more, "field 'recommend_more'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FollowingCommendHolder followingCommendHolder = this.target;
            if (followingCommendHolder != null) {
                this.target = null;
                followingCommendHolder.recommendRecycleView = null;
                followingCommendHolder.recommend_more = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class TopShareHolder_ViewBinding implements Unbinder {
        private TopShareHolder target;

        @UiThread
        public TopShareHolder_ViewBinding(TopShareHolder topShareHolder, View view) {
            this.target = topShareHolder;
            topShareHolder.icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_icon, "field 'icon'", ImageView.class);
            topShareHolder.post = (ImageView) Utils.findRequiredViewAsType(view, R.id.following_top_post, "field 'post'", ImageView.class);
            topShareHolder.postLayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.post_layout, "field 'postLayout'", RelativeLayout.class);
        }

        @CallSuper
        public void unbind() {
            TopShareHolder topShareHolder = this.target;
            if (topShareHolder != null) {
                this.target = null;
                topShareHolder.icon = null;
                topShareHolder.post = null;
                topShareHolder.postLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class BaseForumHolder_ViewBinding implements Unbinder {
        private BaseForumHolder target;

        @UiThread
        public BaseForumHolder_ViewBinding(BaseForumHolder baseForumHolder, View view) {
            this.target = baseForumHolder;
            baseForumHolder.itemUserIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.item_user_icon, "field 'itemUserIcon'", ImageView.class);
            baseForumHolder.itemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_user_name, "field 'itemUserName'", TextView.class);
            baseForumHolder.itemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.item_post_time, "field 'itemPostTime'", TextView.class);
            baseForumHolder.itemUserNameContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_post_type, "field 'itemUserNameContent'", TextView.class);
            baseForumHolder.itemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", TextView.class);
            baseForumHolder.likeCommentView = (LikeAndCommentView) Utils.findRequiredViewAsType(view, R.id.view_like_comment, "field 'likeCommentView'", LikeAndCommentView.class);
        }

        @CallSuper
        public void unbind() {
            BaseForumHolder baseForumHolder = this.target;
            if (baseForumHolder != null) {
                this.target = null;
                baseForumHolder.itemUserIcon = null;
                baseForumHolder.itemUserName = null;
                baseForumHolder.itemPostTime = null;
                baseForumHolder.itemUserNameContent = null;
                baseForumHolder.itemTitle = null;
                baseForumHolder.likeCommentView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
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

    public FollowingAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        this.pageName = str;
        this.followDatas = new ArrayList();
    }

    public void addData(List<FollowData> list) {
        if (list != null) {
            this.followDatas.clear();
            this.followDatas.addAll(list);
            notifyDataSetChanged();
        }
    }

    public FollowData getFollowDatas(int i) {
        return this.followDatas.get(i);
    }

    public void clear() {
        this.followDatas.clear();
    }

    public int getNormalViewType(int i) {
        FollowData followData = this.followDatas.get(i);
        String str = null;
        List<String> list = (followData == null || followData.getFollowingFeedModel() == null || followData.getFollowingFeedModel().ExtraData == null) ? null : followData.getFollowingFeedModel().ExtraData.Urls;
        if (!(followData == null || followData.getFollowingFeedModel() == null)) {
            str = followData.getFollowingFeedModel().Content;
        }
        int type = followData.getType();
        if (type == 2) {
            return 2;
        }
        if (type == 4) {
            return 4;
        }
        if (type == 12) {
            return 12;
        }
        if (type == 200) {
            return 200;
        }
        switch (type) {
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            default:
                switch (type) {
                    case 14:
                        return 14;
                    case 15:
                        if (list == null) {
                            return 151;
                        }
                        switch (list.size()) {
                            case 0:
                                return 151;
                            case 1:
                                return 153;
                            case 2:
                                return 154;
                            default:
                                return 155;
                        }
                    case 16:
                        return 16;
                    case 17:
                        return 17;
                    case 18:
                        return 18;
                    case 19:
                        if (!TextUtils.isEmpty(str)) {
                            return 193;
                        }
                        if (list == null) {
                            return 190;
                        }
                        switch (list.size()) {
                            case 1:
                                return 190;
                            case 2:
                                return 191;
                            case 3:
                                return 192;
                            default:
                                return 190;
                        }
                    default:
                        switch (type) {
                            case 91:
                                return 91;
                            case 92:
                                return 92;
                            default:
                                return 999;
                        }
                }
        }
    }

    public int getDataItemCount() {
        return this.followDatas.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 2:
            case 4:
            case 12:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
                return new ForumOnePicHolder(this.layoutInflater.inflate(R.layout.following_item_one_pic, viewGroup, false));
            case 7:
                return new FollowingFollowOtherHolder(this.layoutInflater.inflate(R.layout.follow_item_follow_other, viewGroup, false));
            case 8:
                return new FollowingSubscribeHolder(this.layoutInflater.inflate(R.layout.follow_item_subscription, viewGroup, false));
            case 9:
                return new FollowingCommendHolder(this.layoutInflater.inflate(R.layout.following_recommend_recycle, viewGroup, false));
            case 14:
                return new ForumSignHolder(this.layoutInflater.inflate(R.layout.following_item_sigin_pic, viewGroup, false));
            case 16:
            case 17:
                return new ActivityItemHolder(this.layoutInflater.inflate(R.layout.following_activitys_type_item, viewGroup, false));
            case 18:
                return new BirthdayItemHolder(this.layoutInflater.inflate(R.layout.following_item_bitrhday, viewGroup, false));
            case 91:
                return new FollowingEmptyHolder(this.layoutInflater.inflate(R.layout.following_sub_empty, viewGroup, false));
            case 92:
                return new DiscoveryPeopleItemHolder(this.layoutInflater.inflate(R.layout.discover_item, viewGroup, false));
            case 190:
                return new ForumBigPicHolder(this.layoutInflater.inflate(R.layout.following_item_big_pic, viewGroup, false));
            case 191:
                return new ForumTwoPicHolder(this.layoutInflater.inflate(R.layout.following_item_two_pic, viewGroup, false));
            case 192:
                return new ForumMultiPicHolder(this.layoutInflater.inflate(R.layout.following_item_three_pic, viewGroup, false));
            case 193:
                return new LinkItemHolder(this.layoutInflater.inflate(R.layout.following_item_link, viewGroup, false));
            case 200:
                return new TopShareHolder(this.layoutInflater.inflate(R.layout.following_top_layout, viewGroup, false));
            case 999:
                return new DefaultHolder(this.layoutInflater.inflate(R.layout.following_item_default, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case 7:
                bindFollowOtherHolder((FollowingFollowOtherHolder) viewHolder, i);
                return;
            case 8:
                bindSubscribeHolder((FollowingSubscribeHolder) viewHolder, i);
                return;
            case 9:
                bindRecommendHolder((FollowingCommendHolder) viewHolder, i);
                return;
            default:
                switch (itemViewType) {
                    case 16:
                    case 17:
                        bindActivityHolder((ActivityItemHolder) viewHolder, i);
                        return;
                    case 18:
                        bindBithdayHolder((BirthdayItemHolder) viewHolder, i);
                        return;
                    default:
                        switch (itemViewType) {
                            case 151:
                            case 152:
                            case 153:
                            case 154:
                            case 155:
                                break;
                            default:
                                switch (itemViewType) {
                                    case 190:
                                        bindBigPicHolder((ForumBigPicHolder) viewHolder, i);
                                        return;
                                    case 191:
                                        bindTwoPicHolder((ForumTwoPicHolder) viewHolder, i);
                                        return;
                                    case 192:
                                        bindMultiHolder((ForumMultiPicHolder) viewHolder, i);
                                        return;
                                    case 193:
                                        bindLinkHolder((LinkItemHolder) viewHolder, i);
                                        return;
                                    default:
                                        switch (itemViewType) {
                                            case 2:
                                            case 4:
                                            case 12:
                                                break;
                                            case 14:
                                                bindSignHolder((ForumSignHolder) viewHolder, i);
                                                return;
                                            case 92:
                                                bindRecommendEmptyHolder((DiscoveryPeopleItemHolder) viewHolder, i);
                                                return;
                                            case 200:
                                                bindTopShareHolder((TopShareHolder) viewHolder);
                                                return;
                                            case 999:
                                                bindDefault((DefaultHolder) viewHolder, i);
                                                return;
                                            default:
                                                return;
                                        }
                                }
                        }
                        bindOnePicHolder((ForumOnePicHolder) viewHolder, i);
                        return;
                }
        }
    }

    private void bindSignHolder(ForumSignHolder forumSignHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(forumSignHolder, followingFeedModel, i);
        Glide.a((FragmentActivity) this.context).a(followingFeedModel.Url).b((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.feel_icon)).c(R.drawable.feel_icon)).b(R.drawable.feel_icon)).a(forumSignHolder.mSignedFeelIv);
    }

    private void bindBigPicHolder(ForumBigPicHolder forumBigPicHolder, int i) {
        final FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(forumBigPicHolder, followingFeedModel, i);
        if (!(followingFeedModel == null || followingFeedModel.ExtraData == null || followingFeedModel.ExtraData.Urls == null || followingFeedModel.ExtraData.Urls.size() <= 0)) {
            ImageLoader.showImage(forumBigPicHolder.forumItemBigPic, followingFeedModel.ExtraData.Urls.get(0));
        }
        final ArrayList arrayList = new ArrayList();
        if (followingFeedModel == null || followingFeedModel.ExtraData == null || followingFeedModel.ExtraData.Urls == null || followingFeedModel.ExtraData.Urls.size() <= 0) {
            forumBigPicHolder.forumItemBigPic.setVisibility(8);
        } else {
            forumBigPicHolder.forumItemBigPic.setVisibility(0);
            arrayList.add(followingFeedModel.ExtraData.Urls.get(0));
        }
        forumBigPicHolder.forumItemBigPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, followingFeedModel.ExtraData.Urls.get(0), (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindMultiHolder(ForumMultiPicHolder forumMultiPicHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(forumMultiPicHolder, followingFeedModel, i);
        final List<String> list = followingFeedModel.ExtraData.Urls;
        ImageLoader.showImage(forumMultiPicHolder.itemPic1, list.get(0));
        ImageLoader.showImage(forumMultiPicHolder.itemPic2, list.get(1));
        ImageLoader.showImage(forumMultiPicHolder.itemPic3, list.get(2));
        final ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(0));
        arrayList.add(list.get(1));
        arrayList.add(list.get(2));
        forumMultiPicHolder.itemPic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, (String) list.get(0), (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumMultiPicHolder.itemPic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, (String) list.get(1), (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumMultiPicHolder.itemPic3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, (String) list.get(2), (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindTwoPicHolder(ForumTwoPicHolder forumTwoPicHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(forumTwoPicHolder, followingFeedModel, i);
        final List<String> list = followingFeedModel.ExtraData.Urls;
        ImageLoader.showImage(forumTwoPicHolder.itemPic1, list.get(0));
        ImageLoader.showImage(forumTwoPicHolder.itemPic2, list.get(1));
        final ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(0));
        arrayList.add(list.get(1));
        forumTwoPicHolder.itemPic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, (String) list.get(0), (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumTwoPicHolder.itemPic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(FollowingAdapter.this.context, (String) list.get(1), (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindOnePicHolder(ForumOnePicHolder forumOnePicHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(forumOnePicHolder, followingFeedModel, i);
        forumOnePicHolder.itemPic.setVisibility(0);
        if (followingFeedModel.Type == 4) {
            Glide.a((FragmentActivity) this.context).a(Integer.valueOf(R.drawable.vote_default_img)).a(forumOnePicHolder.itemPic);
        } else if (followingFeedModel.Type == 12) {
            Glide.a((FragmentActivity) this.context).a(Integer.valueOf(R.drawable.debate_default_img)).a(forumOnePicHolder.itemPic);
        } else if (followingFeedModel.Type == 2) {
            if (TextUtils.isEmpty(followingFeedModel.Url)) {
                forumOnePicHolder.itemPic.setVisibility(8);
            } else {
                ImageLoader.showImage(forumOnePicHolder.itemPic, followingFeedModel.Url);
            }
        } else if (getNormalViewType(i) == 151) {
            forumOnePicHolder.itemPic.setVisibility(8);
        } else if (followingFeedModel.Type == 15) {
            ImageLoader.showImage(forumOnePicHolder.itemPic, (followingFeedModel.ExtraData == null || followingFeedModel.ExtraData.Urls == null || followingFeedModel.ExtraData.Urls.size() <= 0) ? "" : followingFeedModel.ExtraData.Urls.get(0));
        } else {
            ImageLoader.showImage(forumOnePicHolder.itemPic, followingFeedModel.Url);
        }
        String str = "";
        if (!TextUtils.isEmpty(followingFeedModel.ExtraData.Area)) {
            str = followingFeedModel.ExtraData.Area;
        }
        String str2 = "";
        if (!TextUtils.isEmpty(followingFeedModel.ExtraData.Fname)) {
            str2 = followingFeedModel.ExtraData.Fname;
        }
        TextView textView = forumOnePicHolder.itemInfo;
        textView.setText(getLocalByArea(str) + " " + str2);
    }

    private String getLocalByArea(String str) {
        for (String[] strArr : LocaleHelper.COUNTRIES_MAP) {
            if (strArr[0].equalsIgnoreCase(str)) {
                return strArr[2];
            }
        }
        return "India";
    }

    private void bindSubscribeHolder(FollowingSubscribeHolder followingSubscribeHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(followingSubscribeHolder, followingFeedModel, i);
        followingSubscribeHolder.forumItemContent.setText(followingFeedModel.Content);
        ImageLoader.showImage(followingSubscribeHolder.followingItemPic, followingFeedModel.Url);
    }

    private void bindFollowOtherHolder(FollowingFollowOtherHolder followingFollowOtherHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(followingFollowOtherHolder, followingFeedModel, i);
        TextHelper.setQuantityString((Context) this.context, followingFollowOtherHolder.mFollowerItemFollowerCount, R.plurals._followers, followingFeedModel.ExtraData.Follower);
        TextHelper.setQuantityString((Context) this.context, followingFollowOtherHolder.mFollowerItemThreadCount, R.plurals._threads, followingFeedModel.ExtraData.Threads);
        ImageLoader.showImage(followingFollowOtherHolder.followingItemPic, followingFeedModel.Url);
    }

    private void bindNoPicHolder(ForumNoPicHolder forumNoPicHolder, int i) {
        handleCommonPart(forumNoPicHolder, this.followDatas.get(i).getFollowingFeedModel(), i);
    }

    private void bindRecommendHolder(FollowingCommendHolder followingCommendHolder, int i) {
        List<FollowingUserDataModel> followingUserDataModels = this.followDatas.get(i).getFollowingUserDataModels();
        if (followingUserDataModels != null) {
            followingCommendHolder.itemAdapter.setData(followingUserDataModels);
        }
        followingCommendHolder.itemAdapter.addData(new FollowingUserDataModel());
    }

    private void bindRecommendEmptyHolder(DiscoveryPeopleItemHolder discoveryPeopleItemHolder, final int i) {
        String str;
        final FollowingUserDataModel followingUserDataModel = this.followDatas.get(i).getFollowingUserDataModel();
        if (followingUserDataModel != null) {
            if (followingUserDataModel.Userfrom == 1) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_recommend);
            } else if (followingUserDataModel.Userfrom == 2) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(this.context.getString(R.string.discovery_moderator, new Object[]{followingUserDataModel.Forum}));
            } else if (followingUserDataModel.Userfrom == 3) {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_favorite_user);
            } else {
                discoveryPeopleItemHolder.tvDiscoveryTitle.setText(R.string.discovery_recommend);
            }
            discoveryPeopleItemHolder.mFollowerItemName.setText(followingUserDataModel.Name);
            ImageLoader.showImage(discoveryPeopleItemHolder.mFollowerItemIcon, followingUserDataModel.Icon);
            discoveryPeopleItemHolder.mFollowerItemModerator.setText(followingUserDataModel.Group);
            TextHelper.setQuantityString((Context) this.context, discoveryPeopleItemHolder.mFollowerItemFollowerCount, R.plurals._followers, followingUserDataModel.Follower);
            TextHelper.setQuantityString((Context) this.context, discoveryPeopleItemHolder.mFollowerItemReplyCount, R.plurals._replies, followingUserDataModel.Reply);
            TextHelper.setQuantityString((Context) this.context, discoveryPeopleItemHolder.mFollowerItemThreadCount, R.plurals._threads, followingUserDataModel.Threads);
            TextView textView = discoveryPeopleItemHolder.mFollowerItemFollowerBt;
            if (followingUserDataModel.follow == 1) {
                str = this.context.getString(R.string.following);
            } else {
                str = "+ " + this.context.getString(R.string.follow);
            }
            textView.setText(str);
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
                        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, "click_thread", "click_thread_" + followingUserDataModel.Tid);
                        WebActivity.jump(FollowingAdapter.this.context, appUrl);
                    }
                });
            } else {
                discoveryPeopleItemHolder.llMessagePic.setVisibility(8);
            }
            discoveryPeopleItemHolder.relDiscoveryDetail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(FollowingAdapter.this.context, followingUserDataModel.Uid);
                }
            });
            discoveryPeopleItemHolder.mFollowerItemIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_USER, "click_user_" + followingUserDataModel.Uid);
                    UserCenterActivity.jump(FollowingAdapter.this.context, followingUserDataModel.Uid);
                }
            });
            discoveryPeopleItemHolder.mFollowerItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FollowingAdapter.this.mOnFollowListener != null) {
                        FollowingAdapter.this.mOnFollowListener.onFollow(i, followingUserDataModel.Uid, followingUserDataModel.follow == 0);
                    }
                }
            });
        }
    }

    private void bindActivityHolder(ActivityItemHolder activityItemHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        if (followingFeedModel != null && !TextUtils.isEmpty(followingFeedModel.Extra)) {
            handleCommonPart(activityItemHolder, followingFeedModel, i);
            FollowingActivityModel followingActivityModel = (FollowingActivityModel) JsonParser.parse(followingFeedModel.Extra, FollowingActivityModel.class);
            if (followingActivityModel != null) {
                if (!TextUtils.isEmpty(followingActivityModel.icon)) {
                    ImageLoader.showImage(activityItemHolder.followingActivityPic, followingActivityModel.icon);
                } else {
                    activityItemHolder.followingActivityPic.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.activity_default_img, this.context.getTheme()));
                }
                if (!TextUtils.isEmpty(followingActivityModel.starttimefrom)) {
                    activityItemHolder.followingActivityDateLine.setText(followingActivityModel.starttimefrom);
                }
                if (!TextUtils.isEmpty(followingActivityModel.place)) {
                    activityItemHolder.followingActivityAddress.setText(followingActivityModel.place);
                }
            }
        }
    }

    private void bindBithdayHolder(BirthdayItemHolder birthdayItemHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(birthdayItemHolder, followingFeedModel, i);
        ImageLoader.showImage(birthdayItemHolder.itemPic, followingFeedModel.ExtraData.Urls.get(0));
    }

    private void bindLinkHolder(final LinkItemHolder linkItemHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        handleCommonPart(linkItemHolder, followingFeedModel, i);
        final String str = followingFeedModel.Content;
        LinkModel linkModel = this.mLinkMap.get(str);
        if (linkModel != null) {
            showLinkData(linkModel, linkItemHolder);
        } else {
            LinkModel.loadByUrl(this.context, followingFeedModel.Content, new LinkModel.LinkDispatcher() {
                public void onDispatch(LinkModel linkModel) {
                    FollowingAdapter.this.mLinkMap.put(str, linkModel);
                    FollowingAdapter.this.showLinkData(linkModel, linkItemHolder);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showLinkData(LinkModel linkModel, LinkItemHolder linkItemHolder) {
        if (linkItemHolder.itemTitle != null) {
            linkItemHolder.itemTitle.setText(linkModel.title);
        }
        if (linkItemHolder.itemPic != null) {
            String str = linkModel.firstImagePath;
            ((ViewGroup) linkItemHolder.itemPic.getParent()).findViewById(R.id.link_img_cover).setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            ImageLoader.showImage(linkItemHolder.itemPic, str, (RequestOptions) ((RequestOptions) ImageLoader.getDefaultOptions().c(R.drawable.link_default)).b(R.drawable.link_default));
        }
    }

    private void bindTopShareHolder(TopShareHolder topShareHolder) {
        String stringPref = Utils.Preference.getStringPref(this.context, "userInfo", "");
        final String userId = LoginManager.getInstance().getUserId();
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(stringPref)) {
            Glide.a((FragmentActivity) this.context).a(Integer.valueOf(R.drawable.icon_default_head)).a(topShareHolder.icon);
        } else {
            UserInfoModel userInfoModel = (UserInfoModel) JsonParser.parse(stringPref, UserInfoModel.class);
            if (!(userInfoModel == null || userInfoModel.data == null || userInfoModel.data.icon == null)) {
                ImageLoader.showImage(topShareHolder.icon, userInfoModel.data.icon, (RequestOptions) new RequestOptions().a(R.drawable.icon_default_head));
            }
        }
        topShareHolder.icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(FollowingAdapter.this.context, userId);
            }
        });
        topShareHolder.postLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING, Constants.ClickEvent.CLICK_TOP_POST, "");
                PostShortContentActivity.jump(FollowingAdapter.this.context);
            }
        });
    }

    private void bindDefault(DefaultHolder defaultHolder, int i) {
        FollowingFeedModel followingFeedModel = this.followDatas.get(i).getFollowingFeedModel();
        if (!TextUtils.isEmpty(followingFeedModel.Icon)) {
            ImageLoader.showImage(defaultHolder.itemUserIcon, followingFeedModel.Icon);
        } else {
            defaultHolder.itemUserIcon.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.icon_default_head, this.context.getTheme()));
        }
        defaultHolder.itemUserName.setText(followingFeedModel.FollowUsername);
        defaultHolder.itemPostTime.setText(TimeUtils.localDateHMSStr(Long.parseLong(followingFeedModel.Addtime) * 1000));
    }

    private void handleCommonPart(BaseForumHolder baseForumHolder, FollowingFeedModel followingFeedModel, int i) {
        String str = followingFeedModel.Subject;
        if (!TextUtils.isEmpty(followingFeedModel.Icon)) {
            ImageLoader.showImage(baseForumHolder.itemUserIcon, followingFeedModel.Icon);
        } else {
            baseForumHolder.itemUserIcon.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.icon_default_head, this.context.getTheme()));
        }
        baseForumHolder.itemUserName.setText(followingFeedModel.FollowUsername);
        baseForumHolder.itemPostTime.setText(TimeUtils.localDateHMSStr(Long.parseLong(followingFeedModel.Addtime) * 1000));
        if (followingFeedModel.Type != 19 || TextUtils.isEmpty(followingFeedModel.Url)) {
            baseForumHolder.itemTitle.setText(str);
        }
        baseForumHolder.itemUserNameContent.setVisibility(0);
        baseForumHolder.itemUserNameContent.setText(getDescriptionText(i));
        setCommonPartClickListener(baseForumHolder, followingFeedModel, str);
    }

    private String getDescriptionText(int i) {
        int normalViewType = getNormalViewType(i);
        if (normalViewType == 4) {
            return this.context.getResources().getString(R.string.following_vote_content);
        }
        if (normalViewType == 12) {
            return this.context.getResources().getString(R.string.following_debate_content);
        }
        if (normalViewType == 14) {
            return this.context.getResources().getString(R.string.checked_in_today, new Object[]{""});
        } else if (normalViewType == 999) {
            return "";
        } else {
            switch (normalViewType) {
                case 7:
                    return this.context.getResources().getString(R.string._followed_column, new Object[]{""});
                case 8:
                    return this.context.getResources().getString(R.string.following_str_subscribed);
                default:
                    switch (normalViewType) {
                        case 16:
                            return this.context.getResources().getString(R.string._started_a_activity, new Object[]{""});
                        case 17:
                            return this.context.getResources().getString(R.string._joined_a_activity, new Object[]{""});
                        case 18:
                            return this.context.getResources().getString(R.string.birthday_content);
                        case 19:
                            return "";
                        default:
                            switch (normalViewType) {
                                case 190:
                                case 191:
                                case 192:
                                case 193:
                                    return "";
                                default:
                                    return this.context.getResources().getString(R.string._post_a_thread, new Object[]{""});
                            }
                    }
            }
        }
    }

    private void setCommonPartClickListener(BaseForumHolder baseForumHolder, final FollowingFeedModel followingFeedModel, String str) {
        boolean z = true;
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{followingFeedModel.EventId}));
        baseForumHolder.likeCommentView.resetView();
        baseForumHolder.likeCommentView.setLikeAnimationShow(false);
        LikeAndCommentView likeAndCommentView = baseForumHolder.likeCommentView;
        if (followingFeedModel.ExtraData.LikeStatus != 1) {
            z = false;
        }
        likeAndCommentView.setLikeAndComment(z, followingFeedModel.ExtraData.Like, Integer.valueOf(followingFeedModel.ExtraData.Replies).intValue());
        baseForumHolder.likeCommentView.setCommentClickListener(new OnCommentClickListener() {
            public void onCommentClick() {
                if (14 == followingFeedModel.Type) {
                    SignDetailActivity.jump(FollowingAdapter.this.context, followingFeedModel.EventId, true);
                } else if (followingFeedModel.Type == 19 || followingFeedModel.Type == 18) {
                    ShortContentDetailActivity.jump(FollowingAdapter.this.context, followingFeedModel.EventId, true);
                } else {
                    CommentsActivity.jump(FollowingAdapter.this.context, String.valueOf(followingFeedModel.ExtraData.Fid), String.valueOf(followingFeedModel.EventId), true);
                }
                if (followingFeedModel.Type == 14) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_COMMENT, "click_comment_0");
                } else if (followingFeedModel.Type == 8) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_COMMENT, "click_comment_2");
                } else {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_COMMENT, "click_comment_1");
                }
            }
        });
        baseForumHolder.likeCommentView.setOnPriaseClickListener(new OnPraiseListener() {
            public void onNotLogin() {
                FollowingAdapter.this.context.gotoAccount();
            }

            public void praised() {
                followingFeedModel.ExtraData.LikeStatus = 1;
                followingFeedModel.ExtraData.Like++;
                if (followingFeedModel.Type == 14) {
                    FollowingAdapter.this.signThumbUp(followingFeedModel, 0);
                } else if (followingFeedModel.Type == 19 || followingFeedModel.Type == 18) {
                    FollowingAdapter.this.shortContentThumbUp(followingFeedModel, 0, 1);
                } else {
                    ApiClient.thumbUp(followingFeedModel.EventId + "", LoginManager.getInstance().getUserId());
                }
                if (followingFeedModel.Type == 14) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_0");
                } else if (followingFeedModel.Type == 8) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_2");
                } else {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_1");
                }
            }

            public void unpraised() {
                followingFeedModel.ExtraData.LikeStatus = 0;
                if (followingFeedModel.ExtraData.Like > 0) {
                    followingFeedModel.ExtraData.Like--;
                }
                if (followingFeedModel.Type == 14) {
                    FollowingAdapter.this.signThumbUp(followingFeedModel, 0);
                } else if (followingFeedModel.Type == 19 || followingFeedModel.Type == 18) {
                    FollowingAdapter.this.shortContentThumbUp(followingFeedModel, 0, 1);
                } else {
                    ApiClient.thumbUp(followingFeedModel.EventId + "", LoginManager.getInstance().getUserId());
                }
                if (followingFeedModel.Type == 14) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_0");
                } else if (followingFeedModel.Type == 8) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_2");
                } else {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_LIKE, "click_like_1");
                }
            }
        });
        baseForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (14 == followingFeedModel.Type) {
                    SignDetailActivity.jump(FollowingAdapter.this.context, followingFeedModel.EventId);
                } else if (8 == followingFeedModel.Type) {
                    ColumnDetailActivity.jumpWithId(FollowingAdapter.this.context, followingFeedModel.EventId);
                } else if (7 == followingFeedModel.Type) {
                    UserCenterActivity.jump(FollowingAdapter.this.context, followingFeedModel.EventId);
                } else if (followingFeedModel.Type == 17 || followingFeedModel.Type == 16) {
                    WebActivity.jump(FollowingAdapter.this.context, String.format("%sthread-%s-1-0.html", new Object[]{ConnectionHelper.getAppIndexUrl(), followingFeedModel.EventId}));
                } else if (19 == followingFeedModel.Type || 18 == followingFeedModel.Type) {
                    ShortContentDetailActivity.jump(FollowingAdapter.this.context, followingFeedModel.EventId);
                } else {
                    WebActivity.jump(FollowingAdapter.this.context, appUrl);
                }
                if (followingFeedModel.Type == 14) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_FEED, "click_feed_0");
                } else if (followingFeedModel.Type == 8) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_FEED, "click_feed_2");
                } else {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, Constants.ClickEvent.CLICK_FEED, "click_feed_1");
                }
            }
        });
        baseForumHolder.itemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(FollowingAdapter.this.context, followingFeedModel.FollowUid);
            }
        });
    }

    /* access modifiers changed from: private */
    public void shortContentThumbUp(FollowingFeedModel followingFeedModel, int i, int i2) {
        ApiClient.getApiService().shortContentThumbUp(String.valueOf(followingFeedModel.EventId), i, i2).compose(this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    /* access modifiers changed from: private */
    public void signThumbUp(FollowingFeedModel followingFeedModel, int i) {
        ApiClient.getApiService().signThumbUp(String.valueOf(followingFeedModel.EventId), i).compose(this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    public class ForumNoPicHolder extends BaseForumHolder {
        public ForumNoPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class BirthdayItemHolder extends BaseForumHolder {
        @BindView(2131493485)
        ImageView itemPic;

        public BirthdayItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class TopShareHolder extends RecyclerView.ViewHolder {
        @BindView(2131493285)
        ImageView icon;
        @BindView(2131493286)
        ImageView post;
        @BindView(2131493819)
        RelativeLayout postLayout;

        public TopShareHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class DefaultHolder extends RecyclerView.ViewHolder {
        @BindView(2131493507)
        TextView itemPostTime;
        @BindView(2131493515)
        ImageView itemUserIcon;
        @BindView(2131493516)
        TextView itemUserName;

        public DefaultHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class LinkItemHolder extends BaseForumHolder {
        @BindView(2131493502)
        ImageView itemPic;

        public LinkItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumOnePicHolder extends BaseForumHolder {
        @BindView(2131493498)
        TextView itemInfo;
        @BindView(2131493502)
        ImageView itemPic;

        public ForumOnePicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class FollowingSubscribeHolder extends BaseForumHolder {
        @BindView(2131493502)
        ImageView followingItemPic;
        @BindView(2131493487)
        TextView forumItemContent;

        public FollowingSubscribeHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class FollowingFollowOtherHolder extends BaseForumHolder {
        @BindView(2131493502)
        ImageView followingItemPic;
        @BindView(2131493271)
        TextView mFollowerItemFollowerCount;
        @BindView(2131493276)
        TextView mFollowerItemThreadCount;

        public FollowingFollowOtherHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumTwoPicHolder extends BaseForumHolder {
        @BindView(2131493503)
        ImageView itemPic1;
        @BindView(2131493504)
        ImageView itemPic2;

        public ForumTwoPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumBigPicHolder extends BaseForumHolder {
        @BindView(2131493485)
        ImageView forumItemBigPic;

        public ForumBigPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumSignHolder extends BaseForumHolder {
        @BindView(2131493997)
        ImageView mSignedFeelIv;

        public ForumSignHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumMultiPicHolder extends BaseForumHolder {
        @BindView(2131493503)
        ImageView itemPic1;
        @BindView(2131493504)
        ImageView itemPic2;
        @BindView(2131493505)
        ImageView itemPic3;

        public ForumMultiPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class BaseForumHolder extends RecyclerView.ViewHolder {
        @BindView(2131493507)
        TextView itemPostTime;
        @BindView(2131493512)
        TextView itemTitle;
        @BindView(2131493515)
        ImageView itemUserIcon;
        @BindView(2131493516)
        TextView itemUserName;
        @BindView(2131493508)
        TextView itemUserNameContent;
        @BindView(2131494247)
        LikeAndCommentView likeCommentView;

        public BaseForumHolder(View view) {
            super(view);
        }
    }

    public class FollowingCommendHolder extends RecyclerView.ViewHolder {
        FollowingRecommendItemAdapter itemAdapter;
        @BindView(2131493440)
        RecyclerView recommendRecycleView;
        @BindView(2131494133)
        TextView recommend_more;

        public FollowingCommendHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            ViewGroup.LayoutParams layoutParams = this.recommendRecycleView.getLayoutParams();
            layoutParams.height = Coder.a((Activity) FollowingAdapter.this.context, 200.0f);
            this.recommendRecycleView.setLayoutParams(layoutParams);
            this.recommendRecycleView.setLayoutDirection(3);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FollowingAdapter.this.context);
            linearLayoutManager.setOrientation(0);
            this.recommendRecycleView.setLayoutManager(linearLayoutManager);
            this.itemAdapter = new FollowingRecommendItemAdapter(FollowingAdapter.this.context);
            this.itemAdapter.setOnFollowListener(FollowingAdapter.this.followingFollowListener);
            this.recommendRecycleView.setAdapter(this.itemAdapter);
            this.recommend_more.setOnClickListener(new View.OnClickListener(FollowingAdapter.this) {
                public void onClick(View view) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FOLLOWING_FEED, "click_more", "click_more");
                    DiscoverPeopleActivity.show(FollowingAdapter.this.context);
                }
            });
        }
    }

    public class FollowingEmptyHolder extends RecyclerView.ViewHolder {
        public FollowingEmptyHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
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

    public class ActivityItemHolder extends BaseForumHolder {
        @BindView(2131492945)
        LinearLayout activityLayout;
        @BindView(2131493688)
        TextView followingActivityAddress;
        @BindView(2131493690)
        TextView followingActivityDateLine;
        @BindView(2131493687)
        ImageView followingActivityPic;

        public ActivityItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public void setOnFollowingFollowListener(OnFollowingFollowListener onFollowingFollowListener) {
        this.followingFollowListener = onFollowingFollowListener;
    }
}
