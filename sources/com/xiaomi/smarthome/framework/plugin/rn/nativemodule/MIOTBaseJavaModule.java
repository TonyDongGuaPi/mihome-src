package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.xiaomi.miot.support.monitor.core.tasks.TaskConfig;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import java.io.File;

public abstract class MIOTBaseJavaModule extends ReactContextBaseJavaModule {
    public MIOTBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public DeviceStat getDevice() {
        return RNRuntime.a().f();
    }

    public PluginRecord getPluginRecord() {
        return RNRuntime.a().e();
    }

    public String getStatDevloperLabel() {
        PluginRecord pluginRecord = getPluginRecord();
        if (pluginRecord == null) {
            return null;
        }
        return PluginStatReporter.a(pluginRecord.d(), pluginRecord.e());
    }

    public SharedPreferences getSharedPreferences() {
        File pluginDir = getPluginDir(getReactApplicationContext(), getPluginRecord());
        pluginDir.mkdirs();
        return SharedPreferencesImpl.a(new File(pluginDir, "config.xml"));
    }

    public SharedPreferences getSharedPreferencesV2() {
        File pluginSpFileDir = getPluginSpFileDir(getReactApplicationContext(), getPluginRecord(), getDevice());
        if (!pluginSpFileDir.exists()) {
            pluginSpFileDir.mkdirs();
        }
        return SharedPreferencesImpl.a(new File(pluginSpFileDir, "config.xml"));
    }

    public File getFilesPath() {
        File file = new File(getPluginDir(getReactApplicationContext(), getPluginRecord()).getAbsolutePath(), "files");
        file.mkdirs();
        return file;
    }

    public File getDatabasePath() {
        File file = new File(getPluginDir(getReactApplicationContext(), getPluginRecord()).getAbsolutePath(), TaskConfig.v);
        file.mkdirs();
        return file;
    }

    public static File getPluginDir(Context context, PluginRecord pluginRecord) {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append(getAppFilesDir(context));
        sb.append(File.separator);
        sb.append("plugin");
        sb.append(File.separator);
        sb.append("install");
        sb.append(File.separator);
        sb.append("rn");
        sb.append(File.separator);
        if (pluginRecord == null) {
            obj = "0";
        } else {
            obj = Long.valueOf(pluginRecord.d());
        }
        sb.append(obj);
        sb.append(File.separator);
        sb.append("data");
        return new File(sb.toString());
    }

    public static File getPluginSpFileDir(Context context, PluginRecord pluginRecord, DeviceStat deviceStat) {
        Object obj;
        String str = (deviceStat == null || TextUtils.isEmpty(deviceStat.did)) ? "0" : deviceStat.did;
        StringBuilder sb = new StringBuilder();
        sb.append(getAppFilesDir(context));
        sb.append(File.separator);
        sb.append("plugin");
        sb.append(File.separator);
        sb.append("install");
        sb.append(File.separator);
        sb.append("rn");
        sb.append(File.separator);
        if (pluginRecord == null) {
            obj = "0";
        } else {
            obj = Long.valueOf(pluginRecord.d());
        }
        sb.append(obj);
        sb.append(File.separator);
        sb.append("data");
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append("data");
        return new File(sb.toString());
    }

    public static File getPluginFilesPath(File file) {
        return new File(file, "files");
    }

    public static File getPluginDatabasePath(File file) {
        return new File(file, TaskConfig.v);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r0 = java.io.File.separator + "data" + java.io.File.separator + "data" + java.io.File.separator + r2.getPackageName() + java.io.File.separator + "files";
        new java.io.File(r0).mkdirs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0044, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0045, code lost:
        r2 = r2.getDir("files", 0);
        r2.mkdirs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0053, code lost:
        return r2.getPath();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getAppFilesDir(android.content.Context r2) {
        /*
            java.io.File r0 = r2.getFilesDir()     // Catch:{ Throwable -> 0x0009 }
            java.lang.String r0 = r0.getPath()     // Catch:{ Throwable -> 0x0009 }
            return r0
        L_0x0009:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0045 }
            r0.<init>()     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "data"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "data"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = r2.getPackageName()     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "files"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0045 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0045 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0045 }
            r1.mkdirs()     // Catch:{ Throwable -> 0x0045 }
            return r0
        L_0x0045:
            java.lang.String r0 = "files"
            r1 = 0
            java.io.File r2 = r2.getDir(r0, r1)
            r2.mkdirs()
            java.lang.String r2 = r2.getPath()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTBaseJavaModule.getAppFilesDir(android.content.Context):java.lang.String");
    }
}
