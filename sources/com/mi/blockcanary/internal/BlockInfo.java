package com.mi.blockcanary.internal;

import android.os.Build;
import android.text.TextUtils;
import com.mi.blockcanary.BlockCanaryInternals;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONObject;

public class BlockInfo {
    private static final String D = "BlockInfo";

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleDateFormat f6753a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
    public static final String b = "\r\n";
    public static final String c = " = ";
    public static final String d = "newInstance: ";
    public static final String e = "block_hold";
    public static final String f = "cpu_core";
    public static final String g = "cpu_busy";
    public static final String h = "cpu_rate";
    public static final String i = "time";
    public static final String j = "thread_time";
    public static final String k = "time_start";
    public static final String l = "time_end";
    public static final String m = "stack";
    public static final String n = "totalMemory";
    public static final String o = "freeMemory";
    public static String p = BlockCanaryInternals.c().a();
    public static String q = Build.MODEL;
    public static int r;
    public boolean A;
    public String B;
    public ArrayList<String> C = new ArrayList<>();
    private StringBuilder E = new StringBuilder();
    private StringBuilder F = new StringBuilder();
    private StringBuilder G = new StringBuilder();
    private StringBuilder H = new StringBuilder();
    public int s = -1;
    public String t;
    public String u;
    public int v;
    public long w;
    public long x;
    public String y;
    public String z;

    static {
        r = -1;
        r = PerformanceUtils.a();
        if (BlockCanaryInternals.c() != null) {
        }
    }

    public static BlockInfo a() {
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.v = (int) BlockCanaryInternals.d;
        blockInfo.s = r;
        blockInfo.t = String.valueOf(PerformanceUtils.b());
        blockInfo.u = String.valueOf(PerformanceUtils.c());
        return blockInfo;
    }

    public BlockInfo a(boolean z2) {
        this.A = z2;
        return this;
    }

    public BlockInfo a(String str) {
        this.B = str;
        return this;
    }

    public BlockInfo a(ArrayList<String> arrayList) {
        this.C = arrayList;
        return this;
    }

    public BlockInfo a(long j2, long j3, long j4, long j5) {
        this.w = j3 - j2;
        this.x = j5 - j4;
        this.y = String.valueOf(j2);
        this.z = String.valueOf(j3);
        return this;
    }

    public BlockInfo b() {
        StringBuilder sb = this.E;
        sb.append(f);
        sb.append(c);
        sb.append(this.s);
        sb.append("\r\n");
        StringBuilder sb2 = this.E;
        sb2.append(o);
        sb2.append(c);
        sb2.append(this.t);
        sb2.append("\r\n");
        StringBuilder sb3 = this.E;
        sb3.append(n);
        sb3.append(c);
        sb3.append(this.u);
        sb3.append("\r\n");
        StringBuilder sb4 = this.G;
        sb4.append("time");
        sb4.append(c);
        sb4.append(this.w);
        sb4.append("\r\n");
        StringBuilder sb5 = this.G;
        sb5.append(j);
        sb5.append(c);
        sb5.append(this.x);
        sb5.append("\r\n");
        StringBuilder sb6 = this.G;
        sb6.append(k);
        sb6.append(c);
        sb6.append(this.y);
        sb6.append("\r\n");
        StringBuilder sb7 = this.G;
        sb7.append(l);
        sb7.append(c);
        sb7.append(this.z);
        sb7.append("\r\n");
        StringBuilder sb8 = this.F;
        sb8.append(g);
        sb8.append(c);
        sb8.append(this.A);
        sb8.append("\r\n");
        StringBuilder sb9 = this.F;
        sb9.append(h);
        sb9.append(c);
        sb9.append(this.B);
        sb9.append("\r\n");
        if (this.C != null && !this.C.isEmpty()) {
            StringBuilder sb10 = new StringBuilder();
            Iterator<String> it = this.C.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    sb10.append(next);
                    sb10.append("\r\n");
                }
            }
            StringBuilder sb11 = this.H;
            sb11.append("stack");
            sb11.append(c);
            sb11.append(sb10.toString());
            sb11.append("\r\n");
        }
        return this;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(e, this.v);
            jSONObject.put(f, this.s);
            jSONObject.put(o, this.t);
            jSONObject.put(n, this.u);
            jSONObject.put("time", this.w);
            jSONObject.put(j, this.x);
            jSONObject.put(k, this.y);
            jSONObject.put(l, this.z);
            jSONObject.put(g, this.A);
            jSONObject.put(h, this.B);
            if (this.C != null && !this.C.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = this.C.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    sb.append("\r\n");
                }
                jSONObject.put("stack", sb.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String d() {
        return this.E.toString();
    }

    public String e() {
        return this.F.toString();
    }

    public String f() {
        return this.G.toString();
    }

    public String g() {
        return this.H.toString();
    }

    public String toString() {
        return String.valueOf(this.E) + this.G + this.F + this.H;
    }
}
