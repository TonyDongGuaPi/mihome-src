package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.DialogTextAdapter;
import com.mi.global.bbs.adapter.ForumCommentsAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.inter.LinkClickHandler;
import com.mi.global.bbs.inter.OnFollowColumnListener;
import com.mi.global.bbs.inter.PostVoteListener;
import com.mi.global.bbs.model.Attach;
import com.mi.global.bbs.model.BaseForumCommentsBean;
import com.mi.global.bbs.model.CommentAction;
import com.mi.global.bbs.model.PostDataItem;
import com.mi.global.bbs.model.PostDetailModel;
import com.mi.global.bbs.service.MiDownloader;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.HeadLogoView;
import com.taobao.weex.annotation.JSMethod;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.sufficientlysecure.htmltextview.LocalLinkMovementMethod;

public class PostDetailAdapter extends RecyclerView.Adapter {
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_BAN = "Ban";
    private static final String KEY_DELETE = "Delete";
    private static final String KEY_EDIT = "Edit";
    private static final String KEY_FACE = "face";
    private static final String KEY_IMG = "img";
    private static final String KEY_MESSAGE = "bbs_message";
    private static final String KEY_QUOTE = "quote";
    private static final String KEY_TXT = "txt";
    private LinearLayout.LayoutParams actionParams;
    private int imgHeight = 0;
    private LinearLayout.LayoutParams imgParams;
    private List<PostDataItem> items = new ArrayList();
    private LayoutInflater layoutInflater;
    /* access modifiers changed from: private */
    public LinkClickHandler linkClickHandler;
    /* access modifiers changed from: private */
    public Activity mContext;
    /* access modifiers changed from: private */
    public OnFollowColumnListener mOnFollowColunListener;
    /* access modifiers changed from: private */
    public OnFollowListener mOnFollowListener;
    private int margin = 0;
    /* access modifiers changed from: private */
    public ForumCommentsAdapter.onBanClickListener onBanClickListener;
    /* access modifiers changed from: private */
    public ForumCommentsAdapter.OnReplyListener onReplyListener;
    private int padding;
    /* access modifiers changed from: private */
    public List<String> picList = new ArrayList();
    private LinearLayout.LayoutParams txtParams;
    /* access modifiers changed from: private */
    public PostVoteListener voteListener;

    public class PostItemImageHolder_ViewBinding implements Unbinder {
        private PostItemImageHolder target;

