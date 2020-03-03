package com.xiaomi.mobilestats.upload;

import android.os.Handler;
import com.xiaomi.mobilestats.controller.LogController;
import com.xiaomi.mobilestats.data.ReadFromFileThead;
import java.io.File;

public class UploadManager {
    public static File[] getCacheFiles() {
        File[] listFiles;
        try {
            File file = new File(LogController.uploadFileDir);
            if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
                return null;
            }
            return listFiles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        r1 = r1.listFiles();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isHasCacheFile() {
        /*
            r0 = 0
            java.lang.String r1 = com.xiaomi.mobilestats.controller.LogController.uploadFileDir     // Catch:{ Exception -> 0x0028 }
            boolean r1 = com.xiaomi.mobilestats.common.StrUtil.isEmpty(r1)     // Catch:{ Exception -> 0x0028 }
            if (r1 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = com.xiaomi.mobilestats.controller.LogController.uploadFileDir     // Catch:{ Exception -> 0x0028 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0028 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x002c
            boolean r2 = r1.isDirectory()     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x002c
            java.io.File[] r1 = r1.listFiles()     // Catch:{ Exception -> 0x0028 }
            if (r1 == 0) goto L_0x002c
            int r1 = r1.length     // Catch:{ Exception -> 0x0028 }
            if (r1 <= 0) goto L_0x002c
            r0 = 1
            return r0
        L_0x0028:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.upload.UploadManager.isHasCacheFile():boolean");
    }

    public static void uploadCachedUploadFiles(Handler handler) {
        try {
            File[] cacheFiles = getCacheFiles();
            if (cacheFiles != null && cacheFiles.length > 0) {
                for (File file : cacheFiles) {
                    if (file != null && file.exists()) {
                        ReadFromFileThead readFromFileThead = new ReadFromFileThead(file.getPath());
                        if (handler != null) {
                            handler.post(readFromFileThead);
                        } else {
                            new Handler().post(readFromFileThead);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
