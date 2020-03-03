package com.taobao.weex.common;

import android.widget.ImageView;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXImageStrategy {
    private static transient /* synthetic */ boolean[] $jacocoData;
    public int blurRadius;
    ImageListener imageListener;
    public String instanceId;
    @Deprecated
    public boolean isClipping;
    public boolean isSharpen;
    public String placeHolder;

    public interface ImageListener {
        void onImageFinish(String str, ImageView imageView, boolean z, Map map);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9050447541250479718L, "com/taobao/weex/common/WXImageStrategy", 4);
        $jacocoData = a2;
        return a2;
    }

    public WXImageStrategy() {
        $jacocoInit()[0] = true;
    }

    public WXImageStrategy(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.instanceId = str;
        $jacocoInit[1] = true;
    }

    public ImageListener getImageListener() {
        boolean[] $jacocoInit = $jacocoInit();
        ImageListener imageListener2 = this.imageListener;
        $jacocoInit[2] = true;
        return imageListener2;
    }

    public void setImageListener(ImageListener imageListener2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.imageListener = imageListener2;
        $jacocoInit[3] = true;
    }
}
