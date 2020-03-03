package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.unionpay.a.d;
import com.unionpay.b.e;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.b;
import com.unionpay.utils.i;
import com.unionpay.utils.k;
import com.xiaomi.smarthome.download.DownloadManager;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UPPayAssistEx {
    /* access modifiers changed from: private */
    public static String A = "";
    private static String B = "";
    /* access modifiers changed from: private */
    public static String C = "";
    private static String D = "";
    private static boolean E = false;
    private static int F = 10;
    /* access modifiers changed from: private */
    public static Context G = null;
    /* access modifiers changed from: private */
    public static String H = "";
    private static String I = null;
    private static String J = null;
    /* access modifiers changed from: private */
    public static String K = "";
    /* access modifiers changed from: private */
    public static String L = "";
    /* access modifiers changed from: private */
    public static boolean M = false;
    /* access modifiers changed from: private */
    public static String N = "";
    /* access modifiers changed from: private */
    public static int O = 0;
    /* access modifiers changed from: private */
    public static boolean P = false;
    public static final int PLUGIN_NOT_FOUND = -1;
    public static final int PLUGIN_VALID = 0;
    /* access modifiers changed from: private */
    public static d Q = null;
    /* access modifiers changed from: private */
    public static Handler R = null;
    /* access modifiers changed from: private */
    public static String S = "application/vnd.android.package-archive";
    private static String T = "http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin";
    private static String U = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay\",\"version\":\"^[5-9]{1}+(.[0-9]{1})+(.[0-9]{1,3})?$\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":101}],\"need_install\":false,\"install_msg\":\"请先安装银联在线支付服务，安装完成后重新发起付款\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"银联在线支付服务\",\"download_desp\":\"正在下载银联在线支付服务…\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"},{\"type\":\"jar\",\"sort\":200}]";
    private static String V = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay.tsmservice\",\"version\":\"^[1-9].*|^0[2-9].*|^01\\.[1-9].*|^01\\.0[1-9].*|^01\\.00\\.[2-9].*|^01\\.00\\.1[012789].*|^01\\.00\\.0[8-9].*\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":102},{\"schema\":\"com.unionpay.tsmservice.mi\",\"version\":\"^[1-9].*|^0[2-9].*|^01\\.[1-9].*|^01\\.0[1-9].*|^01\\.00\\.[1-9].*|^01\\.00\\.0[8-9].*\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":103}],\"need_install\":false,\"install_msg\":\"请先安装银联在线支付服务，安装完成后重新发起付款\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"银联在线支付服务\",\"download_desp\":\"正在下载银联在线支付服务…\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"}]";
    public static final String VERSION = "3.4.8";
    /* access modifiers changed from: private */
    public static JSONArray W = null;
    private static IntentFilter X = new IntentFilter(DownloadManager.D);
    /* access modifiers changed from: private */
    public static BroadcastReceiver Y = new a();
    private static final Handler.Callback Z = new b();

    /* renamed from: a  reason: collision with root package name */
    private static String f9533a = "SpId";
    private static String b = "paydata";
    private static String c = "SysProvide";
    private static String d = "UseTestMode";
    private static String e = "SecurityChipType";
    private static String f = "uppayuri";
    private static String g = "resultIntentAction";
    private static String h = "reqOriginalId";
    private static String i = "wapurl";
    private static String j = "dlgstyle";
    private static String k = "com.unionpay.uppay";
    private static String l = "com.unionpay.uppay.PayActivity";
    private static String m = "ex_mode";
    private static String n = "server";
    private static String o = "source";
    private static String p = "samsung_out";
    private static String q = "se_type";
    private static String r = "se_title_logo";
    private static String s = "se_loading_logo";
    private static String t = "se_title_bg_color";
    private static String u = "se_cancel_bg_color";
    private static String v = "02";
    private static String w;
    private static String x;
    private static String y;
    private static String z;

    private static int a(Activity activity, String str, String str2) {
        try {
            String[] strArr = {"30820267308201d0a00302010202044", "ecb7d98300d06092a8", "64886f70d01010505003078310b30090603550", "406130238363111300f060355040813085", "368616e676", "861693111300f060355040713085368616e67686169311730", "15060355040a130e4368696e6120556e696f6e50617931173015060355040b130e4", "368696e612055", "6e696f6e5061793111300f06035504031308556e696f6e5061", "79301e170d3131313132323130343634385a170d333631313135313034", "3634385a3078310b300906035504061302383631", "11300f060355040813085368616e67686169311130", "0f060355040713085368616e676861693117", "3015060355040a130e4368696e6120556e696", "f6e50617931173015060355040b130e4368696e6120556e696", "f6e5061793111300f06035504031308556e696f6e50617930819f300d060", "92a864886f70d010101050003818d0030818902818100c42e6236d5054ffccaa", "a430ecd929d562", "b1ff56cef0e21c87260c63ce3ca868bf5974c14", "d9255940da7b6cd07483f4b4243fd1825b2705", "08eb9b5c67474d027fa03ce35109b11604083ab6bb4df2c46240f879f", "8cc1d6ed5e1b2cc00489215aec3fc2eac008e767b0215981cb5e", "e94ddc285669ec06b8a405dd4341eac4ea7030203010001300d06092a864886f70d010105050003818", "1001a3e74c601e3beb1b7ae4f9ab2872a0aaf1dbc2cba89c7528cd", "54aa526e7a37d8ba2311a1d3d2ab79b3fbeaf3ebb9e7da9e7cdd9be1ae5a53595f47", "b1fdf62b0f540fca5458b063af9354925a6c3505a18ff164b6b195f6e517eaee1fb783", "64c2f89fdffa16729c9779f99562bc189d2ce4722ba0faedb11aa22d0d9db228fda"};
            PackageManager packageManager = activity.getPackageManager();
            packageManager.getActivityInfo(activity.getComponentName(), 128);
            packageManager.getApplicationInfo("com.unionpay.uppay", 8192);
            PackageInfo packageInfo = packageManager.getPackageInfo("com.unionpay.uppay", 4160);
            String charsString = packageInfo.signatures[0].toCharsString();
            String str3 = "";
            for (int i2 = 0; i2 < 27; i2++) {
                str3 = str3 + strArr[i2];
            }
            if (!(charsString != null && charsString.equals(str3) && packageInfo.versionCode >= 31)) {
                return -1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(h, 1);
            bundle.putString(f, str);
            bundle.putString(g, str2);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClassName(k, l);
            activity.startActivity(intent);
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static int a(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback, boolean z2) {
        return b.d(context, "com.unionpay.tsmservice.mi") ? new e(context, uPQuerySEPayInfoCallback, z2).a() : new com.unionpay.b.b(context, uPQuerySEPayInfoCallback, z2).a();
    }

    private static int a(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        G = context;
        H = str3;
        I = str;
        J = str2;
        K = str4;
        C = str6;
        B = str5;
        D = TextUtils.isEmpty(str6) ? "0" : "1";
        w = null;
        x = null;
        y = null;
        if (!TextUtils.isEmpty(str6)) {
            A = str6;
        } else if (TextUtils.isEmpty(UPUtils.a(context, "se_type"))) {
            if (a(G, (UPQuerySEPayInfoCallback) new c(), false) == 0) {
                return 0;
            }
        }
        s();
        return 0;
    }

    private static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath == null) {
                return "";
            }
            String str2 = absolutePath + File.separator + str;
            if (!new File(str2).exists()) {
                FileOutputStream openFileOutput = context.openFileOutput(str, 1);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    openFileOutput.write(bArr, 0, read);
                }
                openFileOutput.close();
                open.close();
            }
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0235, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0239, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00bd, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0199, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d A[ExcHandler: PatternSyntaxException (r7v10 'e' java.util.regex.PatternSyntaxException A[CUSTOM_DECLARE]), Splitter:B:9:0x0018] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0010 A[ExcHandler: JSONException (r7v9 'e' org.json.JSONException A[CUSTOM_DECLARE]), Splitter:B:9:0x0018] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String a(android.content.Context r7, boolean r8) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r1 = 0
            java.lang.String r2 = K     // Catch:{ NumberFormatException -> 0x0013 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0013 }
            goto L_0x0014
        L_0x000d:
            r7 = move-exception
            goto L_0x0235
        L_0x0010:
            r7 = move-exception
            goto L_0x0239
        L_0x0013:
            r2 = 0
        L_0x0014:
            if (r8 != 0) goto L_0x0025
            java.lang.String r3 = "tn"
            java.lang.String r4 = H     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r4 = com.unionpay.utils.b.a((java.lang.String) r4)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = com.unionpay.utils.UPUtils.forWap(r2, r4)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r3, r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x0025:
            java.lang.String r2 = "imei"
            java.lang.String r3 = com.unionpay.utils.e.b(r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "locale"
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r4 = "zh"
            boolean r3 = r3.startsWith(r4)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r3 == 0) goto L_0x0043
            java.lang.String r3 = "zh_CN"
            goto L_0x0045
        L_0x0043:
            java.lang.String r3 = "en_US"
        L_0x0045:
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "terminal_version"
            java.lang.String r3 = "3.4.8"
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "terminal_resolution"
            android.content.res.Resources r3 = r7.getResources()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            int r3 = r3.widthPixels     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            android.content.res.Resources r4 = r7.getResources()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            int r4 = r4.heightPixels     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r5.<init>()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r5.append(r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = "*"
            r5.append(r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r5.append(r4)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = r5.toString()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = r3.trim()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "os_name"
            java.lang.String r3 = "android"
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "os_version"
            java.lang.String r3 = android.os.Build.VERSION.RELEASE     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = r3.trim()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "device_model"
            java.lang.String r3 = android.os.Build.MODEL     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r3 = r3.trim()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r3 == 0) goto L_0x00a3
            java.lang.String r4 = " "
            java.lang.String r5 = ""
            r3.replace(r4, r5)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x00a3:
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "mac"
            java.lang.String r3 = com.unionpay.utils.e.a((android.content.Context) r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = "time_zone"
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x00bd, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r3 = r3.getDisplayName(r1, r1)     // Catch:{ Exception -> 0x00bd, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00bd, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x00c1
        L_0x00bd:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x00c1:
            r2 = 1
            java.lang.String r3 = "network_mode"
            java.lang.String r4 = "connectivity"
            java.lang.Object r4 = r7.getSystemService(r4)     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r4 == 0) goto L_0x0108
            boolean r5 = r4.isAvailable()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r5 == 0) goto L_0x0108
            int r5 = r4.getType()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r5 != 0) goto L_0x00fc
            android.net.NetworkInfo$State r5 = r4.getState()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            android.net.NetworkInfo$State r6 = android.net.NetworkInfo.State.CONNECTED     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r5 != r6) goto L_0x00f9
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r6 = "mobile:"
            r5.<init>(r6)     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r4 = r4.getExtraInfo()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r5.append(r4)     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x010a
        L_0x00f9:
            java.lang.String r4 = "mobile"
            goto L_0x010a
        L_0x00fc:
            int r4 = r4.getType()     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r4 != r2) goto L_0x0105
            java.lang.String r4 = "wifi"
            goto L_0x010a
        L_0x0105:
            java.lang.String r4 = "other"
            goto L_0x010a
        L_0x0108:
            java.lang.String r4 = "disConnect"
        L_0x010a:
            r0.put(r3, r4)     // Catch:{ Exception -> 0x010e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x0112
        L_0x010e:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x0112:
            java.lang.String r3 = "imsi"
            java.lang.String r4 = com.unionpay.utils.e.c(r7)     // Catch:{ Exception -> 0x011c, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r0.put(r3, r4)     // Catch:{ Exception -> 0x011c, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x0120
        L_0x011c:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x0120:
            java.lang.String r3 = "baseband_version"
            java.lang.String r4 = com.unionpay.utils.e.a()     // Catch:{ Exception -> 0x012e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r4 = e(r4)     // Catch:{ Exception -> 0x012e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r0.put(r3, r4)     // Catch:{ Exception -> 0x012e, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x0132
        L_0x012e:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x0132:
            java.lang.String r3 = "root"
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x014a, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r5 = "/system/bin/su"
            r4.<init>(r5)     // Catch:{ Exception -> 0x014a, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            boolean r4 = r4.exists()     // Catch:{ Exception -> 0x014a, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r4 == 0) goto L_0x0144
            java.lang.String r4 = "1"
            goto L_0x0146
        L_0x0144:
            java.lang.String r4 = "0"
        L_0x0146:
            r0.put(r3, r4)     // Catch:{ Exception -> 0x014a, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x014e
        L_0x014a:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x014e:
            java.lang.String r3 = "support_map"
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r5 = "000"
            r4.<init>(r5)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r6 = 10
            if (r5 < r6) goto L_0x0191
            java.lang.String r5 = "nfc"
            java.lang.Object r5 = r7.getSystemService(r5)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            android.nfc.NfcManager r5 = (android.nfc.NfcManager) r5     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            android.nfc.NfcAdapter r5 = r5.getDefaultAdapter()     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r5 == 0) goto L_0x0191
            boolean r5 = r5.isEnabled()     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r6 = 49
            if (r5 == 0) goto L_0x0177
            r4.setCharAt(r1, r6)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x017c
        L_0x0177:
            r5 = 50
            r4.setCharAt(r1, r5)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
        L_0x017c:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r5 = 19
            if (r1 < r5) goto L_0x0191
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            java.lang.String r5 = "android.hardware.nfc.hce"
            boolean r1 = r1.hasSystemFeature(r5)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            if (r1 == 0) goto L_0x0191
            r4.setCharAt(r2, r6)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
        L_0x0191:
            java.lang.String r1 = r4.toString()     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            r0.put(r3, r1)     // Catch:{ Exception -> 0x0199, JSONException -> 0x0010, PatternSyntaxException -> 0x000d }
            goto L_0x019d
        L_0x0199:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x019d:
            java.lang.String r1 = "country"
            java.util.Locale r2 = java.util.Locale.getDefault()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = r2.getCountry()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = e(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r1 = "package"
            r2 = r7
            android.app.Activity r2 = (android.app.Activity) r2     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r2 == 0) goto L_0x01ba
            goto L_0x01bc
        L_0x01ba:
            java.lang.String r2 = ""
        L_0x01bc:
            java.lang.String r2 = e(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r1 = "latitude"
            android.location.Location r2 = com.unionpay.utils.e.d(r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r2 == 0) goto L_0x01d8
            double r2 = r2.getLatitude()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            goto L_0x01da
        L_0x01d8:
            java.lang.String r2 = ""
        L_0x01da:
            java.lang.String r2 = e(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r1 = "longitude"
            android.location.Location r2 = com.unionpay.utils.e.d(r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r2 == 0) goto L_0x01f6
            double r2 = r2.getLongitude()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.Double r2 = java.lang.Double.valueOf(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            goto L_0x01f8
        L_0x01f6:
            java.lang.String r2 = ""
        L_0x01f8:
            java.lang.String r2 = e(r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r1 = "tel"
            java.lang.String r7 = com.unionpay.utils.e.e(r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r7 = e(r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r1, r7)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            if (r8 == 0) goto L_0x0222
            java.lang.String r7 = "com.unionpay.uppay.PayActivity"
            java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x021b }
            java.lang.String r7 = "has_sdk"
            java.lang.String r8 = "1"
            r0.put(r7, r8)     // Catch:{ ClassNotFoundException -> 0x021b }
            goto L_0x0222
        L_0x021b:
            java.lang.String r7 = "has_sdk"
            java.lang.String r8 = "0"
            r0.put(r7, r8)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
        L_0x0222:
            java.lang.String r7 = "seType"
            java.lang.String r8 = A     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r8 = com.unionpay.utils.b.d(r8)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r7, r8)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            java.lang.String r7 = "isLimitSe"
            java.lang.String r8 = D     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            r0.put(r7, r8)     // Catch:{ JSONException -> 0x0010, PatternSyntaxException -> 0x000d, Exception -> 0x023c }
            goto L_0x023c
        L_0x0235:
            r7.printStackTrace()
            goto L_0x023c
        L_0x0239:
            r7.printStackTrace()
        L_0x023c:
            java.lang.String r7 = r0.toString()
            java.lang.String r8 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "init: "
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            com.unionpay.utils.j.b(r8, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayAssistEx.a(android.content.Context, boolean):java.lang.String");
    }

    static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        boolean z2;
        String a2 = a(context, str2);
        if (a2 == null || TextUtils.isEmpty(a2)) {
            z2 = false;
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file:" + a2), "application/vnd.android.package-archive");
            context.startActivity(intent);
            z2 = true;
        }
        if (!z2 && f(str)) {
            L = str5;
            N = str2;
            if (!M) {
                try {
                    context.registerReceiver(Y, X);
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                    request.setMimeType(S);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
                    request.setTitle(str3);
                    request.setDescription(str4);
                    request.setNotificationVisibility(1);
                    UPUtils.a(context, ((android.app.DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).enqueue(request), "id");
                    M = true;
                } catch (Exception e2) {
                    if (e2 instanceof IllegalArgumentException) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(k.a().d);
                        builder.setMessage(k.a().f);
                        builder.setPositiveButton(k.a().b, new g(context));
                        builder.setNegativeButton(k.a().c, new h(context));
                        builder.create().show();
                        return;
                    }
                    context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        }
    }

    static /* synthetic */ void a(Context context, JSONArray jSONArray, int i2) {
        String str;
        boolean z2;
        Context context2 = context;
        JSONArray jSONArray2 = jSONArray;
        int i3 = i2;
        while (jSONArray2 != null && i3 < jSONArray2.length()) {
            Object a2 = i.a(jSONArray2, i3);
            if (a2 != null) {
                JSONObject jSONObject = (JSONObject) a2;
                String a3 = i.a(jSONObject, "type");
                if ("app".equals(a3)) {
                    JSONArray c2 = i.c(jSONObject, "package_info");
                    String a4 = i.a(jSONObject, "install_msg");
                    String a5 = i.a(jSONObject, "url");
                    boolean b2 = i.b(jSONObject, "need_install");
                    String a6 = i.a(jSONObject, "download_app");
                    String a7 = i.a(jSONObject, "download_title");
                    String a8 = i.a(jSONObject, "download_desp");
                    String a9 = i.a(jSONObject, "md5");
                    String a10 = i.a(jSONObject, "app_server");
                    JSONArray b3 = b(c2, DTransferConstants.D);
                    if (b3.length() > 0) {
                        int length = b3.length();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= length) {
                                break;
                            }
                            Object a11 = i.a(b3, i4);
                            if (a11 != null) {
                                JSONObject jSONObject2 = (JSONObject) a11;
                                String a12 = i.a(jSONObject2, "schema");
                                String a13 = i.a(jSONObject2, "sign");
                                String a14 = i.a(jSONObject2, "version");
                                if (b.a(context2, a12) && a13.equalsIgnoreCase(b.b(context2, a12)) && b.c(context2, a12).matches(a14)) {
                                    try {
                                        Bundle bundle = new Bundle();
                                        a(H, bundle, K);
                                        bundle.putString(f9533a, I);
                                        bundle.putString(c, J);
                                        bundle.putString(b, H);
                                        bundle.putString(o, B);
                                        bundle.putString(q, C);
                                        if (!TextUtils.isEmpty(C)) {
                                            bundle.putString(r, w);
                                            bundle.putString(s, x);
                                            bundle.putString(t, y);
                                            bundle.putString(u, z);
                                        }
                                        bundle.putBoolean(j, E);
                                        bundle.putString(n, a10);
                                        bundle.putString(e, (String) null);
                                        try {
                                            bundle.putInt(h, 0);
                                            Intent intent = new Intent();
                                            intent.putExtras(bundle);
                                            intent.setClassName(a12, l);
                                            if (G instanceof Activity) {
                                                ((Activity) G).startActivityForResult(intent, F);
                                            } else {
                                                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                                G.startActivity(intent);
                                            }
                                            z2 = true;
                                        } catch (Exception unused) {
                                        }
                                    } catch (Exception unused2) {
                                    }
                                }
                            }
                            i4++;
                        }
                    }
                    z2 = false;
                    if (z2) {
                        return;
                    }
                    if (b2) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context2);
                        builder.setTitle(k.a().d);
                        builder.setMessage(a4);
                        builder.setPositiveButton(k.a().b, new e(context, a5, a6, a7, a8, a9));
                        builder.setNegativeButton(k.a().c, new f(context2));
                        builder.create().show();
                        return;
                    }
                } else {
                    int i5 = 0;
                    if ("wap".equals(a3)) {
                        if (!p.equals(B)) {
                            try {
                                str = (String) jSONObject.get("url");
                            } catch (Exception unused3) {
                                str = "";
                            }
                            Bundle bundle2 = new Bundle();
                            a(H, bundle2, K);
                            bundle2.putString(f9533a, I);
                            bundle2.putString(c, J);
                            bundle2.putString("magic_data", "949A1CC");
                            try {
                                i5 = Integer.parseInt(K);
                            } catch (NumberFormatException unused4) {
                            }
                            bundle2.putString(b, UPUtils.forWap(i5, b.a(H)));
                            bundle2.putString(i, str);
                            Intent intent2 = new Intent();
                            intent2.putExtras(bundle2);
                            intent2.setClass(G, UPPayWapActivity.class);
                            ((Activity) G).startActivityForResult(intent2, 100);
                            return;
                        }
                    } else if ("jar".equals(a3)) {
                        a(i.a(jSONObject, "app_server"));
                        return;
                    } else {
                        return;
                    }
                }
                jSONArray2 = W;
                i3 = O + 1;
                O = i3;
            } else {
                return;
            }
        }
    }

    static void a(String str) {
        Bundle bundle = new Bundle();
        a(H, bundle, K);
        bundle.putString(f9533a, I);
        bundle.putString(c, J);
        bundle.putString(b, H);
        bundle.putString(o, B);
        bundle.putString(q, C);
        if (!TextUtils.isEmpty(C)) {
            bundle.putString(r, w);
            bundle.putString(s, x);
            bundle.putString(t, y);
            bundle.putString(u, z);
        }
        bundle.putString(n, str);
        bundle.putBoolean(j, E);
        bundle.putInt(h, 2);
        try {
            Class<?> cls = Class.forName("com.unionpay.uppay.PayActivity");
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(G, cls);
            if (G instanceof Activity) {
                ((Activity) G).startActivityForResult(intent, F);
                return;
            }
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            G.startActivity(intent);
        } catch (ClassNotFoundException unused) {
        }
    }

    private static void a(String str, Bundle bundle, String str2) {
        if (str != null && str.trim().length() > 0) {
            if (str.trim().charAt(0) != '<') {
                bundle.putString(m, str2);
            } else if (str2 == null || !str2.trim().equalsIgnoreCase("00")) {
                bundle.putBoolean(d, true);
            } else {
                bundle.putBoolean(d, false);
            }
        }
    }

    /* access modifiers changed from: private */
    public static JSONArray b(JSONArray jSONArray, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.optJSONObject(i2));
        }
        Collections.sort(arrayList, new i(str));
        JSONArray jSONArray2 = new JSONArray();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            jSONArray2.put((JSONObject) arrayList.get(i3));
        }
        return jSONArray2;
    }

    static /* synthetic */ void b(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:com.android.providers.downloads"));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            context.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|(12:6|7|8|9|10|13|14|(1:16)|17|(1:19)|20|(1:24))|25|26|27|(3:29|(2:31|(2:33|(3:35|(3:37|(2:39|(2:51|45))(1:56)|46)|55)(1:54))(1:53))(1:52)|47)|50|48) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x00c3 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(android.content.Context r11) {
        /*
            java.lang.String r0 = U
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "configs"
            r1.<init>(r2)
            java.lang.String r2 = C
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = com.unionpay.utils.UPUtils.a((android.content.Context) r11, (java.lang.String) r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "mode"
            r2.<init>(r3)
            java.lang.String r3 = C
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = com.unionpay.utils.UPUtils.a((android.content.Context) r11, (java.lang.String) r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "or"
            r3.<init>(r4)
            java.lang.String r4 = C
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = com.unionpay.utils.UPUtils.a((android.content.Context) r11, (java.lang.String) r3)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            r5 = 0
            if (r4 != 0) goto L_0x00c3
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x00c3
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x00c3
            java.lang.String r4 = "entryexpro"
            java.lang.System.loadLibrary(r4)     // Catch:{ Exception -> 0x00c3 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x00c3 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r1 = "sign"
            java.lang.String r1 = com.unionpay.utils.i.a((org.json.JSONObject) r4, (java.lang.String) r1)     // Catch:{ Exception -> 0x00c3 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0066 }
            goto L_0x0067
        L_0x0066:
            r2 = 0
        L_0x0067:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r7 = "configs"
            java.lang.String r7 = r4.getString(r7)     // Catch:{ Exception -> 0x00c3 }
            r8 = 2
            byte[] r7 = android.util.Base64.decode(r7, r8)     // Catch:{ Exception -> 0x00c3 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r7 = ""
            java.lang.String r9 = "sePayConf"
            boolean r9 = r4.has(r9)     // Catch:{ Exception -> 0x00c3 }
            if (r9 == 0) goto L_0x0090
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r9 = "sePayConf"
            java.lang.String r4 = r4.getString(r9)     // Catch:{ Exception -> 0x00c3 }
            byte[] r4 = android.util.Base64.decode(r4, r8)     // Catch:{ Exception -> 0x00c3 }
            r7.<init>(r4)     // Catch:{ Exception -> 0x00c3 }
        L_0x0090:
            boolean r4 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x00c3 }
            if (r4 == 0) goto L_0x0098
            java.lang.String r7 = ""
        L_0x0098:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c3 }
            r4.<init>()     // Catch:{ Exception -> 0x00c3 }
            r4.append(r6)     // Catch:{ Exception -> 0x00c3 }
            r4.append(r7)     // Catch:{ Exception -> 0x00c3 }
            r4.append(r3)     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r3 = com.unionpay.utils.UPUtils.a(r3)     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r3 = com.unionpay.utils.b.a((java.lang.String) r3)     // Catch:{ Exception -> 0x00c3 }
            java.lang.String r1 = com.unionpay.utils.UPUtils.forConfig(r2, r1)     // Catch:{ Exception -> 0x00c3 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00c3 }
            if (r2 != 0) goto L_0x00c3
            boolean r1 = r1.equals(r3)     // Catch:{ Exception -> 0x00c3 }
            if (r1 == 0) goto L_0x00c3
            r0 = r6
        L_0x00c3:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x013a }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x013a }
            int r0 = r1.length()
            r2 = 0
        L_0x00cd:
            if (r2 >= r0) goto L_0x013a
            java.lang.Object r3 = com.unionpay.utils.i.a((org.json.JSONArray) r1, (int) r2)
            if (r3 == 0) goto L_0x0137
            org.json.JSONObject r3 = (org.json.JSONObject) r3
            java.lang.String r4 = "type"
            java.lang.String r4 = com.unionpay.utils.i.a((org.json.JSONObject) r3, (java.lang.String) r4)
            java.lang.String r6 = "app"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x0137
            java.lang.String r4 = "package_info"
            org.json.JSONArray r3 = com.unionpay.utils.i.c(r3, r4)
            java.lang.String r4 = "sort"
            org.json.JSONArray r3 = b(r3, r4)
            int r4 = r3.length()
            if (r4 <= 0) goto L_0x0137
            int r4 = r3.length()
            r6 = 0
        L_0x00fc:
            if (r6 >= r4) goto L_0x0137
            java.lang.Object r7 = com.unionpay.utils.i.a((org.json.JSONArray) r3, (int) r6)
            if (r7 == 0) goto L_0x0134
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            java.lang.String r8 = "schema"
            java.lang.String r8 = com.unionpay.utils.i.a((org.json.JSONObject) r7, (java.lang.String) r8)
            java.lang.String r9 = "sign"
            java.lang.String r9 = com.unionpay.utils.i.a((org.json.JSONObject) r7, (java.lang.String) r9)
            java.lang.String r10 = "version"
            java.lang.String r7 = com.unionpay.utils.i.a((org.json.JSONObject) r7, (java.lang.String) r10)
            boolean r10 = com.unionpay.utils.b.a((android.content.Context) r11, (java.lang.String) r8)
            if (r10 == 0) goto L_0x0134
            java.lang.String r10 = com.unionpay.utils.b.b(r11, r8)
            boolean r9 = r9.equalsIgnoreCase(r10)
            if (r9 == 0) goto L_0x0134
            java.lang.String r8 = com.unionpay.utils.b.c(r11, r8)
            boolean r7 = r8.matches(r7)
            if (r7 == 0) goto L_0x0134
            r11 = 1
            return r11
        L_0x0134:
            int r6 = r6 + 1
            goto L_0x00fc
        L_0x0137:
            int r2 = r2 + 1
            goto L_0x00cd
        L_0x013a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayAssistEx.c(android.content.Context):boolean");
    }

    public static boolean checkWalletInstalled(Context context) {
        return b.a(context, "com.unionpay") && "536C79B93ACFBEA950AE365D8CE1AEF91FEA9535".equalsIgnoreCase(b.b(context, "com.unionpay"));
    }

    /* access modifiers changed from: private */
    public static void d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            w = jSONObject.getString("titleLogo");
            x = jSONObject.getString("loadingLogo");
            y = jSONObject.getString("backGroundColor");
            z = jSONObject.getString("textColor");
        } catch (JSONException unused) {
        }
    }

    private static String e(String str) {
        return str != null ? Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim() : "";
    }

    private static boolean f(String str) {
        try {
            return Pattern.compile(".*(\\.95516\\.com|\\.chinaunionpay\\.com|\\.unionpay\\.com|\\.unionpaysecure\\.com|\\.95516\\.net)(,.*|$)", 2).matcher(new URL(str).getHost()).matches();
        } catch (Exception unused) {
            return false;
        }
    }

    public static int getSEPayInfo(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        return a(context, uPQuerySEPayInfoCallback, true);
    }

    public static boolean installUPPayPlugin(Context context) {
        try {
            InputStream open = context.getAssets().open("UPPayPluginEx.apk");
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath == null) {
                return false;
            }
            String str = absolutePath + File.separator + "UPPayPluginEx.apk";
            if (!new File(str).exists()) {
                FileOutputStream openFileOutput = context.openFileOutput("UPPayPluginEx.apk", 1);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    openFileOutput.write(bArr, 0, read);
                }
                openFileOutput.close();
                open.close();
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file:" + str), "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ int r() {
        int i2 = O + 1;
        O = i2;
        return i2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x012d */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0135 A[Catch:{ JSONException -> 0x014f }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0145 A[Catch:{ JSONException -> 0x014f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int s() {
        /*
            android.content.Context r0 = G
            r1 = 1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r0 = B
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r0 == 0) goto L_0x001b
            java.lang.String r0 = C
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0018
            goto L_0x001b
        L_0x0018:
            E = r2
            goto L_0x002b
        L_0x001b:
            E = r1
            java.lang.String r0 = v
            java.lang.String r1 = C
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = p
            B = r0
        L_0x002b:
            O = r2
            P = r2
            java.lang.String r0 = "entryexpro"
            java.lang.System.loadLibrary(r0)
            android.content.Context r0 = G
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "configs"
            r1.<init>(r3)
            java.lang.String r3 = C
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = com.unionpay.utils.UPUtils.a((android.content.Context) r0, (java.lang.String) r1)
            android.content.Context r1 = G
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "mode"
            r3.<init>(r4)
            java.lang.String r4 = C
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r1 = com.unionpay.utils.UPUtils.a((android.content.Context) r1, (java.lang.String) r3)
            android.content.Context r3 = G
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "or"
            r4.<init>(r5)
            java.lang.String r5 = C
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r3 = com.unionpay.utils.UPUtils.a((android.content.Context) r3, (java.lang.String) r4)
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L_0x012d
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x012d
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x012d
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x012d }
            r4.<init>(r0)     // Catch:{ Exception -> 0x012d }
            java.lang.String r0 = "sign"
            java.lang.String r0 = com.unionpay.utils.i.a((org.json.JSONObject) r4, (java.lang.String) r0)     // Catch:{ Exception -> 0x012d }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0098 }
            goto L_0x0099
        L_0x0098:
            r1 = 0
        L_0x0099:
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x012d }
            java.lang.String r6 = "configs"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Exception -> 0x012d }
            r7 = 2
            byte[] r6 = android.util.Base64.decode(r6, r7)     // Catch:{ Exception -> 0x012d }
            r5.<init>(r6)     // Catch:{ Exception -> 0x012d }
            java.lang.String r6 = ""
            java.lang.String r8 = "sePayConf"
            boolean r8 = r4.has(r8)     // Catch:{ Exception -> 0x012d }
            if (r8 == 0) goto L_0x00c2
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x012d }
            java.lang.String r8 = "sePayConf"
            java.lang.String r4 = r4.getString(r8)     // Catch:{ Exception -> 0x012d }
            byte[] r4 = android.util.Base64.decode(r4, r7)     // Catch:{ Exception -> 0x012d }
            r6.<init>(r4)     // Catch:{ Exception -> 0x012d }
        L_0x00c2:
            boolean r4 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x012d }
            if (r4 == 0) goto L_0x00ca
            java.lang.String r6 = ""
        L_0x00ca:
            java.lang.String r4 = "uppay"
            com.unionpay.utils.j.a(r4, r5)     // Catch:{ Exception -> 0x012d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012d }
            r4.<init>()     // Catch:{ Exception -> 0x012d }
            r4.append(r5)     // Catch:{ Exception -> 0x012d }
            r4.append(r6)     // Catch:{ Exception -> 0x012d }
            r4.append(r3)     // Catch:{ Exception -> 0x012d }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x012d }
            java.lang.String r3 = com.unionpay.utils.UPUtils.a(r3)     // Catch:{ Exception -> 0x012d }
            java.lang.String r3 = com.unionpay.utils.b.a((java.lang.String) r3)     // Catch:{ Exception -> 0x012d }
            java.lang.String r0 = com.unionpay.utils.UPUtils.forConfig(r1, r0)     // Catch:{ Exception -> 0x012d }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x012d }
            if (r1 != 0) goto L_0x012d
            boolean r0 = r0.equals(r3)     // Catch:{ Exception -> 0x012d }
            if (r0 == 0) goto L_0x012d
            java.lang.String r0 = C     // Catch:{ Exception -> 0x012d }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x012d }
            if (r0 == 0) goto L_0x0104
            U = r5     // Catch:{ Exception -> 0x012d }
            goto L_0x0106
        L_0x0104:
            V = r5     // Catch:{ Exception -> 0x012d }
        L_0x0106:
            java.lang.String r0 = A     // Catch:{ Exception -> 0x012d }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x012d }
            if (r0 != 0) goto L_0x012d
            android.content.Context r0 = G     // Catch:{ Exception -> 0x012d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012d }
            java.lang.String r3 = "se_configs"
            r1.<init>(r3)     // Catch:{ Exception -> 0x012d }
            java.lang.String r3 = A     // Catch:{ Exception -> 0x012d }
            r1.append(r3)     // Catch:{ Exception -> 0x012d }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x012d }
            java.lang.String r0 = com.unionpay.utils.UPUtils.a((android.content.Context) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x012d }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x012d }
            if (r1 != 0) goto L_0x012d
            d(r0)     // Catch:{ Exception -> 0x012d }
        L_0x012d:
            java.lang.String r0 = C     // Catch:{ JSONException -> 0x014f }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ JSONException -> 0x014f }
            if (r0 == 0) goto L_0x0145
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x014f }
            java.lang.String r1 = U     // Catch:{ JSONException -> 0x014f }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x014f }
            java.lang.String r1 = "sort"
        L_0x013e:
            org.json.JSONArray r0 = b(r0, r1)     // Catch:{ JSONException -> 0x014f }
            W = r0     // Catch:{ JSONException -> 0x014f }
            goto L_0x014f
        L_0x0145:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x014f }
            java.lang.String r1 = V     // Catch:{ JSONException -> 0x014f }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x014f }
            java.lang.String r1 = "sort"
            goto L_0x013e
        L_0x014f:
            android.content.Context r0 = G
            java.lang.String r1 = K     // Catch:{ NumberFormatException -> 0x0158 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0158 }
            goto L_0x0159
        L_0x0158:
            r1 = 0
        L_0x0159:
            java.lang.String r1 = com.unionpay.utils.UPUtils.forUrl(r1)
            java.lang.String r3 = "uppay"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "url: "
            r4.<init>(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.unionpay.utils.j.b(r3, r4)
            com.unionpay.a.d r3 = new com.unionpay.a.d
            r3.<init>(r1)
            Q = r3
            java.lang.String r0 = a((android.content.Context) r0, (boolean) r2)
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "postdata: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.unionpay.utils.j.b(r1, r3)
            com.unionpay.a.d r1 = Q
            r1.a(r0)
            android.os.Handler r0 = new android.os.Handler
            android.os.Handler$Callback r1 = Z
            r0.<init>(r1)
            R = r0
            com.unionpay.d r0 = new com.unionpay.d
            r0.<init>()
            r0.start()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayAssistEx.s():int");
    }

    public static int startPay(Context context, String str, String str2, String str3, String str4) {
        return a(context, str, str2, str3, str4, "", "");
    }

    public static void startPayByJAR(Context context, Class cls, String str, String str2, String str3, String str4) {
        startPay(context, str, str2, str3, str4);
    }

    public static int startPayFromBrowser(Activity activity, String str, String str2) {
        return a(activity, str, str2);
    }

    public static int startSEPay(Context context, String str, String str2, String str3, String str4, String str5) {
        return a(context, str, str2, str3, str4, "", str5);
    }

    public static void startSamsungPay(Context context, Class cls, String str, String str2, String str3, String str4) {
        a(context, str, str2, str3, str4, p, v);
    }
}
