package com.xiaomi.smarthome.core.server;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.message.CoreMessageType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoreManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14039a = "action.coremanager.process_dead";
    public static final String b = "process_name";
    public static final String c = "com.xiaomi.smarthome";
    public static final String d = "com.xiaomi.smarthome:core";
    public static final String e = "com.xiaomi.smarthome.notishortcut";
    public static final String f = "com.xiaomi.smarthome:pushservice";
    public static final String g = "com.xiaomi.smarthome:plugin";
    public static final String h = "com.xiaomi.smarthome:hotfix";
    public static final String i = ":core";
    public static final String j = ":plugin0";
    public static final String k = ":plugin1";
    public static final String l = ":plugin2";
    public static final String m = ":plugin3";
    public static final String n = ":frame1";
    public static final String o = ":frame2";
    public static final String p = ":hotfix";
    private static CoreManager t;
    private static Object u = new Object();
    HandlerThread q;
    Handler r;
    Map<Integer, ClientRecord> s = new ConcurrentHashMap();

    private CoreManager() {
    }

    public static CoreManager a() {
        if (t == null) {
            synchronized (u) {
                if (t == null) {
                    t = new CoreManager();
                }
            }
        }
        return t;
    }

    public Handler b() {
        return CommonApplication.getGlobalWorkerHandler();
    }

    public Handler c() {
        return this.r;
    }

    public void a(CoreAsyncTask coreAsyncTask) {
        CommonApplication.getGlobalWorkerHandler().post(coreAsyncTask);
    }

    public void a(IClientApi iClientApi, int i2, int i3) {
        ClientRecord clientRecord = this.s.get(Integer.valueOf(i2));
        if (clientRecord == null) {
            clientRecord = new ClientRecord();
        }
        String str = "";
        ActivityManager.RunningAppProcessInfo c2 = c(i2);
        if (c2 != null && !TextUtils.isEmpty(c2.processName)) {
            str = c2.processName;
        }
        clientRecord.a(iClientApi, i2, i3, str, d(i3), System.currentTimeMillis());
        this.s.put(Integer.valueOf(i2), clientRecord);
        try {
            ClientDeathRecipient clientDeathRecipient = new ClientDeathRecipient(i2);
            iClientApi.asBinder().linkToDeath(clientDeathRecipient, 0);
            clientRecord.a(clientDeathRecipient);
        } catch (RemoteException unused) {
        }
    }

    public void a(int i2) {
        if (this.s.containsKey(Integer.valueOf(i2))) {
            this.s.remove(Integer.valueOf(i2));
        }
    }

    public boolean b(int i2) {
        return this.s.containsKey(Integer.valueOf(i2));
    }

    public IClientApi a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry<Integer, ClientRecord> value : this.s.entrySet()) {
            ClientRecord clientRecord = (ClientRecord) value.getValue();
            if (!TextUtils.isEmpty(clientRecord.b()) && clientRecord.b().equalsIgnoreCase(str)) {
                return clientRecord.a();
            }
        }
        return null;
    }

    public ClientRecord b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry<Integer, ClientRecord> value : this.s.entrySet()) {
            ClientRecord clientRecord = (ClientRecord) value.getValue();
            if (!TextUtils.isEmpty(clientRecord.b()) && clientRecord.b().equalsIgnoreCase(str)) {
                return clientRecord;
            }
        }
        return null;
    }

    public List<IClientApi> d() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, ClientRecord> value : this.s.entrySet()) {
            arrayList.add(((ClientRecord) value.getValue()).a());
        }
        return arrayList;
    }

    public void a(CoreMessageType coreMessageType, Bundle bundle) {
        for (Map.Entry value : this.s.entrySet()) {
            try {
                ((ClientRecord) value.getValue()).a().onCoreMessage(coreMessageType, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    private ActivityManager.RunningAppProcessInfo c(int i2) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) CoreService.getAppContext().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (int i3 = 0; i3 < runningAppProcesses.size(); i3++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i3);
                if (runningAppProcessInfo != null && runningAppProcessInfo.pid == i2) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    private String[] d(int i2) {
        try {
            return CoreService.getAppContext().getPackageManager().getPackagesForUid(i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return new String[0];
        }
    }

    private final class ClientDeathRecipient implements IBinder.DeathRecipient {
        private int b;

        ClientDeathRecipient(int i) {
            this.b = i;
        }

        public void binderDied() {
            ClientRecord clientRecord = CoreManager.this.s.get(Integer.valueOf(this.b));
            CoreManager.this.a(this.b);
            if (clientRecord != null) {
                LocalBroadcastManager instance = LocalBroadcastManager.getInstance(CoreService.getAppContext());
                Intent intent = new Intent(CoreManager.f14039a);
                intent.putExtra(CoreManager.b, clientRecord.b());
                instance.sendBroadcast(intent);
            }
        }
    }
}
