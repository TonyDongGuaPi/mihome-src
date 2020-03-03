package com.xiaomi.smarthome.device.bluetooth;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleConnectActivity;
import com.xiaomi.smarthome.device.bluetooth.ui.BleMatchActivity;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.ArrayList;

public class BleDispatcher extends BluetoothContextManager {
    public static void a(Context context, BleDevice bleDevice, Intent intent, ArrayList<String> arrayList) {
        if (bleDevice != null) {
            Log.i("stopScan", "BleDispatcher stop");
            BluetoothHelper.b();
            if (DeviceFactory.t.equalsIgnoreCase(bleDevice.model) || DeviceFactory.u.equalsIgnoreCase(bleDevice.model)) {
                Intent intent2 = new Intent("android.settings.BLUETOOTH_SETTINGS");
                if (intent != null) {
                    intent2.putExtras(intent);
                }
                context.startActivity(intent2);
            } else if ("onemore.soundbox.sm001".equals(bleDevice.model)) {
                b(context, bleDevice, intent, arrayList);
            } else if (bleDevice.o()) {
                a(context, bleDevice, intent);
            } else if (bleDevice.m()) {
                b(context, bleDevice, intent, arrayList);
            } else {
                a(context, bleDevice, intent);
            }
        }
    }

    public static void b(Context context, BleDevice bleDevice, Intent intent, ArrayList<String> arrayList) {
        BleDeviceGroup a2;
        if (bleDevice == null) {
            BluetoothLog.e("device null");
        } else if ((bleDevice instanceof BleDeviceGroup) && (a2 = BLEDeviceManager.a(bleDevice.model)) != null) {
            if (a(a2)) {
                BleMatchActivity.onDeviceMatched((Activity) context, a2.w().get(0), intent);
                return;
            }
            c(context, bleDevice, intent, arrayList);
        }
    }

    public static boolean a(BleDevice bleDevice) {
        if (bleDevice == null || bleDevice.d() == null || bleDevice.d().f14276a == null) {
            return false;
        }
        if ((TextUtils.equals(bleDevice.model, DeviceFactory.C) || TextUtils.equals(bleDevice.model, DeviceFactory.D)) && bleDevice.d().f14276a.l == 5) {
            return true;
        }
        return false;
    }

    private static boolean a(BleDeviceGroup bleDeviceGroup) {
        if (bleDeviceGroup.r() != 1 || "xiaomi.mikey.v1".equals(bleDeviceGroup.model) || a(bleDeviceGroup.w().get(0))) {
            return false;
        }
        if (!bleDeviceGroup.m()) {
            return true;
        }
        String t = bleDeviceGroup.t();
        switch (bleDeviceGroup.u()) {
            case 0:
            case 1:
                return TextUtils.isEmpty(t);
        }
        return false;
    }

    private static void c(Context context, BleDevice bleDevice, Intent intent, ArrayList<String> arrayList) {
        Intent intent2 = new Intent();
        if (intent != null) {
            intent2.putExtras(intent);
        }
        intent2.putExtra("model", bleDevice.model);
        intent2.putExtra(BleMatchActivity.KEY_COMBINE_MODEL, arrayList);
        intent2.putExtra(BluetoothConstants.i, BluetoothConstants.o);
        intent2.setClass(context, BleMatchActivity.class);
        context.startActivity(intent2);
    }

    public static void a(Activity activity, BleDevice bleDevice, Intent intent) {
        if (bleDevice.m()) {
            Intent intent2 = new Intent();
            intent2.setClass(activity, BleBindActivityV2.class);
            intent2.putExtra(BluetoothConstants.i, BluetoothConstants.m);
            if (intent != null && intent.hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                intent2.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
            }
            activity.startActivity(intent2);
            c(activity);
            return;
        }
        a((Context) activity, bleDevice, (Intent) null, true);
    }

    public static void a(Context context, Device device) {
        if (device == null) {
            BluetoothLog.e("device null, return");
            return;
        }
        Intent intent = new Intent();
        if (context instanceof Application) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        intent.putExtra("model", device.model);
        intent.putExtra(BluetoothConstants.i, BluetoothConstants.o);
        intent.putExtra("extra_did", device.did);
        intent.putExtra("extra_mac", device.mac);
        intent.setClass(context, BleConnectActivity.class);
        context.startActivity(intent);
    }

