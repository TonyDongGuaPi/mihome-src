package com.xiaomi.smarthome.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class VoiceH5Page_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private VoiceH5Page f22795a;

    @UiThread
    public VoiceH5Page_ViewBinding(VoiceH5Page voiceH5Page) {
        this(voiceH5Page, voiceH5Page.getWindow().getDecorView());
    }

    @UiThread
    public VoiceH5Page_ViewBinding(VoiceH5Page voiceH5Page, View view) {
        this.f22795a = voiceH5Page;
        voiceH5Page.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'mWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        VoiceH5Page voiceH5Page = this.f22795a;
        if (voiceH5Page != null) {
            this.f22795a = null;
            voiceH5Page.mWebView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
