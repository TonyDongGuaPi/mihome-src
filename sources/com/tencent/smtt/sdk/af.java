package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.WebResourceError;

class af extends WebResourceError {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ android.webkit.WebResourceError f9136a;
    final /* synthetic */ ad b;

    af(ad adVar, android.webkit.WebResourceError webResourceError) {
        this.b = adVar;
        this.f9136a = webResourceError;
    }

    public CharSequence getDescription() {
        return this.f9136a.getDescription();
    }

    public int getErrorCode() {
        return this.f9136a.getErrorCode();
    }
}
