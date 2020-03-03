package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.taobao.weex.dom.TextDecorationSpan;
import com.taobao.weex.dom.WXStyle;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class SpanNode extends RichTextNode {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String NODE_TYPE = "span";

    /* renamed from: com.taobao.weex.ui.component.richtext.node.SpanNode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8529617160324218039L, "com/taobao/weex/ui/component/richtext/node/SpanNode$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4574627998338041218L, "com/taobao/weex/ui/component/richtext/node/SpanNode", 11);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ SpanNode(Context context, String str, String str2, AnonymousClass1 r4) {
        this(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[10] = true;
    }

    static class SpanNodeCreator implements RichTextNodeCreator<SpanNode> {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4789510800829730509L, "com/taobao/weex/ui/component/richtext/node/SpanNode$SpanNodeCreator", 3);
            $jacocoData = a2;
            return a2;
        }

        SpanNodeCreator() {
            $jacocoInit()[0] = true;
        }

        public SpanNode createRichTextNode(Context context, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            SpanNode spanNode = new SpanNode(context, str, str2, (AnonymousClass1) null);
            $jacocoInit[1] = true;
            return spanNode;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private SpanNode(Context context, String str, String str2) {
        super(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public String toString() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.attr == null) {
            $jacocoInit[1] = true;
        } else if (!this.attr.containsKey("value")) {
            $jacocoInit[2] = true;
        } else {
            String obj = this.attr.get("value").toString();
            $jacocoInit[4] = true;
            return obj;
        }
        $jacocoInit[3] = true;
        return "";
    }

    /* access modifiers changed from: protected */
    public boolean isInternalNode() {
        $jacocoInit()[5] = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateSpans(SpannableStringBuilder spannableStringBuilder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateSpans(spannableStringBuilder, i);
        $jacocoInit[6] = true;
        TextDecorationSpan textDecorationSpan = new TextDecorationSpan(WXStyle.getTextDecoration(this.style));
        $jacocoInit[7] = true;
        int length = spannableStringBuilder.length();
        int createSpanFlag = createSpanFlag(i);
        $jacocoInit[8] = true;
        spannableStringBuilder.setSpan(textDecorationSpan, 0, length, createSpanFlag);
        $jacocoInit[9] = true;
    }
}
