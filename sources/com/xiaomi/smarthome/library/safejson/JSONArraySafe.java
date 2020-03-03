package com.xiaomi.smarthome.library.safejson;

import android.annotation.TargetApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONArraySafe extends JSONArray {

    /* renamed from: a  reason: collision with root package name */
    private JSONArray f19125a;
    private boolean b = false;

    public JSONArraySafe() {
    }

    public JSONArraySafe(String str) throws JSONException {
        super(str);
    }

    public JSONArraySafe(JSONArray jSONArray) {
        this.f19125a = jSONArray;
    }

    public JSONArraySafe(boolean z) {
        this.b = z;
    }

    public JSONArraySafe(String str, boolean z) throws JSONException {
        super(str);
        this.b = z;
    }

    public JSONArraySafe(JSONArray jSONArray, boolean z) {
        this.f19125a = jSONArray;
        this.b = z;
    }

    /* renamed from: a */
    public JSONArraySafe getJSONArray(int i) throws JSONException {
        JSONArray jSONArray;
        try {
            jSONArray = this.f19125a == null ? super.getJSONArray(i) : this.f19125a.getJSONArray(i);
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
    public JSONObjectSafe getJSONObject(int i) throws JSONException {
        JSONObject jSONObject;
        try {
            jSONObject = this.f19125a == null ? super.getJSONObject(i) : this.f19125a.getJSONObject(i);
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
    public JSONArraySafe optJSONArray(int i) {
        JSONArray optJSONArray = this.f19125a == null ? super.optJSONArray(i) : this.f19125a.optJSONArray(i);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        return new JSONArraySafe(optJSONArray, this.b);
    }

    /* renamed from: d */
    public JSONObjectSafe optJSONObject(int i) {
        JSONObject optJSONObject = this.f19125a == null ? super.optJSONObject(i) : this.f19125a.optJSONObject(i);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        return new JSONObjectSafe(optJSONObject, this.b);
    }

    public int length() {
        return this.f19125a == null ? super.length() : this.f19125a.length();
    }

    /* renamed from: a */
    public JSONArraySafe put(boolean z) {
        JSONArray put = this.f19125a == null ? super.put(z) : this.f19125a.put(z);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(double d) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(d) : this.f19125a.put(d);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: e */
    public JSONArraySafe put(int i) {
        JSONArray put = this.f19125a == null ? super.put(i) : this.f19125a.put(i);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(long j) {
        JSONArray put = this.f19125a == null ? super.put(j) : this.f19125a.put(j);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(Object obj) {
        JSONArray put = this.f19125a == null ? super.put(obj) : this.f19125a.put(obj);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(int i, boolean z) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(i, z) : this.f19125a.put(i, z);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(int i, double d) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(i, d) : this.f19125a.put(i, d);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(int i, int i2) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(i, i2) : this.f19125a.put(i, i2);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(int i, long j) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(i, j) : this.f19125a.put(i, j);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    /* renamed from: a */
    public JSONArraySafe put(int i, Object obj) throws JSONException {
        JSONArray put = this.f19125a == null ? super.put(i, obj) : this.f19125a.put(i, obj);
        if (put == null) {
            put = new JSONArray();
        }
        return new JSONArraySafe(put);
    }

    public boolean isNull(int i) {
        return this.f19125a == null ? super.isNull(i) : this.f19125a.isNull(i);
    }

    public Object get(int i) throws JSONException {
        return this.f19125a == null ? super.get(i) : this.f19125a.get(i);
    }

    public Object opt(int i) {
        return this.f19125a == null ? super.opt(i) : this.f19125a.opt(i);
    }

    @TargetApi(19)
    public Object remove(int i) {
        return this.f19125a == null ? super.remove(i) : this.f19125a.remove(i);
    }

    @Deprecated
    public boolean getBoolean(int i) throws JSONException {
        return this.f19125a == null ? super.getBoolean(i) : this.f19125a.getBoolean(i);
    }

    @Deprecated
    public boolean optBoolean(int i) {
        return this.f19125a == null ? super.optBoolean(i) : this.f19125a.optBoolean(i);
    }

    public boolean optBoolean(int i, boolean z) {
        return this.f19125a == null ? super.optBoolean(i, z) : this.f19125a.optBoolean(i, z);
    }

    @Deprecated
    public double getDouble(int i) throws JSONException {
        return this.f19125a == null ? super.getDouble(i) : this.f19125a.getDouble(i);
    }

    @Deprecated
    public double optDouble(int i) {
        return this.f19125a == null ? super.optDouble(i) : this.f19125a.optDouble(i);
    }

    public double optDouble(int i, double d) {
        return this.f19125a == null ? super.optDouble(i, d) : this.f19125a.optDouble(i, d);
    }

    @Deprecated
    public int getInt(int i) throws JSONException {
        return this.f19125a == null ? super.getInt(i) : this.f19125a.getInt(i);
    }

    @Deprecated
    public int optInt(int i) {
        return this.f19125a == null ? super.optInt(i) : this.f19125a.optInt(i);
    }

    public int optInt(int i, int i2) {
        return this.f19125a == null ? super.optInt(i, i2) : this.f19125a.optInt(i, i2);
    }

    @Deprecated
    public long getLong(int i) throws JSONException {
        return this.f19125a == null ? super.getLong(i) : this.f19125a.getLong(i);
    }

    @Deprecated
    public long optLong(int i) {
        return this.f19125a == null ? super.optLong(i) : this.f19125a.optLong(i);
    }

    public long optLong(int i, long j) {
        return this.f19125a == null ? super.optLong(i, j) : this.f19125a.optLong(i, j);
    }

    public String getString(int i) throws JSONException {
        String string = this.f19125a == null ? super.getString(i) : this.f19125a.getString(i);
        return string == null ? "" : string;
    }

    public String optString(int i) {
        String optString = this.f19125a == null ? super.optString(i) : this.f19125a.optString(i);
        return optString == null ? "" : optString;
    }

    public String optString(int i, String str) {
        return this.f19125a == null ? super.optString(i, str) : this.f19125a.optString(i, str);
    }

    /* renamed from: a */
    public JSONObjectSafe toJSONObject(JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = this.f19125a == null ? super.toJSONObject(jSONArray) : this.f19125a.toJSONObject(jSONArray);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return new JSONObjectSafe(jSONObject);
    }

    public String join(String str) throws JSONException {
        String join = this.f19125a == null ? super.join(str) : this.f19125a.join(str);
        return join == null ? "" : join;
    }

    public String toString() {
        String jSONArray = this.f19125a == null ? super.toString() : this.f19125a.toString();
        return jSONArray == null ? "" : jSONArray;
    }

    public String toString(int i) throws JSONException {
        String jSONArray = this.f19125a == null ? super.toString(i) : this.f19125a.toString(i);
        return jSONArray == null ? "" : jSONArray;
    }

    public boolean equals(Object obj) {
        return this.f19125a == null ? super.equals(obj) : this.f19125a.equals(obj);
    }

    public int hashCode() {
        return this.f19125a == null ? super.hashCode() : this.f19125a.hashCode();
    }
}
