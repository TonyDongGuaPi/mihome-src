package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.mi.global.bbs.model.ColumnHomeData;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.model.HomeFormTitle;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.column.ColumnAllActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.util.Coder;
import java.util.ArrayList;
import java.util.List;

public class HotTopicsAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public OnShareListener onShareListener;
    /* access modifiers changed from: private */
    public String page_name;
    public List<ColumnHomeData> threadlist = new ArrayList();

    public class HotColumnHolder_ViewBinding implements Unbinder {
        private HotColumnHolder target;

        @UiThread
        public HotColumnHolder_ViewBinding(HotColumnHolder hotColumnHolder, View view) {
            this.target = hotColumnHolder;
            hotColumnHolder.topicRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.id_recyclerview_horizontal, "field 'topicRecycleView'", RecyclerView.class);
        }

        @CallSuper
        public void unbind() {
            HotColumnHolder hotColumnHolder = this.target;
            if (hotColumnHolder != null) {
                this.target = null;
                hotColumnHolder.topicRecycleView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class HotTopicTitleHolder_ViewBinding implements Unbinder {
        private HotTopicTitleHolder target;

        @UiThread
        public HotTopicTitleHolder_ViewBinding(HotTopicTitleHolder hotTopicTitleHolder, View view) {
            this.target = hotTopicTitleHolder;
            hotTopicTitleHolder.tvColumnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_column_title, "field 'tvColumnTitle'", TextView.class);
            hotTopicTitleHolder.tvColumnTitleMore = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_column_title_more, "field 'tvColumnTitleMore'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            HotTopicTitleHolder hotTopicTitleHolder = this.target;
            if (hotTopicTitleHolder != null) {
                this.target = null;
                hotTopicTitleHolder.tvColumnTitle = null;
                hotTopicTitleHolder.tvColumnTitleMore = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class HotArticleHolder_ViewBinding implements Unbinder {
        private HotArticleHolder target;

        @UiThread
        public HotArticleHolder_ViewBinding(HotArticleHolder hotArticleHolder, View view) {
            this.target = hotArticleHolder;
            hotArticleHolder.columnItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.column_article_item_user_icon, "field 'columnItemUserIcon'", HeadLogoView.class);
            hotArticleHolder.columnItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_user_name, "field 'columnItemUserName'", TextView.class);
            hotArticleHolder.columnItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_post_time, "field 'columnItemPostTime'", TextView.class);
            hotArticleHolder.columnItemPostType = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_post_type, "field 'columnItemPostType'", TextView.class);
            hotArticleHolder.columnItemPostTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_title, "field 'columnItemPostTitle'", TextView.class);
            hotArticleHolder.columnItemPostPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_article_item_pic, "field 'columnItemPostPic'", ImageView.class);
            hotArticleHolder.columnItemPostDes = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_des, "field 'columnItemPostDes'", TextView.class);
            hotArticleHolder.columnItemActionShare = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_share, "field 'columnItemActionShare'", FrameLayout.class);
            hotArticleHolder.columnItemActionCommentText = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment_text, "field 'columnItemActionCommentText'", TextView.class);
            hotArticleHolder.columnItemActionComment = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment, "field 'columnItemActionComment'", FrameLayout.class);
            hotArticleHolder.columnItemActionLikeText = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like_text, "field 'columnItemActionLikeText'", CheckedTextView.class);
            hotArticleHolder.columnItemActionLike = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like, "field 'columnItemActionLike'", FrameLayout.class);
        }

        @CallSuper
        public void unbind() {
            HotArticleHolder hotArticleHolder = this.target;
            if (hotArticleHolder != null) {
                this.target = null;
                hotArticleHolder.columnItemUserIcon = null;
                hotArticleHolder.columnItemUserName = null;
                hotArticleHolder.columnItemPostTime = null;
                hotArticleHolder.columnItemPostType = null;
                hotArticleHolder.columnItemPostTitle = null;
                hotArticleHolder.columnItemPostPic = null;
                hotArticleHolder.columnItemPostDes = null;
                hotArticleHolder.columnItemActionShare = null;
                hotArticleHolder.columnItemActionCommentText = null;
                hotArticleHolder.columnItemActionComment = null;
                hotArticleHolder.columnItemActionLikeText = null;
                hotArticleHolder.columnItemActionLike = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HotTopicsAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(activity, dataLoading);
        this.context = activity;
        this.page_name = str;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new HotTopicTitleHolder(this.layoutInflater.inflate(R.layout.bbs_column_topic_title, viewGroup, false));
            case 2:
                return new HotColumnHolder(this.layoutInflater.inflate(R.layout.bbs_column_topic_recycle, viewGroup, false));
            case 3:
                return new HotArticleHolder(this.layoutInflater.inflate(R.layout.bbs_column_home_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 1:
                bindTitleHolder((HotTopicTitleHolder) viewHolder, i);
                return;
            case 2:
                bindTopicHolder((HotColumnHolder) viewHolder, i);
                return;
            case 3:
                bindArticleHolder((HotArticleHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindTitleHolder(HotTopicTitleHolder hotTopicTitleHolder, int i) {
        HomeFormTitle formTitle = this.threadlist.get(i).getFormTitle();
        if (formTitle != null) {
            hotTopicTitleHolder.tvColumnTitle.setText(formTitle.getTitle());
            if (!TextUtils.isEmpty(formTitle.getLink())) {
                hotTopicTitleHolder.tvColumnTitleMore.setVisibility(0);
                hotTopicTitleHolder.tvColumnTitleMore.setText(hotTopicTitleHolder.tvColumnTitleMore.getResources().getString(R.string.str_more));
                hotTopicTitleHolder.tvColumnTitleMore.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ColumnAllActivity.jump(HotTopicsAdapter.this.context);
                    }
                });
                return;
            }
            hotTopicTitleHolder.tvColumnTitleMore.setVisibility(8);
        }
    }

    private void bindTopicHolder(HotColumnHolder hotColumnHolder, int i) {
        List<ColumnHomeModel.DataBean.ColumnRecommend> topicColumnBeenList = this.threadlist.get(i).getTopicColumnBeenList();
        if (topicColumnBeenList != null) {
            hotColumnHolder.topicColumnAdapter.setData(topicColumnBeenList);
        }
    }

    private void bindArticleHolder(HotArticleHolder hotArticleHolder, int i) {
        final ColumnHomeModel.DataBean.ColumnArticle topicArticleBean = this.threadlist.get(i).getTopicArticleBean();
        ImageLoader.showHeadLogo(hotArticleHolder.columnItemUserIcon, topicArticleBean.authorImg, topicArticleBean.showlogo, topicArticleBean.groupid);
        hotArticleHolder.columnItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = HotTopicsAdapter.this.context;
                UserCenterActivity.jump(access$000, topicArticleBean.authorID + "");
            }
        });
        hotArticleHolder.columnItemUserName.setText(topicArticleBean.author);
        hotArticleHolder.columnItemUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = HotTopicsAdapter.this.context;
                UserCenterActivity.jump(access$000, topicArticleBean.authorID + "");
            }
        });
        hotArticleHolder.columnItemPostType.setText(topicArticleBean.columnName);
        hotArticleHolder.columnItemPostType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$000 = HotTopicsAdapter.this.context;
                ColumnDetailActivity.jumpWithId(access$000, topicArticleBean.columnID + "");
            }
        });
        hotArticleHolder.columnItemPostTime.setText(topicArticleBean.posttimeStr);
        final String str = topicArticleBean.subject;
        hotArticleHolder.columnItemPostTitle.setText(str);
        ImageLoader.showImage(hotArticleHolder.columnItemPostPic, topicArticleBean.bgimg);
        hotArticleHolder.columnItemPostDes.setText(topicArticleBean.brief);
        if (topicArticleBean.replies == 0) {
            hotArticleHolder.columnItemActionCommentText.setText(this.context.getString(R.string.comment));
        } else {
            TextView textView = hotArticleHolder.columnItemActionCommentText;
            textView.setText(topicArticleBean.replies + "");
        }
        hotArticleHolder.columnItemActionLikeText.setToggleAble(true);
        if (topicArticleBean.thumbpCount == 0) {
            hotArticleHolder.columnItemActionLikeText.setText(this.context.getString(R.string.like));
        } else {
            CheckedTextView checkedTextView = hotArticleHolder.columnItemActionLikeText;
            checkedTextView.setText(topicArticleBean.thumbpCount + "");
        }
        hotArticleHolder.columnItemActionLikeText.setChecked(topicArticleBean.thumbpStatus);
        hotArticleHolder.columnItemActionLikeText.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent(HotTopicsAdapter.this.page_name, Constants.ClickEvent.CLICK_LIKE, topicArticleBean.tid + "");
                    topicArticleBean.thumbpStatus = z;
                    topicArticleBean.thumbpCount += z ? 1 : -1;
                    if (topicArticleBean.thumbpCount == 0) {
                        checkedTextView.setText(HotTopicsAdapter.this.context.getString(R.string.like));
                    } else {
                        checkedTextView.setText(topicArticleBean.thumbpCount + "");
                    }
                    ApiClient.thumbUp(topicArticleBean.tid + "", LoginManager.getInstance().getUserId());
                    return;
                }
                checkedTextView.setChecked(false);
                ((BaseActivity) HotTopicsAdapter.this.context).gotoAccount();
            }
        });
        hotArticleHolder.columnItemActionComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(HotTopicsAdapter.this.page_name, Constants.ClickEvent.CLICK_COMMENT, topicArticleBean.tid + "");
                CommentsActivity.jump((BaseActivity) HotTopicsAdapter.this.context, topicArticleBean.fid + "", topicArticleBean.tid + "");
            }
        });
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{Long.valueOf(topicArticleBean.tid)}));
        hotArticleHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String access$100 = HotTopicsAdapter.this.page_name;
                GoogleTrackerUtil.sendRecordEvent(access$100, "click_thread", topicArticleBean.tid + "");
                WebActivity.jump(HotTopicsAdapter.this.context, appUrl);
            }
        });
        hotArticleHolder.columnItemActionShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HotTopicsAdapter.this.onShareListener != null) {
                    String access$100 = HotTopicsAdapter.this.page_name;
                    GoogleTrackerUtil.sendRecordEvent(access$100, Constants.ClickEvent.CLICK_SHARE, topicArticleBean.tid + "");
                    HotTopicsAdapter.this.onShareListener.onShare(str, appUrl);
                }
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

    public void setData(ArrayList<ColumnHomeData> arrayList) {
        if (arrayList != null) {
            this.threadlist.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.threadlist.clear();
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    public class HotTopicTitleHolder extends RecyclerView.ViewHolder {
        @BindView(2131494124)
        TextView tvColumnTitle;
        @BindView(2131494125)
        TextView tvColumnTitleMore;

        public HotTopicTitleHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class HotColumnHolder extends RecyclerView.ViewHolder {
        HotTopicColumnAdapter topicColumnAdapter;
        @BindView(2131493440)
        RecyclerView topicRecycleView;

        public HotColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.topicColumnAdapter = new HotTopicColumnAdapter(HotTopicsAdapter.this.context);
            this.topicRecycleView.setAdapter(this.topicColumnAdapter);
            ViewGroup.LayoutParams layoutParams = this.topicRecycleView.getLayoutParams();
            layoutParams.height = Coder.a(HotTopicsAdapter.this.context, 195.0f);
            this.topicRecycleView.setLayoutParams(layoutParams);
            this.topicRecycleView.setLayoutManager(new WrapContentLinearLayoutManager(HotTopicsAdapter.this.context, 0, false));
        }
    }

    public class HotArticleHolder extends RecyclerView.ViewHolder {
        @BindView(2131493303)
        FrameLayout columnItemActionComment;
        @BindView(2131493304)
        TextView columnItemActionCommentText;
        @BindView(2131493305)
        FrameLayout columnItemActionLike;
        @BindView(2131493306)
        CheckedTextView columnItemActionLikeText;
        @BindView(2131493307)
        FrameLayout columnItemActionShare;
        @BindView(2131493088)
        TextView columnItemPostDes;
        @BindView(2131493089)
        ImageView columnItemPostPic;
        @BindView(2131493090)
        TextView columnItemPostTime;
        @BindView(2131493092)
        TextView columnItemPostTitle;
        @BindView(2131493091)
        TextView columnItemPostType;
        @BindView(2131493093)
        HeadLogoView columnItemUserIcon;
        @BindView(2131493094)
        TextView columnItemUserName;

        public HotArticleHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
