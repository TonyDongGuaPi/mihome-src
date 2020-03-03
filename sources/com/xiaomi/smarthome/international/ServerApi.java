package com.xiaomi.smarthome.international;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

public final class ServerApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18410a = "ServerApi";
    private static final String b = "/cgi-op/api/v1/countries/get";
    private static ServerApi c;

    private ServerApi() {
    }

    public static ServerApi a() {
        if (c == null) {
            synchronized (ServerApi.class) {
                if (c == null) {
                    c = new ServerApi();
                }
            }
        }
        return c;
    }

    public Observable<List<ServerBean>> b() {
        return Observable.defer(new Callable() {
            public final Object call() {
                return ServerApi.this.e();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource e() throws Exception {
        Context appContext = SHApplication.getAppContext();
        List<ServerBean> a2 = ServerCompact.a(ServerCompact.c(appContext), appContext);
        if (!a2.isEmpty()) {
            return Observable.just(a2);
        }
        return Observable.just(d());
    }

    @Deprecated
    private Observable<List<ServerBean>> c() {
        if (!StartupCheckList.b() || !CoreApi.a().l()) {
            return Observable.just(d());
        }
        final Locale c2 = ServerCompact.c(SHApplication.getAppContext());
        List singletonList = Collections.singletonList(new KeyValuePair("languageCode", LocaleUtil.b(c2)));
        Request.Builder a2 = new Request.Builder().a("GET");
        return RxApi.a(a2.b(ServerRouteUtil.b(SHApplication.getAppContext()) + b).a((List<KeyValuePair>) singletonList).a(), $$Lambda$dEXiBBofASX1a_QB6D3_jsgBUQM.INSTANCE).map(new Function<String, List<ServerBean>>() {
            /* renamed from: a */
            public List<ServerBean> apply(String str) throws Exception {
                new ArrayList();
                try {
                    List<ServerBean> a2 = ServerBean.a(new JSONObject(str));
                    if (a2.isEmpty()) {
                        return ServerApi.this.d();
                    }
                    ServerCompact.a(SHApplication.getAppContext(), c2, str);
                    LogUtil.a("wangwei", "online servers: " + str);
                    return a2;
                } catch (JSONException unused) {
                    return ServerApi.this.d();
                }
            }
        }).onErrorReturn(new Function() {
            public final Object apply(Object obj) {
                return ServerApi.this.a((Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List a(Throwable th) throws Exception {
        return d();
    }

    /* access modifiers changed from: private */
    public List<ServerBean> d() {
        LogUtil.b(f18410a, "获取服务器列表失败， 使用兜底方案");
        return ServerCompact.a(Locale.ENGLISH, SHApplication.getAppContext());
    }

    public void a(boolean z) {
        new ServerCodeUploader().a(z);
    }

    public Observable<String> a(ServerBean serverBean) {
        return Observable.defer(new Callable(serverBean) {
            private final /* synthetic */ ServerBean f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ServerApi.this.b(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource b(ServerBean serverBean) throws Exception {
        if (serverBean == null) {
            return Observable.error((Throwable) new IllegalArgumentException());
        }
        String a2 = ServerCompact.a(SHApplication.getAppContext(), serverBean.b);
        if (!TextUtils.isEmpty(a2)) {
            return Observable.just(a2);
        }
        return Observable.just(d()).zipWith(Observable.just(serverBean), $$Lambda$ServerApi$LYQGi3wN0WLEPxSrHCqpzz1YgLg.INSTANCE).map($$Lambda$ServerApi$YWSR4W_WCgn9RnYL32IVNDhXtw.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ServerBean a(List list, ServerBean serverBean) throws Exception {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ServerBean serverBean2 = (ServerBean) it.next();
                if (TextUtils.equals(serverBean2.b, serverBean.b)) {
                    return serverBean2;
                }
            }
        }
        serverBean.c = "";
        return serverBean;
    }
}
