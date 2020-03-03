package com.xiaomi.smarthome.bbs;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.manager.InitOptions;
import com.mi.global.bbs.manager.MiCommunitySdkManager;
import com.mi.global.bbs.manager.SdkListener;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import java.util.HashMap;
import java.util.Map;

public class BBSInitializer {

    /* renamed from: a  reason: collision with root package name */
    public static String f1508a = "";
    public static final String b = "bbssdk";
    public static Map<ServerBean, String> c = new HashMap();
    private static boolean d = false;

    static {
        c.put(ServerCompact.f(), "in");
    }

    public static boolean a(Context context) {
        return !TextUtils.isEmpty(b(context));
    }

    public static boolean a(ServerBean serverBean) {
        return !TextUtils.isEmpty(c(serverBean));
    }

    private static String c(ServerBean serverBean) {
        if (c.containsKey(serverBean)) {
            return c.get(serverBean);
        }
        return null;
    }

    private static String b(Context context) {
        ServerBean a2 = ServerCompact.a(context);
        if (a2 == null) {
            return "";
        }
        return c(a2);
    }

    public static void a() {
        if (ProcessUtil.b(SHApplication.getApplication()) && a((Context) SHApplication.getApplication())) {
            b(f1508a);
        }
    }

    public static void b() {
        if (ProcessUtil.b(SHApplication.getApplication()) && a((Context) SHApplication.getApplication())) {
            try {
                MiCommunitySdkManager.getInstance().invalidateLoginData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(ServerBean serverBean) {
        try {
            if (a((Context) SHApplication.getApplication())) {
                MiCommunitySdkManager.getInstance().changeRegion(b((Context) SHApplication.getApplication()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a(Application application) {
        if (d || !a((Context) application)) {
            return false;
        }
        try {
            BBSApplication.bbsInit(application, false, new InitOptions.Builder().useCrashReporter(false).useStatistics(false).create());
            try {
                c();
                d = true;
                return d;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static void c() {
        MiCommunitySdkManager.getInstance().setListener(new SdkListener() {
            public void onNeedLogin(String str) {
                LogUtil.b(BBSInitializer.b, "sid=  " + str);
                BBSInitializer.f1508a = str;
                if (SHApplication.getStateNotifier().a() == 4) {
                    BBSInitializer.b(BBSInitializer.f1508a);
                } else {
                    LoginApi.a().a(SHApplication.getAppContext(), 5, new LoginApi.LoginCallback());
                }
            }

            public void onTokenExpired(String str) {
                BBSInitializer.f1508a = str;
                if (GlobalSetting.q) {
                    ToastUtil.a((CharSequence) "bbs onTokenExpired!");
                }
                if (SHApplication.getStateNotifier().a() == 4) {
                    BBSInitializer.b(BBSInitializer.f1508a);
                } else {
                    LoginApi.a().a(SHApplication.getAppContext(), 5, new LoginApi.LoginCallback());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
        LoginManager.a().a(str, new AsyncCallback<MiServiceTokenInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                if (GlobalSetting.q) {
                    ToastUtil.a((CharSequence) "bbs token onSuccess");
                }
                if (GlobalSetting.u) {
                    Log.d(BBSInitializer.b, "bbs token onSuccess");
                }
                LogUtil.b(BBSInitializer.b, "refreshServiceToken token:  " + miServiceTokenInfo.c);
                LogUtil.b(BBSInitializer.b, "refreshServiceToken ssecurity:  " + miServiceTokenInfo.d);
                try {
                    MiCommunitySdkManager.getInstance().setLoginData(false, CoreApi.a().y().a(), ExtendedAuthToken.build(miServiceTokenInfo.c, miServiceTokenInfo.d));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                LogUtil.b(BBSInitializer.b, "refreshServiceToken token:  fail");
                if (GlobalSetting.q) {
                    ToastUtil.a((CharSequence) "bbs  token fail:" + error);
                }
                if (GlobalSetting.u) {
                    Log.d(BBSInitializer.b, "bbs  token fail:" + error);
                }
                try {
                    MiCommunitySdkManager.getInstance().setLoginData(false, CoreApi.a().y().a(), (ExtendedAuthToken) null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
