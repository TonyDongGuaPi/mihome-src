package com.xiaomi.mishopsdk.io.http;

import android.content.Context;
import android.os.Build;
import com.alibaba.fastjson.TypeReference;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.RequestQueue;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.toolbox.Volley;
import com.xiaomi.mishopsdk.io.http.ShopJSONRequest;
import java.util.HashMap;

public class RequestQueueManager {
    private static RequestQueueManager instance;
    protected Context mContext;
    protected RequestQueue mRequestQueue;

    public static RequestQueueManager getInstance() {
        if (instance == null) {
            instance = new RequestQueueManager();
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 9) {
            this.mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack());
        } else {
            this.mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue != null) {
            return this.mRequestQueue;
        }
        throw new IllegalStateException("Not initialized");
    }

    public <T> Request postApiRequest(Object obj, String str, HashMap<String, String> hashMap, Class cls, boolean z, Response.Listener<T> listener) {
        return this.mRequestQueue.add(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ShopJSONRequest.builder().setTag(obj)).setUrl(str)).setClass(cls).setShouldCache(z)).setListner(listener)).addParams(hashMap)).build());
    }

    public <T> Request postApiRequest(Object obj, String str, HashMap<String, String> hashMap, Class cls, Response.Listener<T> listener) {
        return postApiRequest(obj, str, hashMap, cls, true, listener);
    }

    public <T> Request postApiRequest(Object obj, String str, HashMap<String, String> hashMap, TypeReference<T> typeReference, boolean z, Response.Listener<T> listener) {
        return this.mRequestQueue.add(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ShopJSONRequest.builder().setTag(obj)).setUrl(str)).setTypeToken(typeReference).setShouldCache(z)).setListner(listener)).addParams(hashMap)).build());
    }

    public <T> Request postApiRequest(Object obj, String str, HashMap<String, String> hashMap, TypeReference<T> typeReference, Response.Listener<T> listener) {
        return postApiRequest(obj, str, hashMap, typeReference, true, listener);
    }

    public Request addRequest(Request request) {
        return this.mRequestQueue.add(request);
    }

    public void clearRequest(Object obj) {
        this.mRequestQueue.cancelAll(obj);
    }
}
