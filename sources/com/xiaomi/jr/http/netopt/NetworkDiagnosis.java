package com.xiaomi.jr.http.netopt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.NetworkStatusReceiver;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.http.netopt.Ping;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class NetworkDiagnosis {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Integer> f1437a = new HashMap();
    private static final String b = "NetworkDiagnosis";
    private static final String c = "http://resolver.gslb.mi-idc.com/gslb/gslb/getbucket.asp?deviceType=0&list=%s&ver=3.0";
    private static boolean d = false;
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static NetworkDiagnosis e;
    private DiagnosisApi f;
    /* access modifiers changed from: private */
    public Context g;
    private Executor h = Executors.newCachedThreadPool();
    private String i;
    private Gslb j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public Map<Long, String> l = new ConcurrentHashMap();
    private TimeoutHandler m = new TimeoutHandler(Looper.getMainLooper());
    private NetworkStatusReceiver.NetworkStatusListener n = new NetworkStatusReceiver.NetworkStatusListener() {
        public void a(Context context, NetworkInfo networkInfo) {
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                DiagnosisCache.a(NetworkDiagnosis.this.g);
                boolean unused = NetworkDiagnosis.this.k = false;
                NetworkDiagnosis.this.c();
            }
        }
    };
    private String o;

    private static class TimeoutHandler extends Handler {
        TimeoutHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Long l = (Long) message.obj;
            if (NetworkDiagnosis.e.l.containsKey(l)) {
                NetworkDiagnosis.e.c((String) NetworkDiagnosis.e.l.get(l));
                NetworkDiagnosis.e.l.remove(l);
            }
        }
    }

    public static NetworkDiagnosis a() {
        return e;
    }

    public static void a(@NonNull Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        e = new NetworkDiagnosis(applicationContext);
        e.b(str);
        if (d) {
            NetworkStatusReceiver.addNetworkStatusListener(applicationContext, e.n, true);
            f1437a.put("WIFI", 10000);
            f1437a.put("2G", 30000);
            f1437a.put("3G", 10000);
            f1437a.put("4G", 10000);
        }
    }

    private NetworkDiagnosis(Context context) {
        this.g = context;
        this.f = (DiagnosisApi) MifiHttpManager.a().a(DiagnosisApi.class);
    }

    private void b(String str) {
        this.i = String.format(Locale.getDefault(), c, new Object[]{str});
        this.o = str;
    }

    public boolean a(String str) {
        if (d && str != null && TextUtils.equals(Uri.parse(str).getHost(), this.o)) {
            return true;
        }
        return false;
    }

    public void a(long j2, String str) {
        if (d && !this.l.containsKey(Long.valueOf(j2))) {
            this.l.put(Long.valueOf(j2), str);
            Message obtain = Message.obtain();
            obtain.obj = Long.valueOf(j2);
            this.m.sendMessageDelayed(obtain, (long) a(this.g));
        }
    }

    public void a(long j2) {
        if (d && this.l.containsKey(Long.valueOf(j2))) {
            if (System.currentTimeMillis() - j2 > ((long) a(this.g))) {
                c(this.l.get(Long.valueOf(j2)));
            }
            this.l.remove(Long.valueOf(j2));
        }
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        MifiLog.c(b, "diagnoseIfNeeded: " + str);
        if (!this.k && NetworkUtils.b(this.g)) {
            this.k = true;
            MifiLog.c(b, "start diagnosis...");
            this.h.execute(new Runnable() {
                public final void run() {
                    NetworkDiagnosis.this.l();
                }
            });
        }
        DiagnosisCache.b(this.g, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void l() {
        JSONObject d2 = new NetworkDiagnosis(this.g).d();
        if (d2 != null) {
            DiagnosisCache.a(this.g, d2.toString());
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            String[] b2 = DiagnosisCache.b(this.g);
            if (b2 == null) {
                MifiLog.c(b, "no cached diagnosis to report");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("reason", b2[1]);
            jSONObject.put(LogCategory.CATEGORY_NETWORK, new JSONObject(b2[0]));
            this.f.a(jSONObject.toString()).enqueue(new Callback<MiFiResponse<Void>>() {
                public void onResponse(Call<MiFiResponse<Void>> call, Response<MiFiResponse<Void>> response) {
                    if (response.isSuccessful()) {
                        DiagnosisCache.c(NetworkDiagnosis.this.g);
                    } else {
                        MifiLog.d(NetworkDiagnosis.b, "report network diagnosis fail: response code not OK");
                    }
                }

                public void onFailure(Call<MiFiResponse<Void>> call, Throwable th) {
                    MifiLog.d(NetworkDiagnosis.b, "report network diagnosis fail: " + th.getMessage());
                }
            });
        } catch (JSONException e2) {
            MifiLog.b(b, "report network diagnosis exception: " + e2.getMessage());
        }
    }

    private JSONObject d() {
        e();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", System.currentTimeMillis());
            NetworkInfo a2 = NetworkUtils.a(this.g);
            jSONObject.put(StatType.NETWORKINFO, a2 != null ? a2.toString() : null);
            jSONObject.put("localIP", f());
            List<String> g2 = g();
            if (g2 != null) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                for (String put : g2) {
                    jSONArray.put(put);
                }
                jSONObject2.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, jSONArray);
                if (g2.size() > 0) {
                    Ping.PingResult a3 = Ping.a(g2.get(0));
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("accessible", a3.f10838a);
                    if (a3.f10838a) {
                        jSONObject3.put("avg", String.format("%.3f", new Object[]{Float.valueOf(a3.c)}));
                    }
                    jSONObject2.put("test", jSONObject3);
                    Map<String, String> h2 = h();
                    if (h2 != null) {
                        JSONObject jSONObject4 = new JSONObject();
                        for (String next : h2.keySet()) {
                            jSONObject4.put(next, h2.get(next));
                        }
                        jSONObject2.put("resolve", jSONObject4);
                    }
                }
                jSONObject.put("dns", jSONObject2);
            }
            jSONObject.put("gatewayIP", i());
            JSONObject jSONObject5 = new JSONObject();
            Map<String, Float> j2 = j();
            if (j2 != null) {
                JSONObject jSONObject6 = new JSONObject();
                for (String next2 : j2.keySet()) {
                    jSONObject6.put(next2, j2.get(next2));
                }
                jSONObject5.put("ping", jSONObject6);
            }
            Map<String, Map<String, Float>> k2 = k();
            if (k2 != null) {
                JSONObject jSONObject7 = new JSONObject();
                for (String next3 : k2.keySet()) {
                    Map map = k2.get(next3);
                    JSONArray jSONArray2 = new JSONArray();
                    for (String str : map.keySet()) {
                        JSONObject jSONObject8 = new JSONObject();
                        jSONObject8.put(str, map.get(str));
                        jSONArray2.put(jSONObject8);
                    }
                    jSONObject7.put(next3, jSONArray2);
                }
                jSONObject5.put("traceroute", jSONObject7);
            }
            jSONObject.put("server", jSONObject5);
            MifiLog.c(b, "end diagnosis: " + jSONObject.toString());
            return jSONObject;
        } catch (JSONException e2) {
            MifiLog.b(b, "diagnosis exception: " + e2.getMessage());
            return null;
        }
    }

    private void e() {
        this.j = Gslb.a(this.i);
    }

    private String f() {
        return NetworkUtils.a(true);
    }

    private List<String> g() {
        ArrayList arrayList = new ArrayList();
        try {
            Process exec = Runtime.getRuntime().exec("getprop");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, ":");
                    if (TextUtils.indexOf(stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "", "dns") != -1) {
                        String nextToken = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "";
                        if (!TextUtils.isEmpty(nextToken)) {
                            String replaceAll = nextToken.replaceAll("[ \\[\\]]", "");
                            if (!TextUtils.isEmpty(replaceAll)) {
                                arrayList.add(replaceAll);
                            }
                        }
                    }
                } else {
                    exec.destroy();
                    return arrayList;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    private Map<String, String> h() {
        if (this.j == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : (NetworkUtils.e(this.g) ? this.j.f : this.j.g).keySet()) {
            try {
                hashMap.put(next, InetAddress.getByName(next).getHostAddress());
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    private String i() {
        if (this.j != null) {
            return this.j.e;
        }
        return null;
    }

    private Map<String, Float> j() {
        if (this.j == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> it = a((Map<String, ArrayList<String>>) NetworkUtils.e(this.g) ? this.j.f : this.j.g).iterator();
        while (it.hasNext()) {
            String next = it.next();
            hashMap.put(next, Float.valueOf(Ping.a(next).c));
        }
        return hashMap;
    }

    private Map<String, Map<String, Float>> k() {
        if (this.j == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> it = a((Map<String, ArrayList<String>>) NetworkUtils.e(this.g) ? this.j.f : this.j.g).iterator();
        while (it.hasNext()) {
            String next = it.next();
            hashMap.put(next, Traceroute.a(next));
        }
        return hashMap;
    }

    private ArrayList<String> a(Map<String, ArrayList<String>> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (ArrayList<String> it : map.values()) {
            Iterator it2 = it.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static int a(Context context) {
        Integer num = f1437a.get(NetworkUtils.c(context.getApplicationContext()));
        if (num != null) {
            return num.intValue();
        }
        return 10000;
    }
}
