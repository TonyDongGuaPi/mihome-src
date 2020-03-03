package com.taobao.weex;

import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public final class BuildConfig {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String APPLICATION_ID = "com.taobao.weex";
    public static final long ARM64V8_Size = 0;
    public static final long ARMEABIV7_Size = 0;
    public static final long ARMEABI_Size = 0;
    public static final String BUILD_TYPE = "release";
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static final boolean ENABLE_TRACE = false;
    public static final String FLAVOR = "";
    public static final int VERSION_CODE = 1;
    public static final String VERSION_NAME = "1.0";
    public static final long X86_Size = 0;
    public static final String buildJavascriptFrameworkVersion = "null";
    public static final String buildVersion = "0.20.0";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1739556568110745830L, "com/taobao/weex/BuildConfig", 2);
        $jacocoData = a2;
        return a2;
    }

    public BuildConfig() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }
}
