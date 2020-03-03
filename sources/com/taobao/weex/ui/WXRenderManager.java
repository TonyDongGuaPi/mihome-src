package com.taobao.weex.ui;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.dom.RenderContext;
import com.taobao.weex.performance.WXInstanceApm;
import com.taobao.weex.ui.action.BasicGraphicAction;
import com.taobao.weex.ui.action.GraphicActionBatchAction;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXExceptionUtils;
import com.taobao.weex.utils.WXUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRenderManager {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static int nativeBatchTimes = 0;
    private static final String sKeyAction = "Action";
    private final int MAX_DROP_FRAME_NATIVE_BATCH = 2000;
    private ArrayList<Map<String, Object>> mBatchActions = new ArrayList<>();
    private volatile ConcurrentHashMap<String, RenderContextImpl> mRenderContext;
    private WXRenderHandler mWXRenderHandler;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4945804253462496768L, "com/taobao/weex/ui/WXRenderManager", 88);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[87] = true;
    }

    public WXRenderManager() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mRenderContext = new ConcurrentHashMap<>();
        $jacocoInit[2] = true;
        this.mWXRenderHandler = new WXRenderHandler();
        $jacocoInit[3] = true;
    }

    public RenderContext getRenderContext(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        RenderContext renderContext = this.mRenderContext.get(str);
        $jacocoInit[4] = true;
        return renderContext;
    }

    @Nullable
    public WXComponent getWXComponent(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent wXComponent = null;
        if (str == null) {
            $jacocoInit[5] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[6] = true;
        } else {
            RenderContext renderContext = getRenderContext(str);
            $jacocoInit[8] = true;
            if (renderContext == null) {
                $jacocoInit[9] = true;
            } else {
                wXComponent = renderContext.getComponent(str2);
                $jacocoInit[10] = true;
            }
            $jacocoInit[11] = true;
            return wXComponent;
        }
        $jacocoInit[7] = true;
        return null;
    }

    public WXSDKInstance getWXSDKInstance(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        RenderContextImpl renderContextImpl = this.mRenderContext.get(str);
        if (renderContextImpl == null) {
            $jacocoInit[12] = true;
            return null;
        }
        WXSDKInstance wXSDKInstance = renderContextImpl.getWXSDKInstance();
        $jacocoInit[13] = true;
        return wXSDKInstance;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void postOnUiThread(Runnable runnable, long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderHandler.postDelayed(WXThread.secure(runnable), j);
        $jacocoInit[14] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void postOnUiThread(Runnable runnable, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderHandler.post(str, WXThread.secure(runnable));
        $jacocoInit[15] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void postOnUiThread(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderHandler.post(WXThread.secure(runnable));
        $jacocoInit[16] = true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void removeTask(Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXRenderHandler.removeCallbacks(runnable);
        $jacocoInit[17] = true;
    }

    public void removeRenderStatement(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXUtils.isUiThread()) {
            RenderContextImpl remove = this.mRenderContext.remove(str);
            if (remove == null) {
                $jacocoInit[20] = true;
            } else {
                $jacocoInit[21] = true;
                remove.destroy();
                $jacocoInit[22] = true;
            }
            if (str == null) {
                $jacocoInit[23] = true;
                this.mWXRenderHandler.removeCallbacksAndMessages((Object) null);
                $jacocoInit[24] = true;
            } else {
                this.mWXRenderHandler.removeMessages(str.hashCode());
                $jacocoInit[25] = true;
            }
            $jacocoInit[26] = true;
            return;
        }
        $jacocoInit[18] = true;
        WXRuntimeException wXRuntimeException = new WXRuntimeException("[WXRenderManager] removeRenderStatement can only be called in main thread");
        $jacocoInit[19] = true;
        throw wXRuntimeException;
    }

    private void postAllStashedGraphicAction(String str, BasicGraphicAction basicGraphicAction) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(this.mBatchActions);
        $jacocoInit[27] = true;
        this.mBatchActions.clear();
        $jacocoInit[28] = true;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        $jacocoInit[29] = true;
        $jacocoInit[30] = true;
        int i = 0;
        while (i < arrayList.size()) {
            $jacocoInit[31] = true;
            $jacocoInit[32] = true;
            BasicGraphicAction basicGraphicAction2 = (BasicGraphicAction) ((Map) arrayList.get(i)).get(sKeyAction);
            if (basicGraphicAction2.mActionType == 1) {
                $jacocoInit[33] = true;
            } else if (basicGraphicAction2.mActionType == 2) {
                $jacocoInit[34] = true;
            } else {
                arrayList2.add(basicGraphicAction2);
                $jacocoInit[35] = true;
            }
            i++;
            $jacocoInit[36] = true;
        }
        nativeBatchTimes = 0;
        $jacocoInit[37] = true;
        postGraphicAction(str, new GraphicActionBatchAction(basicGraphicAction.getWXSDKIntance(), basicGraphicAction.getRef(), arrayList2));
        $jacocoInit[38] = true;
    }

    public void postGraphicAction(String str, BasicGraphicAction basicGraphicAction) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRenderContext.get(str) == null) {
            $jacocoInit[39] = true;
        } else if (basicGraphicAction.mActionType == 2) {
            $jacocoInit[40] = true;
            postAllStashedGraphicAction(str, basicGraphicAction);
            $jacocoInit[41] = true;
        } else {
            if (basicGraphicAction.mActionType == 1) {
                $jacocoInit[42] = true;
            } else if (this.mBatchActions.size() <= 0) {
                $jacocoInit[43] = true;
                this.mWXRenderHandler.post(str, basicGraphicAction);
                $jacocoInit[50] = true;
                return;
            } else {
                $jacocoInit[44] = true;
            }
            nativeBatchTimes++;
            if (nativeBatchTimes > 2000) {
                $jacocoInit[45] = true;
                postAllStashedGraphicAction(str, basicGraphicAction);
                $jacocoInit[46] = true;
                this.mWXRenderHandler.post(str, basicGraphicAction);
                $jacocoInit[50] = true;
                return;
            }
            HashMap hashMap = new HashMap(1);
            $jacocoInit[47] = true;
            hashMap.put(sKeyAction, basicGraphicAction);
            $jacocoInit[48] = true;
            this.mBatchActions.add(hashMap);
            $jacocoInit[49] = true;
        }
    }

    public void registerInstance(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXSDKInstance.getInstanceId() == null) {
            $jacocoInit[51] = true;
            WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_INSTANCE_ID_NULL;
            StringBuilder sb = new StringBuilder();
            WXErrorCode wXErrorCode2 = WXErrorCode.WX_RENDER_ERR_INSTANCE_ID_NULL;
            $jacocoInit[52] = true;
            sb.append(wXErrorCode2.getErrorMsg());
            sb.append("instanceId is null");
            String sb2 = sb.toString();
            $jacocoInit[53] = true;
            WXExceptionUtils.commitCriticalExceptionRT((String) null, wXErrorCode, "registerInstance", sb2, (Map<String, String>) null);
            $jacocoInit[54] = true;
        } else {
            this.mRenderContext.put(wXSDKInstance.getInstanceId(), new RenderContextImpl(wXSDKInstance));
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
    }

    public List<WXSDKInstance> getAllInstances() {
        ArrayList arrayList;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[57] = true;
        if (this.mRenderContext == null) {
            $jacocoInit[58] = true;
        } else if (this.mRenderContext.isEmpty()) {
            $jacocoInit[59] = true;
        } else {
            $jacocoInit[60] = true;
            arrayList = new ArrayList();
            $jacocoInit[61] = true;
            $jacocoInit[62] = true;
            for (Map.Entry<String, RenderContextImpl> value : this.mRenderContext.entrySet()) {
                $jacocoInit[64] = true;
                RenderContextImpl renderContextImpl = (RenderContextImpl) value.getValue();
                if (renderContextImpl == null) {
                    $jacocoInit[65] = true;
                } else {
                    $jacocoInit[66] = true;
                    arrayList.add(renderContextImpl.getWXSDKInstance());
                    $jacocoInit[67] = true;
                }
                $jacocoInit[68] = true;
            }
            $jacocoInit[63] = true;
            $jacocoInit[69] = true;
            return arrayList;
        }
        arrayList = null;
        $jacocoInit[69] = true;
        return arrayList;
    }

    public void registerComponent(String str, String str2, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        RenderContextImpl renderContextImpl = this.mRenderContext.get(str);
        if (renderContextImpl == null) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            renderContextImpl.registerComponent(str2, wXComponent);
            $jacocoInit[72] = true;
            if (renderContextImpl.getInstance() == null) {
                $jacocoInit[73] = true;
            } else {
                $jacocoInit[74] = true;
                WXInstanceApm apmForInstance = renderContextImpl.getInstance().getApmForInstance();
                $jacocoInit[75] = true;
                $jacocoInit[76] = true;
                apmForInstance.updateMaxStats(WXInstanceApm.KEY_PAGE_STATS_MAX_COMPONENT_NUM, (double) renderContextImpl.getComponentCount());
                $jacocoInit[77] = true;
            }
        }
        $jacocoInit[78] = true;
    }

    public WXComponent unregisterComponent(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        RenderContextImpl renderContextImpl = this.mRenderContext.get(str);
        if (renderContextImpl != null) {
            $jacocoInit[79] = true;
            if (renderContextImpl.getInstance() == null) {
                $jacocoInit[80] = true;
            } else {
                $jacocoInit[81] = true;
                WXInstanceApm apmForInstance = renderContextImpl.getInstance().getApmForInstance();
                $jacocoInit[82] = true;
                $jacocoInit[83] = true;
                apmForInstance.updateMaxStats(WXInstanceApm.KEY_PAGE_STATS_MAX_COMPONENT_NUM, (double) renderContextImpl.getComponentCount());
                $jacocoInit[84] = true;
            }
            WXComponent unregisterComponent = renderContextImpl.unregisterComponent(str2);
            $jacocoInit[85] = true;
            return unregisterComponent;
        }
        $jacocoInit[86] = true;
        return null;
    }
}
