package com.xiaomi.smarthome.device.api.spec.instance;

import com.xiaomi.smarthome.device.api.spec.definitions.SpecDefinition;

public class Spec {
    protected int iid;

    public static abstract class SpecItem extends Spec {
        public abstract SpecDefinition getDefinition();
    }

    public int getIid() {
        return this.iid;
    }

    public void setIid(int i) {
        this.iid = i;
    }
}
