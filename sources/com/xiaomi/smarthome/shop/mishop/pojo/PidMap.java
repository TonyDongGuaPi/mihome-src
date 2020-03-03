package com.xiaomi.smarthome.shop.mishop.pojo;

import android.util.SparseArray;

public class PidMap {
    private SparseArray<String> pidMap = new SparseArray<>();

    public SparseArray<String> getPidMap() {
        return this.pidMap;
    }

    public void setPidMap(SparseArray<String> sparseArray) {
        this.pidMap = sparseArray;
    }

    public boolean hasData() {
        return this.pidMap.size() > 0;
    }
}
