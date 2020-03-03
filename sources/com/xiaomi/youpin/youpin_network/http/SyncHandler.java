package com.xiaomi.youpin.youpin_network.http;

import okhttp3.Response;

public abstract class SyncHandler<T> {
    public abstract T a(Response response) throws Exception;
}
