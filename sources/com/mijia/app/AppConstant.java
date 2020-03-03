package com.mijia.app;

import android.os.Environment;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;

public class AppConstant {

    /* renamed from: a  reason: collision with root package name */
    public static final File f7857a = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), Constant.DEVICE_XIAOMI);
    public static final File b = new File(f7857a, "local");
    public static final File c = new File(f7857a, "alarm");
    public static final int d = 8;
    public static final int e = 4;
    public static final String f = "摄像机监控视频$";
    public static final int g = 0;
    public static final File h = new File(f7857a, "xLog");
    public static final File i = new File(f7857a, "CrashInfos");
    public static final File j = new File(f7857a, "audio");
    public static final File k = new File(f7857a, "timelapse");
    public static final File l = new File(f7857a, "timelapseDemo");
}
