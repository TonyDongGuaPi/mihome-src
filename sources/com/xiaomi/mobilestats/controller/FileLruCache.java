package com.xiaomi.mobilestats.controller;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;

public class FileLruCache {
    private static FileLruCache E;
    private static HashMap F = new a(20, 0.75f, true);

    private FileLruCache() {
    }

    public static FileLruCache getInstance() {
        if (E == null) {
            E = new FileLruCache();
        }
        return E;
    }

    public void clearCache() {
        if (F != null) {
            F.clear();
        }
    }

    public HashMap getCacheHashMap() {
        return F;
    }

    public File getFile(String str) {
        if (F == null || !F.containsKey(str)) {
            return null;
        }
        return (File) F.get(str);
    }

    public void putFile(String str, File file) {
        if (F != null && !F.containsKey(str)) {
            F.put(str, file);
        }
    }

    public void removeFile(String str) {
        if (!TextUtils.isEmpty(str) && F != null && F.containsKey(str)) {
            F.remove(str);
        }
    }
}
