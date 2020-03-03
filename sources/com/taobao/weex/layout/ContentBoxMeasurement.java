package com.taobao.weex.layout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.taobao.weex.base.CalledByNative;
import com.taobao.weex.common.Destroyable;
import com.taobao.weex.ui.component.WXComponent;
import java.io.Serializable;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class ContentBoxMeasurement implements Destroyable, Serializable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    @Nullable
    protected WXComponent mComponent;
    protected float mMeasureHeight;
    protected float mMeasureWidth;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7384411404685329957L, "com/taobao/weex/layout/ContentBoxMeasurement", 6);
        $jacocoData = a2;
        return a2;
    }

    @CalledByNative
    public abstract void layoutAfter(float f, float f2);

    @CalledByNative
    public abstract void layoutBefore();

    public abstract void measureInternal(float f, float f2, int i, int i2);

    public ContentBoxMeasurement() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponent = null;
        $jacocoInit[0] = true;
    }

    public ContentBoxMeasurement(@NonNull WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponent = wXComponent;
        $jacocoInit[1] = true;
    }

    @CalledByNative
    public final void measure(float f, float f2, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        measureInternal(f, f2, i, i2);
        $jacocoInit[2] = true;
    }

    @CalledByNative
    public float getWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mMeasureWidth;
        $jacocoInit[3] = true;
        return f;
    }

    @CalledByNative
    public float getHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mMeasureHeight;
        $jacocoInit[4] = true;
        return f;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mComponent = null;
        $jacocoInit[5] = true;
    }
}
