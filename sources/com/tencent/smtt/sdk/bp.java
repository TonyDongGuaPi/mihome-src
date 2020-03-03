package com.tencent.smtt.sdk;

import android.graphics.Picture;
import android.webkit.WebView;
import com.tencent.smtt.sdk.WebView;

class bp implements WebView.PictureListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView.PictureListener f9169a;
    final /* synthetic */ WebView b;

    bp(WebView webView, WebView.PictureListener pictureListener) {
        this.b = webView;
        this.f9169a = pictureListener;
    }

    public void onNewPicture(android.webkit.WebView webView, Picture picture) {
        this.b.a(webView);
        this.f9169a.onNewPicture(this.b, picture);
    }
}
