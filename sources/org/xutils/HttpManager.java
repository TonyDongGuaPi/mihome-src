package org.xutils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

public interface HttpManager {
    <T> T a(HttpMethod httpMethod, RequestParams requestParams, Class<T> cls) throws Throwable;

    <T> T a(HttpMethod httpMethod, RequestParams requestParams, Callback.TypedCallback<T> typedCallback) throws Throwable;

    <T> T a(RequestParams requestParams, Class<T> cls) throws Throwable;

    <T> Callback.Cancelable a(HttpMethod httpMethod, RequestParams requestParams, Callback.CommonCallback<T> commonCallback);

    <T> Callback.Cancelable a(RequestParams requestParams, Callback.CommonCallback<T> commonCallback);

    <T> T b(RequestParams requestParams, Class<T> cls) throws Throwable;

    <T> Callback.Cancelable b(RequestParams requestParams, Callback.CommonCallback<T> commonCallback);
}
