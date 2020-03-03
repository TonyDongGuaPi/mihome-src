package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.ParseError;
import com.mishopsdk.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayRequest extends JsonRequest<JSONArray> {
    public JsonArrayRequest(int i, String str, String str2, Response.Listener<JSONArray> listener) {
        super(i, str, str2, listener);
    }

    public JsonArrayRequest(String str, Response.Listener<JSONArray> listener) {
        super(0, str, (String) null, listener);
    }

    public JsonArrayRequest(int i, String str, Response.Listener<JSONArray> listener) {
        super(i, str, (String) null, listener);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonArrayRequest(int i, String str, JSONArray jSONArray, Response.Listener<JSONArray> listener) {
        super(i, str, jSONArray == null ? null : jSONArray.toString(), listener);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonArrayRequest(int i, String str, JSONObject jSONObject, Response.Listener<JSONArray> listener) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), listener);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JsonArrayRequest(String str, JSONArray jSONArray, Response.Listener<JSONArray> listener) {
        this(jSONArray == null ? 0 : 1, str, jSONArray, listener);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JsonArrayRequest(String str, JSONObject jSONObject, Response.Listener<JSONArray> listener) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, listener);
    }

    /* access modifiers changed from: protected */
    public Response<JSONArray> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONArray(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError((Throwable) e));
        } catch (JSONException e2) {
            return Response.error(new ParseError((Throwable) e2));
        }
    }
}
