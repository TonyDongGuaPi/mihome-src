package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.Context;
import android.os.Build;

public class gqgqgqq9gq9q9q9 {
    public static boolean gqg9qq9gqq9q9q(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean gqg9qq9gqq9q9q(Context context, String... strArr) {
        if (r0 == 0) {
            return false;
        }
        boolean z = false;
        for (String checkCallingOrSelfPermission : strArr) {
            if (context.checkCallingOrSelfPermission(checkCallingOrSelfPermission) == 0) {
                z = true;
            }
        }
        return z;
    }

    public static boolean gqg9qq9gqq9q9q(int i) {
        return Build.VERSION.SDK_INT < i;
    }

    public static boolean qgg9qgg9999g9g(int i) {
        return Build.VERSION.SDK_INT >= i;
    }
}
