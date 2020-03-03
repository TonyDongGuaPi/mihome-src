package com.mi.global.bbs.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.bbs.inter.OnCommentClickListener;
import com.mi.global.bbs.model.AllThoughtModel;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.LinkModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.post.PostShortContentActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.TimeUtils;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.global.bbs.view.LikeAndCommentView;
import com.mi.global.bbs.view.dialog.MPopupWindow;
import com.mi.global.bbs.view.praise.OnPraiseListener;
import com.mi.util.Coder;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThoughtAdapter extends BaseLoadMoreAdapter {
    private static final int SHORT_CONTENT_THUMB_UP_TYPE = 1;
    private static final int SIGN_THUMB_UP_TYPE = 0;
    /* access modifiers changed from: private */
    public BaseActivity context;
    public List<AllThoughtModel.ListBean> followDatas;
    /* access modifiers changed from: private */
    public HashMap<String, LinkModel> mLinkMap = new HashMap<>();
    /* access modifiers changed from: private */
    public OnFollowListener mOnFollowListener;
    /* access modifiers changed from: private */
    public MPopupWindow pop;
    private RelativeLayout relativeLayout;

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
            baseForumHolder.itemUserIcon = (HeadLogoView) Utils.findRequiredViewAsType(view, R.id.item_user_icon, "field 'itemUserIcon'", HeadLogoView.class);
            baseForumHolder.itemUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.item_user_name, "field 'itemUserName'", TextView.class);
            baseForumHolder.itemPostTime = (TextView) Utils.findRequiredViewAsType(view, R.id.item_post_time, "field 'itemPostTime'", TextView.class);
            baseForumHolder.itemUserNameContent = (TextView) Utils.findRequiredViewAsType(view, R.id.item_post_type, "field 'itemUserNameContent'", TextView.class);
            baseForumHolder.itemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", TextView.class);
            baseForumHolder.likeCommentView = (LikeAndCommentView) Utils.findRequiredViewAsType(view, R.id.view_like_comment, "field 'likeCommentView'", LikeAndCommentView.class);
            baseForumHolder.itemFollowing = (TextView) Utils.findRequiredViewAsType(view, R.id.item_following, "field 'itemFollowing'", TextView.class);
            baseForumHolder.itemMore = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.item_action_more, "field 'itemMore'", RelativeLayout.class);
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
                baseForumHolder.itemFollowing = null;
                baseForumHolder.itemMore = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ThoughtAdapter(BaseActivity baseActivity, InfiniteScrollListener.DataLoading dataLoading) {
        super(baseActivity, dataLoading);
        this.context = baseActivity;
        this.followDatas = new ArrayList();
    }

    public void addData(List<AllThoughtModel.ListBean> list) {
        if (list != null) {
            this.followDatas.clear();
            this.followDatas.addAll(list);
            notifyDataSetChanged();
        }
    }

    public AllThoughtModel.ListBean getFollowDatas(int i) {
        return this.followDatas.get(i);
    }

    public void clear() {
        this.followDatas.clear();
    }

    public int getNormalViewType(int i) {
        List<AllThoughtModel.ImageModel> list;
        AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        String str = null;
        if (listBean != null) {
            list = listBean.image;
            str = listBean.urls;
        } else {
            list = null;
        }
        int i2 = listBean.type_id;
        if (i2 != 19) {
            return i2 != 200 ? 999 : 200;
        }
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
    }

    public int getDataItemCount() {
        return this.followDatas.size();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 200) {
            return new TopShareHolder(this.layoutInflater.inflate(R.layout.following_top_layout, viewGroup, false));
        }
        if (i == 999) {
            return new DefaultHolder(this.layoutInflater.inflate(R.layout.thought_item_default, viewGroup, false));
        }
        switch (i) {
            case 190:
                return new ForumBigPicHolder(this.layoutInflater.inflate(R.layout.thought_item_big_pic, viewGroup, false));
            case 191:
                return new ForumTwoPicHolder(this.layoutInflater.inflate(R.layout.thought_item_two_pic, viewGroup, false));
            case 192:
                return new ForumMultiPicHolder(this.layoutInflater.inflate(R.layout.thought_item_three_pic, viewGroup, false));
            case 193:
                return new LinkItemHolder(this.layoutInflater.inflate(R.layout.thought_item_link, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        int itemViewType = getItemViewType(i);
        if (itemViewType == 200) {
            bindTopShareHolder((TopShareHolder) viewHolder);
        } else if (itemViewType != 999) {
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
                    return;
            }
        } else {
            bindDefault((DefaultHolder) viewHolder, i);
        }
    }

    private void bindBigPicHolder(ForumBigPicHolder forumBigPicHolder, int i) {
        final AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        handleCommonPart(forumBigPicHolder, listBean, i);
        if (!(listBean == null || listBean.image == null || listBean.image.size() <= 0)) {
            ImageLoader.showFixXYBigPic(forumBigPicHolder.forumItemBigPic, listBean.image.get(0).url, Integer.valueOf(listBean.image.get(0).width).intValue(), Integer.valueOf(listBean.image.get(0).height).intValue());
        }
        final ArrayList arrayList = new ArrayList();
        if (listBean == null || listBean.image == null || listBean.image.size() <= 0) {
            forumBigPicHolder.forumItemBigPic.setVisibility(8);
        } else {
            forumBigPicHolder.forumItemBigPic.setVisibility(0);
            arrayList.add(listBean.image.get(0).url);
        }
        forumBigPicHolder.forumItemBigPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, listBean.image.get(0).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindMultiHolder(ForumMultiPicHolder forumMultiPicHolder, int i) {
        AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        handleCommonPart(forumMultiPicHolder, listBean, i);
        final List<AllThoughtModel.ImageModel> list = listBean.image;
        ImageLoader.showImage(forumMultiPicHolder.itemPic1, list.get(0).url);
        ImageLoader.showImage(forumMultiPicHolder.itemPic2, list.get(1).url);
        ImageLoader.showImage(forumMultiPicHolder.itemPic3, list.get(2).url);
        final ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(0).url);
        arrayList.add(list.get(1).url);
        arrayList.add(list.get(2).url);
        forumMultiPicHolder.itemPic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, ((AllThoughtModel.ImageModel) list.get(0)).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumMultiPicHolder.itemPic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, ((AllThoughtModel.ImageModel) list.get(1)).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumMultiPicHolder.itemPic3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, ((AllThoughtModel.ImageModel) list.get(2)).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindTwoPicHolder(ForumTwoPicHolder forumTwoPicHolder, int i) {
        AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        handleCommonPart(forumTwoPicHolder, listBean, i);
        final List<AllThoughtModel.ImageModel> list = listBean.image;
        ImageLoader.showImage(forumTwoPicHolder.itemPic1, list.get(0).url);
        ImageLoader.showImage(forumTwoPicHolder.itemPic2, list.get(1).url);
        final ArrayList arrayList = new ArrayList();
        arrayList.add(list.get(0).url);
        arrayList.add(list.get(1).url);
        forumTwoPicHolder.itemPic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, ((AllThoughtModel.ImageModel) list.get(0)).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
        forumTwoPicHolder.itemPic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WatchBigPicActivity.jump(ThoughtAdapter.this.context, ((AllThoughtModel.ImageModel) list.get(1)).url, (String[]) arrayList.toArray(new String[0]));
            }
        });
    }

    private void bindLinkHolder(final LinkItemHolder linkItemHolder, int i) {
        AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        handleCommonPart(linkItemHolder, listBean, i);
        final String str = listBean.urls;
        LinkModel linkModel = this.mLinkMap.get(str);
        if (linkModel != null) {
            showLinkData(linkModel, linkItemHolder);
        } else {
            LinkModel.loadByUrl(this.context, listBean.urls, new LinkModel.LinkDispatcher() {
                public void onDispatch(LinkModel linkModel) {
                    ThoughtAdapter.this.mLinkMap.put(str, linkModel);
                    ThoughtAdapter.this.showLinkData(linkModel, linkItemHolder);
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
                UserCenterActivity.jump(ThoughtAdapter.this.context, userId);
            }
        });
        topShareHolder.postLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PostShortContentActivity.jump(ThoughtAdapter.this.context);
            }
        });
    }

    private void bindDefault(DefaultHolder defaultHolder, int i) {
        AllThoughtModel.ListBean listBean = this.followDatas.get(i);
        if (!TextUtils.isEmpty(listBean.icon)) {
            ImageLoader.showImage(defaultHolder.itemUserIcon, listBean.icon);
        } else {
            defaultHolder.itemUserIcon.setBackground(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.icon_default_head, this.context.getTheme()));
        }
        defaultHolder.itemUserName.setText(listBean.username);
        defaultHolder.itemPostTime.setText(TimeUtils.localDateHMSStr(Long.parseLong(listBean.add_time) * 1000));
    }

    private void handleCommonPart(BaseForumHolder baseForumHolder, AllThoughtModel.ListBean listBean, int i) {
        String str = listBean.message;
        ImageLoader.showHeadLogo(baseForumHolder.itemUserIcon, listBean.icon, listBean.showlogo, listBean.groupid);
        baseForumHolder.itemUserName.setText(listBean.username);
        baseForumHolder.itemPostTime.setText(TimeUtils.localDateHMSStr(Long.parseLong(listBean.add_time) * 1000));
        baseForumHolder.itemTitle.setText(str);
        baseForumHolder.itemUserNameContent.setVisibility(0);
        baseForumHolder.itemUserNameContent.setText(getDescriptionText(i));
        if (LoginManager.getInstance().getUserId() == null || !LoginManager.getInstance().getUserId().equals(listBean.uid)) {
            baseForumHolder.itemFollowing.setVisibility(0);
        } else {
            baseForumHolder.itemFollowing.setVisibility(8);
        }
        baseForumHolder.itemMore.setVisibility(0);
        if (listBean.followed == 0) {
            baseForumHolder.itemFollowing.setText(this.context.getResources().getString(R.string.add_follow));
            baseForumHolder.itemFollowing.setTextColor(this.context.getResources().getColor(R.color.item_suggest_grid_1));
            baseForumHolder.itemFollowing.setBackground(this.context.getResources().getDrawable(R.drawable.follow_bt_bg));
        } else {
            baseForumHolder.itemFollowing.setText(this.context.getResources().getString(R.string.following));
            baseForumHolder.itemFollowing.setTextColor(this.context.getResources().getColor(R.color.discover_item_advanced_color));
            baseForumHolder.itemFollowing.setBackground(this.context.getResources().getDrawable(R.drawable.follow_bt_gray_bg));
        }
        setCommonPartClickListener(baseForumHolder, listBean, str, i);
    }

    private String getDescriptionText(int i) {
        int normalViewType = getNormalViewType(i);
        if (normalViewType == 19 || normalViewType == 999) {
            return "";
        }
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

    private void setCommonPartClickListener(final BaseForumHolder baseForumHolder, final AllThoughtModel.ListBean listBean, String str, final int i) {
        baseForumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShortContentDetailActivity.jump(ThoughtAdapter.this.context, String.valueOf(listBean.id));
            }
        });
        baseForumHolder.itemUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.jump(ThoughtAdapter.this.context, listBean.uid);
            }
        });
        baseForumHolder.itemFollowing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ThoughtAdapter.this.mOnFollowListener != null) {
                    GoogleTrackerUtil.sendRecordEvent("feed", "click_Feed_Thoughts", "click_Feed_Thoughts_follow");
                    if (LoginManager.getInstance().hasLogin()) {
                        ThoughtAdapter.this.mOnFollowListener.onFollow(i, listBean.uid, listBean.followed == 0);
                    } else {
                        ThoughtAdapter.this.context.gotoAccount();
                    }
                }
            }
        });
        baseForumHolder.itemMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThoughtAdapter.this.showDialog(baseForumHolder.itemMore, i);
            }
        });
        baseForumHolder.likeCommentView.resetView();
        boolean z = false;
        baseForumHolder.likeCommentView.setLikeAnimationShow(false);
        LikeAndCommentView likeAndCommentView = baseForumHolder.likeCommentView;
        if (listBean.likestatus == 1) {
            z = true;
        }
        likeAndCommentView.setLikeAndComment(z, listBean.like, Integer.valueOf(listBean.replies).intValue());
        baseForumHolder.likeCommentView.setCommentClickListener(new OnCommentClickListener() {
            public void onCommentClick() {
                if (LoginManager.getInstance().hasLogin()) {
                    ShortContentDetailActivity.jump(ThoughtAdapter.this.context, String.valueOf(listBean.id), true);
                } else {
                    ThoughtAdapter.this.context.gotoAccount();
                }
            }
        });
        baseForumHolder.likeCommentView.setOnPriaseClickListener(new OnPraiseListener() {
            public void onNotLogin() {
                ThoughtAdapter.this.context.gotoAccount();
            }

            public void praised() {
                listBean.likestatus = 1;
                listBean.like++;
                ThoughtAdapter.this.shortContentThumbUp(listBean, 0, 1);
            }

            public void unpraised() {
                GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_LIKE, listBean.id);
                listBean.likestatus = 0;
                if (listBean.like > 0) {
                    listBean.like--;
                }
                ThoughtAdapter.this.shortContentThumbUp(listBean, 0, 1);
            }
        });
    }

    /* access modifiers changed from: private */
    public void shortContentThumbUp(AllThoughtModel.ListBean listBean, int i, int i2) {
        ApiClient.getApiService().shortContentThumbUp(String.valueOf(listBean.id), i, i2).compose(this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
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
        @BindView(2131493489)
        TextView itemFollowing;
        @BindView(2131493483)
        RelativeLayout itemMore;
        @BindView(2131493507)
        TextView itemPostTime;
        @BindView(2131493512)
        TextView itemTitle;
        @BindView(2131493515)
        HeadLogoView itemUserIcon;
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

    public void setOnFollowListener(OnFollowListener onFollowListener) {
        this.mOnFollowListener = onFollowListener;
    }

    /* access modifiers changed from: private */
    public void showDialog(View view, final int i) {
        if (this.pop == null || this.relativeLayout == null) {
            this.relativeLayout = (RelativeLayout) this.context.getLayoutInflater().inflate(R.layout.thought_pop_layout, (ViewGroup) null);
            this.relativeLayout.setPaddingRelative(0, (int) TypedValue.applyDimension(1, 10.0f, this.context.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 13.0f, this.context.getResources().getDisplayMetrics()), 0);
            this.pop = new MPopupWindow.Builder(this.context).setView((View) this.relativeLayout).enableBackgroundDark(false).create();
        }
        TextView textView = (TextView) this.relativeLayout.findViewById(R.id.edit_quit_hint_item_1);
        TextView textView2 = (TextView) this.relativeLayout.findViewById(R.id.edit_quit_hint_item_2);
        View findViewById = this.relativeLayout.findViewById(R.id.item_2_line);
        if (this.followDatas.get(i).delete == 1) {
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.round_dialog_middle_item_selector));
            textView2.setVisibility(0);
            findViewById.setVisibility(0);
        } else {
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.round_dialog_last_item_selector));
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(Coder.a(1.0f), Coder.a(1.0f), Coder.a(1.0f), Coder.a(1.0f));
            textView2.setVisibility(8);
            findViewById.setVisibility(8);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThoughtAdapter.this.pop.dismiss();
                ThoughtAdapter.this.reportThought(ThoughtAdapter.this.followDatas.get(i).id);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThoughtAdapter.this.pop.dismiss();
                ThoughtAdapter.this.deleteThought(i, ThoughtAdapter.this.followDatas.get(i).id);
            }
        });
        this.pop.showAsDropDown(view, (-this.relativeLayout.getMeasuredWidth()) + view.getMeasuredWidth(), 0);
    }

    public void reportThought(final String str) {
        DialogFactory.showReportDialog(this.context, this.context.getResources().getString(R.string.report_adverting), new DialogFactory.OnClickListener() {
            public void onClick(String str) {
                ApiClient.reportThought(str, str, ThoughtAdapter.this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() == 0) {
                            Toast.makeText(ThoughtAdapter.this.context, ThoughtAdapter.this.context.getResources().getString(R.string.report_success), 0).show();
                        } else {
                            Toast.makeText(ThoughtAdapter.this.context, baseResult.getErrmsg(), 0).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                    }
                });
            }
        });
    }

    public void deleteThought(final int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("dynamic_id", str + "");
        hashMap.put("type", "1");
        hashMap.put(ParamKey.typeid, "1");
        this.context.showProDialog("");
        ApiClient.getApiService().delShortContentReply(hashMap).compose(this.context.bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
                ThoughtAdapter.this.context.dismissProDialog();
                if (baseResult.getErrno() == 0) {
                    ThoughtAdapter.this.removeItem(i);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                ThoughtAdapter.this.context.dismissProDialog();
            }
        });
    }

    public void removeItem(int i) {
        if (i >= this.followDatas.size()) {
            i = this.followDatas.size() - 1;
        }
        this.followDatas.remove(i);
        notifyItemRemoved(i);
        if (i != this.followDatas.size() - 1) {
            notifyItemRangeChanged(i, this.followDatas.size() - i);
        }
    }
}
