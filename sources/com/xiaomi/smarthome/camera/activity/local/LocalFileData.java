package com.xiaomi.smarthome.camera.activity.local;

import android.net.Uri;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.SDKLog;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.io.File;

public class LocalFileData {
    private static final String TAG = "AlbumActivity";
    public static int timelapseSaveType = 1;
    public String displayImgPath = "";
    public String durationInStr = "";
    public long endTimeInSec = -1;
    public String imgPath = "";
    public LocalFileManager.LocalFile item;
    public long realStartTimeInSec;
    public int recordStatus = 0;
    public long startTimeInSec = -1;
    public String subTitle;
    private int timezoneInMunites;
    public String title;
    public File videoFile = null;
    public String videoPath = "";
    public int videoType = 0;

    public void copyTimeLapse(TimelapseTask timelapseTask, MijiaCameraDevice mijiaCameraDevice) {
        if (timelapseTask != null) {
            this.startTimeInSec = timelapseTask.getStartTimestampInUTCSeconds();
            this.endTimeInSec = timelapseTask.getEndTimestampInUTCSeconds();
            this.videoType = 3;
            this.recordStatus = timelapseTask.status;
            this.videoFile = timelapseTask.getVideoFile("mp4");
            this.imgPath = timelapseTask.getLatestPicPath();
            if (this.recordStatus == 3 && this.videoFile != null) {
                this.durationInStr = getLocalVideoDurationInStr(this.videoFile.getPath(), "00:01");
            }
            this.displayImgPath = Uri.fromFile(new File(timelapseTask.getLatestPicPath())).toString();
            this.timezoneInMunites = timelapseTask.getTimezoneInMinutes();
            this.realStartTimeInSec = this.startTimeInSec + ((long) (this.timezoneInMunites * 60));
            this.videoPath = timelapseTask.getVideoPath(mijiaCameraDevice);
            SDKLog.b(TAG, "copyTimeLapse duration=" + this.durationInStr);
            LogUtil.a(TAG, "copyTimeLapse: tt.taskID=" + timelapseTask.getTaskID());
            LogUtil.a(TAG, "copyTimeLapse: imgPath=" + this.imgPath);
            LogUtil.a(TAG, "copyTimeLapse: recordStatus=" + this.recordStatus);
        }
    }

    public LocalFileData() {
    }

    public LocalFileData(TimelapseTask timelapseTask, MijiaCameraDevice mijiaCameraDevice) {
        if (timelapseTask != null) {
            this.startTimeInSec = timelapseTask.getStartTimestampInUTCSeconds();
            this.endTimeInSec = timelapseTask.getEndTimestampInUTCSeconds();
            this.videoType = 3;
            this.recordStatus = timelapseTask.status;
            this.videoFile = timelapseTask.getVideoFile("mp4");
            this.imgPath = timelapseTask.getLatestPicPath();
            this.displayImgPath = Uri.fromFile(new File(timelapseTask.getLatestPicPath())).toString();
            if (this.recordStatus == 3 && this.videoFile != null) {
                this.durationInStr = getLocalVideoDurationInStr(this.videoFile.getPath(), "00:01");
            }
            this.timezoneInMunites = timelapseTask.getTimezoneInMinutes();
            this.realStartTimeInSec = this.startTimeInSec + ((long) (this.timezoneInMunites * 60));
            this.videoPath = timelapseTask.getVideoPath(mijiaCameraDevice);
            LogUtil.a(TAG, "RecordItem TimelapseTask duration=" + this.durationInStr + "=====tt.status====" + timelapseTask.status);
            StringBuilder sb = new StringBuilder();
            sb.append("RecordItem: tt-taskID=");
            sb.append(timelapseTask.getTaskID());
            LogUtil.a(TAG, sb.toString());
            LogUtil.a(TAG, "RecordItem: displayImgPath=" + this.displayImgPath);
            LogUtil.a(TAG, "RecordItem: videoFile=" + this.videoFile);
            LogUtil.a(TAG, "RecordItem: startTimeInSec=" + this.startTimeInSec);
            LogUtil.a(TAG, "RecordItem: timezoneInMunites=" + this.timezoneInMunites);
            LogUtil.a(TAG, "RecordItem: realStartTimeInSec=" + this.realStartTimeInSec);
            LogUtil.a(TAG, "RecordItem: videoPath=" + this.videoPath);
            LogUtil.a(TAG, "RecordItem: recordStatus=" + this.recordStatus);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getLocalVideoDurationInStr(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0009
            java.lang.String r4 = "00:00"
            return r4
        L_0x0009:
            android.media.MediaMetadataRetriever r0 = new android.media.MediaMetadataRetriever
            r0.<init>()
            java.lang.String r1 = "0"
            r0.setDataSource(r4)     // Catch:{ Exception -> 0x001f }
            r4 = 9
            java.lang.String r4 = r0.extractMetadata(r4)     // Catch:{ Exception -> 0x001f }
            r0.release()     // Catch:{ Exception -> 0x001d }
            goto L_0x0024
        L_0x001d:
            r0 = move-exception
            goto L_0x0021
        L_0x001f:
            r0 = move-exception
            r4 = r1
        L_0x0021:
            r0.printStackTrace()
        L_0x0024:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x0093
            long r4 = java.lang.Long.parseLong(r4)
            r0 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r0
            int r4 = (int) r4
            int r5 = r4 / 60
            int r0 = r5 * 60
            int r4 = r4 - r0
            int r4 = r4 % 60
            r0 = 10
            if (r5 < r0) goto L_0x004f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            java.lang.String r5 = ""
            r1.append(r5)
        L_0x004a:
            java.lang.String r5 = r1.toString()
            goto L_0x005d
        L_0x004f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "0"
            r1.append(r2)
            r1.append(r5)
            goto L_0x004a
        L_0x005d:
            if (r4 < r0) goto L_0x0071
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = ""
            r0.append(r4)
        L_0x006c:
            java.lang.String r4 = r0.toString()
            goto L_0x007f
        L_0x0071:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "0"
            r0.append(r1)
            r0.append(r4)
            goto L_0x006c
        L_0x007f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = ":"
            r0.append(r5)
            r0.append(r4)
            java.lang.String r5 = r0.toString()
        L_0x0093:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.local.LocalFileData.getLocalVideoDurationInStr(java.lang.String, java.lang.String):java.lang.String");
    }

    public long getStartTimeInSec() {
        return this.startTimeInSec;
    }

    public int getVideoType() {
        return this.videoType;
    }

    public void setVideoType(int i) {
        if (i <= 0) {
            i = 0;
        }
        this.videoType = i;
    }
}
