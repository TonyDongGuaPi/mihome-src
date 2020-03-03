package com.xiaomi.smarthome.camera.activity.timelapse;

import com.Utils.FileUtil;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.SDKLog;
import com.taobao.weex.annotation.JSMethod;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Timelapse {
    public static final int LOADING = 5;
    public static final int LOAD_FAILURE = 6;
    public static final int NOT_EDIT = 2;
    public static final int NOT_ENOUGH_ONE_MIN = 4;
    public static final int NOT_FINISH = 1;
    public static final int NOT_START = 0;
    public static final int RECORD_TYPE_ALARM = 1;
    public static final int RECORD_TYPE_AUTO = 2;
    public static final int RECORD_TYPE_TIMELAPSE = 3;
    public static final int SAVED = 3;
    private static final String TAG = "AlbumActivity";
    public static ArrayList<TimelapseTask> timeLapseList = new ArrayList<>();
    private MijiaCameraDevice mCameraDevice;

    private static void addToList(TimelapseTask timelapseTask) {
        SDKLog.b(TAG, "addToList  tlt =" + timelapseTask.getStartTimestampInSeconds());
        SDKLog.b(TAG, "addToList  tlt =" + timelapseTask.getEndTimestampInSeconds());
        SDKLog.b(TAG, "addToList  tlt =" + timelapseTask.status);
        SDKLog.b(TAG, "");
        if (timelapseTask == null) {
            SDKLog.b(TAG, "tlt==null");
            return;
        }
        if (timeLapseList == null) {
            SDKLog.b(TAG, "timeLapseList==null");
            timeLapseList = new ArrayList<>();
        }
        boolean z = false;
        Iterator<TimelapseTask> it = timeLapseList.iterator();
        while (it.hasNext()) {
            TimelapseTask next = it.next();
            if (next.getTaskID() == timelapseTask.getTaskID()) {
                z = true;
                next.setEndTimestampInSeconds((long) timelapseTask.getEndTimestampInSeconds());
                next.refreshState();
            }
        }
        SDKLog.b(TAG, "addToList ==" + z);
        if (!z) {
            SDKLog.b(TAG, ShareConstants.O + timelapseTask.getTaskName());
            timeLapseList.add(timelapseTask);
        }
    }

    public static void getSavedTimeLapseData(MijiaCameraDevice mijiaCameraDevice) throws IOException {
        File file = new File(FileUtil.a());
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        SDKLog.b(TAG, "getLocalTimeLapseData");
        if (listFiles != null) {
            for (File file2 : listFiles) {
                SDKLog.b(TAG, "cameraFolder getPath =" + file2.getPath());
                SDKLog.b(TAG, "cameraFolder getName =" + file2.getName());
                if (mijiaCameraDevice.getDid().equals(file2.getName())) {
                    SDKLog.b(TAG, "cameraFolder current Camera did =");
                    getTimelapseFromLocolFolder(file2, mijiaCameraDevice);
                    return;
                }
            }
        }
    }

    private static void getTimelapseFromLocolFolder(File file, MijiaCameraDevice mijiaCameraDevice) {
        for (File file2 : file.listFiles()) {
            SDKLog.b(TAG, "file getTimelapseFromLocolFolder=" + file2.getAbsolutePath());
            String name = file2.getName();
            SDKLog.b(TAG, "fileName= " + name);
            if (!file2.isFile()) {
                try {
                    SDKLog.b(TAG, "fileName.split(\"_\")[1]=" + name.split(JSMethod.NOT_SET)[1] + ",Long.valueOf(fileName.split(\"_\")[0])=" + Long.valueOf(name.split(JSMethod.NOT_SET)[0]));
                    TimelapseTask timelapseTask = new TimelapseTask(name.split(JSMethod.NOT_SET)[1], Long.valueOf(name.split(JSMethod.NOT_SET)[0]).longValue());
                    if (timelapseTask.readLogFile(mijiaCameraDevice) == -1) {
                        SDKLog.b(TAG, "log文件不存在或读写错误, " + name);
                    } else {
                        timelapseTask.refreshState();
                        SDKLog.b(TAG, "status = " + timelapseTask.status);
                        if (timelapseTask.status == 3) {
                            addToList(timelapseTask);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    SDKLog.b(TAG, "getTimelapseFromCameraFolder exception: " + e.getMessage());
                }
            }
        }
    }

    public static TimelapseTask getTimeLapseFromList(long j) {
        Iterator<TimelapseTask> it = timeLapseList.iterator();
        while (it.hasNext()) {
            TimelapseTask next = it.next();
            if (((long) (next.getStartTimestampInSeconds() - (next.getTimezoneInMinutes() * 60))) == j) {
                return next;
            }
        }
        return null;
    }
}