        @UiThread
        public PostItemImageHolder_ViewBinding(PostItemImageHolder postItemImageHolder, View view) {
            this.target = postItemImageHolder;
            postItemImageHolder.postDetailImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.post_detail_image, "field 'postDetailImg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PostItemImageHolder postItemImageHolder = this.target;
            if (postItemImageHolder != null) {
                this.target = null;
                postItemImageHolder.postDetailImg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostItemTextHolder_ViewBinding implements Unbinder {
        private PostItemTextHolder target;

        @UiThread
        public PostItemTextHolder_ViewBinding(PostItemTextHolder postItemTextHolder, View view) {
            this.target = postItemTextHolder;
            postItemTextHolder.postHtmlText = (HtmlTextView) Utils.findRequiredViewAsType(view, R.id.post_detail_text, "field 'postHtmlText'", HtmlTextView.class);
        }

        @CallSuper
        public void unbind() {
            PostItemTextHolder postItemTextHolder = this.target;
            if (postItemTextHolder != null) {
                this.target = null;
                postItemTextHolder.postHtmlText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostPastColumnHeaderItemHolder_ViewBinding implements Unbinder {
        private PostPastColumnHeaderItemHolder target;

        @UiThread
        public PostPastColumnHeaderItemHolder_ViewBinding(PostPastColumnHeaderItemHolder postPastColumnHeaderItemHolder, View view) {
            this.target = postPastColumnHeaderItemHolder;
            postPastColumnHeaderItemHolder.columnPastHeader = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rel_layout_column_past_header, "field 'columnPastHeader'", RelativeLayout.class);
        }

        @CallSuper
        public void unbind() {
            PostPastColumnHeaderItemHolder postPastColumnHeaderItemHolder = this.target;
            if (postPastColumnHeaderItemHolder != null) {
                this.target = null;
                postPastColumnHeaderItemHolder.columnPastHeader = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostVideoItemHolder_ViewBinding implements Unbinder {
        private PostVideoItemHolder target;

        @UiThread
        public PostVideoItemHolder_ViewBinding(PostVideoItemHolder postVideoItemHolder, View view) {
            this.target = postVideoItemHolder;
            postVideoItemHolder.postItemVideoFrame = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.post_item_video_layout, "field 'postItemVideoFrame'", FrameLayout.class);
        }

        @CallSuper
        public void unbind() {
            PostVideoItemHolder postVideoItemHolder = this.target;
            if (postVideoItemHolder != null) {
                this.target = null;
                postVideoItemHolder.postItemVideoFrame = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostItemTitleHolder_ViewBinding implements Unbinder {
        private PostItemTitleHolder target;

        @UiThread
        public PostItemTitleHolder_ViewBinding(PostItemTitleHolder postItemTitleHolder, View view) {
            this.target = postItemTitleHolder;
            postItemTitleHolder.postItemUserIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.post_item_user_icon, "field 'postItemUserIcon'", ImageView.class);
            postItemTitleHolder.postItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.post_item_user_name, "field 'postItemUserName'", TextView.class);
            postItemTitleHolder.postItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.post_item_post_time, "field 'postItemPostTime'", TextView.class);
            postItemTitleHolder.postItemSubject = (TextView) Utils.findRequiredViewAsType(view, R.id.post_item_subject, "field 'postItemSubject'", TextView.class);
            postItemTitleHolder.postItemPostViews = (TextView) Utils.findRequiredViewAsType(view, R.id.post_item_post_views, "field 'postItemPostViews'", TextView.class);
            postItemTitleHolder.ivStamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_post_stamp, "field 'ivStamp'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PostItemTitleHolder postItemTitleHolder = this.target;
            if (postItemTitleHolder != null) {
                this.target = null;
                postItemTitleHolder.postItemUserIcon = null;
                postItemTitleHolder.postItemUserName = null;
                postItemTitleHolder.postItemPostTime = null;
                postItemTitleHolder.postItemSubject = null;
                postItemTitleHolder.postItemPostViews = null;
                postItemTitleHolder.ivStamp = null;
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
            postColumnHolder.relSubItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.column_sub_suggest_item_layout, "field 'relSubItem'", RelativeLayout.class);
            postColumnHolder.column_sub_item_icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_icon, "field 'column_sub_item_icon'", ImageView.class);
            postColumnHolder.column_sub_item_title = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_title, "field 'column_sub_item_title'", TextView.class);
            postColumnHolder.column_sub_item_des = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_des, "field 'column_sub_item_des'", TextView.class);
            postColumnHolder.column_sub_item_article_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_article_count, "field 'column_sub_item_article_count'", TextView.class);
            postColumnHolder.column_sub_item_follower_count = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_item_follower_count, "field 'column_sub_item_follower_count'", TextView.class);
            postColumnHolder.tvColumnFollow = (TextView) Utils.findRequiredViewAsType(view, R.id.column_sub_follower_bt, "field 'tvColumnFollow'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            PostColumnHolder postColumnHolder = this.target;
            if (postColumnHolder != null) {
                this.target = null;
                postColumnHolder.relSubItem = null;
                postColumnHolder.column_sub_item_icon = null;
                postColumnHolder.column_sub_item_title = null;
                postColumnHolder.column_sub_item_des = null;
                postColumnHolder.column_sub_item_article_count = null;
                postColumnHolder.column_sub_item_follower_count = null;
                postColumnHolder.tvColumnFollow = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostCommentsItemHolder_ViewBinding implements Unbinder {
        private PostCommentsItemHolder target;

        @UiThread
        public PostCommentsItemHolder_ViewBinding(PostCommentsItemHolder postCommentsItemHolder, View view) {
            this.target = postCommentsItemHolder;
            postCommentsItemHolder.commentsItemTop = (TextView) Utils.findRequiredViewAsType(view, R.id.comment_item_top, "field 'commentsItemTop'", TextView.class);
            postCommentsItemHolder.commentsItemViewAll = (TextView) Utils.findRequiredViewAsType(view, R.id.comment_view_all, "field 'commentsItemViewAll'", TextView.class);
            postCommentsItemHolder.commentsItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.comments_item_user_icon, "field 'commentsItemUserIcon'", HeadLogoView.class);
            postCommentsItemHolder.commentsItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_user_name, "field 'commentsItemUserName'", TextView.class);
            postCommentsItemHolder.commentsItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_post_time, "field 'commentsItemPostTime'", TextView.class);
            postCommentsItemHolder.commentsItemPostPosition = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_post_position, "field 'commentsItemPostPosition'", TextView.class);
            postCommentsItemHolder.commentsItemPostTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_post_item_comment_top, "field 'commentsItemPostTop'", LinearLayout.class);
            postCommentsItemHolder.commentsItemPostContentContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.comments_item_post_content_container, "field 'commentsItemPostContentContainer'", LinearLayout.class);
            postCommentsItemHolder.commentsItemActionContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.comments_item_action_container, "field 'commentsItemActionContainer'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            PostCommentsItemHolder postCommentsItemHolder = this.target;
            if (postCommentsItemHolder != null) {
                this.target = null;
                postCommentsItemHolder.commentsItemTop = null;
                postCommentsItemHolder.commentsItemViewAll = null;
                postCommentsItemHolder.commentsItemUserIcon = null;
                postCommentsItemHolder.commentsItemUserName = null;
                postCommentsItemHolder.commentsItemPostTime = null;
                postCommentsItemHolder.commentsItemPostPosition = null;
                postCommentsItemHolder.commentsItemPostTop = null;
                postCommentsItemHolder.commentsItemPostContentContainer = null;
                postCommentsItemHolder.commentsItemActionContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostItemAuthorHolder_ViewBinding implements Unbinder {
        private PostItemAuthorHolder target;

        @UiThread
        public PostItemAuthorHolder_ViewBinding(PostItemAuthorHolder postItemAuthorHolder, View view) {
            this.target = postItemAuthorHolder;
            postItemAuthorHolder.layoutPostAuthor = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_post_author, "field 'layoutPostAuthor'", RelativeLayout.class);
            postItemAuthorHolder.mPostUserItemIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.post_user_item_icon, "field 'mPostUserItemIcon'", CircleImageView.class);
            postItemAuthorHolder.mPostUserItemName = (TextView) Utils.findRequiredViewAsType(view, R.id.post_user_item_name, "field 'mPostUserItemName'", TextView.class);
            postItemAuthorHolder.mPostUserItemModerator = (TextView) Utils.findRequiredViewAsType(view, R.id.post_user_item_moderator, "field 'mPostUserItemModerator'", TextView.class);
            postItemAuthorHolder.mPostUserItemFollowerBt = (TextView) Utils.findRequiredViewAsType(view, R.id.post_user_item_follower_bt, "field 'mPostUserItemFollowerBt'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            PostItemAuthorHolder postItemAuthorHolder = this.target;
            if (postItemAuthorHolder != null) {
                this.target = null;
                postItemAuthorHolder.layoutPostAuthor = null;
                postItemAuthorHolder.mPostUserItemIcon = null;
                postItemAuthorHolder.mPostUserItemName = null;
                postItemAuthorHolder.mPostUserItemModerator = null;
                postItemAuthorHolder.mPostUserItemFollowerBt = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostItemVoteHolder_ViewBinding implements Unbinder {
        private PostItemVoteHolder target;

        @UiThread
        public PostItemVoteHolder_ViewBinding(PostItemVoteHolder postItemVoteHolder, View view) {
            this.target = postItemVoteHolder;
            postItemVoteHolder.layoutVoteItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rel_vote_layout, "field 'layoutVoteItem'", RelativeLayout.class);
            postItemVoteHolder.postVoteWay = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_polls_way, "field 'postVoteWay'", TextView.class);
            postItemVoteHolder.postVoteMutilMax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_polls_way_multi_max, "field 'postVoteMutilMax'", TextView.class);
            postItemVoteHolder.postVoteUserCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_user_voted, "field 'postVoteUserCount'", TextView.class);
            postItemVoteHolder.postVoteList = (ListView) Utils.findRequiredViewAsType(view, R.id.list_vote, "field 'postVoteList'", ListView.class);
            postItemVoteHolder.postVoteSubmit = (TextView) Utils.findRequiredViewAsType(view, R.id.btn_vote_submit, "field 'postVoteSubmit'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            PostItemVoteHolder postItemVoteHolder = this.target;
            if (postItemVoteHolder != null) {
                this.target = null;
                postItemVoteHolder.layoutVoteItem = null;
                postItemVoteHolder.postVoteWay = null;
                postItemVoteHolder.postVoteMutilMax = null;
                postItemVoteHolder.postVoteUserCount = null;
                postItemVoteHolder.postVoteList = null;
                postItemVoteHolder.postVoteSubmit = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PostPastColumnItemHolder_ViewBinding implements Unbinder {
        private PostPastColumnItemHolder target;

        @UiThread
        public PostPastColumnItemHolder_ViewBinding(PostPastColumnItemHolder postPastColumnItemHolder, View view) {
            this.target = postPastColumnItemHolder;
            postPastColumnItemHolder.columnPastItem = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rel_layout_column_past, "field 'columnPastItem'", RelativeLayout.class);
            postPastColumnItemHolder.columnPastSubject = (TextView) Utils.findRequiredViewAsType(view, R.id.post_column_past_subject, "field 'columnPastSubject'", TextView.class);
            postPastColumnItemHolder.columnPastBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.past_column_bg, "field 'columnPastBg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PostPastColumnItemHolder postPastColumnItemHolder = this.target;
            if (postPastColumnItemHolder != null) {
                this.target = null;
                postPastColumnItemHolder.columnPastItem = null;
                postPastColumnItemHolder.columnPastSubject = null;
                postPastColumnItemHolder.columnPastBg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public PostDetailAdapter(Activity activity) {
        this.mContext = activity;
        this.layoutInflater = LayoutInflater.from(activity);
        initParam();
    }

    private void initParam() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.padding = (int) TypedValue.applyDimension(1, 2.0f, displayMetrics);
        this.margin = (int) TypedValue.applyDimension(1, 40.0f, displayMetrics);
        this.imgHeight = ((displayMetrics.widthPixels - ((int) TypedValue.applyDimension(1, 63.0f, displayMetrics))) * 9) / 16;
        this.txtParams = new LinearLayout.LayoutParams(-1, -2);
        this.imgParams = new LinearLayout.LayoutParams(-1, this.imgHeight);
        this.imgParams.topMargin = this.padding * 6;
        this.actionParams = new LinearLayout.LayoutParams(-2, -2);
        this.actionParams.rightMargin = this.margin;
    }

    public void setOnReplyListener(ForumCommentsAdapter.OnReplyListener onReplyListener2) {
        this.onReplyListener = onReplyListener2;
    }

    public void setOnBanClickListener(ForumCommentsAdapter.onBanClickListener onbanclicklistener) {
        this.onBanClickListener = onbanclicklistener;
    }

    public void setLinkClickHandler(LinkClickHandler linkClickHandler2) {
        this.linkClickHandler = linkClickHandler2;
    }

    public void setVoteListener(PostVoteListener postVoteListener) {
        this.voteListener = postVoteListener;
    }

    public void setOnFollowUserListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    public void setmOnFollowColunListener(OnFollowColumnListener onFollowColumnListener) {
        this.mOnFollowColunListener = onFollowColumnListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return createTextItemHolder(viewGroup);
            case 2:
                return createImageItemHolder(viewGroup);
            case 3:
                return createTitleItemHolder(viewGroup);
            case 4:
                return createVoteItemHolder(viewGroup);
            case 5:
                return createAuthorItemHolder(viewGroup);
            case 6:
                return createColumnItemHolder(viewGroup);
            case 7:
                return createCommentsItemHolder(viewGroup);
            case 8:
                return createPastColumnHeaderHolder(viewGroup);
            case 9:
                return createPastColumnItemHolder(viewGroup);
            case 10:
                return createPostItemVideoHolder(viewGroup);
            default:
                return null;
        }
    }

    private PostItemTextHolder createTextItemHolder(ViewGroup viewGroup) {
        return new PostItemTextHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_text, viewGroup, false));
    }

    private PostItemImageHolder createImageItemHolder(ViewGroup viewGroup) {
        return new PostItemImageHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_image, viewGroup, false));
    }

