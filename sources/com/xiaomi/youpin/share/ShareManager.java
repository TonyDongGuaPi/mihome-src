package com.xiaomi.youpin.share;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.youpin.common.util.ImageUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.share.ui.WeiboShareActivity;
import com.xiaomiyoupin.toast.YPDToast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23668a = "ShareManager";
    private static final String[] b = {"com.tencent.mm.ui.tools.ShareToTimeLineUI"};
    private static final String[] c = {"com.tencent.mm.ui.tools.ShareImgUI"};
    private static final String[] d = {"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity", "com.sina.weibo.composerinde.ComposerDispatchActivity"};

    public static boolean a(@NonNull Context context, @NonNull ShareObject shareObject) {
        Intent intent;
        if (!shareObject.m().isEmpty()) {
            if (shareObject.m().size() > 1) {
                intent = new Intent("android.intent.action.SEND_MULTIPLE");
            } else {
                intent = new Intent("android.intent.action.SEND");
            }
            intent.setType(ShareObject.d);
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
            intent.setFlags(268435457);
            Intent createChooser = Intent.createChooser(intent, "share");
            createChooser.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(createChooser);
        } else if (shareObject.l() != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(shareObject.l());
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType(ShareObject.d);
            intent2.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            intent2.setFlags(268435457);
            Intent createChooser2 = Intent.createChooser(intent2, "share");
            createChooser2.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(createChooser2);
        } else {
            Intent intent3 = new Intent("android.intent.action.SEND");
            intent3.setType("text/plain");
            intent3.setFlags(268435457);
            intent3.putExtra("android.intent.extra.SUBJECT", shareObject.f());
            intent3.putExtra("android.intent.extra.TEXT", shareObject.g());
            Intent createChooser3 = Intent.createChooser(intent3, "share");
            createChooser3.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(createChooser3);
        }
        return true;
    }

    public static boolean b(@NonNull Context context, ShareObject shareObject) {
        Intent intent;
        if (!ShareUtil.a()) {
            YPDToast.getInstance().toast(context, R.string.device_shop_share_no_weixin);
            return false;
        } else if (shareObject == null) {
            return false;
        } else {
            if (TextUtils.equals(shareObject.n(), "more") && !shareObject.m().isEmpty()) {
                if (shareObject.m().size() > 1) {
                    intent = a(context, c, "android.intent.action.SEND_MULTIPLE", shareObject.r());
                    if (intent == null) {
                        YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                        return false;
                    }
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                    intent.setFlags(268435457);
                } else {
                    intent = a(context, c, "android.intent.action.SEND", shareObject.r());
                    if (intent == null) {
                        YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                        return false;
                    }
                    intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                    intent.setFlags(268435457);
                }
                context.startActivity(intent);
            } else if (!TextUtils.equals(shareObject.n(), "one") || shareObject.l() == null) {
                try {
                    if (TextUtils.equals(shareObject.n(), "pic")) {
                        Bitmap a2 = a(shareObject.l());
                        if (a2 == null) {
                            YPDToast.getInstance().toast(context, R.string.device_shop_share_failure);
                            return false;
                        }
                        Bitmap f = ImageUtils.f(a2, 300);
                        if (f == null) {
                            f = a(context);
                        }
                        WXMediaMessage wXMediaMessage = new WXMediaMessage();
                        wXMediaMessage.title = shareObject.f();
                        wXMediaMessage.description = shareObject.g();
                        if (f != null) {
                            wXMediaMessage.setThumbImage(f);
                        }
                        wXMediaMessage.mediaObject = new WXImageObject(a2);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = wXMediaMessage;
                        req.scene = 0;
                        boolean sendReq = YouPinShareApi.a().b().sendReq(req);
                        String str = f23668a;
                        LogUtils.d(str, "sendReq return " + sendReq);
                    } else {
                        String a3 = shareObject.a();
                        if (!TextUtils.isEmpty(a3)) {
                            Uri l = shareObject.l();
                            if (l == null) {
                                l = shareObject.k();
                            }
                            Bitmap a4 = a(l);
                            if (a4 != null && a4.getWidth() > 600) {
                                a4 = ImageUtils.f(a4, 600);
                            }
                            if (a4 == null) {
                                a4 = a(context);
                            }
                            WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
                            wXMiniProgramObject.webpageUrl = shareObject.i();
                            wXMiniProgramObject.userName = GlobalSetting.f;
                            if (!TextUtils.isEmpty(shareObject.s())) {
                                wXMiniProgramObject.userName = shareObject.s();
                            }
                            wXMiniProgramObject.path = a3;
                            WXMediaMessage wXMediaMessage2 = new WXMediaMessage(wXMiniProgramObject);
                            wXMediaMessage2.title = shareObject.f();
                            wXMediaMessage2.description = shareObject.g();
                            wXMediaMessage2.setThumbImage(a4);
                            SendMessageToWX.Req req2 = new SendMessageToWX.Req();
                            req2.transaction = String.valueOf(System.currentTimeMillis());
                            req2.message = wXMediaMessage2;
                            req2.scene = 0;
                            boolean sendReq2 = YouPinShareApi.a().b().sendReq(req2);
                            String str2 = f23668a;
                            LogUtils.d(str2, "sendReq return " + sendReq2);
                        } else {
                            Bitmap a5 = a(shareObject.k());
                            if (a5 != null && a5.getWidth() > 300) {
                                a5 = ImageUtils.f(a5, 150);
                            }
                            if (a5 == null) {
                                a5 = a(context);
                            }
                            WXMediaMessage wXMediaMessage3 = new WXMediaMessage();
                            wXMediaMessage3.title = shareObject.f();
                            wXMediaMessage3.description = shareObject.g();
                            wXMediaMessage3.setThumbImage(a5);
                            wXMediaMessage3.mediaObject = new WXWebpageObject(shareObject.i());
                            SendMessageToWX.Req req3 = new SendMessageToWX.Req();
                            req3.transaction = String.valueOf(System.currentTimeMillis());
                            req3.message = wXMediaMessage3;
                            req3.scene = 0;
                            boolean sendReq3 = YouPinShareApi.a().b().sendReq(req3);
                            String str3 = f23668a;
                            LogUtils.d(str3, "sendReq return " + sendReq3);
                        }
                    }
                } catch (OutOfMemoryError unused) {
                    LogUtils.w(f23668a, "bitmap decode failed!");
                    return false;
                } catch (Exception unused2) {
                    return false;
                }
            } else {
                Intent a6 = a(context, c, "android.intent.action.SEND", shareObject.r());
                if (a6 == null) {
                    YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                    return false;
                }
                a6.putExtra("android.intent.extra.STREAM", shareObject.l());
                a6.setFlags(268435457);
                context.startActivity(a6);
            }
            return true;
        }
    }

    public static Bitmap a(Context context) {
        Drawable drawable;
        if (context == null || (drawable = context.getResources().getDrawable(R.drawable.youpin)) == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static boolean c(@NonNull Context context, @NonNull ShareObject shareObject) {
        Intent intent;
        if (!ShareUtil.a()) {
            YPDToast.getInstance().toast(context, R.string.device_shop_share_no_weixin);
            return false;
        } else if (shareObject == null) {
            YPDToast.getInstance().toast(context, R.string.device_shop_share_failure);
            return false;
        } else {
            if (TextUtils.equals(shareObject.p(), "more") && !shareObject.m().isEmpty()) {
                if (shareObject.m().size() > 1) {
                    intent = a(context, b, "android.intent.action.SEND_MULTIPLE", shareObject.r());
                    if (intent == null) {
                        YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                        return false;
                    }
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                    intent.setFlags(268435457);
                } else {
                    intent = a(context, b, "android.intent.action.SEND", shareObject.r());
                    if (intent == null) {
                        YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                        return false;
                    }
                    intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                    intent.setFlags(268435457);
                }
                context.startActivity(intent);
            } else if (!TextUtils.equals(shareObject.p(), "one") || shareObject.l() == null) {
                try {
                    WXMediaMessage wXMediaMessage = new WXMediaMessage();
                    wXMediaMessage.title = shareObject.f();
                    wXMediaMessage.description = shareObject.g();
                    if (!TextUtils.isEmpty(shareObject.b())) {
                        wXMediaMessage.title = shareObject.b();
                    }
                    if (!TextUtils.isEmpty(shareObject.c())) {
                        wXMediaMessage.description = shareObject.c();
                    }
                    if (!TextUtils.isEmpty(wXMediaMessage.title)) {
                        wXMediaMessage.title = "【" + wXMediaMessage.title + "】" + wXMediaMessage.description;
                    } else {
                        wXMediaMessage.title = wXMediaMessage.description;
                    }
                    if (TextUtils.equals(shareObject.n(), "pic")) {
                        Bitmap a2 = a(shareObject.l());
                        if (a2 == null) {
                            YPDToast.getInstance().toast(context, R.string.device_shop_share_failure);
                            return false;
                        }
                        Bitmap f = ImageUtils.f(a2, 150);
                        if (f == null) {
                            f = a(context);
                        }
                        if (f != null) {
                            wXMediaMessage.setThumbImage(f);
                        }
                        wXMediaMessage.mediaObject = new WXImageObject(a2);
                    } else {
                        Bitmap a3 = a(shareObject.k());
                        if (a3 != null && a3.getWidth() > 300) {
                            a3 = ImageUtils.f(a3, 150);
                        }
                        if (a3 == null) {
                            a3 = a(context);
                        }
                        wXMediaMessage.setThumbImage(a3);
                        wXMediaMessage.mediaObject = new WXWebpageObject(shareObject.i());
                    }
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wXMediaMessage;
                    req.scene = 1;
                    boolean sendReq = YouPinShareApi.a().b().sendReq(req);
                    String str = f23668a;
                    LogUtils.d(str, "sendReq return " + sendReq);
                } catch (OutOfMemoryError unused) {
                    LogUtils.w(f23668a, "bitmap decode failed!");
                    return false;
                } catch (Exception unused2) {
                    return false;
                }
            } else {
                Intent a4 = a(context, b, "android.intent.action.SEND", shareObject.r());
                if (a4 == null) {
                    YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                    return false;
                }
                a4.putExtra("android.intent.extra.STREAM", shareObject.l());
                a4.setFlags(268435457);
                context.startActivity(a4);
            }
            return true;
        }
    }

    public static boolean a(@NonNull Context context, @NonNull ShareObject shareObject, boolean z) {
        Intent intent;
        if (shareObject == null) {
            YPDToast.getInstance().toast(context, R.string.device_shop_share_failure);
            return false;
        }
        if (!TextUtils.equals(shareObject.q(), "more") || shareObject.m().isEmpty()) {
            Intent intent2 = new Intent(context, WeiboShareActivity.class);
            intent2.putExtra("share", shareObject);
            intent2.putExtra("extra_handle_result", z);
            intent2.setFlags(268435457);
            context.startActivity(intent2);
        } else {
            if (shareObject.m().size() > 1) {
                intent = a(context, d, "android.intent.action.SEND_MULTIPLE", shareObject.r());
                if (intent == null) {
                    YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                    return false;
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                intent.setFlags(268435457);
            } else {
                intent = a(context, d, "android.intent.action.SEND", shareObject.r());
                if (intent == null) {
                    YPDToast.getInstance().toast(context, R.string.share_score_share_no_install);
                    return false;
                }
                intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                intent.setFlags(268435457);
            }
            context.startActivity(intent);
        }
        return true;
    }

    private static Intent a(Context context, String[] strArr, String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(str).setType(str2);
        List<ResolveInfo> queryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            return null;
        }
        for (ResolveInfo next : queryIntentActivities) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (next.activityInfo.name.contains(strArr[i])) {
                        Intent intent2 = new Intent(str);
                        intent2.setType(str2);
                        intent2.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                        intent2.addFlags(268435457);
                        return intent2;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    @Nullable
    public static Bitmap a(Uri uri) throws OutOfMemoryError {
        if (uri == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        return BitmapFactory.decodeFile(new File(uri.getPath()).getAbsolutePath(), options);
    }
}
