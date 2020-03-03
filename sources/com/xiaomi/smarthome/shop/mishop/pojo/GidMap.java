package com.xiaomi.smarthome.shop.mishop.pojo;

import android.util.SparseArray;

public class GidMap {
    private SparseArray<String> gidMap = new SparseArray<>();

    public SparseArray<String> getGidMap() {
        return this.gidMap;
    }

    public void setGidMap(SparseArray<String> sparseArray) {
        this.gidMap = sparseArray;
    }

    public boolean hasData() {
        return this.gidMap.size() > 0;
    }
}
