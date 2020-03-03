package com.xiaomi.mishopsdk.plugin.lib;

import android.text.TextUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.NetworkUtil;
import java.io.File;
import java.io.Serializable;

public class PluginInfo implements Serializable {
    public static final long DURATION_SYNC = 120000;
    public static final long EXPIRATION_INTERVAL = 130000;
    public static int PLUGIN_PRIORITY_IMPORTANT = 2;
    public static int PLUGIN_PRIORITY_NORMAL = 10;
    public static int PLUGIN_PRIORITY_REALTIME = 1;
    public static final String PLUGIN_TYPE_APK = "apk";
    public static final String PLUGIN_TYPE_RN = "rn";
    private static String TAG = "PluginInfo";
    private static final long serialVersionUID = -8620068535543164827L;
    @JSONField(name = "has_so")
    public boolean hasSo;
    private boolean hasVisit;
    @JSONField(name = "id")
    public String id;
    public String localPath;
    @JSONField(name = "file_md5")
    public String md5;
    @JSONField(name = "online_time")
    public String onlineTime;
    @JSONField(name = "parent_version")
    public String parentVersion;
    @JSONField(name = "path")
    public String path;
    @JSONField(name = "priority")
    public int priority;
    private String restoredPath;
    @JSONField(name = "root_fragment")
    public String rootFragment;
    private volatile long syncTimeMillis;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "version")
    public String version;

    public boolean checkEqual(PluginInfo pluginInfo) {
        if (pluginInfo == null) {
            return false;
        }
        return this.id.equals(pluginInfo.id);
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(this.id) && !TextUtils.isEmpty(this.localPath) && new File(this.localPath).exists();
    }

    public boolean checkVersion(PluginInfo pluginInfo) {
        if (checkEqual(pluginInfo) && this.id.equals(pluginInfo.id) && this.version.equals(pluginInfo.version)) {
            return true;
        }
        return false;
    }

    public boolean checkMd5(PluginInfo pluginInfo) {
        if (!checkEqual(pluginInfo)) {
            return false;
        }
        if (TextUtils.isEmpty(this.md5) && TextUtils.isEmpty(pluginInfo.md5)) {
            return true;
        }
        if ((TextUtils.isEmpty(this.md5) && TextUtils.isEmpty(pluginInfo.md5)) && this.id.equals(pluginInfo.id) && this.md5.equals(pluginInfo.md5)) {
            return true;
        }
        return false;
    }

    public boolean checkNeedSyncTime() {
        if (!isValid()) {
            return false;
        }
        if (this.priority < PLUGIN_PRIORITY_IMPORTANT && System.currentTimeMillis() - this.syncTimeMillis >= EXPIRATION_INTERVAL) {
            return false;
        }
        return true;
    }

    public static boolean checkVersion(PluginInfo pluginInfo, PluginInfo pluginInfo2) {
        return pluginInfo != null && pluginInfo2 != null && pluginInfo.checkEqual(pluginInfo2) && pluginInfo.checkVersion(pluginInfo2);
    }

    public static boolean downloadCanDelayed(PluginInfo pluginInfo, boolean z) {
        if (pluginInfo == null) {
            Log.e(TAG, "downloadCanDelayed get a null PluginInfo.");
            return false;
        } else if (z || pluginInfo.priority < PLUGIN_PRIORITY_NORMAL || NetworkUtil.isWifiConnected(ShopApp.instance)) {
            return false;
        } else {
            return true;
        }
    }

    public PluginInfo() {
        this.type = "apk";
        this.syncTimeMillis = 0;
        this.hasVisit = false;
    }

    public PluginInfo(PluginInfo pluginInfo) {
        this.type = "apk";
        this.syncTimeMillis = 0;
        deepCopy(pluginInfo);
    }

    public void visitPlugin() {
        this.hasVisit = true;
    }

    public void setLatestSyncTime(long j) {
        this.syncTimeMillis = j;
    }

    public synchronized void deepCopy(PluginInfo pluginInfo) {
        this.id = pluginInfo.id;
        this.version = pluginInfo.version;
        this.path = pluginInfo.path;
        this.rootFragment = pluginInfo.rootFragment;
        this.priority = pluginInfo.priority;
        this.localPath = pluginInfo.localPath;
        this.parentVersion = pluginInfo.parentVersion;
        this.md5 = pluginInfo.md5;
        this.onlineTime = pluginInfo.onlineTime;
        this.type = pluginInfo.type;
        this.hasSo = pluginInfo.hasSo;
        this.hasVisit = pluginInfo.hasVisit;
        this.syncTimeMillis = pluginInfo.syncTimeMillis;
        this.restoredPath = null;
    }

    public String getPluginType() {
        if (TextUtils.isEmpty(this.type)) {
            return "apk";
        }
        return this.type;
    }

    public synchronized String getRestoredPath() {
        if (TextUtils.isEmpty(this.restoredPath)) {
            File dir = ShopApp.instance.getDir(PluginSyncManager.PATH_PLUGINSIGNED, 0);
            this.restoredPath = dir.getAbsolutePath() + FileUtil.getFileName(this.localPath);
        }
        return this.restoredPath;
    }
}
