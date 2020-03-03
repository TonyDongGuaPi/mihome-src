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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.mi.global.bbs.inter.OnTakePhotoListener;
import com.mi.global.bbs.model.ColumnDetailData;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.user.ColumnFollowersActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.HeadLogoView;
import java.util.ArrayList;
import java.util.List;

public class ColumnDetailAdapter extends BaseLoadMoreAdapter {
    /* access modifiers changed from: private */
    public Context context;
    OnFollowListener mOnFollowListener;
    /* access modifiers changed from: private */
    public OnShareListener onShareListener;
    /* access modifiers changed from: private */
    public OnTakePhotoListener onTakePhotoListener;
    /* access modifiers changed from: private */
    public String page_name;
    public List<ColumnDetailData> threadlist = new ArrayList();

    public class ColumnTitleHolder_ViewBinding implements Unbinder {
        private ColumnTitleHolder target;

        @UiThread
        public ColumnTitleHolder_ViewBinding(ColumnTitleHolder columnTitleHolder, View view) {
            this.target = columnTitleHolder;
            columnTitleHolder.columnDetailTitleBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_detail_top_bg, "field 'columnDetailTitleBg'", ImageView.class);
            columnTitleHolder.tvColumnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.column_detail_title, "field 'tvColumnTitle'", TextView.class);
            columnTitleHolder.tvColumnDes = (TextView) Utils.findRequiredViewAsType(view, R.id.column_detail_description, "field 'tvColumnDes'", TextView.class);
            columnTitleHolder.columnFollowerBt = (TextView) Utils.findRequiredViewAsType(view, R.id.column_detail_follower_bt, "field 'columnFollowerBt'", TextView.class);
            columnTitleHolder.ivTakePhoto = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_detail_camera, "field 'ivTakePhoto'", ImageView.class);
            columnTitleHolder.layoutFollowers = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_column_detail_follower, "field 'layoutFollowers'", RelativeLayout.class);
            columnTitleHolder.tvColumnFollwers = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_column_followers, "field 'tvColumnFollwers'", TextView.class);
            columnTitleHolder.columnDetailFollower = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.column_detail_follower_container, "field 'columnDetailFollower'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ColumnTitleHolder columnTitleHolder = this.target;
            if (columnTitleHolder != null) {
                this.target = null;
                columnTitleHolder.columnDetailTitleBg = null;
                columnTitleHolder.tvColumnTitle = null;
                columnTitleHolder.tvColumnDes = null;
                columnTitleHolder.columnFollowerBt = null;
                columnTitleHolder.ivTakePhoto = null;
                columnTitleHolder.layoutFollowers = null;
                columnTitleHolder.tvColumnFollwers = null;
                columnTitleHolder.columnDetailFollower = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ColumnItemHolder_ViewBinding implements Unbinder {
        private ColumnItemHolder target;

        @UiThread
        public ColumnItemHolder_ViewBinding(ColumnItemHolder columnItemHolder, View view) {
            this.target = columnItemHolder;
            columnItemHolder.columnItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.column_detail_item_user_icon, "field 'columnItemUserIcon'", HeadLogoView.class);
            columnItemHolder.columnItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.column_detail_item_user_name, "field 'columnItemUserName'", TextView.class);
            columnItemHolder.columnItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.column_detail_item_post_time, "field 'columnItemPostTime'", TextView.class);
            columnItemHolder.columnItemPostTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_title, "field 'columnItemPostTitle'", TextView.class);
            columnItemHolder.columnItemPostPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_article_item_pic, "field 'columnItemPostPic'", ImageView.class);
            columnItemHolder.columnItemPostDes = (TextView) Utils.findRequiredViewAsType(view, R.id.column_article_item_des, "field 'columnItemPostDes'", TextView.class);
            columnItemHolder.columnItemActionShare = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_share, "field 'columnItemActionShare'", FrameLayout.class);
            columnItemHolder.columnItemActionCommentText = (TextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment_text, "field 'columnItemActionCommentText'", TextView.class);
            columnItemHolder.columnItemActionComment = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_comment, "field 'columnItemActionComment'", FrameLayout.class);
            columnItemHolder.columnItemActionLikeText = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like_text, "field 'columnItemActionLikeText'", CheckedTextView.class);
            columnItemHolder.columnItemActionLike = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.forum_item_action_like, "field 'columnItemActionLike'", FrameLayout.class);
        }

        @CallSuper
        public void unbind() {
            ColumnItemHolder columnItemHolder = this.target;
            if (columnItemHolder != null) {
                this.target = null;
                columnItemHolder.columnItemUserIcon = null;
                columnItemHolder.columnItemUserName = null;
                columnItemHolder.columnItemPostTime = null;
                columnItemHolder.columnItemPostTitle = null;
                columnItemHolder.columnItemPostPic = null;
                columnItemHolder.columnItemPostDes = null;
                columnItemHolder.columnItemActionShare = null;
                columnItemHolder.columnItemActionCommentText = null;
                columnItemHolder.columnItemActionComment = null;
                columnItemHolder.columnItemActionLikeText = null;
                columnItemHolder.columnItemActionLike = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public ColumnDetailAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading, String str) {
        super(activity, dataLoading);
        this.context = activity;
        this.page_name = str;
    }

    public List<ColumnDetailData> getThreadlist() {
        return this.threadlist;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new ColumnTitleHolder(this.layoutInflater.inflate(R.layout.bbs_column_detail_item_title, viewGroup, false));
            case 2:
                return new ColumnItemHolder(this.layoutInflater.inflate(R.layout.bbs_column_detail_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        switch (getItemViewType(i)) {
            case 1:
                bindTitleHolder((ColumnTitleHolder) viewHolder, i);
                return;
            case 2:
                bindColumnItemHolder((ColumnItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    private void bindTitleHolder(ColumnTitleHolder columnTitleHolder, final int i) {
        final ColumnDetailData columnDetailData = this.threadlist.get(i);
        final ColumnDetailModel.DataBean.ColumnInfo columnInfo = columnDetailData.getColumnInfo();
        columnTitleHolder.tvColumnTitle.setText(columnInfo.name);
        columnTitleHolder.tvColumnDes.setText(columnInfo.info);
        ImageLoader.showImage(columnTitleHolder.columnDetailTitleBg, columnInfo.background);
        columnTitleHolder.columnFollowerBt.setVisibility(0);
        if (columnDetailData.subcribStatus) {
            columnTitleHolder.columnFollowerBt.setText(R.string.str_subscribed);
        } else {
            columnTitleHolder.columnFollowerBt.setText(R.string.str_subscribe);
        }
        columnTitleHolder.columnFollowerBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginManager.getInstance().hasLogin()) {
                    String access$000 = ColumnDetailAdapter.this.page_name;
                    GoogleTrackerUtil.sendRecordEvent(access$000, Constants.ClickEvent.CLICK_COLUMN_SUBSCRIBE, columnInfo.columnID + "");
                    if (ColumnDetailAdapter.this.mOnFollowListener != null) {
                        OnFollowListener onFollowListener = ColumnDetailAdapter.this.mOnFollowListener;
                        int i = i;
                        onFollowListener.onFollow(i, columnInfo.columnID + "", columnDetailData.subcribStatus);
                        return;
                    }
                    return;
                }
                ((BaseActivity) ColumnDetailAdapter.this.context).gotoAccount();
            }
        });
        if (!LoginManager.getInstance().hasLogin()) {
            columnTitleHolder.ivTakePhoto.setVisibility(8);
        } else if (String.valueOf(columnInfo.ownerID).equals(LoginManager.getInstance().getUserId())) {
            columnTitleHolder.ivTakePhoto.setVisibility(0);
            columnTitleHolder.ivTakePhoto.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ColumnDetailAdapter.this.onTakePhotoListener != null) {
                        OnTakePhotoListener access$200 = ColumnDetailAdapter.this.onTakePhotoListener;
                        access$200.onTakePhoto(columnInfo.columnID + "");
                    }
                }
            });
        } else {
            columnTitleHolder.ivTakePhoto.setVisibility(8);
        }
        TextHelper.setQuantityString(this.context, columnTitleHolder.tvColumnFollwers, R.plurals._followers, String.valueOf(columnInfo.subscribeCount));
        if (columnDetailData.getFollowers() == null || columnDetailData.getFollowers().size() <= 0) {
            columnTitleHolder.layoutFollowers.setVisibility(8);
            return;
        }
        columnTitleHolder.layoutFollowers.setVisibility(0);
        columnTitleHolder.columnDetailFollower.removeAllViews();
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.follower_icon_size);
        int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen.follower_icon_top_margin);
        int dimensionPixelSize3 = this.context.getResources().getDimensionPixelSize(R.dimen.follower_icon_margin);
        int i2 = 0;
        while (i2 < columnDetailData.getFollowers().size() && i2 < 7) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 1.0f);
            layoutParams.topMargin = dimensionPixelSize2;
            layoutParams.bottomMargin = dimensionPixelSize3;
            if (i2 == 6) {
                layoutParams.rightMargin = 0;
            } else {
                layoutParams.rightMargin = dimensionPixelSize3;
            }
            if (i2 == 0) {
                layoutParams.leftMargin = dimensionPixelSize3;
            } else {
                layoutParams.leftMargin = 0;
            }
            ColumnDetailModel.DataBean.SubcribUser subcribUser = columnDetailData.getFollowers().get(i2);
            CircleImageView circleImageView = new CircleImageView(this.context);
            if (!TextUtils.isEmpty(subcribUser.icon)) {
                ImageLoader.showHeadIcon(circleImageView, subcribUser.icon);
            }
            circleImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Context access$100 = ColumnDetailAdapter.this.context;
                    ColumnFollowersActivity.show(access$100, columnInfo.columnID + "");
                }
            });
            columnTitleHolder.columnDetailFollower.addView(circleImageView, layoutParams);
            i2++;
        }
        columnTitleHolder.layoutFollowers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context access$100 = ColumnDetailAdapter.this.context;
                ColumnFollowersActivity.show(access$100, columnInfo.columnID + "");
            }
        });
    }

    private void bindColumnItemHolder(ColumnItemHolder columnItemHolder, int i) {
        final ColumnHomeModel.DataBean.ColumnArticle columnArticle = this.threadlist.get(i).getColumnArticle();
        ImageLoader.showHeadLogo(columnItemHolder.columnItemUserIcon, columnArticle.authorImg, columnArticle.showlogo, columnArticle.groupid);
        columnItemHolder.columnItemUserName.setText(columnArticle.author);
        columnItemHolder.columnItemPostTime.setText(columnArticle.posttimeStr);
        final String str = columnArticle.subject;
        columnItemHolder.columnItemPostTitle.setText(str);
        ImageLoader.showImage(columnItemHolder.columnItemPostPic, columnArticle.bgimg);
        columnItemHolder.columnItemPostDes.setText(columnArticle.brief);
        if (columnArticle.replies == 0) {
            columnItemHolder.columnItemActionCommentText.setText(this.context.getString(R.string.comment));
        } else {
            TextView textView = columnItemHolder.columnItemActionCommentText;
            textView.setText(columnArticle.replies + "");
        }
        columnItemHolder.columnItemActionLikeText.setToggleAble(true);
        if (columnArticle.thumbpCount == 0) {
            columnItemHolder.columnItemActionLikeText.setText(this.context.getString(R.string.like));
        } else {
            CheckedTextView checkedTextView = columnItemHolder.columnItemActionLikeText;
            checkedTextView.setText(columnArticle.thumbpCount + "");
        }
        columnItemHolder.columnItemActionLikeText.setChecked(columnArticle.thumbpStatus);
        columnItemHolder.columnItemActionLikeText.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                if (LoginManager.getInstance().hasLogin()) {
                    GoogleTrackerUtil.sendRecordEvent(ColumnDetailAdapter.this.page_name, Constants.ClickEvent.CLICK_LIKE, columnArticle.tid + "");
                    columnArticle.thumbpStatus = z;
                    columnArticle.thumbpCount += z ? 1 : -1;
                    if (columnArticle.thumbpCount == 0) {
                        checkedTextView.setText(ColumnDetailAdapter.this.context.getString(R.string.like));
                    } else {
                        checkedTextView.setText(columnArticle.thumbpCount + "");
                    }
                    ApiClient.thumbUp(columnArticle.tid + "", LoginManager.getInstance().getUserId());
                    return;
                }
                checkedTextView.setChecked(false);
                ((BaseActivity) ColumnDetailAdapter.this.context).gotoAccount();
            }
        });
        columnItemHolder.columnItemActionComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GoogleTrackerUtil.sendRecordEvent(ColumnDetailAdapter.this.page_name, Constants.ClickEvent.CLICK_COMMENT, columnArticle.tid + "");
                CommentsActivity.jump((BaseActivity) ColumnDetailAdapter.this.context, columnArticle.fid + "", columnArticle.tid + "");
            }
        });
        final String appUrl = ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{Long.valueOf(columnArticle.tid)}));
        columnItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String access$000 = ColumnDetailAdapter.this.page_name;
                GoogleTrackerUtil.sendRecordEvent(access$000, "click_thread", columnArticle.tid + "");
                WebActivity.jump(ColumnDetailAdapter.this.context, appUrl);
            }
        });
        columnItemHolder.columnItemActionShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ColumnDetailAdapter.this.onShareListener != null) {
                    String access$000 = ColumnDetailAdapter.this.page_name;
                    GoogleTrackerUtil.sendRecordEvent(access$000, Constants.ClickEvent.CLICK_SHARE, columnArticle.tid + "");
                    ColumnDetailAdapter.this.onShareListener.onShare(str, appUrl);
                }
            }
        });
        columnItemHolder.columnItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(ColumnDetailAdapter.this.context, columnArticle.authorID);
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

    public void setData(List<ColumnDetailData> list) {
        if (list != null) {
            this.threadlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        this.threadlist.clear();
    }

    public void setOnShareListener(OnShareListener onShareListener2) {
        this.onShareListener = onShareListener2;
    }

    public void setOnTakePhotoListener(OnTakePhotoListener onTakePhotoListener2) {
        this.onTakePhotoListener = onTakePhotoListener2;
    }

    public class ColumnTitleHolder extends RecyclerView.ViewHolder {
        @BindView(2131493099)
        LinearLayout columnDetailFollower;
        @BindView(2131493106)
        ImageView columnDetailTitleBg;
        @BindView(2131493098)
        TextView columnFollowerBt;
        @BindView(2131493096)
        ImageView ivTakePhoto;
        @BindView(2131493549)
        RelativeLayout layoutFollowers;
        @BindView(2131493097)
        TextView tvColumnDes;
        @BindView(2131494123)
        TextView tvColumnFollwers;
        @BindView(2131493105)
        TextView tvColumnTitle;

        public ColumnTitleHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ColumnItemHolder extends RecyclerView.ViewHolder {
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
        @BindView(2131493101)
        TextView columnItemPostTime;
        @BindView(2131493092)
        TextView columnItemPostTitle;
        @BindView(2131493102)
        HeadLogoView columnItemUserIcon;
        @BindView(2131493103)
        TextView columnItemUserName;

        public ColumnItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
