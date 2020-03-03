package com.xiaomi.payment.data;

import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class EntryData implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12220a = "EntryData";
    private transient Map<String, Object> b;
    public Map<String, Object> mExtraData;
    public String mId;
    public String mIntentUri;
    public String mPackageName;
    public Type mType;
    public String mUrl;

    public <T> T getMetaData(String str, T t) {
        if (this.b == null || !this.b.containsKey(str)) {
            return t;
        }
        try {
            return t.getClass().cast(this.b.get(str));
        } catch (Exception unused) {
            return t;
        }
    }

    public enum Type {
        LOCAL,
        WEB,
        APP;

        public static Type getType(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException e) {
                Log.e(EntryData.f12220a, "EntryData Type IllegalArgumentException ", e);
                return null;
            }
        }

        public static String getTypes() {
            Type[] values = values();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(values[i].name());
            }
            return sb.toString();
        }
    }

    public void parseEntryData(JSONObject jSONObject) throws PaymentException {
        if (jSONObject != null) {
            try {
                this.mId = jSONObject.getString("id");
                this.mExtraData = BundleUtils.a(jSONObject.optJSONObject("extra"));
                this.b = BundleUtils.a(jSONObject.optJSONObject("meta"));
                String string = jSONObject.getString("type");
                this.mType = Type.getType(string);
                if (this.mType != null) {
                    switch (this.mType) {
                        case LOCAL:
                            this.mUrl = jSONObject.optString("url");
                            return;
                        case WEB:
                            this.mUrl = jSONObject.getString("url");
                            if (TextUtils.isEmpty(this.mUrl)) {
                                throw new ResultException("mUrl should not all be null in WEB, type is " + string);
                            }
                            return;
                        case APP:
                            this.mUrl = jSONObject.optString("url");
                            this.mIntentUri = jSONObject.getString("intentUri");
                            this.mPackageName = jSONObject.getString("packageName");
                            if (!TextUtils.isEmpty(this.mIntentUri)) {
                                return;
                            }
                            if (TextUtils.isEmpty(this.mPackageName)) {
                                throw new ResultException("mIntentUri and mPackageName should not all be null in APP, type is " + string);
                            }
                            return;
                        default:
                            return;
                    }
                } else {
                    throw new ResultException("entryData type is null, type is " + string);
                }
            } catch (JSONException e) {
                throw new ResultException((Throwable) e);
            }
        }
    }
}
