package com.ximalaya.ting.android.opensdk.datatrasfer;

import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.google.gson.Gson;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.BaseResponse;
import com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.httputil.XmSecretKeyUtil;
import com.ximalaya.ting.android.opensdk.model.SercretPubKey;
import com.ximalaya.ting.android.opensdk.util.DHUtil;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.opensdk.util.dh.DhKeyPair;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class AccessTokenBaseCall {

    /* renamed from: a  reason: collision with root package name */
    public static HashSet<String> f1859a = new HashSet<String>() {
        {
            add("/subscribe/get_albums_by_uid");
            add("/subscribe/add_or_delete");
            add("/subscribe/batch_add");
            add("/play_history/get_by_uid");
            add("/play_history/upload");
            add("/play_history/batch_upload");
            add("/play_history/batch_delete");
            add("/open_pay/client_place_order");
            add("/open_pay/get_bought_albums");
            add("/open_pay/album_bought_status");
            add("/open_pay/track_bought_status");
            add("/profile/user_info");
            add("/profile/persona");
        }
    };
    public static final String b = "uid";
    public static final String c = "access_token";
    public static final String d = "expires_in";

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01d4, code lost:
        throw new com.ximalaya.ting.android.opensdk.httputil.XimalayaException(1011, r6.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d5, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01d6, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cb, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0122, code lost:
        if (android.text.TextUtils.isEmpty(r6.getMessage()) != false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0124, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012b, code lost:
        throw com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(1009);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0132, code lost:
        throw com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(1007);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01a1, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ac, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01b7, code lost:
        if (android.text.TextUtils.isEmpty(r6.getMessage()) != false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01ca, code lost:
        throw new com.ximalaya.ting.android.opensdk.httputil.XimalayaException(1011, com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP.get(1011));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01d5 A[ExcHandler: XimalayaException (r6v1 'e' com.ximalaya.ting.android.opensdk.httputil.XimalayaException A[CUSTOM_DECLARE]), Splitter:B:1:0x0003] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:50:0x00d9=Splitter:B:50:0x00d9, B:74:0x015b=Splitter:B:74:0x015b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(okhttp3.Request r6, java.util.Map<java.lang.String, java.lang.String> r7, java.lang.String r8, java.lang.String r9) throws com.ximalaya.ting.android.opensdk.httputil.XimalayaException {
        /*
            c(r9)
            com.ximalaya.ting.android.opensdk.httputil.BaseCall r0 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            okhttp3.Response r0 = r0.a((okhttp3.Request) r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.httputil.BaseResponse r1 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r1.<init>(r0)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            int r2 = r0.code()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 0
            if (r2 != r3) goto L_0x0023
            okhttp3.ResponseBody r6 = r0.body()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r4 = r6.string()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            goto L_0x0178
        L_0x0023:
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r0 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r0 = r0.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r1)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r2 = 1009(0x3f1, float:1.414E-42)
            if (r0 == 0) goto L_0x01a2
            java.lang.String r5 = r0.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r5 != 0) goto L_0x01a2
            int r1 = r0.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r5 = 701(0x2bd, float:9.82E-43)
            if (r1 == r5) goto L_0x0049
            int r1 = r0.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r5 = 702(0x2be, float:9.84E-43)
            if (r1 != r5) goto L_0x006b
        L_0x0049:
            okhttp3.HttpUrl r1 = r6.url()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r1 = r1.toString()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r5 = "http://api.ximalaya.com/openapi-gateway-app/encrypt/exchange"
            boolean r1 = r1.contains(r5)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r1 != 0) goto L_0x0133
            okhttp3.HttpUrl r1 = r6.url()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r1 = r1.toString()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r5 = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_play_info"
            boolean r1 = r1.contains(r5)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r1 == 0) goto L_0x006b
            goto L_0x0133
        L_0x006b:
            int r0 = r0.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r1 = 206(0xce, float:2.89E-43)
            if (r0 != r1) goto L_0x0178
            r0 = 0
            boolean r1 = c()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007e
        L_0x0079:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r1 = 0
        L_0x007e:
            if (r1 != 0) goto L_0x00b2
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r1 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest$ITokenStateChange r1 = r1.b()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r1 == 0) goto L_0x008e
            boolean r0 = r1.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x008e:
            if (r1 == 0) goto L_0x00a3
            if (r0 != 0) goto L_0x00a3
            boolean r4 = b(r9)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r4 != 0) goto L_0x0099
            goto L_0x00a3
        L_0x0099:
            r1.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r6 = 1013(0x3f5, float:1.42E-42)
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x00a3:
            if (r0 != 0) goto L_0x00b2
            boolean r0 = com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager.i()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r0 == 0) goto L_0x00af
            com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager.k()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            goto L_0x00b2
        L_0x00af:
            d()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x00b2:
            java.lang.String r0 = "POST"
            java.lang.String r6 = r6.method()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r6 = r0.equals(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 == 0) goto L_0x00cd
            java.util.Map r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
            okhttp3.Request$Builder r6 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.b((java.lang.String) r9, (java.util.Map<java.lang.String, java.lang.String>) r6, (java.lang.String) r8)     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
            okhttp3.Request r6 = r6.build()     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
            goto L_0x00d9
        L_0x00cb:
            r6 = move-exception
            goto L_0x011a
        L_0x00cd:
            java.util.Map r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
            okhttp3.Request$Builder r6 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.a((java.lang.String) r9, (java.util.Map<java.lang.String, java.lang.String>) r6, (java.lang.String) r8)     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
            okhttp3.Request r6 = r6.build()     // Catch:{ Exception -> 0x00cb, XimalayaException -> 0x01d5 }
        L_0x00d9:
            com.ximalaya.ting.android.opensdk.httputil.BaseCall r7 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            okhttp3.Response r6 = r7.a((okhttp3.Request) r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.httputil.BaseResponse r7 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r7.<init>(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            int r6 = r7.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 != r3) goto L_0x00f2
            java.lang.String r4 = r7.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            goto L_0x0178
        L_0x00f2:
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r6 = r6.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 == 0) goto L_0x0115
            java.lang.String r7 = r6.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r7 == 0) goto L_0x0107
            goto L_0x0115
        L_0x0107:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r7 = new com.ximalaya.ting.android.opensdk.httputil.XimalayaException     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            int r8 = r6.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r6 = r6.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r7.<init>(r8, r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r7     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x0115:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(r2)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x011a:
            java.lang.String r7 = r6.getMessage()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r7 == 0) goto L_0x012c
            r6.printStackTrace()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(r2)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x012c:
            r6 = 1007(0x3ef, float:1.411E-42)
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x0133:
            b(r6, r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r0 = "POST"
            java.lang.String r6 = r6.method()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r6 = r0.equals(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 == 0) goto L_0x014f
            java.util.Map r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
            okhttp3.Request$Builder r6 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.b((java.lang.String) r9, (java.util.Map<java.lang.String, java.lang.String>) r6, (java.lang.String) r8)     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
            okhttp3.Request r6 = r6.build()     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
            goto L_0x015b
        L_0x014f:
            java.util.Map r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
            okhttp3.Request$Builder r6 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.a((java.lang.String) r9, (java.util.Map<java.lang.String, java.lang.String>) r6, (java.lang.String) r8)     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
            okhttp3.Request r6 = r6.build()     // Catch:{ Exception -> 0x01a1, XimalayaException -> 0x01d5 }
        L_0x015b:
            com.ximalaya.ting.android.opensdk.httputil.BaseCall r7 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()     // Catch:{ Exception -> 0x0164, XimalayaException -> 0x01d5 }
            okhttp3.Response r6 = r7.a((okhttp3.Request) r6)     // Catch:{ Exception -> 0x0164, XimalayaException -> 0x01d5 }
            goto L_0x0169
        L_0x0164:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r6 = r4
        L_0x0169:
            com.ximalaya.ting.android.opensdk.httputil.BaseResponse r7 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r7.<init>(r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            int r6 = r7.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 != r3) goto L_0x0179
            java.lang.String r4 = r7.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x0178:
            return r4
        L_0x0179:
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r6 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r6 = r6.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r6 == 0) goto L_0x019c
            java.lang.String r7 = r6.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            if (r7 == 0) goto L_0x018e
            goto L_0x019c
        L_0x018e:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r7 = new com.ximalaya.ting.android.opensdk.httputil.XimalayaException     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            int r8 = r6.a()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r6 = r6.c()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r7.<init>(r8, r6)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r7     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x019c:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.getExceptionByCode(r2)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x01a1:
            return r4
        L_0x01a2:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = new com.ximalaya.ting.android.opensdk.httputil.XimalayaException     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            java.lang.String r7 = r1.toString()     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            r6.<init>(r2, r7)     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
            throw r6     // Catch:{ XimalayaException -> 0x01d5, Exception -> 0x01ac }
        L_0x01ac:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            r8 = 1011(0x3f3, float:1.417E-42)
            if (r7 == 0) goto L_0x01cb
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r6 = new com.ximalaya.ting.android.opensdk.httputil.XimalayaException
            java.util.Map<java.lang.Integer, java.lang.String> r7 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            java.lang.Object r7 = r7.get(r9)
            java.lang.String r7 = (java.lang.String) r7
            r6.<init>(r8, r7)
            throw r6
        L_0x01cb:
            com.ximalaya.ting.android.opensdk.httputil.XimalayaException r7 = new com.ximalaya.ting.android.opensdk.httputil.XimalayaException
            java.lang.String r6 = r6.getMessage()
            r7.<init>(r8, r6)
            throw r7
        L_0x01d5:
            r6 = move-exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.a(okhttp3.Request, java.util.Map, java.lang.String, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static void b(Request request, Map<String, String> map) {
        XmSecretKeyUtil.a().b();
        if (request.url().toString().contains(DTransferConstants.cX)) {
            int i = 3;
            while (i > 0) {
                try {
                    final DhKeyPair a2 = DHUtil.a();
                    Response a3 = BaseCall.a().a(BaseBuilder.b(DTransferConstants.cY, CommonRequest.a((Map<String, String>) new HashMap<String, String>() {
                        {
                            put(DTransferConstants.aV, a2.getPublicKey().toString());
                        }
                    }), CommonRequest.a().e()).build());
                    if (new BaseResponse(a3).a() == 200) {
                        XmSecretKeyUtil.a().a((SercretPubKey) new Gson().fromJson(a3.body().string(), SercretPubKey.class));
                        i = 0;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception unused) {
                    i--;
                }
            }
            return;
        }
        map.put(DTransferConstants.aV, DHUtil.a().getPublicKey().toString());
    }

    public static void a(Request request, IHttpCallBack iHttpCallBack, Map<String, String> map, String str, String str2) {
        try {
            c(str2);
            final IHttpCallBack iHttpCallBack2 = iHttpCallBack;
            final Request request2 = request;
            final Map<String, String> map2 = map;
            final String str3 = str2;
            final String str4 = str;
            BaseCall.a().a(request, (IHttpCallBack) new IHttpCallBack() {
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
                    r7 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
                    r7 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:60:0x015f, code lost:
                    if (android.text.TextUtils.isEmpty(r7.getMessage()) != false) goto L_0x0161;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:61:0x0161, code lost:
                    r2.a(1011, com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP.get(1011));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:62:0x0173, code lost:
                    r2.a(1011, r7.getMessage());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:63:0x017d, code lost:
                    r2.a(r7.getErrorCode(), r7.getErrorMessage());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:21:0x0075 A[ExcHandler: XimalayaException (r7v25 'e' com.ximalaya.ting.android.opensdk.httputil.XimalayaException A[CUSTOM_DECLARE]), Splitter:B:23:0x0079] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a(okhttp3.Response r7) {
                    /*
                        r6 = this;
                        com.ximalaya.ting.android.opensdk.httputil.BaseResponse r0 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse
                        r0.<init>(r7)
                        int r1 = r0.a()
                        r2 = 200(0xc8, float:2.8E-43)
                        if (r1 != r2) goto L_0x0014
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2
                        r0.a(r7)
                        goto L_0x0259
                    L_0x0014:
                        com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r7 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()
                        com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r7 = r7.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r0)
                        r0 = 1009(0x3f1, float:1.414E-42)
                        if (r7 == 0) goto L_0x0248
                        java.lang.String r1 = r7.c()
                        boolean r1 = android.text.TextUtils.isEmpty(r1)
                        if (r1 == 0) goto L_0x002c
                        goto L_0x0248
                    L_0x002c:
                        int r1 = r7.a()
                        r3 = 701(0x2bd, float:9.82E-43)
                        r4 = 1002(0x3ea, float:1.404E-42)
                        if (r1 == r3) goto L_0x003e
                        int r1 = r7.a()
                        r3 = 702(0x2be, float:9.84E-43)
                        if (r1 != r3) goto L_0x0064
                    L_0x003e:
                        okhttp3.Request r1 = r3
                        okhttp3.HttpUrl r1 = r1.url()
                        java.lang.String r1 = r1.toString()
                        java.lang.String r3 = "http://api.ximalaya.com/openapi-gateway-app/encrypt/exchange"
                        boolean r1 = r1.contains(r3)
                        if (r1 != 0) goto L_0x01a7
                        okhttp3.Request r1 = r3
                        okhttp3.HttpUrl r1 = r1.url()
                        java.lang.String r1 = r1.toString()
                        java.lang.String r3 = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_play_info"
                        boolean r1 = r1.contains(r3)
                        if (r1 == 0) goto L_0x0064
                        goto L_0x01a7
                    L_0x0064:
                        int r1 = r7.a()
                        r3 = 206(0xce, float:2.89E-43)
                        if (r1 != r3) goto L_0x018c
                        r7 = 0
                        boolean r1 = com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.c()     // Catch:{ IOException -> 0x0078 }
                        goto L_0x007d
                    L_0x0072:
                        r7 = move-exception
                        goto L_0x0155
                    L_0x0075:
                        r7 = move-exception
                        goto L_0x017d
                    L_0x0078:
                        r1 = move-exception
                        r1.printStackTrace()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r1 = 0
                    L_0x007d:
                        if (r1 != 0) goto L_0x00bf
                        com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r1 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest$ITokenStateChange r1 = r1.b()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r1 == 0) goto L_0x008d
                        boolean r7 = r1.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                    L_0x008d:
                        if (r1 == 0) goto L_0x00b0
                        if (r7 != 0) goto L_0x00b0
                        java.lang.String r3 = r5     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        boolean r3 = com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.b(r3)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r3 == 0) goto L_0x00b0
                        r1.c()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.util.Map<java.lang.Integer, java.lang.String> r0 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r1 = 1013(0x3f5, float:1.42E-42)
                        java.lang.Integer r2 = java.lang.Integer.valueOf(r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.Object r0 = r0.get(r2)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.String r0 = (java.lang.String) r0     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r7.a(r1, r0)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        return
                    L_0x00b0:
                        if (r7 != 0) goto L_0x00bf
                        boolean r7 = com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager.i()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r7 == 0) goto L_0x00bc
                        com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager.k()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        goto L_0x00bf
                    L_0x00bc:
                        com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.d()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                    L_0x00bf:
                        java.lang.String r7 = "POST"
                        okhttp3.Request r1 = r3     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.String r1 = r1.method()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        boolean r7 = r7.equals(r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r7 == 0) goto L_0x00e0
                        java.lang.String r7 = r5     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.util.Map r1 = r4     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.util.Map r1 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r1)     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.lang.String r3 = r6     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        okhttp3.Request$Builder r7 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.b((java.lang.String) r7, (java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r3)     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        okhttp3.Request r7 = r7.build()     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        goto L_0x00f2
                    L_0x00e0:
                        java.lang.String r7 = r5     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.util.Map r1 = r4     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.util.Map r1 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r1)     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        java.lang.String r3 = r6     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        okhttp3.Request$Builder r7 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.a((java.lang.String) r7, (java.util.Map<java.lang.String, java.lang.String>) r1, (java.lang.String) r3)     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                        okhttp3.Request r7 = r7.build()     // Catch:{ Exception -> 0x0143, XimalayaException -> 0x0075 }
                    L_0x00f2:
                        com.ximalaya.ting.android.opensdk.httputil.BaseCall r1 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        okhttp3.Response r7 = r1.a((okhttp3.Request) r7)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        com.ximalaya.ting.android.opensdk.httputil.BaseResponse r1 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r1.<init>(r7)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        int r3 = r1.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r3 != r2) goto L_0x010c
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r0.a(r7)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        goto L_0x0259
                    L_0x010c:
                        com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r7 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r7 = r7.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r7 == 0) goto L_0x0130
                        java.lang.String r1 = r7.c()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        if (r1 == 0) goto L_0x0121
                        goto L_0x0130
                    L_0x0121:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        int r1 = r7.a()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.String r7 = r7.c()     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r0.a(r1, r7)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        goto L_0x0259
                    L_0x0130:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.util.Map<java.lang.Integer, java.lang.String> r1 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.Object r1 = r1.get(r2)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.String r1 = (java.lang.String) r1     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r7.a(r0, r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        goto L_0x0259
                    L_0x0143:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.util.Map<java.lang.Integer, java.lang.String> r0 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.Integer r1 = java.lang.Integer.valueOf(r4)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.Object r0 = r0.get(r1)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        java.lang.String r0 = (java.lang.String) r0     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        r7.a(r4, r0)     // Catch:{ XimalayaException -> 0x0075, Exception -> 0x0072 }
                        return
                    L_0x0155:
                        java.lang.String r0 = r7.getMessage()
                        boolean r0 = android.text.TextUtils.isEmpty(r0)
                        r1 = 1011(0x3f3, float:1.417E-42)
                        if (r0 == 0) goto L_0x0173
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2
                        java.util.Map<java.lang.Integer, java.lang.String> r0 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP
                        java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
                        java.lang.Object r0 = r0.get(r2)
                        java.lang.String r0 = (java.lang.String) r0
                        r7.a(r1, r0)
                        goto L_0x017c
                    L_0x0173:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2
                        java.lang.String r7 = r7.getMessage()
                        r0.a(r1, r7)
                    L_0x017c:
                        return
                    L_0x017d:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2
                        int r1 = r7.getErrorCode()
                        java.lang.String r7 = r7.getErrorMessage()
                        r0.a(r1, r7)
                        goto L_0x0259
                    L_0x018c:
                        int r0 = r7.a()
                        r1 = 604(0x25c, float:8.46E-43)
                        if (r0 != r1) goto L_0x0198
                        r0 = 3
                        r7.a((int) r0)
                    L_0x0198:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2
                        int r1 = r7.a()
                        java.lang.String r7 = r7.c()
                        r0.a(r1, r7)
                        goto L_0x0259
                    L_0x01a7:
                        okhttp3.Request r1 = r3
                        java.util.Map r3 = r4
                        com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.b(r1, r3)
                        java.lang.String r1 = "POST"
                        okhttp3.Request r3 = r3
                        java.lang.String r3 = r3.method()
                        boolean r1 = r1.equals(r3)
                        if (r1 == 0) goto L_0x01cf
                        java.lang.String r1 = r5     // Catch:{ Exception -> 0x0236 }
                        java.util.Map r3 = r4     // Catch:{ Exception -> 0x0236 }
                        java.util.Map r3 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r3)     // Catch:{ Exception -> 0x0236 }
                        java.lang.String r5 = r6     // Catch:{ Exception -> 0x0236 }
                        okhttp3.Request$Builder r1 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.b((java.lang.String) r1, (java.util.Map<java.lang.String, java.lang.String>) r3, (java.lang.String) r5)     // Catch:{ Exception -> 0x0236 }
                        okhttp3.Request r1 = r1.build()     // Catch:{ Exception -> 0x0236 }
                        goto L_0x01e1
                    L_0x01cf:
                        java.lang.String r1 = r5     // Catch:{ Exception -> 0x0236 }
                        java.util.Map r3 = r4     // Catch:{ Exception -> 0x0236 }
                        java.util.Map r3 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a((java.util.Map<java.lang.String, java.lang.String>) r3)     // Catch:{ Exception -> 0x0236 }
                        java.lang.String r5 = r6     // Catch:{ Exception -> 0x0236 }
                        okhttp3.Request$Builder r1 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.a((java.lang.String) r1, (java.util.Map<java.lang.String, java.lang.String>) r3, (java.lang.String) r5)     // Catch:{ Exception -> 0x0236 }
                        okhttp3.Request r1 = r1.build()     // Catch:{ Exception -> 0x0236 }
                    L_0x01e1:
                        r3 = 0
                        com.ximalaya.ting.android.opensdk.httputil.BaseCall r4 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()     // Catch:{ Exception -> 0x01eb }
                        okhttp3.Response r1 = r4.a((okhttp3.Request) r1)     // Catch:{ Exception -> 0x01eb }
                        goto L_0x01f0
                    L_0x01eb:
                        r1 = move-exception
                        r1.printStackTrace()
                        r1 = r3
                    L_0x01f0:
                        com.ximalaya.ting.android.opensdk.httputil.BaseResponse r3 = new com.ximalaya.ting.android.opensdk.httputil.BaseResponse
                        r3.<init>(r1)
                        int r4 = r3.a()
                        if (r4 != r2) goto L_0x0201
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2
                        r7.a(r1)
                        goto L_0x0259
                    L_0x0201:
                        com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest r1 = com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a()
                        com.ximalaya.ting.android.opensdk.datatrasfer.DataErrorCategory r1 = r1.a((com.ximalaya.ting.android.opensdk.httputil.BaseResponse) r3)
                        if (r1 == 0) goto L_0x0224
                        java.lang.String r1 = r1.c()
                        boolean r1 = android.text.TextUtils.isEmpty(r1)
                        if (r1 == 0) goto L_0x0216
                        goto L_0x0224
                    L_0x0216:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r0 = r2
                        int r1 = r7.a()
                        java.lang.String r7 = r7.c()
                        r0.a(r1, r7)
                        goto L_0x0259
                    L_0x0224:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2
                        java.util.Map<java.lang.Integer, java.lang.String> r1 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP
                        java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
                        java.lang.Object r1 = r1.get(r2)
                        java.lang.String r1 = (java.lang.String) r1
                        r7.a(r0, r1)
                        return
                    L_0x0236:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2
                        java.util.Map<java.lang.Integer, java.lang.String> r0 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP
                        java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                        java.lang.Object r0 = r0.get(r1)
                        java.lang.String r0 = (java.lang.String) r0
                        r7.a(r4, r0)
                        return
                    L_0x0248:
                        com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack r7 = r2
                        java.util.Map<java.lang.Integer, java.lang.String> r1 = com.ximalaya.ting.android.opensdk.httputil.XimalayaException.ERR_MESSAGE_MAP
                        java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
                        java.lang.Object r1 = r1.get(r2)
                        java.lang.String r1 = (java.lang.String) r1
                        r7.a(r0, r1)
                    L_0x0259:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenBaseCall.AnonymousClass3.a(okhttp3.Response):void");
                }

                public void a(int i, String str) {
                    iHttpCallBack2.a(i, str);
                }
            });
        } catch (XimalayaException e) {
            e.printStackTrace();
            if (iHttpCallBack != null) {
                iHttpCallBack.a(1014, XimalayaException.ERR_MESSAGE_MAP.get(1014));
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = f1859a.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static void c(String str) throws XimalayaException {
        if (!TextUtils.isEmpty(str)) {
            Iterator<String> it = f1859a.iterator();
            boolean isEmpty = TextUtils.isEmpty(AccessTokenManager.a().e());
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    if (isEmpty && CommonRequest.a().b() != null) {
                        CommonRequest.a().b().c();
                        throw XimalayaException.getExceptionByCode(1013);
                    }
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean c() throws IOException {
        final SharedPreferencesUtil a2 = SharedPreferencesUtil.a(AccessTokenManager.a().g());
        if (a2.m(AccessTokenManager.f1861a)) {
            final String c2 = a2.c(AccessTokenManager.f1861a);
            String f = CommonRequest.f(new HashMap<String, String>() {
                {
                    put("sso_code", c2);
                    put("redirect_uri", a2.c(AccessTokenManager.b));
                }
            });
            if (!TextUtils.isEmpty(f)) {
                try {
                    Bundle bundle = (Bundle) Class.forName("com.ximalaya.ting.android.opensdk.auth.utils.Utility").getDeclaredMethod("parseUrl", new Class[]{String.class}).invoke((Object) null, new Object[]{f});
                    if (bundle != null && bundle.containsKey("access_token") && !TextUtils.isEmpty(a(bundle, "access_token", ""))) {
                        String a3 = a(bundle, "expires_in", "");
                        long j = 0;
                        if (!TextUtils.isEmpty(a3) && !a3.equals("0")) {
                            j = System.currentTimeMillis() + (Long.parseLong(a3) * 1000);
                        }
                        AccessTokenManager.a().a(a(bundle, "access_token", ""), j, c2.split(a.b)[0]);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    private static String a(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            return str2;
        }
        String string = bundle.getString(str);
        return string != null ? string : str2;
    }

    /* access modifiers changed from: private */
    public static void d() throws XimalayaException {
        Response response;
        try {
            try {
                response = BaseCall.a().a(BaseBuilder.b(DTransferConstants.aZ, AccessTokenManager.a().b()).build());
            } catch (Exception e) {
                e.printStackTrace();
                response = null;
            }
            if (response == null) {
                throw XimalayaException.getExceptionByCode(1010);
            } else if (new BaseResponse(response).a() == 200) {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    AccessTokenManager.a().a(jSONObject.optString("access_token"), jSONObject.optLong("expires_in"));
                } catch (Exception unused) {
                    throw XimalayaException.getExceptionByCode(1009);
                }
            } else {
                throw XimalayaException.getExceptionByCode(1010);
            }
        } catch (Exception unused2) {
            throw XimalayaException.getExceptionByCode(1010);
        }
    }
}
