package com.xiaomi.smarthome.international;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.drew.lang.annotations.NotNull;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class ServerCodeUploader {

    /* renamed from: a  reason: collision with root package name */
    public static String f18412a = "ServerCodeUploader";
    private static final String b = "spfs_server_code_uploader";
    private static final String c = "key_server_code";
    private static final String d = "key_language_code";

    /* access modifiers changed from: private */
    public void f() {
    }

    @SuppressLint({"CheckResult"})
    public void a(boolean z) {
        Observable.defer(new Callable(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ServerCodeUploader.this.c(this.f$1);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                ServerCodeUploader.this.b((ServerBean) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                ServerCodeUploader.this.a((Throwable) obj);
            }
        }, new Action() {
            public final void run() {
                ServerCodeUploader.this.f();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Throwable th) throws Exception {
        b();
        f();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public Observable<ServerBean> c(final boolean z) {
        return Observable.timer(200, TimeUnit.MILLISECONDS).flatMap(new Function<Long, ObservableSource<ServerBean>>() {
            /* renamed from: a */
            public ObservableSource<ServerBean> apply(Long l) throws Exception {
                ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
                if (a2 == null) {
                    return Observable.error((Throwable) new IllegalArgumentException());
                }
                if (z || ServerCodeUploader.this.a()) {
                    return ServerCodeUploader.this.a(a2);
                }
                return Observable.empty();
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<ServerBean> a(@NotNull ServerBean serverBean) {
        return Observable.create(new ObservableOnSubscribe(serverBean) {
            private final /* synthetic */ ServerBean f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                ServerCodeUploader.this.a(this.f$1, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@NotNull final ServerBean serverBean, final ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            final Locale c2 = ServerCompact.c(SHApplication.getAppContext());
            UserApi.a().a(SHApplication.getAppContext(), c2, serverBean, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(serverBean);
                        observableEmitter.onComplete();
                    }
                }

                public void onFailure(Error error) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(new ApiErrorWrapper(error));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean a() {
        ServerBean a2;
        if (SHApplication.getStateNotifier().a() != 4 || (a2 = ServerCompact.a(SHApplication.getAppContext())) == null) {
            return false;
        }
        String c2 = c();
        if (TextUtils.isEmpty(c2)) {
            return true;
        }
        try {
            if (!a2.equals(ServerBean.b(new JSONObject(c2)))) {
                return true;
            }
            Locale c3 = ServerCompact.c(SHApplication.getAppContext());
            String d2 = d();
            if (!TextUtils.isEmpty(d2) && LocaleUtil.b(c3).equalsIgnoreCase(d2)) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void b(ServerBean serverBean) {
        if (serverBean != null) {
            SharePrefsManager.a(SHApplication.getAppContext(), e(), c, serverBean.a());
            SharePrefsManager.a(SHApplication.getAppContext(), e(), d, LocaleUtil.b(ServerCompact.c(SHApplication.getAppContext())).toLowerCase());
        }
    }

    private void b() {
        SharePrefsManager.a(SHApplication.getAppContext(), e(), c, "");
        SharePrefsManager.a(SHApplication.getAppContext(), e(), d, "");
    }

    private String c() {
        return SharePrefsManager.c(SHApplication.getAppContext(), e(), c, "");
    }

    private String d() {
        return SharePrefsManager.c(SHApplication.getAppContext(), e(), d, "");
    }

    private static String e() {
        return MD5.d(CoreApi.a().s()) + JSMethod.NOT_SET + b;
    }
}
