package com.xiaomi.jr.http.certificate;

import android.util.Pair;
import com.xiaomi.jr.common.utils.ReflectUtil;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class CertificatePinning {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1434a;

    public static void a() {
        try {
            Class<?> cls = Class.forName("android.security.net.config.ApplicationConfig");
            Object a2 = ReflectUtil.a(cls, "getDefaultInstance", (Class<?>[]) null, (Object) null, new Object[0]);
            ReflectUtil.a(cls, "ensureInitialized", (Class<?>[]) null, a2, new Object[0]);
            Set<Pair> set = (Set) ReflectUtil.a(cls, "mConfigs", a2);
            if (set != null && set.size() > 0) {
                Class<?> cls2 = Class.forName("android.security.net.config.NetworkSecurityConfig");
                Field a3 = ReflectUtil.a(cls2, "mPins");
                Field a4 = ReflectUtil.a(cls2, "mCertificatesEntryRefs");
                Class<?> cls3 = Class.forName("android.security.net.config.CertificatesEntryRef");
                Class<?> cls4 = Class.forName("android.security.net.config.CertificateSource");
                Object a5 = ReflectUtil.a(Class.forName("android.security.net.config.UserCertificateSource"), "getInstance", (Class<?>[]) null, (Object) null, new Object[0]);
                if (a3 != null && a4 != null) {
                    for (Pair pair : set) {
                        Object obj = pair.second;
                        ReflectUtil.a(a3, obj, (Object) null);
                        List list = (List) ReflectUtil.a(a4, obj);
                        if (list != null) {
                            list.add(ReflectUtil.a((Class) cls3, new Class[]{cls4, Boolean.TYPE}, new Object[]{a5, true}));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
