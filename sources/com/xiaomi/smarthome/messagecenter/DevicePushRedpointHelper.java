package com.xiaomi.smarthome.messagecenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.family.api.ShopApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.messagecenter.shopmessage.MessageCenterCountHelper;
import com.xiaomi.smarthome.miio.db.record.MessageRecordShop;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DevicePushRedpointHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Long> f10438a = new ConcurrentHashMap();

    public static boolean a(String str, long j) {
        Map<String, Long> a2 = a();
        if (a2.containsKey(str) && j <= a2.get(str).longValue()) {
            return false;
        }
        return true;
    }

    public static void a(String str, long j, boolean z) {
        if (a(str, j)) {
            a().put(str, Long.valueOf(j));
            a(z);
        }
    }

    private static Map<String, Long> a() {
        if (f10438a.isEmpty()) {
            Context appContext = SHApplication.getAppContext();
            String string = appContext.getSharedPreferences("msg_center_sp_" + CoreApi.a().s(), 0).getString("device_push_red_point", "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    if (!jSONObject.isNull("map")) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("map");
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            f10438a.put(next, Long.valueOf(optJSONObject.optLong(next)));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return f10438a;
    }

    private static void a(boolean z) {
        JSONObject jSONObject = new JSONObject();
        Map<String, Long> a2 = a();
        for (String next : a2.keySet()) {
            try {
                jSONObject.put(next, a2.get(next));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("map", jSONObject);
            Context appContext = SHApplication.getAppContext();
            SharedPreferences sharedPreferences = appContext.getSharedPreferences("msg_center_sp_" + CoreApi.a().s(), 0);
            if (z) {
                sharedPreferences.edit().putString("device_push_red_point", jSONObject2.toString()).commit();
            } else {
                sharedPreferences.edit().putString("device_push_red_point", jSONObject2.toString()).apply();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(final long j, final int i) {
        Observable.zip(Observable.create(new ObservableOnSubscribe<JSONArray>() {
            public void subscribe(final ObservableEmitter<JSONArray> observableEmitter) throws Exception {
                RemoteFamilyApi.a().a(SHApplication.getAppContext(), MessageCenterCountHelper.a(), j, (AsyncCallback<JSONArray, Error>) new AsyncCallback<JSONArray, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONArray jSONArray) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onNext(jSONArray);
                            observableEmitter.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onNext(new JSONArray());
                            observableEmitter.onComplete();
                        }
                    }
                });
            }
        }), Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter observableEmitter) {
                long j = 0;
                for (MessageRecordShop next : MessageRecordShop.queryAll()) {
                    if (next.receiveTime > j) {
                        j = next.receiveTime;
                    }
                }
                ShopApi.a().a(SHApplication.getAppContext(), j, (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Integer num) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onNext(num);
                            observableEmitter.onComplete();
                        }
                    }

                    public void onFailure(Error error) {
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onNext(0);
                            observableEmitter.onComplete();
                        }
                    }
                });
            }
        }).onErrorReturn(new Function<Throwable, Object>() {
            /* renamed from: a */
            public Object apply(Throwable th) {
                return new Integer(0);
            }
        }), new BiFunction() {
            public Object apply(Object obj, Object obj2) throws Exception {
                try {
                    JSONArray jSONArray = (JSONArray) obj;
                    int intValue = ((Integer) obj2).intValue();
                    int i = 0;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        if (optJSONObject != null) {
                            if (!optJSONObject.isNull("count")) {
                                i += optJSONObject.optInt("count");
                            }
                        }
                    }
                    MessageCenter.a().a(i + intValue, i);
                } catch (Exception unused) {
                }
                return new Object();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
