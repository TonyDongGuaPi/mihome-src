package com.xiaomi.mishopsdk.plugin;

import android.os.SystemClock;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PluginDownloader {
    private static final String TAG = "PluginDownloader";
    public static PluginDownloader instance;
    private final int MSG_UPDATEPLUGININFO = 101;
    HashMap<String, PluginInfo> curDownloadingMap = new HashMap<>();
    protected boolean isDownloading = false;
    private PriorityQueue<PluginInfo> vDownloadPlugins = new PriorityQueue<>(100, new PluginPriorityComparator());

    public static PluginDownloader getInstance() {
        if (instance == null) {
            instance = new PluginDownloader();
        }
        return instance;
    }

    public static class PluginPriorityComparator implements Comparator<PluginInfo> {
        private static String sCurUsedPluginId;

        public static synchronized void setCurUsedPluginId(String str) {
            synchronized (PluginPriorityComparator.class) {
                sCurUsedPluginId = str;
            }
        }

        public int compare(PluginInfo pluginInfo, PluginInfo pluginInfo2) {
            if (pluginInfo.id.equals(pluginInfo2.id)) {
                return 0;
            }
            if (pluginInfo.id.equals(sCurUsedPluginId)) {
                return -1000;
            }
            if (pluginInfo2.id.equals(sCurUsedPluginId)) {
                return 1000;
            }
            return pluginInfo.priority - pluginInfo2.priority;
        }
    }

    public void addPriorityQueueBy(PluginInfo pluginInfo) {
        if (!TextUtils.isEmpty(pluginInfo.id)) {
            PluginInfo pluginInfo2 = this.curDownloadingMap.get(pluginInfo.id);
            if (pluginInfo2 == null || !pluginInfo2.version.equals(pluginInfo.version)) {
                removePriorityQueueById(pluginInfo.id);
                this.vDownloadPlugins.add(pluginInfo);
            }
        }
    }

    public boolean removePriorityQueueById(String str) {
        PluginInfo next;
        Iterator<PluginInfo> it = this.vDownloadPlugins.iterator();
        boolean z = false;
        while (it.hasNext() && (next = it.next()) != null) {
            if (next.id.equals(str)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public void freshDownloadQueue() {
        if (this.vDownloadPlugins != null && !this.vDownloadPlugins.isEmpty() && !this.isDownloading) {
            downloadImmediately(this.vDownloadPlugins.peek());
        }
    }

    public void clearDownloadQueue() {
        if (this.vDownloadPlugins != null) {
            this.vDownloadPlugins.clear();
        }
    }

    public synchronized void downloadImmediately(PluginInfo pluginInfo) {
        if (this.vDownloadPlugins != null && !this.curDownloadingMap.containsKey(pluginInfo.id)) {
            removePriorityQueueById(pluginInfo.id);
            downloadPlugin(pluginInfo);
        }
    }

    public void createPluginLocalPath(PluginInfo pluginInfo) {
        String str;
        File dir = ShopApp.instance.getDir("plugin", 0);
        dir.mkdir();
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(pluginInfo.md5)) {
            str = "";
        } else {
            str = pluginInfo.md5 + JSMethod.NOT_SET;
        }
        sb.append(str);
        sb.append(StringUtils.md5(pluginInfo.id + pluginInfo.version + SystemClock.elapsedRealtime()));
        pluginInfo.localPath = dir.getAbsolutePath() + File.separator + sb.toString() + ".zip";
    }

    public static String getFileMD5(File file) {
        if (file == null || !file.isFile()) {
            return "";
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance2 = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance2.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return String.format("%32s", new Object[]{new BigInteger(1, instance2.digest()).toString(16)}).replace(' ', '0');
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void downloadPlugin(final com.xiaomi.mishopsdk.plugin.lib.PluginInfo r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r9 == 0) goto L_0x0027
            java.lang.String r0 = r9.id     // Catch:{ all -> 0x0024 }
            boolean r0 = com.xiaomi.mishopsdk.utils.StringUtils.isEmpty(r0)     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x000c
            goto L_0x0027
        L_0x000c:
            r8.createPluginLocalPath(r9)     // Catch:{ all -> 0x0024 }
            com.xiaomi.mishopsdk.utils.PluginQueueManager r1 = com.xiaomi.mishopsdk.utils.PluginQueueManager.getQueueInstance()     // Catch:{ all -> 0x0024 }
            java.lang.String r3 = r9.path     // Catch:{ all -> 0x0024 }
            r4 = 0
            java.lang.String r5 = r9.localPath     // Catch:{ all -> 0x0024 }
            r6 = 0
            com.xiaomi.mishopsdk.plugin.PluginDownloader$1 r7 = new com.xiaomi.mishopsdk.plugin.PluginDownloader$1     // Catch:{ all -> 0x0024 }
            r7.<init>(r9)     // Catch:{ all -> 0x0024 }
            r2 = r8
            r1.downLoadFile(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0024 }
            monitor-exit(r8)
            return
        L_0x0024:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        L_0x0027:
            monitor-exit(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.plugin.PluginDownloader.downloadPlugin(com.xiaomi.mishopsdk.plugin.lib.PluginInfo):void");
    }
}
