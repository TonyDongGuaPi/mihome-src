package com.mi.global.shop.request;

import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.mi.global.shop.model.SyncModel;
import com.mi.util.RequestQueueUtil;
import org.json.JSONObject;

public class MiHttpHttpsRequestHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Object f6936a;
    private MiJsonObjectRequest b;
    private RetryPolicy c;

    public MiHttpHttpsRequestHelper(int i, String[] strArr, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(i, strArr, (String) null, listener, errorListener);
    }

    public MiHttpHttpsRequestHelper(int i, String[] strArr, String str, final Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        final Response.Listener<JSONObject> listener2 = listener;
        final String[] strArr2 = strArr;
        final String str2 = str;
        final Response.ErrorListener errorListener2 = errorListener;
        this.b = new MiJsonObjectRequest(i, strArr[0], str2, new Response.Listener<JSONObject>() {
            /* renamed from: a */
            public void onResponse(JSONObject jSONObject) {
                listener.onResponse(jSONObject);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse == null || volleyError.networkResponse.statusCode != 302 || strArr2.length <= 1) {
                    errorListener2.onErrorResponse(volleyError);
                    return;
                }
                MiJsonObjectRequest miJsonObjectRequest = new MiJsonObjectRequest(0, strArr2[1], str2, new Response.Listener<JSONObject>() {
                    /* renamed from: a */
                    public void onResponse(JSONObject jSONObject) {
                        listener2.onResponse(jSONObject);
                        if (strArr2[1].startsWith("https")) {
                            SyncModel.useHttps = true;
                        } else {
                            SyncModel.useHttps = false;
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        errorListener2.onErrorResponse(volleyError);
                    }
                });
                miJsonObjectRequest.setTag(MiHttpHttpsRequestHelper.this.f6936a);
                RequestQueueUtil.a().add(miJsonObjectRequest);
            }
        });
    }

    public void a(Object obj) {
        this.f6936a = obj;
    }

    public void a(RetryPolicy retryPolicy) {
        this.c = retryPolicy;
    }

    public void a() {
        if (this.c != null) {
            this.b.setRetryPolicy(this.c);
        }
        if (this.f6936a != null) {
            this.b.setTag(this.f6936a);
        }
        RequestQueueUtil.a().add(this.b);
    }
}
