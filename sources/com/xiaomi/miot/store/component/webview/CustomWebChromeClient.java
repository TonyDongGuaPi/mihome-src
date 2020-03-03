package com.xiaomi.miot.store.component.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;

public class CustomWebChromeClient extends WebChromeClient {
    public static final int FILECHOOSER_RESULTCODE = 1000;
    ValueCallback<Uri[]> mUploadCallbackAboveL;
    ValueCallback<Uri> mUploadMessage;

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return true;
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        callback.invoke(str, true, false);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        i.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1000);
    }

    public void openFileChooser(ValueCallback valueCallback, String str) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        i.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1000);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i == null) {
            valueCallback.onReceiveValue((Object) null);
            return;
        }
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        i.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1000);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Activity i = RNAppStoreApiManager.a().i();
        if (i == null) {
            valueCallback.onReceiveValue((Object) null);
            return false;
        }
        this.mUploadCallbackAboveL = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        i.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1000);
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1000) {
            return;
        }
        if (this.mUploadMessage != null || this.mUploadCallbackAboveL != null) {
            Uri data = (intent == null || i2 != -1) ? null : intent.getData();
            if (this.mUploadCallbackAboveL != null) {
                onActivityResultAboveL(i, i2, intent);
            } else if (this.mUploadMessage != null) {
                this.mUploadMessage.onReceiveValue(data);
                this.mUploadMessage = null;
            }
        }
    }

    @TargetApi(21)
    private void onActivityResultAboveL(int i, int i2, Intent intent) {
        Uri[] uriArr;
        Uri[] uriArr2;
        if (i == 1000 && this.mUploadCallbackAboveL != null) {
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
            this.mUploadCallbackAboveL.onReceiveValue(uriArr);
            this.mUploadCallbackAboveL = null;
        }
    }
}
