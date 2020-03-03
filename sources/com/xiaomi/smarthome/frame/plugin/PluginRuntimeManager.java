package com.xiaomi.smarthome.frame.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.core.entity.plugin.PluginDexClassLoader;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.IBridgeCallback;
import com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivityMain;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivityPlugin1;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivityPlugin2;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivityPlugin3;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServiceFrame1;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServiceFrame2;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServiceMain;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServicePlugin;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServicePlugin1;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServicePlugin2;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeServicePlugin3;
import com.xiaomi.smarthome.frame.plugin.runtime.service.PluginHostServiceDesaiShoe;
import com.xiaomi.smarthome.frame.plugin.runtime.service.PluginHostServiceOneMore;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.plugin.service.HostService;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PluginRuntimeManager {
    private static final String TAG = "PluginManagerFrame";
    private static PluginRuntimeManager sInstance;
    private static final Object sLock = new Object();
    /* access modifiers changed from: private */
    public Context mAppContext = FrameManager.b().c();
    long[] mCameraProcessModels = new long[2];
    private long mCurrentPackageId;
    private RunningProcess mCurrentProcess;
    private List<String> mCurrentRelations;
    private IBridgeServiceApi mFrame1BridgeApiProxy;
    private IBridgeServiceApi mFrame2BridgeApiProxy;
    private IBridgeServiceApi mMainBridgeApiProxy;
    private final ConcurrentMap<String, XmPluginPackage> mModelPackages = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public RunningProcess mNextProcess;
    private final ConcurrentMap<String, XmPluginPackage> mPackagePathPackages = new ConcurrentHashMap();
    private IBridgeServiceApi mPlugin1BridgeApiProxy;
    private IBridgeServiceApi mPlugin2BridgeApiProxy;
    private IBridgeServiceApi mPlugin3BridgeApiProxy;
    private IBridgeServiceApi mPluginBridgeApiProxy;
    private HashMap<Long, RunningProcess> mStartedCameraProcess = new HashMap<>();
    private HashMap<Long, RunningProcess> mStartedProcess = new HashMap<>();

    enum ProcessType {
        MAIN,
        CAMERA_FRAME,
        OTHER
    }

    public XmPluginPackage getPackageFromPackage(String str) {
        return null;
    }

    private PluginRuntimeManager() {
    }

    public static PluginRuntimeManager getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new PluginRuntimeManager();
                }
            }
        }
        return sInstance;
    }

    public static void clearViewBuffer() {
        try {
            Field declaredField = LayoutInflater.class.getDeclaredField("sConstructorMap");
            if (Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                ((HashMap) declaredField.get((Object) null)).clear();
            }
        } catch (Exception unused) {
        }
    }

    private static void applyLanguage(Resources resources, Locale locale) {
        if (resources != null) {
            Log.d("LanguageUtil", "applyLanguage:" + locale.toString());
            Configuration configuration = resources.getConfiguration();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration.locale = locale;
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    public IBridgeServiceApi getBridgeApiProxy(RunningProcess runningProcess) {
        IBridgeServiceApi iBridgeServiceApi = null;
        if (runningProcess == null) {
            return null;
        }
        synchronized (sLock) {
            if (runningProcess == RunningProcess.MAIN) {
                iBridgeServiceApi = this.mMainBridgeApiProxy;
            } else if (runningProcess == RunningProcess.PLUGIN0) {
                iBridgeServiceApi = this.mPluginBridgeApiProxy;
            } else if (runningProcess == RunningProcess.PLUGIN1) {
                iBridgeServiceApi = this.mPlugin1BridgeApiProxy;
            } else if (runningProcess == RunningProcess.PLUGIN2) {
                iBridgeServiceApi = this.mPlugin2BridgeApiProxy;
            } else if (runningProcess == RunningProcess.PLUGIN3) {
                iBridgeServiceApi = this.mPlugin3BridgeApiProxy;
            } else if (runningProcess == RunningProcess.FRAME1) {
                iBridgeServiceApi = this.mFrame1BridgeApiProxy;
            } else if (runningProcess == RunningProcess.FRAME2) {
                iBridgeServiceApi = this.mFrame2BridgeApiProxy;
            }
        }
        return iBridgeServiceApi;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0075, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBridgeApiProxy(java.lang.String r7, com.xiaomi.smarthome.frame.plugin.RunningProcess r8, com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi r9) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = sLock
            monitor-enter(r0)
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.MAIN     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x000d
            r6.mMainBridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x000d:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.PLUGIN0     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x0014
            r6.mPluginBridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x0014:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.PLUGIN1     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x001b
            r6.mPlugin1BridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x001b:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.PLUGIN2     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x0022
            r6.mPlugin2BridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x0022:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.PLUGIN3     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x0029
            r6.mPlugin3BridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x0029:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.FRAME1     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x0030
            r6.mFrame1BridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
            goto L_0x0036
        L_0x0030:
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = com.xiaomi.smarthome.frame.plugin.RunningProcess.FRAME2     // Catch:{ all -> 0x0076 }
            if (r8 != r1) goto L_0x0036
            r6.mFrame2BridgeApiProxy = r9     // Catch:{ all -> 0x0076 }
        L_0x0036:
            if (r9 != 0) goto L_0x0074
            com.xiaomi.smarthome.frame.core.CoreApi r8 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x0076 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r7 = r8.d((java.lang.String) r7)     // Catch:{ all -> 0x0076 }
            if (r7 != 0) goto L_0x0044
            monitor-exit(r0)     // Catch:{ all -> 0x0076 }
            return
        L_0x0044:
            long r7 = r7.e()     // Catch:{ all -> 0x0076 }
            long[] r9 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r1 = 0
            r2 = r9[r1]     // Catch:{ all -> 0x0076 }
            r4 = 0
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x005f
            long[] r9 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r2 = r9[r1]     // Catch:{ all -> 0x0076 }
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x005f
            long[] r9 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r9[r1] = r4     // Catch:{ all -> 0x0076 }
        L_0x005f:
            long[] r9 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r1 = 1
            r2 = r9[r1]     // Catch:{ all -> 0x0076 }
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0074
            long[] r9 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r2 = r9[r1]     // Catch:{ all -> 0x0076 }
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0074
            long[] r7 = r6.mCameraProcessModels     // Catch:{ all -> 0x0076 }
            r7[r1] = r4     // Catch:{ all -> 0x0076 }
        L_0x0074:
            monitor-exit(r0)     // Catch:{ all -> 0x0076 }
            return
        L_0x0076:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0076 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.setBridgeApiProxy(java.lang.String, com.xiaomi.smarthome.frame.plugin.RunningProcess, com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi):void");
    }

    public XmPluginPackage getXmPluginPackage(String str) {
        return (XmPluginPackage) this.mModelPackages.get(str);
    }

    public void applyLanguage(Locale locale) {
        for (XmPluginPackage resources : this.mPackagePathPackages.values()) {
            applyLanguage(resources.getResources(), locale);
        }
    }

    public XmPluginPackage loadApk(PluginPackageInfo pluginPackageInfo) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        IXmPluginMessageReceiver iXmPluginMessageReceiver;
        long currentTimeMillis = System.currentTimeMillis();
        if (pluginPackageInfo == null) {
            return null;
        }
        XmPluginPackage xmPluginPackage = (XmPluginPackage) this.mPackagePathPackages.get(pluginPackageInfo.f());
        if (xmPluginPackage != null) {
            Locale I = CoreApi.a().I();
            if (I == null) {
                return xmPluginPackage;
            }
            Configuration configuration = xmPluginPackage.getResources().getConfiguration();
            if (!I.equals(configuration.locale)) {
                configuration.locale = I;
                try {
                    xmPluginPackage.getResources().updateConfiguration(configuration, xmPluginPackage.getResources().getDisplayMetrics());
                } catch (Exception unused) {
                }
            }
            return xmPluginPackage;
        }
        long a2 = pluginPackageInfo.a();
        long b = pluginPackageInfo.b();
        String f = pluginPackageInfo.f();
        if (!PluginSetting.a(a2) || !PluginSetting.b(b) || TextUtils.isEmpty(f) || (packageArchiveInfo = packageManager.getPackageArchiveInfo(f, 128)) == null) {
            return null;
        }
        String str = "";
        if (packageArchiveInfo.applicationInfo.metaData != null) {
            str = packageArchiveInfo.applicationInfo.metaData.getString("message_handler");
        }
        String str2 = str;
        String h = pluginPackageInfo.h();
        DexClassLoader createDexClassLoader = createDexClassLoader(a2, b, f);
        AssetManager createAssetManager = createAssetManager(f);
        Resources createResources = createResources(createAssetManager);
        if (!TextUtils.isEmpty(str2)) {
            try {
                iXmPluginMessageReceiver = (IXmPluginMessageReceiver) createDexClassLoader.loadClass(str2).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, "load apk", e);
                return null;
            }
        } else {
            iXmPluginMessageReceiver = null;
        }
        PackageInfo packageInfo = packageArchiveInfo;
        PackageManager packageManager2 = this.mAppContext.getPackageManager();
        DexClassLoader dexClassLoader = createDexClassLoader;
        String str3 = f;
        XmPluginPackage xmPluginPackage2 = new XmPluginPackage(h, f, dexClassLoader, createAssetManager, createResources, packageInfo, "", iXmPluginMessageReceiver);
        xmPluginPackage2.setModelList(pluginPackageInfo.m());
        xmPluginPackage2.setPluginId(pluginPackageInfo.a());
        xmPluginPackage2.setPackageId(pluginPackageInfo.b());
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            applicationInfo.sourceDir = str3;
            applicationInfo.publicSourceDir = str3;
        }
        xmPluginPackage2.miniApiVersion = pluginPackageInfo.j();
        xmPluginPackage2.appLabel = packageManager2.getApplicationLabel(applicationInfo);
        xmPluginPackage2.packageVersion = pluginPackageInfo.g();
        for (String put : pluginPackageInfo.m()) {
            this.mModelPackages.put(put, xmPluginPackage2);
        }
        this.mPackagePathPackages.put(pluginPackageInfo.f(), xmPluginPackage2);
        PluginBridgeService.mLoadTime = System.currentTimeMillis() - currentTimeMillis;
        return xmPluginPackage2;
    }

    private DexClassLoader createDexClassLoader(long j, long j2, String str) {
        String a2 = PluginSetting.a(this.mAppContext, j, j2);
        FileUtils.k(a2);
        String b = PluginSoManager.b(this.mAppContext, j, j2);
        String b2 = b == null ? PluginSetting.b(this.mAppContext, j, j2) : b;
        LogUtil.c(TAG, "createDexClassLoader load so path:" + b2);
        return new PluginDexClassLoader(str, a2, b2, this.mAppContext.getClassLoader().getParent(), this.mAppContext.getClassLoader());
    }

    private AssetManager createAssetManager(String str) {
        try {
            AssetManager newInstance = AssetManager.class.newInstance();
            newInstance.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
            return newInstance;
        } catch (Exception unused) {
            return null;
        }
    }

    public static RunningProcess getProcessByName(String str) {
        if (str.equals(PluginBridgeServicePlugin.class.getName())) {
            return RunningProcess.PLUGIN0;
        }
        if (str.equals(PluginBridgeServicePlugin1.class.getName())) {
            return RunningProcess.PLUGIN1;
        }
        if (str.equals(PluginBridgeServicePlugin2.class.getName())) {
            return RunningProcess.PLUGIN2;
        }
        if (str.equals(PluginBridgeServicePlugin3.class.getName())) {
            return RunningProcess.PLUGIN3;
        }
        if (str.equals(PluginBridgeServiceMain.class.getName())) {
            return RunningProcess.MAIN;
        }
        if (str.equals(PluginBridgeServiceFrame1.class.getName())) {
            return RunningProcess.FRAME1;
        }
        if (str.equals(PluginBridgeServiceFrame2.class.getName())) {
            return RunningProcess.FRAME2;
        }
        return null;
    }

    public void removeDexFile(Context context, long j, long j2) {
        if (PluginSetting.b(j2)) {
            File dir = context.getDir(ShareConstants.q, 0);
            new File(dir.getAbsolutePath() + File.separator + "plugin" + File.separator + j + File.separator + j2 + ShareConstants.w).delete();
        }
    }

    private Resources createResources(AssetManager assetManager) {
        Resources resources = this.mAppContext.getResources();
        return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    public XmPluginPackage getXmPluginPackageByCrashClassName(List<String> list) {
        boolean z;
        if (list == null || list.size() <= 0) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (String next : list) {
                if (!next.startsWith("android") && !next.startsWith("dalvik") && !next.startsWith("java") && !next.startsWith("javax")) {
                    if (!next.startsWith("com.xiaomi.smarthome")) {
                        String[] split = next.split("\\.");
                        if (split.length >= 3) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2]);
                        }
                        if (split.length >= 4) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2] + "." + split[3]);
                        }
                        if (split.length >= 5) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2] + "." + split[3] + "." + split[4]);
                        }
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            XmPluginPackage xmPluginPackage = null;
            for (int i = 0; i < this.mPackagePathPackages.size(); i++) {
                XmPluginPackage xmPluginPackage2 = (XmPluginPackage) this.mPackagePathPackages.get(Integer.valueOf(i));
                String str = xmPluginPackage2.packageName;
                if (!TextUtils.isEmpty(str)) {
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            xmPluginPackage2 = xmPluginPackage;
                            z = false;
                            break;
                        } else if (str.equalsIgnoreCase((String) arrayList.get(i2))) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z) {
                        return xmPluginPackage2;
                    }
                    xmPluginPackage = xmPluginPackage2;
                }
            }
            return xmPluginPackage;
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean checkPluginProcess(RunningProcess runningProcess) {
        if (runningProcess == null) {
            return false;
        }
        return runningProcess == RunningProcess.PLUGIN0 || runningProcess == RunningProcess.MAIN || runningProcess == RunningProcess.PLUGIN2 || runningProcess == RunningProcess.PLUGIN3 || runningProcess == RunningProcess.PLUGIN1 || runningProcess == RunningProcess.FRAME1 || runningProcess == RunningProcess.FRAME2;
    }

    private Class getPluginBridgeServiceClass(RunningProcess runningProcess) {
        if (runningProcess == RunningProcess.PLUGIN0) {
            return PluginBridgeServicePlugin.class;
        }
        if (runningProcess == RunningProcess.PLUGIN1) {
            return PluginBridgeServicePlugin1.class;
        }
        if (runningProcess == RunningProcess.MAIN) {
            return PluginBridgeServiceMain.class;
        }
        if (runningProcess == RunningProcess.PLUGIN2) {
            return PluginBridgeServicePlugin2.class;
        }
        if (runningProcess == RunningProcess.PLUGIN3) {
            return PluginBridgeServicePlugin3.class;
        }
        if (runningProcess == RunningProcess.FRAME1) {
            return PluginBridgeServiceFrame1.class;
        }
        if (runningProcess == RunningProcess.FRAME2) {
            return PluginBridgeServiceFrame2.class;
        }
        return null;
    }

    public RunningProcess getPluginProcess(int i, String str) {
        PluginRecord d = CoreApi.a().d(str);
        long e = d != null ? d.e() : -1;
        if (this.mCurrentProcess != null && e == this.mCurrentPackageId) {
            return this.mCurrentProcess;
        }
        RunningProcess pluginProcessInnerNew = getPluginProcessInnerNew(ProcessType.MAIN, i, str);
        return pluginProcessInnerNew == null ? RunningProcess.PLUGIN0 : pluginProcessInnerNew;
    }

    public RunningProcess getPluginProcess(ProcessType processType, int i, String str) {
        RunningProcess pluginProcessInnerNew = getPluginProcessInnerNew(processType, i, str);
        return pluginProcessInnerNew == null ? RunningProcess.PLUGIN0 : pluginProcessInnerNew;
    }

    private RunningProcess chooseOneCameraProcess(long j) {
        if (this.mStartedCameraProcess.size() < 3) {
            Collection<RunningProcess> values = this.mStartedCameraProcess.values();
            RunningProcess[] frameProcesses = RunningProcess.getFrameProcesses();
            int length = frameProcesses.length;
            int i = 0;
            while (i < length) {
                RunningProcess runningProcess = frameProcesses[i];
                if (values.contains(runningProcess) || runningProcess.equals(RunningProcess.MAIN)) {
                    i++;
                } else {
                    this.mStartedCameraProcess.put(Long.valueOf(j), runningProcess);
                    return runningProcess;
                }
            }
            return RunningProcess.PLUGIN0;
        }
        long j2 = Long.MAX_VALUE;
        long j3 = 0;
        Iterator it = new ArrayList(this.mStartedCameraProcess.keySet()).iterator();
        while (it.hasNext()) {
            Long l = (Long) it.next();
            if (this.mStartedCameraProcess.get(Long.valueOf(j)) != null && this.mStartedCameraProcess.get(Long.valueOf(j)).getTimeStamp() < j2) {
                long longValue = l.longValue();
                j2 = this.mStartedCameraProcess.get(Long.valueOf(j)).getTimeStamp();
                j3 = longValue;
            }
        }
        Log.e(TAG, "kill progress - " + this.mStartedCameraProcess.get(Long.valueOf(j)));
        RunningProcess runningProcess2 = this.mStartedCameraProcess.get(Long.valueOf(j3));
        exitProcess(runningProcess2);
        this.mStartedCameraProcess.put(Long.valueOf(j), runningProcess2);
        return runningProcess2;
    }

    /* access modifiers changed from: package-private */
    public RunningProcess getMainSelectProcess(RunningProcess runningProcess) {
        if (runningProcess == null) {
            return RunningProcess.PLUGIN0;
        }
        String name = runningProcess.name();
        return RunningProcess.getByProcessValue("plugin" + ((Integer.valueOf(name.substring(name.length() - 1)).intValue() + 1) % 3));
    }

    /* access modifiers changed from: package-private */
    public void processDiedProcess(RunningProcess runningProcess) {
        if (runningProcess == this.mNextProcess) {
            Class pluginBridgeServiceClass = getPluginBridgeServiceClass(this.mNextProcess);
            Intent intent = new Intent(this.mAppContext, pluginBridgeServiceClass);
            if (pluginBridgeServiceClass != null) {
                this.mAppContext.bindService(intent, new ServiceConnection() {
                    RunningProcess currentProcess = PluginRuntimeManager.this.mNextProcess;

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, IBridgeServiceApi.Stub.asInterface(iBinder));
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, (IBridgeServiceApi) null);
                        try {
                            PluginRuntimeManager.this.mAppContext.unbindService(this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        PluginRuntimeManager.this.processDiedProcess(this.currentProcess);
                    }
                }, 1);
            }
        }
    }

    private RunningProcess getPluginProcessInnerNew(ProcessType processType, int i, String str) {
        PluginPackageInfo h;
        if (str.equalsIgnoreCase("onemore.soundbox.sm001") || str.equalsIgnoreCase("desay.bleshoes.s311")) {
            return RunningProcess.PLUGIN3;
        }
        if (RnPluginDebugDeviceMock.f22091a.equals(str)) {
            return RunningProcess.PLUGIN0;
        }
        if (str.contains("xiaomi.tv") || "chuangmi.wifi.v1".equals(str)) {
            return RunningProcess.MAIN;
        }
        if (TextUtils.isEmpty(str)) {
            return RunningProcess.PLUGIN0;
        }
        long j = 0;
        PluginRecord d = CoreApi.a().d(str);
        if (d != null) {
            j = d.e();
        }
        List<String> list = null;
        if (processType == ProcessType.MAIN) {
            if (this.mCurrentProcess == null && this.mNextProcess == null) {
                this.mCurrentProcess = RunningProcess.PLUGIN0;
                this.mCurrentPackageId = j;
                if (d != null && d.h().p()) {
                    this.mCurrentRelations = d.G();
                }
                Class pluginBridgeServiceClass = getPluginBridgeServiceClass(RunningProcess.PLUGIN1);
                Intent intent = new Intent(this.mAppContext, pluginBridgeServiceClass);
                if (pluginBridgeServiceClass != null) {
                    this.mAppContext.bindService(intent, new ServiceConnection() {
                        RunningProcess currentProcess = RunningProcess.PLUGIN1;

                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, IBridgeServiceApi.Stub.asInterface(iBinder));
                        }

                        public void onServiceDisconnected(ComponentName componentName) {
                            PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, (IBridgeServiceApi) null);
                            try {
                                PluginRuntimeManager.this.mAppContext.unbindService(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            PluginRuntimeManager.this.processDiedProcess(this.currentProcess);
                        }
                    }, 1);
                }
                this.mNextProcess = RunningProcess.PLUGIN1;
                return this.mCurrentProcess;
            } else if (this.mNextProcess == null) {
                return RunningProcess.PLUGIN0;
            } else {
                boolean z = false;
                if (!(d == null || (h = d.h()) == null)) {
                    z = h.p();
                }
                if (this.mCurrentProcess != null && this.mCurrentPackageId == j && z) {
                    return this.mCurrentProcess;
                }
                if (this.mCurrentRelations != null && z) {
                    if (d != null) {
                        list = d.G();
                    }
                    if (list != null) {
                        for (String next : this.mCurrentRelations) {
                            Iterator<String> it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (it.next().equals(next)) {
                                        return this.mCurrentProcess;
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.mCurrentProcess != null) {
                    exitProcess(this.mCurrentProcess);
                }
                this.mCurrentProcess = this.mNextProcess;
                this.mCurrentPackageId = j;
                if (d != null) {
                    this.mCurrentRelations = d.G();
                }
                this.mNextProcess = getMainSelectProcess(this.mNextProcess);
                if (getBridgeApiProxy(this.mNextProcess) == null) {
                    Class pluginBridgeServiceClass2 = getPluginBridgeServiceClass(this.mNextProcess);
                    Intent intent2 = new Intent(this.mAppContext, pluginBridgeServiceClass2);
                    if (pluginBridgeServiceClass2 != null) {
                        this.mAppContext.bindService(intent2, new ServiceConnection() {
                            RunningProcess currentProcess = PluginRuntimeManager.this.mNextProcess;

                            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                                PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, IBridgeServiceApi.Stub.asInterface(iBinder));
                            }

                            public void onServiceDisconnected(ComponentName componentName) {
                                PluginRuntimeManager.this.setBridgeApiProxy("", this.currentProcess, (IBridgeServiceApi) null);
                                try {
                                    PluginRuntimeManager.this.mAppContext.unbindService(this);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                PluginRuntimeManager.this.processDiedProcess(this.currentProcess);
                            }
                        }, 1);
                    }
                }
                return this.mCurrentProcess;
            }
        } else if (processType != ProcessType.CAMERA_FRAME) {
            PluginRecord d2 = CoreApi.a().d(str);
            if (d2 != null && d2.h() != null && d2.h().q()) {
                return RunningProcess.PLUGIN3;
            }
            if (this.mCurrentProcess == null || this.mCurrentPackageId != j) {
                return RunningProcess.PLUGIN3;
            }
            return this.mCurrentProcess;
        } else if (!ProcessUtil.b(XmPluginHostApi.instance().context())) {
            Log.e(TAG, "getPluginProcess is not in main process:" + ProcessUtil.f1529a);
            return null;
        } else {
            PluginRecord d3 = CoreApi.a().d(str);
            if (d3 == null) {
                Log.e(TAG, "getPluginRecord return null:" + str);
                return null;
            }
            long e = d3.e();
            if (this.mStartedCameraProcess.containsKey(Long.valueOf(e))) {
                return this.mStartedCameraProcess.get(Long.valueOf(e));
            }
            return chooseOneCameraProcess(e);
        }
    }

    private RunningProcess getPluginProcessInner(int i, String str) {
        if (str.equalsIgnoreCase("onemore.soundbox.sm001") || str.equalsIgnoreCase("desay.bleshoes.s311")) {
            return RunningProcess.PLUGIN3;
        }
        if (str.contains("xiaomi.tv") || "chuangmi.wifi.v1".equals(str)) {
            return RunningProcess.MAIN;
        }
        if (TextUtils.isEmpty(str)) {
            return RunningProcess.PLUGIN0;
        }
        if (!str.contains(UserAvatarUpdateActivity.CAMERA)) {
            return RunningProcess.PLUGIN0;
        }
        if (!ProcessUtil.b(XmPluginHostApi.instance().context())) {
            Log.e(TAG, "getPluginProcess is not in main process:" + ProcessUtil.f1529a);
            return null;
        }
        PluginRecord d = CoreApi.a().d(str);
        if (d == null) {
            Log.e(TAG, "getPluginRecord return null:" + str);
            return null;
        }
        long e = d.e();
        if (this.mStartedCameraProcess.containsKey(Long.valueOf(e))) {
            return this.mStartedCameraProcess.get(Long.valueOf(e));
        }
        return chooseOneCameraProcess(e);
    }

    public Class getPluginActivityClass(RunningProcess runningProcess) {
        if (runningProcess == RunningProcess.PLUGIN0) {
            return PluginHostActivity.class;
        }
        if (runningProcess == RunningProcess.PLUGIN2) {
            return PluginHostActivityPlugin2.class;
        }
        if (runningProcess == RunningProcess.MAIN) {
            return PluginHostActivityMain.class;
        }
        if (runningProcess == RunningProcess.PLUGIN3) {
            return PluginHostActivityPlugin3.class;
        }
        if (runningProcess == RunningProcess.PLUGIN1) {
            return PluginHostActivityPlugin1.class;
        }
        return PluginHostActivity.class;
    }

    public String getPluginRNActivityClass(RunningProcess runningProcess) {
        if (runningProcess == RunningProcess.PLUGIN0) {
            return "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivity";
        }
        if (runningProcess == RunningProcess.PLUGIN2) {
            return "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivityPlugin2";
        }
        if (runningProcess == RunningProcess.MAIN) {
            return "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivityMain";
        }
        if (runningProcess == RunningProcess.PLUGIN3) {
            return "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivityPlugin3";
        }
        return runningProcess == RunningProcess.PLUGIN1 ? "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivityPlugin1" : "com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivity";
    }

    public Class getPluginHostServiceClass(RunningProcess runningProcess, String str) {
        if (runningProcess == null || TextUtils.isEmpty(str) || runningProcess != RunningProcess.PLUGIN3 || !str.equalsIgnoreCase("onemore.soundbox.sm001")) {
            return null;
        }
        return PluginHostServiceOneMore.class;
    }

    public Class getPluginHostServiceClass(HostService hostService) {
        if (hostService == null) {
            return null;
        }
        if (hostService == HostService.OneMore) {
            return PluginHostServiceOneMore.class;
        }
        if (hostService == HostService.DesaiShoe) {
            return PluginHostServiceDesaiShoe.class;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public RunningProcess getProcess(int i, String str, DeviceStat deviceStat, RunningProcess runningProcess) {
        ProcessType processType;
        if (i == 18 || i == 19 || i == 20 || i == 21) {
            processType = ProcessType.CAMERA_FRAME;
        } else if (i == 1 || i == 2) {
            processType = ProcessType.MAIN;
        } else if (!PluginSetting.a() || !GlobalSetting.t) {
            processType = ProcessType.OTHER;
        } else {
            processType = ProcessType.MAIN;
        }
        RunningProcess pluginProcess = getInstance().getPluginProcess(processType, deviceStat != null ? deviceStat.pid : 0, str);
        if (pluginProcess == null) {
            pluginProcess = runningProcess;
        }
        return pluginProcess == null ? RunningProcess.PLUGIN0 : pluginProcess;
    }

    /* access modifiers changed from: package-private */
    public void killOtherCameraProcess(RunningProcess runningProcess) {
        Iterator it = new ArrayList(this.mStartedCameraProcess.keySet()).iterator();
        while (it.hasNext()) {
            if (this.mStartedCameraProcess.get((Long) it.next()) != runningProcess) {
                exitProcess(runningProcess);
            }
        }
    }

    public void exitAllFrameProcess() {
        for (RunningProcess exitProcess : RunningProcess.getFrameProcesses()) {
            exitProcess(exitProcess);
        }
    }

    private boolean isRNPlugin(PluginRecord pluginRecord) {
        PluginPackageInfo h;
        if (pluginRecord == null || (h = pluginRecord.h()) == null) {
            return false;
        }
        return h.q();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendMessage(android.content.Context r15, java.lang.String r16, int r17, android.content.Intent r18, com.xiaomi.smarthome.device.api.DeviceStat r19, com.xiaomi.smarthome.frame.plugin.RunningProcess r20, boolean r21, com.xiaomi.smarthome.frame.plugin.PluginApi.SendMessageCallback r22) {
        /*
            r14 = this;
            r10 = r14
            r4 = r16
            r6 = r17
            r7 = r18
            r8 = r22
            r11 = 1
            if (r7 == 0) goto L_0x0017
            if (r6 != r11) goto L_0x0017
            java.lang.String r0 = "plugin_init_time"
            long r1 = java.lang.System.currentTimeMillis()
            r7.putExtra(r0, r1)
        L_0x0017:
            r1 = 0
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r0.d((java.lang.String) r4)
            r2 = 2
            if (r6 != r2) goto L_0x0072
            if (r0 == 0) goto L_0x0072
            long r2 = r0.e()
            long r12 = r10.mCurrentPackageId
            int r5 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r5 != 0) goto L_0x0072
            com.xiaomi.smarthome.frame.plugin.RunningProcess r2 = r10.mCurrentProcess
            if (r2 == 0) goto L_0x0072
            boolean r0 = r14.isRNPlugin(r0)
            if (r0 == 0) goto L_0x0072
            com.xiaomi.smarthome.frame.plugin.RunningProcess r0 = r10.mCurrentProcess     // Catch:{ RemoteException -> 0x005b }
            com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi r0 = r14.getBridgeApiProxy(r0)     // Catch:{ RemoteException -> 0x005b }
            if (r0 == 0) goto L_0x0072
            boolean r0 = r0.isProcessForeground()     // Catch:{ RemoteException -> 0x005b }
            if (r0 == 0) goto L_0x0072
            com.xiaomi.smarthome.frame.plugin.RunningProcess r2 = r10.mCurrentProcess     // Catch:{ RemoteException -> 0x005b }
            java.lang.String r0 = "extra_start_rnplugin_activity"
            r1 = 0
            r7.putExtra(r0, r1)     // Catch:{ RemoteException -> 0x0058 }
            java.lang.String r0 = "PluginManagerFrame"
            java.lang.String r1 = "sendMessage: RN push message, plugin is running foreground, reuse mCurrentProcess"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r0, r1)     // Catch:{ RemoteException -> 0x0058 }
            r1 = r2
            goto L_0x0072
        L_0x0058:
            r0 = move-exception
            r1 = r2
            goto L_0x005c
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            java.lang.String r2 = "PluginManagerFrame"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendMessage:"
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r2, r0)
        L_0x0072:
            if (r1 != 0) goto L_0x007d
            r9 = r19
            r2 = r20
            com.xiaomi.smarthome.frame.plugin.RunningProcess r1 = r14.getProcess(r6, r4, r9, r2)
            goto L_0x007f
        L_0x007d:
            r9 = r19
        L_0x007f:
            r0 = 18
            if (r6 == r0) goto L_0x008f
            r0 = 19
            if (r6 == r0) goto L_0x008f
            r0 = 20
            if (r6 == r0) goto L_0x008f
            r0 = 21
            if (r6 != r0) goto L_0x009f
        L_0x008f:
            if (r7 == 0) goto L_0x0098
            java.lang.String r0 = "run_on_main"
            boolean r0 = r7.getBooleanExtra(r0, r11)
            goto L_0x0099
        L_0x0098:
            r0 = 1
        L_0x0099:
            if (r0 == 0) goto L_0x009f
            com.xiaomi.smarthome.frame.plugin.RunningProcess r0 = com.xiaomi.smarthome.frame.plugin.RunningProcess.MAIN
            r5 = r0
            goto L_0x00a0
        L_0x009f:
            r5 = r1
        L_0x00a0:
            boolean r0 = r14.checkPluginProcess(r5)
            r1 = -1
            if (r0 != 0) goto L_0x00ca
            if (r8 == 0) goto L_0x00c2
            com.xiaomi.smarthome.frame.Error r0 = new com.xiaomi.smarthome.frame.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "PluginProcess check failure"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r0.<init>(r1, r2)
            r8.onSendFailure(r0)
        L_0x00c2:
            java.lang.String r0 = "click_device_list"
            java.lang.String r1 = "PluginRuntimeManager.sendMessage checkPluginProcess false"
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)
            return
        L_0x00ca:
            java.lang.String r0 = "PluginManagerFrame"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r3 = " sendMessage RunningProcess:"
            r2.append(r3)
            java.lang.String r3 = r5.getValue()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r0, r2)
            com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi r2 = r14.getBridgeApiProxy(r5)
            if (r2 != 0) goto L_0x0150
            java.lang.Class r0 = r14.getPluginBridgeServiceClass(r5)
            if (r0 != 0) goto L_0x0116
            if (r8 == 0) goto L_0x010e
            com.xiaomi.smarthome.frame.Error r0 = new com.xiaomi.smarthome.frame.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "PluginProcess not support "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r0.<init>(r1, r2)
            r8.onSendFailure(r0)
        L_0x010e:
            java.lang.String r0 = "click_device_list"
            java.lang.String r1 = "PluginRuntimeManager.sendMessage clazz is null"
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)
            return
        L_0x0116:
            java.lang.String r1 = "PluginManagerFrame"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "BridgeServiceClass:"
            r2.append(r3)
            java.lang.String r3 = r0.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            android.content.Intent r12 = new android.content.Intent
            android.content.Context r1 = r10.mAppContext
            r12.<init>(r1, r0)
            android.content.Context r0 = r10.mAppContext
            com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager$4 r13 = new com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager$4
            r1 = r13
            r2 = r14
            r3 = r22
            r4 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r21
            r1.<init>(r3, r4, r5, r6, r7, r8, r9)
            r0.bindService(r12, r13, r11)
            goto L_0x0160
        L_0x0150:
            r1 = r14
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r21
            r8 = r22
            r1.sendMessageWithProx(r2, r3, r4, r5, r6, r7, r8)
        L_0x0160:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager.sendMessage(android.content.Context, java.lang.String, int, android.content.Intent, com.xiaomi.smarthome.device.api.DeviceStat, com.xiaomi.smarthome.frame.plugin.RunningProcess, boolean, com.xiaomi.smarthome.frame.plugin.PluginApi$SendMessageCallback):void");
    }

    static class SendMessageCallbackWrapper {
        PluginApi.SendMessageCallback mCallback;

        SendMessageCallbackWrapper(PluginApi.SendMessageCallback sendMessageCallback) {
            this.mCallback = sendMessageCallback;
        }
    }

    /* access modifiers changed from: package-private */
    public void sendMessageWithProx(IBridgeServiceApi iBridgeServiceApi, String str, int i, Intent intent, DeviceStat deviceStat, boolean z, PluginApi.SendMessageCallback sendMessageCallback) {
        String str2;
        try {
            LogUtil.c("PluginStartTime", "sendMessageWithProx  " + System.currentTimeMillis());
            final SendMessageCallbackWrapper sendMessageCallbackWrapper = new SendMessageCallbackWrapper(sendMessageCallback);
            if (deviceStat == null) {
                str2 = null;
            } else {
                str2 = deviceStat.did;
            }
            iBridgeServiceApi.sendMessage(str, str2, i, intent, z, new IBridgeCallback.Stub() {
                public void onSendSuccess(Bundle bundle) throws RemoteException {
                    PluginApi.SendMessageCallback sendMessageCallback = sendMessageCallbackWrapper.mCallback;
                    if (sendMessageCallback != null) {
                        if (bundle != null) {
                            bundle.setClassLoader(PluginRuntimeManager.class.getClassLoader());
                        }
                        sendMessageCallback.onSendSuccess(bundle);
                    }
                }

                public void onHandle(boolean z) throws RemoteException {
                    PluginApi.SendMessageCallback sendMessageCallback = sendMessageCallbackWrapper.mCallback;
                    if (sendMessageCallback != null) {
                        sendMessageCallback.onMessageHandle(z);
                    }
                }

                public void onMessageSuccess(Bundle bundle) throws RemoteException {
                    PluginApi.SendMessageCallback sendMessageCallback = sendMessageCallbackWrapper.mCallback;
                    if (sendMessageCallback != null) {
                        Intent intent = new Intent();
                        if (bundle != null) {
                            bundle.setClassLoader(PluginRuntimeManager.class.getClassLoader());
                            intent.putExtras(bundle);
                        }
                        sendMessageCallback.onMessageSuccess(intent);
                    }
                }

                public void onMessageFailure(BridgeError bridgeError) throws RemoteException {
                    PluginApi.SendMessageCallback sendMessageCallback = sendMessageCallbackWrapper.mCallback;
                    if (sendMessageCallback != null) {
                        sendMessageCallback.onMessageFailure(bridgeError.getCode(), bridgeError.getDetail());
                    }
                    LogUtilGrey.a("click_device_list", "PluginRuntimeManager.sendMessageWithProx-sendMessage-onMessageFailure");
                }

                public void onFailure(BridgeError bridgeError) throws RemoteException {
                    PluginApi.SendMessageCallback sendMessageCallback = sendMessageCallbackWrapper.mCallback;
                    if (sendMessageCallback != null) {
                        sendMessageCallback.onSendFailure(new Error(bridgeError.getCode(), bridgeError.getDetail()));
                    }
                    LogUtilGrey.a("click_device_list", "PluginRuntimeManager.sendMessageWithProx-sendMessage-onFailure");
                }
            });
        } catch (RemoteException unused) {
            if (sendMessageCallback != null) {
                sendMessageCallback.onSendFailure(new Error(-1, "sendMessage failure"));
            }
            LogUtilGrey.a("click_device_list", "PluginRuntimeManager.sendMessageWithProx RemoteException");
        }
    }

    public void initOneProgress() {
        if (!(this.mCurrentProcess == null || this.mNextProcess == null)) {
            if (getBridgeApiProxy(this.mCurrentProcess) == null) {
                this.mNextProcess = null;
                this.mCurrentProcess = null;
            } else {
                return;
            }
        }
        if (getBridgeApiProxy(RunningProcess.PLUGIN0) == null) {
            Class pluginBridgeServiceClass = getPluginBridgeServiceClass(RunningProcess.PLUGIN0);
            Intent intent = new Intent(this.mAppContext, pluginBridgeServiceClass);
            if (pluginBridgeServiceClass != null) {
                this.mAppContext.bindService(intent, new ServiceConnection() {
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        PluginRuntimeManager.this.setBridgeApiProxy("", RunningProcess.PLUGIN0, IBridgeServiceApi.Stub.asInterface(iBinder));
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        PluginRuntimeManager.this.setBridgeApiProxy("", RunningProcess.PLUGIN0, (IBridgeServiceApi) null);
                        try {
                            PluginRuntimeManager.this.mAppContext.unbindService(this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        PluginRuntimeManager.this.processDiedProcess(RunningProcess.PLUGIN0);
                    }
                }, 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startService(RunningProcess runningProcess, String str, long j, long j2, Intent intent, String str2, PluginApi.StartServiceCallback startServiceCallback) {
        PluginApi.StartServiceCallback startServiceCallback2 = startServiceCallback;
        if (checkPluginProcess(runningProcess)) {
            IBridgeServiceApi bridgeApiProxy = getBridgeApiProxy(runningProcess);
            if (bridgeApiProxy == null) {
                Class pluginBridgeServiceClass = getPluginBridgeServiceClass(runningProcess);
                if (pluginBridgeServiceClass != null) {
                    final RunningProcess runningProcess2 = runningProcess;
                    final String str3 = str;
                    final long j3 = j;
                    final long j4 = j2;
                    final Intent intent2 = intent;
                    final String str4 = str2;
                    final PluginApi.StartServiceCallback startServiceCallback3 = startServiceCallback;
                    this.mAppContext.bindService(new Intent(this.mAppContext, pluginBridgeServiceClass), new ServiceConnection() {
                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            boolean z;
                            IBridgeServiceApi asInterface = IBridgeServiceApi.Stub.asInterface(iBinder);
                            PluginRuntimeManager.this.setBridgeApiProxy("", runningProcess2, asInterface);
                            try {
                                asInterface.startService(str3, j3, j4, intent2, str4);
                                z = false;
                            } catch (RemoteException unused) {
                                z = true;
                            }
                            if (z) {
                                if (startServiceCallback3 != null) {
                                    startServiceCallback3.onFailure(new Error(-1, ""));
                                }
                            } else if (startServiceCallback3 != null) {
                                startServiceCallback3.onSuccess();
                            }
                        }

                        public void onServiceDisconnected(ComponentName componentName) {
                            PluginRuntimeManager.this.setBridgeApiProxy("", runningProcess2, (IBridgeServiceApi) null);
                            try {
                                PluginRuntimeManager.this.mAppContext.unbindService(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (startServiceCallback3 != null) {
                                startServiceCallback3.onFailure(new Error(-1, ""));
                            }
                        }
                    }, 1);
                } else if (startServiceCallback2 != null) {
                    startServiceCallback2.onFailure(new Error(-1, ""));
                }
            } else {
                boolean z = false;
                try {
                    bridgeApiProxy.startService(str, j, j2, intent, str2);
                } catch (RemoteException unused) {
                    z = true;
                }
                if (z) {
                    if (startServiceCallback2 != null) {
                        startServiceCallback2.onFailure(new Error(-1, ""));
                    }
                } else if (startServiceCallback2 != null) {
                    startServiceCallback.onSuccess();
                }
            }
        } else if (startServiceCallback2 != null) {
            startServiceCallback2.onFailure(new Error(-1, ""));
        }
    }

    /* access modifiers changed from: package-private */
    public void bindService(RunningProcess runningProcess, String str, long j, long j2, String str2, ServiceConnection serviceConnection, int i, PluginApi.BindServiceCallback bindServiceCallback) {
        final String str3 = str;
        final ServiceConnection serviceConnection2 = serviceConnection;
        final int i2 = i;
        final PluginApi.BindServiceCallback bindServiceCallback2 = bindServiceCallback;
        startService(runningProcess, str3, j, j2, (Intent) null, str2, new PluginApi.StartServiceCallback() {
            public void onSuccess() {
                Intent intent = new Intent();
                intent.setClassName(PluginRuntimeManager.this.mAppContext.getPackageName(), str3);
                if (PluginRuntimeManager.this.mAppContext.bindService(intent, serviceConnection2, i2)) {
                    if (bindServiceCallback2 != null) {
                        bindServiceCallback2.onSuccess();
                    }
                } else if (bindServiceCallback2 != null) {
                    bindServiceCallback2.onFailure(new Error(-1, ""));
                }
            }

            public void onFailure(Error error) {
                if (bindServiceCallback2 != null) {
                    bindServiceCallback2.onFailure(error);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void unbindService(RunningProcess runningProcess, String str, long j, long j2, String str2, ServiceConnection serviceConnection, PluginApi.UnBindServiceCallback unBindServiceCallback) {
        if (serviceConnection != null) {
            try {
                this.mAppContext.unbindService(serviceConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (unBindServiceCallback != null) {
                unBindServiceCallback.onSuccess();
            }
        } else if (unBindServiceCallback != null) {
            unBindServiceCallback.onFailure(new Error(-1, "ServiceConnection is null"));
        }
    }

    public void exitALLProcess() {
        for (RunningProcess exitProcess : new RunningProcess[]{RunningProcess.PLUGIN0, RunningProcess.PLUGIN1, RunningProcess.PLUGIN2, RunningProcess.PLUGIN3, RunningProcess.FRAME1, RunningProcess.FRAME2}) {
            exitProcess(exitProcess);
        }
    }

    /* access modifiers changed from: package-private */
    public void exitProcess(RunningProcess runningProcess) {
        if (runningProcess != null) {
            IBridgeServiceApi bridgeApiProxy = getBridgeApiProxy(runningProcess);
            if (bridgeApiProxy != null) {
                try {
                    bridgeApiProxy.exitProcess();
                } catch (RemoteException unused) {
                }
            }
            for (Long next : this.mStartedCameraProcess.keySet()) {
                if (this.mStartedCameraProcess.get(next) == runningProcess) {
                    this.mStartedCameraProcess.remove(next);
                    return;
                }
            }
        }
    }

    abstract class BridgeServiceConnection implements ServiceConnection {
        PluginApi.SendMessageCallback mSendCallback;

        BridgeServiceConnection(PluginApi.SendMessageCallback sendMessageCallback) {
            this.mSendCallback = sendMessageCallback;
        }
    }
}
