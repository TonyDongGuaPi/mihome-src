package com.ximalaya.ting.android.sdkdownloader.http.app;

import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;

public interface RedirectHandler {
    RequestParams a(UriRequest uriRequest);
}
