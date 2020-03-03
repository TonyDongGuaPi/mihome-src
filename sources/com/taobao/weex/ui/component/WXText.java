package com.taobao.weex.ui.component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Layout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.layout.measurefunc.TextContentBoxMeasurement;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.flat.FlatComponent;
import com.taobao.weex.ui.flat.FlatGUIContext;
import com.taobao.weex.ui.flat.widget.TextWidget;
import com.taobao.weex.ui.view.WXTextView;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.TypefaceUtil;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.InvocationTargetException;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXText extends WXComponent<WXTextView> implements FlatComponent<TextWidget> {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int sDEFAULT_SIZE = 32;
    private String mFontFamily;
    private TextWidget mTextWidget;
    private BroadcastReceiver mTypefaceObserver;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(435562531800621454L, "com/taobao/weex/ui/component/WXText", 91);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ String access$000(WXText wXText) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = wXText.mFontFamily;
        $jacocoInit[90] = true;
        return str;
    }

    public boolean promoteToView(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance instance = getInstance();
        if (instance == null) {
            $jacocoInit[0] = true;
            return false;
        }
        FlatGUIContext flatUIContext = instance.getFlatUIContext();
        if (flatUIContext == null) {
            $jacocoInit[1] = true;
            return false;
        }
        boolean promoteToView = flatUIContext.promoteToView(this, z, WXText.class);
        $jacocoInit[2] = true;
        return promoteToView;
    }

    @NonNull
    public TextWidget getOrCreateFlatWidget() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mTextWidget != null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            this.mTextWidget = new TextWidget(getInstance().getFlatUIContext());
            $jacocoInit[5] = true;
        }
        TextWidget textWidget = this.mTextWidget;
        $jacocoInit[6] = true;
        return textWidget;
    }

    public boolean isVirtualComponent() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!promoteToView(true)) {
            $jacocoInit[7] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[8] = true;
        }
        $jacocoInit[9] = true;
        return z;
    }

    public static class Creator implements ComponentCreator {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(258929435769267077L, "com/taobao/weex/ui/component/WXText$Creator", 2);
            $jacocoData = a2;
            return a2;
        }

        public Creator() {
            $jacocoInit()[0] = true;
        }

        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            boolean[] $jacocoInit = $jacocoInit();
            WXText wXText = new WXText(wXSDKInstance, wXVContainer, basicComponentData);
            $jacocoInit[1] = true;
            return wXText;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXText(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[10] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXText(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[11] = true;
        setContentBoxMeasurement(new TextContentBoxMeasurement(this));
        $jacocoInit[12] = true;
    }

    /* access modifiers changed from: protected */
    public WXTextView initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        WXTextView wXTextView = new WXTextView(context);
        $jacocoInit[13] = true;
        wXTextView.holdComponent(this);
        $jacocoInit[14] = true;
        return wXTextView;
    }

    public void updateExtra(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        super.updateExtra(obj);
        if (!(obj instanceof Layout)) {
            $jacocoInit[15] = true;
        } else {
            Layout layout = (Layout) obj;
            $jacocoInit[16] = true;
            if (!promoteToView(true)) {
                $jacocoInit[17] = true;
                getOrCreateFlatWidget().updateTextDrawable(layout);
                $jacocoInit[18] = true;
            } else if (getHostView() == null) {
                $jacocoInit[19] = true;
            } else if (obj.equals(((WXTextView) getHostView()).getTextLayout())) {
                $jacocoInit[20] = true;
            } else {
                $jacocoInit[21] = true;
                ((WXTextView) getHostView()).setTextLayout(layout);
                $jacocoInit[22] = true;
                ((WXTextView) getHostView()).invalidate();
                $jacocoInit[23] = true;
            }
        }
        $jacocoInit[24] = true;
    }

    /* access modifiers changed from: protected */
    public void setAriaLabel(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXTextView wXTextView = (WXTextView) getHostView();
        if (wXTextView == null) {
            $jacocoInit[25] = true;
        } else {
            $jacocoInit[26] = true;
            wXTextView.setAriaLabel(str);
            $jacocoInit[27] = true;
        }
        $jacocoInit[28] = true;
    }

    public void refreshData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.refreshData(wXComponent);
        if (!(wXComponent instanceof WXText)) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            updateExtra(wXComponent.getExtra());
            $jacocoInit[31] = true;
        }
        $jacocoInit[32] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 1
            switch(r1) {
                case -1550943582: goto L_0x00de;
                case -1224696685: goto L_0x00ca;
                case -1065511464: goto L_0x00b7;
                case -879295043: goto L_0x00a4;
                case -734428249: goto L_0x0091;
                case -515807685: goto L_0x007c;
                case 94842723: goto L_0x0067;
                case 102977279: goto L_0x0052;
                case 111972721: goto L_0x003c;
                case 261414991: goto L_0x0027;
                case 365601008: goto L_0x0012;
                default: goto L_0x000c;
            }
        L_0x000c:
            r1 = 33
            r0[r1] = r2
            goto L_0x00f1
        L_0x0012:
            java.lang.String r1 = "fontSize"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0020
            r1 = 36
            r0[r1] = r2
            goto L_0x00f1
        L_0x0020:
            r1 = 37
            r0[r1] = r2
            r1 = 1
            goto L_0x00f2
        L_0x0027:
            java.lang.String r1 = "textOverflow"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0035
            r1 = 48
            r0[r1] = r2
            goto L_0x00f1
        L_0x0035:
            r1 = 7
            r3 = 49
            r0[r3] = r2
            goto L_0x00f2
        L_0x003c:
            java.lang.String r1 = "value"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x004a
            r1 = 52
            r0[r1] = r2
            goto L_0x00f1
        L_0x004a:
            r1 = 9
            r3 = 53
            r0[r3] = r2
            goto L_0x00f2
        L_0x0052:
            java.lang.String r1 = "lines"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0060
            r1 = 34
            r0[r1] = r2
            goto L_0x00f1
        L_0x0060:
            r1 = 0
            r3 = 35
            r0[r3] = r2
            goto L_0x00f2
        L_0x0067:
            java.lang.String r1 = "color"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0075
            r1 = 42
            r0[r1] = r2
            goto L_0x00f1
        L_0x0075:
            r1 = 4
            r3 = 43
            r0[r3] = r2
            goto L_0x00f2
        L_0x007c:
            java.lang.String r1 = "lineHeight"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x008a
            r1 = 50
            r0[r1] = r2
            goto L_0x00f1
        L_0x008a:
            r1 = 8
            r3 = 51
            r0[r3] = r2
            goto L_0x00f2
        L_0x0091:
            java.lang.String r1 = "fontWeight"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x009e
            r1 = 38
            r0[r1] = r2
            goto L_0x00f1
        L_0x009e:
            r1 = 2
            r3 = 39
            r0[r3] = r2
            goto L_0x00f2
        L_0x00a4:
            java.lang.String r1 = "textDecoration"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x00b1
            r1 = 44
            r0[r1] = r2
            goto L_0x00f1
        L_0x00b1:
            r1 = 5
            r3 = 45
            r0[r3] = r2
            goto L_0x00f2
        L_0x00b7:
            java.lang.String r1 = "textAlign"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x00c4
            r1 = 46
            r0[r1] = r2
            goto L_0x00f1
        L_0x00c4:
            r1 = 6
            r3 = 47
            r0[r3] = r2
            goto L_0x00f2
        L_0x00ca:
            java.lang.String r1 = "fontFamily"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x00d7
            r1 = 54
            r0[r1] = r2
            goto L_0x00f1
        L_0x00d7:
            r1 = 10
            r3 = 55
            r0[r3] = r2
            goto L_0x00f2
        L_0x00de:
            java.lang.String r1 = "fontStyle"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x00eb
            r1 = 40
            r0[r1] = r2
            goto L_0x00f1
        L_0x00eb:
            r1 = 3
            r3 = 41
            r0[r3] = r2
            goto L_0x00f2
        L_0x00f1:
            r1 = -1
        L_0x00f2:
            switch(r1) {
                case 0: goto L_0x0119;
                case 1: goto L_0x0119;
                case 2: goto L_0x0119;
                case 3: goto L_0x0119;
                case 4: goto L_0x0119;
                case 5: goto L_0x0119;
                case 6: goto L_0x0119;
                case 7: goto L_0x0119;
                case 8: goto L_0x0119;
                case 9: goto L_0x0119;
                case 10: goto L_0x00fe;
                default: goto L_0x00f5;
            }
        L_0x00f5:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 61
            r0[r6] = r2
            return r5
        L_0x00fe:
            if (r6 != 0) goto L_0x0105
            r5 = 57
            r0[r5] = r2
            goto L_0x0114
        L_0x0105:
            r5 = 58
            r0[r5] = r2
            java.lang.String r5 = r6.toString()
            r4.registerTypefaceObserver(r5)
            r5 = 59
            r0[r5] = r2
        L_0x0114:
            r5 = 60
            r0[r5] = r2
            return r2
        L_0x0119:
            r5 = 56
            r0[r5] = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXText.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object convertEmptyProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 94842723(0x5a72f63, float:1.5722012E-35)
            r3 = 1
            if (r1 == r2) goto L_0x002b
            r2 = 365601008(0x15caa0f0, float:8.1841065E-26)
            if (r1 == r2) goto L_0x0018
            r1 = 62
            r0[r1] = r3
            goto L_0x0037
        L_0x0018:
            java.lang.String r1 = "fontSize"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0025
            r1 = 63
            r0[r1] = r3
            goto L_0x0037
        L_0x0025:
            r1 = 0
            r2 = 64
            r0[r2] = r3
            goto L_0x003e
        L_0x002b:
            java.lang.String r1 = "color"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0039
            r1 = 65
            r0[r1] = r3
        L_0x0037:
            r1 = -1
            goto L_0x003e
        L_0x0039:
            r1 = 66
            r0[r1] = r3
            r1 = 1
        L_0x003e:
            switch(r1) {
                case 0: goto L_0x0051;
                case 1: goto L_0x004a;
                default: goto L_0x0041;
            }
        L_0x0041:
            java.lang.Object r5 = super.convertEmptyProperty(r5, r6)
            r6 = 69
            r0[r6] = r3
            return r5
        L_0x004a:
            java.lang.String r5 = "black"
            r6 = 68
            r0[r6] = r3
            return r5
        L_0x0051:
            r5 = 32
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 67
            r0[r6] = r3
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXText.convertEmptyProperty(java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void createViewImpl() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!promoteToView(true)) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            super.createViewImpl();
            $jacocoInit[72] = true;
        }
        $jacocoInit[73] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        $jacocoInit[74] = true;
        if (WXEnvironment.getApplication() == null) {
            $jacocoInit[75] = true;
        } else if (this.mTypefaceObserver == null) {
            $jacocoInit[76] = true;
        } else {
            $jacocoInit[77] = true;
            WXLogUtils.d("WXText", "Unregister the typeface observer");
            $jacocoInit[78] = true;
            LocalBroadcastManager.getInstance(WXEnvironment.getApplication()).unregisterReceiver(this.mTypefaceObserver);
            this.mTypefaceObserver = null;
            $jacocoInit[79] = true;
        }
        $jacocoInit[80] = true;
    }

    private void registerTypefaceObserver(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXEnvironment.getApplication() == null) {
            $jacocoInit[81] = true;
            WXLogUtils.w("WXText", "ApplicationContent is null on register typeface observer");
            $jacocoInit[82] = true;
            return;
        }
        this.mFontFamily = str;
        if (this.mTypefaceObserver != null) {
            $jacocoInit[83] = true;
            return;
        }
        this.mTypefaceObserver = new BroadcastReceiver(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXText this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(4778083221450988175L, "com/taobao/weex/ui/component/WXText$1", 14);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onReceive(Context context, Intent intent) {
                boolean[] $jacocoInit = $jacocoInit();
                String stringExtra = intent.getStringExtra("fontFamily");
                $jacocoInit[1] = true;
                if (!WXText.access$000(this.this$0).equals(stringExtra)) {
                    $jacocoInit[2] = true;
                    return;
                }
                FontDO fontDO = TypefaceUtil.getFontDO(stringExtra);
                $jacocoInit[3] = true;
                if (fontDO == null) {
                    $jacocoInit[4] = true;
                } else if (fontDO.getTypeface() == null) {
                    $jacocoInit[5] = true;
                } else if (this.this$0.getHostView() == null) {
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[7] = true;
                    $jacocoInit[8] = true;
                    Layout textLayout = ((WXTextView) this.this$0.getHostView()).getTextLayout();
                    if (textLayout != null) {
                        $jacocoInit[9] = true;
                        textLayout.getPaint().setTypeface(fontDO.getTypeface());
                        $jacocoInit[10] = true;
                    } else {
                        WXLogUtils.d("WXText", "Layout not created");
                        $jacocoInit[11] = true;
                    }
                    WXBridgeManager.getInstance().markDirty(this.this$0.getInstanceId(), this.this$0.getRef(), true);
                    $jacocoInit[12] = true;
                }
                $jacocoInit[13] = true;
            }
        };
        $jacocoInit[84] = true;
        LocalBroadcastManager.getInstance(WXEnvironment.getApplication()).registerReceiver(this.mTypefaceObserver, new IntentFilter(TypefaceUtil.ACTION_TYPE_FACE_AVAILABLE));
        $jacocoInit[85] = true;
    }

    /* access modifiers changed from: protected */
    public void layoutDirectionDidChanged(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        forceRelayout();
        $jacocoInit[86] = true;
    }

    private void forceRelayout() {
        boolean[] $jacocoInit = $jacocoInit();
        WXBridgeManager.getInstance().post(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXText this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-464243633510908701L, "com/taobao/weex/ui/component/WXText$2", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!(this.this$0.contentBoxMeasurement instanceof TextContentBoxMeasurement)) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    ((TextContentBoxMeasurement) this.this$0.contentBoxMeasurement).forceRelayout();
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[87] = true;
    }
}
