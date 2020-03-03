package no.nordicsemi.android.error;

import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.taobao.weex.el.parse.Operators;

public final class SecureDfuError {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3165a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 7;
    public static final int f = 8;
    public static final int g = 10;
    public static final int h = 11;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static final int l = 5;
    public static final int m = 6;
    public static final int n = 7;
    public static final int o = 8;
    public static final int p = 9;
    public static final int q = 10;
    public static final int r = 11;
    public static final int s = 12;
    public static final int t = 13;
    public static final int u = 2;
    public static final int v = 4;

    public static String b(int i2) {
        switch (i2) {
            case 1026:
                return "Wrong command format";
            case 1027:
                return "Unknown command";
            case 1028:
                return "Init command invalid";
            case 1029:
                return "FW version failure";
            case 1030:
                return "HW version failure";
            case 1031:
                return "SD version failure";
            case 1032:
                return "Signature mismatch";
            case PhotoshopDirectory.F /*1033*/:
                return "Wrong hash type";
            case PhotoshopDirectory.G /*1034*/:
                return "Hash failed";
            case 1035:
                return "Wrong signature type";
            case PhotoshopDirectory.I /*1036*/:
                return "Verification failed";
            case 1037:
                return "Insufficient space";
            default:
                return "Reserved for future use";
        }
    }

    public static String a(int i2) {
        switch (i2) {
            case 514:
                return "OP CODE NOT SUPPORTED";
            case 515:
                return "INVALID PARAM";
            case 516:
                return "INSUFFICIENT RESOURCES";
            case 517:
                return "INVALID OBJECT";
            case 519:
                return "UNSUPPORTED TYPE";
            case 520:
                return "OPERATION NOT PERMITTED";
            case 522:
                return "OPERATION FAILED";
            case 523:
                return "EXTENDED ERROR";
            default:
                return "UNKNOWN (" + i2 + Operators.BRACKET_END_STR;
        }
    }

    public static String c(int i2) {
        if (i2 == 2050) {
            return "OP CODE NOT SUPPORTED";
        }
        if (i2 == 2052) {
            return "OPERATION FAILED";
        }
        return "UNKNOWN (" + i2 + Operators.BRACKET_END_STR;
    }
}
