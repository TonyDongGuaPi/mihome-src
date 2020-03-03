package com.xiaomi.smarthome.operation.my;

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
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.ImageDownloadManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
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
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MyOperationProvider extends ViewModel {
    private static final String b = "/cgi-op/api/v1/recommendation/myTab";
    private static final String c = "_cached_data";
    private static final String d = "_is_operation_shown_set";

    /* renamed from: a  reason: collision with root package name */
    protected String f21139a = getClass().getSimpleName();
    /* access modifiers changed from: private */
    public Disposable e;
    private BroadcastReceiver f;
    private Disposable g;
    private BroadcastReceiver h;
    private Subject<Long> i;

    /* access modifiers changed from: private */
    public void m() {
    }

    private static String p() {
        return SmartHomeConfig.i;
    }

    private boolean q() {
        return false;
    }

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void a(@NotNull List<Item> list);

    /* access modifiers changed from: protected */
    @Nullable
    public String c() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void d();

    /* access modifiers changed from: protected */
    @UiThread
    public abstract void e();

    public void h() {
    }

    /* access modifiers changed from: protected */
    @UiThread
    public void a(Throwable th) {
        th.printStackTrace();
    }

    /* access modifiers changed from: protected */
    public String g() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: protected */
    public final void onCleared() {
        f();
        super.onCleared();
    }

    private synchronized Subject<Long> a() {
        if (this.i == null) {
            this.i = PublishSubject.create();
            this.g = this.i.throttleLatest(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                /* renamed from: a */
                public void accept(Long l) throws Exception {
                    if (MyOperationProvider.this.e != null) {
                        MyOperationProvider.this.e.dispose();
                    }
                    if (MyOperationProvider.this.b()) {
                        MyOperationProvider.this.l();
                        MyOperationProvider.this.k();
                        MyOperationProvider.this.b(l.longValue(), TimeUnit.MILLISECONDS);
                        return;
                    }
                    MyOperationProvider.this.f();
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    MyOperationProvider.this.a((Throwable) obj);
                }
            });
        }
        return this.i;
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(long j, TimeUnit timeUnit) {
        a().onNext(Long.valueOf(timeUnit.toMillis(j)));
    }

    /* access modifiers changed from: private */
    public synchronized void k() {
        if (this.h == null) {
            this.h = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), HomeManager.u)) {
                        MyOperationProvider.this.h();
                        LogUtil.a(MyOperationProvider.this.f21139a, "changeHome: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.h, new IntentFilter(HomeManager.u));
        }
    }

    /* access modifiers changed from: private */
    public synchronized void l() {
        if (this.f == null) {
            this.f = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), "action_on_logout")) {
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                        MyOperationProvider.this.e();
                        LogUtil.a(MyOperationProvider.this.f21139a, "onLogout: ");
                    }
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.f, new IntentFilter("action_on_logout"));
        }
    }

    public final void i() {
        a(0, TimeUnit.MILLISECONDS);
        LogUtil.a(this.f21139a, "refresh: ");
    }

    /* access modifiers changed from: private */
    public void b(long j, TimeUnit timeUnit) {
        this.e = Observable.timer(j, timeUnit).flatMap(new Function<Long, ObservableSource<List<Item>>>() {
            /* renamed from: a */
            public ObservableSource<List<Item>> apply(Long l) throws Exception {
                return Observable.concat(Observable.just(Item.a(MyOperationProvider.this.o(), true)), (Observable<T>) MyOperationProvider.this.n());
            }
        }).distinctUntilChanged().map(new Function<List<Item>, List<Item>>() {
            /* renamed from: a */
            public List<Item> apply(List<Item> list) throws Exception {
                Iterator<Item> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().b()) {
                        it.remove();
                    }
                }
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).filter(new Predicate() {
            public final boolean test(Object obj) {
                return MyOperationProvider.this.d((List) obj);
            }
        }).observeOn(Schedulers.io()).flatMap(new Function<List<Item>, ObservableSource<List<Item>>>() {
            /* renamed from: a */
            public ObservableSource<List<Item>> apply(List<Item> list) throws Exception {
                return Observable.fromIterable(list).map(BitmapFetcher.a()).buffer(list.size());
            }
        }).filter(new Predicate() {
            public final boolean test(Object obj) {
                return MyOperationProvider.this.b((List<Item>) (List) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Item>>() {
            /* renamed from: a */
            public void accept(List<Item> list) throws Exception {
                try {
                    MyOperationProvider.this.m();
                    MyOperationProvider.this.a(list);
                    LogUtil.a(MyOperationProvider.this.f21139a, "onOperationReady: ");
                } catch (Exception e) {
                    MyOperationProvider.this.a((Throwable) e);
                }
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                MyOperationProvider.this.a((Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean d(List list) throws Exception {
        if (list.isEmpty()) {
            d();
        }
        return !list.isEmpty();
    }

    static class BitmapFetcher implements Function<Item, Item> {
        BitmapFetcher() {
        }

        public static BitmapFetcher a() {
            return new BitmapFetcher();
        }

        /* renamed from: a */
        public Item apply(Item item) throws Exception {
            if (item != null && item.i == null && !TextUtils.isEmpty(item.j)) {
                item.i = ImageDownloadManager.a().b(item.j);
            }
            return item;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void f() {
        LogUtil.a(this.f21139a, "onRelease: ");
        if (this.g != null) {
            this.g.dispose();
            this.g = null;
            this.i = null;
        }
        if (this.e != null) {
            this.e.dispose();
            this.e = null;
        }
        if (this.f != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.f);
            this.f = null;
        }
        if (this.h != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.h);
            this.h = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return !HomeManager.A();
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return SHApplication.getStateNotifier().a() == 4;
    }

    /* access modifiers changed from: protected */
    public boolean b(List<Item> list) {
        for (Item a2 : list) {
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
    public Observable<List<Item>> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("uid", CoreApi.a().s()));
        arrayList.add(new KeyValuePair("platform", "2"));
        arrayList.add(new KeyValuePair("deviceNum", c()));
        arrayList.add(new KeyValuePair("appTabType", "1"));
        arrayList.add(new KeyValuePair("appVersion", String.valueOf(63000)));
        arrayList.add(new KeyValuePair("locale", LocaleUtil.b(ServerCompact.c(SHApplication.getAppContext()))));
        StringBuilder sb = new StringBuilder();
        sb.append(q() ? "http://st.iot.home.mi.com" : ServerRouteUtil.b(SHApplication.getAppContext()));
        sb.append(b);
        return a(new Request.Builder().a("GET").b(sb.toString()).a((List<KeyValuePair>) arrayList).a(), new JsonParser() {
            public final Object parse(JSONObject jSONObject) {
                return MyOperationProvider.this.a(jSONObject);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List a(JSONObject jSONObject) throws JSONException {
        String jSONObject2 = jSONObject.toString();
        a(jSONObject2);
        List<Item> a2 = Item.a(jSONObject2, false);
        String str = this.f21139a;
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
                MyOperationProvider.this.a(this.f$1, this.f$2, observableEmitter);
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
                            String str = MyOperationProvider.this.f21139a;
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
    public boolean c(List<Item> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        String valueOf = String.valueOf(list.hashCode());
        boolean b2 = b(valueOf);
        String str = this.f21139a;
        LogUtil.a(str, "haveOperationShown: hash:" + valueOf + " ; is shown: " + b2);
        return b2;
    }

    /* access modifiers changed from: protected */
    public void a(List<Item> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            String valueOf = String.valueOf(list.hashCode());
            Context appContext = SHApplication.getAppContext();
            appContext.getSharedPreferences(g() + d, 0).edit().putBoolean(valueOf, z).apply();
            String str = this.f21139a;
            LogUtil.a(str, "setOperationHaveShown: hash: " + valueOf + " ;shown: " + z);
        }
    }

    /* access modifiers changed from: private */
    public String o() {
        Context appContext = SHApplication.getAppContext();
        String p = p();
        return SharePrefsManager.c(appContext, p, g() + c, "");
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Context appContext = SHApplication.getAppContext();
            String p = p();
            SharePrefsManager.a(appContext, p, g() + c, str);
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
