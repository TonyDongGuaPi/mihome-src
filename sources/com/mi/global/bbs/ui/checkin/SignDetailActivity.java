package com.mi.global.bbs.ui.checkin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.CommentsSelectImgAdapter;
import com.mi.global.bbs.adapter.OnSortListener;
import com.mi.global.bbs.adapter.SignCommentsAdapter;
import com.mi.global.bbs.adapter.SimpleCheckedAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.SignComment;
import com.mi.global.bbs.model.SignComments;
import com.mi.global.bbs.model.SignContentResult;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.KeyboardChangeListener;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.view.MsgView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.multi_image_selector.MultiImageSelector;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;

public class SignDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnSortListener, SignCommentsAdapter.OnDeleteListener, SignCommentsAdapter.OnReplyListener {
    public static final int DEL_COMMENT = 0;
    public static final int DEL_CONTENT = 1;
    public static final int REQUEST_IMAGE = 291;
    public static final String SHOW_IME = "show_ime";
    public static final String SIGN_ID = "id";
    public static final int THUMBUP_COMMENT = 1;
    public static final int THUMBUP_CONTENT = 0;
    /* access modifiers changed from: private */
    public SignCommentsAdapter adapter;
    private String dynamic_id;
    /* access modifiers changed from: private */
    public CommentsSelectImgAdapter imgAdapter;
    LoadMoreManager loadMoreManager;
    @BindView(2131492903)
    EditText mActivityCommentsEditText;
    @BindView(2131492904)
    ImageView mActivityCommentsPickPicBt;
    @BindView(2131492905)
    RecyclerView mActivityCommentsSelectList;
    @BindView(2131492906)
    TextView mActivityCommentsSendBt;
    @BindView(2131493019)
    LinearLayout mBottomLayout;
    @BindView(2131493141)
    RelativeLayout mCommentLayoutContainer;
    @BindView(2131493155)
    ObservableRecyclerView mCommonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout mCommonRefreshView;
    @BindView(2131493571)
    MsgView mLikeBt;
    @BindView(2131493881)
    AppCompatEditText mReplyBt;
    @BindView(2131493882)
    LinearLayout mReplyHorizontalLayout;
    /* access modifiers changed from: private */
    public int order;
    private String[] orderArray;
    /* access modifiers changed from: private */
    public int page = 1;
    /* access modifiers changed from: private */
    public int pageNum = 10;
    /* access modifiers changed from: private */
    public String replyId;
    private boolean showIme = false;

