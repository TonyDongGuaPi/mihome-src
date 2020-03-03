package com.mi.global.bbs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.LinkModel;
import com.mi.global.bbs.model.UserActivitiesModel;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.checkin.SignDetailActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.Editor.FontTextView;
import java.util.HashMap;
import java.util.List;

public class UserActivitiesAdapter extends BaseLoadMoreAdapter {
    private static final int birthday = 18;
    private static final int checkIn = 14;
    private static final int doPoll = 11;
    private static final int favThread = 5;
    private static final int followColumn = 8;
    private static final int followForum = 6;
    private static final int followUser = 7;
    private static final int getMedal = 9;
    private static final int groupUpgrade = 10;
    private static final int joinActivity = 17;
    private static final int postColumn = 13;
    private static final int postDebate = 12;
    private static final int postPoll = 4;
    private static final int postThread = 2;
    private static final int register = 1;
    private static final int reply = 3;
    private static final int shortContent = 19;
    private static final int startActivity = 16;
    private List<UserActivitiesModel.UserActivity> items;
    /* access modifiers changed from: private */
    public BaseActivity mContext;
    /* access modifiers changed from: private */
    public HashMap<String, LinkModel> mLinkMap = new HashMap<>();
    private String userName;

    public class CommonHolder_ViewBinding implements Unbinder {
        private CommonHolder target;

