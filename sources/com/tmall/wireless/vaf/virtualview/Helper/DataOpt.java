package com.tmall.wireless.vaf.virtualview.Helper;

import com.libra.virtualview.common.Common;
import com.libra.virtualview.common.DataItem;
import com.tmall.wireless.vaf.virtualview.loader.StringLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataOpt {

    /* renamed from: a  reason: collision with root package name */
    private static StringLoader f9367a;

    public static void a(StringLoader stringLoader) {
        f9367a = stringLoader;
    }

    private static void a(JSONArray jSONArray, boolean z) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("tag");
                    if (optString != null) {
                        int a2 = f9367a.a(optString, false);
                        optJSONObject.remove("tag");
                        try {
                            optJSONObject.put("tag", a2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String optString2 = optJSONObject.optString("key");
                    if (optString2 != null) {
                        int a3 = f9367a.a(optString2, false);
                        optJSONObject.remove("key");
                        try {
                            optJSONObject.put("key", a3);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    Object opt = optJSONObject.opt("value");
                    if (opt != null) {
                        if ((opt instanceof JSONArray) || (opt instanceof JSONObject)) {
                            a(opt);
                        } else if (opt instanceof String) {
                            DataItem dataItem = new DataItem((String) opt);
                            if (Common.b(dataItem)) {
                                optJSONObject.remove("value");
                                try {
                                    optJSONObject.put("value", dataItem.b);
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            a(jSONObject.optJSONArray("vData"), false);
            a(jSONObject.optJSONArray("vStyle"), true);
        }
    }

    public static void a(Object obj) {
        JSONArray jSONArray;
        if (obj instanceof JSONObject) {
            jSONArray = ((JSONObject) obj).optJSONArray("data");
        } else {
            jSONArray = obj instanceof JSONArray ? (JSONArray) obj : null;
        }
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                a(jSONArray.optJSONObject(i));
            }
        }
    }
}
