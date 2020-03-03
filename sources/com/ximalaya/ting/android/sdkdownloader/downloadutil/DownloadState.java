package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.xiaomi.infrared.InifraredContants;

public enum DownloadState {
    WAITING(0),
    STARTED(1),
    FINISHED(2),
    STOPPED(3),
    ERROR(4),
    NOADD(5);
    
    private final int value;

    private DownloadState(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static DownloadState valueOf(int i) {
        switch (i) {
            case 0:
                return WAITING;
            case 1:
                return STARTED;
            case 2:
                return FINISHED;
            case 3:
                return STOPPED;
            case 4:
                return ERROR;
            case 5:
                return NOADD;
            default:
                return NOADD;
        }
    }

    public String toString() {
        switch (this.value) {
            case 0:
                return "等待";
            case 1:
                return "开始";
            case 2:
                return "结束";
            case 3:
                return InifraredContants.Q;
            case 4:
                return "错误";
            case 5:
                return "未添加";
            default:
                return "未知";
        }
    }
}
