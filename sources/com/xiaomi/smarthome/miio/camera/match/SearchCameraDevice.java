package com.xiaomi.smarthome.miio.camera.match;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeSoManager;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthomedevice.R;
import java.io.File;
import java.util.ArrayList;

public class SearchCameraDevice {
    static final String TAG = "SearchCameraDevice";
    static final String VERSION = "1";
    private static SearchCameraDevice __INSTANCE;
    String mApkInstallPath = (CommonApplication.getAppContext().getFilesDir().getPath() + File.separator + "app" + File.separator + "1" + File.separator + "YunyiCameraSearch.apk");
    String mApkLibsInstallPath = (CommonApplication.getAppContext().getFilesDir().getPath() + File.separator + "app" + File.separator + "1" + File.separator + "libs");
    ArrayList<CameraDevice> mLocalCameraDevices = new ArrayList<>();
    boolean mSoLoaded = false;
    XmPluginPackage mXmpluginPackage;

    public interface DeviceListener {
        void onDevices(ArrayList<CameraDevice> arrayList);
    }

    private SearchCameraDevice() {
    }

    public static synchronized SearchCameraDevice getInstance() {
        SearchCameraDevice searchCameraDevice;
        synchronized (SearchCameraDevice.class) {
            if (__INSTANCE == null) {
                __INSTANCE = new SearchCameraDevice();
            }
            searchCameraDevice = __INSTANCE;
        }
        return searchCameraDevice;
    }

    public ArrayList<CameraDevice> getLocalCameraDevices() {
        return this.mLocalCameraDevices;
    }

    public boolean hasLocal(String str) {
        for (int i = 0; i < this.mLocalCameraDevices.size(); i++) {
            if (this.mLocalCameraDevices.get(i).did.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void startSearch(final DeviceListener deviceListener) {
        Log.e(TAG, "mStartSearch ");
        if (this.mXmpluginPackage != null && this.mXmpluginPackage.xmPluginMessageReceiver != null) {
            loadSo();
            try {
                this.mXmpluginPackage.xmPluginMessageReceiver.handleMessage(CommonApplication.getAppContext(), this.mXmpluginPackage, 100, (Intent) null, (DeviceStat) null, new MessageCallback() {
                    public void onSuccess(Intent intent) {
                        if (intent != null) {
                            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("devices");
                            MyLog.f("local camera devices:" + stringArrayListExtra.toString());
                            if (stringArrayListExtra != null) {
                                for (int i = 0; i < stringArrayListExtra.size(); i++) {
                                    String[] split = stringArrayListExtra.get(i).split(PaymentOptionsDecoder.pipeSeparator);
                                    if (split != null && split.length == 2) {
                                        SearchCameraDevice.this.addDevice(SearchCameraDevice.this.mLocalCameraDevices, split[0], split[1]);
                                    }
                                }
                            }
                        }
                        if (deviceListener != null) {
                            deviceListener.onDevices(SearchCameraDevice.this.mLocalCameraDevices);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (deviceListener != null) {
                            deviceListener.onDevices(SearchCameraDevice.this.mLocalCameraDevices);
                        }
                    }
                });
            } catch (Throwable th) {
                MyLog.a(th);
            }
        } else if (deviceListener != null) {
            deviceListener.onDevices(this.mLocalCameraDevices);
        }
    }

    /* access modifiers changed from: package-private */
    public void addDevice(ArrayList<CameraDevice> arrayList, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (!str.startsWith("yunyi.")) {
                str = "yunyi." + str;
            }
            Log.d(TAG, "addDevice:" + str);
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    break;
                } else if (arrayList.get(i).did.equals(str)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z && !TextUtils.isEmpty(str)) {
                CameraDevice cameraDevice = new CameraDevice();
                cameraDevice.name = CommonApplication.getAppContext().getString(R.string.camera_name);
                cameraDevice.did = str;
                cameraDevice.ip = str2;
                cameraDevice.mac = cameraDevice.did;
                cameraDevice.setUnbind();
                cameraDevice.location = Device.Location.LOCAL;
                cameraDevice.isOnline = true;
                arrayList.add(cameraDevice);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void loadSo() {
        if (this.mXmpluginPackage != null && !this.mSoLoaded) {
            for (String loadLibrary : new String[]{"IOTCAPIs", "AVAPIs", "anativehelper", "asp", "glnkio"}) {
                try {
                    PluginRuntimeSoManager.getInstance().loadLibrary(this.mApkLibsInstallPath, loadLibrary, this.mXmpluginPackage.classLoader);
                } catch (Throwable th) {
                    MyLog.a(th);
                }
            }
            this.mSoLoaded = true;
        }
    }
}
