package com.taobao.weex.ui.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXSwitchView;
import com.taobao.weex.utils.WXLogUtils;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXSwitch extends WXComponent<WXSwitchView> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private CompoundButton.OnCheckedChangeListener mListener;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5578370434122944397L, "com/taobao/weex/ui/component/WXSwitch", 31);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXSwitch(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSwitch(final WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        setContentBoxMeasurement(new ContentBoxMeasurement(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwitch this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1361825006094565860L, "com/taobao/weex/ui/component/WXSwitch$1", 15);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void measureInternal(float f, float f2, int i, int i2) {
                int i3;
                boolean[] $jacocoInit = $jacocoInit();
                this.mMeasureWidth = 0.0f;
                this.mMeasureHeight = 0.0f;
                try {
                    $jacocoInit[1] = true;
                    WXSwitchView wXSwitchView = new WXSwitchView(wXSDKInstance.getContext());
                    $jacocoInit[2] = true;
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                    $jacocoInit[3] = true;
                    if (Float.isNaN(f)) {
                        $jacocoInit[4] = true;
                        i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
                        $jacocoInit[5] = true;
                    } else {
                        i3 = View.MeasureSpec.makeMeasureSpec((int) f, Integer.MIN_VALUE);
                        $jacocoInit[6] = true;
                    }
                    wXSwitchView.measure(i3, makeMeasureSpec);
                    $jacocoInit[7] = true;
                    this.mMeasureWidth = (float) wXSwitchView.getMeasuredWidth();
                    $jacocoInit[8] = true;
                    this.mMeasureHeight = (float) wXSwitchView.getMeasuredHeight();
                    $jacocoInit[9] = true;
                } catch (RuntimeException e) {
                    $jacocoInit[10] = true;
                    WXLogUtils.e(WXLogUtils.getStackTrace(e));
                    $jacocoInit[11] = true;
                }
                $jacocoInit[12] = true;
            }

            public void layoutBefore() {
                $jacocoInit()[13] = true;
            }

            public void layoutAfter(float f, float f2) {
                $jacocoInit()[14] = true;
            }
        });
        $jacocoInit[2] = true;
    }

    /* access modifiers changed from: protected */
    public WXSwitchView initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSwitchView wXSwitchView = new WXSwitchView(context);
        $jacocoInit[3] = true;
        return wXSwitchView;
    }

    public void addEvent(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.addEvent(str);
        $jacocoInit[4] = true;
        if (str == null) {
            $jacocoInit[5] = true;
        } else if (!str.equals(Constants.Event.CHANGE)) {
            $jacocoInit[6] = true;
        } else if (getHostView() == null) {
            $jacocoInit[7] = true;
        } else {
            if (this.mListener != null) {
                $jacocoInit[8] = true;
            } else {
                $jacocoInit[9] = true;
                this.mListener = new CompoundButton.OnCheckedChangeListener(this) {
                    private static transient /* synthetic */ boolean[] $jacocoData;
                    final /* synthetic */ WXSwitch this$0;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-1658748613664052416L, "com/taobao/weex/ui/component/WXSwitch$2", 8);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        this.this$0 = r3;
                        $jacocoInit[0] = true;
                    }

                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        boolean[] $jacocoInit = $jacocoInit();
                        HashMap hashMap = new HashMap(2);
                        $jacocoInit[1] = true;
                        hashMap.put("value", Boolean.valueOf(z));
                        $jacocoInit[2] = true;
                        HashMap hashMap2 = new HashMap();
                        $jacocoInit[3] = true;
                        HashMap hashMap3 = new HashMap();
                        $jacocoInit[4] = true;
                        hashMap3.put("checked", Boolean.toString(z));
                        $jacocoInit[5] = true;
                        hashMap2.put("attrs", hashMap3);
                        $jacocoInit[6] = true;
                        this.this$0.fireEvent(Constants.Event.CHANGE, hashMap, hashMap2);
                        $jacocoInit[7] = true;
                    }
                };
                $jacocoInit[10] = true;
            }
            ((WXSwitchView) getHostView()).setOnCheckedChangeListener(this.mListener);
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
    }

    /* access modifiers changed from: protected */
    public void removeEventFromView(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        super.removeEventFromView(str);
        $jacocoInit[13] = true;
        if (getHostView() == null) {
            $jacocoInit[14] = true;
        } else if (!Constants.Event.CHANGE.equals(str)) {
            $jacocoInit[15] = true;
        } else {
            $jacocoInit[16] = true;
            ((WXSwitchView) getHostView()).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
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
            r3 = 742313895(0x2c3ecfa7, float:2.7115894E-12)
            if (r1 == r3) goto L_0x0013
            r1 = 19
            r0[r1] = r2
            goto L_0x001f
        L_0x0013:
            java.lang.String r1 = "checked"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0021
            r1 = 20
            r0[r1] = r2
        L_0x001f:
            r1 = -1
            goto L_0x0026
        L_0x0021:
            r1 = 0
            r3 = 21
            r0[r3] = r2
        L_0x0026:
            if (r1 == 0) goto L_0x0031
            boolean r5 = super.setProperty(r5, r6)
            r6 = 26
            r0[r6] = r2
            return r5
        L_0x0031:
            r5 = 0
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r5)
            if (r5 != 0) goto L_0x003d
            r5 = 22
            r0[r5] = r2
            goto L_0x004c
        L_0x003d:
            r6 = 23
            r0[r6] = r2
            boolean r5 = r5.booleanValue()
            r4.setChecked(r5)
            r5 = 24
            r0[r5] = r2
        L_0x004c:
            r5 = 25
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXSwitch.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "checked")
    public void setChecked(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        ((WXSwitchView) getHostView()).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        $jacocoInit[27] = true;
        ((WXSwitchView) getHostView()).setChecked(z);
        $jacocoInit[28] = true;
        ((WXSwitchView) getHostView()).setOnCheckedChangeListener(this.mListener);
        $jacocoInit[29] = true;
    }
}
