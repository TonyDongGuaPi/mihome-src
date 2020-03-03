package com.taobao.weex.ui.module;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXMetaModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String DEVICE_WIDTH = "device-width";
    public static final String WIDTH = "width";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8057675258961985746L, "com/taobao/weex/ui/module/WXMetaModule", 37);
        $jacocoData = a2;
        return a2;
    }

    public WXMetaModule() {
        $jacocoInit()[0] = true;
    }

    @JSMethod(uiThread = false)
    public void setViewport(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1] = true;
        } else {
            try {
                $jacocoInit[2] = true;
                String decode = URLDecoder.decode(str, "utf-8");
                $jacocoInit[3] = true;
                JSONObject parseObject = JSON.parseObject(decode);
                $jacocoInit[4] = true;
                Context context = this.mWXSDKInstance.getContext();
                $jacocoInit[5] = true;
                if (DEVICE_WIDTH.endsWith(parseObject.getString("width"))) {
                    $jacocoInit[6] = true;
                    int screenWidth = (int) (((float) WXViewUtils.getScreenWidth(context)) / WXViewUtils.getScreenDensity(context));
                    $jacocoInit[7] = true;
                    this.mWXSDKInstance.setInstanceViewPortWidth(screenWidth);
                    $jacocoInit[8] = true;
                    WXLogUtils.d("[WXMetaModule] setViewport success[device-width]=" + screenWidth);
                    $jacocoInit[9] = true;
                } else {
                    int intValue = parseObject.getInteger("width").intValue();
                    if (intValue <= 0) {
                        $jacocoInit[10] = true;
                    } else {
                        $jacocoInit[11] = true;
                        this.mWXSDKInstance.setInstanceViewPortWidth(intValue);
                        $jacocoInit[12] = true;
                    }
                    WXLogUtils.d("[WXMetaModule] setViewport success[width]=" + intValue);
                    $jacocoInit[13] = true;
                }
                $jacocoInit[14] = true;
            } catch (Exception e) {
                $jacocoInit[15] = true;
                WXLogUtils.e("[WXMetaModule] alert param parse error ", (Throwable) e);
                $jacocoInit[16] = true;
            }
        }
        $jacocoInit[17] = true;
    }

    @JSMethod(uiThread = true)
    public void openLog(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        Application application = WXEnvironment.getApplication();
        if (application == null) {
            $jacocoInit[18] = true;
            return;
        }
        if ((application.getApplicationInfo().flags & 2) == 0) {
            $jacocoInit[19] = true;
        } else {
            $jacocoInit[20] = true;
            if (WXUtils.getBoolean(str, true).booleanValue()) {
                $jacocoInit[21] = true;
                WXEnvironment.setApkDebugable(true);
                if (this.mWXSDKInstance == null) {
                    $jacocoInit[22] = true;
                } else {
                    $jacocoInit[23] = true;
                    Toast.makeText(this.mWXSDKInstance.getContext(), "log open success", 0).show();
                    $jacocoInit[24] = true;
                }
            } else {
                WXEnvironment.setApkDebugable(false);
                if (this.mWXSDKInstance == null) {
                    $jacocoInit[25] = true;
                } else {
                    $jacocoInit[26] = true;
                    Toast.makeText(this.mWXSDKInstance.getContext(), "log close success", 0).show();
                    $jacocoInit[27] = true;
                }
            }
        }
        $jacocoInit[28] = true;
    }

    @JSMethod(uiThread = false)
    public void getPageInfo(JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSCallback == null) {
            $jacocoInit[29] = true;
            return;
        }
        List<WXSDKInstance> allInstances = WXSDKManager.getInstance().getWXRenderManager().getAllInstances();
        $jacocoInit[30] = true;
        HashMap hashMap = new HashMap(4);
        $jacocoInit[31] = true;
        $jacocoInit[32] = true;
        for (WXSDKInstance next : allInstances) {
            $jacocoInit[33] = true;
            if (TextUtils.isEmpty(next.getBundleUrl())) {
                $jacocoInit[34] = true;
            } else {
                hashMap.put(next.getBundleUrl(), next.getTemplateInfo());
                $jacocoInit[35] = true;
            }
        }
        jSCallback.invoke(hashMap);
        $jacocoInit[36] = true;
    }
}
