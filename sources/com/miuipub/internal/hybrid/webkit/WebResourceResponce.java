package com.miuipub.internal.hybrid.webkit;

import android.webkit.WebResourceResponse;
import miuipub.hybrid.HybridResourceResponse;

public class WebResourceResponce extends WebResourceResponse {
    public WebResourceResponce(HybridResourceResponse hybridResourceResponse) {
        super(hybridResourceResponse.a(), hybridResourceResponse.b(), hybridResourceResponse.c());
    }
}
