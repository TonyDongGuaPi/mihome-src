package com.xiaomi.smarthome;

import android.content.Context;
import com.adobe.xmp.XMPConst;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class AppConfigHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13358a = "appconfig_cache";
    public static final String b = "debug_use_preview_appconfig";
    public static final String c = "debug_spec_preview_appconfig";
    private static final String d = "AppConfigHelper";
    private final Context e;

    public static abstract class JsonAsyncHandler extends AsyncHandler<JSONObject, Error> {
        public final void processFailure(Call call, IOException iOException) {
        }

        public final void processResponse(Response response) {
        }
    }

    public interface OnCacheHandler<T> {
        boolean a(T t) throws Exception;

        boolean b(T t) throws Exception;
    }

    static {
        if (GlobalSetting.q || GlobalSetting.s) {
            GlobalSetting.E = SharePrefsManager.b(CommonApplication.getAppContext(), f13358a, b, false);
        }
    }

    public AppConfigHelper(Context context) {
        this.e = context;
    }

    public void a(String str, String str2, String str3, OnCacheHandler<JSONObject> onCacheHandler, JsonAsyncHandler jsonAsyncHandler) {
        a(str, str2, str3, (String) null, onCacheHandler, jsonAsyncHandler);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e6 A[Catch:{ Throwable -> 0x00fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, com.xiaomi.smarthome.AppConfigHelper.OnCacheHandler<org.json.JSONObject> r20, com.xiaomi.smarthome.AppConfigHelper.JsonAsyncHandler r21) {
        /*
            r15 = this;
            r11 = r15
            r8 = r16
            r9 = r17
            r10 = r18
            r1 = r20
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r8)
            boolean r2 = com.xiaomi.smarthome.globalsetting.GlobalSetting.E
            if (r2 == 0) goto L_0x0018
            java.lang.String r2 = "_preview"
            goto L_0x001a
        L_0x0018:
            java.lang.String r2 = ""
        L_0x001a:
            r0.append(r2)
            java.lang.String r2 = "_"
            r0.append(r2)
            r0.append(r9)
            java.lang.String r2 = "_"
            r0.append(r2)
            r0.append(r10)
            java.lang.String r12 = r0.toString()
            java.lang.String r0 = "AppConfigHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "requestConfigWitchCache:"
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r0, r2)
            java.io.File r6 = new java.io.File
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            android.content.Context r2 = r11.e
            java.io.File r2 = r2.getFilesDir()
            r0.append(r2)
            java.lang.String r2 = java.io.File.separator
            r0.append(r2)
            java.lang.String r2 = "appconfig_cache"
            r0.append(r2)
            java.lang.String r2 = java.io.File.separator
            r0.append(r2)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            r13 = 0
            boolean r0 = r6.exists()     // Catch:{ Throwable -> 0x00a0 }
            if (r0 == 0) goto L_0x009d
            byte[] r0 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((java.io.File) r6)     // Catch:{ Throwable -> 0x00a0 }
            if (r0 == 0) goto L_0x009d
            int r2 = r0.length     // Catch:{ Throwable -> 0x00a0 }
            if (r2 <= 0) goto L_0x009d
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00a0 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x00a0 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00a0 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a0 }
            boolean r0 = r1.a(r2)     // Catch:{ Throwable -> 0x00a0 }
            if (r0 == 0) goto L_0x009d
            java.lang.String r0 = "result"
            org.json.JSONObject r0 = r2.optJSONObject(r0)     // Catch:{ Throwable -> 0x00a0 }
            java.lang.String r2 = "file_url"
            java.lang.String r0 = r0.optString(r2)     // Catch:{ Throwable -> 0x00a0 }
            goto L_0x009e
        L_0x009d:
            r0 = r13
        L_0x009e:
            r2 = r0
            goto L_0x00ab
        L_0x00a0:
            r0 = move-exception
            java.lang.String r2 = "AppConfigHelper"
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r2, r0)
            r2 = r13
        L_0x00ab:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x011e
            boolean r0 = android.text.TextUtils.isEmpty(r19)
            if (r0 != 0) goto L_0x011e
            android.content.Context r0 = com.xiaomi.smarthome.application.CommonApplication.getAppContext()     // Catch:{ Throwable -> 0x0102, all -> 0x00ff }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Throwable -> 0x0102, all -> 0x00ff }
            r5 = r19
            java.io.InputStream r5 = r0.open(r5)     // Catch:{ Throwable -> 0x0102, all -> 0x00ff }
            int r0 = r5.available()     // Catch:{ Throwable -> 0x00fd }
            byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x00fd }
            r5.read(r0)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r7 = new java.lang.String     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r14 = "UTF-8"
            java.nio.charset.Charset r14 = java.nio.charset.Charset.forName(r14)     // Catch:{ Throwable -> 0x00fd }
            r7.<init>(r0, r14)     // Catch:{ Throwable -> 0x00fd }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00fd }
            r0.<init>(r7)     // Catch:{ Throwable -> 0x00fd }
            boolean r1 = r1.b(r0)     // Catch:{ Throwable -> 0x00fd }
            if (r1 == 0) goto L_0x00f3
            java.lang.String r1 = "result"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ Throwable -> 0x00fd }
            java.lang.String r1 = "file_url"
            java.lang.String r0 = r0.optString(r1)     // Catch:{ Throwable -> 0x00fd }
            goto L_0x00f4
        L_0x00f3:
            r0 = r2
        L_0x00f4:
            java.io.Closeable[] r1 = new java.io.Closeable[r4]
            r1[r3] = r5
            com.xiaomi.smarthome.library.common.util.CloseUtils.b(r1)
            r7 = r0
            goto L_0x011f
        L_0x00fd:
            r0 = move-exception
            goto L_0x0104
        L_0x00ff:
            r0 = move-exception
            r5 = r13
            goto L_0x0116
        L_0x0102:
            r0 = move-exception
            r5 = r13
        L_0x0104:
            java.lang.String r1 = "AppConfigHelper"
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0115 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r1, r0)     // Catch:{ all -> 0x0115 }
            java.io.Closeable[] r0 = new java.io.Closeable[r4]
            r0[r3] = r5
            com.xiaomi.smarthome.library.common.util.CloseUtils.b(r0)
            goto L_0x011e
        L_0x0115:
            r0 = move-exception
        L_0x0116:
            java.io.Closeable[] r1 = new java.io.Closeable[r4]
            r1[r3] = r5
            com.xiaomi.smarthome.library.common.util.CloseUtils.b(r1)
            throw r0
        L_0x011e:
            r7 = r2
        L_0x011f:
            if (r7 != 0) goto L_0x0122
            r3 = 1
        L_0x0122:
            if (r3 == 0) goto L_0x0126
            r0 = r13
            goto L_0x0128
        L_0x0126:
            java.lang.String r0 = "1"
        L_0x0128:
            com.xiaomi.smarthome.library.http.Request r0 = r15.a(r8, r9, r10, r0)     // Catch:{ Exception -> 0x0140 }
            com.xiaomi.smarthome.AppConfigHelper$1 r14 = new com.xiaomi.smarthome.AppConfigHelper$1     // Catch:{ Exception -> 0x0140 }
            r1 = r14
            r2 = r15
            r4 = r21
            r5 = r12
            r8 = r16
            r9 = r17
            r10 = r18
            r1.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0140 }
            com.xiaomi.smarthome.library.http.HttpApi.a((com.xiaomi.smarthome.library.http.Request) r0, (com.xiaomi.smarthome.library.http.async.AsyncHandler) r14)     // Catch:{ Exception -> 0x0140 }
            goto L_0x0164
        L_0x0140:
            r0 = move-exception
            java.lang.String r1 = "AppConfigHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "requestConfigWitchCache Exception:"
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r1, r2)
            com.xiaomi.smarthome.library.http.Error r1 = new com.xiaomi.smarthome.library.http.Error
            r2 = -1
            java.lang.String r3 = "getRequest Exception"
            r1.<init>(r2, r3)
            r2 = r21
            r2.sendFailureMessage(r1, r0, r13)
        L_0x0164:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.AppConfigHelper.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.xiaomi.smarthome.AppConfigHelper$OnCacheHandler, com.xiaomi.smarthome.AppConfigHelper$JsonAsyncHandler):void");
    }

    public static boolean a(String str) {
        return str != null && !str.equals("") && !str.equals("null") && !str.equals("{}") && !str.equals(XMPConst.ai);
    }

    /* access modifiers changed from: private */
    public Request a(String str, String str2, String str3, String str4) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, str3);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(GlobalSetting.E ? "_preview" : "");
        jSONObject.put("name", sb.toString());
        jSONObject.put("version", str2);
        if (str4 != null) {
            jSONObject.put("result_level", str4);
        }
        return new Request.Builder().a("GET").b(ServerRouteUtil.a(CommonApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8")).a();
    }
}
