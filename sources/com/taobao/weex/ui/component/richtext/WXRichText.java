package com.taobao.weex.ui.component.richtext;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import java.lang.reflect.InvocationTargetException;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXRichText extends WXText {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4925515845259630350L, "com/taobao/weex/ui/component/richtext/WXRichText", 5);
        $jacocoData = a2;
        return a2;
    }

    static class RichTextContentBoxMeasurement extends TextContentBoxMeasurement {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2061009802980873754L, "com/taobao/weex/ui/component/richtext/WXRichText$RichTextContentBoxMeasurement", 15);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RichTextContentBoxMeasurement(WXComponent wXComponent) {
            super(wXComponent);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
        }

        /* access modifiers changed from: protected */
        @NonNull
        public Spanned createSpanned(String str) {
            boolean z;
            boolean z2;
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mComponent.getInstance() != null) {
                $jacocoInit[1] = true;
                z = true;
            } else {
                $jacocoInit[2] = true;
                z = false;
            }
            if (this.mComponent.getInstance().getUIContext() != null) {
                $jacocoInit[3] = true;
                z2 = true;
            } else {
                $jacocoInit[4] = true;
                z2 = false;
            }
            if (!z || !z2) {
                $jacocoInit[5] = true;
            } else {
                WXComponent wXComponent = this.mComponent;
                $jacocoInit[6] = true;
                if (TextUtils.isEmpty(wXComponent.getInstanceId())) {
                    $jacocoInit[7] = true;
                } else {
                    WXComponent wXComponent2 = this.mComponent;
                    $jacocoInit[8] = true;
                    Context uIContext = wXComponent2.getInstance().getUIContext();
                    WXComponent wXComponent3 = this.mComponent;
                    $jacocoInit[9] = true;
                    String instanceId = wXComponent3.getInstanceId();
                    WXComponent wXComponent4 = this.mComponent;
                    $jacocoInit[10] = true;
                    String ref = wXComponent4.getRef();
                    $jacocoInit[11] = true;
                    Spannable parse = RichTextNode.parse(uIContext, instanceId, ref, str);
                    $jacocoInit[12] = true;
                    updateSpannable(parse, RichTextNode.createSpanFlag(0));
                    $jacocoInit[13] = true;
                    return parse;
                }
            }
            SpannedString spannedString = new SpannedString("");
            $jacocoInit[14] = true;
            return spannedString;
        }
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(3875700289866750891L, "com/taobao/weex/ui/component/richtext/WXRichText$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXRichText wXRichText = new WXRichText(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXRichText;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXRichText(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        setContentBoxMeasurement(new RichTextContentBoxMeasurement(this));
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public WXRichTextView initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRichTextView wXRichTextView = new WXRichTextView(context);
        $jacocoInit[2] = true;
        return wXRichTextView;
    }
}
