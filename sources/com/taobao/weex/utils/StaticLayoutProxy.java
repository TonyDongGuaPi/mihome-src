package com.taobao.weex.utils;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import java.lang.reflect.Constructor;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class StaticLayoutProxy {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static Constructor<StaticLayout> layoutConstructor;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1825390686341702911L, "com/taobao/weex/utils/StaticLayoutProxy", 20);
        $jacocoData = a2;
        return a2;
    }

    public StaticLayoutProxy() {
        $jacocoInit()[0] = true;
    }

    public static StaticLayout create(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, boolean z, boolean z2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT < 18) {
            $jacocoInit[1] = true;
        } else if (!z2) {
            $jacocoInit[2] = true;
        } else {
            TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.RTL;
            $jacocoInit[3] = true;
            StaticLayout createInternal = createInternal(charSequence, textPaint, i, alignment, textDirectionHeuristic, f, f2, z);
            if (createInternal != null) {
                $jacocoInit[4] = true;
                return createInternal;
            }
            StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, i, alignment, f, f2, z);
            $jacocoInit[5] = true;
            return staticLayout;
        }
        StaticLayout staticLayout2 = new StaticLayout(charSequence, textPaint, i, alignment, f, f2, z);
        $jacocoInit[6] = true;
        return staticLayout2;
    }

    private static StaticLayout createInternal(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, TextDirectionHeuristic textDirectionHeuristic, float f, float f2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 18) {
            $jacocoInit[7] = true;
            try {
                if (layoutConstructor != null) {
                    $jacocoInit[9] = true;
                } else {
                    $jacocoInit[10] = true;
                    layoutConstructor = StaticLayout.class.getConstructor(new Class[]{CharSequence.class, TextPaint.class, Integer.TYPE, Layout.Alignment.class, TextDirectionHeuristic.class, Float.TYPE, Float.TYPE, Boolean.TYPE});
                    $jacocoInit[11] = true;
                }
                if (layoutConstructor != null) {
                    $jacocoInit[12] = true;
                    Constructor<StaticLayout> constructor = layoutConstructor;
                    $jacocoInit[13] = true;
                    Object[] objArr = {charSequence, textPaint, Integer.valueOf(i), alignment, textDirectionHeuristic, Float.valueOf(f), Float.valueOf(f2), Boolean.valueOf(z)};
                    $jacocoInit[14] = true;
                    StaticLayout newInstance = constructor.newInstance(objArr);
                    $jacocoInit[15] = true;
                    return newInstance;
                }
                $jacocoInit[16] = true;
                $jacocoInit[19] = true;
                return null;
            } catch (Throwable th) {
                $jacocoInit[17] = true;
                th.printStackTrace();
                $jacocoInit[18] = true;
            }
        } else {
            $jacocoInit[8] = true;
            return null;
        }
    }
}
