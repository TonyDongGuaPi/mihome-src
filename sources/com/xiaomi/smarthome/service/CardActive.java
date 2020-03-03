package com.xiaomi.smarthome.service;

import java.util.concurrent.atomic.AtomicInteger;

public enum CardActive {
    instance;
    
    private AtomicInteger count;

    public boolean isActive() {
        return this.count.get() > 0;
    }

    public void create() {
        this.count.incrementAndGet();
    }

    public void destory() {
        this.count.decrementAndGet();
    }
}
