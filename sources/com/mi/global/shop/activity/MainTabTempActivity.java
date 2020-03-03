package com.mi.global.shop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.ui.HomeFragment;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ThreadPool;
import com.mi.util.UserEncryptionUtil;

public class MainTabTempActivity extends BaseActivity {
    public static final String CHANGE_TAB = "change_tab";
    public static final String GO_USERCENTRAL = "go_usercentral";
    public static final int MAIN_TAB_DISCOVER = 2;
    public static final int MAIN_TAB_INDEX_CATEGORY = 1;
    public static final int MAIN_TAB_INDEX_HOME = 0;
    public static final int MAIN_TAB_USERCENTRAL = 3;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f5395a = "MainTabTempActivity";
    private boolean b = false;
    private HomeFragment c;
    /* access modifiers changed from: private */
    public NewUserInfoData d;
    private int e;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_maintabs);
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        LogUtil.b(f5395a, "refresh userinfo after login");
        d();
    }

    public void onLogout() {
        super.onLogout();
        LogUtil.b(f5395a, "Maintab logout start");
        LogUtil.b(f5395a, "Maintab logout end");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateUnpaidBadge();
        d();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LogUtil.b(f5395a, "on Resume finish");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ShopApp.m().onActivityResult(i, i2, intent);
    }

    private void a() {
        b();
    }

    public void startCartActivity() {
        if (LocaleHelper.g()) {
            startActivityForResult(new Intent(this, ShoppingCartActivity.class), 22);
            return;
        }
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", ConnectionHelper.ay());
        startActivity(intent);
    }

    private void b() {
        this.c = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, this.c).commit();
    }

    public void finish() {
        super.finish();
        ApplicationContextHolder.l();
    }

    public void showHomeNotice(NewNoticeData newNoticeData) {
        try {
            this.c.a(newNoticeData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        ThreadPool.a(new Runnable() {
            public void run() {
                LoginManager u = LoginManager.u();
                if (u.j() && !Utils.Preference.getBooleanPref(MainTabTempActivity.this, "pref_miui_account_available", false)) {
                    String access$000 = MainTabTempActivity.f5395a;
                    LogUtil.b(access$000, "hasSystemAccount ,get getServiceID:" + String.valueOf(Constants.Account.e().c()));
                    String c = u.c(Constants.Account.e().c());
                    String access$0002 = MainTabTempActivity.f5395a;
                    LogUtil.b(access$0002, "hasSystemAccount ,get authToken:" + String.valueOf(c));
                    if (!TextUtils.isEmpty(c)) {
                        Utils.Preference.setBooleanPref(MainTabTempActivity.this, "pref_miui_account_available", true);
                    }
                }
            }
        });
    }

    private void d() {
        Request request;
        if (LoginManager.u().x() && !TextUtils.isEmpty(LoginManager.u().c())) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aJ()).buildUpon();
            buildUpon.appendQueryParameter("mUserId", UserEncryptionUtil.a(LoginManager.u().c()));
            buildUpon.appendQueryParameter("cUserId", UserEncryptionUtil.b(LoginManager.u().c()));
            buildUpon.appendQueryParameter("security", "true");
            AnonymousClass2 r1 = new SimpleCallback<NewUserInfoResult>() {
                public void a(NewUserInfoResult newUserInfoResult) {
                    if (newUserInfoResult.data != null) {
                        if (newUserInfoResult.data.jsonUserInfoData == null) {
                            NewUserInfoData unused = MainTabTempActivity.this.d = newUserInfoResult.data;
                        } else {
                            NewUserInfoData unused2 = MainTabTempActivity.this.d = newUserInfoResult.data.jsonUserInfoData;
                        }
                        MainTabTempActivity.this.saveAndUpdateUnpaidNum(MainTabTempActivity.this.d.not_pay_order_count);
                    }
                }

                public void a(String str) {
                    String access$000 = MainTabTempActivity.f5395a;
                    LogUtil.b(access$000, "RefreshUserInfo Exception:" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            } else {
                request = new SimpleJsonRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            }
            request.setTag(f5395a);
            RequestQueueUtil.a().add(request);
        }
    }
}
