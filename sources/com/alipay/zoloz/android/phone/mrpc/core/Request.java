package com.alipay.zoloz.android.phone.mrpc.core;

public abstract class Request {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1191a = false;
    protected TransportCallback mCallback;

    public TransportCallback getCallback() {
        return this.mCallback;
    }

    public void setTransportCallback(TransportCallback transportCallback) {
        this.mCallback = transportCallback;
    }

    public void cancel() {
        this.f1191a = true;
    }

    public boolean isCanceled() {
        return this.f1191a;
    }
}
