package com.xiaomi.jr.hybrid;

public class HybridException extends Exception {
    private Response mResponse;

    public HybridException(String str) {
        super(new Response(200, str).toString());
        this.mResponse = new Response(200, str);
    }

    public HybridException(int i, String str) {
        super(new Response(i, str).toString());
        this.mResponse = new Response(i, str);
    }

    public Response getResponse() {
        return this.mResponse;
    }
}
