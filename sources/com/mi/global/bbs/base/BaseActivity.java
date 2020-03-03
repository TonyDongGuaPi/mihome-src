package com.mi.global.bbs.base;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.VolleyLog;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.account.sdk.activity.AccountActivity;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamsProvider;
import com.mi.global.bbs.model.PrizeShareInfo;
import com.mi.global.bbs.model.SyncModel;
import com.mi.global.bbs.model.TitleMenu;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.checkin.SignDetailActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.column.ColumnHomeActivity;
import com.mi.global.bbs.ui.forum.NewsForumActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.AndroidBug5497Workaround;
import com.mi.global.bbs.utils.ChannelUtil;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DownloadPluginDialogUtil;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.KeyboardChangeListener;
import com.mi.global.bbs.utils.NetworkErrorHandler;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.ProfileMesView;
import com.mi.global.bbs.view.dialog.GugukaDialogFragment;
import com.mi.global.bbs.view.dialog.LoadingDialog;
import com.mi.global.bbs.view.dialog.UpdateDialog;
import com.mi.global.bbs.view.webview.WebViewCookieManager;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.model.Tags;
import com.mi.model.UpdaterInfo;
import com.mi.util.AppUpdater;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.ResourceUtil;
import com.mi.util.Utils;
import com.mi.util.permission.PermissionUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.xiaomi.payment.data.MibiConstants;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaseActivity extends AccountActivity implements LoginManager.AccountListener, LifecycleProvider<ActivityEvent> {
    protected static final int ACTION_DOWNLOAD = 256;
    protected static final int ACTION_FAVOR = 4;
    protected static final int ACTION_FOLLOW = 128;
    protected static final int ACTION_MSG = 1;
    protected static final int ACTION_POST = 8;
    protected static final int ACTION_SEARCH = 2;
    protected static final int ACTION_SETTINGS = 16;
    protected static final int ACTION_SHARE = 64;
    protected static final int ACTION_TASK = 32;
    private static final int REQUEST_FB_CARD = 65555;
    /* access modifiers changed from: private */
    public static final String TAG = com.mi.activity.BaseActivity.class.getSimpleName();
    public static GugukaDialogFragment mGugukaDialogFragment;
    public CallbackManager callbackManager;
    protected boolean contentNeedMargin = true;
    boolean isUpdate = false;
    private ImageView ivRightButton;
    private LinearLayout layoutRightBtn;
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    protected AndroidBug5497Workaround mAndroidBug5497Workaround;
    protected LinearLayout mAppBarLayout;
    /* access modifiers changed from: protected */
    public FrameLayout mContentLayout;
    private ImageView mFloatingActionButton;
    public LoadingDialog mProgressDialog;
    protected ViewGroup mRrootLayout;
    /* access modifiers changed from: protected */
    public FontTextView mTitleView;
    /* access modifiers changed from: protected */
    public LinearLayout mToolBarContainer;
    /* access modifiers changed from: protected */
    public View mToolBarDivider;
    /* access modifiers changed from: protected */
    public View mToolbarAgent;
    /* access modifiers changed from: protected */
    public ImageView mUpImageView;
    protected AppUpdater mUpdater;
    protected LinearLayout menuLayout;
    protected View notificationBtn;
    protected TextView notificationRedDot;
    ShareDialog shareDialog;
    protected boolean statusBarDark = true;
    protected View.OnClickListener titleBackListener = new View.OnClickListener() {
        public void onClick(View view) {
            BaseActivity.this.onBackPressed();
        }
    };

    private void OtherServiceLogout() {
    }

    public void ShareEventPost(String str) {
    }

    /* access modifiers changed from: protected */
    public void SharePost() {
    }

    /* access modifiers changed from: protected */
    public void changeFavState(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void goToPost() {
    }

    /* access modifiers changed from: protected */
    public void goToPushSetting() {
    }

    public void goToTask() {
    }

    public void onInvalidAuthonToken() {
    }

    /* access modifiers changed from: protected */
    public void onSearch(View view) {
    }

    /* access modifiers changed from: protected */
    public void reloadRefresh() {
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.lifecycleSubject.onNext(ActivityEvent.CREATE);
        setRequestedOrientation(1);
        translucentStatusBar();
        statusBarDarkMode(this.statusBarDark);
        setContentView(R.layout.bbs_activity_base);
        this.mRrootLayout = (ViewGroup) findViewById(R.id.parentView);
        this.mContentLayout = (FrameLayout) findViewById(R.id.content_base);
        this.menuLayout = (LinearLayout) findViewById(R.id.app_title_bar_menu);
        this.layoutRightBtn = (LinearLayout) findViewById(R.id.layout_title_bar_end);
        this.ivRightButton = (ImageView) findViewById(R.id.app_title_bar_end);
        this.callbackManager = CallbackManager.Factory.create();
        this.mFloatingActionButton = (ImageView) findViewById(R.id.floating_button);
        this.mAppBarLayout = (LinearLayout) findViewById(R.id.app_title_bar);
        this.mTitleView = (FontTextView) findViewById(R.id.app_title_bar_title);
        this.mTitleView.setTextStyle(4);
        this.mUpImageView = (ImageView) findViewById(R.id.app_title_bar_up);
        this.mToolBarContainer = (LinearLayout) findViewById(R.id.toolbar_container);
        this.mToolbarAgent = findViewById(R.id.toolbar_agent);
        this.mToolBarDivider = findViewById(R.id.toolbar_divider);
        this.notificationBtn = findViewById(R.id.me_fragment_notification_lv);
        this.notificationRedDot = (TextView) findViewById(R.id.notification_red);
        adjustStatusBar(this.mToolbarAgent);
        if (this.contentNeedMargin) {
            this.mRrootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    BaseActivity.this.mRrootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ((FrameLayout.LayoutParams) BaseActivity.this.mContentLayout.getLayoutParams()).topMargin = BaseActivity.this.mToolBarContainer.getHeight();
                    BaseActivity.this.mContentLayout.requestLayout();
                }
            });
        }
        this.mAndroidBug5497Workaround = AndroidBug5497Workaround.assistActivity(this, true);
    }

    public void hideToolBar() {
        if (this.mToolBarContainer != null) {
            this.mToolBarContainer.setVisibility(8);
        }
    }

    public Resources getResources() {
        if (Build.VERSION.SDK_INT >= 24) {
            return getApplicationContext().getResources();
        }
        return super.getResources();
    }

    public void setFloatingActionButtonListener(View.OnClickListener onClickListener, String str) {
        if (onClickListener != null) {
            this.mFloatingActionButton.setVisibility(0);
            this.mFloatingActionButton.setOnClickListener(onClickListener);
            if (!TextUtils.isEmpty(str)) {
                ImageLoader.showImage(this.mFloatingActionButton, str);
            } else {
                Glide.a((FragmentActivity) this).a(Integer.valueOf(R.drawable.bbs_ic_porn)).a(this.mFloatingActionButton);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hideFloatingButton() {
        if (this.mFloatingActionButton != null) {
            this.mFloatingActionButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void setTitleAndBack(String str, View.OnClickListener onClickListener) {
        this.mTitleView.setText(str);
        this.mUpImageView.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public void setTitleAndBack(@StringRes int i, View.OnClickListener onClickListener) {
        setTitle(i);
        this.mUpImageView.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public void setTitleAndBack(@StringRes int i) {
        setTitle(i);
        this.mUpImageView.setOnClickListener(this.titleBackListener);
    }

    /* access modifiers changed from: protected */
    public void setTitleAndRightBack(@StringRes int i, @DrawableRes int i2, View.OnClickListener onClickListener) {
        setTitle(i);
        this.layoutRightBtn.setVisibility(0);
        this.menuLayout.setVisibility(8);
        this.ivRightButton.setImageResource(i2);
        this.ivRightButton.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public void setTitleAndLeftBack(@StringRes int i, @DrawableRes int i2) {
        setTitle(i);
        this.mUpImageView.setVisibility(0);
        this.menuLayout.setVisibility(8);
        this.layoutRightBtn.setVisibility(8);
        this.mUpImageView.setImageResource(i2);
        this.mUpImageView.setOnClickListener(this.titleBackListener);
    }

    /* access modifiers changed from: protected */
    public void setLeftAndTitleAndRight(@DrawableRes int i, @StringRes int i2, @DrawableRes int i3, View.OnClickListener onClickListener) {
        setTitle(i2);
        this.mUpImageView.setOnClickListener(this.titleBackListener);
        this.mUpImageView.setImageResource(i);
        this.layoutRightBtn.setVisibility(0);
        this.menuLayout.setVisibility(8);
        this.ivRightButton.setImageResource(i3);
        this.ivRightButton.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public final void setLeftAndTitleAndRightButton(@DrawableRes int i, @StringRes int i2, @LayoutRes int i3, View.OnClickListener onClickListener) {
        setTitle(i2);
        this.mUpImageView.setOnClickListener(this.titleBackListener);
        this.mUpImageView.setImageResource(i);
        this.layoutRightBtn.setVisibility(0);
        this.menuLayout.setVisibility(8);
        this.ivRightButton.setVisibility(8);
        this.layoutRightBtn.removeAllViews();
        View inflate = LayoutInflater.from(this).inflate(i3, (ViewGroup) null, false);
        if (inflate != null) {
            this.layoutRightBtn.addView(inflate);
            inflate.setOnClickListener(onClickListener);
        }
    }

    /* access modifiers changed from: protected */
    public void setCustomContentView(int i) {
        this.mContentLayout.addView(LayoutInflater.from(this).inflate(i, this.mContentLayout, false));
    }

    public void showFragment(Fragment fragment) {
        if (this.mContentLayout != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.content_base, fragment);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void showFragment(Fragment fragment, String str) {
        if (this.mContentLayout != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.content_base, fragment, str);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void showFragment(int i, Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(i, fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    public void showProDialog(String str) {
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            this.mProgressDialog = new LoadingDialog(this);
            this.mProgressDialog.show();
        }
    }

    public void dismissProDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void onLogin(String str, String str2, String str3) {
        LogUtil.b(TAG, String.format("BaseActivity-onLogin:%s, %s, %s", new Object[]{str, str2, str3}));
        if (mGugukaDialogFragment != null && mGugukaDialogFragment.getDialog() != null && mGugukaDialogFragment.getDialog().isShowing()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    BaseActivity.mGugukaDialogFragment.addGuakaView(true);
                }
            });
        }
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i, int i2, int i3) {
        LogUtil.b(TAG, String.format("BaseActivity-userinfoupdate:%s, %s, %s, %d, %d, %d", new Object[]{str, str2, str3, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
    }

    /* access modifiers changed from: protected */
    public void sendMiStatInterface(String str, String str2) {
        MiStatInterface.a(Constants.getPageTypeByURL(str), getClass().getSimpleName(), "event", str2);
    }

    public void onLogout() {
        LogUtil.b(TAG, "logout");
        WebViewCookieManager.removeLoginCookie(this);
        WebViewCookieManager.clearCustomCookies(this);
        WebViewCookieManager.removeAllCookie(this);
    }

    public void changeFavState(int i) {
        for (int i2 = 0; i2 < this.menuLayout.getChildCount(); i2++) {
            ViewGroup viewGroup = (ViewGroup) this.menuLayout.getChildAt(i2);
            String str = TAG;
            LogUtil.b(str, "changeFavState getTag " + viewGroup.getTag());
            if ("bbs_fav".equals(viewGroup.getTag())) {
                View childAt = viewGroup.getChildAt(0);
                if (i == 1) {
                    childAt.setBackgroundResource(R.drawable.bbs_faved);
                } else {
                    childAt.setBackgroundResource(R.drawable.bbs_fav);
                }
            }
        }
    }

    public void refreshTitleMenu(String str) {
        this.layoutRightBtn.setVisibility(8);
        this.menuLayout.setVisibility(0);
        this.menuLayout.removeAllViews();
        if (!TextUtils.isEmpty(str)) {
            try {
                for (TitleMenu imageButtonByType : (List) new Gson().fromJson(str, new TypeToken<List<TitleMenu>>() {
                }.getType())) {
                    setImageButtonByType(this.menuLayout, imageButtonByType);
                }
            } catch (Exception e) {
                String str2 = TAG;
                LogUtil.b(str2, "refreshTitleMenu erro " + e.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkCardActivity(boolean z) {
        if (mGugukaDialogFragment == null) {
            mGugukaDialogFragment = GugukaDialogFragment.newInstance("guaguaka");
            mGugukaDialogFragment.setCancelable(false);
            mGugukaDialogFragment.show(getSupportFragmentManager().beginTransaction(), "guaguaka");
            LogUtil.b(TAG, ",checkGameInfo mGugukaDialogFragment  null :");
        } else if (!LoginManager.getInstance().hasLogin()) {
            mGugukaDialogFragment.getDialog().show();
            mGugukaDialogFragment.addLoginView();
        }
        if (LoginManager.getInstance().hasLogin()) {
            mGugukaDialogFragment.addGuakaView(z);
        }
    }

    public boolean checkJumpUrl(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String appUrl = ConnectionHelper.getAppUrl(str);
        if (appUrl.toLowerCase().contains(Constants.WebView.WEB_LOGIN_URL.toLowerCase())) {
            gotoAccount();
            return true;
        } else if (!TextUtils.isEmpty(appUrl) && appUrl.startsWith(ConnectionHelper.getPluginUrl())) {
            DownloadPluginDialogUtil.download(this, appUrl);
            return true;
        } else if (appUrl.contains("space-uid-")) {
            UserCenterActivity.jump(this, str.substring(str.indexOf("space-uid-") + "space-uid-".length(), str.lastIndexOf(".")));
            return true;
        } else {
            if (appUrl.startsWith("mailto:") || appUrl.startsWith("tel:")) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (appUrl.toLowerCase().contains("account.xiaomi.com/pass/serviceLogin".toLowerCase())) {
                LoginManager.getInstance().logout();
                gotoLogin();
                return true;
            } else if (appUrl.endsWith("forum.php")) {
                NewsForumActivity.jump(this, 0);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_COLUMN)) {
                ColumnHomeActivity.jump(this);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_COLUMN_DETAIL)) {
                ColumnDetailActivity.jumpWithUrl(this, appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_SIGN_DETAIL)) {
                SignDetailActivity.jumpWithUrl(this, appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_SHORT_CONTENT_DETAIL)) {
                ShortContentDetailActivity.jumpWithUrl(this, appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.GO_STORE_RN) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_SECOND) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_TEST) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_SECOND_TEST)) {
                String str3 = "bbs_" + Utils.getTopActivity();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("goreactnative://mi.com"));
                if (appUrl.contains("?")) {
                    str2 = appUrl + "&origin=" + str3;
                } else {
                    str2 = appUrl + "?origin=" + str3;
                }
                intent.putExtra("url", str2);
                startActivity(intent);
                return true;
            } else {
                try {
                    if (ConnectionHelper.needOpenInBrowser(appUrl)) {
                        Uri parse = Uri.parse(appUrl);
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        startActivity(intent2);
                        return true;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return false;
            }
        }
    }

    public void checkGameInfo(boolean z) {
        String stringPref = Utils.Preference.getStringPref(this, Constants.Prefence.PREF_KEY_GAME_INFO, "");
        if (!TextUtils.isEmpty(stringPref)) {
            try {
                JSONObject jSONObject = new JSONObject(stringPref);
                int optInt = jSONObject.optInt("open", -1);
                long optLong = jSONObject.optLong("start");
                long optLong2 = jSONObject.optLong("end");
                final String optString = jSONObject.optString("urlLink");
                String optString2 = jSONObject.optString(MibiConstants.fU);
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                hideFloatingButton();
                if (optInt > 0 && currentTimeMillis >= optLong && currentTimeMillis < optLong2) {
                    if (optInt == 2 && !TextUtils.isEmpty(optString)) {
                        setFloatingActionButtonListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                BaseActivity.this.refreshWebUrl(optString);
                            }
                        }, optString2);
                    } else if (optInt == 1) {
                        checkCardActivity(z);
                        setFloatingActionButtonListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                BaseActivity.mGugukaDialogFragment = null;
                                BaseActivity.this.checkGameInfo(true);
                            }
                        }, optString2);
                    }
                }
            } catch (Exception e) {
                hideFloatingButton();
                String str = TAG;
                LogUtil.b(str, ",checkGameInfo Exception :" + e.toString());
            }
        } else {
            hideFloatingButton();
        }
    }

    /* access modifiers changed from: protected */
    public void shareGainChance() {
        if (mGugukaDialogFragment != null) {
            mGugukaDialogFragment.shareGainChance();
        }
    }

    public void goShareCardEventFB() {
        FacebookSdk.setApplicationId(ResourceUtil.a("bbs_facebook_app_id"));
        PrizeShareInfo eventShare = Constants.ShareContent.getEventShare();
        GoogleTrackerUtil.sendRecordEvent("guaguaka", "goShareCardEventFB", eventShare.getShareUrl());
        String shareTitle = eventShare.getShareTitle();
        ShareLinkContent build = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentTitle(shareTitle).setContentDescription(eventShare.getShareDes()).setContentUrl(Uri.parse(eventShare.getShareUrl()))).setImageUrl(Uri.parse(eventShare.getShareImaUrl())).build();
        this.shareDialog = new ShareDialog((Activity) this);
        this.shareDialog.registerCallback(this.callbackManager, new FacebookCallback<Sharer.Result>() {
            public void onSuccess(Sharer.Result result) {
                LogUtil.b(BaseActivity.TAG, " shareDialog onSuccess");
                BaseActivity.this.shareGainChance();
            }

            public void onCancel() {
                LogUtil.b(BaseActivity.TAG, "shareDialog onCancel");
                BaseActivity.this.shareGainChance();
            }

            public void onError(FacebookException facebookException) {
                String access$000 = BaseActivity.TAG;
                LogUtil.b(access$000, "shareDialog onError" + facebookException.toString());
                BaseActivity.this.shareGainChance();
                facebookException.printStackTrace();
            }
        });
        try {
            LogUtil.b(TAG, "goShareFB ShareDialog    ");
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                this.shareDialog.show(build);
                LogUtil.b(TAG, "goShareFB ShareDialog.canShow    ");
                return;
            }
            LogUtil.b(TAG, "goShareFB ShareDialog. url ");
            String facebook = ConnectionHelper.getFacebook();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(facebook));
            startActivityForResult(intent, REQUEST_FB_CARD);
        } catch (Exception e) {
            String str = TAG;
            LogUtil.b(str, "FB share exception:" + e.toString());
            e.printStackTrace();
        }
    }

    public void setImageButtonByType(LinearLayout linearLayout, final TitleMenu titleMenu) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        GoogleTrackerUtil.sendRecordEvent("menu", Constants.WebView.CLICK_MENU, titleMenu.getType());
        if ("alarm".equals(titleMenu.getType())) {
            ProfileMesView profileMesView = new ProfileMesView(this);
            String value = titleMenu.getValue();
            int i = 0;
            if (!TextUtils.isEmpty(value)) {
                i = Integer.valueOf(value).intValue();
            }
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_NEW_MESSAGE, i);
            profileMesView.setMesCount(i);
            profileMesView.setLayoutParams(layoutParams);
            profileMesView.setGravity(16);
            profileMesView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 1, titleMenu);
                }
            });
            linearLayout.addView(profileMesView);
            return;
        }
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setGravity(16);
        ImageButton imageButton = new ImageButton(this);
        linearLayout2.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Coder.a((Activity) this, 20.0f), Coder.a((Activity) this, 20.0f));
        imageButton.setClickable(true);
        imageButton.setLayoutParams(layoutParams2);
        linearLayout2.setPadding(Coder.a((Activity) this, 10.0f), Coder.a((Activity) this, 10.0f), Coder.a((Activity) this, 10.0f), Coder.a((Activity) this, 10.0f));
        if (Constants.TitleMenu.MENU_DOWNLOAD.equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_down);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 256, titleMenu);
                }
            });
        } else if ("search".equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_ic_search);
            imageButton.setTag("search");
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 2, titleMenu);
                }
            });
        } else if (Constants.TitleMenu.MENU_FAVORITE.equals(titleMenu.getType())) {
            linearLayout2.setTag("bbs_fav");
            if ("true".equals(titleMenu.getValue())) {
                imageButton.setBackgroundResource(R.drawable.bbs_faved);
            } else {
                imageButton.setBackgroundResource(R.drawable.bbs_fav);
            }
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 4, titleMenu);
                }
            });
        } else if (Constants.TitleMenu.MENU_FOLLOW.equals(titleMenu.getType())) {
            linearLayout2.setTag("bbs_fav");
            if ("true".equals(titleMenu.getValue())) {
                imageButton.setBackgroundResource(R.drawable.bbs_faved);
            } else {
                imageButton.setBackgroundResource(R.drawable.bbs_fav);
            }
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 128, titleMenu);
                }
            });
        } else if ("share".equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_actionbar_share);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.SharePost();
                }
            });
        } else if (Constants.TitleMenu.MENU_SHARE_EVENT.equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_actionbar_share);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 64, titleMenu);
                }
            });
        } else if (Constants.TitleMenu.MENU_WRITE.equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_post);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 8, titleMenu);
                }
            });
        } else if (Constants.TitleMenu.MENU_TASK.equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_ic_task);
            linearLayout2.setTag("calendar");
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 32, titleMenu);
                }
            });
        } else if (Constants.TitleMenu.MENU_SETTING_PUSH.equals(titleMenu.getType())) {
            imageButton.setBackgroundResource(R.drawable.bbs_ic_setting);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseActivity.this.onActionItemClick(view, 16, titleMenu);
                }
            });
        } else {
            return;
        }
        linearLayout2.addView(imageButton);
        linearLayout.addView(linearLayout2);
    }

    /* access modifiers changed from: protected */
    public void onActionItemClick(View view, int i, TitleMenu titleMenu) {
        if (i == 4) {
            changeFavState(Constants.TitleMenu.TYPE_FAVORITE, titleMenu.getTid());
        } else if (i == 8) {
            goToPost();
        } else if (i == 16) {
            goToPushSetting();
        } else if (i == 32) {
            goToTask();
        } else if (i != 64) {
            if (i == 128) {
                changeFavState("forum", titleMenu.getTid());
            } else if (i != 256) {
                switch (i) {
                    case 1:
                        onMessage();
                        return;
                    case 2:
                        onSearch(view);
                        return;
                    default:
                        return;
                }
            } else {
                refreshWebUrl(titleMenu.getValue());
            }
        } else if (!TextUtils.isEmpty(titleMenu.getShareEvent())) {
            ShareEventPost(titleMenu.getShareEvent());
        }
    }

    public boolean onMessage() {
        if (LoginManager.getInstance().hasLogin()) {
            refreshWebUrl(ConnectionHelper.getProfileAlarmUrl());
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_HOME_NEW_MESSAGE, 0);
            return true;
        }
        gotoAccount();
        return false;
    }

    public void refreshWebUrl(String str) {
        if (!checkJumpUrl(str)) {
            WebActivity.jump(this, ConnectionHelper.getAppUrl(str));
        }
    }

    public void goToLogOut() {
        LoginManager.getInstance().logout();
        OtherServiceLogout();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = TAG;
        LogUtil.b(str, "onActivityResult requestCode = " + i);
        if (i == REQUEST_FB_CARD) {
            shareGainChance();
        }
        this.callbackManager.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void toast(Object obj) {
        if (obj instanceof String) {
            MiToast.a((Context) this, (CharSequence) obj, 0);
        }
        if (obj instanceof Integer) {
            MiToast.a((Context) this, ((Integer) obj).intValue(), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void checkNetWork() {
        if (!ConnectionHelper.isOpenNetwork(this)) {
            toast(Integer.valueOf(R.string.bbs_network_unavaliable));
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionUtil.a(i, strArr, iArr);
        PermissionClaimer.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void login(Runnable runnable) {
        PrintStream printStream = System.out;
        printStream.println("hasLogin:" + LoginManager.getInstance().hasLogin() + ", uid:" + TextUtils.isEmpty(LoginManager.getInstance().getUserId()));
        if (!LoginManager.getInstance().hasLogin() || TextUtils.isEmpty(LoginManager.getInstance().getUserId())) {
            gotoAccount();
        } else {
            runnable.run();
        }
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mTitleView.setText(charSequence);
    }

    public void setTitle(@StringRes int i) {
        super.setTitle(i);
        this.mTitleView.setText(i);
    }

    /* access modifiers changed from: protected */
    public void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE, com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
        }
    }

    /* access modifiers changed from: protected */
    public int getStatusBarHeight() {
        int i = 0;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            int parseInt = Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString());
            try {
                return getResources().getDimensionPixelSize(parseInt);
            } catch (Exception e) {
                int i2 = parseInt;
                e = e;
                i = i2;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return i;
        }
    }

    public void checkUpdate(final boolean z) {
        if (this.mUpdater == null) {
            this.mUpdater = new AppUpdater(this);
        }
        if (!z && !this.mUpdater.needCheck()) {
            return;
        }
        if (z) {
            showProDialog("");
            ApiClient.getSyncInfo(ParamsProvider.getSyncParams("" + Device.r, ChannelUtil.getChannel(this), ChannelUtil.getApkMD5(this)), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                public void accept(@io.reactivex.annotations.NonNull JsonObject jsonObject) throws Exception {
                    BaseActivity.this.dismissProDialog();
                    if (jsonObject != null) {
                        try {
                            BaseActivity.this.handleUpdateData(z, new JSONObject(jsonObject.toString()));
                        } catch (Exception unused) {
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@io.reactivex.annotations.NonNull Throwable th) throws Exception {
                    BaseActivity.this.handleNetworkError(th);
                }
            });
            return;
        }
        handleUpdateData(z, SyncModel.response);
    }

    /* access modifiers changed from: private */
    public void handleUpdateData(boolean z, JSONObject jSONObject) {
        JSONObject jSONObject2;
        UpdaterInfo updaterInfo = null;
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("versionInfo");
                    String optString = optJSONObject2.optString("version", (String) null);
                    int optInt = optJSONObject2.optInt("versionCode");
                    String optString2 = optJSONObject2.optString("url", (String) null);
                    Utils.Preference.setIntPref(this, "versionCode", optInt);
                    String str = TAG;
                    LogUtil.b(str, "handleUpdateData Device.APP_VERSION:" + Device.r + ", versionCode:" + optInt);
                    if (optInt > Device.r) {
                        UpdaterInfo updaterInfo2 = new UpdaterInfo();
                        updaterInfo2.b = optString2;
                        updaterInfo2.c = optJSONObject2.optString("md5", (String) null);
                        updaterInfo2.d = optJSONObject2.optString(Tags.VersionUpdate.d, (String) null);
                        updaterInfo2.e = optString;
                        updaterInfo2.f = optInt;
                        updaterInfo2.g = optJSONObject2.optBoolean("forceUpdate", false);
                        JSONArray optJSONArray = optJSONObject2.optJSONArray("notes");
                        ArrayList<UpdaterInfo.DescType> arrayList = new ArrayList<>();
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                if (!optJSONArray.isNull(i) && (jSONObject2 = (JSONObject) optJSONArray.opt(i)) != null) {
                                    UpdaterInfo.DescType descType = new UpdaterInfo.DescType();
                                    descType.mDescType = jSONObject2.optString("type");
                                    descType.mDesc = jSONObject2.optString("desc");
                                    arrayList.add(descType);
                                }
                            }
                        }
                        updaterInfo2.f7375a = arrayList;
                        updaterInfo = updaterInfo2;
                    }
                } else {
                    Utils.Preference.setLongPref(this, "pref_last_update_is_ok", Long.valueOf(System.currentTimeMillis()));
                }
            } catch (Exception unused) {
                VolleyLog.d(TAG, "JSON parse error");
                return;
            }
        }
        if (updaterInfo != null) {
            final String str2 = updaterInfo.b;
            amendPrivateValue(this.mUpdater, "mURL", str2);
            amendPrivateValue(this.mUpdater, "mMd5", updaterInfo.c);
            amendPrivateValue(this.mUpdater, "mPatchUrl", updaterInfo.d);
            final boolean z2 = updaterInfo.g;
            UpdateDialog updateDialog = new UpdateDialog(this);
            if (z2) {
                updateDialog.setCanceledOnTouchOutside(false);
                updateDialog.setCancelable(false);
            }
            updateDialog.setOnUpdateClickListener(new UpdateDialog.OnUpdateClickListener() {
                public void onUpdate() {
                    BaseActivity.this.isUpdate = true;
                    if (z2) {
                        Utils.Preference.setLongPref(BaseActivity.this, "pref_last_check_update", Long.valueOf(System.currentTimeMillis() - 30000));
                    }
                    BaseActivity.this.mUpdater.download(str2);
                }
            });
            updateDialog.setUpdateMsg(updaterInfo);
            updateDialog.show();
            updateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    if (!z2 || BaseActivity.this.isUpdate) {
                        Utils.Preference.setLongPref(BaseActivity.this, "pref_last_check_update", Long.valueOf(System.currentTimeMillis() + 3600000));
                        return;
                    }
                    Utils.Preference.setLongPref(BaseActivity.this, "pref_last_check_update", Long.valueOf(System.currentTimeMillis() - 30000));
                    if (Build.VERSION.SDK_INT >= 16) {
                        BaseActivity.this.finishAffinity();
                    } else {
                        System.exit(0);
                    }
                }
            });
        } else if (z) {
            toast(Integer.valueOf(com.mi.micomponents.R.string.no_update));
        }
    }

    private void amendPrivateValue(AppUpdater appUpdater, String str, String str2) {
        try {
            Field declaredField = appUpdater.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(appUpdater, str2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public String getQuantityString(int i, int i2) {
        return getResources().getQuantityString(i, i2, new Object[]{Integer.valueOf(i2)});
    }

    /* access modifiers changed from: protected */
    public void handleNetworkError(Throwable th) {
        String handle = NetworkErrorHandler.handle(th);
        if (!TextUtils.isEmpty(handle)) {
            toast(handle);
        }
        dismissProDialog();
    }

    /* access modifiers changed from: protected */
    public boolean isLiteVersion() {
        return Utils.Preference.getBooleanPref(this, "isLite", false);
    }

    @CheckResult
    @NonNull
    public final Observable<ActivityEvent> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent activityEvent) {
        return RxLifecycle.a(this.lifecycleSubject, activityEvent);
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.a((Observable<ActivityEvent>) this.lifecycleSubject);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
    }

    /* access modifiers changed from: protected */
    public void getRenderDuration() {
        super.getRenderDuration();
        MiStatInterface.a("render_duration", getClass().getSimpleName(), "render_duration_label", "render_duration", this.renderDuration);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onDestroy() {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        if (this.mAndroidBug5497Workaround != null) {
            this.mAndroidBug5497Workaround.setKeyboardChangeListener((KeyboardChangeListener) null);
        }
    }

    public boolean setMiuiStatusBarDarkMode(Activity activity, boolean z) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void setStatusBarDarkMode(boolean z) {
        Window window = getWindow();
        if (z) {
            window.addFlags(Integer.MIN_VALUE);
            window.getDecorView().setSystemUiVisibility(8192);
            return;
        }
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
    }

    /* access modifiers changed from: protected */
    public void statusBarDarkMode(boolean z) {
        setMiuiStatusBarDarkMode(this, z);
        setStatusBarDarkMode(z);
    }

    /* access modifiers changed from: protected */
    public void adjustStatusBar(View view) {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            view.getLayoutParams().height = statusBarHeight;
            view.requestLayout();
        }
    }
}
