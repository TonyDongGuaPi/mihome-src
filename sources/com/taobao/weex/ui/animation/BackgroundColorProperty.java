package com.taobao.weex.ui.animation;

import android.graphics.drawable.ColorDrawable;
import android.util.Property;
import android.view.View;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BackgroundColorProperty extends Property<View, Integer> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "BackgroundColorAnimation";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6471187824727440785L, "com/taobao/weex/ui/animation/BackgroundColorProperty", 16);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ Object get(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Integer num = get((View) obj);
        $jacocoInit[14] = true;
        return num;
    }

    public /* synthetic */ void set(Object obj, Object obj2) {
        boolean[] $jacocoInit = $jacocoInit();
        set((View) obj, (Integer) obj2);
        $jacocoInit[15] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BackgroundColorProperty() {
        super(Integer.class, "backgroundColor");
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public Integer get(View view) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(view);
        if (borderDrawable != null) {
            $jacocoInit[1] = true;
            i = borderDrawable.getColor();
            $jacocoInit[2] = true;
        } else if (view.getBackground() instanceof ColorDrawable) {
            $jacocoInit[3] = true;
            i = ((ColorDrawable) view.getBackground()).getColor();
            $jacocoInit[4] = true;
        } else {
            i = 0;
            $jacocoInit[5] = true;
            WXLogUtils.e(TAG, "Unsupported background type");
            $jacocoInit[6] = true;
        }
        Integer valueOf = Integer.valueOf(i);
        $jacocoInit[7] = true;
        return valueOf;
    }

    public void set(View view, Integer num) {
        boolean[] $jacocoInit = $jacocoInit();
        BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(view);
        if (borderDrawable != null) {
            $jacocoInit[8] = true;
            borderDrawable.setColor(num.intValue());
            $jacocoInit[9] = true;
        } else if (view.getBackground() instanceof ColorDrawable) {
            $jacocoInit[10] = true;
            ((ColorDrawable) view.getBackground()).setColor(num.intValue());
            $jacocoInit[11] = true;
        } else {
            WXLogUtils.e(TAG, "Unsupported background type");
            $jacocoInit[12] = true;
        }
        $jacocoInit[13] = true;
    }
}
