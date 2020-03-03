package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.TypefaceUtil;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ActionAddRule implements IExecutable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final JSONObject mData;
    private final String mPageId;
    private final String mType;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8461762392667436055L, "com/taobao/weex/ui/action/ActionAddRule", 21);
        $jacocoData = a2;
        return a2;
    }

    public ActionAddRule(String str, String str2, JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPageId = str;
        this.mType = str2;
        this.mData = jSONObject;
        $jacocoInit[0] = true;
    }

    public void executeAction() {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getWXRenderManager().getWXSDKInstance(this.mPageId);
        $jacocoInit[1] = true;
        if (wXSDKInstance == null) {
            $jacocoInit[2] = true;
        } else if (wXSDKInstance.isDestroy()) {
            $jacocoInit[3] = true;
        } else if (!Constants.Name.FONT_FACE.equals(this.mType)) {
            $jacocoInit[5] = true;
            return;
        } else {
            FontDO parseFontDO = parseFontDO(this.mData, wXSDKInstance);
            $jacocoInit[6] = true;
            if (parseFontDO == null) {
                $jacocoInit[7] = true;
            } else if (TextUtils.isEmpty(parseFontDO.getFontFamilyName())) {
                $jacocoInit[8] = true;
            } else {
                $jacocoInit[9] = true;
                FontDO fontDO = TypefaceUtil.getFontDO(parseFontDO.getFontFamilyName());
                $jacocoInit[10] = true;
                if (fontDO == null) {
                    $jacocoInit[11] = true;
                } else if (!TextUtils.equals(fontDO.getUrl(), parseFontDO.getUrl())) {
                    $jacocoInit[12] = true;
                } else {
                    TypefaceUtil.loadTypeface(fontDO);
                    $jacocoInit[15] = true;
                }
                TypefaceUtil.putFontDO(parseFontDO);
                $jacocoInit[13] = true;
                TypefaceUtil.loadTypeface(parseFontDO);
                $jacocoInit[14] = true;
            }
            $jacocoInit[16] = true;
            return;
        }
        $jacocoInit[4] = true;
    }

    private FontDO parseFontDO(JSONObject jSONObject, WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONObject == null) {
            $jacocoInit[17] = true;
            return null;
        }
        String string = jSONObject.getString("src");
        $jacocoInit[18] = true;
        String string2 = jSONObject.getString("fontFamily");
        $jacocoInit[19] = true;
        FontDO fontDO = new FontDO(string2, string, wXSDKInstance);
        $jacocoInit[20] = true;
        return fontDO;
    }
}
