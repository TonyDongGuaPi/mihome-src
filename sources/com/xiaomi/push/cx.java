package com.xiaomi.push;

import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class cx {

    /* renamed from: a  reason: collision with root package name */
    public String f12682a = "";
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    protected String h;
    private long i;
    private ArrayList<dg> j = new ArrayList<>();
    private String k;
    private double l = 0.1d;
    private String m = "s.mi1.cc";
    private long n = 86400000;

    public cx(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = System.currentTimeMillis();
            this.j.add(new dg(str, -1));
            this.f12682a = db.a();
            this.b = str;
            return;
        }
        throw new IllegalArgumentException("the host is empty");
    }

    private synchronized void d(String str) {
        Iterator<dg> it = this.j.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().f12688a, str)) {
                it.remove();
            }
        }
    }

    public synchronized cx a(JSONObject jSONObject) {
        this.f12682a = jSONObject.optString("net");
        this.n = jSONObject.getLong("ttl");
        this.l = jSONObject.getDouble("pct");
        this.i = jSONObject.getLong("ts");
        this.d = jSONObject.optString("city");
        this.c = jSONObject.optString("prv");
        this.g = jSONObject.optString("cty");
        this.e = jSONObject.optString("isp");
        this.f = jSONObject.optString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP);
        this.b = jSONObject.optString("host");
        this.h = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            a(new dg().a(jSONArray.getJSONObject(i2)));
        }
        return this;
    }

    public ArrayList<String> a(String str) {
        if (!TextUtils.isEmpty(str)) {
            URL url = new URL(str);
            if (TextUtils.equals(url.getHost(), this.b)) {
                ArrayList<String> arrayList = new ArrayList<>();
                Iterator<String> it = a(true).iterator();
                while (it.hasNext()) {
                    cz a2 = cz.a(it.next(), url.getPort());
                    arrayList.add(new URL(url.getProtocol(), a2.b(), a2.a(), url.getFile()).toString());
                }
                return arrayList;
            }
            throw new IllegalArgumentException("the url is not supported by the fallback");
        }
        throw new IllegalArgumentException("the url is empty.");
    }

    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        String substring;
        dg[] dgVarArr = new dg[this.j.size()];
        this.j.toArray(dgVarArr);
        Arrays.sort(dgVarArr);
        arrayList = new ArrayList<>();
        for (dg dgVar : dgVarArr) {
            if (z) {
                substring = dgVar.f12688a;
            } else {
                int indexOf = dgVar.f12688a.indexOf(":");
                substring = indexOf != -1 ? dgVar.f12688a.substring(0, indexOf) : dgVar.f12688a;
            }
            arrayList.add(substring);
        }
        return arrayList;
    }

    public void a(double d2) {
        this.l = d2;
    }

    public void a(long j2) {
        if (j2 > 0) {
            this.n = j2;
            return;
        }
        throw new IllegalArgumentException("the duration is invalid " + j2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(dg dgVar) {
        d(dgVar.f12688a);
        this.j.add(dgVar);
    }

    public void a(String str, int i2, long j2, long j3, Exception exc) {
        a(str, new cw(i2, j2, j3, exc));
    }

    public void a(String str, long j2, long j3) {
        try {
            b(new URL(str).getHost(), j2, j3);
        } catch (MalformedURLException unused) {
        }
    }

    public void a(String str, long j2, long j3, Exception exc) {
        try {
            b(new URL(str).getHost(), j2, j3, exc);
        } catch (MalformedURLException unused) {
        }
    }

    public synchronized void a(String str, cw cwVar) {
        Iterator<dg> it = this.j.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            dg next = it.next();
            if (TextUtils.equals(str, next.f12688a)) {
                next.a(cwVar);
                break;
            }
        }
    }

    public synchronized void a(String[] strArr) {
        int i2;
        int size = this.j.size() - 1;
        while (true) {
            i2 = 0;
            if (size < 0) {
                break;
            }
            int length = strArr.length;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (TextUtils.equals(this.j.get(size).f12688a, strArr[i2])) {
                    this.j.remove(size);
                    break;
                }
                i2++;
            }
            size--;
        }
        Iterator<dg> it = this.j.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            dg next = it.next();
            if (next.b > i3) {
                i3 = next.b;
            }
        }
        while (i2 < strArr.length) {
            a(new dg(strArr[i2], (strArr.length + i3) - i2));
            i2++;
        }
    }

    public boolean a() {
        return TextUtils.equals(this.f12682a, db.a());
    }

    public boolean a(cx cxVar) {
        return TextUtils.equals(this.f12682a, cxVar.f12682a);
    }

    public synchronized void b(String str) {
        a(new dg(str));
    }

    public void b(String str, long j2, long j3) {
        a(str, 0, j2, j3, (Exception) null);
    }

    public void b(String str, long j2, long j3, Exception exc) {
        a(str, -1, j2, j3, exc);
    }

    public boolean b() {
        return System.currentTimeMillis() - this.i < this.n;
    }

    public void c(String str) {
        this.m = str;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        long j2 = 864000000;
        if (864000000 < this.n) {
            j2 = this.n;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - this.i > j2 || (currentTimeMillis - this.i > this.n && this.f12682a.startsWith("WIFI-"));
    }

    public synchronized ArrayList<String> d() {
        return a(false);
    }

    public synchronized String e() {
        if (!TextUtils.isEmpty(this.k)) {
            return this.k;
        } else if (TextUtils.isEmpty(this.e)) {
            return "hardcode_isp";
        } else {
            this.k = bf.a((Object[]) new String[]{this.e, this.c, this.d, this.g, this.f}, JSMethod.NOT_SET);
            return this.k;
        }
    }

    public synchronized JSONObject f() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.f12682a);
        jSONObject.put("ttl", this.n);
        jSONObject.put("pct", this.l);
        jSONObject.put("ts", this.i);
        jSONObject.put("city", this.d);
        jSONObject.put("prv", this.c);
        jSONObject.put("cty", this.g);
        jSONObject.put("isp", this.e);
        jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, this.f);
        jSONObject.put("host", this.b);
        jSONObject.put("xf", this.h);
        JSONArray jSONArray = new JSONArray();
        Iterator<dg> it = this.j.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f12682a);
        sb.append("\n");
        sb.append(e());
        Iterator<dg> it = this.j.iterator();
        while (it.hasNext()) {
            sb.append("\n");
            sb.append(it.next().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
