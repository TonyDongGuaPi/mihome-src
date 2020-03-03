package com.xiaomi.smarthome.homeroom.model;

import android.text.TextUtils;
import com.mi.global.bbs.manager.Region;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.stat.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18317a = "more";
    private HashMap<String, String> b;
    private int c;
    private int d;
    private String e;
    private int f;

    public RoomConfig(HashMap<String, String> hashMap, int i, int i2, String str) {
        this.b = hashMap;
        this.c = i;
        this.d = i2;
        this.e = str;
    }

    public RoomConfig() {
    }

    public String a(Locale locale) {
        if (locale == null) {
            return "";
        }
        String str = this.b.get(locale.toString());
        return TextUtils.isEmpty(str) ? this.b.get("en_US") : str;
    }

    public void a(HashMap<String, String> hashMap) {
        this.b = hashMap;
    }

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public int b() {
        return this.f;
    }

    public static RoomConfig a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONObject jSONObject2;
        RoomConfig roomConfig = new RoomConfig();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("name")) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("name");
                    roomConfig.b = new HashMap<>();
                    if (optJSONObject.has("en")) {
                        roomConfig.b.put("en_US", optJSONObject.getString("en"));
                        optJSONObject.remove("en");
                    }
                    if (optJSONObject.has(d.u)) {
                        roomConfig.b.put("es_ES", optJSONObject.getString(d.u));
                        optJSONObject.remove(d.u);
                    }
                    if (optJSONObject.has("ko")) {
                        roomConfig.b.put("ko_KR", optJSONObject.getString("ko"));
                        optJSONObject.remove("ko");
                    }
                    if (optJSONObject.has(Region.RU)) {
                        roomConfig.b.put("ru_RU", optJSONObject.getString(Region.RU));
                        optJSONObject.remove(Region.RU);
                    }
                    if (optJSONObject.has("zh_CN")) {
                        roomConfig.b.put("zh_CN", optJSONObject.getString("zh_CN"));
                        optJSONObject.remove("zh_CN");
                    }
                    if (optJSONObject.has("zh_TW")) {
                        roomConfig.b.put("zh_HK", optJSONObject.getString("zh_TW"));
                    }
                    if (optJSONObject.has("zh_TW")) {
                        roomConfig.b.put("zh_TW", optJSONObject.getString("zh_TW"));
                        optJSONObject.remove("zh_TW");
                    }
                    if (optJSONObject.has("it")) {
                        roomConfig.b.put("it_IT", optJSONObject.getString("it"));
                        optJSONObject.remove("it");
                    }
                    if (optJSONObject.has("fr")) {
                        roomConfig.b.put("fr_FR", optJSONObject.getString("fr"));
                        optJSONObject.remove("fr");
                    }
                    if (optJSONObject.has("de")) {
                        roomConfig.b.put("de_DE", optJSONObject.getString("de"));
                        optJSONObject.remove("de");
                    }
                    if (optJSONObject.has("id")) {
                        roomConfig.b.put("in_ID", optJSONObject.getString("id"));
                        optJSONObject.remove("id");
                    }
                    if (optJSONObject.has(d.U)) {
                        roomConfig.b.put("pl_PL", optJSONObject.getString(d.U));
                        optJSONObject.remove(d.U);
                    }
                    if (optJSONObject.has("vi")) {
                        roomConfig.b.put("vi_VN", optJSONObject.getString("vi"));
                        optJSONObject.remove("vi");
                    }
                    if (optJSONObject.has("th")) {
                        roomConfig.b.put("th_TH", optJSONObject.getString("th"));
                        optJSONObject.remove("th");
                    }
                    if (optJSONObject.has("ja")) {
                        roomConfig.b.put("ja_JP", optJSONObject.getString("ja"));
                        optJSONObject.remove("ja");
                    }
                    if (optJSONObject.has("nl")) {
                        roomConfig.b.put("nl_NL", optJSONObject.getString("nl"));
                        optJSONObject.remove("nl");
                    }
                    if (optJSONObject.has("pt_BR")) {
                        roomConfig.b.put("pt_BR", optJSONObject.getString("pt_BR"));
                        optJSONObject.remove("pt_BR");
                    }
                    if (optJSONObject.has(BaseInfo.KEY_TIME_RECORD)) {
                        roomConfig.b.put("tr_TR", optJSONObject.getString(BaseInfo.KEY_TIME_RECORD));
                        optJSONObject.remove(BaseInfo.KEY_TIME_RECORD);
                    }
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        roomConfig.b.put(next, optJSONObject.optString(next, ""));
                    }
                }
                if (!jSONObject.isNull("type")) {
                    roomConfig.c = jSONObject.optInt("type");
                }
                if (!jSONObject.isNull("extraInfo") && (optJSONArray = jSONObject.optJSONArray("extraInfo")) != null && optJSONArray.length() > 0 && (jSONObject2 = (JSONObject) optJSONArray.get(0)) != null) {
                    roomConfig.e = jSONObject2.optString(MibiConstants.fU);
                    roomConfig.f = jSONObject2.optInt("iconCount");
                    roomConfig.d = jSONObject2.optInt("id");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return roomConfig;
    }
}
