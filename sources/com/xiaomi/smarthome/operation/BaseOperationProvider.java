package com.xiaomi.smarthome.operation;

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

public abstract class BaseOperationProvider extends ViewModel {
    private static final String b = "/cgi-op/api/v1/recommendation/banner";
    private static final String c = "_cached_data";
    private static final String d = "_is_operation_shown_set";

    /* renamed from: a  reason: collision with root package name */
    protected String f21032a = getClass().getSimpleName();
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    private boolean f = true;
    /* access modifiers changed from: private */
    public Disposable g;
    private BroadcastReceiver h;
    private Disposable i;
    private BroadcastReceiver j;
    private Subject<Long> k;

    /* access modifiers changed from: private */
    public void p() {
    }

    private static String r() {
        return SmartHomeConfig.i;
    }

    private boolean s() {
        return false;
    }

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void a();

    /* access modifiers changed from: protected */
    @UiThread
    public void a(Throwable th) {
    }

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void a(@NotNull List<Operation> list);

    /* access modifiers changed from: protected */
    @NotNull
    public abstract String b();

    /* access modifiers changed from: protected */
    @Nullable
    public String c() {
        return null;
    }

    /* access modifiers changed from: protected */
    public List<Operation> e() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean f() {
        return true;
    }

    public void g() {
    }

    /* access modifiers changed from: protected */
    public String d() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: protected */
    public final void onCleared() {
        i();
        super.onCleared();
    }

