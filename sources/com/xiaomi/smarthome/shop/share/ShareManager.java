package com.xiaomi.smarthome.shop.share;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.channel.sdk.IShareReq;
import com.xiaomi.channel.sdk.MLImgObj;
import com.xiaomi.channel.sdk.MLShareApiFactory;
import com.xiaomi.channel.sdk.MLShareMessage;
import com.xiaomi.channel.sdk.MLShareReq;
import com.xiaomi.channel.sdk.ShareConstants;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.shop.activity.DeviceShopWeiboShareActivity;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.utils.LogUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22183a = "ShareManager";
    public static final String b = "com.xiaomi.smarthome.action.SHARE_RESULT";
    public static final String c = "result_code";
    public static final String d = "message";
    public static final String e = "extras";
    private static final String[] f = {"com.tencent.mm.ui.tools.ShareToTimeLineUI"};
    private static final String[] g = {"com.tencent.mm.ui.tools.ShareImgUI"};
    private static final String[] h = {"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity", "com.sina.weibo.composerinde.ComposerDispatchActivity"};
    private static IWXAPI i;

    public static IWXAPI a() {
        if (i == null) {
            synchronized (ShareManager.class) {
                if (i == null) {
                    i = WXAPIFactory.createWXAPI(XmPluginHostApi.instance().context(), GlobalSetting.e, true);
                    i.registerApp(GlobalSetting.e);
                }
            }
        }
        return i;
    }

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

    public static boolean b(@NonNull Context context, @NonNull ShareObject shareObject) {
        try {
            Bitmap a2 = a(shareObject.k());
            MLShareApiFactory mLShareApiFactory = new MLShareApiFactory(context);
            mLShareApiFactory.a(context.getPackageName(), context.getString(R.string.app_name));
            MLShareMessage mLShareMessage = new MLShareMessage();
            mLShareMessage.c = shareObject.f();
            mLShareMessage.b = shareObject.g();
            mLShareMessage.f10070a = shareObject.i();
            mLShareMessage.d = new MLImgObj(a2);
            mLShareApiFactory.a((IShareReq) new MLShareReq(mLShareMessage, ShareConstants.N), false);
            return true;
        } catch (OutOfMemoryError unused) {
            Log.w(f22183a, "bitmap decode failed!");
            return false;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean c(@NonNull Context context, @NonNull ShareObject shareObject) {
        WXMediaMessage wXMediaMessage;
        Intent intent;
        if (TextUtils.equals(shareObject.n(), "more") && !shareObject.m().isEmpty()) {
            if (shareObject.m().size() > 1) {
                intent = a(g, "android.intent.action.SEND_MULTIPLE");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                intent.setFlags(268435457);
            } else {
                intent = a(g, "android.intent.action.SEND");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                intent.setFlags(268435457);
            }
            context.startActivity(intent);
        } else if (!TextUtils.equals(shareObject.n(), "one") || shareObject.l() == null) {
            try {
                Bitmap a2 = a(shareObject.k());
                IWXAPI a3 = a();
                String a4 = shareObject.a();
                if (!TextUtils.isEmpty(a4)) {
                    WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
                    wXMiniProgramObject.webpageUrl = shareObject.i();
                    wXMiniProgramObject.userName = GlobalSetting.f;
                    wXMiniProgramObject.path = a4;
                    wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
                    wXMediaMessage.title = shareObject.f();
                    wXMediaMessage.description = shareObject.g();
                    wXMediaMessage.setThumbImage(a2);
                } else {
                    wXMediaMessage = new WXMediaMessage();
                    wXMediaMessage.title = shareObject.f();
                    wXMediaMessage.description = shareObject.g();
                    wXMediaMessage.setThumbImage(a2);
                    wXMediaMessage.mediaObject = new WXWebpageObject(shareObject.i());
                }
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = wXMediaMessage;
                req.scene = 0;
                boolean sendReq = a3.sendReq(req);
                String str = f22183a;
                LogUtils.b(str, "sendReq return " + sendReq);
            } catch (OutOfMemoryError unused) {
                Log.w(f22183a, "bitmap decode failed!");
                return false;
            } catch (Exception unused2) {
                return false;
            }
        } else {
            Intent a5 = a(g, "android.intent.action.SEND");
            if (a5 == null) {
                ToastUtil.a((int) R.string.share_score_share_no_install);
                return false;
            }
            a5.putExtra("android.intent.extra.STREAM", shareObject.l());
            a5.setFlags(268435457);
            context.startActivity(a5);
        }
        return true;
    }

    public static boolean d(@NonNull Context context, @NonNull ShareObject shareObject) {
        Intent intent;
        if (TextUtils.equals(shareObject.p(), "more") && !shareObject.m().isEmpty()) {
            if (shareObject.m().size() > 1) {
                intent = a(f, "android.intent.action.SEND_MULTIPLE");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                intent.setFlags(268435457);
            } else {
                intent = a(f, "android.intent.action.SEND");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                intent.setFlags(268435457);
            }
            context.startActivity(intent);
        } else if (!TextUtils.equals(shareObject.p(), "one") || shareObject.l() == null) {
            try {
                Bitmap a2 = a(shareObject.k());
                IWXAPI a3 = a();
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
                wXMediaMessage.setThumbImage(a2);
                wXMediaMessage.mediaObject = new WXWebpageObject(shareObject.i());
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = wXMediaMessage;
                req.scene = 1;
                boolean sendReq = a3.sendReq(req);
                String str = f22183a;
                LogUtils.b(str, "sendReq return " + sendReq);
            } catch (OutOfMemoryError unused) {
                Log.w(f22183a, "bitmap decode failed!");
                return false;
            } catch (Exception unused2) {
                return false;
            }
        } else {
            Intent a4 = a(f, "android.intent.action.SEND");
            if (a4 == null) {
                ToastUtil.a((int) R.string.share_score_share_no_install);
                return false;
            }
            a4.putExtra("android.intent.extra.STREAM", shareObject.l());
            a4.setFlags(268435457);
            context.startActivity(a4);
        }
        return true;
    }

    public static boolean a(@NonNull Context context, @NonNull ShareObject shareObject, boolean z) {
        Intent intent;
        if (!TextUtils.equals(shareObject.q(), "more") || shareObject.m().isEmpty()) {
            Intent intent2 = new Intent(context, DeviceShopWeiboShareActivity.class);
            intent2.putExtra("share", shareObject);
            intent2.putExtra("extra_handle_result", z);
            intent2.putExtra(DeviceShopWeiboShareActivity.KEY_CALLER, DeviceShopWeiboShareActivity.DATA_CALLER_SMARTHOME);
            intent2.setFlags(268435457);
            context.startActivity(intent2);
        } else {
            if (shareObject.m().size() > 1) {
                intent = a(h, "android.intent.action.SEND_MULTIPLE");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", shareObject.m());
                intent.setFlags(268435457);
            } else {
                intent = a(h, "android.intent.action.SEND");
                if (intent == null) {
                    ToastUtil.a((int) R.string.share_score_share_no_install);
                    return false;
                }
                intent.putExtra("android.intent.extra.STREAM", shareObject.m().get(0));
                intent.setFlags(268435457);
            }
            context.startActivity(intent);
        }
        return true;
    }

    private static Intent a(String[] strArr, String str) {
        Intent intent = new Intent();
        intent.setAction(str).setType(ShareObject.d);
        List<ResolveInfo> queryIntentActivities = XmPluginHostApi.instance().application().getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            return null;
        }
        for (ResolveInfo next : queryIntentActivities) {
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (next.activityInfo.name.contains(strArr[i2])) {
                        Intent intent2 = new Intent(str);
                        intent2.setType(ShareObject.d);
                        intent2.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                        intent2.addFlags(268435457);
                        return intent2;
                    }
                    i2++;
                }
            }
        }
        return null;
    }

    public static Bitmap a(Uri uri) throws OutOfMemoryError {
        if (uri == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(new File(uri.getPath()).getAbsolutePath(), options);
    }
}
