package com.taobao.weex.ui.component.richtext.span;

import android.text.style.ClickableSpan;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import com.taobao.weex.utils.WXDataStructureUtil;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ItemClickSpan extends ClickableSpan {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private final String mComponentRef;
    private final String mInstanceId;
    private final String mPseudoRef;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4433997260199872535L, "com/taobao/weex/ui/component/richtext/span/ItemClickSpan", 9);
        $jacocoData = a2;
        return a2;
    }

    public ItemClickSpan(String str, String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPseudoRef = str3;
        this.mInstanceId = str;
        this.mComponentRef = str2;
        $jacocoInit[0] = true;
    }

    public void onClick(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(this.mInstanceId);
        $jacocoInit[1] = true;
        if (sDKInstance == null) {
            $jacocoInit[2] = true;
        } else if (sDKInstance.isDestroy()) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            HashMap newHashMapWithExpectedSize = WXDataStructureUtil.newHashMapWithExpectedSize(1);
            $jacocoInit[5] = true;
            newHashMapWithExpectedSize.put(RichTextNode.PSEUDO_REF, this.mPseudoRef);
            $jacocoInit[6] = true;
            sDKInstance.fireEvent(this.mComponentRef, RichTextNode.ITEM_CLICK, newHashMapWithExpectedSize);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }
}
