package com.xiaomi.smarthome.frame.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import com.google.android.exoplayer2.C;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.mi.global.shop.util.PushRouteUtil;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.CoreBridge;
import com.xiaomi.smarthome.device.api.BaseWidgetView;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.debug.PluginErrorInfoActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.frame.plugin.runtime.context.PluginContext;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONObject;

public class PluginApi {
    public static final String ACTION_LOADING_FINISH = "action_loading_finish";
    public static final String EXTRA_DOWNLOADTASK = "extra_downloadtask";
    public static final String TYPE_LOADING = "type_loading_sendmessage";
    private static PluginApi sInstance;
    private static final Object sLock = new Object();

    public static class BindServiceCallback {
        public void onFailure(Error error) {
        }

        public void onSuccess() {
        }
    }

    public static class StartServiceCallback {
        public void onFailure(Error error) {
        }

        public void onSuccess() {
        }
    }

    public static class UnBindServiceCallback {
        public void onFailure(Error error) {
        }

        public void onSuccess() {
        }
    }

    /* access modifiers changed from: private */
    public static boolean checkPluginUpdateMessageType(int i) {
        return i == 1;
    }

    private PluginApi() {
    }

    public static PluginApi getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new PluginApi();
                }
            }
        }
        return sInstance;
    }

    public BaseWidgetView createWidgetView(PluginRecord pluginRecord, int i, Intent intent, DeviceStat deviceStat) {
        XmPluginPackage loadApk;
        if (!(pluginRecord == null || !pluginRecord.l() || (loadApk = PluginRuntimeManager.getInstance().loadApk(pluginRecord.h())) == null || loadApk.xmPluginMessageReceiver == null)) {
            try {
                PluginContext pluginContext = new PluginContext(FrameManager.b().c(), loadApk);
                LayoutInflater cloneInContext = LayoutInflater.from(FrameManager.b().c()).cloneInContext(pluginContext);
                PluginRuntimeManager.clearViewBuffer();
                BaseWidgetView createWidgetView = loadApk.xmPluginMessageReceiver.createWidgetView(pluginContext, cloneInContext, loadApk, i, intent, deviceStat);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("plugin_id", loadApk.getPluginId());
                    jSONObject.put(PushRouteUtil.k, loadApk.getPackageId());
                    jSONObject.put("msg_type", i);
                    if (deviceStat != null) {
                        jSONObject.put("permitLevel", deviceStat.permitLevel);
                    }
                    CoreApi.a().a(StatType.EVENT, "plugin_message_receiver_create_widget_view", jSONObject.toString(), (String) null, false);
                } catch (Exception unused) {
                }
                return createWidgetView;
            } catch (Throwable th) {
                PluginErrorInfoActivity.showErrorInfo(FrameManager.b().c(), loadApk, th);
            }
        }
        return null;
    }

    public SendMessageHandle installPlugin(Context context, PluginRecord pluginRecord, final SendMessageCallback sendMessageCallback) {
        Log.e("Device_Renderer", "1 - " + System.currentTimeMillis());
        if (context == null) {
            if (sendMessageCallback != null) {
                sendMessageCallback.onSendFailure(new Error(-1, "context is null"));
            }
            LogUtilGrey.a("click_device_list", "sendMessage 1");
            return new SendMessageHandle();
        } else if (pluginRecord == null) {
            if (sendMessageCallback != null) {
                sendMessageCallback.onSendFailure(new Error(-1, "PluginRecord is null"));
            }
            LogUtilGrey.a("click_device_list", "sendMessage 2");
            return new SendMessageHandle();
        } else if (CoreApi.a().g(pluginRecord.o())) {
            if (sendMessageCallback != null) {
                sendMessageCallback.onSendFailure(new Error(-1, "force updating"));
            }
            LogUtilGrey.a("click_device_list", "sendMessage 3");
            return new SendMessageHandle();
        } else {
            final SendMessageHandle sendMessageHandle = new SendMessageHandle();
            if (!pluginRecord.k() && !pluginRecord.l()) {
                CoreApi.a().a(pluginRecord.o(), (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
                    private long startTime = 0;

                    public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadStart(pluginRecord, pluginDownloadTask);
                        }
                    }

                    public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        this.startTime = System.currentTimeMillis();
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadStart(pluginRecord, pluginDownloadTask);
                        }
                    }

                    public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownInfoSuccess(pluginRecord, pluginDownloadTask);
                        }
                    }

                    public void onProgress(PluginRecord pluginRecord, float f) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadProgress(pluginRecord, f);
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (this.startTime > 0 && pluginRecord != null) {
                            STAT.i.a(System.currentTimeMillis() - this.startTime, pluginRecord.o());
                        }
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadSuccess(pluginRecord);
                        }
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onInstallBefore(pluginRecord);
                        }
                        CoreApi.a().a(pluginRecord.o(), false, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                            public void onStart(PluginRecord pluginRecord) {
                                if (sendMessageCallback != null) {
                                    sendMessageCallback.onInstallStart(pluginRecord);
                                }
                            }

                            public void onSuccess(PluginRecord pluginRecord) {
                                if (sendMessageCallback != null) {
                                    sendMessageCallback.onInstallSuccess(pluginRecord);
                                }
                                if (sendMessageHandle.isCanceled() && sendMessageCallback != null) {
                                    sendMessageCallback.onSendCancel();
                                }
                            }

                            public void onFailure(PluginError pluginError) {
                                if (sendMessageCallback != null) {
                                    sendMessageCallback.onInstallFailure(pluginError);
                                    sendMessageCallback.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                                }
                                LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-installPlugin-onFailure error:" + pluginError);
                            }

                            public void onCancel() {
                                if (sendMessageCallback != null) {
                                    sendMessageCallback.onInstallFailure(new PluginError(-1, ""));
                                    sendMessageCallback.onSendFailure(new Error(-1, "install canceled"));
                                }
                                LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-installPlugin-onCancel");
                            }
                        });
                    }

                    public void onFailure(PluginError pluginError) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadFailure(pluginError);
                            sendMessageCallback.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-onFailure");
                    }

                    public void onCancel() {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onDownloadCancel();
                            sendMessageCallback.onSendFailure(new Error(-1, "download canceled"));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-onCancel");
                    }
                });
            } else if (pluginRecord.k() && !pluginRecord.l()) {
                if (sendMessageCallback != null) {
                    sendMessageCallback.onInstallBefore(pluginRecord);
                }
                CoreApi.a().a(pluginRecord.o(), false, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                    public void onStart(PluginRecord pluginRecord) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onInstallStart(pluginRecord);
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onInstallSuccess(pluginRecord);
                        }
                        if (sendMessageHandle.isCanceled() && sendMessageCallback != null) {
                            sendMessageCallback.onSendCancel();
                        }
                    }

                    public void onFailure(PluginError pluginError) {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onInstallFailure(pluginError);
                            sendMessageCallback.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPlugin-onFailure");
                    }

                    public void onCancel() {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onInstallFailure(new PluginError(-1, " canceled"));
                            sendMessageCallback.onSendFailure(new Error(-1, "install canceled"));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPlugin-onCancel");
                    }
                });
            } else if (pluginRecord.k() || !pluginRecord.l()) {
                if (pluginRecord.b()) {
                    if (sendMessageCallback != null) {
                        sendMessageCallback.onInstallBefore(pluginRecord);
                    }
                    CoreApi.a().a(pluginRecord.o(), true, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                        public void onStart(PluginRecord pluginRecord) {
                            if (sendMessageCallback != null) {
                                sendMessageCallback.onInstallStart(pluginRecord);
                            }
                        }

                        public void onSuccess(PluginRecord pluginRecord) {
                            if (sendMessageCallback != null) {
                                sendMessageCallback.onInstallSuccess(pluginRecord);
                            }
                            if (sendMessageHandle.isCanceled() && sendMessageCallback != null) {
                                sendMessageCallback.onSendCancel();
                            }
                        }

                        public void onFailure(PluginError pluginError) {
                            if (sendMessageCallback != null) {
                                sendMessageCallback.onInstallFailure(pluginError);
                                sendMessageCallback.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                            }
                            LogUtilGrey.a("click_device_list", "sendMessage-installPluginNew-onFailure");
                        }

                        public void onCancel() {
                            if (sendMessageCallback != null) {
                                sendMessageCallback.onInstallFailure(new PluginError(-1, PrinterParmater.q));
                                sendMessageCallback.onSendFailure(new Error(-1, "install canceled"));
                            }
                            LogUtilGrey.a("click_device_list", "sendMessage-installPluginNew-onCancel");
                        }
                    });
                } else if (sendMessageCallback != null) {
                    sendMessageCallback.onInstallSuccess(pluginRecord);
                }
            } else if (sendMessageCallback != null) {
                sendMessageCallback.onInstallSuccess(pluginRecord);
            }
            return sendMessageHandle;
        }
    }

    public SendMessageHandle sendMessage(Context context, PluginRecord pluginRecord, int i, Intent intent, DeviceStat deviceStat, RunningProcess runningProcess, boolean z, SendMessageCallback sendMessageCallback) {
        SendMessageHandle sendMessageHandle = new SendMessageHandle();
        sendMessage(context, pluginRecord, i, intent, deviceStat, runningProcess, z, sendMessageCallback, sendMessageHandle);
        return sendMessageHandle;
    }

    public SendMessageHandle sendMessage(Context context, PluginRecord pluginRecord, int i, Intent intent, DeviceStat deviceStat, RunningProcess runningProcess, boolean z, SendMessageCallback sendMessageCallback, SendMessageHandle sendMessageHandle) {
        Context context2 = context;
        PluginRecord pluginRecord2 = pluginRecord;
        int i2 = i;
        SendMessageCallback sendMessageCallback2 = sendMessageCallback;
        Log.i("Device_Renderer", "1 - " + System.currentTimeMillis());
        final Intent intent2 = intent == null ? new Intent() : intent;
        if (context2 == null) {
            if (sendMessageCallback2 != null) {
                sendMessageCallback2.onSendFailure(new Error(-1, "context is null"));
            }
            LogUtilGrey.a("click_device_list", "sendMessage 1");
            return new SendMessageHandle();
        } else if (pluginRecord2 == null) {
            if (sendMessageCallback2 != null) {
                sendMessageCallback2.onSendFailure(new Error(-1, "PluginRecord is null"));
            }
            STAT.i.c(1, "null");
            LogUtilGrey.a("click_device_list", "sendMessage 2");
            return new SendMessageHandle();
        } else if (CoreApi.a().g(pluginRecord.o())) {
            if (sendMessageCallback2 != null) {
                sendMessageCallback2.onSendFailure(new Error(-1, "force updating"));
            }
            LogUtilGrey.a("click_device_list", "sendMessage 3");
            return new SendMessageHandle();
        } else if ("mijia.camera.v3".equals(pluginRecord.o()) || "chuangmi.camera.ipc009".equals(pluginRecord.o()) || "chuangmi.camera.ipc019".equals(pluginRecord.o()) || DeviceConstant.CHUANGMI_CAMERA_021.equals(pluginRecord.o()) || DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(pluginRecord.o())) {
            Intent intent3 = new Intent();
            if (i2 == 1) {
                intent3.setAction("com.xiaomi.smarthome.camera.LANUCH");
            } else if (i2 == 2) {
                intent3.setAction("com.xiaomi.smarthome.camera.PUSH_MSG");
            } else if (i2 == 18) {
                intent3.setAction("com.xiaomi.smarthome.camera.INIT_CAMERA_FRAME_SENDER");
            } else if (i2 == 19) {
                intent3.setAction("com.xiaomi.smarthome.camera.STAR_REQUEST_CAMERA_FRAME");
            } else if (i2 == 20) {
                intent3.setAction("com.xiaomi.smarthome.camera.STOP_REQUEST_CAMERA_FRAME");
            } else if (i2 == 21) {
                intent3.setAction("com.xiaomi.smarthome.camera.DESTROY_REQUEST_CAMERA_FRAME");
            }
            intent3.setPackage(context.getPackageName());
            intent3.putExtra("extra_device_did", deviceStat.did);
            intent3.putExtra("extra_device_model", pluginRecord.o());
            intent3.putExtras(intent2);
            context2.sendBroadcast(intent3);
            if (sendMessageCallback2 != null) {
                sendMessageCallback2.onSendSuccess((Bundle) null);
            }
            return new SendMessageHandle();
        } else {
            Context applicationContext = context.getApplicationContext();
            if (!pluginRecord.k() && !pluginRecord.l()) {
                final SendMessageCallback sendMessageCallback3 = sendMessageCallback;
                final Intent intent4 = intent2;
                final Context context3 = context;
                final DeviceStat deviceStat2 = deviceStat;
                final Context context4 = applicationContext;
                final SendMessageHandle sendMessageHandle2 = sendMessageHandle;
                final int i3 = i;
                final RunningProcess runningProcess2 = runningProcess;
                final boolean z2 = z;
                CoreApi.a().a(pluginRecord.o(), (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
                    /* access modifiers changed from: private */
                    public BroadcastReceiver mCancelReceiver;
                    /* access modifiers changed from: private */
                    public boolean rnPlugin = false;
                    long startTime = 0;

                    public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        if (sendMessageCallback3 != null) {
                            sendMessageCallback3.onDownloadStart(pluginRecord, pluginDownloadTask);
                        }
                    }

                    public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        if (sendMessageCallback3 != null) {
                            sendMessageCallback3.onDownloadStart(pluginRecord, pluginDownloadTask);
                        }
                        this.startTime = System.currentTimeMillis();
                    }

                    public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        if ("rn".equals(pluginDownloadTask.c)) {
                            this.rnPlugin = true;
                            intent4.putExtra(PluginApi.TYPE_LOADING, 1);
                            Intent intent = new Intent(context3, CoreBridge.a().d());
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            intent.putExtra(PluginBridgeService.EXTRA_DEVICESTAT, deviceStat2);
                            intent.putExtra(PluginApi.EXTRA_DOWNLOADTASK, pluginDownloadTask);
                            context3.startActivity(intent);
                            this.mCancelReceiver = new BroadcastReceiver() {
                                public void onReceive(Context context, Intent intent) {
                                    context4.unregisterReceiver(this);
                                    BroadcastReceiver unused = AnonymousClass4.this.mCancelReceiver = null;
                                    if (deviceStat2.equals(intent.getParcelableExtra(PluginBridgeService.EXTRA_DEVICESTAT))) {
                                        sendMessageHandle2.cancel();
                                    }
                                }
                            };
                            context4.registerReceiver(this.mCancelReceiver, new IntentFilter(PluginApi.ACTION_LOADING_FINISH));
                            if (sendMessageCallback3 != null) {
                                sendMessageCallback3.onDownloadSuccess(pluginRecord);
                                sendMessageCallback3.onInstallSuccess(pluginRecord);
                                sendMessageCallback3.onSendSuccess(new Bundle());
                            }
                        } else {
                            this.rnPlugin = false;
                            if (sendMessageCallback3 != null) {
                                sendMessageCallback3.onDownInfoSuccess(pluginRecord, pluginDownloadTask);
                            }
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-onDownInfoSuccess");
                    }

                    public void onProgress(PluginRecord pluginRecord, float f) {
                        if (sendMessageCallback3 != null && !this.rnPlugin) {
                            sendMessageCallback3.onDownloadProgress(pluginRecord, f);
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (sendMessageCallback3 != null && !this.rnPlugin) {
                            sendMessageCallback3.onDownloadSuccess(pluginRecord);
                        }
                        if (this.startTime > 0) {
                            STAT.i.a(System.currentTimeMillis() - this.startTime, pluginRecord.o());
                        }
                        if (sendMessageCallback3 != null && !this.rnPlugin) {
                            sendMessageCallback3.onInstallBefore(pluginRecord);
                        }
                        CoreApi.a().a(pluginRecord.o(), false, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                            public void onStart(PluginRecord pluginRecord) {
                                if (sendMessageCallback3 != null && !AnonymousClass4.this.rnPlugin) {
                                    sendMessageCallback3.onInstallStart(pluginRecord);
                                }
                            }

                            public void onSuccess(PluginRecord pluginRecord) {
                                if (sendMessageCallback3 != null && !AnonymousClass4.this.rnPlugin) {
                                    sendMessageCallback3.onInstallSuccess(pluginRecord);
                                }
                                if (!sendMessageHandle2.isCanceled()) {
                                    if (AnonymousClass4.this.rnPlugin && AnonymousClass4.this.mCancelReceiver != null) {
                                        context4.unregisterReceiver(AnonymousClass4.this.mCancelReceiver);
                                        BroadcastReceiver unused = AnonymousClass4.this.mCancelReceiver = null;
                                    }
                                    PluginRuntimeManager.getInstance().sendMessage(context3, pluginRecord.o(), i3, intent4, deviceStat2, runningProcess2, z2, sendMessageCallback3);
                                } else if (sendMessageCallback3 != null && !AnonymousClass4.this.rnPlugin) {
                                    sendMessageCallback3.onSendCancel();
                                }
                            }

                            public void onFailure(PluginError pluginError) {
                                if (AnonymousClass4.this.rnPlugin) {
                                    if (AnonymousClass4.this.mCancelReceiver != null) {
                                        context4.unregisterReceiver(AnonymousClass4.this.mCancelReceiver);
                                        BroadcastReceiver unused = AnonymousClass4.this.mCancelReceiver = null;
                                    }
                                } else if (sendMessageCallback3 != null) {
                                    sendMessageCallback3.onInstallFailure(pluginError);
                                    sendMessageCallback3.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                                }
                                LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-installPlugin-onFailure error:" + pluginError);
                            }

                            public void onCancel() {
                                if (AnonymousClass4.this.rnPlugin) {
                                    if (AnonymousClass4.this.mCancelReceiver != null) {
                                        context4.unregisterReceiver(AnonymousClass4.this.mCancelReceiver);
                                        BroadcastReceiver unused = AnonymousClass4.this.mCancelReceiver = null;
                                    }
                                } else if (sendMessageCallback3 != null) {
                                    sendMessageCallback3.onInstallFailure(new PluginError(-1, ""));
                                    sendMessageCallback3.onSendFailure(new Error(-1, "install canceled"));
                                }
                                LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-installPlugin-onCancel");
                            }
                        });
                    }

                    public void onFailure(PluginError pluginError) {
                        if (this.rnPlugin) {
                            if (this.mCancelReceiver != null) {
                                context4.unregisterReceiver(this.mCancelReceiver);
                                this.mCancelReceiver = null;
                            }
                        } else if (sendMessageCallback3 != null) {
                            sendMessageCallback3.onDownloadFailure(pluginError);
                            sendMessageCallback3.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-onFailure");
                    }

                    public void onCancel() {
                        if (this.rnPlugin) {
                            if (this.mCancelReceiver != null) {
                                context4.unregisterReceiver(this.mCancelReceiver);
                                this.mCancelReceiver = null;
                            }
                        } else if (sendMessageCallback3 != null) {
                            sendMessageCallback3.onDownloadCancel();
                            sendMessageCallback3.onSendFailure(new Error(-1, "download canceled"));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-downloadPlugin-onCancel");
                    }
                });
            } else if (pluginRecord.k() && !pluginRecord.l()) {
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onInstallBefore(pluginRecord2);
                }
                final SendMessageCallback sendMessageCallback4 = sendMessageCallback;
                final SendMessageHandle sendMessageHandle3 = sendMessageHandle;
                final Context context5 = context;
                final int i4 = i;
                final DeviceStat deviceStat3 = deviceStat;
                final RunningProcess runningProcess3 = runningProcess;
                final boolean z3 = z;
                CoreApi.a().a(pluginRecord.o(), false, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                    public void onStart(PluginRecord pluginRecord) {
                        if (sendMessageCallback4 != null) {
                            sendMessageCallback4.onInstallStart(pluginRecord);
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (sendMessageCallback4 != null) {
                            sendMessageCallback4.onInstallSuccess(pluginRecord);
                        }
                        if (!sendMessageHandle3.isCanceled()) {
                            PluginRuntimeManager.getInstance().sendMessage(context5, pluginRecord.o(), i4, intent2, deviceStat3, runningProcess3, z3, sendMessageCallback4);
                        } else if (sendMessageCallback4 != null) {
                            sendMessageCallback4.onSendCancel();
                        }
                        if (PluginApi.checkPluginUpdateMessageType(i4)) {
                            CoreApi.a().a(pluginRecord.o(), false, (CoreApi.UpdatePluginCallback) null);
                        }
                    }

                    public void onFailure(PluginError pluginError) {
                        if (sendMessageCallback4 != null) {
                            sendMessageCallback4.onInstallFailure(pluginError);
                            sendMessageCallback4.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPlugin-onFailure");
                    }

                    public void onCancel() {
                        if (sendMessageCallback4 != null) {
                            sendMessageCallback4.onInstallFailure(new PluginError(-1, " canceled"));
                            sendMessageCallback4.onSendFailure(new Error(-1, "install canceled"));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPlugin-onCancel");
                    }
                });
            } else if (!pluginRecord.k() && pluginRecord.l()) {
                if (!sendMessageHandle.isCanceled()) {
                    PluginRuntimeManager.getInstance().sendMessage(context, pluginRecord.o(), i, intent2, deviceStat, runningProcess, z, sendMessageCallback);
                } else if (sendMessageCallback2 != null) {
                    sendMessageCallback.onSendCancel();
                }
                if (checkPluginUpdateMessageType(i)) {
                    CoreApi.a().a(pluginRecord.o(), false, (CoreApi.UpdatePluginCallback) null);
                }
            } else if (pluginRecord.b()) {
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onInstallBefore(pluginRecord2);
                }
                final SendMessageCallback sendMessageCallback5 = sendMessageCallback;
                final SendMessageHandle sendMessageHandle4 = sendMessageHandle;
                final Context context6 = context;
                final int i5 = i;
                final DeviceStat deviceStat4 = deviceStat;
                final RunningProcess runningProcess4 = runningProcess;
                final boolean z4 = z;
                CoreApi.a().a(pluginRecord.o(), true, (CoreApi.InstallPluginCallback) new CoreApi.InstallPluginCallback() {
                    public void onStart(PluginRecord pluginRecord) {
                        if (sendMessageCallback5 != null) {
                            sendMessageCallback5.onInstallStart(pluginRecord);
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (sendMessageCallback5 != null) {
                            sendMessageCallback5.onInstallSuccess(pluginRecord);
                        }
                        if (!sendMessageHandle4.isCanceled()) {
                            PluginRuntimeManager.getInstance().sendMessage(context6, pluginRecord.o(), i5, intent2, deviceStat4, runningProcess4, z4, sendMessageCallback5);
                        } else if (sendMessageCallback5 != null) {
                            sendMessageCallback5.onSendCancel();
                        }
                        if (PluginApi.checkPluginUpdateMessageType(i5)) {
                            CoreApi.a().a(pluginRecord.o(), false, (CoreApi.UpdatePluginCallback) null);
                        }
                    }

                    public void onFailure(PluginError pluginError) {
                        if (sendMessageCallback5 != null) {
                            sendMessageCallback5.onInstallFailure(pluginError);
                            sendMessageCallback5.onSendFailure(new Error(pluginError.a(), pluginError.b()));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPluginNew-onFailure");
                    }

                    public void onCancel() {
                        if (sendMessageCallback5 != null) {
                            sendMessageCallback5.onInstallFailure(new PluginError(-1, PrinterParmater.q));
                            sendMessageCallback5.onSendFailure(new Error(-1, "install canceled"));
                        }
                        LogUtilGrey.a("click_device_list", "sendMessage-installPluginNew-onCancel");
                    }
                });
            } else {
                if (!sendMessageHandle.isCanceled()) {
                    PluginRuntimeManager.getInstance().sendMessage(context, pluginRecord.o(), i, intent2, deviceStat, runningProcess, z, sendMessageCallback);
                } else if (sendMessageCallback2 != null) {
                    sendMessageCallback.onSendCancel();
                }
                if (!RnPluginDebugDeviceMock.f22091a.equals(pluginRecord.o()) && checkPluginUpdateMessageType(i)) {
                    CoreApi.a().a(pluginRecord.o(), false, (CoreApi.UpdatePluginCallback) null);
                }
            }
            return sendMessageHandle;
        }
    }

    public void startService(RunningProcess runningProcess, String str, long j, long j2, Intent intent, String str2, StartServiceCallback startServiceCallback) {
        PluginRuntimeManager.getInstance().startService(runningProcess, str, j, j2, intent, str2, startServiceCallback);
    }

    public void exitProcess(RunningProcess runningProcess) {
        PluginRuntimeManager.getInstance().exitProcess(runningProcess);
    }

    public void bindService(RunningProcess runningProcess, String str, long j, long j2, String str2, ServiceConnection serviceConnection, int i, BindServiceCallback bindServiceCallback) {
        PluginRuntimeManager.getInstance().bindService(runningProcess, str, j, j2, str2, serviceConnection, i, bindServiceCallback);
    }

    public void unbindService(RunningProcess runningProcess, String str, long j, long j2, String str2, ServiceConnection serviceConnection, UnBindServiceCallback unBindServiceCallback) {
        PluginRuntimeManager.getInstance().unbindService(runningProcess, str, j, j2, str2, serviceConnection, unBindServiceCallback);
    }

    public static class SendMessageCallback {
        public Object mObj;

        public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
        }

        public void onDownloadCancel() {
        }

        public void onDownloadFailure(PluginError pluginError) {
        }

        public void onDownloadProgress(PluginRecord pluginRecord, float f) {
        }

        public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
        }

        public void onDownloadSuccess(PluginRecord pluginRecord) {
        }

        public void onInstallBefore(PluginRecord pluginRecord) {
        }

        public void onInstallFailure(PluginError pluginError) {
        }

        public void onInstallStart(PluginRecord pluginRecord) {
        }

        public void onInstallSuccess(PluginRecord pluginRecord) {
        }

        public void onLoadFailure(PluginRecord pluginRecord) {
        }

        public void onLoadSuccess(PluginRecord pluginRecord) {
        }

        public void onMessageFailure(int i, String str) {
        }

        public void onMessageHandle(boolean z) {
        }

        public void onMessageSuccess(Intent intent) {
        }

        public void onSendCancel() {
        }

        public void onSendFailure(Error error) {
        }

        public void onSendSuccess(Bundle bundle) {
        }

        public SendMessageCallback() {
        }

        public SendMessageCallback(Object obj) {
            this.mObj = obj;
        }
    }

    public static class SendMessageHandle {
        boolean mIsCanceled = false;

        public synchronized boolean isCanceled() {
            return this.mIsCanceled;
        }

        public synchronized void cancel() {
            this.mIsCanceled = true;
        }
    }
}
