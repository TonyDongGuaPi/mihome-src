package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.google.gson.JsonObject;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.CommentsSelectImgAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.ui.plate.CommentsActivity;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.KeyboardChangeListener;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.StatusBarClickListener;
import com.mi.global.bbs.utils.WebJsListenerImpl;
import com.mi.global.bbs.view.MsgView;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.global.bbs.view.swipe.MySwipeRefreshLayout;
import com.mi.global.bbs.view.swipe.SwipeRefreshLayout;
import com.mi.global.bbs.view.webview.WebViewCookieManager;
import com.mi.global.bbs.view.webview.WebViewHelper;
import com.mi.log.LogUtil;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.multi_image_selector.MultiImageSelectorActivity;
import com.mi.multimonitor.CrashReport;
import com.mi.multimonitor.Request;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.seek.biscuit.CompressException;
import com.seek.biscuit.CompressListener;
import com.taobao.weex.annotation.JSMethod;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class WebActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final int REQUEST_FB_SHARE_EVENT = 66666;
    public static final int REQUEST_IMAGE = 119;
    public static final int REQUEST_TAKE_IMAGE = 9999;
    private static final String SHOW_SOFT_INPUT = "show_soft_input";
    private static final String URL_DIRECT = "open_directly";
    private static final String URL_KEY = "url";
    private final String TAG = com.mi.global.shop.activity.WebActivity.TAG;
    /* access modifiers changed from: private */
    public int bottomSheetHeight = 0;
    /* access modifiers changed from: private */
    public boolean cancelScroll = false;
    private String fid;
    private boolean firstShowSoftInput = false;
    /* access modifiers changed from: private */
    public CommentsSelectImgAdapter imgAdapter;
    private boolean isDestroy = false;
    /* access modifiers changed from: private */
    public boolean isReplyFocus;
    /* access modifiers changed from: private */
    public boolean isShowBottomLayout = false;
    private String likeUrl;
    @BindView(2131492903)
    EditText mActivityCommentsEditText;
    @BindView(2131492905)
    RecyclerView mActivityCommentsSelectList;
    @BindView(2131493141)
    RelativeLayout mCommentLayoutContainer;
    /* access modifiers changed from: private */
    public ProgressBar mTitleProgress;
    @BindView(2131494257)
    LinearLayout mWebActivityBottomSheet;
    @BindView(2131494263)
    AppCompatEditText mWebActivityReplyBt;
    /* access modifiers changed from: private */
    public boolean needLoadDirectly = false;
    /* access modifiers changed from: private */
    public int preScrollY = 0;
    /* access modifiers changed from: private */
    public boolean scrollUp = false;
    private String shareTitle = "";
    private String tid;
    /* access modifiers changed from: private */
    public int toolbarHeight = 0;
    private String url;
    @BindView(2131494256)
    LinearLayout webActivityBottomLayout;
    @BindView(2131494258)
    MsgView webActivityCommentBt;
    @BindView(2131494259)
    MsgView webActivityLikeBt;
    @BindView(2131494261)
    ProgressBar webActivityProgressBar;
    @BindView(2131494262)
    MySwipeRefreshLayout webActivityRefreshView;
    @BindView(2131494264)
    ObservableWebView webActivityWebView;

    public static void jumpWithTid(Context context, String str) {
        jump(context, ApiClient.getAppUrl(String.format(UrlAction.THREAD_URL, new Object[]{str})));
    }

    public static void jump(Context context, String str) {
        String appUrl = ConnectionHelper.getAppUrl(str);
        if (ConnectionHelper.needOpenInBrowser(appUrl)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(appUrl));
            context.startActivity(intent);
            return;
        }
        context.startActivity(new Intent(context, WebActivity.class).putExtra("url", str));
    }

    public static void jumpDirectly(Context context, String str) {
        context.startActivity(new Intent(context, WebActivity.class).putExtra("url", str).putExtra(URL_DIRECT, true));
    }

    public static void jump(Context context, String str, boolean z) {
        String appUrl = ConnectionHelper.getAppUrl(str);
        if (ConnectionHelper.needOpenInBrowser(appUrl)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(appUrl));
            context.startActivity(intent);
            return;
        }
        context.startActivity(new Intent(context, WebActivity.class).putExtra("url", str).putExtra(SHOW_SOFT_INPUT, z));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        try {
            setCustomContentView(R.layout.bbs_activity_web);
            ButterKnife.bind((Activity) this);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
            if (CrashReport.get() != null) {
                CrashReport.postCrash(e);
            }
            finish();
        }
    }

    private void initView() {
        Uri data;
        this.mTitleProgress = (ProgressBar) findViewById(R.id.activity_progressBar);
        this.webActivityCommentBt.setMsgBackgroundColor(Color.parseColor("#f66b6b"));
        this.webActivityLikeBt.setMsgBackgroundColor(0);
        this.webActivityLikeBt.setMsgTextColor(Color.parseColor("#666666"));
        this.webActivityLikeBt.setMsgPadding((float) ((int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics())));
        GoogleTrackerUtil.sendRecordPage(com.mi.global.shop.activity.WebActivity.TAG);
        setTitleAndBack("", this.titleBackListener);
        dismissReplyPanel();
        LoginManager.getInstance().addLoginListener(this);
        this.url = getIntent().getStringExtra("url");
        this.needLoadDirectly = getIntent().getBooleanExtra(URL_DIRECT, false);
        this.firstShowSoftInput = getIntent().getBooleanExtra(SHOW_SOFT_INPUT, false);
        String action = getIntent().getAction();
        if (!(action == null || action.compareTo("android.intent.action.VIEW") != 0 || (data = getIntent().getData()) == null)) {
            this.url = data.toString();
        }
        if (TextUtils.isEmpty(this.url)) {
            this.url = ConnectionHelper.getAppIndexUrl();
        }
        addWebViewListener();
        this.webActivityWebView.getSettings().setJavaScriptEnabled(true);
        this.webActivityWebView.getSettings().setCacheMode(-1);
        this.webActivityWebView.addJavascriptInterface(new WebJsListenerImpl(this, this.webActivityWebView), "app");
        this.webActivityWebView.getSettings().setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > 7) {
            this.webActivityWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }
        if (Build.VERSION.SDK_INT >= 19 && BBSApplication.isUserDebug()) {
            ObservableWebView observableWebView = this.webActivityWebView;
            ObservableWebView.setWebContentsDebuggingEnabled(true);
        }
        WebViewHelper.initWebSetting(this.webActivityWebView);
        WebViewHelper.setUrl(this.url);
        this.webActivityRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.webActivityRefreshView.setOnRefreshListener(this);
        this.webActivityRefreshView.setViewGroup(this.webActivityWebView);
        this.mCommentLayoutContainer.setVisibility(8);
        this.imgAdapter = new CommentsSelectImgAdapter(this);
        this.mActivityCommentsSelectList.setLayoutManager(new GridLayoutManager(this, 5));
        this.mActivityCommentsSelectList.setAdapter(this.imgAdapter);
        this.mActivityCommentsSelectList.setVisibility(8);
        this.imgAdapter.setOnDismissListener(new CommentsSelectImgAdapter.OnDismissListener() {
            public void onDismiss() {
                WebActivity.this.mActivityCommentsSelectList.setVisibility(8);
            }
        });
        refreshByURL(this.url);
        checkGameInfo(false);
        this.mAndroidBug5497Workaround.setKeyboardChangeListener(new KeyboardChangeListener() {
            public void onKeyboardChange(boolean z) {
                if (!WebActivity.this.isShowBottomLayout) {
                    return;
                }
                if (z) {
                    WebActivity.this.mCommentLayoutContainer.setVisibility(0);
                    if (WebActivity.this.isReplyFocus) {
                        WebActivity.this.mActivityCommentsEditText.requestFocus();
                    }
                    WebActivity.this.webActivityBottomLayout.setVisibility(8);
                    return;
                }
                WebActivity.this.mCommentLayoutContainer.setVisibility(8);
                WebActivity.this.webActivityBottomLayout.setVisibility(0);
            }
        });
        this.mWebActivityReplyBt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                boolean unused = WebActivity.this.isReplyFocus = z;
            }
        });
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int unused = WebActivity.this.bottomSheetHeight = WebActivity.this.mWebActivityBottomSheet.getHeight();
                int unused2 = WebActivity.this.toolbarHeight = WebActivity.this.mToolBarContainer.getHeight();
                WebActivity.this.webActivityRefreshView.setProgressViewEndTarget(true, WebActivity.this.toolbarHeight * 2);
            }
        });
        this.webActivityWebView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onScrollChanged(int i, boolean z, boolean z2) {
                if (WebActivity.this.isShowBottomLayout && !WebActivity.this.touchBottomAction() && !WebActivity.this.cancelScroll) {
                    if (i > WebActivity.this.preScrollY && !WebActivity.this.scrollUp) {
                        boolean unused = WebActivity.this.scrollUp = true;
                        WebActivity.this.mWebActivityBottomSheet.animate().translationY((float) WebActivity.this.bottomSheetHeight);
                        WebActivity.this.transToolbar(WebActivity.this.scrollUp);
                    }
                    if (i < WebActivity.this.preScrollY && WebActivity.this.scrollUp) {
                        boolean unused2 = WebActivity.this.scrollUp = false;
                        WebActivity.this.mWebActivityBottomSheet.animate().translationY(0.0f);
                        WebActivity.this.transToolbar(WebActivity.this.scrollUp);
                    }
                    int unused3 = WebActivity.this.preScrollY = i;
                }
            }

            public void onDownMotionEvent() {
                boolean unused = WebActivity.this.cancelScroll = false;
                ImeUtils.hideIme(WebActivity.this.mWebActivityReplyBt);
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                boolean unused = WebActivity.this.cancelScroll = true;
            }
        });
        doubleClickToTop();
    }

    /* access modifiers changed from: private */
    public boolean touchBottomAction() {
        if (((int) (((float) this.webActivityWebView.getContentHeight()) * this.webActivityWebView.getScale())) - (this.webActivityWebView.getHeight() + this.webActivityWebView.getScrollY()) >= 5) {
            return false;
        }
        this.mWebActivityBottomSheet.animate().translationY(0.0f);
        transToolbar(false);
        return true;
    }

    /* access modifiers changed from: private */
    public void transToolbar(boolean z) {
        this.mToolBarContainer.animate().translationY(z ? (float) (-this.toolbarHeight) : 0.0f);
        if (z) {
            getWindow().addFlags(1024);
        } else {
            getWindow().clearFlags(1024);
        }
    }

    private void addWebViewListener() {
        this.webActivityWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                WebActivity.this.refreshWebUrl(str);
                boolean unused = WebActivity.this.needLoadDirectly = false;
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
                super.onPageFinished(webView, str);
                if (WebActivity.this.isShowBottomLayout) {
                    WebActivity.this.setWebViewPadding();
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                webView.loadUrl("javascript:document.body.innerHTML=\"\"");
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (BBSApplication.isUserTest()) {
                    sslErrorHandler.proceed();
                } else {
                    super.onReceivedSslError(webView, sslErrorHandler, sslError);
                }
            }
        });
        this.webActivityWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                WebActivity.this.showOrHideByProgress(WebActivity.this.webActivityProgressBar, i);
                if (i <= 0 || !WebActivity.this.isShowBottomLayout) {
                    WebActivity.this.setContentMargin(WebActivity.this.toolbarHeight);
                    if (WebActivity.this.mTitleProgress.getVisibility() == 0) {
                        WebActivity.this.mTitleProgress.setVisibility(8);
                        return;
                    }
                    return;
                }
                WebActivity.this.setContentMargin(0);
                WebActivity.this.showOrHideByProgress(WebActivity.this.mTitleProgress, i);
            }
        });
        this.mActivityCommentsEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z && !LoginManager.getInstance().hasLogin()) {
                    WebActivity.this.gotoAccount();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showOrHideByProgress(ProgressBar progressBar, int i) {
        progressBar.setVisibility(i == 100 ? 8 : 0);
        progressBar.setProgress(i);
    }

    /* access modifiers changed from: private */
    public void setContentMargin(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.webActivityRefreshView.getLayoutParams();
        if (layoutParams.topMargin != i) {
            layoutParams.topMargin = i;
            this.webActivityRefreshView.requestLayout();
        }
    }

    /* access modifiers changed from: private */
    public void setWebViewPadding() {
        String format = String.format(Locale.ENGLISH, "javascript:document.body.style.padding=\"%dpx 0px %dpx\"; void 0", new Object[]{Integer.valueOf((int) (((float) this.toolbarHeight) / getResources().getDisplayMetrics().density)), Integer.valueOf((int) (((float) this.bottomSheetHeight) / getResources().getDisplayMetrics().density))});
        if (this.webActivityWebView != null) {
            this.webActivityWebView.loadUrl(format);
        }
    }

    public void refreshByURL(String str) {
        if (!this.isDestroy) {
            if (this.needLoadDirectly) {
                this.webActivityWebView.loadUrl(str);
            } else if (!checkJumpUrl(str)) {
                clearForumData();
                dismissReplyPanel();
                setCookies();
                this.webActivityWebView.loadUrl(str);
            }
        }
    }

    private void setCookies() {
        WebViewCookieManager.addAppCookie();
        WebViewCookieManager.setLoginCookies(this);
        WebViewCookieManager.setShopLoginCookies(this);
        WebViewCookieManager.updateCustomCookies(this);
        WebViewCookieManager.showAllCookie(ConnectionHelper.getWebCookieDomain());
    }

    public void onBackPressed() {
        if (this.webActivityWebView.canGoBack()) {
            this.webActivityWebView.goBack();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void goToPost() {
        super.goToPost();
        GoogleTrackerUtil.sendRecordEvent("web", "click_post", "");
        if (LoginManager.getInstance().hasLogin()) {
            startActivityForResult(new Intent(this, PostActivity.class).putExtra("default_type", getTitle()), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            gotoAccount();
        }
    }

    /* access modifiers changed from: protected */
    public void goToPushSetting() {
        super.goToPushSetting();
        GoogleTrackerUtil.sendRecordEvent("web", Constants.TitleMenu.MENU_SETTING_PUSH, "");
        if (LoginManager.getInstance().hasLogin()) {
            startActivity(new Intent(this, AppSettingsActivity.class));
        } else {
            gotoAccount();
        }
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        runOnUiThread(new Runnable() {
            public void run() {
                WebActivity.this.onRefresh();
            }
        });
        LoginManager.getInstance().loginCallback();
    }

    public void goToLogOut() {
        super.goToLogOut();
        finish();
    }

    public void refreshWebUrl(String str) {
        if (!str.equals(this.webActivityWebView.getUrl())) {
            if (ViewHelper.getTranslationY(this.mToolBarContainer) != 0.0f) {
                transToolbar(false);
            }
            refreshByURL(str);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        super.onActivityResult(i, i2, intent);
        if (i == 9999 && i2 == -1) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_result");
            boolean booleanExtra = intent.getBooleanExtra(MultiImageSelectorActivity.EXTRA_RESULT_BOOLEAN, false);
            if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                if (booleanExtra) {
                    uploadImage(stringArrayListExtra.get(0));
                } else {
                    ImageUtil.compress((Context) this, (List<String>) stringArrayListExtra, (CompressListener) new CompressListener() {
                        public void onSuccess(String str) {
                            WebActivity.this.uploadImage(str);
                        }

                        public void onError(CompressException compressException) {
                            WebActivity.this.uploadImage(compressException.originalPath);
                        }
                    });
                }
            }
        }
        if (!(i != 55555 || intent == null || (extras = intent.getExtras()) == null)) {
            refreshWebUrl(extras.getString("url"));
        }
        if (i == 119 && i2 == -1) {
            this.imgAdapter.setData(intent.getStringArrayListExtra("select_result"));
            this.mActivityCommentsSelectList.setVisibility(0);
            this.mCommentLayoutContainer.setVisibility(0);
            this.webActivityBottomLayout.setVisibility(8);
            if (this.imgAdapter.getItemCount() > 0) {
                this.mActivityCommentsEditText.requestFocus();
                ImeUtils.showIme(this.mActivityCommentsEditText);
            }
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
                    WebActivity.this.dismissProDialog();
                    WebActivity.this.handleResponseData(jsonObject);
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    WebActivity.this.dismissProDialog();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleResponseData(JsonObject jsonObject) {
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
            LogUtil.b(com.mi.global.shop.activity.WebActivity.TAG, "changeFavState Exception " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void SharePost() {
        if (!ConnectionHelper.isOpenNetwork(this)) {
            toast(Integer.valueOf(R.string.bbs_network_unavaliable));
        } else {
            new ShareDialog(this).setShareTitle(this.shareTitle).setShareUrl(this.webActivityWebView.getUrl()).setCallbackManager(this.callbackManager).show();
        }
    }

    public void ShareEventPost(String str) {
        super.ShareEventPost(str);
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!ConnectionHelper.isOpenNetwork(this)) {
                    toast(Integer.valueOf(R.string.bbs_network_unavaliable));
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("title");
                String optString2 = jSONObject.optString("des");
                String optString3 = jSONObject.optString("shareImg");
                String optString4 = jSONObject.optString("shareUrl");
                final int optInt = jSONObject.optInt("callback");
                final String optString5 = jSONObject.optString("callbackJs");
                new ShareDialog(this).setShareTitle(optString).setShareText(optString2).setShareUrl(optString4).setShareImgUrl(optString3).setCallbackManager(this.callbackManager).setClickRunnable(new Runnable() {
                    public void run() {
                        WebActivity.this.handleJsCallback(optInt, optString5);
                    }
                }).show();
            }
        } catch (Exception unused) {
        }
    }

    public void onPause() {
        super.onPause();
        this.webActivityWebView.onPause();
    }

    public void onResume() {
        super.onResume();
        this.webActivityWebView.onResume();
    }

    /* access modifiers changed from: private */
    public void handleJsCallback(int i, String str) {
        if (i == 1) {
            final String str2 = "javascript:" + str + "()";
            this.webActivityWebView.post(new Runnable() {
                public void run() {
                    WebActivity.this.webActivityWebView.loadUrl(str2);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onSearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void disableRefresh(boolean z) {
        ViewGroup viewGroup = this.webActivityRefreshView.getViewGroup();
        if (viewGroup != null) {
            viewGroup.setOnTouchListener((View.OnTouchListener) null);
        }
        this.webActivityRefreshView.setEnabled(!z);
    }

    public void onRefresh() {
        if (this.webActivityWebView != null) {
            setCookies();
            this.webActivityWebView.reload();
            this.webActivityRefreshView.setRefreshing(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.isDestroy = true;
        this.webActivityWebView.loadUrl("about:blank");
        this.webActivityWebView.destroy();
        this.webActivityWebView = null;
        mGugukaDialogFragment = null;
        LoginManager.getInstance().removeLoginListener(this);
        super.onDestroy();
    }

    public void dismissReplyPanel() {
        this.webActivityBottomLayout.setVisibility(8);
    }

    public void showReplyPanel(String str, String str2) {
        if (this.imgAdapter == null || this.imgAdapter.getItemCount() == 0) {
            this.webActivityBottomLayout.setVisibility(0);
        } else {
            this.mCommentLayoutContainer.setVisibility(0);
            this.webActivityBottomLayout.setVisibility(8);
            this.mActivityCommentsSelectList.setVisibility(0);
        }
        this.fid = str;
        this.tid = str2;
        this.isShowBottomLayout = true;
        if (this.firstShowSoftInput) {
            ImeUtils.showIme(this.mActivityCommentsEditText);
        }
        setContentMargin(0);
        setWebViewPadding();
    }

    public void jumpToReplyPerson(String str, String str2, String str3, String str4) {
        CommentsActivity.jump(this, str, str2, str4, str3);
    }

    public void jumpToComments(boolean z) {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_COMMENT, this.fid + JSMethod.NOT_SET + this.tid);
        CommentsActivity.jump((BaseActivity) this, this.fid, this.tid, z);
    }

    @OnClick({2131494259, 2131494258, 2131492904, 2131492906})
    public void onClick(View view) {
        int i = 0;
        if (view.getId() == R.id.web_activity_likeBt) {
            if (!LoginManager.getInstance().hasLogin()) {
                gotoAccount();
            } else if (!TextUtils.isEmpty(this.likeUrl)) {
                GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_LIKE, this.fid + JSMethod.NOT_SET + this.tid);
                boolean isChecked = this.webActivityLikeBt.isChecked() ^ true;
                this.webActivityLikeBt.setChecked(isChecked);
                int msgCount = this.webActivityLikeBt.getMsgCount();
                if (isChecked) {
                    i = msgCount + 1;
                } else if (msgCount != 0) {
                    i = msgCount - 1;
                }
                this.webActivityLikeBt.showMsg(i);
                ApiClient.get(this.likeUrl);
            }
        } else if (view.getId() == R.id.web_activity_commentBt) {
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_COMMENT, "click_reply_mesview");
            jumpToComments(false);
        } else if (view.getId() == R.id.activity_comments_pick_pic_bt) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
                public void onGranted() {
                    super.onGranted();
                    MultiImageSelector.a().c().a((ArrayList<String>) (ArrayList) WebActivity.this.imgAdapter.getPathList()).a((Activity) WebActivity.this, 119);
                }
            }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
        } else if (view.getId() == R.id.activity_comments_send_bt) {
            ImeUtils.hideIme(this.mActivityCommentsEditText);
            postComment();
        }
    }

    public void takePicture() {
        LogUtil.b(com.mi.global.shop.activity.WebActivity.TAG, "takePicture takePicture ");
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) WebActivity.this, 9999, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: private */
    public void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                WebActivity.this.handleUploadResult(uploadResultModel.getData().getUrl());
                WebActivity.this.dismissProDialog();
                FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                WebActivity.this.handleNetworkError(th);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(String str) {
        LogUtil.b(com.mi.global.shop.activity.WebActivity.TAG, "takePicture handleUploadResult " + str);
        final String str2 = "javascript:takePictureResult('" + str + "')";
        this.webActivityWebView.post(new Runnable() {
            public void run() {
                WebActivity.this.webActivityWebView.loadUrl(str2);
            }
        });
    }

    public void showLikeAndComments(int i, int i2, boolean z, String str) {
        this.webActivityCommentBt.showMsg(i2);
        this.webActivityLikeBt.showMsg(i);
        this.webActivityLikeBt.setChecked(z);
        this.likeUrl = str;
        this.isShowBottomLayout = true;
        setContentMargin(0);
        setWebViewPadding();
    }

    private void postComment() {
        if (!LoginManager.getInstance().hasLogin()) {
            gotoAccount();
            return;
        }
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_COMMENT, Constants.ClickEvent.CLICK_COMMENT, this.fid + JSMethod.NOT_SET + this.tid);
        String obj = this.mActivityCommentsEditText.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            toast(Integer.valueOf(R.string.post_reply_empty));
        } else if (obj.length() < 10) {
            toast(Integer.valueOf(R.string.post_content_short));
        } else {
            showProDialog("");
            ApiClient.postComments(this.fid, this.tid, obj, this.imgAdapter.getPathList(), (String) null, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    WebActivity.this.dismissProDialog();
                    if (baseResult.getErrno() == 0) {
                        WebActivity.this.mActivityCommentsEditText.setText("");
                        ImeUtils.hideIme(WebActivity.this.mActivityCommentsEditText);
                        WebActivity.this.imgAdapter.clear();
                        WebActivity.this.toast(Integer.valueOf(R.string.comment_success));
                        return;
                    }
                    WebActivity.this.toast(baseResult.getErrmsg());
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    WebActivity.this.dismissProDialog();
                }
            });
        }
    }

    private void clearForumData() {
        this.webActivityCommentBt.showMsg(0);
        this.webActivityLikeBt.showMsg(0);
        this.webActivityLikeBt.setChecked(false);
        this.likeUrl = "";
        this.fid = "";
        this.tid = "";
        this.isShowBottomLayout = false;
    }

    public void setShareTitle(String str) {
        this.shareTitle = str;
    }

    private void doubleClickToTop() {
        this.mAppBarLayout.setOnClickListener(new StatusBarClickListener(new StatusBarClickListener.OnDoubleClickListener() {
            public void onDoubleClick() {
                WebActivity.this.animateScrollToTop();
            }
        }));
    }

    /* access modifiers changed from: private */
    public void animateScrollToTop() {
        int currentScrollY = this.webActivityWebView.getCurrentScrollY();
        if (currentScrollY > 0) {
            ValueAnimator duration = ValueAnimator.ofFloat((float) currentScrollY, 0.0f).setDuration(500);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    WebActivity.this.webActivityWebView.scrollTo(0, (int) valueAnimator.getAnimatedFraction());
                }
            });
            duration.start();
        }
    }
}
