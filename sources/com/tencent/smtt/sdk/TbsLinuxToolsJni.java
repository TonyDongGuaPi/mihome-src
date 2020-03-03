package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

class TbsLinuxToolsJni {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f9094a = false;
    private static boolean b = false;

    public TbsLinuxToolsJni(Context context) {
        a(context);
    }

    private native int ChmodInner(String str, String str2);

    private void a(Context context) {
        File file;
        synchronized (TbsLinuxToolsJni.class) {
            if (!b) {
                b = true;
                try {
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        String a2 = TbsShareManager.a();
                        if (a2 == null) {
                            a2 = TbsShareManager.c(context);
                        }
                        file = new File(a2);
                    } else {
                        file = am.a().q(context);
                    }
                    if (file != null) {
                        if (!new File(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so").exists() && !TbsShareManager.isThirdPartyApp(context)) {
                            file = am.a().p(context);
                        }
                        if (file != null) {
                            System.load(file.getAbsolutePath() + File.separator + "liblinuxtoolsfortbssdk_jni.so");
                            f9094a = true;
                        }
                    }
                    ChmodInner("/checkChmodeExists", "700");
                } catch (Throwable th) {
                    th.printStackTrace();
                    f9094a = false;
                }
            }
        }
    }

    public int a(String str, String str2) {
        if (f9094a) {
            return ChmodInner(str, str2);
        }
        TbsLog.e("TbsLinuxToolsJni", "jni not loaded!", true);
        return -1;
    }
}
