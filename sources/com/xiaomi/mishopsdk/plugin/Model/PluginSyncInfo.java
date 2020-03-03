package com.xiaomi.mishopsdk.plugin.Model;

import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import java.io.Serializable;

public class PluginSyncInfo implements Serializable {
    private static final long serialVersionUID = -2969227098618920947L;
    public String pluginId;
    public PluginInfo pluginInfo;
    public PluginSyncManager.SyncStatus status;

    public PluginSyncInfo(PluginSyncManager.SyncStatus syncStatus, PluginInfo pluginInfo2, String str) {
        this.status = syncStatus;
        this.pluginInfo = pluginInfo2;
        this.pluginId = str;
    }
}
