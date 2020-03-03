package com.xiaomi.mishopsdk.event;

import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.VolleyError;

public class NetworkCompletedEvent {
    public VolleyError mError;
    public NetworkResponse mNetworkResponse;
    public Request mRequest;

    public NetworkCompletedEvent(NetworkResponse networkResponse, Request request) {
        this.mNetworkResponse = networkResponse;
        this.mRequest = request;
    }

    public NetworkCompletedEvent(NetworkResponse networkResponse, Request<?> request, VolleyError volleyError) {
        this.mNetworkResponse = networkResponse;
        this.mRequest = request;
        this.mError = volleyError;
    }

    public NetworkCompletedEvent(Request<?> request, VolleyError volleyError) {
        this.mRequest = request;
        this.mError = volleyError;
    }
}
