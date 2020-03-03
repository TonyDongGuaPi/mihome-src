package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.DrawableStrategy;
import com.taobao.weex.ui.component.richtext.span.ImgSpan;
import com.taobao.weex.ui.component.richtext.span.ItemClickSpan;
import com.taobao.weex.utils.ImgURIUtil;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.LinkedList;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class ImgNode extends RichTextNode {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String NODE_TYPE = "image";

    /* renamed from: com.taobao.weex.ui.component.richtext.node.ImgNode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2916214192828658548L, "com/taobao/weex/ui/component/richtext/node/ImgNode$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(918132318977809747L, "com/taobao/weex/ui/component/richtext/node/ImgNode", 41);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ ImgNode(Context context, String str, String str2, AnonymousClass1 r4) {
        this(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[40] = true;
    }

    static class ImgNodeCreator implements RichTextNodeCreator<ImgNode> {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(1534748190959918620L, "com/taobao/weex/ui/component/richtext/node/ImgNode$ImgNodeCreator", 3);
            $jacocoData = a2;
            return a2;
        }

        ImgNodeCreator() {
            $jacocoInit()[0] = true;
        }

        public ImgNode createRichTextNode(Context context, String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            ImgNode imgNode = new ImgNode(context, str, str2, (AnonymousClass1) null);
            $jacocoInit[1] = true;
            return imgNode;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ImgNode(Context context, String str, String str2) {
        super(context, str, str2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public String toString() {
        $jacocoInit()[1] = true;
        return " ";
    }

    /* access modifiers changed from: protected */
    public boolean isInternalNode() {
        $jacocoInit()[2] = true;
        return false;
    }

    /* access modifiers changed from: protected */
    public void updateSpans(SpannableStringBuilder spannableStringBuilder, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(this.mInstanceId);
        $jacocoInit[3] = true;
        if (WXSDKEngine.getDrawableLoader() == null) {
            $jacocoInit[4] = true;
        } else {
            Map map = this.style;
            $jacocoInit[5] = true;
            if (!map.containsKey("width")) {
                $jacocoInit[6] = true;
            } else {
                Map map2 = this.style;
                $jacocoInit[7] = true;
                if (!map2.containsKey("height")) {
                    $jacocoInit[8] = true;
                } else {
                    Map map3 = this.attr;
                    $jacocoInit[9] = true;
                    if (!map3.containsKey("src")) {
                        $jacocoInit[10] = true;
                    } else if (sDKInstance == null) {
                        $jacocoInit[11] = true;
                    } else {
                        $jacocoInit[12] = true;
                        LinkedList linkedList = new LinkedList();
                        $jacocoInit[13] = true;
                        linkedList.add(createImgSpan(sDKInstance));
                        $jacocoInit[14] = true;
                        if (!this.attr.containsKey(RichTextNode.PSEUDO_REF)) {
                            $jacocoInit[15] = true;
                        } else {
                            String str = this.mInstanceId;
                            String str2 = this.mComponentRef;
                            Map map4 = this.attr;
                            $jacocoInit[16] = true;
                            ItemClickSpan itemClickSpan = new ItemClickSpan(str, str2, map4.get(RichTextNode.PSEUDO_REF).toString());
                            $jacocoInit[17] = true;
                            linkedList.add(itemClickSpan);
                            $jacocoInit[18] = true;
                        }
                        $jacocoInit[19] = true;
                        for (Object next : linkedList) {
                            $jacocoInit[21] = true;
                            int length = spannableStringBuilder.length();
                            int createSpanFlag = createSpanFlag(i);
                            $jacocoInit[22] = true;
                            spannableStringBuilder.setSpan(next, 0, length, createSpanFlag);
                            $jacocoInit[23] = true;
                        }
                        $jacocoInit[20] = true;
                    }
                }
            }
        }
        $jacocoInit[24] = true;
    }

    @NonNull
    private ImgSpan createImgSpan(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = WXUtils.getFloat(this.style.get("width"));
        $jacocoInit[25] = true;
        int instanceViewPortWidth = wXSDKInstance.getInstanceViewPortWidth();
        $jacocoInit[26] = true;
        int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(f, instanceViewPortWidth);
        $jacocoInit[27] = true;
        float f2 = WXUtils.getFloat(this.style.get("height"));
        $jacocoInit[28] = true;
        int instanceViewPortWidth2 = wXSDKInstance.getInstanceViewPortWidth();
        $jacocoInit[29] = true;
        int realPxByWidth2 = (int) WXViewUtils.getRealPxByWidth(f2, instanceViewPortWidth2);
        $jacocoInit[30] = true;
        ImgSpan imgSpan = new ImgSpan(realPxByWidth, realPxByWidth2);
        $jacocoInit[31] = true;
        String obj = this.attr.get("src").toString();
        $jacocoInit[32] = true;
        Uri rewriteUri = wXSDKInstance.rewriteUri(Uri.parse(obj), "image");
        $jacocoInit[33] = true;
        if ("local".equals(rewriteUri.getScheme())) {
            $jacocoInit[34] = true;
            Drawable drawableFromLoaclSrc = ImgURIUtil.getDrawableFromLoaclSrc(this.mContext, rewriteUri);
            $jacocoInit[35] = true;
            imgSpan.setDrawable(drawableFromLoaclSrc, false);
            $jacocoInit[36] = true;
        } else {
            DrawableStrategy drawableStrategy = new DrawableStrategy();
            drawableStrategy.width = realPxByWidth;
            drawableStrategy.height = realPxByWidth2;
            $jacocoInit[37] = true;
            WXSDKEngine.getDrawableLoader().setDrawable(rewriteUri.toString(), imgSpan, drawableStrategy);
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
        return imgSpan;
    }
}
