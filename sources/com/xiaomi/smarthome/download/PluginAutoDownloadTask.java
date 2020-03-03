package com.xiaomi.smarthome.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.framework.network.NetworkManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PluginAutoDownloadTask {
    private static PluginAutoDownloadTask b = null;
    /* access modifiers changed from: private */
    public static final String c = "com.xiaomi.smarthome.download.PluginAutoDownloadTask";
    private static Object d = new Object();
    private static final int e = 1;

    /* renamed from: a  reason: collision with root package name */
    ConcurrentLinkedQueue<String> f15570a = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public TaskHandler f = new TaskHandler();
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public boolean h = false;

    public static PluginAutoDownloadTask a() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new PluginAutoDownloadTask();
                }
            }
        }
        return b;
    }

    private PluginAutoDownloadTask() {
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                CoreApi.a().c((AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        boolean unused = PluginAutoDownloadTask.this.h = bool.booleanValue();
                        if (PluginAutoDownloadTask.this.h && !PluginAutoDownloadTask.this.g) {
                            PluginAutoDownloadTask.this.c();
                        }
                    }
                });
            }
        });
    }

    static final class TaskHandler extends Handler {
        TaskHandler() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                PluginAutoDownloadTask pluginAutoDownloadTask = (PluginAutoDownloadTask) message.obj;
                if (pluginAutoDownloadTask.f15570a.isEmpty() || SHApplication.getForegroundActivityCount() == 0 || !NetworkManager.a().e() || !pluginAutoDownloadTask.h) {
                    boolean unused = pluginAutoDownloadTask.g = false;
                    return;
                }
                pluginAutoDownloadTask.a(pluginAutoDownloadTask.f15570a.poll());
                boolean unused2 = pluginAutoDownloadTask.g = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        PluginRecord d2 = CoreApi.a().d(str);
        if (d2 == null) {
            this.f.sendMessageDelayed(Message.obtain(this.f, 1, this), 1000);
        }
        PluginApi.getInstance().installPlugin(SHApplication.getAppContext(), d2, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                String d = PluginAutoDownloadTask.c;
                Log.e(d, "start download - " + pluginRecord.o());
            }

            public void onDownloadFailure(PluginError pluginError) {
                Log.e(PluginAutoDownloadTask.c, "download failed");
                PluginAutoDownloadTask.this.f.sendMessageDelayed(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this), 1000);
            }

            public void onDownloadCancel() {
                Log.e(PluginAutoDownloadTask.c, "download canceled");
                PluginAutoDownloadTask.this.f.sendMessageDelayed(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this), 1000);
            }

            public void onInstallSuccess(PluginRecord pluginRecord) {
                String d = PluginAutoDownloadTask.c;
                Log.e(d, "install success - " + pluginRecord.o());
                PluginAutoDownloadTask.this.f.sendMessageDelayed(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this), 1000);
            }

            public void onInstallFailure(PluginError pluginError) {
                Log.e(PluginAutoDownloadTask.c, "install failed");
                PluginAutoDownloadTask.this.f.sendMessageDelayed(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this), 1000);
            }
        });
    }

    public void a(boolean z) {
        this.h = z;
        if (this.h && !this.g) {
            c();
        }
    }

    public void b() {
        if (!this.g && !this.f15570a.isEmpty()) {
            this.f.sendMessageDelayed(Message.obtain(this.f, 1, this), 1000);
        }
    }

    public void c() {
        IntentFilter intentFilter = new IntentFilter(HomeManager.S);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                PluginRecord d;
                Log.e(PluginAutoDownloadTask.c, "receive broadcast");
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                if (!PluginAutoDownloadTask.this.f15570a.isEmpty()) {
                    Log.e(PluginAutoDownloadTask.c, "waiting queue not null");
                    return;
                }
                for (GridViewData next : HomeManager.a().F()) {
                    String str = null;
                    if (next.f18311a == GridViewData.GridType.TYPE_IR) {
                        str = IRDeviceUtil.a();
                    } else if (next.b != null) {
                        str = next.b.model;
                    }
                    if (!TextUtils.isEmpty(str) && (d = CoreApi.a().d(str)) != null && !d.k()) {
                        PluginAutoDownloadTask.this.f15570a.offer(d.o());
                    }
                }
                if (PluginAutoDownloadTask.this.f15570a != null) {
                    PluginAutoDownloadTask.this.f.sendMessage(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this));
                }
            }
        }, intentFilter);
        Log.e(c, "register broadcast");
        NetworkManager.a().a((NetworkManager.NetworkListener) new NetworkManager.NetworkListener() {
            public void b() {
            }

            public void a() {
                if (NetworkManager.a().e() && PluginAutoDownloadTask.this.h && !PluginAutoDownloadTask.this.g) {
                    PluginAutoDownloadTask.this.f.sendMessage(Message.obtain(PluginAutoDownloadTask.this.f, 1, PluginAutoDownloadTask.this));
                }
            }
        });
    }
}
