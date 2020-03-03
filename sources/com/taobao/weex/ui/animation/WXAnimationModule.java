package com.taobao.weex.ui.animation;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.action.GraphicActionAnimation;
import com.taobao.weex.ui.component.WXComponent;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXAnimationModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8026720359120213167L, "com/taobao/weex/ui/animation/WXAnimationModule", 8);
        $jacocoData = a2;
        return a2;
    }

    public WXAnimationModule() {
        $jacocoInit()[0] = true;
    }

    @JSMethod
    public void transition(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[2] = true;
        } else if (this.mWXSDKInstance == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            GraphicActionAnimation graphicActionAnimation = new GraphicActionAnimation(this.mWXSDKInstance, str, str2, str3);
            $jacocoInit[5] = true;
            WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionAnimation.getPageId(), graphicActionAnimation);
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
    }

    public static class AnimationHolder {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private String callback;
        private WXAnimationBean wxAnimationBean;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(1935900838836691842L, "com/taobao/weex/ui/animation/WXAnimationModule$AnimationHolder", 7);
            $jacocoData = a2;
            return a2;
        }

        public void execute(WXSDKInstance wXSDKInstance, WXComponent wXComponent) {
            boolean[] $jacocoInit = $jacocoInit();
            if (wXSDKInstance == null) {
                $jacocoInit[0] = true;
            } else if (wXComponent == null) {
                $jacocoInit[1] = true;
            } else {
                $jacocoInit[2] = true;
                GraphicActionAnimation graphicActionAnimation = new GraphicActionAnimation(wXSDKInstance, wXComponent.getRef(), this.wxAnimationBean, this.callback);
                $jacocoInit[3] = true;
                WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionAnimation.getPageId(), graphicActionAnimation);
                $jacocoInit[4] = true;
            }
            $jacocoInit[5] = true;
        }

        public AnimationHolder(WXAnimationBean wXAnimationBean, String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.wxAnimationBean = wXAnimationBean;
            this.callback = str;
            $jacocoInit[6] = true;
        }
    }
}
