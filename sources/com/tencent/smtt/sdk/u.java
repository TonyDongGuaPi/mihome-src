package com.tencent.smtt.sdk;

import android.content.Intent;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;

class u extends WebChromeClient.FileChooserParams {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IX5WebChromeClient.FileChooserParams f9191a;
    final /* synthetic */ q b;

    u(q qVar, IX5WebChromeClient.FileChooserParams fileChooserParams) {
        this.b = qVar;
        this.f9191a = fileChooserParams;
    }

    public Intent createIntent() {
        return this.f9191a.createIntent();
    }

    public String[] getAcceptTypes() {
        return this.f9191a.getAcceptTypes();
    }

    public String getFilenameHint() {
        return this.f9191a.getFilenameHint();
    }

    public int getMode() {
        return this.f9191a.getMode();
    }

    public CharSequence getTitle() {
        return this.f9191a.getTitle();
    }

    public boolean isCaptureEnabled() {
        return this.f9191a.isCaptureEnabled();
    }
}
