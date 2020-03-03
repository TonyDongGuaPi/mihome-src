package com.xiaomi.smarthome.miio.miband.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.miio.miband.data.PluginDeviceDownloadItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PluginDeviceNavigateHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final Byte[] f19482a = new Byte[0];
    /* access modifiers changed from: private */
    public static HashMap<String, PluginDeviceDownloadItem> c = null;
    private static PluginDeviceNavigateHelper e = null;
    private Handler b;
    private List<DownloadStateListener> d;

    public interface DownloadStateListener {
        void a(PluginDeviceDownloadItem pluginDeviceDownloadItem);
    }

    public void a(DownloadStateListener downloadStateListener) {
        for (DownloadStateListener equals : this.d) {
            if (equals.equals(downloadStateListener)) {
                return;
            }
        }
        this.d.add(downloadStateListener);
    }

    public void b(DownloadStateListener downloadStateListener) {
        this.d.remove(downloadStateListener);
    }

    public static PluginDeviceNavigateHelper a() {
        if (e == null) {
            synchronized (f19482a) {
                if (e == null) {
                    e = new PluginDeviceNavigateHelper();
                    c = new HashMap<>(5);
                }
            }
        }
        return e;
    }

    public void a(PluginDeviceDownloadItem pluginDeviceDownloadItem) {
        for (DownloadStateListener a2 : this.d) {
            a2.a(pluginDeviceDownloadItem);
        }
    }

    public void a(Context context, String str, Intent intent) {
        PluginRecord d2;
        if (context != null && str != null) {
            Device b2 = SmartHomeDeviceManager.a().b(str);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(str);
            }
            if (b2 != null && CoreApi.a().c(b2.model) && (d2 = CoreApi.a().d(b2.model)) != null) {
                Intent intent2 = new Intent();
                if (intent != null) {
                    intent2.putExtras(intent);
                }
                PluginApi.getInstance().sendMessage(context, d2, 1, intent2, b2.newDeviceStat(), (RunningProcess) null, false, new PluginDownloadCallBack(b2.model));
            }
        }
    }

    public void b(Context context, String str, Intent intent) {
        PluginRecord d2;
        if (intent != null && str != null && !str.isEmpty() && CoreApi.a().c(str) && (d2 = CoreApi.a().d(str)) != null) {
            PluginApi.getInstance().sendMessage(context, d2, 4, intent, (DeviceStat) null, (RunningProcess) null, true, new PluginDownloadCallBack(str));
        }
    }

    class PluginDownloadCallBack extends PluginApi.SendMessageCallback {
        private String b;

        public PluginDownloadCallBack(String str) {
            this.b = str;
        }

        public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            PluginDeviceDownloadItem pluginDeviceDownloadItem = (PluginDeviceDownloadItem) PluginDeviceNavigateHelper.c.get(this.b);
            if (pluginDeviceDownloadItem == null) {
                pluginDeviceDownloadItem = new PluginDeviceDownloadItem();
                pluginDeviceDownloadItem.b = this.b;
                pluginDeviceDownloadItem.c = PluginDeviceDownloadItem.Status.DOWNLOADING;
                PluginDeviceNavigateHelper.c.put(this.b, pluginDeviceDownloadItem);
            }
            PluginDeviceNavigateHelper.this.a(pluginDeviceDownloadItem);
        }

        public void onDownloadProgress(PluginRecord pluginRecord, float f) {
            PluginDeviceDownloadItem pluginDeviceDownloadItem = (PluginDeviceDownloadItem) PluginDeviceNavigateHelper.c.get(this.b);
            if (pluginDeviceDownloadItem != null) {
                pluginDeviceDownloadItem.f19463a = f;
            }
            PluginDeviceNavigateHelper.this.a(pluginDeviceDownloadItem);
        }

        public void onDownloadSuccess(PluginRecord pluginRecord) {
            PluginDeviceDownloadItem pluginDeviceDownloadItem = (PluginDeviceDownloadItem) PluginDeviceNavigateHelper.c.get(this.b);
            if (pluginDeviceDownloadItem != null) {
                pluginDeviceDownloadItem.c = PluginDeviceDownloadItem.Status.DOWNLOADING_SUCCESS;
            }
            PluginDeviceNavigateHelper.this.a(pluginDeviceDownloadItem);
            PluginDeviceNavigateHelper.c.remove(this.b);
        }

        public void onDownloadFailure(PluginError pluginError) {
            PluginDeviceDownloadItem pluginDeviceDownloadItem = (PluginDeviceDownloadItem) PluginDeviceNavigateHelper.c.get(this.b);
            if (pluginDeviceDownloadItem != null) {
                pluginDeviceDownloadItem.c = PluginDeviceDownloadItem.Status.DOWNLOADING_FAILURE;
            }
            PluginDeviceNavigateHelper.this.a(pluginDeviceDownloadItem);
            PluginDeviceNavigateHelper.c.remove(this.b);
        }

        public void onDownloadCancel() {
            PluginDeviceDownloadItem pluginDeviceDownloadItem = (PluginDeviceDownloadItem) PluginDeviceNavigateHelper.c.get(this.b);
            if (pluginDeviceDownloadItem != null) {
                pluginDeviceDownloadItem.c = PluginDeviceDownloadItem.Status.DOWNLOADING_FAILURE;
            }
            PluginDeviceNavigateHelper.this.a(pluginDeviceDownloadItem);
            PluginDeviceNavigateHelper.c.remove(this.b);
        }
    }

    private PluginDeviceNavigateHelper() {
        this.d = null;
        this.d = new ArrayList();
        this.b = new Handler(Looper.getMainLooper());
    }

    public PluginDeviceDownloadItem a(String str) {
        return c.get(str);
    }
}