    private synchronized Subject<Long> m() {
        if (this.k == null) {
            this.k = PublishSubject.create();
            this.i = this.k.throttleLatest(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                /* renamed from: a */
                public void accept(Long l) throws Exception {
                    if (BaseOperationProvider.this.g != null) {
                        BaseOperationProvider.this.g.dispose();
                    }
                    if (BaseOperationProvider.this.j()) {
                        BaseOperationProvider.this.o();
                        BaseOperationProvider.this.n();
                        BaseOperationProvider.this.b(l.longValue(), TimeUnit.MILLISECONDS);
                        return;
                    }
                    BaseOperationProvider.this.i();
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    BaseOperationProvider.this.a((Throwable) obj);
                }
            });
        }
        return this.k;
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j2, TimeUnit timeUnit) {
        m().onNext(Long.valueOf(timeUnit.toMillis(j2)));
    }

    /* access modifiers changed from: private */
    public synchronized void n() {
        if (this.j == null) {
            this.j = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), HomeManager.u)) {
                        BaseOperationProvider.this.e.set(false);
                        BaseOperationProvider.this.g();
                        LogUtil.a(BaseOperationProvider.this.f21032a, "changeHome: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.j, new IntentFilter(HomeManager.u));
        }
    }

    /* access modifiers changed from: private */
    public synchronized void o() {
        if (this.h == null) {
            this.h = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), "action_on_logout")) {
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                        BaseOperationProvider.this.e.set(false);
                        BaseOperationProvider.this.a();
                        LogUtil.a(BaseOperationProvider.this.f21032a, "onLogout: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.h, new IntentFilter("action_on_logout"));
        }
    }

    public final void h() {
        a(0, TimeUnit.MILLISECONDS);
        LogUtil.a(this.f21032a, "refresh: ");
    }

    /* access modifiers changed from: private */
    public void b(long j2, TimeUnit timeUnit) {
        this.g = Observable.timer(j2, timeUnit).flatMap(new Function<Long, ObservableSource<List<Operation>>>() {
            /* renamed from: a */
            public ObservableSource<List<Operation>> apply(Long l) throws Exception {
                Observable<T> observable;
                List<Operation> e = BaseOperationProvider.this.e();
                if (e == null || e.isEmpty()) {
                    observable = Observable.just(Operation.a(BaseOperationProvider.this.l()));
                } else {
                    observable = Observable.just(e, Operation.a(BaseOperationProvider.this.l()));
                }
                return Observable.concat(observable, (Observable<T>) BaseOperationProvider.this.q());
            }
        }).distinctUntilChanged().map(new Function<List<Operation>, List<Operation>>() {
            /* renamed from: a */
            public List<Operation> apply(List<Operation> list) throws Exception {
                Iterator<Operation> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().b()) {
                        it.remove();
                    }
                }
                return list;
            }
        }).filter($$Lambda$BaseOperationProvider$TnDgz756HuiKHWF2I3q4Yd8y3o.INSTANCE).flatMap(new Function<List<Operation>, ObservableSource<List<Operation>>>() {
            /* renamed from: a */
            public ObservableSource<List<Operation>> apply(List<Operation> list) throws Exception {
                return Observable.fromIterable(list).map(BitmapFetcher.a()).buffer(list.size());
            }
        }).map(new Function<List<Operation>, List<Operation>>() {
            /* renamed from: a */
            public List<Operation> apply(List<Operation> list) throws Exception {
                List<Operation> e = BaseOperationProvider.this.e();
                if (e == null || e.size() <= 0 || e.equals(list) || BaseOperationProvider.this.b(list)) {
                    return list;
                }
                BaseOperationProvider.this.e.set(false);
                return e;
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return BaseOperationProvider.this.b((List<Operation>) (List) obj);
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return BaseOperationProvider.this.e((List<Operation>) (List) obj);
            }
        }).doOnNext(new Consumer() {
            public final void accept(Object obj) {
                BaseOperationProvider.this.d((List<Operation>) (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Operation>>() {
            /* renamed from: a */
            public void accept(List<Operation> list) throws Exception {
                try {
                    BaseOperationProvider.this.p();
                    BaseOperationProvider.this.a(list);
                    LogUtil.a(BaseOperationProvider.this.f21032a, "onOperationReady: ");
                } catch (Exception e) {
                    BaseOperationProvider.this.a((Throwable) e);
                }
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                BaseOperationProvider.this.a((Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean f(List list) throws Exception {
        return !list.isEmpty();
    }

    static class BitmapFetcher implements Function<Operation, Operation> {
        BitmapFetcher() {
        }

        public static BitmapFetcher a() {
            return new BitmapFetcher();
        }

        /* renamed from: a */
        public Operation apply(Operation operation) throws Exception {
            if (operation != null && operation.i == null && !TextUtils.isEmpty(operation.b)) {
                operation.i = ImageDownloadManager.a().b(operation.b);
                operation.o = ImageDownloadManager.a().c(operation.b);
            }
            return operation;
        }
    }

    /* access modifiers changed from: private */
    public void d(List<Operation> list) {
        if (!list.equals(e())) {
            this.e.set(true);
        }
    }

    /* access modifiers changed from: private */
    public boolean e(List<Operation> list) {
        if (!NetworkManager.b()) {
            return true;
        }
        if (this.f) {
            this.f = false;
            return true;
        } else if (this.e.get() && f()) {
            return !list.equals(e());
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void i() {
        LogUtil.a(this.f21032a, "onRelease: ");
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
    public boolean j() {
        return !HomeManager.A();
    }

    /* access modifiers changed from: protected */
    public boolean k() {
        return SHApplication.getStateNotifier().a() == 4;
    }

    /* access modifiers changed from: protected */
    public boolean b(List<Operation> list) {
        for (Operation a2 : list) {
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
    public Observable<List<Operation>> q() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("uid", CoreApi.a().s()));
        arrayList.add(new KeyValuePair("platform", "2"));
        String c2 = c();
        if (!TextUtils.isEmpty(c2)) {
            arrayList.add(new KeyValuePair("deviceNum", c2));
        }
        arrayList.add(new KeyValuePair("type", b()));
        arrayList.add(new KeyValuePair("appTabType", "1"));
        arrayList.add(new KeyValuePair("appVersion", String.valueOf(63000)));
        String b2 = s() ? "http://st.iot.home.mi.com" : ServerRouteUtil.b(SHApplication.getAppContext());
        Request.Builder a2 = new Request.Builder().a("GET");
        return a(a2.b(b2 + b).a((List<KeyValuePair>) arrayList).a(), new JsonParser() {
            public final Object parse(JSONObject jSONObject) {
                return BaseOperationProvider.this.a(jSONObject);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List a(JSONObject jSONObject) throws JSONException {
        String jSONObject2 = jSONObject.toString();
        a(jSONObject2);
        List<Operation> a2 = Operation.a(jSONObject2);
        for (Operation operation : a2) {
            operation.p = false;
        }
        String str = this.f21032a;
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
                BaseOperationProvider.this.a(this.f$1, this.f$2, observableEmitter);
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
                            String str = BaseOperationProvider.this.f21032a;
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
                    try {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.tryOnError(iOException);
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(List<Operation> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        String valueOf = String.valueOf(list.hashCode());
        boolean b2 = b(valueOf);
        String str = this.f21032a;
        LogUtil.a(str, "haveOperationShown: hash:" + valueOf + " ; is shown: " + b2);
        return b2;
    }

    /* access modifiers changed from: protected */
    public void a(List<Operation> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            String valueOf = String.valueOf(list.hashCode());
            Context appContext = SHApplication.getAppContext();
            appContext.getSharedPreferences(d() + d, 0).edit().putBoolean(valueOf, z).apply();
            String str = this.f21032a;
            LogUtil.a(str, "setOperationHaveShown: hash: " + valueOf + " ;shown: " + z);
        }
    }

    public String l() {
        Context appContext = SHApplication.getAppContext();
        String r = r();
        return SharePrefsManager.c(appContext, r, d() + c, "");
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Context appContext = SHApplication.getAppContext();
            String r = r();
            SharePrefsManager.a(appContext, r, d() + c, str);
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        try {
            return appContext.getSharedPreferences(d() + d, 0).getBoolean(str, false);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
