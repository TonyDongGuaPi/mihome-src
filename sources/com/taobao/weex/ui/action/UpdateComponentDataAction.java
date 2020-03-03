package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class UpdateComponentDataAction extends BasicGraphicAction {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String callback;
    private JSONObject data;
    private String virtualComponentId;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5721651822556006528L, "com/taobao/weex/ui/action/UpdateComponentDataAction", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UpdateComponentDataAction(WXSDKInstance wXSDKInstance, String str, JSONObject jSONObject, String str2) {
        super(wXSDKInstance, CellDataManager.getListRef(str));
        boolean[] $jacocoInit = $jacocoInit();
        this.virtualComponentId = str;
        this.data = jSONObject;
        this.callback = str2;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(getRef())) {
            $jacocoInit[1] = true;
            WXLogUtils.e("wrong virtualComponentId split error " + this.virtualComponentId);
            $jacocoInit[2] = true;
            return;
        }
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent instanceof WXRecyclerTemplateList) {
            WXRecyclerTemplateList wXRecyclerTemplateList = (WXRecyclerTemplateList) wXComponent;
            $jacocoInit[3] = true;
            wXRecyclerTemplateList.getCellDataManager().updateVirtualComponentData(this.virtualComponentId, this.data);
            $jacocoInit[4] = true;
            wXRecyclerTemplateList.notifyUpdateList();
            $jacocoInit[5] = true;
            SimpleJSCallback simpleJSCallback = new SimpleJSCallback(wXComponent.getInstanceId(), this.callback);
            $jacocoInit[6] = true;
            simpleJSCallback.invoke(true);
            $jacocoInit[7] = true;
        } else {
            WXLogUtils.e("recycler-list wrong virtualComponentId " + this.virtualComponentId);
            $jacocoInit[8] = true;
        }
        $jacocoInit[9] = true;
    }
}
