package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.sys.a;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.util.Util;
import com.ximalaya.ting.android.opensdk.model.pay.ObfuscatePlayInfo;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.player.XMediaPlayerConstants;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PayUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f2264a = "0123456789abcdef".toCharArray();
    private static String b;
    private static String c;
    private static String d;

    public static void a(Map<String, String> map, final IDataCallBack<String> iDataCallBack, Track track) {
        map.put("track_id", track.a() + "");
        map.put("device", "android");
        CommonRequest.h(map, new IDataCallBack<ObfuscatePlayInfo>() {
            public void a(ObfuscatePlayInfo obfuscatePlayInfo) {
                if (obfuscatePlayInfo == null) {
                    a(IXmPayOrderListener.l, "updateTrackForPlay return result be null");
                    return;
                }
                StringBuilder a2 = PayUtil.b(obfuscatePlayInfo);
                if (a2 == null) {
                    a(IXmPayOrderListener.l, "updateTrackForPlay return result be null");
                } else {
                    iDataCallBack.a(a2.toString());
                }
            }

            public void a(int i, String str) {
                iDataCallBack.a(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public static StringBuilder b(ObfuscatePlayInfo obfuscatePlayInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("file_id", obfuscatePlayInfo.m());
        hashMap.put(DTransferConstants.aN, obfuscatePlayInfo.n());
        hashMap.put("duration", obfuscatePlayInfo.f() + "");
        hashMap.put("api_version", obfuscatePlayInfo.i());
        String a2 = a((Map<String, String>) hashMap);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(a2);
        if (!TextUtils.isEmpty(a2) && obfuscatePlayInfo.q() > 0 && a2.contains("preview")) {
            sb.append("&totalLength=");
            sb.append(obfuscatePlayInfo.q());
        }
        sb.append(a.b);
        sb.append(XMediaPlayerConstants.f);
        sb.append("=true");
        return sb;
    }

    public static String a(long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", j + "");
        hashMap.put("device", "android");
        return b(CommonRequest.b((Map<String, String>) hashMap)).toString();
    }

    private static String a(Map<String, String> map) {
        byte[] bArr;
        String str;
        if (map != null) {
            String remove = map.remove("file_id");
            String remove2 = map.remove(DTransferConstants.aN);
            String remove3 = map.remove(DTransferConstants.aO);
            String remove4 = map.remove("duration");
            String remove5 = map.remove("api_version");
            if (TextUtils.isEmpty(remove)) {
                Logger.a((Object) "getAntiLeechUrl specificParams fileId is null");
                return null;
            }
            try {
                bArr = EncryptUtilForSDK.b().a(Base64.decode(remove, 0));
            } catch (Exception e) {
                e.printStackTrace();
                bArr = null;
            }
            if (bArr == null) {
                return null;
            }
            try {
                str = new String(bArr, "UTF-8").trim();
            } catch (UnsupportedEncodingException unused) {
                if (TextUtils.isEmpty(remove)) {
                    Logger.a((Object) "getAntiLeechUrl specificParams fileId decode fail");
                    return null;
                }
                str = "";
            }
            Logger.a((Object) "encryptStr xxx result:" + str);
            if (TextUtils.isEmpty(remove2)) {
                Logger.a((Object) "getAntiLeechUrl specificParams no ep");
                return null;
            }
            String a2 = EncryptUtilForSDK.b().a(remove2);
            if (TextUtils.isEmpty(a2)) {
                Logger.a((Object) "getAntiLeechUrl specificParams ep decode fail");
                return null;
            }
            String trim = a2.trim();
            if (TextUtils.isEmpty(trim)) {
                Logger.a((Object) "getAntiLeechUrl specificParams ep decode fail");
                return null;
            }
            String[] split = trim.split("-");
            if (split.length != 4) {
                Logger.a((Object) "getAntiLeechUrl specificParams ep decode fail length error deEp:" + trim);
                return null;
            }
            StringBuilder sb = new StringBuilder(ConnectionHelper.HTTP_PREFIX);
            map.clear();
            sb.append("audio");
            map.put("sign", split[1]);
            sb.append(".pay.");
            map.put("buy_key", split[0]);
            sb.append("xmcdn.com/");
            map.put("token", split[2]);
            sb.append("download/");
            map.put("timestamp", split[3]);
            map.put("duration", remove4);
            String str2 = (sb.toString() + remove5 + "/" + str) + "?" + Util.b(Util.c(map));
            Logger.a((Object) "encryptStr url:" + str2);
            return str2;
        } else if (!ConstantsOpenSdk.b) {
            Logger.a((Object) "getAntiLeechUrl specificParams == null(getAntiLeechUrl func)");
            return null;
        } else {
            throw new RuntimeException("getAntiLeechUrl specificParams == null(getAntiLeechUrl func)");
        }
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("pack_id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            String e = CommonRequest.a().e();
            if (!TextUtils.isEmpty(e)) {
                StringBuilder sb = new StringBuilder();
                char[] charArray = e.toCharArray();
                for (int i = 0; i < charArray.length / 2; i++) {
                    sb.append(f2264a[((char) (charArray[i] ^ charArray[(charArray.length - 1) - i])) % 16]);
                }
                b = sb.toString();
            }
        }
        return b;
    }

    public static String b() {
        String a2;
        if (TextUtils.isEmpty(c) && (a2 = a()) != null) {
            c = a2.substring(0, a2.length() / 2) + "0";
        }
        PrintStream printStream = System.out;
        printStream.println("PayUtil.getKey0   " + c);
        return c;
    }

    public static String c() {
        String a2;
        if (TextUtils.isEmpty(d) && (a2 = a()) != null) {
            d = a2.substring(a2.length() / 2) + "1";
        }
        PrintStream printStream = System.out;
        printStream.println("PayUtil.getKey1   " + d);
        return d;
    }
}
