package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IconListener;
import com.tencent.smtt.sdk.WebIconDatabase;

class bj implements IconListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebIconDatabase.a f9163a;
    final /* synthetic */ WebIconDatabase b;

    bj(WebIconDatabase webIconDatabase, WebIconDatabase.a aVar) {
        this.b = webIconDatabase;
        this.f9163a = aVar;
    }

    public void onReceivedIcon(String str, Bitmap bitmap) {
        this.f9163a.a(str, bitmap);
    }
}
