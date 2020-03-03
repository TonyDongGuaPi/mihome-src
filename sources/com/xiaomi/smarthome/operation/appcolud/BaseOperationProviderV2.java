package com.xiaomi.smarthome.operation.appcolud;

import android.arch.lifecycle.ViewModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.drew.lang.annotations.NotNull;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.ImageDownloadManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.smarthome.shop.utils.NetworkManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseOperationProviderV2 extends ViewModel {
    private static final String b = "/newoperation/banners/v2";
    private static final String c = "_cached_data";
    private static final String d = "_is_operation_shown_set";

    /* renamed from: a  reason: collision with root package name */
    protected String f21047a = getClass().getSimpleName();
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    private boolean f = true;
    /* access modifiers changed from: private */
    public Disposable g;
    private BroadcastReceiver h;
    private Disposable i;
    private BroadcastReceiver j;
    private Subject<Long> k;

    private static String q() {
        return SmartHomeConfig.i;
    }

    private boolean r() {
        return false;
    }

    /* access modifiers changed from: protected */
    @UiThread
    public void a(Throwable th) {
    }

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void a(@NotNull List<Banner> list);

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void c();

    /* access modifiers changed from: protected */
    @NotNull
    public abstract String d();

    /* access modifiers changed from: protected */
    @Nullable
    public String f() {
        return null;
    }

    /* access modifiers changed from: protected */
    public List<Banner> h() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean i() {
        return true;
    }

    public void j() {
    }

    /* access modifiers changed from: protected */
    public String g() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: protected */
    public final void onCleared() {
        e();
        super.onCleared();
    }

    private synchronized Subject<Long> a() {
        if (this.k == null) {
            this.k = PublishSubject.create();
            this.i = this.k.throttleLatest(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                /* renamed from: a */
                public void accept(Long l) throws Exception {
                    if (BaseOperationProviderV2.this.g != null) {
                        BaseOperationProviderV2.this.g.dispose();
                    }
                    if (BaseOperationProviderV2.this.b()) {
                        BaseOperationProviderV2.this.n();
                        BaseOperationProviderV2.this.m();
                        BaseOperationProviderV2.this.b(l.longValue(), TimeUnit.MILLISECONDS);
                        return;
                    }
                    BaseOperationProviderV2.this.e();
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    BaseOperationProviderV2.this.a((Throwable) obj);
                }
            });
        }
        return this.k;
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j2, TimeUnit timeUnit) {
        a().onNext(Long.valueOf(timeUnit.toMillis(j2)));
    }

    /* access modifiers changed from: private */
    public synchronized void m() {
        if (this.j == null) {
            this.j = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), HomeManager.u)) {
                        BaseOperationProviderV2.this.e.set(false);
                        BaseOperationProviderV2.this.j();
                        LogUtil.a(BaseOperationProviderV2.this.f21047a, "changeHome: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.j, new IntentFilter(HomeManager.u));
        }
    }

    /* access modifiers changed from: private */
    public synchronized void n() {
        if (this.h == null) {
            this.h = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), "action_on_logout")) {
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                        BaseOperationProviderV2.this.e.set(false);
                        BaseOperationProviderV2.this.c();
                        LogUtil.a(BaseOperationProviderV2.this.f21047a, "onLogout: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.h, new IntentFilter("action_on_logout"));
        }
    }

    public final void k() {
        a(0, TimeUnit.MILLISECONDS);
        LogUtil.a(this.f21047a, "refresh: ");
    }

    /* access modifiers changed from: private */
    public void b(long j2, TimeUnit timeUnit) {
        this.g = Observable.timer(j2, timeUnit).flatMap(new Function<Long, ObservableSource<List<Banner>>>() {
            /* renamed from: a */
            public ObservableSource<List<Banner>> apply(Long l) throws Exception {
                Observable<T> observable;
                List<Banner> h = BaseOperationProviderV2.this.h();
                if (h == null || h.isEmpty()) {
                    observable = Observable.just(Banner.a(BaseOperationProviderV2.this.p()));
                } else {
                    observable = Observable.just(h, Banner.a(BaseOperationProviderV2.this.p()));
                }
                return Observable.concat(observable, (Observable<T>) BaseOperationProviderV2.this.o());
            }
        }).distinctUntilChanged().map(new Function<List<Banner>, List<Banner>>() {
            /* renamed from: a */
            public List<Banner> apply(List<Banner> list) throws Exception {
                Iterator<Banner> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().b()) {
                        it.remove();
                    }
                }
                return list;
            }
        }).filter($$Lambda$BaseOperationProviderV2$0JyB5Qotk9h8vEnT6BbhFl760A8.INSTANCE).flatMap(new Function<List<Banner>, ObservableSource<List<Banner>>>() {
            /* renamed from: a */
            public ObservableSource<List<Banner>> apply(List<Banner> list) throws Exception {
                return Observable.fromIterable(list).map(BitmapFetcher.a()).buffer(list.size());
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return BaseOperationProviderV2.this.b((List<Banner>) (List) obj);
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return BaseOperationProviderV2.this.e((List<Banner>) (List) obj);
            }
        }).doOnNext(new Consumer() {
            public final void accept(Object obj) {
                BaseOperationProviderV2.this.d((List<Banner>) (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Banner>>() {
            /* renamed from: a */
            public void accept(List<Banner> list) throws Exception {
                try {
                    BaseOperationProviderV2.this.a(list);
                    LogUtil.a(BaseOperationProviderV2.this.f21047a, "onOperationReady: ");
                } catch (Exception e) {
                    BaseOperationProviderV2.this.a((Throwable) e);
                }
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                BaseOperationProviderV2.this.a((Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean f(List list) throws Exception {
        return !list.isEmpty();
    }

    static class BitmapFetcher implements Function<Banner, Banner> {
        BitmapFetcher() {
        }

        public static BitmapFetcher a() {
            return new BitmapFetcher();
        }

        /* renamed from: a */
        public Banner apply(Banner banner) throws Exception {
            if (banner != null && banner.f == null && !TextUtils.isEmpty(banner.f21046a)) {
                banner.f = ImageDownloadManager.a().b(banner.f21046a);
            }
            return banner;
        }
    }

    /* access modifiers changed from: private */
    public void d(List<Banner> list) {
        if (!list.equals(h())) {
            this.e.set(true);
        }
    }

    /* access modifiers changed from: private */
    public boolean e(List<Banner> list) {
        if (!NetworkManager.b()) {
            return true;
        }
        if (this.f) {
            this.f = false;
            return true;
        } else if (this.e.get() && i()) {
            return !list.equals(h());
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void e() {
        LogUtil.a(this.f21047a, "onRelease: ");
        if (this.i != null) {
            this.i.dispose();
            this.i = null;
            this.k = null;
        }
        if (this.g != null) {
            this.g.dispose();
            this.g = null;
        }
        if (this.h != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.h);
            this.h = null;
        }
        if (this.j != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.j);
            this.j = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return !HomeManager.A();
    }

    /* access modifiers changed from: protected */
    public boolean l() {
        return SHApplication.getStateNotifier().a() == 4;
    }

    /* access modifiers changed from: protected */
    public boolean b(List<Banner> list) {
        for (Banner a2 : list) {
            if (!a2.a()) {
                return false;
            }
        }
        if (c(list) || HomeManager.A()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Observable<List<Banner>> o() {
        String str;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("uid", CoreApi.a().s()));
        arrayList.add(new KeyValuePair("platform", "2"));
        String f2 = f();
        if (!TextUtils.isEmpty(f2)) {
            arrayList.add(new KeyValuePair("deviceNum", f2));
        }
        arrayList.add(new KeyValuePair("type", d()));
        arrayList.add(new KeyValuePair("appTabType", "1"));
        arrayList.add(new KeyValuePair("appVersion", String.valueOf(63000)));
        if (r()) {
            str = "http://st.iot.home.mi.com";
        } else {
            str = ServerRouteUtil.b(SHApplication.getAppContext());
        }
        Request.Builder a2 = new Request.Builder().a("GET");
        return a(a2.b(str + b).a((List<KeyValuePair>) arrayList).a(), new JsonParser() {
            public final Object parse(JSONObject jSONObject) {
                return BaseOperationProviderV2.this.a(jSONObject);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List a(JSONObject jSONObject) throws JSONException {
        String jSONObject2 = jSONObject.toString();
        a(jSONObject2);
        List<Banner> a2 = Banner.a(jSONObject2);
        String str = this.f21047a;
        LogUtil.a(str, "fromNet: hash: " + String.valueOf(a2.hashCode()));
        return a2;
    }

    private <T> Observable<T> a(Request request, JsonParser<T> jsonParser) {
        return Observable.create(new ObservableOnSubscribe(request, jsonParser) {
            private final /* synthetic */ Request f$1;
            private final /* synthetic */ JsonParser f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                BaseOperationProviderV2.this.a(this.f$1, this.f$2, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Request request, final JsonParser jsonParser, final ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processResponse(Response response) {
                    if (!observableEmitter.isDisposed()) {
                        try {
                            String string = response.body().string();
                            String str = BaseOperationProviderV2.this.f21047a;
                            LogUtil.a(str, "processResponse:" + string);
                            observableEmitter.onNext(jsonParser.parse(new JSONObject(string)));
                            observableEmitter.onComplete();
                        } catch (Exception e) {
                            observableEmitter.tryOnError(e);
                            e.printStackTrace();
                        }
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.tryOnError(iOException);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(List<Banner> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        String valueOf = String.valueOf(list.hashCode());
        boolean b2 = b(valueOf);
        String str = this.f21047a;
        LogUtil.a(str, "haveOperationShown: hash:" + valueOf + " ; is shown: " + b2);
        return b2;
    }

    /* access modifiers changed from: protected */
    public void a(List<Banner> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            String valueOf = String.valueOf(list.hashCode());
            Context appContext = SHApplication.getAppContext();
            appContext.getSharedPreferences(g() + d, 0).edit().putBoolean(valueOf, z).apply();
            String str = this.f21047a;
            LogUtil.a(str, "setOperationHaveShown: hash: " + valueOf + " ;shown: " + z);
        }
    }

    /* access modifiers changed from: private */
    public String p() {
        Context appContext = SHApplication.getAppContext();
        String q = q();
        return SharePrefsManager.c(appContext, q, g() + c, "");
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Context appContext = SHApplication.getAppContext();
            String q = q();
            SharePrefsManager.a(appContext, q, g() + c, str);
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        try {
            return appContext.getSharedPreferences(g() + d, 0).getBoolean(str, false);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
