package com.taobao.weex.tracing;

import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Stopwatch {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static final ThreadLocal<Stopwatch> sThreadLocal = new ThreadLocal<>();
    private List<ProcessEvent> splits = new ArrayList();
    private long startMillis;
    private long startNanos;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7688653126950876050L, "com/taobao/weex/tracing/Stopwatch", 53);
        $jacocoData = a2;
        return a2;
    }

    public Stopwatch() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[52] = true;
    }

    private static void prepare() {
        boolean[] $jacocoInit = $jacocoInit();
        if (sThreadLocal.get() != null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            sThreadLocal.set(new Stopwatch());
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public static void tick() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXTracing.isAvailable()) {
            $jacocoInit[6] = true;
        } else {
            try {
                $jacocoInit[7] = true;
                prepare();
                $jacocoInit[8] = true;
                if (sThreadLocal.get().startNanos == 0) {
                    $jacocoInit[9] = true;
                } else {
                    $jacocoInit[10] = true;
                    WXLogUtils.w("Stopwatch", "Stopwatch is not reset");
                    $jacocoInit[11] = true;
                }
                sThreadLocal.get().startNanos = System.nanoTime();
                $jacocoInit[12] = true;
                sThreadLocal.get().startMillis = System.currentTimeMillis();
                $jacocoInit[13] = true;
            } catch (Throwable th) {
                $jacocoInit[14] = true;
                th.printStackTrace();
                $jacocoInit[15] = true;
            }
        }
        $jacocoInit[16] = true;
    }

    public static void split(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXTracing.isAvailable()) {
            $jacocoInit[17] = true;
        } else {
            try {
                $jacocoInit[18] = true;
                ProcessEvent processEvent = new ProcessEvent();
                $jacocoInit[19] = true;
                long j = sThreadLocal.get().startMillis;
                $jacocoInit[20] = true;
                double tackAndTick = tackAndTick();
                processEvent.fname = str;
                processEvent.duration = tackAndTick;
                processEvent.startMillis = j;
                $jacocoInit[21] = true;
                sThreadLocal.get().splits.add(processEvent);
                $jacocoInit[22] = true;
            } catch (Throwable th) {
                $jacocoInit[23] = true;
                th.printStackTrace();
                $jacocoInit[24] = true;
            }
        }
        $jacocoInit[25] = true;
    }

    public static List<ProcessEvent> getProcessEvents() {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXTracing.isAvailable()) {
            $jacocoInit[26] = true;
            tack();
            $jacocoInit[27] = true;
            List<ProcessEvent> list = sThreadLocal.get().splits;
            $jacocoInit[28] = true;
            sThreadLocal.get().splits = new ArrayList();
            $jacocoInit[29] = true;
            return list;
        }
        List<ProcessEvent> emptyList = Collections.emptyList();
        $jacocoInit[30] = true;
        return emptyList;
    }

    public static double tack() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXTracing.isAvailable()) {
            $jacocoInit[31] = true;
        } else {
            try {
                $jacocoInit[32] = true;
                long j = sThreadLocal.get().startNanos;
                if (j != 0) {
                    $jacocoInit[33] = true;
                } else {
                    $jacocoInit[34] = true;
                    WXLogUtils.w("Stopwatch", "Should call Stopwatch.tick() before Stopwatch.tack() called");
                    $jacocoInit[35] = true;
                }
                $jacocoInit[36] = true;
                sThreadLocal.get().startNanos = 0;
                $jacocoInit[37] = true;
                double nanosToMillis = nanosToMillis(System.nanoTime() - j);
                $jacocoInit[38] = true;
                return nanosToMillis;
            } catch (Throwable th) {
                $jacocoInit[39] = true;
                th.printStackTrace();
                $jacocoInit[40] = true;
            }
        }
        $jacocoInit[41] = true;
        return -1.0d;
    }

    public static long lastTickStamp() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!WXTracing.isAvailable()) {
            $jacocoInit[42] = true;
        } else {
            try {
                $jacocoInit[43] = true;
                long j = sThreadLocal.get().startMillis;
                $jacocoInit[44] = true;
                return j;
            } catch (Throwable th) {
                $jacocoInit[45] = true;
                th.printStackTrace();
                $jacocoInit[46] = true;
            }
        }
        $jacocoInit[47] = true;
        return -1;
    }

    public static double tackAndTick() {
        boolean[] $jacocoInit = $jacocoInit();
        double tack = tack();
        $jacocoInit[48] = true;
        tick();
        $jacocoInit[49] = true;
        return tack;
    }

    public static double nanosToMillis(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        double d = (double) j;
        Double.isNaN(d);
        double d2 = d / 1000000.0d;
        $jacocoInit[50] = true;
        return d2;
    }

    public static double millisUntilNow(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        double nanosToMillis = nanosToMillis(System.nanoTime() - j);
        $jacocoInit[51] = true;
        return nanosToMillis;
    }

    public static class ProcessEvent {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public double duration;
        public String fname;
        public long startMillis;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3855020881448077334L, "com/taobao/weex/tracing/Stopwatch$ProcessEvent", 1);
            $jacocoData = a2;
            return a2;
        }

        public ProcessEvent() {
            $jacocoInit()[0] = true;
        }
    }
}
