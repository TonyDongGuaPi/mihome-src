package com.xiaomi.youpin.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import java.io.ByteArrayOutputStream;

public class WechatShareUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23676a = 100;
    public static final int b = 80;
    public static final int c = 60;

    private static void a(Context context, WXMediaMessage wXMediaMessage, int i, String str, final AsyncCallback<Void, YouPinError> asyncCallback) {
        IWXAPI b2 = YouPinShareApi.a().b();
        if (b2.isWXAppInstalled()) {
            final String b3 = b(str);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = b3;
            req.message = wXMediaMessage;
            req.scene = i;
            if (asyncCallback != null) {
                ShareEventUtil.e(context, new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
                            String action = intent.getAction();
                            char c = 65535;
                            if (action.hashCode() == -1583157845 && action.equals(ShareEventUtil.g)) {
                                c = 0;
                            }
                            if (c == 0) {
                                if (b3.equals(ShareEventUtil.d(intent))) {
                                    ShareEventUtil.f(context, this);
                                    boolean a2 = ShareEventUtil.a(intent);
                                    int b2 = ShareEventUtil.b(intent);
                                    String c2 = ShareEventUtil.c(intent);
                                    if (a2) {
                                        asyncCallback.b(null);
                                    } else {
                                        asyncCallback.b(new YouPinError(b2, c2));
                                    }
                                }
                            }
                        }
                    }
                });
            }
            b2.sendReq(req);
        } else if (asyncCallback != null) {
            asyncCallback.b(new YouPinError(-1, "Wechat App not install"));
        }
    }

    private static WXMediaMessage a(String str) {
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str;
        return wXMediaMessage;
    }

    private static WXMediaMessage a(@NonNull Bitmap bitmap) {
        WXImageObject wXImageObject = new WXImageObject(bitmap);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        if (createScaledBitmap.getByteCount() > 32768) {
            createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true);
        }
        if (createScaledBitmap.getByteCount() > 32768) {
            createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
        }
        bitmap.recycle();
        wXMediaMessage.thumbData = a(createScaledBitmap, false);
        return wXMediaMessage;
    }

    private static WXMediaMessage a(String str, String str2, String str3, Bitmap bitmap) {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.title = str2;
        wXMediaMessage.description = str3;
        wXMediaMessage.thumbData = a(bitmap, false);
        return wXMediaMessage;
    }

    private static WXMediaMessage b(String str, String str2, String str3, Bitmap bitmap) {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.title = str2;
        wXMediaMessage.description = str3;
        wXMediaMessage.thumbData = a(bitmap, false);
        return wXMediaMessage;
    }

    private static WXMediaMessage c(String str, String str2, String str3, Bitmap bitmap) {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXWebpageObject;
        wXMediaMessage.title = str2;
        wXMediaMessage.description = str3;
        wXMediaMessage.thumbData = a(bitmap, false);
        return wXMediaMessage;
    }

    private static WXMediaMessage a(String str, String str2, String str3, String str4, String str5, Bitmap bitmap) {
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = str;
        wXMiniProgramObject.miniprogramType = 0;
        wXMiniProgramObject.userName = str2;
        wXMiniProgramObject.path = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
        wXMediaMessage.title = str4;
        wXMediaMessage.description = str5;
        wXMediaMessage.thumbData = a(bitmap, false);
        return wXMediaMessage;
    }

    public static void a(Context context, String str, int i, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, a(str), i, "text", asyncCallback);
    }

    public static void a(Context context, @NonNull Bitmap bitmap, int i, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, a(bitmap), i, "img", asyncCallback);
    }

    public static void a(Context context, String str, String str2, String str3, Bitmap bitmap, int i, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, a(str, str2, str3, bitmap), i, "music", asyncCallback);
    }

    public static void b(Context context, String str, String str2, String str3, Bitmap bitmap, int i, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, b(str, str2, str3, bitmap), i, "video", asyncCallback);
    }

    public static void c(Context context, String str, String str2, String str3, Bitmap bitmap, int i, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, c(str, str2, str3, bitmap), i, "webpage", asyncCallback);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, Bitmap bitmap, AsyncCallback<Void, YouPinError> asyncCallback) {
        a(context, a(str, str2, str3, str4, str5, bitmap), 0, "mini", asyncCallback);
    }

    private static String b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        return str + currentTimeMillis;
    }

    private static byte[] a(Bitmap bitmap, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}
