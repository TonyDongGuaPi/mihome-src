package com.taobao.weex.ui.component.binding;

import android.os.Looper;
import android.support.v4.util.ArrayMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.WXStatement;
import com.taobao.weex.el.parse.ArrayStack;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.el.parse.Token;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.action.GraphicPosition;
import com.taobao.weex.ui.action.GraphicSize;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentFactory;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.list.WXCell;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.taobao.weex.ui.component.list.template.CellRenderContext;
import com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Statements {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private static final ThreadLocal<Map<String, Object>> dynamicLocal = new ThreadLocal<>();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3931216983554721517L, "com/taobao/weex/ui/component/binding/Statements", TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD);
        $jacocoData = a2;
        return a2;
    }

    public Statements() {
        $jacocoInit()[0] = true;
    }

    public static void parseStatementsToken(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!wXComponent.getBasicComponentData().isRenderPtrEmpty()) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            wXComponent.getBasicComponentData().setRenderObjectPr(wXComponent.getRenderObjectPtr());
            $jacocoInit[3] = true;
        }
        if (wXComponent.getBasicComponentData() == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            BasicComponentData basicComponentData = wXComponent.getBasicComponentData();
            $jacocoInit[6] = true;
            basicComponentData.getAttrs().parseStatements();
            $jacocoInit[7] = true;
            basicComponentData.getStyles().parseStatements();
            $jacocoInit[8] = true;
            basicComponentData.getEvents().parseStatements();
            $jacocoInit[9] = true;
        }
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[10] = true;
        } else {
            WXVContainer wXVContainer = (WXVContainer) wXComponent;
            $jacocoInit[11] = true;
            int childCount = wXVContainer.getChildCount();
            int i = 0;
            $jacocoInit[12] = true;
            while (i < childCount) {
                $jacocoInit[14] = true;
                parseStatementsToken(wXVContainer.getChild(i));
                i++;
                $jacocoInit[15] = true;
            }
            $jacocoInit[13] = true;
        }
        $jacocoInit[16] = true;
    }

    public static void initLazyComponent(WXComponent wXComponent, WXVContainer wXVContainer) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.isLazy()) {
            $jacocoInit[17] = true;
        } else if (wXComponent.getHostView() != null) {
            $jacocoInit[18] = true;
            $jacocoInit[26] = true;
        } else {
            $jacocoInit[19] = true;
        }
        wXComponent.lazy(false);
        if (wXVContainer != null) {
            $jacocoInit[20] = true;
            int indexOf = wXVContainer.indexOf(wXComponent);
            $jacocoInit[21] = true;
            wXVContainer.createChildViewAt(indexOf);
            $jacocoInit[22] = true;
        } else {
            wXComponent.createView();
            $jacocoInit[23] = true;
        }
        wXComponent.applyLayoutAndEvent(wXComponent);
        $jacocoInit[24] = true;
        wXComponent.bindData(wXComponent);
        $jacocoInit[25] = true;
        $jacocoInit[26] = true;
    }

    public static WXComponent copyComponentTree(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        WXComponent copyComponentTree = copyComponentTree(wXComponent, wXComponent.getParent());
        $jacocoInit[27] = true;
        return copyComponentTree;
    }

    private static final WXComponent copyComponentTree(WXComponent wXComponent, WXVContainer wXVContainer) {
        BasicComponentData basicComponentData;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[28] = true;
            basicComponentData = wXComponent.getBasicComponentData().clone();
            $jacocoInit[29] = true;
        } catch (CloneNotSupportedException e) {
            $jacocoInit[30] = true;
            e.printStackTrace();
            $jacocoInit[31] = true;
            basicComponentData = null;
        }
        WXComponent newInstance = WXComponentFactory.newInstance(wXComponent.getInstance(), wXVContainer, basicComponentData);
        $jacocoInit[32] = true;
        GraphicPosition layoutPosition = wXComponent.getLayoutPosition();
        $jacocoInit[33] = true;
        GraphicSize layoutSize = wXComponent.getLayoutSize();
        $jacocoInit[34] = true;
        float top = layoutPosition.getTop();
        float bottom = layoutPosition.getBottom();
        float left = layoutPosition.getLeft();
        float right = layoutPosition.getRight();
        $jacocoInit[35] = true;
        float height = layoutSize.getHeight();
        float width = layoutSize.getWidth();
        $jacocoInit[36] = true;
        newInstance.updateDemission(top, bottom, left, right, height, width);
        $jacocoInit[37] = true;
        newInstance.updateExtra(wXComponent.getExtra());
        if (!(wXComponent instanceof WXVContainer)) {
            $jacocoInit[38] = true;
        } else {
            WXVContainer wXVContainer2 = (WXVContainer) wXComponent;
            WXVContainer wXVContainer3 = (WXVContainer) newInstance;
            $jacocoInit[39] = true;
            int childCount = wXVContainer2.getChildCount();
            int i = 0;
            $jacocoInit[40] = true;
            while (i < childCount) {
                $jacocoInit[42] = true;
                WXComponent child = wXVContainer2.getChild(i);
                if (child == null) {
                    $jacocoInit[43] = true;
                } else {
                    $jacocoInit[44] = true;
                    WXComponent copyComponentTree = copyComponentTree(child, wXVContainer3);
                    wXVContainer3.addChild(copyComponentTree);
                    $jacocoInit[45] = true;
                    long renderObjectPtr = wXVContainer3.getRenderObjectPtr();
                    $jacocoInit[46] = true;
                    long renderObjectPtr2 = copyComponentTree.getRenderObjectPtr();
                    $jacocoInit[47] = true;
                    NativeRenderObjectUtils.nativeAddChildRenderObject(renderObjectPtr, renderObjectPtr2);
                    $jacocoInit[48] = true;
                }
                i++;
                $jacocoInit[49] = true;
            }
            $jacocoInit[41] = true;
        }
        if (!wXComponent.isWaste()) {
            $jacocoInit[50] = true;
        } else {
            $jacocoInit[51] = true;
            newInstance.setWaste(true);
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
        return newInstance;
    }

    public static final List<WXComponent> doRender(WXComponent wXComponent, CellRenderContext cellRenderContext) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(4);
        try {
            $jacocoInit[54] = true;
            doRenderComponent(wXComponent, cellRenderContext, arrayList);
            $jacocoInit[55] = true;
        } catch (Exception e) {
            $jacocoInit[56] = true;
            WXLogUtils.e("WeexStatementRender", (Throwable) e);
            $jacocoInit[57] = true;
        }
        $jacocoInit[58] = true;
        return arrayList;
    }

    public static final void doInitCompontent(List<WXComponent> list) {
        boolean[] $jacocoInit = $jacocoInit();
        if (list == null) {
            $jacocoInit[59] = true;
        } else if (list.size() == 0) {
            $jacocoInit[60] = true;
        } else {
            $jacocoInit[62] = true;
            for (WXComponent next : list) {
                $jacocoInit[63] = true;
                if (next.getParent() != null) {
                    WXVContainer parent = next.getParent();
                    $jacocoInit[66] = true;
                    int indexOf = parent.indexOf(next);
                    if (indexOf >= 0) {
                        parent.createChildViewAt(indexOf);
                        $jacocoInit[69] = true;
                        next.applyLayoutAndEvent(next);
                        $jacocoInit[70] = true;
                        next.bindData(next);
                        $jacocoInit[71] = true;
                    } else {
                        $jacocoInit[67] = true;
                        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("render node cann't find");
                        $jacocoInit[68] = true;
                        throw illegalArgumentException;
                    }
                } else {
                    $jacocoInit[64] = true;
                    IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("render node parent cann't find");
                    $jacocoInit[65] = true;
                    throw illegalArgumentException2;
                }
            }
            $jacocoInit[72] = true;
            return;
        }
        $jacocoInit[61] = true;
    }

    private static final int doRenderComponent(WXComponent wXComponent, CellRenderContext cellRenderContext, List<WXComponent> list) {
        Token token;
        JSONObject jSONObject;
        Object obj;
        Map map;
        Collection collection;
        int i;
        Object obj2;
        WXComponent wXComponent2;
        Token token2;
        Map map2;
        String str;
        WXComponent wXComponent3 = wXComponent;
        CellRenderContext cellRenderContext2 = cellRenderContext;
        List<WXComponent> list2 = list;
        boolean[] $jacocoInit = $jacocoInit();
        WXVContainer parent = wXComponent.getParent();
        $jacocoInit[73] = true;
        WXAttr attrs = wXComponent.getAttrs();
        $jacocoInit[74] = true;
        WXStatement statement = attrs.getStatement();
        if (statement == null) {
            $jacocoInit[75] = true;
        } else {
            $jacocoInit[76] = true;
            if (!(statement.get(WXStatement.WX_IF) instanceof Token)) {
                $jacocoInit[77] = true;
                token = null;
            } else {
                $jacocoInit[78] = true;
                token = (Token) statement.get(WXStatement.WX_IF);
                $jacocoInit[79] = true;
            }
            if (!(statement.get(WXStatement.WX_FOR) instanceof JSONObject)) {
                $jacocoInit[80] = true;
                jSONObject = null;
            } else {
                $jacocoInit[81] = true;
                jSONObject = (JSONObject) statement.get(WXStatement.WX_FOR);
                $jacocoInit[82] = true;
            }
            boolean z = false;
            if (jSONObject != null) {
                $jacocoInit[83] = true;
                int indexOf = parent.indexOf(wXComponent3);
                $jacocoInit[84] = true;
                if (jSONObject.get(WXStatement.WX_FOR_LIST) instanceof Token) {
                    $jacocoInit[85] = true;
                    Token token3 = (Token) jSONObject.get(WXStatement.WX_FOR_LIST);
                    $jacocoInit[86] = true;
                    String string = jSONObject.getString(WXStatement.WX_FOR_INDEX);
                    $jacocoInit[87] = true;
                    String string2 = jSONObject.getString(WXStatement.WX_FOR_ITEM);
                    if (token3 == null) {
                        $jacocoInit[88] = true;
                        obj = null;
                    } else {
                        $jacocoInit[89] = true;
                        obj = token3.execute(cellRenderContext2.stack);
                        $jacocoInit[90] = true;
                    }
                    boolean z2 = obj instanceof List;
                    if (z2) {
                        $jacocoInit[91] = true;
                    } else if (!(obj instanceof Map)) {
                        $jacocoInit[92] = true;
                        $jacocoInit[153] = true;
                    } else {
                        $jacocoInit[93] = true;
                    }
                    if (z2) {
                        collection = (List) obj;
                        $jacocoInit[94] = true;
                        map = null;
                    } else {
                        Map map3 = (Map) obj;
                        $jacocoInit[95] = true;
                        Set keySet = map3.keySet();
                        $jacocoInit[96] = true;
                        Set set = keySet;
                        map = map3;
                        collection = set;
                    }
                    HashMap hashMap = new HashMap();
                    $jacocoInit[97] = true;
                    $jacocoInit[98] = true;
                    int i2 = 0;
                    for (Object next : collection) {
                        if (map == null) {
                            $jacocoInit[100] = true;
                            Integer valueOf = Integer.valueOf(i2);
                            $jacocoInit[101] = true;
                            Integer num = valueOf;
                            i = i2 + 1;
                            obj2 = next;
                            next = num;
                        } else {
                            $jacocoInit[102] = true;
                            Object obj3 = map.get(next);
                            $jacocoInit[103] = true;
                            Object obj4 = obj3;
                            i = i2;
                            obj2 = obj4;
                        }
                        if (string == null) {
                            $jacocoInit[104] = true;
                        } else {
                            $jacocoInit[105] = true;
                            hashMap.put(string, next);
                            $jacocoInit[106] = true;
                        }
                        if (string2 != null) {
                            $jacocoInit[107] = true;
                            hashMap.put(string2, obj2);
                            $jacocoInit[108] = true;
                        } else {
                            cellRenderContext2.stack.push(obj2);
                            $jacocoInit[109] = true;
                        }
                        if (hashMap.size() <= 0) {
                            $jacocoInit[110] = true;
                        } else {
                            $jacocoInit[111] = true;
                            cellRenderContext2.stack.push(hashMap);
                            $jacocoInit[112] = true;
                        }
                        if (token == null) {
                            $jacocoInit[113] = true;
                        } else {
                            $jacocoInit[114] = true;
                            if (Operators.isTrue(token.execute(cellRenderContext2.stack))) {
                                $jacocoInit[115] = true;
                            } else {
                                Token token4 = token;
                                Map map4 = map;
                                String str2 = string;
                                $jacocoInit[116] = true;
                                i2 = i;
                                z = false;
                            }
                        }
                        $jacocoInit[117] = true;
                        if (indexOf >= parent.getChildCount()) {
                            $jacocoInit[118] = true;
                            wXComponent2 = null;
                        } else {
                            $jacocoInit[119] = true;
                            WXComponent child = parent.getChild(indexOf);
                            $jacocoInit[120] = true;
                            if (isCreateFromNodeStatement(child, wXComponent3)) {
                                $jacocoInit[121] = true;
                                wXComponent2 = child;
                            } else {
                                $jacocoInit[122] = true;
                                wXComponent2 = null;
                            }
                            if (wXComponent2 == null) {
                                $jacocoInit[123] = true;
                            } else {
                                $jacocoInit[124] = true;
                                if (!wXComponent2.isWaste()) {
                                    $jacocoInit[125] = true;
                                } else {
                                    $jacocoInit[126] = true;
                                    wXComponent2.setWaste(z);
                                    $jacocoInit[127] = true;
                                }
                            }
                        }
                        if (wXComponent2 != null) {
                            $jacocoInit[128] = true;
                            token2 = token;
                            map2 = map;
                            str = string;
                        } else {
                            $jacocoInit[129] = true;
                            long currentTimeMillis = System.currentTimeMillis();
                            $jacocoInit[130] = true;
                            wXComponent2 = copyComponentTree(wXComponent3, parent);
                            $jacocoInit[131] = true;
                            wXComponent2.setWaste(z);
                            $jacocoInit[132] = true;
                            if (wXComponent2.getAttrs().getStatement() == null) {
                                $jacocoInit[133] = true;
                            } else {
                                $jacocoInit[134] = true;
                                wXComponent2.getAttrs().getStatement().remove(WXStatement.WX_FOR);
                                $jacocoInit[135] = true;
                                wXComponent2.getAttrs().getStatement().remove(WXStatement.WX_IF);
                                $jacocoInit[136] = true;
                            }
                            parent.addChild(wXComponent2, indexOf);
                            $jacocoInit[137] = true;
                            map2 = map;
                            str = string;
                            long renderObjectPtr = parent.getRenderObjectPtr();
                            $jacocoInit[138] = true;
                            token2 = token;
                            long renderObjectPtr2 = wXComponent2.getRenderObjectPtr();
                            $jacocoInit[139] = true;
                            NativeRenderObjectUtils.nativeAddChildRenderObject(renderObjectPtr, renderObjectPtr2);
                            $jacocoInit[140] = true;
                            list2.add(wXComponent2);
                            $jacocoInit[141] = true;
                            if (!WXEnvironment.isApkDebugable()) {
                                $jacocoInit[142] = true;
                            } else {
                                $jacocoInit[143] = true;
                                WXLogUtils.d(WXRecyclerTemplateList.TAG, Thread.currentThread().getName() + wXComponent2.getRef() + wXComponent2.getComponentType() + "statements copy component tree used " + (System.currentTimeMillis() - currentTimeMillis));
                                $jacocoInit[144] = true;
                            }
                        }
                        doBindingAttrsEventAndRenderChildNode(wXComponent2, cellRenderContext2, list2);
                        indexOf++;
                        $jacocoInit[145] = true;
                        if (hashMap.size() <= 0) {
                            $jacocoInit[146] = true;
                        } else {
                            $jacocoInit[147] = true;
                            cellRenderContext2.stack.push(hashMap);
                            $jacocoInit[148] = true;
                        }
                        if (string2 != null) {
                            $jacocoInit[149] = true;
                        } else {
                            $jacocoInit[150] = true;
                            cellRenderContext2.stack.pop();
                            $jacocoInit[151] = true;
                        }
                        $jacocoInit[152] = true;
                        i2 = i;
                        string = str;
                        map = map2;
                        token = token2;
                        z = false;
                    }
                    $jacocoInit[99] = true;
                    $jacocoInit[153] = true;
                } else {
                    WXLogUtils.e(WXRecyclerTemplateList.TAG, jSONObject.toJSONString() + " not call vfor block, for pre compile");
                    $jacocoInit[154] = true;
                }
                while (true) {
                    if (indexOf >= parent.getChildCount()) {
                        $jacocoInit[155] = true;
                        break;
                    }
                    $jacocoInit[156] = true;
                    WXComponent child2 = parent.getChild(indexOf);
                    $jacocoInit[157] = true;
                    if (!isCreateFromNodeStatement(child2, wXComponent3)) {
                        $jacocoInit[158] = true;
                        break;
                    }
                    child2.setWaste(true);
                    indexOf++;
                    $jacocoInit[159] = true;
                }
                int indexOf2 = indexOf - parent.indexOf(wXComponent3);
                $jacocoInit[160] = true;
                return indexOf2;
            }
            Token token5 = token;
            if (token5 == null) {
                $jacocoInit[161] = true;
            } else {
                $jacocoInit[162] = true;
                if (!Operators.isTrue(token5.execute(cellRenderContext2.stack))) {
                    $jacocoInit[163] = true;
                    wXComponent3.setWaste(true);
                    $jacocoInit[164] = true;
                    return 1;
                }
                wXComponent3.setWaste(false);
                $jacocoInit[165] = true;
            }
        }
        doBindingAttrsEventAndRenderChildNode(wXComponent, cellRenderContext, list);
        $jacocoInit[166] = true;
        return 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: java.util.Map<java.lang.String, java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v32, resolved type: java.util.Map<java.lang.String, java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v46, resolved type: java.util.Map<java.lang.String, java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v47, resolved type: java.util.Map<java.lang.String, java.lang.Object>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doBindingAttrsEventAndRenderChildNode(com.taobao.weex.ui.component.WXComponent r16, com.taobao.weex.ui.component.list.template.CellRenderContext r17, java.util.List<com.taobao.weex.ui.component.WXComponent> r18) {
        /*
            r0 = r16
            r1 = r17
            boolean[] r2 = $jacocoInit()
            com.taobao.weex.dom.WXAttr r3 = r16.getAttrs()
            com.taobao.weex.el.parse.ArrayStack r4 = r1.stack
            r5 = 1
            r6 = 167(0xa7, float:2.34E-43)
            r2[r6] = r5
            java.lang.String r6 = "@isComponentRoot"
            java.lang.Object r6 = r3.get(r6)
            r10 = 0
            r11 = 0
            if (r6 != 0) goto L_0x0022
            r6 = 168(0xa8, float:2.35E-43)
            r2[r6] = r5
            goto L_0x0064
        L_0x0022:
            java.lang.String r6 = "@isComponentRoot"
            r12 = 169(0xa9, float:2.37E-43)
            r2[r12] = r5
            java.lang.Object r6 = r3.get(r6)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r11)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r12)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x003f
            r6 = 170(0xaa, float:2.38E-43)
            r2[r6] = r5
            goto L_0x0064
        L_0x003f:
            r6 = 171(0xab, float:2.4E-43)
            r2[r6] = r5
            java.lang.String r6 = "@componentProps"
            java.lang.Object r6 = r3.get(r6)
            if (r6 != 0) goto L_0x0050
            r6 = 172(0xac, float:2.41E-43)
            r2[r6] = r5
            goto L_0x0064
        L_0x0050:
            java.lang.String r6 = "@componentProps"
            r12 = 173(0xad, float:2.42E-43)
            r2[r12] = r5
            java.lang.Object r6 = r3.get(r6)
            boolean r6 = com.taobao.weex.dom.binding.JSONUtils.isJSON((java.lang.Object) r6)
            if (r6 != 0) goto L_0x0067
            r6 = 174(0xae, float:2.44E-43)
            r2[r6] = r5
        L_0x0064:
            r6 = 0
            goto L_0x023b
        L_0x0067:
            r6 = 175(0xaf, float:2.45E-43)
            r2[r6] = r5
            java.lang.String r6 = "@templateId"
            java.lang.Object r6 = r3.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            r12 = 176(0xb0, float:2.47E-43)
            r2[r12] = r5
            boolean r12 = android.text.TextUtils.isEmpty(r6)
            if (r12 != 0) goto L_0x020c
            r12 = 177(0xb1, float:2.48E-43)
            r2[r12] = r5
            com.taobao.weex.ui.component.list.template.CellRenderState r12 = r17.getRenderState()
            java.util.Map r12 = r12.getVirtualComponentIds()
            java.lang.String r13 = r16.getViewTreeKey()
            java.lang.Object r12 = r12.get(r13)
            java.lang.String r12 = (java.lang.String) r12
            r13 = 5
            if (r12 != 0) goto L_0x0158
            r12 = 178(0xb2, float:2.5E-43)
            r2[r12] = r5
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r12 = r1.templateList
            java.lang.String r12 = r12.getRef()
            r14 = 179(0xb3, float:2.51E-43)
            r2[r14] = r5
            java.lang.String r14 = r16.getViewTreeKey()
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r15 = r1.templateList
            int r7 = r1.position
            long r8 = r15.getItemId(r7)
            r7 = 180(0xb4, float:2.52E-43)
            r2[r7] = r5
            java.lang.String r12 = com.taobao.weex.ui.component.list.template.CellDataManager.createVirtualComponentId(r12, r14, r8)
            r7 = 181(0xb5, float:2.54E-43)
            r2[r7] = r5
            java.lang.String r7 = "@componentProps"
            java.lang.Object r7 = r3.get(r7)
            com.alibaba.fastjson.JSONObject r7 = com.taobao.weex.dom.binding.JSONUtils.toJSON(r7)
            com.taobao.weex.el.parse.ArrayStack r8 = r1.stack
            java.util.Map r7 = renderProps(r7, r8)
            r8 = 182(0xb6, float:2.55E-43)
            r2[r8] = r5
            com.taobao.weex.bridge.WXBridgeManager r8 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            java.lang.String r9 = "componentHook"
            java.lang.String r14 = r16.getInstanceId()
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r13[r11] = r6
            java.lang.String r6 = "lifecycle"
            r13[r5] = r6
            java.lang.String r6 = "create"
            r15 = 2
            r13[r15] = r6
            java.lang.Object[] r6 = new java.lang.Object[r15]
            r6[r11] = r12
            r6[r5] = r7
            r15 = 3
            r13[r15] = r6
            r6 = 4
            r13[r6] = r10
            com.taobao.weex.bridge.EventResult r6 = r8.syncCallJSEventWithResult(r9, r14, r10, r13)
            if (r6 != 0) goto L_0x00fe
            r6 = 183(0xb7, float:2.56E-43)
            r2[r6] = r5
            goto L_0x012f
        L_0x00fe:
            r8 = 184(0xb8, float:2.58E-43)
            r2[r8] = r5
            java.lang.Object r8 = r6.getResult()
            if (r8 != 0) goto L_0x010d
            r6 = 185(0xb9, float:2.59E-43)
            r2[r6] = r5
            goto L_0x012f
        L_0x010d:
            r8 = 186(0xba, float:2.6E-43)
            r2[r8] = r5
            java.lang.Object r8 = r6.getResult()
            boolean r8 = r8 instanceof java.util.Map
            if (r8 != 0) goto L_0x011e
            r6 = 187(0xbb, float:2.62E-43)
            r2[r6] = r5
            goto L_0x012f
        L_0x011e:
            r8 = 188(0xbc, float:2.63E-43)
            r2[r8] = r5
            java.lang.Object r6 = r6.getResult()
            java.util.Map r6 = (java.util.Map) r6
            r7.putAll(r6)
            r6 = 189(0xbd, float:2.65E-43)
            r2[r6] = r5
        L_0x012f:
            r6 = 190(0xbe, float:2.66E-43)
            r2[r6] = r5
            com.taobao.weex.ui.component.list.template.CellRenderState r6 = r17.getRenderState()
            java.util.Map r6 = r6.getVirtualComponentIds()
            java.lang.String r8 = r16.getViewTreeKey()
            r6.put(r8, r12)
            r6 = 191(0xbf, float:2.68E-43)
            r2[r6] = r5
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r6 = r1.templateList
            com.taobao.weex.ui.component.list.template.CellDataManager r6 = r6.getCellDataManager()
            int r8 = r1.position
            r6.createVirtualComponentData(r8, r12, r7)
            r6 = 192(0xc0, float:2.69E-43)
            r2[r6] = r5
            r6 = 1
            goto L_0x01fd
        L_0x0158:
            com.taobao.weex.ui.component.list.template.CellRenderState r6 = r17.getRenderState()
            java.util.Map r6 = r6.getVirtualComponentDatas()
            java.lang.Object r7 = r6.get(r12)
            r6 = 193(0xc1, float:2.7E-43)
            r2[r6] = r5
            com.taobao.weex.ui.component.list.template.CellRenderState r6 = r17.getRenderState()
            boolean r6 = r6.isHasDataUpdate()
            if (r6 != 0) goto L_0x0177
            r6 = 194(0xc2, float:2.72E-43)
            r2[r6] = r5
            goto L_0x01db
        L_0x0177:
            r6 = 195(0xc3, float:2.73E-43)
            r2[r6] = r5
            java.lang.String r6 = "@componentProps"
            java.lang.Object r6 = r3.get(r6)
            com.alibaba.fastjson.JSONObject r6 = (com.alibaba.fastjson.JSONObject) r6
            com.taobao.weex.el.parse.ArrayStack r8 = r1.stack
            java.util.Map r6 = renderProps(r6, r8)
            r8 = 196(0xc4, float:2.75E-43)
            r2[r8] = r5
            com.taobao.weex.bridge.WXBridgeManager r8 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            java.lang.String r9 = "componentHook"
            java.lang.String r14 = r16.getInstanceId()
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r13[r11] = r12
            java.lang.String r15 = "lifecycle"
            r13[r5] = r15
            java.lang.String r15 = "syncState"
            r10 = 2
            r13[r10] = r15
            java.lang.Object[] r15 = new java.lang.Object[r10]
            r15[r11] = r12
            r15[r5] = r6
            r10 = 3
            r13[r10] = r15
            r10 = 0
            r15 = 4
            r13[r15] = r10
            com.taobao.weex.bridge.EventResult r8 = r8.syncCallJSEventWithResult(r9, r14, r10, r13)
            if (r8 != 0) goto L_0x01bc
            r6 = 197(0xc5, float:2.76E-43)
            r2[r6] = r5
            goto L_0x01db
        L_0x01bc:
            r9 = 198(0xc6, float:2.77E-43)
            r2[r9] = r5
            java.lang.Object r9 = r8.getResult()
            if (r9 != 0) goto L_0x01cb
            r6 = 199(0xc7, float:2.79E-43)
            r2[r6] = r5
            goto L_0x01db
        L_0x01cb:
            r9 = 200(0xc8, float:2.8E-43)
            r2[r9] = r5
            java.lang.Object r9 = r8.getResult()
            boolean r9 = r9 instanceof java.util.Map
            if (r9 != 0) goto L_0x01dd
            r6 = 201(0xc9, float:2.82E-43)
            r2[r6] = r5
        L_0x01db:
            r6 = 0
            goto L_0x01fd
        L_0x01dd:
            r7 = 202(0xca, float:2.83E-43)
            r2[r7] = r5
            java.lang.Object r7 = r8.getResult()
            java.util.Map r7 = (java.util.Map) r7
            r6.putAll(r7)
            r7 = 203(0xcb, float:2.84E-43)
            r2[r7] = r5
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r7 = r1.templateList
            com.taobao.weex.ui.component.list.template.CellDataManager r7 = r7.getCellDataManager()
            r7.updateVirtualComponentData(r12, r6)
            r7 = 204(0xcc, float:2.86E-43)
            r2[r7] = r5
            r7 = r6
            goto L_0x01db
        L_0x01fd:
            com.taobao.weex.dom.WXAttr r8 = r16.getAttrs()
            java.lang.String r9 = "@virtualComponentId"
            r8.put((java.lang.String) r9, (java.lang.Object) r12)
            r8 = 205(0xcd, float:2.87E-43)
            r2[r8] = r5
            r10 = r12
            goto L_0x0220
        L_0x020c:
            java.lang.String r6 = "@componentProps"
            java.lang.Object r6 = r3.get(r6)
            com.alibaba.fastjson.JSONObject r6 = (com.alibaba.fastjson.JSONObject) r6
            com.taobao.weex.el.parse.ArrayStack r7 = r1.stack
            java.util.Map r7 = renderProps(r6, r7)
            r6 = 206(0xce, float:2.89E-43)
            r2[r6] = r5
            r6 = 0
            r10 = 0
        L_0x0220:
            com.taobao.weex.el.parse.ArrayStack r8 = new com.taobao.weex.el.parse.ArrayStack
            r8.<init>()
            r1.stack = r8
            if (r7 != 0) goto L_0x022e
            r7 = 207(0xcf, float:2.9E-43)
            r2[r7] = r5
            goto L_0x023b
        L_0x022e:
            r8 = 208(0xd0, float:2.91E-43)
            r2[r8] = r5
            com.taobao.weex.el.parse.ArrayStack r8 = r1.stack
            r8.push(r7)
            r7 = 209(0xd1, float:2.93E-43)
            r2[r7] = r5
        L_0x023b:
            r7 = 210(0xd2, float:2.94E-43)
            r2[r7] = r5
            com.taobao.weex.dom.binding.WXStatement r7 = r3.getStatement()
            if (r7 != 0) goto L_0x024b
            r3 = 211(0xd3, float:2.96E-43)
            r2[r3] = r5
            r3 = 0
            goto L_0x025d
        L_0x024b:
            r7 = 212(0xd4, float:2.97E-43)
            r2[r7] = r5
            com.taobao.weex.dom.binding.WXStatement r3 = r3.getStatement()
            java.lang.String r7 = "[[once]]"
            java.lang.Object r3 = r3.get(r7)
            r7 = 213(0xd5, float:2.98E-43)
            r2[r7] = r5
        L_0x025d:
            if (r3 != 0) goto L_0x0264
            r3 = 214(0xd6, float:3.0E-43)
            r2[r3] = r5
            goto L_0x02a8
        L_0x0264:
            r3 = 215(0xd7, float:3.01E-43)
            r2[r3] = r5
            com.taobao.weex.ui.component.list.template.CellRenderState r3 = r17.getRenderState()
            java.util.Map r3 = r3.getOnceComponentStates()
            java.lang.String r7 = r16.getViewTreeKey()
            java.lang.Object r3 = r3.get(r7)
            com.taobao.weex.el.parse.ArrayStack r3 = (com.taobao.weex.el.parse.ArrayStack) r3
            if (r3 == 0) goto L_0x0281
            r7 = 216(0xd8, float:3.03E-43)
            r2[r7] = r5
            goto L_0x02a2
        L_0x0281:
            r3 = 217(0xd9, float:3.04E-43)
            r2[r3] = r5
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r3 = r1.templateList
            com.taobao.weex.el.parse.ArrayStack r3 = r3.copyStack(r1, r4)
            r7 = 218(0xda, float:3.05E-43)
            r2[r7] = r5
            com.taobao.weex.ui.component.list.template.CellRenderState r7 = r17.getRenderState()
            java.util.Map r7 = r7.getOnceComponentStates()
            java.lang.String r8 = r16.getViewTreeKey()
            r7.put(r8, r3)
            r7 = 219(0xdb, float:3.07E-43)
            r2[r7] = r5
        L_0x02a2:
            r1.stack = r3
            r3 = 220(0xdc, float:3.08E-43)
            r2[r3] = r5
        L_0x02a8:
            doRenderBindingAttrsAndEvent(r16, r17)
            boolean r3 = r0 instanceof com.taobao.weex.ui.component.WXVContainer
            if (r3 != 0) goto L_0x02b4
            r3 = 221(0xdd, float:3.1E-43)
            r2[r3] = r5
            goto L_0x02e1
        L_0x02b4:
            r3 = 222(0xde, float:3.11E-43)
            r2[r3] = r5
            boolean r3 = r16.isWaste()
            if (r3 != 0) goto L_0x02c3
            r3 = 223(0xdf, float:3.12E-43)
            r2[r3] = r5
            goto L_0x02cb
        L_0x02c3:
            boolean r3 = r0 instanceof com.taobao.weex.ui.component.list.WXCell
            if (r3 == 0) goto L_0x035f
            r3 = 224(0xe0, float:3.14E-43)
            r2[r3] = r5
        L_0x02cb:
            r3 = r0
            com.taobao.weex.ui.component.WXVContainer r3 = (com.taobao.weex.ui.component.WXVContainer) r3
            r7 = 226(0xe2, float:3.17E-43)
            r2[r7] = r5
            r7 = 227(0xe3, float:3.18E-43)
            r2[r7] = r5
            r7 = 0
        L_0x02d7:
            int r8 = r3.getChildCount()
            if (r7 < r8) goto L_0x0342
            r3 = 228(0xe4, float:3.2E-43)
            r2[r3] = r5
        L_0x02e1:
            com.taobao.weex.el.parse.ArrayStack r3 = r1.stack
            if (r4 != r3) goto L_0x02ea
            r3 = 232(0xe8, float:3.25E-43)
            r2[r3] = r5
            goto L_0x02f0
        L_0x02ea:
            r1.stack = r4
            r3 = 233(0xe9, float:3.27E-43)
            r2[r3] = r5
        L_0x02f0:
            if (r6 != 0) goto L_0x02f7
            r0 = 234(0xea, float:3.28E-43)
            r2[r0] = r5
            goto L_0x033d
        L_0x02f7:
            if (r10 != 0) goto L_0x02fe
            r0 = 235(0xeb, float:3.3E-43)
            r2[r0] = r5
            goto L_0x033d
        L_0x02fe:
            r3 = 236(0xec, float:3.31E-43)
            r2[r3] = r5
            com.taobao.weex.bridge.WXBridgeManager r3 = com.taobao.weex.bridge.WXBridgeManager.getInstance()
            java.lang.String r4 = "componentHook"
            java.lang.String r6 = r16.getInstanceId()
            r8 = 4
            java.lang.Object[] r7 = new java.lang.Object[r8]
            r7[r11] = r10
            java.lang.String r8 = "lifecycle"
            r7[r5] = r8
            java.lang.String r8 = "attach"
            r9 = 2
            r7[r9] = r8
            java.lang.Object[] r8 = new java.lang.Object[r5]
            com.taobao.weex.ui.component.list.template.WXRecyclerTemplateList r9 = r1.templateList
            r10 = 237(0xed, float:3.32E-43)
            r2[r10] = r5
            java.lang.String r9 = r9.getRef()
            int r1 = r1.position
            java.util.Map r0 = com.taobao.weex.ui.component.list.template.TemplateDom.findAllComponentRefs(r9, r1, r0)
            r8[r11] = r0
            r12 = 3
            r7[r12] = r8
            r0 = 238(0xee, float:3.34E-43)
            r2[r0] = r5
            r13 = 0
            r3.asyncCallJSEventVoidResult(r4, r6, r13, r7)
            r0 = 239(0xef, float:3.35E-43)
            r2[r0] = r5
        L_0x033d:
            r0 = 240(0xf0, float:3.36E-43)
            r2[r0] = r5
            return
        L_0x0342:
            r8 = 4
            r9 = 2
            r12 = 3
            r13 = 0
            r14 = 229(0xe5, float:3.21E-43)
            r2[r14] = r5
            com.taobao.weex.ui.component.WXComponent r14 = r3.getChild(r7)
            r15 = 230(0xe6, float:3.22E-43)
            r2[r15] = r5
            r15 = r18
            int r14 = doRenderComponent(r14, r1, r15)
            int r7 = r7 + r14
            r14 = 231(0xe7, float:3.24E-43)
            r2[r14] = r5
            goto L_0x02d7
        L_0x035f:
            r0 = 225(0xe1, float:3.15E-43)
            r2[r0] = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.binding.Statements.doBindingAttrsEventAndRenderChildNode(com.taobao.weex.ui.component.WXComponent, com.taobao.weex.ui.component.list.template.CellRenderContext, java.util.List):void");
    }

    private static boolean isCreateFromNodeStatement(WXComponent wXComponent, WXComponent wXComponent2) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent.getRef() == null) {
            $jacocoInit[241] = true;
        } else if (!wXComponent.getRef().equals(wXComponent2.getRef())) {
            $jacocoInit[242] = true;
        } else {
            $jacocoInit[243] = true;
            z = true;
            $jacocoInit[245] = true;
            return z;
        }
        z = false;
        $jacocoInit[244] = true;
        $jacocoInit[245] = true;
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0122  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doRenderBindingAttrsAndEvent(com.taobao.weex.ui.component.WXComponent r8, com.taobao.weex.ui.component.list.template.CellRenderContext r9) {
        /*
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.el.parse.ArrayStack r9 = r9.stack
            r1 = 1
            r2 = 246(0xf6, float:3.45E-43)
            r0[r2] = r1
            r2 = 0
            r8.setWaste(r2)
            r2 = 247(0xf7, float:3.46E-43)
            r0[r2] = r1
            com.taobao.weex.dom.WXAttr r2 = r8.getAttrs()
            if (r2 != 0) goto L_0x001f
            r2 = 248(0xf8, float:3.48E-43)
            r0[r2] = r1
            goto L_0x0134
        L_0x001f:
            r3 = 249(0xf9, float:3.49E-43)
            r0[r3] = r1
            android.support.v4.util.ArrayMap r3 = r2.getBindingAttrs()
            if (r3 != 0) goto L_0x002f
            r2 = 250(0xfa, float:3.5E-43)
            r0[r2] = r1
            goto L_0x0134
        L_0x002f:
            r3 = 251(0xfb, float:3.52E-43)
            r0[r3] = r1
            android.support.v4.util.ArrayMap r3 = r2.getBindingAttrs()
            int r3 = r3.size()
            if (r3 > 0) goto L_0x0043
            r2 = 252(0xfc, float:3.53E-43)
            r0[r2] = r1
            goto L_0x0134
        L_0x0043:
            r3 = 253(0xfd, float:3.55E-43)
            r0[r3] = r1
            com.taobao.weex.dom.WXAttr r3 = r8.getAttrs()
            android.support.v4.util.ArrayMap r3 = r3.getBindingAttrs()
            r4 = 254(0xfe, float:3.56E-43)
            r0[r4] = r1
            java.util.Map r3 = renderBindingAttrs(r3, r9)
            r4 = 255(0xff, float:3.57E-43)
            r0[r4] = r1
            java.util.Set r4 = r3.entrySet()
            r5 = 256(0x100, float:3.59E-43)
            r0[r5] = r1
            java.util.Iterator r4 = r4.iterator()
            r5 = 257(0x101, float:3.6E-43)
            r0[r5] = r1
        L_0x006b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00c5
            r5 = 258(0x102, float:3.62E-43)
            r0[r5] = r1
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            r6 = 259(0x103, float:3.63E-43)
            r0[r6] = r1
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            r7 = 260(0x104, float:3.64E-43)
            r0[r7] = r1
            java.lang.Object r5 = r5.getValue()
            r7 = 261(0x105, float:3.66E-43)
            r0[r7] = r1
            java.lang.Object r6 = r2.get(r6)
            if (r5 != 0) goto L_0x00aa
            if (r6 == 0) goto L_0x009e
            r5 = 262(0x106, float:3.67E-43)
            r0[r5] = r1
            goto L_0x00c0
        L_0x009e:
            r5 = 263(0x107, float:3.69E-43)
            r0[r5] = r1
            r4.remove()
            r5 = 264(0x108, float:3.7E-43)
            r0[r5] = r1
            goto L_0x006b
        L_0x00aa:
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00b5
            r5 = 265(0x109, float:3.71E-43)
            r0[r5] = r1
            goto L_0x00c0
        L_0x00b5:
            r5 = 266(0x10a, float:3.73E-43)
            r0[r5] = r1
            r4.remove()
            r5 = 267(0x10b, float:3.74E-43)
            r0[r5] = r1
        L_0x00c0:
            r5 = 268(0x10c, float:3.76E-43)
            r0[r5] = r1
            goto L_0x006b
        L_0x00c5:
            int r2 = r3.size()
            if (r2 > 0) goto L_0x00d0
            r2 = 269(0x10d, float:3.77E-43)
            r0[r2] = r1
            goto L_0x0134
        L_0x00d0:
            r2 = 270(0x10e, float:3.78E-43)
            r0[r2] = r1
            int r2 = r3.size()
            if (r2 == r1) goto L_0x00df
            r2 = 271(0x10f, float:3.8E-43)
            r0[r2] = r1
            goto L_0x00f8
        L_0x00df:
            java.lang.String r2 = "src"
            r4 = 272(0x110, float:3.81E-43)
            r0[r4] = r1
            java.lang.Object r2 = r3.get(r2)
            if (r2 != 0) goto L_0x00f0
            r2 = 273(0x111, float:3.83E-43)
            r0[r2] = r1
            goto L_0x00f8
        L_0x00f0:
            boolean r2 = r8 instanceof com.taobao.weex.ui.component.WXImage
            if (r2 != 0) goto L_0x0100
            r2 = 274(0x112, float:3.84E-43)
            r0[r2] = r1
        L_0x00f8:
            r8.nativeUpdateAttrs(r3)
            r2 = 277(0x115, float:3.88E-43)
            r0[r2] = r1
            goto L_0x0117
        L_0x0100:
            r2 = 275(0x113, float:3.85E-43)
            r0[r2] = r1
            com.taobao.weex.dom.WXAttr r2 = r8.getAttrs()
            java.lang.String r4 = "src"
            java.lang.String r5 = "src"
            java.lang.Object r5 = r3.get(r5)
            r2.put((java.lang.String) r4, (java.lang.Object) r5)
            r2 = 276(0x114, float:3.87E-43)
            r0[r2] = r1
        L_0x0117:
            boolean r2 = isMainThread()
            if (r2 != 0) goto L_0x0122
            r2 = 278(0x116, float:3.9E-43)
            r0[r2] = r1
            goto L_0x012d
        L_0x0122:
            r2 = 279(0x117, float:3.91E-43)
            r0[r2] = r1
            r8.updateProperties(r3)
            r2 = 280(0x118, float:3.92E-43)
            r0[r2] = r1
        L_0x012d:
            r3.clear()
            r2 = 281(0x119, float:3.94E-43)
            r0[r2] = r1
        L_0x0134:
            com.taobao.weex.dom.WXStyle r2 = r8.getStyles()
            r3 = 282(0x11a, float:3.95E-43)
            r0[r3] = r1
            if (r2 != 0) goto L_0x0144
            r2 = 283(0x11b, float:3.97E-43)
            r0[r2] = r1
            goto L_0x01fa
        L_0x0144:
            android.support.v4.util.ArrayMap r3 = r2.getBindingStyle()
            if (r3 != 0) goto L_0x0150
            r2 = 284(0x11c, float:3.98E-43)
            r0[r2] = r1
            goto L_0x01fa
        L_0x0150:
            r3 = 285(0x11d, float:4.0E-43)
            r0[r3] = r1
            android.support.v4.util.ArrayMap r3 = r2.getBindingStyle()
            r4 = 286(0x11e, float:4.01E-43)
            r0[r4] = r1
            java.util.Map r3 = renderBindingAttrs(r3, r9)
            r4 = 287(0x11f, float:4.02E-43)
            r0[r4] = r1
            java.util.Set r4 = r3.entrySet()
            r5 = 288(0x120, float:4.04E-43)
            r0[r5] = r1
            java.util.Iterator r4 = r4.iterator()
            r5 = 289(0x121, float:4.05E-43)
            r0[r5] = r1
        L_0x0174:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01ce
            r5 = 290(0x122, float:4.06E-43)
            r0[r5] = r1
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            r6 = 291(0x123, float:4.08E-43)
            r0[r6] = r1
            java.lang.Object r6 = r5.getKey()
            java.lang.String r6 = (java.lang.String) r6
            r7 = 292(0x124, float:4.09E-43)
            r0[r7] = r1
            java.lang.Object r5 = r5.getValue()
            r7 = 293(0x125, float:4.1E-43)
            r0[r7] = r1
            java.lang.Object r6 = r2.get(r6)
            if (r5 != 0) goto L_0x01b3
            if (r6 == 0) goto L_0x01a7
            r5 = 294(0x126, float:4.12E-43)
            r0[r5] = r1
            goto L_0x01c9
        L_0x01a7:
            r5 = 295(0x127, float:4.13E-43)
            r0[r5] = r1
            r4.remove()
            r5 = 296(0x128, float:4.15E-43)
            r0[r5] = r1
            goto L_0x0174
        L_0x01b3:
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x01be
            r5 = 297(0x129, float:4.16E-43)
            r0[r5] = r1
            goto L_0x01c9
        L_0x01be:
            r5 = 298(0x12a, float:4.18E-43)
            r0[r5] = r1
            r4.remove()
            r5 = 299(0x12b, float:4.19E-43)
            r0[r5] = r1
        L_0x01c9:
            r5 = 300(0x12c, float:4.2E-43)
            r0[r5] = r1
            goto L_0x0174
        L_0x01ce:
            int r2 = r3.size()
            if (r2 > 0) goto L_0x01d9
            r2 = 301(0x12d, float:4.22E-43)
            r0[r2] = r1
            goto L_0x01fa
        L_0x01d9:
            r2 = 302(0x12e, float:4.23E-43)
            r0[r2] = r1
            r8.updateNativeStyles(r3)
            r2 = 303(0x12f, float:4.25E-43)
            r0[r2] = r1
            boolean r2 = isMainThread()
            if (r2 != 0) goto L_0x01ef
            r2 = 304(0x130, float:4.26E-43)
            r0[r2] = r1
            goto L_0x01fa
        L_0x01ef:
            r2 = 305(0x131, float:4.27E-43)
            r0[r2] = r1
            r8.updateProperties(r3)
            r2 = 306(0x132, float:4.29E-43)
            r0[r2] = r1
        L_0x01fa:
            com.taobao.weex.dom.WXEvent r8 = r8.getEvents()
            r2 = 307(0x133, float:4.3E-43)
            r0[r2] = r1
            if (r8 != 0) goto L_0x0209
            r8 = 308(0x134, float:4.32E-43)
            r0[r8] = r1
            goto L_0x0213
        L_0x0209:
            android.support.v4.util.ArrayMap r2 = r8.getEventBindingArgs()
            if (r2 != 0) goto L_0x0218
            r8 = 309(0x135, float:4.33E-43)
            r0[r8] = r1
        L_0x0213:
            r8 = 310(0x136, float:4.34E-43)
            r0[r8] = r1
            return
        L_0x0218:
            android.support.v4.util.ArrayMap r2 = r8.getEventBindingArgs()
            java.util.Set r2 = r2.entrySet()
            r3 = 311(0x137, float:4.36E-43)
            r0[r3] = r1
            java.util.Iterator r2 = r2.iterator()
            r3 = 312(0x138, float:4.37E-43)
            r0[r3] = r1
        L_0x022c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0261
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            r4 = 313(0x139, float:4.39E-43)
            r0[r4] = r1
            java.lang.Object r4 = r3.getValue()
            java.util.List r4 = getBindingEventArgs(r9, r4)
            if (r4 != 0) goto L_0x024b
            r3 = 314(0x13a, float:4.4E-43)
            r0[r3] = r1
            goto L_0x025c
        L_0x024b:
            r5 = 315(0x13b, float:4.41E-43)
            r0[r5] = r1
            java.lang.Object r3 = r3.getKey()
            java.lang.String r3 = (java.lang.String) r3
            r8.putEventBindingArgsValue(r3, r4)
            r3 = 316(0x13c, float:4.43E-43)
            r0[r3] = r1
        L_0x025c:
            r3 = 317(0x13d, float:4.44E-43)
            r0[r3] = r1
            goto L_0x022c
        L_0x0261:
            r8 = 318(0x13e, float:4.46E-43)
            r0[r8] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.binding.Statements.doRenderBindingAttrsAndEvent(com.taobao.weex.ui.component.WXComponent, com.taobao.weex.ui.component.list.template.CellRenderContext):void");
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[417] = true;
    }

    public static Map<String, Object> renderBindingAttrs(ArrayMap arrayMap, ArrayStack arrayStack) {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry> entrySet = arrayMap.entrySet();
        $jacocoInit[319] = true;
        Map<String, Object> map = dynamicLocal.get();
        if (map != null) {
            $jacocoInit[320] = true;
        } else {
            $jacocoInit[321] = true;
            map = new HashMap<>();
            $jacocoInit[322] = true;
            dynamicLocal.set(map);
            $jacocoInit[323] = true;
        }
        if (map.size() <= 0) {
            $jacocoInit[324] = true;
        } else {
            $jacocoInit[325] = true;
            map.clear();
            $jacocoInit[326] = true;
        }
        $jacocoInit[327] = true;
        for (Map.Entry entry : entrySet) {
            $jacocoInit[328] = true;
            Object value = entry.getValue();
            $jacocoInit[329] = true;
            String str = (String) entry.getKey();
            if (!(value instanceof JSONObject)) {
                $jacocoInit[330] = true;
            } else {
                JSONObject jSONObject = (JSONObject) value;
                $jacocoInit[331] = true;
                if (!(jSONObject.get(ELUtils.BINDING) instanceof Token)) {
                    $jacocoInit[332] = true;
                } else {
                    $jacocoInit[333] = true;
                    $jacocoInit[334] = true;
                    Object execute = ((Token) jSONObject.get(ELUtils.BINDING)).execute(arrayStack);
                    $jacocoInit[335] = true;
                    map.put(str, execute);
                    $jacocoInit[336] = true;
                    $jacocoInit[360] = true;
                }
            }
            if (!(value instanceof JSONArray)) {
                $jacocoInit[337] = true;
            } else {
                JSONArray jSONArray = (JSONArray) value;
                $jacocoInit[338] = true;
                StringBuilder sb = new StringBuilder();
                $jacocoInit[339] = true;
                int i = 0;
                $jacocoInit[340] = true;
                while (i < jSONArray.size()) {
                    $jacocoInit[341] = true;
                    Object obj = jSONArray.get(i);
                    if (obj instanceof CharSequence) {
                        $jacocoInit[342] = true;
                        sb.append(obj);
                        $jacocoInit[343] = true;
                    } else if (!(obj instanceof JSONObject)) {
                        $jacocoInit[344] = true;
                    } else {
                        JSONObject jSONObject2 = (JSONObject) obj;
                        $jacocoInit[345] = true;
                        if (!(jSONObject2.get(ELUtils.BINDING) instanceof Token)) {
                            $jacocoInit[346] = true;
                        } else {
                            $jacocoInit[347] = true;
                            $jacocoInit[348] = true;
                            Object execute2 = ((Token) jSONObject2.get(ELUtils.BINDING)).execute(arrayStack);
                            if (execute2 != null) {
                                $jacocoInit[349] = true;
                            } else {
                                execute2 = "";
                                $jacocoInit[350] = true;
                            }
                            sb.append(execute2);
                            $jacocoInit[351] = true;
                        }
                    }
                    i++;
                    $jacocoInit[352] = true;
                }
                String sb2 = sb.toString();
                $jacocoInit[353] = true;
                if (sb2.length() <= 256) {
                    $jacocoInit[354] = true;
                } else {
                    $jacocoInit[355] = true;
                    if (!WXEnvironment.isApkDebugable()) {
                        $jacocoInit[356] = true;
                    } else {
                        $jacocoInit[357] = true;
                        WXLogUtils.w(WXRecyclerTemplateList.TAG, " warn too big string " + sb2);
                        $jacocoInit[358] = true;
                    }
                }
                map.put(str, sb2);
                $jacocoInit[359] = true;
            }
            $jacocoInit[360] = true;
        }
        $jacocoInit[361] = true;
        return map;
    }

    public static Map<String, Object> renderProps(JSONObject jSONObject, ArrayStack arrayStack) {
        boolean[] $jacocoInit = $jacocoInit();
        Set<Map.Entry<String, Object>> entrySet = jSONObject.entrySet();
        $jacocoInit[362] = true;
        ArrayMap arrayMap = new ArrayMap(4);
        $jacocoInit[363] = true;
        $jacocoInit[364] = true;
        for (Map.Entry next : entrySet) {
            $jacocoInit[365] = true;
            Object value = next.getValue();
            $jacocoInit[366] = true;
            String str = (String) next.getKey();
            if (!(value instanceof JSONObject)) {
                $jacocoInit[367] = true;
            } else {
                JSONObject jSONObject2 = (JSONObject) value;
                $jacocoInit[368] = true;
                if (!(jSONObject2.get(ELUtils.BINDING) instanceof Token)) {
                    $jacocoInit[369] = true;
                } else {
                    $jacocoInit[370] = true;
                    $jacocoInit[371] = true;
                    Object execute = ((Token) jSONObject2.get(ELUtils.BINDING)).execute(arrayStack);
                    $jacocoInit[372] = true;
                    arrayMap.put(str, execute);
                    $jacocoInit[373] = true;
                    $jacocoInit[375] = true;
                }
            }
            arrayMap.put(str, value);
            $jacocoInit[374] = true;
            $jacocoInit[375] = true;
        }
        $jacocoInit[376] = true;
        return arrayMap;
    }

    public static List<Object> getBindingEventArgs(ArrayStack arrayStack, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(4);
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            $jacocoInit[377] = true;
            int i = 0;
            $jacocoInit[378] = true;
            while (i < jSONArray.size()) {
                $jacocoInit[379] = true;
                Object obj2 = jSONArray.get(i);
                if (!(obj2 instanceof JSONObject)) {
                    $jacocoInit[380] = true;
                } else {
                    JSONObject jSONObject = (JSONObject) obj2;
                    $jacocoInit[381] = true;
                    if (!(jSONObject.get(ELUtils.BINDING) instanceof Token)) {
                        $jacocoInit[382] = true;
                    } else {
                        $jacocoInit[383] = true;
                        $jacocoInit[384] = true;
                        Object execute = ((Token) jSONObject.get(ELUtils.BINDING)).execute(arrayStack);
                        $jacocoInit[385] = true;
                        arrayList.add(execute);
                        $jacocoInit[386] = true;
                        i++;
                        $jacocoInit[388] = true;
                    }
                }
                arrayList.add(obj2);
                $jacocoInit[387] = true;
                i++;
                $jacocoInit[388] = true;
            }
            $jacocoInit[389] = true;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = (JSONObject) obj;
            $jacocoInit[390] = true;
            if (jSONObject2.get(ELUtils.BINDING) instanceof Token) {
                $jacocoInit[391] = true;
                $jacocoInit[392] = true;
                Object execute2 = ((Token) jSONObject2.get(ELUtils.BINDING)).execute(arrayStack);
                $jacocoInit[393] = true;
                arrayList.add(execute2);
                $jacocoInit[394] = true;
            } else {
                arrayList.add(obj.toString());
                $jacocoInit[395] = true;
            }
            $jacocoInit[396] = true;
        } else {
            arrayList.add(obj.toString());
            $jacocoInit[397] = true;
        }
        $jacocoInit[398] = true;
        return arrayList;
    }

    private static boolean isMainThread() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            $jacocoInit[399] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[400] = true;
        }
        $jacocoInit[401] = true;
        return z;
    }

    public static String getComponentId(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (wXComponent instanceof WXCell) {
            $jacocoInit[402] = true;
        } else if (wXComponent == null) {
            $jacocoInit[403] = true;
        } else {
            WXAttr attrs = wXComponent.getAttrs();
            $jacocoInit[405] = true;
            if (attrs.get(ELUtils.IS_COMPONENT_ROOT) == null) {
                $jacocoInit[406] = true;
            } else {
                $jacocoInit[407] = true;
                if (!WXUtils.getBoolean(attrs.get(ELUtils.IS_COMPONENT_ROOT), false).booleanValue()) {
                    $jacocoInit[408] = true;
                } else {
                    $jacocoInit[409] = true;
                    if (attrs.get(ELUtils.COMPONENT_PROPS) == null) {
                        $jacocoInit[410] = true;
                    } else {
                        $jacocoInit[411] = true;
                        if (!(attrs.get(ELUtils.COMPONENT_PROPS) instanceof JSONObject)) {
                            $jacocoInit[412] = true;
                        } else {
                            $jacocoInit[413] = true;
                            Object obj = attrs.get(CellDataManager.VIRTUAL_COMPONENT_ID);
                            if (obj == null) {
                                $jacocoInit[414] = true;
                                return null;
                            }
                            String obj2 = obj.toString();
                            $jacocoInit[415] = true;
                            return obj2;
                        }
                    }
                }
            }
            String componentId = getComponentId(wXComponent.getParent());
            $jacocoInit[416] = true;
            return componentId;
        }
        $jacocoInit[404] = true;
        return null;
    }
}
