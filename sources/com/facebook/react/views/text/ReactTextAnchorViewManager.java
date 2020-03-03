package com.facebook.react.views.text;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.yoga.YogaConstants;

public abstract class ReactTextAnchorViewManager<T extends View, C extends ReactBaseTextShadowNode> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView reactTextView, @Nullable String str) {
        if (str == null || str.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
        } else if (str.equals(TtmlNode.TAG_HEAD)) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
        } else if (str.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else if (str.equals("clip")) {
            reactTextView.setEllipsizeLocation((TextUtils.TruncateAt) null);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid ellipsizeMode: " + str);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView reactTextView, @Nullable String str) {
        if (str == null || "auto".equals(str)) {
            reactTextView.setGravityVertical(0);
        } else if ("top".equals(str)) {
            reactTextView.setGravityVertical(48);
        } else if ("bottom".equals(str)) {
            reactTextView.setGravityVertical(80);
        } else if ("center".equals(str)) {
            reactTextView.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + str);
        }
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView reactTextView, boolean z) {
        reactTextView.setTextIsSelectable(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView reactTextView, @Nullable Integer num) {
        if (num == null) {
            reactTextView.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactTextView.getContext()));
        } else {
            reactTextView.setHighlightColor(num.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
        } else {
            reactTextView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, @Nullable String str) {
        reactTextView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactTextView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactTextView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView reactTextView, boolean z) {
        reactTextView.setIncludeFontPadding(z);
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactTextView reactTextView, boolean z) {
        reactTextView.setEnabled(!z);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "dataDetectorType")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDataDetectorType(com.facebook.react.views.text.ReactTextView r6, @android.support.annotation.Nullable java.lang.String r7) {
        /*
            r5 = this;
            int r0 = r7.hashCode()
            r1 = 2
            r2 = 1
            r3 = 4
            r4 = 0
            switch(r0) {
                case -1192969641: goto L_0x0034;
                case 96673: goto L_0x002a;
                case 3321850: goto L_0x0020;
                case 3387192: goto L_0x0016;
                case 96619420: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x003e
        L_0x000c:
            java.lang.String r0 = "email"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003e
            r7 = 2
            goto L_0x003f
        L_0x0016:
            java.lang.String r0 = "none"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003e
            r7 = 4
            goto L_0x003f
        L_0x0020:
            java.lang.String r0 = "link"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003e
            r7 = 1
            goto L_0x003f
        L_0x002a:
            java.lang.String r0 = "all"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003e
            r7 = 3
            goto L_0x003f
        L_0x0034:
            java.lang.String r0 = "phoneNumber"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003e
            r7 = 0
            goto L_0x003f
        L_0x003e:
            r7 = -1
        L_0x003f:
            switch(r7) {
                case 0: goto L_0x0054;
                case 1: goto L_0x0050;
                case 2: goto L_0x004c;
                case 3: goto L_0x0046;
                default: goto L_0x0042;
            }
        L_0x0042:
            r6.setLinkifyMask(r4)
            goto L_0x0057
        L_0x0046:
            r7 = 15
            r6.setLinkifyMask(r7)
            goto L_0x0057
        L_0x004c:
            r6.setLinkifyMask(r1)
            goto L_0x0057
        L_0x0050:
            r6.setLinkifyMask(r2)
            goto L_0x0057
        L_0x0054:
            r6.setLinkifyMask(r3)
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextAnchorViewManager.setDataDetectorType(com.facebook.react.views.text.ReactTextView, java.lang.String):void");
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView reactTextView, boolean z) {
        reactTextView.setNotifyOnInlineViewLayout(z);
    }
}
