package com.xiaomi.youpin.youpin_common.widget.video;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ResizeMode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23834a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public static int a(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return 0;
        }
    }
}
