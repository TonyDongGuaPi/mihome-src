package com.xiaomi.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class db {

    /* renamed from: a  reason: collision with root package name */
    protected static Context f12685a;

    /* renamed from: a  reason: collision with other field name */
    private static a f56a;

    /* renamed from: a  reason: collision with other field name */
    private static db f57a;

    /* renamed from: a  reason: collision with other field name */
    protected static boolean f58a = false;
    protected static Map<String, cx> b = new HashMap();
    private static String c;
    private static String d;

    /* renamed from: a  reason: collision with other field name */
    private long f59a;

    /* renamed from: a  reason: collision with other field name */
    private da f60a;

    /* renamed from: a  reason: collision with other field name */
    protected b f61a;

    /* renamed from: a  reason: collision with other field name */
    private String f62a;

    /* renamed from: a  reason: collision with other field name */
    protected Map<String, cy> f63a;

    /* renamed from: b  reason: collision with other field name */
    private final long f64b;

    /* renamed from: b  reason: collision with other field name */
    private String f65b;

    /* renamed from: c  reason: collision with other field name */
    private long f66c;

    public interface a {
        db a(Context context, da daVar, b bVar, String str);
    }

    public interface b {
        String a(String str);
    }

    protected db(Context context, da daVar, b bVar, String str) {
        this(context, daVar, bVar, str, (String) null, (String) null);
    }

    protected db(Context context, da daVar, b bVar, String str, String str2, String str3) {
        this.f63a = new HashMap();
        this.f62a = "0";
        this.f59a = 0;
        this.f64b = 15;
        this.f66c = 0;
        this.f65b = "isp_prov_city_country_ip";
        this.f61a = bVar;
        this.f60a = daVar == null ? new dc(this) : daVar;
        this.f62a = str;
        c = str2 == null ? context.getPackageName() : str2;
        d = str3 == null ? f() : str3;
    }

    public static synchronized db a() {
        db dbVar;
        synchronized (db.class) {
            if (f57a != null) {
                dbVar = f57a;
            } else {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
        }
        return dbVar;
    }

    /* renamed from: a  reason: collision with other method in class */
    static String m109a() {
        NetworkInfo activeNetworkInfo;
        if (f12685a == null) {
            return "unknown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) f12685a.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                WifiManager wifiManager = (WifiManager) f12685a.getSystemService("wifi");
                if (wifiManager == null || wifiManager.getConnectionInfo() == null) {
                    return "unknown";
                }
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    static String a(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b2 = bytes[i];
                byte b3 = b2 & 240;
                if (b3 != 240) {
                    bytes[i] = (byte) (((b2 & 15) ^ ((byte) (((b2 >> 4) + length) & 15))) | b3);
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    private ArrayList<cx> a(ArrayList<String> arrayList) {
        boolean z;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        ArrayList<String> arrayList2 = arrayList;
        d();
        synchronized (this.f63a) {
            a();
            for (String next : this.f63a.keySet()) {
                if (!arrayList2.contains(next)) {
                    arrayList2.add(next);
                }
            }
        }
        boolean isEmpty = b.isEmpty();
        synchronized (b) {
            z = isEmpty;
            for (Object obj : b.values().toArray()) {
                cx cxVar = (cx) obj;
                if (!cxVar.b()) {
                    b.remove(cxVar.b);
                    z = true;
                }
            }
        }
        if (!arrayList2.contains(b())) {
            arrayList2.add(b());
        }
        ArrayList<cx> arrayList3 = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList3.add((Object) null);
        }
        try {
            String str = az.e(f12685a) ? "wifi" : "wap";
            String a2 = a(arrayList2, str, this.f62a, z);
            if (!TextUtils.isEmpty(a2)) {
                JSONObject jSONObject3 = new JSONObject(a2);
                com.xiaomi.channel.commonutils.logger.b.b(a2);
                if ("OK".equalsIgnoreCase(jSONObject3.getString("S"))) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("R");
                    String string = jSONObject4.getString("province");
                    String string2 = jSONObject4.getString("city");
                    String string3 = jSONObject4.getString("isp");
                    String string4 = jSONObject4.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP);
                    String string5 = jSONObject4.getString("country");
                    JSONObject jSONObject5 = jSONObject4.getJSONObject(str);
                    com.xiaomi.channel.commonutils.logger.b.c("get bucket: ip = " + string4 + " net = " + string3 + " hosts = " + jSONObject5.toString());
                    int i2 = 0;
                    while (i2 < arrayList.size()) {
                        String str2 = arrayList2.get(i2);
                        JSONArray optJSONArray = jSONObject5.optJSONArray(str2);
                        if (optJSONArray == null) {
                            com.xiaomi.channel.commonutils.logger.b.a("no bucket found for " + str2);
                            jSONObject = jSONObject5;
                        } else {
                            cx cxVar2 = new cx(str2);
                            int i3 = 0;
                            while (i3 < optJSONArray.length()) {
                                String string6 = optJSONArray.getString(i3);
                                if (!TextUtils.isEmpty(string6)) {
                                    jSONObject2 = jSONObject5;
                                    cxVar2.a(new dg(string6, optJSONArray.length() - i3));
                                } else {
                                    jSONObject2 = jSONObject5;
                                }
                                i3++;
                                jSONObject5 = jSONObject2;
                            }
                            jSONObject = jSONObject5;
                            arrayList3.set(i2, cxVar2);
                            cxVar2.g = string5;
                            cxVar2.c = string;
                            cxVar2.e = string3;
                            cxVar2.f = string4;
                            cxVar2.d = string2;
                            if (jSONObject4.has("stat-percent")) {
                                cxVar2.a(jSONObject4.getDouble("stat-percent"));
                            }
                            if (jSONObject4.has("stat-domain")) {
                                cxVar2.c(jSONObject4.getString("stat-domain"));
                            }
                            if (jSONObject4.has("ttl")) {
                                cxVar2.a(((long) jSONObject4.getInt("ttl")) * 1000);
                            }
                            a(cxVar2.e());
                        }
                        i2++;
                        jSONObject5 = jSONObject;
                    }
                    JSONObject optJSONObject = jSONObject4.optJSONObject("reserved");
                    if (optJSONObject != null) {
                        long j = DateUtils.d;
                        if (jSONObject4.has("reserved-ttl")) {
                            j = ((long) jSONObject4.getInt("reserved-ttl")) * 1000;
                        }
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next2 = keys.next();
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray(next2);
                            if (optJSONArray2 == null) {
                                com.xiaomi.channel.commonutils.logger.b.a("no bucket found for " + next2);
                            } else {
                                cx cxVar3 = new cx(next2);
                                cxVar3.a(j);
                                for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                                    String string7 = optJSONArray2.getString(i4);
                                    if (!TextUtils.isEmpty(string7)) {
                                        cxVar3.a(new dg(string7, optJSONArray2.length() - i4));
                                    }
                                }
                                synchronized (b) {
                                    if (this.f60a.a(next2)) {
                                        b.put(next2, cxVar3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("failed to get bucket " + e.getMessage());
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            cx cxVar4 = arrayList3.get(i5);
            if (cxVar4 != null) {
                a(arrayList2.get(i5), cxVar4);
            }
        }
        c();
        return arrayList3;
    }

    public static synchronized void a(Context context, da daVar, b bVar, String str, String str2, String str3) {
        synchronized (db.class) {
            f12685a = context.getApplicationContext();
            if (f12685a == null) {
                f12685a = context;
            }
            if (f57a == null) {
                if (f56a == null) {
                    f57a = new db(context, daVar, bVar, str, str2, str3);
                } else {
                    f57a = f56a.a(context, daVar, bVar, str);
                }
            }
        }
    }

    public static synchronized void a(a aVar) {
        synchronized (db.class) {
            f56a = aVar;
            f57a = null;
        }
    }

    public static void a(String str, String str2) {
        cx cxVar = b.get(str);
        synchronized (b) {
            if (cxVar == null) {
                try {
                    cx cxVar2 = new cx(str);
                    cxVar2.a((long) DateUtils.d);
                    cxVar2.b(str2);
                    b.put(str, cxVar2);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                cxVar.b(str2);
            }
        }
    }

    private String f() {
        try {
            PackageInfo packageInfo = f12685a.getPackageManager().getPackageInfo(f12685a.getPackageName(), 16384);
            return packageInfo != null ? packageInfo.versionName : "0";
        } catch (Exception unused) {
            return "0";
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public cx m110a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return a(new URL(str).getHost(), true);
        }
        throw new IllegalArgumentException("the url is empty");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r4 = d(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.push.cx a(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0034
            com.xiaomi.push.da r0 = r2.f60a
            boolean r0 = r0.a(r3)
            if (r0 != 0) goto L_0x0010
            r3 = 0
            return r3
        L_0x0010:
            com.xiaomi.push.cx r0 = r2.c(r3)
            if (r0 == 0) goto L_0x001d
            boolean r1 = r0.b()
            if (r1 == 0) goto L_0x001d
            return r0
        L_0x001d:
            if (r4 == 0) goto L_0x002e
            android.content.Context r4 = f12685a
            boolean r4 = com.xiaomi.push.az.c(r4)
            if (r4 == 0) goto L_0x002e
            com.xiaomi.push.cx r4 = r2.d(r3)
            if (r4 == 0) goto L_0x002e
            return r4
        L_0x002e:
            com.xiaomi.push.dd r4 = new com.xiaomi.push.dd
            r4.<init>(r2, r3, r0)
            return r4
        L_0x0034:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "the host is empty"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.db.a(java.lang.String, boolean):com.xiaomi.push.cx");
    }

    /* access modifiers changed from: protected */
    public String a(ArrayList<String> arrayList, String str, String str2, boolean z) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<ay> arrayList3 = new ArrayList<>();
        arrayList3.add(new aw("type", str));
        if (str.equals("wap")) {
            arrayList3.add(new aw("conpt", a(az.k(f12685a))));
        }
        if (z) {
            arrayList3.add(new aw("reserved", "1"));
        }
        arrayList3.add(new aw("uuid", str2));
        arrayList3.add(new aw("list", bf.a((Collection<?>) arrayList, ",")));
        arrayList3.add(new aw("countrycode", com.xiaomi.push.service.a.a(f12685a).b()));
        cx c2 = c(b());
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{b()});
        if (c2 == null) {
            arrayList2.add(format);
            synchronized (b) {
                cx cxVar = b.get("resolver.msg.xiaomi.net");
                if (cxVar != null) {
                    Iterator<String> it = cxVar.a(true).iterator();
                    while (it.hasNext()) {
                        arrayList2.add(String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", new Object[]{it.next()}));
                    }
                }
            }
        } else {
            arrayList2 = c2.a(format);
        }
        Iterator<String> it2 = arrayList2.iterator();
        IOException e = null;
        while (it2.hasNext()) {
            Uri.Builder buildUpon = Uri.parse(it2.next()).buildUpon();
            for (ay ayVar : arrayList3) {
                buildUpon.appendQueryParameter(ayVar.a(), ayVar.b());
            }
            try {
                return this.f61a == null ? az.a(f12685a, new URL(buildUpon.toString())) : this.f61a.a(buildUpon.toString());
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e == null) {
            return null;
        }
        com.xiaomi.channel.commonutils.logger.b.a("network exception: " + e.getMessage());
        throw e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a  reason: collision with other method in class */
    public JSONObject m111a() {
        JSONObject jSONObject;
        synchronized (this.f63a) {
            jSONObject = new JSONObject();
            jSONObject.put(DeviceTagInterface.m, 2);
            JSONArray jSONArray = new JSONArray();
            for (cy d2 : this.f63a.values()) {
                jSONArray.put(d2.d());
            }
            jSONObject.put("data", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (cx f : b.values()) {
                jSONArray2.put(f.f());
            }
            jSONObject.put("reserved", jSONArray2);
        }
        return jSONObject;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m112a() {
        synchronized (this.f63a) {
            this.f63a.clear();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m113a(String str) {
        this.f65b = str;
    }

    public void a(String str, cx cxVar) {
        if (TextUtils.isEmpty(str) || cxVar == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + cxVar);
        } else if (this.f60a.a(str)) {
            synchronized (this.f63a) {
                a();
                if (this.f63a.containsKey(str)) {
                    this.f63a.get(str).a(cxVar);
                } else {
                    cy cyVar = new cy(str);
                    cyVar.a(cxVar);
                    this.f63a.put(str, cyVar);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a  reason: collision with other method in class */
    public boolean m114a() {
        synchronized (this.f63a) {
            if (f58a) {
                return true;
            }
            f58a = true;
            this.f63a.clear();
            try {
                String d2 = d();
                if (!TextUtils.isEmpty(d2)) {
                    b(d2);
                    com.xiaomi.channel.commonutils.logger.b.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a("load bucket failure: " + th.getMessage());
            }
        }
        return false;
    }

    public cx b(String str) {
        return a(str, true);
    }

    /* access modifiers changed from: protected */
    public String b() {
        String a2 = com.xiaomi.push.service.a.a(f12685a).a();
        return (TextUtils.isEmpty(a2) || PushChannelRegion.China.name().equals(a2)) ? "resolver.msg.xiaomi.net" : "resolver.msg.global.xiaomi.net";
    }

    /* renamed from: b  reason: collision with other method in class */
    public void m115b() {
        ArrayList arrayList;
        synchronized (this.f63a) {
            a();
            arrayList = new ArrayList(this.f63a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                cy cyVar = this.f63a.get(arrayList.get(size));
                if (!(cyVar == null || cyVar.a() == null)) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList<cx> a2 = a((ArrayList<String>) arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (a2.get(i) != null) {
                a((String) arrayList.get(i), a2.get(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b  reason: collision with other method in class */
    public void m116b(String str) {
        synchronized (this.f63a) {
            this.f63a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(DeviceTagInterface.m) == 2) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    cy a2 = new cy().a(optJSONArray.getJSONObject(i));
                    this.f63a.put(a2.c(), a2);
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    cx a3 = new cx("").a(optJSONArray2.getJSONObject(i2));
                    b.put(a3.b, a3);
                }
            } else {
                throw new JSONException("Bad version");
            }
        }
    }

    /* access modifiers changed from: protected */
    public cx c(String str) {
        cy cyVar;
        cx a2;
        synchronized (this.f63a) {
            a();
            cyVar = this.f63a.get(str);
        }
        if (cyVar == null || (a2 = cyVar.a()) == null) {
            return null;
        }
        return a2;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.f63a) {
            for (Map.Entry next : this.f63a.entrySet()) {
                sb.append((String) next.getKey());
                sb.append(":\n");
                sb.append(((cy) next.getValue()).toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: c  reason: collision with other method in class */
    public void m117c() {
        synchronized (this.f63a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(f12685a.openFileOutput(e(), 0)));
                String jSONObject = a().toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    bufferedWriter.write(jSONObject);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public cx d(String str) {
        if (System.currentTimeMillis() - this.f66c <= this.f59a * 60 * 1000) {
            return null;
        }
        this.f66c = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        cx cxVar = a((ArrayList<String>) arrayList).get(0);
        if (cxVar != null) {
            this.f59a = 0;
            return cxVar;
        } else if (this.f59a >= 15) {
            return null;
        } else {
            this.f59a++;
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String d() {
        BufferedReader bufferedReader;
        try {
            File file = new File(f12685a.getFilesDir(), e());
            if (file.isFile()) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            String sb2 = sb.toString();
                            y.a((Closeable) bufferedReader);
                            return sb2;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        com.xiaomi.channel.commonutils.logger.b.a("load host exception " + th.getMessage());
                        y.a((Closeable) bufferedReader);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        y.a((Closeable) bufferedReader);
                        throw th;
                    }
                }
            } else {
                y.a((Closeable) null);
                return null;
            }
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            y.a((Closeable) bufferedReader);
            throw th;
        }
    }

    /* renamed from: d  reason: collision with other method in class */
    public void m118d() {
        synchronized (this.f63a) {
            for (cy a2 : this.f63a.values()) {
                a2.a(true);
            }
            while (true) {
                for (boolean z = false; !z; z = true) {
                    for (String next : this.f63a.keySet()) {
                        if (this.f63a.get(next).b().isEmpty()) {
                            this.f63a.remove(next);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String e() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) f12685a.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "com.xiaomi";
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == Process.myPid()) {
                return next.processName;
            }
        }
        return "com.xiaomi";
    }
}
