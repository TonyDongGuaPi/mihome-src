package com.xiaomi.smarthome.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public class DeviceBroadcastReceiver extends BroadcastReceiver {
    static final String TAG = "DeviceBroadcastReceiver";

    public void onReceive(Context context, final Intent intent) {
        BluetoothLog.c("DeviceBroadcastReceiver.onReceive " + intent.getAction());
        if ("com.xiaomi.smarthome.RECEIVE_MESSAGE".equals(intent.getAction())) {
            Log.d(TAG, "onReceive:" + intent.toString());
            final Device n = SmartHomeDeviceManager.a().n(intent.getStringExtra("device_id"));
            final String stringExtra = n != null ? n.model : intent.getStringExtra(ApiConst.f);
            CoreApi.a().a(context, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    boolean c2 = CoreApi.a().c(stringExtra);
                    BluetoothLog.c(">>> model = " + stringExtra + ", isPlugin = " + c2);
                    if (!TextUtils.isEmpty(stringExtra) && c2) {
                        PluginRecord d2 = CoreApi.a().d(stringExtra);
                        if (d2 == null) {
                            Log.e(DeviceBroadcastReceiver.TAG, "not found plugin record:" + stringExtra);
                        } else if (!d2.A()) {
                            Log.e(DeviceBroadcastReceiver.TAG, "plugin is not installed:" + stringExtra);
                        } else {
                            Intent intent = new Intent();
                            if (intent != null) {
                                intent.putExtras(intent);
                            }
                            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 15, intent, n != null ? n.newDeviceStat() : null, (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                    super.onDownloadStart(pluginRecord, pluginDownloadTask);
                                    BluetoothLog.c(">>> onDownloadStart");
                                }

                                public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                                    super.onDownloadProgress(pluginRecord, f);
                                    BluetoothLog.c(">>> onDownloadProgress");
                                }

                                public void onDownloadSuccess(PluginRecord pluginRecord) {
                                    super.onDownloadSuccess(pluginRecord);
                                    BluetoothLog.c(">>> onDownloadSuccess");
                                }

                                public void onDownloadFailure(PluginError pluginError) {
                                    super.onDownloadFailure(pluginError);
                                    BluetoothLog.c(">>> onDownloadFailure");
                                }

                                public void onDownloadCancel() {
                                    super.onDownloadCancel();
                                    BluetoothLog.c(">>> onDownloadCancel");
                                }

                                public void onInstallStart(PluginRecord pluginRecord) {
                                    super.onInstallStart(pluginRecord);
                                    BluetoothLog.c(">>> onInstallStart");
                                }

                                public void onInstallSuccess(PluginRecord pluginRecord) {
                                    super.onInstallSuccess(pluginRecord);
                                    BluetoothLog.c(">>> onInstallSuccess");
                                }

                                public void onInstallFailure(PluginError pluginError) {
                                    super.onInstallFailure(pluginError);
                                    BluetoothLog.c(">>> onInstallFailure " + pluginError.b());
                                }

                                public void onLoadSuccess(PluginRecord pluginRecord) {
                                    super.onLoadSuccess(pluginRecord);
                                    BluetoothLog.c(">>> onLoadSuccess");
                                }

                                public void onLoadFailure(PluginRecord pluginRecord) {
                                    super.onLoadFailure(pluginRecord);
                                    BluetoothLog.c(">>> onLoadFailure ");
                                }

                                public void onSendSuccess(Bundle bundle) {
                                    super.onSendSuccess(bundle);
                                    BluetoothLog.c(">>> onSendSuccess ");
                                }

                                public void onMessageHandle(boolean z) {
                                    super.onMessageHandle(z);
                                    BluetoothLog.c(">>> onMessageHandle " + z);
                                }

                                public void onMessageSuccess(Intent intent) {
                                    super.onMessageSuccess(intent);
                                    BluetoothLog.c(">>> onMessageSuccess ");
                                }

                                public void onMessageFailure(int i, String str) {
                                    super.onMessageFailure(i, str);
                                    BluetoothLog.c(">>> onMessageFailure " + i + ", " + str);
                                }

                                public void onSendCancel() {
                                    super.onSendCancel();
                                    BluetoothLog.c(">>> onSendCancel ");
                                }

                                public void onSendFailure(Error error) {
                                    super.onSendFailure(error);
                                    BluetoothLog.c(">>> onSendFailure " + error.b());
                                }
                            });
                        }
                    }
                }
            });
        }
    }
}
