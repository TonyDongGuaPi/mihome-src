package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.text.TextUtils;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnotherNameManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10647a = "AnotherNameManager";
    private static volatile AnotherNameManager b;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, List<String>> d = new HashMap();

    public static AnotherNameManager a() {
        if (b == null) {
            synchronized (AnotherNameManager.class) {
                if (b == null) {
                    b = new AnotherNameManager();
                }
            }
        }
        return b;
    }

    private AnotherNameManager() {
    }

    public List<String> a(AsyncCallback<List<String>, Error> asyncCallback, boolean z) {
        if (asyncCallback == null) {
            return this.c;
        }
        if (z) {
            a(asyncCallback);
        }
        return this.c;
    }

    public List<String> a(String str, AsyncCallback<List<String>, Error> asyncCallback, boolean z, String str2) {
        if (asyncCallback == null) {
            Map<String, List<String>> map = this.d;
            return map.get(str + str2);
        }
        if (z) {
            a(str, asyncCallback, str2);
        }
        Map<String, List<String>> map2 = this.d;
        return map2.get(str + str2);
    }

    public void a(String str, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback, String str2, List<PowerMultikeyBean> list2) {
        final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        final String str3 = str;
        final String str4 = str2;
        final List<String> list3 = list;
        RemoteFamilyApi.a().a(str, list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (asyncCallback2 != null) {
                    asyncCallback2.onSuccess(jSONObject);
                }
                if (jSONObject != null && jSONObject.optInt("code") == 0) {
                    Map a2 = AnotherNameManager.this.d;
                    a2.put(str3 + str4, list3);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback2 != null) {
                    asyncCallback2.onFailure(error);
                }
            }
        }, str2, list2);
    }

    private void a(final AsyncCallback<List<String>, Error> asyncCallback) {
        RemoteFamilyApi.a().h(new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    try {
                        if (jSONObject.get("result") == null) {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray("result");
                    if (optJSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            String optString = optJSONArray.optJSONObject(i).optString("model");
                            if (!TextUtils.isEmpty(optString)) {
                                arrayList.add(optString);
                            }
                        }
                        AnotherNameManager.this.c.addAll(arrayList);
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(arrayList);
                            return;
                        }
                        return;
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(-1, "result cannot cast to JSONArray"));
                        return;
                    } else {
                        return;
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "response is null or result is null"));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    private void a(final String str, final AsyncCallback<List<String>, Error> asyncCallback, final String str2) {
        RemoteFamilyApi.a().h(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray optJSONArray = jSONObject.optJSONArray(next);
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                arrayList.add(optJSONArray.optString(i));
                            }
                            Map a2 = AnotherNameManager.this.d;
                            a2.put(str + next, arrayList);
                        }
                    }
                    Map a3 = AnotherNameManager.this.d;
                    List list = (List) a3.get(str + str2);
                    if (list == null && asyncCallback != null) {
                        asyncCallback.onFailure(new Error(-1, "result cannot cast to JSONArray"));
                    } else if (asyncCallback != null) {
                        asyncCallback.onSuccess(list);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "response is null or result is null"));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }
}
