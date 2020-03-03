package com.xiaomi.mipush.sdk;

import com.xiaomi.push.ht;
import java.util.HashMap;

public class k {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<d, a> f11556a = new HashMap<>();

    static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f11557a;
        public String b;

        public a(String str, String str2) {
            this.f11557a = str;
            this.b = str2;
        }
    }

    static {
        a(d.ASSEMBLE_PUSH_HUAWEI, new a("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        a(d.ASSEMBLE_PUSH_FCM, new a("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        a(d.ASSEMBLE_PUSH_COS, new a("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
        a(d.ASSEMBLE_PUSH_FTOS, new a("com.xiaomi.assemble.control.FTOSPushManager", "newInstance"));
    }

    public static a a(d dVar) {
        return f11556a.get(dVar);
    }

    private static void a(d dVar, a aVar) {
        if (aVar != null) {
            f11556a.put(dVar, aVar);
        }
    }

    public static ht b(d dVar) {
        return ht.AggregatePushSwitch;
    }

    public static bb c(d dVar) {
        switch (l.f11558a[dVar.ordinal()]) {
            case 1:
                return bb.UPLOAD_HUAWEI_TOKEN;
            case 2:
                return bb.UPLOAD_FCM_TOKEN;
            case 3:
                return bb.UPLOAD_COS_TOKEN;
            case 4:
                return bb.UPLOAD_FTOS_TOKEN;
            default:
                return null;
        }
    }
}
