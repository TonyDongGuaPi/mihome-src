package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.base.BaseFragmentWebViewActivity;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;

public class CloudVideoWebActivity extends BaseFragmentWebViewActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_URL = "url";
    public static final String REQUEST_CLOUD_BUY_CATEYE = "https://camera.api.io.mi.com/miot/camera/web/vip/cateye";
    public static final String REQUEST_CLOUD_BUY_CATEYE_mipay_sr62m5p7ds = "https://camera.api.io.mi.com/miot/camera/web/vip/mipay_sr62m5p7ds/cateye";
    public static final String REQUEST_CLOUD_BUY_mipay_sr62m5p7ds_v2 = "https://camera.api.io.mi.com/miot/camera/web/vip/mipay_sr62m5p7ds/v2/home";
    public static final String REQUEST_CLOUD_BUY_v2 = "https://camera.api.io.mi.com/miot/camera/web/vip/v2/home";
    public static final String REQUEST_CLOUD_MANAGEMENT = "https://camera.api.io.mi.com/miot/camera/web/vip/myhome/cloud";
    public static final String REQUEST_CLOUD_SERVICE = "https://camera.api.io.mi.com/miot/camera/web/vip";
    public static final String REQUEST_CLOUD_SERVICE_EULA = "https://home.mi.com/app_page/new_cloud_service.html";
    public static final String REQUEST_CLOUD_SERVICE_PURCHASE = "https://camera.api.io.mi.com/miot/camera/web/vip/home";
    public static final String REQUEST_CLOUD_SERVICE_PURCHASE_mipay_sr62m5p7ds = "https://camera.api.io.mi.com/miot/camera/web/vip/mipay_sr62m5p7ds/home";
    public static final String REQUEST_CLOUD_URL_ORDER_LIST = "https://camera.api.io.mi.com/miot/camera/web/orderlist";
    private static final String TAG = "CloudVideoWebActivity";
    private String mDid;

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return R.id.container;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_web_activity);
        String stringExtra = getIntent().getStringExtra("url");
        String stringExtra2 = getIntent().getStringExtra("title");
        this.mDid = getIntent().getStringExtra("did");
        if (!TextUtils.isEmpty(stringExtra)) {
            openNewWindow((CommonWebViewFragment) null, CloudWebViewFragmentCompat.newInstance(stringExtra, stringExtra2, this.mDid));
        } else {
            finish();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 427) {
            if (i2 == -1) {
                LogUtil.a(TAG, "signDeduct success");
                finish();
                return;
            }
            reloadWebView();
            LogUtil.a(TAG, "signDeduct failed resultCode = " + i2);
        } else if (i != 424) {
        } else {
            if (i2 == -1) {
                LogUtil.a(TAG, "pay success");
                finish();
            } else if (i2 == 0) {
                LogUtil.a(TAG, "pay cancelled");
                reloadWebView();
            } else {
                LogUtil.a(TAG, "pay failed");
                reloadWebView();
            }
        }
    }

    private void reloadWebView() {
        try {
            CommonWebView webView = getLatestFragment().getWebView();
            webView.setDid(this.mDid);
            webView.initCookie();
            webView.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
