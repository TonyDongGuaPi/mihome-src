package com.ximalaya.ting.android.player.cdn;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.stat.a.j;
import com.xiaomi.stat.b;
import com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel;
import com.ximalaya.ting.android.player.Logger;
import com.ximalaya.ting.android.player.PlayerUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CdnUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2300a = "http://xdcs-collector.test.ximalaya.com/api/v1/cdnAndroid";
    private static final String b = "http://xdcs-collector.ximalaya.com/api/v1/cdnAndroid";
    private static CdnConfigModel c;
    private static HttpURLConnection d;
    private static OutputStream e;
    private static InputStream f;

    public static void a(String str, String str2) {
    }

    private CdnUtil() {
    }

    public static void a(CdnConfigModel cdnConfigModel) {
        c = cdnConfigModel;
    }

    public static CdnConfigModel a() {
        return c;
    }

    public static void a(CdnCollectDataForPlay cdnCollectDataForPlay, CdnConfigModel cdnConfigModel) {
        String str;
        if (cdnCollectDataForPlay != null && cdnConfigModel != null) {
            try {
                CdnEvent cdnEvent = new CdnEvent();
                cdnEvent.e("CDN");
                cdnEvent.a(cdnCollectDataForPlay);
                cdnEvent.a(System.currentTimeMillis());
                ArrayList arrayList = new ArrayList();
                arrayList.add(cdnEvent);
                String a2 = a((List<CdnEvent>) arrayList);
                String a3 = cdnConfigModel.a();
                String b2 = cdnConfigModel.b();
                StringBuffer stringBuffer = new StringBuffer();
                HashMap<String, String> c2 = cdnConfigModel.c();
                if (c2 != null) {
                    for (String next : c2.keySet()) {
                        stringBuffer.append(next);
                        stringBuffer.append("=");
                        stringBuffer.append(c2.get(next));
                        stringBuffer.append(i.b);
                    }
                }
                HashMap hashMap = new HashMap();
                hashMap.put("Cookie", stringBuffer.toString() + "domain=" + a3 + ";path=" + b2);
                hashMap.put("User-Agent", cdnConfigModel.d());
                hashMap.put("Content-Length", String.valueOf(a2.length()));
                hashMap.put("Content-Type", "application/json");
                if (TextUtils.isEmpty(cdnConfigModel.e())) {
                    str = b;
                } else {
                    str = cdnConfigModel.e();
                }
                d = PlayerUtil.a(str, "POST", (Map<String, String>) hashMap);
                d.connect();
                e = d.getOutputStream();
                e.write(a2.getBytes("utf-8"));
                e.flush();
                e.close();
                int responseCode = d.getResponseCode();
                Logger.a((Object) "post to xdcs url: " + d.getURL());
                PrintStream printStream = System.out;
                printStream.println("post to xdcs Response Code : " + responseCode);
                f = d.getInputStream();
                if (f != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(f));
                    StringBuffer stringBuffer2 = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer2.append(readLine);
                    }
                    Logger.a((Object) stringBuffer2.toString());
                }
                if (e != null) {
                    try {
                        e.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (f != null) {
                    try {
                        f.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (d == null) {
                    return;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                if (e != null) {
                    try {
                        e.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                if (f != null) {
                    try {
                        f.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
                if (d == null) {
                    return;
                }
            } catch (Throwable th) {
                if (e != null) {
                    try {
                        e.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
                if (f != null) {
                    try {
                        f.close();
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                }
                if (d != null) {
                    d.disconnect();
                    d = null;
                }
                throw th;
            }
            d.disconnect();
            d = null;
        }
    }

    public static String a(List<CdnEvent> list) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (CdnEvent next : list) {
            if (next != null) {
                JSONObject jSONObject2 = new JSONObject();
                if (!TextUtils.isEmpty(next.a())) {
                    jSONObject2.put("viewId", next.a());
                }
                if (!TextUtils.isEmpty(next.b())) {
                    jSONObject2.put("parentSpanId", next.b());
                }
                if (!TextUtils.isEmpty(next.d())) {
                    jSONObject2.put("viewId", next.d());
                }
                if (!TextUtils.isEmpty(next.e())) {
                    jSONObject2.put("viewId", next.e());
                }
                if (next.f() >= 0) {
                    jSONObject2.put("ts", next.f());
                }
                if (next.c() >= 0) {
                    jSONObject2.put("seqId", next.c());
                }
                if (!TextUtils.isEmpty(next.g())) {
                    jSONObject2.put("type", next.g());
                }
                if (next.h() != null) {
                    CdnCollectDataForPlay h = next.h();
                    JSONObject jSONObject3 = new JSONObject();
                    if (!TextUtils.isEmpty(h.g())) {
                        jSONObject3.put("audioUrl", h.g());
                    }
                    if (!TextUtils.isEmpty(h.i())) {
                        jSONObject3.put("cdnIP", h.i());
                    }
                    if (!TextUtils.isEmpty(h.k())) {
                        jSONObject3.put("downloadSpeed", h.k() + "");
                    } else {
                        jSONObject3.put("downloadSpeed", b.m);
                    }
                    if (!TextUtils.isEmpty(h.m())) {
                        jSONObject3.put("errorType", h.m());
                    }
                    if (!TextUtils.isEmpty(h.l())) {
                        jSONObject3.put("exceptionReason", h.l());
                    }
                    if (!TextUtils.isEmpty(h.n())) {
                        jSONObject3.put("statusCode", h.n());
                    }
                    if (!TextUtils.isEmpty(h.f())) {
                        jSONObject3.put("type", h.f());
                    }
                    if (!TextUtils.isEmpty(h.o())) {
                        jSONObject3.put("viaInfo", h.o());
                    }
                    if (h.h() > 0) {
                        jSONObject3.put("audioBytes", h.h() + "");
                    } else {
                        jSONObject3.put("audioBytes", 0);
                    }
                    if (h.p() >= 0.0f) {
                        jSONObject3.put("connectedTime", h.p() + "");
                    }
                    if (h.q() >= 0) {
                        jSONObject3.put("timestamp", h.q());
                    }
                    if (h.d() != null) {
                        jSONObject3.put(Common.v, h.d());
                    }
                    if (h.e() != null) {
                        jSONObject3.put("fileSize", h.e());
                    }
                    if (!TextUtils.isEmpty(h.a())) {
                        jSONObject3.put("downloaded", h.a());
                    } else {
                        jSONObject3.put("downloaded", "");
                    }
                    if (!TextUtils.isEmpty(h.b())) {
                        jSONObject3.put("downloadTime", h.b());
                    } else {
                        jSONObject3.put("downloadTime", "");
                    }
                    if (!TextUtils.isEmpty(h.c())) {
                        jSONObject3.put("downloadResult", h.c());
                    } else {
                        jSONObject3.put("downloadResult", "failed");
                    }
                    if (!TextUtils.isEmpty(h.r())) {
                        jSONObject3.put("cdnDomain", h.r());
                    }
                    jSONObject3.put("timeout", h.j());
                    jSONObject2.put("props", jSONObject3);
                }
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put(j.b, jSONArray);
        return jSONObject.toString();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return InetAddress.getByName(b(str)).getHostAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(Throwable th) {
        StackTraceElement stackTraceElement = th.getStackTrace()[0];
        if (stackTraceElement == null) {
            return th.getMessage();
        }
        if (!TextUtils.isEmpty(th.getMessage())) {
            return th.getMessage();
        }
        return "null  located at:" + stackTraceElement.toString();
    }

    private static String b(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        Matcher matcher = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static float a(float f2, boolean z) {
        String str;
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (z) {
            str = new DecimalFormat(".0").format((double) f2);
        } else if (((double) f2) < 0.1d) {
            return 0.001f;
        } else {
            str = new DecimalFormat(".000").format((double) (f2 / 1000.0f));
        }
        try {
            return Float.valueOf(str).floatValue();
        } catch (Exception unused) {
            return f2;
        }
    }
}
