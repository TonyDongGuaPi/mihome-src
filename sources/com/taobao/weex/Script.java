package com.taobao.weex;

import android.text.TextUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Script {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private byte[] mBinary;
    private String mContent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1564867155297623350L, "com/taobao/weex/Script", 15);
        $jacocoData = a2;
        return a2;
    }

    public Script(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContent = str;
        $jacocoInit[0] = true;
    }

    public Script(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBinary = bArr;
        $jacocoInit[1] = true;
    }

    public String getContent() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mContent;
        $jacocoInit[2] = true;
        return str;
    }

    public byte[] getBinary() {
        boolean[] $jacocoInit = $jacocoInit();
        byte[] bArr = this.mBinary;
        $jacocoInit[3] = true;
        return bArr;
    }

    public int length() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mContent != null) {
            $jacocoInit[4] = true;
            int length = this.mContent.length();
            $jacocoInit[5] = true;
            return length;
        } else if (this.mBinary != null) {
            int length2 = this.mBinary.length;
            $jacocoInit[6] = true;
            return length2;
        } else {
            $jacocoInit[7] = true;
            return 0;
        }
    }

    public boolean isEmpty() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(this.mContent)) {
            $jacocoInit[8] = true;
        } else {
            if (this.mBinary == null) {
                $jacocoInit[9] = true;
            } else if (this.mBinary.length != 0) {
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[11] = true;
            }
            $jacocoInit[12] = true;
            z = true;
            $jacocoInit[14] = true;
            return z;
        }
        z = false;
        $jacocoInit[13] = true;
        $jacocoInit[14] = true;
        return z;
    }
}
