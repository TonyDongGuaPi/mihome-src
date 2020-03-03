package com.xiaomi.smarthome.device.choosedevice;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceSearchApi;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.international.ApiErrorWrapper;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChooseDeviceSearchApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15334a = "ChooseDeviceSearchApi";
    private static final String b = "/v2/product/serach_product";
    private final Subject<String> c = PublishSubject.create();
    private final Subject<QueryResult> d = BehaviorSubject.create();

    @SuppressLint({"CheckResult"})
    ChooseDeviceSearchApi() {
        Observable<R> observeOn = this.c.debounce(200, TimeUnit.MILLISECONDS).filter($$Lambda$ChooseDeviceSearchApi$IlAcAZPYGGR4iinoGx8CFJ1nBdk.INSTANCE).switchMap(new Function<String, ObservableSource<QueryResult>>() {
            /* renamed from: a */
            public ObservableSource<QueryResult> apply(String str) throws Exception {
                return ChooseDeviceSearchApi.this.b(str);
            }
        }).observeOn(AndroidSchedulers.mainThread());
        Subject<QueryResult> subject = this.d;
        subject.getClass();
        observeOn.subscribe((Consumer<? super R>) new Consumer() {
            public final void accept(Object obj) {
                Subject.this.onNext((ChooseDeviceSearchApi.QueryResult) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean c(String str) throws Exception {
        return !str.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.c.onNext(str);
    }

    /* access modifiers changed from: package-private */
    public Observable<QueryResult> a() {
        return this.d.hide();
    }

    static class QueryResult {

        /* renamed from: a  reason: collision with root package name */
        final String f15337a;
        final List<Integer> b;
        final boolean c;
        final String d;

        QueryResult(String str, List<Integer> list, boolean z, String str2) {
            this.f15337a = str;
            this.b = list;
            this.c = z;
            this.d = str2;
        }
    }

    public Observable<QueryResult> b(final String str) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("input", str);
        } catch (JSONException unused) {
        }
        return RxApi.a(new NetRequest.Builder().a("GET").b(b).b((List<KeyValuePair>) Collections.singletonList(new KeyValuePair("data", jSONObject.toString()))).a(), new JsonParser<QueryResult>() {
            /* renamed from: a */
            public QueryResult parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(ChooseDeviceSearchApi.f15334a, "queryFromServer param: " + jSONObject + " ; response: " + jSONObject);
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("related");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
                }
                return new QueryResult(str, arrayList, true, (String) null);
            }
        }).onErrorReturn(new Function(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return ChooseDeviceSearchApi.a(this.f$0, (Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ QueryResult a(String str, Throwable th) throws Exception {
        String str2;
        if (th instanceof ApiErrorWrapper) {
            ApiErrorWrapper apiErrorWrapper = (ApiErrorWrapper) th;
            str2 = apiErrorWrapper.mExtra;
            if (TextUtils.isEmpty(str2)) {
                str2 = apiErrorWrapper.detail;
            }
        } else {
            str2 = th.getMessage();
        }
        LogUtil.a(f15334a, "queryFromServer error: " + str2);
        return new QueryResult(str, (List<Integer>) null, false, SHApplication.getAppContext().getString(R.string.device_choose_search_net_error_result));
    }

    public void b() {
        this.c.onComplete();
        this.d.onComplete();
    }
}
