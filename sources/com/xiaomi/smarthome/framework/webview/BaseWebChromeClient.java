package com.xiaomi.smarthome.framework.webview;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class BaseWebChromeClient extends WebChromeClient implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    /* renamed from: a  reason: collision with root package name */
    boolean f17840a = false;
    FrameLayout b;
    WebChromeClient.CustomViewCallback c;
    Callback d;
    WebView e;
    View f;
    ViewGroup g;

    public interface Callback {
        void a();

        void b();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
    }

    class JavaScriptInterface {

        /* renamed from: a  reason: collision with root package name */
        WebChromeClient f17841a;

        public JavaScriptInterface(WebChromeClient webChromeClient) {
            this.f17841a = webChromeClient;
        }

        @JavascriptInterface
        public void notifyVideoEnd() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (JavaScriptInterface.this.f17841a != null) {
                        JavaScriptInterface.this.f17841a.onHideCustomView();
                    }
                }
            });
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void a(WebView webView, View view, ViewGroup viewGroup, Callback callback) {
        this.e = webView;
        this.f = view;
        this.g = viewGroup;
        this.d = callback;
        webView.addJavascriptInterface(new JavaScriptInterface(this), "_VideoEnabledWebView");
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            View focusedChild = frameLayout.getFocusedChild();
            this.f17840a = true;
            this.b = frameLayout;
            this.c = customViewCallback;
            this.f.setVisibility(4);
            this.g.addView(this.b, new ViewGroup.LayoutParams(-1, -1));
            this.g.setVisibility(0);
            if (focusedChild instanceof VideoView) {
                VideoView videoView = (VideoView) focusedChild;
                videoView.setOnPreparedListener(this);
                videoView.setOnCompletionListener(this);
                videoView.setOnErrorListener(this);
            } else if (this.e != null && this.e.getSettings().getJavaScriptEnabled() && (focusedChild instanceof SurfaceView)) {
                a(this.e, (((((((("javascript:" + "var _ytrp_html5_video_last;") + "var _ytrp_html5_video = document.getElementsByTagName('video')[0];") + "if (_ytrp_html5_video != undefined && _ytrp_html5_video != _ytrp_html5_video_last) {") + "_ytrp_html5_video_last = _ytrp_html5_video;") + "function _ytrp_html5_video_ended() {") + "_VideoEnabledWebView.notifyVideoEnd();") + "}") + "_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);") + "}");
            }
            if (this.d != null) {
                this.d.a();
            }
        }
    }

    public void onHideCustomView() {
        if (this.f17840a) {
            this.g.setVisibility(4);
            this.g.removeView(this.b);
            this.f.setVisibility(0);
            if (this.c != null && !this.c.getClass().getName().contains(".chromium.")) {
                this.c.onCustomViewHidden();
            }
            this.f17840a = false;
            this.b = null;
            this.c = null;
            if (this.d != null) {
                this.d.b();
            }
        }
    }

    private void a(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        onHideCustomView();
    }

    public boolean a() {
        if (!this.f17840a) {
            return false;
        }
        onHideCustomView();
        return true;
    }
}
