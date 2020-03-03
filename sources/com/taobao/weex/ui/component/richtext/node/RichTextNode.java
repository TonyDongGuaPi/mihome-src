package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.dom.WXCustomStyleSpan;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.utils.WXResourceUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public abstract class RichTextNode {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ATTR = "attr";
    public static final String CHILDREN = "children";
    public static final String ITEM_CLICK = "itemclick";
    private static final int MAX_LEVEL = 255;
    public static final String PSEUDO_REF = "pseudoRef";
    public static final String STYLE = "style";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    protected Map<String, Object> attr;
    protected List<RichTextNode> children;
    protected final String mComponentRef;
    protected final Context mContext;
    protected final String mInstanceId;
    protected Map<String, Object> style;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8342512821049467012L, "com/taobao/weex/ui/component/richtext/node/RichTextNode", 94);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isInternalNode();

    public abstract String toString();

    protected RichTextNode(Context context, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContext = context;
        this.mInstanceId = str;
        this.mComponentRef = str2;
        $jacocoInit[0] = true;
    }

    @NonNull
    public static Spannable parse(@NonNull Context context, @NonNull String str, @NonNull String str2, String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        JSONArray parseArray = JSON.parseArray(str3);
        $jacocoInit[1] = true;
        if (parseArray == null) {
            $jacocoInit[2] = true;
        } else if (parseArray.isEmpty()) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            ArrayList arrayList = new ArrayList(parseArray.size());
            $jacocoInit[5] = true;
            int i = 0;
            $jacocoInit[6] = true;
            while (i < parseArray.size()) {
                $jacocoInit[7] = true;
                JSONObject jSONObject = parseArray.getJSONObject(i);
                if (jSONObject == null) {
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[9] = true;
                    RichTextNode createRichTextNode = RichTextNodeManager.createRichTextNode(context, str, str2, jSONObject);
                    if (createRichTextNode == null) {
                        $jacocoInit[10] = true;
                    } else {
                        $jacocoInit[11] = true;
                        arrayList.add(createRichTextNode);
                        $jacocoInit[12] = true;
                    }
                }
                i++;
                $jacocoInit[13] = true;
            }
            Spannable parse = parse(arrayList);
            $jacocoInit[14] = true;
            return parse;
        }
        SpannableString spannableString = new SpannableString("");
        $jacocoInit[15] = true;
        return spannableString;
    }

    public static int createSpanFlag(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int createPriorityFlag = createPriorityFlag(i) | 17;
        $jacocoInit[16] = true;
        return createPriorityFlag;
    }

    /* access modifiers changed from: package-private */
    public final void parse(@NonNull Context context, @NonNull String str, @NonNull String str2, JSONObject jSONObject) {
        boolean[] $jacocoInit = $jacocoInit();
        JSONObject jSONObject2 = jSONObject.getJSONObject("style");
        int i = 0;
        if (jSONObject2 != null) {
            $jacocoInit[17] = true;
            this.style = new ArrayMap();
            $jacocoInit[18] = true;
            this.style.putAll(jSONObject2);
            $jacocoInit[19] = true;
        } else {
            this.style = new ArrayMap(0);
            $jacocoInit[20] = true;
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject(ATTR);
        if (jSONObject3 != null) {
            $jacocoInit[21] = true;
            this.attr = new ArrayMap(jSONObject3.size());
            $jacocoInit[22] = true;
            this.attr.putAll(jSONObject3);
            $jacocoInit[23] = true;
        } else {
            this.attr = new ArrayMap(0);
            $jacocoInit[24] = true;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("children");
        if (jSONArray != null) {
            $jacocoInit[25] = true;
            this.children = new ArrayList(jSONArray.size());
            $jacocoInit[26] = true;
            $jacocoInit[27] = true;
            while (i < jSONArray.size()) {
                $jacocoInit[28] = true;
                JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                $jacocoInit[29] = true;
                RichTextNode createRichTextNode = RichTextNodeManager.createRichTextNode(context, str, str2, jSONObject4);
                if (createRichTextNode == null) {
                    $jacocoInit[30] = true;
                } else {
                    $jacocoInit[31] = true;
                    this.children.add(createRichTextNode);
                    $jacocoInit[32] = true;
                }
                i++;
                $jacocoInit[33] = true;
            }
            $jacocoInit[34] = true;
        } else {
            this.children = new ArrayList(0);
            $jacocoInit[35] = true;
        }
        $jacocoInit[36] = true;
    }

    /* access modifiers changed from: protected */
    public void updateSpans(SpannableStringBuilder spannableStringBuilder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(this.mInstanceId);
        if (this.style == null) {
            $jacocoInit[37] = true;
        } else if (sDKInstance == null) {
            $jacocoInit[38] = true;
        } else {
            $jacocoInit[39] = true;
            LinkedList linkedList = new LinkedList();
            $jacocoInit[40] = true;
            WXCustomStyleSpan createCustomStyleSpan = createCustomStyleSpan();
            if (createCustomStyleSpan == null) {
                $jacocoInit[41] = true;
            } else {
                $jacocoInit[42] = true;
                linkedList.add(createCustomStyleSpan);
                $jacocoInit[43] = true;
            }
            if (!this.style.containsKey("fontSize")) {
                $jacocoInit[44] = true;
            } else {
                $jacocoInit[45] = true;
                linkedList.add(new AbsoluteSizeSpan(WXStyle.getFontSize(this.style, sDKInstance.getInstanceViewPortWidth())));
                $jacocoInit[46] = true;
            }
            if (!this.style.containsKey("backgroundColor")) {
                $jacocoInit[47] = true;
            } else {
                $jacocoInit[48] = true;
                int color = WXResourceUtils.getColor(this.style.get("backgroundColor").toString(), 0);
                if (color == 0) {
                    $jacocoInit[49] = true;
                } else {
                    $jacocoInit[50] = true;
                    linkedList.add(new BackgroundColorSpan(color));
                    $jacocoInit[51] = true;
                }
            }
            if (!this.style.containsKey("color")) {
                $jacocoInit[52] = true;
            } else {
                $jacocoInit[53] = true;
                linkedList.add(new ForegroundColorSpan(WXResourceUtils.getColor(WXStyle.getTextColor(this.style))));
                $jacocoInit[54] = true;
            }
            int createSpanFlag = createSpanFlag(i);
            $jacocoInit[55] = true;
            $jacocoInit[56] = true;
            for (Object next : linkedList) {
                $jacocoInit[58] = true;
                spannableStringBuilder.setSpan(next, 0, spannableStringBuilder.length(), createSpanFlag);
                $jacocoInit[59] = true;
            }
            $jacocoInit[57] = true;
        }
        $jacocoInit[60] = true;
    }

    private static int createPriorityFlag(int i) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        if (i <= 255) {
            i2 = (255 - i) << 16;
            $jacocoInit[61] = true;
        } else {
            i2 = 16711680;
            $jacocoInit[62] = true;
        }
        $jacocoInit[63] = true;
        return i2;
    }

    @NonNull
    private static Spannable parse(@NonNull List<RichTextNode> list) {
        boolean[] $jacocoInit = $jacocoInit();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        $jacocoInit[64] = true;
        $jacocoInit[65] = true;
        for (RichTextNode span : list) {
            $jacocoInit[66] = true;
            spannableStringBuilder.append(span.toSpan(1));
            $jacocoInit[67] = true;
        }
        $jacocoInit[68] = true;
        return spannableStringBuilder;
    }

    private Spannable toSpan(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        $jacocoInit[69] = true;
        spannableStringBuilder.append(toString());
        $jacocoInit[70] = true;
        if (!isInternalNode()) {
            $jacocoInit[71] = true;
        } else if (this.children == null) {
            $jacocoInit[72] = true;
        } else {
            $jacocoInit[73] = true;
            $jacocoInit[74] = true;
            for (RichTextNode span : this.children) {
                $jacocoInit[76] = true;
                spannableStringBuilder.append(span.toSpan(i + 1));
                $jacocoInit[77] = true;
            }
            $jacocoInit[75] = true;
        }
        updateSpans(spannableStringBuilder, i);
        $jacocoInit[78] = true;
        return spannableStringBuilder;
    }

    @Nullable
    private WXCustomStyleSpan createCustomStyleSpan() {
        int i;
        int i2;
        String str;
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[79] = true;
        if (!this.style.containsKey("fontWeight")) {
            $jacocoInit[80] = true;
            i = -1;
        } else {
            $jacocoInit[81] = true;
            i = WXStyle.getFontWeight(this.style);
            $jacocoInit[82] = true;
        }
        if (!this.style.containsKey("fontStyle")) {
            $jacocoInit[83] = true;
            i2 = -1;
        } else {
            $jacocoInit[84] = true;
            i2 = WXStyle.getFontStyle(this.style);
            $jacocoInit[85] = true;
        }
        if (!this.style.containsKey("fontFamily")) {
            $jacocoInit[86] = true;
            str = null;
        } else {
            $jacocoInit[87] = true;
            str = WXStyle.getFontFamily(this.style);
            $jacocoInit[88] = true;
        }
        if (i != -1) {
            $jacocoInit[89] = true;
        } else if (i2 != -1) {
            $jacocoInit[90] = true;
        } else if (str != null) {
            $jacocoInit[91] = true;
        } else {
            $jacocoInit[93] = true;
            return null;
        }
        WXCustomStyleSpan wXCustomStyleSpan = new WXCustomStyleSpan(i2, i, str);
        $jacocoInit[92] = true;
        return wXCustomStyleSpan;
    }
}
