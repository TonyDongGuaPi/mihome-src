package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.ui.component.richtext.node.ANode;
import com.taobao.weex.ui.component.richtext.node.ImgNode;
import com.taobao.weex.ui.component.richtext.node.SpanNode;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class RichTextNodeManager {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static final Map<String, RichTextNodeCreator> registeredTextNodes = new ArrayMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1295576147426540307L, "com/taobao/weex/ui/component/richtext/node/RichTextNodeManager", 16);
        $jacocoData = a2;
        return a2;
    }

    public RichTextNodeManager() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[12] = true;
        registeredTextNodes.put("span", new SpanNode.SpanNodeCreator());
        $jacocoInit[13] = true;
        registeredTextNodes.put("image", new ImgNode.ImgNodeCreator());
        $jacocoInit[14] = true;
        registeredTextNodes.put("a", new ANode.ANodeCreator());
        $jacocoInit[15] = true;
    }

    public static void registerTextNode(String str, RichTextNodeCreator richTextNodeCreator) {
        boolean[] $jacocoInit = $jacocoInit();
        registeredTextNodes.put(str, richTextNodeCreator);
        $jacocoInit[1] = true;
    }

    @Nullable
    static RichTextNode createRichTextNode(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        RichTextNode richTextNode = null;
        try {
            $jacocoInit[2] = true;
            if (jSONObject == null) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                $jacocoInit[5] = true;
                RichTextNode createRichTextNode = registeredTextNodes.get(jSONObject.getString("type")).createRichTextNode(context, str, str2);
                $jacocoInit[6] = true;
                createRichTextNode.parse(context, str, str2, jSONObject);
                $jacocoInit[7] = true;
                richTextNode = createRichTextNode;
            }
            $jacocoInit[8] = true;
        } catch (Exception e) {
            $jacocoInit[9] = true;
            WXLogUtils.e("Richtext", WXLogUtils.getStackTrace(e));
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
        return richTextNode;
    }
}
