package com.xiaomi.smarthome.framework.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.xiaomi.router.miio.miioplugin.IPluginCallback;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.LocalDeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.page.MiioPageV2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonDeviceH5WebPage extends MiioPageV2 {

    /* renamed from: a  reason: collision with root package name */
    Handler f17844a = new Handler();
    AsyncCallback<JSONObject, Error> b;
    IPluginCallback.Stub c = new IPluginCallback.Stub() {
        public void onRequestSuccess(String str) throws RemoteException {
            CommonDeviceH5WebPage.this.a(str);
            CommonDeviceH5WebPage.this.f17844a.postDelayed(new Runnable() {
                public void run() {
                    CommonDeviceH5WebPage.this.c();
                }
            }, 50);
            Miio.g("usb on success");
        }

        public void onRequestFailed(int i, String str) throws RemoteException {
            CommonDeviceH5WebPage commonDeviceH5WebPage = CommonDeviceH5WebPage.this;
            commonDeviceH5WebPage.a("erorr=" + i);
            Miio.g("usb on failed");
        }
    };
    /* access modifiers changed from: private */
    public WebView d;
    /* access modifiers changed from: private */
    public ProgressBar j;

    public void a() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        if (this.d != null) {
            this.d.onResume();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.d != null) {
            this.d.onPause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.common_device_h5_web, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                CommonDeviceH5WebPage.this.a(jSONObject.toString());
                CommonDeviceH5WebPage.this.f17844a.postDelayed(new Runnable() {
                    public void run() {
                        CommonDeviceH5WebPage.this.c();
                    }
                }, 50);
            }

            public void onFailure(Error error) {
                CommonDeviceH5WebPage.this.a("");
            }
        };
        this.d = (WebView) view.findViewById(R.id.common_web_view);
        this.j = (ProgressBar) view.findViewById(R.id.common_web_loading);
        b();
        this.d.loadUrl("file:///android_asset/test.html");
        this.f17844a.postDelayed(new Runnable() {
            public void run() {
                CommonDeviceH5WebPage.this.c();
            }
        }, 500);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (Build.VERSION.SDK_INT >= 11) {
            this.d.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        WebSettings settings = this.d.getSettings();
        settings.setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT < 19) {
            settings.setDatabasePath("/data/data/" + this.d.getContext().getPackageName() + "/databases/");
        }
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkLoads(false);
        settings.setBlockNetworkImage(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        this.d.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void rpcCall(String str) {
                if (CommonDeviceH5WebPage.this.f.location != Device.Location.LOCAL || WifiUtil.a(SHApplication.getAppContext()) == null) {
                    DeviceApi.getInstance().rpcAsyncRemote(CommonDeviceH5WebPage.this.getActivity(), CommonDeviceH5WebPage.this.f.did, CommonDeviceH5WebPage.this.f.token, str, CommonDeviceH5WebPage.this.b);
                    return;
                }
                LocalDeviceApi.getInstance().rpcAsync(CommonDeviceH5WebPage.this.f.did, str, CommonDeviceH5WebPage.this.b);
            }
        }, "rpcObject");
        this.d.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.d.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                CommonDeviceH5WebPage.this.j.setProgress(i);
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        this.d.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                CommonDeviceH5WebPage.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
    }

    public void a(final String str) {
        this.f17844a.post(new Runnable() {
            public void run() {
                WebView f = CommonDeviceH5WebPage.this.d;
                f.loadUrl("javascript:rpc_return('" + str + "')");
            }
        });
    }

    public void b(final String str) {
        this.f17844a.post(new Runnable() {
            public void run() {
                WebView f = CommonDeviceH5WebPage.this.d;
                f.loadUrl("javascript:rpc_status('" + str + "')");
            }
        });
    }

    public void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", 6);
            jSONObject.put("method", "get_prop");
            jSONObject.put("params", new JSONObject());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("on");
            jSONArray.put("usb_on");
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        this.f.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                CommonDeviceH5WebPage.this.b(jSONObject.toString());
            }
        });
    }
}
