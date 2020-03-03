package com.xiaomi.mistatistic.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.mistatistic.sdk.a;
import com.xiaomi.mistatistic.sdk.controller.f;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import java.util.List;

public class BaseService extends Service {
    public static final String CATEGORY = "category";
    public static final int DELETE_BY_START_END_TIME = 5;
    public static final int DELETE_BY_TS = 4;
    public static final int DELETE_OLD = 3;
    public static final String END_TIME = "endTime";
    public static final int INSERT = 1;
    public static final String KEY = "key";
    public static final String NEW_VALUE = "newValue";
    public static final String START_TIME = "startTime";
    public static final String STAT_EVENT_POJO = "StatEventPojo";
    public static final String TIME_STAMP = "timeStamp";
    public static final String TYPE = "type";
    public static final int UPDATE = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public f f11991a;

    public void onCreate() {
        super.onCreate();
        f.a();
        this.f11991a = new f();
    }

    public IBinder onBind(Intent intent) {
        return new a.C0085a() {
            public StatEventPojo a(String str, String str2) throws RemoteException {
                return BaseService.this.f11991a.b(str, str2);
            }

            public List<StatEventPojo> a(long j) throws RemoteException {
                return BaseService.this.f11991a.b(j);
            }

            public int a() throws RemoteException {
                return BaseService.this.f11991a.e();
            }
        };
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || intent.getExtras() == null) {
            return 1;
        }
        Bundle extras = intent.getExtras();
        if (!extras.containsKey("type")) {
            return 1;
        }
        switch (extras.getInt("type")) {
            case 1:
                this.f11991a.b((StatEventPojo) extras.getParcelable(STAT_EVENT_POJO));
                return 1;
            case 2:
                this.f11991a.b(extras.getString("key"), extras.getString("category"), extras.getString(NEW_VALUE));
                return 1;
            case 3:
                this.f11991a.c();
                return 1;
            case 4:
                this.f11991a.d(extras.getLong(TIME_STAMP));
                return 1;
            case 5:
                this.f11991a.b(extras.getLong("startTime"), extras.getLong("endTime"));
                return 1;
            default:
                return 1;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
