package com.mi.global.shop.util;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.mi.global.shop.R;
import com.mi.global.shop.cache.WebCache;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.ui.HomeFragment;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.ThreadPool;
import org.json.JSONException;

public class HomeFragmentInitHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f7096a = HomeFragment.class.getSimpleName();

    public static void a(Context context, SplashUtil.OnNoticeCallback onNoticeCallback) {
        AppEventsLogger.newLogger(context).logEvent(AppEventsConstants.EVENT_NAME_ACTIVATED_APP);
        a(context);
        b(context, onNoticeCallback);
        if (b(context)) {
            MiToast.a(context, R.string.shop_settting_data_saver_toast, 1);
        }
        if (NetworkUtil.d()) {
            MiToast.a(context, R.string.shop_toast_network_unavailable, 1);
        }
        b();
        try {
            MiStatUtil.a(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void a(final Context context) {
        ThreadPool.a(new Runnable() {
            public void run() {
                LoginManager u = LoginManager.u();
                if (u.j() && !Utils.Preference.getBooleanPref(context, "pref_miui_account_available", false)) {
                    String a2 = HomeFragmentInitHelper.f7096a;
                    LogUtil.b(a2, "hasSystemAccount ,get getServiceID:" + String.valueOf(Constants.Account.e().c()));
                    String c = u.c(Constants.Account.e().c());
                    String a3 = HomeFragmentInitHelper.f7096a;
                    LogUtil.b(a3, "hasSystemAccount ,get authToken:" + String.valueOf(c));
                    if (!TextUtils.isEmpty(c)) {
                        Utils.Preference.setBooleanPref(context, "pref_miui_account_available", true);
                    }
                }
            }
        });
    }

    private static void b(Context context, SplashUtil.OnNoticeCallback onNoticeCallback) {
        SplashUtil.a(context, new Runnable() {
            public void run() {
            }
        }, onNoticeCallback);
    }

    private static boolean b(Context context) {
        int intPref;
        if (!Setting.a() || !NetworkUtil.c() || (intPref = Utils.Preference.getIntPref(context, "pref_data_saver_toast_count", 0)) >= 3) {
            return false;
        }
        Utils.Preference.setIntPref(context, "pref_data_saver_toast_count", intPref + 1);
        return true;
    }

    private static void b() {
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
}
