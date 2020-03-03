package com.taobao.weex.common;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.WXRenderManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class WXModule implements IWXObject {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ACTION_ACTIVITY_RESULT = "actionActivityResult";
    public static final String ACTION_REQUEST_PERMISSIONS_RESULT = "actionRequestPermissionsResult";
    public static final String GRANT_RESULTS = "grantResults";
    public static final String PERMISSIONS = "permissions";
    public static final String REQUEST_CODE = "requestCode";
    public static final String RESULT_CODE = "resultCode";
    private Map<String, List<String>> mEvents = new HashMap();
    private Map<String, Boolean> mKeepAlives;
    private String mModuleName;
    public WXSDKInstance mWXSDKInstance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3816829448770236215L, "com/taobao/weex/common/WXModule", 48);
        $jacocoData = a2;
        return a2;
    }

    public WXModule() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
        this.mKeepAlives = new HashMap();
        $jacocoInit[2] = true;
    }

    /* access modifiers changed from: protected */
    public final WXComponent findComponent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWXSDKInstance == null) {
            $jacocoInit[3] = true;
        } else if (str == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            WXSDKManager instance = WXSDKManager.getInstance();
            $jacocoInit[6] = true;
            WXRenderManager wXRenderManager = instance.getWXRenderManager();
            WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
            $jacocoInit[7] = true;
            WXComponent wXComponent = wXRenderManager.getWXComponent(wXSDKInstance.getInstanceId(), str);
            $jacocoInit[8] = true;
            return wXComponent;
        }
        $jacocoInit[9] = true;
        return null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        $jacocoInit()[10] = true;
    }

    public void onActivityCreate() {
        $jacocoInit()[11] = true;
    }

    public void onActivityStart() {
        $jacocoInit()[12] = true;
    }

    public void onActivityPause() {
        $jacocoInit()[13] = true;
    }

    public void onActivityResume() {
        $jacocoInit()[14] = true;
    }

    public void onActivityStop() {
        $jacocoInit()[15] = true;
    }

    public void onActivityDestroy() {
        $jacocoInit()[16] = true;
    }

    public boolean onActivityBack() {
        $jacocoInit()[17] = true;
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        $jacocoInit()[18] = true;
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        $jacocoInit()[19] = true;
    }

    @JSMethod
    public void addEventListener(String str, String str2, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[20] = true;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[23] = true;
            boolean z = false;
            if (map == null) {
                $jacocoInit[24] = true;
            } else if (map.size() <= 0) {
                $jacocoInit[25] = true;
            } else if (!map.containsKey("once")) {
                $jacocoInit[26] = true;
            } else {
                $jacocoInit[27] = true;
                Object obj = map.get("once");
                $jacocoInit[28] = true;
                if (!WXUtils.getBoolean(obj, false).booleanValue()) {
                    $jacocoInit[29] = true;
                } else {
                    $jacocoInit[30] = true;
                    z = true;
                }
            }
            this.mKeepAlives.put(str2, Boolean.valueOf(z));
            $jacocoInit[31] = true;
            if (this.mEvents.get(str) != null) {
                $jacocoInit[32] = true;
            } else {
                $jacocoInit[33] = true;
                this.mEvents.put(str, new ArrayList());
                $jacocoInit[34] = true;
            }
            this.mEvents.get(str).add(str2);
            $jacocoInit[35] = true;
            return;
        }
        $jacocoInit[22] = true;
    }

    @JSMethod
    public void removeAllEventListeners(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mEvents.containsKey(str)) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            $jacocoInit[38] = true;
            $jacocoInit[39] = true;
            for (String remove : this.mEvents.remove(str)) {
                $jacocoInit[41] = true;
                this.mKeepAlives.remove(remove);
                $jacocoInit[42] = true;
            }
            $jacocoInit[40] = true;
        }
        $jacocoInit[43] = true;
    }

    public List<String> getEventCallbacks(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> list = this.mEvents.get(str);
        $jacocoInit[44] = true;
        return list;
    }

    public boolean isOnce(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean booleanValue = this.mKeepAlives.get(str).booleanValue();
        $jacocoInit[45] = true;
        return booleanValue;
    }

    public String getModuleName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mModuleName;
        $jacocoInit[46] = true;
        return str;
    }

    public void setModuleName(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mModuleName = str;
        $jacocoInit[47] = true;
    }
}
