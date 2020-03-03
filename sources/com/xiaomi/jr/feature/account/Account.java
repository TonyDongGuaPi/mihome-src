package com.xiaomi.jr.feature.account;

import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.Constants;
import com.xiaomi.jr.account.MiFiAccountUtils;
import com.xiaomi.jr.account.SimpleAccountLoginCallback;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.account.XiaomiService;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Feature("Account")
public class Account extends HybridFeature {
    private static List<Request<LoginServiceParam>> sLoginServiceRequests = new CopyOnWriteArrayList();
    private static Set<String> sServiceUrlSet = new HashSet();
    private SimpleAccountLoginCallback mAccountLoginCallback;
    private XiaomiAccountManager.GetAccountInfoObserver mGetAccountInfoObserver = $$Lambda$Account$7IGTHFCSesTc1lrGMVkXcGoAO74.INSTANCE;

    private static class LoginResult {
        @SerializedName("success")

        /* renamed from: a  reason: collision with root package name */
        public boolean f10383a;
        @SerializedName("cUserId")
        public String b;

        private LoginResult() {
        }
    }

    private static class LoginServiceParam {
        @SerializedName("sid")

        /* renamed from: a  reason: collision with root package name */
        String f10384a;
        @SerializedName("cookieUrl")
        String b;
        @SerializedName("webloginUrl")
        String c;

        private LoginServiceParam() {
        }
    }

    private static class LoginServiceResult {
        @SerializedName("success")

        /* renamed from: a  reason: collision with root package name */
        public boolean f10385a;
        @SerializedName("sid")
        public String b;
        @SerializedName("cookieUrl")
        public String c;

        private LoginServiceResult() {
        }
    }

    private static class GetUserInfoResult {
        @SerializedName("userId")

        /* renamed from: a  reason: collision with root package name */
        public String f10382a;
        @SerializedName("userName")
        public String b;
        @SerializedName("nickName")
        public String c;
        @SerializedName("avatarUrl")
        public String d;
        @SerializedName("safePhone")
        public String e;

        private GetUserInfoResult() {
        }
    }

    static /* synthetic */ void lambda$new$0(boolean z, XiaomiService xiaomiService) {
        onLoginServiceResult(z, xiaomiService.b, xiaomiService.f10282a);
        if (!z) {
            QualityMonitor.a(Constants.j, "login_service_failure", "systemAccount", String.valueOf(XiaomiAccountManager.a().c()), "service", xiaomiService.toString());
        }
    }

    @Action
    public Response hasLogin(Request request) {
        return new Response(Boolean.valueOf(XiaomiAccountManager.a().d()));
    }

    @Action
    public Response hasLoginSystemAccount(Request request) {
        return new Response(Boolean.valueOf(XiaomiAccountManager.a().a(HybridUtils.a(request))));
    }

    @Action
    public Response login(final Request request) {
        if (XiaomiAccountManager.a().d()) {
            QualityMonitor.a(Constants.i, "login_abort", "reason", "repeat login");
            return new Response.RuntimeErrorResponse(request, "Don't repeat login");
        }
        this.mAccountLoginCallback = new SimpleAccountLoginCallback() {
            public void a(boolean z) {
                super.a(z);
                LoginResult loginResult = new LoginResult();
                loginResult.f10383a = z;
                loginResult.b = XiaomiAccountManager.g();
                HybridUtils.a(request, new Response(loginResult));
                HybridUtils.a(request, 13, (Object) null);
            }
        };
        XiaomiAccountManager.a().a(HybridUtils.b(request), (AccountNotifier.AccountLoginCallback) this.mAccountLoginCallback);
        return Response.j;
    }

    @Action
    public Response logout(Request request) {
        if (!XiaomiAccountManager.a().d()) {
            return new Response.RuntimeErrorResponse(request, "Not login yet. Can not logout.");
        }
        XiaomiAccountManager.a().logout(HybridUtils.a(request), new AccountNotifier.AccountLogoutCallback() {
            public final void onLogout() {
                Account.lambda$logout$1(Request.this);
            }
        });
        return Response.j;
    }

    static /* synthetic */ void lambda$logout$1(Request request) {
        HybridUtils.a(request, Response.j);
        HybridUtils.a(request, 26, (Object) null);
    }

    @Action(paramClazz = LoginServiceParam.class)
    public Response loginService(Request<LoginServiceParam> request) {
        CookieManager instance = CookieManager.getInstance();
        MifiLog.c("TestCookie", "loginService getCookie: " + instance.getCookie(request.c().b) + ", url=" + request.c().b);
        sLoginServiceRequests.add(request);
        if (!XiaomiAccountManager.a().d()) {
            QualityMonitor.a(Constants.j, "login_service_abort", "reason", "app has not login", "url", request.a().f());
            onLoginServiceResult(false, request.c().f10384a, request.c().b);
            return new Response.RuntimeErrorResponse((Request) request, "App not login, should call login() in advance.");
        }
        synchronized (Account.class) {
            if (sServiceUrlSet.contains(request.c().b)) {
                QualityMonitor.a(Constants.j, "login_service_abort", "reason=repeat doing service login, url=" + request.a().f());
                Response.RuntimeErrorResponse runtimeErrorResponse = new Response.RuntimeErrorResponse((Request) request, "Doing setup serviceToken. just return here. No callback");
                return runtimeErrorResponse;
            }
            sServiceUrlSet.add(request.c().b);
            XiaomiAccountManager.a().a(HybridUtils.a((Request) request), new XiaomiService(request.c().b, request.c().f10384a, request.c().c), request.a().f(), this.mGetAccountInfoObserver);
            return Response.j;
        }
    }

    @Action
    public Response getUserInfo(Request request) {
        if (!XiaomiAccountManager.a().d()) {
            return new Response.RuntimeErrorResponse(request, "Not login yet. Can not get account info.");
        }
        HybridUtils.a((Runnable) new Runnable() {
            public final void run() {
                Account.lambda$getUserInfo$2(Request.this);
            }
        });
        return Response.j;
    }

    static /* synthetic */ void lambda$getUserInfo$2(Request request) {
        GetUserInfoResult getUserInfoResult = new GetUserInfoResult();
        XiaomiUserCoreInfo a2 = MiFiAccountUtils.a(HybridUtils.a(request));
        if (a2 != null) {
            getUserInfoResult.f10382a = a2.userId;
            getUserInfoResult.b = a2.userName;
            getUserInfoResult.c = a2.nickName;
            getUserInfoResult.d = a2.avatarAddress;
            getUserInfoResult.e = a2.safePhone;
        }
        HybridUtils.a(request, new Response(getUserInfoResult));
    }

    public void cleanup() {
        for (Request next : sLoginServiceRequests) {
            if (next.a() == this.mHybridContext) {
                sLoginServiceRequests.remove(next);
            }
        }
    }

    private static void onLoginServiceResult(boolean z, String str, String str2) {
        for (Request next : sLoginServiceRequests) {
            if (TextUtils.equals(((LoginServiceParam) next.c()).f10384a, str)) {
                LoginServiceResult loginServiceResult = new LoginServiceResult();
                loginServiceResult.f10385a = z;
                loginServiceResult.b = str;
                loginServiceResult.c = str2;
                next.a().a(new Response(loginServiceResult), next.e());
                sLoginServiceRequests.remove(next);
            }
        }
        synchronized (Account.class) {
            sServiceUrlSet.remove(str2);
        }
    }
}
