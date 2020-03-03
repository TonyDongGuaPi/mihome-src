package com.xiaomi.mobilestats.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

final class a extends LinkedHashMap {
    a(int i, float f, boolean z) {
        super(i, f, z);
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry entry) {
        if (size() <= 20) {
            return false;
        }
        File file = (File) entry.getValue();
        if (file == null || !file.exists()) {
            return true;
        }
        file.delete();
        return true;
    }
}
