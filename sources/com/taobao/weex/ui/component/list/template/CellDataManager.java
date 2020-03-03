package com.taobao.weex.ui.component.list.template;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.utils.WXUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CellDataManager {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String SUB_COMPONENT_TEMPLATE_ID = "@templateId";
    public static final String VIRTUAL_COMPONENT_ID = "@virtualComponentId";
    private static final String VIRTUAL_COMPONENT_SEPRATOR = "@";
    JSONArray listData;
    private Map<Integer, CellRenderState> renderStates = new ArrayMap();
    public final WXRecyclerTemplateList templateList;
    private Map<String, CellRenderState> virtualComponentRenderStates;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1399072988866997218L, "com/taobao/weex/ui/component/list/template/CellDataManager", 88);
        $jacocoData = a2;
        return a2;
    }

    public CellDataManager(WXRecyclerTemplateList wXRecyclerTemplateList) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.templateList = wXRecyclerTemplateList;
        $jacocoInit[1] = true;
    }

    public CellRenderState getRenderState(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        CellRenderState cellRenderState = this.renderStates.get(Integer.valueOf(i));
        if (cellRenderState != null) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            cellRenderState = new CellRenderState();
            cellRenderState.position = i;
            $jacocoInit[4] = true;
            this.renderStates.put(Integer.valueOf(i), cellRenderState);
            $jacocoInit[5] = true;
        }
        if (i == cellRenderState.position) {
            $jacocoInit[6] = true;
        } else {
            cellRenderState.position = i;
            cellRenderState.hasPositionChange = true;
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return cellRenderState;
    }

    public void updateVirtualComponentData(String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.virtualComponentRenderStates != null) {
            $jacocoInit[9] = true;
            CellRenderState cellRenderState = this.virtualComponentRenderStates.get(str);
            if (cellRenderState != null) {
                $jacocoInit[10] = true;
                cellRenderState.getVirtualComponentDatas().put(str, obj);
                cellRenderState.hasVirtualCompoentUpdate = true;
                $jacocoInit[11] = true;
            } else if (!WXEnvironment.isApkDebugable()) {
                $jacocoInit[12] = true;
            } else {
                $jacocoInit[13] = true;
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("virtualComponentDatas illegal state empty render state" + str);
                $jacocoInit[14] = true;
                throw illegalArgumentException;
            }
            $jacocoInit[15] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[16] = true;
        } else {
            $jacocoInit[17] = true;
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("virtualComponentDatas illegal state " + str);
            $jacocoInit[18] = true;
            throw illegalArgumentException2;
        }
        $jacocoInit[19] = true;
    }

    public void createVirtualComponentData(int i, String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.virtualComponentRenderStates != null) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[21] = true;
            this.virtualComponentRenderStates = new HashMap(8);
            $jacocoInit[22] = true;
        }
        CellRenderState cellRenderState = this.renderStates.get(Integer.valueOf(i));
        $jacocoInit[23] = true;
        cellRenderState.getVirtualComponentDatas().put(str, obj);
        $jacocoInit[24] = true;
        this.virtualComponentRenderStates.put(str, cellRenderState);
        $jacocoInit[25] = true;
    }

    public void setListData(JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.listData == jSONArray) {
            $jacocoInit[26] = true;
        } else {
            if (this.listData == null) {
                $jacocoInit[27] = true;
            } else {
                $jacocoInit[28] = true;
                if (!WXUtils.getBoolean(this.templateList.getAttrs().get("exitDetach"), true).booleanValue()) {
                    $jacocoInit[29] = true;
                } else {
                    $jacocoInit[30] = true;
                    int i = 0;
                    $jacocoInit[31] = true;
                    while (i < this.listData.size()) {
                        $jacocoInit[33] = true;
                        cleanRenderState(this.renderStates.remove(Integer.valueOf(i)));
                        i++;
                        $jacocoInit[34] = true;
                    }
                    $jacocoInit[32] = true;
                }
            }
            this.listData = jSONArray;
            $jacocoInit[35] = true;
            this.renderStates.clear();
            if (this.virtualComponentRenderStates == null) {
                $jacocoInit[36] = true;
            } else {
                $jacocoInit[37] = true;
                this.virtualComponentRenderStates.clear();
                $jacocoInit[38] = true;
            }
        }
        $jacocoInit[39] = true;
    }

    public boolean insertData(int i, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        this.listData.add(i, obj);
        $jacocoInit[40] = true;
        int size = this.listData.size();
        $jacocoInit[41] = true;
        boolean z = false;
        while (size >= i) {
            $jacocoInit[42] = true;
            CellRenderState remove = this.renderStates.remove(Integer.valueOf(size));
            if (remove == null) {
                $jacocoInit[43] = true;
            } else {
                $jacocoInit[44] = true;
                this.renderStates.put(Integer.valueOf(size + 1), remove);
                $jacocoInit[45] = true;
                z = true;
            }
            size--;
            $jacocoInit[46] = true;
        }
        $jacocoInit[47] = true;
        return z;
    }

    public boolean insertRange(int i, JSONArray jSONArray) {
        boolean[] $jacocoInit = $jacocoInit();
        this.listData.addAll(i, jSONArray);
        $jacocoInit[48] = true;
        int size = this.listData.size() - 1;
        $jacocoInit[49] = true;
        boolean z = false;
        while (size >= i) {
            $jacocoInit[50] = true;
            CellRenderState remove = this.renderStates.remove(Integer.valueOf(size));
            if (remove == null) {
                $jacocoInit[51] = true;
            } else {
                $jacocoInit[52] = true;
                this.renderStates.put(Integer.valueOf(size + 1), remove);
                $jacocoInit[53] = true;
                z = true;
            }
            size--;
            $jacocoInit[54] = true;
        }
        $jacocoInit[55] = true;
        return z;
    }

    public boolean updateData(Object obj, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean equals = TextUtils.equals(this.templateList.getTemplateKey(i), this.templateList.getTemplateKey(obj));
        $jacocoInit[56] = true;
        this.listData.set(i, obj);
        if (!equals) {
            $jacocoInit[57] = true;
            cleanRenderState(this.renderStates.remove(Integer.valueOf(i)));
            $jacocoInit[58] = true;
        } else {
            CellRenderState cellRenderState = this.renderStates.get(Integer.valueOf(i));
            if (cellRenderState == null) {
                $jacocoInit[59] = true;
            } else {
                cellRenderState.hasDataUpdate = true;
                $jacocoInit[60] = true;
            }
        }
        $jacocoInit[61] = true;
        return equals;
    }

    public void removeData(Integer num) {
        boolean[] $jacocoInit = $jacocoInit();
        this.listData.remove(num.intValue());
        $jacocoInit[62] = true;
        cleanRenderState(this.renderStates.remove(num));
        $jacocoInit[63] = true;
        int size = this.listData.size() + 1;
        $jacocoInit[64] = true;
        int intValue = num.intValue() + 1;
        $jacocoInit[65] = true;
        while (intValue < size) {
            $jacocoInit[66] = true;
            CellRenderState remove = this.renderStates.remove(Integer.valueOf(intValue));
            if (remove == null) {
                $jacocoInit[67] = true;
            } else {
                $jacocoInit[68] = true;
                this.renderStates.put(Integer.valueOf(intValue - 1), remove);
                $jacocoInit[69] = true;
            }
            intValue++;
            $jacocoInit[70] = true;
        }
        $jacocoInit[71] = true;
    }

    private void cleanRenderState(CellRenderState cellRenderState) {
        boolean[] $jacocoInit = $jacocoInit();
        if (cellRenderState == null) {
            $jacocoInit[72] = true;
            return;
        }
        if (!cellRenderState.hasVirtualComponents()) {
            $jacocoInit[73] = true;
        } else {
            $jacocoInit[74] = true;
            Collection<String> values = cellRenderState.getVirtualComponentIds().values();
            $jacocoInit[75] = true;
            $jacocoInit[76] = true;
            for (String next : values) {
                if (this.virtualComponentRenderStates == null) {
                    $jacocoInit[78] = true;
                } else {
                    $jacocoInit[79] = true;
                    this.virtualComponentRenderStates.remove(next);
                    $jacocoInit[80] = true;
                }
                WXBridgeManager instance = WXBridgeManager.getInstance();
                WXRecyclerTemplateList wXRecyclerTemplateList = this.templateList;
                $jacocoInit[81] = true;
                String instanceId = wXRecyclerTemplateList.getInstanceId();
                Object[] objArr = {next, VirtualComponentLifecycle.LIFECYCLE, "detach", null};
                $jacocoInit[82] = true;
                instance.asyncCallJSEventVoidResult(WXBridgeManager.METHD_COMPONENT_HOOK_SYNC, instanceId, (List<Object>) null, objArr);
                $jacocoInit[83] = true;
            }
            $jacocoInit[77] = true;
        }
        $jacocoInit[84] = true;
    }

    public static String createVirtualComponentId(String str, String str2, long j) {
        String str3 = str + VIRTUAL_COMPONENT_SEPRATOR + str2 + VIRTUAL_COMPONENT_SEPRATOR + j;
        $jacocoInit()[85] = true;
        return str3;
    }

    public static String getListRef(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[86] = true;
            return null;
        }
        String str2 = str.split(VIRTUAL_COMPONENT_SEPRATOR)[0];
        $jacocoInit[87] = true;
        return str2;
    }
}
