package com.amap.openapi;

import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.network.HttpRequest;
import com.amap.location.common.network.HttpResponse;
import com.amap.location.common.util.b;
import com.miuipub.internal.hybrid.SignUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mishopsdk.util.Constants;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import javax.crypto.Cipher;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f4713a = {48, Constants.TagName.ORDER_TRADE_STATUSES, 48, 13, 6, 9, 42, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ACTIVITY_TOTAL, -9, 13, 1, 1, 1, 5, 0, 3, TarConstants.U, 0, 48, Constants.TagName.BUSINESS_ORDER_TYPE, 2, Constants.TagName.TERMINAL_BACK_CONTENT, 0, -18, 74, Constants.TagName.PAY_ORDER, Constants.TagName.STATION_ID, Constants.TagName.ORDER_BRIEF_INFO, -9, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.DEVICE_MODEL, -44, Constants.TagName.ELECTRONIC_OUT_STATE, Constants.TagName.ELECTRONIC_OUT_SERIAL, Constants.TagName.EUID, -9, 4, -10, -30, 102, -73, Constants.TagName.ORDER_TYPE, 13, Constants.TagName.COMPANY_CODE, -83, Constants.TagName.OPERATION_ID, Constants.TagName.CITY_CODE, 87, -8, Constants.TagName.IMEI, ScriptToolsConst.TagName.TagApdu, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE, Constants.TagName.PAY_CHANNEL_MIN, -49, Constants.TagName.PAY_CHANNEL_NAME, 45, -73, TarConstants.U, 39, -45, -16, Constants.TagName.PREDEPOSIT_STATUS, 34, -36, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.APP_TYPE, Constants.TagName.EUID, Byte.MIN_VALUE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, -15, -31, 23, -7, -21, Constants.TagName.EUID, -127, Constants.TagName.OPERATION_STEP, ScriptToolsConst.TagName.ScriptDown, -23, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.USER_PLATFORM_TYPE, 24, 5, Constants.TagName.BUSINESS_HANDLE_RESULT, 2, 3, 1, 0, 1};

    public static boolean a(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(Operators.DOLLAR_STR)) <= 0) {
            return false;
        }
        try {
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1, str.length());
            if (TextUtils.isEmpty(substring)) {
                return false;
            }
            byte[] a2 = a(b.a(substring, "", ""));
            byte[] b = b(substring2);
            if (a2 != null) {
                if (b != null) {
                    return Arrays.equals(a2, b);
                }
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static byte[] a(String str, byte[] bArr, d dVar) {
        try {
            if (dVar.f() != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("et", Constants.Plugin.PLUGINID_WEBVIEW);
                hashMap.put("Accept-Encoding", "gzip");
                HttpRequest httpRequest = new HttpRequest();
                httpRequest.b = hashMap;
                httpRequest.c = bArr;
                httpRequest.f4591a = str;
                HttpResponse a2 = dVar.f().a(httpRequest);
                if (a2 == null || a2.f4592a != 200) {
                    return null;
                }
                return a2.c;
            }
            ALLog.d("CloudUtils", "网络库为空");
            return null;
        } catch (Throwable unused) {
        }
    }

    private static byte[] a(byte[] bArr) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(f4713a));
            Cipher instance = Cipher.getInstance(SignUtils.b);
            instance.init(2, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private static byte[] b(String str) {
        try {
            return MessageDigest.getInstance("SHA1").digest(str.getBytes());
        } catch (Exception unused) {
            return null;
        }
    }
}
