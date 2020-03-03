package com.taobao.weex.dom.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.animation.BackgroundColorProperty;
import com.taobao.weex.ui.animation.TransformParser;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.SingleFunctionParser;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTransition {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final Set<String> LAYOUT_PROPERTIES = new HashSet();
    public static final Pattern PROPERTY_SPLIT_PATTERN = Pattern.compile("\\||,");
    private static final Set<String> TRANSFORM_PROPERTIES = new HashSet();
    public static final String TRANSITION_DELAY = "transitionDelay";
    public static final String TRANSITION_DURATION = "transitionDuration";
    public static final String TRANSITION_PROPERTY = "transitionProperty";
    public static final String TRANSITION_TIMING_FUNCTION = "transitionTimingFunction";
    private Runnable animationRunnable;
    private long delay;
    private long duration;
    private Handler handler;
    private Interpolator interpolator;
    private Map<String, Object> layoutPendingUpdates;
    private ValueAnimator layoutValueAnimator;
    private volatile AtomicInteger lockToken = new AtomicInteger(0);
    private WXComponent mWXComponent;
    private List<String> properties;
    private Map<String, Object> targetStyles;
    private Runnable transformAnimationRunnable;
    private ObjectAnimator transformAnimator;
    private Map<String, Object> transformPendingUpdates;
    private Runnable transitionEndEvent;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4475360385780806013L, "com/taobao/weex/dom/transition/WXTransition", 360);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ AtomicInteger access$000(WXTransition wXTransition) {
        boolean[] $jacocoInit = $jacocoInit();
        AtomicInteger atomicInteger = wXTransition.lockToken;
        $jacocoInit[332] = true;
        return atomicInteger;
    }

    static /* synthetic */ void access$100(WXTransition wXTransition, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXTransition.doTransitionAnimation(i);
        $jacocoInit[333] = true;
    }

    static /* synthetic */ Runnable access$202(WXTransition wXTransition, Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        wXTransition.animationRunnable = runnable;
        $jacocoInit[334] = true;
        return runnable;
    }

    static /* synthetic */ Runnable access$302(WXTransition wXTransition, Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        wXTransition.transitionEndEvent = runnable;
        $jacocoInit[335] = true;
        return runnable;
    }

    static /* synthetic */ long access$400(WXTransition wXTransition) {
        boolean[] $jacocoInit = $jacocoInit();
        long j = wXTransition.duration;
        $jacocoInit[336] = true;
        return j;
    }

    static /* synthetic */ WXComponent access$500(WXTransition wXTransition) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = wXTransition.mWXComponent;
        $jacocoInit[337] = true;
        return wXComponent;
    }

    static /* synthetic */ void access$600(WXTransition wXTransition, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXTransition.doPendingTransformAnimation(i);
        $jacocoInit[338] = true;
    }

    static /* synthetic */ void access$700(WXTransition wXTransition) {
        boolean[] $jacocoInit = $jacocoInit();
        wXTransition.onTransitionAnimationEnd();
        $jacocoInit[339] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[340] = true;
        $jacocoInit[341] = true;
        LAYOUT_PROPERTIES.add("width");
        $jacocoInit[342] = true;
        LAYOUT_PROPERTIES.add("height");
        $jacocoInit[343] = true;
        LAYOUT_PROPERTIES.add("marginTop");
        $jacocoInit[344] = true;
        LAYOUT_PROPERTIES.add("marginBottom");
        $jacocoInit[345] = true;
        LAYOUT_PROPERTIES.add("marginLeft");
        $jacocoInit[346] = true;
        LAYOUT_PROPERTIES.add("marginRight");
        $jacocoInit[347] = true;
        LAYOUT_PROPERTIES.add("left");
        $jacocoInit[348] = true;
        LAYOUT_PROPERTIES.add("right");
        $jacocoInit[349] = true;
        LAYOUT_PROPERTIES.add("top");
        $jacocoInit[350] = true;
        LAYOUT_PROPERTIES.add("bottom");
        $jacocoInit[351] = true;
        LAYOUT_PROPERTIES.add("paddingLeft");
        $jacocoInit[352] = true;
        LAYOUT_PROPERTIES.add("paddingRight");
        $jacocoInit[353] = true;
        LAYOUT_PROPERTIES.add("paddingTop");
        $jacocoInit[354] = true;
        LAYOUT_PROPERTIES.add("paddingBottom");
        $jacocoInit[355] = true;
        $jacocoInit[356] = true;
        TRANSFORM_PROPERTIES.add("opacity");
        $jacocoInit[357] = true;
        TRANSFORM_PROPERTIES.add("backgroundColor");
        $jacocoInit[358] = true;
        TRANSFORM_PROPERTIES.add("transform");
        $jacocoInit[359] = true;
    }

    public WXTransition() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.properties = new ArrayList(4);
        $jacocoInit[2] = true;
        this.handler = new Handler();
        $jacocoInit[3] = true;
        this.layoutPendingUpdates = new ArrayMap();
        $jacocoInit[4] = true;
        this.transformPendingUpdates = new ArrayMap();
        $jacocoInit[5] = true;
        this.targetStyles = new ArrayMap();
        $jacocoInit[6] = true;
    }

    public static WXTransition fromMap(Map<String, Object> map, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map.get(TRANSITION_PROPERTY) == null) {
            $jacocoInit[7] = true;
            return null;
        }
        String string = WXUtils.getString(map.get(TRANSITION_PROPERTY), (String) null);
        if (string == null) {
            $jacocoInit[8] = true;
            return null;
        }
        WXTransition wXTransition = new WXTransition();
        $jacocoInit[9] = true;
        updateTransitionProperties(wXTransition, string);
        $jacocoInit[10] = true;
        if (wXTransition.properties.isEmpty()) {
            $jacocoInit[11] = true;
            return null;
        }
        wXTransition.duration = parseTimeMillis(map, TRANSITION_DURATION, 0);
        $jacocoInit[12] = true;
        wXTransition.delay = parseTimeMillis(map, TRANSITION_DELAY, 0);
        $jacocoInit[13] = true;
        wXTransition.interpolator = createTimeInterpolator(WXUtils.getString(map.get(TRANSITION_TIMING_FUNCTION), (String) null));
        wXTransition.mWXComponent = wXComponent;
        $jacocoInit[14] = true;
        return wXTransition;
    }

    public boolean hasTransitionProperty(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[15] = true;
        for (String containsKey : this.properties) {
            $jacocoInit[16] = true;
            if (map.containsKey(containsKey)) {
                $jacocoInit[17] = true;
                return true;
            }
            $jacocoInit[18] = true;
        }
        $jacocoInit[19] = true;
        return false;
    }

    public void updateTranstionParams(Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!map.containsKey(TRANSITION_DELAY)) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[21] = true;
            this.mWXComponent.getStyles().put(TRANSITION_DELAY, map.remove(TRANSITION_DELAY));
            $jacocoInit[22] = true;
            this.delay = parseTimeMillis(this.mWXComponent.getStyles(), TRANSITION_DELAY, 0);
            $jacocoInit[23] = true;
        }
        if (!map.containsKey(TRANSITION_TIMING_FUNCTION)) {
            $jacocoInit[24] = true;
        } else if (map.get(TRANSITION_TIMING_FUNCTION) == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            this.mWXComponent.getStyles().put(TRANSITION_TIMING_FUNCTION, map.remove(TRANSITION_TIMING_FUNCTION));
            $jacocoInit[27] = true;
            this.interpolator = createTimeInterpolator(this.mWXComponent.getStyles().get(TRANSITION_TIMING_FUNCTION).toString());
            $jacocoInit[28] = true;
        }
        if (!map.containsKey(TRANSITION_DURATION)) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            this.mWXComponent.getStyles().put(TRANSITION_DURATION, map.remove(TRANSITION_DURATION));
            $jacocoInit[31] = true;
            this.duration = parseTimeMillis(this.mWXComponent.getStyles(), TRANSITION_DURATION, 0);
            $jacocoInit[32] = true;
        }
        if (!map.containsKey(TRANSITION_PROPERTY)) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            this.mWXComponent.getStyles().put(TRANSITION_PROPERTY, map.remove(TRANSITION_PROPERTY));
            $jacocoInit[35] = true;
            updateTransitionProperties(this, WXUtils.getString(this.mWXComponent.getStyles().get(TRANSITION_PROPERTY), (String) null));
            $jacocoInit[36] = true;
        }
        $jacocoInit[37] = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e7, code lost:
        r0[62] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00eb, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startTransition(java.util.Map<java.lang.String, java.lang.Object> r10) {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            java.util.concurrent.atomic.AtomicInteger r1 = r9.lockToken
            monitor-enter(r1)
            r2 = 38
            r3 = 1
            r0[r2] = r3     // Catch:{ all -> 0x00f2 }
            android.view.View r2 = r9.getTargetView()     // Catch:{ all -> 0x00f2 }
            if (r2 == 0) goto L_0x00ec
            r2 = 39
            r0[r2] = r3     // Catch:{ all -> 0x00f2 }
            java.util.concurrent.atomic.AtomicInteger r2 = r9.lockToken     // Catch:{ all -> 0x00f2 }
            int r2 = r2.incrementAndGet()     // Catch:{ all -> 0x00f2 }
            r4 = 41
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
            java.util.List<java.lang.String> r4 = r9.properties     // Catch:{ all -> 0x00f2 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00f2 }
            r5 = 42
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
        L_0x002a:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0086
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00f2 }
            r6 = 43
            r0[r6] = r3     // Catch:{ all -> 0x00f2 }
            boolean r6 = r10.containsKey(r5)     // Catch:{ all -> 0x00f2 }
            if (r6 != 0) goto L_0x0045
            r5 = 44
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x0081
        L_0x0045:
            r6 = 45
            r0[r6] = r3     // Catch:{ all -> 0x00f2 }
            java.lang.Object r6 = r10.remove(r5)     // Catch:{ all -> 0x00f2 }
            r7 = 46
            r0[r7] = r3     // Catch:{ all -> 0x00f2 }
            java.util.Set<java.lang.String> r7 = LAYOUT_PROPERTIES     // Catch:{ all -> 0x00f2 }
            boolean r7 = r7.contains(r5)     // Catch:{ all -> 0x00f2 }
            if (r7 == 0) goto L_0x0067
            r7 = 47
            r0[r7] = r3     // Catch:{ all -> 0x00f2 }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r9.layoutPendingUpdates     // Catch:{ all -> 0x00f2 }
            r7.put(r5, r6)     // Catch:{ all -> 0x00f2 }
            r5 = 48
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x0081
        L_0x0067:
            java.util.Set<java.lang.String> r7 = TRANSFORM_PROPERTIES     // Catch:{ all -> 0x00f2 }
            boolean r7 = r7.contains(r5)     // Catch:{ all -> 0x00f2 }
            if (r7 != 0) goto L_0x0074
            r5 = 49
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x0081
        L_0x0074:
            r7 = 50
            r0[r7] = r3     // Catch:{ all -> 0x00f2 }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r9.transformPendingUpdates     // Catch:{ all -> 0x00f2 }
            r7.put(r5, r6)     // Catch:{ all -> 0x00f2 }
            r5 = 51
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
        L_0x0081:
            r5 = 52
            r0[r5] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x002a
        L_0x0086:
            com.taobao.weex.ui.component.WXComponent r10 = r9.mWXComponent     // Catch:{ all -> 0x00f2 }
            com.taobao.weex.dom.WXAttr r10 = r10.getAttrs()     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = "actionDelay"
            java.lang.Object r10 = r10.get(r4)     // Catch:{ all -> 0x00f2 }
            r4 = 16
            int r10 = com.taobao.weex.utils.WXUtils.getNumberInt(r10, r4)     // Catch:{ all -> 0x00f2 }
            long r4 = (long) r10     // Catch:{ all -> 0x00f2 }
            long r6 = r9.duration     // Catch:{ all -> 0x00f2 }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x00a4
            r4 = 53
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x00ab
        L_0x00a4:
            long r4 = r9.duration     // Catch:{ all -> 0x00f2 }
            int r10 = (int) r4     // Catch:{ all -> 0x00f2 }
            r4 = 54
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
        L_0x00ab:
            java.lang.Runnable r4 = r9.animationRunnable     // Catch:{ all -> 0x00f2 }
            if (r4 != 0) goto L_0x00b4
            r4 = 55
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x00c3
        L_0x00b4:
            r4 = 56
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
            android.os.Handler r4 = r9.handler     // Catch:{ all -> 0x00f2 }
            java.lang.Runnable r5 = r9.animationRunnable     // Catch:{ all -> 0x00f2 }
            r4.removeCallbacks(r5)     // Catch:{ all -> 0x00f2 }
            r4 = 57
            r0[r4] = r3     // Catch:{ all -> 0x00f2 }
        L_0x00c3:
            com.taobao.weex.dom.transition.WXTransition$1 r4 = new com.taobao.weex.dom.transition.WXTransition$1     // Catch:{ all -> 0x00f2 }
            r4.<init>(r9, r2)     // Catch:{ all -> 0x00f2 }
            r9.animationRunnable = r4     // Catch:{ all -> 0x00f2 }
            if (r10 <= 0) goto L_0x00dd
            r2 = 58
            r0[r2] = r3     // Catch:{ all -> 0x00f2 }
            android.os.Handler r2 = r9.handler     // Catch:{ all -> 0x00f2 }
            java.lang.Runnable r4 = r9.animationRunnable     // Catch:{ all -> 0x00f2 }
            long r5 = (long) r10     // Catch:{ all -> 0x00f2 }
            r2.postDelayed(r4, r5)     // Catch:{ all -> 0x00f2 }
            r10 = 59
            r0[r10] = r3     // Catch:{ all -> 0x00f2 }
            goto L_0x00e6
        L_0x00dd:
            java.lang.Runnable r10 = r9.animationRunnable     // Catch:{ all -> 0x00f2 }
            r10.run()     // Catch:{ all -> 0x00f2 }
            r10 = 60
            r0[r10] = r3     // Catch:{ all -> 0x00f2 }
        L_0x00e6:
            monitor-exit(r1)     // Catch:{ all -> 0x00f2 }
            r10 = 62
            r0[r10] = r3
            return
        L_0x00ec:
            monitor-exit(r1)     // Catch:{ all -> 0x00f2 }
            r10 = 40
            r0[r10] = r3
            return
        L_0x00f2:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00f2 }
            r1 = 61
            r0[r1] = r3
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.transition.WXTransition.startTransition(java.util.Map):void");
    }

    private void doTransitionAnimation(final int i) {
        boolean[] $jacocoInit = $jacocoInit();
        View targetView = getTargetView();
        if (targetView == null) {
            $jacocoInit[63] = true;
            return;
        }
        if (this.targetStyles.size() <= 0) {
            $jacocoInit[64] = true;
        } else {
            $jacocoInit[65] = true;
            $jacocoInit[66] = true;
            for (String next : this.properties) {
                $jacocoInit[68] = true;
                if (LAYOUT_PROPERTIES.contains(next)) {
                    $jacocoInit[69] = true;
                } else if (TRANSFORM_PROPERTIES.contains(next)) {
                    $jacocoInit[70] = true;
                } else {
                    $jacocoInit[71] = true;
                }
                if (this.layoutPendingUpdates.containsKey(next)) {
                    $jacocoInit[72] = true;
                } else if (this.transformPendingUpdates.containsKey(next)) {
                    $jacocoInit[73] = true;
                } else {
                    synchronized (this.targetStyles) {
                        try {
                            $jacocoInit[74] = true;
                            if (!this.targetStyles.containsKey(next)) {
                                $jacocoInit[75] = true;
                            } else {
                                $jacocoInit[76] = true;
                                Object remove = this.targetStyles.remove(next);
                                $jacocoInit[77] = true;
                                this.mWXComponent.getStyles().put(next, remove);
                                $jacocoInit[78] = true;
                            }
                        } catch (Throwable th) {
                            while (true) {
                                $jacocoInit[79] = true;
                                throw th;
                            }
                        }
                    }
                    $jacocoInit[80] = true;
                }
            }
            $jacocoInit[67] = true;
        }
        if (this.transitionEndEvent == null) {
            $jacocoInit[81] = true;
        } else {
            $jacocoInit[82] = true;
            targetView.removeCallbacks(this.transitionEndEvent);
            $jacocoInit[83] = true;
        }
        if (this.transitionEndEvent != null) {
            $jacocoInit[84] = true;
        } else if (((float) this.duration) <= Float.MIN_NORMAL) {
            $jacocoInit[85] = true;
        } else {
            $jacocoInit[86] = true;
            this.transitionEndEvent = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXTransition this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-3956825839506100145L, "com/taobao/weex/dom/transition/WXTransition$2", 8);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    WXTransition.access$302(this.this$0, (Runnable) null);
                    $jacocoInit[1] = true;
                    if (((float) WXTransition.access$400(this.this$0)) < Float.MIN_NORMAL) {
                        $jacocoInit[2] = true;
                        return;
                    }
                    if (WXTransition.access$500(this.this$0) == null) {
                        $jacocoInit[3] = true;
                    } else if (!WXTransition.access$500(this.this$0).getEvents().contains(Constants.Event.ON_TRANSITION_END)) {
                        $jacocoInit[4] = true;
                    } else {
                        $jacocoInit[5] = true;
                        WXTransition.access$500(this.this$0).fireEvent(Constants.Event.ON_TRANSITION_END);
                        $jacocoInit[6] = true;
                    }
                    $jacocoInit[7] = true;
                }
            };
            $jacocoInit[87] = true;
        }
        if (this.transformAnimationRunnable == null) {
            $jacocoInit[88] = true;
        } else {
            $jacocoInit[89] = true;
            targetView.removeCallbacks(this.transformAnimationRunnable);
            $jacocoInit[90] = true;
        }
        this.transformAnimationRunnable = new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXTransition this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(2695569671139233118L, "com/taobao/weex/dom/transition/WXTransition$3", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                synchronized (WXTransition.access$000(this.this$0)) {
                    try {
                        $jacocoInit[1] = true;
                        if (i != WXTransition.access$000(this.this$0).get()) {
                            $jacocoInit[2] = true;
                        } else {
                            $jacocoInit[3] = true;
                            WXTransition.access$600(this.this$0, i);
                            $jacocoInit[4] = true;
                        }
                    } catch (Throwable th) {
                        while (true) {
                            $jacocoInit[5] = true;
                            throw th;
                        }
                    }
                }
                $jacocoInit[6] = true;
            }
        };
        $jacocoInit[91] = true;
        targetView.post(this.transformAnimationRunnable);
        $jacocoInit[92] = true;
        doPendingLayoutAnimation();
        $jacocoInit[93] = true;
    }

    private void doPendingTransformAnimation(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.transformAnimator == null) {
            $jacocoInit[94] = true;
        } else {
            $jacocoInit[95] = true;
            this.transformAnimator.cancel();
            this.transformAnimator = null;
            $jacocoInit[96] = true;
        }
        if (this.transformPendingUpdates.size() == 0) {
            $jacocoInit[97] = true;
            return;
        }
        View targetView = getTargetView();
        if (targetView == null) {
            $jacocoInit[98] = true;
            return;
        }
        ArrayList arrayList = new ArrayList(8);
        $jacocoInit[99] = true;
        String string = WXUtils.getString(this.transformPendingUpdates.remove("transform"), (String) null);
        $jacocoInit[100] = true;
        if (TextUtils.isEmpty(string)) {
            $jacocoInit[101] = true;
        } else {
            $jacocoInit[102] = true;
            Map<Property<View, Float>, Float> parseTransForm = TransformParser.parseTransForm(this.mWXComponent.getInstanceId(), string, (int) this.mWXComponent.getLayoutWidth(), (int) this.mWXComponent.getLayoutHeight(), this.mWXComponent.getViewPortWidth());
            $jacocoInit[103] = true;
            PropertyValuesHolder[] holders = TransformParser.toHolders(parseTransForm);
            int length = holders.length;
            $jacocoInit[104] = true;
            int i2 = 0;
            while (i2 < length) {
                PropertyValuesHolder propertyValuesHolder = holders[i2];
                $jacocoInit[105] = true;
                arrayList.add(propertyValuesHolder);
                i2++;
                $jacocoInit[106] = true;
            }
            synchronized (this.targetStyles) {
                try {
                    $jacocoInit[107] = true;
                    this.targetStyles.put("transform", string);
                } catch (Throwable th) {
                    while (true) {
                        $jacocoInit[109] = true;
                        throw th;
                    }
                }
            }
            $jacocoInit[108] = true;
        }
        $jacocoInit[110] = true;
        for (String next : this.properties) {
            $jacocoInit[111] = true;
            if (!TRANSFORM_PROPERTIES.contains(next)) {
                $jacocoInit[112] = true;
            } else if (!this.transformPendingUpdates.containsKey(next)) {
                $jacocoInit[113] = true;
            } else {
                Object remove = this.transformPendingUpdates.remove(next);
                synchronized (this.targetStyles) {
                    try {
                        $jacocoInit[114] = true;
                        this.targetStyles.put(next, remove);
                    } catch (Throwable th2) {
                        while (true) {
                            $jacocoInit[115] = true;
                            throw th2;
                        }
                    }
                }
                char c = 65535;
                int hashCode = next.hashCode();
                if (hashCode != -1267206133) {
                    if (hashCode != 1287124693) {
                        $jacocoInit[116] = true;
                    } else if (!next.equals("backgroundColor")) {
                        $jacocoInit[119] = true;
                    } else {
                        $jacocoInit[120] = true;
                        c = 1;
                    }
                } else if (!next.equals("opacity")) {
                    $jacocoInit[117] = true;
                } else {
                    $jacocoInit[118] = true;
                    c = 0;
                }
                switch (c) {
                    case 0:
                        arrayList.add(PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{targetView.getAlpha(), WXUtils.getFloat(remove, Float.valueOf(1.0f)).floatValue()}));
                        $jacocoInit[122] = true;
                        targetView.setLayerType(1, (Paint) null);
                        $jacocoInit[123] = true;
                        break;
                    case 1:
                        int color = WXResourceUtils.getColor(WXUtils.getString(this.mWXComponent.getStyles().getBackgroundColor(), (String) null), 0);
                        $jacocoInit[124] = true;
                        int color2 = WXResourceUtils.getColor(WXUtils.getString(remove, (String) null), 0);
                        $jacocoInit[125] = true;
                        if (WXViewUtils.getBorderDrawable(targetView) != null) {
                            $jacocoInit[126] = true;
                            color = WXViewUtils.getBorderDrawable(targetView).getColor();
                            $jacocoInit[127] = true;
                        } else if (!(targetView.getBackground() instanceof ColorDrawable)) {
                            $jacocoInit[128] = true;
                        } else {
                            $jacocoInit[129] = true;
                            color = ((ColorDrawable) targetView.getBackground()).getColor();
                            $jacocoInit[130] = true;
                        }
                        arrayList.add(PropertyValuesHolder.ofObject(new BackgroundColorProperty(), new ArgbEvaluator(), new Integer[]{Integer.valueOf(color), Integer.valueOf(color2)}));
                        $jacocoInit[131] = true;
                        break;
                    default:
                        $jacocoInit[121] = true;
                        break;
                }
                $jacocoInit[132] = true;
            }
        }
        if (i != this.lockToken.get()) {
            $jacocoInit[133] = true;
        } else {
            $jacocoInit[134] = true;
            this.transformPendingUpdates.clear();
            $jacocoInit[135] = true;
        }
        this.transformAnimator = ObjectAnimator.ofPropertyValuesHolder(targetView, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
        $jacocoInit[136] = true;
        this.transformAnimator.setDuration(this.duration);
        if (this.delay <= 0) {
            $jacocoInit[137] = true;
        } else {
            $jacocoInit[138] = true;
            this.transformAnimator.setStartDelay(this.delay);
            $jacocoInit[139] = true;
        }
        if (this.interpolator == null) {
            $jacocoInit[140] = true;
        } else {
            $jacocoInit[141] = true;
            this.transformAnimator.setInterpolator(this.interpolator);
            $jacocoInit[142] = true;
        }
        this.transformAnimator.addListener(new AnimatorListenerAdapter(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            boolean hasCancel = false;
            final /* synthetic */ WXTransition this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(4705054222928251843L, "com/taobao/weex/dom/transition/WXTransition$4", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationCancel(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onAnimationCancel(animator);
                this.hasCancel = true;
                $jacocoInit[1] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                if (this.hasCancel) {
                    $jacocoInit[2] = true;
                    return;
                }
                super.onAnimationEnd(animator);
                $jacocoInit[3] = true;
                WXTransition.access$700(this.this$0);
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[143] = true;
        this.transformAnimator.start();
        $jacocoInit[144] = true;
    }

    public void doPendingLayoutAnimation() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.layoutValueAnimator == null) {
            $jacocoInit[145] = true;
        } else {
            $jacocoInit[146] = true;
            this.layoutValueAnimator.cancel();
            this.layoutValueAnimator = null;
            $jacocoInit[147] = true;
        }
        if (this.layoutPendingUpdates.size() == 0) {
            $jacocoInit[148] = true;
            return;
        }
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[this.layoutPendingUpdates.size()];
        int i = 0;
        $jacocoInit[149] = true;
        $jacocoInit[150] = true;
        for (String next : this.properties) {
            $jacocoInit[151] = true;
            if (!LAYOUT_PROPERTIES.contains(next)) {
                $jacocoInit[152] = true;
            } else {
                if (!this.layoutPendingUpdates.containsKey(next)) {
                    $jacocoInit[153] = true;
                } else {
                    $jacocoInit[154] = true;
                    Object remove = this.layoutPendingUpdates.remove(next);
                    synchronized (this.targetStyles) {
                        try {
                            $jacocoInit[155] = true;
                            this.targetStyles.put(next, remove);
                        } catch (Throwable th) {
                            while (true) {
                                $jacocoInit[156] = true;
                                throw th;
                            }
                        }
                    }
                    propertyValuesHolderArr[i] = createLayoutPropertyValueHolder(next, remove);
                    i++;
                    $jacocoInit[157] = true;
                }
                $jacocoInit[158] = true;
            }
        }
        this.layoutPendingUpdates.clear();
        $jacocoInit[159] = true;
        doLayoutPropertyValuesHolderAnimation(propertyValuesHolderArr);
        $jacocoInit[160] = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.animation.PropertyValuesHolder createLayoutPropertyValueHolder(java.lang.String r9, java.lang.Object r10) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 1
            r2 = 161(0xa1, float:2.26E-43)
            r0[r2] = r1
            int r2 = r9.hashCode()
            r3 = 0
            r4 = 2
            switch(r2) {
                case -1501175880: goto L_0x0125;
                case -1383228885: goto L_0x0111;
                case -1221029593: goto L_0x00fe;
                case -1044792121: goto L_0x00eb;
                case -289173127: goto L_0x00d8;
                case 115029: goto L_0x00c3;
                case 3317767: goto L_0x00ae;
                case 90130308: goto L_0x0098;
                case 108511772: goto L_0x0083;
                case 113126854: goto L_0x006e;
                case 202355100: goto L_0x0058;
                case 713848971: goto L_0x0042;
                case 975087886: goto L_0x002d;
                case 1970934485: goto L_0x0018;
                default: goto L_0x0012;
            }
        L_0x0012:
            r2 = 162(0xa2, float:2.27E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0018:
            java.lang.String r2 = "marginLeft"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x0026
            r2 = 169(0xa9, float:2.37E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0026:
            r2 = 3
            r5 = 170(0xaa, float:2.38E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x002d:
            java.lang.String r2 = "marginRight"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x003b
            r2 = 171(0xab, float:2.4E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x003b:
            r2 = 4
            r5 = 172(0xac, float:2.41E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x0042:
            java.lang.String r2 = "paddingRight"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x0050
            r2 = 189(0xbd, float:2.65E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0050:
            r2 = 13
            r5 = 190(0xbe, float:2.66E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x0058:
            java.lang.String r2 = "paddingBottom"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x0066
            r2 = 185(0xb9, float:2.59E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0066:
            r2 = 11
            r5 = 186(0xba, float:2.6E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x006e:
            java.lang.String r2 = "width"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x007c
            r2 = 163(0xa3, float:2.28E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x007c:
            r2 = 164(0xa4, float:2.3E-43)
            r0[r2] = r1
            r2 = 0
            goto L_0x013a
        L_0x0083:
            java.lang.String r2 = "right"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x0091
            r2 = 177(0xb1, float:2.48E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0091:
            r2 = 7
            r5 = 178(0xb2, float:2.5E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x0098:
            java.lang.String r2 = "paddingTop"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x00a6
            r2 = 183(0xb7, float:2.56E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x00a6:
            r2 = 10
            r5 = 184(0xb8, float:2.58E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x00ae:
            java.lang.String r2 = "left"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x00bc
            r2 = 175(0xaf, float:2.45E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x00bc:
            r2 = 6
            r5 = 176(0xb0, float:2.47E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x00c3:
            java.lang.String r2 = "top"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x00d1
            r2 = 181(0xb5, float:2.54E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x00d1:
            r2 = 9
            r5 = 182(0xb6, float:2.55E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x00d8:
            java.lang.String r2 = "marginBottom"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x00e5
            r2 = 173(0xad, float:2.42E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x00e5:
            r2 = 5
            r5 = 174(0xae, float:2.44E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x00eb:
            java.lang.String r2 = "marginTop"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x00f8
            r2 = 167(0xa7, float:2.34E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x00f8:
            r2 = 168(0xa8, float:2.35E-43)
            r0[r2] = r1
            r2 = 2
            goto L_0x013a
        L_0x00fe:
            java.lang.String r2 = "height"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x010b
            r2 = 165(0xa5, float:2.31E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x010b:
            r2 = 166(0xa6, float:2.33E-43)
            r0[r2] = r1
            r2 = 1
            goto L_0x013a
        L_0x0111:
            java.lang.String r2 = "bottom"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x011e
            r2 = 179(0xb3, float:2.51E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x011e:
            r2 = 8
            r5 = 180(0xb4, float:2.52E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x0125:
            java.lang.String r2 = "paddingLeft"
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L_0x0132
            r2 = 187(0xbb, float:2.62E-43)
            r0[r2] = r1
            goto L_0x0139
        L_0x0132:
            r2 = 12
            r5 = 188(0xbc, float:2.63E-43)
            r0[r5] = r1
            goto L_0x013a
        L_0x0139:
            r2 = -1
        L_0x013a:
            r5 = 0
            switch(r2) {
                case 0: goto L_0x0429;
                case 1: goto L_0x03f4;
                case 2: goto L_0x03bb;
                case 3: goto L_0x0381;
                case 4: goto L_0x0347;
                case 5: goto L_0x030d;
                case 6: goto L_0x02d5;
                case 7: goto L_0x029d;
                case 8: goto L_0x0265;
                case 9: goto L_0x022d;
                case 10: goto L_0x01f3;
                case 11: goto L_0x01b9;
                case 12: goto L_0x017f;
                case 13: goto L_0x0145;
                default: goto L_0x013e;
            }
        L_0x013e:
            r10 = 191(0xbf, float:2.68E-43)
            r0[r10] = r1
            r10 = 0
            goto L_0x045d
        L_0x0145:
            java.lang.String r2 = "paddingRight"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 231(0xe7, float:3.24E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 232(0xe8, float:3.25E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 233(0xe9, float:3.27E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x017f:
            java.lang.String r2 = "paddingLeft"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 228(0xe4, float:3.2E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 229(0xe5, float:3.21E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 230(0xe6, float:3.22E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x01b9:
            java.lang.String r2 = "paddingBottom"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 225(0xe1, float:3.15E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 226(0xe2, float:3.17E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 227(0xe3, float:3.18E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x01f3:
            java.lang.String r2 = "paddingTop"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getPadding()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 222(0xde, float:3.11E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 223(0xdf, float:3.12E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 224(0xe0, float:3.14E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x022d:
            java.lang.String r2 = "top"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.ui.action.GraphicPosition r6 = r6.getLayoutPosition()
            float r6 = r6.getTop()
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 219(0xdb, float:3.07E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 220(0xdc, float:3.08E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 221(0xdd, float:3.1E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x0265:
            java.lang.String r2 = "bottom"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.ui.action.GraphicPosition r6 = r6.getLayoutPosition()
            float r6 = r6.getBottom()
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 216(0xd8, float:3.03E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 217(0xd9, float:3.04E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 218(0xda, float:3.05E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x029d:
            java.lang.String r2 = "right"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.ui.action.GraphicPosition r6 = r6.getLayoutPosition()
            float r6 = r6.getRight()
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 213(0xd5, float:2.98E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 214(0xd6, float:3.0E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 215(0xd7, float:3.01E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x02d5:
            java.lang.String r2 = "left"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.ui.action.GraphicPosition r6 = r6.getLayoutPosition()
            float r6 = r6.getLeft()
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 210(0xd2, float:2.94E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 211(0xd3, float:2.96E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 212(0xd4, float:2.97E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x030d:
            java.lang.String r2 = "marginBottom"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 207(0xcf, float:2.9E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 208(0xd0, float:2.91E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 209(0xd1, float:2.93E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x0347:
            java.lang.String r2 = "marginRight"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 204(0xcc, float:2.86E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 205(0xcd, float:2.87E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 206(0xce, float:2.89E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x0381:
            java.lang.String r2 = "marginLeft"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 201(0xc9, float:2.82E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 202(0xca, float:2.83E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 203(0xcb, float:2.84E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x03bb:
            java.lang.String r2 = "marginTop"
            float[] r5 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r6 = r8.mWXComponent
            com.taobao.weex.dom.CSSShorthand r6 = r6.getMargin()
            com.taobao.weex.dom.CSSShorthand$EDGE r7 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
            float r6 = r6.get((com.taobao.weex.dom.CSSShorthand.EDGE) r7)
            r5[r3] = r6
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            r6 = 198(0xc6, float:2.77E-43)
            r0[r6] = r1
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXUtils.getFloatByViewport(r10, r3)
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r5[r1] = r10
            r10 = 199(0xc7, float:2.79E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r5)
            r2 = 200(0xc8, float:2.8E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x03f4:
            java.lang.String r2 = "height"
            float[] r6 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r7 = r8.mWXComponent
            float r7 = r7.getLayoutHeight()
            r6[r3] = r7
            r3 = 195(0xc3, float:2.73E-43)
            r0[r3] = r1
            java.lang.Float r3 = java.lang.Float.valueOf(r5)
            java.lang.Float r10 = com.taobao.weex.utils.WXUtils.getFloat(r10, r3)
            float r10 = r10.floatValue()
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r6[r1] = r10
            r10 = 196(0xc4, float:2.75E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r6)
            r2 = 197(0xc5, float:2.76E-43)
            r0[r2] = r1
            goto L_0x045d
        L_0x0429:
            java.lang.String r2 = "width"
            float[] r6 = new float[r4]
            com.taobao.weex.ui.component.WXComponent r7 = r8.mWXComponent
            float r7 = r7.getLayoutWidth()
            r6[r3] = r7
            r3 = 192(0xc0, float:2.69E-43)
            r0[r3] = r1
            java.lang.Float r3 = java.lang.Float.valueOf(r5)
            java.lang.Float r10 = com.taobao.weex.utils.WXUtils.getFloat(r10, r3)
            float r10 = r10.floatValue()
            com.taobao.weex.ui.component.WXComponent r3 = r8.mWXComponent
            int r3 = r3.getViewPortWidth()
            float r10 = com.taobao.weex.utils.WXViewUtils.getRealPxByWidth(r10, r3)
            r6[r1] = r10
            r10 = 193(0xc1, float:2.7E-43)
            r0[r10] = r1
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r2, r6)
            r2 = 194(0xc2, float:2.72E-43)
            r0[r2] = r1
        L_0x045d:
            if (r10 == 0) goto L_0x0464
            r9 = 234(0xea, float:3.28E-43)
            r0[r9] = r1
            goto L_0x0475
        L_0x0464:
            r10 = 235(0xeb, float:3.3E-43)
            r0[r10] = r1
            float[] r10 = new float[r4]
            r10 = {1065353216, 1065353216} // fill-array
            android.animation.PropertyValuesHolder r10 = android.animation.PropertyValuesHolder.ofFloat(r9, r10)
            r9 = 236(0xec, float:3.31E-43)
            r0[r9] = r1
        L_0x0475:
            r9 = 237(0xed, float:3.32E-43)
            r0[r9] = r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.transition.WXTransition.createLayoutPropertyValueHolder(java.lang.String, java.lang.Object):android.animation.PropertyValuesHolder");
    }

    private void doLayoutPropertyValuesHolderAnimation(PropertyValuesHolder[] propertyValuesHolderArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.layoutValueAnimator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolderArr);
        $jacocoInit[238] = true;
        this.layoutValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXTransition this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-6591049082766038933L, "com/taobao/weex/dom/transition/WXTransition$5", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                boolean[] $jacocoInit = $jacocoInit();
                PropertyValuesHolder[] values = valueAnimator.getValues();
                int length = values.length;
                $jacocoInit[1] = true;
                int i = 0;
                while (i < length) {
                    PropertyValuesHolder propertyValuesHolder = values[i];
                    $jacocoInit[2] = true;
                    String propertyName = propertyValuesHolder.getPropertyName();
                    $jacocoInit[3] = true;
                    WXTransition.asynchronouslyUpdateLayout(WXTransition.access$500(this.this$0), propertyName, ((Float) valueAnimator.getAnimatedValue(propertyName)).floatValue());
                    i++;
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[239] = true;
        this.layoutValueAnimator.addListener(new AnimatorListenerAdapter(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            boolean hasCancel = false;
            final /* synthetic */ WXTransition this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3672181756718697032L, "com/taobao/weex/dom/transition/WXTransition$6", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationCancel(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onAnimationCancel(animator);
                this.hasCancel = true;
                $jacocoInit[1] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                if (this.hasCancel) {
                    $jacocoInit[2] = true;
                    return;
                }
                super.onAnimationEnd(animator);
                $jacocoInit[3] = true;
                WXTransition.access$700(this.this$0);
                $jacocoInit[4] = true;
            }
        });
        if (this.interpolator == null) {
            $jacocoInit[240] = true;
        } else {
            $jacocoInit[241] = true;
            this.layoutValueAnimator.setInterpolator(this.interpolator);
            $jacocoInit[242] = true;
        }
        this.layoutValueAnimator.setStartDelay(this.delay);
        $jacocoInit[243] = true;
        this.layoutValueAnimator.setDuration(this.duration);
        $jacocoInit[244] = true;
        this.layoutValueAnimator.start();
        $jacocoInit[245] = true;
    }

    public static void asynchronouslyUpdateLayout(WXComponent wXComponent, final String str, final float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent == null) {
            $jacocoInit[246] = true;
            return;
        }
        final String ref = wXComponent.getRef();
        $jacocoInit[247] = true;
        final String instanceId = wXComponent.getInstanceId();
        $jacocoInit[248] = true;
        if (TextUtils.isEmpty(ref)) {
            $jacocoInit[249] = true;
        } else if (TextUtils.isEmpty(instanceId)) {
            $jacocoInit[250] = true;
        } else {
            WXSDKManager.getInstance().getWXBridgeManager().post(new Runnable() {
                private static transient /* synthetic */ boolean[] $jacocoData;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-3790321604281757047L, "com/taobao/weex/dom/transition/WXTransition$7", 46);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    $jacocoInit[0] = true;
                }

                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r17 = this;
                        r0 = r17
                        boolean[] r1 = $jacocoInit()
                        java.lang.String r2 = r6
                        int r3 = r2.hashCode()
                        r4 = 11
                        r5 = 3
                        r6 = 10
                        r7 = 9
                        r8 = 13
                        r9 = 7
                        r10 = 2
                        r11 = 6
                        r12 = 5
                        r13 = 4
                        r14 = 8
                        r15 = 12
                        r16 = 1
                        switch(r3) {
                            case -1501175880: goto L_0x0119;
                            case -1383228885: goto L_0x0105;
                            case -1221029593: goto L_0x00f6;
                            case -1044792121: goto L_0x00e7;
                            case -289173127: goto L_0x00d8;
                            case 115029: goto L_0x00c4;
                            case 3317767: goto L_0x00af;
                            case 90130308: goto L_0x0099;
                            case 108511772: goto L_0x0084;
                            case 113126854: goto L_0x0073;
                            case 202355100: goto L_0x005f;
                            case 713848971: goto L_0x0049;
                            case 975087886: goto L_0x0038;
                            case 1970934485: goto L_0x0027;
                            default: goto L_0x0023;
                        }
                    L_0x0023:
                        r1[r16] = r16
                        goto L_0x012d
                    L_0x0027:
                        java.lang.String r3 = "marginLeft"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0033
                        r1[r14] = r16
                        goto L_0x012d
                    L_0x0033:
                        r1[r7] = r16
                        r4 = 3
                        goto L_0x012e
                    L_0x0038:
                        java.lang.String r3 = "marginRight"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0044
                        r1[r6] = r16
                        goto L_0x012d
                    L_0x0044:
                        r1[r4] = r16
                        r4 = 4
                        goto L_0x012e
                    L_0x0049:
                        java.lang.String r3 = "paddingRight"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0057
                        r2 = 28
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x0057:
                        r2 = 29
                        r1[r2] = r16
                        r4 = 13
                        goto L_0x012e
                    L_0x005f:
                        java.lang.String r3 = "paddingBottom"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x006d
                        r2 = 24
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x006d:
                        r2 = 25
                        r1[r2] = r16
                        goto L_0x012e
                    L_0x0073:
                        java.lang.String r3 = "width"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x007f
                        r1[r10] = r16
                        goto L_0x012d
                    L_0x007f:
                        r4 = 0
                        r1[r5] = r16
                        goto L_0x012e
                    L_0x0084:
                        java.lang.String r3 = "right"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0092
                        r2 = 16
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x0092:
                        r2 = 17
                        r1[r2] = r16
                        r4 = 7
                        goto L_0x012e
                    L_0x0099:
                        java.lang.String r3 = "paddingTop"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x00a7
                        r2 = 22
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x00a7:
                        r2 = 23
                        r1[r2] = r16
                        r4 = 10
                        goto L_0x012e
                    L_0x00af:
                        java.lang.String r3 = "left"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x00bd
                        r2 = 14
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x00bd:
                        r2 = 15
                        r1[r2] = r16
                        r4 = 6
                        goto L_0x012e
                    L_0x00c4:
                        java.lang.String r3 = "top"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x00d1
                        r2 = 20
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x00d1:
                        r2 = 21
                        r1[r2] = r16
                        r4 = 9
                        goto L_0x012e
                    L_0x00d8:
                        java.lang.String r3 = "marginBottom"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x00e3
                        r1[r15] = r16
                        goto L_0x012d
                    L_0x00e3:
                        r1[r8] = r16
                        r4 = 5
                        goto L_0x012e
                    L_0x00e7:
                        java.lang.String r3 = "marginTop"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x00f2
                        r1[r11] = r16
                        goto L_0x012d
                    L_0x00f2:
                        r1[r9] = r16
                        r4 = 2
                        goto L_0x012e
                    L_0x00f6:
                        java.lang.String r3 = "height"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0101
                        r1[r13] = r16
                        goto L_0x012d
                    L_0x0101:
                        r1[r12] = r16
                        r4 = 1
                        goto L_0x012e
                    L_0x0105:
                        java.lang.String r3 = "bottom"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0112
                        r2 = 18
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x0112:
                        r2 = 19
                        r1[r2] = r16
                        r4 = 8
                        goto L_0x012e
                    L_0x0119:
                        java.lang.String r3 = "paddingLeft"
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0126
                        r2 = 26
                        r1[r2] = r16
                        goto L_0x012d
                    L_0x0126:
                        r2 = 27
                        r1[r2] = r16
                        r4 = 12
                        goto L_0x012e
                    L_0x012d:
                        r4 = -1
                    L_0x012e:
                        switch(r4) {
                            case 0: goto L_0x0241;
                            case 1: goto L_0x022f;
                            case 2: goto L_0x021b;
                            case 3: goto L_0x0207;
                            case 4: goto L_0x01f3;
                            case 5: goto L_0x01df;
                            case 6: goto L_0x01ca;
                            case 7: goto L_0x01b5;
                            case 8: goto L_0x01a0;
                            case 9: goto L_0x018b;
                            case 10: goto L_0x0176;
                            case 11: goto L_0x0161;
                            case 12: goto L_0x014c;
                            case 13: goto L_0x0137;
                            default: goto L_0x0131;
                        }
                    L_0x0131:
                        r2 = 30
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x0137:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
                        float r6 = r7
                        r2.setPadding(r3, r4, r5, r6)
                        r2 = 44
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x014c:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
                        float r6 = r7
                        r2.setPadding(r3, r4, r5, r6)
                        r2 = 43
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x0161:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
                        float r6 = r7
                        r2.setPadding(r3, r4, r5, r6)
                        r2 = 42
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x0176:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
                        float r6 = r7
                        r2.setPadding(r3, r4, r5, r6)
                        r2 = 41
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x018b:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
                        float r6 = r7
                        r2.setPosition(r3, r4, r5, r6)
                        r2 = 40
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x01a0:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
                        float r6 = r7
                        r2.setPosition(r3, r4, r5, r6)
                        r2 = 39
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x01b5:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
                        float r6 = r7
                        r2.setPosition(r3, r4, r5, r6)
                        r2 = 38
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x01ca:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
                        float r6 = r7
                        r2.setPosition(r3, r4, r5, r6)
                        r2 = 37
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x01df:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.BOTTOM
                        float r6 = r7
                        r2.setMargin(r3, r4, r5, r6)
                        r2 = 36
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x01f3:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.RIGHT
                        float r6 = r7
                        r2.setMargin(r3, r4, r5, r6)
                        r2 = 35
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x0207:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.LEFT
                        float r6 = r7
                        r2.setMargin(r3, r4, r5, r6)
                        r2 = 34
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x021b:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        com.taobao.weex.dom.CSSShorthand$EDGE r5 = com.taobao.weex.dom.CSSShorthand.EDGE.TOP
                        float r6 = r7
                        r2.setMargin(r3, r4, r5, r6)
                        r2 = 33
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x022f:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        float r5 = r7
                        r2.setStyleHeight(r3, r4, r5)
                        r2 = 32
                        r1[r2] = r16
                        goto L_0x0252
                    L_0x0241:
                        com.taobao.weex.bridge.WXBridgeManager r2 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
                        java.lang.String r3 = r5
                        java.lang.String r4 = r2
                        float r5 = r7
                        r2.setStyleWidth(r3, r4, r5)
                        r2 = 31
                        r1[r2] = r16
                    L_0x0252:
                        r2 = 45
                        r1[r2] = r16
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.dom.transition.WXTransition.AnonymousClass7.run():void");
                }
            });
            $jacocoInit[252] = true;
            return;
        }
        $jacocoInit[251] = true;
    }

    private synchronized void onTransitionAnimationEnd() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.duration <= 0) {
            $jacocoInit[253] = true;
        } else if (this.transitionEndEvent == null) {
            $jacocoInit[254] = true;
        } else {
            $jacocoInit[255] = true;
            View targetView = getTargetView();
            if (targetView == null) {
                $jacocoInit[256] = true;
            } else if (this.transitionEndEvent == null) {
                $jacocoInit[257] = true;
            } else {
                $jacocoInit[258] = true;
                targetView.post(this.transitionEndEvent);
                $jacocoInit[259] = true;
            }
            this.transitionEndEvent = null;
            $jacocoInit[260] = true;
        }
        synchronized (this.targetStyles) {
            try {
                $jacocoInit[261] = true;
                if (this.targetStyles.size() <= 0) {
                    $jacocoInit[262] = true;
                } else {
                    $jacocoInit[263] = true;
                    $jacocoInit[264] = true;
                    for (String next : this.properties) {
                        $jacocoInit[265] = true;
                        if (!this.targetStyles.containsKey(next)) {
                            $jacocoInit[266] = true;
                        } else {
                            $jacocoInit[267] = true;
                            Object remove = this.targetStyles.remove(next);
                            $jacocoInit[268] = true;
                            this.mWXComponent.getStyles().put(next, remove);
                            $jacocoInit[269] = true;
                        }
                        $jacocoInit[270] = true;
                    }
                    this.targetStyles.clear();
                    $jacocoInit[271] = true;
                }
            } catch (Throwable th) {
                while (true) {
                    $jacocoInit[272] = true;
                    throw th;
                }
            }
        }
        $jacocoInit[273] = true;
    }

    private View getTargetView() {
        View view;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXComponent != null) {
            view = this.mWXComponent.getHostView();
            $jacocoInit[274] = true;
        } else {
            view = null;
            $jacocoInit[275] = true;
        }
        $jacocoInit[276] = true;
        return view;
    }

    private static long parseTimeMillis(Map<String, Object> map, String str, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        String string = WXUtils.getString(map.get(str), (String) null);
        if (string == null) {
            $jacocoInit[277] = true;
        } else {
            $jacocoInit[278] = true;
            string = string.replaceAll(d.H, "");
            $jacocoInit[279] = true;
        }
        if (!TextUtils.isEmpty(string)) {
            $jacocoInit[280] = true;
            try {
                long parseFloat = (long) Float.parseFloat(string);
                $jacocoInit[282] = true;
                return parseFloat;
            } catch (NumberFormatException unused) {
                $jacocoInit[283] = true;
                return j;
            }
        } else {
            $jacocoInit[281] = true;
            return j;
        }
    }

    private static Interpolator createTimeInterpolator(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[285] = true;
            char c = 65535;
            switch (str.hashCode()) {
                case -1965120668:
                    if (str.equals("ease-in")) {
                        $jacocoInit[288] = true;
                        c = 0;
                        break;
                    } else {
                        $jacocoInit[287] = true;
                        break;
                    }
                case -1102672091:
                    if (str.equals("linear")) {
                        $jacocoInit[296] = true;
                        c = 4;
                        break;
                    } else {
                        $jacocoInit[295] = true;
                        break;
                    }
                case -789192465:
                    if (str.equals("ease-out")) {
                        $jacocoInit[290] = true;
                        c = 1;
                        break;
                    } else {
                        $jacocoInit[289] = true;
                        break;
                    }
                case -361990811:
                    if (str.equals("ease-in-out")) {
                        $jacocoInit[292] = true;
                        c = 2;
                        break;
                    } else {
                        $jacocoInit[291] = true;
                        break;
                    }
                case 3105774:
                    if (str.equals(Constants.TimeFunction.EASE)) {
                        $jacocoInit[294] = true;
                        c = 3;
                        break;
                    } else {
                        $jacocoInit[293] = true;
                        break;
                    }
                default:
                    $jacocoInit[286] = true;
                    break;
            }
            switch (c) {
                case 0:
                    Interpolator create = PathInterpolatorCompat.create(0.42f, 0.0f, 1.0f, 1.0f);
                    $jacocoInit[298] = true;
                    return create;
                case 1:
                    Interpolator create2 = PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f);
                    $jacocoInit[299] = true;
                    return create2;
                case 2:
                    Interpolator create3 = PathInterpolatorCompat.create(0.42f, 0.0f, 0.58f, 1.0f);
                    $jacocoInit[300] = true;
                    return create3;
                case 3:
                    Interpolator create4 = PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f);
                    $jacocoInit[301] = true;
                    return create4;
                case 4:
                    Interpolator create5 = PathInterpolatorCompat.create(0.0f, 0.0f, 1.0f, 1.0f);
                    $jacocoInit[302] = true;
                    return create5;
                default:
                    $jacocoInit[297] = true;
                    try {
                        SingleFunctionParser singleFunctionParser = new SingleFunctionParser(str, new SingleFunctionParser.FlatMapper<Float>() {
                            private static transient /* synthetic */ boolean[] $jacocoData;

                            private static /* synthetic */ boolean[] $jacocoInit() {
                                boolean[] zArr = $jacocoData;
                                if (zArr != null) {
                                    return zArr;
                                }
                                boolean[] a2 = Offline.a(-2486322724351762272L, "com/taobao/weex/dom/transition/WXTransition$8", 3);
                                $jacocoData = a2;
                                return a2;
                            }

                            {
                                $jacocoInit()[0] = true;
                            }

                            public Float map(String str) {
                                boolean[] $jacocoInit = $jacocoInit();
                                Float valueOf = Float.valueOf(Float.parseFloat(str));
                                $jacocoInit[1] = true;
                                return valueOf;
                            }
                        });
                        $jacocoInit[303] = true;
                        List parse = singleFunctionParser.parse("cubic-bezier");
                        $jacocoInit[304] = true;
                        if (parse == null) {
                            $jacocoInit[305] = true;
                        } else if (parse.size() != 4) {
                            $jacocoInit[306] = true;
                        } else {
                            $jacocoInit[307] = true;
                            float floatValue = ((Float) parse.get(0)).floatValue();
                            float floatValue2 = ((Float) parse.get(1)).floatValue();
                            float floatValue3 = ((Float) parse.get(2)).floatValue();
                            float floatValue4 = ((Float) parse.get(3)).floatValue();
                            $jacocoInit[308] = true;
                            Interpolator create6 = PathInterpolatorCompat.create(floatValue, floatValue2, floatValue3, floatValue4);
                            $jacocoInit[309] = true;
                            return create6;
                        }
                        $jacocoInit[310] = true;
                        break;
                    } catch (RuntimeException e) {
                        $jacocoInit[311] = true;
                        if (WXEnvironment.isApkDebugable()) {
                            $jacocoInit[313] = true;
                            WXLogUtils.e("WXTransition", (Throwable) e);
                            $jacocoInit[314] = true;
                            break;
                        } else {
                            $jacocoInit[312] = true;
                            break;
                        }
                    }
            }
        } else {
            $jacocoInit[284] = true;
        }
        Interpolator create7 = PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f);
        $jacocoInit[315] = true;
        return create7;
    }

    private static void updateTransitionProperties(WXTransition wXTransition, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[316] = true;
            return;
        }
        wXTransition.properties.clear();
        $jacocoInit[317] = true;
        String[] split = PROPERTY_SPLIT_PATTERN.split(str);
        int length = split.length;
        int i = 0;
        $jacocoInit[318] = true;
        while (i < length) {
            String str2 = split[i];
            $jacocoInit[319] = true;
            String trim = str2.trim();
            $jacocoInit[320] = true;
            if (TextUtils.isEmpty(trim)) {
                $jacocoInit[321] = true;
            } else {
                if (LAYOUT_PROPERTIES.contains(trim)) {
                    $jacocoInit[322] = true;
                } else if (TRANSFORM_PROPERTIES.contains(trim)) {
                    $jacocoInit[323] = true;
                } else {
                    $jacocoInit[324] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[325] = true;
                    } else {
                        $jacocoInit[326] = true;
                        WXLogUtils.e("WXTransition Property Not Supported" + trim + " in " + str);
                        $jacocoInit[327] = true;
                    }
                }
                wXTransition.properties.add(trim);
                $jacocoInit[328] = true;
            }
            i++;
            $jacocoInit[329] = true;
        }
        $jacocoInit[330] = true;
    }

    public List<String> getProperties() {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.properties;
        $jacocoInit[331] = true;
        return list;
    }
}
