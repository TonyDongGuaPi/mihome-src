package com.xiaomi.smarthome.service.tasks;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.miot.service.IMiuiService;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServiceManager extends IMiuiService.Stub {
    private static final int CORE_POOL_SIZE = 1;
    private static final int KEEPALIVE_TIME = 90;
    private static final int MAX_POOL_SIZE = 5;
    private static final int MAX_QUEUE = 256;
    private static final String TAG = "ServiceManager";
    private static final int TIME_GAP = 180000;
    /* access modifiers changed from: private */
    public GetNearListTask getNearListTask;
    private long lastRefreshTime = 0;
    /* access modifiers changed from: private */
    public ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(1, 5, 90, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(256));

    public ServiceManager() {
        SmartHomeDeviceManager.a().q();
    }

    public void isLogin(ICallback iCallback) throws RemoteException {
        Log.i(TAG, "isLogin");
        this.mExecutor.execute(new LoginTask(iCallback));
    }

    public void getDeviceList(ICallback iCallback) throws RemoteException {
        Log.i(TAG, "getDeviceList");
        if (this.mExecutor != null) {
            this.mExecutor.execute(new GetDeviceTask(iCallback, true));
        }
        STAT.e.d();
    }

    public void setDeviceOn(String str, boolean z, ICallback iCallback) throws RemoteException {
        Log.i(TAG, "setDeviceOn");
        this.lastRefreshTime = System.currentTimeMillis();
        this.mExecutor.execute(new DeviceOnTask(str, z, iCallback));
    }

    public void scanNearList(boolean z, boolean z2, int i, ICallback iCallback) throws RemoteException {
        Log.i(TAG, "scanNearList");
        final boolean z3 = z;
        final boolean z4 = z2;
        final int i2 = i;
        final ICallback iCallback2 = iCallback;
        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
            public void onDeviceReady(List<Device> list) {
                Log.i(ServiceManager.TAG, "scanNearList onDeviceReady");
                if (ServiceManager.this.getNearListTask == null) {
                    GetNearListTask unused = ServiceManager.this.getNearListTask = new GetNearListTask();
                }
                ServiceManager.this.getNearListTask.a(z3, z4, i2, iCallback2);
            }
        });
    }

    public void stopScanNear() {
        Log.i(TAG, "stopScanNear");
        if (this.getNearListTask == null) {
            this.getNearListTask = new GetNearListTask();
        }
        this.getNearListTask.a();
    }

    public void isSameCard(String str, ICallback iCallback) throws RemoteException {
        if (iCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("same", MainPageOpManager.a().d(SmartHomeDeviceManager.a().b(str)));
            iCallback.onSuccess(bundle);
        }
    }

    public void getDeviceDesc(final boolean z, final ICallback iCallback) throws RemoteException {
        Log.i(TAG, "getDeviceDesc");
        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
            public void onDeviceReady(List<Device> list) {
                Log.i(ServiceManager.TAG, "getDeviceDesc onDeviceReady");
                if (SmartHomeDeviceManager.a().f().size() == 0) {
                    Log.i(ServiceManager.TAG, "getDeviceDesc onDeviceReady size = 0");
                    try {
                        ServiceManager.this.getDeviceList(new ICallback.Stub() {
                            public void onSuccess(Bundle bundle) throws RemoteException {
                                ServiceManager.this.mExecutor.execute(new CallApitTask(iCallback, z, "/v2/home/get_negative_screen_desc", "{}"));
                            }

                            public void onFailure(Bundle bundle) throws RemoteException {
                                Log.i(ServiceManager.TAG, "getDeviceDesc onDeviceReady onFailure");
                                iCallback.onFailure(bundle);
                            }
                        });
                    } catch (RemoteException e) {
                        Log.e(ServiceManager.TAG, Log.getStackTraceString(e));
                    }
                } else {
                    ServiceManager.this.mExecutor.execute(new CallApitTask(iCallback, z, "/v2/home/get_negative_screen_desc", "{}"));
                }
            }
        });
    }
}
