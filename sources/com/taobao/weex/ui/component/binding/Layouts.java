package com.taobao.weex.ui.component.binding;

import android.os.AsyncTask;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.ui.component.list.template.TemplateViewHolder;
import com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Layouts {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6109442579817099140L, "com/taobao/weex/ui/component/binding/Layouts", 52);
        $jacocoData = a2;
        return a2;
    }

    public Layouts() {
        $jacocoInit()[0] = true;
    }

    public static void doLayoutAsync(TemplateViewHolder templateViewHolder, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent component = templateViewHolder.getComponent();
        $jacocoInit[1] = true;
        int holderPosition = templateViewHolder.getHolderPosition();
        if (templateViewHolder.asyncTask == null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            templateViewHolder.asyncTask.cancel(false);
            templateViewHolder.asyncTask = null;
            $jacocoInit[4] = true;
        }
        if (z) {
            $jacocoInit[5] = true;
            AsynLayoutTask asynLayoutTask = new AsynLayoutTask(templateViewHolder, holderPosition, component);
            templateViewHolder.asyncTask = asynLayoutTask;
            $jacocoInit[6] = true;
            asynLayoutTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            $jacocoInit[7] = true;
        } else {
            doLayoutOnly(component, templateViewHolder);
            $jacocoInit[8] = true;
            setLayout(component, false);
            $jacocoInit[9] = true;
            if (templateViewHolder.getHolderPosition() < 0) {
                $jacocoInit[10] = true;
            } else {
                $jacocoInit[11] = true;
                templateViewHolder.getTemplateList().fireEvent(TemplateDom.ATTACH_CELL_SLOT, TemplateDom.findAllComponentRefs(templateViewHolder.getTemplateList().getRef(), holderPosition, component));
                $jacocoInit[12] = true;
            }
        }
        $jacocoInit[13] = true;
    }

    public static void doLayoutSync(WXCell wXCell, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        doSafeLayout(wXCell, f, f2);
        $jacocoInit[14] = true;
        setLayout(wXCell, false);
        $jacocoInit[15] = true;
    }

    public static void doLayoutOnly(WXComponent wXComponent, TemplateViewHolder templateViewHolder) {
        boolean[] $jacocoInit = $jacocoInit();
        doSafeLayout(wXComponent, templateViewHolder.getTemplateList().getLayoutWidth(), templateViewHolder.getTemplateList().getLayoutHeight());
        $jacocoInit[16] = true;
    }

    private static void doSafeLayout(WXComponent wXComponent, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            System.currentTimeMillis();
            $jacocoInit[17] = true;
            int nativeLayoutRenderObject = NativeRenderObjectUtils.nativeLayoutRenderObject(wXComponent.getRenderObjectPtr(), f, f2);
            $jacocoInit[18] = true;
            if (!WXEnvironment.isOpenDebugLog()) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
            }
            if (nativeLayoutRenderObject > 0) {
                $jacocoInit[21] = true;
            } else {
                $jacocoInit[22] = true;
                StringBuilder sb = new StringBuilder();
                sb.append(" WXTemplateList doSafeLayout wrong template ");
                $jacocoInit[23] = true;
                sb.append(wXComponent.getAttrs().get(Constants.Name.Recycler.SLOT_TEMPLATE_CASE));
                sb.append(" cell height ");
                sb.append(nativeLayoutRenderObject);
                String sb2 = sb.toString();
                $jacocoInit[24] = true;
                WXLogUtils.e(WXRecyclerTemplateList.TAG, sb2);
                $jacocoInit[25] = true;
            }
            $jacocoInit[26] = true;
        } catch (Exception e) {
            $jacocoInit[27] = true;
            if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
                WXLogUtils.e(WXRecyclerTemplateList.TAG, (Throwable) e);
                $jacocoInit[30] = true;
            }
        }
        $jacocoInit[31] = true;
    }

    public static final void setLayout(WXComponent wXComponent, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.isWaste()) {
            $jacocoInit[32] = true;
            return;
        }
        if (!wXComponent.getAttrs().containsKey(TemplateDom.KEY_RESET_ANIMATION)) {
            $jacocoInit[33] = true;
        } else {
            $jacocoInit[34] = true;
            if (!WXUtils.getBoolean(wXComponent.getAttrs().get(TemplateDom.KEY_RESET_ANIMATION), true).booleanValue()) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                TemplateDom.resetAnimaiton(wXComponent.getHostView());
                $jacocoInit[37] = true;
            }
        }
        long renderObjectPtr = wXComponent.getRenderObjectPtr();
        $jacocoInit[38] = true;
        if (!NativeRenderObjectUtils.nativeRenderObjectHasNewLayout(renderObjectPtr)) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            NativeRenderObjectUtils.nativeRenderObjectUpdateComponent(renderObjectPtr, wXComponent);
            $jacocoInit[41] = true;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[42] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[43] = true;
            int childCount = wXVContainer.getChildCount();
            int i = 0;
            $jacocoInit[44] = true;
            while (i < childCount) {
                $jacocoInit[46] = true;
                WXComponent child = wXVContainer.getChild(i);
                if (child == null) {
                    $jacocoInit[47] = true;
                } else {
                    $jacocoInit[48] = true;
                    setLayout(child, z);
                    $jacocoInit[49] = true;
                }
                i++;
                $jacocoInit[50] = true;
            }
            $jacocoInit[45] = true;
        }
        $jacocoInit[51] = true;
    }
}
