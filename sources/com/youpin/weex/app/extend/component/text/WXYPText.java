package com.youpin.weex.app.extend.component.text;

import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.Layout;
import android.text.TextPaint;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXTextView;
import java.lang.reflect.InvocationTargetException;

@Component(lazyload = false)
public class WXYPText extends WXText {
    private int beginColor = -16777216;
    private int colorDirection = 0;
    private boolean colourful = false;
    private int endColor = -16777216;
    private Rect mTextBound = new Rect();

    public static class Creator implements ComponentCreator {
        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            return new WXYPText(wXSDKInstance, wXVContainer, basicComponentData);
        }
    }

    public WXYPText(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r6, java.lang.Object r7) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 1
            switch(r0) {
                case -1737058468: goto L_0x00a2;
                case -1550943582: goto L_0x0098;
                case -1224696685: goto L_0x008d;
                case -1065511464: goto L_0x0083;
                case -879295043: goto L_0x0079;
                case -734428249: goto L_0x006f;
                case -515807685: goto L_0x0064;
                case 94842723: goto L_0x005a;
                case 102977279: goto L_0x0050;
                case 111972721: goto L_0x0045;
                case 261414991: goto L_0x003a;
                case 365601008: goto L_0x002f;
                case 1086179386: goto L_0x0023;
                case 1699546952: goto L_0x0017;
                case 1984588849: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x00ad
        L_0x000b:
            java.lang.String r0 = "colourful"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 14
            goto L_0x00ae
        L_0x0017:
            java.lang.String r0 = "endColor"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 12
            goto L_0x00ae
        L_0x0023:
            java.lang.String r0 = "beginColor"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 11
            goto L_0x00ae
        L_0x002f:
            java.lang.String r0 = "fontSize"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 1
            goto L_0x00ae
        L_0x003a:
            java.lang.String r0 = "textOverflow"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 7
            goto L_0x00ae
        L_0x0045:
            java.lang.String r0 = "value"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 9
            goto L_0x00ae
        L_0x0050:
            java.lang.String r0 = "lines"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 0
            goto L_0x00ae
        L_0x005a:
            java.lang.String r0 = "color"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 4
            goto L_0x00ae
        L_0x0064:
            java.lang.String r0 = "lineHeight"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 8
            goto L_0x00ae
        L_0x006f:
            java.lang.String r0 = "fontWeight"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 2
            goto L_0x00ae
        L_0x0079:
            java.lang.String r0 = "textDecoration"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 5
            goto L_0x00ae
        L_0x0083:
            java.lang.String r0 = "textAlign"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 6
            goto L_0x00ae
        L_0x008d:
            java.lang.String r0 = "fontFamily"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 10
            goto L_0x00ae
        L_0x0098:
            java.lang.String r0 = "fontStyle"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 3
            goto L_0x00ae
        L_0x00a2:
            java.lang.String r0 = "colorDirection"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00ad
            r0 = 13
            goto L_0x00ae
        L_0x00ad:
            r0 = -1
        L_0x00ae:
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r4 = 0
            switch(r0) {
                case 0: goto L_0x00fe;
                case 1: goto L_0x00fe;
                case 2: goto L_0x00fe;
                case 3: goto L_0x00fe;
                case 4: goto L_0x00fe;
                case 5: goto L_0x00fe;
                case 6: goto L_0x00fe;
                case 7: goto L_0x00fe;
                case 8: goto L_0x00fe;
                case 9: goto L_0x00fe;
                case 10: goto L_0x00fd;
                case 11: goto L_0x00e9;
                case 12: goto L_0x00d5;
                case 13: goto L_0x00cb;
                case 14: goto L_0x00b9;
                default: goto L_0x00b4;
            }
        L_0x00b4:
            boolean r6 = super.setProperty(r6, r7)
            return r6
        L_0x00b9:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            java.lang.Boolean r6 = com.taobao.weex.utils.WXUtils.getBoolean(r7, r6)
            boolean r6 = r6.booleanValue()
            r5.colourful = r6
            r5.invaliteHostView()
            return r2
        L_0x00cb:
            int r6 = com.taobao.weex.utils.WXUtils.getInt(r7)
            r5.colorDirection = r6
            r5.invaliteHostView()
            return r2
        L_0x00d5:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00e5
            int r6 = com.taobao.weex.utils.WXResourceUtils.getColor(r6, r3)
            r5.endColor = r6
        L_0x00e5:
            r5.invaliteHostView()
            return r2
        L_0x00e9:
            java.lang.String r6 = com.taobao.weex.utils.WXUtils.getString(r7, r4)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00f9
            int r6 = com.taobao.weex.utils.WXResourceUtils.getColor(r6, r3)
            r5.beginColor = r6
        L_0x00f9:
            r5.invaliteHostView()
            return r2
        L_0x00fd:
            return r2
        L_0x00fe:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.component.text.WXYPText.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    private void invaliteHostView() {
        Layout textLayout;
        WXTextView wXTextView = (WXTextView) getHostView();
        if (wXTextView != null && (textLayout = wXTextView.getTextLayout()) != null) {
            TextPaint paint = textLayout.getPaint();
            if (!this.colourful) {
                paint.setShader((Shader) null);
                ((WXTextView) getHostView()).invalidate();
                return;
            }
            String charSequence = ((WXTextView) getHostView()).getText().toString();
            paint.getTextBounds(charSequence, 0, charSequence.length(), this.mTextBound);
            float[] fArr = {0.0f, 0.0f};
            float[] fArr2 = {1.0f, 0.0f};
            int i = this.colorDirection;
            if (i == 45) {
                fArr[0] = 0.0f;
                fArr[1] = 1.0f;
                fArr2[0] = 1.0f;
                fArr2[1] = 0.0f;
            } else if (i == 90) {
                fArr[0] = 0.0f;
                fArr[1] = 1.0f;
                fArr2[0] = 0.0f;
                fArr2[1] = 0.0f;
            } else if (i == 135) {
                fArr[0] = 1.0f;
                fArr[1] = 1.0f;
                fArr2[0] = 0.0f;
                fArr2[1] = 0.0f;
            } else if (i == 180) {
                fArr[0] = 1.0f;
                fArr[1] = 0.0f;
                fArr2[0] = 0.0f;
                fArr2[1] = 0.0f;
            } else if (i == 225) {
                fArr[0] = 1.0f;
                fArr[1] = 0.0f;
                fArr2[0] = 0.0f;
                fArr2[1] = 1.0f;
            } else if (i == 270) {
                fArr[0] = 0.0f;
                fArr[1] = 0.0f;
                fArr2[0] = 0.0f;
                fArr2[1] = 1.0f;
            } else if (i != 315) {
                fArr[0] = 0.0f;
                fArr[1] = 0.0f;
                fArr2[0] = 1.0f;
                fArr2[1] = 0.0f;
            } else {
                fArr[0] = 0.0f;
                fArr[1] = 0.0f;
                fArr2[0] = 1.0f;
                fArr2[1] = 1.0f;
            }
            int width = this.mTextBound.width();
            int i2 = ((WXTextView) getHostView()).getLayoutParams().width;
            int lineCount = textLayout.getLineCount();
            float f = 0.0f;
            for (int i3 = 0; i3 < lineCount; i3++) {
                float lineWidth = textLayout.getLineWidth(i3);
                if (lineWidth > f) {
                    f = lineWidth;
                }
            }
            if (i2 != 0 && width > i2) {
                width = i2;
            }
            float f2 = (float) width;
            if (f <= f2) {
                f2 = f;
            }
            float f3 = (float) ((int) f2);
            float f4 = (float) ((WXTextView) getHostView()).getLayoutParams().height;
            paint.setShader(new LinearGradient(fArr[0] * f3, fArr[1] * f4, fArr2[0] * f3, fArr2[1] * f4, new int[]{this.beginColor, this.endColor}, (float[]) null, Shader.TileMode.CLAMP));
            ((WXTextView) getHostView()).invalidate();
        }
    }

    public void updateExtra(Object obj) {
        super.updateExtra(obj);
        invaliteHostView();
    }

    public void refreshData(WXComponent wXComponent) {
        super.refreshData(wXComponent);
        invaliteHostView();
    }
}
