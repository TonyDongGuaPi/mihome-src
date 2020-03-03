package com.xiaomi.youpin.share.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.R;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;

public class WeiboShareActivity extends BaseActivity implements WbShareCallback {
    public static final String EXTRA_HANDLE_RESULT = "extra_handle_result";
    static final String TAG = "WeiboShareActivity";

    /* renamed from: a  reason: collision with root package name */
    private WbShareHandler f23736a;
    private boolean b = false;
    private boolean c;
    private ShareObject d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        this.d = (ShareObject) getIntent().getParcelableExtra("share");
        if (this.d == null) {
            ShareEventUtil.b(this.mContext, false, "weibo", -1, "shareObject is null");
            return;
        }
        this.f23736a = new WbShareHandler(this);
        this.f23736a.registerApp();
        String f = this.d.f();
        String g = this.d.g();
        if (!TextUtils.isEmpty(this.d.h())) {
            g = this.d.h();
        }
        if (!TextUtils.isEmpty(this.d.d())) {
            f = this.d.d();
        }
        if (!TextUtils.isEmpty(this.d.e())) {
            g = this.d.e();
        }
        try {
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            TextObject textObject = new TextObject();
            if (TextUtils.equals(g, f)) {
                textObject.text = g;
            } else {
                textObject.text = "#" + f + "#" + g;
            }
            weiboMultiMessage.textObject = textObject;
            if (this.d.l() != null) {
                ImageObject imageObject = new ImageObject();
                imageObject.setImageObject(ShareManager.a(this.d.l()));
                weiboMultiMessage.imageObject = imageObject;
            }
            Bitmap a2 = ShareManager.a(this.d.k());
            if (a2 == null) {
                a2 = getAppIcon(getApplicationContext());
            }
            if (a2 != null) {
                WebpageObject webpageObject = new WebpageObject();
                webpageObject.identify = Utility.a();
                if (weiboMultiMessage.imageObject != null) {
                    f = "";
                }
                webpageObject.title = f;
                webpageObject.setThumbImage(a2);
                webpageObject.description = this.d.g();
                webpageObject.actionUrl = this.d.i();
                webpageObject.defaultText = this.d.g();
                weiboMultiMessage.mediaObject = webpageObject;
            }
            this.f23736a.shareMessage(weiboMultiMessage, true);
        } catch (OutOfMemoryError unused) {
            LogUtils.w(TAG, "bitmap decode failed!");
            ShareEventUtil.b(this.mContext, false, "weibo", -1, "OutOfMemoryError");
        } catch (Exception e) {
            LogUtils.w(TAG, "bitmap decode failed!");
            Context context = this.mContext;
            ShareEventUtil.b(context, false, "weibo", -1, "Exception " + e);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.f23736a != null) {
            this.f23736a.doResultIntent(intent, this);
        }
    }

    public static Bitmap getAppIcon(Context context) {
        Drawable drawable;
        if (context == null || (drawable = context.getResources().getDrawable(R.drawable.youpin)) == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.b) {
            a();
        } else {
            this.b = true;
        }
    }

    private void a() {
        overridePendingTransition(0, 0);
        finish();
    }

    public void onWbShareSuccess() {
        ShareEventUtil.b(this.mContext, true, "weibo", 0, "");
        a();
    }

    public void onWbShareCancel() {
        ShareEventUtil.b(this.mContext, false, "weibo", -100, "cancel");
        a();
    }

    public void onWbShareFail() {
        ShareEventUtil.b(this.mContext, false, "weibo", -1, "");
        a();
    }
}
