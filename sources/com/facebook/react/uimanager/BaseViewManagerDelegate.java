package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;

public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    public BaseViewManagerDelegate(U u) {
        this.mViewManager = u;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: boolean} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r5, java.lang.String r6, @android.support.annotation.Nullable java.lang.Object r7) {
        /*
            r4 = this;
            int r0 = r6.hashCode()
            r1 = 0
            switch(r0) {
                case -1721943862: goto L_0x012a;
                case -1721943861: goto L_0x011e;
                case -1267206133: goto L_0x0113;
                case -1228066334: goto L_0x0108;
                case -908189618: goto L_0x00fd;
                case -908189617: goto L_0x00f2;
                case -877170387: goto L_0x00e6;
                case -731417480: goto L_0x00da;
                case -101663499: goto L_0x00d0;
                case -101359900: goto L_0x00c5;
                case -80891667: goto L_0x00b9;
                case -40300674: goto L_0x00ad;
                case -4379043: goto L_0x00a1;
                case 36255470: goto L_0x0096;
                case 333432965: goto L_0x008a;
                case 581268560: goto L_0x007e;
                case 588239831: goto L_0x0072;
                case 746986311: goto L_0x0066;
                case 1052666732: goto L_0x0059;
                case 1146842694: goto L_0x004e;
                case 1153872867: goto L_0x0043;
                case 1287124693: goto L_0x0038;
                case 1349188574: goto L_0x002c;
                case 1410320624: goto L_0x0021;
                case 1505602511: goto L_0x0016;
                case 2045685618: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0136
        L_0x000a:
            java.lang.String r0 = "nativeID"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 15
            goto L_0x0137
        L_0x0016:
            java.lang.String r0 = "accessibilityActions"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 0
            goto L_0x0137
        L_0x0021:
            java.lang.String r0 = "accessibilityStates"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 6
            goto L_0x0137
        L_0x002c:
            java.lang.String r0 = "borderRadius"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 8
            goto L_0x0137
        L_0x0038:
            java.lang.String r0 = "backgroundColor"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 7
            goto L_0x0137
        L_0x0043:
            java.lang.String r0 = "accessibilityState"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 5
            goto L_0x0137
        L_0x004e:
            java.lang.String r0 = "accessibilityLabel"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 2
            goto L_0x0137
        L_0x0059:
            java.lang.String r0 = "transform"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 22
            goto L_0x0137
        L_0x0066:
            java.lang.String r0 = "importantForAccessibility"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 14
            goto L_0x0137
        L_0x0072:
            java.lang.String r0 = "borderBottomRightRadius"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 10
            goto L_0x0137
        L_0x007e:
            java.lang.String r0 = "borderBottomLeftRadius"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 9
            goto L_0x0137
        L_0x008a:
            java.lang.String r0 = "borderTopRightRadius"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 12
            goto L_0x0137
        L_0x0096:
            java.lang.String r0 = "accessibilityLiveRegion"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 3
            goto L_0x0137
        L_0x00a1:
            java.lang.String r0 = "elevation"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 13
            goto L_0x0137
        L_0x00ad:
            java.lang.String r0 = "rotation"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 18
            goto L_0x0137
        L_0x00b9:
            java.lang.String r0 = "renderToHardwareTextureAndroid"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 17
            goto L_0x0137
        L_0x00c5:
            java.lang.String r0 = "accessibilityRole"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 4
            goto L_0x0137
        L_0x00d0:
            java.lang.String r0 = "accessibilityHint"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 1
            goto L_0x0137
        L_0x00da:
            java.lang.String r0 = "zIndex"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 25
            goto L_0x0137
        L_0x00e6:
            java.lang.String r0 = "testID"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 21
            goto L_0x0137
        L_0x00f2:
            java.lang.String r0 = "scaleY"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 20
            goto L_0x0137
        L_0x00fd:
            java.lang.String r0 = "scaleX"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 19
            goto L_0x0137
        L_0x0108:
            java.lang.String r0 = "borderTopLeftRadius"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 11
            goto L_0x0137
        L_0x0113:
            java.lang.String r0 = "opacity"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 16
            goto L_0x0137
        L_0x011e:
            java.lang.String r0 = "translateY"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 24
            goto L_0x0137
        L_0x012a:
            java.lang.String r0 = "translateX"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0136
            r6 = 23
            goto L_0x0137
        L_0x0136:
            r6 = -1
        L_0x0137:
            r0 = 1065353216(0x3f800000, float:1.0)
            r2 = 0
            r3 = 2143289344(0x7fc00000, float:NaN)
            switch(r6) {
                case 0: goto L_0x0282;
                case 1: goto L_0x027a;
                case 2: goto L_0x0272;
                case 3: goto L_0x026a;
                case 4: goto L_0x0262;
                case 5: goto L_0x025a;
                case 6: goto L_0x0252;
                case 7: goto L_0x0243;
                case 8: goto L_0x0234;
                case 9: goto L_0x0225;
                case 10: goto L_0x0215;
                case 11: goto L_0x0205;
                case 12: goto L_0x01f5;
                case 13: goto L_0x01e5;
                case 14: goto L_0x01dc;
                case 15: goto L_0x01d3;
                case 16: goto L_0x01c3;
                case 17: goto L_0x01b3;
                case 18: goto L_0x01a3;
                case 19: goto L_0x0193;
                case 20: goto L_0x0183;
                case 21: goto L_0x017a;
                case 22: goto L_0x0171;
                case 23: goto L_0x0161;
                case 24: goto L_0x0151;
                case 25: goto L_0x0141;
                default: goto L_0x013f;
            }
        L_0x013f:
            goto L_0x0289
        L_0x0141:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0146
            goto L_0x014c
        L_0x0146:
            java.lang.Double r7 = (java.lang.Double) r7
            float r2 = r7.floatValue()
        L_0x014c:
            r6.setZIndex(r5, r2)
            goto L_0x0289
        L_0x0151:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0156
            goto L_0x015c
        L_0x0156:
            java.lang.Double r7 = (java.lang.Double) r7
            float r2 = r7.floatValue()
        L_0x015c:
            r6.setTranslateY(r5, r2)
            goto L_0x0289
        L_0x0161:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0166
            goto L_0x016c
        L_0x0166:
            java.lang.Double r7 = (java.lang.Double) r7
            float r2 = r7.floatValue()
        L_0x016c:
            r6.setTranslateX(r5, r2)
            goto L_0x0289
        L_0x0171:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setTransform(r5, r7)
            goto L_0x0289
        L_0x017a:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setTestId(r5, r7)
            goto L_0x0289
        L_0x0183:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0188
            goto L_0x018e
        L_0x0188:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x018e:
            r6.setScaleY(r5, r0)
            goto L_0x0289
        L_0x0193:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0198
            goto L_0x019e
        L_0x0198:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x019e:
            r6.setScaleX(r5, r0)
            goto L_0x0289
        L_0x01a3:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01a8
            goto L_0x01ae
        L_0x01a8:
            java.lang.Double r7 = (java.lang.Double) r7
            float r2 = r7.floatValue()
        L_0x01ae:
            r6.setRotation(r5, r2)
            goto L_0x0289
        L_0x01b3:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01b8
            goto L_0x01be
        L_0x01b8:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x01be:
            r6.setRenderToHardwareTexture(r5, r1)
            goto L_0x0289
        L_0x01c3:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01c8
            goto L_0x01ce
        L_0x01c8:
            java.lang.Double r7 = (java.lang.Double) r7
            float r0 = r7.floatValue()
        L_0x01ce:
            r6.setOpacity(r5, r0)
            goto L_0x0289
        L_0x01d3:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setNativeId(r5, r7)
            goto L_0x0289
        L_0x01dc:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setImportantForAccessibility(r5, r7)
            goto L_0x0289
        L_0x01e5:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01ea
            goto L_0x01f0
        L_0x01ea:
            java.lang.Double r7 = (java.lang.Double) r7
            float r2 = r7.floatValue()
        L_0x01f0:
            r6.setElevation(r5, r2)
            goto L_0x0289
        L_0x01f5:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01fa
            goto L_0x0200
        L_0x01fa:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x0200:
            r6.setBorderTopRightRadius(r5, r3)
            goto L_0x0289
        L_0x0205:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x020a
            goto L_0x0210
        L_0x020a:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x0210:
            r6.setBorderTopLeftRadius(r5, r3)
            goto L_0x0289
        L_0x0215:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x021a
            goto L_0x0220
        L_0x021a:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x0220:
            r6.setBorderBottomRightRadius(r5, r3)
            goto L_0x0289
        L_0x0225:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x022a
            goto L_0x0230
        L_0x022a:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x0230:
            r6.setBorderBottomLeftRadius(r5, r3)
            goto L_0x0289
        L_0x0234:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0239
            goto L_0x023f
        L_0x0239:
            java.lang.Double r7 = (java.lang.Double) r7
            float r3 = r7.floatValue()
        L_0x023f:
            r6.setBorderRadius(r5, r3)
            goto L_0x0289
        L_0x0243:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0248
            goto L_0x024e
        L_0x0248:
            java.lang.Double r7 = (java.lang.Double) r7
            int r1 = r7.intValue()
        L_0x024e:
            r6.setBackgroundColor(r5, r1)
            goto L_0x0289
        L_0x0252:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setViewStates(r5, r7)
            goto L_0x0289
        L_0x025a:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableMap r7 = (com.facebook.react.bridge.ReadableMap) r7
            r6.setViewState(r5, r7)
            goto L_0x0289
        L_0x0262:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityRole(r5, r7)
            goto L_0x0289
        L_0x026a:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityLiveRegion(r5, r7)
            goto L_0x0289
        L_0x0272:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityLabel(r5, r7)
            goto L_0x0289
        L_0x027a:
            U r6 = r4.mViewManager
            java.lang.String r7 = (java.lang.String) r7
            r6.setAccessibilityHint(r5, r7)
            goto L_0x0289
        L_0x0282:
            U r6 = r4.mViewManager
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setAccessibilityActions(r5, r7)
        L_0x0289:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BaseViewManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
