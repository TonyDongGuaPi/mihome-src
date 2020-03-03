package com.loc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.loc.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ct {

    /* renamed from: a  reason: collision with root package name */
    public boolean f6543a = false;
    private String b = null;
    private Context c = null;
    private boolean d = true;
    /* access modifiers changed from: private */
    public s e = null;
    private ServiceConnection f = null;
    private ServiceConnection g = null;
    private ServiceConnection h = null;
    private Intent i = new Intent();
    private String j = "com.autonavi.minimap";
    private String k = "com.amap.api.service.AMapService";
    private String l = "com.autonavi.minimap.LBSConnectionService";
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private List<Intent> s = new ArrayList();
    private boolean t = false;

    public ct(Context context) {
        this.c = context;
        try {
            this.b = y.b(eh.a(u.f(context).getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            es.a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    private AMapLocationServer a(Bundle bundle) {
        byte[] bArr;
        if (!bundle.containsKey("key")) {
            return null;
        }
        try {
            bArr = eh.b(y.b(bundle.getString("key")), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n");
        } catch (Throwable th) {
            es.a(th, "ConnectionServiceManager", "parseData part");
            bArr = null;
        }
        if (bundle.containsKey("result")) {
            try {
                JSONObject jSONObject = new JSONObject(new String(eh.a(bArr, y.b(bundle.getString("result"))), "utf-8"));
                if (jSONObject.has("error")) {
                    String string = jSONObject.getString("error");
                    if ("invaid type".equals(string)) {
                        this.d = false;
                    }
                    if ("empty appkey".equals(string)) {
                        this.d = false;
                    }
                    if ("refused".equals(string)) {
                        this.d = false;
                    }
                    "failed".equals(string);
                    return null;
                }
                AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                aMapLocationServer.b(jSONObject);
                aMapLocationServer.setProvider("lbs");
                aMapLocationServer.setLocationType(7);
                if (AMapLocation.COORD_TYPE_WGS84.equals(aMapLocationServer.e()) && es.a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude())) {
                    DPoint a2 = eu.a(this.c, aMapLocationServer.getLongitude(), aMapLocationServer.getLatitude());
                    aMapLocationServer.setLatitude(a2.getLatitude());
                    aMapLocationServer.setLongitude(a2.getLongitude());
                }
                return aMapLocationServer;
            } catch (Throwable th2) {
                es.a(th2, ct.class.getName(), "parseData");
            }
        }
        return null;
    }

    private void f() {
        ArrayList<String> h2;
        if (er.c(this.c)) {
            Intent intent = new Intent();
            intent.putExtra("appkey", this.b);
            intent.setComponent(new ComponentName(this.j, this.l));
            try {
                this.n = this.c.bindService(intent, this.g, 1);
            } catch (Throwable unused) {
            }
            if (!this.n && (h2 = er.h()) != null) {
                Iterator<String> it = h2.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (!next.equals(this.l)) {
                        intent.setComponent(new ComponentName(this.j, next));
                        try {
                            this.n = this.c.bindService(intent, this.g, 1);
                        } catch (Throwable unused2) {
                        }
                        if (this.m) {
                            break;
                        }
                    }
                }
            }
            this.q = true;
        }
    }

    private AMapLocationServer g() {
        try {
            if (this.d) {
                if (this.m) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "corse");
                    bundle.putString("appkey", this.b);
                    bundle.putInt("opensdk", 1);
                    if (this.e != null) {
                        this.e.a(bundle);
                        if (bundle.size() > 0) {
                            return a(bundle);
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            es.a(th, "ConnectionServiceManager", "sendCommand");
        }
    }

    public final void a() {
        try {
            if (this.f != null && this.p) {
                this.c.unbindService(this.f);
            }
        } catch (Throwable th) {
            es.a(th, "ConnectionServiceManager", "unbindService connService");
        }
        try {
            if (this.g != null && this.q) {
                this.c.unbindService(this.g);
            }
        } catch (Throwable th2) {
            es.a(th2, "ConnectionServiceManager", "unbindService pushService");
        }
        try {
            if (this.h != null && this.r) {
                this.c.unbindService(this.h);
            }
        } catch (Throwable th3) {
            es.a(th3, "ConnectionServiceManager", "unbindService otherService");
        }
        if (this.s != null && this.s.size() > 0) {
            for (Intent stopService : this.s) {
                this.c.stopService(stopService);
            }
        }
        this.e = null;
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.d = true;
        this.f6543a = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.t = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s.clear();
        this.s = null;
    }

    public final void b() {
        try {
            if (this.f == null) {
                this.f = new ServiceConnection() {
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        ct.this.f6543a = true;
                        s unused = ct.this.e = s.a.a(iBinder);
                    }

                    public final void onServiceDisconnected(ComponentName componentName) {
                        ct.this.f6543a = false;
                        s unused = ct.this.e = null;
                    }
                };
            }
            if (this.g == null) {
                this.g = new ServiceConnection() {
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
            if (this.h == null) {
                this.h = new ServiceConnection() {
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
        } catch (Throwable th) {
            es.a(th, "ConnectionServiceManager", "init");
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006b */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045 A[Catch:{ Throwable -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f A[EDGE_INSN: B:33:0x006f->B:26:0x006f ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x003f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r6 = this;
            boolean r0 = r6.t
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 1
            android.content.Context r1 = r6.c     // Catch:{ Throwable -> 0x0077 }
            boolean r1 = com.loc.er.b((android.content.Context) r1)     // Catch:{ Throwable -> 0x0077 }
            if (r1 == 0) goto L_0x0071
            android.content.Intent r1 = r6.i     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r2 = "appkey"
            java.lang.String r3 = r6.b     // Catch:{ Throwable -> 0x0077 }
            r1.putExtra(r2, r3)     // Catch:{ Throwable -> 0x0077 }
            android.content.Intent r1 = r6.i     // Catch:{ Throwable -> 0x0077 }
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r3 = r6.j     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r4 = r6.k     // Catch:{ Throwable -> 0x0077 }
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x0077 }
            r1.setComponent(r2)     // Catch:{ Throwable -> 0x0077 }
            android.content.Context r1 = r6.c     // Catch:{ Throwable -> 0x0031 }
            android.content.Intent r2 = r6.i     // Catch:{ Throwable -> 0x0031 }
            android.content.ServiceConnection r3 = r6.f     // Catch:{ Throwable -> 0x0031 }
            boolean r1 = r1.bindService(r2, r3, r0)     // Catch:{ Throwable -> 0x0031 }
            r6.m = r1     // Catch:{ Throwable -> 0x0031 }
        L_0x0031:
            boolean r1 = r6.m     // Catch:{ Throwable -> 0x0077 }
            if (r1 != 0) goto L_0x006f
            java.util.ArrayList r1 = com.loc.er.g()     // Catch:{ Throwable -> 0x0077 }
            if (r1 == 0) goto L_0x006f
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x0077 }
        L_0x003f:
            boolean r2 = r1.hasNext()     // Catch:{ Throwable -> 0x0077 }
            if (r2 == 0) goto L_0x006f
            java.lang.Object r2 = r1.next()     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r3 = r6.k     // Catch:{ Throwable -> 0x0077 }
            boolean r3 = r2.equals(r3)     // Catch:{ Throwable -> 0x0077 }
            if (r3 != 0) goto L_0x003f
            android.content.Intent r3 = r6.i     // Catch:{ Throwable -> 0x0077 }
            android.content.ComponentName r4 = new android.content.ComponentName     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r5 = r6.j     // Catch:{ Throwable -> 0x0077 }
            r4.<init>(r5, r2)     // Catch:{ Throwable -> 0x0077 }
            r3.setComponent(r4)     // Catch:{ Throwable -> 0x0077 }
            android.content.Context r2 = r6.c     // Catch:{ Throwable -> 0x006b }
            android.content.Intent r3 = r6.i     // Catch:{ Throwable -> 0x006b }
            android.content.ServiceConnection r4 = r6.f     // Catch:{ Throwable -> 0x006b }
            boolean r2 = r2.bindService(r3, r4, r0)     // Catch:{ Throwable -> 0x006b }
            r6.m = r2     // Catch:{ Throwable -> 0x006b }
        L_0x006b:
            boolean r2 = r6.m     // Catch:{ Throwable -> 0x0077 }
            if (r2 == 0) goto L_0x003f
        L_0x006f:
            r6.p = r0     // Catch:{ Throwable -> 0x0077 }
        L_0x0071:
            r6.f()     // Catch:{ Throwable -> 0x0077 }
            r6.d()     // Catch:{ Throwable -> 0x0077 }
        L_0x0077:
            r6.t = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ct.c():void");
    }

    public final void d() {
        if (!this.r && !this.t) {
            try {
                if (er.f(this.c)) {
                    List<ev> p2 = er.p();
                    if (p2 != null && p2.size() > 0) {
                        for (ev next : p2) {
                            if (next != null) {
                                if (next.a()) {
                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName(next.b(), next.c()));
                                    if (!TextUtils.isEmpty(next.e())) {
                                        intent.setAction(next.e());
                                    }
                                    List<Map<String, String>> d2 = next.d();
                                    if (d2 != null && d2.size() > 0) {
                                        for (int i2 = 0; i2 < d2.size(); i2++) {
                                            Iterator it = d2.get(i2).entrySet().iterator();
                                            if (it.hasNext()) {
                                                Map.Entry entry = (Map.Entry) it.next();
                                                intent.putExtra(((String) entry.getKey()).toString(), ((String) entry.getValue()).toString());
                                            }
                                        }
                                    }
                                    if (next.f()) {
                                        this.c.startService(intent);
                                        this.s.add(intent);
                                    }
                                    if (this.c.bindService(intent, this.h, 1)) {
                                        this.o = true;
                                    }
                                }
                            }
                        }
                    }
                    this.r = true;
                }
            } catch (Throwable th) {
                es.a(th, "ConnectionServiceManager", "bindOtherService");
            }
        }
    }

    public final AMapLocationServer e() {
        AMapLocationServer g2;
        if (!er.f()) {
            return null;
        }
        c();
        for (int i2 = 4; i2 > 0 && !this.f6543a; i2--) {
            SystemClock.sleep(500);
        }
        if (!this.f6543a || (g2 = g()) == null) {
            return null;
        }
        return g2;
    }
}
