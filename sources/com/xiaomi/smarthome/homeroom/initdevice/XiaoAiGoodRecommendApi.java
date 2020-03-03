package com.xiaomi.smarthome.homeroom.initdevice;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XiaoAiGoodRecommendApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18307a = "XiaoAiGoodRecommendApi";
    private static XiaoAiGoodRecommendApi b;
    private final BehaviorSubject<List<XiaoAiGood>> c = BehaviorSubject.create();
    private Disposable d;

    private static boolean f() {
        return false;
    }

    private XiaoAiGoodRecommendApi() {
    }

    public static XiaoAiGoodRecommendApi a() {
        if (b == null) {
            synchronized (XiaoAiGoodRecommendApi.class) {
                if (b == null) {
                    b = new XiaoAiGoodRecommendApi();
                }
            }
        }
        return b;
    }

    public void b() {
        if (!c()) {
            Observable<List<XiaoAiGood>> subscribeOn = a("100328").subscribeOn(Schedulers.io());
            BehaviorSubject<List<XiaoAiGood>> behaviorSubject = this.c;
            behaviorSubject.getClass();
            this.d = subscribeOn.subscribe(new Consumer() {
                public final void accept(Object obj) {
                    BehaviorSubject.this.onNext((List) obj);
                }
            }, new Consumer<Throwable>() {
                /* renamed from: a */
                public void accept(Throwable th) throws Exception {
                    Log.d(XiaoAiGoodRecommendApi.f18307a, "accept: " + Log.getStackTraceString(th));
                }
            });
        }
    }

    public boolean c() {
        return this.c.hasValue();
    }

    public List<XiaoAiGood> d() {
        return this.c.getValue();
    }

    private Observable<List<XiaoAiGood>> a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("gIds", str));
        arrayList.add(new KeyValuePair("source", "mico_xiaoaikuailian"));
        String b2 = f() ? "http://st.iot.home.mi.com" : ServerRouteUtil.b(SHApplication.getAppContext());
        Request.Builder a2 = new Request.Builder().a("GET");
        return RxApi.a(a2.b(b2 + "/cgi-op/api/v1/youpin/goodsCard").a((List<KeyValuePair>) arrayList).a(), $$Lambda$XiaoAiGoodRecommendApi$qYS1undI4LojbD7xhFUydcHTjS8.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List a(JSONObject jSONObject) throws JSONException {
        LogUtil.a(f18307a, "getYouPinGoods: " + jSONObject);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.equals(jSONObject.getString("result"), "ok")) {
            JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("list");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                arrayList.add(new XiaoAiGood(jSONObject2.getString("name"), jSONObject2.getString(MibiConstants.ee), jSONObject2.getLong("priceMin"), jSONObject2.getString("img800s"), jSONObject2.getString("url")));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.d != null) {
            this.d.dispose();
        }
        synchronized (XiaoAiGoodRecommendApi.class) {
            b = null;
        }
    }

    static class XiaoAiGood {

        /* renamed from: a  reason: collision with root package name */
        final String f18309a;
        final String b;
        final long c;
        final String d;
        final String e;

        XiaoAiGood(String str, String str2, long j, String str3, String str4) {
            this.f18309a = str;
            this.b = str2;
            this.c = j;
            this.d = str3;
            this.e = str4;
        }
    }
}
