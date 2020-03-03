package com.tencent.wxop.stat;

import android.content.Context;
import com.alipay.sdk.util.i;
import com.tencent.wxop.stat.a.j;
import com.tencent.wxop.stat.common.StatLogger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

class ap implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Context f9296a = null;
    private Map<String, Integer> b = null;
    private StatSpecifyReportedInfo c = null;

    public ap(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f9296a = context;
        this.c = statSpecifyReportedInfo;
        if (map != null) {
            this.b = map;
        }
    }

    private NetworkMonitor a(String str, int i) {
        int i2;
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        try {
            networkMonitor.a(str);
            networkMonitor.b(i);
            long currentTimeMillis = System.currentTimeMillis();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, 30000);
            networkMonitor.a(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.b(inetSocketAddress.getAddress().getHostAddress());
            socket.close();
            try {
                socket.close();
            } catch (Throwable th) {
                StatServiceImpl.q.b(th);
            }
            i2 = 0;
        } catch (IOException e) {
            try {
                StatServiceImpl.q.b((Throwable) e);
                socket.close();
            } catch (Throwable th2) {
                StatServiceImpl.q.b(th2);
            }
        } catch (Throwable th3) {
            StatServiceImpl.q.b(th3);
        }
        networkMonitor.a(i2);
        return networkMonitor;
        i2 = -1;
        networkMonitor.a(i2);
        return networkMonitor;
        throw th;
    }

    private Map<String, Integer> a() {
        String str;
        HashMap hashMap = new HashMap();
        String b2 = StatConfig.b("__MTA_TEST_SPEED__", (String) null);
        if (!(b2 == null || b2.trim().length() == 0)) {
            for (String split : b2.split(i.b)) {
                String[] split2 = split.split(",");
                if (!(split2 == null || split2.length != 2 || (str = split2[0]) == null || str.trim().length() == 0)) {
                    try {
                        hashMap.put(str, Integer.valueOf(Integer.valueOf(split2[1]).intValue()));
                    } catch (NumberFormatException e) {
                        StatServiceImpl.q.b((Throwable) e);
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        StatLogger g;
        String str;
        try {
            if (this.b == null) {
                this.b = a();
            }
            if (this.b != null) {
                if (this.b.size() != 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry next : this.b.entrySet()) {
                        String str2 = (String) next.getKey();
                        if (str2 != null) {
                            if (str2.length() != 0) {
                                if (((Integer) next.getValue()) == null) {
                                    g = StatServiceImpl.q;
                                    str = "port is null for " + str2;
                                    g.f(str);
                                } else {
                                    jSONArray.put(a((String) next.getKey(), ((Integer) next.getValue()).intValue()).f());
                                }
                            }
                        }
                        g = StatServiceImpl.q;
                        str = "empty domain name.";
                        g.f(str);
                    }
                    if (jSONArray.length() != 0) {
                        j jVar = new j(this.f9296a, StatServiceImpl.a(this.f9296a, false, this.c), this.c);
                        jVar.a(jSONArray.toString());
                        new aq(jVar).a();
                        return;
                    }
                    return;
                }
            }
            StatServiceImpl.q.b((Object) "empty domain list.");
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
        }
    }
}