    private PostItemTitleHolder createTitleItemHolder(ViewGroup viewGroup) {
        return new PostItemTitleHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_title, viewGroup, false));
    }

    private PostItemVoteHolder createVoteItemHolder(ViewGroup viewGroup) {
        return new PostItemVoteHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_vote, viewGroup, false));
    }

    private PostItemAuthorHolder createAuthorItemHolder(ViewGroup viewGroup) {
        return new PostItemAuthorHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_author, viewGroup, false));
    }

    private PostColumnHolder createColumnItemHolder(ViewGroup viewGroup) {
        return new PostColumnHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_column, viewGroup, false));
    }

    private PostCommentsItemHolder createCommentsItemHolder(ViewGroup viewGroup) {
        return new PostCommentsItemHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_comments, viewGroup, false));
    }

    private PostPastColumnHeaderItemHolder createPastColumnHeaderHolder(ViewGroup viewGroup) {
        return new PostPastColumnHeaderItemHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_column_past_header, viewGroup, false));
    }

    private PostPastColumnItemHolder createPastColumnItemHolder(ViewGroup viewGroup) {
        return new PostPastColumnItemHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_column_past, viewGroup, false));
    }

    private PostVideoItemHolder createPostItemVideoHolder(ViewGroup viewGroup) {
        return new PostVideoItemHolder(this.layoutInflater.inflate(R.layout.bbs_post_item_video, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 1:
                bindTextDataHolder((PostItemTextHolder) viewHolder, i);
                return;
            case 2:
                bindImageDataHolder((PostItemImageHolder) viewHolder, i);
                return;
            case 3:
                bindTitleDataHolder((PostItemTitleHolder) viewHolder, i);
                return;
            case 4:
                bindVoteDataHolder((PostItemVoteHolder) viewHolder, i);
                return;
            case 5:
                bindAuthorDataHolder((PostItemAuthorHolder) viewHolder, i);
                return;
            case 6:
                bindColumnDataHolder((PostColumnHolder) viewHolder, i);
                return;
            case 7:
                bindCommentsItem((PostCommentsItemHolder) viewHolder, i);
                return;
            case 8:
                bindPastColumnHeaderHolder((PostPastColumnHeaderItemHolder) viewHolder, i);
                return;
            case 9:
                bindPastColumnDataHolder((PostPastColumnItemHolder) viewHolder, i);
                return;
            case 10:
                bindPostVideoDataHolder((PostVideoItemHolder) viewHolder, i);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public List<PostDataItem> getItems() {
        return this.items;
    }

    private void bindTextDataHolder(PostItemTextHolder postItemTextHolder, int i) {
        Spanned fromHtml = Html.fromHtml(this.items.get(i).getPostTxt());
        SpannableString spannableString = new SpannableString(fromHtml);
        Linkify.addLinks(spannableString, Pattern.compile("[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*"), (String) null);
        Linkify.addLinks(spannableString, 1);
        for (URLSpan uRLSpan : (URLSpan[]) fromHtml.getSpans(0, fromHtml.length(), URLSpan.class)) {
            int spanEnd = fromHtml.getSpanEnd(uRLSpan);
            int spanStart = fromHtml.getSpanStart(uRLSpan);
            spannableString.setSpan(new URLSpan(uRLSpan.getURL()) {
                public void onClick(View view) {
                    if (PostDetailAdapter.this.linkClickHandler != null) {
                        PostDetailAdapter.this.linkClickHandler.onClickLink(getURL());
                    } else {
                        super.onClick(view);
                    }
                }
            }, spanStart, spanEnd, 33);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), spanStart, spanEnd, 33);
        }
        postItemTextHolder.postHtmlText.setText(spannableString);
        postItemTextHolder.postHtmlText.setMovementMethod(LocalLinkMovementMethod.a());
    }

    private void bindImageDataHolder(PostItemImageHolder postItemImageHolder, int i) {
        final PostDataItem postDataItem = this.items.get(i);
        ImageLoader.showCenterAdjustBoundImage(postItemImageHolder.postDetailImg, postDataItem.getPostImage());
        postItemImageHolder.postDetailImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(PostDetailAdapter.this.mContext, postDataItem.getPostImage(), (String[]) PostDetailAdapter.this.picList.toArray(new String[0]));
            }
        });
    }

    private void bindTitleDataHolder(PostItemTitleHolder postItemTitleHolder, int i) {
        PostDataItem postDataItem = this.items.get(i);
        if (postDataItem.getAuthor() != null && postDataItem.getAuthor().base != null) {
            ImageLoader.showHeadIcon(postItemTitleHolder.postItemUserIcon, postDataItem.getAuthor().base.icon);
            final String str = postDataItem.getAuthor().base.uid;
            postItemTitleHolder.postItemUserIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(PostDetailAdapter.this.mContext, str);
                }
            });
            postItemTitleHolder.postItemUserName.setText(postDataItem.getAuthor().base.name);
            if (postDataItem.getThreadBean() != null) {
                postItemTitleHolder.postItemPostTime.setText(postDataItem.getThreadBean().dateline);
                postItemTitleHolder.postItemSubject.setText(postDataItem.getThreadBean().subject);
                TextHelper.setQuantityString((Context) this.mContext, postItemTitleHolder.postItemPostViews, R.plurals._total_views, postDataItem.getThreadBean().views);
                if (postDataItem.getThreadBean().stamp != null) {
                    postItemTitleHolder.ivStamp.setVisibility(0);
                    ImageLoader.showImage(postItemTitleHolder.ivStamp, postDataItem.getThreadBean().stamp.imgurl);
                    return;
                }
                postItemTitleHolder.ivStamp.setVisibility(8);
            }
        }
    }

    private void bindVoteDataHolder(PostItemVoteHolder postItemVoteHolder, final int i) {
        final PostDataItem postDataItem = this.items.get(i);
        if (postDataItem.getThreadBean() != null && postDataItem.getSpecialInfo() != null && postDataItem.getSpecialInfo().polls != null) {
            final PostDetailModel.DataBean.SpecialInfo specialInfo = postDataItem.getSpecialInfo();
            if (specialInfo.multiple == 1) {
                postItemVoteHolder.postVoteWay.setText(this.mContext.getString(R.string.multi_polls));
            } else {
                postItemVoteHolder.postVoteWay.setText(this.mContext.getString(R.string.single_polls));
            }
            final PostItemVoteAdapter postItemVoteAdapter = new PostItemVoteAdapter(this.mContext);
            postItemVoteHolder.postVoteList.setAdapter(postItemVoteAdapter);
            postItemVoteAdapter.clear();
            postItemVoteAdapter.setPollData(specialInfo.polls);
            postItemVoteAdapter.setSelectPostion(-1);
            postItemVoteAdapter.setMultiple(specialInfo.multiple == 1);
            postItemVoteAdapter.setMaxMultiChoices(specialInfo.maxchoices);
            postItemVoteAdapter.setPolled(specialInfo.polled == 1);
            postItemVoteAdapter.notifyDataSetChanged();
            int i2 = 0;
            for (int i3 = 0; i3 < postItemVoteAdapter.getCount(); i3++) {
                View view = postItemVoteAdapter.getView(i3, (View) null, postItemVoteHolder.postVoteList);
                view.measure(0, 0);
                i2 += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = postItemVoteHolder.postVoteList.getLayoutParams();
            layoutParams.height = i2 + (postItemVoteHolder.postVoteList.getDividerHeight() * (postItemVoteAdapter.getCount() - 1));
            postItemVoteHolder.postVoteList.setLayoutParams(layoutParams);
            postItemVoteHolder.postVoteList.requestLayout();
            postItemVoteHolder.layoutVoteItem.requestLayout();
            if (specialInfo.multiple == 1) {
                postItemVoteHolder.postVoteMutilMax.setVisibility(0);
                TextHelper.setQuantityString((Context) this.mContext, postItemVoteHolder.postVoteMutilMax, R.plurals._multi_max, specialInfo.maxchoices);
            } else {
                postItemVoteHolder.postVoteMutilMax.setVisibility(8);
            }
            TextHelper.setQuantityString((Context) this.mContext, postItemVoteHolder.postVoteUserCount, R.plurals._total_vote_user, specialInfo.total);
            if (specialInfo.polled == 1) {
                postItemVoteHolder.postVoteSubmit.setEnabled(false);
                postItemVoteHolder.postVoteSubmit.setText(this.mContext.getString(R.string.voted));
            } else {
                postItemVoteHolder.postVoteSubmit.setEnabled(true);
                postItemVoteHolder.postVoteSubmit.setText(this.mContext.getString(R.string.vote));
            }
            postItemVoteHolder.postVoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (specialInfo.polled != 1) {
                        postItemVoteAdapter.setSelectPostion(i);
                    }
                }
            });
            postItemVoteHolder.postVoteSubmit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PostDetailAdapter.this.voteListener == null) {
                        return;
                    }
                    if (LoginManager.getInstance().hasLogin()) {
                        PostDetailAdapter.this.voteListener.onVote(i, postDataItem.getThreadBean().fid, postDataItem.getThreadBean().tid, postItemVoteAdapter.getSelectedList());
                    } else {
                        ((BaseActivity) PostDetailAdapter.this.mContext).gotoAccount();
                    }
                }
            });
        }
    }

    private void bindAuthorDataHolder(PostItemAuthorHolder postItemAuthorHolder, final int i) {
        String str;
        PostDataItem postDataItem = this.items.get(i);
        if (postDataItem.getAuthor() != null && postDataItem.getAuthor().base != null) {
            final PostDetailModel.DataBean.Author.AuthorBean authorBean = postDataItem.getAuthor().base;
            ImageLoader.showHeadIcon(postItemAuthorHolder.mPostUserItemIcon, authorBean.icon);
            final String str2 = authorBean.uid;
            postItemAuthorHolder.layoutPostAuthor.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(PostDetailAdapter.this.mContext, str2);
                }
            });
            postItemAuthorHolder.mPostUserItemIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(PostDetailAdapter.this.mContext, str2);
                }
            });
            postItemAuthorHolder.mPostUserItemName.setText(authorBean.name);
            postItemAuthorHolder.mPostUserItemModerator.setText(authorBean.groupname);
            TextView textView = postItemAuthorHolder.mPostUserItemFollowerBt;
            if (authorBean.isfollow == 1) {
                str = this.mContext.getString(R.string.following);
            } else {
                str = "+ " + this.mContext.getString(R.string.follow);
            }
            textView.setText(str);
            postItemAuthorHolder.mPostUserItemFollowerBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PostDetailAdapter.this.mOnFollowListener != null) {
                        PostDetailAdapter.this.mOnFollowListener.onFollow(i, str2, authorBean.isfollow == 0);
                    }
                }
            });
        }
    }

    private void bindPastColumnHeaderHolder(PostPastColumnHeaderItemHolder postPastColumnHeaderItemHolder, int i) {
        PostDetailModel.DataBean.Column column = this.items.get(i).getColumn();
        if (column != null && column.column != null && column.column.size() != 0) {
            final PostDetailModel.DataBean.Column.ColumnBean columnBean = column.column.get(0);
            postPastColumnHeaderItemHolder.columnPastHeader.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Activity access$100 = PostDetailAdapter.this.mContext;
                    ColumnDetailActivity.jumpWithId(access$100, columnBean.columnid + "");
                }
            });
        }
    }

    private void bindPastColumnDataHolder(PostPastColumnItemHolder postPastColumnItemHolder, int i) {
        final PostDetailModel.DataBean.Column.ColumnThread pastColunm = this.items.get(i).getPastColunm();
        if (pastColunm != null) {
            ImageLoader.showImage(postPastColumnItemHolder.columnPastBg, pastColunm.bgimg);
            postPastColumnItemHolder.columnPastSubject.setText(pastColunm.subject);
            postPastColumnItemHolder.columnPastItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Activity access$100 = PostDetailAdapter.this.mContext;
                    ColumnDetailActivity.jumpWithId(access$100, pastColunm.columnid + "");
                }
            });
        }
    }

    private void bindPostVideoDataHolder(PostVideoItemHolder postVideoItemHolder, int i) {
        PostDataItem postDataItem = this.items.get(i);
        if (postDataItem != null) {
            postDataItem.getPostTxt();
            postVideoItemHolder.postItemVideoFrame.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
        }
    }

    private void bindColumnDataHolder(PostColumnHolder postColumnHolder, final int i) {
        final PostDetailModel.DataBean.Column column = this.items.get(i).getColumn();
        if (column != null && column.column != null && column.column.size() != 0) {
            final PostDetailModel.DataBean.Column.ColumnBean columnBean = column.column.get(0);
            ImageLoader.showImage(postColumnHolder.column_sub_item_icon, columnBean.background);
            postColumnHolder.column_sub_item_title.setText(columnBean.name);
            postColumnHolder.column_sub_item_des.setText(columnBean.info);
            TextHelper.setQuantityString((Context) this.mContext, postColumnHolder.column_sub_item_article_count, R.plurals._articles, String.valueOf(columnBean.count));
            TextHelper.setQuantityString((Context) this.mContext, postColumnHolder.column_sub_item_follower_count, R.plurals._followers, String.valueOf(columnBean.subscribeCount));
            if (column.subscribe == 1) {
                postColumnHolder.tvColumnFollow.setText(R.string.str_subscribed);
            } else {
                postColumnHolder.tvColumnFollow.setText(R.string.str_subscribe);
            }
            postColumnHolder.tvColumnFollow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!LoginManager.getInstance().hasLogin()) {
                        ((BaseActivity) PostDetailAdapter.this.mContext).gotoAccount();
                    } else if (PostDetailAdapter.this.mOnFollowListener != null) {
                        OnFollowColumnListener access$500 = PostDetailAdapter.this.mOnFollowColunListener;
                        int i = i;
                        String str = columnBean.columnid + "";
                        boolean z = true;
                        if (column.subscribe != 1) {
                            z = false;
                        }
                        access$500.onFollowColumn(i, str, z);
                    }
                }
            });
            postColumnHolder.relSubItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Activity access$100 = PostDetailAdapter.this.mContext;
                    ColumnDetailActivity.jumpWithId(access$100, columnBean.columnid + "");
                }
            });
        }
    }

    private void bindCommentsItem(PostCommentsItemHolder postCommentsItemHolder, int i) {
        final BaseForumCommentsBean.PostListBean comment = this.items.get(i).getComment();
        PostDataItem postDataItem = this.items.get(i);
        if (postDataItem.getCommentSize() > 0) {
            if (postDataItem.getCommentPostion() == 0) {
                postCommentsItemHolder.commentsItemPostTop.setVisibility(0);
            } else {
                postCommentsItemHolder.commentsItemPostTop.setVisibility(8);
            }
            if (postDataItem.getCommentPostion() == postDataItem.getCommentSize() - 1) {
                postCommentsItemHolder.commentsItemViewAll.setVisibility(0);
                postCommentsItemHolder.commentsItemViewAll.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CommentsActivity.jump((BaseActivity) PostDetailAdapter.this.mContext, comment.fid + "", comment.tid + "");
                    }
                });
            } else {
                postCommentsItemHolder.commentsItemViewAll.setVisibility(8);
            }
        }
        String str = comment.icon;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showHeadLogo(postCommentsItemHolder.commentsItemUserIcon, str, comment.showlogo, comment.groupid);
        }
        postCommentsItemHolder.commentsItemPostPosition.setText(this.mContext.getString(R.string.comment_position, new Object[]{Integer.valueOf(comment.position)}));
        postCommentsItemHolder.commentsItemPostTime.setText(comment.dateline);
        postCommentsItemHolder.commentsItemUserName.setText(comment.author);
        final String str2 = comment.authorid;
        postCommentsItemHolder.commentsItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(PostDetailAdapter.this.mContext, str2);
            }
        });
        postCommentsItemHolder.commentsItemUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(PostDetailAdapter.this.mContext, str2);
            }
        });
        if (comment.deleted == 1) {
            TextView textView = new TextView(this.mContext);
            textView.setTextSize(2, 15.0f);
            textView.setTextColor(Color.parseColor("#222222"));
            textView.setText(R.string.comment_deleted_hint);
            postCommentsItemHolder.commentsItemPostContentContainer.removeAllViews();
            postCommentsItemHolder.commentsItemActionContainer.removeAllViews();
            postCommentsItemHolder.commentsItemPostContentContainer.addView(textView, this.txtParams);
        } else if (comment.banned != 1) {
            postCommentsItemHolder.imgUrls.clear();
            postCommentsItemHolder.commentsItemActionContainer.removeAllViews();
            postCommentsItemHolder.commentsItemPostContentContainer.removeAllViews();
            handleMessageJson(postCommentsItemHolder.commentsItemPostContentContainer, comment.message, postCommentsItemHolder.imgUrls);
            handleAttach(postCommentsItemHolder.commentsItemPostContentContainer, comment.attachlist);
            addActionBt(postCommentsItemHolder.commentsItemActionContainer, comment, i);
        } else if (comment.manage == 1) {
            postCommentsItemHolder.commentsItemActionContainer.removeAllViews();
            postCommentsItemHolder.commentsItemPostContentContainer.removeAllViews();
            addBanView(postCommentsItemHolder.commentsItemPostContentContainer);
            postCommentsItemHolder.imgUrls.clear();
            handleMessageJson(postCommentsItemHolder.commentsItemPostContentContainer, comment.message, postCommentsItemHolder.imgUrls);
            handleAttach(postCommentsItemHolder.commentsItemPostContentContainer, comment.attachlist);
            addActionBt(postCommentsItemHolder.commentsItemActionContainer, comment, i);
        } else {
            TextView textView2 = new TextView(this.mContext);
            textView2.setTextSize(2, 15.0f);
            textView2.setTextColor(Color.parseColor("#222222"));
            textView2.setText(R.string.comment_ban_hint);
            postCommentsItemHolder.commentsItemPostContentContainer.removeAllViews();
            postCommentsItemHolder.commentsItemActionContainer.removeAllViews();
            postCommentsItemHolder.commentsItemPostContentContainer.addView(textView2, this.txtParams);
        }
    }

    private void handleAttach(LinearLayout linearLayout, List<Attach> list) {
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (Attach next : list) {
                if (next.isimage == 1) {
                    addImage(linearLayout, next.attachment, arrayList);
                } else {
                    View inflate = this.layoutInflater.inflate(R.layout.bbs_attach_item, linearLayout, false);
                    ((TextView) inflate.findViewById(R.id.attach_name)).setText(next.filename);
                    ((TextView) inflate.findViewById(R.id.attach_length)).setText((((float) ((int) (((((float) next.filesize) / 1024.0f) / 1024.0f) * 100.0f))) / 100.0f) + "MB");
                    final String str = next.attachment;
                    inflate.findViewById(R.id.attach_download_bt).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            PostDetailAdapter.this.downloadAttachment(str);
                        }
                    });
                    linearLayout.addView(inflate);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void downloadAttachment(String str) {
        Toast.makeText(this.mContext, String.format(this.mContext.getString(R.string.the_file_will_be_saved_at), new Object[]{FileUtils.getAttachFileDir()}), 0).show();
        MiDownloader.init(this.mContext).download(str);
    }

    private void addBanView(LinearLayout linearLayout) {
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.user_activity_item_gray_sharp));
        textView.setText(Html.fromHtml("<font color=\"#ff6702\">" + (this.mContext.getResources().getString(R.string.note) + ": ") + "</font> " + this.mContext.getResources().getString(R.string.comment_ban_hint)));
        textView.setLineSpacing(0.0f, 1.2f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = this.padding * 4;
        linearLayout.addView(textView, layoutParams);
        textView.setPadding(this.padding * 7, this.padding * 3, this.padding * 7, this.padding * 3);
    }

    private void addActionBt(LinearLayout linearLayout, BaseForumCommentsBean.PostListBean postListBean, int i) {
        linearLayout.removeAllViews();
        List parseList = JsonParser.parseList(postListBean.action.toString(), new TypeToken<List<CommentAction>>() {
        }.getType());
        addReplyOrEditBt(linearLayout, postListBean.edit == 1, postListBean, parseList);
        addLikeBt(linearLayout, postListBean);
        addManagerBt(linearLayout, postListBean.manage == 1, postListBean.banned == 1, parseList, i);
    }

    private void addLikeBt(LinearLayout linearLayout, final BaseForumCommentsBean.PostListBean postListBean) {
        CheckedTextView checkedTextView = new CheckedTextView(this.mContext);
        checkedTextView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        checkedTextView.setTextSize(2, 13.0f);
        boolean z = true;
        checkedTextView.setToggleAble(true);
        checkedTextView.setClickable(true);
        Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.like_bt_selector);
        checkedTextView.setCompoundDrawablePadding(this.padding);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        checkedTextView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        if (postListBean.thumbupstatus != 1) {
            z = false;
        }
        checkedTextView.setChecked(z);
        if (postListBean.thumbupcount == 0) {
            checkedTextView.setText(R.string.like);
        } else {
            checkedTextView.setText(postListBean.thumbupcount + "");
        }
        checkedTextView.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_POST_DETAIL, Constants.ClickEvent.CLICK_LIKE, postListBean.tid + JSMethod.NOT_SET + postListBean.position);
                postListBean.thumbupstatus = z ? 1 : 0;
                postListBean.thumbupcount += z ? 1 : -1;
                if (postListBean.thumbupcount == 0) {
                    checkedTextView.setText(PostDetailAdapter.this.mContext.getString(R.string.like));
                } else {
                    checkedTextView.setText(postListBean.thumbupcount + "");
                }
                ApiClient.thumbUpComment(postListBean.pid + "", LoginManager.getInstance().getUserId());
            }
        });
        linearLayout.addView(checkedTextView, this.actionParams);
    }

    private void addManagerBt(LinearLayout linearLayout, boolean z, boolean z2, List<CommentAction> list, int i) {
        if (z) {
            TextView textView = new TextView(this.mContext);
            textView.setClickable(true);
            textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
            textView.setTextSize(2, 13.0f);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.bbs_manage);
            textView.setCompoundDrawablePadding(this.padding);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setText(R.string.manage);
            final String[] stringArray = this.mContext.getResources().getStringArray(!z2 ? R.array.comment_manage : R.array.comment_manage2);
            final List<CommentAction> list2 = list;
            final boolean z3 = z2;
            final int i2 = i;
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DialogTextAdapter dialogTextAdapter = new DialogTextAdapter(PostDetailAdapter.this.mContext, stringArray);
                    final AlertDialog showListDialog = DialogFactory.showListDialog(PostDetailAdapter.this.mContext, dialogTextAdapter);
                    dialogTextAdapter.setOnItemClickListener(new DialogTextAdapter.OnItemClickListener() {
                        public void onClick(int i) {
                            switch (i) {
                                case 0:
                                    String access$800 = PostDetailAdapter.this.getEditUrl(list2);
                                    if (!TextUtils.isEmpty(access$800) && PostDetailAdapter.this.linkClickHandler != null) {
                                        PostDetailAdapter.this.linkClickHandler.onClickLink(access$800);
                                        break;
                                    }
                                case 1:
                                    if (!z3) {
                                        String access$1100 = PostDetailAdapter.this.getDeleteUrl(list2);
                                        if (!TextUtils.isEmpty(access$1100)) {
                                            ApiClient.get(access$1100);
                                            PostDetailAdapter.this.removeItem(i2);
                                            break;
                                        }
                                    } else {
                                        String access$900 = PostDetailAdapter.this.getBanUrl(list2);
                                        if (!TextUtils.isEmpty(access$900) && PostDetailAdapter.this.onBanClickListener != null) {
                                            PostDetailAdapter.this.onBanClickListener.onBan(access$900);
                                            break;
                                        }
                                    }
                                    break;
                                case 2:
                                    String access$11002 = PostDetailAdapter.this.getDeleteUrl(list2);
                                    if (!TextUtils.isEmpty(access$11002)) {
                                        ApiClient.get(access$11002);
                                        PostDetailAdapter.this.removeItem(i2);
                                        break;
                                    }
                                    break;
                            }
                            if (showListDialog != null) {
                                showListDialog.dismiss();
                            }
                        }
                    });
                }
            });
            linearLayout.addView(textView, this.actionParams);
        }
    }

    public void removeItem(int i) {
        this.items.remove(i);
        notifyItemRemoved(i + 1);
        notifyDataSetChanged();
    }

    private void addReplyOrEditBt(LinearLayout linearLayout, boolean z, final BaseForumCommentsBean.PostListBean postListBean, final List<CommentAction> list) {
        Drawable drawable;
        TextView textView = new TextView(this.mContext);
        textView.setClickable(true);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setTextSize(2, 13.0f);
        textView.setCompoundDrawablePadding(this.padding);
        if (z) {
            textView.setText(R.string.edit);
            drawable = this.mContext.getResources().getDrawable(R.drawable.bbs_edit);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String access$800 = PostDetailAdapter.this.getEditUrl(list);
                    if (!TextUtils.isEmpty(access$800) && PostDetailAdapter.this.linkClickHandler != null) {
                        PostDetailAdapter.this.linkClickHandler.onClickLink(access$800);
                    }
                }
            });
        } else {
            textView.setText(R.string.reply);
            drawable = this.mContext.getResources().getDrawable(R.drawable.bbs_forum_comment);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PostDetailAdapter.this.onReplyListener != null) {
                        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_POST_DETAIL, Constants.ClickEvent.CLICK_REPLY, postListBean.tid + JSMethod.NOT_SET + postListBean.position);
                        PostDetailAdapter.this.onReplyListener.onReply(postListBean);
                    }
                }
            });
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        linearLayout.addView(textView, this.actionParams);
    }

    /* access modifiers changed from: private */
    public String getEditUrl(List<CommentAction> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (CommentAction next : list) {
            if (next.name.equalsIgnoreCase(KEY_EDIT)) {
                return next.url;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public String getDeleteUrl(List<CommentAction> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (CommentAction next : list) {
            if (next.name.equalsIgnoreCase(KEY_DELETE)) {
                return next.url;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public String getBanUrl(List<CommentAction> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (CommentAction next : list) {
            if (next.name.equalsIgnoreCase(KEY_BAN)) {
                return next.url;
            }
        }
        return null;
    }

    private void handleMessageJson(LinearLayout linearLayout, JsonArray jsonArray, List<String> list) {
        try {
            JSONArray jSONArray = new JSONArray(jsonArray.toString());
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if (next.equals("quote")) {
                                addQuoteView(linearLayout, optJSONObject.optJSONObject(next));
                            } else {
                                addCommentsView(linearLayout, next, optJSONObject.getString(next), list);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addQuoteView(LinearLayout linearLayout, JSONObject jSONObject) throws JSONException {
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.user_activity_item_gray_sharp));
        String string = jSONObject.getString("author");
        String string2 = jSONObject.getString(KEY_MESSAGE);
        textView.setText(Html.fromHtml("<font color=\"#ff6702\">" + string + "</font> " + string2));
        textView.setLineSpacing(0.0f, 1.2f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = this.padding * 4;
        linearLayout.addView(textView, layoutParams);
        textView.setPadding(this.padding * 7, this.padding * 3, this.padding * 7, this.padding * 3);
    }

    private void addCommentsView(LinearLayout linearLayout, String str, String str2, List<String> list) {
        if (str.equals(KEY_TXT)) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            if (linearLayout.getChildCount() > 0) {
                View childAt = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
                if (!(childAt instanceof TextView) || childAt.getTag() == null || !childAt.getTag().equals(KEY_FACE)) {
                    addTxtView(linearLayout, str2, KEY_TXT);
                    return;
                }
                TextView textView = (TextView) childAt;
                textView.setText(Html.fromHtml(textView.getText() + str2));
                return;
            }
            addTxtView(linearLayout, str2, KEY_TXT);
        } else if (str.equals("img")) {
            addImage(linearLayout, str2, list);
        } else if (str.equals(KEY_FACE)) {
            ImageView imageView = new ImageView(this.mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.showImage(imageView, str2);
            linearLayout.addView(imageView, new LinearLayout.LayoutParams(this.padding * 12, this.padding * 12));
        }
    }

    private void addImage(LinearLayout linearLayout, final String str, final List<String> list) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.showImage(imageView, str);
        list.add(str);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(PostDetailAdapter.this.mContext, str, (String[]) list.toArray(new String[0]));
            }
        });
        linearLayout.addView(imageView, this.imgParams);
    }

    private void addTxtView(LinearLayout linearLayout, String str, String str2) {
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor("#222222"));
        textView.setText(Html.fromHtml(str));
        textView.setTag(str2);
        textView.setTextDirection(5);
        textView.setAutoLinkMask(1);
        textView.setLinkTextColor(this.mContext.getResources().getColor(R.color.main_yellow));
        textView.setLineSpacing(0.0f, 1.2f);
        linkClick(textView);
        linearLayout.addView(textView, this.txtParams);
    }

    private void linkClick(TextView textView) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int length = text.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            spannableStringBuilder.clearSpans();
            for (URLSpan uRLSpan : uRLSpanArr) {
                spannableStringBuilder.setSpan(new MyURLSpan(uRLSpan.getURL(), this.linkClickHandler), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 33);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    private static class MyURLSpan extends ClickableSpan implements Parcelable {
        public static final Parcelable.Creator<MyURLSpan> CREATOR = new Parcelable.Creator<MyURLSpan>() {
            public MyURLSpan createFromParcel(Parcel parcel) {
                return new MyURLSpan(parcel);
            }

            public MyURLSpan[] newArray(int i) {
                return new MyURLSpan[i];
            }
        };
        private LinkClickHandler clickHandler;
        private final String mURL;

        public int describeContents() {
            return 0;
        }

        public MyURLSpan(String str, LinkClickHandler linkClickHandler) {
            this.mURL = str;
            this.clickHandler = linkClickHandler;
        }

        public void writeToParcelInternal(Parcel parcel, int i) {
            parcel.writeString(this.mURL);
        }

        public String getURL() {
            return this.mURL;
        }

        public void onClick(View view) {
            if (this.clickHandler != null) {
                this.clickHandler.onClickLink(getURL());
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mURL);
        }

        protected MyURLSpan(Parcel parcel) {
            this.mURL = parcel.readString();
        }
    }

    public int getItemViewType(int i) {
        return this.items.get(i).getDataType();
    }

    public void clear() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
            notifyDataSetChanged();
        }
    }

    public void replaceData(List<PostDataItem> list) {
        this.items.clear();
        this.items.addAll(list);
        getAllPicList(list);
        notifyDataSetChanged();
    }

    public void addListData(List<PostDataItem> list) {
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public void addOneData(PostDataItem postDataItem) {
        this.items.add(postDataItem);
    }

    private void getAllPicList(List<PostDataItem> list) {
        this.picList.clear();
        for (PostDataItem next : list) {
            if (next.getDataType() == 2) {
                this.picList.add(next.getPostImage());
            }
        }
    }

    public int getDataItemCount() {
        return this.items.size();
    }

    static class PostItemTextHolder extends RecyclerView.ViewHolder {
        @BindView(2131493810)
        HtmlTextView postHtmlText;

        public PostItemTextHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    static class PostItemImageHolder extends RecyclerView.ViewHolder {
        @BindView(2131493809)
        ImageView postDetailImg;

        public PostItemImageHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    static class PostItemTitleHolder extends RecyclerView.ViewHolder {
        @BindView(2131493535)
        ImageView ivStamp;
        @BindView(2131493812)
        TextView postItemPostTime;
        @BindView(2131493813)
        TextView postItemPostViews;
        @BindView(2131493814)
        TextView postItemSubject;
        @BindView(2131493815)
        ImageView postItemUserIcon;
        @BindView(2131493816)
        TextView postItemUserName;

        public PostItemTitleHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    static class PostItemVoteHolder extends RecyclerView.ViewHolder {
        @BindView(2131493879)
        RelativeLayout layoutVoteItem;
        @BindView(2131493586)
        ListView postVoteList;
        @BindView(2131494151)
        TextView postVoteMutilMax;
        @BindView(2131493032)
        TextView postVoteSubmit;
        @BindView(2131494163)
        TextView postVoteUserCount;
        @BindView(2131494150)
        TextView postVoteWay;

        public PostItemVoteHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    static class PostItemAuthorHolder extends RecyclerView.ViewHolder {
        @BindView(2131493557)
        RelativeLayout layoutPostAuthor;
        @BindView(2131493821)
        TextView mPostUserItemFollowerBt;
        @BindView(2131493822)
        CircleImageView mPostUserItemIcon;
        @BindView(2131493823)
        TextView mPostUserItemModerator;
        @BindView(2131493824)
        TextView mPostUserItemName;

        public PostItemAuthorHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostColumnHolder extends RecyclerView.ViewHolder {
        @BindView(2131493115)
        TextView column_sub_item_article_count;
        @BindView(2131493116)
        TextView column_sub_item_des;
        @BindView(2131493117)
        TextView column_sub_item_follower_count;
        @BindView(2131493118)
        ImageView column_sub_item_icon;
        @BindView(2131493121)
        TextView column_sub_item_title;
        @BindView(2131493122)
        RelativeLayout relSubItem;
        @BindView(2131493114)
        TextView tvColumnFollow;

        public PostColumnHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostCommentsItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493144)
        LinearLayout commentsItemActionContainer;
        @BindView(2131493145)
        LinearLayout commentsItemPostContentContainer;
        @BindView(2131493146)
        TextView commentsItemPostPosition;
        @BindView(2131493147)
        TextView commentsItemPostTime;
        @BindView(2131493560)
        LinearLayout commentsItemPostTop;
        @BindView(2131493135)
        TextView commentsItemTop;
        @BindView(2131493148)
        HeadLogoView commentsItemUserIcon;
        @BindView(2131493149)
        TextView commentsItemUserName;
        @BindView(2131493142)
        TextView commentsItemViewAll;
        /* access modifiers changed from: private */
        public List<String> imgUrls = new ArrayList();

        public PostCommentsItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostPastColumnHeaderItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493878)
        RelativeLayout columnPastHeader;

        public PostPastColumnHeaderItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostPastColumnItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493776)
        ImageView columnPastBg;
        @BindView(2131493877)
        RelativeLayout columnPastItem;
        @BindView(2131493808)
        TextView columnPastSubject;

        public PostPastColumnItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class PostVideoItemHolder extends RecyclerView.ViewHolder {
        @BindView(2131493818)
        FrameLayout postItemVideoFrame;

        public PostVideoItemHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
