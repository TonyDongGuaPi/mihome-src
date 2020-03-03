package com.xiaomi.jr.http.netopt;

import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.http.SimpleHttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class Gslb {
    private static final String h = "Gslb";

    /* renamed from: a  reason: collision with root package name */
    public String f10833a;
    public String b;
    public String c;
    public String d;
    public String e;
    public HashMap<String, ArrayList<String>> f;
    public HashMap<String, ArrayList<String>> g;

    public static Gslb a(String str) {
        try {
            SimpleHttpRequest.Response a2 = SimpleHttpRequest.a(str, (Map<String, String>) null);
            if (a2.f10820a) {
                JSONObject jSONObject = new JSONObject(a2.b);
                if ("ok".equalsIgnoreCase(jSONObject.getString("S"))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("R");
                    Gslb gslb = new Gslb();
                    gslb.f10833a = jSONObject2.getString("country");
                    gslb.b = jSONObject2.getString("province");
                    gslb.c = jSONObject2.getString("city");
                    gslb.d = jSONObject2.getString("isp");
                    gslb.e = jSONObject2.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP);
                    gslb.f = a(jSONObject2.getJSONObject("wifi"));
                    gslb.g = a(jSONObject2.getJSONObject("wap"));
                    return gslb;
                }
            }
        } catch (Exception e2) {
            MifiLog.b(h, "create gslb fail: " + e2.getMessage());
        }
        return null;
    }

    private static HashMap<String, ArrayList<String>> a(JSONObject jSONObject) {
        try {
            HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONArray jSONArray = jSONObject.getJSONArray(next);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                hashMap.put(next, arrayList);
            }
            return hashMap;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
