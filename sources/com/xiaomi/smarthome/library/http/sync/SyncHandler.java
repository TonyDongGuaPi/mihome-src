package com.xiaomi.smarthome.library.http.sync;

import okhttp3.Response;

public abstract class SyncHandler<T> {
    public abstract T b(Response response) throws Exception;
}
