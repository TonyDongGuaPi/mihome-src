package com.taobao.weex.ui.component.list.template;

import android.text.TextUtils;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXCell;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Selector {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4837073812073842934L, "com/taobao/weex/ui/component/list/template/Selector", 43);
        $jacocoData = a2;
        return a2;
    }

    public Selector() {
        $jacocoInit()[0] = true;
    }

    public static void queryElementAll(WXComponent wXComponent, String str, List<WXComponent> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1] = true;
            return;
        }
        String replaceAll = str.replaceAll("\\[|]", "");
        $jacocoInit[2] = true;
        String[] split = replaceAll.split("=");
        if (split.length <= 0) {
            $jacocoInit[3] = true;
            return;
        }
        int i = 0;
        String str2 = split[0];
        String str3 = null;
        if (split.length <= 1) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            str3 = split[1].trim();
            $jacocoInit[6] = true;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[7] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[8] = true;
            $jacocoInit[9] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[11] = true;
                queryElementAllByAttrs(wXVContainer.getChild(i), str2, str3, list);
                i++;
                $jacocoInit[12] = true;
            }
            $jacocoInit[10] = true;
        }
        $jacocoInit[13] = true;
    }

    public static void closest(WXComponent wXComponent, String str, List<WXComponent> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[14] = true;
            return;
        }
        String replaceAll = str.replaceAll("\\[|]", "");
        $jacocoInit[15] = true;
        String[] split = replaceAll.split("=");
        if (split.length <= 0) {
            $jacocoInit[16] = true;
            return;
        }
        String str2 = split[0];
        String str3 = null;
        if (split.length <= 1) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            str3 = split[1].trim();
            $jacocoInit[19] = true;
        }
        closestByAttrs(wXComponent, str2, str3, list);
        $jacocoInit[20] = true;
    }

    private static void closestByAttrs(WXComponent wXComponent, String str, String str2, List<WXComponent> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!matchAttrs(wXComponent, str, str2)) {
            $jacocoInit[21] = true;
        } else {
            $jacocoInit[22] = true;
            list.add(wXComponent);
            $jacocoInit[23] = true;
        }
        if (wXComponent instanceof WXCell) {
            $jacocoInit[24] = true;
        } else if (wXComponent instanceof WXRecyclerTemplateList) {
            $jacocoInit[25] = true;
        } else {
            queryElementAllByAttrs(wXComponent.getParent(), str, str2, list);
            $jacocoInit[27] = true;
            return;
        }
        $jacocoInit[26] = true;
    }

    private static void queryElementAllByAttrs(WXComponent wXComponent, String str, String str2, List<WXComponent> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!matchAttrs(wXComponent, str, str2)) {
            $jacocoInit[28] = true;
        } else {
            $jacocoInit[29] = true;
            list.add(wXComponent);
            $jacocoInit[30] = true;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[31] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[32] = true;
            int i = 0;
            $jacocoInit[33] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[35] = true;
                queryElementAllByAttrs(wXVContainer.getChild(i), str, str2, list);
                i++;
                $jacocoInit[36] = true;
            }
            $jacocoInit[34] = true;
        }
        $jacocoInit[37] = true;
    }

    private static boolean matchAttrs(WXComponent wXComponent, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.isWaste()) {
            $jacocoInit[38] = true;
            return false;
        } else if (!wXComponent.getAttrs().containsKey(str)) {
            $jacocoInit[39] = true;
            return false;
        } else if (TextUtils.isEmpty(str2)) {
            $jacocoInit[40] = true;
            return true;
        } else {
            Object obj = wXComponent.getAttrs().get(str);
            if (obj == null) {
                $jacocoInit[41] = true;
                return false;
            }
            boolean equals = str2.equals(obj.toString());
            $jacocoInit[42] = true;
            return equals;
        }
    }
}
