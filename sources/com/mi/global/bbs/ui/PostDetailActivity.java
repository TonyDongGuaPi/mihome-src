package com.mi.global.bbs.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.CallbackManager;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.CommentsSelectImgAdapter;
import com.mi.global.bbs.adapter.ForumCommentsAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.adapter.PostDetailAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.inter.LinkClickHandler;
import com.mi.global.bbs.inter.OnFollowColumnListener;
import com.mi.global.bbs.inter.PostVoteListener;
import com.mi.global.bbs.model.BaseForumCommentsBean;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ForumCommentsList;
import com.mi.global.bbs.model.PostDataItem;
import com.mi.global.bbs.model.PostDetailModel;
import com.mi.global.bbs.model.TitleMenu;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.KeyboardChangeListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.PostContentUtil;
import com.mi.global.bbs.view.MsgView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.log.LogUtil;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.multi_image_selector.MultiImageSelectorActivity;
import com.mi.multimonitor.CrashReport;
import com.mi.multimonitor.Request;
import com.nineoldandroids.view.ViewHelper;
import com.taobao.weex.annotation.JSMethod;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PostDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ForumCommentsAdapter.OnReplyListener, ForumCommentsAdapter.onBanClickListener, OnFollowListener, LinkClickHandler, OnFollowColumnListener, PostVoteListener, OnShareListener {
    private static final int POST_HAS_DELETED_CODE = 5001;
    private static final int REQUEST_IMAGE = 119;
    private static final int REQUEST_TAKE_IMAGE = 9999;
    private static final String TAG = "PostDetailActivity";
    private static final String URL_KEY = "url";
    /* access modifiers changed from: private */
    public PostDetailAdapter adapter;
    /* access modifiers changed from: private */
    public int bottomSheetHeight = 0;
    private CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    private String fid;
    /* access modifiers changed from: private */
    public CommentsSelectImgAdapter imgAdapter;
    /* access modifiers changed from: private */
    public boolean isReplyFocus = false;
    /* access modifiers changed from: private */
    public boolean isShowBottomLayout = false;
    @BindView(2131493558)
    RelativeLayout layoutPostDeleted;
    @BindView(2131493559)
    RelativeLayout layoutPostFailed;
    @BindView(2131492903)
    EditText mActivityCommentsEditText;
    @BindView(2131492905)
    RecyclerView mActivityCommentsSelectList;
    @BindView(2131493141)
    RelativeLayout mCommentLayoutContainer;
    @BindView(2131492949)
    ProgressBar mProgress;
    @BindView(2131493155)
    ObservableRecyclerView mRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mRefreshView;
    @BindView(2131494257)
    LinearLayout mWebActivityBottomSheet;
    @BindView(2131494263)
    AppCompatEditText mWebActivityReplyBt;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;
    private String shareTitle;
    private String threadDetailUrl = "";
    private String threadUrl;
    private String tid;
    /* access modifiers changed from: private */
    public int toolbarHeight = 0;
    private ValueAnimator valueAnimator;
    @BindView(2131494256)
    LinearLayout webActivityBottomLayout;
    @BindView(2131494258)
    MsgView webActivityCommentBt;
    @BindView(2131494259)
    MsgView webActivityLikeBt;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_post_detail);
        initView();
        initCommentBottomView();
        initEvent();
        checkGameInfo(false);
        initData();
        getData();
    }

    public static void jump(Context context, String str) {
        context.startActivity(new Intent(context, PostDetailActivity.class).putExtra("url", str));
    }

    private void initView() {
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        this.mRefreshView.setOnRefreshListener(this);
        this.mRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.adapter = new PostDetailAdapter(this);
    }

    private void initEvent() {
        this.mRecycleView.setAdapter(this.adapter);
        this.adapter.setLinkClickHandler(this);
        this.adapter.setVoteListener(this);
        this.adapter.setOnFollowUserListener(this);
        this.adapter.setmOnFollowColunListener(this);
        this.adapter.setOnReplyListener(this);
        this.adapter.setOnBanClickListener(this);
    }

    private void initCommentBottomView() {
        ((FrameLayout.LayoutParams) this.mContentLayout.getLayoutParams()).topMargin = 0;
        this.mContentLayout.requestLayout();
        this.webActivityCommentBt.setMsgBackgroundColor(Color.parseColor("#f66b6b"));
        this.webActivityLikeBt.setMsgBackgroundColor(0);
        this.webActivityLikeBt.setMsgTextColor(Color.parseColor("#666666"));
        this.webActivityLikeBt.setMsgPadding((float) ((int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics())));
        GoogleTrackerUtil.sendRecordPage(TAG);
        setTitleAndBack("", this.titleBackListener);
        LoginManager.getInstance().addLoginListener(this);
        this.mCommentLayoutContainer.setVisibility(8);
        this.imgAdapter = new CommentsSelectImgAdapter(this);
        this.mActivityCommentsSelectList.setLayoutManager(new GridLayoutManager(this, 5));
        this.mActivityCommentsSelectList.setAdapter(this.imgAdapter);
        this.mActivityCommentsSelectList.setVisibility(8);
        this.imgAdapter.setOnDismissListener(new CommentsSelectImgAdapter.OnDismissListener() {
            public void onDismiss() {
                PostDetailActivity.this.mActivityCommentsSelectList.setVisibility(8);
            }
        });
        this.mAndroidBug5497Workaround.setKeyboardChangeListener(new KeyboardChangeListener() {
            public void onKeyboardChange(boolean z) {
                if (!PostDetailActivity.this.isShowBottomLayout) {
                    return;
                }
                if (z) {
                    PostDetailActivity.this.mCommentLayoutContainer.setVisibility(0);
                    if (PostDetailActivity.this.isReplyFocus) {
                        PostDetailActivity.this.mActivityCommentsEditText.requestFocus();
                    }
                    PostDetailActivity.this.webActivityBottomLayout.setVisibility(8);
                    return;
                }
                PostDetailActivity.this.mCommentLayoutContainer.setVisibility(8);
                PostDetailActivity.this.webActivityBottomLayout.setVisibility(0);
            }
        });
        this.mWebActivityReplyBt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                boolean unused = PostDetailActivity.this.isReplyFocus = z;
            }
        });
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int unused = PostDetailActivity.this.bottomSheetHeight = PostDetailActivity.this.mWebActivityBottomSheet.getHeight();
                int unused2 = PostDetailActivity.this.toolbarHeight = PostDetailActivity.this.mAppBarLayout.getHeight();
                PostDetailActivity.this.mRefreshView.setProgressViewEndTarget(true, PostDetailActivity.this.toolbarHeight * 2);
            }
        });
        this.mRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (!PostDetailActivity.this.cancelScroll) {
                    if (i > PostDetailActivity.this.preScrollY && !PostDetailActivity.this.scrollUp) {
                        boolean unused = PostDetailActivity.this.scrollUp = true;
                        PostDetailActivity.this.mWebActivityBottomSheet.animate().translationY((float) PostDetailActivity.this.bottomSheetHeight);
                        PostDetailActivity.this.transToolbar(PostDetailActivity.this.scrollUp);
                    }
                    if (i < PostDetailActivity.this.preScrollY && PostDetailActivity.this.scrollUp) {
                        boolean unused2 = PostDetailActivity.this.scrollUp = false;
                        PostDetailActivity.this.mWebActivityBottomSheet.animate().translationY(0.0f);
                        PostDetailActivity.this.transToolbar(PostDetailActivity.this.scrollUp);
                    }
                    int unused3 = PostDetailActivity.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = PostDetailActivity.this.cancelScroll = false;
                ImeUtils.hideIme(PostDetailActivity.this.mWebActivityReplyBt);
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = PostDetailActivity.this.cancelScroll = true;
            }
        });
    }

    @OnClick({2131494259, 2131494258, 2131492904, 2131492906, 2131493559})
    public void onClick(View view) {
        if (view.getId() == R.id.web_activity_likeBt) {
            if (LoginManager.getInstance().hasLogin()) {
                thumbUpPost();
            } else {
                gotoAccount();
            }
        } else if (view.getId() == R.id.web_activity_commentBt) {
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_COMMENT, "click_reply_mesview");
            jumpToComments(false);
        } else if (view.getId() == R.id.activity_comments_pick_pic_bt) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            takePicture();
        } else if (view.getId() == R.id.activity_comments_send_bt) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            postComment();
        } else if (view.getId() == R.id.layout_post_failed) {
            onRefresh();
        }
    }

    private void initData() {
        this.threadUrl = getIntent().getStringExtra("url");
        this.threadDetailUrl = this.threadUrl + "?outputajax=1&viewall=1";
    }

    /* access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void getData() {
        showLoadingView();
        ApiClient.getPostDetail(this.threadDetailUrl, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<PostDetailModel>() {
            public void accept(@NonNull PostDetailModel postDetailModel) throws Exception {
                PostDetailActivity.this.dismissLoading();
                int errno = postDetailModel.getErrno();
                if (errno == 0) {
                    try {
                        PostDetailActivity.this.handleData(postDetailModel.data);
                    } catch (Exception e) {
                        PostDetailActivity.this.showFailedView();
                        if (CrashReport.get() != null) {
                            CrashReport.postCrash(e);
                        }
                    }
                } else if (errno == 5001) {
                    PostDetailActivity.this.showPostDeletedView();
                } else {
                    PostDetailActivity.this.showFailedView();
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                PostDetailActivity.this.showFailedView();
                if (CrashReport.get() != null) {
                    CrashReport.postCrash(th);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(PostDetailModel.DataBean dataBean) {
        if (dataBean != null && dataBean.thread != null && dataBean.thread.arraydata != null) {
            this.fid = dataBean.thread.fid;
            this.tid = dataBean.thread.tid;
            this.shareTitle = dataBean.thread.subject;
            setTitle((CharSequence) dataBean.thread.subject);
            setTitleMenu(dataBean);
            showReplyPanel();
            showLikeAndComments(dataBean);
            this.adapter.clear();
            this.adapter.replaceData(PostContentUtil.getPostData(dataBean));
            getComment(dataBean);
        }
    }

    private void setTitleMenu(PostDetailModel.DataBean dataBean) {
        ArrayList arrayList = new ArrayList();
        TitleMenu titleMenu = new TitleMenu(Constants.TitleMenu.MENU_FAVORITE, dataBean.thread.favorite);
        titleMenu.setTid(this.tid);
        TitleMenu titleMenu2 = new TitleMenu("share", "");
        arrayList.add(titleMenu);
        arrayList.add(titleMenu2);
        refreshTitleMenu(JsonParser.list2String(arrayList));
    }

    @SuppressLint({"CheckResult"})
    private void getComment(final PostDetailModel.DataBean dataBean) {
        if (!TextUtils.isEmpty(this.tid)) {
            ApiClient.getForumComments(this.tid, 1, 0, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                public void accept(@NonNull JsonObject jsonObject) throws Exception {
                    ForumCommentsList forumCommentsList = (ForumCommentsList) JsonParser.parse(jsonObject.toString(), ForumCommentsList.class);
                    if (!(forumCommentsList == null || forumCommentsList.data == null || forumCommentsList.data.postlist == null)) {
                        PostDetailActivity.this.handleCommentsData(forumCommentsList.data.postlist);
                    }
                    PostDetailActivity.this.handleColumnPastData(dataBean);
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    PostDetailActivity.this.showFailedView();
                    if (CrashReport.get() != null) {
                        CrashReport.postCrash(th);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleCommentsData(List<BaseForumCommentsBean.PostListBean> list) {
        int size = list.size() > 3 ? 3 : list.size();
        int i = 0;
        while (i < list.size() && i < 3) {
            PostDataItem postDataItem = new PostDataItem();
            postDataItem.setDataType(7);
            postDataItem.setCommentPostion(i);
            postDataItem.setCommentSize(size);
            postDataItem.setComment(list.get(i));
            this.adapter.addOneData(postDataItem);
            i++;
        }
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void handleColumnPastData(PostDetailModel.DataBean dataBean) {
        if (dataBean.column.threads != null && dataBean.column.threads.size() > 0) {
            PostDataItem postDataItem = new PostDataItem();
            postDataItem.setDataType(8);
            postDataItem.setColumn(dataBean.column);
            this.adapter.addOneData(postDataItem);
            for (PostDetailModel.DataBean.Column.ColumnThread pastColunm : dataBean.column.threads) {
                PostDataItem postDataItem2 = new PostDataItem();
                postDataItem2.setDataType(9);
                postDataItem2.setPastColunm(pastColunm);
                this.adapter.addOneData(postDataItem2);
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    private void postComment() {
        if (!LoginManager.getInstance().hasLogin()) {
            gotoAccount();
            return;
        }
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_POST_DETAIL, Constants.ClickEvent.CLICK_COMMENT, this.fid + JSMethod.NOT_SET + this.tid);
        String obj = this.mActivityCommentsEditText.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            toast(Integer.valueOf(R.string.post_reply_empty));
        } else if (obj.length() < 10) {
            toast(Integer.valueOf(R.string.post_content_short));
        } else {
            showProDialog("");
            ApiClient.postComments(this.fid, this.tid, obj, this.imgAdapter.getPathList(), (String) null, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    PostDetailActivity.this.dismissProDialog();
                    if (baseResult.getErrno() == 0) {
                        PostDetailActivity.this.mActivityCommentsEditText.setText("");
                        ImeUtils.hideIme(PostDetailActivity.this.mActivityCommentsEditText);
                        PostDetailActivity.this.imgAdapter.clear();
                        PostDetailActivity.this.toast(Integer.valueOf(R.string.comment_success));
                        return;
                    }
                    PostDetailActivity.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    PostDetailActivity.this.dismissProDialog();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleCommentResponseData(JsonObject jsonObject) {
        try {
            JSONObject jSONObject = new JSONObject(jsonObject.toString());
            if (!TextUtils.isEmpty(jSONObject.toString()) && jSONObject.has(Request.RESULT_CODE_KEY)) {
                if (jSONObject.optInt(Request.RESULT_CODE_KEY) == 0) {
                    String optString = jSONObject.optString("data");
                    if (!TextUtils.isEmpty(optString)) {
                        JSONObject jSONObject2 = new JSONObject(optString);
                        changeFavState(jSONObject2.optInt("state"));
                        toast(jSONObject2.optString("message"));
                        return;
                    }
                    return;
                }
                toast(Integer.valueOf(R.string.fav_failed));
            }
        } catch (Exception e) {
            String str = TAG;
            LogUtil.b(str, "changeFavState Exception " + e.toString());
        }
    }

    private void thumbUpPost() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_LIKE, this.fid + JSMethod.NOT_SET + this.tid);
        boolean isChecked = this.webActivityLikeBt.isChecked() ^ true;
        this.webActivityLikeBt.setChecked(isChecked);
        int msgCount = this.webActivityLikeBt.getMsgCount();
        this.webActivityLikeBt.showMsg(isChecked ? msgCount + 1 : msgCount == 0 ? 0 : msgCount - 1);
        this.webActivityLikeBt.setChecked(isChecked);
        ApiClient.thumbUp(this.tid + "", LoginManager.getInstance().getUserId());
    }

    public void takePicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().c().a((ArrayList<String>) (ArrayList) PostDetailActivity.this.imgAdapter.getPathList()).a((Activity) PostDetailActivity.this, 119);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                PostDetailActivity.this.dismissProDialog();
                FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                PostDetailActivity.this.handleNetworkError(th);
            }
        });
    }

    /* access modifiers changed from: private */
    public void transToolbar(final boolean z) {
        if (this.valueAnimator == null) {
            this.valueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        }
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float f;
                float animatedFraction = valueAnimator.getAnimatedFraction();
                LinearLayout access$1900 = PostDetailActivity.this.mAppBarLayout;
                if (z) {
                    f = -(animatedFraction * ((float) PostDetailActivity.this.toolbarHeight));
                } else {
                    f = (animatedFraction * ((float) PostDetailActivity.this.toolbarHeight)) + ((float) (-PostDetailActivity.this.toolbarHeight));
                }
                ViewHelper.setTranslationY(access$1900, f);
            }
        });
        this.valueAnimator.start();
    }

    public void showLikeAndComments(PostDetailModel.DataBean dataBean) {
        if (dataBean.thread.thumbup != null) {
            this.webActivityCommentBt.showMsg(dataBean.thread.replies);
            this.webActivityLikeBt.showMsg(dataBean.thread.thumbup.count);
            this.webActivityLikeBt.setChecked(dataBean.thread.thumbup.status);
        }
    }

    public void dismissReplyPanel() {
        this.webActivityBottomLayout.setVisibility(8);
    }

    public void showReplyPanel() {
        if (this.imgAdapter == null || this.imgAdapter.getItemCount() == 0) {
            this.webActivityBottomLayout.setVisibility(0);
        } else {
            this.mCommentLayoutContainer.setVisibility(0);
            this.webActivityBottomLayout.setVisibility(8);
            this.mActivityCommentsSelectList.setVisibility(0);
        }
        this.isShowBottomLayout = true;
    }

    public void jumpToComments(boolean z) {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_COMMENT, this.fid + JSMethod.NOT_SET + this.tid);
        CommentsActivity.jump((BaseActivity) this, this.fid, this.tid, z);
    }

    private void showLoadingView() {
        this.mRefreshView.setVisibility(8);
        if (this.mProgress != null) {
            this.mProgress.setVisibility(0);
        }
        this.layoutPostFailed.setVisibility(8);
        this.layoutPostDeleted.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showFailedView() {
        dismissReplyPanel();
        this.mRefreshView.setVisibility(8);
        if (this.mProgress != null) {
            this.mProgress.setVisibility(8);
        }
        this.layoutPostFailed.setVisibility(0);
        this.layoutPostDeleted.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showPostDeletedView() {
        dismissReplyPanel();
        this.mRefreshView.setVisibility(8);
        if (this.mProgress != null) {
            this.mProgress.setVisibility(8);
        }
        this.layoutPostFailed.setVisibility(8);
        this.layoutPostDeleted.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void dismissLoading() {
        this.mRefreshView.setVisibility(0);
        if (this.mRefreshView != null) {
            this.mRefreshView.setRefreshing(false);
        }
        if (this.mProgress != null) {
            this.mProgress.setVisibility(8);
        }
        this.layoutPostFailed.setVisibility(8);
        this.layoutPostDeleted.setVisibility(8);
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        runOnUiThread(new Runnable() {
            public void run() {
                PostDetailActivity.this.getData();
            }
        });
    }

    public void onRefresh() {
        getData();
    }

    private void share(String str, String str2) {
        new ShareDialog(this).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    /* access modifiers changed from: protected */
    public void SharePost() {
        if (!ConnectionHelper.isOpenNetwork(this)) {
            toast(Integer.valueOf(R.string.bbs_network_unavaliable));
        } else {
            new ShareDialog(this).setShareTitle(this.shareTitle).setShareUrl(this.threadUrl).setCallbackManager(this.callbackManager).show();
        }
    }

    /* access modifiers changed from: protected */
    public void changeFavState(String str, String str2) {
        super.changeFavState(str, str2);
        if (!LoginManager.getInstance().hasLogin()) {
            gotoAccount();
        } else if (!TextUtils.isEmpty(str2)) {
            GoogleTrackerUtil.sendRecordEvent("web", Constants.WebView.CLICK_FAVORITE, str);
            showProDialog(getString(R.string.str_dialog_wait));
            ApiClient.postFavorite(str, str2, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                public void accept(@NonNull JsonObject jsonObject) throws Exception {
                    PostDetailActivity.this.dismissProDialog();
                    PostDetailActivity.this.handleCommentResponseData(jsonObject);
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    PostDetailActivity.this.dismissProDialog();
                }
            });
        }
    }

    public void onVote(int i, String str, String str2, List<String> list) {
        showProDialog(getString(R.string.bbs_loading));
        ApiClient.postVotePoll(str, str2, list, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() == 0) {
                    PostDetailActivity.this.onRefresh();
                } else {
                    PostDetailActivity.this.toast(baseResult.getErrmsg());
                }
                PostDetailActivity.this.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                PostDetailActivity.this.dismissProDialog();
            }
        });
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.follow(str, true, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        PostDetailActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        PostDetailActivity.this.adapter.getItems().get(i).getAuthor().base.isfollow = 1;
                        PostDetailActivity.this.adapter.notifyDataSetChanged();
                    } else {
                        PostDetailActivity.this.toast(baseResult.getErrmsg());
                    }
                    PostDetailActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    PostDetailActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_hint), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                PostDetailActivity.this.showProDialog(PostDetailActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.follow(str, false, PostDetailActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() == 0) {
                            PostDetailActivity.this.adapter.getItems().get(i).getAuthor().base.isfollow = 0;
                            PostDetailActivity.this.adapter.notifyDataSetChanged();
                        } else {
                            PostDetailActivity.this.toast(baseResult.getErrmsg());
                        }
                        PostDetailActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        PostDetailActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }

    public void onFollowColumn(final int i, final String str, boolean z) {
        if (!z) {
            ApiClient.followColumn(str, 1, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    PostDetailModel.DataBean.Column column;
                    if (baseResult.getErrno() == 0) {
                        PostDetailActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        if (PostDetailActivity.this.adapter.getItems() != null && i < PostDetailActivity.this.adapter.getItems().size() && (column = PostDetailActivity.this.adapter.getItems().get(i).getColumn()) != null && column.column != null) {
                            column.subscribe = 1;
                            PostDetailActivity.this.adapter.notifyDataSetChanged();
                            return;
                        }
                        return;
                    }
                    PostDetailActivity.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                }
            });
        } else {
            DialogFactory.showAlert((Context) this, getString(R.string.unfollow_column_tip), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
                public void onOkClick(View view) {
                    ApiClient.followColumn(str, 0, PostDetailActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                        public void accept(@NonNull BaseResult baseResult) throws Exception {
                            PostDetailModel.DataBean.Column column;
                            if (baseResult.getErrno() != 0) {
                                PostDetailActivity.this.toast(baseResult.getErrmsg());
                            } else if (PostDetailActivity.this.adapter.getItems() != null && i < PostDetailActivity.this.adapter.getItems().size() && (column = PostDetailActivity.this.adapter.getItems().get(i).getColumn()) != null && column.column != null) {
                                column.subscribe = 0;
                                PostDetailActivity.this.adapter.notifyDataSetChanged();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 9999 && i2 == -1) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_result");
            boolean booleanExtra = intent.getBooleanExtra(MultiImageSelectorActivity.EXTRA_RESULT_BOOLEAN, false);
            if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                if (booleanExtra) {
                    uploadImage(stringArrayListExtra.get(0));
                } else {
                    String saveCacheBitmap = ImageUtil.saveCacheBitmap("temp", ImageUtil.compressQuality(ImageUtil.compressSize(stringArrayListExtra.get(0)), 0.9f));
                    if (!TextUtils.isEmpty(saveCacheBitmap)) {
                        uploadImage(saveCacheBitmap);
                    }
                }
            }
        }
        if (i == 119 && i2 == -1) {
            this.imgAdapter.setData(intent.getStringArrayListExtra("select_result"));
            this.mActivityCommentsSelectList.setVisibility(0);
            this.mCommentLayoutContainer.setVisibility(0);
            this.webActivityBottomLayout.setVisibility(8);
        }
    }

    public void onReply(BaseForumCommentsBean.PostListBean postListBean) {
        if (postListBean != null) {
            CommentsActivity.jump(this, this.fid, this.tid, postListBean.pid, postListBean.author);
        }
    }

    public void onBan(String str) {
        showProDialog("");
        ApiClient.post(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                PostDetailActivity.this.dismissProDialog();
                if (baseResult == null) {
                    return;
                }
                if (baseResult.getErrno() == 0) {
                    PostDetailActivity.this.onRefresh();
                } else {
                    PostDetailActivity.this.toast(baseResult.getErrmsg());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                PostDetailActivity.this.dismissProDialog();
            }
        });
    }

    public void onClickLink(String str) {
        if (!checkJumpUrl(str)) {
            WebActivity.jump(this, str);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        mGugukaDialogFragment = null;
        LoginManager.getInstance().removeLoginListener(this);
    }
}
