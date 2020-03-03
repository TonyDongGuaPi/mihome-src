package com.xiaomi.smarthome.frame.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.login.api.manager.LoginManager;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

public class MijiaLoginManager extends LoginManager {
    private volatile boolean c = false;

    public MijiaLoginManager(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void a() {
        FrameManager.b().g().a();
    }

    /* access modifiers changed from: protected */
    public void a(String str, LoginMiAccount loginMiAccount, @Nullable Map<String, String> map, BaseLoginCallback baseLoginCallback) {
        this.c = false;
        final String str2 = str;
        final LoginMiAccount loginMiAccount2 = loginMiAccount;
        final Map<String, String> map2 = map;
        final BaseLoginCallback baseLoginCallback2 = baseLoginCallback;
        CoreApi.a().a(loginMiAccount, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LoginHostApi g = FrameManager.b().g();
                g.a("login success loginType " + str2);
                MijiaLoginManager.super.a(str2, loginMiAccount2, map2, baseLoginCallback2);
            }

            public void onFailure(Error error) {
                MijiaLoginManager.super.a(str2, error.a(), error.b(), (Map<String, String>) map2, baseLoginCallback2);
                MijiaLoginManager.this.a(str2, loginMiAccount2, (Map<String, String>) map2, error, "Core-setMiAccount");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(String str, int i, String str2, @Nullable Map<String, String> map, BaseLoginCallback baseLoginCallback) {
        super.a(str, i, str2, map, baseLoginCallback);
        a(str, (LoginMiAccount) null, map, new Error(i, str2), "onManagerLoginFail");
    }

    /* access modifiers changed from: private */
    public void a(String str, LoginMiAccount loginMiAccount, Map<String, String> map, Error error, String str2) {
        String str3;
        try {
            StringBuilder sb = new StringBuilder();
            JSONObject jSONObject = new JSONObject();
            sb.append("-------------------login fail start----------------\r\n");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            sb.append("GMT time=" + simpleDateFormat.format(new Date()) + "\r\n");
            jSONObject.put("time", System.currentTimeMillis());
            if (error != null) {
                sb.append("errorcode=" + error.a() + "\r\n");
                sb.append("errordetail=" + error.b() + "\r\n");
                jSONObject.put("err_code", error.a());
                jSONObject.put("err_msg", error.b());
            }
            sb.append("extra=" + str2 + "\r\n");
            sb.append("loginType=" + str + "\r\n");
            jSONObject.put("extra", str2);
            jSONObject.put("loginType", str);
            if (loginMiAccount != null) {
                sb.append("userid=" + loginMiAccount.a() + "\r\n");
                sb.append("isSystemAccount=" + loginMiAccount.b() + "\r\n");
                jSONObject.put("uid", loginMiAccount.a());
                jSONObject.put("isSystemAccount", loginMiAccount.b());
            }
            if (map != null) {
                TextUtils.isEmpty(map.get("password"));
                for (Map.Entry next : map.entrySet()) {
                    sb.append(((String) next.getKey()) + "=" + ((String) next.getValue()) + "\r\n");
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            }
            ServerBean a2 = ServerCompact.a(CommonApplication.getAppContext());
            if (a2 == null) {
                str3 = null;
            } else {
                str3 = a2.f1530a + ":" + a2.b + ":" + a2.c;
            }
            sb.append("server=" + str3);
            jSONObject.put("server", str3);
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) SHApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                sb.append("network=" + activeNetworkInfo.getType() + "\r\n");
                jSONObject.put(LogCategory.CATEGORY_NETWORK, activeNetworkInfo.getType());
            } else {
                sb.append("activeNetwork is null\r\n");
                jSONObject.put(LogCategory.CATEGORY_NETWORK, "unknown");
            }
            Locale a3 = LocaleUtil.a();
            if (a3 != null) {
                jSONObject.put("locale", a3.toString());
            }
            sb.append("-------------------login fail end----------------\r\n");
            String sb2 = sb.toString();
            LogUtil.a("login", sb2);
            if (SHApplication.shouldEnableBugly()) {
                BuglyLog.i("login", sb2);
                if (HomeManager.A()) {
                    CrashReport.postCatchedException(new InternationalLoginException(sb2));
                } else {
                    CrashReport.postCatchedException(new LoginException(sb2));
                }
            }
            MyLog.d(sb2);
            LogUtilGrey.a("login", "login fail:" + sb2);
            if (this.c) {
                STAT.h.a(jSONObject);
            }
            this.c = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LoginException extends Exception {
        public LoginException(String str) {
            super(str);
        }
    }

    public static class InternationalLoginException extends Exception {
        public InternationalLoginException(String str) {
            super(str);
        }
    }
}
