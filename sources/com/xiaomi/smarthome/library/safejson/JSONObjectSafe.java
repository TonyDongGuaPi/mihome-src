package com.xiaomi.smarthome.library.safejson;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectSafe extends JSONObject {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f19126a;
    private boolean b = false;

    public JSONObjectSafe() {
    }

    public JSONObjectSafe(String str) throws JSONException {
        super(str);
    }

    public JSONObjectSafe(JSONObject jSONObject) {
        this.f19126a = jSONObject;
    }

    public JSONObjectSafe(boolean z) {
        this.b = z;
    }

    public JSONObjectSafe(String str, boolean z) throws JSONException {
        super(str);
        this.b = z;
    }

    public JSONObjectSafe(JSONObject jSONObject, boolean z) {
        this.f19126a = jSONObject;
        this.b = z;
    }

    /* renamed from: a */
    public JSONArraySafe getJSONArray(String str) throws JSONException {
        JSONArray jSONArray;
        try {
            jSONArray = this.f19126a == null ? super.getJSONArray(str) : this.f19126a.getJSONArray(str);
        } catch (JSONException e) {
            if (this.b) {
                e.printStackTrace();
                jSONArray = null;
            } else {
                throw e;
            }
        }
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        return new JSONArraySafe(jSONArray, this.b);
    }

    /* renamed from: b */
    public JSONObjectSafe getJSONObject(String str) throws JSONException {
        JSONObject jSONObject;
        try {
            jSONObject = this.f19126a == null ? super.getJSONObject(str) : this.f19126a.getJSONObject(str);
        } catch (JSONException e) {
            if (this.b) {
                e.printStackTrace();
                jSONObject = null;
            } else {
                throw e;
            }
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return new JSONObjectSafe(jSONObject, this.b);
    }

    /* renamed from: c */
    public JSONArraySafe optJSONArray(String str) {
        JSONArray optJSONArray = this.f19126a == null ? super.optJSONArray(str) : this.f19126a.optJSONArray(str);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        return new JSONArraySafe(optJSONArray, this.b);
    }

    /* renamed from: d */
    public JSONObjectSafe optJSONObject(String str) {
        JSONObject optJSONObject = this.f19126a == null ? super.optJSONObject(str) : this.f19126a.optJSONObject(str);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        return new JSONObjectSafe(optJSONObject, this.b);
    }

    /* renamed from: a */
    public JSONArraySafe toJSONArray(JSONArray jSONArray) throws JSONException {
        JSONArray jSONArray2 = this.f19126a == null ? super.toJSONArray(jSONArray) : this.f19126a.toJSONArray(jSONArray);
        if (jSONArray2 == null) {
            jSONArray2 = new JSONArray();
        }
        return new JSONArraySafe(jSONArray2);
    }

    public Iterator<String> keys() {
        return this.f19126a == null ? super.keys() : this.f19126a.keys();
    }

    /* renamed from: a */
    public JSONArraySafe names() {
        JSONArray names = this.f19126a == null ? super.names() : this.f19126a.names();
        if (names == null) {
            names = new JSONArray();
        }
        return new JSONArraySafe(names);
    }

    public String toString() {
        return this.f19126a == null ? super.toString() : this.f19126a.toString();
    }

    public String toString(int i) throws JSONException {
        return this.f19126a == null ? super.toString(i) : this.f19126a.toString(i);
    }

    public int hashCode() {
        return this.f19126a == null ? super.hashCode() : this.f19126a.hashCode();
    }

    public boolean equals(Object obj) {
        return this.f19126a == null ? super.equals(obj) : this.f19126a.equals(obj);
    }

    public int length() {
        return this.f19126a == null ? super.length() : this.f19126a.length();
    }

    /* renamed from: a */
    public JSONObjectSafe put(String str, boolean z) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, z) : this.f19126a.put(str, z);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: a */
    public JSONObjectSafe put(String str, double d) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, d) : this.f19126a.put(str, d);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: a */
    public JSONObjectSafe put(String str, int i) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, i) : this.f19126a.put(str, i);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: a */
    public JSONObjectSafe put(String str, long j) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, j) : this.f19126a.put(str, j);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: a */
    public JSONObjectSafe put(String str, Object obj) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, obj) : this.f19126a.put(str, obj);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: b */
    public JSONObjectSafe putOpt(String str, Object obj) throws JSONException {
        JSONObject put = this.f19126a == null ? super.put(str, obj) : this.f19126a.put(str, obj);
        if (put == null) {
            put = new JSONObject();
        }
        return new JSONObjectSafe(put);
    }

    /* renamed from: c */
    public JSONObjectSafe accumulate(String str, Object obj) throws JSONException {
        JSONObject accumulate = this.f19126a == null ? super.accumulate(str, obj) : this.f19126a.accumulate(str, obj);
        if (accumulate == null) {
            accumulate = new JSONObject();
        }
        return new JSONObjectSafe(accumulate);
    }

    public Object remove(String str) {
        return this.f19126a == null ? super.remove(str) : this.f19126a.remove(str);
    }

    public boolean isNull(String str) {
        return this.f19126a == null ? super.isNull(str) : this.f19126a.isNull(str);
    }

    public boolean has(String str) {
        return this.f19126a == null ? super.has(str) : this.f19126a.has(str);
    }

    public Object get(String str) throws JSONException {
        return this.f19126a == null ? super.get(str) : this.f19126a.get(str);
    }

    public Object opt(String str) {
        return this.f19126a == null ? super.opt(str) : this.f19126a.opt(str);
    }

    @Deprecated
    public boolean getBoolean(String str) throws JSONException {
        return this.f19126a == null ? super.getBoolean(str) : this.f19126a.getBoolean(str);
    }

    @Deprecated
    public boolean optBoolean(String str) {
        return this.f19126a == null ? super.optBoolean(str) : this.f19126a.optBoolean(str);
    }

    public boolean optBoolean(String str, boolean z) {
        return this.f19126a == null ? super.optBoolean(str, z) : this.f19126a.optBoolean(str, z);
    }

    @Deprecated
    public double getDouble(String str) throws JSONException {
        return this.f19126a == null ? super.getDouble(str) : this.f19126a.getDouble(str);
    }

    @Deprecated
    public double optDouble(String str) {
        return this.f19126a == null ? super.optDouble(str) : this.f19126a.optDouble(str);
    }

    public double optDouble(String str, double d) {
        return this.f19126a == null ? super.optDouble(str, d) : this.f19126a.optDouble(str, d);
    }

    @Deprecated
    public int getInt(String str) throws JSONException {
        return this.f19126a == null ? super.getInt(str) : this.f19126a.getInt(str);
    }

    @Deprecated
    public int optInt(String str) {
        return this.f19126a == null ? super.optInt(str) : this.f19126a.optInt(str);
    }

    public int optInt(String str, int i) {
        return this.f19126a == null ? super.optInt(str, i) : this.f19126a.optInt(str, i);
    }

    @Deprecated
    public long getLong(String str) throws JSONException {
        return this.f19126a == null ? super.getLong(str) : this.f19126a.getLong(str);
    }

    @Deprecated
    public long optLong(String str) {
        return this.f19126a == null ? super.optLong(str) : this.f19126a.optLong(str);
    }

    public long optLong(String str, long j) {
        return this.f19126a == null ? super.optLong(str, j) : this.f19126a.optLong(str, j);
    }

    public String getString(String str) throws JSONException {
        String string = this.f19126a == null ? super.getString(str) : this.f19126a.getString(str);
        return string == null ? "" : string;
    }

    public String optString(String str) {
        String optString = this.f19126a == null ? super.optString(str) : this.f19126a.optString(str);
        return optString == null ? "" : optString;
    }

    public String optString(String str, String str2) {
        String optString = this.f19126a == null ? super.optString(str, str2) : this.f19126a.optString(str, str2);
        return optString == null ? "" : optString;
    }
}
