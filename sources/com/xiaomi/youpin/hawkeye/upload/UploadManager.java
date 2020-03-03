package com.xiaomi.youpin.hawkeye.upload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.storage.StorageManager;
import com.xiaomi.youpin.hawkeye.utils.AsyncThreadTask;
import com.xiaomi.youpin.hawkeye.utils.DeviceUtils;
import com.xiaomi.youpin.hawkeye.utils.HLog;
import java.util.HashMap;
import java.util.List;

public class UploadManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23382a = "UploadManager";
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public IUpload c;
    private BroadcastReceiver d;

    private static class UploadManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static UploadManager f23385a = new UploadManager();

        private UploadManagerHolder() {
        }
    }

    private UploadManager() {
        this.d = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                    boolean unused = UploadManager.this.c();
                }
                HLog.b("UploadManager", "");
            }
        };
    }

    public static UploadManager a() {
        return UploadManagerHolder.f23385a;
    }

    public void a(Context context) {
        HLog.b("UploadManager", "UploadManager  init  ******** ");
        this.b = context;
        this.c = HawkEye.c().d();
        this.b.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void b() {
        if (HawkEye.a() && c() && this.c != null) {
            AsyncThreadTask.a((Runnable) new Runnable() {
                public void run() {
                    List<BaseInfo> b = StorageManager.a().b();
                    if (!b.isEmpty()) {
                        for (BaseInfo next : b) {
                            UploadManager.this.c.a(UploadManager.this.b, next.id, UploadManager.this.a(next));
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public HashMap<String, String> a(BaseInfo baseInfo) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("timestamp", baseInfo.timestamp + "");
        hashMap.put("platform", baseInfo.platform);
        hashMap.put("statType", baseInfo.mStatType);
        hashMap.put("system", baseInfo.systemJson);
        hashMap.put("statInfo", baseInfo.statInfoJson);
        return hashMap;
    }

    /* access modifiers changed from: private */
    public boolean c() {
        return DeviceUtils.a(this.b) || HawkEye.c().c();
    }
}
