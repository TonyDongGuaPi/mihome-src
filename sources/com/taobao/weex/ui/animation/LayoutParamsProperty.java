package com.taobao.weex.ui.animation;

import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.IRenderResult;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

abstract class LayoutParamsProperty extends Property<View, Integer> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4079742688225009549L, "com/taobao/weex/ui/animation/LayoutParamsProperty", 18);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract Integer getProperty(ViewGroup.LayoutParams layoutParams);

    /* access modifiers changed from: protected */
    public abstract void setProperty(ViewGroup.LayoutParams layoutParams, Integer num);

    public /* synthetic */ Object get(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Integer num = get((View) obj);
        $jacocoInit[16] = true;
        return num;
    }

    public /* synthetic */ void set(Object obj, Object obj2) {
        boolean[] $jacocoInit = $jacocoInit();
        set((View) obj, (Integer) obj2);
        $jacocoInit[17] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LayoutParamsProperty() {
        super(Integer.class, "layoutParams");
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public Integer get(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[1] = true;
        } else {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                $jacocoInit[2] = true;
            } else {
                $jacocoInit[3] = true;
                Integer property = getProperty(layoutParams);
                $jacocoInit[4] = true;
                return property;
            }
        }
        $jacocoInit[5] = true;
        return 0;
    }

    public void set(View view, Integer num) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[6] = true;
        } else {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                $jacocoInit[7] = true;
            } else {
                $jacocoInit[8] = true;
                setProperty(layoutParams, num);
                if (!(view instanceof IRenderResult)) {
                    $jacocoInit[9] = true;
                } else {
                    $jacocoInit[10] = true;
                    WXComponent component = ((IRenderResult) view).getComponent();
                    if (component == null) {
                        $jacocoInit[11] = true;
                    } else {
                        $jacocoInit[12] = true;
                        component.notifyNativeSizeChanged(layoutParams.width, layoutParams.height);
                        $jacocoInit[13] = true;
                    }
                }
                view.requestLayout();
                $jacocoInit[14] = true;
            }
        }
        $jacocoInit[15] = true;
    }
}
