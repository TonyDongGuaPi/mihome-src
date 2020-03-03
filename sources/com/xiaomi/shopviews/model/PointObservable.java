package com.xiaomi.shopviews.model;

import java.util.Observable;

public class PointObservable extends Observable {

    /* renamed from: a  reason: collision with root package name */
    private BaseUserCentralCounterInfo f13167a;

    public BaseUserCentralCounterInfo a() {
        return this.f13167a;
    }

    public void b() {
        if (this.f13167a != null) {
            setChanged();
            notifyObservers();
        }
    }

    public void a(BaseUserCentralCounterInfo baseUserCentralCounterInfo) {
        if (this.f13167a != baseUserCentralCounterInfo) {
            this.f13167a = baseUserCentralCounterInfo;
            setChanged();
            notifyObservers();
        }
    }
}
