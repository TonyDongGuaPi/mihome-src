package com.mi.global.bbs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.ForYouRecoomend;
import com.mi.global.bbs.model.HomeBanner;
import com.mi.global.bbs.model.HomeColumnList;
import com.mi.global.bbs.model.HomeData;
import com.mi.global.bbs.model.HomeForumBean;
import com.mi.global.bbs.model.HomeLoadMore;
import com.mi.global.bbs.model.HomeNotify;
import com.mi.global.bbs.model.HomePostBean;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.column.QuestionActivity;
import com.mi.global.bbs.ui.forum.NewsForumActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.utils.UIAdapter;
import com.mi.global.bbs.view.SlidingTabStripView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.pop.ForumActionPopup;
import com.mi.log.LogUtil;
import com.mi.util.Coder;
import java.util.ArrayList;
import java.util.List;

public class HomeThreadAdapter extends RecyclerBaseAdapter<RecyclerView.ViewHolder> {
    public static String TAG = "HomeThreadAdapter";
    /* access modifiers changed from: private */
    public List<HomeData> homeDatas = new ArrayList();
    private boolean isLoading;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public BaseFragment mFragment;
    /* access modifiers changed from: private */
    public OnClickForYouCusListener onClickForYouCusListener;
    /* access modifiers changed from: private */
    public onRecycleClickListener onRecycleClickListener;
    /* access modifiers changed from: private */
    public OnShareListener onShareListener;
    /* access modifiers changed from: private */
    public String pageName;

    public interface OnClickForYouCusListener {
        void onClickForYouCusListener();
    }

    public interface onRecycleClickListener {
        void onClickLink(String str);

        void onClickMore(int i);

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

    public class ForumVideoHolder_ViewBinding extends BaseForumHolder_ViewBinding {
        private ForumVideoHolder target;

        @UiThread
        public ForumVideoHolder_ViewBinding(ForumVideoHolder forumVideoHolder, View view) {
            super(forumVideoHolder, view);
            this.target = forumVideoHolder;
            forumVideoHolder.forumItemVideoPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_video_pic, "field 'forumItemVideoPic'", ImageView.class);
            forumVideoHolder.forumItemPlayBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_play_bt, "field 'forumItemPlayBt'", ImageView.class);
        }

