package com.miuipub.internal.hybrid;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConfigParser implements ConfigParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8262a = "signature";
    private static final String b = "timestamp";
    private static final String c = "vendor";
    private static final String d = "content";
    private static final String e = "features";
    private static final String f = "params";
    private static final String g = "name";
    private static final String h = "value";
    private static final String i = "permissions";
    private static final String j = "origin";
    private static final String k = "subdomains";
    private JSONObject l;

    private Config a(Config config, Map<String, Object> map) {
        return config;
    }

    private JsonConfigParser(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public static JsonConfigParser a(String str) throws HybridException {
        try {
            return a(new JSONObject(str));
        } catch (JSONException e2) {
            throw new HybridException(201, e2.getMessage());
        }
    }

    public static JsonConfigParser a(JSONObject jSONObject) {
        return new JsonConfigParser(jSONObject);
    }

    public Config a(Map<String, Object> map) throws HybridException {
        Config config = new Config();
        try {
            JSONObject jSONObject = this.l;
            Security security = new Security();
            security.a(jSONObject.getString("signature"));
            security.a(jSONObject.getLong("timestamp"));
            config.a(security);
            config.a(jSONObject.getString(c));
            config.b(jSONObject.optString("content"));
            a(config, jSONObject);
            b(config, jSONObject);
            return a(config, map);
        } catch (JSONException e2) {
            throw new HybridException(201, e2.getMessage());
        }
    }

    private void a(Config config, JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("features");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                Feature feature = new Feature();
                feature.a(jSONObject2.getString("name"));
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("params");
                if (optJSONArray2 != null) {
                    for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                        JSONObject jSONObject3 = optJSONArray2.getJSONObject(i3);
                        feature.a(jSONObject3.getString("name"), jSONObject3.getString("value"));
                    }
                }
                config.a(feature);
            }
        }
    }

    private void b(Config config, JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("permissions");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                Permission permission = new Permission();
                permission.a(jSONObject2.getString("origin"));
                permission.a(jSONObject2.optBoolean(k));
                config.a(permission);
            }
        }
    }
}
