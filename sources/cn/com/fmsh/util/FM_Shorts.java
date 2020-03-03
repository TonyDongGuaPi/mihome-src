package cn.com.fmsh.util;

import com.taobao.weex.el.parse.Operators;

public class FM_Shorts {
    public short[] data = new short[0];

    public static short[] join(short[] sArr, short[] sArr2) {
        if (sArr == null) {
            throw new NullPointerException(" data1 Arrays is null");
        } else if (sArr2 != null) {
            int length = sArr.length;
            short[] sArr3 = new short[(sArr.length + sArr2.length)];
            for (int i = 0; i < length; i++) {
                sArr3[i] = sArr[i];
            }
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[length + i2] = sArr2[i2];
            }
            return sArr3;
        } else {
            throw new NullPointerException(" data2 Arrays is null");
        }
    }

    public static short[] copyOf(short[] sArr, int i) {
        if (sArr != null) {
            short[] sArr2 = new short[i];
            int i2 = 0;
            if (sArr.length < i) {
                while (i2 < sArr.length) {
                    sArr2[i2] = sArr[i2];
                    i2++;
                }
            } else {
                while (i2 < i) {
                    sArr2[i2] = sArr[i2];
                    i2++;
                }
            }
            return sArr2;
        }
        throw new NullPointerException(" original Arrays is null");
    }

    public static short[] copyOfRange(short[] sArr, int i, int i2) {
        if (sArr != null) {
            int i3 = i2 - i;
            if (i3 <= 0) {
                throw new IllegalArgumentException(" from[" + i + "]>to[" + i2 + Operators.ARRAY_END_STR);
            } else if (sArr.length < i) {
                throw new IllegalArgumentException(" original.length[" + sArr.length + "]<to[" + i + Operators.ARRAY_END_STR);
            } else if (sArr.length >= i2) {
                short[] sArr2 = new short[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    sArr2[i4] = sArr[i4 + i];
                }
                return sArr2;
            } else {
                throw new IllegalArgumentException(" original.length[" + sArr.length + "]<to[" + i2 + Operators.ARRAY_END_STR);
            }
        } else {
            throw new NullPointerException(" original Arrays is null");
        }
    }
}
