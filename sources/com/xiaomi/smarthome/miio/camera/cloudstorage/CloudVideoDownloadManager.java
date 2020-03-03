package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoM3U8DownloadManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CloudVideoDownloadManager {
    private static final int PROGRESS_FINISH = 100;
    private static final String TAG = "CloudVideoDownloadManager";
    private static CloudVideoDownloadManager instance;
    private CloudVideoM3U8DownloadManager cloudVideoM3U8DownloadManager;
    private CloudVideoDownloadInfo info;
    public boolean isDownloading;
    /* access modifiers changed from: private */
    public List<ICloudVideoDownloadListener> listeners = new ArrayList();

    public interface ICloudVideoDownloadListener {
        void onCancelled(CloudVideoDownloadInfo cloudVideoDownloadInfo);

        void onError(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i);

        void onFinish(CloudVideoDownloadInfo cloudVideoDownloadInfo);

        void onInfo(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i);

        void onProgress(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i);

        void onSpeed(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i);

        void onStart(CloudVideoDownloadInfo cloudVideoDownloadInfo);

        void onStop(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i);
    }

    private void updateStatus(boolean z) {
    }

    public void startDownload() {
    }

    private CloudVideoDownloadManager() {
    }

    public static CloudVideoDownloadManager getInstance() {
        if (instance == null) {
            synchronized (CloudVideoDownloadManager.class) {
                if (instance == null) {
                    instance = new CloudVideoDownloadManager();
                }
            }
        }
        return instance;
    }

    public void addListener(ICloudVideoDownloadListener iCloudVideoDownloadListener) {
        LogUtil.a(TAG, "add listener");
        if (this.listeners.indexOf(iCloudVideoDownloadListener) < 0) {
            this.listeners.add(iCloudVideoDownloadListener);
        }
    }

    public void removeListener(ICloudVideoDownloadListener iCloudVideoDownloadListener) {
        LogUtil.a(TAG, "remove listener");
        this.listeners.remove(iCloudVideoDownloadListener);
    }

    public void resetStatus(String str, String str2) {
        List<CloudVideoDownloadInfo> records = CloudVideoDownloadDBManager.getInstance().getRecords(str, str2);
        if (records != null && records.size() > 0) {
            for (CloudVideoDownloadInfo next : records) {
                if (next.status == 1) {
                    next.status = 4;
                    updateRecord(next);
                }
            }
        }
    }

    public void insertRecords(List<CloudVideoDownloadInfo> list) {
        CloudVideoDownloadDBManager.getInstance().insertRecords(list);
    }

    public void updateRecord(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
    }

    public CloudVideoDownloadInfo getRecord(int i) {
        return CloudVideoDownloadDBManager.getInstance().getRecord(i);
    }

    public List<CloudVideoDownloadInfo> getRecords(String str, String str2) {
        return CloudVideoDownloadDBManager.getInstance().getRecords(str, str2);
    }

    public void deleteRecords(List<CloudVideoDownloadInfo> list) {
        if (list != null && list.size() > 0) {
            for (CloudVideoDownloadInfo next : list) {
                if (next.status == 1) {
                    cancelCurrentDownload(next.did, next.fileId);
                }
                deleteLocalDir(next.m3u8LocalPath);
            }
            CloudVideoDownloadDBManager.getInstance().deleteRecords(list);
        }
    }

    public void cancelCurrentDownload(String str, String str2) {
        if (this.cloudVideoM3U8DownloadManager != null && this.cloudVideoM3U8DownloadManager.cancelDownload(str, str2)) {
            this.isDownloading = false;
        }
    }

    private void deleteLocalDir(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.isDirectory()) {
                CloudVideoFileUtils.RecursionDeleteFile(file);
            }
        }
    }

    private void m3u8Download(Context context, CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        if (context == null || TextUtils.isEmpty(cloudVideoDownloadInfo.videoUrl)) {
            this.isDownloading = false;
            startDownloadFromList(context, cloudVideoDownloadInfo.uid, cloudVideoDownloadInfo.did);
            return;
        }
        this.isDownloading = true;
        CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
        doDownload(context, cloudVideoDownloadInfo);
    }

    private void doDownload(final Context context, final CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        if (!TextUtils.isEmpty(cloudVideoDownloadInfo.fileId)) {
            this.cloudVideoM3U8DownloadManager = new CloudVideoM3U8DownloadManager();
            this.cloudVideoM3U8DownloadManager.DownloadM3U8(cloudVideoDownloadInfo.did, cloudVideoDownloadInfo.fileId, cloudVideoDownloadInfo.videoUrl, new ICloudDataCallback<List<String>>() {
                public void onCloudDataSuccess(List<String> list, Object obj) {
                    CloudVideoDownloadManager.this.isDownloading = false;
                    cloudVideoDownloadInfo.progress = 100;
                    cloudVideoDownloadInfo.status = 0;
                    cloudVideoDownloadInfo.m3u8LocalPath = (String) obj;
                    LogUtil.a(CloudVideoDownloadManager.TAG, "onCloudDataSuccess:" + cloudVideoDownloadInfo.m3u8LocalPath);
                    CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
                    for (ICloudVideoDownloadListener onFinish : CloudVideoDownloadManager.this.listeners) {
                        onFinish.onFinish(cloudVideoDownloadInfo);
                    }
                    CloudVideoDownloadManager.this.startDownloadFromList(context, cloudVideoDownloadInfo.uid, cloudVideoDownloadInfo.did);
                }

                public void onCloudDataFailed(int i, String str) {
                    CloudVideoDownloadManager.this.isDownloading = false;
                    cloudVideoDownloadInfo.progress = 0;
                    if (i == 100) {
                        cloudVideoDownloadInfo.status = 8;
                        CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
                        LogUtil.b(CloudVideoDownloadManager.TAG, "onCloudDataFailed paused:" + i);
                    } else {
                        cloudVideoDownloadInfo.status = 2;
                        CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
                        LogUtil.b(CloudVideoDownloadManager.TAG, "onCloudDataFailed:" + i);
                    }
                    CloudVideoDownloadManager.this.startDownloadFromList(context, cloudVideoDownloadInfo.uid, cloudVideoDownloadInfo.did);
                }

                public void onCloudDataProgress(int i) {
                    cloudVideoDownloadInfo.progress = i;
                    cloudVideoDownloadInfo.status = 1;
                    CloudVideoDownloadDBManager.getInstance().updateRecord(cloudVideoDownloadInfo);
                    for (ICloudVideoDownloadListener onProgress : CloudVideoDownloadManager.this.listeners) {
                        onProgress.onProgress(cloudVideoDownloadInfo, i);
                    }
                }
            });
            return;
        }
        this.isDownloading = false;
        startDownloadFromList(context, cloudVideoDownloadInfo.uid, cloudVideoDownloadInfo.did);
    }

    /* access modifiers changed from: private */
    public void startDownloadFromList(Context context, String str, String str2) {
        List<CloudVideoDownloadInfo> records = CloudVideoDownloadDBManager.getInstance().getRecords(str, str2);
        if (records != null && records.size() > 0) {
            for (CloudVideoDownloadInfo next : records) {
                if (next.status == 4 && !this.isDownloading && !TextUtils.isEmpty(next.fileId)) {
                    m3u8Download(context, next);
                    return;
                }
            }
        }
    }

    private void startDownloadAll(Context context) {
        List<CloudVideoDownloadInfo> records = CloudVideoDownloadDBManager.getInstance().getRecords(4);
        if (records != null && records.size() > 0) {
            for (CloudVideoDownloadInfo next : records) {
                if (next.status == 4 && !this.isDownloading && !TextUtils.isEmpty(next.fileId)) {
                    m3u8Download(context, next);
                    return;
                }
            }
        }
    }

    public List<CloudVideoDownloadInfo> getRecordsFromDB(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return CloudVideoDownloadDBManager.getInstance().getRecords(str, str2);
    }

    public void pullDownloadFromList(Context context, String str, String str2) {
        if (context != null) {
            startDownloadAll(context);
        }
    }
}
