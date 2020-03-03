package com.xiaomi.mobilestats.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.EventCategory;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.mobilestats.common.CommonUtil;
import com.xiaomi.mobilestats.common.FileNameComparator;
import com.xiaomi.mobilestats.common.FileUtil;
import com.xiaomi.mobilestats.common.HostManager;
import com.xiaomi.mobilestats.common.NetType;
import com.xiaomi.mobilestats.common.StrUtil;
import com.xiaomi.mobilestats.data.BasicStoreTools;
import com.xiaomi.mobilestats.data.SendStrategyEnum;
import com.xiaomi.mobilestats.upload.UploadManager;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;

public class LogController {
    private static HandlerThread G = new HandlerThread("LogSenderThread");
    private static LogController I = new LogController();
    public static String baseFilePath = "";
    /* access modifiers changed from: private */
    public static Handler handler;
    public static boolean is2GDownNet = false;
    public static boolean isOnlyWifi = false;
    public static String operatorClientFilePath = "";
    public static String operatorCrashFilePath = "";
    public static String operatorErrorFilePath = "";
    public static String operatorEventFilePath = "";
    public static String operatorFileDir = "";
    public static String operatorPageFilePath = "";
    public static String operatorViewFilePath = "";
    public static String uploadFileDir = "";
    private Timer H = null;
    public long logSendDelayedTime = 0;
    public long logSendIntervalHour = 1;
    public SendStrategyEnum sendStragegy = SendStrategyEnum.REAL_TIME;

    private LogController() {
        G.start();
        handler = new Handler(G.getLooper());
    }

    private void a(Context context, long j) {
        long j2;
        Context context2 = context;
        if (context2 != null && this.sendStragegy.equals(SendStrategyEnum.ONCE_A_DAY)) {
            BasicStoreTools.getInstance().setSendStrategyTime(context2, 86400);
            if (j == 0) {
                BasicStoreTools.getInstance().setLastSendTime(context2, System.currentTimeMillis());
                j2 = BasicStoreTools.getInstance().getLastSendTime(context2);
            } else {
                j2 = j;
            }
            this.H = new Timer();
            long currentTimeMillis = System.currentTimeMillis() - j2;
            if (currentTimeMillis < 0 || currentTimeMillis >= 86400000) {
                this.H.schedule(new MTimerTask(), HostManager.kContinueSessionMillis, 86400000);
            } else {
                this.H.schedule(new MTimerTask(), 86400000 - (System.currentTimeMillis() - j2), 86400000);
            }
        }
    }

    private void a(Context context, long j, long j2) {
        if (context != null && this.sendStragegy.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            if (j < 1) {
                j = 1;
            }
            if (j > 86400) {
                j = 86400;
            }
            this.logSendIntervalHour = j;
            BasicStoreTools.getInstance().setSendStrategyTime(context, this.logSendIntervalHour);
            if (j2 == 0) {
                BasicStoreTools.getInstance().setLastSendTime(context, System.currentTimeMillis());
            }
            this.H = new Timer();
            this.H.schedule(new MTimerTask(), HostManager.kContinueSessionMillis, j * 1000);
        }
    }

    private static void a(File[] fileArr) {
        if (fileArr != null && fileArr.length > 0) {
            Arrays.sort(fileArr, new FileNameComparator());
        }
    }

