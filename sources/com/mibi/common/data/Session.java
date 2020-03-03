package com.mibi.common.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.exception.PaymentException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Session {

    /* renamed from: a  reason: collision with root package name */
    static final String f7542a = "groupGlobalSettings";
    private static final String b = "Session";
    private volatile PaymentResponse c;
    private final String d;
    private final AccountLoader e;
    private volatile long f;
    private volatile Boolean g = false;
    private volatile int h;
    private volatile String i;
    private final UserStorage j;
    private final MemoryStorage k;
    private final Context l;
    private final ConcurrentHashMap<String, Object> m = new ConcurrentHashMap<>();

    Session(Context context, AccountLoader accountLoader, PaymentResponse paymentResponse) {
        this.l = context.getApplicationContext();
        this.e = accountLoader;
        this.d = UUID.randomUUID().toString();
        this.c = paymentResponse;
        this.f = System.currentTimeMillis();
        this.j = UserStorage.a(this.l, accountLoader.a());
        this.k = MemoryStorage.a();
    }

    Session(Context context, SessionSaveData sessionSaveData) {
        this.l = context.getApplicationContext();
        this.e = sessionSaveData.b;
        this.d = sessionSaveData.c;
        this.c = sessionSaveData.f7543a;
        this.g = Boolean.valueOf(sessionSaveData.d);
        this.h = sessionSaveData.e;
        this.i = sessionSaveData.f;
        this.f = sessionSaveData.g;
        this.j = UserStorage.a(this.l, this.e.a());
        this.k = sessionSaveData.h;
    }

    static SessionSaveData a(Session session) {
        SessionSaveData sessionSaveData;
        if (session != null) {
            synchronized (session) {
                session.f = System.currentTimeMillis();
                sessionSaveData = new SessionSaveData();
                sessionSaveData.f7543a = session.c;
                sessionSaveData.b = session.e;
                sessionSaveData.c = session.d;
                sessionSaveData.d = session.g.booleanValue();
                sessionSaveData.e = session.h;
                sessionSaveData.f = session.i;
                sessionSaveData.g = session.f;
                sessionSaveData.h = session.k;
                Log.v(b, "session " + session.d + " saved at " + session.f);
            }
            return sessionSaveData;
        }
        throw new IllegalArgumentException("session to save is null");
    }

    /* access modifiers changed from: package-private */
    public void a(SessionSaveData sessionSaveData) {
        m().a(sessionSaveData.h);
        this.g = Boolean.valueOf(sessionSaveData.d);
        this.h = sessionSaveData.e;
        this.i = sessionSaveData.f;
        this.f = sessionSaveData.g;
    }

    /* access modifiers changed from: package-private */
    public Object a(String str) {
        Object obj = this.m.get(str);
        if (obj != null) {
            return obj;
        }
        Object obj2 = new Object();
        Object putIfAbsent = this.m.putIfAbsent(str, obj2);
        return putIfAbsent == null ? obj2 : putIfAbsent;
    }

    public void a(Context context) throws PaymentException {
        this.e.b(context);
    }

    public void b(Context context) throws PaymentException {
        this.e.c(context);
    }

    public boolean a() {
        return this.e.a(this.l);
    }

    public synchronized void a(int i2, String str) {
        this.g = true;
        this.h = i2;
        this.i = str;
        b(i2, str);
    }

    public boolean b() {
        return this.g.booleanValue();
    }

    public int c() {
        return this.h;
    }

    public String d() {
        return this.i;
    }

    public AccountLoader e() {
        return this.e;
    }

    public String f() {
        return e().a();
    }

    public String g() {
        return this.d;
    }

    public long h() {
        return this.f;
    }

    public Context i() {
        return this.l;
    }

    public boolean j() {
        return this.c != null && this.c.a();
    }

    public void a(Intent intent) {
        if (j()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("intent", intent);
            this.c.a(bundle);
        }
    }

    public void a(Bundle bundle) {
        if (j()) {
            this.c.a(bundle);
            this.c = null;
        }
    }

    public void b(int i2, String str) {
        if (j()) {
            this.c.a(i2, str);
            this.c = null;
        }
    }

    public void a(int i2, String str, Bundle bundle) {
        if (j()) {
            this.c.a(i2, str, bundle);
            this.c = null;
        }
    }

    public StorageDir k() {
        return this.j.a();
    }

    public StorageDir b(String str) {
        return this.j.a(str);
    }

    public SharedPreferences l() {
        return this.j.b();
    }

    public SharedPreferences c(String str) {
        return this.j.b(str);
    }

    public MemoryStorage m() {
        return this.k;
    }

    public static final class SessionSaveData implements Parcelable {
        public static final Parcelable.Creator<SessionSaveData> CREATOR = new Parcelable.Creator<SessionSaveData>() {
            /* renamed from: a */
            public SessionSaveData[] newArray(int i) {
                return new SessionSaveData[i];
            }

            /* renamed from: a */
            public SessionSaveData createFromParcel(Parcel parcel) {
                SessionSaveData sessionSaveData = new SessionSaveData();
                sessionSaveData.f7543a = (PaymentResponse) parcel.readParcelable(PaymentResponse.class.getClassLoader());
                sessionSaveData.b = (AccountLoader) parcel.readParcelable(AccountLoader.class.getClassLoader());
                sessionSaveData.c = parcel.readString();
                sessionSaveData.d = parcel.readByte() != 0;
                sessionSaveData.e = parcel.readInt();
                sessionSaveData.f = parcel.readString();
                sessionSaveData.g = parcel.readLong();
                sessionSaveData.h = MemoryStorage.a();
                sessionSaveData.h.b(parcel);
                return sessionSaveData;
            }
        };

        /* renamed from: a  reason: collision with root package name */
        PaymentResponse f7543a;
        AccountLoader b;
        String c;
        boolean d;
        int e;
        String f;
        long g;
        MemoryStorage h;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f7543a, i);
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeByte(this.d ? (byte) 1 : 0);
            parcel.writeInt(this.e);
            parcel.writeString(this.f);
            parcel.writeLong(this.g);
            this.h.a(parcel);
        }
    }
}
