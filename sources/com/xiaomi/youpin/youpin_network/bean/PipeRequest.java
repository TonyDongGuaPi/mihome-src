package com.xiaomi.youpin.youpin_network.bean;

import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;

public class PipeRequest<T> {
    public RequestAsyncCallback<T, NetError> callback;
    public YouPinJsonParser<T> parser;
    public RequestParams requestParams;

    public PipeRequest() {
    }

    public PipeRequest(RequestParams requestParams2, YouPinJsonParser<T> youPinJsonParser, RequestAsyncCallback<T, NetError> requestAsyncCallback) {
        this.requestParams = requestParams2;
        this.parser = youPinJsonParser;
        this.callback = requestAsyncCallback;
    }
}
