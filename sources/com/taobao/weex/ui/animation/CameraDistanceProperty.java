package com.taobao.weex.ui.animation;

import android.os.Build;
import android.util.Property;
import android.view.View;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class CameraDistanceProperty extends Property<View, Float> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "CameraDistance";
    private static CameraDistanceProperty instance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6900928236805452898L, "com/taobao/weex/ui/animation/CameraDistanceProperty", 8);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ Object get(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Float f = get((View) obj);
        $jacocoInit[6] = true;
        return f;
    }

    public /* synthetic */ void set(Object obj, Object obj2) {
        boolean[] $jacocoInit = $jacocoInit();
        set((View) obj, (Float) obj2);
        $jacocoInit[7] = true;
    }

    static Property<View, Float> getInstance() {
        boolean[] $jacocoInit = $jacocoInit();
        CameraDistanceProperty cameraDistanceProperty = instance;
        $jacocoInit[0] = true;
        return cameraDistanceProperty;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private CameraDistanceProperty() {
        super(Float.class, TAG);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    public Float get(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 16) {
            $jacocoInit[2] = true;
            Float valueOf = Float.valueOf(view.getCameraDistance());
            $jacocoInit[3] = true;
            return valueOf;
        }
        Float valueOf2 = Float.valueOf(Float.NaN);
        $jacocoInit[4] = true;
        return valueOf2;
    }

    public void set(View view, Float f) {
        boolean[] $jacocoInit = $jacocoInit();
        view.setCameraDistance(f.floatValue());
        $jacocoInit[5] = true;
    }
}