        public void unbind() {
            ForumVideoHolder forumVideoHolder = this.target;
            if (forumVideoHolder != null) {
                this.target = null;
                forumVideoHolder.forumItemVideoPic = null;
                forumVideoHolder.forumItemPlayBt = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ADBannerHolder_ViewBinding implements Unbinder {
        private ADBannerHolder target;

        @UiThread
        public ADBannerHolder_ViewBinding(ADBannerHolder aDBannerHolder, View view) {
            this.target = aDBannerHolder;
            aDBannerHolder.ivAdBanner = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ad_banner_item, "field 'ivAdBanner'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ADBannerHolder aDBannerHolder = this.target;
            if (aDBannerHolder != null) {
                this.target = null;
                aDBannerHolder.ivAdBanner = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForYouEmptyHolder_ViewBinding implements Unbinder {
        private ForYouEmptyHolder target;

        @UiThread
        public ForYouEmptyHolder_ViewBinding(ForYouEmptyHolder forYouEmptyHolder, View view) {
            this.target = forYouEmptyHolder;
            forYouEmptyHolder.tvFollowForYou = (TextView) Utils.findRequiredViewAsType(view, R.id.for_you_follow_bt, "field 'tvFollowForYou'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ForYouEmptyHolder forYouEmptyHolder = this.target;
            if (forYouEmptyHolder != null) {
                this.target = null;
                forYouEmptyHolder.tvFollowForYou = null;
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

    public class ForyouCommendHolder_ViewBinding implements Unbinder {
        private ForyouCommendHolder target;

        @UiThread
        public ForyouCommendHolder_ViewBinding(ForyouCommendHolder foryouCommendHolder, View view) {
            this.target = foryouCommendHolder;
            foryouCommendHolder.recommendRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.id_recyclerview_horizontal, "field 'recommendRecycleView'", RecyclerView.class);
        }

        @CallSuper
        public void unbind() {
            ForyouCommendHolder foryouCommendHolder = this.target;
            if (foryouCommendHolder != null) {
                this.target = null;
                foryouCommendHolder.recommendRecycleView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ViewForYouHeaderHolder_ViewBinding implements Unbinder {
        private ViewForYouHeaderHolder target;

        @UiThread
        public ViewForYouHeaderHolder_ViewBinding(ViewForYouHeaderHolder viewForYouHeaderHolder, View view) {
            this.target = viewForYouHeaderHolder;
            viewForYouHeaderHolder.forYouHeaderLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.for_you_header_layout, "field 'forYouHeaderLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ViewForYouHeaderHolder viewForYouHeaderHolder = this.target;
            if (viewForYouHeaderHolder != null) {
                this.target = null;
                viewForYouHeaderHolder.forYouHeaderLayout = null;
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
            baseForumHolder.forumItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_title, "field 'forumItemTitle'", TextView.class);
            baseForumHolder.forumItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.home_item_forum_author, "field 'forumItemUserName'", TextView.class);
            baseForumHolder.forumItemType = (TextView) Utils.findRequiredViewAsType(view, R.id.home_item_forum_type, "field 'forumItemType'", TextView.class);
            baseForumHolder.forumItemForumMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_item_forum_more, "field 'forumItemForumMore'", ImageView.class);
            baseForumHolder.forumItemActionCommentText = (TextView) Utils.findRequiredViewAsType(view, R.id.home_item_forum_comments, "field 'forumItemActionCommentText'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            BaseForumHolder baseForumHolder = this.target;
            if (baseForumHolder != null) {
                this.target = null;
                baseForumHolder.forumItemTitle = null;
                baseForumHolder.forumItemUserName = null;
                baseForumHolder.forumItemType = null;
                baseForumHolder.forumItemForumMore = null;
                baseForumHolder.forumItemActionCommentText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class NotifyHolder_ViewBinding implements Unbinder {
        private NotifyHolder target;

        @UiThread
        public NotifyHolder_ViewBinding(NotifyHolder notifyHolder, View view) {
            this.target = notifyHolder;
            notifyHolder.tvNotify = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_notify_mes, "field 'tvNotify'", TextView.class);
            notifyHolder.ivClose = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_notify_close, "field 'ivClose'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            NotifyHolder notifyHolder = this.target;
            if (notifyHolder != null) {
                this.target = null;
                notifyHolder.tvNotify = null;
                notifyHolder.ivClose = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class RecommendColumnHolder_ViewBinding implements Unbinder {
        private RecommendColumnHolder target;

        @UiThread
        public RecommendColumnHolder_ViewBinding(RecommendColumnHolder recommendColumnHolder, View view) {
            this.target = recommendColumnHolder;
            recommendColumnHolder.tvColumnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_rd_column_title, "field 'tvColumnTitle'", TextView.class);
            recommendColumnHolder.tvColumnTitleMore = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_column_more, "field 'tvColumnTitleMore'", TextView.class);
            recommendColumnHolder.tvColumnDes = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_rd_column_des, "field 'tvColumnDes'", TextView.class);
            recommendColumnHolder.rvRdColumns = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.home_rd_column_list, "field 'rvRdColumns'", RecyclerView.class);
        }

        @CallSuper
        public void unbind() {
            RecommendColumnHolder recommendColumnHolder = this.target;
            if (recommendColumnHolder != null) {
                this.target = null;
                recommendColumnHolder.tvColumnTitle = null;
                recommendColumnHolder.tvColumnTitleMore = null;
                recommendColumnHolder.tvColumnDes = null;
                recommendColumnHolder.rvRdColumns = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class RecommendHolder_ViewBinding implements Unbinder {
        private RecommendHolder target;

        @UiThread
        public RecommendHolder_ViewBinding(RecommendHolder recommendHolder, View view) {
            this.target = recommendHolder;
            recommendHolder.rvHotListTitle = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.home_hot_list_layout, "field 'rvHotListTitle'", RelativeLayout.class);
            recommendHolder.homeHotList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.home_hot_list, "field 'homeHotList'", RecyclerView.class);
        }

        @CallSuper
        public void unbind() {
            RecommendHolder recommendHolder = this.target;
            if (recommendHolder != null) {
                this.target = null;
                recommendHolder.rvHotListTitle = null;
                recommendHolder.homeHotList = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ForumHeader_ViewBinding implements Unbinder {
        private ForumHeader target;

        @UiThread
        public ForumHeader_ViewBinding(ForumHeader forumHeader, View view) {
            this.target = forumHeader;
            forumHeader.forumItemTopBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_top_bg, "field 'forumItemTopBg'", ImageView.class);
            forumHeader.forumItemIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.forum_item_icon, "field 'forumItemIcon'", ImageView.class);
            forumHeader.forumItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_title, "field 'forumItemTitle'", TextView.class);
            forumHeader.forumItemThreadCount = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_thread_count, "field 'forumItemThreadCount'", TextView.class);
            forumHeader.forumItemFollowerCount = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_follower_count, "field 'forumItemFollowerCount'", TextView.class);
            forumHeader.forumItemFollowerBt = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_follower_bt, "field 'forumItemFollowerBt'", TextView.class);
            forumHeader.forumItemTopStrip = (SlidingTabStripView) Utils.findRequiredViewAsType(view, R.id.forum_item_top_strip, "field 'forumItemTopStrip'", SlidingTabStripView.class);
        }

        @CallSuper
        public void unbind() {
            ForumHeader forumHeader = this.target;
            if (forumHeader != null) {
                this.target = null;
                forumHeader.forumItemTopBg = null;
                forumHeader.forumItemIcon = null;
                forumHeader.forumItemTitle = null;
                forumHeader.forumItemThreadCount = null;
                forumHeader.forumItemFollowerCount = null;
                forumHeader.forumItemFollowerBt = null;
                forumHeader.forumItemTopStrip = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class LoadMoreHolder_ViewBinding implements Unbinder {
        private LoadMoreHolder target;

        @UiThread
        public LoadMoreHolder_ViewBinding(LoadMoreHolder loadMoreHolder, View view) {
            this.target = loadMoreHolder;
            loadMoreHolder.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_load_more_title, "field 'tvTitle'", TextView.class);
            loadMoreHolder.relLoadMore = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.info_load_more_layout, "field 'relLoadMore'", RelativeLayout.class);
            loadMoreHolder.pbLoading = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.pb_loading_more, "field 'pbLoading'", ProgressBar.class);
            loadMoreHolder.ivAgain = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading_failed, "field 'ivAgain'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            LoadMoreHolder loadMoreHolder = this.target;
            if (loadMoreHolder != null) {
                this.target = null;
                loadMoreHolder.tvTitle = null;
                loadMoreHolder.relLoadMore = null;
                loadMoreHolder.pbLoading = null;
                loadMoreHolder.ivAgain = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeThreadAdapter(BaseFragment baseFragment, String str) {
        super(baseFragment.getContext());
        this.mFragment = baseFragment;
        this.mContext = baseFragment.getContext();
        this.pageName = str;
    }

    public int getItemViewType(int i) {
        return this.homeDatas.get(i).getType();
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void setLoading(boolean z) {
        this.isLoading = z;
    }

    public void setOnRecycleClickListener(onRecycleClickListener onrecycleclicklistener) {
        this.onRecycleClickListener = onrecycleclicklistener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 2:
                return new NotifyHolder(this.mInflater.inflate(R.layout.bbs_home_notify_list_item, viewGroup, false));
            case 4:
                return new RecommendHolder(this.mInflater.inflate(R.layout.bbs_home_item_hot_list, viewGroup, false));
            case 7:
                return new LoadMoreHolder(this.mInflater.inflate(R.layout.bbs_home_load_more, viewGroup, false));
            case 10:
                return new ADBannerHolder(this.mInflater.inflate(R.layout.bbs_home_ad_banner, viewGroup, false));
            case 12:
                return new ForumNoPicHolder(this.mInflater.inflate(R.layout.bbs_home_item_no_pic, viewGroup, false));
            case 13:
                return new ForumOnePicHolder(this.mInflater.inflate(R.layout.bbs_home_item_one_pic, viewGroup, false));
            case 14:
                return new ForumBigPicHolder(this.mInflater.inflate(R.layout.bbs_home_item_big_pic, viewGroup, false));
            case 15:
                return new ForumTwoPicHolder(this.mInflater.inflate(R.layout.bbs_home_item_two_pic, viewGroup, false));
            case 16:
                return new ForumMultiPicHolder(this.mInflater.inflate(R.layout.bbs_home_item_multi_pic, viewGroup, false));
            case 18:
                return new ForumVoteHolder(this.mInflater.inflate(R.layout.bbs_home_item_vote, viewGroup, false));
            case 19:
                return new ForumDebateHolder(this.mInflater.inflate(R.layout.bbs_home_item_debate, viewGroup, false));
            case 20:
                return new ForyouCommendHolder(this.mInflater.inflate(R.layout.bbs_foryou_recommend_recycle, viewGroup, false));
            case 21:
                return new ForYouEmptyHolder(this.mInflater.inflate(R.layout.bbs_home_foryou_empty, viewGroup, false));
            case 23:
                return new ViewForYouHeaderHolder(this.mInflater.inflate(R.layout.bbs_for_you_list_header, viewGroup, false));
            case 24:
                return new RecommendColumnHolder(this.mInflater.inflate(R.layout.bbs_home_item_recomm_column, viewGroup, false));
            default:
                return null;
        }
    }

    public void refreshDatas(List<HomeData> list) {
        this.homeDatas.clear();
        this.homeDatas.addAll(list);
        setCount(this.homeDatas.size());
        notifyDataSetChanged();
    }

    public void addMoreUrlList(List<HomeData> list) {
        this.homeDatas.addAll(list);
    }

    public void addLoadMoreData(int i) {
        HomeLoadMore homeLoadMore = new HomeLoadMore();
        homeLoadMore.setState(i);
        HomeData homeData = new HomeData();
        homeData.setType(7);
        homeData.setLoadMore(homeLoadMore);
        this.homeDatas.add(homeData);
        new Handler().post(new Runnable() {
            public void run() {
                HomeThreadAdapter.this.notifyItemInserted(HomeThreadAdapter.this.homeDatas.size() - 1);
            }
        });
    }

    public void changeLoadMoreFailed() {
        if (this.homeDatas.size() > 0 && this.homeDatas.get(this.homeDatas.size() - 1).getType() == 7) {
            HomeLoadMore homeLoadMore = new HomeLoadMore();
            homeLoadMore.setState(2);
            HomeData homeData = new HomeData();
            homeData.setType(7);
            homeData.setLoadMore(homeLoadMore);
            this.homeDatas.set(this.homeDatas.size() - 1, homeData);
            notifyItemChanged(this.homeDatas.size() - 1);
            String str = TAG;
            LogUtil.b(str, "changeLoadMoreFailed" + this.homeDatas.size());
        }
    }

    public void removeLoadMore() {
        if (this.homeDatas.size() > 0 && this.homeDatas.get(this.homeDatas.size() - 1).getType() == 7) {
            this.homeDatas.remove(this.homeDatas.size() - 1);
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return this.homeDatas.size();
    }

    public Object getItem(int i) {
        return this.homeDatas.get(i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        HomeData homeData = this.homeDatas.get(i);
        if (homeData != null) {
            switch (this.homeDatas.get(i).getType()) {
                case 2:
                    bindNotifyHolder((NotifyHolder) viewHolder, homeData);
                    return;
                case 4:
                    bindRecommendHolder((RecommendHolder) viewHolder, homeData);
                    return;
                case 7:
                    bindLoadMoreHolder((LoadMoreHolder) viewHolder, homeData);
                    return;
                case 10:
                    bindAdBannerHolder((ADBannerHolder) viewHolder, homeData);
                    return;
                case 12:
                    bindNoPicHolder((ForumNoPicHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 13:
                    bindOnePicHolder((ForumOnePicHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 14:
                    bindBigPicHolder((ForumBigPicHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 15:
                    bindTwoPicHolder((ForumTwoPicHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 16:
                    bindMultiHolder((ForumMultiPicHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 18:
                    bindVoteHolder((ForumVoteHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 19:
                    bindDebateHolder((ForumDebateHolder) viewHolder, homeData.getHomeForumBean());
                    return;
                case 20:
                    bindForYouRecHolder((ForyouCommendHolder) viewHolder, homeData);
                    return;
                case 21:
                    bindForYouEmptyRecHolder((ForYouEmptyHolder) viewHolder);
                    return;
                case 23:
                    bindForYouHeaderHolder((ViewForYouHeaderHolder) viewHolder);
                    return;
                case 24:
                    bindRecommendColumnHolder((RecommendColumnHolder) viewHolder, homeData);
                    return;
                default:
                    return;
            }
        }
    }

    class LoadMoreHolder extends RecyclerView.ViewHolder {
        @BindView(2131493530)
        public ImageView ivAgain;
        @BindView(2131493778)
        public ProgressBar pbLoading;
        @BindView(2131493459)
        public RelativeLayout relLoadMore;
        @BindView(2131494144)
        public TextView tvTitle;

        public LoadMoreHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class NotifyHolder extends RecyclerView.ViewHolder {
        @BindView(2131493532)
        public ImageView ivClose;
        @BindView(2131494147)
        public TextView tvNotify;

        public NotifyHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class ADBannerHolder extends RecyclerView.ViewHolder {
        @BindView(2131493518)
        public ImageView ivAdBanner;

        public ADBannerHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        @BindView(2131493405)
        RecyclerView homeHotList;
        @BindView(2131493409)
        RelativeLayout rvHotListTitle;

        public RecommendHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class RecommendColumnHolder extends RecyclerView.ViewHolder {
        @BindView(2131493416)
        RecyclerView rvRdColumns;
        @BindView(2131493426)
        TextView tvColumnDes;
        @BindView(2131493427)
        TextView tvColumnTitle;
        @BindView(2131493425)
        TextView tvColumnTitleMore;

        public RecommendColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class ViewForYouHeaderHolder extends RecyclerView.ViewHolder {
        @BindView(2131493296)
        public LinearLayout forYouHeaderLayout;

        public ViewForYouHeaderHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    class ForYouEmptyHolder extends RecyclerView.ViewHolder {
        @BindView(2131493295)
        public TextView tvFollowForYou;

        public ForYouEmptyHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    private void bindNotifyHolder(NotifyHolder notifyHolder, final HomeData homeData) {
        final HomeNotify homeNotify = homeData.getHomeNotify();
        notifyHolder.tvNotify.setText(homeNotify.getMessage());
        notifyHolder.tvNotify.setSelected(true);
        notifyHolder.tvNotify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeThreadAdapter.this.onRecycleClickListener != null && !TextUtils.isEmpty(homeNotify.getLink())) {
                    GoogleTrackerUtil.sendRecordEvent(HomeThreadAdapter.this.pageName, Constants.WebView.CLICK_NOTIFY, homeNotify.getLink());
                    HomeThreadAdapter.this.onRecycleClickListener.onClickLink(homeNotify.getLink());
                }
            }
        });
        notifyHolder.ivClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeThreadAdapter.this.homeDatas.remove(homeData);
                HomeThreadAdapter.this.notifyDataSetChanged();
            }
        });
    }

    private void bindRecommendHolder(RecommendHolder recommendHolder, HomeData homeData) {
        List<HomePostBean> homeHots = homeData.getHomeHots();
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.mContext);
        wrapContentLinearLayoutManager.setOrientation(0);
        HomeHotAdapter homeHotAdapter = new HomeHotAdapter(homeHots);
        recommendHolder.homeHotList.setLayoutManager(wrapContentLinearLayoutManager);
        recommendHolder.homeHotList.setAdapter(homeHotAdapter);
        recommendHolder.rvHotListTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("home", "click_hot", "click_hot_more");
                NewsForumActivity.jump(HomeThreadAdapter.this.mContext, 0);
            }
        });
    }

    private void bindRecommendColumnHolder(RecommendColumnHolder recommendColumnHolder, HomeData homeData) {
        final HomeColumnList homeColumnList = homeData.getHomeColumnList();
        if (homeColumnList != null) {
            recommendColumnHolder.tvColumnTitle.setText(homeColumnList.name);
            recommendColumnHolder.tvColumnDes.setText(homeColumnList.info);
            WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.mContext);
            wrapContentLinearLayoutManager.setOrientation(0);
            HomeRdColumnAdapter homeRdColumnAdapter = new HomeRdColumnAdapter(homeColumnList.thread);
            recommendColumnHolder.rvRdColumns.setLayoutManager(wrapContentLinearLayoutManager);
            recommendColumnHolder.rvRdColumns.setAdapter(homeRdColumnAdapter);
            recommendColumnHolder.tvColumnTitleMore.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoogleTrackerUtil.sendRecordEvent("home", "click_ad_column_more", "click_column_more_" + homeColumnList.columnid);
                    ColumnDetailActivity.jumpWithId(HomeThreadAdapter.this.mContext, homeColumnList.columnid);
                }
            });
        }
    }

    private void bindLoadMoreHolder(LoadMoreHolder loadMoreHolder, HomeData homeData) {
        HomeLoadMore loadMore = homeData.getLoadMore();
        if (loadMore.getState() == 1) {
            loadMoreHolder.tvTitle.setText(R.string.str_laoding);
            loadMoreHolder.pbLoading.setVisibility(0);
            loadMoreHolder.ivAgain.setVisibility(8);
        } else if (loadMore.getState() == 2) {
            loadMoreHolder.tvTitle.setText(R.string.tap_to_retry);
            loadMoreHolder.pbLoading.setVisibility(8);
            loadMoreHolder.ivAgain.setVisibility(0);
        }
    }

    private void bindAdBannerHolder(ADBannerHolder aDBannerHolder, HomeData homeData) {
        HomeBanner homeAdBanner = homeData.getHomeAdBanner();
        String pic_url = homeAdBanner.getPic_url();
        final String link = homeAdBanner.getLink();
        ImageLoader.showListItemImage(aDBannerHolder.ivAdBanner, pic_url);
        aDBannerHolder.ivAdBanner.getLayoutParams().height = UIAdapter.getInstance().getWidgetPxValue(20);
        aDBannerHolder.ivAdBanner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeThreadAdapter.this.onRecycleClickListener != null) {
                    GoogleTrackerUtil.sendRecordEvent(HomeThreadAdapter.this.pageName, Constants.WebView.CLICK_AD_BANNER, link);
                    HomeThreadAdapter.this.onRecycleClickListener.onClickLink(link);
                }
            }
        });
    }

    private void bindForYouHeaderHolder(ViewForYouHeaderHolder viewForYouHeaderHolder) {
        viewForYouHeaderHolder.forYouHeaderLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeThreadAdapter.this.onClickForYouCusListener != null) {
                    HomeThreadAdapter.this.onClickForYouCusListener.onClickForYouCusListener();
                }
            }
        });
    }

    private void bindDebateHolder(ForumDebateHolder forumDebateHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumDebateHolder, homeForumBean);
        TextView textView = forumDebateHolder.forumItemDebateText1;
        textView.setText(homeForumBean.affirmreplies + "");
        TextView textView2 = forumDebateHolder.forumItemDebateText2;
        textView2.setText(homeForumBean.negareplies + "");
    }

    private void bindVoteHolder(ForumVoteHolder forumVoteHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumVoteHolder, homeForumBean);
        TextView textView = forumVoteHolder.forumItemPollNumber;
        textView.setText(homeForumBean.pollcount + "");
    }

    private void bindBigPicHolder(ForumBigPicHolder forumBigPicHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumBigPicHolder, homeForumBean);
        ImageLoader.showListItemImage(forumBigPicHolder.forumItemBigPic, homeForumBean.showpiclist.get(0));
    }

    private void bindMultiHolder(ForumMultiPicHolder forumMultiPicHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumMultiPicHolder, homeForumBean);
        List<String> list = homeForumBean.showpiclist;
        int i = 0;
        ImageLoader.showListItemImage(forumMultiPicHolder.forumItemMultiPic1, list.get(0));
        ImageLoader.showListItemImage(forumMultiPicHolder.forumItemMultiPic2, list.get(1));
        ImageLoader.showListItemImage(forumMultiPicHolder.forumItemMultiPic3, list.get(2));
        TextView textView = forumMultiPicHolder.forumItemMorePicText;
        if (list.size() <= 3) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    private void bindTwoPicHolder(ForumTwoPicHolder forumTwoPicHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumTwoPicHolder, homeForumBean);
        List<String> list = homeForumBean.showpiclist;
        ImageLoader.showListItemImage(forumTwoPicHolder.forumItemPic1, list.get(0));
        ImageLoader.showListItemImage(forumTwoPicHolder.forumItemPic2, list.get(1));
    }

    private void bindOnePicHolder(ForumOnePicHolder forumOnePicHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumOnePicHolder, homeForumBean);
        ImageLoader.showListItemImage(forumOnePicHolder.forumItemPic, homeForumBean.showpiclist.get(0));
    }

    private void bindNoPicHolder(ForumNoPicHolder forumNoPicHolder, HomeForumBean homeForumBean) {
        handleCommonPart(forumNoPicHolder, homeForumBean);
    }

    private void handleCommonPart(final BaseForumHolder baseForumHolder, final HomeForumBean homeForumBean) {
        boolean z = true;
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{homeForumBean.tid}));
        baseForumHolder.forumItemUserName.setText(homeForumBean.author);
        baseForumHolder.forumItemType.setVisibility(0);
        baseForumHolder.forumItemType.setText(homeForumBean.fname);
        baseForumHolder.forumItemType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(homeForumBean.fid)) {
                    PlateActivity.jumpWithId(HomeThreadAdapter.this.mContext, homeForumBean.fid, homeForumBean.fname);
                }
            }
        });
        final String str = homeForumBean.subject;
        baseForumHolder.forumItemTitle.setText(str);
        baseForumHolder.forumItemActionCommentText.setText(TextHelper.getQuantityString(this.mContext, R.plurals.comments_count, homeForumBean.replies));
        final ForumActionPopup forumActionPopup = new ForumActionPopup(this.mContext);
        if (homeForumBean.thumbupstatus != 1) {
            z = false;
        }
        forumActionPopup.setLike(z);
        forumActionPopup.setPopItemOnClickListener(new ForumActionPopup.OnPopItemOnClickListener() {
            public void onItemClick(int i, boolean z) {
                switch (i) {
                    case 0:
                        GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_SHARE, homeForumBean.tid);
                        if (HomeThreadAdapter.this.onShareListener != null) {
                            HomeThreadAdapter.this.onShareListener.onShare(str, appUrl);
                            return;
                        }
                        return;
                    case 1:
                        GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_COMMENT, homeForumBean.tid);
                        CommentsActivity.jump(HomeThreadAdapter.this.mFragment, homeForumBean.fid, homeForumBean.tid);
                        return;
                    case 2:
                        if (LoginManager.getInstance().hasLogin()) {
                            GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_LIKE, homeForumBean.tid);
                            homeForumBean.thumbupstatus = z ? 1 : 0;
                            homeForumBean.thumbupcount += z ? 1 : -1;
                            ApiClient.thumbUp(homeForumBean.tid + "", LoginManager.getInstance().getUserId());
                            return;
                        }
                        HomeThreadAdapter.this.mFragment.gotoAccount();
                        return;
                    default:
                        return;
                }
            }
        });
        forumActionPopup.setmDissmissListener(new ForumActionPopup.OnDissmissListener() {
            public void onDissmiss() {
                baseForumHolder.forumItemForumMore.setImageResource(R.drawable.bbs_forum_more);
            }
        });
        baseForumHolder.forumItemForumMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!forumActionPopup.isShowing()) {
                    forumActionPopup.show(baseForumHolder.forumItemForumMore);
                    baseForumHolder.forumItemForumMore.setImageResource(R.drawable.bbs_forum_more_close);
                }
            }
        });
        baseForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent("home", "click_thread", homeForumBean.tid);
                WebActivity.jump(HomeThreadAdapter.this.mContext, appUrl);
            }
        });
    }

    private void bindForYouRecHolder(ForyouCommendHolder foryouCommendHolder, HomeData homeData) {
        ArrayList<ForYouRecoomend> forYouRecoomends = homeData.getForYouRecoomends();
        if (forYouRecoomends != null) {
            foryouCommendHolder.itemAdapter.setData(forYouRecoomends);
        }
    }

    private void bindForYouEmptyRecHolder(ForYouEmptyHolder forYouEmptyHolder) {
        forYouEmptyHolder.tvFollowForYou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FORU, Constants.ClickEvent.CLICK_PERSONALIZE, "click_personalize_start2");
                    HomeThreadAdapter.this.mContext.startActivity(new Intent(HomeThreadAdapter.this.mContext, QuestionActivity.class));
                    return;
                }
                HomeThreadAdapter.this.mFragment.gotoAccount();
            }
        });
    }

    public class ForumHeader extends RecyclerView.ViewHolder {
        @BindView(2131493315)
        TextView forumItemFollowerBt;
        @BindView(2131493316)
        TextView forumItemFollowerCount;
        @BindView(2131493317)
        ImageView forumItemIcon;
        @BindView(2131493330)
        TextView forumItemThreadCount;
        @BindView(2131493331)
        TextView forumItemTitle;
        @BindView(2131493332)
        ImageView forumItemTopBg;
        @BindView(2131493333)
        SlidingTabStripView forumItemTopStrip;

        public ForumHeader(View view) {
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

    public class ForumVideoHolder extends BaseForumHolder {
        @BindView(2131493326)
        ImageView forumItemPlayBt;
        @BindView(2131493339)
        ImageView forumItemVideoPic;

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
        @BindView(2131493412)
        TextView forumItemActionCommentText;
        @BindView(2131493413)
        ImageView forumItemForumMore;
        @BindView(2131493331)
        TextView forumItemTitle;
        @BindView(2131493414)
        TextView forumItemType;
        @BindView(2131493411)
        TextView forumItemUserName;

        public BaseForumHolder(View view) {
            super(view);
        }
    }

    public class ForyouCommendHolder extends RecyclerView.ViewHolder {
        ForyouRecommendItemAdapter itemAdapter;
        @BindView(2131493440)
        RecyclerView recommendRecycleView;

        public ForyouCommendHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.itemAdapter = new ForyouRecommendItemAdapter(HomeThreadAdapter.this.mContext);
            this.recommendRecycleView.setAdapter(this.itemAdapter);
            ViewGroup.LayoutParams layoutParams = this.recommendRecycleView.getLayoutParams();
            layoutParams.height = Coder.a(HomeThreadAdapter.this.mContext, 100.0f);
            this.recommendRecycleView.setLayoutParams(layoutParams);
            this.recommendRecycleView.setLayoutManager(new WrapContentLinearLayoutManager(HomeThreadAdapter.this.mContext, 0, false));
        }
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    public void setOnClickForYouCusListener(OnClickForYouCusListener onClickForYouCusListener2) {
        this.onClickForYouCusListener = onClickForYouCusListener2;
    }
}
