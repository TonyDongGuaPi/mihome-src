package com.mishopsdk.volley;

public interface ResponseDelivery {
    void postError(Request<?> request, VolleyError volleyError);

    void postFinish(Request<?> request);

    void postResponse(Request<?> request, Response<?> response);

    void postResponse(Request<?> request, Response<?> response, Runnable runnable);

    void postStart(Request<?> request);
}
