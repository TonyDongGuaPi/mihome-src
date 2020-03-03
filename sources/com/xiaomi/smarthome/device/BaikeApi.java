package com.xiaomi.smarthome.device;

import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BaikeApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14750a = "BaikeApi";

    public static Observable<String> a(String str) {
        if (ServerCompact.e(SHApplication.getAppContext())) {
            return Observable.empty();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("model", str));
        Request.Builder a2 = new Request.Builder().a("GET");
        return RxApi.a(a2.b(ServerRouteUtil.b(SHApplication.getAppContext()) + "/newoperation/productBaike").a((List<KeyValuePair>) arrayList).a(), $$Lambda$BaikeApi$dvVRGx4aRZuDa3fD7Gm58bRatpU.INSTANCE).onErrorReturn($$Lambda$BaikeApi$SQHExkwkKDAEDgZoboTOVvex1cE.INSTANCE).filter($$Lambda$BaikeApi$eD5sKk19rYC_e15PrinzijyrjY.INSTANCE).observeOn(AndroidSchedulers.mainThread());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String a(JSONObject jSONObject) throws JSONException {
        try {
            return jSONObject.getJSONObject("data").getString("baikeUrl");
        } catch (Exception e) {
            Log.d(f14750a, "parse: " + e.getLocalizedMessage());
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean b(String str) throws Exception {
        return !str.isEmpty();
    }
}
