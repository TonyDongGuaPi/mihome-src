package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.location.LocationPermissionDialogHelper;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiScanManager;
import com.yanzhenjie.permission.Action;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public enum DeviceScanManager {
    instance;
    
    private static final String TAG = "DeviceScanManager";
    /* access modifiers changed from: private */
    public boolean isResumed;
    private DevicePushBindManager.OnScanListener mAiotListener;
    /* access modifiers changed from: private */
    public Map<String, PushBindEntity> mAiotWifiResult;
    /* access modifiers changed from: private */
    public List<BleDevice> mBleDevices;
    /* access modifiers changed from: private */
    public final Runnable mBleSearch;
    private BroadcastReceiver mBroadcastReceiver;
    /* access modifiers changed from: private */
    public List<Object> mCacheScanResult;
    /* access modifiers changed from: private */
    public List<ScanResult> mDeviceScanResult;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private final List<OnScanListener> mListener;
    /* access modifiers changed from: private */
    public List<MiTVDevice> mMitvDevices;
    private final Runnable mScanInterval;
    /* access modifiers changed from: private */
    public List<ScanResult> mScanResult;
    private Dialog mTmpDialog;
    private Runnable mUpdateView;

    public interface OnScanListener {
        void onScan(List<?> list);
    }

    /* access modifiers changed from: private */
    public void dispatchOnScan(List<?> list) {
        for (int i = 0; i < this.mListener.size(); i++) {
            this.mListener.get(i).onScan(list);
        }
    }

    public void checkAll(Activity activity) {
        Home m = HomeManager.a().m();
        if (m == null || m.p()) {
            checkTips(activity);
        } else {
            showSwitchHome(activity);
        }
    }

    private void showSwitchHome(final Activity activity) {
        if (this.mTmpDialog != null && this.mTmpDialog.isShowing()) {
            this.mTmpDialog.dismiss();
        }
        this.mTmpDialog = new MLAlertDialog.Builder(activity).b((int) R.string.share_home_adddevice_tips).a((int) R.string.change_home, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String str;
                List<Home> f = HomeManager.a().f();
                if (f != null) {
                    Iterator<Home> it = f.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str = null;
                            break;
                        }
                        Home next = it.next();
                        if (next != null && next.p()) {
                            str = next.j();
                            break;
                        }
                    }
                    HomeManager.a().a(str, (AsyncCallback) null);
                }
                DeviceScanManager.this.checkTips(activity);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity activity = activity;
                if (activity != null) {
                    activity.finish();
                }
            }
        }).a(false).d();
    }

    /* access modifiers changed from: private */
    public void checkTips(Activity activity) {
        if (!PreferenceUtils.a("open_find_device_tips", true) || !CommonUtils.p()) {
            checkPermission(activity);
        } else {
            showFindDeviceTips(activity);
        }
    }

    private void showFindDeviceTips(final Activity activity) {
        STAT.e.a();
        PreferenceUtils.b("open_find_device_tips", false);
        if (this.mTmpDialog != null && this.mTmpDialog.isShowing()) {
            this.mTmpDialog.dismiss();
        }
        this.mTmpDialog = new MLAlertDialog.Builder(activity).a((int) R.string.setting_tips_title).b((int) R.string.open_find_device_tips).a((int) R.string.auto_discovery_setting_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                STAT.d.P();
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.m, true);
            }
        }).b((int) R.string.setting_later, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                STAT.d.O();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                STAT.d.O();
            }
        }).a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
            public void beforeDismissCallBack() {
            }

            public void afterDismissCallBack() {
                DeviceScanManager.this.checkPermission(activity);
            }
        }).b();
        this.mTmpDialog.show();
    }

    /* access modifiers changed from: private */
    public void checkPermission(final Activity activity) {
        boolean z;
        try {
            z = ((WifiManager) activity.getApplicationContext().getSystemService("wifi")).isWifiEnabled();
        } catch (Exception unused) {
            z = false;
        }
        if (!z) {
            if (this.mTmpDialog != null && this.mTmpDialog.isShowing()) {
                this.mTmpDialog.dismiss();
            }
            this.mTmpDialog = new MLAlertDialog.Builder(activity).b((int) R.string.wifi_disable_hint).a((int) R.string.setting, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        activity.startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialogInterface.dismiss();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).a(activity.getResources().getColor(R.color.miui_blue), -1).d();
        }
        if (!LocationPermissionDialogHelper.a(activity, false, R.string.location_disable_hint)) {
            if (this.mTmpDialog != null && this.mTmpDialog.isShowing()) {
                this.mTmpDialog.dismiss();
            }
            if (CommonUtils.o()) {
                PermissionHelper.a(activity, true, (Action) null, activity.getString(R.string.permission_location_rational_desc_new));
            } else {
                LocationPermissionDialogHelper.a(activity, true, R.string.permission_location_rational_desc_new);
            }
        }
        if (!BluetoothUtils.b()) {
            openBluetooth(activity);
        } else {
            startBleScan();
        }
    }

    public void getScanCache(OnScanListener onScanListener) {
        onScanListener.onScan(this.mCacheScanResult);
    }

    public void onResume(OnScanListener onScanListener) {
        LogUtil.c(TAG, "onResume " + onScanListener);
        this.isResumed = true;
        if (onScanListener != null && !this.mListener.contains(onScanListener)) {
            this.mListener.add(onScanListener);
        }
        this.mScanResult.clear();
        if (WifiDeviceFinder.j != null) {
            this.mScanResult.addAll(WifiDeviceFinder.j);
        }
        this.mDeviceScanResult.clear();
        this.mDeviceScanResult.addAll(ApDeviceManager.a().c());
        this.mBleDevices.clear();
        this.mBleDevices.addAll(BLEDeviceManager.c());
        this.mAiotWifiResult.clear();
        this.mAiotWifiResult.putAll(DevicePushBindManager.instance.getCache());
        this.mMitvDevices.clear();
        this.mMitvDevices.addAll(MitvDeviceManager.b().f());
        updateScanDeviceView();
        IntentFilter intentFilter = new IntentFilter("wifi_scan_result_broadcast");
        intentFilter.addAction(BLEDeviceManager.f15064a);
        intentFilter.addAction(ApDeviceManager.d);
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).registerReceiver(this.mBroadcastReceiver, intentFilter);
        CommonApplication.getAppContext().registerReceiver(this.mBroadcastReceiver, intentFilter);
        DevicePushBindManager.instance.registScanListener(this.mAiotListener);
        this.mHandler.post(this.mBleSearch);
        this.mHandler.post(this.mScanInterval);
        WifiScanManager.a().b();
    }

    public void onPause(OnScanListener onScanListener) {
        LogUtil.c(TAG, "onPause " + onScanListener);
        this.isResumed = false;
        this.mListener.remove(onScanListener);
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).unregisterReceiver(this.mBroadcastReceiver);
        CommonApplication.getAppContext().unregisterReceiver(this.mBroadcastReceiver);
        DevicePushBindManager.instance.unregistScanListener(this.mAiotListener);
        this.mHandler.removeCallbacks(this.mBleSearch);
        this.mHandler.removeCallbacks(this.mScanInterval);
        CoreApi.a().L();
        BLEDeviceManager.f();
    }

    /* access modifiers changed from: private */
    public void startBleScan() {
        BLEDeviceManager.a((MiioBtSearchResponse) null);
        this.mHandler.postDelayed(this.mBleSearch, 11000);
    }

    private void openBluetooth(Activity activity) {
        BluetoothUtils.a(activity, R.string.open_bluetooth_tips, new BleResponse() {
            public void a(int i, Object obj) {
                STAT.d.M();
                if (i == 0) {
                    DeviceScanManager.this.startBleScan();
                }
            }
        });
    }

    public void updateScanDeviceView() {
        this.mHandler.removeCallbacks(this.mUpdateView);
        this.mHandler.postDelayed(this.mUpdateView, 500);
    }

    /* access modifiers changed from: private */
    public void removeDuplicateDevice(List<ScanResult> list, List<ScanResult> list2, List<BleDevice> list3, Map<String, PushBindEntity> map) {
        int i;
        boolean z;
        for (int i2 = 0; i2 < list3.size(); i2 = i + 1) {
            if (DeviceFactory.a(list3.get(i2))) {
                int i3 = 0;
                while (true) {
                    if (i3 >= list.size()) {
                        i = i2;
                        z = false;
                        break;
                    } else if (isSameCombDevice(list.get(i3), list3.get(i2))) {
                        list3.remove(i2);
                        i = i2 - 1;
                        z = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z) {
                    for (int i4 = 0; i4 < list2.size(); i4++) {
                        if (isSameCombDevice(list2.get(i4), list3.get(i))) {
                            list3.remove(i);
                            i--;
                        }
                    }
                }
            } else {
                i = i2;
            }
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            String str = list.get(size).BSSID;
            if (str != null && (map.containsKey(str.toLowerCase(Locale.ENGLISH)) || map.containsKey(str.toUpperCase(Locale.ENGLISH)))) {
                list.remove(size);
            }
        }
    }

    private boolean isSameCombDevice(ScanResult scanResult, BleDevice bleDevice) {
        if (scanResult == null || bleDevice == null || !DeviceFactory.g(scanResult) || !DeviceFactory.a(bleDevice) || bleDevice.d() == null) {
            return false;
        }
        String str = bleDevice.d().f;
        String str2 = scanResult.SSID;
        if (!TextUtils.isEmpty(str2) && str.contains(DeviceFactory.g(str2))) {
            return true;
        }
        String h = DeviceFactory.h(scanResult);
        if (TextUtils.isEmpty(h) || !str.equals(h.toUpperCase())) {
            return false;
        }
        return true;
    }

    public void removeMitv(MiTVDevice miTVDevice) {
        this.mMitvDevices.remove(miTVDevice);
    }
}
