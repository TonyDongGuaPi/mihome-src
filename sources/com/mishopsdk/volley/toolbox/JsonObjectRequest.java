package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.ParseError;
import com.mishopsdk.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectRequest extends JsonRequest<JSONObject> {
    public JsonObjectRequest(int i, String str, String str2, Response.Listener<JSONObject> listener) {
        super(i, str, str2, listener);
    }

    public JsonObjectRequest(String str, Response.Listener<JSONObject> listener) {
        super(0, str, (String) null, listener);
    }

    public JsonObjectRequest(int i, String str, Response.Listener<JSONObject> listener) {
        super(i, str, (String) null, listener);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonObjectRequest(int i, String str, JSONObject jSONObject, Response.Listener<JSONObject> listener) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), listener);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JsonObjectRequest(String str, JSONObject jSONObject, Response.Listener<JSONObject> listener) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, listener);
    }

    /* access modifiers changed from: protected */
    public Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError((Throwable) e));
        } catch (JSONException e2) {
            return Response.error(new ParseError((Throwable) e2));
        }
    }
}
