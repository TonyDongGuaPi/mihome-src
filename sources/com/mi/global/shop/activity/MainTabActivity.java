package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import bolts.AppLinks;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.android.exoplayer2.C;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.account.util.Constants;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.cache.WebCache;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.SyncInfo;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.newmodel.domain.DomainResult;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoData;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.ui.HomeFragment;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.DefaultDomain;
import com.mi.global.shop.util.LocationUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.MiStatUtil;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.PushRouteUtil;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.global.shop.xmsf.account.ShopSdkInitParamGroup;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ThreadPool;
import com.mi.util.UserEncryptionUtil;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class MainTabActivity extends BaseActivity {
    public static final String CHANGE_TAB = "change_tab";
    public static final String GO_USERCENTRAL = "go_usercentral";
    public static final int MAIN_TAB_DISCOVER = 2;
    public static final int MAIN_TAB_INDEX_CATEGORY = 1;
    public static final int MAIN_TAB_INDEX_HOME = 0;
    public static final int MAIN_TAB_USERCENTRAL = 3;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f5383a = "MainTabActivity";
    private Handler b;
    private boolean c = false;
    private HomeFragment d;
    /* access modifiers changed from: private */
    public NewUserInfoData e;
    private int f;

    /* access modifiers changed from: protected */
    public void installHotfix(String str) {
    }

    public void onCreate(Bundle bundle) {
        LogUtil.b(f5383a, "STARTUP start onCreate");
        b();
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_maintabs);
        b(getIntent());
        Uri a2 = AppLinks.a(this, getIntent());
        if (a2 != null) {
            String str = f5383a;
            LogUtil.b(str, "get facebook app link:" + a2.toString());
            b(a2.toString());
        } else {
            LogUtil.b(f5383a, "failed to get facebook app link");
        }
        j();
        this.b = new Handler();
        goon((SyncInfo.LaunchInfo) null);
        e();
        a(getIntent());
        if (c()) {
            MiToast.a((Context) this, R.string.shop_settting_data_saver_toast, 1);
        }
        if (NetworkUtil.d()) {
            MiToast.a((Context) this, R.string.shop_toast_network_unavailable, 1);
        }
        k();
        try {
            MiStatUtil.a(this);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str2 = f5383a;
        LogUtil.b(str2, "on Create finish ,this:" + toString());
        LocationUtil.a(this);
        if (!TextUtils.isEmpty(LocationUtil.b())) {
            MiShopStatInterface.b("locationEvent", f5383a, "location", "location", LocationUtil.b());
        }
        getDomain();
    }

    public void getDomain() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aD()).buildUpon();
        AnonymousClass1 r1 = new SimpleCallback<DomainResult>() {
            public void a(DomainResult domainResult) {
                if (domainResult.domainModels.size() > 0) {
                    Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, new Gson().toJson((Object) domainResult.domainModels));
                    MainTabActivity.this.a();
                    ConnectionHelper.b();
                    return;
                }
                Utils.Preference.setStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
            }

            public void a(String str) {
                String access$100 = MainTabActivity.f5383a;
                LogUtil.b(access$100, "getDomain Exception:" + str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), DomainResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), DomainResult.class, r1);
        }
        request.setTag(f5383a);
        RequestQueueUtil.a().add(request);
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        ArrayList arrayList;
        String stringPref = Utils.Preference.getStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
        if (!TextUtils.isEmpty(stringPref)) {
            ArrayList arrayList2 = new ArrayList();
            try {
                arrayList = (ArrayList) new Gson().fromJson(stringPref, new TypeToken<ArrayList<DomainModel>>() {
                }.getType());
            } catch (Exception unused) {
                arrayList = arrayList2;
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    DomainModel domainModel = (DomainModel) it.next();
                    if (LocaleHelper.b.endsWith(domainModel.local)) {
                        if (System.currentTimeMillis() >= domainModel.launchTime) {
                            Constants.Account.f6727a = domainModel.sid;
                            ConnectionHelper.E = domainModel.cookieDomain;
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    private void b() {
        if (ShopApp.h() == null) {
            finish();
            return;
        }
        PermissionUtil.a((Activity) this, (PermissionCallback) new SamplePermissionCallback() {
            public void onDenied() {
            }

            public void onResult() {
                MainTabActivity.this.initDeviceWithCheckPermission();
            }
        }, "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
    }

    public void initDeviceWithCheckPermission() {
        boolean a2 = PermissionUtil.a((Context) this, "android.permission.READ_PHONE_STATE");
        if (a2) {
            Device.a(ShopApp.g(), a2);
        }
    }

    private boolean c() {
        int intPref;
        if (!Setting.a() || !NetworkUtil.c() || (intPref = Utils.Preference.getIntPref(this, "pref_data_saver_toast_count", 0)) >= 3) {
            return false;
        }
        Utils.Preference.setIntPref(this, "pref_data_saver_toast_count", intPref + 1);
        return true;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        LogUtil.b(f5383a, "refresh userinfo after login");
        l();
    }

    public void onLogout() {
        super.onLogout();
        LogUtil.b(f5383a, "Maintab logout start");
        LogUtil.b(f5383a, "Maintab logout end");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateUnpaidBadge();
        l();
        if (!this.c) {
            d();
        }
    }

    private void d() {
        LogUtil.b(f5383a, "check app link");
        List asList = Arrays.asList(new String[]{ConnectionHelper.an(), ConnectionHelper.ao(), ConnectionHelper.au(), ConnectionHelper.ch});
        if (Utils.Preference.getBooleanPref(ShopApp.g(), "pref_applink_haslink", false)) {
            LogUtil.b(f5383a, "has app link");
            String stringPref = Utils.Preference.getStringPref(ShopApp.g(), "pref_applink_url", "");
            Utils.Preference.setBooleanPref(ShopApp.g(), "pref_applink_haslink", false);
            Utils.Preference.setStringPref(ShopApp.g(), "pref_applink_url", "");
            if (!TextUtils.isEmpty(stringPref)) {
                String str = f5383a;
                LogUtil.b(str, "Get applink url:" + stringPref);
                if (asList.contains(stringPref)) {
                    String str2 = f5383a;
                    LogUtil.b(str2, "Filter url:" + stringPref);
                    return;
                }
                String str3 = f5383a;
                LogUtil.b(str3, "send applink url:" + stringPref + " to webactivity");
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", stringPref);
                startActivity(intent);
                return;
            }
            return;
        }
        LogUtil.b(f5383a, "No applink url detected");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LogUtil.b(f5383a, "on Resume finish");
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    private void a(Intent intent) {
        Bundle extras;
        if (ShopApp.h == null) {
            ShopApp.h = new ShopSdkInitParamGroup.Builder("community_sdk", LocaleHelper.e).a();
        }
        if (intent == null || intent.getAction() == null || !intent.getAction().equals("com.mi.global.shop.action_show_m_site")) {
            if (getIntent().getData() != null) {
                Uri data = getIntent().getData();
                if (!TextUtils.isEmpty(data.getQueryParameter("nativeOpenUrl"))) {
                    this.c = true;
                    String queryParameter = data.getQueryParameter("nativeOpenUrl");
                    Log.d("openUrl", queryParameter);
                    Intent intent2 = new Intent(this, WebActivity.class);
                    intent2.putExtra("url", queryParameter);
                    startActivity(intent2);
                } else if (!TextUtils.isEmpty(data.getQueryParameter("diagnosetool"))) {
                    this.c = true;
                    if ("1".equals(data.getQueryParameter("diagnosetool"))) {
                        startActivity(new Intent(this, OTExActivity.class));
                    }
                } else {
                    this.c = false;
                }
            }
            if (intent != null) {
                String stringExtra = intent.getStringExtra("action_url");
                String stringExtra2 = intent.getStringExtra("start_from");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    ApplicationContextHolder.e(stringExtra2);
                }
                if (!TextUtils.isEmpty(stringExtra)) {
                    Intent intent3 = new Intent(this, WebActivity.class);
                    intent3.putExtra("url", stringExtra);
                    startActivity(intent3);
                }
            }
            if (intent != null && (extras = intent.getExtras()) != null && extras.getString("com.mi.global.shop.action_switch_main") != null) {
                return;
            }
            return;
        }
        String stringExtra3 = intent.getStringExtra("url");
        String str = f5383a;
        LogUtil.b(str, "url:" + stringExtra3);
        if (!TextUtils.isEmpty(stringExtra3) && !PushRouteUtil.a((Context) this, stringExtra3)) {
            LaunchWebActivity.startActivityStandard(this, stringExtra3);
        }
        if (!isOnlyActivity()) {
            finish();
        }
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

    public static boolean isRunningForeground(Context context) {
        String packageName = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
        if (packageName == null || !packageName.equals(context.getPackageName())) {
            return false;
        }
        return true;
    }

    public void changeRegion() {
        PendingIntent activity = PendingIntent.getActivity(this, 123456, new Intent(this, MainTabActivity.class), C.ENCODING_PCM_MU_LAW);
        AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(1, System.currentTimeMillis() + 100, activity);
        } else {
            alarmManager.set(1, System.currentTimeMillis() + 100, activity);
        }
        finish();
        System.exit(0);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 15 && i2 != 0 && i2 == -1 && intent.getIntExtra("changeRegion", 0) == 1) {
            changeRegion();
        }
        if (i != 22 || i2 != 0) {
            ShopApp.m().onActivityResult(i, i2, intent);
        }
    }

    private void e() {
        g();
        f();
        this.mBackView.setVisibility(0);
        setTitle(R.string.main_home);
        this.mOrderListView.setVisibility(0);
    }

    private void f() {
        findViewById(R.id.fab_product).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainTabActivity.this.startActivity(new Intent(MainTabActivity.this, ProductActivity.class));
            }
        });
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

    private void g() {
        this.d = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, this.d).commit();
    }

    private void h() {
        this.b.postDelayed(new Runnable() {
            public void run() {
                MainTabActivity.this.i();
            }
        }, 6000);
    }

    /* access modifiers changed from: private */
    public void i() {
        String[] aP = ConnectionHelper.aP();
        for (String a2 : aP) {
            a(a2);
        }
    }

    private void a(final String str) {
        StringRequest stringRequest = new StringRequest(0, str, new Response.Listener<String>() {
            /* renamed from: a */
            public void onResponse(String str) {
                String access$100 = MainTabActivity.f5383a;
                LogUtil.b(access$100, str + " : " + str);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String access$100 = MainTabActivity.f5383a;
                VolleyLog.d(access$100, "Error: " + volleyError.getMessage());
            }
        });
        stringRequest.setTag(f5383a);
        RequestQueueUtil.a().add(stringRequest);
    }

    public void finish() {
        super.finish();
        ApplicationContextHolder.l();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        String str = f5383a;
        LogUtil.b(str, "on Destroy,this:" + toString());
    }

    private void b(String str) {
        String str2 = f5383a;
        LogUtil.b(str2, "process applink:" + str);
        String replace = str.replace("applink:", "");
        Utils.Preference.setBooleanPref(ShopApp.g(), "pref_applink_haslink", true);
        Utils.Preference.setStringPref(ShopApp.g(), "pref_applink_url", replace);
    }

    private void b(Intent intent) {
        String action = intent.getAction();
        String dataString = intent.getDataString();
        if ("android.intent.action.VIEW".equals(action) && dataString != null) {
            String str = f5383a;
            LogUtil.b(str, "Get Google APP link =" + dataString);
            b(dataString);
        }
    }

    public void showHomeNotice(NewNoticeData newNoticeData) {
        try {
            this.d.a(newNoticeData);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void goon(SyncInfo.LaunchInfo launchInfo) {
        SplashUtil.a(this, new Runnable() {
            public void run() {
            }
        }, new SplashUtil.OnNoticeCallback() {
            public void a(NewNoticeData newNoticeData) {
            }
        });
    }

    private void j() {
        ThreadPool.a(new Runnable() {
            public void run() {
                LoginManager u = LoginManager.u();
                if (u.j() && !Utils.Preference.getBooleanPref(MainTabActivity.this, "pref_miui_account_available", false)) {
                    String access$100 = MainTabActivity.f5383a;
                    LogUtil.b(access$100, "hasSystemAccount ,get getServiceID:" + String.valueOf(Constants.Account.e().c()));
                    String c = u.c(Constants.Account.e().c());
                    String access$1002 = MainTabActivity.f5383a;
                    LogUtil.b(access$1002, "hasSystemAccount ,get authToken:" + String.valueOf(c));
                    if (!TextUtils.isEmpty(c)) {
                        Utils.Preference.setBooleanPref(MainTabActivity.this, "pref_miui_account_available", true);
                    }
                }
            }
        });
    }

    private void k() {
        new HandleCacheTask().execute(new Void[0]);
    }

    private static class HandleCacheTask extends AsyncTask<Void, Void, Void> {
        private HandleCacheTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            WebCache.c();
            WebCache.b();
            return null;
        }
    }

    private void l() {
        Request request;
        if (LoginManager.u().x() && !TextUtils.isEmpty(LoginManager.u().c())) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aJ()).buildUpon();
            buildUpon.appendQueryParameter("mUserId", UserEncryptionUtil.a(LoginManager.u().c()));
            buildUpon.appendQueryParameter("cUserId", UserEncryptionUtil.b(LoginManager.u().c()));
            buildUpon.appendQueryParameter("security", "true");
            AnonymousClass11 r1 = new SimpleCallback<NewUserInfoResult>() {
                public void a(NewUserInfoResult newUserInfoResult) {
                    if (newUserInfoResult.data != null) {
                        if (newUserInfoResult.data.jsonUserInfoData == null) {
                            NewUserInfoData unused = MainTabActivity.this.e = newUserInfoResult.data;
                        } else {
                            NewUserInfoData unused2 = MainTabActivity.this.e = newUserInfoResult.data.jsonUserInfoData;
                        }
                        MainTabActivity.this.saveAndUpdateUnpaidNum(MainTabActivity.this.e.not_pay_order_count);
                    }
                }

                public void a(String str) {
                    String access$100 = MainTabActivity.f5383a;
                    LogUtil.b(access$100, "RefreshUserInfo Exception:" + str);
                }
            };
            if (ShopApp.n()) {
                request = new SimpleProtobufRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            } else {
                request = new SimpleJsonRequest(buildUpon.toString(), NewUserInfoResult.class, r1);
            }
            request.setTag(f5383a);
            RequestQueueUtil.a().add(request);
        }
    }
}