    private static void c(File file) {
        File file2 = new File(file.getPath() + File.separator + "cache");
        File file3 = new File(baseFilePath);
        if (file2.isDirectory()) {
            File[] listFiles = file2.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i] != null && !listFiles[i].getName().equals(file3.getName())) {
                    FileUtil.deleteFloder(listFiles[i]);
                }
            }
        }
    }

    private static void c(String str) {
        if (str.equals("page") && TextUtils.isEmpty(operatorPageFilePath)) {
            operatorPageFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_page.json";
            File file = new File(operatorPageFilePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (str.equals("event") && TextUtils.isEmpty(operatorEventFilePath)) {
            operatorEventFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_event.json";
            File file2 = new File(operatorEventFilePath);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (str.equals("error") && TextUtils.isEmpty(operatorErrorFilePath)) {
            operatorErrorFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_error.json";
            File file3 = new File(operatorErrorFilePath);
            if (!file3.exists()) {
                try {
                    file3.createNewFile();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (str.equals("crash") && TextUtils.isEmpty(operatorCrashFilePath)) {
            operatorCrashFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_crash.json";
            File file4 = new File(operatorCrashFilePath);
            if (!file4.exists()) {
                try {
                    file4.createNewFile();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
        if (str.equals(OpenSdkPlayStatisticUpload.r) && TextUtils.isEmpty(operatorClientFilePath)) {
            operatorClientFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_client.json";
            File file5 = new File(operatorClientFilePath);
            if (!file5.exists()) {
                try {
                    file5.createNewFile();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        }
        if (str.equalsIgnoreCase("view") && TextUtils.isEmpty(operatorViewFilePath)) {
            operatorViewFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_view.json";
            File file6 = new File(operatorViewFilePath);
            if (!file6.exists()) {
                try {
                    file6.createNewFile();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
        }
    }

    public static void checkOrCreate(Context context) {
        String str;
        if (StrUtil.isEmpty(baseFilePath)) {
            File file = new File(context.getFilesDir(), HostManager.DEFAULT_CACHE_DIR);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                str = "cache/" + packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                str = "cache/0";
            }
            baseFilePath = file.getPath() + File.separator + str;
            c(file);
            operatorFileDir = baseFilePath + File.separator + AuthorizeActivityBase.KEY_OPERATOR;
            uploadFileDir = baseFilePath + File.separator + EventCategory.CATEGORY_UPLOAD;
            FileUtil.checkOrCreateFolder(operatorFileDir);
            FileUtil.checkOrCreateFolder(uploadFileDir);
        }
    }

    public static void copyOperatorFilesToUploaDir() {
        try {
            File[] listFiles = new File(operatorFileDir).listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file != null && file.exists()) {
                        copyToUploadDirAndDel(file, uploadFileDir + File.separator + file.getName());
                    }
                }
                e();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyToUploadDirAndDel(File file, String str) {
        CommonUtil.printLog(HostManager.TAG, "copyToUploadDirAndDel:" + file.getName());
        if (file != null) {
            if (file.length() <= 0) {
                file.delete();
            }
            if (file == null || file.exists()) {
                File file2 = new File(str);
                File parentFile = file2.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (file2.exists()) {
                    file.delete();
                    FileLruCache.getInstance().putFile(file2.getName(), file2);
                }
            }
        }
    }

    public static void deleteAllFloder() {
        if (!StrUtil.isEmpty(baseFilePath)) {
            try {
                FileUtil.deleteFloder(new File(baseFilePath));
            } catch (Exception unused) {
            }
        }
    }

    private static void e() {
        operatorEventFilePath = "";
        operatorPageFilePath = "";
        operatorCrashFilePath = "";
        operatorErrorFilePath = "";
        operatorClientFilePath = "";
        operatorViewFilePath = "";
    }

    private static void f() {
        if (FileLruCache.getInstance().getCacheHashMap().isEmpty()) {
            try {
                File[] listFiles = new File(uploadFileDir).listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    a(listFiles);
                    for (int i = 0; i < listFiles.length; i++) {
                        FileLruCache.getInstance().putFile(listFiles[i].getName(), listFiles[i]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void firstCopyLogToUploadFloder(Context context) {
        CommonUtil.printLog(HostManager.TAG, "firstCopyLogToUpload");
        checkOrCreate(context);
        CommonUtil.printLog(HostManager.TAG, "File cache size:" + FileLruCache.getInstance().getCacheHashMap().size());
        f();
        try {
            File[] listFiles = new File(operatorFileDir).listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file != null && file.exists()) {
                        copyToUploadDirAndDel(file, uploadFileDir + File.separator + file.getName());
                    }
                }
                e();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LogController geInstance() {
        return I;
    }

    private void k(Context context) {
        if (context == null) {
        }
    }

    private void l(Context context) {
        if (context != null && this.sendStragegy.equals(SendStrategyEnum.REAL_TIME)) {
            handler.postDelayed(new b(this), 1000);
        }
    }

    public static String saveInfoToLog(Context context, String str, String str2) {
        checkOrCreate(context);
        File file = new File(uploadFileDir);
        File file2 = new File(operatorFileDir);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        c(str);
        String str3 = "";
        if (!TextUtils.isEmpty(str) && str.equals("page")) {
            str3 = operatorPageFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("event")) {
            str3 = operatorEventFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("crash")) {
            str3 = operatorCrashFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("error")) {
            str3 = operatorErrorFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals(OpenSdkPlayStatisticUpload.r)) {
            str3 = operatorClientFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("view")) {
            str3 = operatorViewFilePath;
        }
        File file3 = new File(str3);
        if (!file3.exists() || file3.length() + ((long) str2.getBytes().length) <= 16384) {
            return str3;
        }
        CommonUtil.printLog(HostManager.TAG, "size is over 16k");
        copyToUploadDirAndDel(file3, uploadFileDir + File.separator + file3.getName());
        if (!TextUtils.isEmpty(str) && str.equals("page")) {
            operatorPageFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_page.json";
            return operatorPageFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("event")) {
            operatorEventFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_event.json";
            return operatorEventFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("crash")) {
            operatorCrashFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_crash.json";
            return operatorCrashFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("error")) {
            operatorErrorFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_error.json";
            return operatorErrorFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals(OpenSdkPlayStatisticUpload.r)) {
            operatorClientFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_client.json";
            return operatorClientFilePath;
        } else if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase("view")) {
            return str3;
        } else {
            operatorViewFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_view.json";
            return operatorViewFilePath;
        }
    }

    public static String saveInfoToLog(Context context, String str, byte[] bArr) {
        checkOrCreate(context);
        File file = new File(uploadFileDir);
        File file2 = new File(operatorFileDir);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        c(str);
        String str2 = "";
        if (!TextUtils.isEmpty(str) && str.equals("page")) {
            str2 = operatorPageFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("event")) {
            str2 = operatorEventFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("crash")) {
            str2 = operatorCrashFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("error")) {
            str2 = operatorErrorFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals(OpenSdkPlayStatisticUpload.r)) {
            str2 = operatorClientFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("view")) {
            str2 = operatorViewFilePath;
        }
        File file3 = new File(str2);
        if (!file3.exists() || file3.length() + ((long) bArr.length) <= 16384) {
            return str2;
        }
        CommonUtil.printLog(HostManager.TAG, "size is over 16k");
        copyToUploadDirAndDel(file3, uploadFileDir + File.separator + file3.getName());
        if (!TextUtils.isEmpty(str) && str.equals("page")) {
            operatorPageFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_page.json";
            return operatorPageFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("event")) {
            operatorEventFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_event.json";
            return operatorEventFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("crash")) {
            operatorCrashFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_crash.json";
            return operatorCrashFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals("error")) {
            operatorErrorFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_error.json";
            return operatorErrorFilePath;
        } else if (!TextUtils.isEmpty(str) && str.equals(OpenSdkPlayStatisticUpload.r)) {
            operatorClientFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_client.json";
            return operatorClientFilePath;
        } else if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase("view")) {
            return str2;
        } else {
            operatorViewFilePath = operatorFileDir + File.separator + System.currentTimeMillis() + "_view.json";
            return operatorViewFilePath;
        }
    }

    public void setSendDelayedTime(int i) {
        if (i >= 0 && i <= 30) {
            this.logSendDelayedTime = (long) i;
        }
    }

    public void setSendStrategy(Context context, SendStrategyEnum sendStrategyEnum, long j, boolean z) {
        if (context != null) {
            NetType.isNet2G_DOWN(context);
            checkOrCreate(context);
            firstCopyLogToUploadFloder(context);
            if (this.H != null) {
                this.H.cancel();
                this.H = null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long lastSendTime = BasicStoreTools.getInstance().getLastSendTime(context);
            int sendStrategy = BasicStoreTools.getInstance().getSendStrategy(context);
            if (sendStrategy == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() || sendStrategy == SendStrategyEnum.ONCE_A_DAY.ordinal()) {
                if (lastSendTime != 0) {
                    if (currentTimeMillis - lastSendTime >= BasicStoreTools.getInstance().getSendStrategyTime(context) * 1000 && UploadManager.isHasCacheFile()) {
                        UploadManager.uploadCachedUploadFiles(handler);
                        BasicStoreTools.getInstance().setLastSendTime(context, System.currentTimeMillis());
                    }
                }
            } else if (sendStrategy == SendStrategyEnum.APP_START.ordinal() && UploadManager.isHasCacheFile()) {
                UploadManager.uploadCachedUploadFiles(handler);
            }
            this.sendStragegy = sendStrategyEnum;
            BasicStoreTools.getInstance().setSendStrategy(context, this.sendStragegy.ordinal());
            isOnlyWifi = z;
            BasicStoreTools.getInstance().setOnlyWifi(context, z);
            if (this.sendStragegy.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
                a(context, j, lastSendTime);
            } else if (this.sendStragegy.equals(SendStrategyEnum.ONCE_A_DAY)) {
                a(context, lastSendTime);
            } else if (this.sendStragegy.equals(SendStrategyEnum.REAL_TIME)) {
                l(context);
            } else {
                k(context);
            }
        }
    }
}
