package com.taobao.weex.ui.component;

import android.text.TextUtils;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXEditText;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Textarea extends AbstractEditComponent {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int DEFAULT_ROWS = 2;
    private int mNumberOfLines = 2;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8529902808136462285L, "com/taobao/weex/ui/component/Textarea", 41);
        $jacocoData = a2;
        return a2;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void onHostViewInitialized(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        onHostViewInitialized((WXEditText) view);
        $jacocoInit[40] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Textarea(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public void onHostViewInitialized(WXEditText wXEditText) {
        boolean[] $jacocoInit = $jacocoInit();
        wXEditText.setAllowDisableMovement(false);
        $jacocoInit[1] = true;
        super.onHostViewInitialized(wXEditText);
        $jacocoInit[2] = true;
    }

    /* access modifiers changed from: protected */
    public void appleStyleAfterCreated(WXEditText wXEditText) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        super.appleStyleAfterCreated(wXEditText);
        $jacocoInit[3] = true;
        String str = (String) getStyles().get("rows");
        int i2 = 2;
        try {
            $jacocoInit[4] = true;
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[5] = true;
                i = 2;
            } else {
                $jacocoInit[6] = true;
                i = Integer.parseInt(str);
                try {
                    $jacocoInit[7] = true;
                } catch (NumberFormatException e) {
                    i2 = i;
                    e = e;
                    $jacocoInit[9] = true;
                    e.printStackTrace();
                    $jacocoInit[10] = true;
                    i = i2;
                    wXEditText.setLines(i);
                    $jacocoInit[11] = true;
                    wXEditText.setMinLines(i);
                    $jacocoInit[12] = true;
                }
            }
            $jacocoInit[8] = true;
        } catch (NumberFormatException e2) {
            e = e2;
            $jacocoInit[9] = true;
            e.printStackTrace();
            $jacocoInit[10] = true;
            i = i2;
            wXEditText.setLines(i);
            $jacocoInit[11] = true;
            wXEditText.setMinLines(i);
            $jacocoInit[12] = true;
        }
        wXEditText.setLines(i);
        $jacocoInit[11] = true;
        wXEditText.setMinLines(i);
        $jacocoInit[12] = true;
    }

    /* access modifiers changed from: protected */
    public int getVerticalGravity() {
        $jacocoInit()[13] = true;
        return 48;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 1
            r3 = 3506649(0x3581d9, float:4.913862E-39)
            if (r1 == r3) goto L_0x0013
            r1 = 14
            r0[r1] = r2
            goto L_0x001f
        L_0x0013:
            java.lang.String r1 = "rows"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 15
            r0[r1] = r2
        L_0x001f:
            r1 = -1
            goto L_0x0026
        L_0x0021:
            r1 = 0
            r3 = 16
            r0[r3] = r2
        L_0x0026:
            if (r1 == 0) goto L_0x0031
            boolean r5 = super.setProperty(r5, r6)
            r6 = 21
            r0[r6] = r2
            return r5
        L_0x0031:
            r5 = 0
            java.lang.Integer r5 = com.taobao.weex.utils.WXUtils.getInteger(r6, r5)
            if (r5 != 0) goto L_0x003d
            r5 = 17
            r0[r5] = r2
            goto L_0x004c
        L_0x003d:
            r6 = 18
            r0[r6] = r2
            int r5 = r5.intValue()
            r4.setRows(r5)
            r5 = 19
            r0[r5] = r2
        L_0x004c:
            r5 = 20
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.Textarea.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "rows")
    public void setRows(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        WXEditText wXEditText = (WXEditText) getHostView();
        if (wXEditText == null) {
            $jacocoInit[22] = true;
        } else if (i <= 0) {
            $jacocoInit[23] = true;
        } else {
            wXEditText.setLines(i);
            $jacocoInit[25] = true;
            return;
        }
        $jacocoInit[24] = true;
    }

    /* access modifiers changed from: protected */
    public float getMeasureHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        float measuredLineHeight = getMeasuredLineHeight() * ((float) this.mNumberOfLines);
        $jacocoInit[26] = true;
        return measuredLineHeight;
    }

    /* access modifiers changed from: protected */
    public void updateStyleAndAttrs() {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateStyleAndAttrs();
        $jacocoInit[27] = true;
        Object obj = getAttrs().get("rows");
        if (obj == null) {
            $jacocoInit[28] = true;
            return;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            try {
                $jacocoInit[29] = true;
                int parseInt = Integer.parseInt(str);
                if (parseInt <= 0) {
                    $jacocoInit[30] = true;
                } else {
                    this.mNumberOfLines = parseInt;
                    $jacocoInit[31] = true;
                }
                $jacocoInit[32] = true;
            } catch (NumberFormatException e) {
                $jacocoInit[33] = true;
                e.printStackTrace();
                $jacocoInit[34] = true;
            }
            $jacocoInit[35] = true;
        } else if (!(obj instanceof Integer)) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            this.mNumberOfLines = ((Integer) obj).intValue();
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
    }
}
