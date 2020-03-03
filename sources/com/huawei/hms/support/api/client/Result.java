package com.huawei.hms.support.api.client;

public abstract class Result {

    /* renamed from: a  reason: collision with root package name */
    private Status f5878a = Status.FAILURE;

    public Status getStatus() {
        return this.f5878a;
    }

    public void setStatus(Status status) {
        if (status != null) {
            this.f5878a = status;
        }
    }
}
