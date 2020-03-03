package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import android.webkit.WebIconDatabase;
import com.tencent.smtt.sdk.WebIconDatabase;

class bk implements WebIconDatabase.IconListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebIconDatabase.a f9164a;
    final /* synthetic */ WebIconDatabase b;

    bk(WebIconDatabase webIconDatabase, WebIconDatabase.a aVar) {
        this.b = webIconDatabase;
        this.f9164a = aVar;
    }

    public void onReceivedIcon(String str, Bitmap bitmap) {
        this.f9164a.a(str, bitmap);
    }
}
