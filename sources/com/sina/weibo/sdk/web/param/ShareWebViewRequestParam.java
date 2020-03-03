package com.sina.weibo.sdk.web.param;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.network.IRequestService;
import com.sina.weibo.sdk.network.impl.RequestParam;
import com.sina.weibo.sdk.network.impl.RequestService;
import com.sina.weibo.sdk.network.target.SimpleTarget;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.utils.WbSdkVersion;
import com.sina.weibo.sdk.web.WebPicUploadResult;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;

public class ShareWebViewRequestParam extends BaseWebViewRequestParam {
    public static final String b = "https://service.weibo.com/share/mobilesdk.php";
    private static final String c = "https://service.weibo.com/share/mobilesdk_uppic.php";
    private WeiboMultiMessage d;
    /* access modifiers changed from: private */
    public String e;
    private String f;
    private byte[] g;
    private String h;
    private String i;
    private String j;

    public ShareWebViewRequestParam() {
    }

    public ShareWebViewRequestParam(Context context) {
        this.f8862a = context;
    }

    public ShareWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, String str2, String str3, Context context) {
        this(authInfo, webRequestType, str, 0, str2, str3, context);
    }

    public ShareWebViewRequestParam(AuthInfo authInfo, WebRequestType webRequestType, String str, int i2, String str2, String str3, Context context) {
        super(authInfo, webRequestType, str, i2, str2, str3, context);
    }

    public boolean a() {
        if (this.g == null || this.g.length <= 0) {
            return super.a();
        }
        return true;
    }

    public void a(final BaseWebViewRequestParam.ExtraTaskCallback extraTaskCallback) {
        super.a(extraTaskCallback);
        LogUtil.b("Share", "ShareWebViewRequestParam.doExtraTask()");
        new WeiboParameters(d().getAuthInfo().getAppKey());
        String str = new String(this.g);
        IRequestService instance = RequestService.getInstance();
        RequestParam.Builder builder = new RequestParam.Builder(this.f8862a);
        builder.setShortUrl(c);
        builder.addPostParam("img", str);
        builder.addPostParam("appKey", d().getAuthInfo().getAppKey());
        instance.asyncRequest(builder.build(), new SimpleTarget() {
            public void onSuccess(String str) {
                LogUtil.b("Share", "ShareWebViewRequestParam.doExtraTask().onSuccess()");
                WebPicUploadResult a2 = WebPicUploadResult.a(str);
                if (a2 != null && a2.a() == 1 && !TextUtils.isEmpty(a2.b())) {
                    String unused = ShareWebViewRequestParam.this.e = a2.b();
                    if (extraTaskCallback != null) {
                        extraTaskCallback.a(ShareWebViewRequestParam.this.e);
                    }
                } else if (extraTaskCallback != null) {
                    extraTaskCallback.b("upload pic fail");
                }
            }

            public void onFailure(Exception exc) {
                LogUtil.b("Share", "ShareWebViewRequestParam.doExtraTask().onFailure(),e =" + exc.getMessage());
                if (extraTaskCallback != null) {
                    extraTaskCallback.b("upload pic fail");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        if (this.d != null) {
            this.d.toBundle(bundle);
        }
        bundle.putString("token", this.h);
        bundle.putString("packageName", this.i);
        bundle.putString("hashKey", this.j);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        this.d = new WeiboMultiMessage();
        this.d.toObject(bundle);
        this.h = bundle.getString("token");
        this.i = bundle.getString("packageName");
        this.j = bundle.getString("hashKey");
        e();
    }

    public String b() {
        String appKey = d().getAuthInfo().getAppKey();
        Uri.Builder buildUpon = Uri.parse(b).buildUpon();
        buildUpon.appendQueryParameter("title", this.f);
        buildUpon.appendQueryParameter("version", WbSdkVersion.f8850a);
        if (!TextUtils.isEmpty(appKey)) {
            buildUpon.appendQueryParameter("source", appKey);
        }
        if (!TextUtils.isEmpty(this.h)) {
            buildUpon.appendQueryParameter("access_token", this.h);
        }
        if (this.f8862a != null) {
            String b2 = Utility.b(this.f8862a, appKey);
            if (!TextUtils.isEmpty(b2)) {
                buildUpon.appendQueryParameter("aid", b2);
            }
        }
        if (!TextUtils.isEmpty(this.i)) {
            buildUpon.appendQueryParameter("packagename", this.i);
        }
        if (!TextUtils.isEmpty(this.j)) {
            buildUpon.appendQueryParameter("key_hash", this.j);
        }
        if (!TextUtils.isEmpty(this.e)) {
            buildUpon.appendQueryParameter("picinfo", this.e);
        }
        buildUpon.appendQueryParameter("luicode", "10000360");
        buildUpon.appendQueryParameter("lfid", "OP_" + appKey);
        return buildUpon.build().toString();
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(WeiboMultiMessage weiboMultiMessage) {
        this.d = weiboMultiMessage;
    }

    public void b(String str) {
        this.h = str;
    }

    public void c(String str) {
        this.j = str;
    }

    public void d(String str) {
        this.i = str;
    }

    private void e() {
        StringBuilder sb = new StringBuilder();
        if (this.d.textObject instanceof TextObject) {
            TextObject textObject = this.d.textObject;
            sb.append(textObject.text + " ");
        }
        if (this.d.mediaObject != null && (this.d.mediaObject instanceof WebpageObject) && !TextUtils.isEmpty(this.d.mediaObject.actionUrl)) {
            sb.append(this.d.mediaObject.actionUrl);
        }
        if (this.d.imageObject instanceof ImageObject) {
            ImageObject imageObject = this.d.imageObject;
            a(imageObject.imagePath, imageObject.imageData);
        }
        this.f = sb.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|(0)|24|25) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0044 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041 A[SYNTHETIC, Splitter:B:22:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0048 A[SYNTHETIC, Splitter:B:30:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r6, byte[] r7) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{  }
            if (r0 != 0) goto L_0x004b
            java.io.File r0 = new java.io.File     // Catch:{  }
            r0.<init>(r6)     // Catch:{  }
            boolean r6 = r0.exists()     // Catch:{  }
            if (r6 == 0) goto L_0x004b
            boolean r6 = r0.canRead()     // Catch:{  }
            if (r6 == 0) goto L_0x004b
            long r1 = r0.length()     // Catch:{  }
            r3 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x004b
            long r1 = r0.length()     // Catch:{  }
            int r6 = (int) r1     // Catch:{  }
            byte[] r6 = new byte[r6]     // Catch:{  }
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, all -> 0x003d }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0045, all -> 0x003d }
            r2.read(r6)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            byte[] r6 = com.sina.weibo.sdk.utils.Base64.c(r6)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            r5.g = r6     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            r2.close()     // Catch:{ Exception -> 0x003a }
        L_0x003a:
            return
        L_0x003b:
            r6 = move-exception
            goto L_0x003f
        L_0x003d:
            r6 = move-exception
            r2 = r1
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r2.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            throw r6     // Catch:{  }
        L_0x0045:
            r2 = r1
        L_0x0046:
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ SecurityException -> 0x004b }
        L_0x004b:
            if (r7 == 0) goto L_0x0056
            int r6 = r7.length
            if (r6 <= 0) goto L_0x0056
            byte[] r6 = com.sina.weibo.sdk.utils.Base64.c(r7)
            r5.g = r6
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.web.param.ShareWebViewRequestParam.a(java.lang.String, byte[]):void");
    }
}
