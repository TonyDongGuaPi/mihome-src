package com.mishopsdk.volley;

public class VolleyError extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public VolleyError() {
        this.networkResponse = null;
    }

    public VolleyError(NetworkResponse networkResponse2) {
        this.networkResponse = networkResponse2;
    }

    public VolleyError(String str) {
        super(str);
        this.networkResponse = null;
    }

    public VolleyError(String str, Throwable th) {
        super(str, th);
        this.networkResponse = null;
    }

    public VolleyError(Throwable th) {
        super(th);
        this.networkResponse = null;
    }

    public VolleyError(Throwable th, NetworkResponse networkResponse2) {
        super(th);
        this.networkResponse = networkResponse2;
    }

    public void setNetworkTimeMs(long j) {
        this.networkTimeMs = j;
    }

    public long getNetworkTimeMs() {
        return this.networkTimeMs;
    }
}
