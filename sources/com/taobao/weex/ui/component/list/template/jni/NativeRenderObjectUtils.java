package com.taobao.weex.ui.component.list.template.jni;

import com.taobao.weex.base.CalledByNative;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class NativeRenderObjectUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-700394285907011317L, "com/taobao/weex/ui/component/list/template/jni/NativeRenderObjectUtils", 3);
        $jacocoData = a2;
        return a2;
    }

    public static native void nativeAddChildRenderObject(long j, long j2);

    public static native long nativeCopyRenderObject(long j);

    public static native long nativeGetRenderObject(String str, String str2);

    public static native int nativeLayoutRenderObject(long j, float f, float f2);

    public static native int nativeRenderObjectChildCount(long j);

    public static native void nativeRenderObjectChildWaste(long j, boolean z);

    public static native long nativeRenderObjectGetChild(long j, int i);

    public static native int nativeRenderObjectGetLayoutDirectionFromPathNode(long j);

    public static native boolean nativeRenderObjectHasNewLayout(long j);

    public static native long nativeRenderObjectUpdateComponent(long j, WXComponent wXComponent);

    public static native void nativeUpdateRenderObjectAttr(long j, String str, String str2);

    public static native void nativeUpdateRenderObjectStyle(long j, String str, String str2);

    public NativeRenderObjectUtils() {
        $jacocoInit()[0] = true;
    }

    @CalledByNative
    public static void updateComponentSize(WXComponent wXComponent, float f, float f2, float f3, float f4, float f5, float f6) {
        boolean[] $jacocoInit = $jacocoInit();
        wXComponent.updateDemission(f, f2, f3, f4, f5, f6);
        $jacocoInit[1] = true;
        wXComponent.applyLayoutOnly();
        $jacocoInit[2] = true;
    }
}
