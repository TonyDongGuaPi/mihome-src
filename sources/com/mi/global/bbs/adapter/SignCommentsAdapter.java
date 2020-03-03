package com.mi.global.bbs.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.gson.JsonArray;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.SignComment;
import com.mi.global.bbs.model.SignContentResult;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.MyURLSpan;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.HeadLogoView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignCommentsAdapter extends BaseLoadMoreAdapter {
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
    public BaseActivity context;
    private int imgHeight = 0;
    private LinearLayout.LayoutParams imgParams;
    OnDeleteListener mOnDeleteListener;
    private List<SignComment> mSignComments = new ArrayList();
    public SignContentResult.SignContent mSignContent;
    private int margin = 0;
    /* access modifiers changed from: private */
    public OnSortListener onOrderListener;
    /* access modifiers changed from: private */
    public OnReplyListener onReplyListener;
    private int padding;
    private LinearLayout.LayoutParams txtParams;

    public interface OnDeleteListener {
        void onDelete(int i, int i2, int i3);
    }

    public interface OnReplyListener {
        void onReply(SignComment signComment);
    }

    public int getNormalViewType(int i) {
        return i == 0 ? 1 : 2;
    }

    public class CommentsHeader_ViewBinding implements Unbinder {
        private CommentsHeader target;

        @UiThread
        public CommentsHeader_ViewBinding(CommentsHeader commentsHeader, View view) {
            this.target = commentsHeader;
            commentsHeader.mUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'mUserIcon'", CircleImageView.class);
            commentsHeader.mUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name, "field 'mUserName'", TextView.class);
            commentsHeader.mDeleteBt = (TextView) Utils.findRequiredViewAsType(view, R.id.delete_bt, "field 'mDeleteBt'", TextView.class);
            commentsHeader.mPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.post_time, "field 'mPostTime'", TextView.class);
            commentsHeader.mSignContent = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_content, "field 'mSignContent'", TextView.class);
            commentsHeader.mSignedFeelIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.signed_feel_iv, "field 'mSignedFeelIv'", ImageView.class);
            commentsHeader.mSignItemTopCommentsCount = (TextView) Utils.findRequiredViewAsType(view, R.id.sign_item_top_comments_count, "field 'mSignItemTopCommentsCount'", TextView.class);
            commentsHeader.mSignItemTopCommentsOrderBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.sign_item_top_comments_order_bt, "field 'mSignItemTopCommentsOrderBt'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            CommentsHeader commentsHeader = this.target;
            if (commentsHeader != null) {
                this.target = null;
                commentsHeader.mUserIcon = null;
                commentsHeader.mUserName = null;
                commentsHeader.mDeleteBt = null;
                commentsHeader.mPostTime = null;
                commentsHeader.mSignContent = null;
                commentsHeader.mSignedFeelIv = null;
                commentsHeader.mSignItemTopCommentsCount = null;
                commentsHeader.mSignItemTopCommentsOrderBt = null;
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

    public void setOnSortListener(OnSortListener onSortListener) {
        this.onOrderListener = onSortListener;
    }

    public void setSignContent(SignContentResult.SignContent signContent) {
        this.mSignContent = signContent;
        if (!(signContent == null || signContent.replys == null)) {
            this.mSignComments.clear();
            this.mSignComments.addAll(signContent.replys);
        }
        notifyDataSetChanged();
    }

    public SignCommentsAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        baseActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
                return new CommentsHeader(this.layoutInflater.inflate(R.layout.bbs_sign_detail_top, viewGroup, false));
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
        SignComment signComment = this.mSignComments.get(i2);
        ImageLoader.showHeadLogo(commentsItem.commentsItemUserIcon, signComment.usericon, 0, (String) null);
        commentsItem.commentsItemPostPosition.setText(this.context.getString(R.string.comment_position, new Object[]{Integer.valueOf(signComment.sequence)}));
        commentsItem.commentsItemPostTime.setText(signComment.add_time);
        commentsItem.commentsItemUserName.setText(signComment.username);
        final String str = signComment.uid;
        commentsItem.commentsItemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(SignCommentsAdapter.this.context, str);
            }
        });
        commentsItem.commentsItemUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(SignCommentsAdapter.this.context, str);
            }
        });
        commentsItem.imgUrls.clear();
        commentsItem.commentsItemActionContainer.removeAllViews();
        commentsItem.commentsItemPostContentContainer.removeAllViews();
        handleMessageJson(commentsItem.commentsItemPostContentContainer, signComment.message, commentsItem.imgUrls);
        handleImage(commentsItem.commentsItemPostContentContainer, signComment.images, commentsItem.imgUrls);
        addActionBt(commentsItem.commentsItemActionContainer, signComment, i2);
    }

    private void handleImage(LinearLayout linearLayout, List<String> list, List<String> list2) {
        for (String addImage : list) {
            addImage(linearLayout, addImage, list2);
        }
    }

    private void addActionBt(LinearLayout linearLayout, SignComment signComment, int i) {
        linearLayout.removeAllViews();
        addReplyBt(linearLayout, signComment);
        addLikeBt(linearLayout, signComment);
        addManagerBt(linearLayout, signComment, i);
    }

    private void addLikeBt(LinearLayout linearLayout, final SignComment signComment) {
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
        if (signComment.havethumbup != 1) {
            z = false;
        }
        checkedTextView.setChecked(z);
        if (signComment.thumbupcount == 0) {
            checkedTextView.setText(R.string.like);
        } else {
            checkedTextView.setText(signComment.thumbupcount + "");
        }
        checkedTextView.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(final CheckedTextView checkedTextView, boolean z) {
                signComment.havethumbup = z ? 1 : 0;
                signComment.thumbupcount += z ? 1 : -1;
                if (signComment.thumbupcount == 0) {
                    checkedTextView.setText(SignCommentsAdapter.this.context.getString(R.string.like));
                } else {
                    checkedTextView.setText(signComment.thumbupcount + "");
                }
                ApiClient.getApiService().signThumbUp(String.valueOf(signComment.id), 1).compose(SignCommentsAdapter.this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
                    public void accept(BaseResult baseResult) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable th) throws Exception {
                        int i = 1;
                        signComment.havethumbup = signComment.havethumbup == 0 ? 1 : 0;
                        SignComment signComment = signComment;
                        int i2 = signComment.thumbupcount;
                        if (signComment.havethumbup != 0) {
                            i = -1;
                        }
                        signComment.thumbupcount = i2 + i;
                        if (signComment.thumbupcount == 0) {
                            checkedTextView.setText(SignCommentsAdapter.this.context.getString(R.string.like));
                            return;
                        }
                        CheckedTextView checkedTextView = checkedTextView;
                        checkedTextView.setText(signComment.thumbupcount + "");
                    }
                });
            }
        });
        linearLayout.addView(checkedTextView, this.actionParams);
    }

    private void addManagerBt(LinearLayout linearLayout, SignComment signComment, final int i) {
        if (signComment.delete == 1) {
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
            textView.setText(R.string.delete);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SignCommentsAdapter.this.mOnDeleteListener != null) {
                        SignCommentsAdapter.this.mOnDeleteListener.onDelete(i, -1, 0);
                    }
                }
            });
            linearLayout.addView(textView, this.actionParams);
        }
    }

    public void removeItem(int i) {
        if (i >= this.mSignComments.size()) {
            i = this.mSignComments.size() - 1;
        }
        this.mSignComments.remove(i);
        notifyItemRemoved(i + 1);
        if (i != this.mSignComments.size() - 1) {
            notifyItemRangeChanged(i, this.mSignComments.size() - i);
        }
    }

    private void addReplyBt(LinearLayout linearLayout, final SignComment signComment) {
        TextView textView = new TextView(this.context);
        textView.setClickable(true);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setTextSize(2, 13.0f);
        textView.setCompoundDrawablePadding(this.padding);
        Drawable drawable = this.context.getResources().getDrawable(R.drawable.bbs_forum_comment);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setText(R.string.reply);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SignCommentsAdapter.this.onReplyListener != null) {
                    SignCommentsAdapter.this.onReplyListener.onReply(signComment);
                }
            }
        });
        linearLayout.addView(textView, this.actionParams);
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
        } else if (!str.equals(KEY_FACE)) {
        } else {
            if (linearLayout.getChildCount() > 0) {
                View childAt2 = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
                if (childAt2 instanceof TextView) {
                    TextView textView2 = (TextView) childAt2;
                    textView2.setText(Html.fromHtml(textView2.getText() + str2));
                    return;
                }
                addTxtView(linearLayout, str2, KEY_FACE);
                return;
            }
            addTxtView(linearLayout, str2, KEY_FACE);
        }
    }

    private void addImage(LinearLayout linearLayout, final String str, final List<String> list) {
        ImageView imageView = new ImageView(this.context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.showImage(imageView, str);
        list.add(str);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(SignCommentsAdapter.this.context, str, (String[]) list.toArray(new String[0]));
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
    }

    private void bindHeader(CommentsHeader commentsHeader) {
        if (this.mSignContent != null) {
            TextView textView = commentsHeader.mSignItemTopCommentsCount;
            BaseActivity baseActivity = this.context;
            int i = R.plurals.comments_count;
            textView.setText(TextHelper.getQuantityString(baseActivity, i, this.mSignContent.replynum + ""));
            commentsHeader.mUserName.setText(this.mSignContent.username);
            ImageLoader.showHeadIcon(commentsHeader.mUserIcon, this.mSignContent.usericon);
            commentsHeader.mPostTime.setText(this.mSignContent.add_time);
            commentsHeader.mSignContent.setText(this.mSignContent.username);
            ImageLoader.showImage(commentsHeader.mSignedFeelIv, this.mSignContent.emotionurl);
            commentsHeader.mDeleteBt.setVisibility(this.mSignContent.delete == 1 ? 0 : 8);
            commentsHeader.mDeleteBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SignCommentsAdapter.this.mOnDeleteListener != null) {
                        SignCommentsAdapter.this.mOnDeleteListener.onDelete(0, -1, 1);
                    }
                }
            });
            commentsHeader.mSignContent.setText(this.mSignContent.message);
            commentsHeader.mUserIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity.jump(SignCommentsAdapter.this.context, SignCommentsAdapter.this.mSignContent.uid);
                }
            });
            commentsHeader.mSignItemTopCommentsOrderBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SignCommentsAdapter.this.onOrderListener != null) {
                        SignCommentsAdapter.this.onOrderListener.onSortClick();
                    }
                }
            });
        }
    }

    public int getDataItemCount() {
        return this.mSignComments.size() + 1;
    }

    public void addSignComments(List<SignComment> list) {
        this.mSignComments.addAll(list);
        notifyDataSetChanged();
    }

    public int getCommentsSize() {
        return this.mSignComments.size();
    }

    public void clear() {
        this.mSignComments.clear();
    }

    public void setOnReplyListener(OnReplyListener onReplyListener2) {
        this.onReplyListener = onReplyListener2;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }

    public class CommentsHeader extends RecyclerView.ViewHolder {
        @BindView(2131493184)
        TextView mDeleteBt;
        @BindView(2131493820)
        TextView mPostTime;
        @BindView(2131493982)
        TextView mSignContent;
        @BindView(2131493989)
        TextView mSignItemTopCommentsCount;
        @BindView(2131493990)
        ImageView mSignItemTopCommentsOrderBt;
        @BindView(2131493997)
        ImageView mSignedFeelIv;
        @BindView(2131494213)
        CircleImageView mUserIcon;
        @BindView(2131494225)
        TextView mUserName;

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
}
