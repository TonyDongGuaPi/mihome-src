package com.xiaomi.smarthome.camera.activity.timelapse;

import android.text.TextUtils;
import com.Utils.FileUtil;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.SDKLog;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.connection.ByteOperator;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TimelapseTask {
    public static final int CREATE = 0;
    public static final int DELETE = 2;
    public static final String FILE_TYPE_H264 = "h264";
    public static final String FILE_TYPE_MP4 = "mp4";
    public static final int STOP_AND_SAVE = 1;
    private static final String TAG = AlbumActivity.class.getSimpleName();
    public static final int TIMELAPSE_IN_PHONE = 0;
    public static final int TIMELAPSE_IN_SD = 1;
    private String cameraMac;
    private int creatStatus;
    private int endTimestampInSeconds;
    private int fileStatus;
    private int intervalToTakePicture;
    public boolean isCreator;
    private MijiaCameraDevice mCameraDevice;
    private String mDid;
    public File picFolder;
    public File rootFolder;
    private int startTimestampInSeconds;
    public long startTimestampInUTCSeconds;
    public int status;
    private int taskID;
    private String taskName;
    private int timelapseSaveType;
    private int timezoneInMunites;
    public File videoFolder;
    private int videoType;

    public TimelapseTask(MijiaCameraDevice mijiaCameraDevice, long j, long j2, long j3, int i, int i2) {
        this.cameraMac = "";
        this.taskName = "";
        this.isCreator = true;
        this.creatStatus = 0;
        this.status = 0;
        this.timelapseSaveType = 1;
        this.fileStatus = 1;
        this.videoType = 3;
        this.mCameraDevice = mijiaCameraDevice;
        this.mDid = mijiaCameraDevice.getDid();
        this.cameraMac = shortMac(mijiaCameraDevice.getMac());
        setTaskID(j);
        setTaskName(j + "");
        setStartTimestampInSeconds(j2);
        setEndTimestampInSeconds(j3);
        setIntervalToTakePicture(i);
        setTimezoneInMinutes(i2);
    }

    public TimelapseTask(String str, long j) {
        this.cameraMac = "";
        this.taskName = "";
        this.isCreator = true;
        this.creatStatus = 0;
        this.status = 0;
        this.timelapseSaveType = 1;
        this.fileStatus = 1;
        this.videoType = 3;
        this.timelapseSaveType = 0;
        setTaskID(j);
        setTaskName(str);
    }

    public TimelapseTask(int i, int i2, long j) {
        this.cameraMac = "";
        this.taskName = "";
        this.isCreator = true;
        this.creatStatus = 0;
        this.status = 0;
        this.timelapseSaveType = 1;
        this.fileStatus = 1;
        this.videoType = 3;
        this.videoType = i;
        this.startTimestampInSeconds = i2;
        this.startTimestampInUTCSeconds = j;
    }

    public TimelapseTask(int i, int i2) {
        this.cameraMac = "";
        this.taskName = "";
        this.isCreator = true;
        this.creatStatus = 0;
        this.status = 0;
        this.timelapseSaveType = 1;
        this.fileStatus = 1;
        this.videoType = 3;
        this.videoType = i;
        this.startTimestampInSeconds = i2;
    }

    public int getTimelapseSaveType() {
        return this.timelapseSaveType;
    }

    public void setTimelapseSaveType(int i) {
        this.timelapseSaveType = i;
    }

    public void setStartTimestampInSeconds(long j) {
        if (j < 2147483647L) {
            this.startTimestampInSeconds = (int) j;
        } else {
            this.startTimestampInSeconds = (int) (j / 1000);
        }
        String str = TAG;
        SDKLog.b(str, "setStartTimestampInSeconds startTimestampInSeconds=" + j);
    }

    public int getStartTimestampInSeconds() {
        return this.startTimestampInSeconds;
    }

    public long getRealStartTimeInSec() {
        return getStartTimestampInUTCSeconds() + ((long) (this.timezoneInMunites * 60));
    }

    public void setEndTimestampInSeconds(long j) {
        if (j < 2147483647L) {
            this.endTimestampInSeconds = (int) j;
        } else {
            this.endTimestampInSeconds = (int) (j / 1000);
        }
    }

    public int getEndTimestampInSeconds() {
        return this.endTimestampInSeconds;
    }

    public void setTimezoneInMinutes(int i) {
        this.timezoneInMunites = i;
    }

    public int getTimezoneInMinutes() {
        return this.timezoneInMunites;
    }

    public void setTaskName(String str) {
        if (str == null || str.equals("")) {
            this.taskName = "" + this.taskID;
            return;
        }
        if (str.length() > 10) {
            str = str.substring(0, 10);
        }
        this.taskName = str;
    }

    public String getTaskName() {
        if (this.taskName == null || this.taskName.equals("")) {
            this.taskName = "" + this.taskID;
        }
        return this.taskName;
    }

    public void setTaskID(long j) {
        if (j > 9999999999L) {
            this.taskID = (int) (j / 1000);
        } else {
            this.taskID = (int) j;
        }
    }

    public int getTaskID() {
        return this.taskID;
    }

    private String shortMac(String str) {
        String replaceAll = str == null ? "" : str.replaceAll(":", "");
        return replaceAll.length() > 3 ? replaceAll.substring(replaceAll.length() - 3) : replaceAll;
    }

    public String getCameraMac() {
        return this.cameraMac;
    }

    public void setIntervalToTakePicture(int i) {
        if (i < 1 || i > 86400) {
            this.intervalToTakePicture = 1;
        } else {
            this.intervalToTakePicture = i;
        }
    }

    public int getIntervalToTakePicture() {
        return this.intervalToTakePicture;
    }

    public byte[] toProtocolByteArray() {
        byte[] bArr = new byte[20];
        ByteOperator.a(bArr, 0, ByteOperator.a(this.taskID), 0, 3);
        ByteOperator.a(bArr, 4, ByteOperator.a(this.startTimestampInSeconds), 0, 3);
        ByteOperator.a(bArr, 8, ByteOperator.a(this.endTimestampInSeconds), 0, 3);
        ByteOperator.a(bArr, 12, ByteOperator.a(this.intervalToTakePicture), 0, 3);
        ByteOperator.a(bArr, 16, ByteOperator.a(this.timezoneInMunites), 0, 3);
        return bArr;
    }

    public static byte[] getEmpty() {
        byte[] bArr = new byte[20];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = 0;
        }
        return bArr;
    }

    public long getStartTimestampInUTCSeconds() {
        return (long) (this.startTimestampInSeconds - (this.timezoneInMunites * 60));
    }

    public long getEndTimestampInUTCSeconds() {
        return (long) (this.endTimestampInSeconds - (this.timezoneInMunites * 60));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readLogFile(com.mijia.camera.MijiaCameraDevice r11) throws java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = r11.getDid()
            r10.mDid = r0
            r10.mCameraDevice = r11
            java.lang.String r11 = TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "==========getCurrentTaskFilePath========="
            r0.append(r1)
            java.lang.String r1 = r10.mDid
            int r2 = r10.taskID
            java.lang.String r3 = r10.taskName
            java.lang.String r1 = com.Utils.FileUtil.a((java.lang.String) r1, (int) r2, (java.lang.String) r3)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.mijia.debug.SDKLog.b(r11, r0)
            java.io.File r11 = new java.io.File
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r10.mDid
            int r2 = r10.taskID
            java.lang.String r3 = r10.taskName
            java.lang.String r1 = com.Utils.FileUtil.a((java.lang.String) r1, (int) r2, (java.lang.String) r3)
            r0.append(r1)
            java.lang.String r1 = "log.txt"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            boolean r0 = r11.exists()
            if (r0 != 0) goto L_0x0057
            java.lang.String r11 = TAG
            java.lang.String r0 = "readLogFile log file dose not exsit"
            com.mijia.debug.SDKLog.b(r11, r0)
            r11 = -1
            return r11
        L_0x0057:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r11)
            int r11 = r0.available()
            byte[] r11 = new byte[r11]
            r0.read(r11)
            java.lang.String r1 = new java.lang.String
            java.lang.String r2 = "UTF-8"
            r1.<init>(r11, r2)
            java.lang.String r11 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "readLogFile log file res= "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.mijia.debug.SDKLog.b(r11, r2)
            r11 = 0
            java.lang.String r2 = "_"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r11 = TAG     // Catch:{ Exception -> 0x00a1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a1 }
            r2.<init>()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = "result =="
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            int r3 = r1.length     // Catch:{ Exception -> 0x00a1 }
            r2.append(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00a1 }
            com.mijia.debug.SDKLog.b(r11, r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00aa
        L_0x00a1:
            r11 = move-exception
            goto L_0x00a7
        L_0x00a3:
            r1 = move-exception
            r9 = r1
            r1 = r11
            r11 = r9
        L_0x00a7:
            r11.printStackTrace()
        L_0x00aa:
            r11 = 3
            r2 = 5
            r3 = 4
            r4 = 2
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L_0x00f1
            int r7 = r1.length
            if (r7 != r2) goto L_0x00f1
            r2 = r1[r6]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.startTimestampInSeconds = r2
            r2 = r1[r5]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.endTimestampInSeconds = r2
            r2 = r1[r4]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.intervalToTakePicture = r2
            r11 = r1[r11]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.timezoneInMunites = r11
            r11 = r1[r3]
            java.lang.String r1 = "1"
            boolean r11 = r11.equals(r1)
            r10.isCreator = r11
            goto L_0x0180
        L_0x00f1:
            if (r1 == 0) goto L_0x0129
            int r7 = r1.length
            if (r7 != r3) goto L_0x0129
            r2 = r1[r6]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.startTimestampInSeconds = r2
            r2 = r1[r5]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.endTimestampInSeconds = r2
            r2 = r1[r4]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r10.intervalToTakePicture = r2
            r11 = r1[r11]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.timezoneInMunites = r11
            r10.isCreator = r5
            goto L_0x0180
        L_0x0129:
            if (r1 == 0) goto L_0x0180
            int r11 = r1.length
            r7 = 6
            if (r11 != r7) goto L_0x0180
            java.lang.String r11 = TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "readLogFile log file broken, length= "
            r7.append(r8)
            int r8 = r1.length
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.mijia.debug.SDKLog.b(r11, r7)
            r11 = r1[r6]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.startTimestampInSeconds = r11
            r11 = r1[r5]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.endTimestampInSeconds = r11
            r11 = r1[r4]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.intervalToTakePicture = r11
            r11 = r1[r3]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r11 = r11.intValue()
            r10.timezoneInMunites = r11
            r11 = r1[r2]
            java.lang.String r1 = "1"
            boolean r11 = r11.equals(r1)
            r10.isCreator = r11
        L_0x0180:
            r0.close()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask.readLogFile(com.mijia.camera.MijiaCameraDevice):int");
    }

    public void writeLogFile(boolean z) throws IOException {
        File file = new File(FileUtil.a(this.mDid, this.taskID, this.taskName) + "log.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        String str = TAG;
        SDKLog.b(str, "writeLogFile file path=" + file.getAbsolutePath());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StringBuilder sb = new StringBuilder();
        sb.append(this.startTimestampInSeconds);
        sb.append(JSMethod.NOT_SET);
        sb.append(this.endTimestampInSeconds);
        sb.append(JSMethod.NOT_SET);
        sb.append(this.intervalToTakePicture);
        sb.append(JSMethod.NOT_SET);
        sb.append(this.timezoneInMunites);
        sb.append(JSMethod.NOT_SET);
        sb.append(z ? "1" : "0");
        String sb2 = sb.toString();
        String str2 = TAG;
        SDKLog.b(str2, "writeLogFile " + sb2);
        fileOutputStream.write(sb2.getBytes());
        fileOutputStream.close();
    }

    public void reWriteEndtime(long j) throws IOException {
        SDKLog.b(TAG, "reWriteEndtime=" + j + "  getCurrentTaskFilePath()=" + FileUtil.a(this.mDid, this.taskID, this.taskName));
        StringBuilder sb = new StringBuilder();
        sb.append(FileUtil.a(this.mDid, this.taskID, this.taskName));
        sb.append("log.txt");
        File file = new File(sb.toString());
        long j2 = j + ((long) (this.timezoneInMunites * 60));
        if (!file.exists()) {
            file.createNewFile();
        }
        SDKLog.b(TAG, "writeLogFile file path=" + file.getAbsolutePath());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String str = String.valueOf(this.startTimestampInSeconds) + JSMethod.NOT_SET + String.valueOf(j2) + JSMethod.NOT_SET + String.valueOf(this.intervalToTakePicture) + JSMethod.NOT_SET + String.valueOf(this.timezoneInMunites) + "_1";
        SDKLog.b(TAG, "writeLogFile " + str);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }

    public void createLocalFolder(boolean z, boolean z2, MijiaCameraDevice mijiaCameraDevice) {
        this.mCameraDevice = mijiaCameraDevice;
        this.mDid = mijiaCameraDevice.getDid();
        if (TextUtils.isEmpty(this.taskName)) {
            setTaskName(this.taskID + "");
        }
        String str = TAG;
        SDKLog.b(str, "createLocalFolder ==taskID =" + this.taskID + ",taskName=" + this.taskName + "mDid,=" + this.mDid);
        String a2 = FileUtil.a(this.mDid, this.taskID, this.taskName);
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("createLocalFolder filePath=");
        sb.append(a2);
        SDKLog.b(str2, sb.toString());
        if (!"".equals(a2)) {
            try {
                this.rootFolder = new File(a2);
                if (!this.rootFolder.exists()) {
                    this.rootFolder.mkdirs();
                }
                this.picFolder = new File(this.rootFolder.getPath() + "/pic/");
                if (!this.picFolder.exists()) {
                    this.picFolder.mkdirs();
                }
                this.videoFolder = new File(this.rootFolder.getPath() + "/video/");
                if (!this.videoFolder.exists()) {
                    this.videoFolder.mkdirs();
                }
                if (z) {
                    writeLogFile(z2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String str3 = TAG;
                SDKLog.b(str3, "createLocalData exception: " + e.getMessage());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e5, code lost:
        r8.status = 3;
        com.mijia.debug.SDKLog.c(TAG, "found mp4 file");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refreshState() {
        /*
            r8 = this;
            long r0 = r8.getStartTimestampInUTCSeconds()
            long r2 = java.lang.System.currentTimeMillis()
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r0 = 0
            if (r6 <= 0) goto L_0x0014
            r8.status = r0
            goto L_0x00f5
        L_0x0014:
            long r1 = r8.getEndTimestampInUTCSeconds()
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 / r4
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x0026
            r0 = 1
            r8.status = r0
            goto L_0x00f5
        L_0x0026:
            long r1 = r8.getStartTimestampInUTCSeconds()
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00f3
            long r1 = r8.getEndTimestampInUTCSeconds()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x003a
            goto L_0x00f3
        L_0x003a:
            r1 = 2
            r8.status = r1
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "FileUtil.generateTimeLapseFilepath(mDid,taskID,taskName) + \"video/\")="
            r2.append(r3)
            java.lang.String r3 = r8.mDid
            int r4 = r8.taskID
            java.lang.String r5 = r8.taskName
            java.lang.String r3 = com.Utils.FileUtil.a((java.lang.String) r3, (int) r4, (java.lang.String) r5)
            r2.append(r3)
            java.lang.String r3 = "video/"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.mijia.debug.SDKLog.b(r1, r2)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r8.mDid
            int r4 = r8.taskID
            java.lang.String r5 = r8.taskName
            java.lang.String r3 = com.Utils.FileUtil.a((java.lang.String) r3, (int) r4, (java.lang.String) r5)
            r2.append(r3)
            java.lang.String r3 = "video/"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.io.File[] r1 = r1.listFiles()
            if (r1 == 0) goto L_0x00f5
            int r2 = r1.length
        L_0x0089:
            if (r0 >= r2) goto L_0x00f5
            r3 = r1[r0]
            java.lang.String r4 = TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "refreshState videoFile =="
            r5.append(r6)
            java.lang.String r6 = r3.getAbsolutePath()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.mijia.debug.SDKLog.b(r4, r5)
            java.lang.String r4 = TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "refreshState videoFile.getName() =="
            r5.append(r6)
            java.lang.String r6 = r3.getName()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.mijia.debug.SDKLog.b(r4, r5)
            java.lang.String r4 = r3.getName()
            java.lang.String r5 = "mp4"
            boolean r4 = r4.endsWith(r5)
            if (r4 == 0) goto L_0x00f0
            java.lang.String r4 = r3.getName()
            java.lang.String r5 = "A_"
            boolean r4 = r4.startsWith(r5)
            if (r4 != 0) goto L_0x00e5
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = "Time_"
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L_0x00f0
        L_0x00e5:
            r0 = 3
            r8.status = r0
            java.lang.String r0 = TAG
            java.lang.String r1 = "found mp4 file"
            com.mijia.debug.SDKLog.c(r0, r1)
            goto L_0x00f5
        L_0x00f0:
            int r0 = r0 + 1
            goto L_0x0089
        L_0x00f3:
            r8.status = r0
        L_0x00f5:
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "refreshState status = "
            r1.append(r2)
            int r2 = r8.status
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.mijia.debug.SDKLog.b(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask.refreshState():void");
    }

    public String getVideoPath(MijiaCameraDevice mijiaCameraDevice) {
        SDKLog.b(TAG, "getVideoPath");
        this.mCameraDevice = mijiaCameraDevice;
        this.mDid = mijiaCameraDevice.getDid();
        return FileUtil.a(mijiaCameraDevice.getDid(), this.taskID, this.taskName) + "video/";
    }

    public File getVideoFile(String str) {
        this.rootFolder = new File(FileUtil.a(this.mDid, this.taskID, this.taskName));
        SDKLog.b(TAG, "rootFolder=" + this.rootFolder.getPath());
        if (!this.rootFolder.exists()) {
            this.rootFolder.mkdirs();
        }
        if (this.videoFolder == null || !this.videoFolder.exists()) {
            this.videoFolder = new File(this.rootFolder.getPath() + "/video/");
            if (!this.videoFolder.exists()) {
                this.videoFolder.mkdirs();
            }
        }
        File[] listFiles = this.videoFolder.listFiles();
        File file = null;
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        if (listFiles.length != 1) {
            for (File file2 : listFiles) {
                if (file2.getName().endsWith(str) && (file == null || file2.lastModified() > file.lastModified())) {
                    file = file2;
                }
            }
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("videofile=");
            sb.append(file);
            SDKLog.b(str2, sb.toString() != null ? file.getPath() : "null");
            return file;
        } else if (listFiles[0].getName().endsWith(str)) {
            return listFiles[0];
        } else {
            return null;
        }
    }

    public String getLatestPicPath() {
        this.rootFolder = new File(FileUtil.a(this.mDid, this.taskID, this.taskName));
        if (!this.rootFolder.exists()) {
            this.rootFolder.mkdirs();
        }
        if (this.picFolder == null || !this.picFolder.exists()) {
            this.picFolder = new File(this.rootFolder.getPath() + "/pic/");
            if (!this.picFolder.exists()) {
                this.picFolder.mkdirs();
            }
        }
        File[] listFiles = this.picFolder.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return "";
        }
        if (listFiles.length == 1) {
            return FileUtil.a(this.mDid, this.taskID, this.taskName) + "pic/" + listFiles[0].getName();
        }
        File file = listFiles[0];
        for (File file2 : listFiles) {
            if (file2.lastModified() > file.lastModified()) {
                file = file2;
            }
        }
        return file.getPath();
    }

    public static TimelapseTask getFromProtocol(MijiaCameraDevice mijiaCameraDevice, byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            SDKLog.b("TimeLapsePhotographyActivity", "走了......1");
            return new TimelapseTask(mijiaCameraDevice, 0, 0, 0, 0, 0);
        }
        int d = ByteOperator.d(bArr, 0);
        if (d == 0) {
            SDKLog.b("TimeLapsePhotographyActivity", "走了......2");
            return new TimelapseTask(mijiaCameraDevice, 0, 0, 0, 0, 0);
        }
        int d2 = ByteOperator.d(bArr, 4);
        int d3 = ByteOperator.d(bArr, 8);
        int d4 = ByteOperator.d(bArr, 12);
        int d5 = ByteOperator.d(bArr, 16);
        SDKLog.b("TimeLapsePhotographyActivity", "getFromProtocol taskID " + d + ",startTime=" + d2 + ",endTime=" + d3 + ",intervale=" + d4 + ".timezoneInMinute=" + d5);
        return new TimelapseTask(mijiaCameraDevice, (long) d, (long) d2, (long) d3, d4, d5);
    }

    public TimelapseTask(int i, int i2, int i3, int i4) {
        this.cameraMac = "";
        this.taskName = "";
        this.isCreator = true;
        this.creatStatus = 0;
        this.status = 0;
        this.timelapseSaveType = 1;
        this.fileStatus = 1;
        this.videoType = 3;
        this.startTimestampInSeconds = i;
        this.taskID = this.startTimestampInSeconds;
        String str = TAG;
        SDKLog.b(str, "TimelapseTask: taskID=" + this.taskID);
        String str2 = TAG;
        SDKLog.b(str2, "TimelapseTask: status=" + i2);
        this.timezoneInMunites = i4;
        switch (i2) {
            case 1:
                this.status = 0;
                this.endTimestampInSeconds = (int) ((System.currentTimeMillis() / 1000) + ((long) (i4 * 60)) + 86400);
                break;
            case 2:
                this.status = 1;
                this.endTimestampInSeconds = (int) ((System.currentTimeMillis() / 1000) + ((long) (i4 * 60)) + 86400);
                break;
            case 3:
                this.status = 2;
                this.endTimestampInSeconds = (int) (((System.currentTimeMillis() / 1000) + ((long) (i4 * 60))) - 3600);
                break;
            default:
                this.status = 0;
                break;
        }
        this.fileStatus = i3;
    }

    public void setFileStatus(int i) {
        this.fileStatus = i;
    }

    public int getFileStatus() {
        return this.fileStatus;
    }

    public int getVideoType() {
        return this.videoType;
    }

    public void deleteLocalData(File file) {
        if (file == null) {
            file = this.rootFolder;
        }
        if (file == null || !file.exists()) {
            SDKLog.b(TAG, " deleteLocalData aimed folder not exist");
            return;
        }
        SDKLog.c(TAG, " deleteLocalData start folder:" + file.getPath());
        if (file.isDirectory()) {
            SDKLog.c(TAG, " deleteLocalData is directory");
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                SDKLog.c(TAG, " deleteLocalData delete empty directory: " + file.getPath());
                file.delete();
                return;
            }
            for (File deleteLocalData : listFiles) {
                deleteLocalData(deleteLocalData);
            }
            SDKLog.c(TAG, " deleteLocalData delete directory: " + file.getPath());
            file.delete();
            return;
        }
        SDKLog.c(TAG, " deleteLocalData is file");
        SDKLog.c(TAG, " deleteLocalData delete file: " + file.getPath());
        file.delete();
    }

    public void deleLocalDataNew(File file) {
        if (file == null) {
            file = this.rootFolder;
        }
        if (file == null || !file.exists()) {
            SDKLog.b(TAG, " deleLocalDataNew aimed folder not exist");
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    SDKLog.b(TAG, "deleLocalDataNew file= " + file2.getAbsolutePath());
                    if (file2.isDirectory()) {
                        deleLocalDataNew(file2);
                    } else {
                        file2.delete();
                    }
                }
                file.delete();
            }
        } else {
            file.delete();
        }
    }
}
