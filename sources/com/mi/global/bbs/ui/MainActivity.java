package com.mi.global.bbs.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import bolts.AppLinks;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.google.gson.JsonObject;
import com.mi.blockcanary.BlockCanary;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamsProvider;
import com.mi.global.bbs.model.LaunchInfo;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.checkin.SignActivity;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.ui.qa.QuestionActivity;
import com.mi.global.bbs.utils.ChannelUtil;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.SplashUtil;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.main.ActionOptionItem;
import com.mi.global.bbs.view.main.MainActionOptionsFrame;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.util.Device;
import com.mi.util.permission.PermissionUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.File;

public class MainActivity extends BaseActivity {
    public static final String ACTION_JUMP_TO_MAIN_ACTIVITY = "action_jump_to_main_activity";
    public static final String ACTION_JUMP_TO_MAIN_ACTIVITY_HOME = "action_jump_to_main_HOME_activity";
    private static final String TAG = "MainActivity";
    private final Uri APP_URI = Uri.parse("android-app://com.example.sunlianqing.micommunitysdk/http/c.mi.com/" + LocaleHelper.APP_LOCALE + "/");
    private final String FIRST_IN = "main_first_in";
    private final int RC_SEARCH = PhotoshopDirectory.an;
    private final Uri WEB_URL = Uri.parse("http://c.mi.com/" + LocaleHelper.getLocalCookie() + "/");
    @BindView(2131493166)
    FrameLayout container;
    MainActionOptionsFrame mMainActionFrame;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate((Bundle) null);
        BBSApplication.webViewClear();
        this.mToolBarContainer.setVisibility(8);
        this.mContentLayout.requestLayout();
        setCustomContentView(R.layout.bbs_activity_main);
        ButterKnife.bind((Activity) this);
        LoginManager.getInstance().addLoginListener(this);
        initSyncResponse();
        checkStartupPermission();
        checkNetWork();
        getLaunchInfo();
        checkPushInfo();
        goon();
        updateUserInfo();
        openSystemLogin();
        initGoogleApi();
        setOptionsFrameAction();
        sendReportStatic();
    }

    private void recordClick(int i) {
        GoogleTrackerUtil.sendRecordEvent("bottombar", "click_btmbar", String.format("click_btmbar_%s", new Object[]{new String[]{"home", "subforums", Constants.PageFragment.PAGE_FOLLOWING, "acount"}[i]}));
    }

    /* access modifiers changed from: private */
    public void updateUserInfo() {
        if (LoginManager.getInstance().hasLogin()) {
            LoginManager.getInstance().loginCallback();
        }
    }

    private void initGoogleApi() {
        onGoogleDeeplinkIntent(getIntent());
        Uri a2 = AppLinks.a(getApplicationContext(), getIntent());
        if (a2 != null) {
            processAppLink(a2.toString());
        }
        try {
            initMiDir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getLaunchInfo() {
        Bundle bundleExtra;
        LaunchInfo launchInfo;
        if (getIntent() != null && getIntent().getExtras() != null && (bundleExtra = getIntent().getBundleExtra("data")) != null && bundleExtra.containsKey("launchInfo") && (launchInfo = (LaunchInfo) bundleExtra.getSerializable("launchInfo")) != null && !TextUtils.isEmpty(launchInfo.url)) {
            refreshWebUrl(ConnectionHelper.getAppUrl(launchInfo.url));
        }
    }

    private void checkPushInfo() {
        if (getIntent() != null) {
            getOtherAppAction();
        }
    }

    private void getOtherAppAction() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("action_url");
            String stringExtra2 = intent.getStringExtra("start_from");
            if (!TextUtils.isEmpty(stringExtra2)) {
                ApplicationContextHolder.e(stringExtra2);
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                refreshWebUrl(stringExtra);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        checkPushInfo();
    }

    public void goon() {
        ApiClient.getSyncInfo(ParamsProvider.getSyncParams("" + Device.r, ChannelUtil.getChannel(this), ChannelUtil.getApkMD5(this)), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                SplashUtil.loadInfo(jsonObject.toString());
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                MainActivity.this.handleNetworkError(th);
            }
        }, new Action() {
            public void run() throws Exception {
                MainActivity.this.checkToken();
            }
        });
    }

    private void checkBlockHold() {
        int intPref = Utils.Preference.getIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_BLOCK_HOLD, 3000);
        if (intPref > 0 && BlockCanary.a() != null) {
            BlockCanary.a().a((long) intPref);
        }
    }

    /* access modifiers changed from: private */
    public void checkToken() {
        if (Utils.Preference.getIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_PWD_CHANGE, 0) == 1) {
            goToLogOut();
        }
    }

    private void initSyncResponse() {
        SplashUtil.loadInfo(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SYNC_DATA, ""));
    }

    public void onDestroy() {
        LoginManager.getInstance().removeLoginListener(this);
        mGugukaDialogFragment = null;
        super.onDestroy();
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        runOnUiThread(new Runnable() {
            public void run() {
                MainActivity.this.updateUserInfo();
                MainActivity.this.reloadRefresh();
                DataManager.init().userInfoChange(true);
                RefreshManager.init().startRefresh(true);
            }
        });
    }

    public void onLogout() {
        super.onLogout();
        runOnUiThread(new Runnable() {
            public void run() {
                DataManager.init().userInfoChange(false);
                RefreshManager.init().startRefresh(false);
            }
        });
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i, int i2, int i3) {
        DataManager.init().userInfoChange(true);
    }

    public void onBackPressed() {
        if (this.mMainActionFrame.isExpandOptions()) {
            this.mMainActionFrame.hide();
        } else {
            super.onBackPressed();
        }
    }

    public void finish() {
        super.finish();
        ApplicationContextHolder.l();
    }

    /* access modifiers changed from: protected */
    public void goToPost() {
        super.goToPost();
        GoogleTrackerUtil.sendRecordEvent("home", "click_post", "");
        if (LoginManager.getInstance().hasLogin()) {
            startActivityForResult(new Intent(this, PostActivity.class), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            gotoAccount();
        }
    }

    public void goToTask() {
        super.goToTask();
        GoogleTrackerUtil.sendRecordEvent("home", Constants.WebView.CLICK_TASK, Constants.TitleMenu.MENU_TASK);
        if (LoginManager.getInstance().hasLogin()) {
            startActivityForResult(new Intent(this, SignActivity.class), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            gotoAccount();
        }
    }

    /* access modifiers changed from: protected */
    public void goToPushSetting() {
        super.goToPushSetting();
        if (LoginManager.getInstance().hasLogin()) {
            startActivity(new Intent(this, NoticeSettingsActivity.class));
        } else {
            gotoAccount();
        }
    }

    public void refreshWebUrl(String str) {
        super.refreshWebUrl(str);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        View findViewWithTag;
        Bundle extras;
        super.onActivityResult(i, i2, intent);
        if (!(i != 55555 || intent == null || (extras = intent.getExtras()) == null)) {
            refreshWebUrl(extras.getString("url"));
        }
        if (i == 1073 && (findViewWithTag = this.menuLayout.findViewWithTag("search")) != null) {
            findViewWithTag.setAlpha(1.0f);
        }
    }

    private void openSystemLogin() {
        Utils.Preference.setBooleanPref(BBSApplication.getInstance(), "pref_miui_account_available", true);
    }

    private void initMiDir() {
        File file = new File(Environment.getExternalStorageDirectory() + "/micommunity/");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void checkStartupPermission() {
        try {
            Device.a(BBSApplication.getInstance(), false);
        } catch (Exception unused) {
        }
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_device_info), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MainActivity.this.initDeviceWithCheckPermission();
            }
        }, "android.permission.READ_PHONE_STATE");
    }

    public void initDeviceWithCheckPermission() {
        boolean a2 = PermissionUtil.a((Context) this, "android.permission.READ_PHONE_STATE");
        if (a2) {
            Device.a(BBSApplication.getInstance(), a2);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        checkAppLink();
    }

    private void checkAppLink() {
        int indexOf;
        if (Utils.Preference.getBooleanPref(BBSApplication.getInstance(), "pref_applink_haslink", false)) {
            String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), "pref_applink_url", "");
            Utils.Preference.setBooleanPref(BBSApplication.getInstance(), "pref_applink_haslink", false);
            Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_applink_url", "");
            if (!TextUtils.isEmpty(stringPref) && stringPref.contains(Constants.TitleMenu.TYPE_FAVORITE) && (indexOf = stringPref.indexOf(Constants.TitleMenu.TYPE_FAVORITE)) > 0) {
                String str = ConnectionHelper.getAppIndexUrl() + stringPref.substring(indexOf, stringPref.length());
                LogUtil.b(TAG, "Get applink newUrl :" + str);
                refreshWebUrl(str);
            }
        }
    }

    private void processAppLink(String str) {
        String replace = str.replace("applink:", "");
        Utils.Preference.setBooleanPref(BBSApplication.getInstance(), "pref_applink_haslink", true);
        Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_applink_url", replace);
    }

    private void onGoogleDeeplinkIntent(Intent intent) {
        String action = intent.getAction();
        String dataString = intent.getDataString();
        if ("android.intent.action.VIEW".equals(action) && dataString != null) {
            processAppLink(dataString);
        }
    }

    /* access modifiers changed from: protected */
    public void onSearch(View view) {
        GoogleTrackerUtil.sendRecordEvent("home", Constants.ClickEvent.CLICK_SEARCH, Constants.ClickEvent.CLICK_SEARCH);
        startActivity(new Intent(this, SearchActivity.class));
    }

    private void clearForyouPrefer() {
        HomeFragment.foryouRefreshed = false;
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_LIST);
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_FOLLOW);
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_RECOMMED);
    }

    public boolean isOnlyActivity() {
        try {
            if (((ActivityManager) getSystemService("activity")).getRunningTasks(10).get(0).numActivities == 1) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private void setOptionsFrameAction() {
        this.mMainActionFrame = new MainActionOptionsFrame(this);
        this.mRrootLayout.addView(this.mMainActionFrame, new FrameLayout.LayoutParams(-1, -1));
        this.mMainActionFrame.setOnActionOptionsClickListener(new MainActionOptionsFrame.OnActionOptionsClickListener() {
            public void onActionItemClick(ActionOptionItem actionOptionItem, int i) {
                switch (i) {
                    case 0:
                        GoogleTrackerUtil.sendRecordEvent("home", "click_post", "click_post_thread_btn");
                        MainActivity.this.goToPost();
                        return;
                    case 1:
                        GoogleTrackerUtil.sendRecordEvent("home", "click_post", "click_post_qa_btn");
                        QuestionActivity.jump(MainActivity.this, "");
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void sendReportStatic() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_HOME_FROM_STORE, Constants.ClickEvent.TO_HOME_FROM_STORE, Constants.ClickEvent.TO_HOME_FROM_STORE);
    }
}
