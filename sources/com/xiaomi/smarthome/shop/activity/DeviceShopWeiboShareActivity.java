package com.xiaomi.smarthome.shop.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.shop.share.ShareManager;
import com.xiaomi.smarthome.shop.share.ShareObject;

public class DeviceShopWeiboShareActivity extends DeviceShopBaseActivity {
    public static final String DATA_CALLER_SMARTHOME = "caller_smarthome";
    public static final String EXTRA_HANDLE_RESULT = "extra_handle_result";
    public static final String KEY_CALLER = "caller";
    static final String TAG = "DeviceShopWeiboShareActivity";

    /* renamed from: a  reason: collision with root package name */
    private WbShareHandler f22163a;
    private boolean b = false;
    private boolean c;
    private String d;
    private ShareObject e;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = (ShareObject) getIntent().getParcelableExtra("share");
        this.c = getIntent().getBooleanExtra("extra_handle_result", true);
        this.d = getIntent().getStringExtra(KEY_CALLER);
        this.f22163a = new WbShareHandler(this);
        this.f22163a.registerApp();
        try {
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            TextObject textObject = new TextObject();
            if (TextUtils.equals(this.e.h(), this.e.f())) {
                textObject.text = this.e.h();
            } else {
                textObject.text = "#" + this.e.f() + "#" + this.e.h();
            }
            weiboMultiMessage.textObject = textObject;
            if (this.e.l() != null) {
                ImageObject imageObject = new ImageObject();
                imageObject.setImageObject(ShareManager.a(this.e.l()));
                weiboMultiMessage.imageObject = imageObject;
            }
            if (this.e.k() != null) {
                Bitmap a2 = ShareManager.a(this.e.k());
                WebpageObject webpageObject = new WebpageObject();
                webpageObject.identify = Utility.a();
                webpageObject.title = weiboMultiMessage.imageObject == null ? this.e.f() : "";
                webpageObject.setThumbImage(a2);
                webpageObject.description = this.e.g();
                webpageObject.actionUrl = this.e.i();
                webpageObject.defaultText = this.e.g();
                weiboMultiMessage.mediaObject = webpageObject;
            }
            this.f22163a.shareMessage(weiboMultiMessage, true);
        } catch (OutOfMemoryError unused) {
            Log.w(TAG, "bitmap decode failed!");
        } catch (Exception unused2) {
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.b) {
            finish();
        } else {
            this.b = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Miio.h(TAG, "onNewIntent");
        if (this.f22163a != null) {
            this.f22163a.doResultIntent(intent, new WbShareCallback() {
                public void onWbShareSuccess() {
                    ToastUtil.a((int) R.string.device_shop_share_success);
                    DeviceShopWeiboShareActivity.this.a(ShareManager.b, 0, "", (Bundle) null);
                    DeviceShopWeiboShareActivity.this.finish();
                }

                public void onWbShareCancel() {
                    ToastUtil.a((int) R.string.device_shop_share_cancel);
                    DeviceShopWeiboShareActivity.this.a(ShareManager.b, -100, "cancel", (Bundle) null);
                    DeviceShopWeiboShareActivity.this.finish();
                }

                public void onWbShareFail() {
                    ToastUtil.a((int) R.string.device_shop_share_failure);
                    DeviceShopWeiboShareActivity.this.a(ShareManager.b, -1, "", (Bundle) null);
                    DeviceShopWeiboShareActivity.this.finish();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, int i, String str2, Bundle bundle) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(str);
        intent.putExtra("result_code", i == 0 ? 0 : 2);
        intent.putExtra("message", str2);
        intent.putExtra("extras", bundle);
        instance.sendBroadcast(intent);
    }
}
