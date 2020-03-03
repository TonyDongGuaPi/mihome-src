package com.tencent.smtt.sdk;

import android.content.Intent;
import android.webkit.WebChromeClient;
import com.tencent.smtt.sdk.WebChromeClient;

class ac extends WebChromeClient.FileChooserParams {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebChromeClient.FileChooserParams f9126a;
    final /* synthetic */ SystemWebChromeClient b;

    ac(SystemWebChromeClient systemWebChromeClient, WebChromeClient.FileChooserParams fileChooserParams) {
        this.b = systemWebChromeClient;
        this.f9126a = fileChooserParams;
    }

    public Intent createIntent() {
        return this.f9126a.createIntent();
    }

    public String[] getAcceptTypes() {
        return this.f9126a.getAcceptTypes();
    }

    public String getFilenameHint() {
        return this.f9126a.getFilenameHint();
    }

    public int getMode() {
        return this.f9126a.getMode();
    }

    public CharSequence getTitle() {
        return this.f9126a.getTitle();
    }

    public boolean isCaptureEnabled() {
        return this.f9126a.isCaptureEnabled();
    }
}
