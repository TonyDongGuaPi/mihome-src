package com.tencent.smtt.sdk;

import android.graphics.Picture;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.WebView;

class bq implements IX5WebViewBase.PictureListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView.PictureListener f9170a;
    final /* synthetic */ WebView b;

    bq(WebView webView, WebView.PictureListener pictureListener) {
        this.b = webView;
        this.f9170a = pictureListener;
    }

    public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
        this.b.a(iX5WebViewBase);
        this.f9170a.onNewPicture(this.b, picture);
    }

    public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
    }
}
