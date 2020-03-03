package com.mishopsdk.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.mishopsdk.volley.Cache;
import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.Response;

public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    /* access modifiers changed from: protected */
    public Response<Object> parseNetworkResponse(NetworkResponse networkResponse) {
        return null;
    }

    public ClearCacheRequest(Cache cache, Runnable runnable) {
        super(0, (String) null, (Response.Listener) null);
        this.mCache = cache;
        this.mCallback = runnable;
    }

    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback == null) {
            return true;
        }
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        return true;
    }

    public Request.Priority getPriority() {
        return Request.Priority.IMMEDIATE;
    }
}
