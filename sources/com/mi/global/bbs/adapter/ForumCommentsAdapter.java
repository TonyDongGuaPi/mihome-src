package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.Attach;
import com.mi.global.bbs.model.BaseForumCommentsBean;
import com.mi.global.bbs.model.CommentAction;
import com.mi.global.bbs.model.ForumCommentsBean;
import com.mi.global.bbs.service.MiDownloader;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.MyURLSpan;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.HeadLogoView;
import com.taobao.weex.annotation.JSMethod;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForumCommentsAdapter extends BaseLoadMoreAdapter {
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_BAN = "Ban";
    private static final String KEY_DELETE = "Delete";
    private static final String KEY_EDIT = "Edit";
    private static final String KEY_FACE = "face";
    private static final String KEY_IMG = "img";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_QUOTE = "quote";
    private static final String KEY_TXT = "txt";
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;
    private LinearLayout.LayoutParams actionParams;
    /* access modifiers changed from: private */
    public Activity context;
    private List<BaseForumCommentsBean.PostListBean> data = new ArrayList();
    private int imgHeight = 0;
    private LinearLayout.LayoutParams imgParams;
    private int margin = 0;
    /* access modifiers changed from: private */
    public onBanClickListener onBanClickListener;
    /* access modifiers changed from: private */
    public OnSortListener onOrderListener;
    /* access modifiers changed from: private */
    public OnReplyListener onReplyListener;
    private int padding;
    public ForumCommentsBean.ThreadInfo threadinfo;
    private int total;
    private LinearLayout.LayoutParams txtParams;

    public interface OnReplyListener {
        void onReply(BaseForumCommentsBean.PostListBean postListBean);
    }

    public interface onBanClickListener {
        void onBan(String str);
    }

    public int getNormalViewType(int i) {
        return i == 0 ? 1 : 2;
    }

    public class CommentsHeader_ViewBinding implements Unbinder {
        private CommentsHeader target;

        @UiThread
        public CommentsHeader_ViewBinding(CommentsHeader commentsHeader, View view) {
            this.target = commentsHeader;
            commentsHeader.commentItemTopImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.comment_item_top_img, "field 'commentItemTopImg'", ImageView.class);
            commentsHeader.commentItemTopTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.comment_item_top_title, "field 'commentItemTopTitle'", TextView.class);
            commentsHeader.commentItemTopCommentsCount = (TextView) Utils.findRequiredViewAsType(view, R.id.comment_item_top_comments_count, "field 'commentItemTopCommentsCount'", TextView.class);
            commentsHeader.commentItemTopContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.comment_item_top_container, "field 'commentItemTopContainer'", LinearLayout.class);
            commentsHeader.commentItemTopCommentsOrderBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.comment_item_top_comments_order_bt, "field 'commentItemTopCommentsOrderBt'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            CommentsHeader commentsHeader = this.target;
            if (commentsHeader != null) {
                this.target = null;
                commentsHeader.commentItemTopImg = null;
                commentsHeader.commentItemTopTitle = null;
                commentsHeader.commentItemTopCommentsCount = null;
                commentsHeader.commentItemTopContainer = null;
                commentsHeader.commentItemTopCommentsOrderBt = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class CommentsItem_ViewBinding implements Unbinder {
        private CommentsItem target;

        @UiThread
        public CommentsItem_ViewBinding(CommentsItem commentsItem, View view) {
            this.target = commentsItem;
            commentsItem.commentsItemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.comments_item_user_icon, "field 'commentsItemUserIcon'", HeadLogoView.class);
            commentsItem.commentsItemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_user_name, "field 'commentsItemUserName'", TextView.class);
            commentsItem.commentsItemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_post_time, "field 'commentsItemPostTime'", TextView.class);
            commentsItem.commentsItemPostPosition = (TextView) Utils.findRequiredViewAsType(view, R.id.comments_item_post_position, "field 'commentsItemPostPosition'", TextView.class);
            commentsItem.commentsItemPostContentContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.comments_item_post_content_container, "field 'commentsItemPostContentContainer'", LinearLayout.class);
            commentsItem.commentsItemActionContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.comments_item_action_container, "field 'commentsItemActionContainer'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            CommentsItem commentsItem = this.target;
            if (commentsItem != null) {
                this.target = null;
                commentsItem.commentsItemUserIcon = null;
                commentsItem.commentsItemUserName = null;
                commentsItem.commentsItemPostTime = null;
                commentsItem.commentsItemPostPosition = null;
                commentsItem.commentsItemPostContentContainer = null;
                commentsItem.commentsItemActionContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ForumCommentsAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading) {
        super(activity, dataLoading);
        this.context = activity;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.padding = (int) TypedValue.applyDimension(1, 2.0f, displayMetrics);
        this.margin = (int) TypedValue.applyDimension(1, 40.0f, displayMetrics);
        this.imgHeight = ((displayMetrics.widthPixels - ((int) TypedValue.applyDimension(1, 63.0f, displayMetrics))) * 9) / 16;
        this.txtParams = new LinearLayout.LayoutParams(-1, -2);
        this.imgParams = new LinearLayout.LayoutParams(-1, this.imgHeight);
        this.imgParams.topMargin = this.padding * 6;
        this.actionParams = new LinearLayout.LayoutParams(-2, -2);
        this.actionParams.rightMargin = this.margin;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new CommentsHeader(this.layoutInflater.inflate(R.layout.bbs_forum_comment_item_top, viewGroup, false));
            case 2:
                return new CommentsItem(this.layoutInflater.inflate(R.layout.bbs_forum_comment_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 1:
                bindHeader((CommentsHeader) viewHolder);
                break;
            case 2:
                bindItem((CommentsItem) viewHolder, i);
                break;
        }
        super.onBindViewHolder(viewHolder, i);
    }

    private void bindItem(CommentsItem commentsItem, int i) {
        int i2 = i - 1;
        BaseForumCommentsBean.PostListBean postListBean = this.data.get(i2);
        String str = postListBean.icon;
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showHeadLogo(commentsItem.commentsItemUserIcon, str, postListBean.showlogo, postListBean.groupid);
        }
        commentsItem.commentsItemPostPosition.setText(this.context.getString(R.string.comment_position, new Object[]{Integer.valueOf(postListBean.position)}));
        commentsItem.commentsItemPostTime.setText(postListBean.dateline);
        commentsItem.commentsItemUserName.setText(postListBean.author);
        final String str2 = postListBean.authorid;
        commentsItem.commentsItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(ForumCommentsAdapter.this.context, str2);
            }
        });
        commentsItem.commentsItemUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(ForumCommentsAdapter.this.context, str2);
            }
        });
        if (postListBean.deleted == 1) {
            TextView textView = new TextView(this.context);
            textView.setTextSize(2, 15.0f);
            textView.setTextColor(Color.parseColor("#222222"));
            textView.setText(R.string.comment_deleted_hint);
            commentsItem.commentsItemPostContentContainer.removeAllViews();
            commentsItem.commentsItemActionContainer.removeAllViews();
            commentsItem.commentsItemPostContentContainer.addView(textView, this.txtParams);
        } else if (postListBean.banned != 1) {
            commentsItem.imgUrls.clear();
            commentsItem.commentsItemActionContainer.removeAllViews();
            commentsItem.commentsItemPostContentContainer.removeAllViews();
            handleMessageJson(commentsItem.commentsItemPostContentContainer, postListBean.message, commentsItem.imgUrls);
            handleAttach(commentsItem.commentsItemPostContentContainer, postListBean.attachlist);
            addActionBt(commentsItem.commentsItemActionContainer, postListBean, i2);
        } else if (postListBean.manage == 1) {
            commentsItem.commentsItemActionContainer.removeAllViews();
            commentsItem.commentsItemPostContentContainer.removeAllViews();
            addBanView(commentsItem.commentsItemPostContentContainer);
            commentsItem.imgUrls.clear();
            handleMessageJson(commentsItem.commentsItemPostContentContainer, postListBean.message, commentsItem.imgUrls);
            handleAttach(commentsItem.commentsItemPostContentContainer, postListBean.attachlist);
            addActionBt(commentsItem.commentsItemActionContainer, postListBean, i2);
        } else {
            TextView textView2 = new TextView(this.context);
            textView2.setTextSize(2, 15.0f);
            textView2.setTextColor(Color.parseColor("#222222"));
            textView2.setText(R.string.comment_ban_hint);
            commentsItem.commentsItemPostContentContainer.removeAllViews();
            commentsItem.commentsItemActionContainer.removeAllViews();
            commentsItem.commentsItemPostContentContainer.addView(textView2, this.txtParams);
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
                            ForumCommentsAdapter.this.downloadAttachment(str);
                        }
                    });
                    linearLayout.addView(inflate);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void downloadAttachment(String str) {
        Toast.makeText(this.context, String.format(this.context.getString(R.string.the_file_will_be_saved_at), new Object[]{FileUtils.getAttachFileDir()}), 0).show();
        MiDownloader.init(this.context).download(str);
    }

    private void addBanView(LinearLayout linearLayout) {
        TextView textView = new TextView(this.context);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setBackgroundDrawable(this.context.getResources().getDrawable(R.drawable.user_activity_item_gray_sharp));
        textView.setText(Html.fromHtml("<font color=\"#ff6702\">" + (this.context.getResources().getString(R.string.note) + ": ") + "</font> " + this.context.getResources().getString(R.string.comment_ban_hint)));
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
        CheckedTextView checkedTextView = new CheckedTextView(this.context);
        checkedTextView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        checkedTextView.setTextSize(2, 13.0f);
        boolean z = true;
        checkedTextView.setToggleAble(true);
        checkedTextView.setClickable(true);
        Drawable drawable = this.context.getResources().getDrawable(R.drawable.like_bt_selector);
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
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COMMENT, Constants.ClickEvent.CLICK_LIKE, postListBean.tid + JSMethod.NOT_SET + postListBean.position);
                postListBean.thumbupstatus = z ? 1 : 0;
                postListBean.thumbupcount += z ? 1 : -1;
                if (postListBean.thumbupcount == 0) {
                    checkedTextView.setText(ForumCommentsAdapter.this.context.getString(R.string.like));
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
            TextView textView = new TextView(this.context);
            textView.setClickable(true);
            textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
            textView.setTextSize(2, 13.0f);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            Drawable drawable = this.context.getResources().getDrawable(R.drawable.bbs_manage);
            textView.setCompoundDrawablePadding(this.padding);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setText(R.string.manage);
            final String[] stringArray = this.context.getResources().getStringArray(!z2 ? R.array.comment_manage : R.array.comment_manage2);
            final List<CommentAction> list2 = list;
            final boolean z3 = z2;
            final int i2 = i;
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DialogTextAdapter dialogTextAdapter = new DialogTextAdapter(ForumCommentsAdapter.this.context, stringArray);
                    final AlertDialog showListDialog = DialogFactory.showListDialog(ForumCommentsAdapter.this.context, dialogTextAdapter);
                    dialogTextAdapter.setOnItemClickListener(new DialogTextAdapter.OnItemClickListener() {
                        public void onClick(int i) {
                            switch (i) {
                                case 0:
                                    String access$300 = ForumCommentsAdapter.this.getEditUrl(list2);
                                    if (!TextUtils.isEmpty(access$300)) {
                                        WebActivity.jump(ForumCommentsAdapter.this.context, access$300);
                                        break;
                                    }
                                    break;
                                case 1:
                                    if (!z3) {
                                        String access$600 = ForumCommentsAdapter.this.getDeleteUrl(list2);
                                        if (!TextUtils.isEmpty(access$600)) {
                                            ApiClient.get(access$600);
                                            ForumCommentsAdapter.this.removeItem(i2);
                                            break;
                                        }
                                    } else {
                                        String access$400 = ForumCommentsAdapter.this.getBanUrl(list2);
                                        if (!TextUtils.isEmpty(access$400) && ForumCommentsAdapter.this.onBanClickListener != null) {
                                            ForumCommentsAdapter.this.onBanClickListener.onBan(access$400);
                                            break;
                                        }
                                    }
                                    break;
                                case 2:
                                    String access$6002 = ForumCommentsAdapter.this.getDeleteUrl(list2);
                                    if (!TextUtils.isEmpty(access$6002)) {
                                        ApiClient.get(access$6002);
                                        ForumCommentsAdapter.this.removeItem(i2);
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
        if (i >= this.data.size()) {
            i = this.data.size() - 1;
        }
        this.data.remove(i);
        notifyItemRemoved(i + 1);
        if (i != this.data.size() - 1) {
            notifyItemRangeChanged(i, this.data.size() - i);
        }
    }

    private void addReplyOrEditBt(LinearLayout linearLayout, boolean z, final BaseForumCommentsBean.PostListBean postListBean, final List<CommentAction> list) {
        Drawable drawable;
        TextView textView = new TextView(this.context);
        textView.setClickable(true);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setTextSize(2, 13.0f);
        textView.setCompoundDrawablePadding(this.padding);
        if (z) {
            textView.setText(R.string.edit);
            drawable = this.context.getResources().getDrawable(R.drawable.bbs_edit);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String access$300 = ForumCommentsAdapter.this.getEditUrl(list);
                    if (!TextUtils.isEmpty(access$300)) {
                        WebActivity.jump(ForumCommentsAdapter.this.context, access$300);
                    }
                }
            });
        } else {
            textView.setText(R.string.reply);
            drawable = this.context.getResources().getDrawable(R.drawable.bbs_forum_comment);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ForumCommentsAdapter.this.onReplyListener != null) {
                        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COMMENT, Constants.ClickEvent.CLICK_REPLY, postListBean.tid + JSMethod.NOT_SET + postListBean.position);
                        ForumCommentsAdapter.this.onReplyListener.onReply(postListBean);
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
        TextView textView = new TextView(this.context);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setBackgroundDrawable(this.context.getResources().getDrawable(R.drawable.user_activity_item_gray_sharp));
        String string = jSONObject.getString("author");
        String string2 = jSONObject.getString("message");
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
            ImageView imageView = new ImageView(this.context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.showImage(imageView, str2);
            linearLayout.addView(imageView, new LinearLayout.LayoutParams(this.padding * 12, this.padding * 12));
        }
    }

    private void addImage(LinearLayout linearLayout, final String str, final List<String> list) {
        ImageView imageView = new ImageView(this.context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.showImage(imageView, str);
        list.add(str);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ForumCommentsAdapter.this.context, str, (String[]) list.toArray(new String[0]));
            }
        });
        linearLayout.addView(imageView, this.imgParams);
    }

    private void addTxtView(LinearLayout linearLayout, String str, String str2) {
        TextView textView = new TextView(this.context);
        textView.setTextSize(2, 15.0f);
        textView.setTextColor(Color.parseColor("#222222"));
        textView.setText(Html.fromHtml(str));
        textView.setTag(str2);
        textView.setTextDirection(5);
        textView.setAutoLinkMask(1);
        textView.setLinkTextColor(this.context.getResources().getColor(R.color.main_yellow));
        textView.setLineSpacing(0.0f, 1.2f);
        linkClick(textView);
        linearLayout.addView(textView, this.txtParams);
    }

    private void linkClick(TextView textView) {
        try {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            CharSequence text = textView.getText();
            if (text instanceof Spannable) {
                int length = text.length();
                Spannable spannable = (Spannable) textView.getText();
                URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                spannableStringBuilder.clearSpans();
                for (URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new MyURLSpan(this.context, uRLSpan.getURL()), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 33);
                }
                textView.setText(spannableStringBuilder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindHeader(CommentsHeader commentsHeader) {
        if (this.threadinfo != null) {
            if (TextUtils.isEmpty(this.threadinfo.showpic)) {
                commentsHeader.commentItemTopImg.setVisibility(8);
            } else {
                ImageLoader.showImage(commentsHeader.commentItemTopImg, this.threadinfo.showpic);
            }
            commentsHeader.commentItemTopTitle.setText(this.threadinfo.subject);
            TextView textView = commentsHeader.commentItemTopCommentsCount;
            Activity activity = this.context;
            int i = R.plurals.comments_count;
            textView.setText(TextHelper.getQuantityString(activity, i, this.total + ""));
            final String str = this.threadinfo.url;
            commentsHeader.commentItemTopContainer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COMMENT, "click_thread", str);
                    WebActivity.jump(ForumCommentsAdapter.this.context, str);
                }
            });
            commentsHeader.commentItemTopCommentsOrderBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ForumCommentsAdapter.this.onOrderListener != null) {
                        ForumCommentsAdapter.this.onOrderListener.onSortClick();
                    }
                }
            });
        }
    }

    public int getDataItemCount() {
        return this.data.size() + 1;
    }

    public void setData(BaseForumCommentsBean baseForumCommentsBean) {
        if (baseForumCommentsBean.postlist != null) {
            this.data.addAll(baseForumCommentsBean.postlist);
            notifyDataSetChanged();
        }
    }

    public void setThreadInfoAndTotal(ForumCommentsBean.ThreadInfo threadInfo, int i) {
        this.threadinfo = threadInfo;
        this.total = i;
        notifyDataSetChanged();
    }

    public void clear() {
        this.data.clear();
    }

    public void setOnReplyListener(OnReplyListener onReplyListener2) {
        this.onReplyListener = onReplyListener2;
    }

    public void setOnBanClickListener(onBanClickListener onbanclicklistener) {
        this.onBanClickListener = onbanclicklistener;
    }

    public class CommentsHeader extends RecyclerView.ViewHolder {
        @BindView(2131493136)
        TextView commentItemTopCommentsCount;
        @BindView(2131493137)
        ImageView commentItemTopCommentsOrderBt;
        @BindView(2131493138)
        LinearLayout commentItemTopContainer;
        @BindView(2131493139)
        ImageView commentItemTopImg;
        @BindView(2131493140)
        TextView commentItemTopTitle;

        public CommentsHeader(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class CommentsItem extends RecyclerView.ViewHolder {
        @BindView(2131493144)
        LinearLayout commentsItemActionContainer;
        @BindView(2131493145)
        LinearLayout commentsItemPostContentContainer;
        @BindView(2131493146)
        TextView commentsItemPostPosition;
        @BindView(2131493147)
        TextView commentsItemPostTime;
        @BindView(2131493148)
        HeadLogoView commentsItemUserIcon;
        @BindView(2131493149)
        TextView commentsItemUserName;
        /* access modifiers changed from: private */
        public List<String> imgUrls = new ArrayList();

        public CommentsItem(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public void setOnSortListener(OnSortListener onSortListener) {
        this.onOrderListener = onSortListener;
    }
}
