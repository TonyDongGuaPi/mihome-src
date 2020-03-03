package com.xiaomi.voiceassistant.mijava;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.sdk.sys.a;
import com.google.android.gms.actions.SearchIntents;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class NLProcessor {

    /* renamed from: a  reason: collision with root package name */
    public static int f23141a = 0;
    public static String b = "";
    private static final String c = "https://nlp.ai.xiaomi.com/2.0/answer";
    private static final String d = "https://nlp-preview.ai.xiaomi.com/2.0/answer";
    private static final String e = "http://nlp-staging.ai.xiaomi.com/2.0/answer";
    private static final String f = "2.2";
    private final String g;
    private JSONObject h = new JSONObject();
    private JSONObject i = new JSONObject();
    private JSONObject j = new JSONObject();
    private JSONArray k = new JSONArray();
    private JSONObject l = new JSONObject();
    private ConnectivityManager m;
    private JSONObject n = new JSONObject();
    private JSONObject o = new JSONObject();
    private String p;
    private String q;
    private String r;

    public NLProcessor(Context context) {
        this.m = (ConnectivityManager) context.getSystemService("connectivity");
        this.g = a(DeviceIdCompat.a(context));
    }

    public void a(String str, String str2, String str3) {
        this.p = str;
        this.q = str2;
        this.r = str3;
    }

    public boolean a() {
        NetworkInfo activeNetworkInfo = this.m.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public JSONObject a(String str, JSONObject jSONObject) throws Exception {
        if (a()) {
            String b2 = b();
            String b3 = b(str, jSONObject);
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            RequestBody create = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), b3);
            builder.url(b2);
            builder.post(create);
            return new JSONObject(okHttpClient.newCall(builder.build()).execute().body().string());
        }
        throw new IOException("network unavailable");
    }

    private String b() {
        if (f23141a == 0) {
            return c;
        }
        if (f23141a == 1) {
            return d;
        }
        return f23141a == 2 ? e : c;
    }

    private String b(String str, JSONObject jSONObject) {
        NetworkInfo activeNetworkInfo = this.m.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            try {
                this.h.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, c());
                this.h.put(LogCategory.CATEGORY_NETWORK, activeNetworkInfo.getType() == 0 ? "mobile" : "wifi");
                this.i.put("miot_token", this.q);
                this.i.put("miot_session", this.r);
                this.j.put("id", this.p);
                this.j.put("id_type", "xiaomi_id");
                this.j.put("service_id", MiBrainCloudSDKManager.e());
                this.j.put("auth_token", MiBrainCloudSDKManager.f());
                this.j.put(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND, this.i);
                this.o.put("post_back", jSONObject);
                this.l.put(SearchIntents.EXTRA_QUERY, str);
                this.l.put("confidence", 1.0d);
                this.k.put(this.l);
                this.n.put("is_new", false);
                this.n.put("id", b);
                this.n.put("start_timestamp", System.currentTimeMillis());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return TextUtils.join(a.b, new String[]{"app_id=" + MiBrainCloudSDKManager.e(), "token=" + MiBrainCloudSDKManager.f(), "timestamp=" + System.currentTimeMillis(), "queries=" + this.k.toString(), "device_id=" + this.g, "device=" + this.h.toString(), "session=" + this.n.toString(), "user_info=" + this.j.toString(), "context=" + this.o.toString(), "version=2.2"});
    }

    private String c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b2 : digest) {
                byte b3 = b2 & 255;
                if (b3 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