        @UiThread
        public CommonHolder_ViewBinding(CommonHolder commonHolder, View view) {
            this.target = commonHolder;
            commonHolder.mMyActivitiesCommonName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_common_name, "field 'mMyActivitiesCommonName'", TextView.class);
            commonHolder.mMyActivitiesCommonDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_common_date, "field 'mMyActivitiesCommonDate'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            CommonHolder commonHolder = this.target;
            if (commonHolder != null) {
                this.target = null;
                commonHolder.mMyActivitiesCommonName = null;
                commonHolder.mMyActivitiesCommonDate = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class DebateHolder_ViewBinding implements Unbinder {
        private DebateHolder target;

        @UiThread
        public DebateHolder_ViewBinding(DebateHolder debateHolder, View view) {
            this.target = debateHolder;
            debateHolder.mMyActivitiesDebateName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_debate_name, "field 'mMyActivitiesDebateName'", TextView.class);
            debateHolder.mMyActivitiesDebateDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_debate_date, "field 'mMyActivitiesDebateDate'", TextView.class);
            debateHolder.mMyActivitiesDebateItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_debate_item_title, "field 'mMyActivitiesDebateItemTitle'", TextView.class);
            debateHolder.mMyActivitiesDebateText1 = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_debate_text1, "field 'mMyActivitiesDebateText1'", TextView.class);
            debateHolder.mMyActivitiesDebateText2 = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_debate_text2, "field 'mMyActivitiesDebateText2'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            DebateHolder debateHolder = this.target;
            if (debateHolder != null) {
                this.target = null;
                debateHolder.mMyActivitiesDebateName = null;
                debateHolder.mMyActivitiesDebateDate = null;
                debateHolder.mMyActivitiesDebateItemTitle = null;
                debateHolder.mMyActivitiesDebateText1 = null;
                debateHolder.mMyActivitiesDebateText2 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FavorHolder_ViewBinding implements Unbinder {
        private FavorHolder target;

        @UiThread
        public FavorHolder_ViewBinding(FavorHolder favorHolder, View view) {
            this.target = favorHolder;
            favorHolder.myActivitiesFavoredName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_favored_name, "field 'myActivitiesFavoredName'", TextView.class);
            favorHolder.myActivitiesFavoredDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_favored_date, "field 'myActivitiesFavoredDate'", TextView.class);
            favorHolder.myActivitiesReplyThreadsTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_reply_threads_title, "field 'myActivitiesReplyThreadsTitle'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FavorHolder favorHolder = this.target;
            if (favorHolder != null) {
                this.target = null;
                favorHolder.myActivitiesFavoredName = null;
                favorHolder.myActivitiesFavoredDate = null;
                favorHolder.myActivitiesReplyThreadsTitle = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class RegisterHolder_ViewBinding implements Unbinder {
        private RegisterHolder target;

        @UiThread
        public RegisterHolder_ViewBinding(RegisterHolder registerHolder, View view) {
            this.target = registerHolder;
            registerHolder.myActivitiesRegisterName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_register_name, "field 'myActivitiesRegisterName'", TextView.class);
            registerHolder.myActivitiesRegisterDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_register_date, "field 'myActivitiesRegisterDate'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            RegisterHolder registerHolder = this.target;
            if (registerHolder != null) {
                this.target = null;
                registerHolder.myActivitiesRegisterName = null;
                registerHolder.myActivitiesRegisterDate = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ReplyHolder_ViewBinding implements Unbinder {
        private ReplyHolder target;

        @UiThread
        public ReplyHolder_ViewBinding(ReplyHolder replyHolder, View view) {
            this.target = replyHolder;
            replyHolder.myActivitiesReplyName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_reply_name, "field 'myActivitiesReplyName'", TextView.class);
            replyHolder.myActivitiesReplyDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_reply_date, "field 'myActivitiesReplyDate'", TextView.class);
            replyHolder.myActivitiesReplyContent = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_reply_content, "field 'myActivitiesReplyContent'", TextView.class);
            replyHolder.myActivitiesReplyThreadsTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_reply_threads_title, "field 'myActivitiesReplyThreadsTitle'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ReplyHolder replyHolder = this.target;
            if (replyHolder != null) {
                this.target = null;
                replyHolder.myActivitiesReplyName = null;
                replyHolder.myActivitiesReplyDate = null;
                replyHolder.myActivitiesReplyContent = null;
                replyHolder.myActivitiesReplyThreadsTitle = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class BirthDayHolder_ViewBinding implements Unbinder {
        private BirthDayHolder target;

        @UiThread
        public BirthDayHolder_ViewBinding(BirthDayHolder birthDayHolder, View view) {
            this.target = birthDayHolder;
            birthDayHolder.mMyActivitiesPostName = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_name, "field 'mMyActivitiesPostName'", FontTextView.class);
            birthDayHolder.mMyActivitiesPostDate = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_date, "field 'mMyActivitiesPostDate'", FontTextView.class);
            birthDayHolder.mMyActivitiesBirthdayImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_birthday_image, "field 'mMyActivitiesBirthdayImage'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            BirthDayHolder birthDayHolder = this.target;
            if (birthDayHolder != null) {
                this.target = null;
                birthDayHolder.mMyActivitiesPostName = null;
                birthDayHolder.mMyActivitiesPostDate = null;
                birthDayHolder.mMyActivitiesBirthdayImage = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class CheckInHolder_ViewBinding implements Unbinder {
        private CheckInHolder target;

        @UiThread
        public CheckInHolder_ViewBinding(CheckInHolder checkInHolder, View view) {
            this.target = checkInHolder;
            checkInHolder.mMyActivitiesSignName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_sign_name, "field 'mMyActivitiesSignName'", TextView.class);
            checkInHolder.mMyActivitiesSignDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_sign_date, "field 'mMyActivitiesSignDate'", TextView.class);
            checkInHolder.mMyActivitiesSignContent = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_sign_content, "field 'mMyActivitiesSignContent'", TextView.class);
            checkInHolder.mMyActivitiesSignIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_sign_iv, "field 'mMyActivitiesSignIv'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            CheckInHolder checkInHolder = this.target;
            if (checkInHolder != null) {
                this.target = null;
                checkInHolder.mMyActivitiesSignName = null;
                checkInHolder.mMyActivitiesSignDate = null;
                checkInHolder.mMyActivitiesSignContent = null;
                checkInHolder.mMyActivitiesSignIv = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ComActivityHolder_ViewBinding implements Unbinder {
        private ComActivityHolder target;

        @UiThread
        public ComActivityHolder_ViewBinding(ComActivityHolder comActivityHolder, View view) {
            this.target = comActivityHolder;
            comActivityHolder.mMyActivitiesPostName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_name, "field 'mMyActivitiesPostName'", TextView.class);
            comActivityHolder.mMyActivitiesPostDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_date, "field 'mMyActivitiesPostDate'", TextView.class);
            comActivityHolder.mMyActivitiesPostTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_title, "field 'mMyActivitiesPostTitle'", TextView.class);
            comActivityHolder.mMyActivitiesPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_time, "field 'mMyActivitiesPostTime'", TextView.class);
            comActivityHolder.mMyActivitiesPostLocation = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_location, "field 'mMyActivitiesPostLocation'", TextView.class);
            comActivityHolder.mMyActivitiesPostImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_img, "field 'mMyActivitiesPostImg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ComActivityHolder comActivityHolder = this.target;
            if (comActivityHolder != null) {
                this.target = null;
                comActivityHolder.mMyActivitiesPostName = null;
                comActivityHolder.mMyActivitiesPostDate = null;
                comActivityHolder.mMyActivitiesPostTitle = null;
                comActivityHolder.mMyActivitiesPostTime = null;
                comActivityHolder.mMyActivitiesPostLocation = null;
                comActivityHolder.mMyActivitiesPostImg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class DoPollHolder_ViewBinding implements Unbinder {
        private DoPollHolder target;

        @UiThread
        public DoPollHolder_ViewBinding(DoPollHolder doPollHolder, View view) {
            this.target = doPollHolder;
            doPollHolder.myActivitiesPollName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_name, "field 'myActivitiesPollName'", TextView.class);
            doPollHolder.myActivitiesPollDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_date, "field 'myActivitiesPollDate'", TextView.class);
            doPollHolder.myActivitiesPollTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_title, "field 'myActivitiesPollTitle'", TextView.class);
            doPollHolder.myActivitiesPollImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_img, "field 'myActivitiesPollImg'", ImageView.class);
            doPollHolder.myActivitiesPollNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_number, "field 'myActivitiesPollNumber'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            DoPollHolder doPollHolder = this.target;
            if (doPollHolder != null) {
                this.target = null;
                doPollHolder.myActivitiesPollName = null;
                doPollHolder.myActivitiesPollDate = null;
                doPollHolder.myActivitiesPollTitle = null;
                doPollHolder.myActivitiesPollImg = null;
                doPollHolder.myActivitiesPollNumber = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FollowColumnHolder_ViewBinding implements Unbinder {
        private FollowColumnHolder target;

        @UiThread
        public FollowColumnHolder_ViewBinding(FollowColumnHolder followColumnHolder, View view) {
            this.target = followColumnHolder;
            followColumnHolder.mMyActivitiesFollowColumnUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_follow_column_user_name, "field 'mMyActivitiesFollowColumnUserName'", TextView.class);
            followColumnHolder.mMyActivitiesFollowColumnDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_follow_column_date, "field 'mMyActivitiesFollowColumnDate'", TextView.class);
            followColumnHolder.mMyActivitiesFollowedImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_img, "field 'mMyActivitiesFollowedImg'", ImageView.class);
            followColumnHolder.mMyActivitiesFollowColumnName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_follow_column_name, "field 'mMyActivitiesFollowColumnName'", TextView.class);
            followColumnHolder.mMyActivitiesFollowColumnFollowCount = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_follow_column_followCount, "field 'mMyActivitiesFollowColumnFollowCount'", TextView.class);
            followColumnHolder.mMyActivitiesFollowColumnDes = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_follow_column_des, "field 'mMyActivitiesFollowColumnDes'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FollowColumnHolder followColumnHolder = this.target;
            if (followColumnHolder != null) {
                this.target = null;
                followColumnHolder.mMyActivitiesFollowColumnUserName = null;
                followColumnHolder.mMyActivitiesFollowColumnDate = null;
                followColumnHolder.mMyActivitiesFollowedImg = null;
                followColumnHolder.mMyActivitiesFollowColumnName = null;
                followColumnHolder.mMyActivitiesFollowColumnFollowCount = null;
                followColumnHolder.mMyActivitiesFollowColumnDes = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class FollowedHolder_ViewBinding implements Unbinder {
        private FollowedHolder target;

        @UiThread
        public FollowedHolder_ViewBinding(FollowedHolder followedHolder, View view) {
            this.target = followedHolder;
            followedHolder.myActivitiesFollowedName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_name, "field 'myActivitiesFollowedName'", TextView.class);
            followedHolder.myActivitiesFollowedDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_date, "field 'myActivitiesFollowedDate'", TextView.class);
            followedHolder.myActivitiesFollowedImg = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_img, "field 'myActivitiesFollowedImg'", CircleImageView.class);
            followedHolder.myActivitiesFollowedFollower = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_follower, "field 'myActivitiesFollowedFollower'", TextView.class);
            followedHolder.myActivitiesFollowedThread = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_thread, "field 'myActivitiesFollowedThread'", TextView.class);
            followedHolder.myActivitiesFollowedReply = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_reply, "field 'myActivitiesFollowedReply'", TextView.class);
            followedHolder.myActivitiesFollowedFollowerName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_followed_follower_name, "field 'myActivitiesFollowedFollowerName'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            FollowedHolder followedHolder = this.target;
            if (followedHolder != null) {
                this.target = null;
                followedHolder.myActivitiesFollowedName = null;
                followedHolder.myActivitiesFollowedDate = null;
                followedHolder.myActivitiesFollowedImg = null;
                followedHolder.myActivitiesFollowedFollower = null;
                followedHolder.myActivitiesFollowedThread = null;
                followedHolder.myActivitiesFollowedReply = null;
                followedHolder.myActivitiesFollowedFollowerName = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PollHolder_ViewBinding implements Unbinder {
        private PollHolder target;

        @UiThread
        public PollHolder_ViewBinding(PollHolder pollHolder, View view) {
            this.target = pollHolder;
            pollHolder.myActivitiesPollName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_name, "field 'myActivitiesPollName'", TextView.class);
            pollHolder.myActivitiesPollDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_date, "field 'myActivitiesPollDate'", TextView.class);
            pollHolder.myActivitiesPollTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_title, "field 'myActivitiesPollTitle'", TextView.class);
            pollHolder.myActivitiesPollImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_img, "field 'myActivitiesPollImg'", ImageView.class);
            pollHolder.myActivitiesPollNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_poll_number, "field 'myActivitiesPollNumber'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            PollHolder pollHolder = this.target;
            if (pollHolder != null) {
                this.target = null;
                pollHolder.myActivitiesPollName = null;
                pollHolder.myActivitiesPollDate = null;
                pollHolder.myActivitiesPollTitle = null;
                pollHolder.myActivitiesPollImg = null;
                pollHolder.myActivitiesPollNumber = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostColumnHolder_ViewBinding implements Unbinder {
        private PostColumnHolder target;

        @UiThread
        public PostColumnHolder_ViewBinding(PostColumnHolder postColumnHolder, View view) {
            this.target = postColumnHolder;
            postColumnHolder.mMyActivitiesPostColumnUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_column_user_name, "field 'mMyActivitiesPostColumnUserName'", TextView.class);
            postColumnHolder.mMyActivitiesPostColumnDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_column_date, "field 'mMyActivitiesPostColumnDate'", TextView.class);
            postColumnHolder.mMyActivitiesPostColumnImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_column_img, "field 'mMyActivitiesPostColumnImg'", ImageView.class);
            postColumnHolder.mMyActivitiesPostColumnName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_column_name, "field 'mMyActivitiesPostColumnName'", TextView.class);
            postColumnHolder.mMyActivitiesPostColumnFollowCount = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_column_followCount, "field 'mMyActivitiesPostColumnFollowCount'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            PostColumnHolder postColumnHolder = this.target;
            if (postColumnHolder != null) {
                this.target = null;
                postColumnHolder.mMyActivitiesPostColumnUserName = null;
                postColumnHolder.mMyActivitiesPostColumnDate = null;
                postColumnHolder.mMyActivitiesPostColumnImg = null;
                postColumnHolder.mMyActivitiesPostColumnName = null;
                postColumnHolder.mMyActivitiesPostColumnFollowCount = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostHolder_ViewBinding implements Unbinder {
        private PostHolder target;

        @UiThread
        public PostHolder_ViewBinding(PostHolder postHolder, View view) {
            this.target = postHolder;
            postHolder.myActivitiesPostName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_name, "field 'myActivitiesPostName'", TextView.class);
            postHolder.myActivitiesPostDate = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_date, "field 'myActivitiesPostDate'", TextView.class);
            postHolder.myActivitiesPostTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_title, "field 'myActivitiesPostTitle'", TextView.class);
            postHolder.myActivitiesPostContent = (TextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_content, "field 'myActivitiesPostContent'", TextView.class);
            postHolder.myActivitiesPostImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_img, "field 'myActivitiesPostImg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PostHolder postHolder = this.target;
            if (postHolder != null) {
                this.target = null;
                postHolder.myActivitiesPostName = null;
                postHolder.myActivitiesPostDate = null;
                postHolder.myActivitiesPostTitle = null;
                postHolder.myActivitiesPostContent = null;
                postHolder.myActivitiesPostImg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ShortContentHolder_ViewBinding implements Unbinder {
        private ShortContentHolder target;

        @UiThread
        public ShortContentHolder_ViewBinding(ShortContentHolder shortContentHolder, View view) {
            this.target = shortContentHolder;
            shortContentHolder.mMyActivitiesPostName = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_name, "field 'mMyActivitiesPostName'", FontTextView.class);
            shortContentHolder.mMyActivitiesPostDate = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_activities_post_date, "field 'mMyActivitiesPostDate'", FontTextView.class);
            shortContentHolder.mMyActivitiesShortContent = (FontTextView) Utils.findRequiredViewAsType(view, R.id.my_activities_short_content, "field 'mMyActivitiesShortContent'", FontTextView.class);
            shortContentHolder.mMyActivitiesContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.my_activities_container, "field 'mMyActivitiesContainer'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ShortContentHolder shortContentHolder = this.target;
            if (shortContentHolder != null) {
                this.target = null;
                shortContentHolder.mMyActivitiesPostName = null;
                shortContentHolder.mMyActivitiesPostDate = null;
                shortContentHolder.mMyActivitiesShortContent = null;
                shortContentHolder.mMyActivitiesContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public UserActivitiesAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading, List<UserActivitiesModel.UserActivity> list, String str) {
        super(baseActivity, dataLoading);
        this.mContext = baseActivity;
        this.items = list;
        this.userName = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case -1:
                return super.onCreateViewHolder(viewGroup, i);
            case 1:
                return new RegisterHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_register_item, viewGroup, false));
            case 2:
                return new PostHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_thread_item, viewGroup, false));
            case 3:
                return new ReplyHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_reply_item, viewGroup, false));
            case 4:
                return new PollHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_poll_item, viewGroup, false));
            case 5:
                return new FavorHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_favorited_item, viewGroup, false));
            case 7:
                return new FollowedHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_followed_item, viewGroup, false));
            case 8:
                return new FollowColumnHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_follow_column_item, viewGroup, false));
            case 11:
                return new DoPollHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_poll_item, viewGroup, false));
            case 12:
                return new DebateHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_debate_item, viewGroup, false));
            case 13:
                return new PostColumnHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_post_column_item, viewGroup, false));
            case 14:
                return new CheckInHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_checkin_item, viewGroup, false));
            case 16:
            case 17:
                return new ComActivityHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_activity_item, viewGroup, false));
            case 18:
                return new BirthDayHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_birthday_item, viewGroup, false));
            case 19:
                return new ShortContentHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_short_content_item, viewGroup, false));
            default:
                return new CommonHolder(this.layoutInflater.inflate(R.layout.bbs_my_activitys_type_common_item, viewGroup, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case -1:
                super.onBindViewHolder(viewHolder, i);
                return;
            case 1:
                bindRegisterViewHolder((RegisterHolder) viewHolder, i);
                return;
            case 2:
                bindPostThreadViewHolder((PostHolder) viewHolder, i);
                return;
            case 3:
                BindReplyHolder((ReplyHolder) viewHolder, i);
                return;
            case 4:
                bindPollViewHolder((PollHolder) viewHolder, i);
                return;
            case 5:
                bindFavorViewHolder((FavorHolder) viewHolder, i);
                return;
            case 7:
                bindFollowedHolder((FollowedHolder) viewHolder, i);
                return;
            case 8:
                bindFollowedColumnHolder((FollowColumnHolder) viewHolder, i);
                return;
            case 11:
                bindDoPollHolder((DoPollHolder) viewHolder, i);
                return;
            case 12:
                bindPostDebateHolder((DebateHolder) viewHolder, i);
                return;
            case 13:
                bindPostColumnHolder((PostColumnHolder) viewHolder, i);
                return;
            case 14:
                bindCheckInHolder((CheckInHolder) viewHolder, i);
                return;
            case 16:
            case 17:
                bindComActivityHolder((ComActivityHolder) viewHolder, getItemViewType(i), i);
                return;
            case 18:
                bindBirthDayHolder((BirthDayHolder) viewHolder, i);
                return;
            case 19:
                bindShowContentHolder((ShortContentHolder) viewHolder, i);
                return;
            default:
                bindCommonHolder((CommonHolder) viewHolder, this.items.get(i));
                return;
        }
    }

    private void bindShowContentHolder(ShortContentHolder shortContentHolder, int i) {
        final UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        shortContentHolder.mMyActivitiesPostDate.setText(userActivity.addtime);
        shortContentHolder.mMyActivitiesPostName.setText(this.userName);
        if (TextUtils.isEmpty(userActivity.subject)) {
            shortContentHolder.mMyActivitiesShortContent.setVisibility(8);
        } else {
            shortContentHolder.mMyActivitiesShortContent.setVisibility(0);
            shortContentHolder.mMyActivitiesShortContent.setText(userActivity.subject);
        }
        shortContentHolder.mMyActivitiesContainer.removeAllViews();
        if (!TextUtils.isEmpty(userActivity.message)) {
            addLinkView(shortContentHolder, userActivity);
        }
        if (userActivity.urls != null && userActivity.urls.size() > 0) {
            handleMultiImageItem(shortContentHolder, userActivity);
        }
        shortContentHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShortContentDetailActivity.jump(UserActivitiesAdapter.this.mContext, userActivity.event_id);
            }
        });
    }

    private void handleMultiImageItem(ShortContentHolder shortContentHolder, final UserActivitiesModel.UserActivity userActivity) {
        int size = userActivity.urls.size();
        switch (size) {
            case 1:
                ImageView imageView = new ImageView(this.mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.mContext.getResources().getDimensionPixelOffset(R.dimen.short_content_image_height1));
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        WatchBigPicActivity.jump(UserActivitiesAdapter.this.mContext, userActivity.urls.get(0), (String[]) userActivity.urls.toArray(new String[0]));
                    }
                });
                RequestOptions defaultOptions = ImageLoader.getDefaultOptions();
                defaultOptions.k();
                ImageLoader.showImage(imageView, userActivity.urls.get(0), defaultOptions);
                shortContentHolder.mMyActivitiesContainer.addView(imageView, layoutParams);
                return;
            case 2:
                addMultiImageView(shortContentHolder, userActivity, size, this.mContext.getResources().getDimensionPixelOffset(R.dimen.short_content_image_height2));
                return;
            default:
                addMultiImageView(shortContentHolder, userActivity, size, this.mContext.getResources().getDimensionPixelOffset(R.dimen.short_content_image_height3));
                return;
        }
    }

    private void addMultiImageView(ShortContentHolder shortContentHolder, final UserActivitiesModel.UserActivity userActivity, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            ImageView imageView = new ImageView(this.mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, i2, 1.0f);
            if (i3 != i - 1) {
                layoutParams.setMarginEnd(this.mContext.getResources().getDimensionPixelOffset(R.dimen.short_content_image_divide));
            }
            final String str = userActivity.urls.get(i3);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WatchBigPicActivity.jump(UserActivitiesAdapter.this.mContext, str, (String[]) userActivity.urls.toArray(new String[0]));
                }
            });
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.showImage(imageView, str);
            shortContentHolder.mMyActivitiesContainer.addView(imageView, layoutParams);
        }
    }

    private void addLinkView(ShortContentHolder shortContentHolder, final UserActivitiesModel.UserActivity userActivity) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.bbs_short_content_link_item, shortContentHolder.mMyActivitiesContainer, false);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.link_img);
        final FontTextView fontTextView = (FontTextView) inflate.findViewById(R.id.link_title);
        LinkModel linkModel = this.mLinkMap.get(userActivity.message);
        if (linkModel != null) {
            showLinkData(linkModel, fontTextView, imageView);
        } else {
            LinkModel.loadByUrl(this.mContext, userActivity.message, new LinkModel.LinkDispatcher() {
                public void onDispatch(LinkModel linkModel) {
                    UserActivitiesAdapter.this.mLinkMap.put(userActivity.message, linkModel);
                    UserActivitiesAdapter.this.showLinkData(linkModel, fontTextView, imageView);
                }
            });
        }
        inflate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jumpDirectly(UserActivitiesAdapter.this.mContext, userActivity.message);
            }
        });
        shortContentHolder.mMyActivitiesContainer.addView(inflate);
    }

    /* access modifiers changed from: private */
    public void showLinkData(LinkModel linkModel, FontTextView fontTextView, ImageView imageView) {
        if (fontTextView != null) {
            fontTextView.setText(linkModel.title);
        }
        if (imageView != null) {
            ((ViewGroup) imageView.getParent()).findViewById(R.id.link_img_cover).setVisibility(TextUtils.isEmpty(linkModel.firstImagePath) ? 8 : 0);
            ImageLoader.showImage(imageView, linkModel.firstImagePath, (RequestOptions) ((RequestOptions) ImageLoader.getDefaultOptions().c(R.drawable.bbs_link_default)).b(R.drawable.bbs_link_default));
        }
    }

    private void bindBirthDayHolder(BirthDayHolder birthDayHolder, int i) {
        final UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        birthDayHolder.mMyActivitiesPostDate.setText(userActivity.addtime);
        birthDayHolder.mMyActivitiesPostName.setText(showNickName(this.mContext.getString(R.string._birthday_tracker, new Object[]{this.userName})));
        ImageLoader.showImage(birthDayHolder.mMyActivitiesBirthdayImage, (userActivity.urls == null || userActivity.urls.size() <= 0) ? null : userActivity.urls.get(0));
        birthDayHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShortContentDetailActivity.jump(UserActivitiesAdapter.this.mContext, userActivity.event_id);
            }
        });
    }

    private void bindComActivityHolder(ComActivityHolder comActivityHolder, int i, int i2) {
        final UserActivitiesModel.UserActivity userActivity = this.items.get(i2);
        comActivityHolder.mMyActivitiesPostDate.setText(userActivity.addtime);
        comActivityHolder.mMyActivitiesPostName.setText(showNickName(this.mContext.getString(i == 16 ? R.string._started_a_activity : R.string._joined_a_activity, new Object[]{this.userName})));
        comActivityHolder.mMyActivitiesPostTitle.setText(userActivity.subject);
        comActivityHolder.mMyActivitiesPostLocation.setText(userActivity.place);
        comActivityHolder.mMyActivitiesPostTime.setText(userActivity.starttimefrom);
        ImageLoader.showImage(comActivityHolder.mMyActivitiesPostImg, userActivity.icon);
        comActivityHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, String.format("%sthread-%s-1-0.html", new Object[]{ConnectionHelper.getAppIndexUrl(), userActivity.event_id}));
            }
        });
    }

    private void bindCheckInHolder(CheckInHolder checkInHolder, int i) {
        final UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        checkInHolder.mMyActivitiesSignName.setText(showNickName(this.mContext.getString(R.string.checked_in_today, new Object[]{this.userName})));
        checkInHolder.mMyActivitiesSignDate.setText(userActivity.addtime);
        checkInHolder.mMyActivitiesSignContent.setText(userActivity.subject);
        ImageLoader.showImage(checkInHolder.mMyActivitiesSignIv, userActivity.icon, (RequestOptions) new RequestOptions().a(R.drawable.bbs_feel_icon));
        checkInHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignDetailActivity.jump(UserActivitiesAdapter.this.mContext, userActivity.event_id);
            }
        });
    }

    private void bindCommonHolder(CommonHolder commonHolder, UserActivitiesModel.UserActivity userActivity) {
        commonHolder.mMyActivitiesCommonDate.setText(userActivity.addtime);
        commonHolder.mMyActivitiesCommonName.setText(showNickName(this.mContext.getString(R.string._had_a_new_activity, new Object[]{this.userName})));
    }

    private void bindPostColumnHolder(PostColumnHolder postColumnHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        postColumnHolder.mMyActivitiesPostColumnDate.setText(userActivity.addtime);
        postColumnHolder.mMyActivitiesPostColumnUserName.setText(showNickName(this.mContext.getString(R.string._add_a_thread_to_column, new Object[]{this.userName})));
        ImageLoader.showImage(postColumnHolder.mMyActivitiesPostColumnImg, userActivity.icon);
        postColumnHolder.mMyActivitiesPostColumnName.setText(userActivity.subject);
    }

    private void bindFollowedColumnHolder(FollowColumnHolder followColumnHolder, int i) {
        final UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        followColumnHolder.mMyActivitiesFollowColumnDate.setText(userActivity.addtime);
        followColumnHolder.mMyActivitiesFollowColumnUserName.setText(showNickName(this.mContext.getString(R.string._followed_column, new Object[]{this.userName})));
        followColumnHolder.mMyActivitiesFollowColumnName.setText(userActivity.subject);
        ImageLoader.showImage(followColumnHolder.mMyActivitiesFollowedImg, userActivity.icon);
        followColumnHolder.mMyActivitiesFollowColumnDes.setText(userActivity.desc);
        followColumnHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ColumnDetailActivity.jumpWithId(UserActivitiesAdapter.this.mContext, userActivity.event_id);
            }
        });
    }

    private void bindPostDebateHolder(DebateHolder debateHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        debateHolder.mMyActivitiesDebateDate.setText(userActivity.addtime);
        debateHolder.mMyActivitiesDebateName.setText(showNickName(this.mContext.getString(R.string._post_a_debate, new Object[]{this.userName})));
        debateHolder.mMyActivitiesDebateItemTitle.setText(userActivity.subject);
        debateHolder.mMyActivitiesDebateText1.setText("0");
        debateHolder.mMyActivitiesDebateText2.setText("0");
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        debateHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private void bindDoPollHolder(DoPollHolder doPollHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        doPollHolder.myActivitiesPollDate.setText(userActivity.addtime);
        doPollHolder.myActivitiesPollName.setText(this.mContext.getString(R.string._voted_for, new Object[]{this.userName}));
        String str = userActivity.icon;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showImage(doPollHolder.myActivitiesPollImg, str);
        }
        doPollHolder.myActivitiesPollNumber.setText(userActivity.voters);
        doPollHolder.myActivitiesPollTitle.setText(userActivity.subject);
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        doPollHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private void BindReplyHolder(ReplyHolder replyHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        replyHolder.myActivitiesReplyName.setText(showNickName(this.mContext.getString(R.string._post_a_reply, new Object[]{this.userName})));
        replyHolder.myActivitiesReplyDate.setText(userActivity.addtime);
        replyHolder.myActivitiesReplyContent.setText(userActivity.message);
        replyHolder.myActivitiesReplyThreadsTitle.setText(userActivity.subject);
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        replyHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private void bindFollowedHolder(FollowedHolder followedHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        followedHolder.myActivitiesFollowedName.setText(showNickName(this.mContext.getString(R.string._followed, new Object[]{this.userName})));
        followedHolder.myActivitiesFollowedDate.setText(userActivity.addtime);
        followedHolder.myActivitiesFollowedFollowerName.setText(userActivity.subject);
        TextHelper.setQuantityString((Context) this.mContext, followedHolder.myActivitiesFollowedFollower, R.plurals._followers, userActivity.followerCount);
        String str = userActivity.icon;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showImage(followedHolder.myActivitiesFollowedImg, str);
        }
        TextHelper.setQuantityString((Context) this.mContext, followedHolder.myActivitiesFollowedReply, R.plurals._replies, userActivity.replies);
        TextHelper.setQuantityString((Context) this.mContext, followedHolder.myActivitiesFollowedThread, R.plurals._threads, userActivity.threads);
        final String str2 = userActivity.event_id;
        followedHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(UserActivitiesAdapter.this.mContext, str2);
            }
        });
    }

    private void bindFavorViewHolder(FavorHolder favorHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        favorHolder.myActivitiesFavoredName.setText(showNickName(this.mContext.getString(R.string._favor_a_thread, new Object[]{this.userName})));
        favorHolder.myActivitiesFavoredDate.setText(userActivity.addtime);
        favorHolder.myActivitiesReplyThreadsTitle.setText(userActivity.subject);
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        favorHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private void bindPollViewHolder(PollHolder pollHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        pollHolder.myActivitiesPollDate.setText(userActivity.addtime);
        pollHolder.myActivitiesPollName.setText(showNickName(this.mContext.getString(R.string._post_a_poll, new Object[]{this.userName})));
        String str = userActivity.icon;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showImage(pollHolder.myActivitiesPollImg, str);
        }
        pollHolder.myActivitiesPollNumber.setText(userActivity.voters);
        pollHolder.myActivitiesPollTitle.setText(userActivity.subject);
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        pollHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private void bindRegisterViewHolder(RegisterHolder registerHolder, int i) {
        registerHolder.myActivitiesRegisterName.setText(showNickName(this.mContext.getString(R.string._registered, new Object[]{this.userName})));
        registerHolder.myActivitiesRegisterDate.setText(this.items.get(i).addtime);
    }

    private void bindPostThreadViewHolder(PostHolder postHolder, int i) {
        UserActivitiesModel.UserActivity userActivity = this.items.get(i);
        postHolder.myActivitiesPostName.setText(showNickName(this.mContext.getString(R.string._post_a_thread, new Object[]{this.userName})));
        postHolder.myActivitiesPostDate.setText(userActivity.addtime);
        postHolder.myActivitiesPostContent.setText(Html.fromHtml(userActivity.message));
        postHolder.myActivitiesPostTitle.setText(userActivity.subject);
        String str = userActivity.icon;
        if (TextUtils.isEmpty(str)) {
            postHolder.myActivitiesPostImg.setVisibility(8);
        } else {
            postHolder.myActivitiesPostImg.setVisibility(0);
            ImageLoader.showImage(postHolder.myActivitiesPostImg, str);
        }
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{userActivity.event_id}));
        postHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebActivity.jump(UserActivitiesAdapter.this.mContext, appUrl);
            }
        });
    }

    private SpannableString showNickName(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new RelativeSizeSpan(1.0952381f), 0, this.userName.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), this.userName.length(), str.length(), 33);
        return spannableString;
    }

    public int getNormalViewType(int i) {
        String str = this.items.get(i).typeid;
        if (TextUtils.isEmpty(str)) {
            return -100;
        }
        return Integer.parseInt(str);
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<UserActivitiesModel.UserActivity> list) {
        if (list != null && list.size() > 0) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    public class RegisterHolder extends RecyclerView.ViewHolder {
        @BindView(2131493692)
        TextView myActivitiesRegisterDate;
        @BindView(2131493693)
        TextView myActivitiesRegisterName;

        public RegisterHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        @BindView(2131493685)
        TextView myActivitiesPostContent;
        @BindView(2131493686)
        TextView myActivitiesPostDate;
        @BindView(2131493687)
        ImageView myActivitiesPostImg;
        @BindView(2131493689)
        TextView myActivitiesPostName;
        @BindView(2131493691)
        TextView myActivitiesPostTitle;

        public PostHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ReplyHolder extends RecyclerView.ViewHolder {
        @BindView(2131493694)
        TextView myActivitiesReplyContent;
        @BindView(2131493695)
        TextView myActivitiesReplyDate;
        @BindView(2131493696)
        TextView myActivitiesReplyName;
        @BindView(2131493697)
        TextView myActivitiesReplyThreadsTitle;

        public ReplyHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class FavorHolder extends RecyclerView.ViewHolder {
        @BindView(2131493661)
        TextView myActivitiesFavoredDate;
        @BindView(2131493662)
        TextView myActivitiesFavoredName;
        @BindView(2131493697)
        TextView myActivitiesReplyThreadsTitle;

        public FavorHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class FollowedHolder extends RecyclerView.ViewHolder {
        @BindView(2131493668)
        TextView myActivitiesFollowedDate;
        @BindView(2131493669)
        TextView myActivitiesFollowedFollower;
        @BindView(2131493670)
        TextView myActivitiesFollowedFollowerName;
        @BindView(2131493671)
        CircleImageView myActivitiesFollowedImg;
        @BindView(2131493672)
        TextView myActivitiesFollowedName;
        @BindView(2131493673)
        TextView myActivitiesFollowedReply;
        @BindView(2131493674)
        TextView myActivitiesFollowedThread;

        public FollowedHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PollHolder extends RecyclerView.ViewHolder {
        @BindView(2131493675)
        TextView myActivitiesPollDate;
        @BindView(2131493676)
        ImageView myActivitiesPollImg;
        @BindView(2131493677)
        TextView myActivitiesPollName;
        @BindView(2131493678)
        TextView myActivitiesPollNumber;
        @BindView(2131493679)
        TextView myActivitiesPollTitle;

        public PollHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class DoPollHolder extends RecyclerView.ViewHolder {
        @BindView(2131493675)
        TextView myActivitiesPollDate;
        @BindView(2131493676)
        ImageView myActivitiesPollImg;
        @BindView(2131493677)
        TextView myActivitiesPollName;
        @BindView(2131493678)
        TextView myActivitiesPollNumber;
        @BindView(2131493679)
        TextView myActivitiesPollTitle;

        public DoPollHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class DebateHolder extends RecyclerView.ViewHolder {
        @BindView(2131493656)
        TextView mMyActivitiesDebateDate;
        @BindView(2131493657)
        TextView mMyActivitiesDebateItemTitle;
        @BindView(2131493658)
        TextView mMyActivitiesDebateName;
        @BindView(2131493659)
        TextView mMyActivitiesDebateText1;
        @BindView(2131493660)
        TextView mMyActivitiesDebateText2;

        public DebateHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class FollowColumnHolder extends RecyclerView.ViewHolder {
        @BindView(2131493663)
        TextView mMyActivitiesFollowColumnDate;
        @BindView(2131493664)
        TextView mMyActivitiesFollowColumnDes;
        @BindView(2131493665)
        TextView mMyActivitiesFollowColumnFollowCount;
        @BindView(2131493666)
        TextView mMyActivitiesFollowColumnName;
        @BindView(2131493667)
        TextView mMyActivitiesFollowColumnUserName;
        @BindView(2131493671)
        ImageView mMyActivitiesFollowedImg;

        public FollowColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostColumnHolder extends RecyclerView.ViewHolder {
        @BindView(2131493680)
        TextView mMyActivitiesPostColumnDate;
        @BindView(2131493681)
        TextView mMyActivitiesPostColumnFollowCount;
        @BindView(2131493682)
        ImageView mMyActivitiesPostColumnImg;
        @BindView(2131493683)
        TextView mMyActivitiesPostColumnName;
        @BindView(2131493684)
        TextView mMyActivitiesPostColumnUserName;

        public PostColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class CommonHolder extends RecyclerView.ViewHolder {
        @BindView(2131493653)
        TextView mMyActivitiesCommonDate;
        @BindView(2131493654)
        TextView mMyActivitiesCommonName;

        public CommonHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class CheckInHolder extends RecyclerView.ViewHolder {
        @BindView(2131493699)
        TextView mMyActivitiesSignContent;
        @BindView(2131493700)
        TextView mMyActivitiesSignDate;
        @BindView(2131493701)
        ImageView mMyActivitiesSignIv;
        @BindView(2131493702)
        TextView mMyActivitiesSignName;

        public CheckInHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ComActivityHolder extends RecyclerView.ViewHolder {
        @BindView(2131493686)
        TextView mMyActivitiesPostDate;
        @BindView(2131493687)
        ImageView mMyActivitiesPostImg;
        @BindView(2131493688)
        TextView mMyActivitiesPostLocation;
        @BindView(2131493689)
        TextView mMyActivitiesPostName;
        @BindView(2131493690)
        TextView mMyActivitiesPostTime;
        @BindView(2131493691)
        TextView mMyActivitiesPostTitle;

        public ComActivityHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class BirthDayHolder extends RecyclerView.ViewHolder {
        @BindView(2131493652)
        ImageView mMyActivitiesBirthdayImage;
        @BindView(2131493686)
        FontTextView mMyActivitiesPostDate;
        @BindView(2131493689)
        FontTextView mMyActivitiesPostName;

        public BirthDayHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ShortContentHolder extends RecyclerView.ViewHolder {
        @BindView(2131493655)
        LinearLayout mMyActivitiesContainer;
        @BindView(2131493686)
        FontTextView mMyActivitiesPostDate;
        @BindView(2131493689)
        FontTextView mMyActivitiesPostName;
        @BindView(2131493698)
        FontTextView mMyActivitiesShortContent;

        public ShortContentHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
