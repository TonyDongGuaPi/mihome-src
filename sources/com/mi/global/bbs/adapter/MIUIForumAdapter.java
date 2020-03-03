package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.model.MIUIForumModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.global.bbs.view.WrapContentGridLayoutManager;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class MIUIForumAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_DEBATE = 4;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_NO_PIC = 1;
    private static final int TYPE_ONE_PIC = 2;
    private static final int TYPE_VOTE = 3;
    /* access modifiers changed from: private */
    public BaseActivity ctx;
    private MIUIContentModel mContentModel;
    private List<MIUIForumModel.DataBean.ListBean> mForumList = new ArrayList();
    /* access modifiers changed from: private */
    public OnShareListener onShareListener;

    public class ForumDebateHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumDebateHolder target;

        @UiThread
        public ForumDebateHolder_ViewBinding(ForumDebateHolder forumDebateHolder, View view) {
            super(forumDebateHolder, view);
            this.target = forumDebateHolder;
            forumDebateHolder.forumItemDebateText1 = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_debate_text1, "field 'forumItemDebateText1'", TextView.class);
            forumDebateHolder.forumItemDebateText2 = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_debate_text2, "field 'forumItemDebateText2'", TextView.class);
        }

        public void unbind() {
            ForumDebateHolder forumDebateHolder = this.target;
            if (forumDebateHolder != null) {
                this.target = null;
                forumDebateHolder.forumItemDebateText1 = null;
                forumDebateHolder.forumItemDebateText2 = null;
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
            forumOnePicHolder.forumItemPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_pic, "field 'forumItemPic'", ImageView.class);
        }

        public void unbind() {
            ForumOnePicHolder forumOnePicHolder = this.target;
            if (forumOnePicHolder != null) {
                this.target = null;
                forumOnePicHolder.forumItemPic = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumVoteHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumVoteHolder target;

        @UiThread
        public ForumVoteHolder_ViewBinding(ForumVoteHolder forumVoteHolder, View view) {
            super(forumVoteHolder, view);
            this.target = forumVoteHolder;
            forumVoteHolder.forumItemPollImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_poll_img, "field 'forumItemPollImg'", ImageView.class);
            forumVoteHolder.forumItemPollNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_poll_number, "field 'forumItemPollNumber'", TextView.class);
        }

        public void unbind() {
            ForumVoteHolder forumVoteHolder = this.target;
            if (forumVoteHolder != null) {
                this.target = null;
                forumVoteHolder.forumItemPollImg = null;
                forumVoteHolder.forumItemPollNumber = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class MIUIHeadHolder_ViewBinding implements Unbinder {
        private MIUIHeadHolder target;

        @UiThread
        public MIUIHeadHolder_ViewBinding(MIUIHeadHolder mIUIHeadHolder, View view) {
            this.target = mIUIHeadHolder;
            mIUIHeadHolder.mMiuiIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.miui_icon, "field 'mMiuiIcon'", ImageView.class);
            mIUIHeadHolder.mSubForumList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.sub_forum_list, "field 'mSubForumList'", RecyclerView.class);
            mIUIHeadHolder.mRecommendList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recommend_list, "field 'mRecommendList'", RecyclerView.class);
            mIUIHeadHolder.mMiuiRecommendedColumnLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.miui_recommended_column_layout, "field 'mMiuiRecommendedColumnLayout'", LinearLayout.class);
            mIUIHeadHolder.mUserList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.user_list, "field 'mUserList'", RecyclerView.class);
            mIUIHeadHolder.mMiuiProfessionalUsersLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.miui_professional_users_layout, "field 'mMiuiProfessionalUsersLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            MIUIHeadHolder mIUIHeadHolder = this.target;
            if (mIUIHeadHolder != null) {
                this.target = null;
                mIUIHeadHolder.mMiuiIcon = null;
                mIUIHeadHolder.mSubForumList = null;
                mIUIHeadHolder.mRecommendList = null;
                mIUIHeadHolder.mMiuiRecommendedColumnLayout = null;
                mIUIHeadHolder.mUserList = null;
                mIUIHeadHolder.mMiuiProfessionalUsersLayout = null;
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
            baseForumHolder.forumItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.forum_item_user_icon, "field 'forumItemUserIcon'", HeadLogoView.class);
            baseForumHolder.forumItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_user_name, "field 'forumItemUserName'", TextView.class);
            baseForumHolder.forumItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_post_time, "field 'forumItemPostTime'", TextView.class);
            baseForumHolder.forumItemType = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_info_post_type, "field 'forumItemType'", TextView.class);
            baseForumHolder.forumItemMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_more, "field 'forumItemMore'", ImageView.class);
            baseForumHolder.forumItemFlagIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_flag_icon, "field 'forumItemFlagIcon'", ImageView.class);
            baseForumHolder.forumItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_title, "field 'forumItemTitle'", TextView.class);
            baseForumHolder.forumItemActionShare = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_share, "field 'forumItemActionShare'", FrameLayout.class);
            baseForumHolder.forumItemActionCommentText = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment_text, "field 'forumItemActionCommentText'", TextView.class);
            baseForumHolder.forumItemActionComment = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment, "field 'forumItemActionComment'", FrameLayout.class);
            baseForumHolder.forumItemActionLikeText = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like_text, "field 'forumItemActionLikeText'", CheckedTextView.class);
            baseForumHolder.forumItemActionLike = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like, "field 'forumItemActionLike'", FrameLayout.class);
        }

        @CallSuper
        public void unbind() {
            BaseForumHolder baseForumHolder = this.target;
            if (baseForumHolder != null) {
                this.target = null;
                baseForumHolder.forumItemUserIcon = null;
                baseForumHolder.forumItemUserName = null;
                baseForumHolder.forumItemPostTime = null;
                baseForumHolder.forumItemType = null;
                baseForumHolder.forumItemMore = null;
                baseForumHolder.forumItemFlagIcon = null;
                baseForumHolder.forumItemTitle = null;
                baseForumHolder.forumItemActionShare = null;
                baseForumHolder.forumItemActionCommentText = null;
                baseForumHolder.forumItemActionComment = null;
                baseForumHolder.forumItemActionLikeText = null;
                baseForumHolder.forumItemActionLike = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public MIUIForumAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.ctx = baseActivity;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new MIUIHeadHolder(this.layoutInflater.inflate(R.layout.bbs_miui_list_top_item, viewGroup, false));
            case 1:
                return new ForumNoPicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_no_pic, viewGroup, false));
            case 2:
                return new ForumOnePicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_one_pic, viewGroup, false));
            case 3:
                return new ForumVoteHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_vote, viewGroup, false));
            case 4:
                return new ForumDebateHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_debate, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = i - 1;
        switch (getItemViewType(i)) {
            case 0:
                bindHeadHolder((MIUIHeadHolder) viewHolder);
                return;
            case 1:
                bindNoPicHolder((ForumNoPicHolder) viewHolder, i2);
                return;
            case 2:
                bindOnePicHolder((ForumOnePicHolder) viewHolder, i2);
                return;
            case 3:
                bindVoteHolder((ForumVoteHolder) viewHolder, i2);
                return;
            case 4:
                bindDebateHolder((ForumDebateHolder) viewHolder, i2);
                return;
            default:
                super.onBindViewHolder(viewHolder, i);
                return;
        }
    }

    private void bindHeadHolder(MIUIHeadHolder mIUIHeadHolder) {
        if (this.mContentModel != null && this.mContentModel.data != null) {
            if (this.mContentModel.data.banner != null) {
                ImageLoader.showImage(mIUIHeadHolder.mMiuiIcon, this.mContentModel.data.banner.img);
                final String str = this.mContentModel.data.banner.link;
                if (!TextUtils.isEmpty(str)) {
                    mIUIHeadHolder.mMiuiIcon.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            WebActivity.jump(MIUIForumAdapter.this.ctx, str);
                        }
                    });
                }
            }
            mIUIHeadHolder.mSubForumList.setLayoutManager(new WrapContentGridLayoutManager(this.ctx, 4));
            mIUIHeadHolder.mSubForumList.setAdapter(new MIUISubForumAdapter(this.mContentModel.data.submenu));
            if (this.mContentModel.data == null || this.mContentModel.data.columns == null || this.mContentModel.data.columns.size() <= 0) {
                mIUIHeadHolder.mMiuiRecommendedColumnLayout.setVisibility(8);
            } else {
                mIUIHeadHolder.mMiuiRecommendedColumnLayout.setVisibility(0);
                WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.ctx);
                wrapContentLinearLayoutManager.setOrientation(0);
                MIUIRecommendAdapter mIUIRecommendAdapter = new MIUIRecommendAdapter(this.mContentModel.data.columns);
                mIUIHeadHolder.mRecommendList.setLayoutManager(wrapContentLinearLayoutManager);
                mIUIHeadHolder.mRecommendList.setAdapter(mIUIRecommendAdapter);
            }
            if (this.mContentModel.data.users == null || this.mContentModel.data.users.size() <= 0) {
                mIUIHeadHolder.mMiuiProfessionalUsersLayout.setVisibility(8);
                return;
            }
            mIUIHeadHolder.mMiuiProfessionalUsersLayout.setVisibility(0);
            WrapContentLinearLayoutManager wrapContentLinearLayoutManager2 = new WrapContentLinearLayoutManager(this.ctx);
            wrapContentLinearLayoutManager2.setOrientation(0);
            MIUIProfessionUserAdapter mIUIProfessionUserAdapter = new MIUIProfessionUserAdapter(this.ctx, this.mContentModel.data.users);
            mIUIHeadHolder.mUserList.setLayoutManager(wrapContentLinearLayoutManager2);
            mIUIHeadHolder.mUserList.setAdapter(mIUIProfessionUserAdapter);
        }
    }

    public int getDataItemCount() {
        return ((this.mContentModel == null || this.mContentModel.data == null) ? 0 : 1) + this.mForumList.size();
    }

    public int getNormalViewType(int i) {
        if (i == 0) {
            return 0;
        }
        MIUIForumModel.DataBean.ListBean listBean = this.mForumList.get(i - 1);
        int i2 = listBean.special;
        if (i2 == 1) {
            return 3;
        }
        if (i2 == 5) {
            return 4;
        }
        List<String> list = listBean.showpiclist;
        if (list == null || list.size() == 0) {
            return 1;
        }
        return 2;
    }

    public void setContentModel(MIUIContentModel mIUIContentModel) {
        this.mContentModel = mIUIContentModel;
        notifyDataSetChanged();
    }

    public void addForumList(List<MIUIForumModel.DataBean.ListBean> list) {
        this.mForumList.addAll(list);
        notifyDataSetChanged();
    }

    public int forumSize() {
        return this.mForumList.size();
    }

    public void clear() {
        this.mForumList.clear();
    }

    private void bindOnePicHolder(ForumOnePicHolder forumOnePicHolder, int i) {
        MIUIForumModel.DataBean.ListBean listBean = this.mForumList.get(i);
        handleCommonPart(forumOnePicHolder, listBean);
        ImageLoader.showImage(forumOnePicHolder.forumItemPic, listBean.showpiclist.get(0));
    }

    private void bindNoPicHolder(ForumNoPicHolder forumNoPicHolder, int i) {
        handleCommonPart(forumNoPicHolder, this.mForumList.get(i));
    }

    private void bindDebateHolder(ForumDebateHolder forumDebateHolder, int i) {
        MIUIForumModel.DataBean.ListBean listBean = this.mForumList.get(i);
        handleCommonPart(forumDebateHolder, listBean);
        TextView textView = forumDebateHolder.forumItemDebateText1;
        textView.setText(listBean.affirmreplies + "");
        TextView textView2 = forumDebateHolder.forumItemDebateText2;
        textView2.setText(listBean.negareplies + "");
    }

    private void bindVoteHolder(ForumVoteHolder forumVoteHolder, int i) {
        MIUIForumModel.DataBean.ListBean listBean = this.mForumList.get(i);
        handleCommonPart(forumVoteHolder, listBean);
        TextView textView = forumVoteHolder.forumItemPollNumber;
        textView.setText(listBean.pollcount + "");
    }

    private void handleCommonPart(BaseForumHolder baseForumHolder, final MIUIForumModel.DataBean.ListBean listBean) {
        ImageLoader.showHeadLogo(baseForumHolder.forumItemUserIcon, listBean.authimg, listBean.showlogo, listBean.groupid);
        baseForumHolder.forumItemUserName.setText(listBean.author);
        baseForumHolder.forumItemPostTime.setText(listBean.dateline);
        int i = 0;
        baseForumHolder.forumItemType.setVisibility(0);
        baseForumHolder.forumItemType.setText(listBean.fname);
        final String str = listBean.subject;
        baseForumHolder.forumItemTitle.setText(str);
        if ("0".equals(Integer.valueOf(listBean.replies))) {
            baseForumHolder.forumItemActionCommentText.setText(this.ctx.getString(R.string.comment));
        } else {
            baseForumHolder.forumItemActionCommentText.setText(listBean.replies + "");
        }
        baseForumHolder.forumItemActionLikeText.setToggleAble(true);
        if (listBean.thumbupcount == 0) {
            baseForumHolder.forumItemActionLikeText.setText(this.ctx.getString(R.string.like));
        } else {
            baseForumHolder.forumItemActionLikeText.setText(listBean.thumbupcount + "");
        }
        baseForumHolder.forumItemActionLikeText.setChecked(listBean.thumbupstatus == 1);
        baseForumHolder.forumItemActionLikeText.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_fORUM, Constants.ClickEvent.CLICK_LIKE, listBean.tid + "");
                    listBean.thumbupstatus = z ? 1 : 0;
                    listBean.thumbupcount += z ? 1 : -1;
                    if (listBean.thumbupcount == 0) {
                        checkedTextView.setText(MIUIForumAdapter.this.ctx.getString(R.string.like));
                    } else {
                        checkedTextView.setText(listBean.thumbupcount + "");
                    }
                    ApiClient.thumbUp(listBean.tid + "", LoginManager.getInstance().getUserId());
                    return;
                }
                checkedTextView.setChecked(false);
                MIUIForumAdapter.this.ctx.gotoAccount();
            }
        });
        baseForumHolder.forumItemActionComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_fORUM, Constants.ClickEvent.CLICK_COMMENT, listBean.tid + "");
                CommentsActivity.jump(MIUIForumAdapter.this.ctx, listBean.fid + "", listBean.tid + "");
            }
        });
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{Long.valueOf(listBean.tid)}));
        baseForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("miui", "click_thread", "click_thread_" + listBean.tid);
                WebActivity.jump(MIUIForumAdapter.this.ctx, appUrl);
            }
        });
        ImageView imageView = baseForumHolder.forumItemFlagIcon;
        if (listBean.displayorder == 0) {
            i = 4;
        }
        imageView.setVisibility(i);
        baseForumHolder.forumItemActionShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_fORUM, Constants.ClickEvent.CLICK_SHARE, listBean.tid + "");
                if (MIUIForumAdapter.this.onShareListener != null) {
                    MIUIForumAdapter.this.onShareListener.onShare(str, appUrl);
                }
            }
        });
        final String str2 = listBean.authorid + "";
        baseForumHolder.forumItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(MIUIForumAdapter.this.ctx, str2);
            }
        });
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    public class MIUIHeadHolder extends RecyclerView.ViewHolder {
        @BindView(2131493642)
        ImageView mMiuiIcon;
        @BindView(2131493643)
        LinearLayout mMiuiProfessionalUsersLayout;
        @BindView(2131493646)
        LinearLayout mMiuiRecommendedColumnLayout;
        @BindView(2131493875)
        RecyclerView mRecommendList;
        @BindView(2131494038)
        RecyclerView mSubForumList;
        @BindView(2131494224)
        RecyclerView mUserList;

        public MIUIHeadHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumNoPicHolder extends BaseForumHolder {
        public ForumNoPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumOnePicHolder extends BaseForumHolder {
        @BindView(2131493323)
        ImageView forumItemPic;

        public ForumOnePicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumVoteHolder extends BaseForumHolder {
        @BindView(2131493327)
        ImageView forumItemPollImg;
        @BindView(2131493328)
        TextView forumItemPollNumber;

        public ForumVoteHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumDebateHolder extends BaseForumHolder {
        @BindView(2131493312)
        TextView forumItemDebateText1;
        @BindView(2131493313)
        TextView forumItemDebateText2;

        public ForumDebateHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class BaseForumHolder extends RecyclerView.ViewHolder {
        @BindView(2131493303)
        FrameLayout forumItemActionComment;
        @BindView(2131493304)
        TextView forumItemActionCommentText;
        @BindView(2131493305)
        FrameLayout forumItemActionLike;
        @BindView(2131493306)
        CheckedTextView forumItemActionLikeText;
        @BindView(2131493307)
        FrameLayout forumItemActionShare;
        @BindView(2131493314)
        ImageView forumItemFlagIcon;
        @BindView(2131493318)
        ImageView forumItemMore;
        @BindView(2131493329)
        TextView forumItemPostTime;
        @BindView(2131493331)
        TextView forumItemTitle;
        @BindView(2131494142)
        TextView forumItemType;
        @BindView(2131493334)
        HeadLogoView forumItemUserIcon;
        @BindView(2131493335)
        TextView forumItemUserName;

        public BaseForumHolder(View view) {
            super(view);
        }
    }
}
