package com.xiaomi.smarthome.miio.camera.cloudstorage.model;

public class CloudVideoDownloadInfo {
    public static final int COMPLETE = 0;
    public static final int FAIL = 2;
    public static final int PAUSE = 8;
    public static final int PROGRESS = 1;
    public static final int READY = 4;
    public long createTime;
    public String createTimePretty;
    public String did;
    public long duration;
    public long endTime;
    public String endTimePretty;
    public String fileId;
    public int id;
    public boolean isSelected;
    public String m3u8FilePath;
    public String m3u8LocalPath;
    public String mp4FilePath;
    public int progress;
    public int size;
    public long startTime;
    public String startTimePretty;
    public int status;
    public String timezoneId;
    public String title;
    public String uid;
    public String videoUrl;

    public CloudVideoDownloadInfo() {
    }

    public CloudVideoDownloadInfo(String str, String str2, int i, long j, long j2, long j3) {
        this.uid = str;
        this.did = str2;
        this.startTime = j;
        this.endTime = j2;
        this.status = i;
        this.createTime = j3;
    }

    public CloudVideoDownloadInfo(String str, String str2, String str3, String str4, int i, long j, long j2, long j3) {
        this.uid = str;
        this.did = str2;
        this.videoUrl = str3;
        this.mp4FilePath = str4;
        this.startTime = j;
        this.endTime = j2;
        this.status = i;
        this.createTime = j3;
    }
}
