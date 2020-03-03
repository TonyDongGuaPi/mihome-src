package com.mi.volley;

import com.android.volley.Response;
import org.json.JSONObject;

public class MiJsonObjectRequest extends MiAbstractJsonObjectRequest {
    public String getCookies() {
        return null;
    }

    public MiJsonObjectRequest(int i, String str, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(i, str, (String) null, listener, errorListener);
    }

    public MiJsonObjectRequest(int i, String str, String str2, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
    }
}
