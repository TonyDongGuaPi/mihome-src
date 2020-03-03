package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.taobao.weex.ui.component.richtext.span.ASpan;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class ANode extends RichTextNode {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String HREF = "href";
    public static final String NODE_TYPE = "a";

    /* renamed from: com.taobao.weex.ui.component.richtext.node.ANode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(3532809320193589150L, "com/taobao/weex/ui/component/richtext/node/ANode$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(2076800259544515749L, "com/taobao/weex/ui/component/richtext/node/ANode", 13);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ ANode(Context context, String str, String str2, AnonymousClass1 r4) {
        this(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[12] = true;
    }

    static class ANodeCreator implements RichTextNodeCreator<ANode> {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-1228603774132691486L, "com/taobao/weex/ui/component/richtext/node/ANode$ANodeCreator", 3);
            $jacocoData = a2;
            return a2;
        }

        ANodeCreator() {
            $jacocoInit()[0] = true;
        }

        public ANode createRichTextNode(Context context, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            ANode aNode = new ANode(context, str, str2, (AnonymousClass1) null);
            $jacocoInit[1] = true;
            return aNode;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ANode(Context context, String str, String str2) {
        super(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public String toString() {
        $jacocoInit()[1] = true;
        return "";
    }

    /* access modifiers changed from: protected */
    public boolean isInternalNode() {
        $jacocoInit()[2] = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateSpans(SpannableStringBuilder spannableStringBuilder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateSpans(spannableStringBuilder, i);
        $jacocoInit[3] = true;
        if (this.attr == null) {
            $jacocoInit[4] = true;
        } else if (!this.attr.containsKey("href")) {
            $jacocoInit[5] = true;
        } else {
            $jacocoInit[6] = true;
            ASpan aSpan = new ASpan(this.mInstanceId, this.attr.get("href").toString());
            $jacocoInit[7] = true;
            int length = spannableStringBuilder.length();
            $jacocoInit[8] = true;
            int createSpanFlag = createSpanFlag(i);
            $jacocoInit[9] = true;
            spannableStringBuilder.setSpan(aSpan, 0, length, createSpanFlag);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }
}
