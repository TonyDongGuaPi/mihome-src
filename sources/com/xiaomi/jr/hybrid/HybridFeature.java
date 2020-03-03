package com.xiaomi.jr.hybrid;

public abstract class HybridFeature {
    protected HybridContext mHybridContext;

    public void cleanup() {
    }

    public void setHybridContext(HybridContext hybridContext) {
        this.mHybridContext = hybridContext;
    }
}
