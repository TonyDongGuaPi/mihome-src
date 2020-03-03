package com.taobao.weex.ui.component.list.template;

import android.support.v4.view.ViewCompat;
import android.view.View;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.view.listview.WXRecyclerView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TemplateDom {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ATTACH_CELL_SLOT = "_attach_slot";
    public static final String ATTRS_KEY_REF = "ref";
    public static final String DETACH_CELL_SLOT = "_detach_slot";
    public static final String KEY_ATTRS = "attrs";
    public static final String KEY_RESET_ANIMATION = "resetAnimation";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VIRTUAL_DOM_REF = "ref";
    public static final char SEPARATOR = '@';
    public static final String VIRTUAL_DOM_IDENTIFY = "[[VirtualElement]]";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8405210104888491867L, "com/taobao/weex/ui/component/list/template/TemplateDom", 99);
        $jacocoData = a2;
        return a2;
    }

    public TemplateDom() {
        $jacocoInit()[0] = true;
    }

    public static String genKeyVirtualDomRef(String str, int i, String str2) {
        String str3 = str + SEPARATOR + i + SEPARATOR + str2;
        $jacocoInit()[1] = true;
        return str3;
    }

    public static WXComponent findVirtualComponentByVRef(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            String[] split = str2.split("@");
            String str3 = split[0];
            $jacocoInit[2] = true;
            WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(str, str3);
            if (wXComponent instanceof WXRecyclerTemplateList) {
                $jacocoInit[3] = true;
                WXRecyclerTemplateList wXRecyclerTemplateList = (WXRecyclerTemplateList) wXComponent;
                $jacocoInit[5] = true;
                if (wXRecyclerTemplateList.getHostView() == null) {
                    $jacocoInit[6] = true;
                } else if (((BounceRecyclerView) wXRecyclerTemplateList.getHostView()).getInnerView() != null) {
                    $jacocoInit[7] = true;
                    int parseInt = Integer.parseInt(split[1]);
                    $jacocoInit[10] = true;
                    $jacocoInit[11] = true;
                    TemplateViewHolder templateViewHolder = (TemplateViewHolder) ((WXRecyclerView) ((BounceRecyclerView) wXRecyclerTemplateList.getHostView()).getInnerView()).findViewHolderForAdapterPosition(parseInt);
                    if (templateViewHolder != null) {
                        $jacocoInit[12] = true;
                        WXCell template = templateViewHolder.getTemplate();
                        String str4 = split[2];
                        $jacocoInit[14] = true;
                        WXComponent findComponentByViewTreeKey = findComponentByViewTreeKey(template, str4);
                        $jacocoInit[15] = true;
                        return findComponentByViewTreeKey;
                    }
                    $jacocoInit[13] = true;
                    return null;
                } else {
                    $jacocoInit[8] = true;
                }
                $jacocoInit[9] = true;
                return null;
            }
            $jacocoInit[4] = true;
            return null;
        } catch (Exception unused) {
            $jacocoInit[16] = true;
            return null;
        }
    }

    public static Map<String, Object> findAllComponentRefs(String str, int i, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[17] = true;
        findAllComponentRefs(str, i, wXComponent, hashMap);
        $jacocoInit[18] = true;
        HashMap hashMap2 = new HashMap();
        $jacocoInit[19] = true;
        hashMap2.put("refs", hashMap);
        $jacocoInit[20] = true;
        hashMap2.put("position", Integer.valueOf(i));
        $jacocoInit[21] = true;
        hashMap2.put("listRef", str);
        $jacocoInit[22] = true;
        return hashMap2;
    }

    private static void findAllComponentRefs(String str, int i, WXComponent wXComponent, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.isWaste()) {
            $jacocoInit[23] = true;
            return;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[24] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[25] = true;
            int i2 = 0;
            $jacocoInit[26] = true;
            while (i2 < wXVContainer.getChildCount()) {
                $jacocoInit[28] = true;
                WXComponent child = wXVContainer.getChild(i2);
                $jacocoInit[29] = true;
                findAllComponentRefs(str, i, child, map);
                i2++;
                $jacocoInit[30] = true;
            }
            $jacocoInit[27] = true;
        }
        WXAttr attrs = wXComponent.getAttrs();
        if (attrs == null) {
            $jacocoInit[31] = true;
        } else {
            $jacocoInit[32] = true;
            if (attrs.get("ref") != null) {
                $jacocoInit[33] = true;
            } else {
                $jacocoInit[34] = true;
                return;
            }
        }
        String obj = attrs.get("ref").toString();
        $jacocoInit[35] = true;
        List list = (List) map.get(obj);
        if (list != null) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            list = new ArrayList();
            $jacocoInit[38] = true;
            map.put(obj, list);
            $jacocoInit[39] = true;
        }
        Map map2 = toMap(str, i, wXComponent);
        $jacocoInit[40] = true;
        list.add(map2);
        $jacocoInit[41] = true;
    }

    public static Map toMap(String str, int i, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap();
        $jacocoInit[42] = true;
        hashMap.put("attrs", wXComponent.getAttrs());
        $jacocoInit[43] = true;
        hashMap.put("type", wXComponent.getComponentType());
        $jacocoInit[44] = true;
        hashMap.put("ref", genKeyVirtualDomRef(str, i, wXComponent.getViewTreeKey()));
        $jacocoInit[45] = true;
        hashMap.put(VIRTUAL_DOM_IDENTIFY, true);
        $jacocoInit[46] = true;
        return hashMap;
    }

    public static boolean isVirtualDomRef(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (str != null) {
            $jacocoInit[47] = true;
            if (str.indexOf(64) > 0) {
                $jacocoInit[48] = true;
                z = true;
            } else {
                $jacocoInit[49] = true;
            }
            $jacocoInit[50] = true;
            return z;
        }
        $jacocoInit[51] = true;
        return false;
    }

    public static void resetAnimaiton(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[52] = true;
            return;
        }
        if (ViewCompat.getTranslationX(view) == 0.0f) {
            $jacocoInit[53] = true;
        } else {
            $jacocoInit[54] = true;
            ViewCompat.setTranslationX(view, 0.0f);
            $jacocoInit[55] = true;
        }
        if (ViewCompat.getTranslationY(view) == 0.0f) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[57] = true;
            ViewCompat.setTranslationY(view, 0.0f);
            $jacocoInit[58] = true;
        }
        if (ViewCompat.getTranslationZ(view) == 0.0f) {
            $jacocoInit[59] = true;
        } else {
            $jacocoInit[60] = true;
            ViewCompat.setTranslationZ(view, 0.0f);
            $jacocoInit[61] = true;
        }
        if (ViewCompat.getScaleX(view) == 1.0f) {
            $jacocoInit[62] = true;
        } else {
            $jacocoInit[63] = true;
            ViewCompat.setScaleX(view, 1.0f);
            $jacocoInit[64] = true;
        }
        if (ViewCompat.getScaleY(view) == 1.0f) {
            $jacocoInit[65] = true;
        } else {
            $jacocoInit[66] = true;
            ViewCompat.setScaleY(view, 1.0f);
            $jacocoInit[67] = true;
        }
        if (ViewCompat.getRotationX(view) == 0.0f) {
            $jacocoInit[68] = true;
        } else {
            $jacocoInit[69] = true;
            ViewCompat.setRotationX(view, 0.0f);
            $jacocoInit[70] = true;
        }
        if (ViewCompat.getRotationY(view) == 0.0f) {
            $jacocoInit[71] = true;
        } else {
            $jacocoInit[72] = true;
            ViewCompat.setRotationY(view, 0.0f);
            $jacocoInit[73] = true;
        }
        if (ViewCompat.getElevation(view) == 0.0f) {
            $jacocoInit[74] = true;
        } else {
            $jacocoInit[75] = true;
            ViewCompat.setElevation(view, 0.0f);
            $jacocoInit[76] = true;
        }
        $jacocoInit[77] = true;
    }

    public static final WXComponent findComponentByViewTreeKey(WXComponent wXComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getViewTreeKey().equals(str)) {
            $jacocoInit[78] = true;
            return wXComponent;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[79] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[80] = true;
            int i = 0;
            $jacocoInit[81] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[83] = true;
                WXComponent child = wXVContainer.getChild(i);
                $jacocoInit[84] = true;
                if (findComponentByViewTreeKey(child, str) != null) {
                    $jacocoInit[85] = true;
                    return child;
                }
                i++;
                $jacocoInit[86] = true;
            }
            $jacocoInit[82] = true;
        }
        $jacocoInit[87] = true;
        return null;
    }

    private static WXComponent findChildByAttrsRef(WXComponent wXComponent, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getAttrs() == null) {
            $jacocoInit[88] = true;
        } else if (!str.equals(wXComponent.getAttrs().get("ref"))) {
            $jacocoInit[89] = true;
        } else {
            $jacocoInit[90] = true;
            return wXComponent;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[91] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[92] = true;
            int i = 0;
            $jacocoInit[93] = true;
            while (i < wXVContainer.getChildCount()) {
                $jacocoInit[95] = true;
                WXComponent findChildByAttrsRef = findChildByAttrsRef(wXVContainer.getChild(i), str);
                if (findChildByAttrsRef != null) {
                    $jacocoInit[96] = true;
                    return findChildByAttrsRef;
                }
                i++;
                $jacocoInit[97] = true;
            }
            $jacocoInit[94] = true;
        }
        $jacocoInit[98] = true;
        return null;
    }
}
