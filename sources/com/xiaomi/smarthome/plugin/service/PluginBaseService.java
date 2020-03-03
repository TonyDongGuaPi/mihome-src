package com.xiaomi.smarthome.plugin.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PluginBaseService extends Service implements IPluginService {
    Service mPluginHostService;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Service getHostService() {
        return this.mPluginHostService;
    }

    public void attach(Context context, Service service) {
        attachBaseContext(context);
        this.mPluginHostService = service;
    }

    public void stopSelfForPlugin() {
        if (this.mPluginHostService != null) {
            this.mPluginHostService.stopSelf();
        }
    }

    public void stopSelfForPlugin(int i) {
        if (this.mPluginHostService != null) {
            this.mPluginHostService.stopSelf(i);
        }
    }

    public boolean stopSelfResultForPlugin(int i) {
        if (this.mPluginHostService != null) {
            return this.mPluginHostService.stopSelfResult(i);
        }
        return false;
    }

    public void startForegroundForPlugin(int i, Notification notification) {
        if (this.mPluginHostService != null) {
            this.mPluginHostService.startForeground(i, notification);
        }
    }

    public void stopForegroundForPlugin(boolean z) {
        if (this.mPluginHostService != null) {
            this.mPluginHostService.stopForeground(z);
        }
    }
}
