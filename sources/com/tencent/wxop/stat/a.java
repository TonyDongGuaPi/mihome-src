package com.tencent.wxop.stat;

import android.content.Context;
import android.content.IntentFilter;
import com.alipay.sdk.util.i;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.tencent.wxop.stat.common.StatConstants;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.e;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.q;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class a {
    private static a g;

    /* renamed from: a  reason: collision with root package name */
    private List<String> f9270a = null;
    private volatile int b = 2;
    private volatile String c = "";
    private volatile HttpHost d = null;
    /* access modifiers changed from: private */
    public e e = null;
    private int f = 0;
    private Context h = null;
    private StatLogger i = null;

    private a(Context context) {
        this.h = context.getApplicationContext();
        this.e = new e();
        i.a(context);
        this.i = k.b();
        l();
        i();
        g();
    }

    public static a a(Context context) {
        if (g == null) {
            synchronized (a.class) {
                if (g == null) {
                    g = new a(context);
                }
            }
        }
        return g;
    }

    private boolean b(String str) {
        return Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})").matcher(str).matches();
    }

    private void i() {
        this.f9270a = new ArrayList(10);
        this.f9270a.add("117.135.169.101");
        this.f9270a.add("140.207.54.125");
        this.f9270a.add("180.153.8.53");
        this.f9270a.add("120.198.203.175");
        this.f9270a.add("14.17.43.18");
        this.f9270a.add("163.177.71.186");
        this.f9270a.add("111.30.131.31");
        this.f9270a.add("123.126.121.167");
        this.f9270a.add("123.151.152.111");
        this.f9270a.add("113.142.45.79");
        this.f9270a.add("123.138.162.90");
        this.f9270a.add("103.7.30.94");
    }

    private String j() {
        try {
            return !b(StatConstants.b) ? InetAddress.getByName(StatConstants.b).getHostAddress() : "";
        } catch (Exception e2) {
            this.i.b((Throwable) e2);
            return "";
        }
    }

    private void k() {
        String j = j();
        if (StatConfig.b()) {
            StatLogger statLogger = this.i;
            statLogger.b((Object) "remoteIp ip is " + j);
        }
        if (k.c(j)) {
            if (!this.f9270a.contains(j)) {
                String str = this.f9270a.get(this.f);
                if (StatConfig.b()) {
                    StatLogger statLogger2 = this.i;
                    statLogger2.f(j + " not in ip list, change to:" + str);
                }
                j = str;
            }
            StatConfig.d(ConnectionHelper.HTTP_PREFIX + j + ":80/mstat/report");
        }
    }

    private void l() {
        this.b = 0;
        this.d = null;
        this.c = null;
    }

    public HttpHost a() {
        return this.d;
    }

    public void a(String str) {
        if (StatConfig.b()) {
            this.i.b((Object) "updateIpList " + str);
        }
        try {
            if (k.c(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.length() > 0) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String string = jSONObject.getString(keys.next());
                        if (k.c(string)) {
                            for (String str2 : string.split(i.b)) {
                                if (k.c(str2)) {
                                    String[] split = str2.split(":");
                                    if (split.length > 1) {
                                        String str3 = split[0];
                                        if (b(str3) && !this.f9270a.contains(str3)) {
                                            if (StatConfig.b()) {
                                                this.i.b((Object) "add new ip:" + str3);
                                            }
                                            this.f9270a.add(str3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            this.i.b((Throwable) e2);
        }
        this.f = new Random().nextInt(this.f9270a.size());
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public void d() {
        this.f = (this.f + 1) % this.f9270a.size();
    }

    public boolean e() {
        return this.b == 1;
    }

    public boolean f() {
        return this.b != 0;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (q.f(this.h)) {
            if (StatConfig.g) {
                k();
            }
            this.c = k.l(this.h);
            if (StatConfig.b()) {
                StatLogger statLogger = this.i;
                statLogger.b((Object) "NETWORK name:" + this.c);
            }
            if (k.c(this.c)) {
                this.b = "WIFI".equalsIgnoreCase(this.c) ? 1 : 2;
                this.d = k.a(this.h);
            }
            if (StatServiceImpl.a()) {
                StatServiceImpl.g(this.h);
                return;
            }
            return;
        }
        if (StatConfig.b()) {
            this.i.b((Object) "NETWORK TYPE: network is close.");
        }
        l();
    }

    public void h() {
        this.h.getApplicationContext().registerReceiver(new b(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
