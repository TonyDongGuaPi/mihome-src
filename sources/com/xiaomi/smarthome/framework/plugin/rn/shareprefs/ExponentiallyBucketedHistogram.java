package com.xiaomi.smarthome.framework.plugin.rn.shareprefs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Locale;

public class ExponentiallyBucketedHistogram {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    private final int[] f17493a;

    public ExponentiallyBucketedHistogram(int i) {
        this.f17493a = new int[a(i, 1, 31, "numBuckets")];
    }

    public static int a(int i, int i2, int i3, String str) {
        if (i < i2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        } else if (i <= i3) {
            return i;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public void a(int i) {
        if (i <= 0) {
            int[] iArr = this.f17493a;
            iArr[0] = iArr[0] + 1;
            return;
        }
        int[] iArr2 = this.f17493a;
        int min = Math.min(this.f17493a.length - 1, 32 - Integer.numberOfLeadingZeros(i));
        iArr2[min] = iArr2[min] + 1;
    }

    public void a() {
        Arrays.fill(this.f17493a, 0);
    }

    public void a(@NonNull String str, @Nullable CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence);
        sb.append(Operators.ARRAY_START);
        for (int i = 0; i < this.f17493a.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            if (i < this.f17493a.length - 1) {
                sb.append("<");
                sb.append(1 << i);
            } else {
                sb.append(">=");
                sb.append(1 << (i - 1));
            }
            sb.append(": ");
            sb.append(this.f17493a[i]);
        }
        sb.append(Operators.ARRAY_END_STR);
        Log.d(str, sb.toString());
    }
}
