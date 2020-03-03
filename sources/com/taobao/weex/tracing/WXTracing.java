package com.taobao.weex.tracing;

import android.os.Looper;
import android.util.SparseArray;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.ITracingAdapter;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTracing {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static final AtomicInteger sIdGenerator = new AtomicInteger(0);

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1396406861233306489L, "com/taobao/weex/tracing/WXTracing", 16);
        $jacocoData = a2;
        return a2;
    }

    public WXTracing() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[15] = true;
    }

    public static int nextId() {
        boolean[] $jacocoInit = $jacocoInit();
        int andIncrement = sIdGenerator.getAndIncrement();
        $jacocoInit[1] = true;
        return andIncrement;
    }

    public static boolean isAvailable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isApkDebugable = WXEnvironment.isApkDebugable();
        $jacocoInit[2] = true;
        return isApkDebugable;
    }

    public static synchronized void submit(TraceEvent traceEvent) {
        synchronized (WXTracing.class) {
            boolean[] $jacocoInit = $jacocoInit();
            ITracingAdapter tracingAdapter = WXSDKManager.getInstance().getTracingAdapter();
            if (tracingAdapter == null) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                tracingAdapter.submitTracingEvent(traceEvent);
                $jacocoInit[5] = true;
            }
            $jacocoInit[6] = true;
        }
    }

    public static class TraceEvent {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public String classname;
        public double duration;
        public Map<String, Object> extParams;
        public boolean firstScreenFinish;
        public String fname;
        public String iid;
        public boolean isSegment;
        public String name;
        public int parentId = -1;
        public String parentRef;
        public double parseJsonTime;
        public String payload;
        public String ph;
        public String ref;
        public SparseArray<TraceEvent> subEvents;
        private boolean submitted;
        public String tname;
        public int traceId;
        public long ts;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8449799287984359785L, "com/taobao/weex/tracing/WXTracing$TraceEvent", 8);
            $jacocoData = a2;
            return a2;
        }

        public TraceEvent() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.ts = System.currentTimeMillis();
            $jacocoInit[1] = true;
            this.traceId = WXTracing.nextId();
            $jacocoInit[2] = true;
            this.tname = WXTracing.currentThreadName();
            $jacocoInit[3] = true;
        }

        public void submit() {
            boolean[] $jacocoInit = $jacocoInit();
            if (!this.submitted) {
                this.submitted = true;
                $jacocoInit[4] = true;
                WXTracing.submit(this);
                $jacocoInit[5] = true;
            } else {
                WXLogUtils.w("WXTracing", "Event " + this.traceId + " has been submitted.");
                $jacocoInit[6] = true;
            }
            $jacocoInit[7] = true;
        }
    }

    public static String currentThreadName() {
        boolean[] $jacocoInit = $jacocoInit();
        Thread currentThread = Thread.currentThread();
        $jacocoInit[7] = true;
        String name = currentThread.getName();
        $jacocoInit[8] = true;
        if ("WeexJSBridgeThread".equals(name)) {
            $jacocoInit[9] = true;
            return "JSThread";
        } else if ("WeeXDomThread".equals(name)) {
            $jacocoInit[10] = true;
            return "DOMThread";
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            $jacocoInit[11] = true;
            return "UIThread";
        } else {
            $jacocoInit[12] = true;
            return name;
        }
    }

    public static TraceEvent newEvent(String str, String str2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        TraceEvent traceEvent = new TraceEvent();
        traceEvent.fname = str;
        traceEvent.iid = str2;
        $jacocoInit[13] = true;
        traceEvent.traceId = nextId();
        traceEvent.parentId = i;
        $jacocoInit[14] = true;
        return traceEvent;
    }

    public static class TraceInfo {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public long domQueueTime;
        public long domThreadNanos;
        public long domThreadStart = -1;
        public int rootEventId;
        public long uiQueueTime;
        public long uiThreadNanos;
        public long uiThreadStart = -1;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(1887391122304095042L, "com/taobao/weex/tracing/WXTracing$TraceInfo", 1);
            $jacocoData = a2;
            return a2;
        }

        public TraceInfo() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }
    }
}
