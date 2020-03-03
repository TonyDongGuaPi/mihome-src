package com.taobao.weex.utils;

import android.annotation.TargetApi;
import android.util.Log;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Trace {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "Weex_Trace";
    private static final boolean sEnabled = false;
    private static final AbstractTrace sTrace;

    /* renamed from: com.taobao.weex.utils.Trace$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5739271957758885382L, "com/taobao/weex/utils/Trace$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6233774253203044748L, "com/taobao/weex/utils/Trace", 13);
        $jacocoData = a2;
        return a2;
    }

    public Trace() {
        $jacocoInit()[0] = true;
    }

    private static abstract class AbstractTrace {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(4875015713972618480L, "com/taobao/weex/utils/Trace$AbstractTrace", 2);
            $jacocoData = a2;
            return a2;
        }

        /* access modifiers changed from: package-private */
        public abstract void beginSection(String str);

        /* access modifiers changed from: package-private */
        public abstract void endSection();

        private AbstractTrace() {
            $jacocoInit()[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ AbstractTrace(AnonymousClass1 r2) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[1] = true;
        }
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[6] = true;
        if (!sEnabled) {
            $jacocoInit[7] = true;
        } else if (!OsVersion.isAtLeastJB_MR2()) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            sTrace = new TraceJBMR2((AnonymousClass1) null);
            $jacocoInit[10] = true;
            $jacocoInit[12] = true;
        }
        sTrace = new TraceDummy((AnonymousClass1) null);
        $jacocoInit[11] = true;
        $jacocoInit[12] = true;
    }

    public static final boolean getTraceEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = sEnabled;
        $jacocoInit[1] = true;
        return z;
    }

    public static void beginSection(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Log.i(TAG, "beginSection() " + str);
        $jacocoInit[2] = true;
        sTrace.beginSection(str);
        $jacocoInit[3] = true;
    }

    public static void endSection() {
        boolean[] $jacocoInit = $jacocoInit();
        sTrace.endSection();
        $jacocoInit[4] = true;
        Log.i(TAG, "endSection()");
        $jacocoInit[5] = true;
    }

    @TargetApi(18)
    private static final class TraceJBMR2 extends AbstractTrace {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(1499882747630137176L, "com/taobao/weex/utils/Trace$TraceJBMR2", 4);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private TraceJBMR2() {
            super((AnonymousClass1) null);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ TraceJBMR2(AnonymousClass1 r3) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[3] = true;
        }

        /* access modifiers changed from: package-private */
        public void beginSection(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            android.os.Trace.beginSection(str);
            $jacocoInit[1] = true;
        }

        /* access modifiers changed from: package-private */
        public void endSection() {
            boolean[] $jacocoInit = $jacocoInit();
            android.os.Trace.endSection();
            $jacocoInit[2] = true;
        }
    }

    private static final class TraceDummy extends AbstractTrace {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2407002761288186324L, "com/taobao/weex/utils/Trace$TraceDummy", 4);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private TraceDummy() {
            super((AnonymousClass1) null);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ TraceDummy(AnonymousClass1 r3) {
            this();
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[3] = true;
        }

        /* access modifiers changed from: package-private */
        public void beginSection(String str) {
            $jacocoInit()[1] = true;
        }

        /* access modifiers changed from: package-private */
        public void endSection() {
            $jacocoInit()[2] = true;
        }
    }
}
