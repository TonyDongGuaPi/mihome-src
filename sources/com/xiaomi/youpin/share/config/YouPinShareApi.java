package com.xiaomi.youpin.share.config;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareRouter;
import com.xiaomi.youpin.share.ShareUtil;
import com.xiaomi.youpin.share.WechatShareUtil;
import com.xiaomi.youpin.share.model.PosterData;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.util.Map;

public class YouPinShareApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1589a = "YouPinShareApi";
    private static ShareConfig b;

    public interface Callback {
        void a(int i, String str);
    }

    public static void a(ShareConfig shareConfig) {
        b = shareConfig;
    }

    public static ShareConfig a() {
        return b;
    }

    public static void a(Map<String, Object> map, final Callback callback, Activity activity) {
        String str = (String) map.get("url");
        Context e = b.e();
        ShareEventUtil.c(e, new BroadcastReceiver() {

            /* renamed from: a  reason: collision with root package name */
            boolean f23678a = false;

            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction() != null) {
                    String action = intent.getAction();
                    if (((action.hashCode() == 109400031 && action.equals("share")) ? (char) 0 : 65535) == 0 && !this.f23678a) {
                        this.f23678a = true;
                        ShareEventUtil.d(context, this);
                        boolean a2 = ShareEventUtil.a(intent);
                        String e = ShareEventUtil.e(intent);
                        LogUtils.d(YouPinShareApi.f1589a, "share onReceive isSuccess " + a2 + " channel " + e);
                        if (!a2) {
                            int b2 = ShareEventUtil.b(intent);
                            ShareEventUtil.c(intent);
                            if (callback == null) {
                                return;
                            }
                            if (b2 == -100) {
                                callback.a(-100, e);
                            } else {
                                callback.a(-1, e);
                            }
                        } else if (callback != null) {
                            callback.a(0, e);
                        }
                    }
                }
            }
        });
        boolean z = false;
        if (map.containsKey("poster")) {
            PosterData posterData = new PosterData();
            Map map2 = (Map) map.get("poster");
            posterData.f23682a = (String) map2.get("title");
            posterData.b = (String) map2.get("desc");
            posterData.c = (String) map2.get("img");
            posterData.d = Double.valueOf(String.valueOf(map2.get("price"))).intValue();
            if (Double.valueOf(String.valueOf(map2.get("priceMore"))).intValue() == 1) {
                z = true;
            }
            posterData.e = z;
            posterData.f = (String) map2.get("tag");
            if (posterData.a()) {
                ShareRouter.a(e, str, posterData);
            } else if (callback != null) {
                callback.a(-2, "");
            }
        } else {
            ShareRouter.a(e, str, false);
        }
    }

    public static void a(Activity activity, String str) {
        ShareRouter.a(b.e(), str, false);
    }

    public static void a(Context context, boolean z, String str, int i, String str2) {
        ShareEventUtil.a(context, z, str, i, str2);
    }

    public static void a(final Context context, final String str, String str2, @NonNull final Callback callback) {
        if (!ShareChannel.b.equals(str) && !ShareChannel.f23683a.equals(str)) {
            callback.a(-2, "unsupport channel");
        } else if (TextUtils.isEmpty(str2)) {
            callback.a(-2, "image empty");
        } else if (str2.startsWith("data:")) {
            try {
                Bitmap a2 = a(str2);
                if (a2 == null) {
                    callback.a(-1, "getBitmapFromBase64 fail");
                } else {
                    b(context, a2, str, callback);
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.a(-1, "getBitmapFromBase64 fail");
            }
        } else if (str2.startsWith(ConnectionHelper.HTTP_PREFIX) || str2.startsWith("https://")) {
            ShareUtil.a(str2, (AsyncCallback<Bitmap, YouPinError>) new AsyncCallback<Bitmap, YouPinError>() {
                public void a(Bitmap bitmap) {
                    YouPinShareApi.b(context, bitmap, str, callback);
                }

                public void a(YouPinError youPinError) {
                    callback.a(youPinError.b(), str);
                }
            });
        } else {
            Uri a3 = ShareUtil.a(context, Uri.parse(str2));
            if (a3 == null) {
                callback.a(-1, str);
                return;
            }
            Bitmap a4 = ShareManager.a(a3);
            if (a4 == null) {
                callback.a(-2, str);
            } else {
                b(context, a4, str, callback);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, Bitmap bitmap, String str, final Callback callback) {
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (ShareChannel.b.equals(str)) {
            WechatShareUtil.a(context, copy, 0, (AsyncCallback<Void, YouPinError>) new AsyncCallback<Void, YouPinError>() {
                public void a(Void voidR) {
                    callback.a(0, "");
                }

                public void a(YouPinError youPinError) {
                    callback.a(youPinError.b(), youPinError.c());
                }
            });
        } else {
            WechatShareUtil.a(context, copy, 1, (AsyncCallback<Void, YouPinError>) new AsyncCallback<Void, YouPinError>() {
                public void a(Void voidR) {
                    callback.a(0, "");
                }

                public void a(YouPinError youPinError) {
                    callback.a(youPinError.b(), youPinError.c());
                }
            });
        }
    }

    private static Bitmap a(String str) {
        byte[] decode = Base64.decode(b(str), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    private static String b(String str) {
        return str.startsWith("data:") ? str.substring(str.indexOf("base64,") + 7) : str;
    }
}
