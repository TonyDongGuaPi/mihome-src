package com.mibi.common.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.data.Session;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7544a = "SessionManager";
    private static SessionCache b = new SessionCache();

    private SessionManager() {
    }

    public static Session a(Context context, AccountLoader accountLoader) {
        return a(context, accountLoader, (PaymentResponse) null);
    }

    public static Session a(Context context, AccountLoader accountLoader, PaymentResponse paymentResponse) {
        Session session = new Session(context, accountLoader, paymentResponse);
        Log.v(f7544a, "new session " + session.g() + " created");
        b.a(session);
        return session;
    }

    public static Session a(String str) {
        return b.a(str);
    }

    public static Session.SessionSaveData a(Session session) {
        return Session.a(session);
    }

    public static Session a(Context context, Session.SessionSaveData sessionSaveData, Session.SessionSaveData sessionSaveData2) {
        if (sessionSaveData2 == null && sessionSaveData == null) {
            Log.d(f7544a, "intent session and saved session all null");
            return null;
        }
        String str = sessionSaveData2 != null ? sessionSaveData2.c : sessionSaveData.c;
        StringBuilder sb = new StringBuilder();
        sb.append("session ");
        sb.append(str);
        sb.append(" restore from ");
        sb.append(sessionSaveData2 != null ? "intent data" : "saved data");
        Log.v(f7544a, sb.toString());
        if (sessionSaveData == null || sessionSaveData2 == null ? sessionSaveData != null : sessionSaveData2.g <= sessionSaveData.g) {
            sessionSaveData2 = sessionSaveData;
        }
        Session a2 = a(str);
        if (a2 == null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("session ");
            sb2.append(str);
            sb2.append(" is not in cache, restore from ");
            sb2.append(sessionSaveData2 == sessionSaveData ? "saved data" : "intent data");
            Log.v(f7544a, sb2.toString());
            Session session = new Session(context, sessionSaveData2);
            b.a(session);
            return session;
        }
        synchronized (a2) {
            if (sessionSaveData2.g > a2.h()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("session ");
                sb3.append(str);
                sb3.append(" in cache is out of date, try to update from ");
                sb3.append(sessionSaveData2 == sessionSaveData ? "saved data" : "intent data");
                Log.v(f7544a, sb3.toString());
                a2.a(sessionSaveData2);
            } else {
                Log.v(f7544a, "session " + str + " found in cache");
            }
        }
        return a2;
    }

    private static class SessionCache {

        /* renamed from: a  reason: collision with root package name */
        private static Map<String, SoftReference<Session>> f7545a = new ConcurrentHashMap();

        private SessionCache() {
        }

        public Session a(String str) {
            SoftReference softReference;
            if (!TextUtils.isEmpty(str) && (softReference = f7545a.get(str)) != null) {
                return (Session) softReference.get();
            }
            return null;
        }

        public void a(Session session) {
            if (session != null) {
                f7545a.put(session.g(), new SoftReference(session));
            }
        }
    }
}
