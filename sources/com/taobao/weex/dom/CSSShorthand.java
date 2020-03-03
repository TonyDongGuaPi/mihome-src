package com.taobao.weex.dom;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.lang.Enum;
import java.util.Arrays;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CSSShorthand<T extends Enum<? extends CSSProperty>> implements Cloneable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float[] values;

    interface CSSProperty {
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1211579746959884890L, "com/taobao/weex/dom/CSSShorthand", 28);
        $jacocoData = a2;
        return a2;
    }

    public enum EDGE implements CSSProperty {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        ALL;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[3] = true;
        }
    }

    public enum CORNER implements CSSProperty {
        BORDER_TOP_LEFT,
        BORDER_TOP_RIGHT,
        BORDER_BOTTOM_RIGHT,
        BORDER_BOTTOM_LEFT,
        ALL;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[4] = true;
        }
    }

    public enum TYPE {
        MARGIN,
        PADDING,
        BORDER;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[3] = true;
        }
    }

    public CSSShorthand(float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        replace(fArr);
        $jacocoInit[1] = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CSSShorthand() {
        this(false);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
    }

    CSSShorthand(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[3] = true;
        this.values = new float[Math.max(EDGE.values().length, CORNER.values().length)];
        if (!z) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            Arrays.fill(this.values, Float.NaN);
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void set(@NonNull EDGE edge, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        setInternal(edge, f);
        $jacocoInit[8] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void set(@NonNull CORNER corner, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        setInternal(corner, f);
        $jacocoInit[9] = true;
    }

    public float get(@NonNull EDGE edge) {
        boolean[] $jacocoInit = $jacocoInit();
        float internal = getInternal(edge);
        $jacocoInit[10] = true;
        return internal;
    }

    public float get(@NonNull CORNER corner) {
        boolean[] $jacocoInit = $jacocoInit();
        float internal = getInternal(corner);
        $jacocoInit[11] = true;
        return internal;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void replace(float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.values = fArr;
        $jacocoInit[12] = true;
    }

    public CSSShorthand clone() throws CloneNotSupportedException {
        boolean[] $jacocoInit = $jacocoInit();
        CSSShorthand cSSShorthand = (CSSShorthand) super.clone();
        $jacocoInit[13] = true;
        return cSSShorthand;
    }

    private void setInternal(@NonNull Enum<? extends CSSProperty> enumR, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (enumR == EDGE.ALL) {
            $jacocoInit[14] = true;
        } else if (enumR == CORNER.ALL) {
            $jacocoInit[15] = true;
        } else {
            this.values[enumR.ordinal()] = f;
            $jacocoInit[17] = true;
            $jacocoInit[18] = true;
        }
        Arrays.fill(this.values, f);
        $jacocoInit[16] = true;
        $jacocoInit[18] = true;
    }

    private float getInternal(@NonNull Enum<? extends CSSProperty> enumR) {
        float f;
        boolean[] $jacocoInit = $jacocoInit();
        if (enumR == EDGE.ALL) {
            $jacocoInit[19] = true;
        } else if (enumR == CORNER.ALL) {
            $jacocoInit[20] = true;
        } else {
            f = this.values[enumR.ordinal()];
            $jacocoInit[22] = true;
            $jacocoInit[23] = true;
            return f;
        }
        f = 0.0f;
        $jacocoInit[21] = true;
        $jacocoInit[23] = true;
        return f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String toString() {
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(this.values.toString())) {
            str = "";
            $jacocoInit[24] = true;
        } else {
            str = Arrays.toString(this.values);
            $jacocoInit[25] = true;
        }
        $jacocoInit[26] = true;
        return str;
    }
}
