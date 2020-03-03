package com.tencent.bugly.proguard;

import com.mi.mistatistic.sdk.data.EventData;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.xiaomi.youpin.network.annotation.Encoding;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class a {

    /* renamed from: a  reason: collision with root package name */
    protected HashMap<String, HashMap<String, byte[]>> f9027a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    public static ag a(int i) {
        if (i == 1) {
            return new af();
        }
        if (i == 3) {
            return new ae();
        }
        return null;
    }

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = Encoding.GBK;
        this.c = new i();
    }

    public void a(String str) {
        this.b = str;
    }

    public static aq a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        aq aqVar = new aq();
        aqVar.f9039a = userInfoBean.e;
        aqVar.e = userInfoBean.j;
        aqVar.d = userInfoBean.c;
        aqVar.c = userInfoBean.d;
        aqVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        aqVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case 1:
                aqVar.b = 1;
                break;
            case 2:
                aqVar.b = 4;
                break;
            case 3:
                aqVar.b = 2;
                break;
            case 4:
                aqVar.b = 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    aqVar.b = (byte) userInfoBean.b;
                    break;
                } else {
                    x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
        }
        aqVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = aqVar.f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = aqVar.f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry next : userInfoBean.r.entrySet()) {
                Map<String, String> map3 = aqVar.f;
                map3.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry next2 : userInfoBean.s.entrySet()) {
                Map<String, String> map4 = aqVar.f;
                map4.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        Map<String, String> map5 = aqVar.f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map5.put("A36", sb3.toString());
        Map<String, String> map6 = aqVar.f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.g);
        map6.put("F02", sb4.toString());
        Map<String, String> map7 = aqVar.f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.h);
        map7.put("F03", sb5.toString());
        Map<String, String> map8 = aqVar.f;
        map8.put("F04", userInfoBean.j);
        Map<String, String> map9 = aqVar.f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map9.put("F05", sb6.toString());
        Map<String, String> map10 = aqVar.f;
        map10.put("F06", userInfoBean.m);
        Map<String, String> map11 = aqVar.f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map11.put("F10", sb7.toString());
        x.c("summary type %d vm:%d", Byte.valueOf(aqVar.b), Integer.valueOf(aqVar.f.size()));
        return aqVar;
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = EventData.b;
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (str2.equals("list")) {
                int i3 = i2 - 1;
                arrayList.set(i3, "<" + arrayList.get(i3));
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("map")) {
                int i4 = i2 - 1;
                arrayList.set(i4, "<" + arrayList.get(i4) + ",");
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("Array")) {
                int i5 = i2 - 1;
                arrayList.set(i5, "<" + arrayList.get(i5));
                arrayList.set(0, arrayList.get(0) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (!(t instanceof Set)) {
            j jVar = new j();
            jVar.a(this.b);
            jVar.a((Object) t, 0);
            byte[] a2 = l.a(jVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a((ArrayList<String>) arrayList, (Object) t);
            hashMap.put(a((ArrayList<String>) arrayList), a2);
            this.d.remove(str);
            this.f9027a.put(str, hashMap);
        } else {
            throw new IllegalArgumentException("can not support Set");
        }
    }

    public static ar a(List<UserInfoBean> list, int i) {
        com.tencent.bugly.crashreport.common.info.a b2;
        if (list == null || list.size() == 0 || (b2 = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return null;
        }
        b2.t();
        ar arVar = new ar();
        arVar.b = b2.d;
        arVar.c = b2.h();
        ArrayList<aq> arrayList = new ArrayList<>();
        for (UserInfoBean a2 : list) {
            aq a3 = a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        arVar.d = arrayList;
        arVar.e = new HashMap();
        Map<String, String> map = arVar.e;
        map.put("A7", b2.f);
        Map<String, String> map2 = arVar.e;
        map2.put("A6", b2.s());
        Map<String, String> map3 = arVar.e;
        map3.put("A5", b2.r());
        Map<String, String> map4 = arVar.e;
        StringBuilder sb = new StringBuilder();
        sb.append(b2.p());
        map4.put("A2", sb.toString());
        Map<String, String> map5 = arVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b2.p());
        map5.put("A1", sb2.toString());
        Map<String, String> map6 = arVar.e;
        map6.put("A24", b2.h);
        Map<String, String> map7 = arVar.e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(b2.q());
        map7.put("A17", sb3.toString());
        Map<String, String> map8 = arVar.e;
        map8.put("A15", b2.w());
        Map<String, String> map9 = arVar.e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(b2.x());
        map9.put("A13", sb4.toString());
        Map<String, String> map10 = arVar.e;
        map10.put("F08", b2.v);
        Map<String, String> map11 = arVar.e;
        map11.put("F09", b2.w);
        Map<String, String> E = b2.E();
        if (E != null && E.size() > 0) {
            for (Map.Entry next : E.entrySet()) {
                Map<String, String> map12 = arVar.e;
                map12.put("C04_" + ((String) next.getKey()), next.getValue());
            }
        }
        switch (i) {
            case 1:
                arVar.f9040a = 1;
                break;
            case 2:
                arVar.f9040a = 2;
                break;
            default:
                x.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return arVar;
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T t = (k) cls.newInstance();
            i iVar = new i(bArr);
            iVar.a("utf-8");
            t.a(iVar);
            return t;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0251, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0256, code lost:
        if (com.tencent.bugly.proguard.x.b(r9) == false) goto L_0x0258;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0258, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x025b, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.proguard.am a(android.content.Context r9, int r10, byte[] r11) {
        /*
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.a.b()
            com.tencent.bugly.crashreport.common.strategy.a r1 = com.tencent.bugly.crashreport.common.strategy.a.a()
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.c()
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x025c
            if (r1 != 0) goto L_0x0014
            goto L_0x025c
        L_0x0014:
            com.tencent.bugly.proguard.am r4 = new com.tencent.bugly.proguard.am     // Catch:{ Throwable -> 0x0251 }
            r4.<init>()     // Catch:{ Throwable -> 0x0251 }
            monitor-enter(r0)     // Catch:{ Throwable -> 0x0251 }
            r5 = 1
            r4.f9035a = r5     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r0.f()     // Catch:{ all -> 0x024e }
            r4.b = r6     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r0.c     // Catch:{ all -> 0x024e }
            r4.c = r6     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r0.j     // Catch:{ all -> 0x024e }
            r4.d = r6     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r0.l     // Catch:{ all -> 0x024e }
            r4.e = r6     // Catch:{ all -> 0x024e }
            r0.getClass()     // Catch:{ all -> 0x024e }
            java.lang.String r6 = "2.6.5"
            r4.f = r6     // Catch:{ all -> 0x024e }
            r4.g = r10     // Catch:{ all -> 0x024e }
            if (r11 != 0) goto L_0x0041
            java.lang.String r10 = ""
            byte[] r10 = r10.getBytes()     // Catch:{ all -> 0x024e }
            goto L_0x0042
        L_0x0041:
            r10 = r11
        L_0x0042:
            r4.h = r10     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.g     // Catch:{ all -> 0x024e }
            r4.i = r10     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.h     // Catch:{ all -> 0x024e }
            r4.j = r10     // Catch:{ all -> 0x024e }
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ all -> 0x024e }
            r10.<init>()     // Catch:{ all -> 0x024e }
            r4.k = r10     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.e()     // Catch:{ all -> 0x024e }
            r4.l = r10     // Catch:{ all -> 0x024e }
            long r6 = r1.p     // Catch:{ all -> 0x024e }
            r4.m = r6     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.h()     // Catch:{ all -> 0x024e }
            r4.o = r10     // Catch:{ all -> 0x024e }
            java.lang.String r9 = com.tencent.bugly.crashreport.common.info.b.e(r9)     // Catch:{ all -> 0x024e }
            r4.p = r9     // Catch:{ all -> 0x024e }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x024e }
            r4.q = r9     // Catch:{ all -> 0x024e }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r9.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.k()     // Catch:{ all -> 0x024e }
            r9.append(r10)     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x024e }
            r4.r = r9     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r0.j()     // Catch:{ all -> 0x024e }
            r4.s = r9     // Catch:{ all -> 0x024e }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r9.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.m()     // Catch:{ all -> 0x024e }
            r9.append(r10)     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x024e }
            r4.t = r9     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r0.l()     // Catch:{ all -> 0x024e }
            r4.u = r9     // Catch:{ all -> 0x024e }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r9.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r0.n()     // Catch:{ all -> 0x024e }
            r9.append(r10)     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x024e }
            r4.v = r9     // Catch:{ all -> 0x024e }
            java.lang.String r9 = r4.p     // Catch:{ all -> 0x024e }
            r4.w = r9     // Catch:{ all -> 0x024e }
            r0.getClass()     // Catch:{ all -> 0x024e }
            java.lang.String r9 = "com.tencent.bugly"
            r4.n = r9     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "A26"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.y()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "F11"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            boolean r7 = r0.z     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "F12"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            boolean r7 = r0.y     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G1"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.u()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            boolean r9 = r0.B     // Catch:{ all -> 0x024e }
            if (r9 == 0) goto L_0x01a6
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G2"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.K()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.L()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G4"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.M()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G5"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.N()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G6"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.O()     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G7"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            long r7 = r0.P()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = java.lang.Long.toString(r7)     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
        L_0x01a6:
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "D3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x024e }
            r6.<init>()     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r0.k     // Catch:{ all -> 0x024e }
            r6.append(r7)     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.List<com.tencent.bugly.a> r9 = com.tencent.bugly.b.b     // Catch:{ all -> 0x024e }
            if (r9 == 0) goto L_0x01e3
            java.util.List<com.tencent.bugly.a> r9 = com.tencent.bugly.b.b     // Catch:{ all -> 0x024e }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x024e }
        L_0x01c5:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x024e }
            if (r10 == 0) goto L_0x01e3
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x024e }
            com.tencent.bugly.a r10 = (com.tencent.bugly.a) r10     // Catch:{ all -> 0x024e }
            java.lang.String r6 = r10.versionKey     // Catch:{ all -> 0x024e }
            if (r6 == 0) goto L_0x01c5
            java.lang.String r6 = r10.version     // Catch:{ all -> 0x024e }
            if (r6 == 0) goto L_0x01c5
            java.util.Map<java.lang.String, java.lang.String> r6 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r7 = r10.versionKey     // Catch:{ all -> 0x024e }
            java.lang.String r10 = r10.version     // Catch:{ all -> 0x024e }
            r6.put(r7, r10)     // Catch:{ all -> 0x024e }
            goto L_0x01c5
        L_0x01e3:
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "G15"
            java.lang.String r6 = "G15"
            java.lang.String r7 = ""
            java.lang.String r6 = com.tencent.bugly.proguard.z.b((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            java.util.Map<java.lang.String, java.lang.String> r9 = r4.k     // Catch:{ all -> 0x024e }
            java.lang.String r10 = "D4"
            java.lang.String r6 = "D4"
            java.lang.String r7 = "0"
            java.lang.String r6 = com.tencent.bugly.proguard.z.b((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x024e }
            r9.put(r10, r6)     // Catch:{ all -> 0x024e }
            monitor-exit(r0)     // Catch:{ all -> 0x024e }
            com.tencent.bugly.proguard.u r9 = com.tencent.bugly.proguard.u.a()     // Catch:{ Throwable -> 0x0251 }
            if (r9 == 0) goto L_0x0225
            boolean r9 = r9.f9056a     // Catch:{ Throwable -> 0x0251 }
            if (r9 != 0) goto L_0x0225
            if (r11 == 0) goto L_0x0225
            byte[] r9 = r4.h     // Catch:{ Throwable -> 0x0251 }
            r10 = 2
            java.lang.String r11 = r1.u     // Catch:{ Throwable -> 0x0251 }
            byte[] r9 = com.tencent.bugly.proguard.z.a((byte[]) r9, (int) r10, (int) r5, (java.lang.String) r11)     // Catch:{ Throwable -> 0x0251 }
            r4.h = r9     // Catch:{ Throwable -> 0x0251 }
            byte[] r9 = r4.h     // Catch:{ Throwable -> 0x0251 }
            if (r9 != 0) goto L_0x0225
            java.lang.String r9 = "reqPkg sbuffer error!"
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0251 }
            com.tencent.bugly.proguard.x.e(r9, r10)     // Catch:{ Throwable -> 0x0251 }
            return r3
        L_0x0225:
            java.util.Map r9 = r0.D()     // Catch:{ Throwable -> 0x0251 }
            if (r9 == 0) goto L_0x024d
            java.util.Set r9 = r9.entrySet()     // Catch:{ Throwable -> 0x0251 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Throwable -> 0x0251 }
        L_0x0233:
            boolean r10 = r9.hasNext()     // Catch:{ Throwable -> 0x0251 }
            if (r10 == 0) goto L_0x024d
            java.lang.Object r10 = r9.next()     // Catch:{ Throwable -> 0x0251 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ Throwable -> 0x0251 }
            java.util.Map<java.lang.String, java.lang.String> r11 = r4.k     // Catch:{ Throwable -> 0x0251 }
            java.lang.Object r0 = r10.getKey()     // Catch:{ Throwable -> 0x0251 }
            java.lang.Object r10 = r10.getValue()     // Catch:{ Throwable -> 0x0251 }
            r11.put(r0, r10)     // Catch:{ Throwable -> 0x0251 }
            goto L_0x0233
        L_0x024d:
            return r4
        L_0x024e:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ Throwable -> 0x0251 }
            throw r9     // Catch:{ Throwable -> 0x0251 }
        L_0x0251:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.x.b(r9)
            if (r10 != 0) goto L_0x025b
            r9.printStackTrace()
        L_0x025b:
            return r3
        L_0x025c:
            java.lang.String r9 = "Can not create request pkg for parameters is invalid."
            java.lang.Object[] r10 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.e(r9, r10)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.a.a(android.content.Context, int, byte[]):com.tencent.bugly.proguard.am");
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public byte[] a() {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a(this.f9027a, 0);
        return l.a(jVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f9027a = this.c.a(hashMap, 0, false);
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.b();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static an a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.b();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b2 = dVar.b("detail", new an());
                an cast = an.class.isInstance(b2) ? an.class.cast(b2) : null;
                if (!z && cast != null && cast.c != null && cast.c.length > 0) {
                    x.c("resp buf %d", Integer.valueOf(cast.c.length));
                    cast.c = z.b(cast.c, 2, 1, StrategyBean.d);
                    if (cast.c == null) {
                        x.e("resp sbuffer error!", new Object[0]);
                        return null;
                    }
                }
                return cast;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
