package com.mishopsdk.volley;

import com.mishopsdk.volley.Cache;

public class Response<T> {
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    public boolean isResponseFromCache;
    public final T result;

    public interface Listener<T> {
        void onError(VolleyError volleyError);

        void onFinish();

        void onStart();

        void onSuccess(T t, boolean z);
    }

    public static class SimpleListener<T> implements Listener<T> {
        public void onError(VolleyError volleyError) {
        }

        public void onFinish() {
        }

        public void onStart() {
        }

        public void onSuccess(T t, boolean z) {
        }
    }

    public static <T> Response<T> success(T t, Cache.Entry entry) {
        return new Response<>(t, entry);
    }

    public static <T> Response<T> error(VolleyError volleyError) {
        return new Response<>(volleyError);
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    private Response(T t, Cache.Entry entry) {
        this.intermediate = false;
        this.isResponseFromCache = false;
        this.result = t;
        this.cacheEntry = entry;
        this.error = null;
    }

    private Response(VolleyError volleyError) {
        this.intermediate = false;
        this.isResponseFromCache = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = volleyError;
    }
}
