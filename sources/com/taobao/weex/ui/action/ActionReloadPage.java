package com.taobao.weex.ui.action;

import android.util.Log;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ActionReloadPage implements IExecutable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String TAG = "ReloadPageAction";
    private String mPageId;
    private boolean mReloadThis;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3968441094055865370L, "com/taobao/weex/ui/action/ActionReloadPage", 5);
        $jacocoData = a2;
        return a2;
    }

    public ActionReloadPage(String str, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPageId = str;
        this.mReloadThis = z;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getWXRenderManager().getWXSDKInstance(this.mPageId);
        if (wXSDKInstance != null) {
            $jacocoInit[1] = true;
            wXSDKInstance.reloadPage(this.mReloadThis);
            $jacocoInit[2] = true;
        } else {
            Log.e("ReloadPageAction", "ReloadPageAction executeDom reloadPage instance is null");
            $jacocoInit[3] = true;
        }
        $jacocoInit[4] = true;
    }
}
