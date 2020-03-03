package com.xiaomi.pluginhost;

import android.os.Build;
import com.alipay.sdk.sys.a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArtDexOptimizer {
    public static void a(String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList.add("--runtime-arg");
            arrayList.add("-classpath");
            arrayList.add("--runtime-arg");
            arrayList.add(a.b);
        }
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=arm");
        if (Build.VERSION.SDK_INT > 25) {
            arrayList.add("--compiler-filter=quicken");
        } else {
            arrayList.add("--compiler-filter=interpret-only");
        }
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        processBuilder.redirectErrorStream(true);
        try {
            int waitFor = processBuilder.start().waitFor();
            if (waitFor != 0) {
                throw new IOException("dex2oat works unsuccessfully, exit code: " + waitFor);
            }
        } catch (InterruptedException e) {
            throw new IOException("dex2oat is interrupted, msg: " + e.getMessage(), e);
        }
    }
}
