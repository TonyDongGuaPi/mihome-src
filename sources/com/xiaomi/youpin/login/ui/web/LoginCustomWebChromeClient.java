package com.xiaomi.youpin.login.ui.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

public class LoginCustomWebChromeClient extends WebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23621a = 1001;
    private ValueCallback<Uri> b;
    private ValueCallback<Uri[]> c;
    private WeakReference<Activity> d;

    public LoginCustomWebChromeClient(Activity activity) {
        this.d = new WeakReference<>(activity);
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        callback.invoke(str, true, false);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        Activity activity = (Activity) this.d.get();
        if (activity == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.b = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1001);
    }

    public void openFileChooser(ValueCallback valueCallback, String str) {
        Activity activity = (Activity) this.d.get();
        if (activity == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.b = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1001);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        Activity activity = (Activity) this.d.get();
        if (activity == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.b = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1001);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Activity activity = (Activity) this.d.get();
        if (activity == null) {
            valueCallback.onReceiveValue((Object) null);
            return false;
        }
        this.c = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1001);
        return true;
    }

    public void a(int i, int i2, Intent intent) {
        if (i != 1001) {
            return;
        }
        if (this.b != null || this.c != null) {
            Uri data = (intent == null || i2 != -1) ? null : intent.getData();
            if (this.c != null) {
                b(i, i2, intent);
            } else if (this.b != null) {
                this.b.onReceiveValue(data);
                this.b = null;
            }
        }
    }

    @TargetApi(21)
    private void b(int i, int i2, Intent intent) {
        Uri[] uriArr;
        Uri[] uriArr2;
        if (i == 1001 && this.c != null) {
            if (i2 != -1 || intent == null) {
                uriArr = null;
            } else {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    uriArr2 = new Uri[clipData.getItemCount()];
                    for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                        uriArr2[i3] = clipData.getItemAt(i3).getUri();
                    }
                } else {
                    uriArr2 = null;
                }
                uriArr = dataString != null ? new Uri[]{Uri.parse(dataString)} : uriArr2;
            }
            this.c.onReceiveValue(uriArr);
            this.c = null;
        }
    }
}
