package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask;
import java.util.List;

public class CloudVideoM3U8DownloadManager {
    private static final String TAG = "CloudVideoM3U8DownloadManager";
    private String did;
    private String fileId;
    private M3U8DownloadAndDecryptTask m3U8DownloadAndDecryptTask = null;

    public void DownloadM3U8(String str, String str2, String str3, final ICloudDataCallback<List<String>> iCloudDataCallback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            this.did = str;
            this.fileId = str2;
            this.m3U8DownloadAndDecryptTask = new M3U8DownloadAndDecryptTask(new M3U8DownloadAndDecryptTask.IFileDownloadCallback() {
                public void onSuccess(List<String> list, String str) {
                    LogUtil.a(CloudVideoM3U8DownloadManager.TAG, "DownloadM3U8:success:" + str);
                    if (iCloudDataCallback != null) {
                        iCloudDataCallback.onCloudDataSuccess(list, str);
                    }
                }

                public void onFailure(int i) {
                    LogUtil.a(CloudVideoM3U8DownloadManager.TAG, "DownloadM3U8:fail:" + i);
                    if (iCloudDataCallback != null) {
                        ICloudDataCallback iCloudDataCallback = iCloudDataCallback;
                        iCloudDataCallback.onCloudDataFailed(i, "DownloadM3U8:fail:" + i);
                    }
                }

                public void onProgress(int i) {
                    if (iCloudDataCallback != null) {
                        iCloudDataCallback.onCloudDataProgress(i);
                    }
                }
            });
            this.m3U8DownloadAndDecryptTask.execute(new String[]{str3, str, str2});
        }
    }

    public void cancelDownload() {
        if (this.m3U8DownloadAndDecryptTask != null && !this.m3U8DownloadAndDecryptTask.isCancelled()) {
            this.m3U8DownloadAndDecryptTask.cancel(true);
        }
    }

    public boolean cancelDownload(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(this.did) || !str2.equals(this.fileId) || this.m3U8DownloadAndDecryptTask == null || this.m3U8DownloadAndDecryptTask.isCancelled()) {
            return false;
        }
        this.m3U8DownloadAndDecryptTask.cancel(true);
        return true;
    }
}
