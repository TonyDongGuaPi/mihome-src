package com.mi.global.shop.request;

import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.mi.global.shop.model.SyncModel;
import com.mi.util.RequestQueueUtil;

public class MiProtobufRequestHelper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Object f6944a;
    private MiProtobufRequest b;
    private RetryPolicy c;

    public MiProtobufRequestHelper(int i, String[] strArr, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(i, strArr, (String) null, listener, errorListener);
    }

    public MiProtobufRequestHelper(int i, String[] strArr, String str, final Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        final Response.Listener<byte[]> listener2 = listener;
        final String[] strArr2 = strArr;
        final String str2 = str;
        final Response.ErrorListener errorListener2 = errorListener;
        this.b = new MiProtobufRequest(i, strArr[0], str2, new Response.Listener<byte[]>() {
            /* renamed from: a */
            public void onResponse(byte[] bArr) {
                listener.onResponse(bArr);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse == null || volleyError.networkResponse.statusCode != 302 || strArr2.length <= 1) {
                    errorListener2.onErrorResponse(volleyError);
                    return;
                }
                MiProtobufRequest miProtobufRequest = new MiProtobufRequest(0, strArr2[1], str2, new Response.Listener<byte[]>() {
                    /* renamed from: a */
                    public void onResponse(byte[] bArr) {
                        listener2.onResponse(bArr);
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
                miProtobufRequest.setTag(MiProtobufRequestHelper.this.f6944a);
                RequestQueueUtil.a().add(miProtobufRequest);
            }
        });
    }

    public void a(Object obj) {
        this.f6944a = obj;
    }

    public void a(RetryPolicy retryPolicy) {
        this.c = retryPolicy;
    }

    public void a() {
        if (this.c != null) {
            this.b.setRetryPolicy(this.c);
        }
        if (this.f6944a != null) {
            this.b.setTag(this.f6944a);
        }
        RequestQueueUtil.a().add(this.b);
    }
}
