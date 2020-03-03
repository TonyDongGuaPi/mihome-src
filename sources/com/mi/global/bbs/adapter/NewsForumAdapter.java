package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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
import com.mi.global.bbs.model.SubForumItem;
import com.mi.global.bbs.model.ThreadListBean;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.HeadLogoView;
import java.util.ArrayList;
import java.util.List;

public class NewsForumAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_BIG_PIC = 4;
    private static final int TYPE_DEBATE = 128;
    private static final int TYPE_MULTI_PIC = 16;
    private static final int TYPE_NO_PIC = 1;
    private static final int TYPE_ONE_PIC = 2;
    private static final int TYPE_TWO_PIC = 8;
    private static final int TYPE_VIDEO = 32;
    private static final int TYPE_VIDEO_VALUE = 1;
    private static final int TYPE_VOTE = 64;
    /* access modifiers changed from: private */
    public Context context;
    private SubForumItem.DataBean data;
    /* access modifiers changed from: private */
    public onRecycleClickListener onRecycleClickListener;
    /* access modifiers changed from: private */
    public OnShareListener onShareListener;
    /* access modifiers changed from: private */
    public String page_name;
    public List<ThreadListBean> threadlist = new ArrayList();

    public interface onRecycleClickListener {
        void onClickYoutube(String str);
    }

    public class ForumBigPicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumBigPicHolder target;

        @UiThread
        public ForumBigPicHolder_ViewBinding(ForumBigPicHolder forumBigPicHolder, View view) {
            super(forumBigPicHolder, view);
            this.target = forumBigPicHolder;
            forumBigPicHolder.forumItemBigPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_big_pic, "field 'forumItemBigPic'", ImageView.class);
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

    public class ForumTwoPicHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumTwoPicHolder target;

        @UiThread
        public ForumTwoPicHolder_ViewBinding(ForumTwoPicHolder forumTwoPicHolder, View view) {
            super(forumTwoPicHolder, view);
            this.target = forumTwoPicHolder;
            forumTwoPicHolder.forumItemPic1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_pic_1, "field 'forumItemPic1'", ImageView.class);
            forumTwoPicHolder.forumItemPic2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_pic_2, "field 'forumItemPic2'", ImageView.class);
        }

        public void unbind() {
            ForumTwoPicHolder forumTwoPicHolder = this.target;
            if (forumTwoPicHolder != null) {
                this.target = null;
                forumTwoPicHolder.forumItemPic1 = null;
                forumTwoPicHolder.forumItemPic2 = null;
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
            forumMultiPicHolder.forumItemMultiPic1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_multi_pic_1, "field 'forumItemMultiPic1'", ImageView.class);
            forumMultiPicHolder.forumItemMultiPic2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_multi_pic_2, "field 'forumItemMultiPic2'", ImageView.class);
            forumMultiPicHolder.forumItemMultiPic3 = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_multi_pic_3, "field 'forumItemMultiPic3'", ImageView.class);
            forumMultiPicHolder.forumItemMorePicText = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_more_pic_text, "field 'forumItemMorePicText'", TextView.class);
        }

        public void unbind() {
            ForumMultiPicHolder forumMultiPicHolder = this.target;
            if (forumMultiPicHolder != null) {
                this.target = null;
                forumMultiPicHolder.forumItemMultiPic1 = null;
                forumMultiPicHolder.forumItemMultiPic2 = null;
                forumMultiPicHolder.forumItemMultiPic3 = null;
                forumMultiPicHolder.forumItemMorePicText = null;
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

    public class ForumVideoHolder_ViewBinding implements Unbinder {
        private ForumVideoHolder target;

        @UiThread
        public ForumVideoHolder_ViewBinding(ForumVideoHolder forumVideoHolder, View view) {
            this.target = forumVideoHolder;
            forumVideoHolder.forumItemVideoPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_video_pic, "field 'forumItemVideoPic'", ImageView.class);
            forumVideoHolder.forumItemPlayBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_play_bt, "field 'forumItemPlayBt'", ImageView.class);
            forumVideoHolder.forumItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_title, "field 'forumItemTitle'", TextView.class);
            forumVideoHolder.videoLinearLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_video_layout, "field 'videoLinearLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ForumVideoHolder forumVideoHolder = this.target;
            if (forumVideoHolder != null) {
                this.target = null;
                forumVideoHolder.forumItemVideoPic = null;
                forumVideoHolder.forumItemPlayBt = null;
                forumVideoHolder.forumItemTitle = null;
                forumVideoHolder.videoLinearLayout = null;
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
            baseForumHolder.forumItemCommonTopFl = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_common_top_fl, "field 'forumItemCommonTopFl'", FrameLayout.class);
            baseForumHolder.forumItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.forum_item_user_icon, "field 'forumItemUserIcon'", HeadLogoView.class);
            baseForumHolder.forumItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_user_name, "field 'forumItemUserName'", TextView.class);
            baseForumHolder.forumItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_post_time, "field 'forumItemPostTime'", TextView.class);
            baseForumHolder.forumItemMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_more, "field 'forumItemMore'", ImageView.class);
            baseForumHolder.forumItemFlagIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_flag_icon, "field 'forumItemFlagIcon'", ImageView.class);
            baseForumHolder.forumItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_title, "field 'forumItemTitle'", TextView.class);
            baseForumHolder.forumItemActionShare = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_share, "field 'forumItemActionShare'", FrameLayout.class);
            baseForumHolder.forumItemActionCommentText = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment_text, "field 'forumItemActionCommentText'", TextView.class);
            baseForumHolder.forumItemActionComment = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment, "field 'forumItemActionComment'", FrameLayout.class);
            baseForumHolder.forumItemActionLikeText = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like_text, "field 'forumItemActionLikeText'", CheckedTextView.class);
            baseForumHolder.forumItemActionLike = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like, "field 'forumItemActionLike'", FrameLayout.class);
            baseForumHolder.forumItemBottomLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_bottom_layout, "field 'forumItemBottomLy'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            BaseForumHolder baseForumHolder = this.target;
            if (baseForumHolder != null) {
                this.target = null;
                baseForumHolder.forumItemCommonTopFl = null;
                baseForumHolder.forumItemUserIcon = null;
                baseForumHolder.forumItemUserName = null;
                baseForumHolder.forumItemPostTime = null;
                baseForumHolder.forumItemMore = null;
                baseForumHolder.forumItemFlagIcon = null;
                baseForumHolder.forumItemTitle = null;
                baseForumHolder.forumItemActionShare = null;
                baseForumHolder.forumItemActionCommentText = null;
                baseForumHolder.forumItemActionComment = null;
                baseForumHolder.forumItemActionLikeText = null;
                baseForumHolder.forumItemActionLike = null;
                baseForumHolder.forumItemBottomLy = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public NewsForumAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(activity, dataLoading);
        this.context = activity;
        this.page_name = str;
    }

    public void setOnRecycleClickListener(onRecycleClickListener onrecycleclicklistener) {
        this.onRecycleClickListener = onrecycleclicklistener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4) {
            return new ForumBigPicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_big_pic, viewGroup, false));
        }
        if (i == 8) {
            return new ForumTwoPicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_two_pic, viewGroup, false));
        }
        if (i == 16) {
            return new ForumMultiPicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_multi_pic, viewGroup, false));
        }
        if (i == 32) {
            return new ForumVideoHolder(this.layoutInflater.inflate(R.layout.bbs_hot_item_vedio, viewGroup, false));
        }
        if (i == 64) {
            return new ForumVoteHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_vote, viewGroup, false));
        }
        if (i == 128) {
            return new ForumDebateHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_debate, viewGroup, false));
        }
        switch (i) {
            case 1:
                return new ForumNoPicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_no_pic, viewGroup, false));
            case 2:
                return new ForumOnePicHolder(this.layoutInflater.inflate(R.layout.bbs_forum_item_one_pic, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        int itemViewType = getItemViewType(i);
        if (itemViewType == 4) {
            bindBigPicHolder((ForumBigPicHolder) viewHolder, i);
        } else if (itemViewType == 8) {
            bindTwoPicHolder((ForumTwoPicHolder) viewHolder, i);
        } else if (itemViewType == 16) {
            bindMultiHolder((ForumMultiPicHolder) viewHolder, i);
        } else if (itemViewType == 32) {
            bindVideoHolder((ForumVideoHolder) viewHolder, i);
        } else if (itemViewType == 64) {
            bindVoteHolder((ForumVoteHolder) viewHolder, i);
        } else if (itemViewType != 128) {
            switch (itemViewType) {
                case 1:
                    bindNoPicHolder((ForumNoPicHolder) viewHolder, i);
                    return;
                case 2:
                    bindOnePicHolder((ForumOnePicHolder) viewHolder, i);
                    return;
                default:
                    return;
            }
        } else {
            bindDebateHolder((ForumDebateHolder) viewHolder, i);
        }
    }

    private void bindDebateHolder(ForumDebateHolder forumDebateHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumDebateHolder, threadListBean);
        TextView textView = forumDebateHolder.forumItemDebateText1;
        textView.setText(threadListBean.affirmreplies + "");
        TextView textView2 = forumDebateHolder.forumItemDebateText2;
        textView2.setText(threadListBean.negareplies + "");
    }

    private void bindVoteHolder(ForumVoteHolder forumVoteHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumVoteHolder, threadListBean);
        TextView textView = forumVoteHolder.forumItemPollNumber;
        textView.setText(threadListBean.pollcount + "");
    }

    private void bindVideoHolder(ForumVideoHolder forumVideoHolder, int i) {
        final ThreadListBean threadListBean = this.threadlist.get(i);
        if (threadListBean.showpiclist != null && threadListBean.showpiclist.size() > 0) {
            ImageLoader.showImage(forumVideoHolder.forumItemVideoPic, threadListBean.showpiclist.get(0));
        }
        forumVideoHolder.forumItemTitle.setText(threadListBean.subject);
        forumVideoHolder.videoLinearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NewsForumAdapter.this.onRecycleClickListener != null) {
                    NewsForumAdapter.this.onRecycleClickListener.onClickYoutube(threadListBean.vedio_url);
                }
            }
        });
    }

    private void bindBigPicHolder(ForumBigPicHolder forumBigPicHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumBigPicHolder, threadListBean);
        ImageLoader.showImage(forumBigPicHolder.forumItemBigPic, threadListBean.showpiclist.get(0));
    }

    private void bindMultiHolder(ForumMultiPicHolder forumMultiPicHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumMultiPicHolder, threadListBean);
        List<String> list = threadListBean.showpiclist;
        ImageLoader.showImage(forumMultiPicHolder.forumItemMultiPic1, list.get(0));
        ImageLoader.showImage(forumMultiPicHolder.forumItemMultiPic2, list.get(1));
        ImageLoader.showImage(forumMultiPicHolder.forumItemMultiPic3, list.get(2));
    }

    private void bindTwoPicHolder(ForumTwoPicHolder forumTwoPicHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumTwoPicHolder, threadListBean);
        List<String> list = threadListBean.showpiclist;
        ImageLoader.showImage(forumTwoPicHolder.forumItemPic1, list.get(0));
        ImageLoader.showImage(forumTwoPicHolder.forumItemPic2, list.get(1));
    }

    private void bindOnePicHolder(ForumOnePicHolder forumOnePicHolder, int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        handleCommonPart(forumOnePicHolder, threadListBean);
        ImageLoader.showImage(forumOnePicHolder.forumItemPic, threadListBean.showpiclist.get(0));
    }

    private void bindNoPicHolder(ForumNoPicHolder forumNoPicHolder, int i) {
        handleCommonPart(forumNoPicHolder, this.threadlist.get(i));
    }

    private void handleCommonPart(BaseForumHolder baseForumHolder, final ThreadListBean threadListBean) {
        ImageLoader.showHeadLogo(baseForumHolder.forumItemUserIcon, threadListBean.authimg, threadListBean.showlogo, threadListBean.groupid);
        baseForumHolder.forumItemUserName.setText(threadListBean.author);
        baseForumHolder.forumItemPostTime.setText(threadListBean.dateline);
        final String str = threadListBean.subject;
        baseForumHolder.forumItemTitle.setText(str);
        if ("0".equals(threadListBean.replies)) {
            baseForumHolder.forumItemActionCommentText.setText(this.context.getString(R.string.comment));
        } else {
            baseForumHolder.forumItemActionCommentText.setText(threadListBean.replies);
        }
        baseForumHolder.forumItemActionLikeText.setToggleAble(true);
        if (threadListBean.thumbupcount == 0) {
            baseForumHolder.forumItemActionLikeText.setText(this.context.getString(R.string.like));
        } else {
            CheckedTextView checkedTextView = baseForumHolder.forumItemActionLikeText;
            checkedTextView.setText(threadListBean.thumbupcount + "");
        }
        baseForumHolder.forumItemActionLikeText.setChecked(threadListBean.thumbupstatus == 1);
        baseForumHolder.forumItemActionLikeText.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                GoogleTrackerUtil.sendRecordEvent(NewsForumAdapter.this.page_name, Constants.ClickEvent.CLICK_LIKE, threadListBean.tid);
                threadListBean.thumbupstatus = z ? 1 : 0;
                threadListBean.thumbupcount += z ? 1 : -1;
                if (threadListBean.thumbupcount == 0) {
                    checkedTextView.setText(NewsForumAdapter.this.context.getString(R.string.like));
                } else {
                    checkedTextView.setText(threadListBean.thumbupcount + "");
                }
                ApiClient.thumbUp(threadListBean.tid + "", LoginManager.getInstance().getUserId());
            }
        });
        baseForumHolder.forumItemActionComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(NewsForumAdapter.this.page_name, Constants.ClickEvent.CLICK_COMMENT, threadListBean.tid);
                CommentsActivity.jump((BaseActivity) NewsForumAdapter.this.context, threadListBean.fid, threadListBean.tid);
            }
        });
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{threadListBean.tid}));
        baseForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(NewsForumAdapter.this.page_name, "click_thread", threadListBean.tid);
                WebActivity.jump(NewsForumAdapter.this.context, appUrl);
            }
        });
        baseForumHolder.forumItemActionShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NewsForumAdapter.this.onShareListener != null) {
                    GoogleTrackerUtil.sendRecordEvent(NewsForumAdapter.this.page_name, Constants.ClickEvent.CLICK_SHARE, threadListBean.tid);
                    NewsForumAdapter.this.onShareListener.onShare(str, appUrl);
                }
            }
        });
    }

    public int getNormalViewType(int i) {
        ThreadListBean threadListBean = this.threadlist.get(i);
        int i2 = threadListBean.special;
        if (threadListBean.type == 1) {
            return 32;
        }
        if (i2 == 1) {
            return 64;
        }
        if (i2 == 5) {
            return 128;
        }
        List<String> list = threadListBean.showpiclist;
        if (list == null) {
            return 1;
        }
        int size = list.size();
        if (this.data.forum != null) {
            if (this.data.forum.photography != 0) {
                switch (size) {
                    case 0:
                        return 1;
                    case 1:
                        return 4;
                    case 2:
                        return 8;
                    default:
                        return 16;
                }
            }
        }
        if (size != 0) {
            return 2;
        }
        return 1;
    }

    public int getDataItemCount() {
        if (this.threadlist == null) {
            return 0;
        }
        return this.threadlist.size();
    }

    public void setData(SubForumItem.DataBean dataBean) {
        this.data = dataBean;
        if (dataBean.threadlist != null) {
            this.threadlist.addAll(dataBean.threadlist);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.threadlist.clear();
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
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

    public class ForumTwoPicHolder extends BaseForumHolder {
        @BindView(2131493324)
        ImageView forumItemPic1;
        @BindView(2131493325)
        ImageView forumItemPic2;

        public ForumTwoPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumBigPicHolder extends BaseForumHolder {
        @BindView(2131493308)
        ImageView forumItemBigPic;

        public ForumBigPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumMultiPicHolder extends BaseForumHolder {
        @BindView(2131493319)
        TextView forumItemMorePicText;
        @BindView(2131493320)
        ImageView forumItemMultiPic1;
        @BindView(2131493321)
        ImageView forumItemMultiPic2;
        @BindView(2131493322)
        ImageView forumItemMultiPic3;

        public ForumMultiPicHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ForumVideoHolder extends RecyclerView.ViewHolder {
        @BindView(2131493326)
        ImageView forumItemPlayBt;
        @BindView(2131493331)
        TextView forumItemTitle;
        @BindView(2131493339)
        ImageView forumItemVideoPic;
        @BindView(2131493338)
        LinearLayout videoLinearLayout;

        public ForumVideoHolder(View view) {
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
        @BindView(2131493309)
        LinearLayout forumItemBottomLy;
        @BindView(2131493310)
        FrameLayout forumItemCommonTopFl;
        @BindView(2131493314)
        ImageView forumItemFlagIcon;
        @BindView(2131493318)
        ImageView forumItemMore;
        @BindView(2131493329)
        TextView forumItemPostTime;
        @BindView(2131493331)
        TextView forumItemTitle;
        @BindView(2131493334)
        HeadLogoView forumItemUserIcon;
        @BindView(2131493335)
        TextView forumItemUserName;

        public BaseForumHolder(View view) {
            super(view);
        }
    }
}
