package com.youpin.weex.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.IWXDebugProxy;
import com.taobao.weex.common.WXRenderStrategy;
import com.xiaomiyoupin.toast.YPDToast;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.util.CommonUtils;
import java.util.HashMap;

@Deprecated
public abstract class AbsWeexActivity extends AppCompatActivity implements IWXRenderListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2476a = "AbsWeexActivity";
    /* access modifiers changed from: private */
    public WxReloadListener b;
    /* access modifiers changed from: private */
    public WxRefreshListener c;
    private String d;
    private String e = f2476a;
    private boolean f = false;
    protected Boolean isLocalUrl = false;
    protected BroadcastReceiver mBroadcastReceiver;
    protected ViewGroup mContainer;
    protected WXSDKInstance mInstance;
    protected Uri mUri;

    public interface WxRefreshListener {
        void a();
    }

    public interface WxReloadListener {
        void a();
    }

    public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
    }

    public void onRefreshSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
    }

    public void onRenderSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void postRenderPage() {
    }

    /* access modifiers changed from: protected */
    public void preRenderPage() {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        createWeexInstance();
        this.mInstance.onActivityCreate();
        registerBroadcastReceiver(this.mBroadcastReceiver, (IntentFilter) null);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup getContainer() {
        return this.mContainer;
    }

    /* access modifiers changed from: protected */
    public final void setContainer(ViewGroup viewGroup) {
        this.mContainer = viewGroup;
    }

    /* access modifiers changed from: protected */
    public void destoryWeexInstance() {
        if (this.mInstance != null) {
            this.mInstance.registerRenderListener((IWXRenderListener) null);
            this.mInstance.destroy();
            this.mInstance = null;
        }
    }

    /* access modifiers changed from: protected */
    public void createWeexInstance() {
        destoryWeexInstance();
        this.mInstance = new WXSDKInstance(this);
        this.mInstance.registerRenderListener(this);
    }

    /* access modifiers changed from: protected */
    public void renderPageByURL(String str) {
        renderPageByURL(str, (String) null);
    }

    /* access modifiers changed from: protected */
    public void renderPageByURL(String str, String str2) {
        CommonUtils.a(this.mContainer, new RuntimeException("Can't render page, container is null"));
        HashMap hashMap = new HashMap();
        hashMap.put("bundleUrl", str);
        this.mInstance.renderByUrl(getPageName(), str, hashMap, str2, WXRenderStrategy.APPEND_ASYNC);
    }

    public String getPageName() {
        return this.e;
    }

    public void onStart() {
        super.onStart();
        if (this.mInstance != null) {
            this.mInstance.onActivityStart();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mInstance != null) {
            this.mInstance.onActivityResume();
        }
        WXAppStoreApiManager.b().a((Activity) this);
        WXAppStoreApiManager.b().c().a("weex", this.d, (String) null, this.f ? 1 : 2);
        this.f = true;
    }

    public void onPause() {
        super.onPause();
        if (this.mInstance != null) {
            this.mInstance.onActivityPause();
        }
        WXAppStoreApiManager.b().a((Activity) null);
    }

    public void onStop() {
        super.onStop();
        if (this.mInstance != null) {
            this.mInstance.onActivityStop();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mInstance != null) {
            this.mInstance.onRequestPermissionsResult(i, strArr, iArr);
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mInstance != null) {
            this.mInstance.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mInstance != null) {
            this.mInstance.onActivityDestroy();
        }
        unregisterBroadcastReceiver();
    }

    public void onViewCreated(WXSDKInstance wXSDKInstance, View view) {
        if (this.mContainer != null) {
            this.mContainer.removeAllViews();
            this.mContainer.addView(view);
        }
    }

    public void runWithPermissionsCheck(int i, String str, Runnable runnable) {
        if (ContextCompat.checkSelfPermission(this, str) != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                YPDToast.getInstance().toast((Context) this, "please give me the permission");
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{str}, i);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void setReloadListener(WxReloadListener wxReloadListener) {
        this.b = wxReloadListener;
    }

    public void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (broadcastReceiver == null) {
            broadcastReceiver = new DefaultBroadcastReceiver();
        }
        this.mBroadcastReceiver = broadcastReceiver;
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
        }
        intentFilter.addAction(IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH);
        intentFilter.addAction(WXSDKEngine.JS_FRAMEWORK_RELOAD);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.mBroadcastReceiver, intentFilter);
        if (this.b == null) {
            setReloadListener(new WxReloadListener() {
                public void a() {
                    AbsWeexActivity.this.createWeexInstance();
                    AbsWeexActivity.this.renderPage();
                }
            });
        }
        if (this.c == null) {
            setRefreshListener(new WxRefreshListener() {
                public void a() {
                    AbsWeexActivity.this.createWeexInstance();
                    AbsWeexActivity.this.renderPage();
                }
            });
        }
    }

    public void unregisterBroadcastReceiver() {
        if (this.mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
        setReloadListener((WxReloadListener) null);
        setRefreshListener((WxRefreshListener) null);
    }

    public void setRefreshListener(WxRefreshListener wxRefreshListener) {
        this.c = wxRefreshListener;
    }

    public String getUrl() {
        return this.d;
    }

    public void setUrl(String str) {
        this.d = str;
    }

    public void loadUrl(String str) {
        setUrl(str);
        renderPage();
    }

    /* access modifiers changed from: protected */
    public void renderPage() {
        preRenderPage();
        renderPageByURL(this.d);
        postRenderPage();
    }

    /* access modifiers changed from: protected */
    public boolean isLocalPage() {
        if (this.mUri == null) {
            return true;
        }
        String scheme = this.mUri.getScheme();
        if (!this.mUri.isHierarchical()) {
            return true;
        }
        if (TextUtils.equals(scheme, "http") || TextUtils.equals(scheme, "https")) {
            return false;
        }
        return true;
    }

    public void setPageName(String str) {
        this.e = str;
    }

    public class DefaultBroadcastReceiver extends BroadcastReceiver {
        public DefaultBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH.equals(intent.getAction())) {
                if (AbsWeexActivity.this.c != null) {
                    AbsWeexActivity.this.c.a();
                }
            } else if (WXSDKEngine.JS_FRAMEWORK_RELOAD.equals(intent.getAction()) && AbsWeexActivity.this.b != null) {
                AbsWeexActivity.this.b.a();
            }
        }
    }
}