    public static void a(Context context, BleDevice bleDevice, Intent intent, boolean z) {
        PluginRecord d;
        Context context2 = context;
        BleDevice bleDevice2 = bleDevice;
        Intent intent2 = intent;
        if (bleDevice2 != null && (d = CoreApi.a().d(bleDevice2.model)) != null) {
            final XQProgressHorizontalDialog b = XQProgressHorizontalDialog.b(context, context.getString(R.string.plugin_downloading) + d.p() + context.getString(R.string.plugin));
            PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            Intent intent3 = new Intent();
            intent3.putExtra("null", "null");
            intent3.setExtrasClassLoader(BleDispatcher.class.getClassLoader());
            ArrayList arrayList = new ArrayList();
            int i = 1;
            if (!(bleDevice2 instanceof BleDeviceGroup)) {
                arrayList.add(bleDevice.a());
                intent3.putParcelableArrayListExtra("devices", arrayList);
            } else if (!"yeelink.light.ble1".equalsIgnoreCase(bleDevice2.model)) {
                arrayList.addAll(((BleDeviceGroup) bleDevice2).x());
                intent3.putParcelableArrayListExtra("devices", arrayList);
                i = 8;
            }
            if (intent2 != null) {
                intent3.putExtras(intent2);
            }
            intent3.putExtra("extra_device_model", bleDevice2.model);
            intent3.putExtra("extra_device_did", bleDevice2.did);
            byte[] l = BleCacheUtils.l(bleDevice2.mac);
            if (!ByteUtils.e(l)) {
                intent3.putExtra(XmBluetoothManager.EXTRA_SCANRECORD, l);
            }
            byte[] q = BleCacheUtils.q(bleDevice2.mac);
            if (!ByteUtils.e(q)) {
                intent3.putExtra("token", q);
            }
            BleGattProfile m = BleCacheUtils.m(bleDevice2.mac);
            if (m != null) {
                intent3.putExtra("key_gatt_profile", m);
            }
            bleDevice2.setLaunchParams(intent3);
            DeviceStat newDeviceStat = bleDevice.newDeviceStat();
            newDeviceStat.userId = CoreApi.a().s();
            bleDevice2.userId = newDeviceStat.userId;
            BluetoothHelper.b(bleDevice2.mac);
            final PluginDownloadTask pluginDownloadTask2 = pluginDownloadTask;
            final Context context3 = context;
            final PluginRecord pluginRecord = d;
            final boolean z2 = z;
            PluginApi.getInstance().sendMessage(n(), d, i, intent3, newDeviceStat, (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                public void onMessageFailure(int i, String str) {
                }

                public void onMessageSuccess(Intent intent) {
                }

                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    pluginDownloadTask.a(pluginDownloadTask2);
                    if (BleDispatcher.d(context3) && b != null) {
                        b.a(100, 0);
                        b.c();
                        b.setCancelable(true);
                        b.show();
                        b.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                                CoreApi.a().a(pluginRecord.o(), pluginDownloadTask2, (CoreApi.CancelPluginDownloadCallback) null);
                            }
                        });
                    }
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                    if (BleDispatcher.d(context3) && b != null) {
                        b.a(100, (int) (f * 100.0f));
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (b != null) {
                        b.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (b != null) {
                        b.dismiss();
                    }
                    Toast.makeText(BluetoothContextManager.n(), R.string.plugin_download_failure, 1).show();
                }

                public void onDownloadCancel() {
                    if (b != null) {
                        b.dismiss();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (z2) {
                        BleDispatcher.c(context3);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void c(final Context context) {
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                try {
                    if (!(context instanceof SmartHomeMainActivity) && !(context instanceof ChooseDeviceActivity)) {
                        ((Activity) context).finish();
                    }
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        }, 300);
    }

    /* access modifiers changed from: private */
    public static boolean d(Context context) {
        if (context instanceof Activity) {
            return !((Activity) context).isFinishing();
        }
        return false;
    }

    public static void a(Context context, BleDevice bleDevice, Intent intent) {
        a(context, bleDevice, intent, false);
    }
}
