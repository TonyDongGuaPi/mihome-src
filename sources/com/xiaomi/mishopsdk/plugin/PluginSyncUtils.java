package com.xiaomi.mishopsdk.plugin;

import android.os.SystemClock;
import android.text.TextUtils;
import com.alibaba.fastjson.TypeReference;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.toolbox.RequestFuture;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.ShopJSONRequest;
import com.xiaomi.mishopsdk.plugin.Model.PluginSyncInfo;
import com.xiaomi.mishopsdk.plugin.PluginDownloader;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import com.xiaomi.mishopsdk.utils.PluginQueueManager;
import com.xiaomi.mishopsdk.utils.StringUtils;
import com.xiaomi.shop2.plugin.NativePluginDBUtils;
import com.xiaomi.shop2.util.Device;
import com.xiaomi.shop2.util.DeviceUtil;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PluginSyncUtils {
    private static final String TAG = "PluginSyncUtils";
    private static PluginSyncUtils instance;
    public static long mLatestSyncTime;
    public ArrayList<PluginInfo> mLatestPlugins = new ArrayList<>();
    Object synObject = new Object();

    public static PluginSyncUtils getInstance() {
        if (instance == null) {
            instance = new PluginSyncUtils();
        }
        return instance;
    }

    public void syncInit() {
        if (ShopApp.instance != null) {
            AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
                public void run() {
                    int i = Device.MISHOP_SDK_VERSION;
                    PluginSyncUtils.mLatestSyncTime = 0;
                    if (i != PreferenceUtil.getIntPref(ShopApp.instance, Constants.Preference.PREFERNCE_KEY_CLIENTVERSION, 0)) {
                        PluginSyncUtils.this.updateLocalPluginFirstTime();
                    }
                    PluginInfoCache.getInstance(ShopApp.instance).initAllPluginInfos();
                }
            });
        }
    }

    public void checkPluginSyncInfo(final String str) {
        if (ShopApp.instance != null) {
            AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
                public void run() {
                    if (TextUtils.isEmpty(str)) {
                        EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.error, (PluginInfo) null, str));
                    }
                    PluginInfo pluginInfo = PluginInfoCache.getInstance(ShopApp.instance).getPluginInfo(ShopApp.instance, str);
                    PluginInfo lastNetPluginInfo = PluginSyncUtils.this.getLastNetPluginInfo(str);
                    if (SystemClock.elapsedRealtime() - PluginSyncUtils.mLatestSyncTime >= 120000) {
                        PluginSyncUtils.getInstance().pullSinglePluginVersion(str);
                    } else if (PluginInfo.checkVersion(lastNetPluginInfo, pluginInfo)) {
                        EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.synced, pluginInfo, str));
                    } else if (lastNetPluginInfo != null) {
                        PluginSyncUtils.getInstance().pullSinglePluginVersion(lastNetPluginInfo.id);
                    } else if (pluginInfo != null) {
                        EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.discard, pluginInfo, str));
                    } else {
                        EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.error, pluginInfo, str));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public PluginInfo getLastNetPluginInfo(String str) {
        Iterator<PluginInfo> it = this.mLatestPlugins.iterator();
        while (it.hasNext()) {
            PluginInfo next = it.next();
            if (next.id.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public void pullPluginVersion() {
        if (ShopApp.instance != null) {
            AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
                public void run() {
                    RequestFuture access$000 = PluginSyncUtils.this.addRequestAllPluginInfoToQueue();
                    try {
                        synchronized (PluginSyncUtils.this.synObject) {
                            ArrayList arrayList = (ArrayList) access$000.get();
                            PluginSyncUtils.mLatestSyncTime = SystemClock.elapsedRealtime();
                            PluginSyncUtils.this.mLatestPlugins.clear();
                            PluginSyncUtils.this.mLatestPlugins.addAll(arrayList);
                            PluginSyncUtils.this.syncPlugins(arrayList);
                        }
                    } catch (Exception e) {
                        try {
                            Log.e(PluginSyncUtils.TAG, "request plugin version failed.", (Object) e);
                        } catch (Throwable th) {
                            PluginSyncManager.getInstance().scheduleTimerDelay();
                            throw th;
                        }
                    }
                    PluginSyncManager.getInstance().scheduleTimerDelay();
                }
            });
        }
    }

    public void pullSinglePluginVersion(final String str) {
        if (ShopApp.instance == null || TextUtils.isEmpty(str)) {
            Log.e(TAG, "ShopApp.instance=%s, pluginId=%s.", ShopApp.instance, str);
        } else {
            AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
                public void run() {
                    RequestFuture access$000 = PluginSyncUtils.this.addRequestAllPluginInfoToQueue();
                    try {
                        synchronized (PluginSyncUtils.this.synObject) {
                            PluginInfo pluginInfo = null;
                            Iterator it = ((ArrayList) access$000.get()).iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    PluginInfo pluginInfo2 = (PluginInfo) it.next();
                                    if (pluginInfo2 != null && str.equals(pluginInfo2.id)) {
                                        pluginInfo = pluginInfo2;
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            if (pluginInfo == null) {
                                Log.d(PluginSyncUtils.TAG, "did not find curInfo, pullSinglePluginVersion return, id=%s.", (Object) str);
                                return;
                            }
                            PluginSyncUtils.this.detectAndUpdatePlugin(pluginInfo, PluginInfoCache.getInstance(ShopApp.instance).getPluginInfo(ShopApp.instance, str), true);
                            PluginDownloader.getInstance().freshDownloadQueue();
                        }
                    } catch (Exception e) {
                        Log.e(PluginSyncUtils.TAG, "pullSinglePluginVersion error, the plugin id:%s.", str, e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public RequestFuture<ArrayList<PluginInfo>> addRequestAllPluginInfoToQueue() {
        String str = HostManager.FORMAL_DOMAIN_APP_SHOPAPI_HTTPS + "apkversion/plugin";
        PluginQueueManager.getInstance().clearRequest(str);
        RequestFuture<ArrayList<PluginInfo>> newFuture = RequestFuture.newFuture();
        HashMap hashMap = new HashMap();
        hashMap.put("versionCode", "" + Device.MISHOP_SDK_VERSION);
        String str2 = "";
        try {
            str2 = DeviceUtil.getDSid();
        } catch (Exception e) {
            System.out.println("getDSid ExceptionError:" + e.getMessage());
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
            System.out.println("getDSid ThrowableError:" + th.getMessage());
        }
        PluginQueueManager.getInstance().addRequest(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ShopJSONRequest.builder().setTag(str)).setUrl(str)).addParams(hashMap)).setTypeToken(new TypeReference<ArrayList<PluginInfo>>() {
        }).setShouldCache(false)).setPriority(Request.Priority.LOW)).addHeader("App-Others", str2)).setListner(newFuture)).build());
        return newFuture;
    }

    /* access modifiers changed from: protected */
    public synchronized void syncPlugins(ArrayList<PluginInfo> arrayList) {
        PluginDownloader.getInstance().clearDownloadQueue();
        ConcurrentHashMap<String, PluginInfo> allPluginCached = PluginInfoCache.getInstance(ShopApp.instance).getAllPluginCached();
        HashMap hashMap = new HashMap(allPluginCached);
        Iterator<PluginInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            PluginInfo next = it.next();
            hashMap.remove(next.id);
            detectAndUpdatePlugin(next, allPluginCached.get(next.id), false);
        }
        for (Map.Entry key : hashMap.entrySet()) {
            PluginInfo pluginInfo = allPluginCached.get(key.getKey());
            EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.discard, pluginInfo, pluginInfo.id));
        }
        PluginDownloader.getInstance().freshDownloadQueue();
    }

    private boolean needSyncPluginInfo(PluginInfo pluginInfo, PluginInfo pluginInfo2) {
        if (pluginInfo == null) {
            return false;
        }
        if (pluginInfo2 == null) {
            return true;
        }
        if (!pluginInfo.checkEqual(pluginInfo2) || (pluginInfo2.isValid() && pluginInfo.checkVersion(pluginInfo2))) {
            Log.d(TAG, "push plugin ver is same, ver=%s.", (Object) pluginInfo.version);
            return false;
        }
        Log.d(TAG, "push plugin ver is different, %s->%s.", pluginInfo2.version, pluginInfo.version);
        return true;
    }

    /* access modifiers changed from: private */
    public void detectAndUpdatePlugin(PluginInfo pluginInfo, PluginInfo pluginInfo2, boolean z) {
        if (pluginInfo == null) {
            try {
                Log.e(TAG, "detectAndUpdatePlugin: the curInfo is null, exit.");
            } catch (Exception e) {
                Log.e(TAG, "detectAndUpdatePlugin failed.", (Object) e);
            }
        } else if (pluginInfo2 == null || pluginInfo.checkEqual(pluginInfo2)) {
            if (pluginInfo2 != null) {
                if (pluginInfo.checkVersion(pluginInfo2)) {
                    Log.d(TAG, "plugin %s is synced", (Object) pluginInfo.id);
                    EventBus.getDefault().post(new PluginSyncInfo(PluginSyncManager.SyncStatus.synced, pluginInfo2, pluginInfo2.id));
                    pluginInfo2.setLatestSyncTime(System.currentTimeMillis());
                    return;
                }
            }
            if (!PluginInfo.downloadCanDelayed(pluginInfo, z)) {
                Log.d(TAG, "add plugin %s to download queue.", (Object) pluginInfo.id);
                if (z) {
                    PluginDownloader.PluginPriorityComparator.setCurUsedPluginId(pluginInfo.id);
                    Log.d(TAG, "promote plugin %s's download priority.", (Object) pluginInfo.id);
                }
                PluginDownloader.getInstance().addPriorityQueueBy(pluginInfo);
                if (pluginInfo2 != null) {
                    pluginInfo2.setLatestSyncTime(0);
                    return;
                }
                return;
            }
            Log.d(TAG, "althpugh %s has new version, but it can be delayed.", (Object) pluginInfo.id);
        } else {
            Log.e(TAG, "error， ids are not equals, curId=%s, nId=%s, curVersion=%s, nativeVer=%s.", pluginInfo.id, pluginInfo2.id, pluginInfo.version, pluginInfo2.version);
        }
    }

    public boolean updateLocalPluginFirstTime() {
        if (ShopApp.instance == null) {
            return false;
        }
        try {
            PluginInfoCache.getInstance(ShopApp.instance).deletePluginsIncompatible();
            NativePluginDBUtils.getInstance().deleteAllPluginInfosToDB();
            NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ShopApp.instance.getAssets().open("config/plugins.xml")).getDocumentElement().getElementsByTagName("plugin");
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                PluginInfo pluginInfo = new PluginInfo();
                Element element = (Element) elementsByTagName.item(i);
                pluginInfo.id = element.getAttribute("id");
                pluginInfo.path = element.getAttribute("path");
                pluginInfo.priority = StringUtils.toInt(element.getAttribute("priority"), 100);
                pluginInfo.version = element.getAttribute("version");
                pluginInfo.rootFragment = element.getAttribute("rootFragment");
                pluginInfo.parentVersion = element.getAttribute("versionParent");
                pluginInfo.hasSo = StringUtils.toBool(element.getAttribute("has_so"));
                pluginInfo.onlineTime = "0";
                syncPluginFirstTime(pluginInfo, true);
            }
            PreferenceUtil.setIntPref(ShopApp.instance, Constants.Preference.PREFERNCE_KEY_CLIENTVERSION, Device.MISHOP_SDK_VERSION);
        } catch (Exception e) {
            Log.e(TAG, "updateLocalPluginFirstTime failed.", (Object) e);
        }
        return true;
    }

    private void syncPluginFirstTime(PluginInfo pluginInfo, boolean z) {
        if (ShopApp.instance != null && pluginInfo != null && !StringUtils.isEmpty(pluginInfo.id)) {
            PluginInfo pluginInfo2 = PluginInfoCache.getInstance(ShopApp.instance).getPluginInfo(ShopApp.instance, pluginInfo.id);
            if (z || pluginInfo2 == null || !pluginInfo.checkVersion(pluginInfo2)) {
                PluginDownloader.getInstance().createPluginLocalPath(pluginInfo);
                if (copyPluginFromAsset(pluginInfo)) {
                    PluginInfoCache.getInstance(ShopApp.instance).updatePluginInfo(ShopApp.instance, pluginInfo.id, pluginInfo);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean copyPluginFromAsset(PluginInfo pluginInfo) {
        if (ShopApp.instance == null || pluginInfo == null) {
            return false;
        }
        try {
            InputStream open = ShopApp.instance.getAssets().open(pluginInfo.path);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(pluginInfo.localPath));
            byte[] bArr = new byte[4096];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized void clearHistory() {
        AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
            public void run() {
                if (ShopApp.instance != null) {
                    PluginInfoCache instance = PluginInfoCache.getInstance(ShopApp.instance);
                    ArrayList<PluginInfo> allPluginInfosFromDisk = instance.getAllPluginInfosFromDisk();
                    ConcurrentHashMap<String, PluginInfo> allPluginCached = instance.getAllPluginCached();
                    File dir = ShopApp.instance.getDir("plugin", 0);
                    File dir2 = ShopApp.instance.getDir(PluginSyncManager.PATH_DEXPLUGIN, 0);
                    File dir3 = ShopApp.instance.getDir(PluginSyncManager.PATH_FASTPLUGIN, 0);
                    File dir4 = ShopApp.instance.getDir(PluginSyncManager.PATH_PLUGINSIGNED, 0);
                    try {
                        dir.mkdir();
                        File[] listFiles = dir.listFiles();
                        HashSet hashSet = new HashSet();
                        Iterator<PluginInfo> it = allPluginInfosFromDisk.iterator();
                        while (it.hasNext()) {
                            hashSet.add(PluginSyncUtils.this.generatePathKey(it.next().localPath));
                        }
                        for (Map.Entry<String, PluginInfo> value : allPluginCached.entrySet()) {
                            hashSet.add(PluginSyncUtils.this.generatePathKey(((PluginInfo) value.getValue()).localPath));
                        }
                        for (File file : listFiles) {
                            if (!hashSet.contains(PluginSyncUtils.this.generatePathKey(file.getPath()))) {
                                String name = file.getName();
                                file.delete();
                                File file2 = new File(dir2.getAbsolutePath() + File.separator + name.replace(ArchiveStreamFactory.g, ShareConstants.q));
                                if (file2.exists()) {
                                    file2.delete();
                                }
                                File file3 = new File(dir3.getAbsolutePath() + File.separator + name.replace(ArchiveStreamFactory.g, ShareConstants.q));
                                if (file3.isFile()) {
                                    file3.delete();
                                }
                                File file4 = new File(dir3.getAbsolutePath() + File.separator + name.replace(ArchiveStreamFactory.g, "cfg"));
                                if (file4.isFile()) {
                                    file4.delete();
                                }
                                File file5 = new File(dir4.getAbsolutePath() + File.separator + name);
                                if (file5.exists()) {
                                    file5.delete();
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String generatePathKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(Device.PACKAGE);
        return indexOf == -1 ? str : str.substring(indexOf);
    }
}