    static /* synthetic */ int access$108(SignDetailActivity signDetailActivity) {
        int i = signDetailActivity.page;
        signDetailActivity.page = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(getString(R.string.check_in), this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_sign_detail);
        ButterKnife.bind((Activity) this);
        this.orderArray = getResources().getStringArray(R.array.comments_sort);
        this.mLikeBt.setMsgBackgroundColor(0);
        this.mLikeBt.setMsgTextColor(Color.parseColor("#666666"));
        this.mLikeBt.setMsgPadding((float) ((int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics())));
        this.mAndroidBug5497Workaround.setKeyboardChangeListener(new KeyboardChangeListener() {
            public void onKeyboardChange(boolean z) {
                if (z) {
                    SignDetailActivity.this.mCommentLayoutContainer.setVisibility(0);
                    SignDetailActivity.this.mActivityCommentsEditText.requestFocus();
                    SignDetailActivity.this.mBottomLayout.setVisibility(8);
                    return;
                }
                SignDetailActivity.this.mCommentLayoutContainer.setVisibility(8);
                SignDetailActivity.this.mBottomLayout.setVisibility(0);
            }
        });
        this.imgAdapter = new CommentsSelectImgAdapter(this);
        this.mActivityCommentsSelectList.setLayoutManager(new GridLayoutManager(this, 5));
        this.mActivityCommentsSelectList.setAdapter(this.imgAdapter);
        this.mActivityCommentsSelectList.setVisibility(8);
        this.mCommonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.mCommonRefreshView.setOnRefreshListener(this);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(1);
        this.mCommonRecycleView.setLayoutManager(wrapContentLinearLayoutManager);
        this.loadMoreManager = new LoadMoreManager();
        this.adapter = new SignCommentsAdapter(this, this.loadMoreManager);
        this.adapter.setOnReplyListener(this);
        this.adapter.setOnDeleteListener(this);
        this.adapter.setOnSortListener(this);
        this.mCommonRecycleView.setAdapter(this.adapter);
        this.mCommonRecycleView.addOnScrollListener(new InfiniteScrollListener(wrapContentLinearLayoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (SignDetailActivity.this.adapter.getCommentsSize() >= SignDetailActivity.this.page * SignDetailActivity.this.pageNum) {
                    SignDetailActivity.access$108(SignDetailActivity.this);
                    SignDetailActivity.this.obtainComments();
                }
            }
        });
        this.mCommonRecycleView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String unused = SignDetailActivity.this.replyId = "";
                SignDetailActivity.this.mActivityCommentsEditText.setHint("");
                ImeUtils.hideIme(SignDetailActivity.this.mActivityCommentsEditText);
                return false;
            }
        });
        this.imgAdapter.setOnDismissListener(new CommentsSelectImgAdapter.OnDismissListener() {
            public void onDismiss() {
                SignDetailActivity.this.mActivityCommentsSelectList.setVisibility(8);
            }
        });
        this.mLikeBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignDetailActivity.this.like();
            }
        });
        this.dynamic_id = getIntent().getStringExtra("id");
        this.showIme = getIntent().getBooleanExtra("show_ime", false);
        if (this.showIme) {
            delayRequestFocus();
        }
        obtainSignContent();
    }

    private void delayRequestFocus() {
        this.mActivityCommentsEditText.postDelayed(new Runnable() {
            public void run() {
                ImeUtils.showIme(SignDetailActivity.this.mActivityCommentsEditText);
                SignDetailActivity.this.mActivityCommentsEditText.requestFocus();
            }
        }, 500);
    }

    /* access modifiers changed from: private */
    public void obtainComments() {
        this.loadMoreManager.loadStarted();
        ApiClient.getApiService().getSignReply(this.dynamic_id, this.page, this.pageNum, this.order == 0 ? "newest" : "oldest").compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SignComments>() {
            public void accept(SignComments signComments) throws Exception {
                SignDetailActivity.this.dismissLoadingState();
                if (signComments != null && signComments.data != null) {
                    if (SignDetailActivity.this.page == 1) {
                        SignDetailActivity.this.adapter.clear();
                    }
                    SignDetailActivity.this.adapter.addSignComments(signComments.data);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignDetailActivity.this.dismissLoadingState();
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissLoadingState() {
        this.loadMoreManager.loadFinished();
        this.mCommonRefreshView.setRefreshing(false);
    }

    /* access modifiers changed from: private */
    public void obtainSignContent() {
        this.page = 1;
        this.loadMoreManager.loadStarted();
        ApiClient.getApiService().getSignDetail(this.dynamic_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<SignContentResult>() {
            public void accept(SignContentResult signContentResult) throws Exception {
                SignDetailActivity.this.dismissLoadingState();
                if (signContentResult == null || signContentResult.data == null) {
                    SignDetailActivity.this.toast(signContentResult.getErrmsg());
                    return;
                }
                SignDetailActivity.this.adapter.setSignContent(signContentResult.data);
                SignDetailActivity.this.mLikeBt.showMsg(signContentResult.data.thumbupnum);
                MsgView msgView = SignDetailActivity.this.mLikeBt;
                boolean z = true;
                if (signContentResult.data.havethumbup != 1) {
                    z = false;
                }
                msgView.setChecked(z);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignDetailActivity.this.dismissLoadingState();
            }
        });
    }

    @OnClick({2131492904, 2131492906})
    public void onClick(View view) {
        int id = view.getId();
        if (id == com.xiaomi.smarthome.R.string.mishopsdk_weibo) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
                public void onGranted() {
                    super.onGranted();
                    MultiImageSelector.a().c().a((ArrayList<String>) (ArrayList) SignDetailActivity.this.imgAdapter.getPathList()).a((Activity) SignDetailActivity.this, 291);
                }
            }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
        } else if (id == com.xiaomi.smarthome.R.string.mishopsdk_wx_timeline) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            postComment();
        }
    }

    public void onBackPressed() {
        ImeUtils.hideIme(this.mActivityCommentsEditText);
        finish();
        overridePendingTransition(0, R.anim.slide_from_top_to_bottom_out);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 291 && i2 == -1) {
            this.imgAdapter.setData(intent.getStringArrayListExtra("select_result"));
            this.mActivityCommentsSelectList.setVisibility(0);
            ImeUtils.showIme(this.mLikeBt);
        }
    }

    private void postComment() {
        String obj = this.mActivityCommentsEditText.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            toast(Integer.valueOf(R.string.post_reply_empty));
        } else if (obj.length() < 10) {
            toast(Integer.valueOf(R.string.post_content_short));
        } else {
            showProDialog("");
            ApiClient.postSignComments(this.dynamic_id, this.replyId, obj, this.imgAdapter.getPathList(), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(BaseResult baseResult) throws Exception {
                    SignDetailActivity.this.dismissProDialog();
                    if (baseResult.getErrno() == 0) {
                        SignDetailActivity.this.mActivityCommentsEditText.setText("");
                        ImeUtils.hideIme(SignDetailActivity.this.mActivityCommentsEditText);
                        SignDetailActivity.this.imgAdapter.clear();
                        SignDetailActivity.this.toast(Integer.valueOf(R.string.comment_success));
                        SignDetailActivity.this.mCommonRefreshView.setRefreshing(true);
                        SignDetailActivity.this.obtainSignContent();
                        return;
                    }
                    SignDetailActivity.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    SignDetailActivity.this.dismissProDialog();
                }
            });
        }
    }

    public static void jump(Context context, String str) {
        context.startActivity(new Intent(context, SignDetailActivity.class).putExtra("id", str));
    }

    public static void jump(Context context, String str, boolean z) {
        context.startActivity(new Intent(context, SignDetailActivity.class).putExtra("id", str).putExtra("show_ime", z));
    }

    public static void jumpWithUrl(Context context, String str) {
        try {
            context.startActivity(new Intent(context, SignDetailActivity.class).putExtra("id", str.substring(str.indexOf("signId=") + "signId=".length(), str.length())));
        } catch (Exception unused) {
        }
    }

    public void onReply(SignComment signComment) {
        this.mActivityCommentsEditText.setHint(getString(R.string.reply_to_, new Object[]{signComment.username}));
        this.replyId = signComment.id + "";
        ImeUtils.showIme(this.mActivityCommentsEditText);
    }

    public void onRefresh() {
        this.page = 1;
        obtainComments();
    }

    public void like() {
        changeLikeState();
        ApiClient.getApiService().signThumbUp(this.dynamic_id, 0).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() != 0) {
                    SignDetailActivity.this.toast(baseResult.getErrmsg());
                    SignDetailActivity.this.changeLikeState();
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignDetailActivity.this.changeLikeState();
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeLikeState() {
        boolean z = !this.mLikeBt.isChecked();
        this.mLikeBt.setChecked(z);
        int msgCount = this.mLikeBt.getMsgCount();
        this.mLikeBt.showMsg(z ? msgCount + 1 : msgCount == 0 ? 0 : msgCount - 1);
    }

    public void onDelete(int i, int i2, int i3) {
        showProDialog("");
        HashMap hashMap = new HashMap();
        if (i3 == 1) {
            hashMap.put("dynamic_id", this.dynamic_id + "");
            hashMap.put("type", "1");
            delSignContentOrReply(i, hashMap, true);
            return;
        }
        hashMap.put("dynamic_id", this.dynamic_id + "");
        hashMap.put("type", "0");
        hashMap.put(ParamKey.reply_id, i2 + "");
        hashMap.put("page", this.page + "");
        delSignContentOrReply(i, hashMap, false);
    }

    private void delSignContentOrReply(final int i, HashMap<String, String> hashMap, final boolean z) {
        ApiClient.getApiService().delSignReply(hashMap).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) throws Exception {
                SignDetailActivity.this.dismissProDialog();
                if (baseResult.getErrno() != 0) {
                    return;
                }
                if (z) {
                    SignDetailActivity.this.finish();
                } else {
                    SignDetailActivity.this.adapter.removeItem(i);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignDetailActivity.this.dismissProDialog();
            }
        });
    }

    public void onSortClick() {
        showSelectDialog();
    }

    private void showSelectDialog() {
        SimpleCheckedAdapter simpleCheckedAdapter = new SimpleCheckedAdapter(this, this.orderArray, this.order);
        final AlertDialog showListDialog = DialogFactory.showListDialog(this, simpleCheckedAdapter);
        simpleCheckedAdapter.setOnItemClickListener(new SimpleCheckedAdapter.OnItemClickListener() {
            public void onClick(int i) {
                int unused = SignDetailActivity.this.order = i;
                SignDetailActivity.this.mCommonRefreshView.setRefreshing(true);
                SignDetailActivity.this.onRefresh();
                showListDialog.dismiss();
            }
        });
    }
}
