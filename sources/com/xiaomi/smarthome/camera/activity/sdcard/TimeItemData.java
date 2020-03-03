package com.xiaomi.smarthome.camera.activity.sdcard;

import com.mijia.model.local.LocalFileManager;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.mijia.model.sdcard.TimeItem;

public class TimeItemData {
    public DownloadSdCardManager.DownloadItem downloadItem;
    public LocalFileManager.LocalFile localFile;
    public String subTitle;
    public TimeItem timeItem;
    public String title;
}
