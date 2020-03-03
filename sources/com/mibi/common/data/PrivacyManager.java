package com.mibi.common.data;

import android.content.SharedPreferences;
import android.util.Log;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.exception.PaymentException;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class PrivacyManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7537a = "PrivacyManager";

    public static void a(Session session, String str, String str2) {
        MemoryStorage m = session.m();
        String f = session.f();
        m.a(f, CommonConstants.bj, (Object) str);
        m.a(f, "entry", (Object) str2);
    }

    public static void a(Session session) throws PaymentException {
        if (session == null) {
            throw new IllegalArgumentException("session should not be null when uploadPrivacy privacy info.");
        } else if (!b(session)) {
            MemoryStorage m = session.m();
            String f = session.f();
            String a2 = m.a(f, CommonConstants.bj, "105");
            Log.d(f7537a, "upload privacy marketType = " + a2);
            String a3 = m.a(f, "entry", MibiPrivacyUtils.b);
            boolean a4 = m.a(f, CommonConstants.bk, true);
            if (!(session.e() instanceof FakeAccountLoader)) {
                Connection a5 = ConnectionFactory.a(CommonConstants.a(CommonConstants.E), session, false);
                SortedParameter d = a5.d();
                d.a("userId", (Object) f);
                d.a(CommonConstants.bj, (Object) a2);
                d.a("entry", (Object) a3);
                d.a(CommonConstants.bk, (Object) Boolean.valueOf(a4));
                JSONObject e = a5.e();
                try {
                    int i = e.getInt("errcode");
                    Log.d(f7537a, "upload privacy errorCode is " + i);
                    if (i != 200) {
                        a(session, false);
                        return;
                    }
                    try {
                        if (e.getBoolean(CommonConstants.bm)) {
                            a(session, false);
                            return;
                        }
                    } catch (JSONException unused) {
                        Log.d(f7537a, "upload privacy fail");
                    } catch (Throwable th) {
                        a(session, true);
                        throw th;
                    }
                    a(session, true);
                } catch (JSONException unused2) {
                    Log.d(f7537a, "upload privacy fail");
                    Log.d(f7537a, "upload privacy errorCode is " + 0);
                    a(session, false);
                } catch (Throwable unused3) {
                    Log.d(f7537a, "upload privacy errorCode is " + 0);
                    a(session, false);
                }
            }
        }
    }

    public static void a(Session session, boolean z) {
        SharedPreferences.Editor edit = session.c(CommonConstants.bl).edit();
        edit.putBoolean(CommonConstants.bl, z);
        edit.apply();
    }

    public static boolean b(Session session) {
        return session.c(CommonConstants.bl).getBoolean(CommonConstants.bl, false);
    }
}
