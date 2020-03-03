package com.liulishuo.filedownloader.model;

import com.liulishuo.filedownloader.BaseDownloadTask;

public class FileDownloadStatus {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f6443a = 10;
    public static final byte b = 11;
    public static final byte c = 1;
    public static final byte d = 6;
    public static final byte e = 2;
    public static final byte f = 3;
    public static final byte g = 4;
    public static final byte h = 5;
    public static final byte i = -1;
    public static final byte j = -2;
    public static final byte k = -3;
    public static final byte l = -4;
    public static final byte m = 0;

    public static boolean a(int i2) {
        return i2 < 0;
    }

    public static boolean b(int i2) {
        return i2 > 0;
    }

    public static boolean a(int i2, int i3) {
        if ((i2 != 3 && i2 != 5 && i2 == i3) || a(i2)) {
            return false;
        }
        if (i2 >= 1 && i2 <= 6 && i3 >= 10 && i3 <= 11) {
            return false;
        }
        switch (i2) {
            case 1:
                return i3 != 0;
            case 2:
                if (i3 != 6) {
                    switch (i3) {
                        case 0:
                        case 1:
                            break;
                        default:
                            return true;
                    }
                }
                return false;
            case 3:
                if (i3 != 6) {
                    switch (i3) {
                        case 0:
                        case 1:
                        case 2:
                            break;
                        default:
                            return true;
                    }
                }
                return false;
            case 5:
                return (i3 == 1 || i3 == 6) ? false : true;
            case 6:
                switch (i3) {
                    case 0:
                    case 1:
                        return false;
                    default:
                        return true;
                }
            default:
                return true;
        }
    }

    public static boolean b(int i2, int i3) {
        if ((i2 != 3 && i2 != 5 && i2 == i3) || a(i2)) {
            return false;
        }
        if (i3 == -2 || i3 == -1) {
            return true;
        }
        switch (i2) {
            case 0:
                return i3 == 10;
            case 1:
                return i3 == 6;
            case 2:
            case 3:
                return i3 == -3 || i3 == 3 || i3 == 5;
            case 5:
            case 6:
                return i3 == 2 || i3 == 5;
            case 10:
                return i3 == 11;
            case 11:
                if (i3 != 1) {
                    switch (i3) {
                        case -4:
                        case -3:
                            break;
                        default:
                            return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public static boolean a(BaseDownloadTask baseDownloadTask) {
        return baseDownloadTask.B() == 0 || baseDownloadTask.B() == 3;
    }
}
