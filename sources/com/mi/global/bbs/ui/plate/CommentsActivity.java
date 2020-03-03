package com.mi.global.bbs.ui.plate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.CommentsSelectImgAdapter;
import com.mi.global.bbs.adapter.ForumCommentsAdapter;
import com.mi.global.bbs.adapter.OnSortListener;
import com.mi.global.bbs.adapter.SimpleCheckedAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.model.BaseForumCommentsBean;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ForumComments;
import com.mi.global.bbs.model.ForumCommentsList;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.multi_image_selector.MultiImageSelector;
import com.taobao.weex.annotation.JSMethod;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public class CommentsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ForumCommentsAdapter.OnReplyListener, ForumCommentsAdapter.onBanClickListener, OnSortListener {
    private static final String FORUM_ID = "forum_id";
    private static final String REPLY_ID = "reply_id";
    private static final String REPLY_TO_WHO = "reply_to_who";
    private static final String REPLY_URL = "reply_url";
    public static final int REQUEST_IMAGE = 291;
    private static final String SHOW_IME = "show_ime";
    private static final String SUB_ID = "sub_id";
    @BindView(2131492903)
    EditText activityCommentsEditText;
    @BindView(2131492905)
    RecyclerView activityCommentsSelectList;
    /* access modifiers changed from: private */
    public ForumCommentsAdapter adapter;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    private String id;
    /* access modifiers changed from: private */
    public CommentsSelectImgAdapter imgAdapter;
    private LoadMoreManager loadMoreManager;
    /* access modifiers changed from: private */
    public int order = 0;
    private String[] orderArray;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public boolean refresh = false;
    /* access modifiers changed from: private */
    public String replyId;
    private String sid;
    /* access modifiers changed from: private */
    public int total = 0;

    static /* synthetic */ int access$208(CommentsActivity commentsActivity) {
        int i = commentsActivity.page;
        commentsActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(CommentsActivity commentsActivity) {
        int i = commentsActivity.page;
        commentsActivity.page = i - 1;
        return i;
    }

    public static void jump(BaseActivity baseActivity, String str, String str2) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivity(new Intent(baseActivity, CommentsActivity.class).putExtra(FORUM_ID, str2).putExtra(SUB_ID, str));
            baseActivity.overridePendingTransition(R.anim.slide_from_bottom_to_top_enter, 0);
            return;
        }
        baseActivity.gotoAccount();
    }

    public static void jump(BaseFragment baseFragment, String str, String str2) {
        Context context = baseFragment.getContext();
        if (!LoginManager.getInstance().hasLogin()) {
            baseFragment.gotoAccount();
        } else if (context != null) {
            context.startActivity(new Intent(context, CommentsActivity.class).putExtra(FORUM_ID, str2).putExtra(SUB_ID, str));
        }
    }

    public static void jump(BaseActivity baseActivity, String str, String str2, boolean z) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivity(new Intent(baseActivity, CommentsActivity.class).putExtra(FORUM_ID, str2).putExtra(SUB_ID, str).putExtra("show_ime", z));
            baseActivity.overridePendingTransition(R.anim.slide_from_bottom_to_top_enter, 0);
            return;
        }
        baseActivity.gotoAccount();
    }

    public static void jump(BaseActivity baseActivity, String str, String str2, String str3, String str4) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivity(new Intent(baseActivity, CommentsActivity.class).putExtra(FORUM_ID, str2).putExtra(SUB_ID, str).putExtra("reply_id", str3).putExtra(REPLY_TO_WHO, str4).putExtra("show_ime", true));
            baseActivity.overridePendingTransition(R.anim.slide_from_bottom_to_top_enter, 0);
            return;
        }
        baseActivity.gotoAccount();
    }

    public static void jump(BaseActivity baseActivity, String str, String str2, String str3) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivity(new Intent(baseActivity, CommentsActivity.class).putExtra(FORUM_ID, str2).putExtra(SUB_ID, str).putExtra(REPLY_URL, str3));
            baseActivity.overridePendingTransition(R.anim.slide_from_bottom_to_top_enter, 0);
            return;
        }
        baseActivity.gotoAccount();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.bbs_comments, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_comments);
        ButterKnife.bind((Activity) this);
        this.orderArray = getResources().getStringArray(R.array.comments_sort);
        this.loadMoreManager = new LoadMoreManager();
        this.commonRefreshView.setOnRefreshListener(this);
        this.imgAdapter = new CommentsSelectImgAdapter(this);
        this.activityCommentsSelectList.setLayoutManager(new GridLayoutManager(this, 5));
        this.activityCommentsSelectList.setAdapter(this.imgAdapter);
        this.activityCommentsSelectList.setVisibility(8);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.adapter = new ForumCommentsAdapter(this, this.loadMoreManager);
        this.adapter.setOnReplyListener(this);
        this.adapter.setOnSortListener(this);
        this.adapter.setOnBanClickListener(this);
        this.commonRecycleView.setAdapter(this.adapter);
        this.commonRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (CommentsActivity.this.adapter.getDataItemCount() < CommentsActivity.this.total + 1) {
                    CommentsActivity.access$208(CommentsActivity.this);
                    CommentsActivity.this.getComment();
                }
            }
        });
        this.commonRecycleView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String unused = CommentsActivity.this.replyId = "";
                CommentsActivity.this.activityCommentsEditText.setHint("");
                ImeUtils.hideIme(CommentsActivity.this.activityCommentsEditText);
                return false;
            }
        });
        this.imgAdapter.setOnDismissListener(new CommentsSelectImgAdapter.OnDismissListener() {
            public void onDismiss() {
                CommentsActivity.this.activityCommentsSelectList.setVisibility(8);
            }
        });
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        this.sid = intent.getStringExtra(SUB_ID);
        this.id = intent.getStringExtra(FORUM_ID);
        this.replyId = intent.getStringExtra("reply_id");
        String stringExtra = intent.getStringExtra(REPLY_TO_WHO);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.activityCommentsEditText.setHint(getString(R.string.reply_to_, new Object[]{stringExtra}));
        }
        String stringExtra2 = intent.getStringExtra(REPLY_URL);
        if (!TextUtils.isEmpty(stringExtra2)) {
            getCommentByUrl(stringExtra2);
        } else {
            getComment();
        }
        if (intent.getBooleanExtra("show_ime", false)) {
            delayRequestFocus();
        }
    }

    private void delayRequestFocus() {
        this.activityCommentsEditText.postDelayed(new Runnable() {
            public void run() {
                ImeUtils.showIme(CommentsActivity.this.activityCommentsEditText);
                CommentsActivity.this.activityCommentsEditText.requestFocus();
            }
        }, 800);
    }

    private void getCommentByUrl(String str) {
        this.page = 0;
        ApiClient.getForumComments(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                String jsonObject2 = jsonObject.toString();
                if (CommentsActivity.this.page == 1 || CommentsActivity.this.page == 0) {
                    ForumComments forumComments = (ForumComments) JsonParser.parse(jsonObject2, ForumComments.class);
                    if (!(forumComments == null || forumComments.data == null)) {
                        if (CommentsActivity.this.refresh) {
                            CommentsActivity.this.adapter.clear();
                        }
                        int unused = CommentsActivity.this.total = forumComments.data.total;
                        CommentsActivity.this.adapter.setThreadInfoAndTotal(forumComments.data.threadinfo, CommentsActivity.this.total);
                        CommentsActivity.this.adapter.setData(forumComments.data);
                    }
                } else {
                    ForumCommentsList forumCommentsList = (ForumCommentsList) JsonParser.parse(jsonObject2, ForumCommentsList.class);
                    if (!(forumCommentsList == null || forumCommentsList.data == null)) {
                        CommentsActivity.this.adapter.setData(forumCommentsList.data);
                    }
                }
                CommentsActivity.this.dismissProgress();
                boolean unused2 = CommentsActivity.this.refresh = false;
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                CommentsActivity.this.handleNetworkError(th);
                CommentsActivity.access$210(CommentsActivity.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void getComment() {
        if (!TextUtils.isEmpty(this.id)) {
            this.loadMoreManager.loadStarted();
            ApiClient.getForumComments(this.id, this.page, this.order, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                public void accept(@NonNull JsonObject jsonObject) throws Exception {
                    String jsonObject2 = jsonObject.toString();
                    if (CommentsActivity.this.page == 1 || CommentsActivity.this.page == 0) {
                        ForumComments forumComments = (ForumComments) JsonParser.parse(jsonObject2, ForumComments.class);
                        if (!(forumComments == null || forumComments.data == null)) {
                            if (CommentsActivity.this.refresh) {
                                CommentsActivity.this.adapter.clear();
                            }
                            int unused = CommentsActivity.this.total = forumComments.data.total;
                            CommentsActivity.this.adapter.setThreadInfoAndTotal(forumComments.data.threadinfo, CommentsActivity.this.total);
                            CommentsActivity.this.adapter.setData(forumComments.data);
                        }
                    } else {
                        ForumCommentsList forumCommentsList = (ForumCommentsList) JsonParser.parse(jsonObject2, ForumCommentsList.class);
                        if (!(forumCommentsList == null || forumCommentsList.data == null)) {
                            CommentsActivity.this.adapter.setData(forumCommentsList.data);
                        }
                    }
                    CommentsActivity.this.dismissProgress();
                    boolean unused2 = CommentsActivity.this.refresh = false;
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    CommentsActivity.this.handleNetworkError(th);
                    CommentsActivity.access$210(CommentsActivity.this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.commonRefreshView != null) {
            this.commonRefreshView.setRefreshing(false);
        }
        dismissProDialog();
    }

    public void onRefresh() {
        this.page = 1;
        this.refresh = true;
        getComment();
    }

    public void onReply(BaseForumCommentsBean.PostListBean postListBean) {
        this.activityCommentsEditText.setHint(getString(R.string.reply_to_, new Object[]{postListBean.author}));
        this.replyId = postListBean.pid;
        ImeUtils.showIme(this.activityCommentsEditText);
        this.activityCommentsEditText.requestFocus();
    }

    @OnClick({2131492904, 2131492906})
    public void onClick(View view) {
        if (view.getId() == R.id.activity_comments_pick_pic_bt) {
            ImeUtils.hideIme(this.activityCommentsEditText);
            PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
                public void onGranted() {
                    super.onGranted();
                    MultiImageSelector.a().c().a(9).a((ArrayList<String>) (ArrayList) CommentsActivity.this.imgAdapter.getPathList()).a((Activity) CommentsActivity.this, 291);
                }
            }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
        } else if (view.getId() == R.id.activity_comments_send_bt) {
            ImeUtils.hideIme(this.activityCommentsEditText);
            postComment();
        }
    }

    private void postComment() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COMMENT, Constants.ClickEvent.CLICK_COMMENT, this.sid + JSMethod.NOT_SET + this.id);
        String obj = this.activityCommentsEditText.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            toast(Integer.valueOf(R.string.post_reply_empty));
        } else if (obj.length() < 10) {
            toast(Integer.valueOf(R.string.post_content_short));
        } else {
            showProDialog("");
            ApiClient.postComments(this.sid, this.id, obj, this.imgAdapter.getPathList(), this.replyId, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    CommentsActivity.this.dismissProgress();
                    if (baseResult.getErrno() == 0) {
                        CommentsActivity.this.activityCommentsEditText.setText("");
                        ImeUtils.hideIme(CommentsActivity.this.activityCommentsEditText);
                        CommentsActivity.this.imgAdapter.clear();
                        CommentsActivity.this.toast(Integer.valueOf(R.string.comment_success));
                        CommentsActivity.this.commonRefreshView.setRefreshing(true);
                        CommentsActivity.this.onRefresh();
                        return;
                    }
                    CommentsActivity.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    CommentsActivity.this.dismissProgress();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 291 && i2 == -1) {
            this.imgAdapter.setData(intent.getStringArrayListExtra("select_result"));
            this.activityCommentsSelectList.setVisibility(0);
        }
    }

    public void onBackPressed() {
        ImeUtils.hideIme(this.activityCommentsEditText);
        finish();
        overridePendingTransition(0, R.anim.slide_from_top_to_bottom_out);
    }

    public void onSortClick() {
        showSelectDialog();
    }

    private void showSelectDialog() {
        SimpleCheckedAdapter simpleCheckedAdapter = new SimpleCheckedAdapter(this, this.orderArray, this.order);
        final AlertDialog showListDialog = DialogFactory.showListDialog(this, simpleCheckedAdapter);
        simpleCheckedAdapter.setOnItemClickListener(new SimpleCheckedAdapter.OnItemClickListener() {
            public void onClick(int i) {
                int unused = CommentsActivity.this.order = i;
                CommentsActivity.this.commonRefreshView.setRefreshing(true);
                CommentsActivity.this.onRefresh();
                showListDialog.dismiss();
            }
        });
    }

    public void onBan(String str) {
        showProDialog("");
        ApiClient.post(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                CommentsActivity.this.dismissProgress();
                if (baseResult == null) {
                    return;
                }
                if (baseResult.getErrno() == 0) {
                    CommentsActivity.this.onRefresh();
                } else {
                    CommentsActivity.this.toast(baseResult.getErrmsg());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                CommentsActivity.this.dismissProgress();
            }
        });
    }
}
