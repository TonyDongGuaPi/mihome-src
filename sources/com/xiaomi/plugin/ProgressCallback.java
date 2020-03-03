package com.xiaomi.plugin;

public interface ProgressCallback<T> extends Callback<T> {
    void onProgress(long j, long j2);
}
