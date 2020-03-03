package com.xiaomi.smarthome.listcamera;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CameraInfoRefreshManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19255a = "camera_info_cache_complete";
    public static final String b = "camera_info_complete";
    private static final String c = "camera_list_info";
    private static final int d = 0;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 1;
    private static final int h = 2;
    private static CameraInfoRefreshManager i;
    private HandlerThread j = new MessageHandlerThread(c);
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public boolean l = false;
    private AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean n = true;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public boolean p = false;
    /* access modifiers changed from: private */
    public boolean q = false;
    /* access modifiers changed from: private */
    public List<RefreshListener> r = new ArrayList();
    /* access modifiers changed from: private */
    public Handler s = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 4) {
                switch (i) {
                    case 1:
                        boolean unused = CameraInfoRefreshManager.this.l = false;
                        CameraDeviceOpManager.a().b();
                        CameraGroupManager.a().b();
                        for (RefreshListener a2 : CameraInfoRefreshManager.this.r) {
                            a2.a();
                        }
                        return;
                    case 2:
                        boolean unused2 = CameraInfoRefreshManager.this.k = false;
                        if (((Class) message.obj).equals(CameraGroupManager.class)) {
                            boolean unused3 = CameraInfoRefreshManager.this.o = false;
                        }
                        if (!CameraInfoRefreshManager.this.o) {
                            if (CameraInfoRefreshManager.this.p) {
                                CameraGroupManager.a().b();
                            }
                            for (RefreshListener b : CameraInfoRefreshManager.this.r) {
                                b.b();
                            }
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(GetDeviceTask.h));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                CameraInfoRefreshManager.this.h();
            }
        }
    };
    private WorkerHandler t;

    public interface RefreshListener {
        void a();

        void b();
    }

    public static CameraInfoRefreshManager a() {
        if (i == null) {
            i = new CameraInfoRefreshManager();
        }
        return i;
    }

    private class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                CameraGroupManager.a().g();
                CameraInfoRefreshManager.this.s.sendMessage(CameraInfoRefreshManager.this.s.obtainMessage(1));
                Log.e("device_rpc", "Refresh Cache complete");
                CameraGroupManager.a().b((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        boolean unused = CameraInfoRefreshManager.this.p = true;
                        CameraInfoRefreshManager.this.s.sendMessage(CameraInfoRefreshManager.this.s.obtainMessage(2, CameraGroupManager.class));
                    }

                    public void onFailure(Error error) {
                        boolean unused = CameraInfoRefreshManager.this.p = false;
                        CameraInfoRefreshManager.this.s.sendMessage(CameraInfoRefreshManager.this.s.obtainMessage(2, CameraGroupManager.class));
                    }
                });
            } else if (i == 3) {
                Log.e("device_rpc", "Start Refresh PROP");
                CameraDeviceOpManager.a().b((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        LogUtil.a("refresh", "loadAllCameraCloudStorageInfoFromServer onSuccess");
                    }

                    public void onFailure(Error error) {
                        LogUtil.a("refresh", "loadAllCameraCloudStorageInfoFromServer onFailure:" + error.a());
                    }
                });
                CameraDeviceOpManager.a().c((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        CameraInfoRefreshManager.this.s.post(new Runnable() {
                            public void run() {
                                for (RefreshListener b : CameraInfoRefreshManager.this.r) {
                                    b.b();
                                }
                            }
                        });
                        boolean unused = CameraInfoRefreshManager.this.k = false;
                        boolean unused2 = CameraInfoRefreshManager.this.q = true;
                        boolean unused3 = CameraInfoRefreshManager.this.n = true;
                    }

                    public void onFailure(Error error) {
                        CameraInfoRefreshManager.this.s.post(new Runnable() {
                            public void run() {
                                for (RefreshListener b : CameraInfoRefreshManager.this.r) {
                                    b.b();
                                }
                            }
                        });
                        boolean unused = CameraInfoRefreshManager.this.k = false;
                        boolean unused2 = CameraInfoRefreshManager.this.n = false;
                    }
                });
            }
        }
    }

    CameraInfoRefreshManager() {
        this.j.start();
        this.t = new WorkerHandler(this.j.getLooper());
    }

    public void a(RefreshListener refreshListener) {
        synchronized (this) {
            Iterator<RefreshListener> it = this.r.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(refreshListener)) {
                        this.r.remove(refreshListener);
                        break;
                    }
                } else {
                    break;
                }
            }
            this.r.add(refreshListener);
        }
    }

    public void b(RefreshListener refreshListener) {
        synchronized (this) {
            Iterator<RefreshListener> it = this.r.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(refreshListener)) {
                        this.r.remove(refreshListener);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.m.getAndSet(false)) {
            ArrayList arrayList = new ArrayList();
            for (Device next : MultiHomeDeviceManager.a().d()) {
                PluginRecord d2 = CoreApi.a().d(next.model);
                if (d2 != null && !IRDeviceUtil.a(d2) && next.model.contains(UserAvatarUpdateActivity.CAMERA) && !next.model.contains("virtual")) {
                    arrayList.add(next);
                }
            }
            for (Device next2 : MultiHomeDeviceManager.a().e()) {
                PluginRecord d3 = CoreApi.a().d(next2.model);
                if (d3 != null && !IRDeviceUtil.a(d3) && next2.model.contains(UserAvatarUpdateActivity.CAMERA) && !next2.model.contains("virtual")) {
                    arrayList.add(next2);
                }
            }
            CameraGroupManager.a().c((List<Device>) arrayList);
            for (RefreshListener b2 : this.r) {
                b2.b();
            }
            this.k = false;
            this.t.sendEmptyMessage(3);
            return;
        }
        this.k = false;
        SHApplication.getAppContext().sendBroadcast(new Intent(AllCameraPage.c));
        CameraGroupManager.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
            }

            public void onFailure(Error error) {
            }
        });
    }

    public boolean b() {
        return this.k;
    }

    public boolean c() {
        return this.q;
    }

    public boolean d() {
        return this.n && this.p;
    }

    public void e() {
        CameraGroupManager.a().e();
    }

    public void f() {
        if (!this.k) {
            this.k = true;
            this.o = true;
            this.q = false;
            this.p = false;
            this.l = false;
            this.t.sendEmptyMessage(0);
        }
    }

    public void g() {
        this.m.set(true);
        if (!this.k) {
            this.k = true;
            h();
        }
    }
}
